package com.huak.web.home;

import com.huak.mdc.MeterCollectService;
import com.huak.org.OrgService;
import com.huak.sys.IndexTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/13<BR>
 * Description:  系统下拉组件统一接口   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/select")
public class SelectController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IndexTypeService indexTypeService;//指标类型
    @Resource
    private OrgService orgService;//用能单位
    @Resource
    private MeterCollectService meterCollectService;//采集表

    @RequestMapping(value = "/index/type",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> getIndexTypeList(@RequestParam Map<String, Object> paramsMap){
        logger.info("获取指标类型下拉列表");
        return indexTypeService.selectByMap(paramsMap);
    }

    @RequestMapping(value = "/org/unit",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>> getUnitList(@RequestParam Map<String, Object> paramsMap){
        logger.info("获取用能单位下拉列表");
        return orgService.selectViewByMap(paramsMap);
    }

    @RequestMapping(value = "/tags",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>> getUnitTags(){
        logger.info("获取用能单位下的点表下拉列表");
        return meterCollectService.selectTags();
    }
}
