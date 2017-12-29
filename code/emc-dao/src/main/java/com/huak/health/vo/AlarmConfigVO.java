package com.huak.health.vo;

import java.io.Serializable;

public class AlarmConfigVO implements Serializable {
    private static final long serialVersionUID = 7046455220653574627L;
    private String id;

    private String tag;

    private String alarmName;

    private Byte alarmType;

    private Byte alarmLevel;

    private Byte model;

    private Double num;

    private Byte isenable;

    private String comId;

    private String unitId;

    private Byte unitType;

    private String orgId;

    private String orgName;

    private String unitName;

    public AlarmConfigVO(String id, String tag, String alarmName, Byte alarmType, Byte alarmLevel, Byte model, Double num, Byte isenable, String comId, String unitId, Byte unitType, String orgId, String orgName, String unitName) {
        this.id = id;
        this.tag = tag;
        this.alarmName = alarmName;
        this.alarmType = alarmType;
        this.alarmLevel = alarmLevel;
        this.model = model;
        this.num = num;
        this.isenable = isenable;
        this.comId = comId;
        this.unitId = unitId;
        this.unitType = unitType;
        this.orgId = orgId;
        this.orgName = orgName;
        this.unitName = unitName;
    }

    public AlarmConfigVO() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName == null ? null : alarmName.trim();
    }

    public Byte getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Byte alarmType) {
        this.alarmType = alarmType;
    }

    public Byte getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Byte alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Byte getModel() {
        return model;
    }

    public void setModel(Byte model) {
        this.model = model;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public Byte getIsenable() {
        return isenable;
    }

    public void setIsenable(Byte isenable) {
        this.isenable = isenable;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
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

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Byte getUnitType() {
        return unitType;
    }

    public void setUnitType(Byte unitType) {
        this.unitType = unitType;
    }

}