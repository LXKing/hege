<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <jsp:include page="../../../head.jsp"></jsp:include>
    <script src="${platform}/script/sys/huak.energy.project.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
    	<div class="org-tree" style="display: none;"></div>
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="energyProject-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="_method" value="PATCH">
						<!-- 查询条件 -->
                        <div class="row">
                            <div class="col-sm-5 col-xs-5 col-md-5 col-lg-5">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">所属组织</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <input type="hidden" class="form-control" id="orgId" name="orgId">
                                        <input type="text" class="form-control" name="orgName" placeholder="请输入所属机构">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5 col-xs-5 col-md-5 col-lg-5">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">所属采暖季</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <select class="form-control" id="seasonId" name="seasonId" placeholder="请选择采暖季"></select>
                                    </div>
                                </div>
                            </div>
                        </div>
						<!-- 查询条件下的按钮 -->
                        <div class="row">
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">
<%--                                 <c:if test="${sessionScope._auth['energyTypeAdd']}"> --%>
                                    <button id="addButton" type="button" class="btn btn-sm btn-info top-layer-min" layer-form-id="energyProjectAddForm" layer-title="添加能耗计划" layer-url="${platform}/energy/project/add">
                                        <i class="fa fa-plus"></i>添加
                                    </button>
<%--                                 </c:if> --%>
<%--                                 <input type="hidden" id="energyTypeUpdate" value="${sessionScope._auth['energyTypeUpdate']}"> --%>
<%--                                 <input type="hidden" id="energyTypeDelete" value="${sessionScope._auth['energyTypeDelete']}"> --%>

                            </div>
                            <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">

                                <button type="button" class="btn btn-sm btn-primary emc-search" bootstrap-table-id="energyProject-table-list">搜索</button>
                                <button type="button" class="btn btn-sm btn-success emc-reset"> 重置</button>
<%--                                 <c:if test="${sessionScope._auth['energyTypeExport']}"> --%>
                                <button type="button" class="btn btn-sm btn-primary excel-export-btn" export-url="${platform}/energy/project/export"> 导出Excel</button>
<%--                                 </c:if> --%>
                            </div>
                        </div>
                    </form>
                    <div class="example">
                        <table id="energyProject-table-list">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
var companyId = parent.$("[name='searchComp']").val();
var addurl = "${platform}/energy/project/add?comId="+companyId;
$('#addButton').attr('layer-url',addurl);

seasonSelect();

function seasonSelect(){
	$.get('${platform}/energy/project/getSeasonSelectHtml',{
		comId:companyId
	},function(data){
		$('#seasonId').html(data.html);
	},'json');
}

$('.emc-reset').click(function(){
	$('#orgId').val('');
	$('#seasonId').val('');
});
</script>
</body>
</html>