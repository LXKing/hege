package com.huak.weather.model;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.weather.model<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-11-27<BR>
 * Description:  航天三院天气实体   <BR>
 * Function List:  <BR>
 */
public class HTSYWeather implements Serializable{

    private static final long serialVersionUID = -3088432640435463806L;

    private String cdate;//日期
    private String maxTemp;//最高温度
    private String minTemp;//最低温度
    private String avgTemp;//平均温度
    private String tempDetail;//天气情况

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(String avgTemp) {
        this.avgTemp = avgTemp;
    }

    public String getTempDetail() {
        return tempDetail;
    }

    public void setTempDetail(String tempDetail) {
        this.tempDetail = tempDetail;
    }
}
