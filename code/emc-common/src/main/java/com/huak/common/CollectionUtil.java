package com.huak.common;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.utils.DateUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.common<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/13<BR>
 * Description:   集合工具类  <BR>
 * Function List:  <BR>
 */
public class CollectionUtil {
    private static final String TYPE_NAME = "typeName";
    private static final String DATA_LIST = "dataList";

    /**
     * 删除ArrayList中重复元素，保持顺序
     *
     * @param list
     * @return
     */
    public static List removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element)) {
                newList.add(element);
            }
        }
        list.clear();
        list.addAll(newList);
        return list;
    }

    /**
     * 对象转换map key为大写
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> Obj2Map(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            //过滤序列化字段
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            field.setAccessible(true);
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), obj.getClass());
            Method getMethod = pd.getReadMethod();
            map.put(field.getName().toUpperCase(), getMethod.invoke(obj));
        }
        return map;
    }

    /**
     * 封装数据方法
     * 由于emc系统有大量的本期和同期的折线图，故有此公共方法
     * 限制：横坐标每天时间、本期折线、同期折线
     *
     * @param start    开始时间
     * @param end      结束时间
     * @param thisList 本期数据List<Map<String,Object>> map中一定存在key{dayDate,dayValue}
     * @param sameList 同期数据List<Map<String,Object>> map中一定存在key{dayDate,dayValue}
     * @return JSONObject
     * key = xdatas value = List<String>{}   横坐标
     * key = datas value = List<String>{}    两条线，本期同期
     */
    public static JSONObject packageDataLine(String start, String end, List<Map<String, Object>> thisList, List<Map<String, Object>> sameList)
            throws Exception {
        JSONObject json = new JSONObject();
        List<String> aList = new ArrayList<>();
        List<String> bList = new ArrayList<>();
        //格式化时间 返回时间list 确定2月29号
        List<String> dates = getDatesList(start, end);

        //list转map
        Map<String, Object> aMap = listToMap(thisList);
        Map<String, Object> bMap = listToMap(sameList);
        //循环时间list 从map取数据封装成list 没有补0
        for (String key : dates) {
            Object a = aMap.get(key);
            Object b = bMap.get(getYestYear(key));
            if (StringUtils.isEmpty(a)) {
                aList.add("0");
            } else {
                aList.add(a.toString());
            }
            if (StringUtils.isEmpty(b)) {
                bList.add("0");
            } else {
                bList.add(b.toString());
            }
        }
        //返回数据map
        json.put("xdatas", dates);
        Map<String, Object> bqMap = new HashMap<>();
        bqMap.put(TYPE_NAME, "本期");
        bqMap.put(DATA_LIST, aList);
        Map<String, Object> tqMap = new HashMap<>();
        tqMap.put(TYPE_NAME, "同期");
        tqMap.put(DATA_LIST, bList);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(bqMap);
        list.add(tqMap);
        json.put("datas", list);
        return json;
    }

    private static String getYestYear(String key) {
        Integer year = Integer.valueOf(key.substring(0, 4));
        String suffix = key.substring(4, key.length());
        return (year - 1) + suffix;
    }

    /**
     * @param start 开始时间 格式必须存在年月日
     * @param end   结束时间 格式必须存在年月日
     * @return List<String> yyyy-MM-dd
     */
    private static List<String> getDatesList(String start, String end) throws Exception {
        List<String> dates = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = format.parse(start);
        Date eDate = format.parse(end);
        start = format.format(sDate);
        end = format.format(eDate);
        if (end.compareTo(start) < 0) {
            return null;
        }
        while (!start.equals(end)) {
            dates.add(start);
            DateUtils.isAddDate(start, dates);
            start = DateUtils.getYearDate(start, Calendar.DATE, 1);
        }
        dates.add(end);
        return dates;
    }

    /**
     * list转map
     *
     * @param list
     * @return
     */
    private static Map<String, Object> listToMap(List<Map<String, Object>> list) {
        Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> listMap : list) {
            map.put(listMap.get("dayDate").toString(), listMap.get("dayValue"));
        }
        return map;
    }

    /**
     * list转map
     *
     * @param list
     * @return
     */
    private static Map<String, Object> listToMap(List<Map<String, Object>> list,String type) {
        Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> listMap : list) {
            map.put(listMap.get("dayDate").toString(), listMap.get(type));
        }
        return map;
    }

//    public static void main(String[] args) {
//        String start = "2017-01-02 33";
//        String end = "2017-03-04 33";
//        List<Map<String, Object>> thisList = new ArrayList<>();
//        List<Map<String, Object>> sameList = new ArrayList<>();
//        for(int i=0;i<13;i+=2){
//            Map<String, Object> map = new HashMap<>();
//            try {
//                map.put("dayDate",DateUtils.getYearDate(start, Calendar.DATE, i));
//                map.put("dayValue",i);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            thisList.add(map);
//        }
//        for(int i=0;i<21;i+=3){
//            Map<String, Object> map = new HashMap<>();
//            try {
//                map.put("dayDate",DateUtils.getYearDate(start, Calendar.DATE, i));
//                map.put("dayValue",i);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            sameList.add(map);
//        }
//        Map<String,Object> packageMap = new HashMap<>();
//        try {
//            packageMap = packageDataLine(start,end,thisList,sameList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.err.println("------------------");
//    }

    /**
     * 封装数据方法
     * 由于emc系统有大量的本期和同期的折线图，故有此公共方法
     * 限制：横坐标每天时间、本期折线、同期折线
     *
     * @param start    开始时间
     * @param end      结束时间
     * @param thisList 本期数据List<Map<String,Object>> map中一定存在key{dayDate,dayValue}
     * @param sameList 同期数据List<Map<String,Object>> map中一定存在key{dayDate,dayValue}
     * @return JSONObject
     * key = xdatas value = List<String>{}   横坐标
     * key = datas value = List<String>{}    两条线，本期同期
     */
    public static JSONObject packageDataLineYl(String start, String end, List<Map<String, Object>> thisList, List<Map<String, Object>> sameList,String type) throws Exception {
        JSONObject json = new JSONObject();
        List<String> aList = new ArrayList<>();
        List<String> bList = new ArrayList<>();
        //格式化时间 返回时间list 确定2月29号
        List<String> dates = getDatesList(start, end);
        //list转map
        Map<String, Object> aMap = listToMap(thisList,type);
        Map<String, Object> bMap = listToMap(sameList,type);
        //循环时间list 从map取数据封装成list 没有补0
        for (String key : dates) {
            Object a = aMap.get(key);
            Object b = bMap.get(getYestYear(key));
            if (StringUtils.isEmpty(a)) {
                aList.add("0");
            } else {
                aList.add(a.toString());
            }
            if (StringUtils.isEmpty(b)) {
                bList.add("0");
            } else {
                bList.add(b.toString());
            }
        }
        //返回数据map
        json.put("xdatas", dates);
        Map<String, Object> bqMap = new HashMap<>();
        bqMap.put(TYPE_NAME, "本期");
        bqMap.put(DATA_LIST, aList);
        Map<String, Object> tqMap = new HashMap<>();
        tqMap.put(TYPE_NAME, "同期");
        tqMap.put(DATA_LIST, bList);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(bqMap);
        list.add(tqMap);
        json.put("datas", list);
        return json;
    }

}
