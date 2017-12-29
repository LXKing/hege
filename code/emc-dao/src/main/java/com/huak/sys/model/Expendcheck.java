package com.huak.sys.model;

import java.io.Serializable;
import java.util.Date;

public class Expendcheck implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String comid;

    private String typeid;

    private Double standard;

    private Double excellent;

    private Date sdate;

    private Date edate;

    private Long orgId;

    public Expendcheck(String id, String comid, String typeid, Double standard, Double excellent, Date sdate, Date edate, Long orgId) {
        this.id = id;
        this.comid = comid;
        this.typeid = typeid;
        this.standard = standard;
        this.excellent = excellent;
        this.sdate = sdate;
        this.edate = edate;
        this.orgId = orgId;
    }

    public Expendcheck() {
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

    public Double getStandard() {
        return standard;
    }

    public void setStandard(Double standard) {
        this.standard = standard;
    }

    public Double getExcellent() {
        return excellent;
    }

    public void setExcellent(Double excellent) {
        this.excellent = excellent;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}