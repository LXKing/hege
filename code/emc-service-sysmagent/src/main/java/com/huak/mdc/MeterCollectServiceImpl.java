package com.huak.mdc;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.huak.base.dao.DateDao;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.mdc.dao.MeterCollectDao;
import com.huak.mdc.model.EnergyDataHis;
import com.huak.mdc.model.MeterCollect;
import com.huak.mdc.vo.MeterCollectA;
import com.huak.mdc.vo.MeterCollectDataA;
import com.huak.org.dao.CompanyDao;
import com.huak.org.model.Company;
import com.huak.sys.dao.EnergyTypeDao;
import com.huak.sys.model.EnergyType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class MeterCollectServiceImpl implements MeterCollectService {
    @Resource
    MeterCollectDao meterCollectDao;
    @Resource
    private EnergyDataHisService energyDataHisService;
    @Resource
    private CompanyDao companyDao;
    @Resource
    DateDao dateDao;
    @Resource
    private EnergyTypeDao energyTypeDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static AtomicLong counter = new AtomicLong(0L);

    private final String  COLLECT_TIME= "collectTime";

    @Override
    public int deleteByPrimaryKey(String id) {
        return meterCollectDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(MeterCollect record) {
        return meterCollectDao.insertSelective(record);
    }

    @Override
    public int insert(MeterCollect record) {
        return meterCollectDao.insert(record);
    }

    @Override
    public MeterCollect selectByPrimaryKey(String id) {
        return meterCollectDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MeterCollect record) {
        return meterCollectDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageResult<MeterCollectA> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(meterCollectDao.selectPageByMap(paramsMap));
    }

    @Override
    public List<Map<String, Object>> exportExcel(Map<String, Object> paramsMap) throws IOException {
        return  meterCollectDao.selectAllByMap(paramsMap);
    }

    @Override
    public boolean checkName(Map<String, Object> paramsMap) {
        boolean flag=false;
        List<MeterCollect> list =  meterCollectDao.checkName(paramsMap);
        if(list.size()>0){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }

    @Override
    public boolean checkCode(Map<String, Object> paramsMap) {
        boolean flag=false;
        List<MeterCollect> list =  meterCollectDao.checkCode(paramsMap);
        if(list.size()>0){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }

    @Override
    public boolean checkNo(String serialNo) {
        boolean flag=false;
        List<MeterCollect> list =  meterCollectDao.checkSerialNo(serialNo);
        if(list.size()>0){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }

    @Override
    public List<Map<String, Object>> getUnitInfo(Map<String, Object> params) {
        return meterCollectDao.getUnitInfo(params);
    }

    /**
     * 填报数据查询
     * @param params
     * @return
     */
    @Override
    public List<Map<String, Object>> getMeterDatas(Map<String, Object> params) {
        String time = dateDao.getTime().substring(0,13);

        if(!params.containsKey(COLLECT_TIME)){
            params.put(COLLECT_TIME,time);
        }else{
            if(StringUtils.isBlank(params.get(COLLECT_TIME).toString())){
                params.put(COLLECT_TIME,time);
            }else{
                params.put(COLLECT_TIME,params.get(COLLECT_TIME).toString().substring(0,13));
            }
        }
        return meterCollectDao.getMeterDatas(params);
    }
    @Override
    public String getGeneralCode(String comId) {
        return meterCollectDao.getGeneralCode(comId);
    }

    @Override
    public boolean getFormulaByIsReal(String formula) {
        boolean flag=false;
        Integer i = meterCollectDao.getFormulaByIsReal(formula);
        if(i>0){
            flag=true;
        }
        return flag;
    }

    /**
     * 安全与后台-数据填报
     * @param jo
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public boolean fillData(JSONObject jo) {
        List<EnergyDataHis> datalist0 = new ArrayList<>();//实表
        List<String> ids = new ArrayList<>();//虚表
        List<Map<String,Object>> datas = (List<Map<String, Object>>) jo.get("data");
        String comId = (String) jo.get("comId");
        Company company = companyDao.selectByPrimaryKey(comId);
        String collectTime = "";
        for(Map data:datas){
            String flag = data.get("realFlag").toString();
           if(flag.equals("0")){
               if(StringUtils.isBlank(collectTime)){
                   collectTime = data.get(COLLECT_TIME).toString()+":00:00";
               }
               EnergyDataHis energy0 = new EnergyDataHis();
               energy0.setCollectId(data.get("id").toString());
               energy0.setCollectTime(collectTime);
               energy0.setIschange((byte) 0);
               energy0.setIsprestore((byte) 0);
               energy0.setCollectNum(Double.parseDouble(data.get("num").toString()));
               datalist0.add(energy0);
           }
            if(flag.equals("1")){
                ids.add(data.get("id").toString());
            }
        }
        try {

            List<MeterCollect> meterCollects = meterCollectDao.selectFictitiousMeters(ids);
//            if(energyDataHisService.saveEnergyDatas(datalist0,company)){
//                energyDataHisService.saveVirtualDatas(meterCollects,collectTime,company);
//            };
            return true;
        }catch (Exception e){
            logger.error("安全与后台-数据填报异常！"+e);
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> selectByMaps(HashMap<String, Object> stringObjectHashMap) {
        return meterCollectDao.selectByMaps(stringObjectHashMap);
    }

    @Override
    public PageResult<MeterCollectDataA>  queryDataLoadByPage(Map<String, Object> params, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(meterCollectDao.getDataLoad(params));

    }

    /**
     * 安全与后台-历史数据填报
     * @param jo
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public boolean addData(JSONObject jo) {
        List<Map<String, Object>> datas = (List<Map<String, Object>>) jo.get("data");
        String comId = (String) jo.get("comId");
        Company company = companyDao.selectByPrimaryKey(comId);
        for (Map data : datas) {
            String realFlag = data.get("realFlag").toString();
            String flag = data.get("flag").toString();
            Map<String,Object> _data = new HashMap<>();
            List<EnergyDataHis> datalist0 = new ArrayList<>();//实表
            if("1".equals(flag)) {
                if (realFlag.equals("0")) {
                    _data.put("code",data.get("code").toString());
                    _data.put("comId",comId);
                    EnergyDataHis energy0 = new EnergyDataHis();
                    energy0.setCollectId(data.get("id").toString());
                    energy0.setCollectTime(data.get(COLLECT_TIME).toString()+":00:00");
                    energy0.setIschange((byte) 0);
                    energy0.setIsprestore((byte) 0);
                    energy0.setCollectNum(Double.parseDouble(data.get("num").toString()));
                    datalist0.add(energy0);
                    try {
                        List<MeterCollect> meterCollects = meterCollectDao.selectFictitiousMetersByCode(_data);
//                        if(energyDataHisService.saveEnergyDatas(datalist0,company)){
//                            energyDataHisService.saveVirtualDatas(meterCollects,data.get(COLLECT_TIME).toString()+":00:00",company);
//                        };
                        return true;
                    }catch (Exception e){
                        logger.error("安全与后台-数据填报异常！"+e);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<EnergyType> getEnergyType() {
         Map<String,Object> data = new HashMap<>();
        return energyTypeDao.queryByMap(data);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectTags() {
        return meterCollectDao.selectTags();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MeterCollect> selectAutoMeters(Map<String, Object> paramsMap) {
        return meterCollectDao.selectAutoMeters(paramsMap);
    }
}
