package cn.csust.lingyi.common.VO;

/**
 * Created by Enzo Cotter on 2020/3/19.
 */
public class RespData<T> {
    private String name;
    private T value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
