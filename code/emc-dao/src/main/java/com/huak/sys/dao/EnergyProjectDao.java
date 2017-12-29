package com.huak.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.huak.sys.model.EnergyProject;
import com.huak.sys.model.EnergyProjectVo;

@Repository
public interface EnergyProjectDao {

	/**
	 * 分页查询
	 * @param paramsMap
	 * @return
	 */
	List<EnergyProjectVo> selectPageByMap(Map<String, Object> paramsMap);

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	EnergyProject get(String id);

	/**
	 * 新增
	 * @param energyProject
	 * @return
	 */
	int insert(EnergyProject energyProject);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(String id);

	/**
	 * 修改
	 * @param energyProject
	 * @return
	 */
	int update(EnergyProject energyProject);

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
	 * @return
	 */
	List<Map<String, String>> getSeasonSelectHtml(Map<String, Object> paramsMap);

}