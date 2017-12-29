package com.huak.auth;

import com.github.pagehelper.PageHelper;
import com.huak.auth.dao.MenuDao;
import com.huak.auth.dao.RoleDao;
import com.huak.auth.dao.UserDao;
import com.huak.auth.model.Menu;
import com.huak.auth.model.Role;
import com.huak.auth.model.User;
import com.huak.auth.model.vo.OrgEmpVo;
import com.huak.common.des.DESUtil;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.type.MenuModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${user.initPassword}")
	private String userInitPassword;
	
	@Value("${huak.des.key}")
	private String desKey;

	@Resource
	private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private MenuDao menuDao;

    private final String  P_MENU_ID = "pMenuId";

	/**
	 * 分页查询用户信息
	 */
	@Override
    @Transactional(readOnly = true)
	public PageResult<User> queryByPage(Map<String, String> paramsMap, Page page) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		return Convert.convert(userDao.selectPageByMap(paramsMap));
	}

	/**
	 * 查询组织机构下的员工信息
	 */
	@Override
    @Transactional(readOnly = true)
	public List<OrgEmpVo> queryOrgEmpByOrgId(String orgId) {
		return userDao.selectOrgEmpById(orgId);
	}

	/**
	 * 添加用户信息
	 */
	@Override
    @Transactional(readOnly = false)
	public int addUser(User user) throws RuntimeException{
		int ret = 0;
		try {
			//密码加密
			String password = DESUtil.encrypt(user.getPassword(), desKey);
			user.setPassword(password);
			ret = userDao.insertUser(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

	/**
	 * 根据主键获取用户信息
	 */
	@Override
    @Transactional(readOnly = true)
	public User getUser(String id) throws Exception{
		User user = null;
		try{
			user = userDao.selectUserById(id);
			//密码解密
			String password = DESUtil.decrypt(user.getPassword(), desKey);
			user.setPassword(password);
		}catch(Exception e){
			user = null;
			throw e;
		}
		return user;
	}

    /**
     * 获取用户信息，根据登录账号
     *
     * @param userName
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public User getUserByName(String userName) throws Exception {
        User user = userDao.selectUserByName(userName);
        if(null == user){
            return null;
        }else{
            //密码解密
            String password = DESUtil.decrypt(user.getPassword(), desKey);
            user.setPassword(password);
        }
        return user;
    }

    /**
	 * 更新用户信息，编辑
	 */
	@Override
    @Transactional(readOnly = false)
	public int editUser(User user) throws Exception{
		int ret = 0;
		try{
			String password = DESUtil.encrypt(user.getPassword(), desKey);
			user.setPassword(password);
			ret = userDao.updateUser(user);
		}catch(Exception e){
			ret = -1;
			throw e;
		}
		return ret;
	}

	/**
	 * 删除用户信息，可批量
	 */
	@Override
    @Transactional(readOnly = false)
	public int removeUsers(String[] ids) throws Exception{
		int ret = 0;
		try{
			ret = userDao.deleteUsers(ids);
		}catch(Exception e){
			ret = -1;
			throw e;
		}
		return ret;
	}

	/**
	 * 重置用户密码
	 */
	@Override
    @Transactional(readOnly = false)
	public String resetPwd(String[] ids) throws Exception {
		String initPwd = null;
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			initPwd = DESUtil.encrypt(userInitPassword, desKey);
			map.put("initPwd", initPwd);
			map.put("ids", ids);
			int ret = userDao.updateUser2ResetPwd(map);
			if(ret != ids.length){
				initPwd = null;
			}else{
				initPwd = userInitPassword;
			}
		}catch(Exception e){
			throw e;
		}
		return initPwd;
	}

	/**
	 * 禁用/启用用户
	 */
	@Override
    @Transactional(readOnly = false)
	public int editUseStatus(String status, String[] ids) throws Exception{
		int ret = 0;
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", status);
			map.put("ids", ids);
			ret = userDao.updateUser2Status(map);
			if(ret!=ids.length){
				ret = -1;
			}
		}catch(Exception e){
			ret = -1;
			throw e;
		}
		return ret;
	}

	/**
	 * 校验登录名称是否唯一
	 */
	@Override
    @Transactional(readOnly = true)
	public Long checkLogin(String login) {
		return userDao.selectUserCountByLogin(login);
	}

	/**
	 * 导出符合条件的用户信息
	 */
	@Override
    @Transactional(readOnly = true)
	public List<Map<String, Object>> exportUser(Map<String, String> paramsMap) {
		List<Map<String, Object>> userList = new ArrayList<Map<String,Object>>();
		try {
			userList = userDao.selectUser2Excel(paramsMap);
			if(userList!=null&&userList.size()>0){
				for(Map<String,Object> map : userList){
					if(null!=map.get("PASSWORD")){
						String password = map.get("PASSWORD").toString();
						//解密密码
						String pwd = DESUtil.decrypt(password, desKey);
						map.remove("PASSWORD");
						map.put("PASSWORD", pwd);
					}
				}
			}
		} catch (Exception e) {
			userList = null;
			e.printStackTrace();
		}
		return userList;
	}

    /**
     * 根据用户获取角色
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Role getRole(String userId) {
        return roleDao.getRoleByUser(userId);
    }

    /**
     * 给用户分配角色
     *
     * @param paramsMap
     */
    @Override
    @Transactional(readOnly = false)
    public void grantRole(Map<String, Object> paramsMap) {
        roleDao.grantRoleByUser(paramsMap);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteRoleByUser(Map<String, Object> paramsMap) {
        roleDao.deleteRoleByUser(paramsMap);
    }

    /**
     * 根据账号密码查询用户
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public User findByLoginAndPwd(User user) {
        return userDao.findByLoginAndPwd(user);
    }

    /**
     * 查询后台菜单
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getBackMenus(String id,String pMenuId) {
        List<Map<String, Object>> menus = new LinkedList<>();

        Map<String, Object> paramsMap = new HashMap<>();
        //查询后台一级菜单
        paramsMap.put("menuType",1);
        paramsMap.put("id",id);
        paramsMap.put(P_MENU_ID,pMenuId);
        List<Map<String, Object>> afterMenus = userDao.selectMenusByUser(paramsMap);
        //查询二级菜单
        for (Map<String, Object> oneMap:afterMenus){
            paramsMap.put(P_MENU_ID,oneMap.get("id"));
            List<Map<String, Object>> oneMenus = userDao.selectMenusByUser(paramsMap);
            List<Map<String, Object>> oneMenusNew = new LinkedList<>();
            for(Map<String, Object> twoMap:oneMenus){
                paramsMap.put(P_MENU_ID,twoMap.get("id"));
                List<Map<String, Object>> twoMenus = userDao.selectMenusByUser(paramsMap);
                twoMap.put("menus",twoMenus);
                oneMenusNew.add(twoMap);
            }
            oneMap.put("menus",oneMenusNew);
            menus.add(oneMap);
        }

        return menus;
    }

    /**
     * 查询用户权限
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Boolean> getAuths(String id) {
        List<Map<String,Object>> auths =  userDao.getAuthByUser(id);
        Map<String, Boolean> authMap = new HashMap<>();
        for (Map<String, Object> map: auths){
            authMap.put(map.get("uname").toString(),true);
        }
        return  authMap;
    }

	@Override
    @Transactional(readOnly = false)
	public void update2LoginSuccess(String id) {
		userDao.update2LoginSuccess(id);
	}

    /**
     * 根据用户取后台每个模块下的菜单
     * @param model
     * @param user
     * @return json
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getSystemMenusByUser(MenuModel model, User user) {
        List<Map<String, Object>> menus = new LinkedList<>();

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("menuName",model.getValue());
        paramsMap.put("menuType",model.getMenuType());
        paramsMap.put("type",model.getType());
        Menu menu = menuDao.getMenuModel(paramsMap);
        if(null == menu){
            return null;
        }
        //查询后台一级菜单
        paramsMap.clear();
        paramsMap.put("menuType",model.getMenuType());
        paramsMap.put("id",user.getId());
        paramsMap.put(P_MENU_ID,menu.getId());
        List<Map<String, Object>> afterMenus = userDao.selectMenusByUser(paramsMap);
        //查询二级菜单
        for (Map<String, Object> oneMap:afterMenus){
            paramsMap.put(P_MENU_ID,oneMap.get("id"));
            List<Map<String, Object>> oneMenus = userDao.selectMenusByUser(paramsMap);
            oneMap.put("menus",oneMenus);
            menus.add(oneMap);
        }
        return menus;
    }
}
