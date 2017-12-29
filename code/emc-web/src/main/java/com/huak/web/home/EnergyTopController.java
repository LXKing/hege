package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.home.FrameService;
import com.huak.home.type.ToolVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/energy/top")
public class EnergyTopController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String suc="success";
    private String msg="message";
    private String info="查询数据成功";
    @Autowired
    private  FrameService frameService;
    /**
     * 查询首页顶部All的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
     @ResponseBody
     public String getAllData(ToolVO toolVO, HttpServletRequest request){
        logger.info("查询首页顶部All的数据");
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        String eTotal=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costTotal=0.0;
        Double costAll=0.00;
        try{
            eTotal= frameService.selectTopEtotalByMap(params);

            energy=this.frameService.selectTopAllTotalByEnergy(params);
            if(energy==null){
                energy=0.0;
                logger.info("energy再该时间内该成本没有值");
            }
            costTotal=this.frameService.selectTopAllTotal(params);
            if(costTotal==null){
                costTotal=0.0;
                logger.info("costTotalI再该时间内该成本没有值");
            }
            costAll=costTotal+energy;
            logger.info(energy.toString()+"................energy");
            logger.info(costTotal.toString()+"..............costTotal");
            logger.info(costAll.toString());
//            Map<String, Object>  costVo = frameService.selectCostTotalByMap(params);
            logger.info(costAll.toString() );
            topAll.put("eTotal",eTotal);
            topAll.put("costAll",costAll);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        jo.put("all", topAll);
        jo.put(suc, true);
        jo.put(msg, info);

        return jo.toJSONString();
    }
    @RequestMapping(value = "/all1", method = RequestMethod.GET)
    @ResponseBody
    public String getAllData1(ToolVO toolVO, HttpServletRequest request){
        logger.info("查询首页顶部All的数据");
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        String yardage=null;
        String priceArea=null;
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        try{
            yardage = frameService.selectYardageByMap(params);
            priceArea = frameService.selectPriceAreaByMap(params);
            topAll.put("yardage",yardage);
            topAll.put("priceArea",priceArea);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        jo.put("all", topAll);
        jo.put(suc, true);
        jo.put(msg, info);

        return jo.toJSONString();
    }

    /**
     * 查询首页顶部能源，管理成本，其他成本，设备成本，人工成本的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/getAllEachCost", method = RequestMethod.GET)
    @ResponseBody
    public String getAllEachCost(ToolVO toolVO, HttpServletRequest request){
        logger.info("查询首页顶部All的数据");
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        String yardage=null;
        String priceArea=null;
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        try{
            manage= this.frameService.getEachManagerCost(params);
            if(manage==null){
                manage=0.0;
                logger.info("该时间内该成本没有值");
            }
           device =this.frameService.getEachDeviceCost(params);
            if(device==null){
                device=0.0;
                logger.info("该时间内该成本没有值");
            }
            other   = this.frameService.getEachOtherCost(params);
            if(other==null){
                other=0.0;
                logger.info("该时间内该成本没有值");
            }
            labor = this.frameService.getEachLaborCost(params);
            if(labor==null){
                labor=0.0;
                logger.info("该时间内该成本没有值");
            }
            energy=this.frameService.getEachEnergyCost(params);
            if(energy==null){
                energy=0.0;
                logger.info("该时间内该成本没有值");
            }
            logger.info(manage.toString()+".....manage");
            logger.info(device.toString()+".....device");
            logger.info(other.toString()+".....other");
            logger.info(labor.toString()+".....labor");
            logger.info(energy.toString()+".....energy");
            topAll.put("manage",manage);
            topAll.put("device",device);
            topAll.put("other",other);
            topAll.put("labor",labor);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        jo.put("all", topAll);
        jo.put(suc, true);
        jo.put(msg, info);

        return jo.toJSONString();
    }


    /**
     * 查询首页顶部供热源的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    @ResponseBody
    public String getFeedData(ToolVO toolVO, HttpServletRequest request){
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        logger.info("查询首页顶部供热源的数据");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        String eTotal=null;
        String carbonTotal=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costAll=0.00;
        try{
            eTotal= frameService.selectFeedTotalByMap(params);
           Map<String, Object> costVo = frameService.selectFeedCostTotalByMap(params);
            energy=this.frameService.selectTopFeedCarbonTotalByEnergy(params);
           if(costVo==null||costVo.isEmpty()) {
               logger.info("----------------------该成本没有值--------------------------");
            }else {
               if (costVo.get("device") != null && !"".equals(costVo.get("device"))) {
                   device = Double.valueOf(costVo.get("device").toString());
              }
                if (costVo.get("labor") != null && !"".equals(costVo.get("labor"))) {
                   labor = Double.valueOf(costVo.get("labor").toString());
               }
               if (costVo.get("manage") != null && !"".equals(costVo.get("manage"))) {
                  manage = Double.valueOf(costVo.get("manage").toString());
               }
             if (costVo.get("other") != null && !"".equals(costVo.get("other"))) {
                  other = Double.valueOf(costVo.get("other").toString());
              }
               costAll=device+energy+labor+manage+other;
            }
            topAll.put("eTotal",eTotal);
            topAll.put("carbonTotal",carbonTotal);
            topAll.put("costAll",costAll);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        jo.put("all", topAll);
        jo.put(suc, true);
        jo.put(msg, info);

        return jo.toJSONString();
    }
    /**
     * 查询首页顶部管网的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/net", method = RequestMethod.GET)
    @ResponseBody
    public String getNetData(ToolVO toolVO, HttpServletRequest request){
        logger.info("查询首页顶部管网的数据");
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        String netlen=null;
        String carbonTotal=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();

        BigDecimal b1 = new BigDecimal(0.0);
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costAll=0.00;
        try{
            netlen= frameService.selectGetNetLengh(params);
            Map<String, Object> costVo = frameService.selectGetNetCost(params);
            energy=this.frameService.selectTopNetCarbonTotalByEnergy(params);
            if(null==energy){
                energy=0.0;
                logger.info("----------------------energy该成本没有值--------------------------");
            }
            if(costVo==null||costVo.isEmpty()) {
               logger.info("----------------------该成本没有值--------------------------");
           }else {
              if (costVo.get("device") != null && !"".equals(costVo.get("device"))) {
                   device = Double.valueOf(costVo.get("device").toString());
              }
              if (costVo.get("labor") != null && !"".equals(costVo.get("labor"))) {
                labor = Double.valueOf(costVo.get("labor").toString());
                }
               if (costVo.get("manage") != null && !"".equals(costVo.get("manage"))) {
                   manage = Double.valueOf(costVo.get("manage").toString());
               }
               if (costVo.get("other") != null && !"".equals(costVo.get("other"))) {
                   other = Double.valueOf(costVo.get("other").toString());
              }
              costAll=device+energy+labor+manage+other;
         }

            topAll.put("netLen",netlen);
            topAll.put("netCost",costAll);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        jo.put("all", topAll);
        jo.put(suc, true);
        jo.put(msg, info);

        return jo.toJSONString();
    }

    /**
     * 查询首页顶部换热站的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/station", method = RequestMethod.GET)
    @ResponseBody
    public String getStationData(ToolVO toolVO, HttpServletRequest request){
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        logger.info("查询首页顶部换热站的数据");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        String eTotal=null;
        String carbonTotal=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costAll=0.00;
        Map<String, Object>cost=new HashMap<>();
        try{
            eTotal= frameService.selectTopStationTotalByMap(params);
            energy=frameService.selectTopStationCarbonTotalByEnergy(params);
            logger.info(energy+".............energy");
            //carbonTotal = frameService.selectTopStationCarbonTotalByMap(params);
            cost = frameService.selectStationCostTotalByMap(params);
            if (cost == null ||cost.isEmpty()) {
                other = 0.0;
                device = 0.0;
                labor = 0.0;
                manage = 0.0;
              }else{
                device=Double.valueOf(cost.get("device").toString()==null ? "0" : cost.get("device").toString());
                labor=Double.valueOf(cost.get("labor")==null ? "0" : cost.get("labor").toString());
                 manage=Double.valueOf(cost.get("manage")==null ? "0" : cost.get("manage").toString());
                 other=Double.valueOf(cost.get("other")==null ? "0" : cost.get("other").toString());
            }
            costAll=device+energy+labor+manage+other;
            costAll=device+energy+labor+manage+other;
            topAll.put("eTotal",eTotal);
            topAll.put("energy",energy);
            topAll.put("carbonTotal",carbonTotal);
            topAll.put("costAll",costAll);
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
        jo.put("all", topAll);
        jo.put(suc, true);
        jo.put(msg, info);
        logger.info(eTotal+"...............eTotal");
        logger.info(carbonTotal+"......carbonTotal");
        return jo.toJSONString();
    }

    /**
     * 查询首页顶部管线的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/line", method = RequestMethod.GET)
    @ResponseBody
    public String getLineData(ToolVO toolVO, HttpServletRequest request){
        logger.info("查询首页顶部管线的数据");
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        String linelen=null;
        String carbonTotal=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costAll=0.00;
        try{
            linelen= frameService.selectGetLineLengh(params);
            energy=this.frameService.selectTopLineCarbonTotalByEnergy(params);
          Map<String, Object> costVo = frameService.selectGetLineCost(params);
           if(costVo==null||costVo.isEmpty()) {
               logger.info("----------------------该成本没有值--------------------------");
            }else {
               if (costVo.get("device") != null && !"".equals(costVo.get("device"))) {
                   device = Double.valueOf(costVo.get("device").toString());
                }
                if (costVo.get("labor") != null && !"".equals(costVo.get("labor"))) {
                   labor = Double.valueOf(costVo.get("labor").toString());
                }
                if (costVo.get("manage") != null && !"".equals(costVo.get("manage"))) {
                   manage = Double.valueOf(costVo.get("manage").toString());
                }
               if (costVo.get("other") != null && !"".equals(costVo.get("other"))) {
                   other = Double.valueOf(costVo.get("other").toString());
               }
              costAll=device+energy+labor+manage+other;
            }
            topAll.put("lineCost",costAll);
            topAll.put("lineLen",linelen);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        jo.put("all", topAll);
        jo.put(suc, true);
        jo.put(msg, info);

        return jo.toJSONString();
    }

    /**
     * 查询首页顶部民户的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/room", method = RequestMethod.GET)
    @ResponseBody
    public String getRoomData(ToolVO toolVO, HttpServletRequest request){
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        logger.info("查询首页顶部民户的数据");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        String rTotal=null;
        String hgl=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costAll=0.00;
        try{
            energy=this.frameService.selectTopRoomCarbonTotalByEnergy(params);
            Map<String, Object> costVo = frameService.selectTopRoomCostByMap(params);
            if(costVo==null||costVo.isEmpty()) {
               logger.info("----------------------该成本没有值--------------------------");
            }else {
                if (costVo.get("device") != null && !"".equals(costVo.get("device"))) {
                    device = Double.valueOf(costVo.get("device").toString());
               }
                if (costVo.get("labor") != null && !"".equals(costVo.get("labor"))) {
                    labor = Double.valueOf(costVo.get("labor").toString());
                }
                if (costVo.get("manage") != null && !"".equals(costVo.get("manage"))) {
                    manage = Double.valueOf(costVo.get("manage").toString());
                }
                if (costVo.get("other") != null && !"".equals(costVo.get("other"))) {
                    other = Double.valueOf(costVo.get("other").toString());
                }
                costAll=device+energy+labor+manage+other;
           }
            topAll.put("roomCost",costAll);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        jo.put("all", topAll);
        jo.put(suc, true);
        jo.put(msg, info);
        return jo.toJSONString();
    }

}
