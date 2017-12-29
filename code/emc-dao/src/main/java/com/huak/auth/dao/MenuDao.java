package com.huak.auth.dao;


import com.huak.auth.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuDao {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectPageByName(Map<String,String> params);

    List<Map<String,Object>> selectMenuTree(Map<String, Object> params);

    int selectCheck(Map<String, String> paramsMap);

    int delete(String[] ids);

    List<Map<String,Object>> selectAuthByMap(Map<String, Object> params);

    Menu getMenuModel(Map<String, Object> paramsMap);
}