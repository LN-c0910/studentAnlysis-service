package cn.csust.lingyi.bo;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/4/3.
 */
public class Major_and_Classes {
    private String majorName;
    private List<Integer> classes;

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public List<Integer> getClasses() {
        return classes;
    }

    public void setClasses(List<Integer> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Major_and_Classes{" +
                "majorName='" + majorName + '\'' +
                ", classes=" + classes +
                '}';
    }
}
