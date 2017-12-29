package com.huak.task.model;

import java.io.Serializable;

public class EnergyAnalySisdata implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;

    private String unitid;

    private String area;

    private String hotplan;

    private String hotfinishplan;

    private String hotsumanalysis;

    private String hotoneanalysis;

    private String powerplan;

    private String powerfinishplan;

    private String powersumanalysis;

    private String poweroneanalysis;

    private String waterplan;

    private String waterfinishplan;

    private String watersumanalysis;

    private String wateroneanalysis;

    private String comprehensiveconsumption;

    private String avgteairmperature;

    private String avgroomtemperature;

    private String type;

    private String beforheat;

    private String scadatime;

    private String zshotoneanalysis;

    private String zspoweroneanalysis;

    private String zswateroneanalysis;

    private String heatconsumption;

    private String comId;
    public EnergyAnalySisdata(String id, String unitid, String area, String hotplan, String hotfinishplan, String hotsumanalysis, String hotoneanalysis, String powerplan, String powerfinishplan, String powersumanalysis, String poweroneanalysis, String waterplan, String waterfinishplan, String watersumanalysis, String wateroneanalysis, String comprehensiveconsumption, String avgteairmperature, String avgroomtemperature, String type, String beforheat, String scadatime, String zshotoneanalysis, String zspoweroneanalysis, String zswateroneanalysis, String heatconsumption, String comId) {
        this.id = id;
        this.unitid = unitid;
        this.area = area;
        this.hotplan = hotplan;
        this.hotfinishplan = hotfinishplan;
        this.hotsumanalysis = hotsumanalysis;
        this.hotoneanalysis = hotoneanalysis;
        this.powerplan = powerplan;
        this.powerfinishplan = powerfinishplan;
        this.powersumanalysis = powersumanalysis;
        this.poweroneanalysis = poweroneanalysis;
        this.waterplan = waterplan;
        this.waterfinishplan = waterfinishplan;
        this.watersumanalysis = watersumanalysis;
        this.wateroneanalysis = wateroneanalysis;
        this.comprehensiveconsumption = comprehensiveconsumption;
        this.avgteairmperature = avgteairmperature;
        this.avgroomtemperature = avgroomtemperature;
        this.type = type;
        this.beforheat = beforheat;
        this.scadatime = scadatime;
        this.zshotoneanalysis = zshotoneanalysis;
        this.zspoweroneanalysis = zspoweroneanalysis;
        this.zswateroneanalysis = zswateroneanalysis;
        this.heatconsumption = heatconsumption;
        this.comId=comId;
    }

    public EnergyAnalySisdata() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getHotplan() {
        return hotplan;
    }

    public void setHotplan(String hotplan) {
        this.hotplan = hotplan == null ? null : hotplan.trim();
    }

    public String getHotfinishplan() {
        return hotfinishplan;
    }

    public void setHotfinishplan(String hotfinishplan) {
        this.hotfinishplan = hotfinishplan == null ? null : hotfinishplan.trim();
    }

    public String getHotsumanalysis() {
        return hotsumanalysis;
    }

    public void setHotsumanalysis(String hotsumanalysis) {
        this.hotsumanalysis = hotsumanalysis == null ? null : hotsumanalysis.trim();
    }

    public String getHotoneanalysis() {
        return hotoneanalysis;
    }

    public void setHotoneanalysis(String hotoneanalysis) {
        this.hotoneanalysis = hotoneanalysis == null ? null : hotoneanalysis.trim();
    }

    public String getPowerplan() {
        return powerplan;
    }

    public void setPowerplan(String powerplan) {
        this.powerplan = powerplan == null ? null : powerplan.trim();
    }

    public String getPowerfinishplan() {
        return powerfinishplan;
    }

    public void setPowerfinishplan(String powerfinishplan) {
        this.powerfinishplan = powerfinishplan == null ? null : powerfinishplan.trim();
    }

    public String getPowersumanalysis() {
        return powersumanalysis;
    }

    public void setPowersumanalysis(String powersumanalysis) {
        this.powersumanalysis = powersumanalysis == null ? null : powersumanalysis.trim();
    }

    public String getPoweroneanalysis() {
        return poweroneanalysis;
    }

    public void setPoweroneanalysis(String poweroneanalysis) {
        this.poweroneanalysis = poweroneanalysis == null ? null : poweroneanalysis.trim();
    }

    public String getWaterplan() {
        return waterplan;
    }

    public void setWaterplan(String waterplan) {
        this.waterplan = waterplan == null ? null : waterplan.trim();
    }

    public String getWaterfinishplan() {
        return waterfinishplan;
    }

    public void setWaterfinishplan(String waterfinishplan) {
        this.waterfinishplan = waterfinishplan == null ? null : waterfinishplan.trim();
    }

    public String getWatersumanalysis() {
        return watersumanalysis;
    }

    public void setWatersumanalysis(String watersumanalysis) {
        this.watersumanalysis = watersumanalysis == null ? null : watersumanalysis.trim();
    }

    public String getWateroneanalysis() {
        return wateroneanalysis;
    }

    public void setWateroneanalysis(String wateroneanalysis) {
        this.wateroneanalysis = wateroneanalysis == null ? null : wateroneanalysis.trim();
    }

    public String getComprehensiveconsumption() {
        return comprehensiveconsumption;
    }

    public void setComprehensiveconsumption(String comprehensiveconsumption) {
        this.comprehensiveconsumption = comprehensiveconsumption == null ? null : comprehensiveconsumption.trim();
    }

    public String getAvgteairmperature() {
        return avgteairmperature;
    }

    public void setAvgteairmperature(String avgteairmperature) {
        this.avgteairmperature = avgteairmperature == null ? null : avgteairmperature.trim();
    }

    public String getAvgroomtemperature() {
        return avgroomtemperature;
    }

    public void setAvgroomtemperature(String avgroomtemperature) {
        this.avgroomtemperature = avgroomtemperature == null ? null : avgroomtemperature.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBeforheat() {
        return beforheat;
    }

    public void setBeforheat(String beforheat) {
        this.beforheat = beforheat == null ? null : beforheat.trim();
    }

    public String getScadatime() {
        return scadatime;
    }

    public void setScadatime(String scadatime) {
        this.scadatime = scadatime == null ? null : scadatime.trim();
    }

    public String getZshotoneanalysis() {
        return zshotoneanalysis;
    }

    public void setZshotoneanalysis(String zshotoneanalysis) {
        this.zshotoneanalysis = zshotoneanalysis == null ? null : zshotoneanalysis.trim();
    }

    public String getZspoweroneanalysis() {
        return zspoweroneanalysis;
    }

    public void setZspoweroneanalysis(String zspoweroneanalysis) {
        this.zspoweroneanalysis = zspoweroneanalysis == null ? null : zspoweroneanalysis.trim();
    }

    public String getZswateroneanalysis() {
        return zswateroneanalysis;
    }

    public void setZswateroneanalysis(String zswateroneanalysis) {
        this.zswateroneanalysis = zswateroneanalysis == null ? null : zswateroneanalysis.trim();
    }

    public String getHeatconsumption() {
        return heatconsumption;
    }

    public void setHeatconsumption(String heatconsumption) {
        this.heatconsumption = heatconsumption == null ? null : heatconsumption.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }
}