package com.huak.tools;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.tools<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/10/12<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class Item implements Serializable{
    private static final long serialVersionUID = 1648009934463575034L;
    private String title;
    private String img;
    private String parentName;
    private String parentTitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public Item() {
        super();
    }

    public Item(String title, String img, String parentName, String parentTitle) {
        this.title = title;
        this.img = img;
        this.parentName = parentName;
        this.parentTitle = parentTitle;
    }
}
