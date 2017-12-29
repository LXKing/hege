/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lc  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-11-09<BR>
 */


/**
 * 工单状态翻译
 * statusCode 状态码
 * roleType 角色类型 1派单员 2班长 3接单员
 */
function workOrderStatus(statusCode,roleType){
    var descs = {
        0:{1:"",2:"",3:""},
        111:{1:"待派单",2:"",3:""},
        112:{1:"已派单",2:"待接单",3:""},
        113:{1:"已派单",2:"待接单",3:"待接单"},
        121:{1:"已完成",2:"已完成",3:"已完成"},
        131:{1:"已关闭",2:"已退单",3:"已退单"},
        141:{1:"已完成",2:"已完成",3:""},
        151:{1:"已关闭",2:"已退单",3:""},
        161:{1:"已关闭",2:"已退单",3:"已退单"},
        171:{1:"已完成",2:"已完成",3:"已完成"},
        211:{1:"已接单",2:"处理中",3:""},
        212:{1:"待处理",2:"已退单",3:""},
        213:{1:"待确认",2:"已完成",3:""},
        214:{1:"已派单",2:"已派单",3:"待接单"},
        311:{1:"待确认",2:"已完成",3:"已完成"},
        312:{1:"待处理",2:"已退单",3:"已退单"},
        321:{1:"待处理",2:"已退单",3:"已退单"},
        322:{1:"已接单",2:"处理中",3:"处理中"},
        323:{1:"待确认",2:"已完成",3:"已完成"},
        991:{1:"已重派",2:"已完成",3:"已完成"},
        992:{1:"已重派",2:"已退单",3:"已退单"},
        993:{1:"已重派",2:"已完成",3:""},
        994:{1:"已重派",2:"已退单",3:""},
        995:{1:"已重派",2:"",3:"已完成"},
        996:{1:"已重派",2:"",3:"已退单"}
    };
    return descs[statusCode][roleType];
}

/**
 * 打开工单详情页
 * code 工单编号
 */
function openDetail(type,code){
    var url = _web + "/work/order/info/detail/" + type + '/'+ code;
    $("#panelright").empty().load(url);
}

/**
 * 工单操作翻译
 * desc 操作说明
 * opertor 操作人
 * sendee 接收人
 */
function workOrderOperate(desc,opertor,sendee){
    desc += "";
    opertor += "";
    if(null!=sendee&&"undefined"!=sendee){
        sendee += "";
    }else{
        sendee = "";
    }

    var opertorSitg = '{1}';
    var sendeeSitg = '{2}';
    return desc.replace(opertorSitg,opertor).replace(sendeeSitg,sendee);
}