package cn.csust.lingyi.mapper;

import cn.csust.lingyi.pojo.ViewScoreanalyses;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/4/25.
 * @author linan
 */
@Component
public interface ViewScoreAnalysesMapper extends Mapper<ViewScoreanalyses> {


    /**
     * 查询某学年某班级已参加综测的学生的成绩列表
     * @param termYear 年级
     * @param major 专业
     * @param cno 班级
     * @param xn 学年
     * @return 成绩统计数据列表
     */
    @Select("SELECT  * FROM view_scoreanalyses WHERE studentNo IN (\n" +
            "  SELECT studentNo FROM personknowledge WHERE personknowledge.studentNo IN (\n" +
            "    SELECT studentNo FROM student WHERE student.termYear=#{termYear} AND student.major=#{major} AND class =#{cno}\n" +
            "  )\n" +
            ") AND xuenian=#{xn}")
    List<ViewScoreanalyses> queryTestedScoreByClass(@Param("termYear") String termYear,
                                       @Param("major") String major,
                                       @Param("cno") Integer cno,
                                       @Param("xn") String xn);

}
