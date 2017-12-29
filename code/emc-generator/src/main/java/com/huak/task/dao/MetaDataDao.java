package com.huak.task.dao;

import com.huak.task.model.MetaData;

public interface MetaDataDao {
    int insert(MetaData record);

    int insertSelective(MetaData record);
}