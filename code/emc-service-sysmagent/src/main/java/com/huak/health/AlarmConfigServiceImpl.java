package com.huak.health;

import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.health.dao.AlarmConfigDao;
import com.huak.health.model.AlarmConfig;
import com.huak.health.vo.AlarmConfigVO;
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
 * File name:  com.huak.health<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/19<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class AlarmConfigServiceImpl implements AlarmConfigService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AlarmConfigDao alarmConfigDao;
    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        logger.info("删除报警配置");
        return alarmConfigDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(AlarmConfig record) {
        logger.info("新增报警配置");
        return alarmConfigDao.insert(record);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(AlarmConfig record) {
        logger.info("新增报警配置");
        return alarmConfigDao.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public AlarmConfig selectByPrimaryKey(String id) {
        logger.info("查询报警配置");
        return alarmConfigDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(AlarmConfig record) {
        logger.info("更新报警配置");
        return alarmConfigDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(AlarmConfig record) {
        logger.info("更新报警配置");
        return alarmConfigDao.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<AlarmConfigVO> queryByPage(Map<String, Object> paramsMap, Page page) {
        logger.info("分页查询报警配置");
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(alarmConfigDao.selectPageByMap(paramsMap));
    }

    @Override
    @Transactional(readOnly = true)
    public AlarmConfigVO selectUpdateMap(String id) {
        logger.info("更新查询报警配置");
        return alarmConfigDao.selectUpdate(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> exportAlarmConfig(Map<String, Object> paramsMap) {
        logger.info("导出报警配置");
        return alarmConfigDao.exportAlarmConfig(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkAddRepeat(Map<String, Object> paramsMap) {
        logger.info("导出报警配置");
        return alarmConfigDao.checkAddRepeat(paramsMap);
    }
}
