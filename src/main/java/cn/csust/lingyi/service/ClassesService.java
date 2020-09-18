package cn.csust.lingyi.service;

import cn.csust.lingyi.bo.*;
import cn.csust.lingyi.common.VO.PageResult;
import cn.csust.lingyi.pojo.Personsummary;
import cn.csust.lingyi.pojo.Student;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;

/**
 * Created by Enzo Cotter on 2020/4/2.
 * @author linan
 */
public interface ClassesService {
    /**
     * 返回年级 专业 班级 的映射关系
     * @return 返回年级 专业 班级 的映射关系
     */

    List<ClassesMapping> queryClassesMapping();

    /**
     *  获取某班级4或6级通过率 或其他技能通过率
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级  如果为null 则统计整个专业
     * @param skillType 技能类型
     * @return 获取某班级4或6级通过率 或其他技能通过率
     */
    Double queryCET4or6PassRate(String termYear, String major, Integer classNo, SkillType skillType);

    /**
     * 获取班级总人数
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级  如果为null 则统计整个专业
     * @return 班级总人数
     */
    Integer querySizeByClass(String termYear, String major, Integer classNo);

    /**
     * 获取班级中某性别人数
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级 如果为null 则统计整个专业
     * @param sex 性别枚举 男|女
     * @return 班级中某性别人数
     */
    Integer querySexByClass(SexEnum sex,String termYear, String major, Integer classNo);

    /**
     * 获取班级的民族分布
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级  如果为null 则统计整个专业
     * @return 民族分布
     */
    List<Nation> queryNationByClass(String termYear, String major, Integer classNo);

    /**
     * 获取班级的政治面貌分布
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级  如果为null 则统计整个专业
     * @return 班级的政治面貌分布
     */
    List<PoliticalStatus> queryPoliticalStatusByClass(String termYear, String major, Integer classNo);


    /**
     * 获取班级的学生省份分布
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级  如果为null 则统计整个专业
     * @return 班级的学生省份分布 返回JSON字符串
     */
    String queryProvinceByClass(String termYear, String major, Integer classNo);

    /**
     * 通过班级查询班级学生成员
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级
     * @param page 开始页数
     * @param rows 每页行数
     * @return 班级学生成员分页结果
     */
    PageResult<Student> queryStudentByClass(String termYear, String major, Integer classNo,Integer page,Integer rows);

    /**
     * 计算班级综测平均分
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级
     * @param xn 学年范围
     * @return 班级综测平均分
     */
    Double avgScoreOfClass(String termYear, String major, Integer classNo,String xn);

    /**
     * 计算某学年班级综测平均分的年级排名
     * @param termYear 年级
     * @param xn 学年
     * @param major 专业
     * @param classNo 班级
     * @return 某学年班级综测平均分的年级排名
     */
    Integer avgScoreRankOfClass(String termYear, String xn,String major, Integer classNo);

    /**
     * 计算某学年班级挂科率
     * @param termYear 年级
     * @param xn 学年
     * @param major 专业
     * @param classNo 班级
     * @return 某学年班级挂科率
     */
    Double failsOfClass(String termYear, String xn,String major, Integer classNo);

    /**
     * 查询某学年某班级综测最高分
     * @param termYear 年级
     * @param xn 学年范围
     * @param major 专业
     * @param classNo 班级
     * @return 班级综测最高分
     */
    Double topScoreOfClass(String termYear, String xn,String major, Integer classNo);

    /**
     * 查询某学年班级综测成绩汇总表
     * @param termYear 年级
     * @param xn 学年
     * @param major 专业
     * @param classNo 班级
     * @param page 开始页码
     * @param rows 每页显示条数
     * @return 某学年班级综测成绩汇总分页结果
     */
    PageResult<Personsummary> queryScoreListByClass(String termYear, String xn,
                                                    String major, Integer classNo,
                                                    Integer page,Integer rows);

    /**
     * 获取班级画像词云图路径
     * @param termYear 年级
     * @param major 专业
     * @param classNo 班级
     * @return 图片地址
     */
    String getClassDescriptionWordCloud(String termYear,String major,Integer classNo);

}
