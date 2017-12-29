package com.huak.weather.dao;


import com.huak.weather.model.Weather;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface WeatherDao {
    int deleteByPrimaryKey(Long id);

    int insert(Weather record);

    int insertSelective(Weather record);

    Weather selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Weather record);

    int updateByPrimaryKey(Weather record);

    List<Weather> selectWeathers(HashMap<String, Object> selParams);

    void updateWeather(Weather weather);

    Weather selectByPrimaryKey(Map<String, Object> params);

    List<Weather> getLatestWeathers(Map<String, Object> params);

    List<Map<String,Object>> getParams();

    Double getWeatherByTime(Map<String, Object> params);
}