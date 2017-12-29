package com.huak.home;

import com.huak.home.model.EnergyDetail;

import java.util.List;
import java.util.Map;
/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/16<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface FrameService {


    public String selectTopEtotalByMap(Map<String, String> paramsMap);

    public String selectCarbonTotalByMap(Map<String, String> paramsMap);

    public Map<String, Object> selectCostTotalByMap(Map<String, String> paramsMap);

    public String selectYardageByMap(Map<String, String> paramsMap);

    public String selectPriceAreaByMap(Map<String, String> paramsMap);

    public String selectFeedTotalByMap(Map<String, String> paramsMap);

    public String selectTopFeedCarbonTotalByMap(Map<String, String> paramsMap);

    public Map<String, Object> selectFeedCostTotalByMap(Map<String, String> paramsMap);

    public String selectGetNetLengh(Map<String, String> paramsMap);

    public Map<String, Object> selectGetNetCost(Map<String, String> paramsMap);

    public String selectTopStationTotalByMap(Map<String, String> paramsMap);

    public String selectTopStationCarbonTotalByMap(Map<String, String> paramsMap);

    public Map<String, Object> selectStationCostTotalByMap(Map<String, String> paramsMap);

    public String selectGetLineLengh(Map<String, String> paramsMap);

    public Map<String, Object> selectGetLineCost(Map<String, String> paramsMap);

    public String selectTopRoomTotalByMap(Map<String, String> paramsMap);

    public String selectTopRoomHglByMap(Map<String, String> paramsMap);

    public Map<String, Object> getTopRoomCostByMap(Map<String, String> paramsMap);

    public List<EnergyDetail> selectEnergyDetail(Map<String, Object> paramsMap);

    public List<Map<String, Object>> selectEnergyProportion(Map<String, Object> params);

    public List<Map<String, Object>> selectEnergyTrend(Map<String, Object> params);

    public List<Map<String, Object>> selectEnergyTong(Map<String, Object> params);

    public List<EnergyDetail> exportEnergyDetail(Map<String, Object> params);

    public Map<String, Object> selectTopRoomCostByMap(Map<String, String> paramsMap);
    /***
     *
     *能源总成本
     */
    public Double selectTopAllTotalByEnergy(Map<String, String> paramsMap);

    /***
     *
     *g管理+设备+其他+人工总成本
     */
    public Double selectTopAllTotal(Map<String, String> paramsMap);
    /***
     *
     *供热源能源总成本
     */
    public Double selectTopFeedCarbonTotalByEnergy(Map<String, String> paramsMap);

    /***
     *
     *换热站能源总成本
     */
    public Double selectTopStationCarbonTotalByEnergy(Map<String, String> paramsMap);



    /***
     *
     *网能源总成本
     */
    public Double selectTopNetCarbonTotalByEnergy(Map<String, String> paramsMap);

    /***
     *
     *线能源总成本
     */
    public Double selectTopLineCarbonTotalByEnergy(Map<String, String> paramsMap);

    /***
     *
     *民户能源总成本
     */
    public Double selectTopRoomCarbonTotalByEnergy(Map<String, String> paramsMap);

    /***
     *
     *管理总成本
     */
    public Double getEachManagerCost(Map<String, String> paramsMap);

    /***
     *
     *设备总成本
     */
    public Double getEachDeviceCost(Map<String, String> paramsMap);

    /***
     *
     *其他总成本
     */
    public Double getEachOtherCost(Map<String, String> paramsMap);

    /***
     *
     *人工总成本
     */
    public Double getEachLaborCost(Map<String, String> paramsMap);
    /***
     *
     *能源总成本
     */
    public Double getEachEnergyCost(Map<String, String> paramsMap);
}
