package com.huak.health.vo;

import java.io.Serializable;

public class IndexTempA implements Serializable{

    private static final long serialVersionUID = -4118207783644086499L;
    private String id;
    private String stationName;
    private String temp;
    private String roomCode;
    private String communityName;
    private String redeTime;
    private Double maxTemp;
    private Double minTemp;

    public IndexTempA(String id,String stationName, String temp, String roomCode, String communityName, String redeTime, Double maxTemp, Double minTemp) {
        this.id=id;
        this.stationName = stationName;
        this.temp = temp;
        this.roomCode = roomCode;
        this.communityName = communityName;
        this.redeTime = redeTime;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getRedeTime() {
        return redeTime;
    }

    public void setRedeTime(String redeTime) {
        this.redeTime = redeTime;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }
}