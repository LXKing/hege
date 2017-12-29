package com.huak.org.model;

import java.io.Serializable;

public class Cell implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *ID
     * @preserve 声明此方法不被JOC混淆
     */
    private String id;

    /**
     *名称
     * @preserve 声明此方法不被JOC混淆
     */
    private String cellName;

    /**
     *楼栋
     * @preserve 声明此方法不被JOC混淆
     */
    private String banId;

    /**
     *所属机构
     * @preserve 声明此方法不被JOC混淆
     */
    private Long orgId;

    /**
     *公司
     * @preserve 声明此方法不被JOC混淆
     */
    private String comId;
    
    private String communityId;
    

    public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName == null ? null : cellName.trim();
    }

    public String getBanId() {
        return banId;
    }

    public void setBanId(String banId) {
        this.banId = banId == null ? null : banId.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }
}