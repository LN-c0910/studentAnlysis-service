package cn.csust.lingyi.mapper;

import cn.csust.lingyi.pojo.Jl;
import cn.csust.lingyi.pojo.Practice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/3/19.
 */
public interface PracticeMapper extends Mapper<Practice> {

    /**
     * 根据学号查询指定学年范围之间的实践记录 时间为null时查询所有记录 并按时间降序
     * @param sno 学号
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @Select("SELECT * FROM practice WHERE studentNo=#{sno} AND IF(#{sTime} is not null and #{sTime} is not null,(Date(startTime) BETWEEN #{sTime} and #{eTime}),1=1) ORDER BY startTime DESC")
    List<Practice> queryPracticeBySnoAndXuenian(@Param("sno") String sno, @Param("sTime") String sTime, @Param("eTime" ) String eTime);


}
