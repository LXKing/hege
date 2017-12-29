package com.huak.health;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.health.model.IndexRecord;
import com.huak.health.vo.IndexRecordA;

import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/12<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface IndexRecordService {
    int deleteByPrimaryKey(String id);

    int insert(IndexRecord record);

    int insertSelective(IndexRecord record);

    IndexRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(IndexRecord record);

    int updateByPrimaryKey(IndexRecord record);

    PageResult<IndexRecordA> queryByPage(Map<String, Object> paramsMap, Page page);

    Long checkType(Map<String, Object> paramsMap);

    Map<String, Object> selectUpdateMap(String id);
}
