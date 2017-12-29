package com.huak.log;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.huak.base.dao.DateDao;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.log.dao.OperateLogDao;
import com.huak.log.model.OperateLog;

@Service
public class OperateLogServiceImpl implements OperateLogService {

	@Resource
	private OperateLogDao logDao;
	
	@Resource
	private DateDao dateDao;

	/**
	 * 存储日志
	 */
	@Override
	public void saveOperateLog(OperateLog log) {
		log.setCreateTime(dateDao.getTime().substring(0,19));
		logDao.insertOperateLog(log);
	}

	/**
	 * 分页查询日志
	 */
	@Override
	public PageResult<OperateLog> queryByPage(Map<String, String> paramsMap, Page page) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		return Convert.convert(logDao.selectPageByMap(paramsMap));
	}

	/**
	 * 导出日志到excel
	 */
	@Override
	public List<Map<String, Object>> exportLog(Map<String, String> param) {
		return logDao.selectLog2Excel(param);
	}
}
