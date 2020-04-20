package cn.csust.lingyi.mapper;

import cn.csust.lingyi.bo.ClassRank;
import cn.csust.lingyi.pojo.Personsummary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

/**
 * Created by Enzo Cotter on 2020/3/19.
 * @author linan
 */
public interface PersonsummaryMapper extends Mapper<Personsummary> {

    /**
     * 获取某年级某学年各班级评测平均分和排名
     * @param termYear 年级
     * @param xn 学年范围
     * @return 班级平均分列表
     */
    @Select("SELECT t.xuenian,t.major,t.class classNo,t.avgs,(@Rank:=@Rank+1) AS classRank FROM (\n" +
            "  SELECT\n" +
            "    p.xuenian,\n" +
            "    s.major,\n" +
            "    s.class,\n" +
            "    ROUND(SUM(p.moral + p.knowledge + p.sports - p.deduction) / COUNT(s.class),2) AS avgs\n" +
            "  FROM personsummary p, student s\n" +
            "  WHERE s.studentNo = p.studentNo\n" +
            "        AND s.termYear = #{termYear}\n" +
            "        AND p.xuenian = #{xn}\n" +
            "  GROUP BY s.major, s.class\n" +
            "  HAVING avgs > 10\n" +
            "         AND COUNT(s.class) > 10\n" +
            "  ORDER BY avgs DESC\n" +
            ") as t,(SELECT (@Rank:=0)) as r;")
    List<ClassRank> queryClassRank(@Param("termYear") String termYear, @Param("xn") String xn) ;

    /**
     * 某学年班级挂科人数占比
     * @param termYear 年级
     * @param xn 学年
     * @param major 专业
     * @param classNo 班级
     * @return 学年班级挂科人数占比
     */
    @Select("SELECT ROUND( (SELECT COUNT(*)\n" +
            "        FROM personsummary WHERE studentNo IN (\n" +
            "          SELECT s.studentNo FROM student AS s WHERE s.termYear=#{termYear} AND major=#{major} AND class=#{classNo}\n" +
            "        ) AND xuenian=#{xn} AND fails != 0)/COUNT(*),2) AS failsRate\n" +
            "FROM personsummary WHERE studentNo IN (\n" +
            "  SELECT s.studentNo FROM student AS s WHERE s.termYear=#{termYear} AND major=#{major} AND class=#{classNo}\n" +
            ") AND xuenian=#{xn}")
    Double queryClassFailsRate(@Param("termYear") String termYear,@Param("xn") String xn,@Param("major") String major,
                               @Param("classNo") Integer classNo);


    /**
     * 查询某学年某班级综测最高分
     * @param termYear 年级
     * @param xn 学年范围
     * @param major 专业
     * @param classNo 班级
     * @return 某学年某班级综测最高分
     */
    @Select("SELECT (moral+knowledge+sports-deduction) as total\n" +
            "FROM personsummary WHERE studentNo IN (\n" +
            "  SELECT s.studentNo FROM student AS s WHERE s.termYear=#{termYear} AND major=#{major} AND class=#{classNo}\n" +
            ") AND xuenian=#{xn} ORDER BY total DESC LIMIT 1")
    Double queryTopScoreOfClass(@Param("termYear") String termYear,@Param("xn") String xn,@Param("major") String major,
                                @Param("classNo") Integer classNo);

    /**
     * 查询某学年班级综测成绩汇总表
     * @param termYear 年级
     * @param xn 学年
     * @param major 专业
     * @param classNo 班级
     * @return 综测成绩列表
     */
    @Select("SELECT p.*,s.studentName stuName\n" +
            "FROM personsummary p,student s\n" +
            "WHERE p.studentNo\n" +
            "      IN (\n" +
            "        SELECT studentNo FROM student\n" +
            "        WHERE termYear=#{termYear} AND\n" +
            "          major=#{major} AND\n" +
            "          class=#{classNo}\n" +
            "      )\n" +
            "      AND xuenian = #{xn}\n" +
            "      AND p.studentNo = s.studentNo ORDER BY p.studentNo")
    List<Personsummary> queryScoreListByClass(@Param("termYear") String termYear,@Param("xn") String xn,
                                              @Param("major") String major, @Param("classNo") Integer classNo);
}
