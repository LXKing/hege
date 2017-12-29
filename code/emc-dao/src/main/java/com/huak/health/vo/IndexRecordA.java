package com.huak.health.vo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IndexRecordA implements Serializable{

    private static final long serialVersionUID = -8051752265609262982L;
    private String id;
    private String unitName;
    private String name;

    private String unitMeter;

    private Double enterprise;

    private Double local;

    private Double industry;

    private String indexTime;

    private String createTime;

    private String userName;

    public IndexRecordA(String id, String unitName, String name, String unitMeter, Double enterprise, Double local, Double industry, String indexTime, String createTime, String userName) {
        this.id = id;
        this.unitName = unitName;
        this.name = name;
        this.unitMeter = unitMeter;
        this.enterprise = enterprise;
        this.local = local;
        this.industry = industry;
        this.indexTime = indexTime;
        this.createTime = createTime;
        this.userName = userName;
    }

    public IndexRecordA() {
        super();
    }


    public Double getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Double enterprise) {
        this.enterprise = enterprise;
    }

    public Double getLocal() {
        return local;
    }

    public void setLocal(Double local) {
        this.local = local;
    }

    public Double getIndustry() {
        return industry;
    }

    public void setIndustry(Double industry) {
        this.industry = industry;
    }

    public String getIndexTime() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        Date date = format.parse(indexTime);
        indexTime = format.format(date);
        return indexTime;
    }

    public void setIndexTime(String indexTime) {
        this.indexTime = indexTime;
    }

    public String getCreateTime() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(createTime);
        createTime = format.format(date);
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitMeter() {
        return unitMeter;
    }

    public void setUnitMeter(String unitMeter) {
        this.unitMeter = unitMeter;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}