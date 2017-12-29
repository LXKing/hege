package com.huak.sys.type;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys.type<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/1<BR>
 * Description: 前台用户模块    <BR>
 * Function List:  <BR>
 */
public enum MenuModel {
    HOME(1, "首页",0,0),
    ENERGY(2, "能耗分析",0,0),
    COST(3, "成本管控",0,0),
    C(4, "碳排管理",0,0),
    WARN(5, "报警管理",0,0),
    PROJECT(6, "项目后评估",0,0),
    SYSTEM(7, "安全与后台",0,0),
    PROCESS(8, "生产调度",0,0);

    private int key;
    private String value;
    private int menuType;
    private int type;

    MenuModel(int key, String value,int menuType,int type) {
        this.key = key;
        this.value = value;
        this.menuType = menuType;
        this.type = type;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getMenuType() {
        return menuType;
    }

    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
