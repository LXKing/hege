package com.huak.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.tools<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/25<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class NavigationConstant {


    /*首页*/
    public final static Navigation HOME_U = new Navigation(null,"首页","/");
    public final static Navigation HOME = new Navigation(null,"首页","/index");
    public final static Navigation SECOND_ENERGY = new Navigation(HOME,"能耗分析","/energy/monitor/tsec");
    public final static Navigation SECOND_CONS = new Navigation(HOME,"单耗分析","/cons/analysis/tsec");
    /*能源类型三级*/
    public final static Navigation THIRD_ENERGY_WATER = new Navigation(SECOND_ENERGY,"水能耗分析","/third/energy/page/1");
    public final static Navigation THIRD_ENERGY_ELECTRIC = new Navigation(SECOND_ENERGY,"电能耗分析","/third/energy/page/2");
    public final static Navigation THIRD_ENERGY_GAS = new Navigation(SECOND_ENERGY,"气能耗分析","/third/energy/page/3");
    public final static Navigation THIRD_ENERGY_HEAT = new Navigation(SECOND_ENERGY,"热能耗分析","/third/energy/page/4");
    public final static Navigation THIRD_ENERGY_COAL = new Navigation(SECOND_ENERGY,"煤能耗分析","/third/energy/page/5");
    public final static Navigation THIRD_ENERGY_OIL = new Navigation(SECOND_ENERGY,"油能耗分析","/third/energy/page/6");
    public final static Navigation THIRD_CONS_WATER = new Navigation(SECOND_CONS,"水单耗分析","/third/analysis/page/1");
    public final static Navigation THIRD_CONS_ELECTRIC = new Navigation(SECOND_CONS,"电单耗分析","/third/analysis/page/2");
    public final static Navigation THIRD_CONS_GAS = new Navigation(SECOND_CONS,"气单耗分析","/third/analysis/page/3");
    public final static Navigation THIRD_CONS_HEAT = new Navigation(SECOND_CONS,"热单耗分析","/third/analysis/page/4");
    public final static Navigation THIRD_CONS_COAL = new Navigation(SECOND_CONS,"煤单耗分析","/third/analysis/page/5");
    public final static Navigation THIRD_CONS_OIL = new Navigation(SECOND_CONS,"油单耗分析","/third/analysis/page/6");
    /*分公司三级*/
    public final static Navigation THIRD_ENERGY_FGS = new Navigation(SECOND_ENERGY,"单位能耗分析","/third/energy/fgs/*");
    public final static Navigation THIRD_CONS_FGS = new Navigation(SECOND_CONS,"单位单耗分析","/third/analysis/fgs/*");
    /*单位类型三级*/
    public final static Navigation THIRD_ENERGY_FEED = new Navigation(SECOND_ENERGY,"供热源能耗分析","/third/energy/unit/1");
    public final static Navigation THIRD_ENERGY_NET = new Navigation(SECOND_ENERGY,"一次网能耗分析","/third/energy/unit/2");
    public final static Navigation THIRD_ENERGY_STATION = new Navigation(SECOND_ENERGY,"换热站能耗分析","/third/energy/unit/3");
    public final static Navigation THIRD_ENERGY_LINE = new Navigation(SECOND_ENERGY,"二次线能耗分析","/third/energy/unit/4");
    public final static Navigation THIRD_ENERGY_ROOM = new Navigation(SECOND_ENERGY,"民户能耗分析","/third/energy/unit/5");
    public final static Navigation THIRD_CONS_FEED = new Navigation(SECOND_CONS,"供热源单耗分析","/third/analysis/unit/1");
    public final static Navigation THIRD_CONS_NET = new Navigation(SECOND_CONS,"一次网单耗分析","/third/analysis/unit/2");
    public final static Navigation THIRD_CONS_STATION = new Navigation(SECOND_CONS,"换热站单耗分析","/third/analysis/unit/3");
    public final static Navigation THIRD_CONS_LINE = new Navigation(SECOND_CONS,"二次线单耗分析","/third/analysis/unit/4");
    public final static Navigation THIRD_CONS_ROOM = new Navigation(SECOND_CONS,"民户单耗分析","/third/analysis/unit/5");

    /* 健康指数 */
    public final static Navigation HEALTH = new Navigation(HOME,"健康指数","/health");

    /*能耗分析*/


    /*成本管控*/

    /*碳排管理*/

    /*报警管理*/

    /*项目后评估*/

    /*后台*/
    public final static Navigation SYSTEM = new Navigation(null,"安全与后台","/system/index");

    /*流程调度*/
    public final static Navigation PROCESS = new Navigation(null,"流程调度","/process/index");

    /*全部*/
    public final static List<Navigation> NAVIGATIONS = new ArrayList<>();

    static{
        NAVIGATIONS.add(HOME_U);
        NAVIGATIONS.add(HOME);
        NAVIGATIONS.add(SECOND_ENERGY);
        NAVIGATIONS.add(SECOND_CONS);
        NAVIGATIONS.add(THIRD_ENERGY_WATER);
        NAVIGATIONS.add(THIRD_ENERGY_ELECTRIC);
        NAVIGATIONS.add(THIRD_ENERGY_GAS);
        NAVIGATIONS.add(THIRD_ENERGY_HEAT);
        NAVIGATIONS.add(THIRD_ENERGY_COAL);
        NAVIGATIONS.add(THIRD_ENERGY_OIL);
        NAVIGATIONS.add(THIRD_CONS_WATER);
        NAVIGATIONS.add(THIRD_CONS_ELECTRIC);
        NAVIGATIONS.add(THIRD_CONS_GAS);
        NAVIGATIONS.add(THIRD_CONS_HEAT);
        NAVIGATIONS.add(THIRD_CONS_COAL);
        NAVIGATIONS.add(THIRD_CONS_OIL);
        NAVIGATIONS.add(THIRD_ENERGY_FGS);
        NAVIGATIONS.add(THIRD_CONS_FGS);
        NAVIGATIONS.add(THIRD_ENERGY_FEED);
        NAVIGATIONS.add(THIRD_ENERGY_NET);
        NAVIGATIONS.add(THIRD_ENERGY_STATION);
        NAVIGATIONS.add(THIRD_ENERGY_LINE);
        NAVIGATIONS.add(THIRD_ENERGY_ROOM);
        NAVIGATIONS.add(THIRD_CONS_FEED);
        NAVIGATIONS.add(THIRD_CONS_NET);
        NAVIGATIONS.add(THIRD_CONS_STATION);
        NAVIGATIONS.add(THIRD_CONS_LINE);
        NAVIGATIONS.add(THIRD_CONS_ROOM);

        NAVIGATIONS.add(HEALTH);

        NAVIGATIONS.add(SYSTEM);

        NAVIGATIONS.add(PROCESS);
    }

}
