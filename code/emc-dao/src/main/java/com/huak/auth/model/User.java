package com.huak.auth.model;

import java.io.Serializable;


public class User implements Serializable{
    private static final long serialVersionUID = 5316500870437437959L;
    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String id;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String login;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String password;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String userName;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String mobile;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String mail;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String loginTime;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String lastLoginTime;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private Integer loginNum;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String useStatus;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String memo;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String creator;

    /**
     *
     * @preserve 声明此方法不被JOC混淆
     */
    private String createTime;
    
    private String orgId;
    
    private String empId;

    public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login == null ? null : login.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus == null ? null : useStatus.trim();
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
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
    
}