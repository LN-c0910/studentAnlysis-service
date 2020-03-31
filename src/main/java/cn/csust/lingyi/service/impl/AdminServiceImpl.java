package cn.csust.lingyi.service.impl;

import cn.csust.lingyi.common.VO.ResultVo;
import cn.csust.lingyi.common.utils.Utils;
import cn.csust.lingyi.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/3/20.
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public String add_user_dict(List<String> userdict) {
        Map<String, List<String>> map = new HashMap<>();
        map.put("data", userdict);
        ResultVo resultVo = Utils.sendPost(Utils.DATA_PROCESSING_URL+"/adduserdict", map);
        if (resultVo.getCode() != 200 && resultVo.getCode() != 201){
            return "ERROR";
        }
        return (String) resultVo.getData();
    }

    @Override
    public String add_stop_words(List<String> stopwd) {
        Map<String, List<String>> map = new HashMap<>();
        map.put("data", stopwd);
        ResultVo resultVo = Utils.sendPost(Utils.DATA_PROCESSING_URL+"/addstopwords", map);
        if (resultVo.getCode() != 200 && resultVo.getCode() != 201){
            return "ERROR";
        }
        return (String) resultVo.getData();
    }

    @Override
    public String del_file(String token) {
        Map<String, String> map = new HashMap<>();
        map.put("token", "lingyi");
        ResultVo resultVo = Utils.sendPost(Utils.DATA_PROCESSING_URL+"/clearpicbuffer", map);
        if (resultVo.getCode() != 200 && resultVo.getCode() != 201){
            return "ERROR";
        }
        return (String) resultVo.getData();
    }
}
