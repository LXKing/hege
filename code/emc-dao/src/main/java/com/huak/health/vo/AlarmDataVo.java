package com.huak.health.vo;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health.vo<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/26<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class AlarmDataVo implements Serializable{

    private static final long serialVersionUID = -1837499202727609005L;
    private String id;

    private String tag;

    private String collectTime;

    private Double collectValue;

    private Byte alarmType;

    private Byte alarmLevel;

    private Byte model;

    private Double num;

    public AlarmDataVo(String id, String tag, String collectTime, Double collectValue, Byte alarmType, Byte alarmLevel, Byte model, Double num) {
        this.id = id;
        this.tag = tag;
        this.collectTime = collectTime;
        this.collectValue = collectValue;
        this.alarmType = alarmType;
        this.alarmLevel = alarmLevel;
        this.model = model;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public Double getCollectValue() {
        return collectValue;
    }

    public void setCollectValue(Double collectValue) {
        this.collectValue = collectValue;
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
}
