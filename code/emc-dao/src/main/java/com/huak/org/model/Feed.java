package com.huak.org.model;

import java.io.Serializable;

public class Feed implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String feedName;

    private String feedCode;

    private Byte feedType;

    private Byte heatType;

    private Double installCapacity;

    private Double heatCapacity;

    private Integer boilerNum;

    private Integer steamturbineNum;

    private String provinceId;

    private String cityId;

    private String countyId;

    private String townId;

    private String villageId;

    private String addr;

    private Double lng;

    private Double lat;

    private Double heatArea;

    private Long orgId;

    private String netId;



    private String comId;

    private Byte status;

    public Feed(String id,String feedName, String feedCode, Byte feedType, Byte heatType, Double installCapacity, Double heatCapacity, Integer boilerNum, Integer steamturbineNum, String provinceId, String cityId, String countyId, String townId, String villageId, String addr, Double lng, Double lat, Double heatArea,
                Long orgId,String netId,String comId,Byte status) {
        this.id = id;
        this.feedName = feedName;
        this.feedCode = feedCode;
        this.feedType = feedType;
        this.heatType = heatType;
        this.installCapacity = installCapacity;
        this.heatCapacity = heatCapacity;
        this.boilerNum = boilerNum;
        this.steamturbineNum = steamturbineNum;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.countyId = countyId;
        this.townId = townId;
        this.villageId = villageId;
        this.addr = addr;
        this.lng = lng;
        this.lat = lat;
        this.orgId = orgId;
        this.netId = netId;
        this.heatArea = heatArea;
        this.comId = comId;
        this.status = status;
    }

    public Feed() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id== null ? null : id.trim();
    }

    public Byte getFeedType() {
        return feedType;
    }

    public void setFeedType(Byte feedType) {
        this.feedType = feedType;
    }

    public Byte getHeatType() {
        return heatType;
    }

    public void setHeatType(Byte heatType) {
        this.heatType = heatType;
    }

    public Double getInstallCapacity() {
        return installCapacity;
    }

    public void setInstallCapacity(Double installCapacity) {
        this.installCapacity = installCapacity;
    }

    public Double getHeatCapacity() {
        return heatCapacity;
    }

    public void setHeatCapacity(Double heatCapacity) {
        this.heatCapacity = heatCapacity;
    }

    public Integer getBoilerNum() {
        return boilerNum;
    }

    public void setBoilerNum(Integer boilerNum) {
        this.boilerNum = boilerNum;
    }

    public Integer getSteamturbineNum() {
        return steamturbineNum;
    }

    public void setSteamturbineNum(Integer steamturbineNum) {
        this.steamturbineNum = steamturbineNum;
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

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName == null ? null : feedName.trim();
    }

    public String getFeedCode() {
        return feedCode;
    }

    public void setFeedCode(String feedCode) {
        this.feedCode = feedCode == null ? null : feedCode.trim();
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
        this.netId = netId== null ? null : netId.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId== null ? null : comId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}