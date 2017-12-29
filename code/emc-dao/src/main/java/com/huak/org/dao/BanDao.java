package com.huak.org.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.huak.org.model.Ban;

@Repository
public interface BanDao {

	/**
	 * 查询楼座列表信息
	 * @param params
	 * @return
	 */
	List<Ban> selectByPage(Map<String, String> params);

	/**
	 * 添加楼座信息
	 * @param ban
	 * @return
	 */
	int insertBan(Ban ban);

	/**
	 * 修改楼座信息
	 * @param ban
	 * @return
	 */
	int updateBan(Ban ban);

	/**
	 * 删除楼座信息
	 * @param banId
	 * @return
	 */
	int deleteBan(String banId);

	/**
	 * 根据主键获取楼座信息
	 * @param banId
	 * @return
	 */
	Ban get(String id);

	/**
	 * 导出楼座信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> export(Map<String, String> param);

	/**
	 * 楼座名称唯一
	 * @param param
	 * @return
	 */
	Long selectBanCount(Map<String,String> param);

}
