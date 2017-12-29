package com.huak.home.dao.thiredpage;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.dao.thiredpage<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/11<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Repository
public interface ThirdAnalysisDao {

    /**
     * 计算折线图本期和同期数据
     * @param map
     * @return
     */
    List<Map<String,Object>> getWaterDhDetail(Map<String,Object> map);
    List<Map<String,Object>> getWaterDhDetailTq(Map<String,Object> map);

    /**
     *同期总值和同期比的计算
     * @param map
     * @return
     */
    Map<String,Object> getTotalAndBq(Map<String,Object> map);
    Map<String,Object> getTotalAndTq(Map<String,Object> map);

    List<Map<String,Object>> getWaterDhOrg(Map<String,Object> map);

    /**
     * 分公司单耗详细
     * @param map
     * @return
     */
    List<Map<String,Object>> getFgsDh(Map<String,Object> map);
    List<Map<String,Object>> getFgsDhTq(Map<String,Object> map);
    /**
     *分公司同期总值和同期比的计算
     * @param map
     * @return
     */

    /**
     *分公司同期总值和同期比的计算
     * @param map
     * @return
     */
    Map<String,Object> getFgsZdhAndBq(Map<String,Object> map);
    Map<String,Object> getFgsZdhAndTq(Map<String,Object> map);

    /**
     * 分公司（水，电，气，热，煤）
     * @param map
     * @return
     */
    Map<String,Object> getFgsOrgAndBQ(Map<String,Object> map);
    Map<String,Object> getFgsOrgAndTQ(Map<String,Object> map);

    /**
     * 分公司（水，电，气，热，煤） 每天的
     * @param map
     * @return
     */
    List<Map<String,Object>> getFgsOrgDhTQ(Map<String,Object> map);
    List<Map<String,Object>> getFgsOrgDhBQ(Map<String,Object> map);


    Map<String,Object> getTotalOrgAndTq(Map<String,Object> map);

    List<Map<String,Object>> getFeedDh(Map<String,Object> map);

    List<Map<String,Object>> getStationDh(Map<String,Object> map);

    List<Map<String,Object>> getTables(Map<String, Object> params);

    List<Map<String,Object>> getTotal(Map<String, Object> params);

    List<Map<String,Object>> getFgsFeedDh(Map<String,Object> map);

    List<Map<String,Object>> getFgsStationDh(Map<String,Object> map);

    List<Map<String,Object>> getThirdTables(Map paramsMap);

    List<Map<String,Object>> getThirdTotals(Map paramsMap);


    List<Map<String,Object>> getUnitsEnergyDetail(Map paramsMap);

    List<Map<String,Object>> getUnitAllAssessment(Map params);

    List<Map<String,Object>> getUnitsAssessment(Map paramsMap);

    List<Map<String,Object>> getThirdUnitTables(Map paramsMap);

    List<Map<String,Object>> getThirdUnitTotals(Map paramsMap);
}
