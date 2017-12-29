package com.huak.org;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.StringUtils;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.org.model.Company;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.auth<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/30<BR>
 * Description:   公司  <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/company")
public class CompanyController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CompanyService companyService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统公司列表页");
        return "/org/company/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("公司列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, companyService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("公司列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/weather/city", method = RequestMethod.PATCH)
    @ResponseBody
    public String weatherCity(@RequestParam Map<String, Object> paramsMap) {
        logger.info("查询天气城市列表");

        JSONObject jo = new JSONObject();
        try {
            if(paramsMap.get("wPCode")==null||paramsMap.get("wPCode").toString().trim().equals("")){
                jo.put(Constants.LIST, "");
            }else{
                jo.put(Constants.LIST, companyService.selectWeatherCity(paramsMap));
            }

        } catch (Exception e) {
            logger.error("查询天气城市列表异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        Map<String, Object> paramsMap = new HashMap();
        paramsMap.put("wLevel",1);
        List<Map<String,Object>> cites = companyService.selectWeatherCity(paramsMap);
        model.addAttribute("cites",cites);
        return "/org/company/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(Company company) {
        logger.info("添加公司");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            String uuid = UUIDGenerator.getUUID();
            company.setId(uuid);
            /*如果没有wcode 则表示气象选择的是自定义接口*/
            if(StringUtils.isEmpty(company.getWcode())){
                company.setWcode(uuid);
            }
            companyService.insertSelective(company);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加公司成功");
        } catch (Exception e) {
            logger.error("添加公司异常" + e.getMessage());
            jo.put(Constants.MSG, "添加公司失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改公司页");
        try {
            Map<String, Object> paramsMap = new HashMap();
            paramsMap.put("wLevel",1);
            List<Map<String,Object>> cites = companyService.selectWeatherCity(paramsMap);
            model.addAttribute("cites",cites);
            Company company = companyService.selectByPrimaryKey(id);
            String wcode = company.getWcode();
            /*判断天气采集类型
            * 自定义 id == code
            * 气象节点 id!=code
            * */
            if(!company.getId().equals(wcode)){
                Map<String,Object> weatherCity = companyService.selectParentWeatherByCode(wcode);
                Map<String,Object> weatherPrivence = companyService.selectParentWeatherByCode(weatherCity.get("W_CODE").toString());
                String pcode = weatherPrivence.get("W_CODE").toString();
                String ccode = weatherCity.get("W_CODE").toString();
                paramsMap.clear();
                paramsMap.put("wPCode",pcode);
                List<Map<String,Object>> pcties = companyService.selectWeatherCity(paramsMap);
                paramsMap.put("wPCode",ccode);
                List<Map<String,Object>> ccties = companyService.selectWeatherCity(paramsMap);
                model.addAttribute("ccode",pcode);
                model.addAttribute("pcties",pcties);
                model.addAttribute("pcode",ccode);
                model.addAttribute("ccties",ccties);
            }
            model.addAttribute("company", company);
        } catch (Exception e) {
            logger.error("跳转修改公司页异常" + e.getMessage());
        }
        return "/org/company/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public String edit(Company company) {
        logger.info("修改公司");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            companyService.updateByPrimaryKeySelective(company);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改公司成功");
        } catch (Exception e) {
            logger.error("修改公司异常" + e.getMessage());
            jo.put(Constants.MSG, "修改公司失败");
        }
        return jo.toJSONString();
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteRole(@PathVariable("id") String id) {
        logger.info("删除公司");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            companyService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除公司成功");
        } catch (Exception e) {
            logger.error("删除公司异常" + e.getMessage());
            jo.put(Constants.MSG, "删除公司失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出公司列表EXCEL");
        String workBookName = "公司列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("CNAME", "公司名称");
        cellName.put("NEXT_DES", "公司下级翻译");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = companyService.exportCompanys(paramsMap);
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
            logger.error("导出公司列表EXCEL异常" + e.getMessage());
        }
    }

    @RequestMapping(value = "/lista", method = RequestMethod.POST)
    @ResponseBody
    public String list() {
        logger.info("首页顶部显示下拉框");

        JSONObject jo = new JSONObject();
        Map<String,Object> paramsMap=null;
        try {
            jo.put(Constants.LIST, companyService.selectAllByMap(paramsMap));
        } catch (Exception e) {
            logger.error("首页顶部显示下拉框" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/table/name", method = RequestMethod.POST)
    @ResponseBody
    public String checkTableName(@RequestParam Map<String, Object> paramsMap) {
        logger.info("数据表名称唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = companyService.checkTableName(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("数据表名称唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


}
