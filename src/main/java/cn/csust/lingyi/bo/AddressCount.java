package cn.csust.lingyi.bo;

/**
 * Created by Enzo Cotter on 2020/3/17.
 */
public class AddressCount {
    private String position;
    private Integer counts;

    public AddressCount() {
    }

    public AddressCount(String position, Integer counts) {
        this.position = position;
        this.counts = counts;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
