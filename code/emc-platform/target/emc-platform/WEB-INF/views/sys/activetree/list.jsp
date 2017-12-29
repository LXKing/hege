<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <jsp:include page="../../head.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 个人资料</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <script type="application/javascript" src="${platform}/script/sys/huak.sys.active.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row animated fadeInRight">
        <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>行政区划信息</h5>
                </div>
                <div id="jstree1" class="ibox-content">
                    <ul id="activeTree" class="ztree"></ul>
                </div>
            </div>
        </div>
        <%--<input id="menuAddAuth" VALUE="${sessionScope._auth['menuAdd']}" type="hidden"/>--%>
        <%--<input id="menuUpdateAuth" VALUE="${sessionScope._auth['menuUpdate']}"  type="hidden"/>--%>
        <%--<input id="menuDeleteAuth" VALUE="${sessionScope._auth['menuDelete']}"  type="hidden"/>--%>
        <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>行政区划详情</h5>
                </div>
                <div class="ibox-content" style="height: 100%">
                    <div class="row">
                        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
                            <form class="form-horizontal" id="addForm" role="form">
                                <input name="id"  type="hidden"/>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">行政名称：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input id="admName" readonly class="form-control" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">行政代码：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input id="admCode" readonly class="form-control" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">级别：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input id="admLevel" readonly class="form-control" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">城乡区划类别：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input id="admType" readonly class="form-control" type="text">
                                        </div>
                                    </div>
                                </div><div class="form-group">
                                <div class="td">
                                    <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">经度：</label>
                                    <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                        <input id="lng" readonly class="form-control" type="text">
                                    </div>
                                </div>
                            </div>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">纬度：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input id="lat" readonly class="form-control" type="text">
                                        </div>
                                    </div>
                                </div>

                                <%--<div class="form-group">--%>
                                    <%--<div class="td">--%>
                                        <%--<label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">机构简称：</label>--%>
                                        <%--<div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">--%>
                                            <%--<input  class="form-control" id="shortName" readonly  type="text">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<div class="td">--%>
                                        <%--<label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">机构类型：</label>--%>
                                        <%--<div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">--%>
                                            <%--<input  class="form-control" id="typeId" readonly  type="text">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<div class="td">--%>
                                        <%--<label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">备注：</label>--%>
                                        <%--<div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">--%>
                                            <%--<input id="memo"  readonly class="form-control" type="text">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<div class="td">--%>
                                        <%--<label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">序号：</label>--%>
                                        <%--<div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">--%>
                                            <%--<input id="seq"  readonly class="form-control" type="text">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="orgtree-layer-div" style="display: none"></div>
</body>
</html>
