package com.huak.auth.type;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.auth<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/11/9<BR>
 * Description:   用户可用状态枚举  <BR>
 * Function List:  <BR>
 */
public enum UserStatusType {
    ENABLE(0, "启用"),
    DISABLE(1, "禁用");

    private int key;
    private String value;

    UserStatusType(int key, String value) {
        this.key = key;
        this.value = value;
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
}
