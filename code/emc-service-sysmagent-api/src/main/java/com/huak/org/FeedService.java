package com.huak.org;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Feed;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/16<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Service
public interface FeedService {
    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(String id);

    /**
     * 根据实体类新增数据
     * @param record
     * @return
     */
    public int insertSelective(Feed record);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    public Feed selectByPrimaryKey(String id);

    /**
     * 修改数据
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Feed record);

    /**
     * 分页查询
     * @param paramsMap
     * @param page
     * @return
     */
    public PageResult<Feed> queryByPage(Map<String,Object> paramsMap, Page page);

    /**
     * 导出数据
     * @param paramsMap
     * @return
     */
    public List<Map<String,Object>> exportFeeds(Map<String, Object> paramsMap);

    /**
     * 多条件查询数据
     * @param paramsMap
     * @return
     */
    List<Map<String,Object>> selectFeedByMap(Map<String, Object> paramsMap);
}
