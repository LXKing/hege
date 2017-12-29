package com.huak.sys.type;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.sys.bean<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/10/28<BR>
 * Description: 行政区划等级枚举   <BR>
 * Function List:  <BR>
 */
public enum AdminLevelType {
    PROVINCE(1,"省级/直辖市"),
    CITY(2,"市级/区"),
    COUNTY(3,"县级"),
    TOWN(4,"镇"),
    VILLAGE(5,"村");

    private int key;
    private String value;

    AdminLevelType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public static <T extends Enum> List<T> toList(Class<T> clazz) {
        return Arrays.asList(clazz.getEnumConstants());
    }

    public static void main(String[] args){
        //System.out.print(OrgType.GS.getKey()+"--------"+OrgType.GS.getValue());
        //遍历所有的枚举
        for( AdminLevelType color : AdminLevelType.values()){
            System.out.println( color + "  name: " + color.getKey() + "  index: " + color.getValue() );
        }
        List<AdminLevelType> list =  AdminLevelType.toList(AdminLevelType.class);
        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i).getKey()+"---"+list.get(i).getValue());
        }
    }
}
