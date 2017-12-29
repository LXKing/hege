package com.huak.web.module;

import com.alibaba.fastjson.JSONArray;
import com.huak.auth.UserService;
import com.huak.auth.model.User;
import com.huak.common.Constants;
import com.huak.sys.type.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.module<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/24<BR>
 * Description:  后台管理模块   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(Model model,HttpServletRequest request){
        logger.info("跳转后台管理首页");
        /*根据用户加载后台菜单*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_KEY);
        List<Map<String, Object>> menus =  userService.getSystemMenusByUser(MenuModel.SYSTEM,user);

        model.addAttribute("menus", JSONArray.toJSON(menus));
        return "system/index";
    }

}
