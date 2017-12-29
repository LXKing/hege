package com.huak.sys.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EnergyType implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;

    private String nameZh;

    private String nameEn;

    private String dosageUnit;

    private BigDecimal price;

    private Double coef;

    private Byte ecoType;

    private Byte type;

    public EnergyType(String id, String nameZh, String nameEn, String dosageUnit, BigDecimal price, Double coef, Byte ecoType, Byte type) {
        this.id = id;
        this.nameZh = nameZh;
        this.nameEn = nameEn;
        this.dosageUnit = dosageUnit;
        this.price = price;
        this.coef = coef;
        this.ecoType = ecoType;
        this.type = type;
    }

    public EnergyType() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getCoef() {
        return coef;
    }

    public void setCoef(Double coef) {
        this.coef = coef;
    }

    public Byte getEcoType() {
        return ecoType;
    }

    public void setEcoType(Byte ecoType) {
        this.ecoType = ecoType;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}