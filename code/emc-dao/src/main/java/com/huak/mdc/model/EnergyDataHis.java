package com.huak.mdc.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc.model<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description: 能耗历史    <BR>
 * Function List:  <BR>
 */
public class EnergyDataHis implements Serializable {
    private static final long serialVersionUID = -753595351892039632L;

    private String id;

    private String collectId;

    private Double collectNum;

    private String collectTime;

    private Byte ischange;

    private Double changeNum;

    private Byte isprestore;

    private Double prestoreNum;

    public EnergyDataHis(String id, String collectId, Double collectNum, String collectTime, Byte ischange, Double changeNum, Byte isprestore, Double prestoreNum) {
        this.id = id;
        this.collectId = collectId;
        this.collectNum = collectNum;
        this.collectTime = collectTime;
        this.ischange = ischange;
        this.changeNum = changeNum;
        this.isprestore = isprestore;
        this.prestoreNum = prestoreNum;
    }

    public EnergyDataHis() {
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

    public Double getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Double collectNum) {
        this.collectNum = collectNum;
    }

    public String getCollectTime() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        Date date = format.parse(collectTime);
        collectTime = format.format(date);
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public Byte getIschange() {
        return ischange;
    }

    public void setIschange(Byte ischange) {
        this.ischange = ischange;
    }

    public Double getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(Double changeNum) {
        this.changeNum = changeNum;
    }

    public Byte getIsprestore() {
        return isprestore;
    }

    public void setIsprestore(Byte isprestore) {
        this.isprestore = isprestore;
    }

    public Double getPrestoreNum() {
        return prestoreNum;
    }

    public void setPrestoreNum(Double prestoreNum) {
        this.prestoreNum = prestoreNum;
    }

    @Override
    public String toString() {
        return "EnergyDataHis{" +
                "id='" + id + '\'' +
                ", collectId='" + collectId + '\'' +
                ", collectNum=" + collectNum +
                ", collectTime='" + collectTime + '\'' +
                ", ischange=" + ischange +
                ", changeNum=" + changeNum +
                ", isprestore=" + isprestore +
                ", prestoreNum=" + prestoreNum +
                '}';
    }
}