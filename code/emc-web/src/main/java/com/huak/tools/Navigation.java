package com.huak.tools;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.tools<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/25<BR>
 * Description:   面包屑导航  <BR>
 * Function List:  <BR>
 */
public class Navigation {
    /**
     * 上级
     */
    private Navigation navigation;
    /**
     * 标题
     */
    private String title;
    /**
     * url
     */
    private String url;

    public Navigation(Navigation navigation,String title,String url){
        this.navigation = navigation;
        this.title = title;
        this.url = url;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
