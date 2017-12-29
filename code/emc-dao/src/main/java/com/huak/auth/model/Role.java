package com.huak.auth.model;

import java.io.Serializable;

public class Role implements Serializable{
    private static final long serialVersionUID = -5793762861215338383L;
    private String id;

    private String roleName;

    private String roleDes;

    private String memo;

    private String creator;

    private String createTime;

    public Role(String id, String roleName, String roleDes, String memo, String creator, String createTime) {
        this.id = id;
        this.roleName = roleName;
        this.roleDes = roleDes;
        this.memo = memo;
        this.creator = creator;
        this.createTime = createTime;
    }

    public Role() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDes() {
        return roleDes;
    }

    public void setRoleDes(String roleDes) {
        this.roleDes = roleDes == null ? null : roleDes.trim();
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
}