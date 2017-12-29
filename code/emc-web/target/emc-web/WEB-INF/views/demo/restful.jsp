<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>Restful Test</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<div
        style="width:800px;margin-top:10px;margin-left: auto;margin-right: auto;text-align: center;">
    <h2>Restful Test</h2>
</div>
<div style="width:800px;margin-left: auto;margin-right: auto;">
    <fieldset class="uk-form">
        <legend>基于Restful架构风格的资源请求测试</legend>
        <button class="uk-button uk-button-primary uk-button-large" id="btnGet">获取人员GET</button>
        <button class="uk-button uk-button-primary uk-button-large" id="btnAdd">添加人员POST</button>
        <button class="uk-button uk-button-primary uk-button-large" id="btnUpdate">更新人员PUT</button>
        <button class="uk-button uk-button-danger uk-button-large" id="btnDel">删除人员DELETE</button>
        <button class="uk-button uk-button-primary uk-button-large" id="btnList">查询列表PATCH</button>
    </fieldset>
</div>

<script type="text/javascript" src="<%=basePath%>static/jquery3/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
    (function (window, $) {

        var dekota = {

            url: '',

            init: function () {
                dekota.url = '<%=basePath%>';
                console.info("初始化成功");
                $("#btnGet").click(dekota.getPerson);
                $("#btnAdd").click(dekota.addPerson);
                $("#btnDel").click(dekota.delPerson);
                $("#btnUpdate").click(dekota.updatePerson);
                $("#btnList").click(dekota.listPerson);
            },
            getPerson: function () {
                $.ajax({
                    url: dekota.url + 'restful/person/101/',
                    type: 'GET',
                    dataType: 'json'
                }).done(function (data, status, xhr) {
                    console.info("获取人员信息成功");
                }).fail(function (xhr, status, error) {
                    console.info("请求失败");
                });
            },
            addPerson: function () {
                $.ajax({
                    url: dekota.url + 'restful/person',
                    type: 'POST',
                    dataType: 'json',
                    data: {id: 1, name: '张三', sex: '男', age: 23}
                }).done(function (data, status, xhr) {
                    console.info("成功");
                }).fail(function (xhr, status, error) {
                    console.info("请求失败");
                });
            },
            delPerson: function () {
                $.ajax({
                    url: dekota.url + 'restful/person/109',
                    type: 'DELETE',
                    dataType: 'json'
                }).done(function (data, status, xhr) {
                    console.info("成功");
                }).fail(function (xhr, status, error) {
                    console.info("请求失败");
                });
            },
            updatePerson: function () {
                $.ajax({
                    url: dekota.url + 'restful/person',
                    type: 'POST',//注意在传参数时，加：_method:'PUT'　将对应后台的PUT请求方法
                    dataType: 'json',
                    data: {_method: 'PUT', id: 221, name: '王五', sex: '男', age: 23}
                }).done(function (data, status, xhr) {
                    console.info("成功");
                }).fail(function (xhr, status, error) {
                    console.info("请求失败");
                });
            },
            listPerson: function () {
                $.ajax({
                    url: dekota.url + 'restful/person',
                    type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
                    dataType: 'json',
                    data: {_method: 'PATCH', name: '张三'}
                }).done(function (data, status, xhr) {
                    console.info("成功");
                }).fail(function (xhr, status, error) {
                    console.info("请求失败");
                });
            }
        };
        window.dekota = (window.dekota) ? window.dekota : dekota;
        $(function () {
            dekota.init();
        });
    })(window, jQuery);

</script>
</body>
</html>