package com.huak.task.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.task.dao<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-11-17<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Repository
public interface AutoSaveDataDao {
    String getMaxDataTime(Map<String, Object> params);

    List<Map<String,Object>> selectAutoDataByTime(Map<String, Object> params);

    List<String> selectTags(Map<String, Object> paramsMap);

    void updateStates(Map<String, Object> params);
}
