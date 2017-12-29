package com.huak.home.thiredpage;

import com.huak.base.dao.DateDao;
import com.huak.common.utils.DateUtils;
import com.huak.home.dao.thiredpage.ThiredpageEnergyDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by MR-BIN on 2017/8/2.
 */
@Service
public class ThiredpageEnergyImpl implements ThiredpageEnergyService{
    @Resource
    private DateDao dateDao;
    @Resource
    private ThiredpageEnergyDao thiredpageEnergyDao;

    private static String START_TIME = "startTime";
    private static String END_TIME = "endTime";
    private static String CURRENT_YEAR = "currentYear";
    private static String LAST_YEAR = "lastyear";
    private static String UNIT_TYPE = "unitType";
    private static String UNIT_NAME = "unitName";
    private static String UNIT_NAME1 = "unitname";
    private static String[] UNITTYPE = {"1","2","3","4","5","6"};
    private static String TOTAL_CURRENT_YEAR = "totalcurrentyear";
    private static String TOTAL_LAST_YEAR = "totallastyear";
    private static  List<Object> TYPE_NAME = new ArrayList<Object>(){
        {
            add("水(T)");
            add("电(kWh)");
            add("气(m³)");
            add("热(GJ)");
            add("煤(T)");
            add("油(L)");
        }
    };



    /**
     *三级页面-集团总能源类型的能耗趋势图
     * sunbinbin
     * @return map
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getDatasAll(Map<String, Object> params) throws Exception {
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
        List<Map<String,Object>> listMap = thiredpageEnergyDao.getDatasAll(params);
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
                        String value = String.valueOf(map.get("num"));
                        String value1 = String.valueOf(map.get("bm_num"));
                        if (yd.equals(yeardate) && "0".equals(curyear)) {
                            curList.add(value);//添加 今年的用能单位类型数据添加
                            bm_curList.add(value1);
                            curYearTotal += Double.parseDouble(value);//拿到本期的能耗
                            curYearBm += Double.parseDouble(value1);//拿到本期的标煤
                        }
                        if (yd.equals(yeardate) && "1".equals(curyear)) {

                            lastList.add(value);//添加 去年的用能单位类型数据添加
                            bm_lastList.add(value1);//添加 去年的用能单位类型数据添加
                            lastYearTotal += Double.parseDouble(value);//拿到去年今天的能耗
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
        System.out.println(result+"........result");
        return result;
    }

    /**
     *三级页面-表格数据加载
     * sunbinbin
     * @return map
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getTable(Map<String, Object> params) throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = new ArrayList<>();
        //所有的用能单位类型

        String  sDate = (null== params.get(START_TIME)||"".equals( params.get(START_TIME)))?getYearDate(null, Calendar.DATE, -5): params.get(START_TIME).toString().substring(0,10);//如果查询条件的开始时间为空，设置默认的开始时间
        String  eDate = getYearDate(sDate,Calendar.DATE,6);
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
        params.put(CURRENT_YEAR,clyearList);
        params.put("lastYear",lyearList);
        params.put(END_TIME,eDate+" 23:59:59");
        params.put("endTimeTq",leDate+" 23:59:59");
        //根据查询条件，查询相应的数据
        List<Map<String,Object>> listMap = thiredpageEnergyDao.getTables(params);
        List<Map<String,Object>> totalMap = thiredpageEnergyDao.getTotal(params);

        //数据封装
        if(null !=totalMap && !totalMap.isEmpty()) {
            for (Map data1 : totalMap) {
                if ("1".equals(data1.get(UNIT_TYPE))) {
                    result.put("feedTotal", data1);
                }
                if ("2".equals(data1.get(UNIT_TYPE))) {
                    result.put("netTotal", data1);
                }
                if ("3".equals(data1.get(UNIT_TYPE))) {
                    result.put("stationTotal", data1);
                }
                if ("4".equals(data1.get(UNIT_TYPE))) {
                    result.put("lineTotal", data1);
                }
                if ("5".equals(data1.get(UNIT_TYPE))) {
                    result.put("roomTotal", data1);
                }
            }
        }else{
            for (String type :UNITTYPE) {
                Map<String, Object> temap = new HashMap<>();

                for (int k = 0; k < clyearList.size(); k++) {
                    String key1 = "c" + k;
                    String key2 = "l" + k;
                    String key3 = "p" + k;
                    temap.put(key1,0);
                    temap.put(key2,0);
                    temap.put(key3,0);
                }
                if(type.equals("1")){
                    temap.put(UNIT_NAME,"源");
                    temap.put(UNIT_TYPE,type);
                    result.put("feedTotal", temap);
                }
                if(type.equals("2")){
                    temap.put(UNIT_NAME,"网");
                    temap.put(UNIT_TYPE,type);
                    result.put("netTotal", temap);
                }
                if(type.equals("3")){
                    temap.put(UNIT_NAME,"站");
                    temap.put(UNIT_TYPE,type);
                    result.put("stationTotal", temap);
                }
                if(type.equals("4")){
                    temap.put(UNIT_NAME,"线");
                    temap.put(UNIT_TYPE,type);
                    result.put("lineTotal", temap);
                }
                if(type.equals("5")){
                    temap.put(UNIT_NAME,"户");
                    temap.put(UNIT_TYPE,type);
                    result.put("roomTotal", temap);
                }

            }

        }
        result.put("list",listMap);
        result.put("dates",clyearList);

        return result;
    }

    /**
     *三级页面-源、网、站、线、户的各个能源类型的能耗趋势图
     * sunbinbin
     * @return map
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getDatas(Map<String, Object> params) throws Exception {
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
        List<Map<String,Object>> listMap = thiredpageEnergyDao.getDatas(params);
        //组装chartJson格式开始
        boolean lmEmpty = null!=listMap && listMap.size()>0;
        for(String type:energyTypes){//源、网、站、线、户
            List<String> curList = new ArrayList<String>();//今年某一时间段（查询条件中的时间段）的值
            List<String> lastList = new ArrayList<String>();//去年某一时间段（查询条件中的时间段）的值
            Map<String,Object> my = new HashMap<>();
            Double curYearTotal = 0.0,lastYearTotal = 0.0;//今年和去年能耗数据
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

    /**
     *三级页面-换热站排名趋势图
     * sunbinbin
     * @return string
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getassessment(Map<String, Object> paramsMap) throws Exception{
        Map<String,Object> data = new HashMap<>();
        paramsMap.put("orgType","1");
        List<Map<String,Object>> heats = thiredpageEnergyDao.selectassessment(paramsMap);//热源能源类型的排名
        paramsMap.put("orgType","3");
        List<Map<String,Object>> stations = thiredpageEnergyDao.selectassessment(paramsMap);//热力站能源类型的排名
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

    /**
     *三级页面-用能单位类型-个能源能耗趋势图数据加载
     * sunbinbin
     * @return Map
     */
    @Override
    public Map<String, Object> getUnitEnergyDetail(Map paramsMap) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = thiredpageEnergyDao.getUnitsEnergyDetail(paramsMap);
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

    /**
     *三级页面-用能单位类型-个能源能耗趋势图数据加载
     * sunbinbin
     * @return Map
     */
    @Override
    public Map<String, Object> getUnitAssessments(Map paramsMap) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = thiredpageEnergyDao.getUnitsAssessment(paramsMap);
        List<Object> name =  new ArrayList<>();
        List<Object> cur =  new ArrayList<>();
        for (Map da:data){
            name.add(da.get(UNIT_NAME1));
            cur.add(da.get("jn_num"));
        }
        result.put("name",name);
        result.put("cur",cur);
        return result;
    }

    /**
     *三级页面-用能单位类型-各个能源类型用量趋势图加载
     * sunbinbin
     * @return Map
     */
    @Override
    public Map<String, Object> getUnitAllAssessment(Map params) throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = new ArrayList<>();
        //所有的能源类型

        String  sDate = params.get(START_TIME).toString().substring(0,10);//如果查询条件的开始时间为空，设置默认的开始时间
        String  eDate = params.get(END_TIME).toString().substring(0,10);//如果查询条件的结束时间为空，设置默认的结束时间
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
        List<Map<String,Object>> listMap = thiredpageEnergyDao.getUnitAllAssessment(params);
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

    /**
     *三级页面-用能单位类型-同期计划数据表格加载
     * sunbinbin
     * @return List
     */
    @Override
    public Map<String,Object> getThirdTables(Map paramsMap) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = thiredpageEnergyDao.getThirdTables(paramsMap);
        List<Map<String,Object>> totalMap = thiredpageEnergyDao.getThirdTotals(paramsMap);
        //数据封装
        if(null !=totalMap && !totalMap.isEmpty()) {
            for (Map data1 : totalMap) {
                if ("1".equals(data1.get(UNIT_TYPE))) {
                    result.put("feedTotal", data1);
                }
                if ("2".equals(data1.get(UNIT_TYPE))) {
                    result.put("netTotal", data1);
                }
                if ("3".equals(data1.get(UNIT_TYPE))) {
                    result.put("stationTotal", data1);
                }
                if ("4".equals(data1.get(UNIT_TYPE))) {
                    result.put("lineTotal", data1);
                }
                if ("5".equals(data1.get(UNIT_TYPE))) {
                    result.put("roomTotal", data1);
                }
            }
        }else{
            Map<String, Object> temap = new HashMap<>();

            temap.put("w",0);
            temap.put("w1",0);
            temap.put("w_plan",0);
            temap.put("e",0);
            temap.put("e1",0);
            temap.put("e_plan",0);
            temap.put("g",0);
            temap.put("g1",0);
            temap.put("g_plan",0);
            temap.put("h",0);
            temap.put("h1",0);
            temap.put("h_plan",0);
            temap.put("c",0);
            temap.put("c1",0);
            temap.put("c_plan",0);
            temap.put("l",0);
            temap.put("l1",0);
            temap.put("l_plan",0);
            for (String type :UNITTYPE) {
                if(type.equals("1")){
                    temap.put(UNIT_NAME,"源");
                    temap.put(UNIT_TYPE,type);
                    result.put("feedTotal", temap);
                }
                if(type.equals("2")){
                    temap.put(UNIT_NAME,"网");
                    temap.put(UNIT_TYPE,type);
                    result.put("netTotal", temap);
                }
                if(type.equals("3")){
                    temap.put(UNIT_NAME,"站");
                    temap.put(UNIT_TYPE,type);
                    result.put("stationTotal", temap);
                }
                if(type.equals("4")){
                    temap.put(UNIT_NAME,"线");
                    temap.put(UNIT_TYPE,type);
                    result.put("lineTotal", temap);
                }
                if(type.equals("5")){
                    temap.put(UNIT_NAME,"户");
                    temap.put(UNIT_TYPE,type);
                    result.put("roomTotal", temap);
                }

            }

        }
        result.put("dates",TYPE_NAME);
        result.put("list", null==data?new ArrayList<Map<String, Object>>():data);
        return result;
    }

    /**
     *三级页面-分公司-源、网、站、线、户的各个能源类型的能耗趋势图
     * sunbinbin
     * @return map
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getFgsEnergyTypes(Map params) throws Exception {
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
        List<Map<String,Object>> listMap = thiredpageEnergyDao.getFgsDatas(params);
        //组装chartJson格式开始
        boolean lmEmpty = null!=listMap && listMap.size()>0;
        for(String type:energyTypes){//源、网、站、线、户
            List<String> curList = new ArrayList<String>();//今年某一时间段（查询条件中的时间段）的值
            List<String> lastList = new ArrayList<String>();//去年某一时间段（查询条件中的时间段）的值
            Map<String,Object> my = new HashMap<>();
            Double curYearTotal = 0.0,lastYearTotal = 0.0;//今年和去年能耗数据
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

    /**
     *三级页面-分公司-换热站排名趋势图
     * sunbinbin
     * @return string
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> assessments(Map<String, Object> paramsMap) throws Exception{
        Map<String,Object> data = new HashMap<>();
        List<Map<String,Object>> heats = thiredpageEnergyDao.selectassessment(paramsMap);//热源能源类型的排名
        List<String> unitnames = new ArrayList<>();
        List<String> unitnumbers = new ArrayList<>();
        for(Map<String,Object> heat:heats){
            if(null != heat){
                unitnames.add(heat.get(UNIT_NAME1).toString());
                unitnumbers.add(heat.get("dosage").toString());
            }
        }
        data.put("unitnames",unitnames);
        data.put("unitnumber",unitnumbers);
        return data;
    }
}
