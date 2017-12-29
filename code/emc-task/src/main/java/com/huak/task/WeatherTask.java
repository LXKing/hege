package com.huak.task;

import com.huak.weather.WeatherTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.task<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/11<BR>
 * Description:   定时任务测试  <BR>
 * Function List:  <BR>
 */
@Component("weatherTask")
public class WeatherTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private WeatherTaskService weatherTaskService;

    public void currentWeather(){
        logger.info("-------------采集每小时天气和空气质量----------------");
        List<Map<String,Object>> params = weatherTaskService.getParams();
        System.out.println(params.size());
        weatherTaskService.executeWeatherTask(params);
        logger.info("-------------每小时天气和空气质量完成----------------");
    }
    public void forcastWeather7d(){
        logger.info("-------------采集七天天气预报----------------");
        List<Map<String,Object>> params =  weatherTaskService.getParams();
        System.out.println(params.size());
        weatherTaskService.executeWeather7dTask(params);
        logger.info("-------------七天天气预报采集完成----------------");
    }
}
