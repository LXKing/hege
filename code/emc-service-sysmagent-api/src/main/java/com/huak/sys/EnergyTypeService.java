package com.huak.sys;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.model.EnergyType;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/1<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface EnergyTypeService {
    public int deleteByPrimaryKey(String id);

    public int insertSelective(EnergyType record);

    public EnergyType selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(EnergyType record);

    public PageResult<EnergyType> queryByPage(Map<String,Object> paramsMap, Page page);

    public List<Map<String,Object>> exportEnergyTypes(Map<String, Object> paramsMap);

    public List<EnergyType> queryByMap(Map<String,Object> paramsMap);

    public Long checkNameZh(Map<String, Object> paramsMap);

    public Long checkNameEn(Map<String, Object> paramsMap);
}
