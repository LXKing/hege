package com.huak.org.model;

import java.io.Serializable;

public class Ban implements Serializable {
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
    private String banName;

    /**
     *省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE
     * @preserve 声明此方法不被JOC混淆
     */
    private String provinceId;

    /**
     *市主键
     * @preserve 声明此方法不被JOC混淆
     */
    private String cityId;

    /**
     *县主键
     * @preserve 声明此方法不被JOC混淆
     */
    private String countyId;

    /**
     *乡主键
     * @preserve 声明此方法不被JOC混淆
     */
    private String townId;

    /**
     *村主键
     * @preserve 声明此方法不被JOC混淆
     */
    private String villageId;

    /**
     *详细地址
     * @preserve 声明此方法不被JOC混淆
     */
    private String addr;

    /**
     *小区
     * @preserve 声明此方法不被JOC混淆
     */
    private String communityId;

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

    public String getBanName() {
        return banName;
    }

    public void setBanName(String banName) {
        this.banName = banName == null ? null : banName.trim();
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId == null ? null : countyId.trim();
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId == null ? null : townId.trim();
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId == null ? null : villageId.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
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