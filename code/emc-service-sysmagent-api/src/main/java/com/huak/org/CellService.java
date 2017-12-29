package com.huak.org;

import java.util.List;
import java.util.Map;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Cell;

/**
 * 单元service
 * @author Administrator
 *
 */
public interface CellService {

	/**
	 * 查询单元列表信息
	 * @param params 查询条件
	 * @return
	 */
	PageResult<Cell> list(Map<String, String> params,Page page);

	/**
	 * 新增单元信息
	 * @param cell 单元信息
	 * @return
	 */
	int save(Cell cell);

	/**
	 * 修改单元信息
	 * @param cell 修改的单元信息
	 * @return
	 */
	int edit(Cell cell);

	/**
	 * 删除单元信息
	 * @param cellId 要删除的单元的ID
	 * @return
	 */
	int delete(String cellId);

	/**
	 * 根据主键获取单元信息
	 * @param cellId 单元主键
	 * @return
	 */
	Cell get(String cellId);

	/**
	 * 导出单元信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> exportCell(Map<String, String> param);


	/**
	 * 公司下拉框HTML文
	 * @return
	 */
	String getCompanySelectHtmlStr(String selectedComId);
	
	/**
	 * 小区下拉框HTML文
	 * @param param
	 * @param communityId
	 * @return
	 */
	String getCommunitySelectHtmlStr(Map<String, String> param, String communityId);
	
	/**
	 * 查询楼座下拉框html字符串
	 * @param param
	 * @param object
	 * @return
	 */
	String getBanSelectHtmlStr(Map<String, String> param, String selectBanId);

	/**
	 * 单元名称唯一
	 * @param cellName
	 * @return
	 */
	Long checkCellName(Map<String,String> param);

}
