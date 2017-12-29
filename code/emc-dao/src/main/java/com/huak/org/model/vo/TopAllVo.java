package com.huak.org.model.vo;

import java.io.Serializable;

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

public class TopAllVo implements Serializable{

    private static final long serialVersionUID = -4900160118433240964L;
    public String id;
    public String energyTotal;//能源总量
    public String carbonTotal;//总碳排放
    public String costTotal;//总成本
    public String yardage;//单耗
    public String conventYardage;//折算热单耗
    public String priceArea;//单位面积的价格


    public TopAllVo() {
    }

    public TopAllVo(String id, String energyTotal, String carbonTotal, String costTotal, String yardage, String conventYardage, String priceArea) {
        this.id = id;
        this.energyTotal = energyTotal;
        this.carbonTotal = carbonTotal;
        this.costTotal = costTotal;
        this.yardage = yardage;
        this.conventYardage = conventYardage;
        this.priceArea = priceArea;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnergyTotal() {
        return energyTotal;
    }

    public void setEnergyTotal(String energyTotal) {
        this.energyTotal = energyTotal;
    }

    public String getCarbonTotal() {
        return carbonTotal;
    }

    public void setCarbonTotal(String carbonTotal) {
        this.carbonTotal = carbonTotal;
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

    public String getConventYardage() {
        return conventYardage;
    }

    public void setConventYardage(String conventYardage) {
        this.conventYardage = conventYardage;
    }

    public String getPriceArea() {
        return priceArea;
    }

    public void setPriceArea(String priceArea) {
        this.priceArea = priceArea;
    }
}

