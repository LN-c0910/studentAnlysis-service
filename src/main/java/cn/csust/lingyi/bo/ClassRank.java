package cn.csust.lingyi.bo;

/**
 * Created by Enzo Cotter on 2020/4/20.
 * @author linan
 * 班级综测平均分排名类
 */

public class ClassRank {
    /**
     * 学年范围 如"2018-2019"
     */
    private String xuenian;
    /**
     * 专业
     */
    private String major;
    /**
     * 班级
     */
    private Integer classNo;
    /**
     * 班级平均分
     */
    private Double avgs;
    /**
     * 班级排名
     */
    private Integer classRank;

    public String getXuenian() {
        return xuenian;
    }

    public void setXuenian(String xuenian) {
        this.xuenian = xuenian;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getClassNo() {
        return classNo;
    }

    public void setClassNo(Integer classNo) {
        this.classNo = classNo;
    }

    public Double getAvgs() {
        return avgs;
    }

    public void setAvgs(Double avgs) {
        this.avgs = avgs;
    }

    public Integer getClassRank() {
        return classRank;
    }

    public void setClassRank(Integer classRank) {
        this.classRank = classRank;
    }

    @Override
    public String toString() {
        return "ClassRank{" +
                "xuenian='" + xuenian + '\'' +
                ", major='" + major + '\'' +
                ", classNo=" + classNo +
                ", avgs=" + avgs +
                ", classRank=" + classRank +
                '}';
    }
}
