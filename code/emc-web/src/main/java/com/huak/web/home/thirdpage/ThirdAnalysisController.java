package com.huak.web.home.thirdpage;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CollectionUtil;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.utils.DoubleUtils;
import com.huak.home.thiredpage.ThirdAnalysisService;
import com.huak.home.type.ToolVO;
import com.huak.org.OrgService;
import com.huak.org.model.Company;
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
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/2<BR>
 * Description:   三级单耗  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/third/analysis")
public class ThirdAnalysisController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static  String COMPANY_ID = "comId";
    @Autowired
    private ThirdAnalysisService thirdAnalysisService;
    @Resource
    private OrgService orgService;

    private static  String ENERGY_TYPE = "energytype";
    private static  String ORG_TYPE = "orgType";
    private static String coding="UTF-8";
    /**
     * 跳转三级单耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/page/{type}", method = RequestMethod.GET)
    public String secondEconPage(Model model,HttpServletRequest request,@PathVariable("type")String type){
        logger.info("跳转三级单耗页面");
        model.addAttribute("type", type);
        return "third/analysis";
    }

    /**
     * 跳转分公司三级单耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/fgs/{id}", method = RequestMethod.GET)
    public String fgsPage(Model model,HttpServletRequest request,@PathVariable("id")String id){
        logger.info("跳转分公司三级单耗页面");
        model.addAttribute("id",id);
        Org org  = orgService.selectByPrimaryKey(id);
        model.addAttribute("orgName",org.getOrgName());
        return "third/analysis-fgs";
    }

    /**
     * 跳转用能单位类型三级单耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/unit/{type}", method = RequestMethod.GET)
    public String unitPage(Model model,HttpServletRequest request,@PathVariable("type")String type){
        logger.info("跳转用能单位类型三级单耗页面");
        model.addAttribute("type",type);
        return "third/analysis-unit";
    }

    /**
     * 水单耗明细
     */
    @RequestMapping(value = "/water/detail/{type}/{unittype}", method = RequestMethod.GET)
    @ResponseBody
    public String getAllData1(ToolVO toolVO, HttpServletRequest request,
                              @PathVariable("type")String type,
                              @PathVariable("unittype")String unittype){
        logger.info("计算水单耗");
        DecimalFormat  df   = new DecimalFormat("######0.0");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        if(type!=null&&!"".equals(type)){
            params.put("type",type);
        }
        if(unittype!=null&&!"".equals(unittype)&&!"0".equals(unittype)){
            params.put(ORG_TYPE,unittype);
        }
        String start = params.get("startTime").toString();
        String end = params.get("endTime").toString();
        List<Map<String,Object>> listBq = thirdAnalysisService.getWaterDhDetail(params);
        List<Map<String,Object>> listTq = thirdAnalysisService.getWaterDhDetailTq(params);
        Map<String, Object> bqMap =thirdAnalysisService.getWaterDhAndBQ(params);
        Map<String, Object> tqMap =thirdAnalysisService.getWaterDhAndTQ(params);
        double dhbq;
        double dhtq;
        if(bqMap==null){
            dhbq=0;
        }else {
            dhbq=Double.valueOf(bqMap.get("DHBQ").toString());
        }
        if(tqMap==null){
            dhtq=0;
        }else {
            dhtq=Double.valueOf(tqMap.get("DHTQ").toString());
        }
        double tb;
        if(dhbq==0||dhtq==0){
            tb=0.0;
        }else {
            tb = DoubleUtils.div(DoubleUtils.sub(dhbq, dhtq),dhtq,4)*100;
            }
            try {
                jo = CollectionUtil.packageDataLine(start,end,listBq,listTq);
                jo.put("ZDH",dhbq);
                jo.put("TB", df.format(tb));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo.toString();
    }
    /**
     * 热源的水单耗排名
     */
    @RequestMapping(value = "/water/feed-dh/{type}", method = RequestMethod.GET)
    @ResponseBody
    public String getFeedDh(ToolVO toolVO, HttpServletRequest request,
                            @PathVariable("type")String type){
        logger.info("计算热源的水单耗排名");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        List<String> mapName = new ArrayList<String>();
        List<String> mapValue = new ArrayList<String>();
        if(type!=null&&!"".equals(type)){
            params.put("type",type);
        }
        try {
            List<Map<String,Object>>  list = thirdAnalysisService.getFeedDh(params);
            for (int i = 0; i <list.size() ; i++) {
                mapName.add(list.get(i).get("NAME").toString());
                mapValue.add(list.get(i).get("VALUE").toString());
            }
            jo.put("mapName", mapName);
            jo.put("mapValue", mapValue);
        }catch (Exception e){
            logger.error("热源的水单耗排名catch" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 换热站的水单耗排名
     */
    @RequestMapping(value = "/water/station-dh/{type}", method = RequestMethod.GET)
    @ResponseBody
    public String getStationDh(ToolVO toolVO, HttpServletRequest request,@PathVariable("type")String type){
        logger.info("计算换热站的水单耗排名");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        List<String> mapName = new ArrayList<String>();
        List<String> mapValue = new ArrayList<String>();
        if(type!=null&&!"".equals(type)){
            params.put("type",type);
        }
        try {
            List<Map<String,Object>>  list = thirdAnalysisService.getStationDh(params);
            for (int i = 0; i <list.size() ; i++) {
                mapName.add(list.get(i).get("NAME").toString());
                mapValue.add(list.get(i).get("VALUE").toString());
            }
            jo.put("mapName", mapName);
            jo.put("mapValue", mapValue);
        }catch (Exception e){
            logger.error("换热站的水单耗排名catch" + e.getMessage());
        }
        return jo.toJSONString();
    }
    /**
     *三级页面-换热站列表显示图
     *
     * @return string
     */
    @RequestMapping(value = "/table-list", method = RequestMethod.POST)
    @ResponseBody
    public String getTables(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-表单数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Map paramsMap = paramsPackageOrg(toolVO, request);
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            paramsMap.put("comId",company.getId());
            paramsMap.put("type",type);
            Map<String, Object> map =  thirdAnalysisService.getTable(paramsMap);
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
     *热源的水单耗排名
     */
    @RequestMapping(value = "/fgs/feed-dh/{type}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getFgsFeedDhPm(ToolVO toolVO, HttpServletRequest request,
                                    @PathVariable("type")String type,
                                    @PathVariable("id")String id){
        logger.info("热源的水单耗排名");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        List<String> mapName = new ArrayList<String>();
        List<String> mapValue = new ArrayList<String>();

        if(type==null||"".equals(type)){
            type="1";
            params.put("eType",type);
        }else {
            params.put("eType",type);
        }
        params.put("id",id);
        try {
            List<Map<String,Object>>  list = thirdAnalysisService.getFgsFeedDh(params);
            for (int i = 0; i <list.size() ; i++) {
                mapName.add(list.get(i).get("NAME").toString());
                mapValue.add(list.get(i).get("VALUE").toString());
            }
            jo.put("mapName", mapName);
            jo.put("mapValue", mapValue);
        }catch (Exception e){
            logger.error("热源的水单耗排名catch" + e.getMessage());
        }
        return jo.toJSONString();
    }
    /**
     *换热站的水单耗排名
     */
    @RequestMapping(value = "/fgs/station-dh/{type}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getFgsStationDhPm(ToolVO toolVO, HttpServletRequest request,
                                    @PathVariable("type")String type,
                                    @PathVariable("id")String id){
        logger.info("换热站的水单耗排名");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        List<String> mapName = new ArrayList<String>();
        List<String> mapValue = new ArrayList<String>();

        if(type==null||"".equals(type)){
            type="1";
            params.put("eType",type);
        }else {
            params.put("eType",type);
        }
        params.put("id",id);
        try {
            List<Map<String,Object>>  list = thirdAnalysisService.getFgsStationDh(params);
            for (int i = 0; i <list.size() ; i++) {
                mapName.add(list.get(i).get("NAME").toString());
                mapValue.add(list.get(i).get("VALUE").toString());
            }
            jo.put("mapName", mapName);
            jo.put("mapValue", mapValue);
        }catch (Exception e){
            logger.error("换热站的水单耗排名catch" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-分公司 表单
     *
     * @return string
     */
    @RequestMapping(value = "/fgs/table-list", method = RequestMethod.POST)
    @ResponseBody
    public String getFgsTables(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-表单数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Map paramsMap = paramsPackageOrg(toolVO, request);
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            paramsMap.put("comId",company.getId());
            paramsMap.put("type",type);
            Map<String, Object> map =  thirdAnalysisService.getTable(paramsMap);
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
     * 单耗 (水，电，气，热，煤)
     */
    @RequestMapping(value = "/fgs/org/{id}/{energytype}", method = RequestMethod.GET)
    @ResponseBody
    public String getFgsOrgAll(ToolVO toolVO, HttpServletRequest request,
                               @PathVariable("id")String id,
                               @PathVariable("energytype")String energytype){
        logger.info("计算源网站线户的单耗同比");
        DecimalFormat  df   = new DecimalFormat("######0.0");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        String start = params.get("startTime").toString();
        String end = params.get("endTime").toString();
        if(id!=null&&!"".equals(id)){
            params.put("id",id);
        }
        if(energytype!=null&&!"".equals(energytype)){
            params.put("energytype",energytype);
        }

            List<Map<String,Object>>  listBq = thirdAnalysisService.getFgsOrgDhBQ(params);
            List<Map<String,Object>>  listTq = thirdAnalysisService.getFgsOrgDhTQ(params);

            Map<String,Object> bqMap=thirdAnalysisService.getFgsOrgDhAndBQ(params);
            Map<String,Object> tqMap=thirdAnalysisService.getFgsOrgDhAndTQ(params);

            double dhbq;
            double dhtq;
            if(bqMap==null){
                dhbq=0;
            }else {
                dhbq=Double.valueOf(bqMap.get("BQDH").toString());
            }
            if(tqMap==null){
                dhtq=0;
            }else {
                dhtq=Double.valueOf(tqMap.get("TQDH").toString());
            }
            double tb;
            if(dhbq==0||dhtq==0){
                tb=0.0;
            }else {
                tb = DoubleUtils.div(DoubleUtils.sub(dhbq, dhtq),dhtq,4)*100;
            }
            try {
                jo = CollectionUtil.packageDataLine(start,end,listBq,listTq);
                jo.put("ZDH",dhbq);
                jo.put("TB", df.format(tb));
            } catch (Exception e) {
                logger.error("计算分公司能耗折线图和同比" + e.getMessage());
            }
            return jo.toString();

    }
    /**
     * 分公司单耗明细
     */
    @RequestMapping(value = "/fgs/detail/{orgId}", method = RequestMethod.GET)
    @ResponseBody
    public String getFgsData(ToolVO toolVO, HttpServletRequest request,@PathVariable("orgId")String orgId){
        logger.info("计算分公司能耗折线图和同比");
        JSONObject jo = new JSONObject();
        DecimalFormat  df   = new DecimalFormat("######0.0");

        Map params = paramsPackageOrg(toolVO, request);
        params.put("orgId",orgId);
        String start = params.get("startTime").toString();
        String end = params.get("endTime").toString();

            //单耗详细
            List<Map<String, Object>> listBq = thirdAnalysisService.getFgsDhDetail(params);
            List<Map<String, Object>> listTq = thirdAnalysisService.getFgsDhDetailTq(params);
            //总单耗和同比
            Map<String, Object> tqMap =thirdAnalysisService.getFgsDhAndTQ(params);
            Map<String, Object> bqMap =thirdAnalysisService.getFgsDhAndBQ(params);

            double dhbq;
            double dhtq;
            if(bqMap==null){
                dhbq=0;
            }else {
                dhbq=Double.valueOf(bqMap.get("BQDH").toString());
            }
            if(tqMap==null){
                dhtq=0;
            }else {
                dhtq=Double.valueOf(tqMap.get("TQDH").toString());
            }
            double tb;
            if(dhbq==0||dhtq==0){
                tb=0.0;
            }else {
                tb = DoubleUtils.div(DoubleUtils.sub(dhbq, dhtq),dhtq,4)*100;
            }
            try {
                jo = CollectionUtil.packageDataLine(start,end,listBq,listTq);
                jo.put("ZDH",dhbq);
                jo.put("TB", df.format(tb));

            } catch (Exception e) {
                logger.error("计算分公司能耗折线图和同比" + e.getMessage());
            }
        return jo.toString();
    }

    /**
     *三级页面-用能单位类型-能源类型表格加载
     *
     * @return string
     */
    @RequestMapping(value = "/fgs/unitTableList", method = RequestMethod.POST)
    @ResponseBody
    public String unitTableList(ToolVO toolVO,@RequestParam String id,HttpServletRequest request) {
        logger.info("三级页面-用能单位类型-能源类型表格加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Map paramsMap = paramsPackageOrg(toolVO, request);
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            paramsMap.put(COMPANY_ID,company.getId());
            paramsMap.put("id",id);
             Map<String,Object> map =  thirdAnalysisService.getThirdTables(paramsMap);
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



    /**     三级页面 unit 分割
     *三级页面-用能单位类型-能源对比数据加载
     *
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
            Map<String,Object> map =  thirdAnalysisService.getUnitEnergyDetail(paramsMap);
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
     *
     */
    @RequestMapping(value = "/unit/unitAssessment", method = RequestMethod.POST)
    @ResponseBody
    public String unitAssessment(ToolVO toolVO,@RequestParam String type,@RequestParam String energyType,HttpServletRequest request) {
        logger.info("三级页面-用能单位类型-能源能耗排名加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ORG_TYPE,type);
            paramsMap.put(ENERGY_TYPE,energyType);
            Map<String,Object> map =  thirdAnalysisService.getUnitAssessments(paramsMap);
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
     *
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
            paramsMap.put(ORG_TYPE,type);
            paramsMap.put(ENERGY_TYPE,energyType);
            Map<String,Object> map =  thirdAnalysisService.getUnitAllAssessment(paramsMap);
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
     *
     */
    @RequestMapping(value = "/unit/unitTableList", method = RequestMethod.POST)
    @ResponseBody
    public String unitTable(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-用能单位类型-能源类型表格加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ORG_TYPE,type);
            Map<String,Object> map =  thirdAnalysisService.getThirdTableList(paramsMap);
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

    @RequestMapping(value = "/energy/export/{type}", method = RequestMethod.GET)
    public void exportEnergy(ToolVO toolVO, HttpServletResponse response, HttpServletRequest request,@PathVariable("type")String type) {
        logger.info("导出能源类型单耗明细列表EXCEL");

        String workBookName = "能源类型单耗明细列表";//文件名
        OutputStream out = null;
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            params.put("type",type);

            Map<String, Object> map =  thirdAnalysisService.getTable(params);

            HSSFWorkbook wb = CommonExcelExport.excelExportThird(map);
            //response输出流导出excel
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding(coding);
            String fileName = workBookName + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, coding));
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("导出能源类型单耗明细列表EXCEL异常" + e.getMessage());
        }
    }
    @RequestMapping(value = "/fgs/export/{id}", method = RequestMethod.GET)
    public void exportFgsEngsergy(ToolVO toolVO, HttpServletResponse response, HttpServletRequest request,@PathVariable("id")String id) {
        logger.info("导出分公司单耗明细列表EXCEL");

        String workBookName = "分公司单耗明细列表";//文件名
        OutputStream out = null;
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            params.put("id",id);

            Map<String, Object> map =  thirdAnalysisService.getThirdTables(params);

            HSSFWorkbook wb = CommonExcelExport.excelExportThird(map);
            //response输出流导出excel
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding(coding);
            String fileName = workBookName + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, coding));
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("导出分公司单耗明细列表EXCEL异常" + e.getMessage());
        }
    }

    @RequestMapping(value = "/unit/export/{type}", method = RequestMethod.GET)
    public void exportUnitEngsergy(ToolVO toolVO, HttpServletResponse response, HttpServletRequest request,@PathVariable("type")String type) {
        logger.info("导出能源单位单耗明细列表EXCEL");

        String workBookName = "能源单位单耗明细列表";//文件名
        OutputStream out = null;
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            params.put("type",type);

            Map<String, Object> map =  thirdAnalysisService.getThirdTableList(params);

            HSSFWorkbook wb = CommonExcelExport.excelExportThird(map);
            //response输出流导出excel
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding(coding);
            String fileName = workBookName + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, coding));
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("导出能源单位单耗明细列表EXCEL异常" + e.getMessage());
        }
    }
}
