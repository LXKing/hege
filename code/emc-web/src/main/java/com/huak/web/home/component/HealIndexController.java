package com.huak.web.home.component;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.User;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.utils.DoubleUtils;
import com.huak.health.model.HealthScoreRecord;
import com.huak.health.type.PercentType;
import com.huak.home.SearchService;
import com.huak.home.component.ComponentService;
import com.huak.home.component.HealthScoreRecordService;
import com.huak.home.type.ToolVO;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/9/26.
 */
@Controller
@RequestMapping("/healthcheck")
public class HealIndexController   extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String SERIOUS = "serious";
    private final static String MODERATE = "moderate";
    private final static String MILD = "mild";
    private final static String LEVEL1 = "level1";
    private final static String LEVEL2 = "level2";
    private final static String LEVEL3 = "level3";
    private final static String LEVEL4 = "level4";
    private final static String MIN = "min";
    private final static String MAX = "max";
    private final static String CSS = "css";
    @Resource
    private ComponentService componentService;

    @Resource
    private HealthScoreRecordService healthService;

    @Resource
    private SearchService searchService;
    /**
     * 组件-健康指数检测
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String healthcheck(ToolVO toolVO,HttpServletRequest request) {
        logger.info("组件-健康指数检测加载");
        JSONObject jo = new JSONObject();
        String startTime=null;
        String endTime=null;
        jo.put(Constants.FLAG, false);

        try {
             /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            Map<String,Object> jjdata = new HashMap<>();
            Map<String,Object> datemp  = componentService.getAlarms(params);

            if((boolean)datemp.get("flag")){
                Map<String,Object> temp = (Map<String, Object>) datemp.get("data");

                int jjSerious = null == temp.get(SERIOUS) ?0:Integer.valueOf(temp.get(SERIOUS).toString());
                int jjModerate = null == temp.get(MODERATE) ?0:Integer.valueOf(temp.get(MODERATE).toString());
                int jjMild = null == temp.get(MILD) ?0:Integer.valueOf(temp.get(MILD).toString());
                jjdata.put(SERIOUS,jjSerious);
                jjdata.put(MODERATE,jjModerate);
                jjdata.put(MILD,jjMild);
                if(jjSerious+jjModerate+jjMild>0){
                    jjdata.put(CSS,"m");
                }else{
                    jjdata.put(CSS,"a");
                }

            }else{
                jjdata.put(SERIOUS,0);
                jjdata.put(MODERATE,0);
                jjdata.put(MILD,0);
                jjdata.put(CSS,"a");
            }
            Map<String,Object> data = new HashMap<>();
            Map<String,Object> workData = componentService.getWorkAlarms(params);
            int workLevel1 = null == workData.get(LEVEL1) ?0:Integer.valueOf(workData.get(LEVEL1).toString());
            int workLevel2 = null == workData.get(LEVEL2) ?0:Integer.valueOf(workData.get(LEVEL2).toString());
            int workLevel3 = null == workData.get(LEVEL3) ?0:Integer.valueOf(workData.get(LEVEL3).toString());
            int workLevel4 = null == workData.get(LEVEL4) ?0:Integer.valueOf(workData.get(LEVEL4).toString());
            workData.put(LEVEL1,workLevel1);
            workData.put(LEVEL2,workLevel2);
            workData.put(LEVEL3,workLevel3);
            workData.put(LEVEL4,workLevel4);
            if(workLevel1+workLevel2+workLevel3+workLevel4>0){
                workData.put(CSS,"m");
            }else{
                workData.put(CSS,"a");
            }

            Map<String,Object> fwdata = new HashMap<>();
            fwdata.put(SERIOUS,0);
            fwdata.put(MODERATE,0);
            fwdata.put(MILD,0);
            fwdata.put(CSS,"a");

            Map<String,Object> tempData = componentService.getTempAlarms(params);
            int tempMin = null == tempData.get(MIN) ?0:Integer.valueOf(tempData.get(MIN).toString());
            int tempMax = null == tempData.get(MAX) ?0:Integer.valueOf(tempData.get(MAX).toString());
            tempData.put(MIN,tempMin);
            tempData.put(MAX,tempMax);
            if(tempMax+tempMin>0){
                tempData.put(CSS,"m");
            }else{
                tempData.put(CSS,"a");
            }
            data.put("gkyx",workData);
            data.put("jjyx",jjdata);
            data.put("fwqk",fwdata);
            data.put("zygl",tempData);
            Double score = calculatedFraction(workData,jjdata,fwdata,tempData);
            data.put("score",score);
            if (data!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, data);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组件-健康指数检测加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    @RequestMapping(value = "/list/second", method = RequestMethod.POST)
    @ResponseBody
    public String second(ToolVO toolVO,HttpServletRequest request,@CookieValue(name="toolEndDate")String endTime,
                         @CookieValue(name="toolStartDate")String toolStartDate) {
        logger.info("健康指数检测二级页面分数计算");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        if(toolVO.getToolEndDate()==null&&toolVO.getToolStartDate()==null&&toolVO.getToolOrgId()==null){
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.SESSION_KEY);
            toolVO.setToolOrgId(user.getOrgId());
            toolVO.setToolEndDate(endTime);
            toolVO.setToolStartDate(toolStartDate);
        }
        try {
             /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            Map<String,Object> jjdata = new HashMap<>();
            Map<String,Object> datemp  = componentService.getAlarms(params);

            if((boolean)datemp.get("flag")){
                Map<String,Object> temp = (Map<String, Object>) datemp.get("data");

                int jjSerious = null == temp.get(SERIOUS) ?0:Integer.valueOf(temp.get(SERIOUS).toString());
                int jjModerate = null == temp.get(MODERATE) ?0:Integer.valueOf(temp.get(MODERATE).toString());
                int jjMild = null == temp.get(MILD) ?0:Integer.valueOf(temp.get(MILD).toString());
                jjdata.put(SERIOUS,jjSerious);
                jjdata.put(MODERATE,jjModerate);
                jjdata.put(MILD,jjMild);
                if(jjSerious+jjModerate+jjMild>0){
                    jjdata.put(CSS,"m");
                }else{
                    jjdata.put(CSS,"a");
                }

            }else{
                jjdata.put(SERIOUS,0);
                jjdata.put(MODERATE,0);
                jjdata.put(MILD,0);
                jjdata.put(CSS,"a");
            }
            Map<String,Object> data = new HashMap<>();
            Map<String,Object> workData = componentService.getWorkAlarms(params);
            int workLevel1 = null == workData.get(LEVEL1) ?0:Integer.valueOf(workData.get(LEVEL1).toString());
            int workLevel2 = null == workData.get(LEVEL2) ?0:Integer.valueOf(workData.get(LEVEL2).toString());
            int workLevel3 = null == workData.get(LEVEL3) ?0:Integer.valueOf(workData.get(LEVEL3).toString());
            int workLevel4 = null == workData.get(LEVEL4) ?0:Integer.valueOf(workData.get(LEVEL4).toString());
            workData.put(LEVEL1,workLevel1);
            workData.put(LEVEL2,workLevel2);
            workData.put(LEVEL3,workLevel3);
            workData.put(LEVEL4,workLevel4);
            if(workLevel1+workLevel2+workLevel3+workLevel4>0){
                workData.put(CSS,"m");
            }else{
                workData.put(CSS,"a");
            }
            Map<String,Object> fwdata = new HashMap<>();
            fwdata.put(SERIOUS,0);
            fwdata.put(MODERATE,0);
             Map<String,Object> tempData = componentService.getTempAlarms(params);
            int tempMin = null == tempData.get(MIN) ?0:Integer.valueOf(tempData.get(MIN).toString());
            int tempMax = null == tempData.get(MAX) ?0:Integer.valueOf(tempData.get(MAX).toString());
            tempData.put(MIN,tempMin);
            tempData.put(MAX,tempMax);
            if(tempMax+tempMin>0){
                tempData.put(CSS,"m");
            }else{
                tempData.put(CSS,"a");
            }
            data.put("gkyx",workData);
            data.put("jjyx",jjdata);
            data.put("fwqk",fwdata);
            data.put("zygl",tempData);
            Double score = calculatedFraction(workData,jjdata,fwdata,tempData);
            data.put("score",score);
            data.put("date",getDateTime());

            HealthScoreRecord health = new HealthScoreRecord();
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute(Constants.SESSION_KEY);
            Org org = (Org) session.getAttribute(Constants.SESSION_ORG_KEY);
            health.setId(UUIDGenerator.getUUID());
            health.setUserid(user.getId());
            health.setOrgId(org.getId().toString());
            health.setScore(score);
            health.setCreateTime(getDateTime());
            healthService.insertSelective(health);
            if (data!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, data);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("健康指数检测二级页面分数计算" + e.getMessage());
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/season", method = RequestMethod.POST)
    @ResponseBody
    public String season(HttpServletRequest request, HttpServletResponse response) {
        logger.info("检验采暖季");
        JSONObject jo = new JSONObject();
        Map<String, Object> params = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        Org org = (Org) session.getAttribute(Constants.SESSION_ORG_KEY);
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        Calendar calendar = Calendar.getInstance();
//            startTime = calendar.getWeekYear()-1+"-11-15 00:00:00";
//            endTime = calendar.getWeekYear()+"-03-15 23:59:59";
        JSONObject season = searchService.getSeason(company.getId());
        if(season==null){
            jo.put(Constants.FLAG,false);
            jo.put(Constants.MSG,"未设置采暖季,请先设置本采暖季后进行检测");
            return jo.toJSONString();
        }else {
            jo.put(Constants.FLAG,true);
            jo.put(Constants.MSG,"采暖季已经设置");
        }
        return  jo.toJSONString();
    }
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    @ResponseBody
    public String score(HttpServletRequest request, HttpServletResponse response) {
        logger.info("健康指数二级页面-获取分数");
        JSONObject jo = new JSONObject();
        Map<String, Object> params = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constants.SESSION_KEY);
        Org org = (Org) session.getAttribute(Constants.SESSION_ORG_KEY);
        params.put("userid",user.getId());
        params.put("orgId",org.getId());
        HealthScoreRecord  h= healthService.getRecordById(params);
        if(h==null){
            jo.put(Constants.FLAG,false);
        }else {
            jo.put(Constants.FLAG,true);
            jo.put("score",h.getScore());
            jo.put("time",h.getCreateTime().substring(0,h.getCreateTime().length()-2));
        }

        return  jo.toJSONString();
    }

    /**
     * 健康指数分数
     * 初始化分数100分
     * 四个模块分为1/4*100
     * 工况运行 一级4/10 二级3/10 三级2/10 四级1/10
     * 经济运行 行标3/6 地标2/6 企标1/6
     * 服务情况 1/1
     * 室温报警 低温1/2 高温1/2
     * @param workData 工况运行
     * @param jjdata 经济运行
     * @param fwdata 服务情况
     * @param tempData 室温报警
     * @return 分数
     */
    public Double calculatedFraction(Map<String,Object> workData,Map<String,Object> jjdata,Map<String,Object> fwdata,Map<String,Object> tempData){
        Double score = 100d;
        Double work = 0d;
        int workLevel1 = null == workData.get(LEVEL1) ?0:Integer.valueOf(workData.get(LEVEL1).toString());
        int workLevel2 = null == workData.get(LEVEL2) ?0:Integer.valueOf(workData.get(LEVEL2).toString());
        int workLevel3 = null == workData.get(LEVEL3) ?0:Integer.valueOf(workData.get(LEVEL3).toString());
        int workLevel4 = null == workData.get(LEVEL4) ?0:Integer.valueOf(workData.get(LEVEL4).toString());
        if(workLevel1>0){
            work = DoubleUtils.add(work, PercentType.WORK_ONE.getMolecule());
        }
        if(workLevel2>0){
            work = DoubleUtils.add(work, PercentType.WORK_TWO.getMolecule());
        }
        if(workLevel3>0){
            work = DoubleUtils.add(work, PercentType.WORK_THREE.getMolecule());
        }
        if(workLevel4>0){
            work = DoubleUtils.add(work, PercentType.WORK_FOUR.getMolecule());
        }
        work = DoubleUtils.div(work,PercentType.WORK_ONE.getDenominator());

        Double temp = 0d;
        int tempMin = null == tempData.get(MIN) ?0:Integer.valueOf(tempData.get(MIN).toString());
        int tempMax = null == tempData.get(MAX) ?0:Integer.valueOf(tempData.get(MAX).toString());
        if(tempMin>0){
            temp = DoubleUtils.add(temp, PercentType.TEMP_MIN.getMolecule());
        }
        if(tempMax>0){
            temp = DoubleUtils.add(temp, PercentType.TEMP_MAX.getMolecule());
        }
        temp = DoubleUtils.div(temp,PercentType.TEMP_MIN.getDenominator());

        Double jj = 0d;
        int jjSerious = null == jjdata.get(SERIOUS) ?0:Integer.valueOf(jjdata.get(SERIOUS).toString());
        int jjModerate = null == jjdata.get(MODERATE) ?0:Integer.valueOf(jjdata.get(MODERATE).toString());
        int jjMild = null == jjdata.get(MILD) ?0:Integer.valueOf(jjdata.get(MILD).toString());
        if(jjSerious>0){
            jj = DoubleUtils.add(jj, PercentType.JJ_ROWER.getMolecule());
        }
        if(jjModerate>0){
            jj = DoubleUtils.add(jj, PercentType.JJ_LANDMARK.getMolecule());
        }
        if(jjMild>0){
            jj = DoubleUtils.add(jj, PercentType.JJ_ENTER.getMolecule());
        }
        jj = DoubleUtils.div(jj,PercentType.JJ_ROWER.getDenominator());

        work = DoubleUtils.div(DoubleUtils.mul(work,PercentType.FOUR_ONE.getMolecule()),PercentType.FOUR_ONE.getDenominator());
        temp = DoubleUtils.div(DoubleUtils.mul(temp,PercentType.FOUR_ONE.getMolecule()),PercentType.FOUR_ONE.getDenominator());
        jj = DoubleUtils.div(DoubleUtils.mul(jj,PercentType.FOUR_ONE.getMolecule()),PercentType.FOUR_ONE.getDenominator());
        Double service = 0d;

        Double reduce = DoubleUtils.add(work,DoubleUtils.add(temp,DoubleUtils.add(jj,service)));
        score = DoubleUtils.mul(score,DoubleUtils.sub(1d,reduce));
        score = DoubleUtils.round(score,0);
        return score>0?score:0d;
    }

    private String getDateTime(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改格式
        return dateFormat.format(now);
    }
}
