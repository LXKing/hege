package com.huak.web.home.thirdpage;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.home.thiredpage.ThiredpageEnergyService;
import com.huak.home.type.ToolVO;
import com.huak.web.home.BaseController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/2<BR>
 * Description:   三级能耗  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/third/fgs")
public class ThirdEnergyFgsController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ThiredpageEnergyService thiredpageEnergyService;
    private static  String ENERGY_TYPE = "energytype";
    private static  String ORG = "orgId";

    /**
     *三级页面-分公司-集团能源类型的能耗趋势图数据加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/total/energyTotalDetail", method = RequestMethod.POST)
    @ResponseBody
    public String energyTotalDetail(ToolVO toolVO,@RequestParam String orgId,HttpServletRequest request) {
        logger.info("三级页面-分公司-集团能源类型的能耗趋势图数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        paramsMap.put("energytype","");
        paramsMap.put(ORG,orgId);
        try {
            Map<String,Object> map =  thiredpageEnergyService.getDatasAll(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-分公司-集团能源类型的能耗趋势图数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-分公司-源、网、站、线、户的各个能源类型的能耗趋势图
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/energyDetail", method = RequestMethod.POST)
    @ResponseBody
    public String energyDetail(ToolVO toolVO,@RequestParam String orgId,HttpServletRequest request) {
        logger.info("三级页面-分公司-源、网、站、线、户的各个能源类型的能耗趋势图数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ORG,orgId);
            Map<String,Object> map =  thiredpageEnergyService.getFgsEnergyTypes(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-分公司-源、网、站、线、户的各个能源类型的能耗趋势图数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-分公司-换热站排名趋势图
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/assessment", method = RequestMethod.POST)
    @ResponseBody
    public String assessment(ToolVO toolVO,@RequestParam String orgId,@RequestParam String type,@RequestParam String orgType,HttpServletRequest request) {
        logger.info("三级页面-分公司-换热站排名趋势图加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ENERGY_TYPE,type);
            paramsMap.put(ORG,orgId);
            paramsMap.put("orgType",orgType);
            Map<String,Object> map =  thiredpageEnergyService.assessments(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-分公司-换热站排名趋势图加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    /**
     *三级页面-分公司-表单数据加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/tablelist", method = RequestMethod.POST)
    @ResponseBody
    public String getTables(ToolVO toolVO,@RequestParam String orgId,HttpServletRequest request) {
        logger.info("三级页面-分公司-表单数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ORG,orgId);
            Map<String, Object> map =  thiredpageEnergyService.getThirdTables(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-分公司-表单数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-分公司-表单数据导出
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/export/{id}", method = RequestMethod.GET)
    public void export(ToolVO toolVO,@PathVariable("id") String id,HttpServletResponse response,HttpServletRequest request) {
        logger.info("三级页面-分公司-表单数据导出");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        OutputStream out = null;
        try {
            paramsMap.put(ORG,id);
            Map<String, Object> map =  thiredpageEnergyService.getThirdTables(paramsMap);
            HSSFWorkbook wb = CommonExcelExport.excelExportThird(map);
            out = response.getOutputStream();
            //输出Excel文件
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding("UTF-8");
            String fileName = "分公司能耗列表.xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            wb.write(out);
            out.flush();
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-分公司-表单数据导出异常" + e.getMessage());
        }finally {
            if(null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("三级页面-分公司-表单数据导出异常" + e.getMessage());
                }
            }
        }
    }

}
