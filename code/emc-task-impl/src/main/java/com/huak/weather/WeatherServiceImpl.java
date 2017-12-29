package com.huak.weather;

import com.alibaba.fastjson.JSON;
import com.huak.base.dao.DateDao;
import com.huak.common.enums.WeatherEnum;
import com.huak.common.utils.HttpWeatherUtils;
import com.huak.weather.dao.WeatherDao;
import com.huak.weather.dao.WeatherAqiDao;
import com.huak.weather.dao.WeekforcastDao;
import com.huak.weather.model.Weather;
import com.huak.weather.model.WeatherAQI;
import com.huak.weather.model.Weekforcast;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by MR-BIN on 2017/7/11.
 */
@Service
public class WeatherServiceImpl implements WeatherTaskService{

    @Resource
    private WeatherDao weaterDao;

    @Resource
    private WeatherAqiDao weatherAqiDao;

    @Resource
    private WeekforcastDao weekforcastDao;
    @Resource
    private DateDao dateDao;

    /**
     * 公共接口入库每小时天气和空气质量
     * @param params
     */
    @Override
    @Transactional(readOnly = false)
    public void executeWeatherTask(List<Map<String, Object>> params) {
        if(params != null) {
            for (Map param : params) {
                this.weatherActual(param);
                this.curentWeatherAQI(param);
            }
        }

    }

    /**
     * 七天预测
     * @param params
     */
    @Override
    @Transactional(readOnly = false)
    public void executeWeather7dTask(List<Map<String, Object>> params) {
        if(params != null){
            for (Map param : params){
                List<Weekforcast> tempLists = weekforcastDao.selectByParams(param);
                if(tempLists != null && tempLists.size()>0){
                    weekforcastDao.deletebyParmas(param);
                }
                 this.weekForcast(param);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getParams() {
        return weaterDao.getParams();
    }

    /**
     * 公共接口实时空气质量
     * @param params
     */
    private void curentWeatherAQI(Map<String, Object> params) {
        WeatherAQI weatherAQI = this.parseWeatherAqi(params);
        if(weatherAQI != null){
            HashMap<String,Object> selParams = new HashMap<>();
            selParams.put("reportDate",weatherAQI.getReportDate());
            selParams.put("code",weatherAQI.getCode());
            selParams.put("status",weatherAQI.getStatus());
            List<WeatherAQI>  weathers = new ArrayList<>();
            try {
                weathers = weatherAqiDao.selectAqis(selParams);
                if(weathers == null|| weathers.size()<1){
                    weatherAqiDao.insert(weatherAQI);
                }else{
                    weatherAqiDao.update(weatherAQI);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * 实时空气质量解析
     * @param params
     * @return
     */
    private WeatherAQI parseWeatherAqi(Map<String, Object> params) {
        String jsondata =  HttpWeatherUtils.getCurrentAQI(params.get("code").toString(), "json");
        Map<String,Object> obj = (Map<String, Object>) JSON.parse(jsondata);
        String times = dateDao.getTime().substring(0,13)+":21";
        if(obj.get("success").equals("1")){
            Map<String,Object> data = (Map<String, Object>) obj.get("result");
            WeatherAQI weather = new WeatherAQI();
            weather.setCode(data.get("cityid").toString());
            weather.setAqi(data.get("aqi").toString());
            weather.setAqiLevel(data.get("aqi_levnm").toString());
            weather.setAqiRemark(data.get("aqi_remark").toString());
            weather.setAqiScope(data.get("aqi_scope").toString());
            weather.setReportDate(times);
            weather.setStatus((byte)0);
            return weather;
        }else{
            System.out.println(obj.get("msg"));
        }
        return null;
    }

    /**
     * 7天天气预测
     * @param params
     */
    private void weekForcast(Map<String, Object> params) {
        List<Weekforcast>  weeforca = this.weekForcastParse(params);
        if(weeforca != null && weeforca.size()>0){
            weekforcastDao.insertWeekForcastByArray(weeforca);
        }

    }

    /**
     * 当天实时数据接口
     */
    private void weatherActual(Map<String,Object> params) {
        Weather weather = this.parseWeatherJson(params);
        if(weather != null){
            HashMap<String,Object> selParams = new HashMap<>();
            selParams.put("reportDate",weather.getreportDate());
            selParams.put("code",weather.getCode());
            selParams.put("status",weather.getStatus());
            List<Weather>  weathers = new ArrayList<>();
            try {
                weathers = weaterDao.selectWeathers(selParams);
                if(weathers == null || weathers.size()<1){
                    weaterDao.insert(weather);
                }else{
                    weaterDao.updateWeather(weather);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * 5-7天天气json数据解析
     * @param params
     * @return
     */
    private List<Weekforcast> weekForcastParse(Map<String, Object> params) {
        String jsondata =  HttpWeatherUtils.getWeatherFuture(params.get("code").toString(),"json");
        Map<String,Object> obj = (Map<String, Object>) JSON.parse(jsondata);
        List<Weekforcast> weeks = new ArrayList<>();
        if(obj.get("success").equals("1")){
            List<Map<String,Object>> weekds = (List<Map<String, Object>>) obj.get("result");
            if(weekds != null && weekds.size()>0){
                for(Map weekdata:weekds){
                    Weekforcast week = new Weekforcast();
                    week.setWind(weekdata.get("wind").toString());
                    week.setWinp(weekdata.get("winp").toString());
                    week.setWeatherIcon(WeatherEnum.getName(Integer.parseInt(weekdata.get("weatid").toString())));
                    week.setCode(weekdata.get("cityid").toString());
                    week.setReportDate(weekdata.get("days").toString());
                    week.setStatus((byte)0);
                    week.setTempHigh(weekdata.get("temp_high").toString());
                    week.setTempLow(weekdata.get("temp_low").toString());
                    week.setWeek(weekdata.get("week").toString());
                    week.setWeather(weekdata.get("weather").toString());
                    weeks.add(week);
                }
                return weeks;
            }else {
                return null;
            }
        }else{
            System.out.println(obj.get("msg"));
        }
        return null;
    }

    /**
     * 实时天气解析接口
     */
    private Weather parseWeatherJson(Map<String,Object> params) {
        String jsondata =  HttpWeatherUtils.getCurrtentWeather(params.get("code").toString(),"json");
        Map<String,Object> obj = (Map<String, Object>) JSON.parse(jsondata);
        String times = dateDao.getTime().substring(11,13);
        if(obj.get("success").equals("1")){
            Map<String,Object> data = (Map<String, Object>) obj.get("result");
            Weather weather = new Weather();
            weather.setCityName(data.get("citynm").toString());
            weather.setCode(data.get("cityid").toString());
            weather.setHumidity(data.get("humidity").toString());
            weather.setHumiHigh(data.get("humi_high").toString());
            weather.setHumiLow(data.get("humi_low").toString());
            weather.setreportDate(data.get("days").toString() + " " + times + ":21:00");
            weather.setTemperatureCurr(data.get("temperature_curr").toString());
            weather.setTempHigh(StringUtils.isBlank(data.get("temp_high").toString())?null:(new BigDecimal(data.get("temp_high").toString())));
            weather.setTempLow(StringUtils.isBlank(data.get("temp_low").toString())?null:(new BigDecimal(data.get("temp_low").toString())));
            weather.setWeatherCurrent(data.get("weather").toString());
            weather.setWeatherIcon(WeatherEnum.getName(Integer.parseInt(data.get("weatid").toString())));
            weather.setWeekDay(data.get("week").toString());
            weather.setWind(data.get("wind").toString());
            weather.setStatus((byte)0);
            weather.setWinp(data.get("winp").toString());
            return weather;
        }else{
            System.out.println(obj.get("msg"));
        }
        return null;
    }
}
