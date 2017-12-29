package com.huak.org.model.vo;

import java.io.Serializable;

public class Secondnet implements Serializable{
    private static final long serialVersionUID = 3906152256285135245L;
    private String id;

    private String lineName;

    private String lineCode;

    private Byte netTypeId;

    private Double length;

    private Integer cellNum;

    private Integer partNum;

    private String medium;

    private Long orgId;

    private String comId;

    private Byte heatType;

    private String feedId;

    private String stationId;

    private String stationName;

    private String feedName;

    public Secondnet() {
        super();
    }

    public Secondnet(String id, String lineName, String lineCode, Byte netTypeId, Double length, Integer cellNum, Integer partNum, String medium, Long orgId, String comId, Byte heatType, String feedId, String stationId, String stationName, String feedName) {
        this.id = id;
        this.lineName = lineName;
        this.lineCode = lineCode;
        this.netTypeId = netTypeId;
        this.length = length;
        this.cellNum = cellNum;
        this.partNum = partNum;
        this.medium = medium;
        this.orgId = orgId;
        this.comId = comId;
        this.heatType = heatType;
        this.feedId = feedId;
        this.stationId = stationId;
        this.stationName = stationName;
        this.feedName = feedName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public Byte getNetTypeId() {
        return netTypeId;
    }

    public void setNetTypeId(Byte netTypeId) {
        this.netTypeId = netTypeId;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getCellNum() {
        return cellNum;
    }

    public void setCellNum(Integer cellNum) {
        this.cellNum = cellNum;
    }

    public Integer getPartNum() {
        return partNum;
    }

    public void setPartNum(Integer partNum) {
        this.partNum = partNum;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public Byte getHeatType() {
        return heatType;
    }

    public void setHeatType(Byte heatType) {
        this.heatType = heatType;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }
}