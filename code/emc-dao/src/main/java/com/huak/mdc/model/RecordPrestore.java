package com.huak.mdc.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc.model<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description: 预存记录    <BR>
 * Function List:  <BR>
 */
public class RecordPrestore  implements Serializable {
    private static final long serialVersionUID = -8019765798161546360L;
    private String id;

    private String collectId;

    private String prestoreTime;

    private Double usedNum;

    private Double prestoreNum;

    private String createTime;

    private String crestor;

    public RecordPrestore(String id, String collectId, String prestoreTime, Double usedNum, Double prestoreNum, String createTime, String crestor) {
        this.id = id;
        this.collectId = collectId;
        this.prestoreTime = prestoreTime;
        this.usedNum = usedNum;
        this.prestoreNum = prestoreNum;
        this.createTime = createTime;
        this.crestor = crestor;
    }

    public RecordPrestore() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId == null ? null : collectId.trim();
    }

    public String getPrestoreTime() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        Date date = format.parse(prestoreTime);
        prestoreTime = format.format(date);
        return prestoreTime;
    }

    public void setPrestoreTime(String prestoreTime) {
        this.prestoreTime = prestoreTime;
    }

    public Double getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Double usedNum) {
        this.usedNum = usedNum;
    }

    public Double getPrestoreNum() {
        return prestoreNum;
    }

    public void setPrestoreNum(Double prestoreNum) {
        this.prestoreNum = prestoreNum;
    }

    public String getCreateTime()  throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        Date date = format.parse(createTime);
        createTime = format.format(date);
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCrestor() {
        return crestor;
    }

    public void setCrestor(String crestor) {
        this.crestor = crestor == null ? null : crestor.trim();
    }
}