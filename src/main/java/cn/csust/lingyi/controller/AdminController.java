package cn.csust.lingyi.controller;

import cn.csust.lingyi.service.AdminService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PutMapping("adduserdict")
    public ResponseEntity<String> add_user_dict(@RequestParam("userdict") List<String> userdict){
        String msg = this.adminService.add_user_dict(userdict);
        if ("ERROR".equals(msg)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("创建失败");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PutMapping("addstopwords")
    public ResponseEntity<String> add_stop_words(@RequestParam("stopwd") List<String> stopwd){
        String msg = this.adminService.add_stop_words(stopwd);
        if ("ERROR".equals(msg)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("创建失败");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @DeleteMapping("clearwc")
    public ResponseEntity<String> del_file(@RequestParam("token") String token){
        String msg = this.adminService.del_file(token);
        if ("ERROR".equals(msg)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除失败");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
    }
}
