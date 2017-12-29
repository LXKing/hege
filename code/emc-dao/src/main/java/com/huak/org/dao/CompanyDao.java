package com.huak.org.dao;

import com.huak.org.model.Company;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CompanyDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(Company record);

    Company selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Company record);

    List<Company> selectPageByMap(Map<String,Object> paramsMap);

    List<Map<String,Object>> exportCompanys(Map<String, Object> paramsMap);

    List<Company> selectAllByMap(Map<String,Object> paramsMap);

    List<Company> selectCompanyAll();

    List<Map<String,Object>> selectWeatherCity(Map<String, Object> paramsMap);

    Map<String,Object> selectParentWeatherByCode(String code);

    Long checkTableName(Map<String, Object> paramsMap);
}