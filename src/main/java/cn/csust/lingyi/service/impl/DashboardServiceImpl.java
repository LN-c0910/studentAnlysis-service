package cn.csust.lingyi.service.impl;

import cn.csust.lingyi.bo.Nation;
import cn.csust.lingyi.bo.PoliticalStatus;
import cn.csust.lingyi.common.VO.ResultVo;
import cn.csust.lingyi.common.utils.Utils;
import cn.csust.lingyi.mapper.StudentMapper;
import cn.csust.lingyi.mapper.TeacherMapper;
import cn.csust.lingyi.pojo.Student;
import cn.csust.lingyi.pojo.User;
import cn.csust.lingyi.service.DashboardService ;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;


import java.util.*;

/**
 * Created by Enzo Cotter on 2020/3/15.
 */
@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    RestTemplate restTemplate;

    public static final String NATIONRGEX = "[^族]";


    /*
        显示数据面板
     */
    @Override
    public List<Integer> showBoard() {
        //所有学生数
        int allStus = this.studentMapper.selectCount(null);
        Student student = new Student();
        //本科生数
        student.setStutype("本科生");
        int undergraduates = this.studentMapper.selectCount(student);
        //研究生数
        student.setStutype("研究生");
        int graduates = this.studentMapper.selectCount(student);
        //专业种类数
        int majorNum = this.studentMapper.selectMajorNum();

        //教师数
        User teacher = new User();
        teacher.setUsertype("teacher");
        int teacherNum = this.teacherMapper.selectCount(teacher);

        //查询班级数
        Integer classNum = this.studentMapper.selectClassNum();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(allStus);
        list.add(undergraduates);
        list.add(graduates);
        list.add(majorNum);
        list.add(teacherNum);
        list.add(classNum);
        return list;
    }

    /*
        获取年级
     */
    @Override
    public List<String> listGrades() {
        List<String> grades = this.studentMapper.listGrades();
        return grades;
    }

    /*
            获取性别比例
     */
    @Override
    public Map<String,Integer> getSexNumByTermYear(String year) {
        Integer bnum;//男生人数
        Integer gnum;//女生人数
        Map<String, Integer> map = new HashMap<>();
        if ("0".equals(year)){
//            System.out.println("allstudents========");
            Student student = new Student();
            student.setSex("男");
            bnum = this.studentMapper.selectCount(student);
            student.setSex("女");
            gnum = this.studentMapper.selectCount(student);

        }else{
            Example example = new Example(Student.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("sex","男").andEqualTo("termyear",year);
            bnum = this.studentMapper.selectCountByExample(example);
            Example example1 = new Example(Student.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("sex","女").andEqualTo("termyear",year);
            gnum = this.studentMapper.selectCountByExample(example1);
        }


        map.put("bnum",bnum);
        map.put("gnum",gnum);


        return map;
    }

    /**
     * 获取民族分布
     */

    @Override
    /**
     *  查询民族分布，
     *  过滤重复民族
     */
    public Map<String, Integer> getNationNumByTermYear(String year) {
        Map<String, Integer> wrongnations= new HashMap<>();
        Map<String,Integer> correctnations = new HashMap<>();
            List<Nation> nations = this.studentMapper.getNationNumByTermYear(year);
            nations.forEach(nation -> {
                //检出错误民族
                if (nation.getMzname().matches(NATIONRGEX)){
                    String corr = nation.getMzname() + "族";
                    wrongnations.put(corr,nation.getMznumber());
                }else {
                    correctnations.put(nation.getMzname(),nation.getMznumber());
                }
            });
            wrongnations.forEach((key,value) -> {
                correctnations.merge(key,value,Integer::sum);
            });
            return correctnations;
    }

    /*
            获取年级人数统计
     */
    @Override
    public Map<String, Integer> gradecount() {
        List<String> grades = listGrades();
        Student student = new Student();
        Map<String, Integer> map = new HashMap<>();
        grades.forEach(g -> {
            student.setTermyear(g);
            int count = this.studentMapper.selectCount(student);
            map.put(g,count);
        });

        return map;
    }

    /*
        查询各年级政治面貌
     */
    @Override
    public List<PoliticalStatus> pscount() {
        List<PoliticalStatus> pscount = this.studentMapper.pscount();
        List<PoliticalStatus> allpscount = this.studentMapper.allpscount();
        boolean b = pscount.addAll(allpscount);
        return pscount;
    }

    /**
     *
     * @return 统计生源地，返回json字符串
     */
    @Override
    public String stuMap() {
        List<String> address = this.studentMapper.queryAddress();
        Map<String, String> map = new HashMap<>();
        map.put("data", address.toString());
        System.out.println(map);
        ResultVo resultVo = Utils.sendPost(Utils.DATA_PROCESSING_URL+"/addressMap", map);
        if (resultVo == null){
            return "数据处理服务器错误";
        }
        if (resultVo.getCode() == 404){
            return "暂无记录";
        }
        Object data = resultVo.getData();
        return data.toString();
    }


}


