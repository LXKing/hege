package com.huak.api;

import com.huak.org.dao.FeedDao;
import com.huak.org.dao.NodeDao;
import com.huak.org.dao.OrgDao;
import com.huak.org.model.Feed;
import com.huak.org.model.Node;
import com.huak.org.model.Org;
import com.huak.task.dao.EmcOrgInterDao;
import com.huak.task.dao.TemperatureDao;
import com.huak.task.model.EmcOrgInter;
import com.huak.task.model.Temperature;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/13<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class RoomTempServiceImpl implements RoomTempService {

    @Resource
    TemperatureDao tempDao;

    @Resource
    EmcOrgInterDao emcOrgInterDao;

    @Resource
    OrgDao orgDao;

    @Resource
    FeedDao feedDao;

    @Resource
    NodeDao nodeDao;
    @Override
    @Transactional(readOnly = true)
    public List<Temperature> isExsistTemp(Temperature record) {
        return tempDao.selectAllByMap(record);
    }

    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> insertTemp(Temperature temp) {
        Map<String, Object> map = new HashMap<String,Object>();
         try {
             tempDao.insertSelective(temp);
             map.put("flag",true);
             map.put("msg","导入成功");
         }catch (Exception e){
             e.printStackTrace();
             map.put("flag",false);
             map.put("msg","导入失败");
         }
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmcOrgInter> isExsistInter(EmcOrgInter record) {
        return emcOrgInterDao.selectAllByMap(record);
    }

    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> insertOrg(Org org) {
        Map<String, Object> map = new HashMap<String,Object>();
        try {
            orgDao.insertSelective(org);
            map.put("emcId",org.getId());
            map.put("flag",true);
            map.put("msg","导入成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("flag",false);
            map.put("msg","导入失败");
        }
        return map;
    }

    @Override
    @Transactional(readOnly = false)
    public void insertEmcOrgInter(EmcOrgInter inter) {
        emcOrgInterDao.insert(inter);
    }

    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> insertFeed(Feed feed) {
        Map<String, Object> map = new HashMap<String,Object>();
        try {
            feedDao.insertSelective(feed);
            map.put("flag",true);
            map.put("msg","导入成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("flag",false);
            map.put("msg","导入失败");
        }
        return map;
    }

    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> inserStation(Node node) {
        Map<String, Object> map = new HashMap<String,Object>();
        try {
            nodeDao.insertSelective(node);
            map.put("flag",true);
            map.put("msg","导入成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("flag",false);
            map.put("msg","导入失败");
        }
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public EmcOrgInter selectEmcOrgByMap(Map<String, Object> map) {
        return emcOrgInterDao.selectEmcOrgByMap(map);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateOrgPidByCid(Org org) {
        orgDao.updateByPrimaryKeySelective(org);
    }

    @Override
    public void updateFeed(Feed feed) {
        feedDao.updateByPrimaryKeySelective(feed);
    }

    @Override
    public void updateNode(Node node) {
        nodeDao.updateByPrimaryKeySelective(node);
    }

}
