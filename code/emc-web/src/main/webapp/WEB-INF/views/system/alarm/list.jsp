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

<script src="${web}/script/huak.web.system.alarm.config.js"></script>


<div class="main-box">
    <form id="alarmConfigSearch">
        <input type="hidden" name="_method" value="PATCH">
        <input type="hidden" name="pageNumber" value="1">
        <input type="hidden" name="orgId" value="${org.id}">
        <input type="hidden" name="comId" value="${company.id}">
    <div class="selectbg clearfix">
        <div class="sele-row clearfix row">
            <!--<div class="clearfix row">-->

            <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <label for="">单位类型</label>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                    <div class="select-box">
                        <div class="clearfix h-selectbox">
                            <div class="x-selectfree fl">
                                <div class="x-sfbgbox1">
                                    <div class="x-sfleft1 x-sfw1">
                                        <input type="text" value="请选择单位类型" readonly="readonly">
                                    </div>
                                    <div class="x-sfright1"></div>
                                </div>
                                <div class="x-sfoption1" id="unit_type">
                                    <c:forEach items="${sysDic['orgType']}" var="type">
                                        <p value="${type.seq}">${type.des}</p>
                                    </c:forEach>
                                </div>
                                <input type="hidden" id="unitTypeSearch" name="unitType" value="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <label for="">单位名称</label>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                    <input class="inputs-lg" id="unitNameSearch" name="unitName" type="text" placeholder=" 请输入单位名称"/>
                </div>
            </div>
            <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <label for="">报警描述</label>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                    <input class="inputs-lg" id="alarmNameSearch" name="alarmName" type="text" placeholder=" 请输入报警描述"/>
                </div>
            </div>

        </div>
        <div class="sele-row clearfix row">
            <!--<div class="clearfix row">-->
            <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <label for="">点表</label>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                    <input class="inputs-lg" id="tagSearch" name="tag" type="text" placeholder=" 请输入点表"/>
                </div>
            </div>
            <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <label for="">报警类型</label>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                    <div class="select-box">
                        <div class="clearfix h-selectbox">
                            <div class="x-selectfree fl" id="alarm_type">
                                <div class="x-sfbgbox1">
                                    <div class="x-sfleft1 x-sfw1">
                                        <input type="text" value="请选择报警类型" readonly="readonly">
                                    </div>
                                    <div class="x-sfright1"></div>
                                </div>
                                <div class="x-sfoption1" >
                                    <p value="">请选择报警类型</p>
                                    <c:forEach items="${sysDic['alarmType']}" var="type">
                                        <p value="${type.seq}">${type.des}</p>
                                    </c:forEach>
                                </div>
                                <input type="hidden" id="alarmTypeSearch" name="alarmType" value="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <label for="">报警等级</label>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                    <div class="select-box">
                        <div class="clearfix h-selectbox">
                            <div class="x-selectfree fl" id="alarm_level">
                                <div class="x-sfbgbox1">
                                    <div class="x-sfleft1 x-sfw1">
                                        <input type="text" value="请选择报警等级" readonly="readonly">
                                    </div>
                                    <div class="x-sfright1"></div>
                                </div>
                                <div class="x-sfoption1" >
                                    <p value="">请选择报警等级</p>
                                    <c:forEach items="${sysDic['alarmLevel']}" var="type">
                                        <p value="${type.seq}">${type.des}</p>
                                    </c:forEach>
                                </div>
                                <input type="hidden" id="alarmLevelSearch" name="alarmLevel" value="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <a href="javascript:void(0);" class="btns btnsfl btns-lookin" onclick="queryAlarmConfig()">查询</a>
                <a href="javascript:void(0);" class="btns btnsfl btns-reset" onclick =reset()>重置</a>
            </div>

        </div>

    </div>
    </form>
    <div class="col-xs-12 btngroups   ">
    <c:if test="${sessionScope._auth['alarmAdd']}">
        <a class="btns btnsfl btns-green top-layer-min" href="javascript:void(0);"  layer-form-id="alarmConfigAddForm" layer-title="添加报警配置" layer-url="${web}/alarm/config/add">添加</a>
    </c:if>
    <c:if test="${sessionScope._auth['alarmExport']}">
        <a class="btns btnsfl btns-green export-emc" href="javascript:void(0);" export-url="${web}/alarm/config/export">导出</a>
    </c:if>
    <c:if test="${sessionScope._auth['alarmImport']}">
        <a class="btns btnsfl btns-green" href="javascript:void(0);" onclick="uploaderExcel()">导入</a>
    </c:if>
    </div>


    <div class="col-xs-12 main-table no-padding">
        <table class="table table-striped table-bordered table-hover pgtable">
            <thead>
            <tr>
                <td width="4%">序号</td>
                <td width="12%">
                    <div class="text-left">单位名称</div>
                </td>
                <td width="10%">
                    <div class="text-left">点表</div>
                </td>
                <td width="18%">
                    <div class="text-left">报警描述</div>
                </td>
                <td width="6%">
                    <div class="text-left">报警类型</div>
                </td>
                <td width="6%">
                    <div class="text-left">报警等级</div>
                </td>
                <td width="6%">
                    <div class="text-left">报警模式</div>
                </td>
                <td width="6%">
                    <div class="text-left">报警阈值</div>
                </td>
                <td width="6%">
                    <div class="text-left">是否启用</div>
                </td>
                <td width="10%">  <div>操作</div> </td>
            </tr>
            </thead>
            <tbody id="alarmConfigBody">

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
<textarea id="tpl-alarmConfig" style="display:none">
    {#foreach $T.list as record}
    <tr>
        <td>{$T.record$index+1}</td>
        <td>
            <div class="text-left">{$T.record.unitName}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.tag}</div>
        </td>
        <td>
            <div class="text-left" title="{$T.record.alarmName}">{fromatStr($T.record.alarmName,10)}</div>
        </td>
        <td>
            <div class="text-left">
                {#if $T.record.alarmType==0}开关类型{#elseif $T.record.alarmType==1}模拟类型{#else}{$T.record.alarmType}{#/if}
            </div>
        </td>
        <td>
            <div class="text-left">
                {#if $T.record.alarmLevel==0}一级{#elseif $T.record.alarmLevel==1}二级{#elseif $T.record.alarmLevel==2}三级{#elseif $T.record.alarmLevel==3}四级{#else}{$T.record.model}{#/if}
            </div>
        </td>
        <td>
            <div class="text-left">
                {#if $T.record.model==0}低低{#elseif $T.record.model==1}低{#elseif $T.record.model==2}高{#elseif $T.record.model==3}高高{#else}{$T.record.model}{#/if}
            </div>
        </td>
        <td>
            <div class="text-left">{$T.record.num}</div>
        </td>
        <td>
            <div class="text-left">{#if $T.record.isenable==0}启用{#elseif $T.record.isenable==1}停用{#else}{$T.record.isenable}{#/if}</div>
        </td>
        <td>
            <div>
                <c:if test="${sessionScope._auth['alarmUpdate']}">
                <a href="javascript:void(0);" title="修改" class="operationbtn icon-edit top-layer-min"
                    layer-form-id="alarmConfigEditForm" layer-title="修改报警配置" layer-url="${web}/alarm/config/edit/{$T.record.id}"></a>
                </c:if>
                <c:if test="${sessionScope._auth['alarmDelete']}">
                <a href="javascript:delAlarmConfig('{$T.record.id}');" title="删除" class="operationbtn icon-delete"></a>
                </c:if>
            </div>
        </td>
    </tr>
    {#/for}
</textarea>