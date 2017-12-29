package com.huak.sys;

import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.dao.CostTypeDao;
import com.huak.sys.model.CostType;
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
 * Date: 2017/6/7<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class CostTypeServiceImpl implements CostTypeService {
    @Resource
    private CostTypeDao costTypeDao;

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return costTypeDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(CostType record) {
        return costTypeDao.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public CostType selectByPrimaryKey(String id) {
        return costTypeDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(CostType record) {
        return costTypeDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<CostType> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(costTypeDao.selectPageByMap(paramsMap));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> exportCostTypes(Map<String, Object> paramsMap) {
        return costTypeDao.exportCostTypes(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostType> queryByMap(Map<String, Object> paramsMap) {
        return costTypeDao.queryByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkNameZh(Map<String, Object> paramsMap) {
        return costTypeDao.checkNameZh(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkNameEn(Map<String, Object> paramsMap) {
        return costTypeDao.checkNameEn(paramsMap);
    }
}
