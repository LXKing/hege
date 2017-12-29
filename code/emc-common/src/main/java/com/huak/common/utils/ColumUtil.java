package com.huak.common.utils;

/**
 * Created by MR-BIN on 2017/8/28.
 */
public class ColumUtil {

    public static String getColumn(String temp){
        String field = temp.replaceFirst(String.valueOf(temp.charAt(0)),String.valueOf(temp.charAt(0)).toLowerCase());
        StringBuilder fields = new StringBuilder(field);
        char[] q = field.toCharArray();
        for(int i = 0; i < q.length; i++){
            char c = q[i];
            int n = fields.toString().indexOf(c);
            if (Character.isUpperCase(c)&& n !=0)
            {
                String str2=String.valueOf(fields.charAt(n)).toLowerCase();
                fields.replace(n, n+1,"_"+str2);
            }
        }
        return fields.toString().toUpperCase();
    }
}
