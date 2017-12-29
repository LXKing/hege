package com.huak.health.model;

import java.io.Serializable;

public class AlarmRecord  implements Serializable {
    private static final long serialVersionUID = 2772043628641901405L;
    private String id;

    private String configId;

    private Double alarmNum;

    private String startTime;

    private String endTime;

    public AlarmRecord(String id, String configId, Double alarmNum, String startTime, String endTime) {
        this.id = id;
        this.configId = configId;
        this.alarmNum = alarmNum;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public AlarmRecord() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }

    public Double getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(Double alarmNum) {
        this.alarmNum = alarmNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}