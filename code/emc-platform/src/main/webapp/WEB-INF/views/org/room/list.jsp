<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../include.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
	<META HTTP-EQUIV="Expires" CONTENT="0"> 
    <title>户管理</title>
    <jsp:include page="../../head.jsp"></jsp:include>
    <script src="${platform}/script/org/huak.org.room.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
    	<div class="org-tree" style="display: none;"></div>
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="room-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">所属公司</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <select disabled="disabled" id="comId" name="comId" class="form-control m-b" ></select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">所属机构</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="hidden" class="form-control" id="orgId" name="orgId">
                                        <input type="text" class="form-control" name="orgName" placeholder="请输入所属机构">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">所属小区</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <select id="communityId" onclick="banSelect();" name="communityId" class="form-control m-b" ></select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">所属楼座</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <select id="banId" onclick="cellSelect();" name="banId" class="form-control m-b" ></select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">所属单元</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <select id="cellId" name="cellId" class="form-control m-b" ></select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">户名称</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" id="roomName" name="roomName" placeholder="请输入户名称">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">
                            	<c:if test="${sessionScope._auth['roomInsert'] }">
                                	<button id="addButton" type="button" class="btn btn-sm btn-info top-layer-min" layer-url="${platform}/room/add" layer-title="新增户" layer-form-id="roomAddForm" ><i class="fa fa-plus"></i>添加</button>
                                </c:if>
                            </div>
                            <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <button type="button" class="btn btn-sm btn-primary emc-search" bootstrap-table-id="room-table-list"> 搜索</button>
                                <button type="button" onclick="resetSearch()" class="btn btn-sm btn-success"> 重置</button>
                                <c:if test="${sessionScope._auth['roomExport'] }">
                                	<button type="button" class="btn btn-sm btn-primary" onclick="exportRoom()"> 导出Excel</button>
                                </c:if>
                            	<input id="roomUpdate" type="hidden" value="${ sessionScope._auth['roomUpdate']}">
								<input id="roomDelete" type="hidden" value="${ sessionScope._auth['roomDelete']}">
                            </div>
                        </div>
                    </form>
                    <div class="example">
                    	<table id="room-table-list"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	$('#addButton').click(function(){
		var companyId = parent.$("[name='searchComp']").val();
		var baseUrl = "${platform}/room/add/";
		$('#addButton').attr('layer-url',baseUrl+companyId);
	});
	$('#comId').html('${com}');
	$('#comId').val(parent.$("[name='searchComp']").val());
	
	communitySelect();
	
	//搜索栏中小区下拉框
	function communitySelect(){
		$.get('${platform}/ban/communitySelectHtmlStr',{
			comId:$('#comId').val(),
			orgId:$('#orgId').val()
		},function(data){
			$('#communityId').html(data.html);
		},'json');
		banSelect();
	}
	
	//搜索栏中楼座下拉框
	function banSelect(){
		$.get('${platform}/cell/banSelectHtmlStr',{
			comId:$('#comId').val(),
			orgId:$('#orgId').val(),
			communityId:$('#communityId').val()
		},function(data){
			$('#banId').html(data.html);
		},'json');
		cellSelect();
	}
	
	function cellSelect(){
		$.get('${platform}/room/cellSelectHtmlStr',{
			comId:$('#comId').val(),
			orgId:$('#orgId').val(),
			communityId:$('#communityId').val(),
			banId:$('#banId').val()
		},function(data){
			$('#cellId').html(data.html);
		},'json');
	}
	
	function resetSearch(){
		$('#roomName').val('');
		$('#orgId').val('');
		$('input[name="orgName"]').val('');
		communitySelect();
	}
</script>
</body>
</html>