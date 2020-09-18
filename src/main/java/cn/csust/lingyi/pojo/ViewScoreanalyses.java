package cn.csust.lingyi.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VIEW(ViewScoreanalyses)实体类
 *
 * @author makejava
 * @since 2020-04-25 16:53:30
 */
@Entity
@Table(name = "view_Scoreanalyses")
public class ViewScoreanalyses {


    private String termyear;
    private String xuenian;

    private String studentno;
    private Double avgscore;
    private Double stdscore;
    private Integer maxscore;
    private Integer minscore;
    private String fails;
    private Long lt60;
    private Long gt60lt70;
    private Long gt70lt80;
    private Long gt80lt90;
    private Long gt90;

    public String getTermyear() {
        return termyear;
    }

    public void setTermyear(String termyear) {
        this.termyear = termyear;
    }

    public String getXuenian() {
        return xuenian;
    }

    public void setXuenian(String xuenian) {
        this.xuenian = xuenian;
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public Double getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(Double avgscore) {
        this.avgscore = avgscore;
    }

    public Double getStdscore() {
        return stdscore;
    }

    public void setStdscore(Double stdscore) {
        this.stdscore = stdscore;
    }

    public Integer getMaxscore() {
        return maxscore;
    }

    public void setMaxscore(Integer maxscore) {
        this.maxscore = maxscore;
    }

    public Integer getMinscore() {
        return minscore;
    }

    public void setMinscore(Integer minscore) {
        this.minscore = minscore;
    }

    public String getFails() {
        return fails;
    }

    public void setFails(String fails) {
        this.fails = fails;
    }

    public Long getLt60() {
        return lt60;
    }

    public void setLt60(Long lt60) {
        this.lt60 = lt60;
    }

    public Long getGt60lt70() {
        return gt60lt70;
    }

    public void setGt60lt70(Long gt60lt70) {
        this.gt60lt70 = gt60lt70;
    }

    public Long getGt70lt80() {
        return gt70lt80;
    }

    public void setGt70lt80(Long gt70lt80) {
        this.gt70lt80 = gt70lt80;
    }

    public Long getGt80lt90() {
        return gt80lt90;
    }

    public void setGt80lt90(Long gt80lt90) {
        this.gt80lt90 = gt80lt90;
    }

    public Long getGt90() {
        return gt90;
    }

    public void setGt90(Long gt90) {
        this.gt90 = gt90;
    }

    @Override
    public String toString() {
        return "ViewScoreanalyses{" +
                "termyear='" + termyear + '\'' +
                ", xuenian='" + xuenian + '\'' +
                ", studentno='" + studentno + '\'' +
                ", avgscore=" + avgscore +
                ", stdscore=" + stdscore +
                ", maxscore=" + maxscore +
                ", minscore=" + minscore +
                ", fails='" + fails + '\'' +
                ", lt60=" + lt60 +
                ", gt60lt70=" + gt60lt70 +
                ", gt70lt80=" + gt70lt80 +
                ", gt80lt90=" + gt80lt90 +
                ", gt90=" + gt90 +
                '}';
    }
}