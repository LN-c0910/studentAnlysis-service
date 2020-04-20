package cn.csust.lingyi.bo;

/**
 * Created by Enzo Cotter on 2020/4/18.
 */
public class Sex {
    private String sex;
    private Integer sexCount;

    public Sex() {
    }

    public Sex(String sex, Integer sexCount) {
        this.sex = sex;
        this.sexCount = sexCount;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSexCount() {
        return sexCount;
    }

    public void setSexCount(Integer sexCount) {
        this.sexCount = sexCount;
    }

    @Override
    public String toString() {
        return "Sex{" +
                "sex='" + sex + '\'' +
                ", sexCount=" + sexCount +
                '}';
    }
}
