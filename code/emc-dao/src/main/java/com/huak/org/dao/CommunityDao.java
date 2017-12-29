package com.huak.org.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.huak.org.model.Community;

@Repository
public interface CommunityDao {

	/**
	 * 查询小区列表信息
	 * @param params
	 * @return
	 */
	List<Community> selectByPage(Map<String, String> params);

	/**
	 * 添加小区信息
	 * @param community
	 * @return
	 */
	int insertCommunity(Community community);

	/**
	 * 修改小区信息
	 * @param community
	 * @return
	 */
	int updateCommunity(Community community);

	/**
	 * 删除小区信息
	 * @param communityId
	 * @return
	 */
	int deleteCommunity(String communityId);

	/**
	 * 根据主键获取小区信息
	 * @param communityId
	 * @return
	 */
	Community get(String id);

	/**
	 * 查询所有公司信息
	 * @return
	 */
	List<Map<String, String>> selectCompany();

	/**
	 * 导出小区信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> export(Map<String, String> param);

	//============================================================================
	//下面是小区、楼座、单元和户可能要用到的方法，为了一些方法的公用，统一管理

	/**
	 * 公司下拉框HTML文
	 * @return
	 */
	List<Map<String, String>> selectCompanySelectHtmlStr();

	/**
	 * 小区下拉框HTML文
	 * @param params
	 * @return
	 */
	List<Map<String, String>> selectCommunitySelectHtmlStr(Map<String, String> params);

	/**
	 * 楼座下拉框HTML文
	 * @param params
	 * @return
	 */
	List<Map<String, String>> selectBanSelectHtmlStr(Map<String, String> params);

	/**
	 * 单元下拉框HTML文
	 * @param params
	 * @return
	 */
	List<Map<String, String>> selectCellSelectHtmlStr(Map<String, String> params);

	/**
	 * 管线下拉框html
	 * @param param
	 * @return
	 */
	List<Map<String, String>> selectLineSelectHtmlStr(Map<String, String> param);

	/**
	 * 供热类型下拉框html
	 * @return
	 */
	List<Map<String, String>> getHeatTypeSelectHtmlStr();

}
