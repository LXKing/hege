package com.huak.home.workorder;

import com.github.pagehelper.PageHelper;
import com.huak.base.dao.DateDao;
import com.huak.common.StringUtils;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.common.utils.HttpWeatherUtils;
import com.huak.workorder.dao.WorkOrderInfoDao;
import com.huak.workorder.dao.WorkOrderRecordDao;
import com.huak.workorder.dao.WorkOrderResetDao;
import com.huak.workorder.model.WorkOrderInfo;
import com.huak.workorder.model.WorkOrderRecord;
import com.huak.workorder.model.WorkOrderReset;
import com.huak.workorder.type.WorkOrderOperate;
import com.huak.workorder.type.WorkOrderStatus;
import com.huak.workorder.vo.WorkOrderInfoDetail;
import com.huak.workorder.vo.WorkOrderInfoRel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.workorder<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-10-25<BR>
 * Description:  工单   <BR>
 * Function List:  <BR>
 */
@Service
public class WorkOrderInfoServiceImpl implements WorkOrderInfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WorkOrderInfoDao workOrderInfoDao;
    @Resource
    private WorkOrderRecordDao workOrderRecordDao;
    @Resource
    private WorkOrderResetDao workOrderResetDao;
    @Resource
    private DateDao dateDao;




    /**
     * 派单员查询工单所有信息
     *
     */
    @Override
    public PageResult<WorkOrderInfoDetail> selectWorkOrderInfoByMonitor(Map<String, Object> params, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert( workOrderInfoDao.selectWorkOrderInfoByMonitor(params));
    }
    /**
     * 班长查询工单所有信息
     *
     */
    @Override
    public PageResult<WorkOrderInfoDetail> selectWorkOrderInfoByCreator(Map<String, Object> params, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert( workOrderInfoDao.selectWorkOrderInfoByCreator(params));
    }
    /**
     * 接单员查询工单所有信息
     *
     */
    @Override
    public PageResult<WorkOrderInfoDetail> selectWorkOrderInfoByTakor(Map<String, Object> params, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert( workOrderInfoDao.selectWorkOrderInfoByTakor(params));
    }

    @Override
    @Transactional(readOnly = true)
    public WorkOrderInfo selectByPrimaryKey(String id) {
        return workOrderInfoDao.selectByPrimaryKey(id);
    }


    /**
     * 生成code
     * 年月日4位编号3位随机码
     *
     * @return
     */
    private synchronized String generatorCode(String comId) {
        String date = dateDao.getDate();
        Map<String, Object> params = new HashMap<>();
        params.put("comId", comId);
        params.put("date", date);
        Integer num = workOrderInfoDao.getToDayNum(params);
        date = date.replaceAll("-", "");
        String code = date + StringUtils.cover(num.toString(), "0", 4) + StringUtils.getRandomString(3);
        return code;
    }

    /**
     * 保存工单
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void saveA(WorkOrderInfo workOrder) {
        logger.info("保存工单");
        String dateTime = dateDao.getTime();
        //生成code
        String code = generatorCode(workOrder.getComid());
        //封装工单
        workOrder.setId(UUIDGenerator.getUUID());
        workOrder.setCode(code);
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        workOrder.setCreateTime(dateTime);
        workOrderInfoDao.insertSelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b/a-b-c
     * a派送工单b
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void sendABorC(WorkOrderInfo workOrder,String workUrl) {
        logger.info("派单员派送工单给班长");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A112.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.A111.getKey());
        record.setAfterStatus(WorkOrderStatus.A112.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(workOrder.getMonitor());
        record.setDes(WorkOrderOperate.A_SEND.getValue());
        workOrderRecordDao.insertSelective(record);

        //推送工单
        final String id = workOrder.getId();
        final String  appUrl = workUrl;
        new Thread() {
            @Override
            public void run() {
                logger.info("---------开始给接口方推信息（接单员）----------");
                HttpWeatherUtils.sendWorkOrderMsg(id, appUrl);
            }
        }.start();
    }

    /**
     * a-b/a-b-c
     * a保存工单并派送工单b
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void saveAndSendABorC(WorkOrderInfo workOrder,String workUrl) {
        logger.info("派单员保存工单并派送工单给班长");
        String dateTime = dateDao.getTime();
        //生成code
        String code = generatorCode(workOrder.getComid());
        //添加关联
        if(!StringUtils.isEmpty(workOrder.getCode())){
            WorkOrderReset workOrderReset = workOrderResetDao.selectByPrimaryKey(workOrder.getCode());
            WorkOrderReset reset = new WorkOrderReset();
            if(null == workOrderReset){
                reset.setParentCode(workOrder.getCode());
                reset.setResetNum(1);
            }else{
                reset.setParentCode(workOrderReset.getParentCode());
                reset.setResetNum(workOrderReset.getResetNum()+1);
            }
            reset.setOrderCode(code);
            workOrderResetDao.insertSelective(reset);
        }

        //封装工单
        final String id = UUIDGenerator.getUUID();
        workOrder.setId(id);
        workOrder.setCode(code);
        workOrder.setStatus(WorkOrderStatus.A112.getKey());
        workOrder.setCreateTime(dateTime);
        workOrderInfoDao.insertSelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A112.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(workOrder.getMonitor());
        record.setDes(WorkOrderOperate.A_SAVE_SEND.getValue());
        workOrderRecordDao.insertSelective(record);

        //推送工单
        final String  appUrl = workUrl;
        new Thread() {
            @Override
            public void run() {
                logger.info("---------开始给接口方推信息----------");
                HttpWeatherUtils.sendWorkOrderMsg(id, appUrl);
            }
        }.start();
    }

    /**
     * a-b
     * b接单
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void takingB(WorkOrderInfo workOrder) {
        logger.info("班长接单");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.B211.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.A112.getKey());
        record.setAfterStatus(WorkOrderStatus.B211.getKey());
        record.setOpertor(workOrder.getMonitor());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.B_TAKING.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b退单
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void backB(WorkOrderInfo workOrder) {
        logger.info("班长退单");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.B212.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.A112.getKey());
        record.setAfterStatus(WorkOrderStatus.B212.getKey());
        record.setOpertor(workOrder.getMonitor());
        record.setOperateTime(dateTime);
        record.setSendee(workOrder.getCreator());
        record.setDes(WorkOrderOperate.B_BACK.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b完成
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void finishB(WorkOrderInfo workOrder) {
        logger.info("班长完成");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.B213.getKey());
        workOrder.setActualFinishTime(dateTime);
        workOrder.setFinish(workOrder.getMonitor());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.B211.getKey());
        record.setAfterStatus(WorkOrderStatus.B213.getKey());
        record.setOpertor(workOrder.getMonitor());
        record.setOperateTime(dateTime);
        record.setSendee(workOrder.getCreator());
        record.setDes(WorkOrderOperate.B_FINISH.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b完成a确认
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void confirmAB(WorkOrderInfo workOrder) {
        logger.info("班长完成派单员确认");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A141.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.B213.getKey());
        record.setAfterStatus(WorkOrderStatus.A141.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_CONFIRM.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b完成a重新派送
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void resetFinishAB(WorkOrderInfo workOrder,String EmployeeId) {
        logger.info("班长完成派单员重新派送");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.R993.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.B213.getKey());
        record.setAfterStatus(WorkOrderStatus.R993.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(EmployeeId);
        record.setDes(WorkOrderOperate.A_RESET.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b退单a关闭
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void closeAB(WorkOrderInfo workOrder) {
        logger.info("班长退单派单员关闭");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A151.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.B212.getKey());
        record.setAfterStatus(WorkOrderStatus.A151.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_CLOSE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b退单a重新派送
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void resetBackAB(WorkOrderInfo workOrder,String EmployeeId) {
        logger.info("班长退单派单员重新派送");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.R994.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.B212.getKey());
        record.setAfterStatus(WorkOrderStatus.R994.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(EmployeeId);
        record.setDes(WorkOrderOperate.A_RESET.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * a派送工单c
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void sendAC(WorkOrderInfo workOrder,String workUrl) {
        logger.info("派单员派送工单接单员");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A113.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.A111.getKey());
        record.setAfterStatus(WorkOrderStatus.A113.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(workOrder.getTakor());
        record.setDes(WorkOrderOperate.A_SEND.getValue());
        workOrderRecordDao.insertSelective(record);

        //推送工单
        final String id = workOrder.getId();
        final String  appUrl = workUrl;
        new Thread() {
            @Override
            public void run() {
                logger.info("---------开始给接口方推信息----------");
                HttpWeatherUtils.sendWorkOrderMsg(id, appUrl);
            }
        }.start();
    }

    /**
     * a-c
     * a保存工单并派送工单c
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void saveAndSendAC(WorkOrderInfo workOrder,String workUrl) {
        logger.info("派单员保存工单并派送工单接单员");
        String dateTime = dateDao.getTime();
        //生成code
        String code = generatorCode(workOrder.getComid());

        //添加关联
        if(!StringUtils.isEmpty(workOrder.getCode())){
            WorkOrderReset workOrderReset = workOrderResetDao.selectByPrimaryKey(workOrder.getCode());
            WorkOrderReset reset = new WorkOrderReset();
            if(null == workOrderReset){
                reset.setParentCode(workOrder.getCode());
                reset.setResetNum(1);
            }else{
                reset.setParentCode(workOrderReset.getParentCode());
                reset.setResetNum(workOrderReset.getResetNum()+1);
            }
            reset.setOrderCode(code);
            workOrderResetDao.insertSelective(reset);
        }

        //封装工单
        final String id = UUIDGenerator.getUUID();
        workOrder.setId(id);
        workOrder.setCode(code);
        workOrder.setStatus(WorkOrderStatus.A113.getKey());
        workOrder.setCreateTime(dateTime);
        workOrderInfoDao.insertSelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A113.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(workOrder.getTakor());
        record.setDes(WorkOrderOperate.A_SAVE_SEND.getValue());
        workOrderRecordDao.insertSelective(record);

        //推送工单
        final String  appUrl = workUrl;
        new Thread() {
            @Override
            public void run() {
                logger.info("---------开始给接口方推信息----------");
                HttpWeatherUtils.sendWorkOrderMsg(id, appUrl);
            }
        }.start();
    }

    /**
     * a-c
     * c接单
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void takingC(WorkOrderInfo workOrder) {
        logger.info("接单员接单");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.C322.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.A113.getKey());
        record.setAfterStatus(WorkOrderStatus.C322.getKey());
        record.setOpertor(workOrder.getTakor());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.C_TAKING.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c退单
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void backC(WorkOrderInfo workOrder) {
        logger.info("接单员退单");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.C321.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.A113.getKey());
        record.setAfterStatus(WorkOrderStatus.C321.getKey());
        record.setOpertor(workOrder.getTakor());
        record.setOperateTime(dateTime);
        record.setSendee(workOrder.getCreator());
        record.setDes(WorkOrderOperate.C_BACK.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c完成
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void finishC(WorkOrderInfo workOrder) {
        logger.info("接单员完成");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.C323.getKey());
        workOrder.setActualFinishTime(dateTime);
        workOrder.setFinish(workOrder.getTakor());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C322.getKey());
        record.setAfterStatus(WorkOrderStatus.C323.getKey());
        record.setOpertor(workOrder.getTakor());
        record.setOperateTime(dateTime);
        record.setSendee(workOrder.getCreator());
        record.setDes(WorkOrderOperate.C_FINISH.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c完成a确认
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void confirmAC(WorkOrderInfo workOrder) {
        logger.info("接单员完成派单员确认");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A171.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C323.getKey());
        record.setAfterStatus(WorkOrderStatus.A171.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_CONFIRM.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c完成a重新派送
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void resetFinishAC(WorkOrderInfo workOrder,String EmployeeId) {
        logger.info("接单员完成派单员重新派送");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.R995.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C323.getKey());
        record.setAfterStatus(WorkOrderStatus.R995.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(EmployeeId);
        record.setDes(WorkOrderOperate.A_RESET.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c退单a关闭
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void closeAC(WorkOrderInfo workOrder) {
        logger.info("接单员退单派单员关闭");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A161.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C321.getKey());
        record.setAfterStatus(WorkOrderStatus.A161.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_CLOSE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c退单a重新派送
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void resetBackAC(WorkOrderInfo workOrder,String EmployeeId) {
        logger.info("接单员退单派单员重新派送");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.R996.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C321.getKey());
        record.setAfterStatus(WorkOrderStatus.R996.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(EmployeeId);
        record.setDes(WorkOrderOperate.A_RESET.getValue());
        workOrderRecordDao.insertSelective(record);
    }
    @Override
    public int sendABorCRecord(WorkOrderInfo workOrder) {
        logger.info("班长发送工单到接单员");
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.B214.getKey());
        record.setOperateTime(dateDao.getTime());
        record.setOpertor(workOrder.getMonitor());
        record.setSendee(workOrder.getTakor());
        record.setAfterStatus(WorkOrderStatus.C311.getKey());
        record.setDes("班长发送工单到接单员");
        //更新当前工单状态
        workOrder.setStatus(WorkOrderStatus.C311.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);
        return workOrderRecordDao.insertSelective(record);
    }
    @Override
    public int backARecord(WorkOrderInfo workOrder) {
        logger.info("接单员退回工单到派单员");
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C311.getKey());
        record.setOperateTime(dateDao.getTime());
        record.setOpertor(workOrder.getMonitor());
        record.setSendee(workOrder.getTakor());
        record.setAfterStatus(WorkOrderStatus.C312.getKey());
        record.setDes("接单员退回工单到派单员");
        //更新当前工单状态 包括退回原因
        workOrder.setStatus(WorkOrderStatus.C312.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);
        return workOrderRecordDao.insertSelective(record);
    }

    @Override
    public int finishCRecord(WorkOrderInfo workOrder) {
        logger.info("接单员端确认并且完成");
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C311.getKey());
        record.setOperateTime(dateDao.getTime());
        record.setOpertor(workOrder.getMonitor());
        record.setSendee(workOrder.getTakor());
        record.setAfterStatus(WorkOrderStatus.C311.getKey());
        record.setDes("接单员端确认并且完成");
        //更新当前工单状态
        workOrder.setStatus(WorkOrderStatus.C311.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);
        return workOrderRecordDao.insertSelective(record);
    }

    @Override
    public int confirmACRecord(WorkOrderInfo workOrder) {
        logger.info("接端确认并且完成等待派单员确认完成且派单员确认完成");
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C311.getKey());
        record.setOperateTime(dateDao.getTime());
        record.setOpertor(workOrder.getMonitor());
        record.setSendee(workOrder.getTakor());
        record.setAfterStatus(WorkOrderStatus.A121.getKey());
        record.setDes("接端确认并且完成等待派单员确认完成且派单员确认完成");
        //更新当前工单状态
        workOrder.setStatus(WorkOrderStatus.A121.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);
        return workOrderRecordDao.insertSelective(record);
    }

    /**
     * 根据code查询工单详细信息
     *
     * @param code
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public WorkOrderInfoDetail getWorkInfoByCode(String code) {
        return workOrderInfoDao.getWorkInfoByCode(code);
    }

    /**
     * 根据code查询工单关联列表
     *
     * @param code
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<WorkOrderInfoRel> selectWorkRelByCode(String code) {
        return workOrderInfoDao.selectWorkRelByCode(code);
    }

    /**
     * 根据code查询parentcode
     *
     * @param code
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public String selectByCode(String code) {
        return workOrderResetDao.selectByCode(code);
    }

    @Override
    public void closeABC(WorkOrderInfo workOrder) {
        logger.info("派单员关闭工单");
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());//重新发送，工单号为新工单号，与旧工单号无关(状态有关)
        record.setBeforStatus(WorkOrderStatus.C312.getKey());
        record.setOperateTime(dateDao.getTime());
        record.setOpertor(workOrder.getMonitor());
        record.setSendee(workOrder.getTakor());
        record.setAfterStatus(WorkOrderStatus.A131.getKey());
        record.setDes("派单员关闭工单");
        //更新当前工单状态  工单号为新工单号 (状态有关，延续之前状态)
        workOrder.setStatus(WorkOrderStatus.A131.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);
        workOrderRecordDao.insertSelective(record);
    }

    @Override
    public List<Map<String, Object>> getEmployee(Map<String,Object> map) {
        return workOrderInfoDao.getEmployee(map);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getEmployeeAndRole(Map<String,Object> map) {
        return workOrderInfoDao.getEmployeeAndRole(map);
    }

    @Override
    public String getEmployeeById(Map<String,Object> map) {
        return workOrderInfoDao.getEmployeeById(map);
    };
    @Override
    public void resetBackABCRecord(WorkOrderInfo workOrder,String EmployeeId) {
        logger.info("接单员退单派单员重新派送");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.R996.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C321.getKey());
        record.setAfterStatus(WorkOrderStatus.R996.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(EmployeeId);
        record.setDes(WorkOrderOperate.A_RESET.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    @Override
    public WorkOrderInfo selectOneByCode(String code) {
        return workOrderInfoDao.selectOneByCode(code);
    }
}
