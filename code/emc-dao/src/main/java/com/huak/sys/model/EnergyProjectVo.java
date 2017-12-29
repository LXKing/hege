package com.huak.sys.model;

import java.io.Serializable;

public class EnergyProjectVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *主键
     * @preserve 声明此方法不被JOC混淆
     */
    private String id;

    /**
     *组织机构主键
     * @preserve 声明此方法不被JOC混淆
     */
    private String orgId;

    /**
     *采暖季主键
     * @preserve 声明此方法不被JOC混淆
     */
    private String seasonId;

    /**
     *计划能耗
     * @preserve 声明此方法不被JOC混淆
     */
    private Double num;
    
    private String orgName;
    
    private String seasonName;

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

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId == null ? null : seasonId.trim();
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
    
}