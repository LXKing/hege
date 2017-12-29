package com.huak.log;

import java.util.List;
import java.util.Map;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.log.model.OperateLog;

public interface OperateLogService {

	/**
	 * 系统监听自己添加日志
	 * @param log
	 */
	void saveOperateLog(OperateLog log);

	/**
	 * 分页查询日志信息
	 * @param paramsMap
	 * @param page
	 * @return
	 */
	PageResult<OperateLog> queryByPage(Map<String, String> paramsMap, Page page);

	/**
	 * 导出日志
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> exportLog(Map<String, String> param);

}
