package cn.csust.lingyi.pojo;
import javax.persistence.*;

/**
 * (Skill)实体类
 *
 * @author makejava
 * @since 2020-03-19 19:04:41
 */
@Entity
@Table(name="skill")
public class Skill {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long skillid;
        private String studentno;
        private String skillname;
        private String skilltype;
        private String gettime;
        private String stutype;

    public Skill(String studentno) {
        this.studentno = studentno;
    }

    public Skill() {

    }

    public Long getSkillid() {
        return skillid;
    }

    public void setSkillid(Long skillid) {
        this.skillid = skillid;
    }
    
    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }
    
    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }
    
    public String getSkilltype() {
        return skilltype;
    }

    public void setSkilltype(String skilltype) {
        this.skilltype = skilltype;
    }
    
    public String getGettime() {
        return gettime;
    }

    public void setGettime(String gettime) {
        this.gettime = gettime;
    }
    
    public String getStutype() {
        return stutype;
    }

    public void setStutype(String stutype) {
        this.stutype = stutype;
    }
}