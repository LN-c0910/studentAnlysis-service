package cn.csust.lingyi.service;

import java.util.List;

/**
 * Created by Enzo Cotter on 2020/3/20.
 */
public interface AdminService {
    //添加用户字典
    String add_user_dict(List<String> userdict);
    //添加停用词
    String add_stop_words(List<String> stopwd);

    //清理云词图
    String del_file(String token);

}
