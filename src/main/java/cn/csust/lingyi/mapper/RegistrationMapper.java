package cn.csust.lingyi.mapper;

import cn.csust.lingyi.pojo.Registration;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/3/19.
 */
public interface RegistrationMapper extends Mapper<Registration> {
    @Select("select l.title " +
            "from registration as re " +
            "inner join " +
            "lecture as l " +
            "where " +
            "l.Id = re.lid and re.studentNo=#{sno}")
    List<String> queryLectureNameBySno(String sno);
}
