package com.huak.home.component;


import com.huak.base.dao.DateDao;
import com.huak.health.dao.HealthScoreRecordDao;
import com.huak.health.model.HealthScoreRecord;
import com.huak.health.vo.IndexDataA;
import com.huak.health.vo.IndexTempA;
import com.huak.health.vo.WorkWarnVo;
import com.huak.home.dao.SearchDao;
import com.huak.task.dao.TemperatureDao;
import com.huak.weather.dao.WeatherAqiDao;
import com.huak.weather.dao.WeatherDao;
import com.huak.weather.dao.WeekforcastDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/6/8.
 */
@Service
public class HealthScoreRecordServiceImpl implements HealthScoreRecordService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HealthScoreRecordDao healthScoreRecordDao;

    @Autowired
    private DateDao dateDao;

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private WeatherAqiDao weatherAqiDao;

    @Autowired
    private WeekforcastDao weekforcastDao;

    @Autowired
    private WeatherDao weatherDao;

    @Autowired
    private TemperatureDao temperatureDao;


    @Override
    public HealthScoreRecord getRecordById(Map<String, Object> params) {
        return healthScoreRecordDao.selectHealthScoreRecordById(params);
    }

    @Override
    public int insertSelective(HealthScoreRecord record) {
        return healthScoreRecordDao.insertSelective(record);
    }

    @Override
    public List<IndexDataA> getIndexData(Map<String, Object> params) {
        List<IndexDataA> list =  healthScoreRecordDao.getIndexData(params);

        return list;
    }

    @Override
    public List<IndexTempA> getIndexTemp(Map<String, Object> params) {
        Calendar calendar = Calendar.getInstance();
        String startTime = calendar.getWeekYear()-1+"-11-15 00:00:00";
        String endTime = calendar.getWeekYear()+"-03-15 23:59:59";
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        List<IndexTempA> list =  healthScoreRecordDao.getIndexTemp(params);

        return list;
    }

    @Override
    public List<WorkWarnVo> getWorkWarnInfo(Map<String, Object> params) {
        return healthScoreRecordDao.getWorkWarnInfo(params);
    }
}