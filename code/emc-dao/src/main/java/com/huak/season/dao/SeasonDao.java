package com.huak.season.dao;


import com.huak.season.model.Season;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SeasonDao {
    int deleteByPrimaryKey(String id);

    int insert(Season record);

    int insertSelective(Season record);

    Season selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Season record);

    int updateByPrimaryKey(Season record);

    List<Season> selectPageByName(@Param("name")String name);

    List<Season> checkName(Map<String,Object> map);

    List<Season> selectPageByMap(Map<String,Object> paramsMap);

    List<Season> checkTime(Map<String,Object> paramsMap);

    String getNowTime();

    List<Map<String,Object>> exportSeason(Map<String,Object> paramsMap);
}