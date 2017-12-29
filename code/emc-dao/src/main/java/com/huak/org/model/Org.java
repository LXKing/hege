package com.huak.org.model;

import java.io.Serializable;

public class Org implements Serializable{
    private static final long serialVersionUID = 1224499530675458738L;
    private Long id;

    private String orgName;

    private Long pOrgId;

    private String comId;

    private Integer sort;

    public Org() {
        super();
    }

    public Org(Long id, String orgName, Long pOrgId, String comId,Integer sort) {
        this.id = id;
        this.orgName = orgName;
        this.pOrgId = pOrgId;
        this.comId = comId;
        this.sort = sort;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getpOrgId() {
        return pOrgId;
    }

    public void setpOrgId(Long pOrgId) {
        this.pOrgId = pOrgId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}