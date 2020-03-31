package cn.csust.lingyi.common.VO;

import java.util.Date;

/**
 * Created by Enzo Cotter on 2020/3/17.
 * *@description: 数据返回类
 *
 * json格式:
 * {
 *     "code":状态码,
 *     "msg":响应头携带消息,
 *     "data":{
 *         响应体
 *     }
 * }
 */
public class ResultVo<T> {
    private Integer code;
    private String msg;
    private Date createTime;
    private T data;

    public ResultVo(){

    }
    public ResultVo(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public ResultVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
