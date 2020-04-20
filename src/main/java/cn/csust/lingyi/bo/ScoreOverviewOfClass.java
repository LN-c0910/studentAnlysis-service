package cn.csust.lingyi.bo;

/**
 * Created by Enzo Cotter on 2020/4/20.
 * @author linan
 * 班级画像成绩概况数据模型
 */
public class ScoreOverviewOfClass {
    /**
     * 综测班级平均成绩
     */
    private Double avgScoreOfClass;
    /**
     * 班级平均综测成绩年级排名
     */
    private Integer avgScoreRankOfClass;
    /**
     * 挂科人数占比
     */
    private Double failsOfClass;
    /**
     * 综测最高分
     */
    private Double topScore;
    /**
     * 班级英语4级通过率
     */
    private Double cet4PassRate;
    /**
     * 班级英语6级通过率
     */
    private Double cet6PassRate;

    public Double getAvgScoreOfClass() {
        return avgScoreOfClass;
    }

    public void setAvgScoreOfClass(Double avgScoreOfClass) {
        this.avgScoreOfClass = avgScoreOfClass;
    }

    public Integer getAvgScoreRankOfClass() {
        return avgScoreRankOfClass;
    }

    public void setAvgScoreRankOfClass(Integer avgScoreRankOfClass) {
        this.avgScoreRankOfClass = avgScoreRankOfClass;
    }

    public Double getFailsOfClass() {
        return failsOfClass;
    }

    public void setFailsOfClass(Double failsOfClass) {
        this.failsOfClass = failsOfClass;
    }

    public Double getTopScore() {
        return topScore;
    }

    public void setTopScore(Double topScore) {
        this.topScore = topScore;
    }



    public Double getCet4PassRate() {
        return cet4PassRate;
    }

    public void setCet4PassRate(Double cet4PassRate) {
        this.cet4PassRate = cet4PassRate;
    }

    public Double getCet6PassRate() {
        return cet6PassRate;
    }

    public void setCet6PassRate(Double cet6PassRate) {
        this.cet6PassRate = cet6PassRate;
    }

    @Override
    public String toString() {
        return "ScoreOverviewOfClass{" +
                "avgScoreOfClass=" + avgScoreOfClass +
                ", avgScoreRankOfClass=" + avgScoreRankOfClass +
                ", failsOfClass=" + failsOfClass +
                ", topScore=" + topScore +
                ", cet4PassRate=" + cet4PassRate +
                ", cet6PassRate=" + cet6PassRate +
                '}';
    }
}
