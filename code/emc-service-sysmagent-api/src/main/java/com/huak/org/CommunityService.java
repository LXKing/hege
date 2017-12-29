package com.huak.org;

import java.util.List;
import java.util.Map;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Community;

/**
 * 小区service
 * @author Administrator
 *
 */
public interface CommunityService {

	/**
	 * 查询小区列表信息
	 * @param params 查询条件
	 * @return
	 */
	PageResult<Community> list(Map<String, String> params,Page page);

	/**
	 * 新增小区信息
	 * @param community 小区信息
	 * @return
	 */
	int save(Community community);

	/**
	 * 修改小区信息
	 * @param community 修改的小区信息
	 * @return
	 */
	int edit(Community community);

	/**
	 * 删除小区信息
	 * @param communityId 要删除的小区的ID
	 * @return
	 */
	int delete(String communityId);

	/**
	 * 根据主键获取小区信息
	 * @param communityId 小区主键
	 * @return
	 */
	Community get(String communityId);

	/**
	 * 导出小区信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> exportCommunity(Map<String, String> param);
	
	//============================================================================
	//下面是小区、楼座、单元和户可能要用到的方法，为了一些方法的公用，统一管理
	
	/**
	 * 公司下拉框HTML文
	 * @return
	 */
	String getCompanySelectHtmlStr(String selectedComId);
	
	/**
	 * 小区下拉框HTML文
	 * @param params 查询小区的条件 {comId:...,orgId:...}
	 * @param selectedCommunityId 要被选中的
	 * @return
	 */
	String getCommunitySelectHtmlStr(Map<String,String> params,String selectedCommunityId);
	
	/**
	 * 楼座下拉框HTML文
	 * @param params 查询楼座的条件{comId:...,orgId:...,communityId:...}
	 * @param selectedBanId 要被选中的
	 * @return
	 */
	String getBanSelectHtmlStr(Map<String,String> params,String selectedBanId);
	
	/**
	 * 单元下拉框HTML文
	 * @param params 查询单元的条件{comId:...,orgId:...,communityId:...,banId:...}
	 * @param selectedCellId 要被选中的
	 * @return
	 */
	String getCellSelectHtmlStr(Map<String,String> params,String selectedCellId);

	/**
	 * 获取管线下拉框html
	 * @param param
	 * @param selectedLineId
	 * @return
	 */
	String getLineSelectHtmlStr(Map<String, String> param, String selectedLineId);

	/**
	 * 获取供热类型下拉框html
	 * @param heatTypeSelected
	 * @return
	 */
	String getHeatTypeSelectHtmlStr(String heatTypeSelected);

}
