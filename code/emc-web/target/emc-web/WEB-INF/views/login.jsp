<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%--<meta name="renderer" content="webkit">--%>
    <meta name="decorator" content="login"/>
    <title>能源管控中心 - 登录</title>
    <script src="${web}/script/huak.login.js"></script>
    <script>
        var ctx = '${web}';
    </script>

</head>
<body>
<div class="formwrap">
    <h1>
        <img src="${web}/static/img/logo/login_logozz.png" />
    </h1>
    <form id="form" onsubmit="false" autocomplete="off">
    <div class="form">
        <div class="title">能源管控中心 &#183; 供热版</div>
        <div class="d1"><input type="text" maxlength="64" name="login" placeholder="请输入用户名" /></div>
        <div class="d2"><input type="password" maxlength="64" name="pwd"  placeholder="请输入您的密码" /></div>
        <div class="d3">
            <label><input type="checkbox" id="isAutoLogin"/>下次自动登录</label>
            <a>忘记密码</a>
        </div>
        <a class="btnsubmit" id="login">登  录  系  统</a>
        <div style="text-align: center" id="msg"></div>
    </div>
    </form>
</div>
</body>

<%--<body class="signin">--%>
<%--<div class="signinpanel">--%>
    <%--<div class="row">--%>
        <%--<div class="col-sm-7">--%>
            <%--<div class="signin-info">--%>
                <%--<div class="logopanel m-b">--%>
                    <%--<h1>能源管控中心</h1>--%>
                <%--</div>--%>
                <%--<div class="m-b"></div>--%>
                <%--<h4>欢迎使用 <strong>后台管理系统</strong></h4>--%>
                <%--<ul class="m-b">--%>
                    <%--<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>--%>
                    <%--<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>--%>
                    <%--<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>--%>
                    <%--<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>--%>
                    <%--<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>--%>
                <%--</ul>--%>
                <%--<strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="col-sm-5">--%>
            <%--<form id="form" onsubmit="false">--%>
                <%--<h4 class="no-margins">登录：</h4>--%>
                <%--<p class="m-t-md">登录到后台管理系统</p>--%>
                <%--<div class="form-group">--%>
                    <%--<input type="text" maxlength="64" name="login" class="form-control uname" placeholder="用户名" />--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<input type="password" maxlength="64" name="pwd" class="form-control pword m-b" placeholder="密码" />--%>
                <%--</div>--%>
                <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<input type="text" maxlength="4" name="vc" style="width: 63%;margin-top: 0px;color: #333;" class="form-control col-xs-8" placeholder="点击图片切换验证码" />&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<img class="ver-code-img" src="${web}/generateVc" />&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--<a href="">忘记密码了？</a>--%>
                <%--<button type="button" id="login" class="btn btn-success btn-block">登录</button>--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="signup-footer">--%>
        <%--<div class="pull-left">--%>
            <%--&copy; 2015 All Rights Reserved. 北京华热科技发展有限公司--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--</body>--%>

</html>
