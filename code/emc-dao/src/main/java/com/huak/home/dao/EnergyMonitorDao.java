package com.huak.home.dao;

import com.huak.home.model.EnergyMonitor;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnergyMonitorDao {

    /**
     * 查询本期标煤时间折线
     * @param params
     * @return
     */
    List<Map<String, Object>> selectBmBqLine(Map<String, String> params);
    /**
     * 查询同期标煤时间折线
     * @param params
     * @return
     */
    List<Map<String, Object>> selectBmTqLine(Map<String, String> params);
    /**
     * 查询本期标煤总量
     * @param params
     * @return
     */
    Double selectBmBqTotal(Map<String, String> params);
    /**
     * 查询同期标煤总量
     * @param params
     * @return
     */
    Double selectBmTqTotal(Map<String, String> params);

    /**
     * 查询本期用量时间折线
     * @param params
     * @return
     */
    List<Map<String, Object>> selectYlBqLine(Map<String, String> params);
    /**
     * 查询同期用量时间折线
     * @param params
     * @return
     */
    List<Map<String, Object>> selectYlTqLine(Map<String, String> params);
    /**
     * 查询本期用量总量
     * @param params
     * @return
     */
    Map<String, Object> selectYlBqTotal(Map<String, String> params);
    /**
     * 查询同期用量总量
     * @param params
     * @return
     */
    Map<String, Object> selectYlTqTotal(Map<String, String> params);


	/**
	 * 根据查询条件，查询相应数据，返回List<Map>格式
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> groupEnergy(Map<String, String> params);

    void insertByPrimaryKeySelective(EnergyMonitor energyMonitor);

    /**
     * 查询能源流明细
     * @param params
     * @return
     */
	List<Map<String, Object>> energyFlowTable(Map<String, String> params);

	/**
	 * 查询能源流占比分布图
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> energyFlowPie(Map<String, String> params);

	/**
	 * 查询能源流趋势对比图
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> energyFlowLine(Map<String, String> params);

	/**
	 * 查询能源流同比
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> energyFlowBar(Map<String, String> params);

	/**
	 * 导出能源流明细
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> exportEnergyFlowDetail(Map<String, String> params);

}
