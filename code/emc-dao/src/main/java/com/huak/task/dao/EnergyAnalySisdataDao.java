package com.huak.task.dao;

import com.huak.task.model.EnergyAnalySisdata;

import java.util.List;
import java.util.Map;

public interface EnergyAnalySisdataDao {
    int deleteByPrimaryKey(String id);

    int insert(EnergyAnalySisdata record);

    int insertSelective(EnergyAnalySisdata record);

    EnergyAnalySisdata selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EnergyAnalySisdata record);

    int updateByPrimaryKey(EnergyAnalySisdata record);

    List<EnergyAnalySisdata> selectEnergyAnalyByUnitid(String id);

    void selectFinalDataHourById(Map<String,Object> map);

    void selectPowerById(Map<String,Object> map);

    void selectHeatById(Map<String,Object> map);

    void selectQiById(Map<String,Object> map);

    String selectCoal(Map<String,Object> map);
}