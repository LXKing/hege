package com.huak.auth;

import com.huak.auth.model.Role;
import com.huak.auth.model.User;
import com.huak.auth.model.vo.OrgEmpVo;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.type.MenuModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface UserService {

	/**
	 * 分页查询用户信息
	 * @param paramsMap 查询条件
	 * @param page 分页信息
	 * @return
	 */
	PageResult<User> queryByPage(Map<String, String> paramsMap, Page page);

	/**
	 * 查询org下的员工
	 * @param orgId
	 * @return
	 */
	List<OrgEmpVo> queryOrgEmpByOrgId(String orgId);

	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 * @throws RuntimeException
	 */
	int addUser(User user) throws RuntimeException;

	/**
	 * 获取用户信息，根据主键
	 * @param id
	 * @return
	 */
	User getUser(String id) throws Exception;

    /**
     * 获取用户信息，根据登录账号
     * @param userName
     * @return
     */
    User getUserByName(String userName) throws Exception;

	/**
	 * 编辑用户信息 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int editUser(User user) throws Exception;

	/**
	 * 删除用户信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	int removeUsers(String[] ids) throws Exception;

	/**
	 * 重置用户密码
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	String resetPwd(String[] ids) throws Exception;

	/**
	 * 禁用/启用用户
	 * @param status
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	int editUseStatus(String status, String[] ids) throws Exception;

	/**
	 * 根据登录名称校验登录名称是否唯一
	 * @param login
	 * @return
	 */
	Long checkLogin(String login);

	/**
	 * 导出符合条件的用户信息
	 * @param paramsMap
	 * @return
	 */
	List<Map<String, Object>> exportUser(Map<String, String> paramsMap);

    /**
     * 根据用户获取角色
     * @param userId
     * @return
     */
    Role getRole(String userId);

    /**
     * 给用户分配角色
     */
    void grantRole(Map<String, Object> paramsMap );

    @Transactional(readOnly = false)
    void deleteRoleByUser(Map<String, Object> paramsMap);

    /**
     * 根据账号密码查询用户
     * @param user
     * @return
     */
    User findByLoginAndPwd(User user);

    /**
     * 查询后台菜单
     * @param id
     * @return
     */
    List<Map<String,Object>> getBackMenus(String id,String pMenuId);

    /**
     * 查询用户权限
     * @param id
     * @return
     */
    Map<String, Boolean> getAuths(String id);

    /**
     * 登录成功后修改用户参数
     */
	void update2LoginSuccess(String id);

    List<Map<String,Object>> getSystemMenusByUser(MenuModel model, User user);
}
