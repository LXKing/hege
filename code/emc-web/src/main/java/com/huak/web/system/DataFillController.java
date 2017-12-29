package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.mdc.EnergyDataHisService;
import com.huak.mdc.MeterCollectService;
import com.huak.mdc.model.EnergyDataHis;
import com.huak.mdc.model.MeterCollect;
import com.huak.mdc.vo.MeterCollectDataA;
import com.huak.org.model.Company;
import com.huak.web.home.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.system<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/4<BR>
 * Description:  数据填报   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/data/fill")
public class DataFillController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MeterCollectService meterCollectService;
    @Resource
    EnergyDataHisService energyDataHisService;
    private final String COM_ID = "comId";

    @RequestMapping(method = RequestMethod.GET)
    public String page(HttpServletRequest request,Model model){
        logger.info("打开数据填报查询页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Map<String,Object> params = new HashMap<>();
        params.put(COM_ID,company.getId());
        List<Map<String,Object>> data = meterCollectService.getUnitInfo(params);
        model.addAttribute("unit",data);
        return "system/fill/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String pageAdd(HttpServletRequest request,Model model){
        logger.info("打开数据填报页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Map<String,Object> params = new HashMap<>();
        params.put(COM_ID,company.getId());
        List<Map<String,Object>> data = meterCollectService.getUnitInfo(params);
        model.addAttribute("unit",data);
        return "system/fill/add";
    }

    /**
     * 安全与后台-数据填报
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST  )
    @ResponseBody
    public String add(HttpServletRequest request,@RequestBody List<Map<String,Object>> datas){
        logger.info("安全与后台-数据填报开始");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        List<EnergyDataHis> datalist0 = new ArrayList<>();//实表
        List<String> ids = new ArrayList<>();//虚表
        String collectTime = "";
        for(Map data:datas){
            String flag = data.get("realFlag").toString();
            if(flag.equals("0")){
                if(StringUtils.isBlank(collectTime)){
                    collectTime = data.get("collectTime").toString()+":00:00";
                }
                EnergyDataHis energy0 = new EnergyDataHis();
                energy0.setCollectId(data.get("id").toString());
                energy0.setCollectTime(collectTime);
                energy0.setIschange((byte) 0);
                energy0.setIsprestore((byte) 0);
                energy0.setCollectNum(Double.parseDouble(data.get("num").toString()));
                datalist0.add(energy0);
            }
            if(flag.equals("1")){
                ids.add(data.get("id").toString());
            }
        }
        List<MeterCollect> meterCollects = energyDataHisService.selectFictitiousMeters(ids);
        boolean backValue = energyDataHisService.saveEnergyDatas(datalist0,company,meterCollects);
        if(backValue){
            return "0";
        }else {
            return  "1";
        }
}
    /**
     *数据填报
     *
     * @return string
     */
    @RequestMapping(value = "/list/data", method = RequestMethod.POST)
    @ResponseBody
    public String dataListTian(@RequestParam Map<String,Object> params,HttpServletRequest request, Page page) {
        logger.info("数据填报数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            HttpSession session = request.getSession();
            Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
            params.put(COM_ID,company.getId());
            PageResult<MeterCollectDataA> result =  meterCollectService.queryDataLoadByPage(params,page);
            if (result!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, result);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        }catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("数据填报数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 安全与后台-历史数据填报
     * @return
     */
    @RequestMapping(value = "/add/data",method = RequestMethod.POST  )
    @ResponseBody
    public String addData(HttpServletRequest request,@RequestBody List<Map<String,Object>> datas){
        logger.info("安全与后台-历史数据填报开始");
        HttpSession session = request.getSession();
        Map<String,Object> map = new HashMap<>();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        List<String> ids = new ArrayList<>();//虚表
        List<EnergyDataHis> datalist0 = new ArrayList<>();//实表
        for (Map data : datas) {
            String realFlag = data.get("realFlag").toString();
            String flag = data.get("flag").toString();
            if("1".equals(flag)) {
                if (realFlag.equals("0")) {

                    EnergyDataHis energy0 = new EnergyDataHis();
                    energy0.setCollectId(data.get("id").toString());
                    energy0.setCollectTime(data.get("collectTime").toString()+":00:00");
                    energy0.setIschange((byte) 0);
                    energy0.setIsprestore((byte) 0);
                    energy0.setCollectNum(Double.parseDouble(data.get("num").toString()));
                    datalist0.add(energy0);
                }
                if(flag.equals("1")){
                    ids.add(data.get("id").toString());
                }
            }
        }
        List<MeterCollect> meterCollects = energyDataHisService.selectFictitiousMeters(ids);
        boolean backValue = energyDataHisService.saveEnergyDatas(datalist0,company,meterCollects);
        if(backValue){
            return "0";
        }
        return "1";
    }

}
