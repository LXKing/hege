package com.huak.sys.model;

import java.io.Serializable;

public class CostType implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;

    private String nameZh;

    private String nameEn;

    private String dosageUnit;

    public CostType(String id, String nameZh, String nameEn, String dosageUnit) {
        this.id = id;
        this.nameZh = nameZh;
        this.nameEn = nameEn;
        this.dosageUnit = dosageUnit;
    }

    public CostType() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh == null ? null : nameZh.trim();
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit == null ? null : dosageUnit.trim();
    }
}