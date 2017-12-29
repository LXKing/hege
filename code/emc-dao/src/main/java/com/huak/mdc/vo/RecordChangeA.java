package com.huak.mdc.vo;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc.model<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description: 换表记录    <BR>
 * Function List:  <BR>
 */
public class RecordChangeA implements Serializable {

    private static final long serialVersionUID = -82291055349015549L;
    private String id;

    private String collectId;

    private String changeTime;

    private Double usedNum;

    private Double newNum;

    private Double usedCoef;

    private Double newCoef;

    private String createTime;

    private String crestor;

    private String name;

    private String unitName;
    public RecordChangeA(String id, String collectId, String changeTime, Double usedNum, Double newNum, Double usedCoef, Double newCoef, String createTime, String crestor,String name,String unitName) {
        this.id = id;
        this.collectId = collectId;
        this.changeTime = changeTime;
        this.usedNum = usedNum;
        this.newNum = newNum;
        this.usedCoef = usedCoef;
        this.newCoef = newCoef;
        this.createTime = createTime;
        this.crestor = crestor;
        this.name=name;
        this.unitName=unitName;
    }

    public RecordChangeA() {
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

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public Double getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Double usedNum) {
        this.usedNum = usedNum;
    }

    public Double getNewNum() {
        return newNum;
    }

    public void setNewNum(Double newNum) {
        this.newNum = newNum;
    }

    public Double getUsedCoef() {
        return usedCoef;
    }

    public void setUsedCoef(Double usedCoef) {
        this.usedCoef = usedCoef;
    }

    public Double getNewCoef() {
        return newCoef;
    }

    public void setNewCoef(Double newCoef) {
        this.newCoef = newCoef;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}