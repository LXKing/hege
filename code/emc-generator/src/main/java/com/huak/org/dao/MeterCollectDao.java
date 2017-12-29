package com.huak.org.dao;

import com.huak.org.model.MeterCollect;

public interface MeterCollectDao {
    int deleteByPrimaryKey(String id);

    int insert(MeterCollect record);

    int insertSelective(MeterCollect record);

    MeterCollect selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeterCollect record);

    int updateByPrimaryKey(MeterCollect record);
}