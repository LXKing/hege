package com.huak.auth.model;

import java.io.Serializable;

public class Func implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;

    private String menuId;

    private String uname;

    private String funcName;

    private Byte issearch;

    private Integer seq;

    public Func(String id, String menuId, String uname, String funcName, Byte issearch, Integer seq) {
        this.id = id;
        this.menuId = menuId;
        this.uname = uname;
        this.funcName = funcName;
        this.issearch = issearch;
        this.seq = seq;
    }

    public Func() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    public Byte getIssearch() {
        return issearch;
    }

    public void setIssearch(Byte issearch) {
        this.issearch = issearch;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}