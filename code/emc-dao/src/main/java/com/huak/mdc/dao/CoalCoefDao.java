package com.huak.mdc.dao;

import com.huak.mdc.model.CoalCoef;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CoalCoefDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(CoalCoef record);

    CoalCoef selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CoalCoef record);

    Double getCoalCoefByTime(Map<String, Object> params);
}