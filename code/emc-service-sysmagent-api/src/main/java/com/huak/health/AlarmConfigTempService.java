package com.huak.health;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.health.model.AlarmConfig;
import com.huak.health.model.AlarmConfigTemp;
import com.huak.health.vo.AlarmConfigTempVo;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/19<BR>
 * Description: 报警配置    <BR>
 * Function List:  <BR>
 */
public interface AlarmConfigTempService {
    int deleteByPrimaryKey(String id);

    int insert(AlarmConfigTemp record);

    int insertSelective(AlarmConfigTemp record);

    AlarmConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AlarmConfigTemp record);

    int updateByPrimaryKey(AlarmConfigTemp record);

    PageResult<AlarmConfigTempVo> queryByPage(Map<String, Object> paramsMap, Page page);

    AlarmConfigTemp selectUpdateMap(String id);

    List<Map<String,Object>> exportTempConfig(Map<String, Object> paramsMap);

    Long checkUnitName(Map<String, Object> paramsMap);
}
