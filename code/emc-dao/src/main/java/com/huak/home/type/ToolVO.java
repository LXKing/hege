package com.huak.home.type;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.type<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/12<BR>
 * Description:   首页组件条件  <BR>
 * Function List:  <BR>
 */
public class ToolVO {
    private String toolOrgId="74";//组织机构主键
    private String toolFeedType="";//集中供暖和区域供暖
    private String toolStartDate="2016-11-01";//开始时间
    private String toolEndDate="2017-05-30";//结束时间
    private String toolStartDateTq="2017-04-01";//开始时间同期
    private String toolEndDateTq="2018-11-01";//结束时间同期
    private String toolOrgType="";//组织类型

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public String getToolOrgId() {
        return toolOrgId;
    }

    public void setToolOrgId(String toolOrgId) {
        this.toolOrgId = toolOrgId;
    }

    public String getToolFeedType() {
        return toolFeedType;
    }

    public void setToolFeedType(String toolFeedType) {
        this.toolFeedType = toolFeedType;
    }

    public String getToolStartDate() {
        return toolStartDate;
    }

    public void setToolStartDate(String toolStartDate) {
        this.toolStartDate = toolStartDate;
    }

    public String getToolEndDate() {
        return toolEndDate;
    }

    public void setToolEndDate(String toolEndDate) {
        this.toolEndDate = toolEndDate;
    }

    public String getToolStartDateTq() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(this.toolStartDate));
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)-1);
        return format.format(calendar.getTime());
    }

    public void setToolStartDateTq(String toolStartDateTq) {
        this.toolStartDateTq = toolStartDateTq;
    }

    public String getToolEndDateTq() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(this.toolEndDate));
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)-1);


        return format.format(calendar.getTime());
    }

    public void setToolEndDateTq(String toolEndDateTq) {
        this.toolEndDateTq = toolEndDateTq;
    }

    public String getToolOrgType() {
        return toolOrgType;
    }

    public void setToolOrgType(String toolOrgType) {
        this.toolOrgType = toolOrgType;
    }
}
