package com.huak.home.dao.cost;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/27 0027.
 */
@Repository
public interface CostDao {

    /**
     * 根据查询条件，查询分公司源 中的能源成本
     * @param params
     * @return
     */
    String  SourceEnergyCost(Map<String, Object> params);


    /**
     * 根据查询条件，查询分公司源 中的今年其他成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  SourceOtherCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司源 中的今年管理成本
     * @param params
     * @return
     */
    List<Map<String ,Object>> SourceManagerCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司源 中的今年人工费成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  SourceArtificialCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司源 中的今年设备费成本
     * @param params
     * @return
     */
    List<Map<String, Object>> SourceDevicelCost(Map<String, Object> params);




    /**
     * 根据查询条件，查询分公司网中的今年其他成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  NetworkOtherCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司网 中的今年管理成本
     * @param params
     * @return
     */
    List<Map<String ,Object>> NetworkManagerCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司网 中的今年人工费成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  NetworkArtificialCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司网 中的今年设备费成本
     * @param params
     * @return
     */
    List<Map<String, Object>> NetworkDevicelCost(Map<String, Object> params);



    /**
     * 根据查询条件，查询分公司站中的今年其他成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  StationOtherCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司站 中的今年管理成本
     * @param params
     * @return
     */
    List<Map<String ,Object>> StationManagerCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司站 中的今年人工费成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  StationArtificialCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司站中的今年设备费成本
     * @param params
     * @return
     */
    List<Map<String, Object>> StationDevicelCost(Map<String, Object> params);






    /**
     * 根据查询条件，查询分公司线中的今年其他成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  LineOtherCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司线 中的今年管理成本
     * @param params
     * @return
     */
    List<Map<String ,Object>> LineManagerCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司线 中的今年人工费成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  LineArtificialCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司线中的今年设备费成本
     * @param params
     * @return
     */
    List<Map<String, Object>> LineDevicelCost(Map<String, Object> params);





    /**
     * 根据查询条件，查询分公司户中的今年其他成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  RoomOtherCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司户 中的今年管理成本
     * @param params
     * @return
     */
    List<Map<String ,Object>> RoomManagerCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司户 中的今年人工费成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  RoomArtificialCost(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司户中的今年设备费成本
     * @param params
     * @return
     */
    List<Map<String, Object>> RoomDevicelCost(Map<String, Object> params);





    /////














    /**
     * 根据查询条件，查询分公司源 中的去年其他成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  SourceOtherCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司源 中的去年管理成本
     * @param params
     * @return
     */
    List<Map<String ,Object>> SourceManagerCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司源 中的去年人工费成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  SourceArtificialCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司源 中的去年设备费成本
     * @param params
     * @return
     */
    List<Map<String, Object>> SourceDevicelCostLastyear(Map<String, Object> params);




    /**
     * 根据查询条件，查询分公司网中的去年其他成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  NetworkOtherCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司网 中的去年管理成本
     * @param params
     * @return
     */
    List<Map<String ,Object>> NetworkManagerCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司网 中的去年人工费成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  NetworkArtificialCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司网 中的去年设备费成本
     * @param params
     * @return
     */
    List<Map<String, Object>> NetworkDevicelCostLastyear(Map<String, Object> params);



    /**
     * 根据查询条件，查询分公司站中的去年其他成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  StationOtherCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司站 中的去年管理成本
     * @param params
     * @return
     */
    List<Map<String ,Object>> StationManagerCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司站 中的去年人工费成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  StationArtificialCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司站中的去年设备费成本
     * @param params
     * @return
     */
    List<Map<String, Object>> StationDevicelCostLastyear(Map<String, Object> params);






    /**
     * 根据查询条件，查询分公司线中的去年其他成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  LineOtherCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司线 中的去年管理成本
     * @param params
     * @return
     */
    List<Map<String ,Object>> LineManagerCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司线 中的去年人工费成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  LineArtificialCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司线中的去年设备费成本
     * @param params
     * @return
     */
    List<Map<String, Object>> LineDevicelCostLastyear(Map<String, Object> params);





    /**
     * 根据查询条件，查询分公司户中的去年其他成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  RoomOtherCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司户 中的去年管理成本
     * @param params
     * @return
     */
    List<Map<String ,Object>> RoomManagerCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司户 中的去年人工费成本
     * @param params
     * @return
     */
    List<Map<String, Object>>  RoomArtificialCostLastyear(Map<String, Object> params);

    /**
     * 根据查询条件，查询分公司户中的去年设备费成本
     * @param params
     * @return
     */
    List<Map<String, Object>> RoomDevicelCostLastyear(Map<String, Object> params);


}
