package com.huak.org.model.vo;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org.model.vo<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/9<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

public class CostVo implements Serializable{

    private static final long serialVersionUID = 7218843060079240856L;
    public Double manage;//管理费
    public Double other;//其他费用
    public Double device;//设备费
    public Double labor;//人工费
    public Double energy;//能源费
    public CostVo() {
    }

    public CostVo(Double manage, Double other, Double device, Double labor, Double energy) {
        this.manage = manage;
        this.other = other;
        this.device = device;
        this.labor = labor;
        this.energy = energy;
    }

    public Double getManage() {
        return manage;
    }

    public void setManage(Double manage) {
        this.manage = manage;
    }

    public Double getOther() {
        return other;
    }

    public void setOther(Double other) {
        this.other = other;
    }

    public Double getDevice() {
        return device;
    }

    public void setDevice(Double device) {
        this.device = device;
    }

    public Double getLabor() {
        return labor;
    }

    public void setLabor(Double labor) {
        this.labor = labor;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }
}
