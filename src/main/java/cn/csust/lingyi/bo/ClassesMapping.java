package cn.csust.lingyi.bo;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/4/3.
 */
public class ClassesMapping {
    private String termYear;
    private List<Major_and_Classes> major;

    public String getTermYear() {
        return termYear;
    }

    public void setTermYear(String termYear) {
        this.termYear = termYear;
    }

    public List<Major_and_Classes> getMajor() {
        return major;
    }

    public void setMajor(List<Major_and_Classes> major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "ClassesMapping{" +
                "termYear='" + termYear + '\'' +
                ", major=" + major +
                '}';
    }
}
