package com.huak.home.dao.component;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ComponentDao {

	/**
	 * 根据查询条件，查询能耗明细
	 * @param params
	 * @return
	 */
	Map<String, Object> energyDetail(Map<String, Object> params);

    /**
     * 根据参数查询能耗计划用量
     * @param params
     * @return
     */
    Map<String,Object> getplan(Map<String, Object> params);

    /**
     * 获取本采暖季的时间
     * @param paramsMap
     * @return
     */
    Map<String,Object> getCurrentSeason(Map<String, Object> paramsMap);

    /**
     * 获取上个采暖季的时间
     * @param paramsMap
     * @return
     */
    List<Map<String,Object>> getPreSeason(Map<String, Object> paramsMap);

    /**
     * 获取成本明细
     * @param params
     * @return
     */
    Map<String,Object> costDetail(Map<String, Object> params);

    /**
     * 单耗趋势
     * @param params
     * @return
     */
    List<Map<String, Object>>  getenergycomparision(Map<String, Object> params);

    /**
     * 去年单耗详情
     * @param params
     * @return
     */
    Map<String,Object> previousComparison(Map<String, Object> params);

    /**
     * 组件-近期单耗详情
     * @param paramsMap
     * @return
     */
    Map<String,Object> selectrecentDetail(Map<String, Object> paramsMap);

    /**
     * 报警-单耗接口-实现
     * @param params
     * @return
     */
    Map<String,Object> getAlarms(Map<String, Object> params);

    /**
     * 工况预警总数
     * @param params
     * @return
     */
    Double getWorkAlarmTotal(Map<String, Object> params);

    /**
     * 工况预警记录数
     * @param params
     * @return
     */
    Map<String,Object> getWorkAlarmNum(Map<String, Object> params);

    /**
     * 室温预警总数
     * @param params
     * @return
     */
    Double getTempAlarmTotal(Map<String, Object> params);

    /**
     * 室温预警记录数
     * @param params
     * @return
     */
    Map<String,Object> getTempAlarmNum(Map<String, Object> params);
}
