package com.huak.sys;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.init.SysDicAware;
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
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/16<BR>
 * Description:   字典  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/sys/dic")
public class SysDicController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysDicService sysDicService;
    @Resource
    private SysDicAware sysDicAware;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统字典列表页");
        return "/sys/dic/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("字典列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, sysDicService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("字典列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(@RequestParam Map<String, Object> paramsMap,Model model) {
        model.addAllAttributes(paramsMap);
        return "/sys/dic/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(SysDic sysDic, HttpServletRequest request) {
        logger.info("添加字典");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            sysDic.setId(UUIDGenerator.getUUID());
            sysDicService.insertSelective(sysDic);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加字典成功");
            sysDicAware.afterPropertiesSet();
        } catch (Exception e) {
            logger.error("添加字典异常" + e.getMessage());
            jo.put(Constants.MSG, "添加字典失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改字典页");
        try {
            model.addAttribute("sysDic", sysDicService.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("跳转修改字典页异常" + e.getMessage());
        }
        return "/sys/dic/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public String edit(SysDic sysDic) {
        logger.info("修改字典");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            sysDicService.updateByPrimaryKeySelective(sysDic);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改字典成功");
            sysDicAware.afterPropertiesSet();
        } catch (Exception e) {
            logger.error("修改字典异常" + e.getMessage());
            jo.put(Constants.MSG, "修改字典失败");
        }
        return jo.toJSONString();
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteSysDic(@PathVariable("id") String id) {
        logger.info("删除字典");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            sysDicService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除字典成功");
            sysDicAware.afterPropertiesSet();
        } catch (Exception e) {
            logger.error("删除字典异常" + e.getMessage());
            jo.put(Constants.MSG, "删除字典失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出字典列表EXCEL");
        String workBookName = "字典列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("DES", "字典名称");
        cellName.put("TYPE_US", "字典类型(英文)");
        cellName.put("TYPE_ZH", "字典类型(中文)");
        cellName.put("SEQ", "排序");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = sysDicService.exportSysDics(paramsMap);
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
            logger.error("导出字典列表EXCEL异常" + e.getMessage());
        }
    }

    @RequestMapping(value = "/check/name", method = RequestMethod.POST)
    @ResponseBody
    public String checkName(@RequestParam Map<String, Object> paramsMap) {
        logger.info("字典名称(中文)唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = sysDicService.checkName(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("字典名称(中文)唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/seq", method = RequestMethod.POST)
    @ResponseBody
    public String checkSeq(@RequestParam Map<String, Object> paramsMap) {
        logger.info("字典排序校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = sysDicService.checkSeq(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("字典排序校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/type/us", method = RequestMethod.POST)
    @ResponseBody
    public String checkTypeUs(@RequestParam Map<String, Object> paramsMap) {
        logger.info("字典类型(英文)校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = sysDicService.checkTypeUs(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("字典类型(英文校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/type/zh", method = RequestMethod.POST)
    @ResponseBody
    public String checkTypeZh(@RequestParam Map<String, Object> paramsMap) {
        logger.info("字典类型(中文)校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = sysDicService.checkTypeZh(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("字典类型(中文)校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
