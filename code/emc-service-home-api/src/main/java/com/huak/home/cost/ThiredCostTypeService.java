package com.huak.home.cost;

import java.util.Map;

/**
 * Created by pc on 2017/12/14.
 */
public interface ThiredCostTypeService {

    /**
     *三级页面-用能单位类型-各个成本对比数据加载
     * sunbinbin
     * @return List
     */
    Map<String,Object> getUnitCostDetail(Map<String, Object> paramsMap);
    /**
     *三级页面-源、网、站、线、户的各个成本类型的趋势图数据加载
     * sunbinbin
     * @return List
     */
    Map<String, Object> getEachStationCostDetail(Map<String, Object> params) throws Exception;

    /**
     *三级页面-成本类型的各自成本趋势图数据加载（人工，管理，其他，设备）     点击成本
     * sunbinbin
     * @return List  getCostTypeDetail
     */
    Map<String,Object> getCostTypeDetail(Map<String, Object> paramsMap) throws Exception;


    /**
     *三级页面-源网站线户-2016~2017年度成本排名  点击源网站线户
     * sunbinbin
     * @return List
     */
    Map<String,Object> ThirdUnitCostDetail(Map<String, Object> paramsMap) throws Exception;


    /**
     *   三级页面-源网站线户-管理（能源，其他，设备，人工）总成本趋势
     * sunbinbin
     * @return List  thirdUnitCostTrend
     */
    Map<String,Object> thirdUnitCostTrend(Map<String, Object> paramsMap) throws Exception;

    /**
     * 2016~2017年度成本情况对比 (单位: T)
     * sunbinbin
     * @return string
     */
    Map<String,Object> thirdUnitSituationCostContrast(Map<String, Object> paramsMap) throws Exception;

    /**
     *三级页面-点击成本类型-换热站/热源成本量排名
     * sunbinbin
     * @return string
     */
    Map<String,Object> thridCostTypeStationRanking(Map<String, Object> paramsMap) throws Exception;
    /**
     *三级页面-点击能源成本类型-换热站/热源成本量排名 能源
     * sunbinbin
     * @return string
     */
    Map<String,Object> thridCostTypeStationEnergyRanking(Map<String, Object> paramsMap) throws Exception;

}
