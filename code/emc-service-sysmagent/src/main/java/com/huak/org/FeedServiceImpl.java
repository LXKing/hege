package com.huak.org;

import com.github.pagehelper.PageHelper;
import com.huak.base.dao.DateDao;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.dao.FeedDao;
import com.huak.org.model.Feed;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class FeedServiceImpl implements FeedService{

    @Resource
    private FeedDao feedDao;
    @Resource
    private DateDao dateDao;

    /**
     * 分页查询
     * @param paramsMap
     * @param page
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public PageResult<Feed> queryByPage(Map<String, Object> paramsMap, Page page) {

        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(feedDao.selectPageByMap(paramsMap));
    }

    /**
     * 导出数据
     * @param paramsMap
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> exportFeeds(Map<String, Object> paramsMap) {
        return feedDao.selectFeedByMap(paramsMap);
    }

    /**
     * 多条件查询数据
     * @param paramsMap
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> selectFeedByMap(Map<String, Object> paramsMap) {
        return feedDao.selectFeedByMap(paramsMap);
    }

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Feed selectByPrimaryKey(String id) {
        Feed feed = feedDao.selectByPrimaryKey(id);

        return feed;
    }

    /**
     * 根据实体类新增数据
     * @param record
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int insertSelective(Feed record) {

        return feedDao.insertSelective(record);
    }
    /**
     * 修改数据
     * @param record
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(Feed record) {

        return feedDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return feedDao.deleteByStatus(id);
    }
}
