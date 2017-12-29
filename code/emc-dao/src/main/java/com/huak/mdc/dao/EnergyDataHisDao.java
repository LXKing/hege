package com.huak.mdc.dao;

import com.huak.mdc.model.EnergyDataHis;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyDataHisDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(EnergyDataHis record);

    EnergyDataHis selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EnergyDataHis record);

    EnergyDataHis findBqHis(EnergyDataHis energyDataHis);

    EnergyDataHis findQqHis(EnergyDataHis energyDataHis);

    EnergyDataHis findHqHis(EnergyDataHis energyDataHis);
}