package com.huak.org;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.org.model.Oncenet;
import com.huak.sys.model.SysDic;
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
 * File name:  com.huak.org<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/17<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/oncenet")
public class OncenetController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private OncenetService  oncenetService;

    @Resource
    private OrgService orgService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage(Model model) {
        logger.info("转至系统管网列表页");
        String code = "pipeType";
        List<SysDic> dic = orgService.selectSysDicAll(code);
        model.addAttribute("sysdic",dic);
        return "/org/onenet/list";
    }
    @RequestMapping(value = "/listpage", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("一次管网列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, oncenetService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("一次管网列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/add/{orgId}", method = RequestMethod.GET)
    public String addPage(Model model,@PathVariable("orgId") String orgId) {
        String code = "pipeType";
        String typeHeat="supportheattype";
        List<SysDic> dic = orgService.selectSysDicAll(code);
        List<SysDic> dicheat = orgService.selectSysDicAll(typeHeat);
        model.addAttribute("sysdic",dic);
        model.addAttribute("dicheat",dicheat);
        model.addAttribute("orgId",orgId);
        return "/org/onenet/add";
    }

    @RequestMapping(value = "/addvalue", method = RequestMethod.POST)
    @ResponseBody
    public String add(Oncenet oncenet, HttpServletRequest request) {
        logger.info("添加管网");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();
            oncenet.setId(UUIDGenerator.getUUID());
            oncenetService.insert(oncenet);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加管网成功");
        } catch (Exception e) {
            logger.error("添加管网异常" + e.getMessage());
            jo.put(Constants.MSG, "添加管网失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改热源页");
        try {
            String code = "pipeType";
            List<SysDic> dic = orgService.selectSysDicAll(code);
            model.addAttribute("sysdic",dic);
            Oncenet oncenet = oncenetService.selectByPrimaryKey(id);
            String typeHeat="supportheattype";
            List<SysDic> dicheat = orgService.selectSysDicAll(typeHeat);
            model.addAttribute("dicheat",dicheat);
            model.addAttribute("oncenet", oncenet);
        } catch (Exception e) {
            logger.error("跳转修改热源页异常" + e.getMessage());
        }
        return "/org/onenet/edit";
    }

    @RequestMapping(value = "/editvalue", method = RequestMethod.POST)
    @ResponseBody
    public String editValue(Oncenet oncenet) {
        logger.info("修改管网");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int i = oncenetService.updateByPrimaryKeySelective(oncenet);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改管网成功");
        } catch (Exception e) {
            logger.error("修改管网异常" + e.getMessage());
            jo.put(Constants.MSG, "修改管网失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteOncenet(@PathVariable("id") String id) {
        logger.info("删除管网");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            oncenetService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除管网成功");
        } catch (Exception e) {
            logger.error("删除管网异常" + e.getMessage());
            jo.put(Constants.MSG, "删除管网失败");
        }
        return jo.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String checkNode(@RequestParam  String netName,
                            @RequestParam  String comId ){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("netName",netName);
        map.put("comId",comId);
        JSONObject jo = new JSONObject();
        boolean  flag =   oncenetService.checkNetName(map);

        if(flag){
            jo.put(Constants.FLAG,false);
        }else{
            jo.put(Constants.FLAG, true);
        }
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/checkcode", method = RequestMethod.POST)
    public String checkNodeCode(@RequestParam  String netCode,
                            @RequestParam  String comId ){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("netCode",netCode);
        map.put("comId",comId);
        JSONObject jo = new JSONObject();
        boolean  flag =   oncenetService.checkNetCode(map);

        if(flag){
            jo.put(Constants.FLAG,false);
        }else{
            jo.put(Constants.FLAG, true);
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出管网列表EXCEL");
        String workBookName = "一次网列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)

        cellName.put("NET_NAME", "管网名称");
        cellName.put("NET_CODE", "管网代码");
        cellName.put("NET_TYPE_NAME", "管网类型");
        cellName.put("LENGTH", "长度");
        cellName.put("CELL_NUM", "小室数量");
        cellName.put("PART_NUM", "管段数量");
        cellName.put("HEAT_NAME", "供热类型");
        cellName.put("MEDIUM", "输送介质");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = oncenetService.exporNets(paramsMap);
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
            logger.error("导出管网列表EXCEL异常" + e.getMessage());
        }
    }
}
