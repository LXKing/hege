package com.huak.sys;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface SysWeatherService {
    /**
     * 找到公司本小时的平均天气温度
     * @param wcode 可能是公司code,也可能是weather唯一码
     * @param time %Y-%m-%d %H:00:00
     * @return
     */
    Double getWeatherByTime(String wcode, String time);
}
