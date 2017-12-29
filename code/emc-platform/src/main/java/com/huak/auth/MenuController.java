
package com.huak.auth;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.Menu;
import com.huak.auth.model.User;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.auth<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/31<BR>
 * Description:  系统菜单   <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/menu")
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MenuService menuService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统菜单列表页");
        return "auth/menu/list";
    }
    /**
     * 初始化
     *
     * @param paramsMap
     * @return
     */

    @RequestMapping(value = "/list/tree", method = RequestMethod.POST)
    @ResponseBody
    public  List<Map<String,Object>> list(@RequestParam Map<String, Object> paramsMap) {
        logger.info("菜单列表页查询");
        Page page = new Page();
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> data = null;
        try {
            data = menuService.selectTree(paramsMap);

        } catch (Exception e) {
            logger.error("菜单列表页查询异常" + e.getMessage());
        }
        return data;
    }

    /**
     *转至系统菜添加页面
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/add/{pId}", method = RequestMethod.GET)
    public String add(@PathVariable("pId") String pId, Model model) {
        Menu menu = menuService.selectByPrimaryKey(pId);
        model.addAttribute("pId",menu.getId());
        model.addAttribute("menuType",menu.getMenuType());
        logger.info("转至系统菜单添加页");
        return "auth/menu/add";
    }


    /**
     *转至系统菜修改页面
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String eidt(@PathVariable("id") String id, Model model) {
        Menu menu = menuService.selectByPrimaryKey(id);
        model.addAttribute("menu",menu);
        logger.info("转至系统菜单修改页");
        return "auth/menu/edit";
    }

    /**
     *名称唯一性校验
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public String checkUnique(@RequestParam Map<String, String> paramsMap) {
        logger.info("菜单名称唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int num = menuService.checkMenuName(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("菜单名称唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 添加菜单入库
     * @param menu
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(Menu menu,HttpServletRequest request) {
        logger.info("添加菜单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.SESSION_KEY);
            menu.setCreator(user.getId());
            menu.setId(UUIDGenerator.getUUID());
            int flag =menuService.insert(menu);
            if(flag>0){
                jo.put(Constants.FLAG, true);
                jo.put(Constants.MSG, "添加菜单成功");
            }else{
                jo.put(Constants.MSG, "添加菜单失败");
            }

        } catch (Exception e) {
            logger.error("添加菜单异常" + e.getMessage());
            jo.put(Constants.MSG, "添加菜单失败");
        }
        return jo.toJSONString();
    }

    /**
     * 修改菜单并修改数据库记录
     * @param menu
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(Menu menu,HttpServletRequest request) {
        logger.info("修改菜单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            menuService.updateByPrimaryKey(menu);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改菜单成功");
        } catch (Exception e) {
            logger.error("修改菜单异常" + e.getMessage());
            jo.put(Constants.MSG, "修改菜单失败");
        }
        return jo.toJSONString();
    }


    /**
     * 删除数据并删除数据库记录
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteNode(@RequestParam  String ids ) {
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            boolean flag = menuService.delete(ids);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除菜单成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除菜单异常" + e.getMessage());
            jo.put(Constants.MSG, "删除菜单失败");
        }
        return jo.toJSONString();
    }

    /**
     *根据ID查询菜单详情
     * sunbinbin
     * @return string
     */
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public String detail(@RequestParam  String id) {
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Menu menu = menuService.selectByPrimaryKey(id);
            jo.put("menu", menu);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("根据ID查询菜单失败" + e.getMessage());
            jo.put(Constants.MSG, "根据ID查询菜单失败");
        }
        return jo.toJSONString();
    }


}