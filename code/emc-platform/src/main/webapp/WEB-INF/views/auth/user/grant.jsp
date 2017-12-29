<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2017/5/18
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>角色列表</title>
    <script type="application/javascript">
        $(function () {
            $(top.document).find('#grant-role-table-list').bootstrapTable({
                height: getHeight() + 30,//高度
                cache: false,//禁用 AJAX 数据缓存
                url: _platform + '/role/list',//获取数据的Servlet地址
                method: 'post',//使用POST请求到服务器获取数据
                contentType: "application/x-www-form-urlencoded",
                dataType: "json",
                idField: "ID",
                pagination: true,//是否分页
                pageSize: 10,//每页显示的记录数
                pageNumber: 1,//当前第几页
                pageList: [10, 30, 50],//记录数可选列表
                search: false,  //是否启用查询
                striped: true,//表格显示条纹
                //showColumns: false,//不显示隐藏列
                sidePagination: "server", //服务端请求
                //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                //设置为limit可以获取limit, offset, search, sort, order
                queryParamsType: "undefined",
                queryParams: function queryParams(params) {
                    var param = {
                        roleName: $(top.document).find('input[name="roleName"]').val(),
                        _method: "PATCH",
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize
                    };
                    return param;
                }, formatLoadingMessage: function () {
                    return "请稍等，正在加载中...";
                },
                onClickRow: function (row, $element, field) {
                    var $roles = $(top.document).find('#selectRoles');
                    var $a = $roles.find('a');
                    $a.removeClass('btn-danger').addClass('btn-primary');
                    $a.text(row.roleName);
                    $roles.find("#roleId").val(row.id);
                },
                responseHandler: function (res) {
                    return {
                        "rows": res.list.list,
                        "total": res.list.page.total
                    };
                },
                columns: [
                    {
                        field: 'id', title: 'ID', visible: false
                    },
                    /*{
                     checkbox: true
                     },*/
                    {
                        title: '序号',
                        field: 'sn',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return index + 1;
                        }
                    },
                    {
                        title: '角色名称',
                        field: 'roleName',
                        align: 'center'
                    },
                    {
                        title: '角色说明',
                        field: 'roleDes',
                        align: 'center'
                    },
                    {
                        title: '备注',
                        field: 'memo',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (value.length > 20) {
                                return '<span title="' + value + '">' + value.substr(0, 20) + '</span>';
                            }
                            return value;
                        }
                    }

                ]


            });

            //绑定查询事件
            $(top.document).find('#grantSearch').on('click', function () {
                $(top.document).find("#grant-role-table-list").bootstrapTable("refresh");
            });
            $(top.document).find('#grant-role-from').on("submit",function(){
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: _platform + '/user/grant',
                    data: $(top.document).find('#grant-role-from').serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                        } else {
                            top.layer.close(index);
                            top.layer.msg(result.msg);
                        }
                    }
                });
                return false;
            });

        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row float-e-margins">
        <div class="col-sm-12" id="selectRoles">
            <form class="form-horizontal" id="grant-role-from" role="form">
                <input type="hidden" name="userId" value="${user.id}">
                <input type="hidden" id="roleId" name="roleId" value="${role.id}">
                <c:if test="${role eq null}">
                    <a class="btn btn-danger btn-outline btn-sm" title="${role.roleName}">
                    没有分配角色
                    </a>
                </c:if>
                <c:if test="${role ne null}">
                    <a class="btn btn-success btn-outline btn-sm" title="${role.roleName}">
                    ${role.roleName}
                    </a>
                </c:if>

            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">

            <div class="row">
                <div class="col-lg-4">
                    <div class="form-group">
                        <label class="control-label col-lg-4">角色名称</label>

                        <div class="col-lg-8">
                            <input type="text" class="form-control" name="roleName">
                        </div>
                    </div>
                </div>
                <div class="col-lg-2" style="margin-bottom: 8px;">
                    <button type="button" class="btn btn-sm btn-primary" id="grantSearch"> 搜索</button>
                </div>
                <div class="col-lg-6">
                    <p class="text-warning" style="margin-top:8px;">（单击表格的行以选择要分配的角色）</p>
                </div>

            </div>

            <div class="example">
                <table id="grant-role-table-list">
                </table>
            </div>

        </div>

    </div>
</div>
</body>
</html>
