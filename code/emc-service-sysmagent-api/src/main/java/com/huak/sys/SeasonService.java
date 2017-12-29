package com.huak.sys;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.season.model.Season;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.reason<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/4/24<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface SeasonService {

    public Season selectByPrimaryKey(String id);

    public int delete(String id);

    public int insert(Season season);

    public PageResult<Season> queryByPage(Map<String,Object> paramsMap, Page page);

    boolean checkName(Map<String,Object> map);

    public int updateByPrimaryKeySelective(Season record);

    public boolean checkTime(Map<String,Object> paramsMap);

    public String getNowTime();

    public List<Map<String,Object>> exportSeason(Map<String,Object> paramsMap);
}
