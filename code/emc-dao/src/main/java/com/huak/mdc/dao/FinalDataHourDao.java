package com.huak.mdc.dao;


import com.huak.mdc.model.FinalDataHour;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FinalDataHourDao {
    int deleteByPrimaryKey(Map<String, Object> params);

    int insertSelective(FinalDataHour record);

    FinalDataHour selectByPrimaryKey(Map<String, Object> params);

    int updateByPrimaryKeySelective(FinalDataHour record);

    int insertOrUpdate(FinalDataHour dataHour);

    List<Map<String,Object>> selectCodeValue(Map<String, Object> params);
}