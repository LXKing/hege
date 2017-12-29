package com.huak.org.model;

import java.math.BigDecimal;
import java.util.Date;

public class FinalCostDay {
    private String id;

    private String comid;

    private String unitid;

    private String typeid;

    private BigDecimal cost;

    private Date costDate;

    public FinalCostDay(String id, String comid, String unitid, String typeid, BigDecimal cost, Date costDate) {
        this.id = id;
        this.comid = comid;
        this.unitid = unitid;
        this.typeid = typeid;
        this.cost = cost;
        this.costDate = costDate;
    }

    public FinalCostDay() {
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

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getCostDate() {
        return costDate;
    }

    public void setCostDate(Date costDate) {
        this.costDate = costDate;
    }
}