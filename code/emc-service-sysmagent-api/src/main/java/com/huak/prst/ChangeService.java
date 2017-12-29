package com.huak.prst;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.mdc.model.RecordChange;
import com.huak.mdc.vo.RecordChangeA;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.prst<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/31<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface ChangeService {
    int deleteByPrimaryKey(String id);

    int insert(RecordChange record);

    int insertSelective(RecordChange record);

    RecordChange selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RecordChange record);

    int updateByPrimaryKey(RecordChange record);

    /**
     * 换表记录分页查询
     */
    public PageResult<RecordChangeA> queryByPage(Map<String,Object> paramsMap, Page page);

    /**
     * 导出数据信息
     */
    public List<Map<String,Object>> exportChange(Map<String, Object> paramsMap);

    int insert1(RecordChange recordChange);

}
