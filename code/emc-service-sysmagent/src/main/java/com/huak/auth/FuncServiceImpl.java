package com.huak.auth;

import com.huak.auth.dao.FuncDao;
import com.huak.auth.model.Func;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.auth<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/12<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class FuncServiceImpl implements FuncService {
    @Resource
    private FuncDao funcDao;
    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return funcDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(Func record) {
        return funcDao.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public Func selectByPrimaryKey(String id) {
        return funcDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(Func record) {
        return funcDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Func> selectAllByMap(Map<String, Object> paramsMap) {
        return funcDao.selectAllByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkUName(Map<String, String> paramsMap) {
        return funcDao.checkUName(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkName(Map<String, String> paramsMap) {
        return funcDao.checkName(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkSearch(Map<String, String> paramsMap) {
        return funcDao.checkSearch(paramsMap);
    }

}
