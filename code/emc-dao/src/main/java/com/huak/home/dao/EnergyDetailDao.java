package com.huak.home.dao;

import com.huak.home.model.EnergyDetail;
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
public interface EnergyDetailDao {
    /**
     * 查询能源流明细
     * @param params
     * @return
     */
    public List<EnergyDetail>  selectEnergyDetail(Map<String, Object> params);

    /**
     * 能源用量占比分布图
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectEnergyProportion(Map<String, Object> params);

    /**
     * 分公司单耗趋势对比图
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectEnergyTrend(Map<String, Object> params);

    /**
     * 能源单耗同比
     * @param params
     * @return
     */
    public List<Map<String, Object>> selectEnergyTong(Map<String, Object> params);

    /**
     * 导出能源明细列表
     *
     * @param params
     * @return
     */
    public List<EnergyDetail> exportEnergyDetail(Map<String, Object> params);

}
