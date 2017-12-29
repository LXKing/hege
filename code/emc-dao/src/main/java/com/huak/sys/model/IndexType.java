package com.huak.sys.model;

import java.io.Serializable;

public class IndexType implements Serializable {
    private static final long serialVersionUID = 2330301333762106288L;
    private String id;

    private Byte energyType;

    private String name;

    private Byte unitType;

    private String unitMeter;

    public IndexType(String id, Byte energyType, String name, Byte unitType, String unitMeter) {
        this.id = id;
        this.energyType = energyType;
        this.name = name;
        this.unitType = unitType;
        this.unitMeter = unitMeter;
    }

    public IndexType() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Byte getEnergyType() {
        return energyType;
    }

    public void setEnergyType(Byte energyType) {
        this.energyType = energyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getUnitType() {
        return unitType;
    }

    public void setUnitType(Byte unitType) {
        this.unitType = unitType;
    }

    public String getUnitMeter() {
        return unitMeter;
    }

    public void setUnitMeter(String unitMeter) {
        this.unitMeter = unitMeter;
    }
}