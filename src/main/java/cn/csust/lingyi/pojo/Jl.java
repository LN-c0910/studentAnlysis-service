package cn.csust.lingyi.pojo;
import javax.persistence.*;
import java.io.Serializable;

/**
 * (Jl)实体类
 *
 * @author makejava
 * @since 2020-03-19 19:00:21
 */
@Entity
@Table(name="jl")
public class Jl implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long jlid;
        private String studentno;
        private String jlname;
        private String jllevel;
        private String gettime;
        private String adviser;
        private String sponsor;
        private String termorindividual;
        private String imagename;
        private String stutype;
    
    public Long getJlid() {
        return jlid;
    }

    public Jl(String studentno) {
        this.studentno = studentno;
    }

    public Jl() {
    }

    public void setJlid(Long jlid) {
        this.jlid = jlid;
    }
    
    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }
    
    public String getJlname() {
        return jlname;
    }

    public void setJlname(String jlname) {
        this.jlname = jlname;
    }
    
    public String getJllevel() {
        return jllevel;
    }

    public void setJllevel(String jllevel) {
        this.jllevel = jllevel;
    }
    
    public String getGettime() {
        return gettime;
    }

    public void setGettime(String gettime) {
        this.gettime = gettime;
    }
    
    public String getAdviser() {
        return adviser;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }
    
    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }
    
    public String getTermorindividual() {
        return termorindividual;
    }

    public void setTermorindividual(String termorindividual) {
        this.termorindividual = termorindividual;
    }
    
    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
    
    public String getStutype() {
        return stutype;
    }

    public void setStutype(String stutype) {
        this.stutype = stutype;
    }

    @Override
    public String toString() {
        return "Jl{" +
                "jlid=" + jlid +
                ", studentno='" + studentno + '\'' +
                ", jlname='" + jlname + '\'' +
                ", jllevel='" + jllevel + '\'' +
                ", gettime='" + gettime + '\'' +
                ", adviser='" + adviser + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", termorindividual='" + termorindividual + '\'' +
                ", imagename='" + imagename + '\'' +
                ", stutype='" + stutype + '\'' +
                '}';
    }
}