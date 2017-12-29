package com.huak.sys;

import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.dao.EnergyTypeDao;
import com.huak.sys.model.EnergyType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/1<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class EnergyTypeServiceImpl implements EnergyTypeService {
    @Resource
    private EnergyTypeDao energyTypeDao;
    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return energyTypeDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(EnergyType record) {
        return energyTypeDao.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public EnergyType selectByPrimaryKey(String id) {
        return energyTypeDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(EnergyType record) {
        return energyTypeDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<EnergyType> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(energyTypeDao.selectPageByMap(paramsMap));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> exportEnergyTypes(Map<String, Object> paramsMap) {
        return energyTypeDao.exportEnergyTypes(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnergyType> queryByMap(Map<String, Object> paramsMap) {
        return energyTypeDao.queryByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkNameZh(Map<String, Object> paramsMap) {
        return energyTypeDao.checkNameZh(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkNameEn(Map<String, Object> paramsMap) {
        return energyTypeDao.checkNameEn(paramsMap);
    }
}
