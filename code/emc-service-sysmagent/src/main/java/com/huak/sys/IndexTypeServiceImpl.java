package com.huak.sys;

import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.dao.IndexTypeDao;
import com.huak.sys.model.IndexType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Date: 2017/9/11<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class IndexTypeServiceImpl implements IndexTypeService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private IndexTypeDao indexTypeDao;
    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        logger.info("删除指标类型");
        return indexTypeDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(IndexType record) {
        logger.info("新增指标类型");
        return indexTypeDao.insert(record);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(IndexType record) {
        logger.info("新增指标类型");
        return indexTypeDao.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public IndexType selectByPrimaryKey(String id) {
        logger.info("查询指标类型");
        return indexTypeDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(IndexType record) {
        logger.info("修改指标类型");
        return indexTypeDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(IndexType record) {
        logger.info("修改指标类型");
        return indexTypeDao.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<IndexType> queryByPage(Map<String, Object> paramsMap, Page page) {
        logger.info("分页查询指标类型");
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(indexTypeDao.selectPageByMap(paramsMap));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> exportIndexTypes(Map<String, Object> paramsMap) {
        logger.info("导出指标类型");
        return indexTypeDao.exportIndexTypes(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkName(Map<String, Object> paramsMap) {
        logger.info("校验指标类型名称唯一性");
        return indexTypeDao.checkName(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IndexType> queryByMap(Map<String, Object> paramsMap) {
        logger.info("根据条件查询所有指标类型");
        return indexTypeDao.queryByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectByMap(Map<String, Object> paramsMap) {
        logger.info("根据条件查询所有指标类型下拉组件");
        return indexTypeDao.selectByMap(paramsMap);
    }
}
