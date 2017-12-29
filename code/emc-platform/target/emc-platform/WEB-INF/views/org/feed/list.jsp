<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <jsp:include page="../../head.jsp"></jsp:include>
    <script src="${platform}/script/org/huak.org.feed.list.js"></script>
</head>
<body class="gray-bg">
<input id="feedUpdateAuth" VALUE="${sessionScope._auth['feedUpdate']}"  type="hidden"/>
<input id="feedDeleteAuth" VALUE="${sessionScope._auth['feedDelete']}"  type="hidden"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
            <div class="ibox float-e-margins">
                <div class="ibox-content">组织机构
                    <div id="treeDemo" class="org-tree">  </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-xs-9 col-md-9 col-lg-9">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="feed-searchform" role="form" class="form-horizontal m-t">
                        <input type="hidden" id="orgId" name="orgId" value="">
                        <input type="hidden"  name="status" value="0">
                        <input type="hidden" id="comId"  name="comId" value="">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">
                        <div class="row">
                            <div class="row2">
                                <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label  class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">热源名称</label>
                                        <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                            <input type="text" id="feedName" class="form-control" name="feedName" placeholder="请输入热源名称">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label  class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">热源编号</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" name="feedCode" placeholder="请输入热源编号">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">热源性质</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <select id="feedType" name="feedType" class="chosen-select form-control" >
                                            <option value="">请选择</option>
                                            <c:forEach items="${sysDic['natureheat']}" var="types">
                                                <option  value="${types.seq}">${types.des}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row1">
                                <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label  class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">供热类型</label>
                                        <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                            <select id="heatType" name="heatType" class="chosen-select form-control"  >
                                                <option value="">请选择</option>
                                                <c:forEach items="${sysDic['supportheattype']}" var="type">
                                                    <option value="${type.seq}">${type.des}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                        </div>
                        <div class="row2">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label  class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">装机容量</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" name="installCapacity" placeholder="请输入热源性质">
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                            <div class="form-group">
                                <label  class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">供热能力</label>
                                <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                    <input type="text" class="form-control" name="heatCapacity" placeholder="请输入创建者">
                                </div>
                            </div>
                        </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">
                                <c:if test="${sessionScope._auth['feedAdd']}">
                                    <button type="button" class="btn btn-sm btn-info "  onclick="addFeed()">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
                                </c:if>
                            </div>
                            <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索
                                </button>
                                <button type="reset" class="btn btn-sm btn-success" onclick="resetFeedSearch()"> 重置</button>
                                <button type="button" class="btn btn-sm btn-primary excel-export-btn" export-url="${platform}/feed/export"> 导出Excel
                                </button>

                            </div>
                        </div>
                    </form>
                    <div class="example">
                        <table id="feed-table-list">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>