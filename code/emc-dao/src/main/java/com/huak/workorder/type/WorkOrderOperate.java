package com.huak.workorder.type;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.workorder.type<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-10-25<BR>
 * Description:  操作翻译   <BR>
 * Function List:  <BR>
 */
public enum WorkOrderOperate {
    A_SAVE(11,"{1}保存工单"),
    A_SEND(12,"{1}派送工单给{2}"),
    A_SAVE_SEND(13,"{1}保存并派送工单给{2}"),
    A_RESET(14,"{1}重新派送工单给{2}，流程结束"),
    A_CONFIRM(15,"{1}确认工单，流程结束"),
    A_CLOSE(16,"{1}关闭工单，流程结束"),

    B_TAKING(21,"{1}接受工单"),
    B_BACK(22,"{1}退回工单给{2}"),
    B_SEND(23,"{1}派送工单给{2}"),
    B_FINISH(24,"{1}完成工单,待{2}确认"),

    C_TAKING(31,"{1}接受工单"),
    C_BACK(32,"{1}退回工单给{2}"),
    C_FINISH(33,"{1}完成工单,待{2}确认");

    private int key;
    private String value;

    WorkOrderOperate(int key, String value) {
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
