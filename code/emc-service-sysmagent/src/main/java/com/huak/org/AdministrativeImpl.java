package com.huak.org;




import com.huak.sys.dao.AdministrativeDao;
import com.huak.sys.model.Administrative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/17.
 */
@Service
public class AdministrativeImpl implements AdministrativeService {

    @Autowired
    private AdministrativeDao administrativeDao;

    @Override
    @Transactional(readOnly = false)
    public boolean deleteByPrimaryKey(String admCode) {
        boolean flag=false;
        String[] ids = admCode.split(",");
        try {
            if(ids.length>1){
                for (int i = 0; i <ids.length ; i++) {
                    administrativeDao.deleteByPrimaryKey(admCode);
                }
                flag=true;
            }else{
                administrativeDao.deleteByPrimaryKey(admCode);
                flag=true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            flag=false;
        }
        return flag;

    }

    @Override
    @Transactional(readOnly = false)
    public boolean insert(Administrative record) {
        boolean flag=false;
        int i = administrativeDao.insert(record);
        if(i>0){
            flag=true;
        }
        return flag;
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(Administrative record) {
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public Administrative selectByPrimaryKey(String admCode) {
        return administrativeDao.selectByPrimaryKey(admCode);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updateByPrimaryKeySelective(Administrative record) {
        boolean flag=false;
        int i =  administrativeDao.updateByPrimaryKeySelective(record);
        if(i>0){
            flag=true;
        }
        return flag;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(Administrative record) {
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findAllByLevel(Map<String, String> paramsMap) {
        return administrativeDao.findAllByLevel(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Administrative> findAllAdministrative() {
        return administrativeDao.findAllAdministrative();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Administrative> getAdministrativeSize(String admCode) {
        return administrativeDao.getAdministrativeSize(admCode);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Administrative> getAdministrativeSizeCheckName(String admCode) {
        return administrativeDao.getAdministrativeSizeCheckName(admCode);
    }
}
