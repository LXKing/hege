package com.huak.task.model;

import java.util.Date;

public class MetaData {
    private String tag;

    private Date collectTime;

    private String collectType;

    private Double collectValue;

    private Byte readSate;

    public MetaData(String tag, Date collectTime, String collectType, Double collectValue, Byte readSate) {
        this.tag = tag;
        this.collectTime = collectTime;
        this.collectType = collectType;
        this.collectValue = collectValue;
        this.readSate = readSate;
    }

    public MetaData() {
        super();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType == null ? null : collectType.trim();
    }

    public Double getCollectValue() {
        return collectValue;
    }

    public void setCollectValue(Double collectValue) {
        this.collectValue = collectValue;
    }

    public Byte getReadSate() {
        return readSate;
    }

    public void setReadSate(Byte readSate) {
        this.readSate = readSate;
    }
}