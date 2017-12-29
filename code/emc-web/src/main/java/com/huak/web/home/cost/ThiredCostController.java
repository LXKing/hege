package com.huak.web.home.cost;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.cost.ThiredPageCostService;
import com.huak.home.type.ToolVO;
import com.huak.org.OrgService;
import com.huak.org.model.Org;
import com.huak.web.home.BaseController;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by pc on 2017/12/7.
 */
@Controller
@RequestMapping(value = "/cost/thired")
public class ThiredCostController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private OrgService orgService;
    @Autowired
    private ThiredPageCostService thiredPageCostService;
    private static  String ORG_TYPE = "orgType";
    private static  String ENERGY_TYPE = "energytype";
    private static  String ORG = "orgId";

    /**
     * 跳转分公司三级能耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/fgs/{id}", method = RequestMethod.GET)
    public String fgsPage(Model model, HttpServletRequest request,@PathVariable("id")String id){
        logger.info("跳转分公司三级成本页面");
        model.addAttribute("id", id);
        Org org  = orgService.selectByPrimaryKey(id);
        model.addAttribute("orgName",org.getOrgName());
        return "third/cost-fgs";
    }

    /**
     *三级页面-分公司汇总成本趋势图
     * sunbinbin @RequestParam String orgId,
     * @return map
     */
    @RequestMapping(value = "/total/costTotalDetail", method = RequestMethod.GET)
    @ResponseBody
    public String costTotalDetail(ToolVO toolVO,@RequestParam String orgId,HttpServletRequest request) throws Exception{
        logger.info("三级页面-分公司-集团成本汇总趋势图数据加载");
        JSONObject jo = new JSONObject();
       logger.info(orgId+"............dffdfdf.......");
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        paramsMap.put("energytype","");
        paramsMap.put(ORG,"78");
       try {
            Map<String,Object> map =  thiredPageCostService.getCostAll(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
            logger.info(jo.toJSONString()+".......");
            System.out.println(jo.toJSONString());
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-分公司-集团能源类型的能耗趋势图数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/CostDetail", method = RequestMethod.GET)
    @ResponseBody
    public String CostDetail(ToolVO toolVO,@RequestParam String orgId,HttpServletRequest request) throws Exception{
        logger.info("三级页面-管理成本，设备成本，其他成本，人工成本的各个趋势图数据加载");
        JSONObject jo = new JSONObject();
//        logger.info(orgId+".....id");
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ORG,orgId);
            Map<String,Object> map =  thiredPageCostService.ThirdSubCostDetailed(paramsMap);
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
     *三级页面-分公司-换热站成本类型排名趋势图
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/StationRanking", method = RequestMethod.GET)
    @ResponseBody
    public String StationRanking(ToolVO toolVO,@RequestParam String type,@RequestParam String orgId,@RequestParam String orgType,HttpServletRequest request) throws Exception {

        logger.info("三级页面-分公司-换热站成本类型排名趋势图");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        paramsMap.put(ENERGY_TYPE,type);
        paramsMap.put(ORG,orgId);
        paramsMap.put("orgType",orgType);
        Map<String,Object> map =this.thiredPageCostService.StationRanking(paramsMap);
        if (map!= null) {
            jo.put(Constants.FLAG, true);
            jo.put(Constants.OBJECT, map);
        }else{
            jo.put(Constants.FLAG, true);
            jo.put(Constants.OBJECT,  new HashMap<>());
        }
       logger.info(jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     *三级页面-各站点成本-同期计划数据表格加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/ThirdlevelCodeTtable", method = RequestMethod.GET)
    @ResponseBody
    public String ThirdlevelCodeTtable(ToolVO toolVO,@RequestParam String orgId,HttpServletRequest request)throws Exception {
        logger.info("三级页面-各站点成本-同期计划数据表格加载");
        JSONObject jo = new JSONObject();
        /*logger.info(orgId+"............");*/
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ORG,orgId);
            Map<String, Object> levelcodeTable = this.thiredPageCostService.levelCodeTable(paramsMap);
            if (levelcodeTable != null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, levelcodeTable);
            } else {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, new HashMap<>());
            }
            return jo.toJSONString();
        } catch (Exception e) {
            jo.put(Constants.FLAG, false);
            logger.error("三级页面-各站点成本-同期计划数据表格加载" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-分公司-能源成本的趋势图  thirdSubCompanyEnergyDetail
     * sunbinbin
     * @return strin
     */
    @RequestMapping(value = "/unit/thirdSubCompanyEnergyDetail", method = RequestMethod.GET)
    @ResponseBody
    public String thirdSubCompanyEnergyDetail(ToolVO toolVO,@RequestParam String orgId,HttpServletRequest request) {
        logger.info("三级页面-分公司-能源成本的趋势图");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ORG,orgId);
            Map<String,Object> map =  thiredPageCostService.thirdSubCompanyEnergyDetail(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-分公司-能源成本的趋势图" + e.getMessage());
        }
        logger.info(jo.toJSONString());
        return jo.toJSONString();
    }
}
