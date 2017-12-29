package com.huak.health.dao;

import com.huak.health.model.AlarmConfigTemp;
import com.huak.health.vo.AlarmConfigTempVo;

import java.util.List;
import java.util.Map;

public interface AlarmConfigTempDao {
    int deleteByPrimaryKey(String id);

    int insert(AlarmConfigTemp record);

    int insertSelective(AlarmConfigTemp record);

    AlarmConfigTemp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AlarmConfigTemp record);

    int updateByPrimaryKey(AlarmConfigTemp record);

    List<AlarmConfigTempVo> selectPageByMap(Map<String, Object> paramsMap);

    AlarmConfigTemp selectUpdate(String id);

    List<Map<String, Object>> exportTempConfig(Map<String, Object> paramsMap);

    Long checkUnitName(Map<String, Object> paramsMap);
}