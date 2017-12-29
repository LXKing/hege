package com.huak.org;

import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.dao.CompanyDao;
import com.huak.org.dao.OrgDao;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/23<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyDao companyDao;
    @Resource
    private OrgDao orgDao;

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return companyDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(Company record) {
        Org org = new Org();
        org.setComId(record.getId());
        org.setOrgName(record.getCname());
        org.setpOrgId(0l);
        orgDao.insertSelective(org);
        return companyDao.insertSelective(record);
    }
    @Override
    @Transactional(readOnly = true)
    public Company selectByPrimaryKey(String id) {
        return companyDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(Company record) {
        return companyDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<Company> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(companyDao.selectPageByMap(paramsMap));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> exportCompanys(Map<String, Object> paramsMap) {
        return companyDao.exportCompanys(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> selectAllByMap(Map<String, Object> paramsMap) {
        return companyDao.selectAllByMap(paramsMap);
    }

    /**
     * 查询天气城市列表
     *
     * @param paramsMap
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectWeatherCity(Map<String, Object> paramsMap) {
        return companyDao.selectWeatherCity(paramsMap);
    }

    /**
     * 查询上级
     *
     * @param code
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> selectParentWeatherByCode(String code) {
        return companyDao.selectParentWeatherByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkTableName(Map<String, Object> paramsMap) {
        return companyDao.checkTableName(paramsMap);
    }
}
