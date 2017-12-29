package com.huak.sys;

import java.util.List;
import java.util.Map;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.model.EnergyProject;
import com.huak.sys.model.EnergyProjectVo;

public interface EnergyProjectService {

	/**
	 * 分页查询
	 * @param paramsMap
	 * @param page
	 * @return
	 */
	PageResult<EnergyProjectVo> list(Map<String, Object> paramsMap, Page page);

	/**
	 * 新增
	 * @param energyProject
	 * @return
	 */
	int save(EnergyProject energyProject);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	EnergyProject get(String id);

	/**
	 * 修改
	 * @param energyProject
	 * @return
	 */
	int edit(EnergyProject energyProject);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int remove(String id);

	/**
	 * 导出
	 * @param paramsMap
	 * @return
	 */
	List<Map<String, Object>> export(Map<String, Object> paramsMap);

	/**
	 * 校验唯一性
	 * @param paramsMap
	 * @return
	 */
	Long hasUnique(Map<String, Object> paramsMap);

	/**
	 * 采暖季下拉框html
	 * @param orgId
	 * @param selectedId
	 * @return
	 */
	String getSeasonSelectHtml(Map<String, Object> paramsMap, String selectedId);

}
