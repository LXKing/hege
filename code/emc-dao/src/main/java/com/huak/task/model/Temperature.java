package com.huak.task.model;

import java.io.Serializable;
import java.util.Date;

public class Temperature implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;

    private String comId;

    private String stationName;

    private String sensor;

    private String communityName;

    private String rommCode;

    private Double temp;

    private String sunny;

    private String tell;

    private String roomName;

    private Date redeTime;

    public Temperature(String id, String comId, String stationName, String sensor, String communityName, String rommCode, Double temp, String sunny, String tell, String roomName, Date redeTime) {
        this.id = id;
        this.comId = comId;
        this.stationName = stationName;
        this.sensor = sensor;
        this.communityName = communityName;
        this.rommCode = rommCode;
        this.temp = temp;
        this.sunny = sunny;
        this.tell = tell;
        this.roomName = roomName;
        this.redeTime = redeTime;
    }

    public Temperature() {
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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor == null ? null : sensor.trim();
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
    }

    public String getRommCode() {
        return rommCode;
    }

    public void setRommCode(String rommCode) {
        this.rommCode = rommCode == null ? null : rommCode.trim();
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getSunny() {
        return sunny;
    }

    public void setSunny(String sunny) {
        this.sunny = sunny == null ? null : sunny.trim();
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell == null ? null : tell.trim();
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public Date getRedeTime() {
        return redeTime;
    }

    public void setRedeTime(Date redeTime) {
        this.redeTime = redeTime;
    }
}