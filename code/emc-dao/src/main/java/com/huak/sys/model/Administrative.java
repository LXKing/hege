package com.huak.sys.model;

import java.io.Serializable;

public class Administrative implements Serializable{
    private static final long serialVersionUID = 1L;
    private String admCode;

    private String admName;

    private String pCode;

    private Byte admLevel;

    private String admType;

    private Double lng;

    private Double lat;

    public Administrative(String admCode, String admName, String pCode, Byte admLevel, String admType, Double lng, Double lat) {
        this.admCode = admCode;
        this.admName = admName;
        this.pCode = pCode;
        this.admLevel = admLevel;
        this.admType = admType;
        this.lng = lng;
        this.lat = lat;
    }

    public Administrative() {
        super();
    }

    public String getAdmCode() {
        return admCode;
    }

    public void setAdmCode(String admCode) {
        this.admCode = admCode == null ? null : admCode.trim();
    }

    public String getAdmName() {
        return admName;
    }

    public void setAdmName(String admName) {
        this.admName = admName == null ? null : admName.trim();
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode == null ? null : pCode.trim();
    }

    public Byte getAdmLevel() {
        return admLevel;
    }

    public void setAdmLevel(Byte admLevel) {
        this.admLevel = admLevel;
    }

    public String getAdmType() {
        return admType;
    }

    public void setAdmType(String admType) {
        this.admType = admType == null ? null : admType.trim();
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
}