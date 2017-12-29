package com.huak.mdc.vo;

import java.io.Serializable;

public class MeterCollectDataA implements Serializable{

    private static final long serialVersionUID = 4924285657565919782L;
        private String id;

        private String code;

        private String name;

        private String energyType;

        private String isreal;

        private String istotal;

        private String collectTime;

        private String unitname;


        private String isauto;

        private String realFlag;

        private String flag;

        private String changeStatus;

        private String prestorestatus;

        private String num;

        private String isChange;

        private String isPrestore;

        private String changeNum;

        private String prestoreNum;

        private Double coef;

        private String comId;
        private String len;
        private String s;

    public MeterCollectDataA(String id, String code, String name,String collectTime, String energyType, String isreal, String istotal, String unitname, String isauto, String realFlag, String flag, String changeStatus, String prestorestatus, String num, String isChange, String isPrestore, String changeNum, String prestoreNum, Double coef, String comId,String len,String s) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.collectTime=collectTime;
        this.energyType = energyType;
        this.isreal = isreal;
        this.istotal = istotal;
        this.unitname = unitname;
        this.isauto = isauto;
        this.realFlag = realFlag;
        this.flag = flag;
        this.changeStatus = changeStatus;
        this.prestorestatus = prestorestatus;
        this.num = num;
        this.isChange = isChange;
        this.isPrestore = isPrestore;
        this.changeNum = changeNum;
        this.prestoreNum = prestoreNum;
        this.coef = coef;
        this.comId=comId;
        this.len = len;
        this.s = s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCollectTime() {
        return collectTime;
    }
    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }
    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public String getIsreal() {
        return isreal;
    }

    public void setIsreal(String isreal) {
        this.isreal = isreal;
    }

    public String getIstotal() {
        return istotal;
    }

    public void setIstotal(String istotal) {
        this.istotal = istotal;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getIsauto() {
        return isauto;
    }

    public void setIsauto(String isauto) {
        this.isauto = isauto;
    }

    public String getRealFlag() {
        return realFlag;
    }

    public void setRealFlag(String realFlag) {
        this.realFlag = realFlag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    public String getPrestorestatus() {
        return prestorestatus;
    }

    public void setPrestorestatus(String prestorestatus) {
        this.prestorestatus = prestorestatus;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIsChange() {
        return isChange;
    }

    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    public String getIsPrestore() {
        return isPrestore;
    }

    public void setIsPrestore(String isPrestore) {
        this.isPrestore = isPrestore;
    }

    public String getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(String changeNum) {
        this.changeNum = changeNum;
    }

    public String getPrestoreNum() {
        return prestoreNum;
    }

    public void setPrestoreNum(String prestoreNum) {
        this.prestoreNum = prestoreNum;
    }

    public Double getCoef() {
        return coef;
    }

    public void setCoef(Double coef) {
        this.coef = coef;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
