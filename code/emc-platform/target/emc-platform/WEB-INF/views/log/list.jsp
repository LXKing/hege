<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../include.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>日志管理</title>
    <jsp:include page="../head.jsp"></jsp:include>
    <script src="${platform}/script/log/huak.sys.log.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="log-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">操作名称</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" name="optName" placeholder="请输入操作名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">操作模块名称</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" name="optKey" placeholder="请输入操作模块账号">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">操作类型</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <select name="userStatus" class="chosen-select form-control">
                                            <option value="">全部</option>
                                            <option value="0">增</option>
                                            <option value="1">删</option>
                                            <option value="2">改</option>
                                            <option value="3">查</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">创建开始时间</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <input id="cStartTime" name="cStartTime" class="laydate-icon form-control layer-date" placeholder="请输入开始时间">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">创建结束时间</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <input id="cEndTime" name="cEndTime" class="laydate-icon form-control layer-date" placeholder="请输入结束时间">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">
                            </div>
                            <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <button type="button" class="btn btn-sm btn-primary emc-search" bootstrap-table-id="log-table-list"> 搜索</button>
                                <button type="button" class="btn btn-sm btn-success emc-reset"> 重置</button>
                                <c:if test="${sessionScope._auth['logExport'] }">
                                	<button type="button" class="btn btn-sm btn-primary" onclick="exportLog()"> 导出Excel</button>
                                </c:if>
                            </div>
                        </div>
                    </form>
                    <div class="example">
                    	<table id="log-table-list"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>