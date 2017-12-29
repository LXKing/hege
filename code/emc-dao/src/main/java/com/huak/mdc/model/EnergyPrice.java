package com.huak.mdc.model;

import java.math.BigDecimal;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc.model<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description: 能源单价    <BR>
 * Function List:  <BR>
 */
public class EnergyPrice {
    private String id;

    private String comid;

    private String typeid;

    private BigDecimal price;

    private String sdate;

    private String stime;

    private String etime;

    public EnergyPrice(String id, String comid, String typeid, BigDecimal price, String sdate, String stime, String etime) {
        this.id = id;
        this.comid = comid;
        this.typeid = typeid;
        this.price = price;
        this.sdate = sdate;
        this.stime = stime;
        this.etime = etime;
    }

    public EnergyPrice() {
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

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }
}