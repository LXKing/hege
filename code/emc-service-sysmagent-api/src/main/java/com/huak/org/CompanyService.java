package com.huak.org;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Company;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/23<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface CompanyService {
    int deleteByPrimaryKey(String id);

    int insertSelective(Company record);

    Company selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Company record);

    public PageResult<Company> queryByPage(Map<String,Object> paramsMap, Page page);

    public List<Map<String,Object>> exportCompanys(Map<String, Object> paramsMap);

    public List<Company> selectAllByMap(Map<String,Object> paramsMap);

    /**
     * 查询天气城市列表
     * @param paramsMap
     * @return
     */
    public List<Map<String,Object>> selectWeatherCity(Map<String, Object> paramsMap);

    /**
     * 查询上级
     * @param code
     * @return
     */
    public Map<String, Object> selectParentWeatherByCode(String code);

    Long checkTableName(Map<String, Object> paramsMap);
}
