package com.huak.auth;

import com.github.pagehelper.PageHelper;
import com.huak.auth.dao.MenuDao;
import com.huak.auth.model.Menu;
import com.huak.base.dao.DateDao;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/8.
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    private MenuDao menuDao;
    @Resource
    private DateDao dateDao;

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return menuDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(Menu record) {
        record.setCreatTime(dateDao.getTime());
        return menuDao.insert(record);
    }

    @Override
    @Transactional(readOnly = true)
    public Menu selectByPrimaryKey(String id) {
        return menuDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(Menu record) {
        return menuDao.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<Menu> queryByPage(Map<String, String> params, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        List<Menu> data = menuDao.selectPageByName(params);
        return Convert.convert(data);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectTree(Map<String, Object> params) {
        List<Map<String,Object>> resultMap =  menuDao.selectMenuTree(params);
        return resultMap;
    }

    @Override
    @Transactional(readOnly = true)
    public int checkMenuName(Map<String, String> paramsMap) {
        return menuDao.selectCheck(paramsMap);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean delete(String id) {
         String[] ids =id.split(",");
         return menuDao.delete(ids)>0?true:false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String,Object>> selectAuthByMap(Map<String, Object> params) {
        return menuDao.selectAuthByMap(params);
    }
}
