package com.huak.org.dao;

import com.huak.org.model.Org;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrgDao {

    int deleteByPrimaryKey(Long id);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);

    List<Org> selectOrgAll(Map<String,Object> params);

    List<Org> CheckOrgName(String orgName);

    List<Map<String,Object>> selectOrgByMap(Map<String,Object> params);

    List<Map<String,Object>> selectOrgTree(Map<String, Object> paramsMap);

    void updateOrgPid(String comId);

    List<Map<String,Object>> selectViewByMap(Map<String, Object> paramsMap);
}