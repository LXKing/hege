package com.huak.task.model;

import java.io.Serializable;

public class EmcOrgInter implements Serializable{
    private static final long serialVersionUID = 1L;
    private String comId;

    private String orgId;

    private String emcId;

    private String unitType;

    public EmcOrgInter(String comId, String orgId, String emcId,String unitType) {
        this.comId = comId;
        this.orgId = orgId;
        this.emcId = emcId;
        this.unitType=unitType;
    }

    public EmcOrgInter() {
        super();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getEmcId() {
        return emcId;
    }

    public void setEmcId(String emcId) {
        this.emcId = emcId == null ? null : emcId.trim();
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
}