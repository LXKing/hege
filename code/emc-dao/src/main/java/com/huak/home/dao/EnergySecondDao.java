package com.huak.home.dao;

import com.huak.home.model.EnergySecond;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.dao<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/9<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Repository
public interface EnergySecondDao {
    /**
     * 查询分公司列表
     * @param params
     * @return
     */
    public List<EnergySecond> findAssessmentIndicators(Map<String, Object> params);

    /**
     * 分公司能耗占比分布图
     * @param params
     * @return
     */
    public List<Map<String, Object>> fgsEnergyRatio(Map<String, Object> params);

    /**
     * 分公司能耗趋势对比图
     * @param params
     * @return
     */
    public List<Map<String, Object>> fgsEnergyTrend(Map<String, Object> params);

    /**
     * 分公司能耗同比
     * @param params
     * @return
     */
    public List<Map<String, Object>> fgsEnergyAn(Map<String, Object> params);

    /**
     * 分公司能耗排名
     * @param params
     * @return
     */
    public List<Map<String, Object>> fgsEnergyRanking(Map<String, Object> params);

    /**
     * 导出分公司列表
     * @param params
     * @return
     */
    public List<EnergySecond> exportAssessmentIndicators(Map<String, Object> params);
}
