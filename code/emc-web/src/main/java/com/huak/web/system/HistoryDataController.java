package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.huak.common.Constants;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.system<BR>
 * Author:  liuhe  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/11/23<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/history/data")
public class HistoryDataController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String page(){
        logger.info("打开换表记录页");
        return "system/history/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap,Page page) {
        logger.info("记录表页分页查询");
        JSONObject jo = new JSONObject();
        try {
            PageHelper.startPage(page.getPageNumber(), page.getPageSize());
            List<String> list=new ArrayList<String>();
            jo.put(Constants.LIST, Convert.convert(list));
        } catch (Exception e) {
            logger.error("记录列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
