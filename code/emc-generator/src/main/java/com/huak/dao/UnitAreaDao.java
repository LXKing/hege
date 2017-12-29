package com.huak.dao;

import com.huak.model.UnitArea;

public interface UnitAreaDao {
    int deleteByPrimaryKey(String id);

    int insert(UnitArea record);

    int insertSelective(UnitArea record);

    UnitArea selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnitArea record);

    int updateByPrimaryKey(UnitArea record);
}