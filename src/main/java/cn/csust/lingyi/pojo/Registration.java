package cn.csust.lingyi.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * 报名表(Registration)实体类
 *
 * @author makejava
 * @since 2020-03-19 19:05:49
 */
@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String studentno;
    private Integer lid;
    private Date registrationtime;
    private String status;
    @Transient
    private String lecturename;

    public String getStudentno() {
        return studentno;
    }

    public Registration() {
    }

    public Registration(String studentno) {

        this.studentno = studentno;
    }

    public String getLecturename() {
        return lecturename;
    }

    public void setLecturename(String lecturename) {
        this.lecturename = lecturename;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Date getRegistrationtime() {
        return registrationtime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setRegistrationtime(Date registrationtime) {
        this.registrationtime = registrationtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}