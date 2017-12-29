package com.huak.web.home.cost;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.cost.ThiredCostTypeService;
import com.huak.home.cost.ThiredPageCostService;
import com.huak.home.type.ToolVO;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2017/12/14.
 */
@Controller
@RequestMapping(value = "/thired/cost")
public class ThiredCostTypeController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private static String  ENERGY_TYPE = "energytype";
    private static  String ORG_TYPE = "orgType";
    private static String TYPE_ID = "id";

    @Resource
    private ThiredCostTypeService thiredCostTypeService;
    @Resource
    private ThiredPageCostService thiredPageCostService;

    /**
     * 跳转三级单成本页面
     * @param
     * @return
     */
    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public String secondEconPage(@PathVariable String id, HttpServletRequest request){
        logger.info("跳转成本类型三级能耗页面");
        logger.info(id+"..........page");
        return "third/cost";
    }

    /**
     * 跳转用能单位类型三级能耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/unit/{unittype}", method = RequestMethod.GET)
    public String unitPage(Model model,@PathVariable("unittype") String unittype, HttpServletRequest request){
        logger.info("跳转用能单位类型三级能耗页面");
        logger.info("unittype......."+unittype);
        model.addAttribute("unittype",unittype);
        return "third/cost-unit";
    }

    /**
     *三级页面-用能单位类型-各个成本对比数据加载   点击成本类型
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/getUnitCostDetail", method = RequestMethod.GET)
    @ResponseBody
    public String getUnitCostDetail(ToolVO toolVO, @PathVariable String energytype,@RequestParam String type,@RequestParam String id ,HttpServletRequest request){
        logger.info("三级页面-用能单位类型-各个成本对比数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        logger.info(id+"type..........");

        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ORG_TYPE,type);
            paramsMap.put(TYPE_ID,id);
            paramsMap.put(ENERGY_TYPE,energytype);
//            paramsMap.put(ORG_TYPE,type);
            Map<String,Object> map =  this.thiredCostTypeService.getUnitCostDetail(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-用能单位类型-各个成本对比数据加载" + e.getMessage());
        }
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     *三级页面-源、网、站、线、户的各个成本类型的趋势图数据加载   点击成本类型
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/getEachStationCostDetail", method = RequestMethod.GET)
    @ResponseBody
    public String getStationCostDetail(ToolVO toolVO,@RequestParam String energytype,HttpServletRequest request)throws Exception{
        logger.info("三级页面-源、网、站、线、户的各个成本类型的趋势图数据加载   点击成本类型");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        logger.info(energytype+"..............");
        Map paramsMap = paramsPackageOrg(toolVO, request);
       try {
            paramsMap.put(ENERGY_TYPE,energytype);
            Map<String,Object> map =  thiredCostTypeService.getEachStationCostDetail(paramsMap);
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
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     *三级页面-成本类型的各自成本趋势图数据加载     点击成本
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/getCostTypeDetail", method = RequestMethod.GET)
    @ResponseBody
    public String getCostTypeDetail(ToolVO toolVO,@RequestParam String energytype, HttpServletRequest request) throws Exception{
        logger.info("三级页面-成本类型的各自成本趋势图数据加载     点击成本");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        logger.info(energytype+"........energyType");
        Map paramsMap = paramsPackageOrg(toolVO, request);
//       try {
        paramsMap.put(ENERGY_TYPE,energytype);
        Map<String,Object> map =  thiredCostTypeService.getCostTypeDetail(paramsMap);
        if (map!= null) {
            jo.put(Constants.FLAG, true);
            jo.put(Constants.OBJECT, map);
        }else{
            jo.put(Constants.FLAG, true);
            jo.put(Constants.OBJECT,  new HashMap<>());
        }

//        } catch (Exception e) {
//           jo.put(Constants.FLAG,false);
//            logger.error("三级页面-成本类型的各自成本趋势图数据加载     点击成本" + e.getMessage());
//        }
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     *三级页面-分公司-换热站成本类型排名趋势图
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/StationRanking", method = RequestMethod.GET)
    @ResponseBody
    public String StationRanking(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) throws Exception {

        logger.info("三级页面-分公司-换热站成本类型排名趋势图");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ENERGY_TYPE,type);
            Map<String,Object> map =this.thiredPageCostService.StationRanking(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
            logger.info(jo.toJSONString());
        }catch (Exception e){
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-换热站成本排名趋势图数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }




    /**
     *三级页面-源网站线户-2016~2017年度成本排名
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/thirdUnitCostDetail", method = RequestMethod.GET)
    @ResponseBody
    public String thirdUnitCostDetail(ToolVO toolVO, @RequestParam String energytype,@RequestParam String type ,HttpServletRequest request) throws Exception{
        logger.info("三级页面-源网站线户-2016~2017年度成本排名   点击源网站线户");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        logger.info(type+"............orgType");
        logger.info(energytype+"........energyType");
        Map paramsMap = paramsPackageOrg(toolVO, request);
       String  starttime=paramsMap.get("startTime").toString();
        String  endtime=paramsMap.get("endTime").toString();
         logger.info(starttime+"开始时间");
        logger.info(endtime+"结束时间");

        try {

            paramsMap.put(ENERGY_TYPE,energytype);
            paramsMap.put(ORG_TYPE,type);

            Map<String,Object> map =this.thiredCostTypeService.ThirdUnitCostDetail(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-源网站线户-2016~2017年度成本排名   点击源网站线户" + e.getMessage());
        }
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }


    /**
     *三级页面-源网站线户-管理（能源，其他，设备，人工）总成本趋势
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/thirdUnitCostTrend", method = RequestMethod.GET)
    @ResponseBody
    public String thirdUnitCostTrend(ToolVO toolVO,@RequestParam String type,@RequestParam String energytype,HttpServletRequest request) throws Exception{
        logger.info("三级页面-源网站线户-管理（能源，其他，设备，人工）总成本趋势");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ORG_TYPE,type);
            paramsMap.put(ENERGY_TYPE,energytype);
            Map<String,Object> map =this.thiredCostTypeService.thirdUnitCostTrend(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        }catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-源网站线户-管理（能源，其他，设备，人工）总成本趋势" + e.getMessage());
        }
        return jo.toJSONString();

    }


    /**
     * 2016~2017年度成本情况对比 (单位: T)thirdUnitSituationCostContrast
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/thirdUnitSituationCostContrast", method = RequestMethod.GET)
    @ResponseBody
    public String thirdUnitSituationCostContrast(ToolVO toolVO, @RequestParam String energytype,@RequestParam String type ,HttpServletRequest request) throws Exception{
        logger.info("2016~2017年度成本情况对比 (单位: T)   点击源网站线户");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        logger.info(type+"............orgType");
        logger.info(energytype+"........energyType");
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {

            paramsMap.put(ENERGY_TYPE,energytype);
            paramsMap.put(ORG_TYPE,type);

            Map<String,Object> map =  thiredCostTypeService.thirdUnitSituationCostContrast(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("2016~2017年度成本情况对比 (单位: 万元)   点击源网站线户" + e.getMessage());
        }
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     *三级页面-点击成本类型-换热站/成本排名
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/thridCostTypeStationRanking", method = RequestMethod.GET)
    @ResponseBody
    public String thridCostTypeStationRanking(ToolVO toolVO,@RequestParam String energytype,HttpServletRequest request)  throws Exception{
        logger.info("三级页面-换热站排名趋势图数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        logger.info(energytype+"fffdfdfdfdfdfdf成本类型。。。。。。。。。。");

        Map paramsMap = paramsPackageOrg(toolVO, request);
        paramsMap.put(ENERGY_TYPE,energytype);
        try {
            Map<String,Object> map=null;
            if( energytype=="5"|| "5"==energytype|| energytype.equals("5") ){
                logger.info("查询换热站能源费排名");
             map =  thiredCostTypeService.thridCostTypeStationEnergyRanking(paramsMap);
            }else{
                logger.info("查询换热站其他成本费排名");
                map =  thiredCostTypeService.thridCostTypeStationRanking(paramsMap);
            }

            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-点击成本类型-换热站成本排名" + e.getMessage());
        }
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }
}
