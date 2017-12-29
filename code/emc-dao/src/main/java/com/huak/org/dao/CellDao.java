package com.huak.org.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.huak.org.model.Cell;

@Repository
public interface CellDao {

	/**
	 * 查询单元列表信息
	 * @param params
	 * @return
	 */
	List<Cell> selectByPage(Map<String, String> params);

	/**
	 * 添加单元信息
	 * @param unit
	 * @return
	 */
	int insertCell(Cell cell);

	/**
	 * 修改单元信息
	 * @param unit
	 * @return
	 */
	int updateCell(Cell cell);

	/**
	 * 删除单元信息
	 * @param unitId
	 * @return
	 */
	int deleteCell(String cellId);

	/**
	 * 根据主键获取单元信息
	 * @param unitId
	 * @return
	 */
	Cell get(String id);

	/**
	 * 导出单元信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> export(Map<String, String> param);

	/**
	 * 单元名称唯一
	 * @param param
	 * @return
	 */
	Long selectCellCount(Map<String,String> param);

}
