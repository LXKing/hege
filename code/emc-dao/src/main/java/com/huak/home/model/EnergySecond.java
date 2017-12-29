package com.huak.home.model;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.energy.model<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/26<BR>
 * Description:   能耗分析-分公司能耗明细和能源流明细公用  <BR>
 * Function List:  <BR>
 */
public class EnergySecond implements Serializable {

    private static final long serialVersionUID = -4625330717270532174L;
    /* 主键 */
    private String id;
    /* 主键 */
    private String orgName;
    /* 能耗总量（万Tce） */
    private Double totalBq;
    private Double totalTq;
    private Double totalAn;
    /* 水能总量（T） */
    private Double waterBq;
    private Double waterTq;
    private Double waterAn;
    /* 电耗总量(kW·h) */
    private Double electricBq;
    private Double electricTq;
    private Double electricAn;
    /* 气能耗总量（M²） */
    private Double gasBq;
    private Double gasTq;
    private Double gasAn;
    /* 热能耗总量（GJ） */
    private Double heatBq;
    private Double heatTq;
    private Double heatAn;
    /* 煤能耗总量（GJ） */
    private Double coalBq;
    private Double coalTq;
    private Double coalAn;
    /* 油能耗总量（GJ） */
    private Double oilBq;
    private Double oilTq;
    private Double oilAn;
    private int sort;

    DecimalFormat format = new DecimalFormat("#.0000");

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Double getTotalBq() {
        return totalBq;
    }

    public void setTotalBq(Double totalBq) {
        this.totalBq = totalBq;
    }

    public Double getTotalTq() {
        return totalTq;
    }

    public void setTotalTq(Double totalTq) {
        this.totalTq = totalTq;
    }

    public Double getTotalAn() {
        if(this.totalTq == 0){
            return 0d;
        }
        totalAn = Double.valueOf(format.format((this.totalBq-this.totalTq)/this.totalTq));
        return totalAn*100;
    }

    public void setTotalAn(Double totalAn) {
        this.totalAn = totalAn;
    }

    public Double getWaterBq() {
        return waterBq;
    }

    public void setWaterBq(Double waterBq) {
        this.waterBq = waterBq;
    }

    public Double getWaterTq() {
        return waterTq;
    }

    public void setWaterTq(Double waterTq) {
        this.waterTq = waterTq;
    }

    public Double getWaterAn() {
        if(this.waterTq == 0){
            return 0d;
        }
        waterAn = Double.valueOf(format.format((this.waterBq-this.waterTq)/this.waterTq));
        return waterAn*100;
    }

    public void setWaterAn(Double waterAn) {
        this.waterAn = waterAn;
    }

    public Double getElectricBq() {
        return electricBq;
    }

    public void setElectricBq(Double electricBq) {
        this.electricBq = electricBq;
    }

    public Double getElectricTq() {
        return electricTq;
    }

    public void setElectricTq(Double electricTq) {
        this.electricTq = electricTq;
    }

    public Double getElectricAn() {
        if(this.electricTq == 0){
            return 0d;
        }
        electricAn = Double.valueOf(format.format((this.electricBq-this.electricTq)/this.electricTq));
        return electricAn*100;
    }

    public void setElectricAn(Double electricAn) {
        this.electricAn = electricAn;
    }

    public Double getGasBq() {
        return gasBq;
    }

    public void setGasBq(Double gasBq) {
        this.gasBq = gasBq;
    }

    public Double getGasTq() {
        return gasTq;
    }

    public void setGasTq(Double gasTq) {
        this.gasTq = gasTq;
    }

    public Double getGasAn() {
        if(this.gasTq == 0){
            return 0d;
        }
        gasAn = Double.valueOf(format.format((this.gasBq-this.gasTq)/this.gasTq));
        return gasAn*100;
    }

    public void setGasAn(Double gasAn) {
        this.gasAn = gasAn;
    }

    public Double getHeatBq() {
        return heatBq;
    }

    public void setHeatBq(Double heatBq) {
        this.heatBq = heatBq;
    }

    public Double getHeatTq() {
        return heatTq;
    }

    public void setHeatTq(Double heatTq) {
        this.heatTq = heatTq;
    }

    public Double getHeatAn() {
        if(this.heatTq == 0){
            return 0d;
        }
        heatAn = Double.valueOf(format.format((this.heatBq-this.heatTq)/this.heatTq));
        return heatAn*100;
    }

    public void setHeatAn(Double heatAn) {
        this.heatAn = heatAn;
    }

    public Double getCoalBq() {
        return coalBq;
    }

    public void setCoalBq(Double coalBq) {
        this.coalBq = coalBq;
    }

    public Double getCoalTq() {
        return coalTq;
    }

    public void setCoalTq(Double coalTq) {
        this.coalTq = coalTq;
    }

    public Double getCoalAn() {
        if(this.coalTq == 0){
            return 0d;
        }
        coalAn = Double.valueOf(format.format((this.coalBq-this.coalTq)/this.coalTq));
        return coalAn*100;
    }

    public void setCoalAn(Double coalAn) {
        this.coalAn = coalAn;
    }

    public Double getOilBq() {
        return oilBq;
    }

    public void setOilBq(Double oilBq) {
        this.oilBq = oilBq;
    }

    public Double getOilTq() {
        return oilTq;
    }

    public void setOilTq(Double oilTq) {
        this.oilTq = oilTq;
    }

    public Double getOilAn() {
        if(this.oilTq == 0){
            return 0d;
        }
        oilAn = Double.valueOf(format.format((this.oilBq-this.oilTq)/this.oilTq));
        return oilAn*100;
    }

    public void setOilAn(Double oilAn) {
        this.oilAn = oilAn;
    }

    public DecimalFormat getFormat() {
        return format;
    }

    public void setFormat(DecimalFormat format) {
        this.format = format;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
