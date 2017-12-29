package com.huak.health;

import com.huak.health.model.AlarmRecord;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/19<BR>
 * Description:   报警记录  <BR>
 * Function List:  <BR>
 */
public interface AlarmRecordService {
    int deleteByPrimaryKey(String id);

    int insert(AlarmRecord record);

    int insertSelective(AlarmRecord record);

    AlarmRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AlarmRecord record);

    int updateByPrimaryKey(AlarmRecord record);
}
