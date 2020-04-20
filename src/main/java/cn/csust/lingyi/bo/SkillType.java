package cn.csust.lingyi.bo;

/**
 * Created by Enzo Cotter on 2020/4/5.
 * 技能枚举
 */
public enum SkillType {
    /**
     * 技能
     */
    CET4("英语四级"), CET6("英语六级"), NCRE2("计算机二级");

    // 成员变量
    private String name;
    // 构造方法
    SkillType(String name) {
        this.name = name;
    }

    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
