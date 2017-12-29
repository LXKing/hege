package com.huak.weather.dao;


import com.huak.weather.model.Weekforcast;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface WeekforcastDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Weekforcast record);

    int insertSelective(Weekforcast record);

    Weekforcast selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weekforcast record);

    int updateByPrimaryKey(Weekforcast record);

    int deletebyParmas(Map<String, Object> params);

    void insertWeekForcastByArray(List<Weekforcast> weeforca);

    List<Weekforcast> selectByParams(Map<String, Object> params);

    List<Weekforcast> selectByComponent(Map<String, Object> params);
}