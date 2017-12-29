package com.huak.health.model;

import java.io.Serializable;

public class HealthScoreRecord implements Serializable{

    private static final long serialVersionUID = -2635202560088168113L;
    private String id;

    private String orgId;

    private Double score;

    private String userid;

    private String createTime;

    public HealthScoreRecord(String id, String orgId, Double score, String userid, String createTime) {
        this.id = id;
        this.orgId = orgId;
        this.score = score;
        this.userid = userid;
        this.createTime = createTime;
    }

    public HealthScoreRecord() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}