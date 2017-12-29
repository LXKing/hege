package com.huak.mdc.dao;


import com.huak.mdc.model.RecordChange;
import com.huak.mdc.vo.RecordChangeA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RecordChangeDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(RecordChange record);

    RecordChange selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RecordChange record);

    List<RecordChangeA> selectPageByMap(Map<String,Object> paramsMap);

    List<Map<String,Object>> selectAllByMap(Map<String,Object> paramsMap);


}