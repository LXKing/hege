package com.huak.health.model;

import java.io.Serializable;

public class AlarmConfigTemp implements Serializable{

    private static final long serialVersionUID = -6081418099294954842L;
    private String id;

    private String comId;

    private String unitId;

    private Double mintemp;

    private Double maxtemp;

    private Byte unitType;

    private Byte isenable;

    public AlarmConfigTemp(String id, String comId, String unitId, Double mintemp, Double maxtemp, Byte unitType, Byte isenable) {
        this.id = id;
        this.comId = comId;
        this.unitId = unitId;
        this.mintemp = mintemp;
        this.maxtemp = maxtemp;
        this.unitType = unitType;
        this.isenable = isenable;
    }

    public AlarmConfigTemp() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
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
}