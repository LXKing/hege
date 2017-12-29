
package com.huak.auth;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.Func;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.init.MenuAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.auth<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/9/29<BR>
 * Description:  功能控制器   <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/func")
public class FuncController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MenuService menuService;
    @Resource
    private FuncService funcService;
    @Resource
    private MenuAware menuAware;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listPage(){
        logger.info("转至系统功能列表页");
        return "/auth/func/list";
    }


/**
     * 初始化
     * @param paramsMap
     * @return
     */

    @RequestMapping(value = "/list",method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String,Object> paramsMap){
        logger.info("功能列表页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, funcService.selectAllByMap(paramsMap));
        } catch (Exception e) {
            logger.error("功能列表页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id){
        logger.info("跳转功能编辑页");
        try {
            Func func = funcService.selectByPrimaryKey(id);
            model.addAttribute("func",func);
            model.addAttribute("menu",menuService.selectByPrimaryKey(func.getMenuId()));
        } catch (Exception e) {
            logger.error("跳转功能编辑页异常" + e.getMessage());
        }
        return "/auth/func/edit";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    @ResponseBody
    public String edit(Func func){
        logger.info("修改功能");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            funcService.updateByPrimaryKeySelective(func);
            jo.put(Constants.FLAG ,true);
            jo.put(Constants.MSG ,"修改功能成功");
            //重新加载缓存
            //menuAware.afterPropertiesSet();
        } catch (Exception e) {
            logger.error("修改功能异常" + e.getMessage());
            jo.put(Constants.MSG ,"修改功能失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add/{menuId}",method = RequestMethod.GET)
    public String addPage(@PathVariable("menuId") String menuId, Model model) {
        logger.info("跳转功能添加页");
        try {
            model.addAttribute("menu",menuService.selectByPrimaryKey(menuId));
        } catch (Exception e) {
            logger.error("跳转功能添加页异常" + e.getMessage());
        }
        return "/auth/func/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(Func func){
        logger.info("添加功能");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            func.setId(UUIDGenerator.getUUID());
            funcService.insertSelective(func);
            jo.put(Constants.FLAG ,true);
            jo.put(Constants.MSG ,"添加功能成功");
            //重新加载缓存
            //menuAware.afterPropertiesSet();
        } catch (Exception e) {
            logger.error("添加功能异常" + e.getMessage());
            jo.put(Constants.MSG ,"添加功能失败");
        }
        return jo.toJSONString();
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        logger.info("删除功能");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            funcService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除功能成功");
            //重新加载缓存
            //menuAware.afterPropertiesSet();
        } catch (Exception e) {
            logger.error("删除功能异常" + e.getMessage());
            jo.put(Constants.MSG, "删除功能失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/uname",method = RequestMethod.POST)
    @ResponseBody
    public String checkUName(@RequestParam Map<String,String> paramsMap){
        logger.info("功能唯一标识唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            Long num = funcService.checkUName(paramsMap);
            if(num==0){
                jo.put(Constants.FLAG ,true);
            }
        }catch (Exception e){
            logger.error("功能唯一标识唯一性校验异常"+e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/name",method = RequestMethod.POST)
    @ResponseBody
    public String checkName(@RequestParam Map<String,String> paramsMap){
        logger.info("功能名称唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            Long num = funcService.checkName(paramsMap);
            if(num==0){
                jo.put(Constants.FLAG ,true);
            }
        }catch (Exception e){
            logger.error("功能名称唯一性校验异常"+e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/search",method = RequestMethod.POST)
    @ResponseBody
    public String checkSearch(@RequestParam Map<String,String> paramsMap){
        logger.info("功能查询唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            Long num = funcService.checkSearch(paramsMap);
            if(num==0){
                jo.put(Constants.FLAG ,true);
            }
        }catch (Exception e){
            logger.error("功能查询唯一性校验异常"+e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/cache",method = RequestMethod.POST)
    @ResponseBody
    public String cache(){
        logger.info("刷新缓存");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            //重新加载缓存
            menuAware.afterPropertiesSet();

            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "刷新缓存成功");

        } catch (Exception e) {
            logger.error("刷新缓存异常" + e.getMessage());
            jo.put(Constants.MSG, "刷新缓存失败");
        }
        return jo.toJSONString();
    }
}

