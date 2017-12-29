package com.huak.sys;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.model.IndexType;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/11<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface IndexTypeService {
    int deleteByPrimaryKey(String id);

    int insert(IndexType record);

    int insertSelective(IndexType record);

    IndexType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(IndexType record);

    int updateByPrimaryKey(IndexType record);

    PageResult<IndexType> queryByPage(Map<String, Object> paramsMap, Page page);

    List<Map<String, Object>> exportIndexTypes(Map<String, Object> paramsMap);

    Long checkName(Map<String, Object> paramsMap);

    List<IndexType> queryByMap(Map<String, Object> paramsMap);

    List<Map<String, Object>> selectByMap(Map<String, Object> paramsMap);
}
