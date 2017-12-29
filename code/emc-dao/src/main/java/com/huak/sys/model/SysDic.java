package com.huak.sys.model;

import java.io.Serializable;

public class SysDic implements Serializable{
    private static final long serialVersionUID = 4298472155091013987L;
    private String id;

    private String des;

    private String typeUs;

    private String typeZh;

    private Integer seq;

    public SysDic(String id, String des, String typeUs, String typeZh, Integer seq) {
        this.id = id;
        this.des = des;
        this.typeUs = typeUs;
        this.typeZh = typeZh;
        this.seq = seq;
    }

    public SysDic() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public String getTypeUs() {
        return typeUs;
    }

    public void setTypeUs(String typeUs) {
        this.typeUs = typeUs == null ? null : typeUs.trim();
    }

    public String getTypeZh() {
        return typeZh;
    }

    public void setTypeZh(String typeZh) {
        this.typeZh = typeZh == null ? null : typeZh.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}