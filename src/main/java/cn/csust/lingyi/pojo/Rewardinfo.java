package cn.csust.lingyi.pojo;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * (Rewardinfo)实体类
 *
 * @author makejava
 * @since 2020-03-19 19:05:32
 */
@Entity
@Table(name="rewardinfo")
public class Rewardinfo {
                                
                                        
                                        
                                        
                                        
                                        
                                        
                

        private Long rewardid;
        private String rewardtime;
        private String rewardname;
        private String rewardlevel;
        private String sponsor;
        private String termorindividual;
        private String stutype;
    
    public Long getRewardid() {
        return rewardid;
    }

    public void setRewardid(Long rewardid) {
        this.rewardid = rewardid;
    }
    
    public String getRewardtime() {
        return rewardtime;
    }

    public void setRewardtime(String rewardtime) {
        this.rewardtime = rewardtime;
    }
    
    public String getRewardname() {
        return rewardname;
    }

    public void setRewardname(String rewardname) {
        this.rewardname = rewardname;
    }
    
    public String getRewardlevel() {
        return rewardlevel;
    }

    public void setRewardlevel(String rewardlevel) {
        this.rewardlevel = rewardlevel;
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
    
    public String getStutype() {
        return stutype;
    }

    public void setStutype(String stutype) {
        this.stutype = stutype;
    }
}