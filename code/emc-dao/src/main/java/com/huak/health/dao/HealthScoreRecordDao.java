package com.huak.health.dao;

import com.huak.health.model.HealthScoreRecord;
import com.huak.health.vo.IndexDataA;
import com.huak.health.vo.IndexTempA;
import com.huak.health.vo.WorkWarnVo;

import java.util.List;
import java.util.Map;

public interface HealthScoreRecordDao {
    int deleteByPrimaryKey(String id);

    int insert(HealthScoreRecord record);

    int insertSelective(HealthScoreRecord record);

    HealthScoreRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HealthScoreRecord record);

    int updateByPrimaryKey(HealthScoreRecord record);

    HealthScoreRecord  selectHealthScoreRecordById(Map<String,Object> params);

    List<IndexDataA> getIndexData(Map<String,Object> params);

    List<IndexTempA> getIndexTemp(Map<String,Object> params);

    List<WorkWarnVo> getWorkWarnInfo(Map<String,Object> params);
}