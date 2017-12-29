package com.huak.auth.model;

import java.io.Serializable;

public class Employee implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;

    private Long orgId;

    private String empName;

    private Byte sex;

    private Integer age;

    private String birthday;

    private String jobNum;

    private String tel;

    private String phone;

    private String email;

    private String memo;

    private String creator;

    private String createTime;

    private Byte status;

    private String pId;

    public Employee(String id, Long orgId, String empName, Byte sex, Integer age, String birthday, String jobNum, String tel, String phone, String email, String memo, String creator, String createTime,Byte status,String pId) {
        this.id = id;
        this.orgId = orgId;
        this.empName = empName;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
        this.jobNum = jobNum;
        this.tel = tel;
        this.phone = phone;
        this.email = email;
        this.memo = memo;
        this.creator = creator;
        this.createTime = createTime;
        this.status = status;
        this.pId=pId;
    }

    public Employee() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum == null ? null : jobNum.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }
}