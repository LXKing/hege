package com.huak.health.dao;

import com.huak.health.model.AlarmRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRecordDao {
    int deleteByPrimaryKey(String id);

    int insert(AlarmRecord record);

    int insertSelective(AlarmRecord record);

    AlarmRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AlarmRecord record);

    int updateByPrimaryKey(AlarmRecord record);

    AlarmRecord selectByConfigId(String id);
}