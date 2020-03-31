package cn.csust.lingyi.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * (Personmoral)实体类
 *
 * @author makejava
 * @since 2020-03-19 19:02:42
 */
@Entity
@Table(name = "personmoral")
public class Personmoral {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String xuenian;
    private String studentno;
    //德育分加分类别
    private Integer mid;
    private Integer jid;
    private String name;
    private Double score;
    private String gettime;
    private String imagepath;

    public Long getId() {
        return Id;
    }

    public Personmoral(String studentno,String xuenian) {
        this.studentno = studentno;
        this.xuenian = xuenian;
    }

    public Personmoral(String studentno) {
        this.studentno = studentno;
    }

    public Personmoral() {

    }

    public void setId(Long id) {

        Id = id;
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

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getGettime() {
        return gettime;
    }

    public void setGettime(String gettime) {
        this.gettime = gettime;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}