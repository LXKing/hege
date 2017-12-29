package com.huak.auth;

import com.huak.auth.model.Func;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.auth<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/12<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface FuncService {
    int deleteByPrimaryKey(String id);

    int insertSelective(Func record);

    Func selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Func record);

    List<Func> selectAllByMap(Map<String,Object> paramsMap);

    Long checkUName(Map<String, String> paramsMap);

    Long checkName(Map<String, String> paramsMap);

    Long checkSearch(Map<String, String> paramsMap);
}
