<%--
    主框架，带头部、面包屑导航、底部
    lc 2017年5月24日15:47:52
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="HUAK,能源管控中心,能管,能管后台">
    <meta name="description" content="HUAK 能源管控中心">
    <title><sitemesh:title/></title>
    <%@include file="/WEB-INF/include/include.jsp" %>
    <sitemesh:head/>
</head>
<body>
<%@include file="/WEB-INF/include/header.jsp" %>
<div class="main-container">
    <%@include file="/WEB-INF/include/tools.jsp" %>
    <sitemesh:body/>
</div>
<div class="energy_black clearfix">
    <a href="javascript:history.back(-1);" class="block pull-left"><img src="${web}/static/img/images/btn01.png" alt="上级" /></a>
    <a href="${web}/index" class="block pull-left"><img src="${web}/static/img/images/btn02.png" alt="首页" /></a>
    <a href="javascript:;" id="returnTop" class="block pull-left"><img src="${web}/static/img/images/btn03.png" alt="顶部" /></a>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>