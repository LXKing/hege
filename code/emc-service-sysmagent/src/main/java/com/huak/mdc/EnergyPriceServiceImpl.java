package com.huak.mdc;

import com.huak.mdc.dao.EnergyPriceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description:   能源单价  <BR>
 * Function List:  <BR>
 */
@Service
public class EnergyPriceServiceImpl implements EnergyPriceService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EnergyPriceDao energyPriceDao;
    /**
     * @param id           公司主键
     * @param energyTypeId 能源类型
     * @param time         %Y-%m-%d %H:00:00
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public BigDecimal getEnergyPriceByTime(String id, String energyTypeId, String time) throws ParseException {
        Map<String, Object> params = new HashMap();
        params.put("id",id);
        params.put("energyTypeId",energyTypeId);
        params.put("time",time);
        params.put("hour",time.substring(time.length()-8,time.length()));
        return energyPriceDao.getEnergyPriceByTime(params);
    }
}
