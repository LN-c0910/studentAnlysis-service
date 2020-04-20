package cn.csust.lingyi.bo;

/**
 * Created by Enzo Cotter on 2020/3/16.
 */
public class Nation {
    private String mzname;
    private Integer mznumber;

    public Nation() {
    }

    public Nation(String mzname, Integer mznumber) {
        this.mzname = mzname;
        this.mznumber = mznumber;
    }

    public String getMzname() {
        return mzname;
    }

    public void setMzname(String mzname) {
        this.mzname = mzname;
    }

    public Integer getMznumber() {
        return mznumber;
    }

    public void setMznumber(Integer mznumber) {
        this.mznumber = mznumber;
    }

    @Override
    public String toString() {
        return "Nation{" +
                "mzname='" + mzname + '\'' +
                ", mznumber=" + mznumber +
                '}';
    }
}
