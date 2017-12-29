package com.huak.mdc.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc.model<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description: 最终能耗数据    <BR>
 * Function List:  <BR>
 */
public class FinalDataHour  implements Serializable {
    private static final long serialVersionUID = 8037885069097094955L;
    private String id;

    private String comid;

    private String unitid;

    private String nodeid;

    private String typeid;

    private String dosageTime;

    private Double dosage;

    private Double area;

    private BigDecimal price;

    private Double wtemp;

    private Double cwtemp;

    private Double coalCoef;

    private Double cCoef;

    private Double itemp;

    private Double citemp;

    private String tableName;//表名称

    private Long count;//计数

    public FinalDataHour(String id, String comid, String unitid, String nodeid, String typeid, String dosageTime, Double dosage, Double area, BigDecimal price, Double wtemp, Double cwtemp, Double coalCoef, Double cCoef, Double itemp, Double citemp) {
        this.id = id;
        this.comid = comid;
        this.unitid = unitid;
        this.nodeid = nodeid;
        this.typeid = typeid;
        this.dosageTime = dosageTime;
        this.dosage = dosage;
        this.area = area;
        this.price = price;
        this.wtemp = wtemp;
        this.cwtemp = cwtemp;
        this.coalCoef = coalCoef;
        this.cCoef = cCoef;
        this.itemp = itemp;
        this.citemp = citemp;
    }

    public FinalDataHour() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid == null ? null : comid.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid == null ? null : nodeid.trim();
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getDosageTime() {
        return dosageTime;
    }

    public void setDosageTime(String dosageTime) {
        this.dosageTime = dosageTime;
    }

    public Double getDosage() {
        return dosage;
    }

    public void setDosage(Double dosage) {
        this.dosage = dosage;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getWtemp() {
        return wtemp;
    }

    public void setWtemp(Double wtemp) {
        this.wtemp = wtemp;
    }

    public Double getCwtemp() {
        return cwtemp;
    }

    public void setCwtemp(Double cwtemp) {
        this.cwtemp = cwtemp;
    }

    public Double getCoalCoef() {
        return coalCoef;
    }

    public void setCoalCoef(Double coalCoef) {
        this.coalCoef = coalCoef;
    }

    public Double getcCoef() {
        return cCoef;
    }

    public void setcCoef(Double cCoef) {
        this.cCoef = cCoef;
    }

    public Double getItemp() {
        return itemp;
    }

    public void setItemp(Double itemp) {
        this.itemp = itemp;
    }

    public Double getCitemp() {
        return citemp;
    }

    public void setCitemp(Double citemp) {
        this.citemp = citemp;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}