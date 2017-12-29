package com.huak.home.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EnergyMonitor implements Serializable{
    private static final long serialVersionUID = -6831823693379694440L;
    /**
     *主键
     * @preserve 声明此方法不被JOC混淆
     */
    private String id;

    /**
     *公司
     * @preserve 声明此方法不被JOC混淆
     */
    private String comid;

    /**
     *用能单位
     * @preserve 声明此方法不被JOC混淆
     */
    private String unitid;

    /**
     *用能节点
     * @preserve 声明此方法不被JOC混淆
     */
    private String nodeid;

    /**
     *能源类型
     * @preserve 声明此方法不被JOC混淆
     */
    private String typeid;

    /**
     *时间(%Y-%m-%d %H:00:00)
     * @preserve 声明此方法不被JOC混淆
     */
    private String dosageTime;

    /**
     *能源用量
     * @preserve 声明此方法不被JOC混淆
     */
    private Double dosage;

    /**
     *能源面积(m²)
     * @preserve 声明此方法不被JOC混淆
     */
    private Double area;

    /**
     *能源单价(元)
     * @preserve 声明此方法不被JOC混淆
     */
    private BigDecimal price;

    /**
     *天气温度(℃)
     * @preserve 声明此方法不被JOC混淆
     */
    private Double wtemp;

    /**
     *折算天气温度(℃)
     * @preserve 声明此方法不被JOC混淆
     */
    private Double cwtemp;

    /**
     *标煤系数
     * @preserve 声明此方法不被JOC混淆
     */
    private Double coalCoef;

    /**
     *碳排放系数
     * @preserve 声明此方法不被JOC混淆
     */
    private Double cCoef;

    /**
     *平均室内温度(℃)
     * @preserve 声明此方法不被JOC混淆
     */
    private Double itemp;

    /**
     *折算室内温度(℃)
     * @preserve 声明此方法不被JOC混淆
     */
    private Double citemp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid == null ? null : comid.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid == null ? null : nodeid.trim();
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getDosageTime() {
        return dosageTime;
    }

    public void setDosageTime(String dosageTime) {
        this.dosageTime = dosageTime;
    }

    public Double getDosage() {
        return dosage;
    }

    public void setDosage(Double dosage) {
        this.dosage = dosage;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getWtemp() {
        return wtemp;
    }

    public void setWtemp(Double wtemp) {
        this.wtemp = wtemp;
    }

    public Double getCwtemp() {
        return cwtemp;
    }

    public void setCwtemp(Double cwtemp) {
        this.cwtemp = cwtemp;
    }

    public Double getCoalCoef() {
        return coalCoef;
    }

    public void setCoalCoef(Double coalCoef) {
        this.coalCoef = coalCoef;
    }

    public Double getcCoef() {
        return cCoef;
    }

    public void setcCoef(Double cCoef) {
        this.cCoef = cCoef;
    }

    public Double getItemp() {
        return itemp;
    }

    public void setItemp(Double itemp) {
        this.itemp = itemp;
    }

    public Double getCitemp() {
        return citemp;
    }

    public void setCitemp(Double citemp) {
        this.citemp = citemp;
    }
}