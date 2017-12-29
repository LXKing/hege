package com.huak.home.dao;

import com.huak.home.model.ConsSecond;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.energy.dao<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/26<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Repository
public interface ConsAnalysisDao {
    /**
     * 查询分公司列表
     * @param params
     * @return
     */
    public List<ConsSecond> findAssessmentIndicators(Map<String, Object> params);

    /**
     * 分公司单耗占比分布图
     * @param params
     * @return
     */
    public List<Map<String, Object>> fgsEnergyRatio(Map<String, Object> params);

    /**
     * 分公司单耗趋势对比图
     * @param params
     * @return
     */
    public List<Map<String, Object>> fgsEnergyTrend(Map<String, Object> params);

    /**
     * 分公司单耗同比
     * @param params
     * @return
     */
    public List<Map<String, Object>> fgsEnergyAn(Map<String, Object> params);

    /**
     * 分公司单耗排名
     * @param params
     * @return
     */
    public List<Map<String, Object>> fgsEnergyRanking(Map<String, Object> params);

    /**
     * 导出公司单耗列表
     *
     * @param params
     * @return
     */
    public List<ConsSecond> exportAssessmentIndicators(Map<String, Object> params);

    /**
     * 查询单耗
     * @param params
     * @return
     */
	public List<Map<String, Object>> groupDanHao(Map<String, String> params);

	/**
	 * 查询单耗同比数据
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> groupDanHaoTotal(Map<String, String> params);
	
}
