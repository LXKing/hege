package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.User;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.health.IndexRecordService;
import com.huak.health.model.IndexRecord;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.system<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/8<BR>
 * Description: 指标配置    <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/index/allocation")
public class IndexAllocationController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String COMPANY = "company";
    private static final String ORG = "org";
    @Resource
    private IndexRecordService indexRecordService;

    @RequestMapping(method = RequestMethod.GET)
    public String page(HttpServletRequest request,Model model){
        logger.info("打开指标配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(COMPANY,company);
        model.addAttribute(ORG,org);
        return "system/allocation/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPage(HttpServletRequest request,Model model){
        logger.info("打开添加指标配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(COMPANY,company);
        model.addAttribute(ORG,org);
        return "system/allocation/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(IndexRecord indexRecord, HttpServletRequest request) {
        logger.info("添加指标配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute(Constants.SESSION_KEY);

            indexRecord.setId(UUIDGenerator.getUUID());
            indexRecord.setCreator(user.getId());
            indexRecordService.insertSelective(indexRecord);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加指标配置成功");
        } catch (Exception e) {
            logger.error("添加指标配置异常" + e.getMessage());
            jo.put(Constants.MSG, "添加指标配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("指标配置列表分页查询");
        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, indexRecordService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("指标配置列表分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editPage(HttpServletRequest request,Model model,@PathVariable("id") String id){
        logger.info("打开修改指标配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);

        Map<String,Object> indexRecord = indexRecordService.selectUpdateMap(id);
        model.addAttribute("company",company);
        model.addAttribute("indexRecord",indexRecord);
        return "system/allocation/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public String edit(IndexRecord indexRecord) {
        logger.info("添加指标配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            indexRecordService.updateByPrimaryKeySelective(indexRecord);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加指标配置成功");
        } catch (Exception e) {
            logger.error("添加指标配置异常" + e.getMessage());
            jo.put(Constants.MSG, "添加指标配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        logger.info("删除指标配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            indexRecordService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除指标配置成功");
        } catch (Exception e) {
            logger.error("删除指标配置异常" + e.getMessage());
            jo.put(Constants.MSG, "删除指标配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/type", method = RequestMethod.POST)
    @ResponseBody
    public String checkType(@RequestParam Map<String, Object> paramsMap) {
        logger.info("同一用能单位指标类型唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = indexRecordService.checkType(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("同一用能单位指标类型唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
