package cn.csust.lingyi.controller;

import cn.csust.lingyi.bo.*;
import cn.csust.lingyi.common.VO.PageResult;
import cn.csust.lingyi.pojo.Personsummary;
import cn.csust.lingyi.pojo.Student;
import cn.csust.lingyi.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LINAN
 * Created by Enzo Cotter on 2020/4/2.
 */

@Controller
@RequestMapping("classAnalysis")
public class ClassesController {

    // @Autowired ---> Spring 不推荐直接注入属性,可换成构造器方式注入

    private ClassesService classesService;

    public ClassesController() {
    }

    @Autowired
    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    /**
     * 班级映射列表
     *
     * @return 返回映射ClassesMapping模型
     */
    @GetMapping("classMapping")
    public ResponseEntity<List<ClassesMapping>> queryClassesMapping() {
        List<ClassesMapping> classesMappings = this.classesService.queryClassesMapping();
        if (CollectionUtils.isEmpty(classesMappings)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classesMappings);
    }

    /**
     * 根据班级查询技能证书通过率
     *
     * @param termYear 年级
     * @param major    专业
     * @param classNo  班级
     * @param type     证书类型
     * @return 返回通过率
     */
    @GetMapping("passRates/{termYear}/{major}/{classNo}/{type}")
    public ResponseEntity<Double> skillPassRate(@PathVariable("termYear") String termYear,
                                                @PathVariable("major") String major,
                                                @PathVariable("classNo") Integer classNo,
                                                @PathVariable("type") Integer type) {

        Double rate;
        //错误代码
        Double errorCode = -1D;
        //是否查询所有班级
        Integer allClasses = -1;
        classNo = allClasses.equals(classNo) ? null : classNo;
        switch (type) {
            case 0:
                //英语4级
                rate = this.classesService.queryCET4or6PassRate(termYear, major, classNo, SkillType.CET4);
                break;
            case 1:
                //英语六级
                rate = this.classesService.queryCET4or6PassRate(termYear, major, classNo, SkillType.CET6);
                break;
            case 3:
                //计算机二级
                rate = this.classesService.queryCET4or6PassRate(termYear, major, classNo, SkillType.NCRE2);
                break;
            default:
                rate = errorCode;
                break;
        }
        if (errorCode.equals(rate)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rate);

    }


    /**
     * 获取班级人数
     *
     * @param termYear 年级
     * @param major    专业
     * @param classNo  班级
     * @return 班级人数
     */
    @GetMapping("classOfSize/{termYear}/{major}/{classNo}")
    public ResponseEntity<Integer> querySizeByClass(@PathVariable("termYear") String termYear,
                                                    @PathVariable("major") String major,
                                                    @PathVariable("classNo") Integer classNo) {
        //是否查询所有班级
        Integer allClasses = -1;
        classNo = allClasses.equals(classNo) ? null : classNo;
        Integer sizeOfClass = this.classesService.querySizeByClass(termYear, major, classNo);
        return ResponseEntity.ok(sizeOfClass);
    }

    /**
     * 获取班级性别统计
     *
     * @param termYear 年级
     * @param major    专业
     * @param classNo  班级
     * @return 性别统计
     */
    @GetMapping("sexByClass/{termYear}/{major}/{classNo}")
    public ResponseEntity<List<Sex>> querySexByClass(@PathVariable("termYear") String termYear,
                                                     @PathVariable("major") String major,
                                                     @PathVariable("classNo") Integer classNo) {
        //是否查询所有班级
        Integer allClasses = -1;
        classNo = allClasses.equals(classNo) ? null : classNo;
        Integer maleOfClass = this.classesService.querySexByClass(SexEnum.MALE, termYear, major, classNo);
        Integer femaleOfClass = this.classesService.querySexByClass(SexEnum.FEMALE, termYear, major, classNo);
        List<Sex> sexes = new ArrayList<>();
        sexes.add(new Sex("male", maleOfClass));
        sexes.add(new Sex("female", femaleOfClass));
        return ResponseEntity.ok(sexes);
    }

    /**
     * 获取某班级民族分布列表
     *
     * @param termYear 年级
     * @param major    专业
     * @param classNo  班级
     * @return 民族分布列表
     */
    @GetMapping("nationByClass/{termYear}/{major}/{classNo}")
    public ResponseEntity<List<Nation>> queryNationByClass(@PathVariable("termYear") String termYear,
                                                           @PathVariable("major") String major,
                                                           @PathVariable("classNo") Integer classNo) {
        //是否查询所有班级
        Integer allClasses = -1;
        classNo = allClasses.equals(classNo) ? null : classNo;
        List<Nation> nations = this.classesService.queryNationByClass(termYear, major, classNo);
        if (CollectionUtils.isEmpty(nations) | nations == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nations);
    }

    /**
     * 获取某班级政治面貌分布列表
     *
     * @param termYear 年级
     * @param major    专业
     * @param classNo  班级
     * @return 某班级政治面貌分布列表
     */
    @GetMapping("psByClass/{termYear}/{major}/{classNo}")
    public ResponseEntity<List<PoliticalStatus>> queryPoliticalStatusByClass(@PathVariable("termYear") String termYear,
                                                                             @PathVariable("major") String major,
                                                                             @PathVariable("classNo") Integer classNo) {
        //是否查询所有班级
        Integer allClasses = -1;
        classNo = allClasses.equals(classNo) ? null : classNo;
        List<PoliticalStatus> politicalStatuses = this.classesService.queryPoliticalStatusByClass(termYear, major, classNo);
        if (CollectionUtils.isEmpty(politicalStatuses) | politicalStatuses == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(politicalStatuses);
    }


    /**
     * 获取班级学生省份分布
     *
     * @param termYear 年级
     * @param major    专业
     * @param classNo  班级
     * @return 班级学生省份分布列表
     */
    @GetMapping("mapOfClass/{termYear}/{major}/{classNo}")
    public ResponseEntity<String> queryProvinceByClass(@PathVariable("termYear") String termYear,
                                                       @PathVariable("major") String major,
                                                       @PathVariable("classNo") Integer classNo) {
        //是否查询所有班级
        Integer allClasses = -1;
        classNo = allClasses.equals(classNo) ? null : classNo;
        String addressCounts = this.classesService.queryProvinceByClass(termYear, major, classNo);
        return ResponseEntity.ok(addressCounts);
    }

    /**
     * 获取班级学生分页结果
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级
     * @param page 开始页码
     * @param rows 每页行数
     * @return 分页结果
     */
    @GetMapping("stu/page")
    public ResponseEntity<PageResult<Student>> queryStudentByClass(
            @RequestParam(value = "termYear") String termYear,
            @RequestParam(value = "major") String major,
            @RequestParam(value = "classNo") Integer classNo,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "10") Integer rows){
        PageResult<Student> studentPageResult = this.classesService.queryStudentByClass(termYear, major,
                                                                                        classNo, page, rows);
        if (studentPageResult == null || CollectionUtils.isEmpty(studentPageResult.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentPageResult);
    }

    @GetMapping("score/overview")
    public ResponseEntity<ScoreOverviewOfClass> scoreOverviewOfClass(
            @RequestParam(value = "termYear") String termYear,
            @RequestParam(value = "xn") String xn,
            @RequestParam(value = "major") String major,
            @RequestParam(value = "classNo") Integer classNo){

        // 平均分
        Double avgScoreOfClass = this.classesService.avgScoreOfClass(termYear, major, classNo, xn);
        //排名
        Integer rank = this.classesService.avgScoreRankOfClass(termYear, xn, major, classNo);
        //挂科率
        Double failsOfClass = this.classesService.failsOfClass(termYear, xn, major, classNo);
        //最高分
        Double topScore = this.classesService.topScoreOfClass(termYear, xn, major, classNo);
        //4级通过率
        Double cet4PassRate = this.classesService.queryCET4or6PassRate(termYear, major, classNo, SkillType.CET4);
        //6级通过率
        Double cet6PassRate = this.classesService.queryCET4or6PassRate(termYear, major, classNo, SkillType.CET6);

        //封装数据
        ScoreOverviewOfClass overview = new ScoreOverviewOfClass();
        overview.setAvgScoreOfClass(avgScoreOfClass);
        overview.setAvgScoreRankOfClass(rank);
        overview.setFailsOfClass(failsOfClass);
        overview.setTopScore(topScore);
        overview.setCet4PassRate(cet4PassRate);
        overview.setCet6PassRate(cet6PassRate);

        return ResponseEntity.ok(overview);
    }

    @GetMapping("score/page")
    public ResponseEntity<PageResult<Personsummary>> queryScoreListByClass(
            @RequestParam(value = "termYear") String termYear,
            @RequestParam(value = "xn") String xn,
            @RequestParam(value = "major") String major,
            @RequestParam(value = "classNo") Integer classNo,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "10") Integer rows){
        PageResult<Personsummary> pageResult = this.classesService.queryScoreListByClass(termYear, xn, major, classNo, page, rows);
        //判断结果集为空
        if (pageResult == null || CollectionUtils.isEmpty(pageResult.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }

    @GetMapping("wordCloud")
    public ResponseEntity<String> getClassDescriptionWordCloud(@RequestParam("termYear") String termYear,
                                                               @RequestParam("major") String major,
                                                               @RequestParam("classNo") Integer classNo){
        String imgPath = this.classesService.getClassDescriptionWordCloud(termYear, major, classNo);
        if (StringUtils.isEmpty(imgPath) | imgPath == null){
            return ResponseEntity.ok("create_failed");
        }
        return ResponseEntity.ok(imgPath);
    }
}
