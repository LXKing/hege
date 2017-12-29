<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="../../head.jsp"></jsp:include>

    <script src="${platform}/script/org/huak.org.node.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <input id="nodeUpdateAuth" VALUE="${sessionScope._auth['nodeUpdate']}"  type="hidden"/>
    <input id="nodeDeleteAuth" VALUE="${sessionScope._auth['nodeDelete']}"  type="hidden"/>
    <div class="row">
        <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
            <div class="ibox float-e-margins" >
                    <div class="ibox-content " >组织机构
                        <div class="org-tree" ></div>
                    </div>
            </div>
        </div>

        <div class="col-sm-9 col-xs-9 col-md-9 col-lg-9">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="station-search-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">
                        <input type="hidden" name="comId" id="comId" >
                        <input type="hidden" name="status" id="status" value="0">
                        <input type="hidden" name="orgId" id="orgId" >
                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">站名称</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" id="stationName" name="stationName" placeholder="请输入站名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">站编号</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" id="stationCode" name="stationCode" placeholder="请输入创建者">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">管理类型</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <select id="manageTypeId" name="manageTypeId" class="chosen-select form-control"  >
                                            <option value="">请选择管理类型</option>
                                            <c:forEach items="${sysDic['managetype']}" var="type">
                                                <option  value="${type.seq}">${type.des}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                       </div>
                        <div class="row">
                            <c:if test = "${sessionScope._auth['nodeAdd']}">
                                <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">
                                    <button type="button"  onclick="addStation()" class="btn btn-sm btn-info "><i class="fa fa-plus"></i>添加</button>
                                </div>
                            </c:if>

                            <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索</button>
                                <button type="button" class="btn btn-sm btn-success emc-reset"> 重置</button>
                                <button type="button" class="btn btn-sm btn-primary excel-export-btn" export-url="${platform}/station/export"> 导出Excel
                                </button>
                            </div>
                        </div>
                    </form>
                    <div class="example">
                        <table id="station-table-list"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

