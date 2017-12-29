package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CollectionUtil;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.utils.DoubleUtils;
import com.huak.home.EnergyMonitorService;
import com.huak.home.model.EnergySecond;
import com.huak.home.type.ToolVO;
import com.huak.org.model.Company;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * Date: 2017/5/26<BR>
 * Description:  首页-二级页面- 能耗分析  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/energy/monitor")
public class EnergyMonitorController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnergyMonitorService eaService;
    private static String[] TYPES={"electric", "water", "gas", "coal", "oil","heat"};

    /**
     * 跳转二级能耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/tsec", method = RequestMethod.GET)
    public String secondEconPage(Model model, HttpServletRequest request) {
        logger.info("跳转二级能耗页面");
        try {
            Company company = (Company) request.getSession().getAttribute(Constants.SESSION_COM_KEY);
            model.addAttribute("company", company);
        } catch (Exception e) {
            logger.error("跳转二级能耗页面异常：" + e.getMessage());
        }
        return "second/econ";
    }

    @RequestMapping(value = "/fgs/list", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyList(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司能耗详细");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

            jo.put(Constants.LIST, eaService.findAssessmentIndicators(params));
        } catch (Exception e) {
            logger.error("分公司能耗详细异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/ratio", method = RequestMethod.GET)
    @ResponseBody
    public String fgsEnergyRatio(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司能耗占比分布图");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/

            Map params = paramsPackageFgs(toolVO, request);

            List<Map<String, Object>> energySecondList = eaService.fgsEnergyRatio(params);
            jo.put(Constants.LIST, energySecondList);
        } catch (Exception e) {
            logger.error("分公司能耗占比分布图异常" + e.getMessage());
        }
        logger.info(jo.toJSONString());
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/trend", method = RequestMethod.GET)
    @ResponseBody
    public String fgsEnergyTrend(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司能耗趋势对比图");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

            List<Map<String, Object>> trendList = eaService.fgsEnergyTrend(params);

            List<String> xAxis = new ArrayList<>();
            List<Map<String, Object>> series = new LinkedList<>();
            List<String> legends = new ArrayList<>();
            //先确定几条线和横坐标
            for (Map<String, Object> map : trendList) {
                String fgsId = map.get("id").toString();
                String name = map.get("name").toString();
                String date = map.get("date").toString();
                legends.add(name);
                xAxis.add(date);
                map.get("BQBM").toString();
            }
            //去重复
            xAxis = CollectionUtil.removeDuplicateWithOrder(xAxis);
            legends = CollectionUtil.removeDuplicateWithOrder(legends);
            //组装数据
            for (String gsName : legends) {
                Map<String, Object> dataMap = new HashMap<>();
                List<Double> dataList = new ArrayList<>();
                for (Map<String, Object> map : trendList) {
                    String name = map.get("name").toString();
                    if (name.equals(gsName)) {
                        dataList.add(Double.valueOf(map.get("BQBM").toString()));
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
            logger.error("分公司能耗趋势对比图异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/ranking", method = RequestMethod.GET)
    @ResponseBody
    public String fgsEnergyRanking(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司能耗排名");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

            List<Map<String, Object>> energySecondList = eaService.fgsEnergyRanking(params);
            List<String> xAxis = new ArrayList<>();
            List<String> datas = new ArrayList<>();
            for (Map<String, Object> map : energySecondList) {
                xAxis.add(map.get("NAME").toString());
                datas.add(map.get("VALUE").toString());
            }
            jo.put(Constants.XAXIS, xAxis);
            jo.put(Constants.LIST, datas);
        } catch (Exception e) {
            logger.error("分公司能耗排名异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/an", method = RequestMethod.GET)
    @ResponseBody
    public String fgsEnergyAn(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司能耗同比");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

            List<Map<String, Object>> energySecondList = eaService.fgsEnergyAn(params);
            List<String> xAxis = new ArrayList<>();
            List<String> bqs = new ArrayList<>();
            List<String> tqs = new ArrayList<>();
            for (Map<String, Object> map : energySecondList) {
                bqs.add(map.get("BQBM").toString());
                xAxis.add(map.get("ORGNAME").toString());
                tqs.add(map.get("TQBM").toString());
            }
            jo.put(Constants.XAXIS, xAxis);
            jo.put("tq", tqs);
            jo.put("bq", bqs);
        } catch (Exception e) {
            logger.error("分公司能耗同比异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/fgs/export", method = RequestMethod.GET)
    public void export(ToolVO toolVO, HttpServletResponse response, HttpServletRequest request) {
        logger.info("导出分公司能耗列表EXCEL");

        String workBookName = "分公司能耗列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("ID", "组织主键");
        cellName.put("orgName", "组织名称");
        cellName.put("totalBq", "能耗总量本期");
        cellName.put("totalTq", "能耗总量同期");
        cellName.put("totalAn", "能耗总量同比");
        cellName.put("waterBq", "水能耗量本期");
        cellName.put("waterTq", "水能耗量同期");
        cellName.put("waterAn", "水能耗量同比");
        cellName.put("electricBq", "电能耗量本期");
        cellName.put("electricTq", "电能耗量同期");
        cellName.put("electricAn", "电能耗量同比");
        cellName.put("gasBq", "气能耗量本期");
        cellName.put("gasTq", "气能耗量同期");
        cellName.put("gasAn", "气能耗量同比");
        cellName.put("heatBq", "热能耗量本期");
        cellName.put("heatTq", "热能耗量同期");
        cellName.put("heatAn", "热能耗量同比");
        cellName.put("coalBq", "煤能耗量本期");
        cellName.put("coalTq", "煤能耗量同期");
        cellName.put("coalAn", "煤能耗量同比");
        cellName.put("oilBq", "油能耗量本期");
        cellName.put("oilTq", "油能耗量同期");
        cellName.put("oilAn", "油能耗量同比");
        List<Map<String, Object>> cellValues = new ArrayList<>();//列值
        OutputStream out = null;
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

            List<EnergySecond> energys = eaService.exportAssessmentIndicators(params);
            for (EnergySecond second : energys) {
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
            logger.error("导出分公司能耗列表EXCEL异常" + e.getMessage());
        }
    }

    /**
     * 标煤查询折线入口
     *
     * @param toolVO
     * @return
     */
    @RequestMapping(value = "/line/bm", method = RequestMethod.POST)
    @ResponseBody
    public String lineBm(ToolVO toolVO, HttpServletRequest request) {
        logger.info("查询能耗标煤时间折线图统一接口");
        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            String start = params.get("startTime").toString();
            String end = params.get("endTime").toString();
            //查询折线数据
            List<Map<String, Object>> bqLine = eaService.selectBmBqLine(params);
            List<Map<String, Object>> tqLine = eaService.selectBmTqLine(params);
            Double bq = eaService.selectBmBqTotal(params);
            Double tq = eaService.selectBmTqTotal(params);
            if(null == bq){
                bq = 0d;
            }
            if(null == tq){
                tq = 0d;
            }
            Double tb = DoubleUtils.div(DoubleUtils.sub(bq,tq),tq,4)*100;
            jo = CollectionUtil.packageDataLine(start,end,bqLine,tqLine);
            jo.put("bq",bq);
            jo.put("tq",tq);
            jo.put("tb",tb);
            jo.put("success", true);
            jo.put("message", "查询集团能耗数据成功！");
        } catch (Exception e) {
            logger.error("查询能耗标煤时间折线图统一接口异常" + e.getMessage());
            jo.put("success", false);
            jo.put("message", "查询能耗标煤时间折线图统一接口异常！");
        }
        return jo.toJSONString();
    }

    /**
     * 用量查询折线入口
     *
     * @param toolVO
     * @return
     */
    @RequestMapping(value = "/line/yl", method = RequestMethod.GET)
    @ResponseBody
    public String lineYl(ToolVO toolVO, HttpServletRequest request) {
        logger.info("查询能耗用量时间折线图统一接口");
        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            String start = params.get("startTime").toString();
            String end = params.get("endTime").toString();
            //查询折线数据
            List<Map<String, Object>> bqLine = eaService.selectYlBqLine(params);
            List<Map<String, Object>> tqLine = eaService.selectYlTqLine(params);
            Map<String, Object> bqMap = eaService.selectYlBqTotal(params);
            Map<String, Object> tqMap = eaService.selectYlTqTotal(params);
            Map<String, Object> tbMap = new HashMap<>();
            for(String type:TYPES){
                Double bq = 0d;
                Double tq = 0d;
                if(null!=bqMap.get(type)){
                    bq = Double.valueOf(bqMap.get(type).toString());
                }

                if(null== tqMap ||tqMap.isEmpty()|| tqMap.size()==0 ){
                    tq=0.0;
                }else{
                    tq = Double.valueOf(tqMap.get(type).toString());
                }
                Double tb = DoubleUtils.div(DoubleUtils.sub(bq,tq),bq,4)*100;
                tbMap.put(type,tb);

                JSONObject jsonObject = CollectionUtil.packageDataLineYl(start,end,bqLine,tqLine,type);
                jo.put(type,jsonObject);
            }

            jo.put("bq",bqMap);
            jo.put("tq",tqMap);
            jo.put("tb",tbMap);
            jo.put("success", true);
            jo.put("message", "查询集团能耗数据成功！");
        } catch (Exception e) {
            logger.error("查询能耗用量时间折线图统一接口异常" + e.getMessage());
            jo.put("success", false);
            jo.put("message", "查询能耗用量时间折线图统一接口异常！");
        }
        return jo.toJSONString();
    }

    /**
     * 查询能源流明细
     *
     * @param toolVO
     * @return
     */
    @RequestMapping(value = "/energyFlowTable", method = RequestMethod.GET)
    @ResponseBody
    public String energyFlowTable(ToolVO toolVO, HttpServletRequest request) {
        logger.info("查询能源流明细");
        JSONObject jo = new JSONObject();
        try {
            jo.put("success", true);
            jo.put("message", "查询能源流明细成功！");

            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            //查询折线数据
            List<Map<String, Object>> retMap = eaService.energyFlowTable(params);
            jo.put("data", retMap);
        } catch (Exception e) {
            logger.error("查询能源流明细异常" + e.getMessage());
            jo.put("success", false);
            jo.put("message", "查询能源流明细异常！");
        }
        return jo.toJSONString();
    }

    /**
     * 查询能源流占比分布图
     *
     * @param toolVO
     * @return
     */
    @RequestMapping(value = "/energyFlowPie", method = RequestMethod.GET)
    @ResponseBody
    public String energyFlowPie(ToolVO toolVO, HttpServletRequest request) {
        logger.info("查询能源流占比分布图");
        JSONObject jo = new JSONObject();
        try {
            jo.put("success", true);
            jo.put("message", "查询能源流占比分布图成功！");

            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            //查询折线数据
            List<Map<String, Object>> retMap = eaService.energyFlowPie(params);
            jo.put("data", retMap);
        } catch (Exception e) {
            logger.error("查询能源流占比分布图异常" + e.getMessage());
            jo.put("success", false);
            jo.put("message", "查询能源流占比分布图异常！");
        }
        return jo.toJSONString();
    }

    /**
     * 查询能源流趋势对比图
     *
     * @param toolVO
     * @return
     */
    @RequestMapping(value = "/energyFlowLine", method = RequestMethod.GET)
    @ResponseBody
    public String energyFlowLine(ToolVO toolVO, HttpServletRequest request) {
        logger.info("查询能源流趋势对比图");
        JSONObject jo = new JSONObject();
        try {
            jo.put("success", true);
            jo.put("message", "查询能源流趋势对比图成功！");

            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            //查询折线数据
            Map<String, Object> retMap = eaService.energyFlowLine(params);
            jo.put("data", retMap);
        } catch (Exception e) {
            logger.error("查询能源流趋势对比图异常" + e.getMessage());
            jo.put("success", false);
            jo.put("message", "查询能源流趋势对比图异常！");
            System.out.println(jo.toJSONString());
        }
        return jo.toJSONString();
    }

    /**
     * 查询能源流同比
     *
     * @param toolVO
     * @return
     */
    @RequestMapping(value = "/energyFlowBar", method = RequestMethod.GET)
    @ResponseBody
    public String energyFlowBar(ToolVO toolVO, HttpServletRequest request) {
        logger.info("查询能源流同比");
        JSONObject jo = new JSONObject();
        try {
            jo.put("success", true);
            jo.put("message", "查询能源流同比成功！");

            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            //查询折线数据
            Map<String, Object> retMap = eaService.energyFlowBar(params);
            jo.put("data", retMap);
        } catch (Exception e) {
            logger.error("查询能源流同比异常" + e.getMessage());
            jo.put("success", false);
            jo.put("message", "查询能源流同比异常！");
        }
        return jo.toJSONString();
    }

    /**
     * 导出能源流明细
     */
    @RequestMapping(value = "exportEnergyFlowDetail", method = RequestMethod.GET)
    public void exportEnergyFlowDetail(HttpServletResponse response, ToolVO toolVO, HttpServletRequest request) {
        logger.info("导出能源流-能耗列表EXCEL");
        try {

            JSONObject jo = new JSONObject();
            jo.put(Constants.FLAG, false);
            Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
            cellName.put("UNITNAME", "能源站");
            cellName.put("CURGROUP", "能耗总量本期");
            cellName.put("LASTGROUP", "能耗总量同期");
            cellName.put("GROUPS", "能耗总量同比");
            cellName.put("CURWATER", "水能耗量本期");
            cellName.put("LASTWATER", "水能耗量同期");
            cellName.put("WATERS", "水能耗量同比");
            cellName.put("CURELEC", "电能耗量本期");
            cellName.put("LASTELEC", "电能耗量同期");
            cellName.put("ELECS", "电能耗量同比");
            cellName.put("CURGAS", "气能耗量本期");
            cellName.put("LASTGAS", "气能耗量同期");
            cellName.put("GASS", "气能耗量同比");
            cellName.put("CURHOT", "热能耗量本期");
            cellName.put("LASTHOT", "热能耗量同期");
            cellName.put("HOTS", "热能耗量同比");
            cellName.put("CURCOAL", "煤能耗量本期");
            cellName.put("LASTCOAL", "煤能耗量同期");
            cellName.put("COALS", "煤能耗量同比");
            cellName.put("CUROIL", "油能耗量本期");
            cellName.put("LASTOIL", "油能耗量同期");
            cellName.put("OILS", "油能耗量同比");
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            List<Map<String, Object>> cellValues = eaService.exportEnergyFlowDetail(params);
            if (cellValues == null) {
                jo.put(Constants.MSG, "导出失败");
            } else if (cellValues.size() == 0) {
                jo.put(Constants.MSG, "没有数据要导出");
            } else {
                jo.put(Constants.MSG, "导出失败");
                HSSFWorkbook wb = CommonExcelExport.excelExport(cellName, cellValues);
                OutputStream out = response.getOutputStream();
                //输出Excel文件
                String mimetype = "application/vnd.ms-excel";
                response.setContentType(mimetype);
                response.setCharacterEncoding("UTF-8");
                String fileName = "能源流-能耗列表.xls";
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                wb.write(out);
                out.flush();
            }
        } catch (Exception e) {
            logger.error("导出能源流-能耗列表EXCEL异常" + e.getMessage());
        }
    }
}
