package com.huak.home;

import com.huak.home.dao.EnergyDetailDao;
import com.huak.home.model.EnergyDetail;
import com.huak.org.dao.TopAllDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/25<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Service
public class FrameServiceImpl implements FrameService {

    @Resource
    private TopAllDao topAllDao;
    @Resource
    private EnergyDetailDao detailDao;

    @Override
    @Transactional(readOnly = true)
    public String selectTopEtotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopEtotalByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public String selectCarbonTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectCarbonTotalByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> selectCostTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectCostTotalByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public String selectYardageByMap(Map<String, String> paramsMap) {
        return topAllDao.selectYardageByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public String selectPriceAreaByMap(Map<String, String> paramsMap) {
        return topAllDao.selectPriceAreaByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public String selectFeedTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopFeedTotalByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public String selectTopFeedCarbonTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopFeedCarbonTotalByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> selectFeedCostTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectFeedCostTotalByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public String selectGetNetLengh(Map<String, String> paramsMap) {
        return topAllDao.selectGetNetLengh(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> selectGetNetCost(Map<String, String> paramsMap) {
        return topAllDao.selectGetNetCost(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public String selectTopStationTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopStationTotalByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public String selectTopStationCarbonTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopStationCarbonTotalByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> selectStationCostTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectStationCostTotalByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public String selectGetLineLengh(Map<String, String> paramsMap) {
        return topAllDao.selectGetLineLengh(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> selectGetLineCost(Map<String, String> paramsMap) {
        return topAllDao.selectGetLineCost(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public String selectTopRoomHglByMap(Map<String, String> paramsMap) {
        Float f=new Float(0.00);
        String total = topAllDao.selectTopRoomTotalHglByMap(paramsMap);
        String num1 = topAllDao.selectTopRoomNum1HglByMap(paramsMap);
        if(total!=null&&num1!=null){
             f = (float)Integer.valueOf(num1)/(float)Integer.valueOf(total)*100;
        }
        return f+"%";
    }

    @Override
    @Transactional(readOnly = true)
    public String selectTopRoomTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopRoomTotalByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getTopRoomCostByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopRoomCostByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnergyDetail> selectEnergyDetail(Map<String, Object> paramsMap) {
        return detailDao.selectEnergyDetail(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectEnergyProportion(Map<String, Object> params) {
        return detailDao.selectEnergyProportion(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectEnergyTrend(Map<String, Object> params) {
        return detailDao.selectEnergyTrend(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectEnergyTong(Map<String, Object> params) {
        return detailDao.selectEnergyTong(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnergyDetail> exportEnergyDetail(Map<String, Object> params) {
        return detailDao.exportEnergyDetail(params);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> selectTopRoomCostByMap(Map<String, String> paramsMap) {
        return this.topAllDao.selectTopRoomCostByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Double selectTopAllTotalByEnergy(Map<String, String> paramsMap) {
        return this.topAllDao.selectTopAllTotalByEnergy(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Double selectTopAllTotal(Map<String, String> paramsMap) {
        return this.topAllDao.selectTopAllTotal(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Double selectTopFeedCarbonTotalByEnergy(Map<String, String> paramsMap) {
        return topAllDao.selectTopFeedCarbonTotalByEnergy(paramsMap);
    }

    @Override

    @Transactional(readOnly = true)
    public Double selectTopStationCarbonTotalByEnergy(Map<String, String> paramsMap) {
        return topAllDao.selectTopStationCarbonTotalByEnergy(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Double selectTopNetCarbonTotalByEnergy(Map<String, String> paramsMap) {
        return this.topAllDao.selectTopNetCarbonTotalByEnergy(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Double selectTopLineCarbonTotalByEnergy(Map<String, String> paramsMap) {
        return this.topAllDao.selectTopLineCarbonTotalByEnergy(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Double selectTopRoomCarbonTotalByEnergy(Map<String, String> paramsMap) {
        return this.topAllDao.selectTopRoomCarbonTotalByEnergy(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getEachManagerCost(Map<String, String> paramsMap) {
        return this.topAllDao.getEachManagerCost(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getEachDeviceCost(Map<String, String> paramsMap) {
        return this.topAllDao.getEachDeviceCost(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getEachOtherCost(Map<String, String> paramsMap) {
        return this.topAllDao.getEachOtherCost(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getEachLaborCost(Map<String, String> paramsMap) {
        return this.topAllDao.getEachLaborCost(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getEachEnergyCost(Map<String, String> paramsMap) {
        return this.topAllDao.getEachEnergyCost(paramsMap);
    }


}
