package com.huak.mdc.dao;

import com.huak.mdc.model.EnergyPrice;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;

@Repository
public interface EnergyPriceDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(EnergyPrice record);

    EnergyPrice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EnergyPrice record);

    BigDecimal getEnergyPriceByTime(Map<String, Object> params);
}