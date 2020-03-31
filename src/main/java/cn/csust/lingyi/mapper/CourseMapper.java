package cn.csust.lingyi.mapper;

import cn.csust.lingyi.pojo.Personknowledge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/3/18.
 */
public interface CourseMapper extends Mapper<Personknowledge> {

    /**
     *  //查询学生科目成绩
     * @param sno String 学号
     * @param xuenian String 学年 ,xuenian为"0",不将xuenian作为查询条件
     * @param limit 限制查询条数
     * @return
     */
    @Select("SELECT\n" +
            "  ps.studentNo,\n" +
            "  c.courseName as cname,\n" +
            "  ps.score,\n" +
            "  c.credit,\n"+
            "  ps.cid,\n"+
            "  (SELECT s.studentName FROM student as s WHERE ps.studentNo=s.studentNo ) AS sname,\n" +
            "  (\n" +
            "    CASE\n" +
            "    WHEN score BETWEEN 90 AND 100 THEN\n" +
            "      4.0\n" +
            "    WHEN score BETWEEN 85 AND 89 THEN\n" +
            "      3.7\n" +
            "    WHEN score BETWEEN 82 AND 84 THEN\n" +
            "      3.3\n" +
            "    WHEN score BETWEEN 78 AND 81 THEN\n" +
            "      3\n" +
            "    WHEN score BETWEEN 75 AND 77 THEN\n" +
            "      2.7\n" +
            "    WHEN score BETWEEN 72 AND 74 THEN\n" +
            "      2.3\n" +
            "    WHEN score BETWEEN 68 AND 71 THEN\n" +
            "      2\n" +
            "    WHEN score BETWEEN 66 AND 67 THEN\n" +
            "      1.7\n" +
            "    WHEN score BETWEEN 64 AND 65 THEN\n" +
            "      1.3\n" +
            "    WHEN score BETWEEN 60 AND 63 THEN\n" +
            "      1\n" +
            "    WHEN score<60 THEN\n" +
            "      0\n" +
            "    END\n" +
            "      ) as 'jd'\n" +
            "FROM course as c\n" +
            "  INNER JOIN\n" +
            "  personknowledge as ps\n" +
            "WHERE\n" +
            "  c.Id=ps.cid\n" +
            "  and\n" +
            "  if (#{xuenian}<>'0',c.xuenian = #{xuenian},1=1)\n" +
            "  AND\n" +
            "  ps.studentNo = #{sno}\n" +
            "  AND ps.score <> -1\n" +
            "ORDER BY score DESC limit #{limit}")
    List<Personknowledge> queryCourseScoresBySno(@Param("sno") String sno,@Param("xuenian") String xuenian,@Param("limit") Integer limit);

    /**
     * 查询学生某学年挂科数
     * @param sno
     * @param xuenian
     * @return 学生某学年挂科数
     */
    @Select("select count(*) from course as c inner join personknowledge as pd where c.Id=pd.cid and c.xuenian=#{xuenian} and pd.studentNo=#{sno} and score <> -1 and score < 60")
    Integer queryfailsBySnoAndXuenian(@Param("sno") String sno,@Param("xuenian")String xuenian);

    /**
     *
     * @param sno
     * @param xuenian
     * @return 学生某学年课程数
     */
    @Select("select count(*) from course as c inner join personknowledge as pd where c.Id=pd.cid and c.xuenian=#{xuenian} and pd.studentNo=#{sno} and score <> -1")
    Integer queryCourseCountsBySnoAndXuenian(@Param("sno") String sno,@Param("xuenian") String xuenian);

    @Select("select c.courseName as cname,ps.score from course as c inner join personknowledge as ps where c.Id = ps.cid and ps.cid=#{cid} and ps.studentNo=#{sno}")
    List<Personknowledge> queryCourseByCid(@Param("cid") Long cid,@Param("sno") String sno);

    /**
     * 根据学号 学年查询综测专业排名
     */
    @Select("SELECT rowNo FROM (SELECT studentNo,(@rowNum:=@rowNum+1) AS rowNo\n" +
            "               FROM (\n" +
            "                      select st.studentNo, if(sm.moral>25,25+sm.knowledge+sm.sports-sm.deduction,sm.moral+sm.knowledge+sm.sports-sm.deduction) as sum\n" +
            "                      from personsummary sm\n" +
            "                        left join student st on sm.studentNo =st.studentNo\n" +
            "                      where sm.xuenian = #{xuenian}\n" +
            "                            and  st.termYear in (SELECT s.termYear FROM student as s WHERE studentNo = #{sno})\n" +
            "                            and  st.major  in (SELECT s.major FROM student as s WHERE studentNo = #{sno})\n" +
            "                      order by sum  desc\n" +
            "                    ) AS obj,(SELECT(@rowNum:=0)) b ORDER BY sum DESC) c\n" +
            "WHERE studentNo=#{sno}")
    Integer queryRankBySnoAndXuenian(@Param("sno") String sno,@Param("xuenian") String xuenian);
}
