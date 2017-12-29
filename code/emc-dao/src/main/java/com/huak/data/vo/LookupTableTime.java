package com.huak.data.vo;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.data.vo<BR>
 * Author:  liuhe  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/11/21<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class LookupTableTime implements Serializable{

    private static final long serialVersionUID = 6905568013656734401L;
    //上次查表时间
    private String lastTime;
    //本次查表时间
    private String thisTime;
    //时间差
    private String timeDifference;

    @Override
    public String toString() {
        return "LookupTableTime{" +
                "lastTime='" + lastTime + '\'' +
                ", thisTime='" + thisTime + '\'' +
                ", timeDifference='" + timeDifference + '\'' +
                '}';
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getThisTime() {
        return thisTime;
    }

    public void setThisTime(String thisTime) {
        this.thisTime = thisTime;
    }

    public String getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(String timeDifference) {
        this.timeDifference = timeDifference;
    }

    public LookupTableTime() {

    }

    public LookupTableTime(String lastTime, String thisTime, String timeDifference) {

        this.lastTime = lastTime;
        this.thisTime = thisTime;
        this.timeDifference = timeDifference;
    }
}
