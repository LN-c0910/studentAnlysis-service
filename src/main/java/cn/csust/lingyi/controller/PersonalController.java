package cn.csust.lingyi.controller;

import cn.csust.lingyi.common.VO.RespData;
import cn.csust.lingyi.pojo.Student;
import cn.csust.lingyi.service.PersonalService;
import cn.csust.lingyi.pojo.Personknowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/3/18.
 */
@Controller
@RequestMapping("stuAnalysis")
public class PersonalController {
    @Autowired
    PersonalService personalService;

    /**
     * 查询科目成绩
     * @param sno
     * @param xuenian
     * @return
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
    public ResponseEntity<Double> searchfailsRate(@PathVariable(value = "sno",required = true) String sno,
                                                  @PathVariable(value = "xuenian") String xuenian){
        Double rates;
        if ("0".equals(xuenian)){
            rates = this.personalService.queryTfailedratesBySno(sno);
        }else {
            rates = this.personalService.queryfailRatesBySnoAndXuenian(sno, xuenian);
        }
        if (rates == -1.0){
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




}
