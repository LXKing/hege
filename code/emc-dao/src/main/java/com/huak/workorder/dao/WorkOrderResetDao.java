package com.huak.workorder.dao;


import com.huak.workorder.model.WorkOrderReset;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderResetDao {
    int deleteByPrimaryKey(String orderCode);

    int insert(WorkOrderReset record);

    int insertSelective(WorkOrderReset record);

    WorkOrderReset selectByPrimaryKey(String orderCode);

    int updateByPrimaryKeySelective(WorkOrderReset record);

    int updateByPrimaryKey(WorkOrderReset record);

    String selectByCode(String code);
}