package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.SearchService;
import com.huak.org.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/7<BR>
 * Description:  首页查询组件   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/tools/search")
public class SearchController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SearchService searchService;

    @RequestMapping(value = "/org",method = RequestMethod.POST)
    @ResponseBody
    public  List<Map<String,Object>>  getOrgList(HttpServletRequest request){
        logger.info("获取查询组件组织机构");

        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        List<Map<String,Object>> orgs = searchService.getOrgList(company.getId());
        return orgs;
    }


    @RequestMapping(value = "/year",method = RequestMethod.POST)
    @ResponseBody
    public String getYearDate(){
        logger.info("获取查询组件本年度时间段");
        JSONObject jo = searchService.getYearDate();
        return jo.toJSONString();
    }


    @RequestMapping(value = "/season",method = RequestMethod.POST)
    @ResponseBody
    public String getSeasonDate(HttpServletRequest request){
        logger.info("获取查询组件本采暖季时间段");
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        JSONObject jo;
        jo = searchService.getSeason(company.getId());

        if(jo == null){
            jo = new JSONObject();
            jo.put(Constants.FLAG,false);
            jo.put(Constants.MSG,"未设置采暖季,请先设置本采暖季");
        }else{

            jo.put(Constants.FLAG,true);
        }
        return jo.toJSONString();
    }


}
