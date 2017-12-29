package com.huak.task;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.StringUtils;
import com.huak.energy.AutoSaveEnergyService;
import com.huak.mdc.EnergyDataHisService;
import com.huak.mdc.MeterCollectService;
import com.huak.mdc.model.EnergyDataHis;
import com.huak.mdc.model.MeterCollect;
import com.huak.org.CompanyService;
import com.huak.org.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.task<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-11-16<BR>
 * Description:   自动采集数据自动转存为每小时能耗数据  <BR>
 * Function List:  <BR>
 */
@Component("energyDataTask")
public class EnergyDataTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AutoSaveEnergyService autoSaveEnergyService;
    @Resource
    private EnergyDataHisService energyDataHisService;
    @Resource
    private CompanyService companyService;
    @Resource
    private MeterCollectService meterCollectService;

    public void autoSaveEnergyData(){
        logger.info("-------------定时自动填报自动采集数据----------------");
        Map<String,Object> paramsMap = new HashMap<>();
        List<Company> companies = companyService.selectAllByMap(paramsMap);
        for(Company company:companies){
            logger.info("-------------开始填报【" + company.getCname() + "】数据----------------");
            //查询自动采集数据
            JSONObject jsonObject = autoSaveEnergyService.selectDatas(company);
            String time = jsonObject.getString("time");
            String maxTime = jsonObject.getString("maxTime");
            List<EnergyDataHis> dataHisList = new ArrayList<>();
            paramsMap.put("comId",company.getId());
            List<MeterCollect> meterCollectList = meterCollectService.selectAutoMeters(paramsMap);
            if (StringUtils.isEmpty(maxTime)) {
                logger.info("--------time：【" + time + "】没有最新的采集数据--------");
            }else{
                List<Map<String,Object>> datas = (List<Map<String, Object>>) jsonObject.get("datas");
                for(Map<String,Object> map:datas){
                    String collId = map.get("ID").toString();//采集计量表主键
                    String collTime = map.get("TIME").toString()+":00:00";//时间
                    Double collNum = Double.valueOf(map.get("NUM").toString());//表底
                    EnergyDataHis energyDataHis = new EnergyDataHis();
                    energyDataHis.setCollectId(collId);
                    energyDataHis.setCollectNum(collNum);
                    energyDataHis.setCollectTime(collTime);
                    energyDataHis.setIschange((byte) 0);
                    energyDataHis.setIsprestore((byte) 0);
                    dataHisList.add(energyDataHis);
                }
                //调用填报接口
                boolean isSave = energyDataHisService.saveEnergyDatas(dataHisList,company, meterCollectList);

                // 修改自动采集数据状态为1转存
                if(isSave){
                    paramsMap.put("maxTime",maxTime);
                    List<String> tags = autoSaveEnergyService.selectTags(paramsMap);
                    Map<String,Object> params = new HashMap<>();

                    for(String tag:tags){
                        params.clear();
                        params.put("maxTime",maxTime);
                        params.put("tag",tag);
                        params.put("status",1);
                        autoSaveEnergyService.updateStates(params);
                    }
                }
            }
        }





        logger.info("-------------自动填报自动采集数据完成----------------");
    }

}
