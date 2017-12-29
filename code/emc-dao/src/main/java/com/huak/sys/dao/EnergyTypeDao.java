package com.huak.sys.dao;

import com.huak.sys.model.EnergyType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnergyTypeDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(EnergyType record);

    EnergyType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EnergyType record);

    List<EnergyType> selectPageByMap(Map<String, Object> paramsMap);

    List<Map<String,Object>> exportEnergyTypes(Map<String, Object> paramsMap);

    List<EnergyType> queryByMap(Map<String, Object> paramsMap);

    Long checkNameZh(Map<String, Object> paramsMap);

    Long checkNameEn(Map<String, Object> paramsMap);
}