package com.huak.home.cost;

import com.huak.base.dao.DateDao;
import com.huak.common.utils.DateUtils;
import com.huak.home.dao.cost.ThiredCostTypeDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pc on 2017/12/14.
 */
@Service
public class ThiredCostTypeImpl implements ThiredCostTypeService{

    @Resource
    private ThiredCostTypeDao thiredCostTypeDao;
    @Resource
    private DateDao dateDao;

    private static String START_TIME = "startTime";
    private static String END_TIME = "endTime";
    private static String CURRENT_YEAR = "currentYear";
    private static String LAST_YEAR = "lastyear";
    private static String UNIT_NAME1 = "unitname";
    private static String TOTAL_CURRENT_YEAR = "totalcurrentyear";
    private static String TOTAL_LAST_YEAR = "totallastyear";



    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getUnitCostDetail(Map<String, Object> paramsMap) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = this.thiredCostTypeDao.getUnitCostDetail(paramsMap);
        List<Object> tq =  new ArrayList<>();
        List<Object> name =  new ArrayList<>();
        List<Object> tqb =  new ArrayList<>();
        List<Object> plan =  new ArrayList<>();
        List<Object> cur =  new ArrayList<>();
        for (Map da:data){
            tq.add(da.get("qn_num"));
            name.add(da.get(UNIT_NAME1));
            tqb.add(da.get("tqb"));
            plan.add(da.get("jn_plan"));
            cur.add(da.get("jn_num"));
        }
        result.put("tq",tq);
        result.put("name",name);
        result.put("tqb",tqb);
        result.put("plan",plan);
        result.put("cur",cur);
        return result;

    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getEachStationCostDetail(Map<String, Object> params)throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = new ArrayList<>();
        //所有的能源类型
        String[] energyTypes = {"chart1","chart2","chart3","chart4","chart5"};
        String  sDate = (null== params.get(START_TIME)||"".equals( params.get(START_TIME)))?getYearDate(null, Calendar.DATE, -5): params.get(START_TIME).toString().substring(0,10);//如果查询条件的开始时间为空，设置默认的开始时间
        String  eDate = (null==params.get(END_TIME)||"".equals(params.get(END_TIME)))?getYearDate(null,Calendar.DATE, 0):params.get(END_TIME).toString().substring(0,10);//如果查询条件的结束时间为空，设置默认的结束时间
        String lsDate = getYearDate(sDate,Calendar.YEAR, -1);
        String leDate = getYearDate(eDate,Calendar.YEAR, -1);
        //查询时间list
        List<String> clyearList = new ArrayList<String>();//今年日期列表
        List<String> lyearList = new ArrayList<String>();//去年日期列表
        List<String> yearList = new ArrayList<String>();//去年+今年日期列表
        while(!sDate.equals(eDate)){
            clyearList.add(sDate);
            DateUtils.isAddDate(sDate, clyearList);
            sDate = getYearDate(sDate,Calendar.DATE,1);
        }
        clyearList.add(eDate);
        while(!lsDate.equals(leDate)){
            lyearList.add(lsDate);
            DateUtils.isAddDate(lsDate, lyearList);
            lsDate = getYearDate(lsDate,Calendar.DATE,1);
        }
        lyearList.add(leDate);
        yearList.addAll(lyearList);
        yearList.addAll(clyearList);
        //根据查询条件，查询相应的数据
        String costtype=params.get("energytype").toString();

        List<Map<String,Object>> eachStationCostDetailMap=null;
        if(costtype.equals("5")){
            eachStationCostDetailMap=thiredCostTypeDao.getEachStationEnergyCostDetail(params);
        }else{
            eachStationCostDetailMap = thiredCostTypeDao.getEachStationCostDetail(params);
        }
        //组装chartJson格式开始
        boolean lmEmpty = null!=eachStationCostDetailMap && eachStationCostDetailMap.size()>0;
        for(String type:energyTypes){//源、网、站、线、户
            List<String> curList = new ArrayList<String>();//今年某一时间段（查询条件中的时间段）的值
            List<String> lastList = new ArrayList<String>();//去年某一时间段（查询条件中的时间段）的值
            Map<String,Object> my = new HashMap<>();
            Double curYearTotal = 0.0,lastYearTotal = 0.0;//今年和去年能耗数据
            if(lmEmpty){
                //如果查询结果存在，需要设置上面所定义的变量，方便下面封装chartJson
                for(String yd:yearList){
                    boolean isHas = false;
                    for(Map<String,Object> map : eachStationCostDetailMap) {
                        String curyear = map.get("curyear").toString();
                        String yeardate = map.get("time").toString();
                        if (!yeardate.equals(yd)) {
                            continue;
                        }
                        isHas = true;
                        String value = String.valueOf(map.get(type));
                        if (yd.equals(yeardate) && "0".equals(curyear)) {
                            curList.add(value);//添加 今年的用能单位类型数据添加
                            curYearTotal += Double.parseDouble(value);//拿到本期的能耗

                        }
                        if (yd.equals(yeardate) && "1".equals(curyear)) {
                            {
                                lastList.add(value);//添加 去年的用能单位类型数据添加
                                lastYearTotal += Double.parseDouble(value);//拿到去年今天的能耗
                            }
                        }
                    }
                    if (!isHas) {
                        if (lyearList.contains(yd)) {
                            lastList.add("0");
                        } else {
                            curList.add("0");
                        }
                    }
                }
                my.put(TOTAL_CURRENT_YEAR,curYearTotal);
                my.put(TOTAL_LAST_YEAR,lastYearTotal);
                my.put("type",type);
                my.put(CURRENT_YEAR,curList);
                my.put(LAST_YEAR,lastList);
                data.add(my);
            }else{
                for(String yd:yearList){
                    if(lyearList.contains(yd)){
                        lastList.add("0");
                        lastYearTotal += 0;//拿到去年今天的能耗
                    }else{
                        curList.add("0");
                        curYearTotal += 0;//拿到今天的能耗
                    }
                }
                my.put(TOTAL_CURRENT_YEAR,curYearTotal);
                my.put(TOTAL_LAST_YEAR,lastYearTotal);
                my.put("type",type);
                my.put(CURRENT_YEAR,curList);
                my.put(LAST_YEAR,lastList);
                data.add(my);
            }
        }
        //组装chartJson格式结束
        result.put("date",clyearList);
        result.put("data",data);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getCostTypeDetail(Map<String, Object> params)throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = new ArrayList<>();
        //所有的能源类型
        String  sDate = (null== params.get(START_TIME)||"".equals( params.get(START_TIME)))?getYearDate(null, Calendar.DATE, -5): params.get(START_TIME).toString().substring(0,10);//如果查询条件的开始时间为空，设置默认的开始时间
        String  eDate = (null==params.get(END_TIME)||"".equals(params.get(END_TIME)))?getYearDate(null,Calendar.DATE, 0):params.get(END_TIME).toString().substring(0,10);//如果查询条件的结束时间为空，设置默认的结束时间
        String lsDate = getYearDate(sDate,Calendar.YEAR, -1);
        String leDate = getYearDate(eDate,Calendar.YEAR, -1);
        //查询时间list
        List<String> clyearList = new ArrayList<String>();//今年日期列表
        List<String> lyearList = new ArrayList<String>();//去年日期列表
        List<String> yearList = new ArrayList<String>();//去年+今年日期列表
        while(!sDate.equals(eDate)){
            clyearList.add(sDate);
            DateUtils.isAddDate(sDate, clyearList);
            sDate = getYearDate(sDate,Calendar.DATE,1);
        }
        while(!lsDate.equals(leDate)){
            lyearList.add(lsDate);
            DateUtils.isAddDate(lsDate, lyearList);
            lsDate = getYearDate(lsDate,Calendar.DATE,1);
        }
        clyearList.add(eDate);
        lyearList.add(leDate);
        yearList.addAll(lyearList);
        yearList.addAll(clyearList);
        //根据查询条件，查询相应的数据
//        List<Map<String,Object>> listMap =this.costPageDao.getCostTypeDetail(params);
        List<Map<String,Object>> listMap=null;
        String costtype=params.get("energytype").toString();
        if(costtype.equals("5")){
            listMap = this.thiredCostTypeDao.getCostTypeEnergyDetail(params);//热源能源类型的排名
        }else{
            listMap = this.thiredCostTypeDao.getCostTypeDetail(params);//热源能源类型的排名
        }
        //组装chartJson格式开始
        boolean lmEmpty = null!=listMap && listMap.size()>0;
        List<String> curList = new ArrayList<String>();//今年某一时间段（查询条件中的时间段）的值
        List<String> lastList = new ArrayList<String>();//去年某一时间段（查询条件中的时间段）的值
        List<String> bm_curList = new ArrayList<String>();//今年某一时间段（查询条件中的时间段）的值
        List<String> bm_lastList = new ArrayList<String>();//去年某一时间段（查询条件中的时间段）的值
        Map<String,Object> my = new HashMap<>();
        Double curYearTotal = 0.0,lastYearTotal = 0.0;//今年和去年能耗数据
        Double curYearBm = 0.0,lastYearBm = 0.0;//今年和去年能耗数据
        if(lmEmpty){
            //如果查询结果存在，需要设置上面所定义的变量，方便下面封装chartJson
            for(String yd:yearList){
                boolean isHas = false;
                for(Map<String,Object> map : listMap) {
                    String curyear = map.get("curyear").toString();
                    String yeardate = map.get("time").toString();
                    if (!yeardate.equals(yd)) {
                        continue;
                    }
                    isHas = true;
//                    String value = String.valueOf(map.get("num"));
                    String value1 = String.valueOf(map.get("bm_num"));
                    if (yd.equals(yeardate) && "0".equals(curyear)) {
//                        curList.add(value);//添加 今年的用能单位类型数据添加
                        bm_curList.add(value1);
                       curYearTotal += Double.parseDouble(value1);//拿到本期的能耗
                        curYearBm += Double.parseDouble(value1);//拿到本期的标煤
                    }
                    if (yd.equals(yeardate) && "1".equals(curyear)) {

//                        lastList.add(value);//添加 去年的用能单位类型数据添加
                        bm_lastList.add(value1);//添加 去年的用能单位类型数据添加
                        lastYearTotal += Double.parseDouble(value1);//拿到去年今天的能耗
                        lastYearBm +=  Double.parseDouble(value1);
                    }

                }
                if (!isHas) {
                    if (lyearList.contains(yd)) {
                        lastList.add("0");
                        bm_lastList.add("0");
                    } else {
                        curList.add("0");
                        bm_curList.add("0");
                    }
                }
            }
            my.put(TOTAL_CURRENT_YEAR,curYearTotal);
            my.put(TOTAL_LAST_YEAR,lastYearTotal);
            my.put("bm_current",curYearBm);
            my.put("bm_last",lastYearBm);
            my.put(CURRENT_YEAR,curList);
            my.put(LAST_YEAR,lastList);
            my.put("bm_cdata",bm_curList);
            my.put("bm_ldata",bm_lastList);
            data.add(my);
        }else{
            for(String yd:yearList){
                if(lyearList.contains(yd)){
                    lastList.add("0");
                    bm_lastList.add("0");
                    lastYearTotal += 0;//拿到去年今天的能耗
                    lastYearBm += 0;
                }else{
                    bm_curList.add("0");
                    curList.add("0");
                    curYearTotal += 0;//拿到今天的能耗
                    curYearBm += 0;
                }
            }
            my.put(TOTAL_CURRENT_YEAR,curYearTotal);
            my.put(TOTAL_LAST_YEAR,lastYearTotal);
            my.put("bm_current",curYearBm);
            my.put("bm_last",lastYearBm);
            my.put(CURRENT_YEAR,curList);
            my.put(LAST_YEAR,lastList);
            my.put("bm_cdata",bm_curList);
            my.put("bm_ldata",bm_lastList);
            data.add(my);
        }

        //组装chartJson格式结束
        result.put("date",clyearList);
        result.put("data",data);
        return result;


    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> thirdUnitCostTrend(Map<String, Object> paramsMap) throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = new ArrayList<>();
        //所有的能源类型

        String  sDate = paramsMap.get(START_TIME).toString().substring(0,10);//如果查询条件的开始时间为空，设置默认的开始时间
        String  eDate = paramsMap.get(END_TIME).toString().substring(0,10);//如果查询条件的结束时间为空，设置默认的结束时间
        String lsDate = getYearDate(sDate,Calendar.YEAR, -1);
        String leDate = getYearDate(eDate,Calendar.YEAR, -1);
        //查询时间list
        List<String> clyearList = new ArrayList<String>();//今年日期列表
        List<String> lyearList = new ArrayList<String>();//去年日期列表
        List<String> yearList = new ArrayList<String>();//去年+今年日期列表

        while(!sDate.equals(eDate)){
            clyearList.add(sDate);
            DateUtils.isAddDate(sDate, clyearList);
            sDate = getYearDate(sDate,Calendar.DATE,1);
        }
        while(!lsDate.equals(leDate)){
            lyearList.add(lsDate);
            DateUtils.isAddDate(lsDate, lyearList);
            lsDate = getYearDate(lsDate,Calendar.DATE,1);
        }
        clyearList.add(eDate);
        lyearList.add(leDate);
        yearList.addAll(lyearList);
        yearList.addAll(clyearList);
        //根据查询条件，查询相应的数据
        List<Map<String,Object>> listMap=null;
        String costtype=paramsMap.get("energytype").toString();
        if(costtype.equals("5")){
            listMap = this.thiredCostTypeDao.thirdUnitCostEnergyTrend(paramsMap);//热源能源类型的排名
        }else{
            listMap = thiredCostTypeDao.thirdUnitCostTrend(paramsMap);;//热源能源类型的排名
        }

        //组装chartJson格式开始
        boolean lmEmpty = null!=listMap && listMap.size()>0;
        List<String> curList = new ArrayList<String>();//今年某一时间段（查询条件中的时间段）的值
        List<String> lastList = new ArrayList<String>();//去年某一时间段（查询条件中的时间段）的值
        Map<String,Object> my = new HashMap<>();
        Double curYearTotal = 0.0;//今年和去年能耗数据
        if(lmEmpty){
            //如果查询结果存在，需要设置上面所定义的变量，方便下面封装chartJson
            for(String yd:clyearList){
                boolean isHas = false;
                for(Map<String,Object> map : listMap) {
                    String yeardate = map.get("time").toString();
                    if (!yeardate.equals(yd)) {
                        continue;
                    }
                    isHas = true;
                    String value = String.valueOf(map.get("num"));
                    if (yd.equals(yeardate)) {
                        curList.add(value);//添加 今年的用能单位类型数据添加
                        curYearTotal += Double.parseDouble(value);//拿到本期的能耗
                    }
                }
                if (!isHas) {
                    if (lyearList.contains(yd)) {
                        lastList.add("0");
                    } else {
                        curList.add("0");
                    }
                }
            }
            my.put(TOTAL_CURRENT_YEAR,curYearTotal);
            my.put(CURRENT_YEAR,curList);
            data.add(my);
        }else{
            for(String yd:clyearList){
                curList.add("0");
                curYearTotal += 0;//拿到今天的能耗
            }
            my.put(TOTAL_CURRENT_YEAR,curYearTotal);
            my.put(CURRENT_YEAR,curList);
            data.add(my);
        }

        //组装chartJson格式结束
        result.put("date",clyearList);
        result.put("data",data);
        return result;

    }


    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> ThirdUnitCostDetail(Map<String, Object> paramsMap) throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> listMap=null;
        String costtype=paramsMap.get("energytype").toString();
        if(costtype.equals("5")){
            listMap = this.thiredCostTypeDao.ThirdUnitCostEnertyDetail(paramsMap);//该站点下的能源成本的排名
        }else{
            listMap = this.thiredCostTypeDao.getUnitCostDetail(paramsMap);//该站点下的其他成本的排名
        }

        List<Object> name =  new ArrayList<>();
        List<Object> cur =  new ArrayList<>();
        for (Map da:listMap){
            name.add(da.get(UNIT_NAME1));
            cur.add(da.get("jn_num"));
        }
        result.put("name",name);
        result.put("cur",cur);
        return result;

    }
    /**
     * 返回想要的日期
     * 例如：getYearDate（2017-01-01，Calendar.YEAR，-1），返回值为 2016-01-01
     *     getYearDate（2017-01-11，Calendar.DATE，-1），返回值为 2017-01-10
     * @param curDate 元数据，在curDate的基础上获取想要的具体日期str
     * @param type 类型，Calendar.YEAR,Calendar.MONTH...
     * @param num 操作数
     * @return
     */
    private String getYearDate(String curDate,int type,int num) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        curDate = (null == curDate||"".equals(curDate))?dateDao.getDate():curDate;
        Date date = sdf.parse(curDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, num);
        date = calendar.getTime();
        return sdf.format(date);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> thirdUnitSituationCostContrast(Map<String, Object> paramsMap) throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> listMap=null;
        String costtype=paramsMap.get("energytype").toString();
        if(costtype.equals("5")){
            listMap = this.thiredCostTypeDao.thirdUnitSituationCostEnergyContrast(paramsMap);//能源成本的排名
        }else{
            listMap = thiredCostTypeDao.thirdUnitSituationCostContrast(paramsMap);;//管理成本，其他成本，设备成本，人工成本的排名
        }
        List<Object> tq =  new ArrayList<>();
        List<Object> name =  new ArrayList<>();
        List<Object> tqb =  new ArrayList<>();
        List<Object> plan =  new ArrayList<>();
        List<Object> cur =  new ArrayList<>();
        for (Map da:listMap){
            tq.add(da.get("qn_num"));
            name.add(da.get(UNIT_NAME1));
            tqb.add(da.get("tqb"));
            plan.add(da.get("jn_plan"));
            cur.add(da.get("jn_num"));
        }
        result.put("tq",tq);
        result.put("name",name);
        result.put("tqb",tqb);
        result.put("plan",plan);
        result.put("cur",cur);
        return result;
    }


    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> thridCostTypeStationRanking(Map<String, Object> paramsMap) throws Exception {
        Map<String,Object> data = new HashMap<>();
        paramsMap.put("orgType","1");
        List<Map<String,Object>> heats = thiredCostTypeDao.StationRanking(paramsMap);//热源能源类型的排名
        paramsMap.put("orgType","3");
        List<Map<String,Object>> stations = thiredCostTypeDao.StationRanking(paramsMap);//热力站能源类型的排名
        List<String> heatNames = new ArrayList<>();
        List<String> heatnums = new ArrayList<>();
        List<String> stationNames = new ArrayList<>();
        List<String> stationNums = new ArrayList<>();
        for(Map<String,Object> heat:heats){
            if(null != heat){
                heatNames.add(heat.get(UNIT_NAME1).toString());
                heatnums.add(heat.get("dosage").toString());
            }

        }
        for(Map<String,Object> station:stations){
            if(null != station){
                stationNames.add(station.get(UNIT_NAME1).toString());
                stationNums.add(station.get("dosage").toString());
            }
        }
        data.put("heatnames",heatNames);
        data.put("heatnum",heatnums);
        data.put("stationnames",stationNames);
        data.put("stationnums",stationNums);
        return data;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> thridCostTypeStationEnergyRanking(Map<String, Object> paramsMap) throws Exception {
        Map<String,Object> data = new HashMap<>();
        paramsMap.put("orgType","1");
        List<Map<String,Object>> heats = thiredCostTypeDao.StationRankingEnergy(paramsMap);//热源能源类型的排名
        paramsMap.put("orgType","3");
        List<Map<String,Object>> stations = thiredCostTypeDao.StationRankingEnergy(paramsMap);//热力站能源类型的排名
        List<String> heatNames = new ArrayList<>();
        List<String> heatnums = new ArrayList<>();
        List<String> stationNames = new ArrayList<>();
        List<String> stationNums = new ArrayList<>();
        for(Map<String,Object> heat:heats){
            if(null != heat){
                heatNames.add(heat.get(UNIT_NAME1).toString());
                heatnums.add(heat.get("dosage").toString());
            }

        }
        for(Map<String,Object> station:stations){
            if(null != station){
                stationNames.add(station.get(UNIT_NAME1).toString());
                stationNums.add(station.get("dosage").toString());
            }
        }
        data.put("heatnames",heatNames);
        data.put("heatnum",heatnums);
        data.put("stationnames",stationNames);
        data.put("stationnums",stationNums);
        return data;

    }
}
