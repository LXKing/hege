package com.huak.org;

import java.util.List;
import java.util.Map;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Room;

/**
 * 户service
 * @author Administrator
 *
 */
public interface RoomService {

	/**
	 * 查询户列表信息
	 * @param params 查询条件
	 * @return
	 */
	PageResult<Room> list(Map<String, String> params,Page page);

	/**
	 * 新增户信息
	 * @param room 户信息
	 * @return
	 */
	int save(Room room);

	/**
	 * 修改户信息
	 * @param room 修改的户信息
	 * @return
	 */
	int edit(Room room);

	/**
	 * 删除户信息
	 * @param roomId 要删除的户的ID
	 * @return
	 */
	int delete(String roomId);

	/**
	 * 根据主键获取户信息
	 * @param roomId 户主键
	 * @return
	 */
	Room get(String roomId);

	/**
	 * 导出户信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> exportRoom(Map<String, String> param);

	/**
	 * 获取公司下拉框html
	 * @param selectedComId
	 * @return
	 */
	String getCompanySelectHtmlStr(String selectedComId);

	/**
	 * 获取小区下拉框html
	 * @param param
	 * @param selectedCommunityId
	 * @return
	 */
	String getCommunitySelectHtmlStr(Map<String, String> param, String selectedCommunityId);

	/**
	 * 获取楼座下拉框html
	 * @param param
	 * @param selectedBanId
	 * @return
	 */
	String getBanSelectHtmlStr(Map<String, String> param, String selectedBanId);

	/**
	 * 获取单元下拉框html
	 * @param param
	 * @param selectedCellId
	 * @return
	 */
	String getCellSelectHtmlStr(Map<String, String> param, String selectedCellId);

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

	/**
	 * 户名称唯一
	 * @param roomName
	 * @return
	 */
	Long checkRoomName(Map<String,String> param);

	/**
	 * 户编码唯一
	 * @param param
	 * @return
	 */
	Long checkRoomCode(Map<String, String> param);
}
