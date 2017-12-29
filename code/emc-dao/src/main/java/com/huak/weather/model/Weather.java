package com.huak.weather.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Weather implements Serializable {
    private static final long serialVersionUID = -6654572227218222940L;
    private Long id;

    private String code;

    private String reportDate;

    private String weekDay;

    private String temperatureCurr;

    private String cityName;

    private String humidity;

    private String wind;

    private String winp;

    private BigDecimal tempHigh;

    private BigDecimal tempLow;

    private String humiHigh;

    private String humiLow;

    private String weatherIcon;

    private Byte status;

    private String weatherCurrent;

    public Weather(Long id, String code, String reportDate, String weekDay, String temperatureCurr, String cityName, String humidity, String wind, String winp, BigDecimal tempHigh, BigDecimal tempLow, String humiHigh, String humiLow,Byte status, String weatherIcon,  String weatherCurrent) {
        this.id = id;
        this.code = code;
        this.reportDate = reportDate;
        this.weekDay = weekDay;
        this.temperatureCurr = temperatureCurr;
        this.cityName = cityName;
        this.humidity = humidity;
        this.wind = wind;
        this.winp = winp;
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
        this.humiHigh = humiHigh;
        this.humiLow = humiLow;
        this.status = status;
        this.weatherIcon = weatherIcon;
        this.weatherCurrent = weatherCurrent;

    }

    public Weather() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? "" : code.trim();
    }

    public String getreportDate() {
        return reportDate;
    }

    public void setreportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay == null ? "" : weekDay.trim();
    }

    public String getTemperatureCurr() {
        return temperatureCurr.replace("℃","");
    }

    public void setTemperatureCurr(String temperatureCurr) {
        this.temperatureCurr = temperatureCurr == null ? null : temperatureCurr.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? "" : cityName.trim();
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity == null ? "" : humidity.trim();
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

    public BigDecimal getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(BigDecimal tempHigh) {
        this.tempHigh = tempHigh;
    }

    public BigDecimal getTempLow() {
        return tempLow;
    }

    public void setTempLow(BigDecimal tempLow) {
        this.tempLow = tempLow;
    }

    public String getHumiHigh() {
        return humiHigh;
    }

    public void setHumiHigh(String humiHigh) {
        this.humiHigh = humiHigh == null ? "" : humiHigh.trim();
    }

    public String getHumiLow() {
        return humiLow;
    }

    public void setHumiLow(String humiLow) {
        this.humiLow = humiLow == null ? "" : humiLow.trim();
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon == null ? "" : weatherIcon.trim();
    }

    public String getWeatherCurrent() {
        return weatherCurrent;
    }

    public void setWeatherCurrent(String weatherCurrent) {
        this.weatherCurrent = weatherCurrent == null ? "" : weatherCurrent.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}