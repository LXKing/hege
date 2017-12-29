package com.huak.mdc.dao;

import com.huak.mdc.model.UnitArea;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UnitAreaDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(UnitArea record);

    UnitArea selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnitArea record);

    Double getUnitAreaByTime(Map<String, Object> params);
}