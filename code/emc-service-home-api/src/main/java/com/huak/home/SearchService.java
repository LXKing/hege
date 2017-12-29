package com.huak.home;

import com.alibaba.fastjson.JSONObject;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/8<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface SearchService {
    java.util.List<java.util.Map<String, Object>> getOrgList(String id);

    JSONObject getYearDate();

    public JSONObject getSeason(String id);
}
