package com.huak.energy;

import com.alibaba.fastjson.JSONObject;
import com.huak.base.dao.DateDao;
import com.huak.common.StringUtils;
import com.huak.org.model.Company;
import com.huak.task.dao.AutoSaveDataDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.energy<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-11-16<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class AutoSaveEnergyServiceImpl implements AutoSaveEnergyService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DateDao dateDao;
    @Resource
    private AutoSaveDataDao autoSaveDataDao;

    /**
     * 自动保存采集数据到能耗
     */
    @Override
    @Transactional(readOnly = true)
    public JSONObject selectDatas(Company company) {
        JSONObject jo = new JSONObject();
        //当前数据库时间
        String time = dateDao.getTime();
        jo.put("time", time);
        // 获取采集数据最大时间yyyy-MM-dd HH
        Map<String, Object> params = new HashMap<>();
        params.put("time", time);
        params.put("comId", company.getId());
        String maxTime = autoSaveDataDao.getMaxDataTime(params);
        jo.put("maxTime", maxTime);
        if (StringUtils.isEmpty(maxTime)) {
            jo.put("datas", null);
        } else {
            // 查询自动采集的最大时间批次的数据
            params.clear();
            params.put("maxTime", maxTime);
            params.put("comId", company.getId());
            List<Map<String, Object>> datas = autoSaveDataDao.selectAutoDataByTime(params);
            jo.put("datas", datas);
        }
        return jo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> selectTags(Map<String, Object> paramsMap) {
        return autoSaveDataDao.selectTags(paramsMap);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateStates(Map<String, Object> params) {
        autoSaveDataDao.updateStates(params);
    }
}
