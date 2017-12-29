package com.huak.org.dao;


import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface TopAllDao {

    public String selectTopEtotalByMap(Map<String, String> paramsMap);

    public String selectCarbonTotalByMap(Map<String, String> paramsMap);

    public Map<String, Object> selectCostTotalByMap(Map<String, String> paramsMap);

    public String selectYardageByMap(Map<String, String> paramsMap);

    public String selectPriceAreaByMap(Map<String, String> paramsMap);

    public String selectTopFeedTotalByMap(Map<String, String> paramsMap);

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

    public String selectTopRoomNum1HglByMap(Map<String, String> paramsMap);

    public String selectTopRoomTotalHglByMap(Map<String, String> paramsMap);

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
     *网能源总成本
     */
    public Double selectTopNetCarbonTotalByEnergy(Map<String, String> paramsMap);


    /***
     *
     *换热站能源总成本
     */
    public Double selectTopStationCarbonTotalByEnergy(Map<String, String> paramsMap);


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