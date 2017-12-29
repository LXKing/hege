package com.huak.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.base.dao.DateDao;
import com.huak.common.utils.DateUtils;
import com.huak.home.dao.SearchDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/8<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private SearchDao searchDao;
    @Resource
    private DateDao dateDao;
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getOrgList(String id) {
        return searchDao.getOrgList(id);
    }

    @Override
    @Transactional(readOnly = true)
    public JSONObject getYearDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject jsonObject = new JSONObject();
        String nowDate = dateDao.getDate();
        Integer year = Integer.valueOf(nowDate.substring(0, 4));
        Date first = DateUtils.getYearFirst(year);
        Date last = DateUtils.getYearLast(year);
        jsonObject.put("startDate",dateFormat.format(first));
        jsonObject.put("endDate",dateFormat.format(last));
        return jsonObject;
    }

    @Override
    @Transactional(readOnly = true)
    public JSONObject getSeason(String id) {
        JSONObject jsonObject = new JSONObject();
        String nowDate = dateDao.getDate();

        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("comId",id);
        paramsMap.put("sdate",nowDate);
        paramsMap.put("edate",nowDate);
        Map<String,Object> season = searchDao.getSeasonOne(paramsMap);
        //当前时间不在采暖季之间，取去年的采暖季
        if(season == null){
            List<Map<String, Object>> seasons = searchDao.getSeasonAll(paramsMap);
            if(seasons.size()==0){
                return null;
            }else {
                season = seasons.get(0);
                Integer day = null;
                try {
                    day = DateUtils.daysBetween(season.get("EDATE").toString(), nowDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(day>365){
                    return null;
                }
            }

        }
        jsonObject.put("startDate",season.get("SDATE").toString());
        jsonObject.put("endDate",season.get("EDATE").toString());
        return jsonObject;
    }
}
