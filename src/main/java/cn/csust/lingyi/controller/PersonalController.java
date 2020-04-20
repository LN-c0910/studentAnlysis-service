package cn.csust.lingyi.controller;

import cn.csust.lingyi.common.VO.RespData;
import cn.csust.lingyi.pojo.*;
import cn.csust.lingyi.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/3/18.
 * @author LINAN
 */
@Controller
@RequestMapping("stuAnalysis")
public class PersonalController {
    private PersonalService personalService;

    public PersonalController(){

    }

    @Autowired
    public PersonalController(PersonalService personalService){
        this.personalService = personalService;
    }

    /**
     * 查询科目成绩
     * @param sno 学号
     * @param xuenian 学年 (yyyy-yyyy)
     * @param limit 限制条数
     * @return 学生在某学年的科目成绩 成绩降序排序
     */
    @PostMapping("courses/{xuenian}/{sno}/{limit}")
    public ResponseEntity<List<Personknowledge>> queryCourseScoresBySno(
            @PathVariable("sno") String sno,
            @PathVariable("xuenian") String xuenian,
            @PathVariable("limit") Integer limit){
        List<Personknowledge> personknowledges = this.personalService.queryCourseScoresBySno(sno, xuenian,limit);
        if (CollectionUtils.isEmpty(personknowledges)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personknowledges);
    }

    @PostMapping("mks/{xuenian}/{sno}")
    public ResponseEntity<Map<String,Double>> queryMKSByStu(@PathVariable("sno") String sno,
                                                            @PathVariable("xuenian") String xuenian){
        Map<String, Double> mks = this.personalService.queryMKSByStu(sno, xuenian);
        return ResponseEntity.ok(mks);
    }

    @GetMapping("stuwordcloud/{sno}")
    public ResponseEntity<String> queryDescrBySno(@PathVariable("sno") String sno){
        String s = this.personalService.queryDescrBySno(sno);
        return ResponseEntity.ok(s);
    }

    @PostMapping("searchadv")
    public ResponseEntity<List<RespData>> queryStuAdvBySname(@RequestParam("sname") String sname){
        List<RespData> stuAdv = this.personalService.queryStuAdvBySname(sname);
        if (CollectionUtils.isEmpty(stuAdv)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stuAdv);
    }

    @GetMapping("searchstu/{sno}")
    public ResponseEntity<Student> queryStuBySno(@PathVariable("sno") String sno){
        Student student = this.personalService.queryStuBySno(sno);
        if (student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping("searchfailsRate/{xuenian}/{sno}")
    public ResponseEntity<Double> searchfailsRate(@PathVariable(value = "sno") String sno,
                                                  @PathVariable(value = "xuenian") String xuenian){
        Double rates;
        String allDataCode = "0";
        Double errorCode = -1D;
        if (allDataCode.equals(xuenian)){
            rates = this.personalService.queryTfailedratesBySno(sno);
        }else {
            rates = this.personalService.queryfailRatesBySnoAndXuenian(sno, xuenian);
        }
        if (errorCode.equals(rates)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rates);
    }

    @PostMapping("searchCourseBycid/{sno}/{cid}")
    public ResponseEntity<List<Personknowledge>> queryCourseByCid(@PathVariable("sno") String sno,
                                                                  @PathVariable("cid") Long cid){
        List<Personknowledge> personknowledges = this.personalService.queryCourseByCid(cid, sno);
        if (CollectionUtils.isEmpty(personknowledges)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personknowledges);
    }

    @GetMapping("rank/{xuenian}/{sno}")
    public ResponseEntity<Integer> queryRankBySnoAndXuenian(@PathVariable("sno") String sno,
                                                            @PathVariable("xuenian") String xuenian){
        Integer rank = this.personalService.queryRankBySnoAndXuenian(sno, xuenian);
        if (rank == null || rank == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rank);
    }

    /**
     *
     * @param sno
     * @param syear
     * @param eyear
     * @return 奖励记录
     */
    @PostMapping("jl")
    public ResponseEntity<List<Jl>> queryJlBySno(@RequestParam(value = "sno",required = true) String sno,
                                                 @RequestParam(value = "syear",defaultValue = "0",required = false) Integer syear,
                                                 @RequestParam(value = "eyear",defaultValue = "0",required = false) Integer eyear
                                                 ){
        List<Jl> jls;
        if (syear == 0 || eyear == 0 || eyear-syear < 0){
            jls = this.personalService.queryJlBySno(sno, null, null);
        }else{
            jls = this.personalService.queryJlBySno(sno, syear, eyear);
        }
        if (CollectionUtils.isEmpty(jls)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(jls);
    }

    /**
     * 惩罚记录
     * @param sno
     * @param syear
     * @param eyear
     * @return
     */
    @PostMapping("punish")
    public ResponseEntity<List<Punish>> queryPunishBySno(@RequestParam(value = "sno",required = true) String sno,
                                                         @RequestParam(value = "syear",defaultValue = "0",required = false) Integer syear,
                                                         @RequestParam(value = "eyear",defaultValue = "0",required = false) Integer eyear
    ){
        List<Punish> punishes;
        if (syear == 0 || eyear == 0 || eyear-syear < 0){
            punishes = this.personalService.queryPunishBySno(sno, null, null);
        }else{
            punishes = this.personalService.queryPunishBySno(sno, syear, eyear);
        }
        if (CollectionUtils.isEmpty(punishes)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(punishes);
    }


    /**
     * 技能记录
     * @param sno
     * @param syear
     * @param eyear
     * @return
     */
    @PostMapping("skills")
    public ResponseEntity<List<Skill>> querySkillsBySno(@RequestParam(value = "sno",required = true) String sno,
                                                        @RequestParam(value = "syear",defaultValue = "0",required = false) Integer syear,
                                                        @RequestParam(value = "eyear",defaultValue = "0",required = false) Integer eyear
    ){
        List<Skill> skills;
        if (syear == 0 || eyear == 0 || eyear-syear < 0){
            skills = this.personalService.querySkillsBySno(sno, null, null);
        }else{
            skills = this.personalService.querySkillsBySno(sno, syear, eyear);
        }
        if (CollectionUtils.isEmpty(skills)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skills);
    }

    /**
     * 实践记录
     * @param sno
     * @param syear
     * @param eyear
     * @return
     */
    @PostMapping("practice")
    public ResponseEntity<List<Practice>> queryPracticesBySno(@RequestParam(value = "sno",required = true) String sno,
                                                        @RequestParam(value = "syear",defaultValue = "0",required = false) Integer syear,
                                                        @RequestParam(value = "eyear",defaultValue = "0",required = false) Integer eyear
    ){
        List<Practice> practices;
        if (syear == 0 || eyear == 0 || eyear-syear < 0){
            practices = this.personalService.queryPracticeBySno(sno, null, null);
        }else{
            practices = this.personalService.queryPracticeBySno(sno, syear, eyear);
        }
        if (CollectionUtils.isEmpty(practices)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(practices);
    }


}
