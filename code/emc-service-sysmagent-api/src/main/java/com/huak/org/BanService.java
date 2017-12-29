package com.huak.org;

import java.util.List;
import java.util.Map;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Ban;

/**
 * 楼座service
 * @author Administrator
 *
 */
public interface BanService {

	/**
	 * 查询楼座列表信息
	 * @param params 查询条件
	 * @return
	 */
	PageResult<Ban> list(Map<String, String> params,Page page);

	/**
	 * 新增楼座信息
	 * @param Ban 楼座信息
	 * @return
	 */
	int save(Ban ban);

	/**
	 * 修改楼座信息
	 * @param ban 修改的楼座信息
	 * @return
	 */
	int edit(Ban ban);

	/**
	 * 删除楼座信息
	 * @param banId 要删除的楼座的ID
	 * @return
	 */
	int delete(String banId);

	/**
	 * 根据主键获取楼座信息
	 * @param banId 楼座主键
	 * @return
	 */
	Ban get(String banId);

	/**
	 * 导出楼座信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> exportBan(Map<String, String> param);

	/**
	 * 查询公司信息组装成select中的option list
	 * @return
	 */
	String getCompanySelectHtmlStr(String selectedComId);

	/**
	 * 查询小区下拉框html str
	 * @param param
	 * @return
	 */
	String getCommunitySelectHtmlStr(Map<String, String> param,String selectedId);

	/**
	 * 楼座名称唯一
	 * @param banName
	 * @return
	 */
	Long checkBanName(Map<String,String> param);

}
