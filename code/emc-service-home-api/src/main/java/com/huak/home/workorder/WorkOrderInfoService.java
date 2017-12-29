package com.huak.home.workorder;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.workorder.model.WorkOrderInfo;
import com.huak.workorder.vo.WorkOrderInfoDetail;
import com.huak.workorder.vo.WorkOrderInfoRel;

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
 * Description:   工单 a派单员 b班长 c接单员  <BR>
 * Function List:  <BR>
 */
public interface WorkOrderInfoService {

    PageResult<WorkOrderInfoDetail>  selectWorkOrderInfoByMonitor(Map<String,Object> params,Page page);

    PageResult<WorkOrderInfoDetail>  selectWorkOrderInfoByCreator(Map<String,Object> params,Page page);

    PageResult<WorkOrderInfoDetail>  selectWorkOrderInfoByTakor(Map<String,Object> params,Page page);

    WorkOrderInfo selectByPrimaryKey(String id);

    WorkOrderInfo selectOneByCode(String code);

    /**
     * 保存工单
     * @param workOrder
     * @return
     */
    void saveA(WorkOrderInfo workOrder);

    /**
     * close a-b-c 关闭
     */
    void closeABC(WorkOrderInfo workOrder);
    /**
     * a-b/a-b-c
     * a派送工单b
     * @param workOrder
     * @return
     */
    void sendABorC(WorkOrderInfo workOrder,String workUrl);

    /**
     * a-b/a-b-c
     * a保存工单并派送工单b
     * @param workOrder
     * @return
     */
    void saveAndSendABorC(WorkOrderInfo workOrder,String workUrl);

    /**
     * a-b
     * b接单
     * @param workOrder
     * @return
     */
    void takingB(WorkOrderInfo workOrder);

    /**
     * a-b
     * b退单
     * @param workOrder
     * @return
     */
    void backB(WorkOrderInfo workOrder);

    /**
     * a-b
     * b完成
     * @param workOrder
     * @return
     */
    void finishB(WorkOrderInfo workOrder);

    /**
     * a-b
     * b完成a确认
     * @param workOrder
     * @return
     */
    void confirmAB(WorkOrderInfo workOrder);

    /**
     * a-b
     * b完成a重新派送
     * @param workOrder
     * @return
     */
    void resetFinishAB(WorkOrderInfo workOrder, String EmployeeId);

    /**
     * a-b
     * b退单a关闭
     * @param workOrder
     * @return
     */
    void closeAB(WorkOrderInfo workOrder);

    /**
     * a-b
     * b退单a重新派送
     * @param workOrder
     * @return
     */
    void resetBackAB(WorkOrderInfo workOrder, String EmployeeId);


    /**
     * a-c
     * a派送工单c
     * @param workOrder
     * @return
     */
    void sendAC(WorkOrderInfo workOrder,String workUrl);

    /**
     * a-c
     * a保存工单并派送工单c
     * @param workOrder
     * @return
     */
    void saveAndSendAC(WorkOrderInfo workOrder,String workUrl);

    /**
     * a-c
     * c接单
     * @param workOrder
     * @return
     */
    void takingC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c退单
     * @param workOrder
     * @return
     */
    void backC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c完成
     * @param workOrder
     * @return
     */
    void finishC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c完成a确认
     * @param workOrder
     * @return
     */
    void confirmAC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c完成a重新派送
     * @param workOrder
     * @return
     */
    void resetFinishAC(WorkOrderInfo workOrder, String EmployeeId);

    /**
     * a-c
     * c退单a关闭
     * @param workOrder
     * @return
     */
    void closeAC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c退单a重新派送
     * @param workOrder
     * @return
     */
    void resetBackAC(WorkOrderInfo workOrder, String EmployeeId);

    /**
     * a-b/a-b-c
     * ab派送工单c
     * @param workOrder
     * @return
     */
    int sendABorCRecord(WorkOrderInfo workOrder);
    /**
     * ab-c c退工单回A
     * b退单
     * @param workOrder
     * @return
     */
    int backARecord(WorkOrderInfo workOrder);
    /**
     * a-b-c
     * c完成
     * @param workOrder
     * @return
     */
    int finishCRecord(WorkOrderInfo workOrder);


    /**
     * c-a
     * c退单a重新派送
     * @param workOrder
     * @return
     */
    void resetBackABCRecord(WorkOrderInfo workOrder,String EmployeeId);

    /**
     * a-b-c
     * c完成a确认
     * @param workOrder
     * @return
     */
    int confirmACRecord(WorkOrderInfo workOrder);

    /**
     * 查询班长和接单员的信息
     * @return
     */
    List<Map<String,Object>> getEmployee(Map<String,Object> map);

    /**
     * 查询班长和接单员的角色信息
     * @return
     */
    List<Map<String,Object>> getEmployeeAndRole(Map<String,Object> map);

    /**
     * 根据Id查询班长和接单员的信息
     * @return
     */
    String getEmployeeById(Map<String,Object> map);
    /**
     * 查询班长和接单员的信息
     * @return
     */
    //List<Map<String,Object>> getEmployee();
    /**
     * 根据code查询工单详细信息
     * @param code
     * @return
     */
    WorkOrderInfoDetail getWorkInfoByCode(String code);

    /**
     * 根据code查询工单关联列表
     * @param code
     * @return
     */
    List<WorkOrderInfoRel> selectWorkRelByCode(String code);

    /**
     * 根据code查询parentcode
     * @param code
     * @return
     */
    String selectByCode(String code);
}
