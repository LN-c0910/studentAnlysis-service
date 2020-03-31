package cn.csust.lingyi.controller;

import cn.csust.lingyi.bo.PoliticalStatus;
import cn.csust.lingyi.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/3/15.
 */
@Controller
@RequestMapping("overview")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;

    /*
        显示数据面板
     */
    @GetMapping("showBoard")
    @ResponseBody
    public ResponseEntity<List<Integer>> showBoard(){
      List<Integer> boards = this.dashboardService.showBoard();
      if (CollectionUtils.isEmpty(boards)){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
      return ResponseEntity.ok(boards);
    }
/*
    获取年级
 */
    @GetMapping("listGrades")
    public ResponseEntity<List<String>> listGrades(){
        List<String> grades = this.dashboardService.listGrades();
        return ResponseEntity.ok(grades);
    }
    /*
        获取性别比例
     */
    @GetMapping("sexcount/grade/{year}")
    public ResponseEntity<Map<String,Integer>> getSexNumByTermYear(@PathVariable("year") String year){
        Map<String, Integer> sexNum = this.dashboardService.getSexNumByTermYear(year);
        return ResponseEntity.ok(sexNum);
    }

    /**
     * 获取民族分布
     */
    @GetMapping("nation")
    public ResponseEntity<Map<String, Map<String, Integer>>> getNationNumByTermYear(){
        Map<String, Map<String, Integer>> json_res = new HashMap<>();
        List<String> grades = this.dashboardService.listGrades();
        grades.add("0");
        grades.forEach(grade -> {
            Map<String, Integer> nationNumByTermYear = this.dashboardService.getNationNumByTermYear(grade);
            json_res.put(grade,nationNumByTermYear);
        });
        return ResponseEntity.ok(json_res);
    }

    /**
     * 获取年级人数统计
     */

    @GetMapping("grades")
    public ResponseEntity<Map<String,Integer>> gradecount(){
       Map<String,Integer> gradecounts = this.dashboardService.gradecount();
       return ResponseEntity.ok(gradecounts);
    }

    /**
     * 获取政治面貌
     */
    @GetMapping("ps")
    public ResponseEntity<List<PoliticalStatus>> pscount(){
        List<PoliticalStatus> pscount = this.dashboardService.pscount();
        return ResponseEntity.ok(pscount);
    }

    /**
     * 获取生源地图
     */
    @GetMapping("stumap")
    public ResponseEntity<String> stumap(){
        String s = this.dashboardService.stuMap();
        return ResponseEntity.ok(s);
    }
}
