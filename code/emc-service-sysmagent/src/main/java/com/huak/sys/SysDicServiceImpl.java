package com.huak.sys;

import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.sys.dao.SysDicDao;
import com.huak.sys.model.SysDic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/16<BR>
 * Description:  字典   <BR>
 * Function List:  <BR>
 */
@Service
public class SysDicServiceImpl implements SysDicService {
    @Resource
    private SysDicDao sysDicDao;
    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return sysDicDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(SysDic record) {
        return sysDicDao.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public SysDic selectByPrimaryKey(String id) {
        return sysDicDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(SysDic record) {
        return sysDicDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<SysDic> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(sysDicDao.selectPageByMap(paramsMap));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> exportSysDics(Map<String, Object> paramsMap) {
        return sysDicDao.exportSysDics(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysDic> queryAll(Map<String, Object> paramsMap) {
        return sysDicDao.selectAllByMap(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryGroup(Map<String, Object> paramsMap) {
        return sysDicDao.selectGroup(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkName(Map<String, Object> paramsMap) {
        return sysDicDao.checkName(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkSeq(Map<String, Object> paramsMap) {
        return sysDicDao.checkSeq(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkTypeUs(Map<String, Object> paramsMap) {
        List<SysDic> dics =  sysDicDao.selectCheckTypeUs(paramsMap);
        if(dics.size()==0){
            return 0l;
        }
        String typeZh = dics.get(0).getTypeZh();
        if(typeZh.equals(paramsMap.get("typeZh"))){
            return 0l;
        }else{
            return 1l;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long checkTypeZh(Map<String, Object> paramsMap) {
        List<SysDic> dics =  sysDicDao.selectCheckTypeZh(paramsMap);
        if(dics.size()==0){
            return 0l;
        }
        String typeZh = dics.get(0).getTypeUs();
        if(typeZh.equals(paramsMap.get("typeUs"))){
            return 0l;
        }else{
            return 1l;
        }
    }
}
