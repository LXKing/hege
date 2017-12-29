<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2017/5/16
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper wrapper-content">
    <script>
        $(function () {
            $(top.document).find('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green'
            }).on("ifChecked", function () {
                $(this).parent().parent().parent().find(".search-checks").iCheck('check');
                $(this).parents('.two-menu').find('.btn-text-align').first().find(".search-checks").iCheck('check');
                $(this).parents('.one-menu').find('.btn-text-align').first().find(".search-checks").iCheck('check');
            });

            $(top.document).find('.search-checks').on("ifUnchecked", function () {
                $(this).parent().parent().parent().parent().find(".i-checks").iCheck('uncheck');
            });

            $(top.document).find('#roleAuthFrom').on("submit",function(){
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: _platform + '/role/grant',
                    data: $(top.document).find('#roleAuthFrom').serialize(),
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


    <form class="form-horizontal" id="roleAuthFrom" role="form">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title" style="border-style: solid solid none;border-width: 0px 0px 0;">
                        <h5>&nbsp;角色授权<span style="color: #ff0000">（${role.roleName}）</span></h5>
                        <input type="hidden" id="roleId" value="${role.id}" name="roleId">
                    </div>
                    <c:forEach var="oneMenu" items="${grantMenuBefore.menus}">
                        <div class="ibox-content m-b-sm one-menu">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="ibox float-e-margins">
                                        <div class="col-sm-6">
                                            <h5>&nbsp;${oneMenu.menuName}<span style="color: rgb(77, 255, 33)">（前台）</span></h5>
                                        </div>
                                        <div class="col-sm-6 btn-text-align">
                                            <c:forEach  var="func" items="${oneMenu.funcs}" varStatus="status">
                                                <input name="funcId"  class="i-checks <c:if test="${func.issearch eq 0}">search-checks</c:if>"
                                                <c:forEach var="ra" items="${auth}">
                                                <c:if test="${ra.funcId eq func.id}">
                                                       checked
                                                </c:if>
                                                </c:forEach>
                                                       value="${func.id}" type="checkbox">${func.funcName}
                                            </c:forEach>
                                        </div>
                                    </div>

                                    <c:forEach var="twoMenu" items="${oneMenu.menus}">
                                        <div class="ibox-content two-menu">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div class="ibox float-e-margins">
                                                        <div class="col-sm-6">
                                                            <h5>&nbsp;${twoMenu.menuName}<span style="color: rgb(77, 255, 33)">（前台）</span></h5>
                                                        </div>
                                                        <div class="col-sm-6 btn-text-align">
                                                            <c:forEach  var="func" items="${twoMenu.funcs}" varStatus="status">
                                                                <input  name="funcId"  class="i-checks <c:if test="${func.issearch eq 0}">search-checks</c:if>"
                                                                <c:forEach var="ra" items="${auth}">
                                                                <c:if test="${ra.funcId eq func.id}">
                                                                       checked
                                                                </c:if>
                                                                </c:forEach>
                                                                       value="${func.id}" type="checkbox">${func.funcName}
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <c:forEach  var="thrMenu" items="${twoMenu.menus}">
                                                        <div class="ibox-content thr-menu">
                                                            <div class="row">
                                                                <div class="col-sm-12">
                                                                    <div class="ibox float-e-margins">
                                                                        <div class="col-sm-6">
                                                                            <h5>&nbsp;${thrMenu.menuName}<span style="color: rgb(77, 255, 33)">（前台）</span></h5>
                                                                        </div>
                                                                        <div class="col-sm-6 btn-text-align">
                                                                            <c:forEach  var="func" items="${thrMenu.funcs}" varStatus="status">
                                                                                <input name="funcId"   class="i-checks <c:if test="${func.issearch eq 0}">search-checks</c:if>"
                                                                                <c:forEach var="ra" items="${auth}">
                                                                                <c:if test="${ra.funcId eq func.id}">
                                                                                       checked
                                                                                </c:if>
                                                                                </c:forEach>
                                                                                       value="${func.id}" type="checkbox">${func.funcName}
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <br>
                    <c:forEach var="oneMenu" items="${grantMenuAfter.menus}">
                        <div class="ibox-content m-b-sm one-menu">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="ibox float-e-margins">
                                        <div class="col-sm-6">
                                            <h5>&nbsp;${oneMenu.menuName}<span style="color: rgb(255, 156, 29)">（后台）</span></h5>
                                        </div>
                                        <div class="col-sm-6 btn-text-align">
                                            <c:forEach  var="func" items="${oneMenu.funcs}" varStatus="status">
                                                <input name="funcId"   class="i-checks <c:if test="${func.issearch eq 0}">search-checks</c:if>"
                                                <c:forEach var="ra" items="${auth}">
                                                <c:if test="${ra.funcId eq func.id}">
                                                       checked
                                                </c:if>
                                                </c:forEach>
                                                       value="${func.id}" type="checkbox">${func.funcName}
                                            </c:forEach>
                                        </div>
                                    </div>

                                    <c:forEach var="twoMenu" items="${oneMenu.menus}">
                                        <div class="ibox-content two-menu">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div class="ibox float-e-margins">
                                                        <div class="col-sm-6">
                                                            <h5>&nbsp;${twoMenu.menuName}<span style="color: rgb(255, 156, 29)">（后台）</span></h5>
                                                        </div>
                                                        <div class="col-sm-6 btn-text-align">
                                                            <c:forEach  var="func" items="${twoMenu.funcs}" varStatus="status">
                                                                <input name="funcId"   class="i-checks <c:if test="${func.issearch eq 0}">search-checks</c:if>"
                                                                <c:forEach var="ra" items="${auth}">
                                                                <c:if test="${ra.funcId eq func.id}">
                                                                       checked
                                                                </c:if>
                                                                </c:forEach>
                                                                       value="${func.id}" type="checkbox">${func.funcName}
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <c:forEach  var="thrMenu" items="${twoMenu.menus}">
                                                        <div class="ibox-content thr-menu">
                                                            <div class="row">
                                                                <div class="col-sm-12">
                                                                    <div class="ibox float-e-margins">
                                                                        <div class="col-sm-6">
                                                                            <h5>&nbsp;${thrMenu.menuName}<span style="color: rgb(255, 156, 29)">（后台）</span></h5>
                                                                        </div>
                                                                        <div class="col-sm-6 btn-text-align">
                                                                            <c:forEach  var="func" items="${thrMenu.funcs}" varStatus="status">
                                                                                <input name="funcId"   class="i-checks <c:if test="${func.issearch eq 0}">search-checks</c:if>"
                                                                                <c:forEach var="ra" items="${auth}">
                                                                                <c:if test="${ra.funcId eq func.id}">
                                                                                       checked
                                                                                </c:if>
                                                                                </c:forEach>
                                                                                       value="${func.id}" type="checkbox">${func.funcName}
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </form>
</div>