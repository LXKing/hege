package com.huak.weather.model;

import java.io.Serializable;

/**
 * Created by MR-BIN on 2017/7/13.
 */
public class WeatherAQI implements Serializable {

    private static final long serialVersionUID = 8587469044931816275L;
    private String code;

    private String reportDate;

    private String aqi;

    private String aqiLevel;

    private String aqiScope;

    private String aqiRemark;

    private Byte status;

    public WeatherAQI(String code, String reportDate, String aqi, String aqiLevel, String aqiScope, String aqiRemark,Byte status) {
        this.code = code;
        this.reportDate = reportDate;
        this.aqi = aqi;
        this.aqiLevel = aqiLevel;
        this.aqiScope = aqiScope;
        this.aqiRemark = aqiRemark;
        this.status = status;
    }
    public WeatherAQI() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getAqiLevel() {
        return aqiLevel;
    }

    public void setAqiLevel(String aqiLevel) {
        this.aqiLevel = aqiLevel;
    }

    public String getAqiScope() {
        return aqiScope;
    }

    public void setAqiScope(String aqiScope) {
        this.aqiScope = aqiScope;
    }

    public String getAqiRemark() {
        return aqiRemark;
    }

    public void setAqiRemark(String aqiRemark) {
        this.aqiRemark = aqiRemark;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
