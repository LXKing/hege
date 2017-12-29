<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2017/8/31
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="${web}/script/huak.web.system.workinfo.js"></script>
<script src="${web}/script/huak.web.system.workinfo.public.js"></script>

<div class="main-box">
    <form id="workInfoSearch">
        <input type="hidden" name="_method" value="PATCH">
        <input type="hidden" name="pageNumber" value="1">
        <input type="hidden" name="orgId" value="${org.id}">
        <input type="hidden" name="comId" value="${company.id}">
        <div class="selectbg clearfix">
            <div class="sele-row clearfix row">
                <!--<div class="clearfix row">-->
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <label>工单名称</label>
                    </div>
                    <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                        <input class="inputs-lg" id="workOrderNameSearch" name="name" type="text" placeholder=" 请输入工单名称"/>
                    </div>
                </div>
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <label>工单类型</label>
                    </div>
                    <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                        <div class="select-box">
                            <div class="clearfix h-selectbox">
                                <div class="x-selectfree fl">
                                    <div class="x-sfbgbox1">
                                        <div class="x-sfleft1 x-sfw1">
                                            <input type="text" value="请选择工单类型" readonly="readonly">
                                        </div>
                                        <div class="x-sfright1"></div>
                                    </div>
                                    <div class="x-sfoption1" id="workerOder_type">
                                            <p value="">请选择工单类型</p>
                                            <p value="0">指定时间</p>
                                            <p value="1">非指定时间</p>
                                    </div>
                                    <input type="hidden" id="workOrderTypeSearch" name="type" value="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <a href="javascript:void(0);" class="btns btnsfl btns-lookin" onclick="queryWorkInfo()">查询</a>
                    <a href="javascript:void(0);" class="btns btnsfl btns-reset" onclick =reset()>重置</a>
                </div>

            </div>

        </div>
    </form>

    <div class="col-xs-12 btngroups">
        <c:if test="${sessionScope._auth['workOrderAdd']}">
            <a class="btns btnsfl btns-green top-layer-min-work" href="javascript:void(0);"  layer-form-id="workSendAddForm" layer-title="添加工单" layer-url="${web}/work/order/info/add">添加</a>
        </c:if>

        <a class="btns btnsfl exportchange btns-green" export-url="${web}/temp/config/export" href="javascript:void(0);">导出</a>

        <a class="btns btnsfl btns-green" href="javascript:void(0);" onclick="uploaderExcel()">导入</a>
        <%--<c:if test="${sessionScope._auth['alarmExport']}">--%>
        <%--<a class="btns btnsfl btns-green" href="javascript:void(0);">导出</a>--%>
        <%--</c:if>--%>

    </div>


    <div class="col-xs-12 main-table no-padding">
        <table class="table table-striped table-bordered table-hover pgtable">
            <thead>
            <tr>
                <td width="2%">序号</td>
                <td width="4%">
                    <div class="text-left">工单编号</div>
                </td>
                <td width="6%">
                    <div class="text-left">工单名称</div>
                </td>
                <td width="8%">
                    <div class="text-left">工单内容</div>
                </td>
                <td width="11%">
                    <div class="text-left">开始时间</div>
                </td>
                <td width="11%">
                    <div class="text-left">完成时间</div>
                </td>
                <td width="11%">
                    <div class="text-left">创建时间</div>
                </td>
                <td width="5%">
                    <div class="text-left">状态</div>
                </td>
                <td width="5%">
                    <div class="text-left">创建人<i class="icon-sort"></i></div>
                </td>
                <td width="6%">
                    <div class="text-left">班长</div>
                </td>
                <td width="6%">
                    <div class="text-left">接单员</div>
                </td>
                <td width="9%">
                    <div>操作</div>
                </td>
            </tr>
            </thead>
            <tbody id="workOrderBody">

            </tbody>
        </table>
    </div>
    <div class="mainpage clearfix">
        <div class="mianpageCount pull-left">
            共<span class="redtips redtipspad" id="redtipspad"></span>条内容
        </div>
        <div class="mainpageNow  pull-right text-right" id="paging">

        </div>

    </div>

</div>

<!-- 模板内容 -->
<textarea id="tpl-workOrder" style="display:none">
    {#foreach $T.list as record}
    <tr>
        <td>{$T.record$index+1}</td>
        <td>
            <a href="javascript:void(0);" onclick="openDetail(${roleType},'{$T.record.code}')"> <div class="text-left">{$T.record.code}</div></a>
        </td>
        <td>
           <a> <div class="text-left">{$T.record.name}</div></a>
        </td>
        <td>
            <div class="text-left" title='${$T.record.content}'>{fromatStr($T.record.content,7)}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.startTime}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.finishTime}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.createTime}</div>
        </td>
        <td>
            <div class="text-left">{workOrderStatus($T.record.status,${roleType})}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.creatorName}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.monitorName}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.takorName}</div>
        </td>
        <td>
                <%--<a href="javascript:send(0);" title="修改" class="operationbtn icon-edit top-layer-min"
                   layer-form-id="indexEditForm" layer-title="修改指标配置"  layer-url="${web}/work/order/info/edit?code={$T.record.code}&mid={$T.record.monitor}&reid={$T.record.takor}"></a>
                <a href="javascript:delAllocation('{$T.record.id}');" title="删除" class="operationbtn icon-delete"></a>--%>
                   <%-- {#if (($T.order.statusId==2&&$T.order.stepId==1)||($T.order.statusId==2&&$T.order.stepId==2))&&$T.order.ratioOrderCancelable}
                    <a href="javascript:cancel('{$T.order.id}','{$T.order.type}');"
                       class="cancel_order_link">取消订单</a>
                    {#/if}
            --%>


                {#if ($T.record.status == 111)&&${sessionScope._auth['workOrderSend']}}
                    <a href="javascript:send(0);" title="派单" class="operationbtn icon-edit top-layer-min-send"
                       layer-form-id="workSendEditForm" layer-title="工单派送"  layer-url="${web}/work/order/info/edit?code={$T.record.code}&mid={$T.record.monitor}&reid={$T.record.takor}"></a>
                {#/if}
                {#if ($T.record.status == 321 || $T.record.status == 212 || $T.record.status == 312)&&${sessionScope._auth['workOrderClose']}}
                    <a href="javascript:closeOrder('{$T.record.id}');" title="关闭" class="operationbtn icon-delete"></a>
                 {#/if}
                 {#if ($T.record.status == 311 || $T.record.status == 323 || $T.record.status == 213)&&${sessionScope._auth['workOrderConfirm']}}
                    <a href="javascript:confirmOrder('{$T.record.id}');" title="确认" class="operationbtn icon-delete"></a>
                 {#/if}
                {#if ($T.record.status == 212 || $T.record.status == 213 || $T.record.status == 321 || $T.record.status == 323 || $T.record.status == 311 || $T.record.status == 312)&&${sessionScope._auth['workOrderReset']}}
                    <a href="javascript:void(0);" title="重新派送" class="operationbtn icon-edit top-layer-min" layer-form-id="workOrderResetForm" layer-title="重新派送工单" layer-url="${web}/work/order/info/reset/{$T.record.id}"></a>
                 {#/if}
                    {#if ($T.record.status == 112 || $T.record.status == 113)&&${sessionScope._auth['workOrderTaking']}}
                    <%--<a href="javascript:send(0);" title="接单" class="operationbtn icon-edit top-layer-min-send"--%>
                       <%--layer-form-id="workSendEditForm" layer-title="接收工单"  layer-url="${web}/work/order/info/received?code={$T.record.code}&mid={$T.record.monitor}&reid={$T.record.takor}"></a>--%>
                    <a href="javascript:receivedOrder('{$T.record.code}','{$T.record.monitor}','{$T.record.takor}');" title="接单" class="operationbtn icon-edit"></a>
                    {#/if}
                    {#if ($T.record.status == 112 || $T.record.status == 113 || $T.record.status == 214)&&${sessionScope._auth['workOrderBack']}}
                    <a class="operationbtn icon-edit top-layer-min-done" title="退单" href="javascript:void(0);"
                       layer-form-id="workTuiForm" layer-title="退单" layer-url="${web}/work/order/info/tui?code={$T.record.code}&mid={$T.record.monitor}&reid={$T.record.takor}"></a>
                    {#/if}
                    {#if ($T.record.status == 211 || $T.record.status == 322 || $T.record.status == 214)&&${sessionScope._auth['workOrderFinish']}}
                    <a class="operationbtn icon-edit top-layer-min-done" title="处理完成" href="javascript:void(0);"
                       layer-form-id="workDoForm" layer-title="处理完成" layer-url="${web}/work/order/info/do?code={$T.record.code}&mid={$T.record.monitor}&reid={$T.record.takor}"></a>
                    {#/if}
        </td>
    </tr>
    {#/for}
</textarea>