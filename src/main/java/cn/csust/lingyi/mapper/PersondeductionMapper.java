package cn.csust.lingyi.mapper;

import cn.csust.lingyi.pojo.Persondeduction;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/3/19.
 */
public interface PersondeductionMapper extends Mapper<Persondeduction> {

    @Select("select d.name as dname from deduction as d inner join persondeduction as pd where d.Id=pd.did and pd.studentNo=#{sno}")
    List<Persondeduction> queryDeductionsBySno(String sno);
}
