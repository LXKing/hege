<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>用户管理</title>
    <jsp:include page="../../head.jsp"></jsp:include>
    <script src="${platform}/script/auth/huak.auth.user.list.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
		<!-- 组织机构树 -->
<!--         <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3"> -->
<!--             <div class="ibox float-e-margins"> -->
<!--                 <div class="ibox-content"> -->
<!--                     <div> -->
<!--                         <ul id="user-org-tree" class="org-tree"></ul> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->

        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="user-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">用户名称</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <input type="text" class="form-control" name="userName" placeholder="请输入用户名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">用户账号</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <input type="text" class="form-control" name="userAccount" placeholder="请输入用户账号">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
<!--                                     <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">状态</label> -->
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <select name="userStatus" class="chosen-select form-control">
                                            <option value="">全部</option>
                                            <option value="0">启用</option>
                                            <option value="1">禁用</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">创建者</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <input type="text" class="form-control" name="creator" placeholder="请输入创建者">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                <div class="form-group">
                                    <label class="control-label col-sm-2 col-xs-2 col-md-2 col-lg-2">创建时间</label>
                                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                        <input id="cStartTime" name="cStartTime" class="laydate-icon form-control layer-date" placeholder="请输入开始时间">
                                    </div>
                                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                        <input id="cEndTime" name="cEndTime" class="laydate-icon form-control layer-date" placeholder="请输入结束时间">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">直属机构</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <input type="text" class="form-control" name="orgName" placeholder="请输入直属机构">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                <div class="form-group">
                                    <label class="control-label col-sm-2 col-xs-2 col-md-2 col-lg-2">登录时间</label>
                                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                        <input id="lStartTime" name="lStartTime" class="laydate-icon form-control layer-date" placeholder="请输入开始时间">
                                    </div>
                                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                        <input id="lEndTime" name="lEndTime" class="laydate-icon form-control layer-date" placeholder="请输入结束时间">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">

                                <button type="button" class="btn btn-sm btn-info top-layer-min" layer-url="${platform}/user/add" layer-title="新增用户" layer-form-id="userAddForm" ><i class="fa fa-plus"></i>添加</button>
                                <button type="button" class="btn btn-sm btn-danger " onclick="disableUser()"><i class="fa fa-unlock-alt"></i>禁用</button>
                                <button type="button" class="btn btn-sm btn-danger " onclick="enableUser()"><i class="fa fa-unlock"></i>启用</button>
                                <button type="button" class="btn btn-sm btn-danger" onclick="deleteUsers()"><i class="fa fa-trash-o"></i>删除</button>
                                <button type="button" class="btn btn-sm btn-warning" onclick="resetPassword()"><i class="fa fa-key"></i>重置密码</button>
                            </div>
                            <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">

                                <button type="button" class="btn btn-sm btn-primary emc-search" bootstrap-table-id="user-table-list"> 搜索</button>
                                <button type="button" class="btn btn-sm btn-success emc-reset"> 重置</button>
                                <button type="button" class="btn btn-sm btn-primary" onclick="exportUser()"> 导出Excel</button>

                            </div>
                        </div>
                    </form>
                    <div class="example">
                    	<table id="user-table-list"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>