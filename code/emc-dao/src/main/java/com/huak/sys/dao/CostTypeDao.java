package com.huak.sys.dao;


import com.huak.sys.model.CostType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CostTypeDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(CostType record);

    CostType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CostType record);

    public List<CostType> selectPageByMap(Map<String,Object> paramsMap);

    public List<Map<String,Object>> exportCostTypes(Map<String, Object> paramsMap);

    public List<CostType> queryByMap(Map<String,Object> paramsMap);

    public Long checkNameZh(Map<String, Object> paramsMap);

    public Long checkNameEn(Map<String, Object> paramsMap);

}