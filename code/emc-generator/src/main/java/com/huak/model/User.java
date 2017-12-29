package com.huak.model;

import java.util.Date;

public class User {
    private Long userId;

    private Long orgId;

    private String login;

    private String password;

    private Date loginTime;

    private Date lastLoginTime;

    private Integer loginNum;

    private Byte useStatus;

    private Long creator;

    private Date createTime;

    public User(Long userId, Long orgId, String login, String password, Date loginTime, Date lastLoginTime, Integer loginNum, Byte useStatus, Long creator, Date createTime) {
        this.userId = userId;
        this.orgId = orgId;
        this.login = login;
        this.password = password;
        this.loginTime = loginTime;
        this.lastLoginTime = lastLoginTime;
        this.loginNum = loginNum;
        this.useStatus = useStatus;
        this.creator = creator;
        this.createTime = createTime;
    }

    public User() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Byte getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Byte useStatus) {
        this.useStatus = useStatus;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}