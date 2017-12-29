package com.huak.sys.dao;

import com.huak.sys.model.Administrative;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdministrativeDao {
    int deleteByPrimaryKey(String admCode);

    int insert(Administrative record);

    int insertSelective(Administrative record);

    Administrative selectByPrimaryKey(String admCode);

    int updateByPrimaryKeySelective(Administrative record);

    int updateByPrimaryKey(Administrative record);
//
    List<Map<String,Object>> findAllByLevel(Map<String, String> paramsMap);

    List<Administrative> findAllAdministrative();

    List<Administrative> getAdministrativeSize(String admCode);

    List<Administrative> getAdministrativeSizeCheckName(String admCode);
}