package com.huak.home.component;

import com.huak.health.model.HealthScoreRecord;
import com.huak.health.vo.IndexDataA;
import com.huak.health.vo.IndexTempA;
import com.huak.health.vo.WorkWarnVo;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.component<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/10/9<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

public interface HealthScoreRecordService {

    /**
     * 查询健康指数分数
     * @param params
     * @return
     */
    HealthScoreRecord getRecordById(Map<String,Object> params);

    /**
     * 保存计算后的分数
     * @param record
     * @return
     */
    int insertSelective(HealthScoreRecord record);

    /**
     * 查询健康指数 指标单位集合
     */
    List<IndexDataA> getIndexData(Map<String,Object> params);

    /**
     * 查询健康指数 室温检测
     */
    List<IndexTempA> getIndexTemp(Map<String,Object> params);

    /**
     * 查询工况报警信息
     */
    List<WorkWarnVo> getWorkWarnInfo(Map<String,Object> params);
}
