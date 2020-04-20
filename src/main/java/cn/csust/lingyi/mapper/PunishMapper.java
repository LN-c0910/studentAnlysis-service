package cn.csust.lingyi.mapper;

import cn.csust.lingyi.pojo.Punish;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/3/19.
 */
public interface PunishMapper extends Mapper<Punish> {

    /**
     * 根据学号查询指定学年范围之间的惩罚记录 时间为null时查询所有记录 按时间降序
     * @param sno 学号
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @Select("SELECT * FROM punish WHERE studentNo=#{sno} AND IF(#{sTime} IS NOT NULL and #{sTime} IS NOT NULL,(Date(punishTime) BETWEEN #{sTime} AND #{eTime}),1=1) ORDER BY punishTime DESC")
    List<Punish> queryPunishBySnoandXuenian(@Param("sno") String sno, @Param("sTime") String sTime, @Param("eTime" ) String eTime);
}
