package com.huak.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huak.common.StringUtils;
import com.huak.common.UUIDGenerator;
import com.huak.org.model.Company;
import com.huak.org.model.Node;
import com.huak.task.model.EmcOrgInter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.api<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/17<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/station")
public class StationApi {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoomTempService roomTempService;
    @Resource
    private EnergyAnalyService energyAnalyService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object exportData( HttpServletRequest request ,String json) throws IOException {
        //接收请求参数
        String data=null;
        BufferedReader buffer=null;
        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        try {
            inputStream=request.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            buffer = new BufferedReader(inputStreamReader);
            data = buffer.readLine();
            logger.info("导入数据的入参："+data);
        }catch (IOException e){
            logger.info("数据导入时异常");
        }finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException io) {
                    logger.info("流关闭异常"+io.getMessage());
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException io) {
                    logger.info("流关闭异常"+io.getMessage());
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException io) {
                    logger.info("流关闭异常"+io.getMessage());
                }
            }
        }

        JSONObject jb = JSON.parseObject(data);
        Object o =jb.get("json");
        JSONObject jbAll = JSON.parseObject(o.toString());
        Object eccOrgId=jbAll.get("ORGID");
        jbAll.remove("ORGID");
        jbAll.put("ORGID","-1");
        Node eccNode = JSON.parseObject(jbAll.toString(),Node.class);
        JSONObject jsonObj = new JSONObject();
        if(StringUtils.isEmpty(eccNode.getComId())){
            jsonObj.put("status","2");
            jsonObj.put("msg","条件异常");
        }
        Company company = energyAnalyService.selectCompanyByKey(eccNode.getComId());
        if(null == company){
            jsonObj.put("status","2");
            jsonObj.put("msg","条件异常");
        }
        EmcOrgInter orgInter = new EmcOrgInter();
        orgInter.setComId(eccNode.getComId());
        orgInter.setOrgId(eccNode.getId());
        List<EmcOrgInter> list = roomTempService.isExsistInter(orgInter);
        if(list.size()>0){
            jsonObj.put("status","0");
            jsonObj.put("msg","该数据已存在");
            return jsonObj;
        }else {
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("pid",eccOrgId.toString());
            params.put("unitType","0");
            EmcOrgInter emcOrgInter =  roomTempService.selectEmcOrgByMap(params);
            if(emcOrgInter==null){
                jsonObj.put("status","3");
                jsonObj.put("msg","没有上级");
                return jsonObj;
            }
            EmcOrgInter inter = new EmcOrgInter();
            String uuid = UUIDGenerator.getUUID();
            eccNode.setId(uuid);
            logger.info("---------------------开始导入数据---------------------");
            Map<String,Object> result = roomTempService.inserStation(eccNode);
            if(result.get("flag")==true){
                logger.info("----------建立关系表数据----------");
                inter.setComId(eccNode.getComId());
                inter.setEmcId(uuid);
                inter.setOrgId(eccNode.getId());
                inter.setUnitType("3");
                roomTempService.insertEmcOrgInter(inter);
                Node node = new Node();
                node.setId(uuid);
                node.setOrgId(new Long(emcOrgInter.getEmcId()));
                roomTempService.updateNode(node);
                jsonObj.put("status","2");
                jsonObj.put("msg","数据导入成功");
                return jsonObj;
            }
        }
        jsonObj.put("status","2");
        jsonObj.put("msg","系统导入数据异常");
        logger.info("返回给客户端的jsonstr："+json.toString());
        return jsonObj;
    }
}
