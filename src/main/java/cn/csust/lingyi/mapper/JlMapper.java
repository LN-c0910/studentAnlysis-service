package cn.csust.lingyi.mapper;

import cn.csust.lingyi.pojo.Jl;
import cn.csust.lingyi.pojo.Punish;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/3/19.
 */
public interface JlMapper extends Mapper<Jl> {

    /**
     * 根据学号查询指定学年范围之间的奖励记录 时间为null时查询所有记录 并按时间降序
     * @param sno 学号
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @Select("SELECT * FROM jl WHERE studentNo=#{sno} AND IF(#{sTime} is not null and #{sTime} is not null,(Date(getTime) BETWEEN #{sTime} and #{eTime}),1=1) ORDER BY getTime DESC")
    List<Jl> queryJlBySnoAndXuenian(@Param("sno") String sno,@Param("sTime") String sTime,@Param("eTime" ) String eTime);


}
