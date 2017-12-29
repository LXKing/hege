package com.huak.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.common.utils<BR>
 * Author:  Bin  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/12/28<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");

    public static List<String> getDates(String year, String month) throws ParseException {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat tempdate = new SimpleDateFormat("YYYY-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(tempdate.parse(year + "-" + month));//month 为指定月份任意日期
        int tempYear = Integer.parseInt(year);
        int m = cal.get(Calendar.MONTH) + 1;
        int dayNumOfMonth = DateUtils.getDaysByYearMonth(tempYear, m);
        cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始
        for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
            Date d = cal.getTime();

            String df = sdf.format(d);
            dates.add(df);
        }

        return dates;
    }

    private static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取日期
     * @return
     */
    public static String getDay(String date,int i) throws ParseException {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(sdf.parse(date));
        calendar.add(Calendar.DATE,  i);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当年的最后一天

     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 计算日期相差天数
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String smdate,String bdate) throws ParseException{

        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 是否添加2月29
     * @param sDate
     * @param yearList
     * @throws Exception
     */
    public static void isAddDate(String sDate, List<String> yearList) throws Exception {
        if(sDate.indexOf("-02-28")>=1&&getYearDate(sDate, Calendar.DATE,1).indexOf("-02-29")<1){
            yearList.add(sDate.substring(0,5)+"02-29");
        }
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
    public static String getYearDate(String curDate, int type, int num) throws Exception{
        curDate = (curDate==null||"".equals(curDate))?sdf.format(new Date()):curDate;
        Date date = sdf.parse(curDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, num);
        date = calendar.getTime();
        return sdf.format(date);
    }

    public static String getTimeDate(String curDate, int type, int num) throws Exception{
        curDate = (curDate==null||"".equals(curDate))?format.format(new Date()):curDate;
        Date date = format.parse(curDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, num);
        date = calendar.getTime();
        return format.format(date);
    }

    /**
     * 返回时间段每小时集合
     * 不计算结束时间
     * @param time1
     * @param time2
     * @return
     */
    public static List<String> getDateTimes(String time1, String time2) throws Exception {
        List<String> list = new ArrayList<>();
        while (!time1.equals(time2)) {
            list.add(time1);
            time1 = DateUtils.getTimeDate(time1, Calendar.HOUR, 1);
        }
        return list;
    }
}
