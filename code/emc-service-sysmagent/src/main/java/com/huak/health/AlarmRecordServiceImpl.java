package com.huak.health;

import com.huak.health.model.AlarmRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/19<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class AlarmRecordServiceImpl implements AlarmRecordService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        logger.info("删除报警记录");
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(AlarmRecord record) {
        logger.info("新增报警记录");
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(AlarmRecord record) {
        logger.info("新增报警记录");
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public AlarmRecord selectByPrimaryKey(String id) {
        logger.info("查询报警记录");
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(AlarmRecord record) {
        logger.info("更新报警记录");
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(AlarmRecord record) {
        logger.info("更新报警记录");
        return 0;
    }
}
