package com.huak.workorder.vo;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.workorder.vo<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-11-09<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class WorkOrderInfoRel implements Serializable {
    private static final long serialVersionUID = -1273733892021545243L;
    private String code;
    private String name;
    private Integer status;
    private String parentCode;
    private String resetNum;

    public WorkOrderInfoRel(String code, String name, Integer status, String parentCode, String resetNum) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.parentCode = parentCode;
        this.resetNum = resetNum;
    }

    public WorkOrderInfoRel() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getResetNum() {
        return resetNum;
    }

    public void setResetNum(String resetNum) {
        this.resetNum = resetNum;
    }
}
