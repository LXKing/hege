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
    <title>华热能管系统-成本管控</title>
    <script src="${web}/script/huak.web.third.cost.fgs.js"></script>
</head>
<body>
<input id="orgId" value="${id}" type="hidden">
<input id="orgName" value="${orgName}" type="hidden">
<div class="index_mainbody  ">

<div class="index_content row no-margin">
    <div class="col-lg-12 no-padding index_contentList">
        <div class="col-lg-12 mb14  ">
            <div class=" index_contentBox clearfix">
                <div class="titbox clearfix no-padding no-margin">
                    <div class="pull-left groupEnergyTit energyTit"><i></i>${orgName}成本<small class="font-sm">Cost Control</small></div>
                </div>
                <div class="groupEnergy-box col-lg-12  clearfix">
                    <div class="chart-box groupEnergy-chart col-lg-10 ">
                        <div class="cb-header">
                            <span class="cb-title">${orgName}成本 (单位: 万元)</span>
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
                        <h3 id="groupTotal"><!--760.4--></h3>
                        <div class="small">${orgName}成本 (万元)</div>
                        <h4 id="groupchangeRate"><!--3.4<span class="arrow">↑</span>--></h4>
                        <div class="small">同比去年 (%)</div>
                    </div>
                </div>

                <div class="clearfix energy-list col-lg-12 ">
                    <div class="energy-list-box energy-list-box-first">
                        <a >
                            <div class="energy-head ">
                                <span class="energy-list-name">能源费</span>
                                <div class="energy-list-info">
                                    <span class="energy-list-num " id="waterTotal"></span>
                                    <span class="energy-list-measure ">万元</span>
                                    <span class="energy-list-proportion " id="waterchangeRate"></span>
                                </div>
                            </div>

                            <div class="energy-chart">
                                <div id="waterEnergyChart"></div>
                            </div>
                        </a>
                    </div>

                    <div class="energy-list-box">
                        <a>
                            <div class="energy-head ">
                                <span class="energy-list-name">管理费</span>
                                <div class="energy-list-info">
                                    <span class="energy-list-num" id="electricTotal"></span>
                                    <span class="energy-list-measure ">万元</span>
                                    <span class="energy-list-proportion" id="elechangeRate"></span>
                                </div>
                            </div>
                            <div class="energy-chart">
                                <div id="electricEnergyChart"></div>
                            </div>
                        </a>
                    </div>

                    <div class="energy-list-box">
                        <a>
                            <div class="energy-head ">
                                <span class="energy-list-name">其他费</span>
                                <div class="energy-list-info">
                                    <span class="energy-list-num " id="gasTotal"></span>
                                    <span class="energy-list-measure ">万元</span>
                                    <span class="energy-list-proportion " id="gaschangeRate"></span>
                                </div>
                            </div>

                            <div class="energy-chart">
                                <div id="gasEnergyChart"></div>
                            </div>
                        </a>
                    </div>

                    <div class="energy-list-box">
                        <a>
                            <div class="energy-head ">
                                <span class="energy-list-name">设备费</span>
                                <div class="energy-list-info">
                                    <span class="energy-list-num" id="hotTotal"></span>
                                    <span class="energy-list-measure">万元</span>
                                    <span class="energy-list-proportion" id="hotchangeRate"></span>
                                </div>
                            </div>

                            <div class="energy-chart">
                                <div id="hotEnergyChart"></div>
                            </div>
                        </a>
                    </div>

                    <div class="energy-list-box energy-list-box-last">
                        <a>
                            <div class="energy-head ">
                                <span class="energy-list-name">人工费</span>
                                <div class="energy-list-info">
                                    <span class="energy-list-num" id="coalTotal"></span>
                                    <span class="energy-list-measure ">万元</span>
                                    <span class="energy-list-proportion" id="coalchangeRate"></span>
                                </div>
                            </div>

                            <div class="energy-chart">
                                <div id="coalEnergyChart"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="AssessmentBox rconttable col-lg-12 no-padding">

                    <div class="col-lg-12 no-padding mt30">
                        <div class="col-lg-6 no-padding analyBoxList">
                            <div class="ec_title">
                                <span class="title_dw_station">热力站总成本排名(万元)</span>

                                <span class="button-group">
                                    <a href="javascript:;" class="bg-left button-group-act">能源</a><a href="javascript:;"  class="bg-left">设备</a><a href="javascript:;"  class="bg-left">人工</a><a href="javascript:;"  class="bg-left">管理</a><a href="javascript:;"  class="bg-right">其他</a>
                                </span>
                            </div>
                            <div id="piechart_as" style="width: 100%;height:268px;"></div>

                        </div>
                        <div class="col-lg-6 no-padding analyBoxList analyBoxline">
                            <div class="ec_title">
                                <span class="title_dw_feed">供热源成本排名(万元)</span>

                                <span class="button-group">
									<a href="javascript:;" class="bg-left button-group-act">能源</a><a href="javascript:;"  class="bg-left">设备</a><a href="javascript:;"  class="bg-left">人工</a><a href="javascript:;"  class="bg-left">管理</a><a href="javascript:;"  class="bg-right">其他</a>
								</span>
                            </div>
                            <div id="linechart_as" style="width: 100%;height:268px;"></div>

                        </div>

                    </div>

                </div>
            </div>

        </div>

    </div>

    <%--<div class="col-lg-12 no-padding index_contentList">
        <div class="col-lg-12 mb14">
            <div class="index_contentBox clearfix">
                <div class="titbox clearfix no-padding no-margin">
                    <div class="clearfix pull-left energyTit analy_tit_as"><i></i>各站点成本明细<small class="font-sm">Cost Control details</small></div>
                    <a export-url="${web}/third/fgs/export/${id}" class="pull-right exportlist mr15 export-emc">导出列表</a>

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
                                        <script>
                                            for(var i =0;i<20;i++){
                                                document.write("<tr><td>源1</td></tr>")
                                            }
                                        </script>
                                    </table>
                                </div>
                            </div>
                            <div id="right_div">
                                <div id="right_div1">
                                    <div id="right_divx">
                                        <table id="right_table1" class="table table-bordered">
                                            <tr>
                                                <th colspan="3">能源费(万元)</th>
                                                <th colspan="3">设备费(万元)</th>
                                                <th colspan="3">人工费(万元)</th>
                                                <th colspan="3">管理费(万元)</th>
                                                <th colspan="3">其他费用(万元)</th>
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
                                            </tr>

                                        </table>
                                    </div>
                                </div>
                                <div id="right_div2">
                                    <table id="right_table2" class="table table-bordered">

                                        <tr class="bg">
                                        </tr>
                                        <tr>
                                        </tr>
                                        <tr>
                                        </tr>
                                        <tr>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>--%>
</div>


<!-- <div class="index_contentList">-->
</div>
</body>
</html>