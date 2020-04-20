package cn.csust.lingyi.mapper;

import cn.csust.lingyi.pojo.Punish;
import cn.csust.lingyi.pojo.Skill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/3/19.
 */
public interface SkillMapper extends Mapper<Skill> {

    /**
     * 根据学号查询指定学年范围之间的技能记录 时间为null时查询所有记录 按时间降序
     * @param sno 学号
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @Select("SELECT * FROM skill WHERE studentNo=#{sno} AND IF(#{sTime} IS NOT NULL and #{sTime} IS NOT NULL,(Date(gettime) BETWEEN #{sTime} AND #{eTime}),1=1) ORDER BY gettime DESC")
    List<Skill> querySkillBySnoAndXuenian(@Param("sno") String sno, @Param("sTime") String sTime, @Param("eTime" ) String eTime);


    /**
     *
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级
     * @param skillType 技能类型 (英语四级|英语六级)
     * @return 统计班级英语4/六级通过人数
     */
    @Select("SELECT \n" +
            "  count(*)"+
            "FROM skill WHERE studentNo IN (\n" +
            "  SELECT student.studentNo \n" +
            "  FROM student \n" +
            "  WHERE termYear=#{termYear} \n" +
            "        AND major = #{major} \n" +
            "        AND if (#{classNo} is not null,class=#{classNo},1=1))\n" +
            "                 AND skillType=#{skillType}")
    Integer CETCountByClass(@Param("termYear") String termYear,
                            @Param("major") String major,
                            @Param("classNo") Integer classNo,
                            @Param("skillType") String skillType);
}
