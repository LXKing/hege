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
<div class="main-container energy_consumption">
    <div class="clearfix row no-margin index_header">

        <!--面包屑导航-->
        <div class="bread-crumb pull-left">
            <%-- 当前位置：
             <a href="index.html">[<var class="xmhpg">首页 </var>]</a> &gt; [<var class="xmhpg" style="color: #666;">能源流概况</var>]--%>
        </div>
    </div>
    <script>
        $(function () {
            //console.info("重置面包屑");
            var mbhtml = "";
            var mbxdh =  ${navigations};
            var ymurl = document.location.href;
            var prefix = "";
            if(ymurl.indexOf('?')>0){
                prefix = ymurl.substr(0, ymurl.indexOf('?'));
            }else{
                prefix = ymurl;
            }

            $.each(mbxdh, function (idx, item) {
                /*console.info(item);
                 console.info(document.location.href==_web + item.url)*/

                var hturl = _web + item.url;
                if(hturl.indexOf('*')>=0){
                    hturl = hturl.replace('*','');
                    if (prefix.indexOf(hturl)>=0 ) {
                        mbhtml = getMbHtml(item, mbhtml,prefix);
                    }
                }else{
                    if (prefix == hturl) {
                        mbhtml = getMbHtml(item, mbhtml,prefix);
                    }
                }

            });
            $('.bread-crumb.pull-left').html("当前位置：" + mbhtml);
        });
        function getMbHtml(navigation, html,prefix) {
            if (navigation.navigation == "undefined" || navigation.navigation == null || navigation.navigation == "") {
                html += '<a href="' + _web + navigation.url + '">[<var class="xmhpg">' + navigation.title + '</var>]</a>';
                return html;
            } else {
                if(navigation.url.indexOf('*')>=1){
                    html = getMbHtml(navigation.navigation, html,navigation.navigation.url) + '&gt;<a href="' + _web + prefix + '">[<var class="xmhpg">' + navigation.title + '</var>]</a>';
                }else{
                    html = getMbHtml(navigation.navigation, html,navigation.navigation.url) + '&gt;<a href="' + _web + navigation.url + '">[<var class="xmhpg">' + navigation.title + '</var>]</a>';
                }
                return html;
            }
        }
    </script>
    <sitemesh:body/>
</div>

<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>