package com.huak.workorder.type;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.workorder.type<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-10-25<BR>
 * Description:   工单状态  <BR>
 * Function List:  <BR>
 */
public enum WorkOrderStatus {
    E000(0,"","","",""),

    A111(111,"已保存，待派单","待派单","",""),
    A112(112,"已派单，待接单","已派单","待接单",""),
    A113(113,"已派单，待接单","已派单","待接单","待接单"),
    A121(121,"已完成","已完成","已完成","已完成"),
    A131(131,"已关闭","已关闭","已退单","已退单"),
    A141(141,"已完成","已完成","已完成",""),
    A151(151,"已关闭","已关闭","已退单",""),
    A161(161,"已关闭","已关闭","已退单","已退单"),
    A171(171,"已完成","已完成","已完成","已完成"),

    B211(211,"已接单，处理中","已接单","处理中",""),
    B212(212,"已退单，待处理","待处理","已退单",""),
    B213(213,"已完成，待确认","待确认","已完成",""),
    B214(214,"已派单，待接单","已派单","已派单","待接单"),

    C311(311,"已完成，待确认","待确认","已完成","已完成"),
    C312(312,"已退单，待处理","待处理","已退单","已退单"),
    C321(321,"已退单，待处理","待处理","已退单","已退单"),
    C322(322,"已接单，处理中","已接单","处理中","处理中"),
    C323(323,"已完成，待确认","待确认","已完成","已完成"),

    R991(991,"已重派","已重派","已完成","已完成"),
    R992(992,"已重派","已重派","已退单","已退单"),
    R993(993,"已重派","已重派","已完成",""),
    R994(994,"已重派","已重派","已退单",""),
    R995(995,"已重派","已重派","","已完成"),
    R996(996,"已重派","已重派","","已退单");

    private int key;//值
    private String status;//工单状态
    private String dispatchDes;//派单翻译
    private String relayDes;//转发翻译
    private String takingDes;//接单翻译

    WorkOrderStatus(int key, String takingDes, String relayDes, String dispatchDes, String status) {
        this.key = key;
        this.takingDes = takingDes;
        this.relayDes = relayDes;
        this.dispatchDes = dispatchDes;
        this.status = status;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDispatchDes() {
        return dispatchDes;
    }

    public void setDispatchDes(String dispatchDes) {
        this.dispatchDes = dispatchDes;
    }

    public String getRelayDes() {
        return relayDes;
    }

    public void setRelayDes(String relayDes) {
        this.relayDes = relayDes;
    }

    public String getTakingDes() {
        return takingDes;
    }

    public void setTakingDes(String takingDes) {
        this.takingDes = takingDes;
    }
}
