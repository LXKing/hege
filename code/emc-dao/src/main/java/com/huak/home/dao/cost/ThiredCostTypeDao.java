package com.huak.home.dao.cost;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2017/12/14.
 */
@Resource
public interface ThiredCostTypeDao {
    /**
     *三级页面-用能单位类型-各个成本对比数据加载
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> getUnitCostDetail(Map<String, Object> paramsMap);

    /**
     *三级页面-源网站线户-各个相对应成本对比数据加载（人工，管理，其他，设备）
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> getEachStationCostDetail(Map<String, Object> paramsMap);

    /**
     *三级页面-源网站线户-各个相对应成本对比数据加载（能源成本）
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> getEachStationEnergyCostDetail(Map<String, Object> paramsMap);


    /**
     *三级页面-成本类型的各自成本趋势图数据加载（人工，管理，其他，设备）     点击成本
     * sunbinbin
     * @return List  getCostTypeDetail
     */
    List<Map<String,Object>> getCostTypeDetail(Map<String, Object> paramsMap);


    /**
     *三级页面-成本类型的各自成本趋势图数据加载（人工，管理，其他，设备）     点击成本
     * sunbinbin
     * @return List  getCostTypeDetail
     */
    List<Map<String,Object>> getCostTypeEnergyDetail(Map<String, Object> paramsMap);

    /**
     *  三级页面-源网站线户-2016~2017年度成本排名  点击源网站线户
     *
     * sunbinbin
     * @return List  getCostTypeDetail
     */
    List<Map<String,Object>> ThirdUnitCostEnertyDetail(Map<String, Object> paramsMap);
    /**
     *  三级页面-源网站线户-2016~2017年度成本情况对比
     *
     * sunbinbin
     * @return List  getCostTypeDetail
     */

    List<Map<String,Object>> ThirdUnitCostDetail(Map<String, Object> paramsMap);



    /**
     *   三级页面-源网站线户-管理（能源，其他，设备，人工）总成本趋势
     * sunbinbin
     * @return List  thirdUnitCostTrend
     */
    List<Map<String,Object>> thirdUnitCostTrend(Map<String, Object> paramsMap) throws Exception;

    /**
     *   三级页面-源网站线户-能源总成本趋势
     * sunbinbin
     * @return List  thirdUnitCostTrend
     */
    List< Map<String,Object>> thirdUnitCostEnergyTrend(Map<String, Object> paramsMap) throws Exception;

    /**
     * 2016~2017年度成本情况对比 (单位: T)（能源，其他，设备，人工）
     * sunbinbin
     * @return string
     */
    List< Map<String,Object>> thirdUnitSituationCostContrast(Map<String, Object> paramsMap) throws Exception;
    /**
     * 2016~2017年度成本情况对比 (单位: T)（能源)
     * sunbinbin
     * @return string
     */
    List< Map<String,Object>> thirdUnitSituationCostEnergyContrast(Map<String, Object> paramsMap) throws Exception;

     /*
    * 各个分公司的换热站成本排名 ，包含管理费，其他费用，人工费，设备费用
    * **/

    List<Map<String,Object>> StationRanking(Map<String, Object> paramsMap);


    /*
    * 点击成本类型的换热站成本排名 ，能源费用
    * **/

    List<Map<String,Object>> StationRankingEnergy(Map<String, Object> paramsMap);



}
