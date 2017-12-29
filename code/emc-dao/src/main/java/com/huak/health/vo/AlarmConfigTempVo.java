package com.huak.health.vo;

import java.io.Serializable;

public class AlarmConfigTempVo implements Serializable{

    private static final long serialVersionUID = -2243817388826397625L;
    private String id;

    private String comId;

    private String unitId;

    private Double mintemp;

    private Double maxtemp;

    private Byte unitType;

    private Byte isenable;

    private String orgId;

    private String orgName;

    private String unitName;

    public AlarmConfigTempVo(String id, String comId, String unitId, Double mintemp, Double maxtemp, Byte unitType, Byte isenable, String orgId, String orgName, String unitName) {
        this.id = id;
        this.comId = comId;
        this.unitId = unitId;
        this.mintemp = mintemp;
        this.maxtemp = maxtemp;
        this.unitType = unitType;
        this.isenable = isenable;
        this.orgId = orgId;
        this.orgName = orgName;
        this.unitName = unitName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Double getMintemp() {
        return mintemp;
    }

    public void setMintemp(Double mintemp) {
        this.mintemp = mintemp;
    }

    public Double getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(Double maxtemp) {
        this.maxtemp = maxtemp;
    }

    public Byte getUnitType() {
        return unitType;
    }

    public void setUnitType(Byte unitType) {
        this.unitType = unitType;
    }

    public Byte getIsenable() {
        return isenable;
    }

    public void setIsenable(Byte isenable) {
        this.isenable = isenable;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}