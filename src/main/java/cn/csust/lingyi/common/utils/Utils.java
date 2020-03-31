package cn.csust.lingyi.common.utils;


import cn.csust.lingyi.common.VO.ResultVo;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Utils<T> {
    /**
     * 向目的URL发送post请求
     * *@param url       目的url
     * *@param params    发送的参数
     * *@return  ResultVo
     */
    public static final String DATA_PROCESSING_URL = "http://192.168.184.1:5000";
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
