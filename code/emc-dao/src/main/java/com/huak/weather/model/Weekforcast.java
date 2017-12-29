package com.huak.weather.model;

import java.io.Serializable;

public class Weekforcast implements Serializable{
    private static final long serialVersionUID = 2992253033864187342L;
    private Integer id;

    private String code;

    private String reportDate;

    private String week;

    private String wind;

    private String winp;

    private String tempHigh;

    private String tempLow;

    private String weather;

    private String weatherIcon;

    private Byte status;

    public Weekforcast(Integer id, String code, String reportDate, String week, String wind, String winp, String tempHigh, String tempLow, String weather, String weatherIcon, Byte status) {
        this.id = id;
        this.code = code;
        this.reportDate = reportDate;
        this.week = week;
        this.wind = wind;
        this.winp = winp;
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
        this.weather = weather;
        this.weatherIcon = weatherIcon;
        this.status = status;
    }

    public Weekforcast( String reportDate, String week, String wind, String winp, String tempHigh, String tempLow, String weather, String weatherIcon, Byte status) {
        this.reportDate = reportDate;
        this.week = week;
        this.wind = wind;
        this.winp = winp;
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
        this.weather = weather;
        this.weatherIcon = weatherIcon;
        this.status = status;
    }

    public Weekforcast() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? "" : code.trim();
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? "" : week.trim();
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind == null ? "" : wind.trim();
    }

    public String getWinp() {
        return winp;
    }

    public void setWinp(String winp) {
        this.winp = winp == null ? "" : winp.trim();
    }

    public String getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(String tempHigh) {
        this.tempHigh = tempHigh == null ? "" : tempHigh.trim();
    }

    public String getTempLow() {
        return tempLow;
    }

    public void setTempLow(String tempLow) {
        this.tempLow = tempLow == null ? "" : tempLow.trim();
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather == null ? "" : weather.trim();
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon == null ? "" : weatherIcon.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}