package cn.csust.lingyi.bo;

/**
 * Created by Enzo Cotter on 2020/3/17.
 */
public class AddressCount {
    private String name;
    private Integer value;

    public AddressCount() {
    }

    public AddressCount(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
