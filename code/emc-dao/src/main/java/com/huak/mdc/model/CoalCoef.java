package com.huak.mdc.model;
/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc.model<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description: 标煤系数    <BR>
 * Function List:  <BR>
 */
public class CoalCoef {
    private String id;

    private String comid;

    private String typeid;

    private Double coef;

    private String sdate;

    public CoalCoef(String id, String comid, String typeid, Double coef, String sdate) {
        this.id = id;
        this.comid = comid;
        this.typeid = typeid;
        this.coef = coef;
        this.sdate = sdate;
    }

    public CoalCoef() {
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

    public Double getCoef() {
        return coef;
    }

    public void setCoef(Double coef) {
        this.coef = coef;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }
}