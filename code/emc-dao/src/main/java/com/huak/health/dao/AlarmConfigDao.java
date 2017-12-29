package com.huak.health.dao;

import com.huak.health.model.AlarmConfig;
import com.huak.health.vo.AlarmConfigVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AlarmConfigDao {
    int deleteByPrimaryKey(String id);

    int insert(AlarmConfig record);

    int insertSelective(AlarmConfig record);

    AlarmConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AlarmConfig record);

    int updateByPrimaryKey(AlarmConfig record);

    List<AlarmConfigVO> selectPageByMap(Map<String, Object> paramsMap);

    AlarmConfigVO selectUpdate(String id);

    List<Map<String,Object>> exportAlarmConfig(Map<String, Object> paramsMap);

    Long checkAddRepeat(Map<String, Object> paramsMap);
}