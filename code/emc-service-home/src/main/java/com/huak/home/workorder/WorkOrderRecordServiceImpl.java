package com.huak.home.workorder;

import com.huak.base.dao.DateDao;
import com.huak.workorder.dao.WorkOrderInfoDao;
import com.huak.workorder.dao.WorkOrderRecordDao;
import com.huak.workorder.dao.WorkOrderResetDao;
import com.huak.workorder.vo.WorkOrderRecordA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/10/25<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class WorkOrderRecordServiceImpl implements WorkOrderRecordService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WorkOrderInfoDao workOrderInfoDao;
    @Resource
    private WorkOrderRecordDao workOrderRecordDao;
    @Resource
    private WorkOrderResetDao workOrderResetDao;
    @Resource
    private DateDao dateDao;

    @Override
    public List<WorkOrderRecordA> selectAllRecord(String code) {

        logger.info("查询记录表中所有的记录");
        return workOrderRecordDao.selectAllRecord(code);
    }

}
