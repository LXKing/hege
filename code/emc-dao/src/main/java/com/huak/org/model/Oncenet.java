package com.huak.org.model;

import java.io.Serializable;

public class Oncenet implements Serializable{
    private static final long serialVersionUID = -7307712060431464020L;
    private String id;

    private String netName;

    private String netCode;

    private Byte netTypeId;

    private Double length;

    private Integer cellNum;

    private Integer partNum;

    private String medium;

    private Long orgId;

    private String comId;

    private Byte heatType;

    public Oncenet() {
        super();
    }

    public Oncenet(String id, String netName, String netCode, Byte netTypeId, Double length, Integer cellNum, Integer partNum, String medium, Long orgId, String comId, Byte heatType) {
        this.id = id;
        this.netName = netName;
        this.netCode = netCode;
        this.netTypeId = netTypeId;
        this.length = length;
        this.cellNum = cellNum;
        this.partNum = partNum;
        this.medium = medium;
        this.orgId = orgId;
        this.comId = comId;
        this.heatType = heatType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public String getNetCode() {
        return netCode;
    }

    public void setNetCode(String netCode) {
        this.netCode = netCode;
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
}