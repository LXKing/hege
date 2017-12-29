<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../include.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <jsp:include page="../../head.jsp"></jsp:include>
    <script type="application/javascript">
        function search(){
            $table.bootstrapTable('refresh');
        }
    </script>
    <script src="${platform}/script/sys/huak.sys.meter.list.js"></script>
</head>
<body class="gray-bg">
<input id="meterCollectUpdate" VALUE="${sessionScope._auth['meterCollectUpdate']}"  type="hidden"/>
<input id="meterCollectDelete" VALUE="${sessionScope._auth['meterCollectDelete']}"  type="hidden"/>
<div class="wrapper wrapper-content animated fadeInRight ">
    <div class="row">

        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="meter-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">
                        <input type="hidden" id="orgId" name="orgId" value="">
                        <input type="hidden" id="comId"  name="comId" value="">
                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">计量代码</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <input type="text" class="form-control" id="code" name="code" placeholder="请输入代码">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">名称</label>
                                        <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入名称">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">能源类型</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <select name="energyTypeId" id="energyTypeId" class="chosen-select form-control">
                                            <option value="">请选择类型</option>
                                            <c:forEach items="${sysDic['energyType']}" var="type">
                                                <option value="${type.seq}">${type.des}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">单位类型</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <select name="unitType" id="unitType" class="chosen-select form-control">
                                            <option value="">请选择类型</option>
                                            <c:forEach items="${sysDic['orgType']}" var="type">
                                                <option value="${type.seq}">${type.des}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>


                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">实虚表</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <select name="isreal" id="isreal" class="chosen-select form-control">
                                            <option value="">请选择类型</option>
                                            <option value="0">实表</option>
                                            <option value="1">虚表</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">总表</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <select name="istotal" id="istotal" class="chosen-select form-control">
                                            <option value="">请选择类型</option>
                                            <option value="0">总表</option>
                                            <option value="1">单位总表</option>
                                            <option value="2">系统总表</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            </div>
                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">自动采集</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <select name="isauto" id="isauto" class="chosen-select form-control">
                                            <option value="">请选择类型</option>
                                            <option value="0">自动采集</option>
                                            <option value="1">手工</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                                <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">采集点</label>
                                        <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                            <input type="text" class="form-control" id="tag" name="tag" placeholder="请输入点表">
                                        </div>
                                    </div>
                                </div>

                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">预存</label>
                                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                        <select name="isprestore" id="isprestore" class="chosen-select form-control">
                                            <option value="">请选择类型</option>
                                            <option value="0">不是</option>
                                            <option value="1">是</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">
                                    <c:if test="${sessionScope._auth['meterCollectAdd']}">
                                        <button type="button" onclick="addMeterCollect()"  class="btn btn-sm btn-info ">
                                            <i class="fa fa-plus"></i>添加
                                        </button>
                                    </c:if>
                                    <!--<button type="button" class="btn btn-sm btn-info" onclick="editRole()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>

                                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteRoles()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>

                                    <button type="button" class="btn btn-sm btn-warning" onclick="roleAuthPage()">
                                        <i class="fa fa-wrench"></i>角色授权
                                    </button>-->
                                </div>
                                <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">

                                    <button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索
                                    </button>
                                    <button type="button" class="btn btn-sm btn-success emc-reset"> 重置</button>
                                    <button type="button" class="btn btn-sm btn-primary excel-export-btn" export-url="${platform}/meterCollect/export"> 导出Excel </button>
                                    <button type="button" class="btn btn-sm btn-primary" onclick="uploaderExcel()"> 批量导入</button>

                                </div>
                                </div>

                    </form>

                    <div class="example">
                        <table id="meter-table-list">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--<div id="layer-div" style="display: none"></div>

<%--jtemplement 模板--%>
<textarea id="tpl-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.role_id}" name="input[]">
        </td>
        <td>{$T.item.role_name}</td>
        <td>{$T.item.role_des}</td>
        <td title="{$T.item.memo}">{formatText($T.item.memo,10)}</td>
        <td>
            <a class="btn btn-white btn-xs btn-bitbucket" title="查看">
                <i class="fa fa-file-text-o"></i>
            </a>
                <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="deleteRole('{$T.item.role_id}')">
                    <i class="fa fa-trash-o"></i>
                </a>
        </td>
    </tr>
    {#/for}
</textarea>-->
</body>
</html>