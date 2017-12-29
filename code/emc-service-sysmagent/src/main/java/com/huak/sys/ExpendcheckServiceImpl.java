package com.huak.sys;

import com.huak.sys.dao.ExpendcheckDao;
import com.huak.sys.model.Expendcheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MR-BIN on 2017/6/29.
 */
@Service
public class ExpendcheckServiceImpl implements ExpendcheckService {
    @Autowired
    private ExpendcheckDao expendcheckDao;

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(Expendcheck record) {
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(Expendcheck record) {
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public Expendcheck selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(Expendcheck record) {
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(Expendcheck record) {
        return 0;
    }
}
