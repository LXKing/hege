package com.huak.sys;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.sys.model.IndexType;
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
 * Date: 2017/9/11<BR>
 * Description:  指标类型   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/index/type")
public class IndexTypeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private  IndexTypeService indexTypeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统指标类型列表页");
        return "/sys/index/type/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("指标类型列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, indexTypeService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("指标类型列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(@RequestParam Map<String, Object> paramsMap,Model model) {
        model.addAllAttributes(paramsMap);
        return "/sys/index/type/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(IndexType indexType, HttpServletRequest request) {
        logger.info("添加指标类型");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            indexType.setId(UUIDGenerator.getUUID());
            indexTypeService.insertSelective(indexType);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加指标类型成功");
        } catch (Exception e) {
            logger.error("添加指标类型异常" + e.getMessage());
            jo.put(Constants.MSG, "添加指标类型失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改指标类型页");
        try {
            model.addAttribute("indexType", indexTypeService.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("跳转修改指标类型页异常" + e.getMessage());
        }
        return "/sys/index/type/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public String edit(IndexType indexType) {
        logger.info("修改指标类型");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            indexTypeService.updateByPrimaryKeySelective(indexType);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改指标类型成功");
        } catch (Exception e) {
            logger.error("修改指标类型异常" + e.getMessage());
            jo.put(Constants.MSG, "修改指标类型失败");
        }
        return jo.toJSONString();
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteIndexType(@PathVariable("id") String id) {
        logger.info("删除指标类型");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            indexTypeService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除指标类型成功");
        } catch (Exception e) {
            logger.error("删除指标类型异常" + e.getMessage());
            jo.put(Constants.MSG, "删除指标类型失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出指标类型列表EXCEL");
        String workBookName = "指标类型列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("NAME", "名称");
        cellName.put("UNIT_TYPE", "单位类型");
        cellName.put("UNIT_METER", "用量单位");
        cellName.put("ENERGY_TYPE", "能源类型");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = indexTypeService.exportIndexTypes(paramsMap);
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
            logger.error("导出指标类型列表EXCEL异常" + e.getMessage());
        }
    }

    @RequestMapping(value = "/check/name", method = RequestMethod.POST)
    @ResponseBody
    public String checkNameZh(@RequestParam Map<String, Object> paramsMap) {
        logger.info("指标类型名称唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = indexTypeService.checkName(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("指标类型名称唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


}
