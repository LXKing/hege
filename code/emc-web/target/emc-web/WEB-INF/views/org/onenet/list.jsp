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
<html>


<head>
<script src="${web}/static/assets/js/bootstrap.min.js"></script>
<script src="${web}/static/js/My97DatePicker/WdatePicker.js"></script>
<script src="${web}/static/js/jquery/jquery.min.js"></script>
<script src="${web}/static/js/jquery.page.js"></script>
<script src="${web}/static/js/public.js"></script>
<script src="${web}/static/js/jquery.page.js"></script>
<script src="${web}/static/js/navscript.js"></script>
<script src="${web}/static/js/jquery-jtemplates.js"></script>

<script src="${web}/static/js/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${web}/static/js/ztree/jquery.ztree.excheck.js"></script>
<%--<script src="${web}/static/js/ztree/img/metroStyle.css"></script>--%>
<%--<script src="${web}/static/js/ztree/jquery.ztree.exedit.js"></script>--%>
<%--<script src="${web}/static/js/ztree/tree.drop.town.box.js"></script>--%>

<script src="${web}/static/js/tree.org.view.js"></script>
<script src="${web}/script/huak.web.system.oncenet.js"></script>
<%--<script src="${web}/script/system.js"></script>--%>
<%--<jsp:include page="../../head.jsp"></jsp:include>--%>
</head>
<body>
<form id="onecenet-formData">
    <input type="hidden" id="orgId" name="orgId" value=""/>
    <input type="hidden" id="comId" name="comId" value="${comId}"/>
    <input type="hidden" id="pageNo" name="pageNo" value="1">
    <input type="hidden" name="_method" value="PATCH">
    <div class="main-two-panel">
        <div class="panelleft">

            组织机构
            <ul id="treeDemo" class="ztree org-tree"></ul>
        </div>
        <div class="panelright"style="padding-left:180px">
            <div class="main-box">
                <div class="selectbg clearfix">
                    <div class="sele-row clearfix row">
                        <!--<div class="clearfix row">-->
                        <div class="select-box col-xs-12 col-sm-6 col-md-3">
                            <label >管网名称</label>
                            <input class="inputs-lg" id="netName" name="netName" type="text" placeholder="请输入名称" />
                        </div>
                        <div class="select-box col-xs-12 col-sm-6 col-md-3">
                            <label >管网代码</label>
                            <input class="inputs-lg" id="netCode" name="netCode" type="text" placeholder="请输入代码" />
                        </div>
                        <div class="select-box col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <div class="select-box col-xs-12 col-sm-4 col-md-2">
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
                                            <div class="x-sfoption1" id="energyType">
                                                <p value="1">水</p>
                                                <p value="2">电</p>
                                                <p value="3">气</p>
                                                <p value="4">热</p>
                                                <p value="5">煤</p>
                                                <p value="6">油</p>
                                            </div>
                                            <input id="energyType" name="energyType" type="hidden" value="" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a class="btns btnsfl btns-lookin" href="javascript:void(0)" onclick="query(1)">查询</a>
                            <a class="btns btnsfl btns-reset" href="javascript:void(0)" onclick =reset()>重置</a>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 main-table no-padding">
                    <table class="table table-striped table-bordered table-hover pgtable">
                        <thead>
                        <tr>
                            <td width="4%">序号</td>
                            <td width="15%">
                                <div class="text-left">管网名称</div>
                            </td>
                            <td width="10%">
                                <div class="text-left">管网代码</div>
                            </td>
                            <td width="10%">
                                <div class="text-left">管线类型</div>
                            </td>
                            <td width="10%">
                                <div class="text-left">管线长度</div>
                            </td>
                            <td width="10%">
                                <div class="text-left">小室数量</div>
                            </td>
                            <td width="10%">
                                <div class="text-left">管段数量</div>
                            </td>
                            <td width="10%">
                                <div class="text-left">供热类型</div>
                            </td>
                            <td width="10%">
                                <div class="text-left">输送介质</div>
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
        </div>
    </div>
</form>
</body>
</html>
<!-- 模板内容 -->
<textarea id="template" style="display:none">
    {#foreach $T.list as record}
    <tr>
        <td>{$T.record$index+1}</td>
        <td>
            <div class="text-left">{$T.record.netName}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.netCode}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.netTypeId}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.length}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.cellNum}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.partNum}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.heatType}</div>
        </td>
        <td>
            <div class="text-left">{$T.record.medium}</div>
        </td>

        <td>
            <div><a href="javascript:detailId(1);" class="operationbtn icon-edit"></a>
                <a href="javascript:detailId(1);"  class="operationbtn icon-delete"></a>
            </div>
        </td>
    </tr>
    {#/for}
</textarea>