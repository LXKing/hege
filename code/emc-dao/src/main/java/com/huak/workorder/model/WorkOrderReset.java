package com.huak.workorder.model;

import java.io.Serializable;

public class WorkOrderReset implements Serializable {
    private static final long serialVersionUID = 6077128872462297601L;
    private String orderCode;

    private String parentCode;

    private Integer resetNum;

    public WorkOrderReset(String orderCode, String parentCode, Integer resetNum) {
        this.orderCode = orderCode;
        this.parentCode = parentCode;
        this.resetNum = resetNum;
    }

    public WorkOrderReset() {
        super();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public Integer getResetNum() {
        return resetNum;
    }

    public void setResetNum(Integer resetNum) {
        this.resetNum = resetNum;
    }
}