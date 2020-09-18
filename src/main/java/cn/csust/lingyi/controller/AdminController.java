package cn.csust.lingyi.controller;

import cn.csust.lingyi.service.AdminService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by Enzo Cotter on 2020/3/20.
 */

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 词云图增加用户字典
     * @param userdict 用户字典
     * @return 增加状态信息
     */
    @PutMapping("adduserdict")
    public ResponseEntity<String> add_user_dict(@RequestParam("userdict") List<String> userdict){
        String msg = this.adminService.add_user_dict(userdict);
        if ("ERROR".equals(msg)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("创建失败");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    /**
     * 词云图增加停用字典
     * @param stopwd 停用字典
     * @return 增加状态信息
     */
    @PutMapping("addstopwords")
    public ResponseEntity<String> add_stop_words(@RequestParam("stopwd") List<String> stopwd){
        String msg = this.adminService.add_stop_words(stopwd);
        if ("ERROR".equals(msg)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("创建失败");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    /**
     * 删除所有词云图
     * @param token 管理员口令
     * @return 删除状态信息
     */
    @DeleteMapping("clearwc")
    public ResponseEntity<String> del_file(@RequestParam("token") String token){
        String msg = this.adminService.del_file(token);
        if ("ERROR".equals(msg)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除失败");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
    }

    /**
     * 训练成绩聚类 (方法未测试)
     * @param token 口令
     * @return 状态信息
     */
    @PostMapping("scoreTraining")
    public ResponseEntity<String> scoreTraining(@RequestParam("token") String token){
        String scoreTraining = this.adminService.scoreTraining(token);
        return ResponseEntity.ok(scoreTraining);
    }


}
