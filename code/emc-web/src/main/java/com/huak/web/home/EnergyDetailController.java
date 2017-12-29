package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CollectionUtil;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.home.FrameService;
import com.huak.home.model.EnergyDetail;
import com.huak.home.type.ToolVO;
import com.huak.org.OrgService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/8<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/energy/secondary")
public class EnergyDetailController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private  FrameService frameService;
    @Autowired
    private OrgService orgService;
    /**
     * 查询能源明细
     * @param
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public String getAllData(ToolVO toolVO, HttpServletRequest request){
        logger.info("能源流明细");

        JSONObject jo = new JSONObject();
        try {
            Map params = paramsPackageOrg(toolVO, request);

            jo.put(Constants.LIST, frameService.selectEnergyDetail(params));
        } catch (Exception e) {
            logger.error("能源流明细" + e.getMessage());
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/proportion", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyRatio(ToolVO toolVO, HttpServletRequest request){
        logger.info("能源流能耗占比分布图");

        JSONObject jo = new JSONObject();
        try {
            Map params = paramsPackageOrg(toolVO, request);

            List<Map<String,Object>> list = frameService.selectEnergyProportion(params);
            jo.put(Constants.LIST, list);
        } catch (Exception e) {
            logger.error("能源流能耗占比分布图" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/trend", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyTrend(ToolVO toolVO, HttpServletRequest request){
        logger.info("能源流单耗趋势对比图");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            List<Map<String,Object>> trendList = frameService.selectEnergyTrend(params);

            List<String> xAxis = new ArrayList<>();
            List<Map<String,Object>> series = new LinkedList<>();
            List<String> legends = new ArrayList<>();
            //先确定几条线和横坐标
            for(Map<String,Object> map : trendList){
                String name = map.get("NAME").toString();
                String date = map.get("DATE").toString();
                legends.add(name);
                xAxis.add(date);
                map.get("VALUE").toString();
            }
            //去重复
            xAxis = CollectionUtil.removeDuplicateWithOrder(xAxis);
            legends = CollectionUtil.removeDuplicateWithOrder(legends);
            //组装数据
            for(String gsName:legends){
                Map<String,Object> dataMap = new HashMap<>();
                List<Double> dataList = new ArrayList<>();
                for(Map<String,Object> map : trendList){
                    String name = map.get("NAME").toString();
                    if(name.equals(gsName)){
                        dataList.add(Double.valueOf(map.get("VALUE").toString()));
                    }
                }
                dataMap.put("name",gsName);
                dataMap.put("type","line");
                dataMap.put("symbol","circle");
                dataMap.put("smooth",false);
                dataMap.put("data",dataList);
                series.add(dataMap);
            }

            jo.put(Constants.XAXIS,xAxis);
            jo.put(Constants.LEGENDS,legends);
            jo.put(Constants.LIST, series);
        } catch (Exception e) {
            logger.error("能源流单耗趋势对比图异常" + e.getMessage());
        }
        return jo.toJSONString();

    }
    @RequestMapping(value = "/an", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyAn(ToolVO toolVO, HttpServletRequest request){
        logger.info("能源流能耗同比");

        JSONObject jo = new JSONObject();
        try {
            Map params = paramsPackageOrg(toolVO, request);

            List<Map<String,Object>> energySecondList = frameService.selectEnergyTong(params);
            List<String> xAxis = new ArrayList<>();
            List<String> bqs = new ArrayList<>();
            List<String> tqs = new ArrayList<>();
            for(Map<String,Object> map:energySecondList){
                bqs.add(map.get("BQ").toString());
                xAxis.add(map.get("NAME").toString());
                tqs.add(map.get("TQ").toString());
            }
            jo.put(Constants.XAXIS, xAxis);
            jo.put("tq", tqs);
            jo.put("bq", bqs);
        } catch (Exception e) {
            logger.error("能源流能耗同比异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/export/detail", method = RequestMethod.GET)
    public void export(ToolVO toolVO, HttpServletResponse response, HttpServletRequest request) {
        logger.info("导出能源明细列表EXCEL");

        String workBookName = "能源明细列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("unitName", "用能单位名称");
        cellName.put("totalBq", "总量本期");
        cellName.put("totalTq", "总量同期");
        cellName.put("totalAn", "总量同比");
        cellName.put("waterBq", "水量本期");
        cellName.put("waterTq", "水量同期");
        cellName.put("waterAn", "水量同比");
        cellName.put("electricBq", "电量本期");
        cellName.put("electricTq", "电量同期");
        cellName.put("electricAn", "电量同比");
        cellName.put("gasBq", "气量本期");
        cellName.put("gasTq", "气量同期");
        cellName.put("gasAn", "气量同比");
        cellName.put("heatBq", "热量本期");
        cellName.put("heatTq", "热量同期");
        cellName.put("heatAn", "热量同比");
        cellName.put("coalBq", "煤量本期");
        cellName.put("coalTq", "煤量同期");
        cellName.put("coalAn", "煤量同比");
        cellName.put("oilBq", "油量本期");
        cellName.put("oilTq", "油量同期");
        cellName.put("oilAn", "油量同比");
        List<Map<String, Object>> cellValues = new ArrayList<Map<String, Object>>();//列值
        OutputStream out = null;
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            List<EnergyDetail> cons = frameService.exportEnergyDetail(params);
            for(EnergyDetail second: cons){
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
            logger.error("导出能源明细列表EXCEL异常" + e.getMessage());
        }
    }
}
