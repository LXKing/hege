package com.huak.web.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huak.home.EnergyMonitorService;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.module<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/24<BR>
 * Description:   能耗分析模块  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/energy/analysis")
public class EnergyAnalysisController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private EnergyMonitorService eaService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(Model model){
        logger.info("跳转能耗分析首页");
        return "energy/analysis/index";
    }
    
}
