package com.huak.dao;

import com.huak.model.Season;

public interface SeasonDao {
    int deleteByPrimaryKey(String id);

    int insert(Season record);

    int insertSelective(Season record);

    Season selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Season record);

    int updateByPrimaryKey(Season record);
}