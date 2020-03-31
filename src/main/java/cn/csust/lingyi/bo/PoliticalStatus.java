package cn.csust.lingyi.bo;

/**
 * Created by Enzo Cotter on 2020/3/16.
 */
public class PoliticalStatus {
    private String psname;
    private String termYear;
    private Integer counts;

    public String getPsname() {
        return psname;
    }

    public void setPsname(String psname) {
        this.psname = psname;
    }

    public String getTermYear() {
        return termYear;
    }

    public void setTermYear(String termYear) {
        this.termYear = termYear;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
