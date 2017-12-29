package com.huak.log.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.huak.log.model.OperateLog;

@Repository
public interface OperateLogDao {

	/**
	 * 存储日志
	 * @param log
	 */
	void insertOperateLog(OperateLog log);

	/**
	 * 分页查询日志
	 * @param paramsMap
	 * @return
	 */
	List<OperateLog> selectPageByMap(Map<String, String> paramsMap);

	/**
	 * 导出日志到excel
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> selectLog2Excel(Map<String, String> param);

}
