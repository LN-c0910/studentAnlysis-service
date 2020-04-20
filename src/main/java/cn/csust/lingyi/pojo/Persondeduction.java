package cn.csust.lingyi.pojo;
import javax.persistence.*;

/**
 * (PersondeductionMapper)实体类
 *
 * @author makejava
 * @since 2020-03-19 19:01:22
 */
@Entity
@Table(name="persondeduction")
public class Persondeduction {
        @javax.persistence.Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;
        private String xuenian;
        private String studentno;
        private Integer did;
        private Integer times;
        @Transient
        private String dname;
    public Persondeduction() {}
    public Persondeduction(String studentno) {
        this.studentno = studentno;
    }
    public String getXuenian() {
        return xuenian;
    }

    public void setXuenian(String xuenian) {
        this.xuenian = xuenian;
    }
    
    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }
    
    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }
    
    public Integer getTimes() {
        return times;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}