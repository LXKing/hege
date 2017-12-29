package com.huak.weather;

import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/7/11.
 */
public interface WeatherTaskService {
    /**
     * 实时数据
     * @param params
     */
    void executeWeatherTask(List<Map<String, Object>> params);

    /**
     * 7d预测
     * @param params
     */
    void executeWeather7dTask(List<Map<String, Object>> params);

    /**
     * 获取需要获取天气的城市
     * @return
     */
    List<Map<String,Object>> getParams();

}
