package cn.csust.lingyi.common.utils;


import cn.csust.lingyi.common.VO.ResultVo;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author linan
 */
public class Utils<T> {

    /**
     * 设置远端数据处理服务器地址
     */
    public static final String DATA_PROCESSING_URL = "http://192.168.184.1:5000";

    /**
     * 远程发送POST请求
     * @param url 远程地址
     * @param params 请求参数Map格式
     * @param <T> 参数类型
     * @return 响应数据
     */
    public static <T> ResultVo sendPost(String url, Map<String,T> params){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        //以表单方式提交
        headers.setContentType(MediaType.APPLICATION_JSON);
        //请求头和参数合并
        HttpEntity<Map<String, T>> entity = new HttpEntity<>(params,headers);
        //发送HTTP请求
        ResponseEntity<ResultVo> response = client.exchange(url, method, entity, ResultVo.class);

        return response.getBody();
    }
}
