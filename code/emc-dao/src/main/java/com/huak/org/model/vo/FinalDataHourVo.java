package com.huak.org.model.vo;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org.model.vo<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/26<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

public class FinalDataHourVo {

    public String id;
    public String eTtotal;//能源总量
    public String coefTotal;//总碳排放
    public String costTotal;//总成本
    public String yardage;//单耗
    public String coventYardage;//折算热单耗
    public String priceArea;//单位面积的价格

    public String feedTotalEnergy;//热源总能耗
    public String feedTotalCoef;//热源总碳排放
    public String feedTotalCost;//热源总成本

    public String netTotalLong;//管网总长度
    public String netTotalEnergy;//管网能耗
    public String netTotalCost;//管网成本

    public String stationTotalEnergy;//换热站总能耗
    public String stationTotalCost;//换热站总成本
    public String stationTotalCoef;//换热站总碳排放

    public String lineTotalLong;//管线总长度
    public String lineTotalEnergy;//管线能耗
    public String lineTotalCost;//管线成本

    public String roomTotalLong;//管线总长度
    public String roomTotalEnergy;//管线能耗
    public String roomTotalCost;//管线成本

    public FinalDataHourVo() {
    }

    public FinalDataHourVo(String id, String eTtotal, String coefTotal, String costTotal, String yardage, String coventYardage, String priceArea, String feedTotalEnergy, String feedTotalCoef, String feedTotalCost, String netTotalLong, String netTotalEnergy, String netTotalCost, String stationTotalEnergy, String stationTotalCost, String stationTotalCoef, String lineTotalLong, String lineTotalEnergy, String lineTotalCost, String roomTotalLong, String roomTotalEnergy, String roomTotalCost) {
        this.id = id;
        this.eTtotal = eTtotal;
        this.coefTotal = coefTotal;
        this.costTotal = costTotal;
        this.yardage = yardage;
        this.coventYardage = coventYardage;
        this.priceArea = priceArea;
        this.feedTotalEnergy = feedTotalEnergy;
        this.feedTotalCoef = feedTotalCoef;
        this.feedTotalCost = feedTotalCost;
        this.netTotalLong = netTotalLong;
        this.netTotalEnergy = netTotalEnergy;
        this.netTotalCost = netTotalCost;
        this.stationTotalEnergy = stationTotalEnergy;
        this.stationTotalCost = stationTotalCost;
        this.stationTotalCoef = stationTotalCoef;
        this.lineTotalLong = lineTotalLong;
        this.lineTotalEnergy = lineTotalEnergy;
        this.lineTotalCost = lineTotalCost;
        this.roomTotalLong = roomTotalLong;
        this.roomTotalEnergy = roomTotalEnergy;
        this.roomTotalCost = roomTotalCost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String geteTtotal() {
        return eTtotal;
    }

    public void seteTtotal(String eTtotal) {
        this.eTtotal = eTtotal;
    }

    public String getCoefTotal() {
        return coefTotal;
    }

    public void setCoefTotal(String coefTotal) {
        this.coefTotal = coefTotal;
    }

    public String getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(String costTotal) {
        this.costTotal = costTotal;
    }

    public String getYardage() {
        return yardage;
    }

    public void setYardage(String yardage) {
        this.yardage = yardage;
    }

    public String getCoventYardage() {
        return coventYardage;
    }

    public void setCoventYardage(String coventYardage) {
        this.coventYardage = coventYardage;
    }

    public String getPriceArea() {
        return priceArea;
    }

    public void setPriceArea(String priceArea) {
        this.priceArea = priceArea;
    }

    public String getFeedTotalEnergy() {
        return feedTotalEnergy;
    }

    public void setFeedTotalEnergy(String feedTotalEnergy) {
        this.feedTotalEnergy = feedTotalEnergy;
    }

    public String getFeedTotalCoef() {
        return feedTotalCoef;
    }

    public void setFeedTotalCoef(String feedTotalCoef) {
        this.feedTotalCoef = feedTotalCoef;
    }

    public String getFeedTotalCost() {
        return feedTotalCost;
    }

    public void setFeedTotalCost(String feedTotalCost) {
        this.feedTotalCost = feedTotalCost;
    }

    public String getNetTotalLong() {
        return netTotalLong;
    }

    public void setNetTotalLong(String netTotalLong) {
        this.netTotalLong = netTotalLong;
    }

    public String getNetTotalEnergy() {
        return netTotalEnergy;
    }

    public void setNetTotalEnergy(String netTotalEnergy) {
        this.netTotalEnergy = netTotalEnergy;
    }

    public String getNetTotalCost() {
        return netTotalCost;
    }

    public void setNetTotalCost(String netTotalCost) {
        this.netTotalCost = netTotalCost;
    }

    public String getStationTotalEnergy() {
        return stationTotalEnergy;
    }

    public void setStationTotalEnergy(String stationTotalEnergy) {
        this.stationTotalEnergy = stationTotalEnergy;
    }

    public String getStationTotalCost() {
        return stationTotalCost;
    }

    public void setStationTotalCost(String stationTotalCost) {
        this.stationTotalCost = stationTotalCost;
    }

    public String getStationTotalCoef() {
        return stationTotalCoef;
    }

    public void setStationTotalCoef(String stationTotalCoef) {
        this.stationTotalCoef = stationTotalCoef;
    }

    public String getLineTotalLong() {
        return lineTotalLong;
    }

    public void setLineTotalLong(String lineTotalLong) {
        this.lineTotalLong = lineTotalLong;
    }

    public String getLineTotalEnergy() {
        return lineTotalEnergy;
    }

    public void setLineTotalEnergy(String lineTotalEnergy) {
        this.lineTotalEnergy = lineTotalEnergy;
    }

    public String getLineTotalCost() {
        return lineTotalCost;
    }

    public void setLineTotalCost(String lineTotalCost) {
        this.lineTotalCost = lineTotalCost;
    }

    public String getRoomTotalLong() {
        return roomTotalLong;
    }

    public void setRoomTotalLong(String roomTotalLong) {
        this.roomTotalLong = roomTotalLong;
    }

    public String getRoomTotalEnergy() {
        return roomTotalEnergy;
    }

    public void setRoomTotalEnergy(String roomTotalEnergy) {
        this.roomTotalEnergy = roomTotalEnergy;
    }

    public String getRoomTotalCost() {
        return roomTotalCost;
    }

    public void setRoomTotalCost(String roomTotalCost) {
        this.roomTotalCost = roomTotalCost;
    }
}

