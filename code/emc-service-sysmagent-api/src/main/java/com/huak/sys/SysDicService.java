package com.huak.sys;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.model.SysDic;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/16<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface SysDicService {
    public int deleteByPrimaryKey(String id);

    public int insertSelective(SysDic record);

    public SysDic selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(SysDic record);

    public PageResult<SysDic> queryByPage(Map<String,Object> paramsMap, Page page);

    public List<Map<String,Object>> exportSysDics(Map<String, Object> paramsMap);

    public List<SysDic> queryAll(Map<String,Object> paramsMap);

    public List<Map<String, Object>> queryGroup(Map<String, Object> paramsMap);

    Long checkName(Map<String, Object> paramsMap);

    Long checkSeq(Map<String, Object> paramsMap);

    Long checkTypeUs(Map<String, Object> paramsMap);

    Long checkTypeZh(Map<String, Object> paramsMap);
}
