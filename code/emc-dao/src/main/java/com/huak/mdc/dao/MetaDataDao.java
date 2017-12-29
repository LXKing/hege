package com.huak.mdc.dao;


import com.huak.mdc.model.MetaData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaDataDao {
    int deleteByPrimaryKey(String tag);

    int insertSelective(MetaData metaData);

    MetaData selectByPrimaryKey(String tag);

    int updateByPrimaryKeySelective(MetaData metaData);

    List<MetaData> selectAllByMap(MetaData metaData);

}