package cn.csust.lingyi.pojo;
import javax.persistence.*;
import java.util.Date;

/**
 * (Punish)实体类
 *
 * @author makejava
 * @since 2020-03-19 19:04:22
 */
@Entity
@Table(name="punish")
public class Punish {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String studentno;
        private String punishname;
        private String punishreason;
        private Date punishtime;
        private String stutype;
    
    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }
    
    public String getPunishname() {
        return punishname;
    }

    public void setPunishname(String punishname) {
        this.punishname = punishname;
    }
    
    public String getPunishreason() {
        return punishreason;
    }

    public void setPunishreason(String punishreason) {
        this.punishreason = punishreason;
    }
    
    public Date getPunishtime() {
        return punishtime;
    }

    public void setPunishtime(Date punishtime) {
        this.punishtime = punishtime;
    }
    
    public String getStutype() {
        return stutype;
    }

    public Long getId() {
        return id;
    }

    public Punish() {
    }

    public Punish(String studentno) {

        this.studentno = studentno;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStutype(String stutype) {
        this.stutype = stutype;
    }
}