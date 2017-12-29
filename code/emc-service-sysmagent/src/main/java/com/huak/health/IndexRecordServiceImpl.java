package com.huak.health;

import com.github.pagehelper.PageHelper;
import com.huak.base.dao.DateDao;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.health.dao.IndexRecordDao;
import com.huak.health.model.IndexRecord;
import com.huak.health.vo.IndexRecordA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/12<BR>
 * Description:   指标配置  <BR>
 * Function List:  <BR>
 */
@Service
public class IndexRecordServiceImpl implements IndexRecordService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private IndexRecordDao indexRecordDao;
    @Resource
    private DateDao dateDao;

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        logger.info("删除指标配置");
        return indexRecordDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(IndexRecord record) {
        logger.info("新增指标配置");
        record.setCreateTime(dateDao.getTime());
        return indexRecordDao.insert(record);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(IndexRecord record) {
        logger.info("删除指标配置");
        record.setCreateTime(dateDao.getTime());
        return indexRecordDao.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public IndexRecord selectByPrimaryKey(String id) {
        logger.info("查询指标配置");
        return indexRecordDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(IndexRecord record) {
        logger.info("更新指标配置");
        return indexRecordDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(IndexRecord record) {
        logger.info("更新指标配置");
        return indexRecordDao.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<IndexRecordA> queryByPage(Map<String, Object> paramsMap, Page page) {
        logger.info("分页查询指标配置");
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(indexRecordDao.selectPageByMap(paramsMap));
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkType(Map<String, Object> paramsMap) {
        logger.info("同一用能单位指标类型唯一性校验");
        return indexRecordDao.checkType(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> selectUpdateMap(String id) {
        logger.info("修改指标配置查询");
        return indexRecordDao.selectUpdateMap(id);
    }
}
