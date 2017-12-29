package com.huak.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.org.AdministrativeService;
import com.huak.org.OrgService;
import com.huak.sys.model.Administrative;
import com.huak.sys.model.SysDic;
import com.huak.sys.type.AdminLevelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak<BR>
 * Author:  liurm  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/3<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/active")
public class AdministrativeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        System.out.println("");
        logger.info("");
        return "index";
    }

    @Resource
    private OrgService orgService;
    @Resource
    private AdministrativeService administrativeService;


    @ResponseBody
    @RequestMapping(value = "/admintree", method = RequestMethod.GET)
    public Object ztreeValue(){
        System.out.print("-------------------controller----------------------------");
        List<Administrative> as = administrativeService.findAllAdministrative();
        System.out.print("-----------------------------------"+as.get(0).getAdmName());
        return JSON.toJSON(as);
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/ztree", method = RequestMethod.GET)
    public String ztree(){
        return "sys/activetree/list";
    }
    @ResponseBody
    @RequestMapping(value = "/checkcode", method = RequestMethod.POST)
    public String checkNode(@RequestParam  String admCode
                                    ){
        JSONObject jo = new JSONObject();
        List<Administrative>  flag = administrativeService.getAdministrativeSize(admCode);

          if(flag.size()>0){
              jo.put(Constants.FLAG,false);
          }else{
              jo.put(Constants.FLAG, true);
          }
        return jo.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String checkName(@RequestParam  String admName
    ){
        JSONObject jo = new JSONObject();
        List<Administrative>  flag = administrativeService.getAdministrativeSizeCheckName(admName);

        if(flag.size()>0){
            jo.put(Constants.FLAG,false);
        }else{
            jo.put(Constants.FLAG, true);
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/addnode/{id}", method = RequestMethod.GET)
    public String addNode(Model model, @PathVariable("id") String id){
        String code = "townCountry";
        List<SysDic> dic = orgService.selectSysDicAll(code);
        List<AdminLevelType> level =  AdminLevelType.toList(AdminLevelType.class);
        model.addAttribute("id",id);
        model.addAttribute("admType", dic);
        model.addAttribute("level", level);
        return "sys/activetree/add";
    }
    @RequestMapping(value = "/editnode/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改行政区划页");
        try {
            String code = "townCountry";
            List<SysDic> dic = orgService.selectSysDicAll(code);
            List<AdminLevelType> level =  AdminLevelType.toList(AdminLevelType.class);
            Administrative adm = administrativeService.selectByPrimaryKey(id);
            model.addAttribute("id",id);
            model.addAttribute("admType", dic);
            model.addAttribute("level", level);
            model.addAttribute("adm", adm);
        } catch (Exception e) {
            logger.error("跳转修改行政区划页异常" + e.getMessage());
        }
        return "/sys/activetree/edit";
    }
    @ResponseBody
    @RequestMapping(value = "/tree/edit", method = RequestMethod.POST)
    public String edit(Administrative adm , HttpServletRequest request) {
        logger.info("修改行政区划");

        JSONObject jo = new JSONObject();
        try {
            boolean flag = administrativeService.updateByPrimaryKeySelective(adm);
            if(flag){
                jo.put(Constants.FLAG, true);
                jo.put(Constants.MSG, "修改行政区划成功");
            }else{
                jo.put(Constants.FLAG, false);
                jo.put(Constants.MSG, "修改行政区划失败");
            }
        } catch (Exception e) {
            logger.error("修改行政区划异常" + e.getMessage());
            jo.put(Constants.MSG, "修改行政区划失败");
        }
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/addnodevalue", method = RequestMethod.POST)
    public String addNodeValue(Administrative adm,HttpServletRequest request){
        logger.info("添加行政区划");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
            try {
            // TODO 添加session，创建者
//            HttpSession session = request.getSession();
//           User user =  (User)session.getAttribute(Constants.SESSION_KEY);
            boolean flag = administrativeService.insert(adm);
            jo.put(Constants.FLAG, flag);
            jo.put(Constants.MSG, "添加行政区划成功");
        } catch (Exception e) {
            logger.error("添加行政区划" + e.getMessage());
            jo.put(Constants.MSG, "添加行政区划");
        }
        return jo.toJSONString();
    }


    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feed(){
        return "org/feed/list";
    }

    @RequestMapping(value = "/node", method = RequestMethod.GET)
    public String node(){
        return "org/node/list";
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteNode(@RequestParam  String ids ){
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {

            boolean  flag =   administrativeService.deleteByPrimaryKey(ids);
              if(flag){
                  jo.put(Constants.FLAG, flag);
                  jo.put(Constants.MSG, "删除机构成功");
              }else{
                  jo.put(Constants.FLAG, flag);
                  jo.put(Constants.MSG, "删除机构失败");
              }

        } catch (Exception e) {
            logger.error("删除机构异常" + e.getMessage());
            jo.put(Constants.MSG, "删除机构失败");
        }
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public String detail(@RequestParam  String id) {
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Administrative active = administrativeService.selectByPrimaryKey(id);
            jo.put("activeDetail", active);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("根据ID查询行政失败" + e.getMessage());
            jo.put(Constants.MSG, "根据ID查询行政失败");
        }
        return jo.toJSONString();
    }

}
