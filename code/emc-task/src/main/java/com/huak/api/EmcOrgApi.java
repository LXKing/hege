package com.huak.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huak.common.StringUtils;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
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
 * Date: 2017/7/14<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/org")
public class EmcOrgApi {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoomTempService roomTempService;
    @Resource
    private EnergyAnalyService energyAnalyService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object exportOrgEcc( HttpServletRequest request ,String json){
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
            logger.info("组织机构导入数据的入参："+data);
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
        Map<String,Object> map = (Map<String,Object>) JSON.parse(o.toString());
        //Org eccOrg = JSON.parseObject(o.toString(),Org.class);
        JSONObject jsonObj = new JSONObject();
        if(StringUtils.isEmpty(map.get("comId"))){
            jsonObj.put("status","2");
            jsonObj.put("msg","条件异常");
        }
        Company company = energyAnalyService.selectCompanyByKey(map.get("comId").toString());
        if(null == company){
            jsonObj.put("status","2");
            jsonObj.put("msg","条件异常");
        }
        Org eccOrg = new Org();
        eccOrg.setComId(map.get("comId").toString());
        eccOrg.setOrgName(map.get("ORGNAME").toString());
        eccOrg.setpOrgId(new Long(-1));

        EmcOrgInter orgInter = new EmcOrgInter();
        orgInter.setComId(eccOrg.getComId());
        orgInter.setOrgId(map.get("ID").toString());
        List<EmcOrgInter> list = roomTempService.isExsistInter(orgInter);
        if(list.size()>0){
            jsonObj.put("status","0");
            jsonObj.put("msg","该组织机构数据已存在");
            return jsonObj;
        }else {
            EmcOrgInter inter = new EmcOrgInter();
            //Long eccOrgId = eccOrg.getId();
            logger.info("---------------------开始导入数据---------------------");
            Map<String,Object> result = roomTempService.insertOrg(eccOrg);
                if(result.get("flag")==true){
                    logger.info("----------建立关系表数据----------");
                    inter.setComId(eccOrg.getComId());
                    inter.setEmcId(result.get("emcId").toString());
                    inter.setOrgId(map.get("ID").toString());
                    inter.setUnitType("0");
                    roomTempService.insertEmcOrgInter(inter);
                    //更新对应的pid
                    Org orgUpdate = new Org();
                    orgUpdate.setId(new Long(result.get("emcId").toString()));
                    Map<String,Object> params = new HashMap<String, Object>();
                    params.put("pid",map.get("PORGID").toString());
                    EmcOrgInter emcOrgInter =  roomTempService.selectEmcOrgByMap(params);
                    orgUpdate.setpOrgId(new Long(emcOrgInter.getEmcId()));
                    roomTempService.updateOrgPidByCid(orgUpdate);
                    jsonObj.put("status","1");
                    jsonObj.put("msg","组织机构数据导入成功");
                    return jsonObj;
                }
        }
            jsonObj.put("status","2");
            jsonObj.put("msg","系统导入数据异常");
            logger.info("返回给客户端的jsonstr："+json.toString());
        return jsonObj;
    }
}
