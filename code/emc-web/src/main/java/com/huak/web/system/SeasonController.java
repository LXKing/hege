package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Org;
import com.huak.season.model.Season;
import com.huak.sys.SeasonService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.system<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-10-18<BR>
 * Description:  采暖季   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/season")
public class SeasonController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SeasonService seasonService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统采暖季列表页面");
        return "/system/season/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap ,HttpServletRequest request, Page page) {
        logger.info("采暖季列表页分页查询");
        JSONObject jo = new JSONObject();
        try {
            HttpSession session = request.getSession();
            Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);
            if(org.getComId()==null) {
                paramsMap.put("comId","flag");
            }else {
                paramsMap.put("comId",org.getComId());
            }
            PageResult<Season> result = seasonService.queryByPage(paramsMap, page);
            jo.put(Constants.LIST, result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("采暖季列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);
        model.addAttribute("comId",org.getComId());
        model.addAttribute("todayNow", seasonService.getNowTime());
        return "/system/season/add";
    }

    @ResponseBody
    @RequestMapping(value = "/checkname", method = RequestMethod.POST)
    public String checkNode(@RequestParam  String name,
                            @RequestParam  String comId){
        System.out.println(name+"-------");
        JSONObject jo = new JSONObject();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        map.put("comId",comId);
        boolean  flag =   seasonService.checkName(map);
        jo.put(Constants.FLAG,flag);
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String checkTime(@RequestParam  String sdate,
                            @RequestParam  String edate,
                            @RequestParam  String comId){
        JSONObject jo = new JSONObject();
        Map<String,Object> map = new HashMap<String,Object>();
        System.out.println(comId+"dadaskmlndaslndasldandalndandis");
        map.put("sdate",sdate);
        map.put("edate",edate);
        map.put("comId",comId);
        boolean  flag =   seasonService.checkTime(map);
        jo.put(Constants.FLAG,flag);
        return jo.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/addvalue", method = RequestMethod.POST)
    public String addNodeValue(@RequestParam  String name,
                               @RequestParam  String sdate,
                               @RequestParam  String edate,
                               @RequestParam  String comId){
        logger.info("添加采暖");
        JSONObject jo = new JSONObject();
        try {
            // TODO 添加session，创建者
            Season season = new Season();
            season.setId(UUIDGenerator.getUUID());
            season.setComid(comId);
            season.setName(name);
            season.setSdate(sdate);
            season.setEdate(edate);
            seasonService.insert(season);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加采暖成功");
        } catch (Exception e) {
            logger.error("添加采暖异常" + e.getMessage());
            jo.put(Constants.MSG, "添加采暖失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改采暖页");
        try {
            model.addAttribute("season", seasonService.selectByPrimaryKey(id));
            model.addAttribute("todayNow", seasonService.getNowTime());
        } catch (Exception e) {
            logger.error("跳转修改采暖页异常" + e.getMessage());
        }
        return "/system/season/edit";
    }
    @ResponseBody
    @RequestMapping(value = "/editvalue", method = RequestMethod.POST)
    public String editValue(@RequestParam  String name,
                            @RequestParam  String sdate,
                            @RequestParam  String edate,
                            @RequestParam  String id,
                            HttpServletRequest request){
        logger.info("修改采暖");
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();
            Season season = new Season();
            season.setId(id);
            season.setName(name);
            season.setSdate(sdate);
            season.setEdate(edate);
            seasonService.updateByPrimaryKeySelective(season);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改采暖成功");
        } catch (Exception e) {
            logger.error("修改采暖异常" + e.getMessage());
            jo.put(Constants.MSG, "修改采暖失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFeed(@PathVariable("id") String id) {
        logger.info("删除采暖");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            seasonService.delete(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除采暖成功");
        } catch (Exception e) {
            logger.error("删除采暖异常" + e.getMessage());
            jo.put(Constants.MSG, "删除采暖失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出采暖季列表EXCEL");
        String workBookName = "采暖季列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("CNAME", "公司名称");
        cellName.put("NAME", "采暖季名称");
        cellName.put("SDATE", "开始时间");
        cellName.put("EDATE", "结束时间");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = seasonService.exportSeason(paramsMap);
            HSSFWorkbook wb = CommonExcelExport.excelExport(cellName, cellValues);
            //response输出流导出excel
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding("UTF-8");
            String fileName = workBookName + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("导出采暖季列表EXCEL异常" + e.getMessage());
        }
    }
}
