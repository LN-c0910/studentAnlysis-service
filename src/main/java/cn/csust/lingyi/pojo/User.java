package cn.csust.lingyi.pojo;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-03-15 19:00:37
 */
@Entity
@Table(name="user")
public class User {

        private String username;
        private String password;
    //默认角色为学生
        private String usertype;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}