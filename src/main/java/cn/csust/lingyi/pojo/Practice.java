package cn.csust.lingyi.pojo;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * (Practice)实体类
 *
 * @author makejava
 * @since 2020-03-19 19:02:57
 */
@Entity
@Table(name="practice")
public class Practice {

        private Long practiceid;
        private String studentno;
        private String practicename;
        private String type;
        private String starttime;
        private String endtime;
        private String stutype;

    public Practice(String studentno) {
        this.studentno = studentno;
    }

    public Practice() {

    }

    public Long getPracticeid() {
        return practiceid;
    }

    public void setPracticeid(Long practiceid) {
        this.practiceid = practiceid;
    }
    
    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }
    
    public String getPracticename() {
        return practicename;
    }

    public void setPracticename(String practicename) {
        this.practicename = practicename;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    
    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    
    public String getStutype() {
        return stutype;
    }

    public void setStutype(String stutype) {
        this.stutype = stutype;
    }
}