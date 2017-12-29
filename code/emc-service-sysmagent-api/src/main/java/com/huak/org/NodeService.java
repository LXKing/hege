package com.huak.org;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Node;
import com.huak.org.model.vo.NodeVo;

import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/16.
 */
public interface NodeService {
    /**
     * 删除热力站
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 添加热力站
     * @param record
     * @return
     */
    int insert(Node record);

    /**
     * 添加热力站
     * @param record
     * @return
     */
    public boolean insertSelective(Node record);

    /**
     * 通过id查询热力站实体
     * @param id
     * @return
     */
    Node selectById(String id);

    /**
     * 修改热力站
     * @param record
     * @return
     */
    int update(Node record);

    /**
     * 修改热力站
     * @param record
     * @return
     */
    int updateByPrimaryKey(Node record);

    /**
     * 热力站分页列表
     * @param paramsMap
     * @param page
     * @return
     */
    public PageResult<Node> queryByPage(Map<String, Object> paramsMap, Page page);

    /**
     * 热力站导出
     * @param paramsMap
     * @return
     */
    List<Map<String,Object>> exportExcel(Map<String, Object> paramsMap);

    /**
     * 热力站查询
     * @param paramsMap
     * @return
     */
    List<Map<String,Object>> selectStationByMap(Map<String, Object> paramsMap);

    /**
     * 一次网信息查询
     * @param params
     * @return
     */
    Object selectNetAll(Map<String, Object> params);

    /**
     * 二次线信息查询
     * @param params
     * @return
     */
    Object selectLineAll(Map<String, Object> params);

    /**
     * 热源信息查询
     * @param params
     * @return
     */
    Object selectFeedByMap(Map<String, Object> params);
}
