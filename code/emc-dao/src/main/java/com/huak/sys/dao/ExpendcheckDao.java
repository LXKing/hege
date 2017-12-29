package com.huak.sys.dao;


import com.huak.sys.model.Expendcheck;

public interface ExpendcheckDao {

    /**
     * 删除对标
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 添加对标
     * @param record
     * @return
     */
    int insert(Expendcheck record);

    /**
     * 新增对标
     * @param record
     * @return
     */
    int insertSelective(Expendcheck record);

    /**
     * 查询对标
     * @param id
     * @return
     */
    Expendcheck selectByPrimaryKey(String id);

    /**
     * 更新对标
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Expendcheck record);

    /**
     * 更新对标
     * @param record
     * @return
     */
    int updateByPrimaryKey(Expendcheck record);

}