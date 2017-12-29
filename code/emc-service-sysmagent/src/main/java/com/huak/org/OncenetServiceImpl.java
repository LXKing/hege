package com.huak.org;

import com.github.pagehelper.PageHelper;
import com.huak.base.dao.DateDao;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.dao.OncenetDao;
import com.huak.org.model.Oncenet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/17<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Service
public class OncenetServiceImpl implements OncenetService {

    @Resource
    private OncenetDao oncenetDao;

    @Resource
    private DateDao dateDao;

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return oncenetDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(Oncenet record) {
        return oncenetDao.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(Oncenet record) {
        return oncenetDao.insert(record);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(Oncenet record) {
        return oncenetDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public Oncenet selectByPrimaryKey(String id) {
        return oncenetDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<Oncenet> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(oncenetDao.selectPageByMap(paramsMap));
    }

    @Override
    @Transactional(readOnly = false)
    public List<Map<String, Object>> exporNets(Map<String, Object> paramsMap) {

        return oncenetDao.selectNetByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Oncenet> selectNetAll(Map<String, Object> paramsMap) {
        return oncenetDao.selectPageByMap(paramsMap);
    }


    @Override
    @Transactional(readOnly = false)
    public boolean checkNetName(Map<String, Object> paramsMap) {
        boolean flag=false;
        List<Oncenet> list =  oncenetDao.selectPageByMap(paramsMap);
        if(list.size()>0){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean checkNetCode(Map<String, Object> paramsMap) {
        boolean flag=false;
        List<Oncenet> list =  oncenetDao.selectPageByMap(paramsMap);
        if(list.size()>0){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }
}
