package cn.csust.lingyi.pojo;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2020-03-15 16:42:14
 */
@Entity
@Table(name="student")
public class Student {
        @Id
        private String studentno;
        private String studentname;
        private String termyear;
        private String major;
        private Integer Class;
        private String sex;
        private String birthday;
        private String tutor;
        private String mz;
        private String zzmm;
        private String jg;
        private String personid;
        private String phone;
        private String qq;
        private String spouse;
        private String fathername;
        private String fatherphone;
        private String fatherworkplace;
        private String mothername;
        private String motherphone;
        private String motherworkplace;
        private String address;
        private String buildingname;
        private String roomnum;
        private String bednum;
        private String tc;
        private Integer sfps;
        private String studytype;
        private String stutype;

    public Student(String studentno) {
        this.studentno = studentno;
    }

    public Student() {
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }
    
    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
    
    public String getTermyear() {
        return termyear;
    }

    public void setTermyear(String termyear) {
        this.termyear = termyear;
    }
    
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    
    public Integer getclass() {
        return Class;
    }

    public void setClass(Integer Class) {
        this.Class = Class;
    }
    
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }
    
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }
    
    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }
    
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }
    
    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
    
    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }
    
    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }
    
    public String getFatherphone() {
        return fatherphone;
    }

    public void setFatherphone(String fatherphone) {
        this.fatherphone = fatherphone;
    }
    
    public String getFatherworkplace() {
        return fatherworkplace;
    }

    public void setFatherworkplace(String fatherworkplace) {
        this.fatherworkplace = fatherworkplace;
    }
    
    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }
    
    public String getMotherphone() {
        return motherphone;
    }

    public void setMotherphone(String motherphone) {
        this.motherphone = motherphone;
    }
    
    public String getMotherworkplace() {
        return motherworkplace;
    }

    public void setMotherworkplace(String motherworkplace) {
        this.motherworkplace = motherworkplace;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }
    
    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }
    
    public String getBednum() {
        return bednum;
    }

    public void setBednum(String bednum) {
        this.bednum = bednum;
    }
    
    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }
    
    public Object getSfps() {
        return sfps;
    }

    public void setSfps(Integer sfps) {
        this.sfps = sfps;
    }
    
    public String getStudytype() {
        return studytype;
    }

    public void setStudytype(String studytype) {
        this.studytype = studytype;
    }
    
    public String getStutype() {
        return stutype;
    }

    public void setStutype(String stutype) {
        this.stutype = stutype;
    }
}