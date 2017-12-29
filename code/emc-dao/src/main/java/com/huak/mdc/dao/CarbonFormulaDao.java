package com.huak.mdc.dao;

import com.huak.mdc.model.CarbonFormula;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CarbonFormulaDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(CarbonFormula record);

    CarbonFormula selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CarbonFormula record);

    Double getCarbonFormulaByTime(Map<String, Object> params);
}