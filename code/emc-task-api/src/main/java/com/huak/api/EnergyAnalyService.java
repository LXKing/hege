package com.huak.api;

/*import com.huak.org.model.Company;
import com.huak.task.model.EnergyAnalySisdata;*/

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.task<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/13<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface EnergyAnalyService {


    /**
     * 查询能源数据是否存在
     */
    public boolean selectEnergyAnalySisdata(String unitid);

    /**
     * 向数据库插入能数据
     *
     */
    /*void insetEnergyAnalySisdata(EnergyAnalySisdata record);
*/
    /**
     * 计算水的每小时用量
     */
    public void selectInsertIntoFinalDataHourById(Map<String,Object> map);

    /**
     * 计算电的每小时用量
     * @param id
     */
    public void selectPowerInsertFinalDataById(Map<String,Object> map);

    /**
     * 计算每小时热的用量
     */
    public void selectHeatInsertFinalDataById(Map<String,Object> map);

    /**
     * 计算每小时气的热量
     */
    public void selectQiInsertFinalDataById(Map<String,Object> map);

    /**
     * 查询标煤
     */
    public String selectCoal(Map<String,Object> map);

    /**
     * 查询公司
     * @param id
     * @return
     */
   /* public Company selectCompanyByKey(String id);*/
}