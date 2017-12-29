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
 * Description: 换表记录    <BR>
 * Function List:  <BR>
 */
public class RecordChange  implements Serializable {
    private static final long serialVersionUID = 4986050626711972242L;
    private String id;

    private String collectId;

    private String changeTime;

    private Double usedNum;

    private Double newNum;

    private Double usedCoef;

    private Double newCoef;

    private String createTime;

    private String crestor;

    public RecordChange(String id, String collectId, String changeTime, Double usedNum, Double newNum, Double usedCoef, Double newCoef, String createTime, String crestor) {
        this.id = id;
        this.collectId = collectId;
        this.changeTime = changeTime;
        this.usedNum = usedNum;
        this.newNum = newNum;
        this.usedCoef = usedCoef;
        this.newCoef = newCoef;
        this.createTime = createTime;
        this.crestor = crestor;
    }

    public RecordChange() {
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

    public String getChangeTime()throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        Date date = format.parse(changeTime);
        changeTime = format.format(date);
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

    public String getCreateTime() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(format.parse(createTime));
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
}