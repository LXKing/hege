package com.huak.home;

import com.huak.home.model.ConsSecond;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/5<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface ConsAnalysisService {

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
     * @param params
     * @return
     */
    public List<ConsSecond> exportAssessmentIndicators(Map<String, Object> params);

    /**
     * 查询单耗
     * @param params
     * @return
     */
	public Map<String, Object> groupDanHaoLine(Map<String, String> params) throws Exception;


    /**
     * 折线图本期数据查询，（3级页面和2级页面通用的）
     */
    public List<Map<String,Object>> getDhDetailBq(Map<String,Object> map);

    /**
     * 折线图同期数据查询，（3级页面和2级页面通用的）
     */
    public List<Map<String,Object>> getDhDetailTq(Map<String,Object> map);
}
