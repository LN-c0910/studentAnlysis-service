package cn.csust.lingyi.service;

import cn.csust.lingyi.common.VO.RespData;
import cn.csust.lingyi.pojo.Personknowledge;
import cn.csust.lingyi.pojo.Student;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.List;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/3/18.
 */
public interface PersonalService {
    //获取学生某个学期的科目成绩
    List<Personknowledge> queryCourseScoresBySno(String sno,String xuenian,Integer limit);

    //获取学生某学期德，智，体育分,并封装成map
    Map<String,Double> queryMKSByStu(String sno,String xuenian);

    //获取学生相关词条分词结果,返回图片路径
    String queryDescrBySno(String sno);

    //获取学生基本信息
    Student queryStuBySno(String sno);

    //获取学生搜索建议
    List<RespData> queryStuAdvBySname(String sname);

    //计算总挂科率
    Double queryTfailedratesBySno(String sno);

    //计算某学年挂科率
    Double queryfailRatesBySnoAndXuenian(String sno,String xuenian);

    //根据cid查询学生科目信息
    List<Personknowledge> queryCourseByCid(Long cid,String sno);

    //获取综测专业排名
    Integer queryRankBySnoAndXuenian(String sno,String xuenian);
}
