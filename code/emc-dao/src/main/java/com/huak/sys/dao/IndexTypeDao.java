package com.huak.sys.dao;


import com.huak.sys.model.IndexType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface IndexTypeDao {
    int deleteByPrimaryKey(String id);

    int insert(IndexType record);

    int insertSelective(IndexType record);

    IndexType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(IndexType record);

    int updateByPrimaryKey(IndexType record);

    List<IndexType> selectPageByMap(Map<String, Object> paramsMap);

    List<Map<String,Object>> exportIndexTypes(Map<String, Object> paramsMap);

    Long checkName(Map<String, Object> paramsMap);

    List<IndexType> queryByMap(Map<String, Object> paramsMap);

    List<Map<String,Object>> selectByMap(Map<String, Object> paramsMap);
}