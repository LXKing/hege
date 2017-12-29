package com.huak.sys;

import com.huak.common.Constants;
import com.huak.weather.dao.WeatherDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description:  天气预报   <BR>
 * Function List:  <BR>
 */
@Service
public class SysWeatherServiceImpl implements SysWeatherService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WeatherDao weatherDao;

    /**
     * 找到公司本小时的平均天气温度
     *
     * @param wcode 可能是公司code,也可能是weather唯一码
     * @param time  %Y-%m-%d %H:00:00
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Double getWeatherByTime(String wcode, String time) {
        Map<String, Object> params = new HashMap();
        params.put("wcode", wcode);
        params.put("time", time);
        Double num = weatherDao.getWeatherByTime(params);
        return null == num ? Constants.EXPTION_NUM : num;
    }
}
