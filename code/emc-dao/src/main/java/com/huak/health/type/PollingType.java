package com.huak.health.type;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health.type<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/10/11<BR>
 * Description:   长连接返回标识  <BR>
 * Function List:  <BR>
 */
public enum PollingType {
    MSG("msg","消息"),
    NUM("num","异常数量"),
    END("end","结束");


    private String key;
    private String des;

    PollingType(String key, String des) {
        this.key = key;
        this.des = des;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
