package com.huak.auth.model;

import java.io.Serializable;

public class RoleFuncRel implements Serializable{
    private static final long serialVersionUID = 4803165687931353533L;
    private String roleId;

    private String funcId;

    public RoleFuncRel(String roleId, String funcId) {
        this.roleId = roleId;
        this.funcId = funcId;
    }

    public RoleFuncRel() {
        super();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId == null ? null : funcId.trim();
    }
}