package com.huak.mdc.dao;


import com.huak.mdc.model.RecordPrestore;
import com.huak.mdc.vo.PrestoreA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RecordPrestoreDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(RecordPrestore record);

    RecordPrestore selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RecordPrestore record);

    List<PrestoreA> selectPageByMap(Map<String,Object> paramsMap);

    List<Map<String,Object>> selectAllByMap(Map<String,Object> paramsMap);

}