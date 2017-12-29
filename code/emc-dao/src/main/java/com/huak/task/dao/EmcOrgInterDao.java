package com.huak.task.dao;

import com.huak.task.model.EmcOrgInter;

import java.util.List;
import java.util.Map;

public interface EmcOrgInterDao {
    int deleteByPrimaryKey(String id);

    int insert(EmcOrgInter record);

    int insertSelective(EmcOrgInter record);

    EmcOrgInter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EmcOrgInter record);

    int updateByPrimaryKey(EmcOrgInter record);

    List<EmcOrgInter> selectAllByMap(EmcOrgInter record);

    EmcOrgInter selectEmcOrgByMap(Map<String,Object> map);


}