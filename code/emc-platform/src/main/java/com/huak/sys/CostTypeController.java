package com.huak.sys;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.sys.model.CostType;
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
 * Date: 2017/6/7<BR>
 * Description:   成本类型  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/cost/type")
public class CostTypeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CostTypeService costTypeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统成本类型列表页");
        return "/sys/cost/type/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("成本类型列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, costTypeService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("成本类型列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(@RequestParam Map<String, Object> paramsMap,Model model) {
        model.addAllAttributes(paramsMap);
        return "/sys/cost/type/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(CostType costType, HttpServletRequest request) {
        logger.info("添加成本类型");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            costType.setId(UUIDGenerator.getUUID());
            costTypeService.insertSelective(costType);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加成本类型成功");
        } catch (Exception e) {
            logger.error("添加成本类型异常" + e.getMessage());
            jo.put(Constants.MSG, "添加成本类型失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改成本类型页");
        try {
            model.addAttribute("costType", costTypeService.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("跳转修改成本类型页异常" + e.getMessage());
        }
        return "/sys/cost/type/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public String edit(CostType costType) {
        logger.info("修改成本类型");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            costTypeService.updateByPrimaryKeySelective(costType);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改成本类型成功");
        } catch (Exception e) {
            logger.error("修改成本类型异常" + e.getMessage());
            jo.put(Constants.MSG, "修改成本类型失败");
        }
        return jo.toJSONString();
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCostType(@PathVariable("id") String id) {
        logger.info("删除成本类型");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            costTypeService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除成本类型成功");
        } catch (Exception e) {
            logger.error("删除成本类型异常" + e.getMessage());
            jo.put(Constants.MSG, "删除成本类型失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出成本类型列表EXCEL");
        String workBookName = "成本类型列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("NAME_ZH", "类型名称(中文)");
        cellName.put("NAME_EN", "类型名称(英文)");
        cellName.put("DOSAGE_UNIT", "用量单位");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = costTypeService.exportCostTypes(paramsMap);
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
            logger.error("导出成本类型列表EXCEL异常" + e.getMessage());
        }
    }

    @RequestMapping(value = "/check/name/zh", method = RequestMethod.POST)
    @ResponseBody
    public String checkNameZh(@RequestParam Map<String, Object> paramsMap) {
        logger.info("成本类型名称(中文)唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = costTypeService.checkNameZh(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("成本类型名称(中文)唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/name/en", method = RequestMethod.POST)
    @ResponseBody
    public String checkNameEn(@RequestParam Map<String, Object> paramsMap) {
        logger.info("成本类型名称(英文)唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = costTypeService.checkNameEn(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("成本类型名称(英文)唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
