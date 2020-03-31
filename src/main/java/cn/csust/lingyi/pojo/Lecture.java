package cn.csust.lingyi.pojo;
import java.util.Date;
import javax.persistence.*;

/**
 * 讲座信息表(Lecture)实体类
 *
 * @author makejava
 * @since 2020-03-19 19:00:51
 */
@Entity
@Table(name="lecture")
public class Lecture {
        @javax.persistence.Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;
        private String xuenian;
    //讲座主题
        private String title;
        private String holdtime;
        private String location;
        private String speaker;
        private String introduction;
        private Date deadlinetime;
        private Integer limitnumber;
        private String publisher;
        private Date releasetime;
        private String speakerintroduction;
        private String imagepath;
    //优先级
        private Integer priority;

    public Lecture() {
    }

    public String getXuenian() {
        return xuenian;
    }

    public void setXuenian(String xuenian) {
        this.xuenian = xuenian;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getHoldtime() {
        return holdtime;
    }

    public void setHoldtime(String holdtime) {
        this.holdtime = holdtime;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
    
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    
    public Date getDeadlinetime() {
        return deadlinetime;
    }

    public void setDeadlinetime(Date deadlinetime) {
        this.deadlinetime = deadlinetime;
    }
    
    public Integer getLimitnumber() {
        return limitnumber;
    }

    public void setLimitnumber(Integer limitnumber) {
        this.limitnumber = limitnumber;
    }
    
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }
    
    public String getSpeakerintroduction() {
        return speakerintroduction;
    }

    public void setSpeakerintroduction(String speakerintroduction) {
        this.speakerintroduction = speakerintroduction;
    }
    
    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
    
    public Integer getPriority() {
        return priority;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}