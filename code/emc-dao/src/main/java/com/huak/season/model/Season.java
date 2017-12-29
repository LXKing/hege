package com.huak.season.model;

import java.io.Serializable;

public class Season implements Serializable{

    private static final long serialVersionUID = -2590794570299749389L;
    private String id;

    private String comid;

    private String name;

    private String sdate;

    private String edate;

    public Season(String id, String comid, String name, String sdate, String edate) {
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

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }
}