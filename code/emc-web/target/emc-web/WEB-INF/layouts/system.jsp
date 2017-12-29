<%--
    后台主框架，带头部、面包屑导航、底部
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
    <script>
        $(function () {
            //菜单绑定单击事件
            $('.publicmenu').on('click','ul a',function(){

                var menuName = $(this).text();
                var url = $(this).attr('menu-url');
                if("后台首页"==menuName){
                    document.location.replace(_web + url);
                    return false;
                }
                if('#'!=url){
                    $('.publicmenu .title').text(menuName);
                    $(".pull-left.yuce-tit").html(menuName);
                    $('.publicmenu li').removeClass('active');
                    $(this).parent().addClass('active');
                    $(this).parents('.more').addClass('active');
                    $('.publicmenu-con').hide();
                    $('.publicmenu').animate({left:'-196px'});
                    $('.publicmenu .scbtn').animate({left:'0px'});
                    $('.publicmenu .scbtn').addClass('close');

                    openPage(_web + url);
                }else{
                    return false;
                }
            });
        });


        /**
        * ajax打开页面
        * @param url
         */
        function openPage(url){
            $("#panelright").empty().load(url);
        }
    </script>
</head>
<body>
<%-- 菜单 --%>
<div class="publicmenu">

    <a href="javascript:void(0);" class="scbtn" style="left:196px"></a>
    <div class="title">后台首页</div>
    <div class="publicmenu-con">
        <a href="javascript:void(0);" class="btn-up btngun"></a>
        <a href="javascript:void(0);" class="btn-down btngun"></a>
        <ul>
            <c:forEach items="${menus}" var="oneMenu">
                <li class="${fn:length(oneMenu.menus)>0?'more':''} ${oneMenu.menuName eq '后台首页'?'active':''}">
                    <a href="javascript:void(0);" menu-url="${oneMenu.menuUrl}">${oneMenu.menuName}</a>
                    <c:if test="${fn:length(oneMenu.menus)>0}">
                        <ul>
                            <c:forEach items="${oneMenu.menus}" var="twoMenu">
                                <li class=""><a href="javascript:void(0);" menu-url="${twoMenu.menuUrl}">${twoMenu.menuName}</a></li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>

</div>

<%@include file="/WEB-INF/include/header.jsp" %>
<div class="main-container">
    <div class="clearfix row no-margin index_header">

        <!--面包屑导航-->
        <div class="bread-crumb row no-margin">
            当前位置：
            <a href="${web}/system/index">[<var class="xmhpg">安全与后台 </var>]</a>
        </div>
    </div>

    <!--标题-->
    <div class="titbox clearfix">
        <div class="pull-left yuce-tit">安全与后台 <%--<small class="font-sm">Security and Backstage</small>--%></div>

    </div>
    <div class="main-two-panel">
        <div class="panelright" id="panelright">
            <sitemesh:body/>
        </div>

    </div>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>