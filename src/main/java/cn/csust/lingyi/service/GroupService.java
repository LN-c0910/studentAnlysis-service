package cn.csust.lingyi.service;

import cn.csust.lingyi.bo.ScoreSignal;

import java.util.List;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/4/25.
 * @author linan
 */
public interface GroupService {

    /**
     * 通过学号预测综测成绩预警信号(不分学年，计算综合成绩)
     * @param sno 学号
     * @return 预警信号
     */
    List<ScoreSignal> queryScoreSignalByStu(String sno);

    /**
     * 预测某个班的所有学生的成绩预警
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级
     * @param xn 学年
     * @return 预警信号列表
     */
    List<ScoreSignal> queryScoreSignalByClass(String termYear,String major,Integer classNo,String xn);
}
