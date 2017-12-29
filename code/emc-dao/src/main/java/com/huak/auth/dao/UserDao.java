package com.huak.auth.dao;

import com.huak.auth.model.User;
import com.huak.auth.model.vo.OrgEmpVo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

	/**
	 * 分页查询用户列表
	 * @param paramsMap
	 * @return
	 */
	List<User> selectPageByMap(Map<String, String> paramsMap);

	/**
	 * 查询某组织机构下的员工信息
	 * @param orgId
	 * @return
	 */
	List<OrgEmpVo> selectOrgEmpById(String orgId);

	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	int insertUser(User user);

	/**
	 * 根据主键获取用户信息
	 * @param id
	 * @return
	 */
	User selectUserById(String id);

	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	int updateUser(User user);

	/**
	 * 删除用户，可批量
	 * @param id
	 * @return
	 */
	int deleteUsers(String[] id);

	/**
	 * 重置密码
	 * @param map
	 * @return
	 */
	int updateUser2ResetPwd(Map<String, Object> map);

	/**
	 * 禁用/启用用户
	 * @param map
	 * @return
	 */
	int updateUser2Status(Map<String, Object> map);

	/**
	 * 根据登录名称查询是否存在此用户
	 * @param login
	 * @return
	 */
	Long selectUserCountByLogin(String login);

	/**
	 * 导出符合条件的用户信息
	 * @param paramsMap
	 * @return
	 */
	List<Map<String, Object>> selectUser2Excel(Map<String, String> paramsMap);

    User findByLoginAndPwd(User user);

    List<Map<String,Object>> selectMenusByUser(Map<String, Object> paramsMap);

    List<Map<String,Object>> getAuthByUser(String id);

	void update2LoginSuccess(String id);

    User selectUserByName(String userName);
}
