package com.huak.org.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.huak.org.model.Room;

@Repository
public interface RoomDao {

	/**
	 * 查询户列表信息
	 * @param params
	 * @return
	 */
	List<Room> selectByPage(Map<String, String> params);

	/**
	 * 添加户信息
	 * @param room
	 * @return
	 */
	int insertRoom(Room room);

	/**
	 * 修改户信息
	 * @param room
	 * @return
	 */
	int updateRoom(Room room);

	/**
	 * 删除户信息
	 * @param roomId
	 * @return
	 */
	int deleteRoom(String roomId);

	/**
	 * 根据主键获取户信息
	 * @param roomId
	 * @return
	 */
	Room get(String id);

	/**
	 * 导出户信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> export(Map<String, String> param);

	/**
	 * 户名称一致
	 * @param param
	 * @return
	 */
	Long selectRoomCount(Map<String,String> param);

	/**
	 * 户编码唯一
	 * @param param
	 * @return
	 */
	Long selectRoomCodeCount(Map<String, String> param);

}
