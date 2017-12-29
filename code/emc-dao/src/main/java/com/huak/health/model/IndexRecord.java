package com.huak.health.model;

import java.io.Serializable;

public class IndexRecord implements Serializable{
    private static final long serialVersionUID = 5278599246197500420L;
    private String id;

    private String typeId;

    private String comId;

    private String unitId;

    private Double enterprise;

    private Double local;

    private Double industry;

    private String indexTime;

    private String createTime;

    private String creator;

    public IndexRecord(String id, String typeId, String comId, String unitId, Double enterprise, Double local, Double industry, String indexTime, String createTime, String creator) {
        this.id = id;
        this.typeId = typeId;
        this.comId = comId;
        this.unitId = unitId;
        this.enterprise = enterprise;
        this.local = local;
        this.industry = industry;
        this.indexTime = indexTime;
        this.createTime = createTime;
        this.creator = creator;
    }

    public IndexRecord() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public Double getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Double enterprise) {
        this.enterprise = enterprise;
    }

    public Double getLocal() {
        return local;
    }

    public void setLocal(Double local) {
        this.local = local;
    }

    public Double getIndustry() {
        return industry;
    }

    public void setIndustry(Double industry) {
        this.industry = industry;
    }

    public String getIndexTime() {
        return indexTime;
    }

    public void setIndexTime(String indexTime) {
        this.indexTime = indexTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
}