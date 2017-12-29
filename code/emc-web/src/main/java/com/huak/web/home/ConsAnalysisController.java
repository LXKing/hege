package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CollectionUtil;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.home.ConsAnalysisService;
import com.huak.home.model.ConsSecond;
import com.huak.home.type.ToolVO;
import com.huak.org.OrgService;
import com.huak.org.model.Company;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/5<BR>
 * Description:  首页-二级页面- 单耗分析   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/cons/analysis")
public class ConsAnalysisController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ConsAnalysisService consAnalysisService;
    @Resource
    private OrgService orgService;

    /**
     * 跳转二级单耗页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/tsec", method = RequestMethod.GET)
    public String secondEconPage(Model model, HttpServletRequest request) {
        logger.info("跳转二级单耗页面");
        Company company = (Company) request.getSession().getAttribute(Constants.SESSION_COM_KEY);

        model.addAttribute("company", company);
        return "second/ucon";
    }

    @RequestMapping(value = "/fgs/list", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyList(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司单耗详细");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

            jo.put(Constants.LIST, consAnalysisService.findAssessmentIndicators(params));
        } catch (Exception e) {
            logger.error("分公司单耗详细异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/ratio", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyRatio(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司单耗占比分布图");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

            List<Map<String, Object>> energySecondList = consAnalysisService.fgsEnergyRatio(params);
            jo.put(Constants.LIST, energySecondList);
        } catch (Exception e) {
            logger.error("分公司单耗占比分布图异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/trend", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyTrend(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司单耗趋势对比图");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

            List<Map<String, Object>> trendList = consAnalysisService.fgsEnergyTrend(params);

            List<String> xAxis = new ArrayList<>();
            List<Map<String, Object>> series = new LinkedList<>();
            List<String> legends = new ArrayList<>();
            //先确定几条线和横坐标
            for (Map<String, Object> map : trendList) {
                String fgsId = map.get("FGSID").toString();
                String name = map.get("NAME").toString();
                String date = map.get("DATE").toString();
                legends.add(name);
                xAxis.add(date);
                map.get("BQDH").toString();
            }
            //去重复
            xAxis = CollectionUtil.removeDuplicateWithOrder(xAxis);
            legends = CollectionUtil.removeDuplicateWithOrder(legends);
            //组装数据
            for (String gsName : legends) {
                Map<String, Object> dataMap = new HashMap<>();
                List<Double> dataList = new ArrayList<>();
                for (Map<String, Object> map : trendList) {
                    String name = map.get("NAME").toString();
                    if (name.equals(gsName)) {
                        dataList.add(Double.valueOf(map.get("BQDH").toString()));
                    }
                }
                dataMap.put("name", gsName);
                dataMap.put("type", "line");
                dataMap.put("symbol", "circle");
                dataMap.put("smooth", false);
                dataMap.put("data", dataList);
                series.add(dataMap);
            }

            jo.put(Constants.XAXIS, xAxis);
            jo.put(Constants.LEGENDS, legends);
            jo.put(Constants.LIST, series);
        } catch (Exception e) {
            logger.error("分公司单耗趋势对比图异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/ranking", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyRanking(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司单耗排名");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

            List<Map<String, Object>> energySecondList = consAnalysisService.fgsEnergyRanking(params);
            List<String> xAxis = new ArrayList<>();
            List<String> datas = new ArrayList<>();
            for (Map<String, Object> map : energySecondList) {
                xAxis.add(map.get("NAME").toString());
                datas.add(map.get("VALUE").toString());
            }
            jo.put(Constants.XAXIS, xAxis);
            jo.put(Constants.LIST, datas);
        } catch (Exception e) {
            logger.error("分公司单耗排名异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/an", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyAn(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司单耗同比");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

            List<Map<String, Object>> energySecondList = consAnalysisService.fgsEnergyAn(params);
            List<String> xAxis = new ArrayList<>();
            List<String> bqs = new ArrayList<>();
            List<String> tqs = new ArrayList<>();
            for (Map<String, Object> map : energySecondList) {
                bqs.add(map.get("BQDH").toString());
                xAxis.add(map.get("ORGNAME").toString());
                tqs.add(map.get("TQDH").toString());
            }
            jo.put(Constants.XAXIS, xAxis);
            jo.put("tq", tqs);
            jo.put("bq", bqs);
        } catch (Exception e) {
            logger.error("分公司单耗同比异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/export", method = RequestMethod.GET)
    public void export(ToolVO toolVO, HttpServletResponse response, HttpServletRequest request) {
        logger.info("导出分公司单耗列表EXCEL");

        String workBookName = "分公司单耗列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("ID", "组织主键");
        cellName.put("orgName", "组织名称");
        cellName.put("totalBq", "单耗总量本期");
        cellName.put("totalTq", "单耗总量同期");
        cellName.put("totalAn", "单耗总量同比");
        cellName.put("waterBq", "水单耗量本期");
        cellName.put("waterTq", "水单耗量同期");
        cellName.put("waterAn", "水单耗量同比");
        cellName.put("electricBq", "电单耗量本期");
        cellName.put("electricTq", "电单耗量同期");
        cellName.put("electricAn", "电单耗量同比");
        cellName.put("gasBq", "气单耗量本期");
        cellName.put("gasTq", "气单耗量同期");
        cellName.put("gasAn", "气单耗量同比");
        cellName.put("heatBq", "热单耗量本期");
        cellName.put("heatTq", "热单耗量同期");
        cellName.put("heatAn", "热单耗量同比");
        cellName.put("coalBq", "煤单耗量本期");
        cellName.put("coalTq", "煤单耗量同期");
        cellName.put("coalAn", "煤单耗量同比");
        cellName.put("oilBq", "油单耗量本期");
        cellName.put("oilTq", "油单耗量同期");
        cellName.put("oilAn", "油单耗量同比");
        List<Map<String, Object>> cellValues = new ArrayList<>();//列值
        OutputStream out = null;
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

            List<ConsSecond> cons = consAnalysisService.exportAssessmentIndicators(params);
            for (ConsSecond second : cons) {
                cellValues.add(CollectionUtil.Obj2Map(second));
            }

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
            e.printStackTrace();
            logger.error("导出分公司单耗列表EXCEL异常" + e.getMessage());
        }
    }

    /**
     * 查询单耗
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/groupDanHao", method = RequestMethod.GET)
    @ResponseBody
    public String groupDanHao(ToolVO toolVO, HttpServletRequest request) {
        logger.info("查询单耗数据");
        JSONObject jo = new JSONObject();
        try {
            jo.put("success", true);
            jo.put("message", "查询单耗数据成功！");
             /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            //查询折线数据
            Map<String, Object> retMap = consAnalysisService.groupDanHaoLine(params);
            jo.put("data", retMap);
        } catch (Exception e) {
            logger.error("查询单耗数据异常" + e.getMessage());
            jo.put("success", false);
            jo.put("message", "查询单耗数据异常！");
        }
        return jo.toJSONString();
    }

}
