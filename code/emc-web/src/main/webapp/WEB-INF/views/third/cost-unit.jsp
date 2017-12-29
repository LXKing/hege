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
    <title>华热能管系统-成本管控</title>
    <script src="${web}/script/huak.web.third.cost.unit.js"></script>
</head>
<body>
<input id="id" value="${id}" type="hidden">
<input id="type" value="${unittype}" type="hidden">
<input id="orgType" value="${orgType}" type="hidden">
<input id="energytype" value="${energytype}" type="hidden">
<div class="index_mainbody  ">

<div class="index_content row no-margin">
<div class="col-lg-12 no-padding index_contentList">
    <div class="col-lg-12 mb14  ">
        <div class=" index_contentBox clearfix">
            <div class="titbox clearfix no-padding no-margin">
                <div class="pull-left groupEnergyTit energyTit"><i></i>源网站成本明细<small class="font-sm">COST CONTROL DETAILS</small></div>
            </div>
            <div class="groupEnergy-box col-lg-12  clearfix">
                <div class="chart-box groupEnergy-chart col-lg-12 ">
                    <div class="resources-bar">
											<span class="button-group">
												<a href="javascript:;" class="bg-left button-group-act">管理</a><a href="javascript:;"  class="bg-left">其他</a><a href="javascript:;"  class="bg-left">设备</a><a href="javascript:;"  class="bg-left">人工</a><a href="javascript:;"  class="bg-right">能源</a>
											</span>
                    </div>
                    <div class="cb-header">
                        <span class="cb-title"></span>
                        <div class="cb-title-right" style="margin-right:10px;">
                            <label>
                                <span class="cb-legend cb-legend-1"></span>
                                同期偏差
                            </label>
                            <label>
                                <span class="cb-legend cb-legend-2"></span>
                               本期计划
                            </label>
                            <label>
                                <span class="cb-legend cb-legend-3"></span>
                                本期成本
                            </label>
                            <label>
                                <span class="cb-legend cb-legend-4"></span>
                               去年同期
                            </label>
                            <label>
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  同比(单位:%)
                            </label>
                        </div>
                    </div>
                    <div id="groupEnergyChart" style="width: 100%;height:365px;"></div>
                </div>
            </div>
            <div class="AssessmentBox rconttable col-lg-12 no-padding">
                <div class="col-lg-12 no-padding mt30">
                    <div class="col-lg-6 no-padding analyBoxList">
                        <div class="ec_title">
                            2016~2017年度能源成本排名(万元)
                        </div>
                        <div id="piechart_as" style="width: 100%;height:268px;"></div>
                    </div>
                    <div class="col-lg-6 no-padding analyBoxList analyBoxline">
                        <div class="ec_title">
                            总能源成本趋势(万元)
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
                <div class=" clearfix pull-left energyTit analy_tit_as"><i></i>各分公司成本明细<small class="font-sm">Cost Control details</small></div>
                <a export-url="${web}/third/energy//unit/unitExport/${type}" class="pull-right exportlist mr15 export-emc">导出列表</a>
                <div class="   col-lg-12  tablewrap">
                    <div  >
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
                                    <div id="right_table1_gun"></div>
                                </div>
                            </div>
                            <div id="right_div2">
                                <table id="right_table2" class="table table-bordered">
                                    <tr class="bg">
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