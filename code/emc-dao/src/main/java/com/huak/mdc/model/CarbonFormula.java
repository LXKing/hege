package com.huak.mdc.model;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc.model<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description: 碳排放系数    <BR>
 * Function List:  <BR>
 */
public class CarbonFormula implements Serializable{
    private static final long serialVersionUID = -4216850905072552173L;
    private String id;

    private String comid;

    private String typeid;

    private Double camount;

    private Double crate;

    private String sdate;

    public CarbonFormula(String id, String comid, String typeid, Double camount, Double crate, String sdate) {
        this.id = id;
        this.comid = comid;
        this.typeid = typeid;
        this.camount = camount;
        this.crate = crate;
        this.sdate = sdate;
    }

    public CarbonFormula() {
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

    public Double getCamount() {
        return camount;
    }

    public void setCamount(Double camount) {
        this.camount = camount;
    }

    public Double getCrate() {
        return crate;
    }

    public void setCrate(Double crate) {
        this.crate = crate;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }
}