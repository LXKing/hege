package com.huak.health.vo;

import java.io.Serializable;

public class IndexDataA implements Serializable{

    private static final long serialVersionUID = -8831913076052056375L;
    private String unitId;
    private String unitName;
    private String name;
    private String Type;
    private String dh;
    private Double enterprise;
    private Double local;
    private Double industry;
    private String unitMeter;

    public IndexDataA(String unitId, String unitName, String name, String type, String dh, Double enterprise, Double local, Double industry,String unitMeter) {
        this.unitId = unitId;
        this.unitName = unitName;
        this.name = name;
        Type = type;
        this.dh = dh;
        this.enterprise = enterprise;
        this.local = local;
        this.industry = industry;
        this.unitMeter=unitMeter;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public Double getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Double enterprise) {
        this.enterprise = enterprise;
    }

    public Double getLocal() {
        return local;
    }

    public void setLocal(Double local) {
        this.local = local;
    }

    public Double getIndustry() {
        return industry;
    }

    public void setIndustry(Double industry) {
        this.industry = industry;
    }
    public String getUnitMeter() {
        return unitMeter;
    }

    public void setUnitMeter(String unitMeter) {
        this.unitMeter = unitMeter;
    }
}