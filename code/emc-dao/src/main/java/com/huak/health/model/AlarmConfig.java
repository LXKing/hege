package com.huak.health.model;

import java.io.Serializable;

public class AlarmConfig  implements Serializable {
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

    public AlarmConfig(String id, String tag, String alarmName, Byte alarmType, Byte alarmLevel, Byte model, Double num, Byte isenable, String comId, String unitId, Byte unitType) {
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
    }

    public AlarmConfig() {
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

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Byte getUnitType() {
        return unitType;
    }

    public void setUnitType(Byte unitType) {
        this.unitType = unitType;
    }
}