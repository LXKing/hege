package com.huak.sys;

import com.huak.sys.model.Expendcheck;

/**
 * Created by MR-BIN on 2017/6/29.
 */
public interface ExpendcheckService {

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
