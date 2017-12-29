package com.huak.org;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.vo.Secondnet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/17<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Service
public interface SecondnetService {

    public int deleteByPrimaryKey(String id);

    public int insertSelective(Secondnet record);

    public int insert(Secondnet record);

    public Secondnet selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(Secondnet record);

    public PageResult<Secondnet> queryByPage(Map<String, Object> paramsMap, Page page);

    public List<Map<String,Object>> exportLines(Map<String, Object> paramsMap);

    public List<Secondnet> selectLineAll(Map<String, Object> paramsMap);

    public boolean checkNetName(Map<String,Object> paramsMap);

    public boolean checkNetCode(Map<String,Object> paramsMap);
}
