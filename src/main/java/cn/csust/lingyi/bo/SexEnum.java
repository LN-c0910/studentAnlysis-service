package cn.csust.lingyi.bo;

/**
 * Created by Enzo Cotter on 2020/4/18.
 * 性别枚举
 */
public enum SexEnum {
    /**
     * 性别:男|女
     */
    MALE("男"),FEMALE("女");

    private String name;

    SexEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
