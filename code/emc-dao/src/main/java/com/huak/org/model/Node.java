package com.huak.org.model;

import java.io.Serializable;
import java.math.BigInteger;

public class Node implements Serializable{
    private static final long serialVersionUID = -3117644947907530600L;
    private String id;

    private Byte manageTypeId;

    private String provinceId;

    private String cityId;

    private String countyId;

    private String townId;

    private String villageId;

    private String addr;

    private Double lng;

    private Double lat;

    private String stationName;

    private String stationCode;

    private Double heatArea;

    private Long orgId;

    private String netId;

    private String feedId;


    private String comId;

    private Byte status;
    private Byte heatType;

    public Node(String id, Byte manageTypeId, String provinceId, String cityId, String countyId, String townId, String villageId, String addr, Double lng, Double lat, String stationName, String stationCode, Double heatArea, Long orgId, String netId, String feedId,  String comId, Byte status,Byte heatType) {
        this.id = id;
        this.manageTypeId = manageTypeId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.countyId = countyId;
        this.townId = townId;
        this.villageId = villageId;
        this.addr = addr;
        this.lng = lng;
        this.lat = lat;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.heatArea = heatArea;
        this.orgId = orgId;
        this.netId = netId;
        this.feedId = feedId;
        this.comId = comId;
        this.status = status;
        this.heatType = heatType;
    }

    public Node() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Byte getManageTypeId() {
        return manageTypeId;
    }

    public void setManageTypeId(Byte manageTypeId) {
        this.manageTypeId = manageTypeId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId == null ? null : countyId.trim();
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId == null ? null : townId.trim();
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId == null ? null : villageId.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode == null ? null : stationCode.trim();
    }

    public Double getHeatArea() {
        return heatArea;
    }

    public void setHeatArea(Double heatArea) {
        this.heatArea = heatArea;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId == null ? null : netId.trim();
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId == null ? null : feedId.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getHeatType() {
        return heatType;
    }

    public void setHeatType(Byte heatType) {
        this.heatType = heatType;
    }
}