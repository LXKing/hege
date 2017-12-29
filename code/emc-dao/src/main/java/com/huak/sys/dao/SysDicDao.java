package com.huak.sys.dao;

import com.huak.sys.model.SysDic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysDicDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(SysDic record);

    SysDic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDic record);

    List<SysDic> selectPageByMap(Map<String,Object> paramsMap);

    List<Map<String,Object>> exportSysDics(Map<String, Object> paramsMap);

    List<SysDic> selectAllByMap(Map<String,Object> paramsMap);

    List<Map<String,Object>> selectGroup(Map<String, Object> paramsMap);

    Long checkName(Map<String, Object> paramsMap);

    Long checkSeq(Map<String, Object> paramsMap);

    List<SysDic> selectCheckTypeUs(Map<String, Object> paramsMap);

    List<SysDic> selectCheckTypeZh(Map<String, Object> paramsMap);
}