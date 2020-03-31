package cn.csust.lingyi.pojo;
import javax.persistence.*;

/**
 * (Personsummary)实体类
 *
 * @author makejava
 * @since 2020-03-19 17:19:15
 */
@Entity
@Table(name="personsummary")
public class Personsummary {
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @javax.persistence.Id

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;
        private String xuenian;
        private String studentno;
        private Double moral;

    public Personsummary(String studentno) {
        this.studentno = studentno;
    }

    public Personsummary() {

    }

    private Double knowledge;
        private Double sports;
        private Double deduction;
        private Integer fails;
        private String bz;
        private Integer status;
        private Integer tban;

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

    public Double getMoral() {
        return moral;
    }

    public void setMoral(Double moral) {
        this.moral = moral;
    }

    public Double getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Double knowledge) {
        this.knowledge = knowledge;
    }

    public Double getSports() {
        return sports;
    }

    public void setSports(Double sports) {
        this.sports = sports;
    }

    public Double getDeduction() {
        return deduction;
    }

    public void setDeduction(Double deduction) {
        this.deduction = deduction;
    }

    public Integer getFails() {
        return fails;
    }

    public void setFails(Integer fails) {
        this.fails = fails;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTban() {
        return tban;
    }

    public void setTban(Integer tban) {
        this.tban = tban;
    }
}