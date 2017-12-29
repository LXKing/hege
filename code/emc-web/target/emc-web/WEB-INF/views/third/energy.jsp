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
    <meta name="decorator" content="main_third"/>
    <title>华热能管系统-能耗分析</title>
    <script src="${web}/script/huak.web.third.energy.js"></script>
</head>
<body>
<input id="type" value="${type}" type="hidden">
<div class="index_mainbody  ">

<div class="index_content row no-margin">
<div class="col-lg-12 no-padding index_contentList">
    <div class="col-lg-12 mb14  ">
        <div class=" index_contentBox clearfix">
            <div class="titbox clearfix no-padding no-margin">
                <div class="pull-left groupEnergyTit energyTit"><i></i><span class="maintitle"></span>明细<small class="font-sm">ENERGY CONSUMPTION DETAILS</small></div>
            </div>
            <div class="groupEnergy-box col-lg-12  clearfix">
                <div class="chart-box groupEnergy-chart col-lg-10 ">
                    <div class="cb-header">
                        <span class="cb-title">集团总能耗 (单位: GJ)</span>
                        <div class="cb-title-right" style="margin-right: 36px;">
                            <label>
                                <span class="cb-legend-blue"></span>
                                今年
                            </label>
                            <label>
                                <span class="cb-legend-gray"></span>
                                去年
                            </label>
                        </div>
                    </div>
                    <div id="groupEnergyChart" style="width: 100%;height:365px;"></div>
                </div>
                <div class="groupEnergy-info col-lg-2 ">
                    <h3 id="groupTotal">0</h3>
                    <div class="small">集团总能耗 (GJ/m3)</div>
                    <h4 id="groupchangeRate">0<span class="arrow"></span></h4>
                    <div class="small">同比去年 (%)</div>
                </div>
            </div>

            <div class="clearfix energy-list col-lg-12 ">
                <div class="energy-list-box energy-list-box-first">
                    <div class="energy-head energy-add">
                        <span class="energy-list-name">供热源-<span class="maintitle"></span></span>
                        <div class="energy-list-info">
                            <span class="energy-list-num">0</span>
                            <span class="energy-list-measure">GJ</span>
                            <span class="energy-list-proportion">(0)</span>
                        </div>
                    </div>

                    <div class="energy-chart">
                        <div id="chart1"></div>
                    </div>
                </div>
                <div class="energy-list-box">
                    <div class="energy-head ">
                        <span class="energy-list-name">管网-<span class="maintitle"></span></span>
                        <div class="energy-list-info">
                            <span class="energy-list-num">0</span>
                            <span class="energy-list-measure ">GJ</span>
                            <span class="energy-list-proportion ">(0)</span>
                        </div>

                    </div>
                    <div class="energy-chart">
                        <div id="chart2"></div>
                    </div>
                </div>

                <div class="energy-list-box">
                    <div class="energy-head ">
                        <span class="energy-list-name">换热站-<span class="maintitle"></span></span>
                        <div class="energy-list-info">
                            <span class="energy-list-num">0</span>
                            <span class="energy-list-measure ">GJ</span>
                            <span class="energy-list-proportion ">(0)</span>
                        </div>

                    </div>

                    <div class="energy-chart">
                        <div id="chart3"></div>
                    </div>
                </div>

                <div class="energy-list-box">
                    <div class="energy-head ">
                        <span class="energy-list-name">管线-<span class="maintitle"></span></span>
                        <div class="energy-list-info">
                            <span class="energy-list-num">0</span>
                            <span class="energy-list-measure ">GJ</span>
                            <span class="energy-list-proportion ">(0)</span>
                        </div>

                    </div>

                    <div class="energy-chart">
                        <div id="chart4"></div>
                    </div>
                </div>

                <div class="energy-list-box energy-list-box-last">
                    <div class="energy-head ">
                        <span class="energy-list-name">民户-<span class="maintitle"></span></span>
                        <div class="energy-list-info">
                            <span class="energy-list-num">0</span>
                            <span class="energy-list-measure ">GJ</span>
                            <span class="energy-list-proportion ">(0)</span>
                        </div>
                    </div>

                    <div class="energy-chart">
                        <div id="chart5"></div>
                    </div>
                </div>
            </div>
            <div class="AssessmentBox rconttable col-lg-12 no-padding">

                <div class="col-lg-12 no-padding mt30">
                    <div class="col-lg-6 no-padding analyBoxList">
                        <div class="ec_title">
                            换热站<span class="maintitle"></span>排名
                        </div>
                        <div id="piechart_as" style="width: 100%;height:268px;"></div>

                    </div>
                    <div class="col-lg-6 no-padding analyBoxList analyBoxline">
                        <div class="ec_title">
                            热源<span class="maintitle"></span>排名
                        </div>
                        <div id="linechart_as" style="width: 100%;height:268px;"></div>

                    </div>

                </div>

            </div>
        </div>

    </div>

</div>

<div class="col-lg-12 no-padding index_contentList">
<div class="col-lg-12 mb14">
<div class="index_contentBox clearfix">
<div class="titbox clearfix no-padding no-margin">
<div class="clearfix pull-left energyTit analy_tit_as"><i></i>各站点<span class="maintitle"></span>明细<small class="font-sm">Energy consumption details</small></div>
<a export-url="${web}/third/energy/export/${type}" class="pull-right exportlist mr15 export-emc">导出列表</a>
<div class=" col-lg-12  tablewrap">
<div class="">
<div id="left_div">
    <div id="left_div1">
        <table id="left_table1" class="table table-bordered">
            <tr>
                <th style="height: 54px;"> </th>
            </tr>

        </table>
    </div>
    <div id="left_div2">
        <table id="left_table2" class="table table-bordered">
            <tr class="bg">
                <th>源</th>
            </tr>
            <tr>
                <td>源1</td>
            </tr>
            <tr>
                <td>源1</td>
            </tr>
            <tr>
                <td>源1</td>
            </tr>
        </table>
    </div>
</div>
<div id="right_div">
    <div id="right_div1">
        <div id="right_divx">
            <table id="right_table1" class="table table-bordered">
                <%--<tr>
                    <th colspan="3">07月03日</th>
                    <th colspan="3">07月03日</th>
                    <th colspan="3">07月03日</th>
                    <th colspan="3">07月03日</th>
                    <th colspan="3">07月03日</th>
                    <th colspan="3">07月03日</th>
                    <th colspan="3">07月03日</th>
                    <th colspan="3">07月03日</th>
                    <th colspan="3">07月03日</th>
                    <th colspan="3">07月03日</th>
                </tr>--%>
                <tr>
                    <td>实际</td>
                    <td>计划</td>
                    <td>同期</td>
                    <td>实际</td>
                    <td>计划</td>
                    <td>同期</td>
                    <td>实际</td>
                    <td>计划</td>
                    <td>同期</td>
                    <td>实际</td>
                    <td>计划</td>
                    <td>同期</td>
                    <td>实际</td>
                    <td>计划</td>
                    <td>同期</td>
                    <td>实际</td>
                    <td>计划</td>
                    <td>同期</td>
                    <td>实际</td>
                    <td>计划</td>
                    <td>同期</td>
                    <td>实际</td>
                    <td>计划</td>
                    <td>同期</td>
                    <td>实际</td>
                    <td>计划</td>
                    <td>同期</td>
                    <td>实际</td>
                    <td>计划</td>
                    <td>同期</td>
                </tr>

            </table>
            <div id="right_table1_gun"></div>
        </div>
    </div>
    <div id="right_div2">
        <table id="right_table2" class="table table-bordered">

            <tr class="bg">
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
            </tr>
            <tr>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
            </tr>
            <tr>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
            </tr>
            <tr>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
                <td>800</td>
                <td>600</td>
                <td>2.6↑</td>
            </tr>
        </table>
    </div>
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