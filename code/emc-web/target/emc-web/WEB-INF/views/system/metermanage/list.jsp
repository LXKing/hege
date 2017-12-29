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
<link href="${web}/static/css/load.css" rel="stylesheet">
<script src="${web}/script/huak.web.meter.data.js"></script>
<input id="meterUpdate" VALUE="${sessionScope._auth['feedUpdate']}"  type="hidden"/>
<input id="meterDelete" VALUE="${sessionScope._auth['feedDelete']}"  type="hidden"/>
<input id="energyTypeJson" VALUE='${energyTypeJson}'  type="hidden"/>
<form id="meter-search-form">

    <div class="main-box">
        <div class="selectbg clearfix">
            <div class="sele-row clearfix row">
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3" >
                    <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <label for="">代码</label>
                    </div>
                    <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                        <input class="inputs-lg"  name="code" type="text" placeholder="请输入计量代码"/>
                    </div>
                </div>
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3" >
                    <div class="select-box col-xs-2 col-sm-2 col-md-2 col-lg-2">
                        <label for="">名称</label>
                    </div>
                    <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                        <input class="inputs-lg"  name="collectName" type="text" placeholder="请输入表名称"/>
                    </div>
                </div>
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3" >
                    <div class="select-box col-xs-2 col-sm-2 col-md-2 col-lg-2">
                        <label for="">采集点</label>
                    </div>
                    <div class="select-box col-xs-4 col-sm-4 col-md-4  col-lg-4">
                        <input class="inputs-lg"  name="tag" type="text" placeholder="请输入采集点"/>
                    </div>
                </div>
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="select-box col-xs-12 col-sm-4 col-md-2">
                        <label for="">单位</label>
                    </div>
                    <div class="select-box col-xs-12 col-sm-4 col-md-2">
                        <div class="select-box">
                            <div class="clearfix h-selectbox">
                                <div class="x-selectfree fl">
                                    <div class="x-sfbgbox">
                                        <div class="x-sfleft1 x-sfw1">
                                            <input type="text" value="请选择单位类型" readonly="readonly">
                                        </div>
                                        <div class="x-sfright1"></div>
                                    </div>
                                    <div class="x-sfoption1 select">
                                        <p value="1">热源</p>
                                        <p value="2">一次网</p>
                                        <p value="3">换热站</p>
                                        <p value="4">二次线</p>
                                        <p value="5">用户</p>
                                    </div>
                                    <input name="unitType" type="hidden" value="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="sele-row clearfix row">
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="select-box col-xs-3 col-sm-3 col-md-3">
                        <label for="">类型</label>
                    </div>
                    <div class="select-box col-xs-12 col-sm-4 col-md-2">
                        <div class="select-box">
                            <div class="clearfix h-selectbox">
                                <div class="x-selectfree fl">
                                    <div class="x-sfbgbox">
                                        <div class="x-sfleft1 x-sfw1">
                                            <input type="text" value="请选择能源类型" readonly="readonly">
                                        </div>
                                        <div class="x-sfright1"></div>
                                    </div>
                                    <div class="x-sfoption1 select">
                                        <c:forEach items="${energyTypes}" var="item">
                                            <p value="${item.id}">${item.nameZh}</p>
                                        </c:forEach>
                                    </div>
                                    <input  name="energyTypeId" type="hidden" value="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="select-box col-xs-12 col-sm-4 col-md-2">
                        <label for="">实虚表</label>
                    </div>
                    <div class="select-box col-xs-12 col-sm-4 col-md-2">
                        <div class="select-box">
                            <div class="clearfix h-selectbox">
                                <div class="x-selectfree fl">
                                    <div class="x-sfbgbox">
                                        <div class="x-sfleft1 x-sfw1">
                                            <input type="text" value="请选择实虚表" readonly="readonly">
                                        </div>
                                        <div class="x-sfright1"></div>
                                    </div>
                                    <div class="x-sfoption1 select">
                                        <p value="0">实表</p>
                                        <p value="1">虚表</p>
                                    </div>
                                    <input name="isreal" type="hidden" value="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="select-box col-xs-12 col-sm-4 col-md-2">
                        <label for="">总分表</label>
                    </div>
                    <div class="select-box col-xs-12 col-sm-4 col-md-2">
                        <div class="select-box">
                            <div class="clearfix h-selectbox">
                                <div class="x-selectfree fl">
                                    <div class="x-sfbgbox">
                                        <div class="x-sfleft1 x-sfw1">
                                            <input type="text" value="请选择总分表" readonly="readonly">
                                        </div>
                                        <div class="x-sfright1"></div>
                                    </div>
                                    <div class="x-sfoption1 select">
                                        <p value="0">总表</p>
                                        <p value="1">单位分表</p>
                                        <p value="2">系统总表</p>
                                    </div>
                                    <input type="hidden" name="istotal" value="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="select-box col-xs-12 col-sm-4 col-md-2">
                        <label for="">采集</label>
                    </div>
                    <div class="select-box col-xs-12 col-sm-4 col-md-2">
                        <div class="select-box">
                            <div class="clearfix h-selectbox">
                                <div class="x-selectfree fl">
                                    <div class="x-sfbgbox">
                                        <div class="x-sfleft1 x-sfw1">
                                            <input type="text" value="请选择采集类型" readonly="readonly">
                                        </div>
                                        <div class="x-sfright1"></div>
                                    </div>
                                    <div class="x-sfoption1 select">
                                        <p value="0">自动采集</p>
                                        <p value="1">手工</p>
                                    </div>
                                    <input type="hidden" name="isauto" value="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="sele-row clearfix row">
                <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <div class="select-box col-xs-3 col-sm-3 col-md-3">
                        <label for="">预存</label>
                    </div>
                    <div class="select-box col-xs-12 col-sm-4 col-md-2">
                        <div class="select-box">
                            <div class="clearfix h-selectbox">
                                <div class="x-selectfree fl">
                                    <div class="x-sfbgbox">
                                        <div class="x-sfleft1 x-sfw1">
                                            <input type="text" value="请选择预存类型" readonly="readonly">
                                        </div>
                                        <div class="x-sfright1"></div>
                                    </div>
                                    <div class="x-sfoption1 select">
                                        <p value="0">不是</p>
                                        <p value="1">是</p>
                                    </div>
                                    <input  type="hidden" name="isprestore" value="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="select-box col-xs-7 col-sm-7 col-md-7 col-lg-7">
                </div>
                <div class="select-box col-xs-2 col-sm-2 col-md-2 col-lg-2">
                    <a class="btns btnsfl btns-lookin" style="cursor: pointer;" onclick="queryMeter(1)">筛选</a>
                    <a class="btns btnsfl btns-reset" style="cursor: pointer;" onclick =resetMeter()>重置</a>
                </div>
            </div>
        </div>
        <div class="col-xs-12 btngroups   ">
            <a class="btns btnsfl btns-green" style="cursor: pointer" onclick="addMeterCollect()" >添加</a>
            <a class="btns btnsfl btns-green export" style="cursor: pointer;" export-url="${web}/meterData/export">导出Excel</a>
            <a  class="btns btnsfl btns-green" style="cursor: pointer" onclick="uploaderExcel()"> 批量导入</a>
        </div>
        <div class="col-xs-12 main-table no-padding">
            <table class="table table-striped table-bordered table-hover pgtable">
                <thead>
                <tr>
                    <td width="4%">序号</td>
                    <td width="4%">
                        <div class="text-center">代码</div>
                    </td>
                    <td width="6%">
                        <div class="text-center">出厂编号</div>
                    </td>
                    <td width="6%">
                        <div class="text-center">名称</div>
                    </td>
                    <td width="8%">
                        <div class="text-center">单位名称</div>
                    </td>
                    <td width="5%">
                        <div class="text-center">能源类型</div>
                    </td>
                    <td width="4%">
                        <div class="text-center">实虚表</div>
                    </td>
                    <td width="6%">
                        <div class="text-center">总表</div>
                    </td>
                    <td width="6%">
                        <div class="text-center">系数</div>
                    </td>
                    <%--<td width="6%">--%>
                        <%--<div class="text-center">单位类型</div>--%>
                    <%--</td>--%>
                    <%--<td width="6%">--%>
                        <%--<div class="text-center">采集</div>--%>
                    <%--</td>--%>
                    <td width="6%">
                        <div class="text-center">点表</div>
                    </td>
                    <td width="10%">
                        <div class="text-center">公式</div>
                    </td>
                    <td width="5%">
                        <div class="text-center">预存</div>
                    </td>
                    <%--<td width="7%">--%>
                        <%--<div class="text-center">删除标识</div>--%>
                    <%--</td>--%>
                    <td width="10%">
                        <div class="text-center">描述<i class="icon-sort"></i></div>
                    </td>
                    <td width="10%">
                        <div>操作</div>
                    </td>
                </tr>
                </thead>
                <tbody id="projectTbody">

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
</form>
<!-- 模板内容 -->
<textarea id="template" style="display:none">
    {#foreach $T.list as record}
    <tr>
        <td>{$T.record$index+1}</td>
        <td> {$T.record.code} </td>
        <td title="{$T.record.serialNo}">{formatValue($T.record.serialNo,8)} </td>
        <td title="{$T.record.name}"> {formatValue($T.record.name,4)} </td>
        <td title="{$T.record.unitname}">{formatValue($T.record.unitname,6)}</td>
        <td> {getTypeName($T.record.energyTypeId)} </td>
        <td> {changeValue($T.record.isreal,'real')}</td>
        <td> {changeValue($T.record.istotal,'total')} </td>
        <td>{$T.record.coef}</td>
        <%--<td>{changeValue($T.record.unitType,'unittype')}</td>--%>
        <%--<td>{changeValue($T.record.isauto,'auto')}</td>--%>
        <td title="{$T.record.tag}">{#if $T.record.isauto==0}{formatValue($T.record.tag,8)}{#else}手工填报{#/if}</td>
        <td title="{$T.record.formula}">{formatValue($T.record.formula,10)}</td>
        <td>{changeValue($T.record.isprestore,'prestore')}</td>
        <%--<td>{changeValue($T.record.isdelete,'delete')}</td>--%>
        <td title="{$T.record.depict}">{formatValue($T.record.depict,8)}</td>
        <td>
            <div>
                <%--if ($("#meterCollectUpdate").val()) {--%>
                <%--html += '<a title="编辑" class="btn btn-xs btn-info top-layer-min" layer-form-id="MeterCollectEditForm" layer-title="编辑计量器具" layer-url="' + _platform + '/meterCollect/edit/' + row.id + '"> <i class="fa fa-edit"></i></a>&nbsp;';--%>
                <%--}--%>
                <%--if ($("#meterCollectDelete").val()) {--%>
                <%--html += '<a title="删除" class="btn btn-xs btn-danger" onclick="deleteMeter(&quot;' + row.id + '&quot;)"><i class="fa fa-trash-o"></i></a>&nbsp;';--%>
                <%--}--%>
                <%--if ($("#meterCollectDelete").val()) {--%>
                <%--if (row.isreal == '0') {--%>
                <%--html += '<a title="换表" class="btn btn-xs btn-info " onclick="changeMeter(&quot;' + row.id + '&quot;)"> <i class="fa  fa-magic"></i></a>&nbsp;';--%>
                <%--//                            if(row.isprestore == '1' ){--%>
                <%--//                                html += '<a title="预存" class="btn btn-xs btn-info" onclick="prestoreMeter(&quot;' + row.id + '&quot;)"> <i class="fa fa-edit"></i></a>&nbsp;';--%>
                <%--//                            }--%>
                <%--}--%>
                <%--}--%>
                <a href="javascript:;" onclick="editMeter('{$T.record.id}')" class="operationbtn icon-edit" title="修改"></a>
                <a href="javascript:;" onclick="deleteMeter('{$T.record.id}')" class="operationbtn icon-delete" title="删除"></a>
                {#if $T.record.isreal == 0}
                    <a href="javascript:;" onclick="changeMeter('{$T.record.id}')"  class="operationbtn icon-edit" title="预存"></a>
                    <a href="javascript:;" onclick="prestoreMeter('{$T.record.id}')" class="operationbtn icon-edit" title="换表"></a>
                {#/if}


            </div>
        </td>
    </tr>
    {#/for}
</textarea>
<div  id="overlay"></div>