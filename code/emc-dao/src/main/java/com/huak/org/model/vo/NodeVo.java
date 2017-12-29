package com.huak.org.model.vo;

import java.io.Serializable;

/**
 * Created by MR-BIN on 2017/5/19.
 */
public class NodeVo implements Serializable{

    private static final long serialVersionUID = 1864682359709825218L;
    private String id;

    private String comId;

    private String orgCode;

    private String orgName;

    private String shortName;

    private Long pOrgId;

    private String typeId;

    private String creator;

    private String createTime;

    private String memo;

    private Integer seq;

    private Double area;

    private String manageTypeId;

    private String provinceId;

    private String cityId;

    private String countyId;

    private String townId;

    private String villageId;

    private String addr;

    private Double lng;

    private Double lat;

    private Double publicArea;

    private Double dwellArea;

    private Byte status;

    public NodeVo(String id, String comId, String orgCode, String orgName, String shortName, Long pOrgId, String typeId, String creator, String createTime, String memo, Integer seq, Double area
                  , String manageTypeId, String provinceId, String cityId, String countyId, String townId, String villageId, String addr, Double lng, Double lat, Double publicArea, Double dwellArea
               ,Byte status) {
        this.id = id;
        this.comId = comId;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.shortName = shortName;
        this.pOrgId = pOrgId;
        this.typeId = typeId;
        this.creator = creator;
        this.createTime = createTime;
        this.memo = memo;
        this.seq = seq;
        this.area = area;
        this.manageTypeId = manageTypeId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.countyId = countyId;
        this.townId = townId;
        this.villageId = villageId;
        this.addr = addr;
        this.lng = lng;
        this.lat = lat;
        this.publicArea = publicArea;
        this.dwellArea = dwellArea;
        this.status = status;
    }

    public NodeVo() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id ;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public Long getpOrgId() {
        return pOrgId;
    }

    public void setpOrgId(Long pOrgId) {
        this.pOrgId = pOrgId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime== null ? null :createTime.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getManageTypeId() {
        return manageTypeId;
    }

    public void setManageTypeId(String manageTypeId) {
        this.manageTypeId = manageTypeId == null ? null : manageTypeId.trim();
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

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getPublicArea() {
        return publicArea;
    }

    public void setPublicArea(Double publicArea) {
        this.publicArea = publicArea;
    }

    public Double getDwellArea() {
        return dwellArea;
    }

    public void setDwellArea(Double dwellArea) {
        this.dwellArea = dwellArea;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
