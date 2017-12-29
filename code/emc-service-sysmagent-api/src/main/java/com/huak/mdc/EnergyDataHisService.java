package com.huak.mdc;

import com.huak.mdc.model.EnergyDataHis;
import com.huak.mdc.model.MeterCollect;
import com.huak.org.model.Company;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description:   能耗历史  <BR>
 * Function List:  <BR>
 */
public interface EnergyDataHisService {
    /**
     * 批量保存能耗数据
     * @param dataHisList
     */
    public boolean saveEnergyDatas(List<EnergyDataHis> dataHisList,Company company, List<MeterCollect> meterCollectList);
    /**
     * 查询虚表
     * @param
     * @return
     */
    List<MeterCollect> selectFictitiousMeters(List<String> ids);
    /**
     * 历史数据填报时，需要查询所有的虚表
     */
    List<MeterCollect> selectFictitiousMetersByCode(Map<String, Object> params);

}
