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

<script src="${web}/script/huak.web.system.season.js"></script>


<div class="main-box">
    <form id="seasonSearch">
        <input type="hidden" name="_method" value="PATCH">
        <input type="hidden" name="pageNumber" value="1">
        <input type="hidden" name="orgId" value="${org.id}">
        <input type="hidden" name="comId" value="${company.id}">
    <div class="selectbg clearfix">
        <div class="sele-row clearfix row">
            <!--<div class="clearfix row">-->

        </div>
        <div class="sele-row clearfix row">
            <!--<div class="clearfix row">-->
            <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <label for="">名称</label>
                </div>
                <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                    <input class="inputs-lg" id="nameSearch" name="name" type="text" placeholder=" 请输入名称"/>
                </div>
            </div>
            <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                <div class="form-group">
                    <label class="control-label col-sm-2 col-xs-2 col-md-2 col-lg-2">时间查询</label>
                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                        <input id="start" name="sdate" class="laydate-icon form-control layer-date" placeholder="请输入开始时间">
                    </div>
                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                        <input id="end" name="edate" class="laydate-icon form-control layer-date" placeholder="请输入结束时间">
                    </div>
                </div>
            </div>
            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                <a href="javascript:void(0);" class="btns btnsfl btns-lookin" onclick="querySeasonConfig()">查询</a>
                <a href="javascript:void(0);" class="btns btnsfl btns-reset" onclick =reset()>重置</a>
            </div>

        </div>

    </div>
    </form>
    <div class="col-xs-12 btngroups   ">
    <c:if test="${sessionScope._auth['alarmAdd']}"></c:if>
        <a class="btns btnsfl btns-green top-layer-min" href="javascript:void(0);"  layer-form-id="seasonAddForm" layer-title="添加采暖季" layer-url="${web}/season/add">添加</a>

    <c:if test="${sessionScope._auth['alarmExport']}"></c:if>
        <a class="btns btnsfl btns-green export-emc" href="javascript:void(0);" export-url="${web}/alarm/config/export">导出</a>

    </div>


    <div class="col-xs-12 main-table no-padding">
        <table class="table table-striped table-bordered table-hover pgtable">
            <thead>
            <tr>
                <td width="4%">序号</td>
                <td width="12%">
                    <div class="text-left">名称</div>
                </td>
                <td width="10%">
                    <div class="text-left">开始时间</div>
                </td>
                <td width="18%">
                    <div class="text-left">结束时间</div>
                </td>
                
                <td width="10%">  <div>操作</div> </td>
            </tr>
            </thead>
            <tbody id="seasonBody">

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
<textarea id="tpl-season" style="display:none">
    {#foreach $T.list as record}
    <tr>
        <td>{$T.record$index+1}</td>
        <td>
            <div class="text-left">{$T.record.name}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.sdate}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.edate}</div>
        </td>
        <td>
            <div>
                <c:if test="${sessionScope._auth['alarmUpdate']}"></c:if>
                <a href="javascript:void(0);" title="修改" class="operationbtn icon-edit top-layer-min"
                    layer-form-id="seasonEditForm" layer-title="修改采暖" layer-url="${web}/season/edit/{$T.record.id}"></a>
                <c:if test="${sessionScope._auth['alarmDelete']}"></c:if>
                <a href="javascript:delSeasonConfig('{$T.record.id}');" title="删除" class="operationbtn icon-delete"></a>

            </div>
        </td>
    </tr>
    {#/for}
</textarea>