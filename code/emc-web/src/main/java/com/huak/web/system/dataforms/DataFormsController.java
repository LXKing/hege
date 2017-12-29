package com.huak.web.system.dataforms;

import com.alibaba.fastjson.JSONObject;
import com.huak.data.vo.LookupTableTime;
import com.huak.home.DataStatisticsService;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.system<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/4<BR>
 * Description:  数据分析   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/data/daily")
public class DataFormsController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DataStatisticsService dataStatisticsService;
    private final String COM_ID = "comId";

    @RequestMapping(method = RequestMethod.GET)
    public String page(HttpServletRequest request,Model model){
        logger.info("打开数据日报分析查询页");

        return "system/dataforms/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("报表查询统计");
        JSONObject jo = new JSONObject();
        LookupTableTime time = dataStatisticsService.getDataTime(paramsMap);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("bcTime",time.getThisTime());
        params.put("scTime",time.getLastTime());
        Map<String, Object> maps = dataStatisticsService.getSanWestLine(paramsMap);
        Map<String, Object> mapt = dataStatisticsService.getTotal(paramsMap);

        return jo.toJSONString();
    }
}
