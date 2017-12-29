package com.huak.mdc;

import com.huak.common.Constants;
import com.huak.mdc.dao.UnitAreaDao;
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
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description: 单位面积    <BR>
 * Function List:  <BR>
 */
@Service
public class UnitAreaServiceImpl implements UnitAreaService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UnitAreaDao unitAreaDao;

    /**
     * @param id       公司主键
     * @param unitId   用能单位主键
     * @param unitType 用能单位类型
     * @param time     %Y-%m-%d %H:00:00
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Double getUnitAreaByTime(String id, String unitId, Byte unitType, String time) {
        Map<String, Object> params = new HashMap();
        params.put("id", id);
        params.put("unitId", unitId);
        params.put("unitType", unitType);
        params.put("time", time);
        Double num = unitAreaDao.getUnitAreaByTime(params);
        return null == num ? Constants.EXPTION_NUM : num;
    }
}
