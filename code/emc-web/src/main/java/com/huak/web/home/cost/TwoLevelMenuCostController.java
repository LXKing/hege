package com.huak.web.home.cost;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CollectionUtil;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.utils.DoubleUtils;
import com.huak.home.cost.TwoLevelMenuCostService;
import com.huak.home.type.ToolVO;
import com.huak.org.model.Company;
import com.huak.web.home.BaseController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.DataBuffer;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
@Controller
@RequestMapping(value = "/cost/secondary")
public class TwoLevelMenuCostController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TwoLevelMenuCostService twoLevelMenuCostService;
    private static String[] TYPES={"manage", "other", "device", "labor"};


    @RequestMapping(value = "/tsec", method = RequestMethod.GET)
    public String secondEconPage(Model model, HttpServletRequest request) {
        logger.info("跳转二级成本页面");
        try {
            Company company = (Company) request.getSession().getAttribute(Constants.SESSION_COM_KEY);

            model.addAttribute("company", company);
        } catch (Exception e) {
            logger.error("跳转二级能耗页面异常：" + e.getMessage());
        }
        return "second/ccont";
    }


    /**
     * 能源流成本站比
     * */
    @RequestMapping(value = "/second/TwoEnergyFlowPie", method = RequestMethod.GET)
    @ResponseBody
    public String TwoEnergyFlowPie(ToolVO toolVO, HttpServletRequest request){
        logger.info("二级分页中能源流成本站比");
        Map params = paramsPackageFgs(toolVO, request);
        JSONObject jo = new JSONObject();
        //获得今年的能源成本
        String start = params.get("startTime").toString();
        String end = params.get("endTime").toString();
        logger.info(start);
        logger.info(end);
        List<Map<String,Object>> TwoEnergyCost=this.twoLevelMenuCostService.TwoEnergyFlowPie(params);
        jo.put(Constants.LIST, TwoEnergyCost);
        System.out.println(jo.toJSONString()+".................");
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }
    /**
     * 能耗流成本同比
     * */
    @RequestMapping(value = "/second/TwoEnergyFlowPieAn", method = RequestMethod.GET)
    @ResponseBody
    public String TwoEnergyFlowPieAn(ToolVO toolVO, HttpServletRequest request){
        logger.info("二级分页中能耗流成本同比");
        Map params = paramsPackageFgs(toolVO, request);
        JSONObject jo = new JSONObject();
        Double costTotolYear = 0.0;
        Double costTotolLastYear = 0.0;
        List<Double> costTotolMapYear = new ArrayList<Double>();
        List<Double> costTotolMapLast = new ArrayList<Double>();
        //获得今年的能源成本
        List<Map<String,Object>> TwoEnergyCost=this.twoLevelMenuCostService.TwoEnergyFlowPie(params);
        //获得去年的能源成本
//        List<Map<String,Object>> TwoEnergyCostAn=this.twoLevelMenuCostService.TwoEnergyFlowPieAn(params);

        for (Map<String,Object> map : TwoEnergyCost){
            costTotolYear=Double.valueOf(map.get("value")==null ? "0" : map.get("value").toString());
            costTotolMapYear.add(costTotolYear);
        }
//        for (Map<String,Object> map : TwoEnergyCostAn){
//            costTotolLastYear = Double.valueOf(map.get("value")==null ? "0" : map.get("value").toString());
//            costTotolMapLast.add(costTotolLastYear);
//        }

        Map<String ,Object> resutMap = new HashMap<String ,Object>();
        resutMap.put("cur",costTotolMapYear);
//        resutMap.put("last",costTotolMapLast);
        jo.put("data", resutMap);
        System.out.println(jo.toJSONString()+".................");
        return jo.toJSONString();
    }

    /**
     * 分公司成本排名
     * */
    @RequestMapping(value = "/second/BranchEnergyCons", method = RequestMethod.GET)
    @ResponseBody
    public String  BranchEnergyCons(ToolVO toolVO, HttpServletRequest request){
        logger.info("二级分页中分公司成本排比");
        Map params = paramsPackageFgs(toolVO, request);
        JSONObject jo = new JSONObject();
        List<Double> list = new ArrayList<Double>();
        List<String> xaxis = new ArrayList<String>();
        //获得今年的能源成本
        List<Map<String,Object>> TwoEnergyCost=this.twoLevelMenuCostService.BranchEnergyCons(params);
        for (Map <String,Object> map : TwoEnergyCost){
                list.add(Double.valueOf(map.get("value") == null ? "0.0" : map.get("value").toString()));
                xaxis.add(map.get("name") == null ? "" : map.get("name").toString());
        }
        jo.put(Constants.LIST, list);
        jo.put(Constants.XAXIS,xaxis);
        System.out.println(jo.toJSONString()+".................");
        return jo.toJSONString();
    }

    /**
     * 分公司成本同比
     * */
    @RequestMapping(value = "/second/BranchEnergyConsAn", method = RequestMethod.GET)
    @ResponseBody
    public String  BranchEnergyConsAn(ToolVO toolVO, HttpServletRequest request){
        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);
            List<Map<String, Object>> energySecondList = twoLevelMenuCostService.subEnergyAn(params);
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
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     * 能源流成本趋势对比分析
     * */
    @RequestMapping(value = "/second/TwoEnergyCostTrend", method = RequestMethod.GET)
    @ResponseBody
    public String  TwoEnergyCostTrend(ToolVO toolVO, HttpServletRequest request){
        logger.info("能源流单耗趋势对比图");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            List<Map<String,Object>> trendList = twoLevelMenuCostService.TwoEnergyCostTrend(params);

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
    /**
     * 分公司成本同比图
     * */
    @RequestMapping(value = "/costFlowBar", method = RequestMethod.GET)
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
            Map<String, Object> retMap = twoLevelMenuCostService.groubCostAn(params);
            jo.put("data", retMap);
        } catch (Exception e) {
            logger.error("查询能源流同比异常" + e.getMessage());
            jo.put("success", false);
            jo.put("message", "查询能源流同比异常！");
        }
        return jo.toJSONString();
    }


    /**
     * 分公司成本占比图
     * */
    @RequestMapping(value = "/second/BranchCostProportion", method = RequestMethod.GET)
    @ResponseBody
    public String  BranchCostProportion(ToolVO toolVO, HttpServletRequest request){
        logger.info("分公司成本占比图");
        Map params = paramsPackageFgs(toolVO, request);
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> BranchCostProportion =this.twoLevelMenuCostService.BranchCostProportion(params);
        jo.put(Constants.LIST,BranchCostProportion);
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }
    /**
     * 集团总成本趋势图
     *
     * @param toolVO
     * @return
     */
    @RequestMapping(value = "/line/groupTotalCost", method = RequestMethod.GET)
    @ResponseBody
    public String groupTotalCost(ToolVO toolVO, HttpServletRequest request)throws Exception {
        logger.info("集团总成本趋势图");
        JSONObject jo = new JSONObject();
//        try {
            /*封装条件*/
        long startTime=System.currentTimeMillis();
        //执行方法

            Map<String,Object>  params = paramsPackageOrg(toolVO, request);
            String start = params.get("startTime").toString();
            String end = params.get("endTime").toString();
            String orgId = params.get("orgId").toString();
//            //查询折线数据
            List<Map<String, Object>> bqLine = twoLevelMenuCostService.selectBmBqLine(params);
            List<Map<String, Object>> tqLine = twoLevelMenuCostService.selectBmTqLine(params);
            Double bq = twoLevelMenuCostService.selectBmBqTotal(params);
            Double tq = twoLevelMenuCostService.selectBmTqTotal(params);
            if(null == bq){
                bq = 0d;
            }
           if(null == tq){
               tq = 0d;
           }
            Double tb = DoubleUtils.div(DoubleUtils.sub(bq,tq),tq,4)*100;
        JSONObject  joson = CollectionUtil.packageDataLine(start,end,bqLine,tqLine);
        jo.put("energy",joson);
            jo.put("bq",bq);
            jo.put("tq",tq);
            jo.put("tb",tb);
            jo.put("success", true);
            jo.put("message", "查询集团能耗数据成功！");
            jo.put("tb",bqLine);
        long endTime=System.currentTimeMillis();
        float excTime=(float)(endTime-startTime)/1000;
        System.out.println("执行时间："+excTime+"s");
//        } catch (Exception e) {
//            logger.error("集团总成本趋势图" + e.getMessage());
//            jo.put("success", false);
//            jo.put("message", "查询能耗标煤时间折线图统一接口异常！");
//        }
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }
    /**
     * 各个子成本趋势图
     *
     * @param toolVO
     * @return
     */
    @RequestMapping(value = "/line/SubCost", method = RequestMethod.GET)
    @ResponseBody
    public String SubCost(ToolVO toolVO, HttpServletRequest request) {
        logger.info("各个子成本趋势图");
        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            String start = params.get("startTime").toString();
            String end = params.get("endTime").toString();
            //查询折线数据

            List<Map<String, Object>> bqLine = twoLevelMenuCostService.selectSubcostLine(params);
            List<Map<String, Object>> tqLine = twoLevelMenuCostService.selectSubcostLineLastYear(params);
            Map<String, Object> bqMap = twoLevelMenuCostService.selectSubcostTotal(params);
            Map<String, Object> tqMap = twoLevelMenuCostService.selectSubcostTotalLastyear(params);
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
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     * 各个子成本中的能源趋势图
     *
     * @param toolVO
     * @return
     */
    @RequestMapping(value = "/line/SubCostEnergy", method = RequestMethod.GET)
    @ResponseBody
    public String SubCostEnergy(ToolVO toolVO, HttpServletRequest request)throws Exception {
        logger.info("各个子成本中的能源趋势图");
        JSONObject jo = new JSONObject();
          /*封装条件*/
        Map params = paramsPackageOrg(toolVO, request);
       try {
            String start = params.get("startTime").toString();
            String end = params.get("endTime").toString();
            //查询折线数据
            List<Map<String, Object>> bqLine = twoLevelMenuCostService.selectSubcostEnergyLine(params);
            List<Map<String, Object>> tqLine = twoLevelMenuCostService.selectSubcostEnergyLineLastYear(params);
            Map<String, Object> bqMap = twoLevelMenuCostService.selectSubcostTotalEnergy(params);
            Map<String, Object> tqMap = twoLevelMenuCostService.selectSubcostTotalEnergyLastYear(params);
            Map<String, Object> tbMap = new HashMap<>();
            Double bq = 0d;
            Double tq = 0d;
        if(null!=bqMap.get("energy")){
            bq = Double.valueOf(bqMap.get("energy").toString());
        }

        if(null== tqMap ||tqMap.isEmpty()|| tqMap.size()==0 ){
            tq=0.0;
       }else{
            tq = Double.valueOf(tqMap.get("energy").toString());
        }

       Double tb = DoubleUtils.div(DoubleUtils.sub(bq,tq),bq,4)*100;
        tbMap.put("energy",tb);

        JSONObject jsonObject = CollectionUtil.packageDataLineYl(start,end,bqLine,tqLine,"energy");
        jo.put("energy",jsonObject);
            jo.put("bq",bqMap);
            jo.put("tq",tqMap);
            jo.put("tb",tbMap);
            jo.put("success", true);
            jo.put("messages", "查询集团能耗数据成功！");
        } catch (Exception e) {
            logger.error("查询能耗用量时间折线图统一接口异常" + e.getMessage());
           jo.put("success", false);
         jo.put("message", "查询能耗用量时间折线图统一接口异常！");
        }
    logger.info(jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     * 分公司成本明细表
     * */
    @RequestMapping(value = "/line/SubCostDetailed", method = RequestMethod.GET)
    @ResponseBody
    public String SubCostDetailed(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司成本明细表");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        String stattime =params.get("startTime").toString();
        String endTime =params.get("endTime").toString();
        logger.info(stattime+"....................stattime");
        logger.info(endTime+"....................endTime");
        List<Integer> subdm = new ArrayList<Integer>();
        List<Integer> suben = new ArrayList<Integer>();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map groubMap=new HashMap();

        HttpSession session = request.getSession();
        String Detailedmapid;
        String Energymapid;
        String EnergymapLastYearid;
        Map<String,Object> enertyCost = new HashMap<String, Object>();
        Map<String,Object> laborCost = new HashMap<String,Object>();
        Map<String,Object> managerCost = new HashMap<String,Object>();
        Map<String,Object> deviceCost = new HashMap<String,Object>();
        Map<String,Object> otherCost = new HashMap<String,Object>();
        Map<String,Object> totalCost = new HashMap<String,Object>();
        Map<String,Object> totalCostAn = new HashMap<String,Object>();
        Map<String,Object> energyCostAn = new HashMap<String,Object>();
        Map<String,Object> laborCostAn = new HashMap<String,Object>();
        Map<String,Object> managerCostAn = new HashMap<String,Object>();
        Map<String,Object> deviceCostAn = new HashMap<String,Object>();
        Map<String,Object> otherCostAn = new HashMap<String,Object>();
        Map<String,Object> orgname = new HashMap<String, Object>();
        Map<String,Object> orgid = new HashMap<String,Object>();
        List<Double> enertylist = new ArrayList<Double>();
        List<Double> laborlist = new ArrayList<Double>();
        List<Double> managerlist = new ArrayList<Double>();
        List<Double> devicelist = new ArrayList<Double>();
        List<Double> otherlist = new ArrayList<Double>();
        List<Double> totallist = new ArrayList<Double>();
        List<Double> totallistAn = new ArrayList<Double>();
        List<Double> enertylistAn = new ArrayList<Double>();
        List<Double> managerlistAn = new ArrayList<Double>();
        List<Double> devicelistAn = new ArrayList<Double>();
        List<Double> laborlistAn = new ArrayList<Double>();
        List<Double> otherlistAn = new ArrayList<Double>();
        List<String> orgName = new ArrayList<String>();
        List<Integer> orgId = new ArrayList<Integer>();
        //管理费，其他费，设备费包含去年和今年的
        List<Map<String, Object>>SubCostDetailedMap=this.twoLevelMenuCostService.SubCostDetailed(params);
        Map<String, Object> org=this.twoLevelMenuCostService.selectOrg(params);
        //今年的能源
        List<Map<String, Object>>SubCostDetailedEnergy=this.twoLevelMenuCostService.SubCostDetailedEnergy(params);
        //去年的能源
        List<Map<String, Object>> SubCostDetailedEnergyLastYear=this.twoLevelMenuCostService.SubCostDetailedEnergyLastYear(params);
        //将去年的能源费和今年的能源费整合在一起
        for (Map<String, Object> subCostenergy:SubCostDetailedEnergy) {
            for (Map<String, Object>subCostenergyTq:SubCostDetailedEnergyLastYear) {
               String subCostenergyId= subCostenergy.get("id").toString();
                String subCostenergyTqId= subCostenergyTq.get("id").toString();
                String subCostenergyTqTotal= subCostenergyTq.get("energyExpensesTq").toString()==null?"0.0":subCostenergy.get("energyExpenses").toString();
                if(subCostenergyId.equals(subCostenergyTqId)){
                    subCostenergy.put("energyExpensesTq",subCostenergyTqTotal);
                    break;
                }
            }
        }
        //将能源费和其他 的费用整合在一起去
        for (Map<String, Object>  subCostDetailedMap: SubCostDetailedMap) {
            for (Map<String, Object> subCostenergy:SubCostDetailedEnergy) {
                String subCostDetailedMapId= subCostDetailedMap.get("id").toString();
                String subCostenergyId= subCostenergy.get("id").toString();
                String subCostenergyTotal= subCostenergy.get("energyExpenses").toString()==null?"0.0":subCostenergy.get("energyExpenses").toString();
                String subCostenergyTqTotal= subCostenergy.get("energyExpensesTq").toString()==null?"0.0":subCostenergy.get("energyExpensesTq").toString();
                if(subCostDetailedMapId.equals(subCostenergyId)){
                    subCostDetailedMap.put("energyExpenses",subCostenergyTotal);
                    subCostDetailedMap.put("energyExpensesTq",subCostenergyTqTotal);
                    break;
                }
            }
        }
            //查询每个公司的今年总成本
            Double currtotal=0.0;
            //查询每个公司的去年总成本
            Double totalLastYear=0.0;
            for (Map<String,Object> Detailedmap : SubCostDetailedMap) {
                        Double laborMoney=Double.parseDouble(Detailedmap.get("laborExpenses")==null?"0.0":Detailedmap.get("laborExpenses").toString());
                        Double managerMoney=Double.parseDouble(Detailedmap.get("managerExpenses")==null?"0.0":Detailedmap.get("managerExpenses").toString());
                        Double energyMoney=Double.parseDouble(Detailedmap.get("energyExpenses")==null?"0.0":Detailedmap.get("energyExpenses").toString());
                        Double deviceMoney=Double.parseDouble(Detailedmap.get("deviceExpenses")==null?"0.0":Detailedmap.get("deviceExpenses").toString());
                        Double otherMoney=Double.parseDouble(Detailedmap.get("otherExpenses")==null?"0.0":Detailedmap.get("otherExpenses").toString());
                        Double laborMoneyLastYear=Double.parseDouble(Detailedmap.get("laborExpenses")==null?"0.0":Detailedmap.get("laborExpenses").toString());

                        Double managerMoneyLastYear=Double.parseDouble(Detailedmap.get("managerExpensesTq")==null?"0.0":Detailedmap.get("managerExpensesTq").toString());
                        Double energyMoneyLastYear=Double.parseDouble(Detailedmap.get("energyExpensesTq")==null?"0.0":Detailedmap.get("energyExpensesTq").toString());
                        Double deviceMoneyLastYear=Double.parseDouble(Detailedmap.get("deviceExpensesTq")==null?"0.0":Detailedmap.get("deviceExpensesTq").toString());
                        Double otherMoneyLastYear=Double.parseDouble(Detailedmap.get("otherExpensesTq")==null?"0.0":Detailedmap.get("otherExpensesTq").toString());

                        orgName .add(Detailedmap.get("orgname") == null ? "" : Detailedmap.get("orgname").toString());
                        orgId.add(Integer.parseInt(Detailedmap.get("id").toString()));
                        currtotal=laborMoney+managerMoney+energyMoney+deviceMoney+otherMoney;
                        totalLastYear=laborMoneyLastYear+managerMoneyLastYear+energyMoneyLastYear+deviceMoneyLastYear+otherMoneyLastYear;
                        Double energyAn = 0.0;
                        Double laborAn = 0.0;
                        Double managerAn =0.0;
                        Double deviceAn = 0.0;
                        Double otherAn = 0.0;
                        Double totalAn = 0.0;
                        if (energyMoneyLastYear!=0){
                            energyAn = ((energyMoney-energyMoneyLastYear)/energyMoneyLastYear)*100;
                        }else {
                            energyAn = 0.0;
                        }

                        if (laborMoneyLastYear!=0){
                            laborAn = ((laborMoney-laborMoneyLastYear)/laborMoneyLastYear)*100;
                        }else {
                            laborAn = 0.0;
                        }

                        if (managerMoneyLastYear!=0){
                            managerAn = ((managerMoney-managerMoneyLastYear)/managerMoneyLastYear)*100;
                        }else {
                            managerAn = 0.0;
                        }
                        if (deviceMoneyLastYear!=0){
                            deviceAn = ((deviceMoney-deviceMoneyLastYear)/deviceMoneyLastYear)*100;
                        }else {
                            deviceAn = 0.0;
                        }
                        if (otherMoneyLastYear!=0){
                            otherAn = ((otherMoney-otherMoneyLastYear)/otherMoneyLastYear)*100;
                        }else {
                            otherAn = 0.0;
                        }
                        if (totalLastYear!=0){
                            totalAn = ((currtotal-totalLastYear)/totalLastYear)*100;
                        }else {
                            totalAn = 0.0;
                        }

                        System.out.println(energyAn);


                        totallist.add(currtotal);
                        enertylist.add(energyMoney);
                        laborlist.add(laborMoney);
                        managerlist.add(managerMoney);
                        devicelist.add(deviceMoney);
                        otherlist.add(otherMoney);

                        totallistAn.add(totalAn);
                        enertylistAn.add(energyAn);
                        laborlistAn.add(laborAn);
                        managerlistAn.add(managerAn);
                        devicelistAn.add(deviceAn);
                        otherlistAn.add(otherAn);
            }
        orgid.put("id",orgId);
        orgname.put("orgName",orgName);
        totalCost.put("totalBq",totallist);
        enertyCost.put("enertyBq",enertylist);
        laborCost.put("laborBq",laborlist);
        managerCost.put("managerBq",managerlist);
        deviceCost.put("deviceBq",devicelist);
        otherCost.put("otherBq",otherlist);

        totalCostAn.put("totalAn",totallistAn);
        energyCostAn.put("enertyAn",enertylistAn);
        laborCostAn.put("laborAn",laborlistAn);
        managerCostAn.put("managerAn",managerlistAn);
        deviceCostAn.put("deviceAn",devicelistAn);
        otherCostAn.put("otherAn",otherlistAn);

        list.add(orgid);
        list.add(orgname);
        list.add(enertyCost);
        list.add(laborCost);
        list.add(totalCost);
        list.add(managerCost);
        list.add(deviceCost);
        list.add(otherCost);

        list.add(totalCostAn);
        list.add(energyCostAn);
        list.add(laborCostAn);
        list.add(managerCostAn);
        list.add(deviceCostAn);
        list.add(otherCostAn);

        jo.put(Constants.LIST,list);
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     * 能源流明细
     * */
    @RequestMapping(value = "/second/EnergyFlowDetail", method = RequestMethod.GET)
    @ResponseBody
    public String  EnergyFlowDetail(ToolVO toolVO, HttpServletRequest request){
        logger.info("查询能源流明细");
        Map params = paramsPackageFgs(toolVO, request);
        JSONObject jo = new JSONObject();
        try {
            List<Map<String,Object>> EnergyFlowDetail=this.twoLevelMenuCostService.EnergyFlowDetail(params);
            jo.put(Constants.LIST,EnergyFlowDetail);
            logger.info(jo.toJSONString()+"能源六明细");
        } catch (Exception e) {
            logger.error("查询能源流明细接口异常" + e.getMessage());
            jo.put("success", false);
            jo.put("message", "查询能源流明细接口异常！");
        }
        return jo.toJSONString();
    }
    /**
     *能源流成本趋势对比图
     * twolEvelEnergyFlowLine
     * */

    @RequestMapping(value = "/second/twolEvelEnergyFlowLine", method = RequestMethod.GET)
    @ResponseBody
    public String twolEvelEnergyFlowLine(ToolVO toolVO, HttpServletRequest request) {
        logger.info("查询能源流趋势对比图");
        JSONObject jo = new JSONObject();
        try {
            jo.put("success", true);
            jo.put("message", "查询能源流成本趋势对比图成功！");
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            //查询折线数据
            Map<String, Object> retMap = this.twoLevelMenuCostService.twolEvelEnergyFlowLine(params);
            jo.put("datas", retMap);
        } catch (Exception e) {
            logger.error("查询能源流成本趋势对比图" + e.getMessage());
            jo.put("success", false);
            jo.put("message", "查询能源流成本趋势对比图！");
            System.out.println(jo.toJSONString());
        }
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     *分公司成本趋势对比图
     * twolEvelEnergyFlowLine
     * */

    @RequestMapping(value = "/second/twolEvelSubFlowLine", method = RequestMethod.GET)
    @ResponseBody
    public String twolEvelSubFlowLine(ToolVO toolVO, HttpServletRequest request) {
        logger.info("分公司成本趋势对比图");
        JSONObject jo = new JSONObject();


            //查询折线数据
        try {
            Map params = paramsPackageFgs(toolVO, request);
            List<Map<String, Object>> trendList = twoLevelMenuCostService.BranchCostTrend(params);
            List<String> xAxis = new ArrayList<>();
            List<Map<String, Object>> series = new LinkedList<>();
            List<String> legends = new ArrayList<>();
            //先确定几条线和横坐标
            for (Map<String, Object> map : trendList) {
                String fgsId = map.get("id").toString();
                String name = map.get("NAME").toString();
                String date = map.get("DATE").toString();
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
                    String name = map.get("NAME").toString();
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
           logger.error("分公司成本趋势对比图异常" + e.getMessage());
       }
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }
    /*
    *导出分公司成本明细
    * */
    @RequestMapping(value = "/fgs/export", method = RequestMethod.GET)
    public void export(ToolVO toolVO, HttpServletResponse response, HttpServletRequest request) {
        logger.info("导出分公司成本列表EXCEL");
        Map groubMap=new HashMap();
        HttpSession session = request.getSession();
        String Detailedmapid;
        String Energymapid;
        String EnergymapLastYearid;
        List<Object> costs = new ArrayList<Object>();

        String workBookName = "分公司成本列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("ID", "组织主键");
        cellName.put("orgName", "组织名称");
        cellName.put("totalBq", "能耗总量本期");
        cellName.put("totalAn", "能耗总量同比");
        cellName.put("energyBq", "能源成本本期");
        cellName.put("energyTq", "能源成本同比");
        cellName.put("managerBq", "管理成本本期");
        cellName.put("managerAn", "管理成本同比");
        cellName.put("otherBq", "其他成本本期");
        cellName.put("otherAn", "其他成本同比");
        cellName.put("deviceBq", "设备成本本期");
        cellName.put("deviceAn", "设备成本同比");
        cellName.put("laborBq", "人工成本本期");
        cellName.put("laborAn", "人工成本同比");
        List<Map<String, Object>> cellValues = new ArrayList<Map<String, Object>>();//列值
        OutputStream out = null;
        try {
            /*封装条件*/
            Map params = paramsPackageFgs(toolVO, request);

           /* List<EnergySecond> energys = eaService.exportAssessmentIndicators(params);*/

            List<Map<String, Object>>SubCostDetailedMap=this.twoLevelMenuCostService.SubCostDetailed(params);
            Map<String, Object> org=this.twoLevelMenuCostService.selectOrg(params);
            //今年的能源
            List<Map<String, Object>>SubCostDetailedEnergy=this.twoLevelMenuCostService.SubCostDetailedEnergy(params);
            //去年的能源
            List<Map<String, Object>> SubCostDetailedEnergyLastYear=this.twoLevelMenuCostService.SubCostDetailedEnergyLastYear(params);
           /* Map<String, Object> subcostetail=new HashMap<>();*/
            for (Map<String,Object> Detailedmap : SubCostDetailedMap) {
                for (Map<String,Object> EnergymapLastYear : SubCostDetailedEnergyLastYear) {
                    for (Map<String, Object> Energymap : SubCostDetailedEnergy) {
                        Detailedmapid = Detailedmap.get("id").toString();
                        Energymapid = Energymap.get("fgsid").toString();
                        EnergymapLastYearid=EnergymapLastYear.get("fgsid").toString();
                        if (Detailedmapid.equals(Energymapid) && Detailedmapid.equals(EnergymapLastYearid)) {
                            Detailedmap.put("能源成本", Energymap.get("total").toString());
                            Detailedmap.put("去年能源成本", EnergymapLastYear.get("total").toString());
                            break;
                        }
                    }
                }
            }
            //查询每个公司的今年总成本
            Double totalBq=0.0;
            //查询每个公司的去年总成本
            Double totalLastYear=0.0;
            for (Map<String,Object> Detailedmap : SubCostDetailedMap) {
                Double laborBq=Double.parseDouble(Detailedmap.get("人工费")==null?"0.0":Detailedmap.get("人工费").toString());
                Double managerBq=Double.parseDouble(Detailedmap.get("管理费")==null?"0.0":Detailedmap.get("管理费").toString());
                Double energyBq=Double.parseDouble(Detailedmap.get("能源成本")==null?"0.0":Detailedmap.get("能源成本").toString());
                Double deviceBq=Double.parseDouble(Detailedmap.get("设备费")==null?"0.0":Detailedmap.get("设备费").toString());
                Double otherBq=Double.parseDouble(Detailedmap.get("其他费")==null?"0.0":Detailedmap.get("其他费").toString());
                Integer orgId = Integer.parseInt(Detailedmap.get("id")==null?"":Detailedmap.get("id").toString());
                String orgName = Detailedmap.get("orgname") == null?"" : Detailedmap.get("orgname").toString();
                Double laborMoneyLastYear=Double.parseDouble(Detailedmap.get("去年人工费")==null?"0.0":Detailedmap.get("去年人工费").toString());

                Double managerMoneyLastYear=Double.parseDouble(Detailedmap.get("去年管理费")==null?"0.0":Detailedmap.get("去年管理费").toString());
                Double energyMoneyLastYear=Double.parseDouble(Detailedmap.get("去年能源成本")==null?"0.0":Detailedmap.get("去年能源成本").toString());
                Double deviceMoneyLastYear=Double.parseDouble(Detailedmap.get("去年设备费")==null?"0.0":Detailedmap.get("去年设备费").toString());
                Double otherMoneyLastYear=Double.parseDouble(Detailedmap.get("去年其他费")==null?"0.0":Detailedmap.get("去年其他费").toString());
                totalBq=laborBq+managerBq+energyBq+deviceBq+otherBq;
                totalLastYear=laborMoneyLastYear+managerMoneyLastYear+energyMoneyLastYear+deviceMoneyLastYear+otherMoneyLastYear;
                Double energyAn = 0.0;
                Double laborAn = 0.0;
                Double managerAn =0.0;
                Double deviceAn = 0.0;
                Double otherAn = 0.0;
                Double totalAn = 0.0;
                if (energyMoneyLastYear!=0){
                    energyAn = ((energyBq-energyMoneyLastYear)/energyMoneyLastYear)*100;
                }else {
                    energyAn = 0.0;
                }

                if (laborMoneyLastYear!=0){
                    laborAn = ((laborBq-laborMoneyLastYear)/laborMoneyLastYear)*100;
                }else {
                    laborAn = 0.0;
                }

                if (managerMoneyLastYear!=0){
                    managerAn = ((managerBq-managerMoneyLastYear)/managerMoneyLastYear)*100;
                }else {
                    managerAn = 0.0;
                }
                if (deviceMoneyLastYear!=0){
                    deviceAn = ((deviceBq-deviceMoneyLastYear)/deviceMoneyLastYear)*100;
                }else {
                    deviceAn = 0.0;
                }
                if (otherMoneyLastYear!=0){
                    otherAn = ((otherBq-otherMoneyLastYear)/otherMoneyLastYear)*100;
                }else {
                    otherAn = 0.0;
                }
                if (totalLastYear!=0){
                    totalAn = ((totalBq-totalLastYear)/totalLastYear)*100;
                }else {
                    totalAn = 0.0;
                }

                costs.add(orgId);
                costs.add(orgName);
                costs.add(energyBq);
                costs.add(laborBq);
                costs.add(managerBq);
                costs.add(deviceBq);
                costs.add(otherBq);
                costs.add(totalBq);

                costs.add(energyAn);
                costs.add(laborAn);
                costs.add(managerAn);
                costs.add(deviceAn);
                costs.add(otherAn);
                costs.add(totalAn);

                for (Object second : costs){
                    cellValues.add(CollectionUtil.Obj2Map(second));
                }
            }
            /*for (EnergySecond second : energys) {
                cellValues.add(CollectionUtil.Obj2Map(second));
            }*/

            HSSFWorkbook wb = CommonExcelExport.excelExportCost(cellName, cellValues);
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
        } catch (Exception e){
            logger.error("导出分公司成本列表EXCEL异常" + e.getMessage());
        }
    }


    /**
     * 导出能源流成本明细
     */
    @RequestMapping(value = "exportEnergyFlowDetail", method = RequestMethod.GET)
    public void exportEnergyFlowDetail(HttpServletResponse response, ToolVO toolVO, HttpServletRequest request) {
        logger.info("导出能源流-成本列表EXCEL");
        try {

            JSONObject jo = new JSONObject();
            jo.put(Constants.FLAG, false);
            Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
            cellName.put("unittype", "编号");
            cellName.put("unitname", "能源站");
            cellName.put("groupE", "成本总量本期");
            cellName.put("groupR", "成本总量同期");
            cellName.put("groupS", "成本总量同比");
            cellName.put("waterE", "水能耗成本本期");
            cellName.put("waterR", "水能耗成本同期");
            cellName.put("waterS", "水能耗成本同比");
            cellName.put("elecE", "电能耗成本本期");
            cellName.put("elecR", "电能耗成本同期");
            cellName.put("elecS", "电能耗成本同比");
            cellName.put("gasE", "汽能耗成本本期");
            cellName.put("gasR", "汽能耗成本同期");
            cellName.put("gasS", "汽能耗成本同比");
            cellName.put("hotE", "热能耗成本本期");
            cellName.put("hotR", "热能耗成本同期");
            cellName.put("hotS", "热能耗成本同比");
            cellName.put("coalE", "煤能耗成本本期");
            cellName.put("coalR", "煤能耗成本同期");
            cellName.put("coalS", "煤能耗成本同比");
            cellName.put("oilE", "煤能耗成本本期");
            cellName.put("oilR", "煤能耗成本同期");
            cellName.put("oilS", "煤能耗成本同比");
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

           /* List<Map<String, Object>> cellValues = eaService.exportEnergyFlowDetail(params);*/
            List<Map<String,Object>> cellValues = this.twoLevelMenuCostService.EnergyFlowDetail(params);
            if (cellValues == null) {
                jo.put(Constants.MSG, "导出失败");
            } else if (cellValues.size() == 0) {
                jo.put(Constants.MSG, "没有数据要导出");
            } else {
                jo.put(Constants.MSG, "导出失败");
                HSSFWorkbook wb = CommonExcelExport.excelExportCost(cellName, cellValues);
                OutputStream out = response.getOutputStream();
                //输出Excel文件
                String mimetype = "application/vnd.ms-excel";
                response.setContentType(mimetype);
                response.setCharacterEncoding("UTF-8");
                String fileName = "能源流-成本列表.xls";
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                wb.write(out);
                out.flush();
            }
        } catch (Exception e) {
            logger.error("导出能源流-成本列表EXCEL异常" + e.getMessage());
        }
    }
}
