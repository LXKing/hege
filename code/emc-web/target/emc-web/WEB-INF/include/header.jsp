<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
    <div class="changeface"></div>
    <!--顶部导航条-->
    <div id="navbar" class="navbar navbar-default">
        <script type="text/javascript">
            try{ace.settings.check('navbar' , 'fixed')}catch(e){}
        </script>
        <div class="navbar-container" id="navbar-container">

            <!--<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
                <span class="sr-only">Toggle sidebar</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>-->
            <div class="navbar-header pull-left">
                <a href="${web}/index" class="navbar-brand">
                    <c:if test="${sessionScope.com_key.logoImg eq null||sessionScope.com_key.logoImg eq ''}">
                        <img src="${web}/static/img/logo/logo.png" width="296" height="31" alt="华热能源管控" />
                    </c:if>
                    <c:if test="${sessionScope.com_key.logoImg ne null&&sessionScope.com_key.logoImg ne ''}">
                        <img src="${web}/static/img/logo/${sessionScope.com_key.logoImg}" width="296" height="31" alt="${sessionScope.com_key.cname}" />
                    </c:if>


                </a>
            </div>
            <div class="navbar-buttons navbar-header pull-right" role="navigation">
                <ul class="nav ace-nav" id="ace-nav">
                    <li class="navlh navlh-act">
                        <a href="${web}/index">
                            <div class="icon-hm"></div>
                            <p>首页</p>
                        </a>
                    </li>
                    <li class="navlh ">
                        <a href="${web}/energy/analysis/index">
                            <div class="icon-nh"></div>
                            <p>能耗分析</p>
                        </a>
                    </li>
                    <li class="navlh">
                        <a href="${web}/cost/secondary/tsec">
                            <div class="icon-cb"></div>
                            <p>成本管控</p>
                        </a>
                    </li>
                    <li class="navlh">
                        <a href="${web}/carbon/emission/index">
                            <div class="icon-tp"></div>
                            <p>碳排管理</p>
                        </a>
                    </li>
                    <li class="navlh">
                        <a href="${web}/alarm/info/index">
                            <div class="icon-bj"></div>
                            <p>报警管理</p>
                        </a>
                    </li>
                    <%--<li class="navlh">--%>
                        <%--<a href="${web}/project/appraisal/index">--%>
                            <%--<div class="icon-pg"></div>--%>
                            <%--<p>项目后评估</p>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                    <li class="navlh">
                        <a href="${web}/process/index">
                            <div class="icon-pg"></div>
                            <p>生产调度</p>
                        </a>
                    </li>
                    <li class="navlh">
                        <a href="${web}/system/index">
                            <div class="icon-aq"></div>
                            <p>安全与后台</p>
                        </a>
                    </li>

                    <li class="tijian bordertopnone">
                        <a href="#">
                            <img src="${web}/static/img/tijian.png" alt="" />
                        </a>
                    </li>

                    <li class="tianqi clearfix bordertopnone">
                        <div class="right-tianqi">
                            <span id="date_today" class="date_today"></span>
                            <iframe name="tianqiiframe" id="tianqiiframe" src="http://i.tianqi.com/index.php?c=code&id=99&color=%23a3abb8&icon=3&num=1" width="120" height="32" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
                        </div>

                    </li>
                    <li class="light-blue bordertopnone">
                        <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                            <span class="navline-left"></span>
                            <img class="nav-user-photo" src="${web}/static/img/manager.png" alt="管理员" />
								<span class="user-info">管理员
								</span>
                            <i class="ace-icon fa fa-caret-down"></i>
                        </a>

                        <!--<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                            <li>
                                <a href="#">
                                    <i class="ace-icon fa fa-cog"></i>
                                    Settings
                                </a>
                            </li>

                            <li>
                                <a href="profile.html">
                                    <i class="ace-icon fa fa-user"></i>
                                    Profile
                                </a>
                            </li>

                            <li class="divider"></li>

                            <li>
                                <a href="#">
                                    <i class="ace-icon fa fa-power-off"></i>
                                    Logout
                                </a>
                            </li>
                        </ul>-->
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script>
    startTime("#date_today");
</script>