package com.huak.org.type;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org.type<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/26<BR>
 * Description:   组织机构枚举  <BR>
 * Function List:  <BR>
 */
public enum OrgType {
    GS(1,"公司"),//2
    FGS(2,"分公司"),//345
    ZX(3,"中心"),//45
    FWZ(4,"服务站"),//5
    RY(5,"部门");


    private int key;
    private String value;

    OrgType(int key, String value) {
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



    public static void main(String[] args){
        //System.out.print(OrgType.GS.getKey()+"--------"+OrgType.GS.getValue());
        //遍历所有的枚举
        for( OrgType color : OrgType.values()){
            System.out.println( color + "  name: " + color.getKey() + "  index: " + color.getValue() );
        }
       List<OrgType> list =  OrgType.toList(OrgType.class);
        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i).getKey()+"---"+list.get(i).getValue());
        }
    }
    public static <T extends Enum> List<T> toList(Class<T> clazz) {
        return Arrays.asList(clazz.getEnumConstants());
    }
}
