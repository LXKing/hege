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
 * Description: 单位面积    <BR>
 * Function List:  <BR>
 */
public class UnitArea implements Serializable{
    private static final long serialVersionUID = 3147962668118280134L;
    private String id;

    private String comid;

    private String unitid;

    private Double area;

    private String stime;

    private Byte unittype;

    public UnitArea(String id, String comid, String unitid, Double area, String stime, Byte unittype) {
        this.id = id;
        this.comid = comid;
        this.unitid = unitid;
        this.area = area;
        this.stime = stime;
        this.unittype = unittype;
    }

    public UnitArea() {
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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public Byte getUnittype() {
        return unittype;
    }

    public void setUnittype(Byte unittype) {
        this.unittype = unittype;
    }
}