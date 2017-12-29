package com.huak.sys;

import com.github.pagehelper.PageHelper;
import com.huak.base.dao.DateDao;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.season.dao.SeasonDao;
import com.huak.season.model.Season;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.reason.impl<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/4/24<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class SeasonServiceImpl implements SeasonService {
    @Resource
    private SeasonDao seasonDao;
    @Resource
    private DateDao dateDao;

    @Transactional(readOnly = true)
    public Season selectByPrimaryKey(String id){
       return seasonDao.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    public int delete(String id){
        return seasonDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(Season season) {
        return seasonDao.insert(season);
    }

//    @Override
//    public PageResult<Season> queryByPage(String name, Page page) {
//        PageHelper.startPage(page.getPageNumber(),page.getPageSize());
//        return Convert.convert(seasonDao.selectPageByName(name));
//    }
    @Override
    @Transactional(readOnly = true)
    public PageResult<Season> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(seasonDao.selectPageByMap(paramsMap));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkName(Map<String,Object> map) {
        boolean flag=true;
        List<Season> list = seasonDao.checkName(map);
        if(list.size()>0){
            flag=false;
        }
        return flag;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(Season record) {
        return seasonDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkTime(Map<String, Object> paramsMap) {
        boolean flag=true;
        List<Season> list = seasonDao.checkTime(paramsMap);
        if(list.size()>0){
            flag=false;
        }
        return flag;
    }

    @Override
    @Transactional(readOnly = true)
    public String getNowTime() {
        return seasonDao.getNowTime();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> exportSeason(Map<String, Object> paramsMap) {
        return seasonDao.exportSeason(paramsMap);
    }
}
