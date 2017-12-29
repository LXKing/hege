package com.huak.web.home.thirdpage;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.home.thiredpage.ThiredpageEnergyService;
import com.huak.home.type.ToolVO;
import com.huak.org.OrgService;
import com.huak.org.model.Org;
import com.huak.web.home.BaseController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
@RequestMapping("/third/energy")
public class ThirdEnergyController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ThiredpageEnergyService thiredpageEnergyService;

    @Resource
    private OrgService orgService;
    
    private static  String ENERGY_TYPE = "energytype";
    private static  String ORG_TYPE = "orgType";
    /**
     * 跳转三级单耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/page/{type}", method = RequestMethod.GET)
    public String secondEconPage(Model model,HttpServletRequest request,@PathVariable("type")String type){
        logger.info("跳转能源类型三级能耗页面");
        model.addAttribute("type",type);
        return "third/energy";
    }

    /**
     * 跳转用能单位类型三级能耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/unit/{type}", method = RequestMethod.GET)
    public String unitPage(Model model,HttpServletRequest request,@PathVariable("type")String type){
        logger.info("跳转用能单位类型三级能耗页面");
        model.addAttribute("type",type);
        return "third/energy-unit";
    }

    /**
     * 跳转分公司三级能耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/fgs/{id}", method = RequestMethod.GET)
    public String fgsPage(Model model,HttpServletRequest request,@PathVariable("id")String id){
        logger.info("跳转分公司三级能耗页面");
        model.addAttribute("id", id);
        Org org  = orgService.selectByPrimaryKey(id);
        model.addAttribute("orgName",org.getOrgName());
        return "third/energy-fgs";
    }

    /**
     *三级页面-集团能源类型的能耗趋势图数据加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/energyTotalDetail", method = RequestMethod.GET)
    @ResponseBody
    public String energyTotalDetail(ToolVO toolVO,@RequestParam String type,HttpServletRequest request)throws Exception {
        logger.info("三级页面-集团能源类型的能耗趋势图数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
//        try {
            paramsMap.put(ENERGY_TYPE,type);
            Map<String,Object> map =  thiredpageEnergyService.getDatasAll(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }

//        } catch (Exception e) {
//            jo.put(Constants.FLAG,false);
//            logger.error("三级页面-集团能源类型的能耗趋势图数据加载异常" + e.getMessage());
//        }
        return jo.toJSONString();
    }

    /**
     *三级页面-源、网、站、线、户的各个能源类型的能耗趋势图
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/energyDetail", method = RequestMethod.POST)
    @ResponseBody
    public String energyDetail(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-源、网、站、线、户的各个能源类型的能耗趋势图数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ENERGY_TYPE,type);
            Map<String,Object> map =  thiredpageEnergyService.getDatas(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-源、网、站、线、户的各个能源类型的能耗趋势图数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-换热站排名趋势图
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/assessment", method = RequestMethod.POST)
    @ResponseBody
    public String assessment(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-换热站排名趋势图数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ENERGY_TYPE,type);
            Map<String,Object> map =  thiredpageEnergyService.getassessment(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-换热站排名趋势图数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    /**
     *三级页面-表单数据加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/tablelist", method = RequestMethod.POST)
    @ResponseBody
    public String getTables(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-表单数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ENERGY_TYPE,type);
            Map<String, Object> map =  thiredpageEnergyService.getTable(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-表单数据加载加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }



    /**
     *三级页面-用能单位类型-能源对比数据加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/energyDetail", method = RequestMethod.POST)
    @ResponseBody
    public String unitEnergyDetail(ToolVO toolVO,@RequestParam String type,@RequestParam String energyType,HttpServletRequest request){
        logger.info("三级页面-用能单位类型-能源对比数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ORG_TYPE,type);
            paramsMap.put(ENERGY_TYPE,energyType);
            Map<String,Object> map =  thiredpageEnergyService.getUnitEnergyDetail(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-用能单位类型-能源对比数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    /**
     *三级页面-用能单位类型-能源能耗排名加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/unitAssessment", method = RequestMethod.POST)
    @ResponseBody
    public String unitAssessment(ToolVO toolVO,@RequestParam String type,@RequestParam String energyType,HttpServletRequest request) {
        logger.info("三级页面-用能单位类型-能源能耗排名加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            logger.info(type+"..........................排名");
            logger.info(energyType+"......................排名");
            paramsMap.put(ORG_TYPE,type);
            paramsMap.put(ENERGY_TYPE,energyType);
            Map<String,Object> map =  thiredpageEnergyService.getUnitAssessments(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        }catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-用能单位类型-个能源能耗排名加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-用能单位类型-个能源能耗趋势加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/unitAllAssessment", method = RequestMethod.POST)
    @ResponseBody
    public String unitAllAssessment(ToolVO toolVO,@RequestParam String type,@RequestParam String energyType,HttpServletRequest request) {
        logger.info("三级页面-用能单位类型-个能源能耗趋势加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            logger.info(type+"..........................能源能耗趋势");
            logger.info(energyType+"......................能源能耗趋势");
            paramsMap.put(ORG_TYPE,type);
            paramsMap.put(ENERGY_TYPE,energyType);
            Map<String,Object> map =  thiredpageEnergyService.getUnitAllAssessment(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        }catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-用能单位类型-个能源能耗趋势加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-用能单位类型-能源类型表格加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/unitTableList", method = RequestMethod.POST)
    @ResponseBody
    public String unitTableList(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-用能单位类型-能源类型表格加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ORG_TYPE,type);
            Map<String,Object> map =  thiredpageEnergyService.getThirdTables(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        }catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-用能单位类型-能源类型表格加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    /**
     *三级页面-能源类型表格导出
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/export/{type}", method = RequestMethod.GET)
    public void export(ToolVO toolVO,@PathVariable("type") String type,HttpServletResponse response,HttpServletRequest request) {
        logger.info("三级页面--能源类型表格导出");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        OutputStream out = null;
        try {
            paramsMap.put(ENERGY_TYPE,type);
            Map<String,Object> map =  thiredpageEnergyService.getTable(paramsMap);
            HSSFWorkbook wb = CommonExcelExport.excelExportThird(map);
            out = response.getOutputStream();
            //输出Excel文件
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding("UTF-8");
            String fileName = "各站点能耗列表.xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            wb.write(out);
            out.flush();
        }catch (Exception e) {
            logger.error("三级页面--能源类型表格导出异常" + e.getMessage());
        }finally {
            if(null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("三级页面--能源类型表格导出异常" + e.getMessage());
                }
            }
        }
    }

    /**
     *三级页面-用能单位类型-能源类型表格导出
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/unitExport/{type}", method = RequestMethod.GET)
    public void unitExport(ToolVO toolVO,@PathVariable("type") String type,HttpServletResponse response,HttpServletRequest request) {
        logger.info("三级页面-用能单位类型-能源类型表格导出");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        OutputStream out = null;
        try {
            paramsMap.put(ORG_TYPE,type);
            Map<String,Object> map =  thiredpageEnergyService.getThirdTables(paramsMap);
            HSSFWorkbook wb = CommonExcelExport.excelExportThird(map);
            out = response.getOutputStream();
            //输出Excel文件
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding("UTF-8");
            String fileName = "用能单位能耗列表.xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            wb.write(out);
            out.flush();
        } catch (Exception e) {
            logger.error("三级页面-用能单位-能源类型表格导出异常" + e.getMessage());
        } finally {
            if(null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("三级页面--能源类型表格导出异常" + e.getMessage());
                }
            }
        }
    }
}
