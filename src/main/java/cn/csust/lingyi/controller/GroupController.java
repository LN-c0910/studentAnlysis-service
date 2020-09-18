package cn.csust.lingyi.controller;

import cn.csust.lingyi.bo.ScoreSignal;
import cn.csust.lingyi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Created by Enzo Cotter on 2020/4/25.
 * @author linan
 */
@Controller
@RequestMapping("groupAnalysis")
public class GroupController {

    private GroupService groupService;

    public GroupController() {
    }

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * 查询个人成绩预警信号
     * @param sno 学号
     * @return 个人成绩预警信号列表
     */
    @GetMapping("scoreSignal/person/{sno}")
    public ResponseEntity<List<ScoreSignal>> queryScoreSignalByStu(@PathVariable("sno") String sno){
        List<ScoreSignal> scoreSignals = this.groupService.queryScoreSignalByStu(sno);
        if (CollectionUtils.isEmpty(scoreSignals) | scoreSignals == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scoreSignals);
    }

    /**
     * 查询班级所有学生某学年成绩预警信号
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级
     * @param xn 学年
     * @return 班级所有学生预警信号列表
     */
    @GetMapping("scoreSignal/class")
    public ResponseEntity<List<ScoreSignal>> queryScoreSignalByClass(
            @RequestParam("termYear") String termYear,
            @RequestParam("major") String major,
            @RequestParam("classNo") Integer classNo,
            @RequestParam("xn") String xn
    ){
        List<ScoreSignal> scoreSignals = this.groupService.queryScoreSignalByClass(termYear,major,classNo,xn);
        if (CollectionUtils.isEmpty(scoreSignals) | scoreSignals == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scoreSignals);
    }


}
