<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <jsp:include page="../../head.jsp"></jsp:include>
    <script src="${platform}/script/auth/huak.auth.func.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div>
                        <ul id="menuTreeFunc" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-9 col-xs-9 col-md-9 col-lg-9">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="funcs-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="menuId" value="1">

                        <div class="row">
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['funcAdd']}">
                                <button type="button" class="btn btn-sm btn-info" onclick="addFunc()"><i class="fa fa-plus"></i>添加</button>
                                </c:if>
                                <c:if test="${sessionScope._auth['funcCache']}">
                                    <button type="button" class="btn btn-sm btn-info" onclick="cache()"><i class="fa fa-spinner"></i>缓存</button>
                                </c:if>
                                <input type="hidden" id="funcUpdate" value="${sessionScope._auth['funcUpdate']}">
                                <input type="hidden" id="funcDelete" value="${sessionScope._auth['funcDelete']}">
                            </div>
                        </div>
                    </form>
                    <div class="example">
                        <table id="func-table-list"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>