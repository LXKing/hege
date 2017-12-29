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
    <meta name="decorator" content="main_third_fgs"/>
    <title>华热能管系统-单耗分析</title>
    <script src="${web}/script/huak.web.third.analysis.fgs.js"></script>
</head>
<body>
<input id="id" value="${id}" type="hidden">
<input id="orgName" value="${orgName}" type="hidden">
<div class="index_mainbody  ">

<div class="index_content row no-margin">
    <div class="col-lg-12 no-padding index_contentList">
        <div class="col-lg-12 mb14  ">
            <div class=" index_contentBox clearfix">
                <div class="titbox clearfix no-padding no-margin">
                    <div class="pull-left groupEnergyTit energyTit"><i></i>${orgName}总单耗<small class="font-sm">Energy Monitoring</small></div>
                </div>
                <div class="groupEnergy-box col-lg-12  clearfix">
                    <div class="chart-box groupEnergy-chart col-lg-10 ">
                        <div class="cb-header">
                            <span class="cb-title">${orgName}总单耗 (单位: Tce/万m²)</span>
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
                        <h3 id="groupTotal" class="groupTotal"><!--760.4--></h3>
                        <div class="small">总单耗 (Tce/万m²)</div>
                        <h4 id="groupchangeRate" class="groupchangeRate"><!--3.4<span class="arrow">↑</span>--></h4>
                        <div class="small">同比去年 (%)</div>
                    </div>
                </div>

                <div class="clearfix energy-list col-lg-12 ">
                    <div class="energy-list-box energy-list-box-first">

                            <div class="energy-head ">
                                <span class="energy-list-name">水单耗</span>
                                <div class="energy-list-info">
                                    <span class="energy-list-num waterTotal" id="waterTotal"></span>
                                    <span class="energy-list-measure waterDw">Tce/万m²</span>
                                    <span class="energy-list-proportion waterchangeRate" id="waterchangeRate"></span>
                                </div>
                            </div>

                            <div class="energy-chart">
                                <div id="waterEnergyChart"></div>
                            </div>

                    </div>

                    <div class="energy-list-box">

                            <div class="energy-head ">
                                <span class="energy-list-name">电单耗</span>
                                <div class="energy-list-info">
                                    <span class="energy-list-num electricTotal" id="electricTotal"></span>
                                    <span class="energy-list-measure electricDw">GJ</span>
                                    <span class="energy-list-proportion elechangeRate" id="elechangeRate"></span>
                                </div>
                            </div>
                            <div class="energy-chart">
                                <div id="electricEnergyChart"></div>
                            </div>

                    </div>

                    <div class="energy-list-box">

                            <div class="energy-head ">
                                <span class="energy-list-name">气单耗</span>
                                <div class="energy-list-info">
                                    <span class="energy-list-num gasTotal" id="gasTotal"></span>
                                    <span class="energy-list-measure gasDw">GJ</span>
                                    <span class="energy-list-proportion gaschangeRate" id="gaschangeRate"></span>
                                </div>
                            </div>

                            <div class="energy-chart">
                                <div id="gasEnergyChart"></div>
                            </div>

                    </div>

                    <div class="energy-list-box">

                            <div class="energy-head ">
                                <span class="energy-list-name">热单耗</span>
                                <div class="energy-list-info">
                                    <span class="energy-list-num hotTotal"  id="hotTotal"></span>
                                    <span class="energy-list-measure hotDw">GJ</span>
                                    <span class="energy-list-proportion hotchangeRate" id="hotchangeRate"></span>
                                </div>
                            </div>

                            <div class="energy-chart">
                                <div id="hotEnergyChart"></div>
                            </div>

                    </div>

                    <div class="energy-list-box energy-list-box-last">

                            <div class="energy-head ">
                                <span class="energy-list-name">煤单耗</span>
                                <div class="energy-list-info">
                                    <span class="energy-list-num coalTotal" id="coalTotal"></span>
                                    <span class="energy-list-measure coalDw">GJ</span>
                                    <span class="energy-list-proportion coalchangeRate" id="coalchangeRate"></span>
                                </div>
                            </div>

                            <div class="energy-chart">
                                <div id="coalEnergyChart"></div>
                            </div>

                    </div>
                </div>
                <div class="AssessmentBox rconttable col-lg-12 no-padding">

                    <div class="col-lg-12 no-padding mt30">
                        <div class="col-lg-6 no-padding analyBoxList">
                            <div class="ec_title">
                                <span class="title_dw_station">换热站单耗排名(Tce/万m²)</span>
                                <%--http://192.168.155.196:8081/emc/js/energy_company.js--%>
												<span class="button-group">
												<a href="javascript:;" onclick="loadStationDH(1)" class="bg-left button-group-act">水</a><a href="javascript:;" onclick="loadStationDH(2)" class="bg-left">电</a><a href="javascript:;" onclick="loadStationDH(3)" class="bg-left">气</a><a href="javascript:;" onclick="loadStationDH(4)" class="bg-left">热</a><a href="javascript:;" onclick="loadStationDH(5)"  class="bg-right">煤</a>
											</span>
                            </div>
                            <div id="piechart_as" style="width: 100%;height:268px;"></div>

                        </div>
                        <div class="col-lg-6 no-padding analyBoxList analyBoxline">
                            <div class="ec_title">
                                <span class="title_dw_feed">供热源单耗排名(Tce/万m²)</span>
                                <span class="button-group">
												<a href="javascript:;" onclick="loadFeedDH(1)"  class="bg-left button-group-act">水</a><a href="javascript:;" onclick="loadFeedDH(2)"  class="bg-left">电</a><a href="javascript:;" onclick="loadFeedDH(3)" class="bg-left">气</a><a href="javascript:;" onclick="loadFeedDH(4)" class="bg-left">热</a><a href="javascript:;" onclick="loadFeedDH(5)" class="bg-right">煤</a>
											</span>
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
    <div class="pull-left energyTit analy_tit_as"><i></i>各站点单耗明细<small class="font-sm">Energy consumption details</small></div>
    <a export-url="${web}/third/analysis/fgs/export/${id}" class="pull-right exportlist mr15 export-emc">导出列表</a>

    <div class="  col-lg-12  tablewrap">
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
                    <tr>
                        <th colspan="3">水能耗(GJ)</th>
                        <th colspan="3">水能耗(GJ)</th>
                        <th colspan="3">水能耗(GJ)</th>
                        <th colspan="3">水能耗(GJ)</th>
                        <th colspan="3">水能耗(GJ)</th>
                        <th colspan="3">水能耗(GJ)</th>
                        <th colspan="3">水能耗(GJ)</th>
                        <th colspan="3">水能耗(GJ)</th>
                        <th colspan="3">水能耗(GJ)</th>
                        <th colspan="3">水能耗(GJ)</th>
                    </tr>
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


<!-- <div class="index_contentList">-->
</div>
</body>
</html>