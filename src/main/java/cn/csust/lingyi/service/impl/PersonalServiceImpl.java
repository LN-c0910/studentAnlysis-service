package cn.csust.lingyi.service.impl;

import cn.csust.lingyi.common.VO.RespData;
import cn.csust.lingyi.common.VO.ResultVo;
import cn.csust.lingyi.common.utils.Utils;
import cn.csust.lingyi.mapper.*;
import cn.csust.lingyi.pojo.*;
import cn.csust.lingyi.service.PersonalService;
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


/**
 * Created by Enzo Cotter on 2020/3/18.
 */
@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    CourseMapper courseMapper;
    @Autowired
    PersonsummaryMapper personsummaryMapper;
    @Autowired
    JlMapper jlMapper;
    @Autowired
    LectureMapper lectureMapper;
    @Autowired
    PersondeductionMapper persondeductionMapper;
    @Autowired
    PersonmoralMapper personmoralMapper;
    @Autowired
    PracticeMapper practiceMapper;
    @Autowired
    PunishMapper punishMapper;
    @Autowired
    RegistrationMapper registrationMapper;
    @Autowired
    RewardInfoMapper rewardInfoMapper;
    @Autowired
    SkillMapper skillMapper;
    @Autowired
    StudentMapper studentMapper;


    /**
     *
     * @param sno 学号
     * @return 学生描述列表
     */
    @Override
    public List<String> getDescriptionList(String sno){
        ArrayList<String> descriptionList = new ArrayList<>();
        //奖励信息
        List<Jl> jls = this.jlMapper.select(new Jl(sno));
        if (!CollectionUtils.isEmpty(jls)) {
            jls.forEach(jl -> {
                if (jl.getJlname() != null){
                    descriptionList.add(jl.getJlname());
                }
            });
        }
        //扣分信息
        List<Persondeduction> persondeductions = this.persondeductionMapper.queryDeductionsBySno(sno);
        if (!CollectionUtils.isEmpty(persondeductions)) {
            persondeductions.forEach(persondeduction -> {
                if (persondeduction.getDname() != null){
                    descriptionList.add(persondeduction.getDname());
                }
            });
        }
        //成绩最高三科
        List<Personknowledge> personknowledges = this.courseMapper.queryCourseScoresBySno(sno, "0", 3);
        if (!CollectionUtils.isEmpty(personknowledges)) {
            personknowledges.forEach(personknowledge -> {
                if (personknowledge.getCname()!=null){
                    descriptionList.add(personknowledge.getCname());
                }
            });
        }
        //德育加分项
        List<Personmoral> personmorals = this.personmoralMapper.select(new Personmoral(sno));
        if (!CollectionUtils.isEmpty(personmorals)) {
            personmorals.forEach(personmoral -> {
                if (personmoral.getName() != null){
                    descriptionList.add(personmoral.getName());
                }
            });
        }
        //实践项
        List<Practice> practices = this.practiceMapper.select(new Practice(sno));
        if (!CollectionUtils.isEmpty(practices)) {
            practices.forEach(practice -> {
                if (practice.getPracticename() != null){
                    descriptionList.add(practice.getPracticename());
                }
            });
        }
        //惩罚项
        List<Punish> punishes = this.punishMapper.select(new Punish(sno));
        if (!CollectionUtils.isEmpty(punishes)){
            punishes.forEach(punish -> {
                if (punish.getPunishreason()!=null){
                    descriptionList.add(punish.getPunishreason());
                }
            });
        }
        //技能证书
        List<Skill> skills = this.skillMapper.select(new Skill(sno));
        if (!CollectionUtils.isEmpty(skills)){
            skills.forEach(skill -> {
                if (skill.getSkillname()!=null){
                    descriptionList.add(skill.getSkillname());
                }
            });
        }
        //讲座记录
        List<String> lectureNames = this.registrationMapper.queryLectureNameBySno(sno);
        if (!CollectionUtils.isEmpty(lectureNames)){
            lectureNames.forEach(s -> {
                if (s != null) {
                    descriptionList.add(s);
                }
            });
        }
        //特长信息
        Student student = new Student();
        student.setStudentno(sno);
        Student student1 = this.studentMapper.selectOne(student);
        if (student1.getTc() != null) {
            descriptionList.add(student1.getTc());
        }
        return descriptionList;
    }

    @Override
    public List<Personknowledge> queryCourseScoresBySno(String sno,String xuenian,Integer limit) {
        return this.courseMapper.queryCourseScoresBySno(sno, xuenian,limit);
    }

    @Override
    public Map<String, Double> queryMKSByStu(String sno, String xuenian) {
        Personsummary personsummary = new Personsummary();
        Map<String, Double> map = new HashMap<>();
        personsummary.setStudentno(sno);
        personsummary.setXuenian(xuenian);
        Personsummary personsummary1 = this.personsummaryMapper.selectOne(personsummary);
        Double md = personsummary1.getMoral()==null ? 0 : (personsummary1.getMoral())/25;
        Double m = new BigDecimal(md).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        map.put("m",m);
        Double v1 = personsummary1.getKnowledge()==null ? 0 : (personsummary1.getKnowledge()) / 70;
        Double k = new BigDecimal(v1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        map.put("k",k);
        Double v2 = personsummary1.getSports()==null ?0 : (personsummary1.getSports()) / 5;
        Double s = new BigDecimal(v2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        map.put("s",s);
        return map;
    }

    /**
     *
     * @param sno 学号
     * @return  获取学生相关词条分词结果的词云图路径  [{name:xx,value:xx},..]
     */
    @Override
    public String queryDescrBySno(String sno) {
        Student init = this.studentMapper.selectByPrimaryKey(sno);
        if (init == null){
            return "static/blank.png";
        }
        List<String> descriptionList = getDescriptionList(sno);
        if (descriptionList.size() < 5){
            return "static/word_list_too_short.png";
        }
        Map<String, List<String>> map = new HashMap<>();
        map.put("data", descriptionList);
        ResultVo resultVo = Utils.sendPost(Utils.DATA_PROCESSING_URL+"/stuwordcloud", map);
        if (resultVo.getCode() == 500){
            return "static/create_failed.png";
        }
        String imgPath = (String) resultVo.getData();
        return imgPath;
    }

    @Override
    public Student queryStuBySno(String sno) {
        Student student = this.studentMapper.selectByPrimaryKey(sno);
        return student;
    }

    @Override
    public List<RespData> queryStuAdvBySname(String sname) {
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("studentname","%"+sname+"%");
        List<Student> students = this.studentMapper.selectByExample(example);
        List<RespData> list = new ArrayList<>();
        students.forEach(student -> {
            RespData<String> resp = new RespData<>();
            resp.setValue(student.getStudentno());
            resp.setName(student.getStudentname());
            list.add(resp);
        });
        return list;
    }

    //获取总挂科率 (挂科数/总课程数)
    @Override
    public Double queryTfailedratesBySno(String sno) {
        Student student = this.studentMapper.selectByPrimaryKey(sno);
        if (student == null){
            return -1.0;
        }
        //计算挂科数
        Example example = new Example(Personknowledge.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("score",-1);
        criteria.andEqualTo("studentno",sno);
        criteria.andLessThan("score",60);
        int fails = this.courseMapper.selectCountByExample(example);
        //计算总课程数
        Example example1 = new Example(Personknowledge.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("studentno",sno);
        criteria1.andNotEqualTo("score",-1);
        int total = this.courseMapper.selectCountByExample(example1);
        double rate = new BigDecimal((float)fails/total).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        return rate;
    }

    //获取某一学年挂科率 (挂科数/总课程数)

    @Override
    public Double queryfailRatesBySnoAndXuenian(String sno, String xuenian) {
        Student student = this.studentMapper.selectByPrimaryKey(sno);
        if (student == null){
            return -1.0;
        }
        Integer total = this.courseMapper.queryCourseCountsBySnoAndXuenian(sno, xuenian);
        if (total == 0){
            return -1.0;
        }
        Integer queryfails = this.courseMapper.queryfailsBySnoAndXuenian(sno, xuenian);
        double rate = new BigDecimal((float)queryfails/total).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return rate;
    }

    /**
     * 根据cid获取学生课程
     * @param cid
     * @return
     */
    @Override
    public List<Personknowledge> queryCourseByCid(Long cid,String sno) {
        List<Personknowledge> personknowledges = this.courseMapper.queryCourseByCid(cid, sno);
        return personknowledges;
    }

    /**
     *
     * @param sno
     * @param xuenian
     * @return 综测专业排名
     */
    @Override
    public Integer queryRankBySnoAndXuenian(String sno, String xuenian) {

        return this.courseMapper.queryRankBySnoAndXuenian(sno, xuenian);
    }

    /**
     * 根据学年范围，学号查询学生奖励记录
     * @param sno 学号
     * @param syear 整数，年份
     * @param eyear 整数，年份
     * @return
     */
    @Override
    public List<Jl> queryJlBySno(String sno,Integer syear,Integer eyear) {

        if (syear == null || eyear == null){
            return this.jlMapper.queryJlBySnoAndXuenian(sno, null, null);
        }
        //若开始学年与结束学年不为空，拼接时间字符串 (一个学年范围：yyyy-09-01-yyyy-08-31)
        String sTime = syear + "-9-1";
        String eTime = eyear + "-8-31";
        List<Jl> jls = this.jlMapper.queryJlBySnoAndXuenian(sno, sTime, eTime);
        return jls;
    }

    /**
     * 根据学年范围查询惩罚记录
     * @param sno
     * @param syear
     * @param eyear
     * @return 惩罚记录
     */
    @Override
    public List<Punish> queryPunishBySno(String sno,Integer syear,Integer eyear) {
        if (syear == null || eyear == null){
            return this.punishMapper.queryPunishBySnoandXuenian(sno, null, null);
        }
        //若开始学年与结束学年不为空，拼接时间字符串 (一个学年范围：yyyy-09-01-yyyy-08-31)
        String sTime = syear + "-9-1";
        String eTime = eyear + "-8-31";
        List<Punish> punishes = this.punishMapper.queryPunishBySnoandXuenian(sno, sTime, eTime);
        return punishes;
    }

    /**
     *
     * @param sno
     * @param syear
     * @param eyear
     * @return 技能记录
     */
    @Override
    public List<Skill> querySkillsBySno(String sno,Integer syear,Integer eyear) {
        if (syear == null || eyear == null){
            return this.skillMapper.querySkillBySnoAndXuenian(sno, null, null);
        }
        //若开始学年与结束学年不为空，拼接时间字符串 (一个学年范围：yyyy-09-01-yyyy-08-31)
        String sTime = syear + "-9-1";
        String eTime = eyear + "-8-31";
        List<Skill> skills = this.skillMapper.querySkillBySnoAndXuenian(sno,sTime,eTime);
        return skills;
    }

    /**
     *
     * @param sno
     * @param syear
     * @param eyear
     * @return 实践记录，按开始时间计算 降序
     */
    @Override
    public List<Practice> queryPracticeBySno(String sno,Integer syear,Integer eyear) {
        if (syear == null || eyear == null){
            return this.practiceMapper.queryPracticeBySnoAndXuenian(sno, null, null);
        }
        //若开始学年与结束学年不为空，拼接时间字符串 (一个学年范围：yyyy-09-01-yyyy-08-31)
        String sTime = syear + "-9-1";
        String eTime = eyear + "-8-31";
        List<Practice> practices = this.practiceMapper.queryPracticeBySnoAndXuenian(sno,sTime,eTime);
        return practices;
    }
}
