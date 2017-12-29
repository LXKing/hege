package com.huak.org.model;

import java.io.Serializable;

public class Community implements Serializable {
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
    private String communityName;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
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