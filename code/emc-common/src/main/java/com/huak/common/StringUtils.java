package com.huak.common;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.common<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/12/7<BR>
 * Description:  字符串操作   <BR>
 * Function List:  <BR>
 */
public class StringUtils {

    /**
     * 获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        //随机字符串的随机字符库
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(new SecureRandom().nextDouble() * (len - 1))));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getRandomString(8));
        System.out.println(new SecureRandom().nextDouble());
    }

    public static boolean isEmpty(Object obj) {
        if (obj != null && !obj.toString().trim().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 解析公式里面的所有code
     * code A00112
     *
     * @param formula
     * @return
     */
    public static List<String> paresCodes(String formula) {
        String A = "A[0-9]{5}";
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(A);
        Matcher matcher = pattern.matcher(formula);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    /**
     * 字符串前补位
     *
     * @param str    原字符串
     * @param pad    补位字符
     * @param length 最后长度
     * @return
     */
    public static String cover(String str, String pad, int length) {
        if (str.length() >= length) {
            return str;
        }

        int l = length - str.length();
        for (int i = 0; i < l; i++) {
            str = pad.concat(str);
        }
        return str;
    }
}
