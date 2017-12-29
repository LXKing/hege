package com.huak.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/3<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/main")
public class MainController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        logger.info("跳转首页");
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/ztree", method = RequestMethod.GET)
    public String ztree(){
        return "org/orgtree/list";
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feed(){
        return "org/feed/list";
    }

    @RequestMapping(value = "/node", method = RequestMethod.GET)
    public String node(){
        return "org/node/list";
    }

    /*
     员工菜单
     */
    @RequestMapping(value = "/employe", method = RequestMethod.GET)
    public String employe(){
        return "org/employee/list";
    }


}
