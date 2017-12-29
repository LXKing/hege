<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <jsp:include page="../../head.jsp"></jsp:include>
    <script src="${platform}/script/org/huak.org.company.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="companys-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-3 col-xs-3 col-md-3 col-lg-3">公司名称</label>
                                    <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                        <input type="text" class="form-control" name="cname" placeholder="请输入公司名称">
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['companyAdd']}">
                                    <button type="button" class="btn btn-sm btn-info top-layer-min" layer-form-id="companyAddForm" layer-title="添加公司" layer-url="${platform}/company/add">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                                <input type="hidden" id="companyUpdate" value="${sessionScope._auth['companyUpdate']}">
                                <input type="hidden" id="companyDelete" value="${sessionScope._auth['companyDelete']}">

                            </div>
                            <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <button type="button" class="btn btn-sm btn-primary emc-search" bootstrap-table-id="company-table-list"> 搜索
                                </button>
                                <button type="button" class="btn btn-sm btn-success emc-reset"> 重置</button>
                                <c:if test="${sessionScope._auth['companyExport']}">
                                <button type="button" class="btn btn-sm btn-primary excel-export-btn" export-url="${platform}/company/export"> 导出Excel
                                </button>
                                </c:if>

                            </div>
                        </div>
                    </form>
                    <div class="example">
                        <table id="company-table-list">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>