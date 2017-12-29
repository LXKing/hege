package com.huak.home.dao;

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
 * Date: 2017/6/8<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Repository
public interface SearchDao {
    public List<Map<String,Object>> getOrgList(String comId);

    public Map<String,Object> getSeasonOne(Map<String, Object> paramsMap);

    public List<Map<String,Object>> getSeasonAll(Map<String, Object> paramsMap);
}
