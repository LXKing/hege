package com.huak.health.vo;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health.vo<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/10/17<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class WorkWarnVo implements Serializable{

    private static final long serialVersionUID = -6979761531192541018L;
    private String id;

    private String alarmName;

    private String alarmLevel;

    private String model;

    private String startTime;

    public WorkWarnVo(String id, String alarmName, String alarmLevel, String model, String startTime) {
        this.id = id;
        this.alarmName = alarmName;
        this.alarmLevel = alarmLevel;
        this.model = model;
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
