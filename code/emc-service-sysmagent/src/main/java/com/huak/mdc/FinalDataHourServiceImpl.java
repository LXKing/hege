package com.huak.mdc;

import com.huak.mdc.dao.FinalDataHourDao;
import com.huak.mdc.dao.MeterCollectDao;
import com.huak.sys.SysWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description: 最终能耗数据    <BR>
 * Function List:  <BR>
 */
@Service
public class FinalDataHourServiceImpl implements FinalDataHourService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private FinalDataHourDao finalDataHourDao;
    @Resource
    private MeterCollectDao meterCollectDao;
    //标煤系数
    @Resource
    private CoalCoefService coalCoefService;
    //碳排放
    @Resource
    private CarbonFormulaService carbonFormulaService;
    //能源单价
    @Resource
    private EnergyPriceService energyPriceService;
    //单位面积
    @Resource
    private UnitAreaService unitAreaService;
    //天气
    @Resource
    private SysWeatherService sysWeatherService;


}
