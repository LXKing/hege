package com.huak.home.thiredpage;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.thiredpage<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/11<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface ThirdAnalysisService {

    /**
     * 三级页面水能耗明细查询
     */
    public List<Map<String,Object>> getWaterDhDetail(Map<String,Object> map);
    /**
     * 三级页面水能耗明细查询同期
     */
    public List<Map<String,Object>> getWaterDhDetailTq(Map<String,Object> map);

    /**
     * 三级页面水能耗明细查询 总值和同比(TQ)
     */
    public Map<String,Object> getWaterDhAndTQ(Map<String,Object> map);
    /**
     * 三级页面水能耗明细查询 总值和同比(BQ)
     */
    public Map<String,Object> getWaterDhAndBQ(Map<String,Object> map);

    /**
     * 三级页面水能耗 (源，网，站，线，户)
     */
    public Map<String,Object> getWaterDhOrg(Map<String,Object> map);
    /**
     * 三级页面水能耗 (源，网，站，线，户) 的总单耗和同比
     */
    public Map<String,Object> getWaterOrgDhAndTQ(Map<String,Object> map);
    /**
     * 热源的水单耗
     */
    public List<Map<String,Object>> getFeedDh(Map<String,Object> map);

    /**
     * 换热站的的水单耗
     */
    public List<Map<String,Object>> getStationDh(Map<String,Object> map);

    /**
     *三级页面-表单
     * sunbinbin
     * @return map
     */
    Map<String, Object> getTable(Map<String, Object> params) throws Exception;

    /**
     * 三级页面分公司耗明细查询
     */
    public List<Map<String,Object>> getFgsDhDetail(Map<String,Object> map);
    /**
     * 三级页面分公司耗明细查询
     */
    public List<Map<String,Object>> getFgsDhDetailTq(Map<String,Object> map);

    /**
     * 三级页面分公司能耗明细查询 总值和同比
     */
    public Map<String,Object> getFgsDhAndTQ(Map<String,Object> map);
    /**
     * 三级页面分公司能耗明细查询 总值和同比 (同期)
     */
    public Map<String,Object> getFgsDhAndBQ(Map<String,Object> map);

    /**
     * 三级页面分公司排名
     */
   public List<Map<String,Object>> getFgsFeedDh(Map<String,Object> map);

    /**
     * 三级页面分公司排名
     */
    public List<Map<String,Object>> getFgsStationDh(Map<String,Object> map);

    /**
     * 三级页面能耗 (源，网，站，线，户)每天的
     */
    public List<Map<String,Object>> getFgsOrgDhBQ(Map<String,Object> map);

    /**
     * 三级页面能耗 (源，网，站，线，户)每天的
     */
    public List<Map<String,Object>> getFgsOrgDhTQ(Map<String,Object> map);


    /**
     * 三级页面水能耗 (源，网，站，线，户) 的总单耗和同比
     */
    public Map<String,Object> getFgsOrgDhAndTQ(Map<String,Object> map);
    /**
     * 三级页面水能耗 (源，网，站，线，户) 的总单耗和同比
     */
    public Map<String,Object> getFgsOrgDhAndBQ(Map<String,Object> map);

    /**
     *三级页面-用能单位类型-同期计划数据表格加载
     * sunbinbin
     * @return List
     */
    Map<String,Object> getThirdTables(Map paramsMap) throws Exception;

    /**
     *三级页面-用能单位类型-个能源能耗趋势图数据加载
     * sunbinbin
     * @return Map
     */
    Map<String,Object> getUnitEnergyDetail(Map paramsMap);

    /**
     *三级页面-用能单位类型-个能源能耗排名加载
     * sunbinbin
     * @return Map
     */
    Map<String,Object> getUnitAssessments(Map paramsMap);

    /**
     *三级页面-用能单位类型-个能源能耗趋势加载
     * sunbinbin
     * @return Map
     */
    Map<String,Object> getUnitAllAssessment(Map paramsMap) throws Exception;


    /**
     *三级页面-用能单位类型-同期计划数据表格加载
     * sunbinbin
     * @return List
     */
    Map<String,Object> getThirdTableList(Map paramsMap) throws Exception;
}
