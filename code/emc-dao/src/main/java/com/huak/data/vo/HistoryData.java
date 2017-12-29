package com.huak.data.vo;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.history.vo<BR>
 * Author:  liuhe  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/11/24<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class HistoryData implements Serializable{

    private static final long serialVersionUID = -8611677786596225445L;
    //采集时间
    private String collect_Time;
    //6区03号站累计流量
    private String aI_03pri_rb_Fl;
    //6区03号站累计热量
    private String aI_03pri_rb_Hl;
    //中支回温
    private String aI_n_c_T;
    //供水压力
    private String aI_E_rb_g_P;
    //供水温度
    private String aI_E_rb_g_T;
    //供水瞬时流量
    private String aI_E_rb_g_Ft;
    //供水瞬时热量
    private String aI_E_rb_g_Ht;
    //供水累计流量
    private String aI_E_rb_g_Fl;
    //供水累计热量
    private String aI_E_rb_g_Hl;
    //回水压力
    private String aI_E_rb_h_P;
    //回水温度
    private String aI_E_rb_h_T;
    //回水瞬时流量
    private String aI_E_rb_h_Ft;
    //回水瞬时热量
    private String aI_E_rb_h_Ht;
    //回水累计流量
    private String aI_E_rb_h_Fl;
    //回水累计热量
    private String aI_E_rb_h_Hl;
    //室外温度
    private String aI_out_T;
    //有功电能
    private String aI_E_KWH;
    //瞬时热量
    private String aI_E_rb_Ht;
    //累计流量
    private String aI_pri_rb_Fl;
    //累计热量
    private String aI_pri_rb_Hl;
    //补水表量
    private String aI_add_Fl;

    public HistoryData(String collect_Time, String aI_03pri_rb_Fl, String aI_03pri_rb_Hl, String aI_n_c_T, String aI_E_rb_g_P, String aI_E_rb_g_T, String aI_E_rb_g_Ft, String aI_E_rb_g_Ht, String aI_E_rb_g_Fl, String aI_E_rb_g_Hl, String aI_E_rb_h_P, String aI_E_rb_h_T, String aI_E_rb_h_Ft, String aI_E_rb_h_Ht, String aI_E_rb_h_Fl, String aI_E_rb_h_Hl, String aI_out_T, String aI_E_KWH, String aI_E_rb_Ht, String aI_pri_rb_Fl, String aI_pri_rb_Hl, String aI_add_Fl) {
        this.collect_Time = collect_Time;
        this.aI_03pri_rb_Fl = aI_03pri_rb_Fl;
        this.aI_03pri_rb_Hl = aI_03pri_rb_Hl;
        this.aI_n_c_T = aI_n_c_T;
        this.aI_E_rb_g_P = aI_E_rb_g_P;
        this.aI_E_rb_g_T = aI_E_rb_g_T;
        this.aI_E_rb_g_Ft = aI_E_rb_g_Ft;
        this.aI_E_rb_g_Ht = aI_E_rb_g_Ht;
        this.aI_E_rb_g_Fl = aI_E_rb_g_Fl;
        this.aI_E_rb_g_Hl = aI_E_rb_g_Hl;
        this.aI_E_rb_h_P = aI_E_rb_h_P;
        this.aI_E_rb_h_T = aI_E_rb_h_T;
        this.aI_E_rb_h_Ft = aI_E_rb_h_Ft;
        this.aI_E_rb_h_Ht = aI_E_rb_h_Ht;
        this.aI_E_rb_h_Fl = aI_E_rb_h_Fl;
        this.aI_E_rb_h_Hl = aI_E_rb_h_Hl;
        this.aI_out_T = aI_out_T;
        this.aI_E_KWH = aI_E_KWH;
        this.aI_E_rb_Ht = aI_E_rb_Ht;
        this.aI_pri_rb_Fl = aI_pri_rb_Fl;
        this.aI_pri_rb_Hl = aI_pri_rb_Hl;
        this.aI_add_Fl = aI_add_Fl;
    }

    @Override
    public String toString() {
        return "HistoryData{" +
                "collect_Time='" + collect_Time + '\'' +
                ", aI_03pri_rb_Fl='" + aI_03pri_rb_Fl + '\'' +
                ", aI_03pri_rb_Hl='" + aI_03pri_rb_Hl + '\'' +
                ", aI_n_c_T='" + aI_n_c_T + '\'' +
                ", aI_E_rb_g_P='" + aI_E_rb_g_P + '\'' +
                ", aI_E_rb_g_T='" + aI_E_rb_g_T + '\'' +
                ", aI_E_rb_g_Ft='" + aI_E_rb_g_Ft + '\'' +
                ", aI_E_rb_g_Ht='" + aI_E_rb_g_Ht + '\'' +
                ", aI_E_rb_g_Fl='" + aI_E_rb_g_Fl + '\'' +
                ", aI_E_rb_g_Hl='" + aI_E_rb_g_Hl + '\'' +
                ", aI_E_rb_h_P='" + aI_E_rb_h_P + '\'' +
                ", aI_E_rb_h_T='" + aI_E_rb_h_T + '\'' +
                ", aI_E_rb_h_Ft='" + aI_E_rb_h_Ft + '\'' +
                ", aI_E_rb_h_Ht='" + aI_E_rb_h_Ht + '\'' +
                ", aI_E_rb_h_Fl='" + aI_E_rb_h_Fl + '\'' +
                ", aI_E_rb_h_Hl='" + aI_E_rb_h_Hl + '\'' +
                ", aI_out_T='" + aI_out_T + '\'' +
                ", aI_E_KWH='" + aI_E_KWH + '\'' +
                ", aI_E_rb_Ht='" + aI_E_rb_Ht + '\'' +
                ", aI_pri_rb_Fl='" + aI_pri_rb_Fl + '\'' +
                ", aI_pri_rb_Hl='" + aI_pri_rb_Hl + '\'' +
                ", aI_add_Fl='" + aI_add_Fl + '\'' +
                '}';
    }

    public String getCollect_Time() {
        return collect_Time;
    }

    public void setCollect_Time(String collect_Time) {
        this.collect_Time = collect_Time;
    }

    public String getaI_03pri_rb_Fl() {
        return aI_03pri_rb_Fl;
    }

    public void setaI_03pri_rb_Fl(String aI_03pri_rb_Fl) {
        this.aI_03pri_rb_Fl = aI_03pri_rb_Fl;
    }

    public String getaI_03pri_rb_Hl() {
        return aI_03pri_rb_Hl;
    }

    public void setaI_03pri_rb_Hl(String aI_03pri_rb_Hl) {
        this.aI_03pri_rb_Hl = aI_03pri_rb_Hl;
    }

    public String getaI_n_c_T() {
        return aI_n_c_T;
    }

    public void setaI_n_c_T(String aI_n_c_T) {
        this.aI_n_c_T = aI_n_c_T;
    }

    public String getaI_E_rb_g_P() {
        return aI_E_rb_g_P;
    }

    public void setaI_E_rb_g_P(String aI_E_rb_g_P) {
        this.aI_E_rb_g_P = aI_E_rb_g_P;
    }

    public String getaI_E_rb_g_T() {
        return aI_E_rb_g_T;
    }

    public void setaI_E_rb_g_T(String aI_E_rb_g_T) {
        this.aI_E_rb_g_T = aI_E_rb_g_T;
    }

    public String getaI_E_rb_g_Ft() {
        return aI_E_rb_g_Ft;
    }

    public void setaI_E_rb_g_Ft(String aI_E_rb_g_Ft) {
        this.aI_E_rb_g_Ft = aI_E_rb_g_Ft;
    }

    public String getaI_E_rb_g_Ht() {
        return aI_E_rb_g_Ht;
    }

    public void setaI_E_rb_g_Ht(String aI_E_rb_g_Ht) {
        this.aI_E_rb_g_Ht = aI_E_rb_g_Ht;
    }

    public String getaI_E_rb_g_Fl() {
        return aI_E_rb_g_Fl;
    }

    public void setaI_E_rb_g_Fl(String aI_E_rb_g_Fl) {
        this.aI_E_rb_g_Fl = aI_E_rb_g_Fl;
    }

    public String getaI_E_rb_g_Hl() {
        return aI_E_rb_g_Hl;
    }

    public void setaI_E_rb_g_Hl(String aI_E_rb_g_Hl) {
        this.aI_E_rb_g_Hl = aI_E_rb_g_Hl;
    }

    public String getaI_E_rb_h_P() {
        return aI_E_rb_h_P;
    }

    public void setaI_E_rb_h_P(String aI_E_rb_h_P) {
        this.aI_E_rb_h_P = aI_E_rb_h_P;
    }

    public String getaI_E_rb_h_T() {
        return aI_E_rb_h_T;
    }

    public void setaI_E_rb_h_T(String aI_E_rb_h_T) {
        this.aI_E_rb_h_T = aI_E_rb_h_T;
    }

    public String getaI_E_rb_h_Ft() {
        return aI_E_rb_h_Ft;
    }

    public void setaI_E_rb_h_Ft(String aI_E_rb_h_Ft) {
        this.aI_E_rb_h_Ft = aI_E_rb_h_Ft;
    }

    public String getaI_E_rb_h_Ht() {
        return aI_E_rb_h_Ht;
    }

    public void setaI_E_rb_h_Ht(String aI_E_rb_h_Ht) {
        this.aI_E_rb_h_Ht = aI_E_rb_h_Ht;
    }

    public String getaI_E_rb_h_Fl() {
        return aI_E_rb_h_Fl;
    }

    public void setaI_E_rb_h_Fl(String aI_E_rb_h_Fl) {
        this.aI_E_rb_h_Fl = aI_E_rb_h_Fl;
    }

    public String getaI_E_rb_h_Hl() {
        return aI_E_rb_h_Hl;
    }

    public void setaI_E_rb_h_Hl(String aI_E_rb_h_Hl) {
        this.aI_E_rb_h_Hl = aI_E_rb_h_Hl;
    }

    public String getaI_out_T() {
        return aI_out_T;
    }

    public void setaI_out_T(String aI_out_T) {
        this.aI_out_T = aI_out_T;
    }

    public String getaI_E_KWH() {
        return aI_E_KWH;
    }

    public void setaI_E_KWH(String aI_E_KWH) {
        this.aI_E_KWH = aI_E_KWH;
    }

    public String getaI_E_rb_Ht() {
        return aI_E_rb_Ht;
    }

    public void setaI_E_rb_Ht(String aI_E_rb_Ht) {
        this.aI_E_rb_Ht = aI_E_rb_Ht;
    }

    public String getaI_pri_rb_Fl() {
        return aI_pri_rb_Fl;
    }

    public void setaI_pri_rb_Fl(String aI_pri_rb_Fl) {
        this.aI_pri_rb_Fl = aI_pri_rb_Fl;
    }

    public String getaI_pri_rb_Hl() {
        return aI_pri_rb_Hl;
    }

    public void setaI_pri_rb_Hl(String aI_pri_rb_Hl) {
        this.aI_pri_rb_Hl = aI_pri_rb_Hl;
    }

    public String getaI_add_Fl() {
        return aI_add_Fl;
    }

    public void setaI_add_Fl(String aI_add_Fl) {
        this.aI_add_Fl = aI_add_Fl;
    }

    public HistoryData() {

    }
}
