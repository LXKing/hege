package com.huak.org.dao;


import com.huak.org.model.Node;
import com.huak.org.model.vo.NodeVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NodeDao {
    int deleteByPrimaryKey(String id);

    int insert(Node record);

    int insertSelective(Node record);

    Node selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);

    List<Node> selectPageByMap(Map<String, Object> paramsMap);

    List<Map<String,Object>> export(Map<String, Object> paramsMap);

    Node selectVoById(String id);

    List<Map<String,Object>> selectStationByMap(Map<String, Object> paramsMap);

    int deleteByStatus(String id);

    Object selectNetByMap(Map<String, Object> params);

    Object selectLineByMap(Map<String, Object> params);
}