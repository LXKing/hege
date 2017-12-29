package com.huak.task.dao;

import com.huak.health.vo.AlarmDataVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.task.dao<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/26<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Repository
public interface DiacrisisDao {

    List<AlarmDataVo> getWorkWarnInfo();

}
