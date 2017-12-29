package com.huak.workorder.vo;

import com.huak.common.StringUtils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkOrderRecordA implements Serializable{
    private static final long serialVersionUID = 2067628565652894835L;
    private String id;

    private String code;

    private Integer beforStatus;

    private String operateTime;

    private String opertor;

    private String sendee;

    private Integer afterStatus;

    private String des;

    private String empName;

    private String reciver;

    public WorkOrderRecordA(String id, String code, Integer beforStatus, String operateTime, String opertor, String sendee, Integer afterStatus, String des, String empName,String reciver) {
        this.id = id;
        this.code = code;
        this.beforStatus = beforStatus;
        this.operateTime = operateTime;
        this.opertor = opertor;
        this.sendee = sendee;
        this.afterStatus = afterStatus;
        this.des = des;
        this.empName=empName;
        this.reciver=reciver;
    }

    public WorkOrderRecordA() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getBeforStatus() {
        return beforStatus;
    }

    public void setBeforStatus(Integer beforStatus) {
        this.beforStatus = beforStatus;
    }

    public String getOperateTime() throws ParseException {
        if(!StringUtils.isEmpty(operateTime)){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(operateTime);
            operateTime = format.format(date);
        }
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getOpertor() {
        return opertor;
    }

    public void setOpertor(String opertor) {
        this.opertor = opertor == null ? null : opertor.trim();
    }

    public String getSendee() {
        return sendee;
    }

    public void setSendee(String sendee) {
        this.sendee = sendee == null ? null : sendee.trim();
    }

    public Integer getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(Integer afterStatus) {
        this.afterStatus = afterStatus;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }
}