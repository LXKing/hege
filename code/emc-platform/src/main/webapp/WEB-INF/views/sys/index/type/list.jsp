<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <jsp:include page="../../../head.jsp"></jsp:include>
    <script src="${platform}/script/sys/huak.index.type.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="indexTypes-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="_method" value="PATCH">

                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">类型名称</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <input type="text" class="form-control" name="name" placeholder="请输入指标类型中文">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">能源类型</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <select name="energyType" class="chosen-select form-control">
                                            <option value="">请选择类型</option>
                                            <c:forEach items="${sysDic['energyType']}" var="type">
                                                <option value="${type.seq}">${type.des}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">单位类型</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <select name="unitType" class="chosen-select form-control">
                                            <option value="">请选择单位类型</option>
                                            <c:forEach items="${sysDic['orgType']}" var="type">
                                                <option value="${type.seq}">${type.des}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['indexTypeAdd']}">
                                    <button type="button" class="btn btn-sm btn-info top-layer-min" layer-form-id="indexTypeAddForm" layer-title="添加指标类型" layer-url="${platform}/index/type/add">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <input type="hidden" id="indexTypeUpdate" value="${sessionScope._auth['indexTypeUpdate']}">
                                <input type="hidden" id="indexTypeDelete" value="${sessionScope._auth['indexTypeDelete']}">

                            </div>
                            <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">

                                <button type="button" class="btn btn-sm btn-primary emc-search" bootstrap-table-id="indexType-table-list"> 搜索
                                </button>
                                <button type="button" class="btn btn-sm btn-success emc-reset"> 重置</button>
                                <%--<c:if test="${sessionScope._auth['indexTypeExport']}">--%>
                                <%--<button type="button" class="btn btn-sm btn-primary excel-export-btn" export-url="${platform}/index/type/export"> 导出Excel--%>
                                <%--</button>--%>
                                <%--</c:if>--%>
                            </div>
                        </div>
                    </form>
                    <div class="example">
                        <table id="indexType-table-list">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>