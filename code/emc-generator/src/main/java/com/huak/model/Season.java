package com.huak.model;

import java.util.Date;

public class Season {
    private String id;

    private String comid;

    private String name;

    private Date sdate;

    private Date edate;

    public Season(String id, String comid, String name, Date sdate, Date edate) {
        this.id = id;
        this.comid = comid;
        this.name = name;
        this.sdate = sdate;
        this.edate = edate;
    }

    public Season() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }
}