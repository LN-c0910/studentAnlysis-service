package cn.csust.lingyi.pojo;

import javax.persistence.*;

/**
 * (Personknowledge)实体类
 *
 * @author makejava
 * @since 2020-03-18 22:49:22
 */
@Entity
@Table(name = "personknowledge")
public class Personknowledge {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String studentno;
    private Integer cid;
    private Integer score;
    /**
     * 课程名
     */
    @Transient
    private String cname;
    /**
     *  学生姓名
     */
    @Transient
    private String sname;
    /**
     * 绩点
     */
    @Transient
    private Float jd;

    /**
     * 学分
     */
    @Transient
    private Double credit;


    public Personknowledge(String studentNo) {
        this.studentno = studentNo;
    }

    public Personknowledge() {}

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getStudentNo() {
        return studentno;
    }

    public void setStudentNo(String studentNo) {
        this.studentno = studentNo;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Float getJd() {
        return jd;
    }

    public void setJd(Float jd) {
        this.jd = jd;
    }

    @Override
    public String  toString() {
        return "Personknowledge{" +
                "Id=" + Id +
                ", studentNo='" + studentno + '\'' +
                ", cid=" + cid +
                ", score=" + score +
                ", cname='" + cname + '\'' +
                ", sname='" + sname + '\'' +
                ", jd=" + jd +
                ", credit=" + credit +
                '}';
    }
}