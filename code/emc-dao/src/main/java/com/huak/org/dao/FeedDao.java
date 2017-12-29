package com.huak.org.dao;

import com.huak.auth.model.Role;
import com.huak.org.model.Feed;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FeedDao {
    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    int insert(Feed record);

    /**
     * 根据实体类新增数据
     * @param record
     * @return
     */
    int insertSelective(Feed record);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    Feed selectByPrimaryKey(String id);

    /**
     * 修改数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Feed record);

    int updateByPrimaryKey(Feed record);

    /**
     * 分页查询
     * @param paramsMap
     * @return
     */
    List<Feed> selectPageByMap(Map<String,Object> paramsMap);

    /**
     * 多条件查询数据
     * @param paramsMap
     * @return
     */
    List<Map<String,Object>> selectFeedByMap(Map<String, Object> paramsMap);

    /**
     * 根据id软删除
     * @param id
     * @return
     */
    int deleteByStatus(String id);
}