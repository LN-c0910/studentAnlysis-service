package cn.csust.lingyi.bo;

/**
 * Created by Enzo Cotter on 2020/4/26.
 */
public class ScoreSignal {
    private String sno;
    private String signal;

    public ScoreSignal() {}

    public ScoreSignal(String sno, String signal) {
        this.sno = sno;
        this.signal = signal;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    @Override
    public String toString() {
        return "ScoreSignal{" +
                "sno='" + sno + '\'' +
                ", signal='" + signal + '\'' +
                '}';
    }
}
