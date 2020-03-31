package cn.csust.lingyi.mapper;

import cn.csust.lingyi.bo.Nation;
import cn.csust.lingyi.bo.PoliticalStatus;
import cn.csust.lingyi.pojo.Personknowledge;
import cn.csust.lingyi.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/3/15.
 */
public interface StudentMapper extends Mapper<Student> {

    //查询性别为空的数量
    @Select("select count(*) from student where sex is null")
    Integer selectSexisnull();

    //查询专业种类数,过滤空值和专业种类数<100,过滤垃圾数据
    @Select("select count(*) FROM (\n" +
            "  SELECT\n" +
            "    major,\n" +
            "    count(*) AS counts\n" +
            "  FROM student\n" +
            "  WHERE major IS NOT NULL\n" +
            "  and stuType='本科生'\n"+
            "  GROUP BY major\n" +
            "  HAVING counts > 30   \n" +
            "\n" +
            ") AS res")
    Integer selectMajorNum();

    //查询班级数
    @Select("SELECT count(*) FROM (SELECT COUNT(*) as nums,major,class,termYear FROM student where major is not null GROUP BY termYear,major,class HAVING nums>10) as res")
    Integer selectClassNum();

    //查询年级
    @Select("SELECT DISTINCT termYear FROM student WHERE termYear is not null ORDER BY termYear")
    List<String> listGrades();

    //查询民族数量 初步清洗数据，过滤非字符数据
    @Select("SELECT count(*) as mznumber,MZ as mzname FROM student WHERE MZ REGEXP '[^a-w0-9]' AND if(#{year}<>'0',termYear=#{year},'1=1') GROUP BY MZ")
    List<Nation>getNationNumByTermYear(@Param("year") String year);

    //查询民族数量(测试用例),所有学生
    @Select("SELECT count(*) as mznumber,MZ as mzname FROM student WHERE MZ REGEXP '[^a-w0-9]' GROUP BY MZ;")
    List<Nation> tgetNationNumByTermYear();

    //查询各年级政治面貌
    @Select("SELECT count(*) as counts,ZZMM as psname,termYear as termYear FROM student WHERE ZZMM in ('群众','共青团员','积极分子','预备党员','党员')  GROUP BY ZZMM,termYear")
    List<PoliticalStatus> pscount();

    //查询全校政治面貌
    @Select("SELECT count(*) as counts,ZZMM as psname FROM student WHERE ZZMM in ('群众','共青团员','积极分子','预备党员','党员')  GROUP BY ZZMM")
    List<PoliticalStatus> allpscount();

    //查询学生地址信息,初步过滤非法地址
    @Select("select address from student where address REGEXP '[^a-w0-9]' and address is not null and LENGTH(address)>3")
    List<String> queryAddress();

    //测试用例
    @Select("select address from student where address REGEXP '[^a-w0-9]' and address is not null and LENGTH(address)>3")
    List<String> queryAddrt();


}
