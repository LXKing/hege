package com.huak.mdc.vo;

import java.io.Serializable;

public class PrestoreA implements Serializable{

    private static final long serialVersionUID = 7055745417281150056L;
    private String id;

    private String collectId;

    private String prestoreTime;

    private Double usedNum;

    private Double prestoreNum;

    private String createTime;

    private String crestor;

    private String name;

    private String unitName;
    public PrestoreA(String id, String collectId, String prestoreTime, Double usedNum, Double prestoreNum, String createTime, String crestor,String unitName,String name) {
        this.id = id;
        this.collectId = collectId;
        this.prestoreTime = prestoreTime;
        this.usedNum = usedNum;
        this.prestoreNum = prestoreNum;
        this.createTime = createTime;
        this.crestor = crestor;
        this.unitName=unitName;
        this.name=name;
    }

    public PrestoreA() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId == null ? null : collectId.trim();
    }

    public String getPrestoreTime() {
        return prestoreTime;
    }

    public void setPrestoreTime(String prestoreTime) {
        this.prestoreTime = prestoreTime;
    }

    public Double getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Double usedNum) {
        this.usedNum = usedNum;
    }

    public Double getPrestoreNum() {
        return prestoreNum;
    }

    public void setPrestoreNum(Double prestoreNum) {
        this.prestoreNum = prestoreNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCrestor() {
        return crestor;
    }

    public void setCrestor(String crestor) {
        this.crestor = crestor == null ? null : crestor.trim();
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
}