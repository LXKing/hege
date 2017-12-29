package com.huak.org;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.org.type.OrgType;
import com.huak.sys.model.SysDic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/org")
public class OrgController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        System.out.println("");
        logger.info("");
        return "index";
    }

    @Resource
    private OrgService orgService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/ztree", method = RequestMethod.GET)
    public String ztree(){
        return "org/orgtree/list";
    }
    @ResponseBody
    @RequestMapping(value = "/checknode", method = RequestMethod.POST)
    public String checkNode(@RequestParam  String orgName,
                            @RequestParam  String comId
                                    ){

        JSONObject jo = new JSONObject();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("orgName",orgName);
        params.put("comId",comId);
        List<Map<String,Object>>  flag = orgService.selectOrgByMap(params);

          if(flag.size()>0){
              jo.put(Constants.FLAG,false);
          }else{
              jo.put(Constants.FLAG, true);
          }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/addnode/{id}", method = RequestMethod.GET)
    public String addNode(Model model, @PathVariable("id") String id){
        String code = "orgType";
        List<Company> company = orgService.selectCompanyAll();
        List<SysDic> dic = orgService.selectSysDicAll(code);
        List<OrgType> list =  OrgType.toList( OrgType.class);
        model.addAttribute("id",id);
//        model.addAttribute("company",company);
//        model.addAttribute("sysdic",list);
        return "org/orgtree/add";
    }
    @RequestMapping(value = "/editnode/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改机构页");
        try {
            String code = "orgType";
            List<Company> company = orgService.selectCompanyAll();
            List<SysDic> dic = orgService.selectSysDicAll(code);
            List<OrgType> list =  OrgType.toList(OrgType.class);
            Org org =  orgService.selectByPrimaryKey(id);
//            model.addAttribute("company",company);
//            model.addAttribute("sysdic",list);
            model.addAttribute("org", org);
        } catch (Exception e) {
            logger.error("跳转修改机构页异常" + e.getMessage());
        }
        return "/org/orgtree/edit";
    }
    @ResponseBody
    @RequestMapping(value = "/tree/edit", method = RequestMethod.POST)
    public String edit(Org org , HttpServletRequest request) {
        logger.info("修改机构");

        JSONObject jo = new JSONObject();
        try {
            boolean flag = orgService.updateOrg(org);
            if(flag){
                jo.put(Constants.FLAG, true);
                jo.put(Constants.MSG, "修改机构成功");
            }else{
                jo.put(Constants.FLAG, false);
                jo.put(Constants.MSG, "修改机构失败");
            }
        } catch (Exception e) {
            logger.error("修改机构异常" + e.getMessage());
            jo.put(Constants.MSG, "修改机构失败");
        }
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/addnodevalue", method = RequestMethod.POST)
    public String addNodeValue(Org org,HttpServletRequest request){
        logger.info("添加机构");
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
        jo.put(Constants.FLAG, false);
            try {
            // TODO 添加session，创建者
//            HttpSession session = request.getSession();
//           User user =  (User)session.getAttribute(Constants.SESSION_KEY);
            boolean flag = orgService.insertOrg(org);
            jo.put(Constants.FLAG, flag);
            jo.put(Constants.MSG, "添加机构成功");
        } catch (Exception e) {
            logger.error("添加机构异常" + e.getMessage());
            jo.put(Constants.MSG, "添加机构失败");
        }
        return jo.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/ztreeValue", method = RequestMethod.GET)
    public Object ztree(@RequestParam Map<String, Object> paramsMap){
        System.out.print("-------------------controller----------------------------");
        List<Org> as = orgService.selectOrgAll(paramsMap);
        return JSON.toJSON(as);
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

            boolean  flag =   orgService.delete(ids);
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
            Org org = orgService.selectByPrimaryKey(id);
            jo.put("orgDetail", org);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("根据ID查询机构失败" + e.getMessage());
            jo.put(Constants.MSG, "根据ID查询机构失败");
        }
        return jo.toJSONString();
    }

}
