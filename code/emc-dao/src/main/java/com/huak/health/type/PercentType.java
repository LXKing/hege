package com.huak.health.type;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health.type<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/30<BR>
 * Description:  健康指数百分比   <BR>
 * Function List:  <BR>
 */
public enum PercentType {
    FOUR_ONE(1,4,"健康指数"),
    WORK_ONE(4,10,"工况一级"),
    WORK_TWO(3,10,"工况二级"),
    WORK_THREE(2,10,"工况三级"),
    WORK_FOUR(1,10,"工况四级"),
    JJ_ROWER(3,6,"经济行标"),
    JJ_LANDMARK(2,6,"经济地标"),
    JJ_ENTER(1,6,"经济企标"),
    SERVICE_ONE(1,1,"服务一级"),
    TEMP_MIN(1,2,"室温低温"),
    TEMP_MAX(1,2,"室温高温");


    private int molecule;
    private int denominator;
    private String des;

    PercentType(int molecule, int denominator, String des) {
        this.molecule = molecule;
        this.denominator = denominator;
        this.des = des;
    }

    public int getMolecule() {
        return molecule;
    }

    public void setMolecule(int molecule) {
        this.molecule = molecule;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
