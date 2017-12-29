package com.huak.weather.dao;

import com.huak.weather.model.WeatherAQI;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/7/13.
 */
@Repository
public interface WeatherAqiDao {
    /**
     * 根据参数查询AQI
     * @param selParams
     * @return
     */
    List<WeatherAQI> selectAqis(HashMap<String, Object> selParams);

    /**
     * 入库AQI
     * @param weatherAQI
     */
    void insert(WeatherAQI weatherAQI);

    /**
     * 更新AQI
     * @param weatherAQI
     */
    void update(WeatherAQI weatherAQI);

    /**
     * 根据id查询空气质量
     * @param params
     * @return
     */
    WeatherAQI selectById(Map<String, Object> params);
}
