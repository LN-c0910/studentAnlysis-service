package cn.csust.lingyi.service.impl;

import cn.csust.lingyi.bo.*;
import cn.csust.lingyi.common.VO.PageResult;
import cn.csust.lingyi.common.VO.ResultVo;
import cn.csust.lingyi.common.utils.Utils;
import cn.csust.lingyi.mapper.PersonsummaryMapper;
import cn.csust.lingyi.mapper.SkillMapper;
import cn.csust.lingyi.mapper.StudentMapper;
import cn.csust.lingyi.pojo.Personsummary;
import cn.csust.lingyi.pojo.Student;
import cn.csust.lingyi.service.ClassesService;
import cn.csust.lingyi.service.PersonalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.csust.lingyi.service.impl.DashboardServiceImpl.NATIONRGEX;

/**
 * Created by Enzo Cotter on 2020/4/2
 * @author linan
 */
@Service
public class ClassesServiceImpl implements ClassesService {
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SkillMapper skillMapper;

    @Autowired
    PersonsummaryMapper personsummaryMapper;

    @Autowired
    PersonalService personalService;

    public ClassesServiceImpl() {
    }

    /**
     * 获取年级-专业-班级映射关系表
     * @return 映射关系列表
     */
    @Override
    public List<ClassesMapping> queryClassesMapping() {
        List<ClassesMapping> classesMappings = new ArrayList<>();
        //需要排除的学生类型
        String removeStuType = "研究生";
        //需要排除的年级
        String removeTermYear = "2014";

        //获取年级列表
        List<String> grades = this.studentMapper.listGrades();
        grades.forEach(g -> {
            //排除2014级和研究生
            if (StringUtils.contains(g,removeStuType)||removeTermYear.equals(g)){
                return;
            }
            List<String> major = this.studentMapper.queryMajorNameByTermYear(g);
            ClassesMapping classesMapping = new ClassesMapping();
            classesMapping.setTermYear(g);
            List<Major_and_Classes> majorMapping = new ArrayList<>();
            //获取专业与班级映射关系
            major.forEach(m -> {
                List<Integer> classes = this.studentMapper.queryClassNoByTermYearAndMajor(g,m);
                Major_and_Classes majorAndClasses = new Major_and_Classes();
                majorAndClasses.setMajorName(m);
                majorAndClasses.setClasses(classes);
                majorMapping.add(majorAndClasses);
            });
            classesMapping.setMajor(majorMapping);
            classesMappings.add(classesMapping);
        });

        return classesMappings;
    }


    /**
     * 根据班级查询某技能证书级通过率
     * @param termYear 学年
     * @param major 专业
     * @param classNo 班级
     * @param skillType 技能类型
     * @return 4/6级通过率
     */
    @Override
    @Transactional
    public Double queryCET4or6PassRate(String termYear, String major, Integer classNo, SkillType skillType) {
        //统计班级人数
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("termyear",termYear);
        criteria.andEqualTo("major",major);
        if (classNo != null){
            criteria.andEqualTo("Class",classNo);
        }
        Integer member = this.studentMapper.selectCountByExample(example);

        //如果班级人数为0,返回结果-1d
        if (member == 0){
            return -1d;
        }
        //统计班级通过率
        Integer passNum = this.skillMapper.CETCountByClass(termYear, major, classNo, skillType.getName());

        //计算通过率并返回结果
        return new BigDecimal((float)passNum/member).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

    }


    @Override
    public Integer querySizeByClass(String termYear, String major, Integer classNo) {
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("termyear",termYear);
        criteria.andEqualTo("major",major);
        if (classNo != null){
            criteria.andEqualTo("Class",classNo);
        }
        return this.studentMapper.selectCountByExample(example);

    }

    @Override
    public Integer querySexByClass(SexEnum sex,String termYear, String major,  Integer classNo) {
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("termyear",termYear);
        criteria.andEqualTo("major",major);
        if (classNo != null){
            criteria.andEqualTo("Class",classNo);
        }
        criteria.andEqualTo("sex",sex.getName());
        return this.studentMapper.selectCountByExample(example);

    }

    @Override
    public  List<Nation> queryNationByClass(String termYear, String major, Integer classNo) {
        List<Nation> nationNumByClass = this.studentMapper.getNationNumByClass(termYear, major, classNo);
        if (CollectionUtils.isEmpty(nationNumByClass)){
            return null;
        }
        Map<String, Integer> wrongnations = new HashMap<>(16);
        Map<String, Integer> correctnations = new HashMap<>(16);
        nationNumByClass.forEach(nation -> {
            //检出错误民族
            if (nation.getMzname().matches(NATIONRGEX)){
                String corr = nation.getMzname() + "族";
                wrongnations.put(corr,nation.getMznumber());
            }else {
                correctnations.put(nation.getMzname(),nation.getMznumber());
            }
        });
        wrongnations.forEach((key,value) -> correctnations.merge(key,value,Integer::sum));
        List<Nation> nations = new ArrayList<>();
        correctnations.forEach((v,k) -> nations.add(new Nation(v,k)));
        return nations;
    }

    @Override
    public List<PoliticalStatus> queryPoliticalStatusByClass(String termYear, String major, Integer classNo) {
        return this.studentMapper.psCountByClass(termYear, major, classNo);
    }

    @Override
    public String queryProvinceByClass(String termYear, String major, Integer classNo) {
        List<String> addressByClass = this.studentMapper.queryAddressByClass(termYear, major, classNo);
        Map<String, List<String>> map = new HashMap<>(16);
        map.put("data", addressByClass);
        ResultVo resultVo = Utils.sendPost(Utils.DATA_PROCESSING_URL+"/addressMap", map);
        if (resultVo == null){
            return "数据处理服务器错误";
        }
        if (resultVo.getCode() == 404){
            return "暂无记录";
        }
        return resultVo.getData().toString();
    }

    @Override
    public PageResult<Student> queryStudentByClass(String termYear, String major,
                                                   Integer classNo,Integer page,Integer rows) {
        //添加查询条件
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        example.orderBy("studentno");
        if (termYear != null){
            criteria.andEqualTo("termyear",termYear);
            if (major != null){
                criteria.andEqualTo("major",major);
                if (classNo != null){
                    criteria.andEqualTo("Class",classNo);
                }
            }
        }
        //添加分页
        PageHelper.startPage(page,rows);
        //执行查询,获取student集合
        List<Student> students = this.studentMapper.selectByExample(example);
        PageInfo<Student> studentPageInfo = new PageInfo<>(students);
        return new PageResult<>(studentPageInfo.getTotal(),students);
    }

    @Override
    public Double avgScoreOfClass(String termYear, String major, Integer classNo,String xn) {
        //lambda表达式中不允许修改使用的（外部）变量，无法改变final量的值
        //数组是final的（即该数组不能再指向其他数组对象），里面的值依旧可变
        final Double[] avgScoreOfClass = new Double[1];
        List<ClassRank> classRanks = this.personsummaryMapper.queryClassRank(termYear, xn);
        classRanks.forEach(classRank -> {
            if (major.equals(classRank.getMajor()) && classNo.equals(classRank.getClassNo())){
                 avgScoreOfClass[0] = classRank.getAvgs();
            }
        });
        //无综测数据或班级不存在返回-1
        if (avgScoreOfClass[0] == null){
            return -1D;
        }
        return avgScoreOfClass[0];
    }

    @Override
    public Integer avgScoreRankOfClass(String termYear, String xn, String major, Integer classNo) {
        final Integer[] avgScoreRankOfClass = new Integer[1];
        List<ClassRank> classRanks = this.personsummaryMapper.queryClassRank(termYear, xn);
        classRanks.forEach(classRank -> {
            if (major.equals(classRank.getMajor()) && classNo.equals(classRank.getClassNo())){
                avgScoreRankOfClass[0] = classRank.getClassRank();
            }
        });
        //无综测数据或班级不存在返回-1
        if (avgScoreRankOfClass[0] == null){
            return -1;
        }
        return avgScoreRankOfClass[0];
    }

    @Override
    public Double failsOfClass(String termYear, String xn, String major, Integer classNo) {
        Double rate = this.personsummaryMapper.queryClassFailsRate(termYear, xn, major, classNo);
        //无综测数据或班级不存在返回-1
        if (rate == null){
            return -1D;
        }
        return rate;
    }

    @Override
    public Double topScoreOfClass(String termYear, String xn, String major, Integer classNo) {
        Double topScoreOfClass = this.personsummaryMapper.queryTopScoreOfClass(termYear, xn, major, classNo);
        if (topScoreOfClass == null){
            return -1D;
        }
        return topScoreOfClass;
    }

    @Override
    public PageResult<Personsummary> queryScoreListByClass(
            String termYear, String xn,
            String major, Integer classNo,
            Integer page,Integer rows) {
        //添加分页
        PageHelper.startPage(page,rows);
        List<Personsummary> personsummaries = this.personsummaryMapper.queryScoreListByClass(termYear, xn, major, classNo);
       //判断查询结果为空
        if (CollectionUtils.isEmpty(personsummaries)){
            return null;
        }

        //构造分页信息
        PageInfo<Personsummary> pageInfo = new PageInfo<>(personsummaries);
        return new PageResult<>(pageInfo.getTotal(),personsummaries);

    }

    @Override
    public String getClassDescriptionWorldCloud(String termYear, String major, Integer classNo) {
        //添加查询条件
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        if (termYear != null){
            criteria.andEqualTo("termyear",termYear);
            if (major != null){
                criteria.andEqualTo("major",major);
                if (classNo != null){
                    criteria.andEqualTo("Class",classNo);
                }
            }
        }
        //执行查询 获取班级所有学生
        List<Student> students = this.studentMapper.selectByExample(example);
        //初始化词条集合
        ArrayList<String> strings = new ArrayList<>();
        //查询每个学生的行为词条数据
        students.forEach(student -> {
            String sno = student.getStudentno();
            strings.addAll(this.personalService.getDescriptionList(sno));
        });
        System.out.println(strings);
        //封装请求
        HashMap<String, List<String>> requestMap = new HashMap<>();
        requestMap.put("data",strings);
        ResultVo resultVo = Utils.sendPost(Utils.DATA_PROCESSING_URL + "/stuwordcloud", requestMap);
        if (resultVo.getCode().toString() == "500"){
            return "static/create_failed.png";
        }
        return  (String) resultVo.getData();
    }
}
