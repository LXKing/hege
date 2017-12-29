package com.huak.health.model;

import java.io.IOException;
import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health.model<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/10/11<BR>
 * Description:   队列消息  <BR>
 * Function List:  <BR>
 */
public class PollingMessage implements Serializable{
    private static final long serialVersionUID = 3965700039912661941L;
    private String key;
    private Object value;

    public PollingMessage(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {
        //执行序列化存储
        out.writeObject(value);
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        //执行反序列化读取
        value =  in.readObject();
    }
}
