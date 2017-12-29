package com.huak.web.module;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huak.auth.UserService;
import com.huak.auth.model.User;
import com.huak.common.CodeUtil;
import com.huak.common.Constants;
import com.huak.sys.type.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.module<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-11-01<BR>
 * Description:   流程调度  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/process")
public class ProcessController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(Model model,HttpServletRequest request){
        logger.info("跳转流程调度首页");
        /*根据用户加载后台菜单*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_KEY);
        List<Map<String, Object>> menus =  userService.getSystemMenusByUser(MenuModel.PROCESS,user);

        model.addAttribute("menus", JSONArray.toJSON(menus));
        return "process/index";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menuPage(Model model,HttpServletRequest request){
        logger.info("流程调度菜单");
        /*根据用户加载后台菜单*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_KEY);
        List<Map<String, Object>> menus =  userService.getSystemMenusByUser(MenuModel.PROCESS,user);

        model.addAttribute("menus", JSONArray.toJSON(menus));
        return "process/menu";
    }

    @RequestMapping(value = "/decode", method = RequestMethod.POST)
    @ResponseBody
    public String decode(String code,String enc) {
        logger.info("任务调度解码");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            code = CodeUtil.encodeURIComponent(code,enc);
            jo.put("code",code);
            jo.put(Constants.FLAG, true);
        } catch (UnsupportedEncodingException e) {
            logger.error("任务调度解码异常" + e.getMessage());
            jo.put(Constants.MSG, "任务调度解码失败");
        }
        return jo.toJSONString();
    }
}
