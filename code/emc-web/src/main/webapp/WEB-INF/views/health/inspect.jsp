<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2017/8/22
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="decorator" content="health"/>
    <title>华热能管系统-健康指数</title>
    <script src="${web}/script/huak.web.health.inspect.js"></script>
</head>
<body>
<div class="index_mainbody  ">
    <div class="index_content row no-margin">
        <div class="col-lg-12 no-padding index_contentList">
            <div class="col-lg-12 mb14  ">
                <div class=" index_contentBox clearfix">
                    <div class="titbox clearfix no-padding no-margin">
                        <div class="pull-left groupEnergyTit energyTit"><i></i>健康指数<small class="font-sm">Health index</small></div>
                    </div>
                    <div class=" col-lg-12  clearfix healthwrap">
                        <div class="left">
                            <div class="charts">
                                <div id="chart01">
                                    <c:choose>
                                        <c:when test="${flag eq false}">
                                        <div class="value">
                                          <h1>100</h1>
                                            <h2>无检测记录</h2>
                                            <h3></h3>
                                         </div>
                                    </c:when>
                                    <c:when test="${flag eq true}">
                                        <div class="value">
                                            <h1 id="score">${score}</h1>
                                            <h2>上次检测记录</h2>
                                            <h3 id="scoreTime">${time}</h3>
                                        </div>
                                    </c:when>

                                    </c:choose>

                                </div>
                            </div>
                            <div id="running" style="display: none">
                                <h1>正在检测...</h1>
                                <div class="pressbar">
                                    <div></div>
                                </div>
                            </div>
                            <a id="runbtn"></a>
                            <a id="replacebtn" style="display: none" href="${web}/health" target="_self"></a>
                            <div id="printMsg">

                            </div>
                        </div>
                        <div id="resulthealth" class="right clearfix">
                            <input type="hidden" id="healthItem" value='${healthItem}'>
                            <input type="hidden" id="key" value='${key}'>
                            <div class="resultlist">

                                <div class="healthitem">
                                    <div><h1>发现<span id="healthcount">23</span>项待检测</h1></div>
                                    <div class="panelwrap">
                                        <ul id="healthlistul">



                                        </ul>
                                    </div>
                                </div>

                                <div class="erroritem" style="display: none">
                                    <div><h1>发现<span id="errorcount">0</span>项异常</h1></div>
                                    <div class="panelwrap">
                                        <ul id="errorlistul">

                                        </ul>
                                    </div>
                                </div>

                                <div class="normalitem" style="display: none">
                                    <div><h1>以下<span id="normalcount">0</span>项无异常</h1></div>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>

            </div>

        </div>

    </div>
</div>

</body>
</html>