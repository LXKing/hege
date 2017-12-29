package com.huak.auth;

import com.huak.auth.model.Menu;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;

import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/8.
 */
public interface MenuService {

   public int deleteByPrimaryKey(String id);

   public int insert(Menu record);

   public Menu selectByPrimaryKey(String id);

   public int updateByPrimaryKey(Menu record);
   public PageResult<Menu> queryByPage( Map<String, String> parmas,Page page);
   public List<Map<String,Object>> selectTree(Map<String,Object> params);

    int checkMenuName(Map<String, String> paramsMap);

    boolean delete(String ids);

   public List<Map<String,Object>> selectAuthByMap(Map<String,Object> params);
}
