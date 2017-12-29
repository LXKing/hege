package com.huak.org.model;

import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = -7491591467559807619L;
    /**
     *ID
     * @preserve 声明此方法不被JOC混淆
     */
    private String id;

    /**
     *名称
     * @preserve 声明此方法不被JOC混淆
     */
    private String roomName;

    /**
     *代码
     * @preserve 声明此方法不被JOC混淆
     */
    private String roomCode;

    /**
     *供热面积
     * @preserve 声明此方法不被JOC混淆
     */
    private Double heatArea;

    /**
     *所属机构
     * @preserve 声明此方法不被JOC混淆
     */
    private Long orgId;

    /**
     *所属管线
     * @preserve 声明此方法不被JOC混淆
     */
    private String lineId;

    /**
     *小区
     * @preserve 声明此方法不被JOC混淆
     */
    private String communityId;

    /**
     *楼栋
     * @preserve 声明此方法不被JOC混淆
     */
    private String banId;

    /**
     *单元
     * @preserve 声明此方法不被JOC混淆
     */
    private String cellId;

    /**
     *公司
     * @preserve 声明此方法不被JOC混淆
     */
    private String comId;
    
    private String heatType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? null : roomCode.trim();
    }

    public Double getHeatArea() {
        return heatArea;
    }

    public void setHeatArea(Double heatArea) {
        this.heatArea = heatArea;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId == null ? null : lineId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getBanId() {
        return banId;
    }

    public void setBanId(String banId) {
        this.banId = banId == null ? null : banId.trim();
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId == null ? null : cellId.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

	public String getHeatType() {
		return heatType;
	}

	public void setHeatType(String heatType) {
		this.heatType = heatType;
	}
    
}