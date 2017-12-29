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
    <meta name="decorator" content="main_top"/>
    <title>华热能管系统-能耗分析</title>
    <script src="${web}/script/huak.web.second.econ.js"></script>
</head>
<body>
<div class="index_mainbody ">

<div class="index_content row no-margin">
<div class="col-lg-12 no-padding index_contentList">
    <div class="col-lg-12 mb14  ">
        <div class=" index_contentBox clearfix">
            <div class="titbox clearfix no-padding no-margin">
                <div class="pull-left groupEnergyTit energyTit"><i></i>总能耗<small class="font-sm">Energy Monitoring</small></div>
            </div>
            <div class="groupEnergy-box col-lg-12  clearfix">
                <div class="chart-box groupEnergy-chart col-lg-10 ">
                    <div class="cb-header">
                        <span class="cb-title">总能耗 (单位: Tce)</span>
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
                    <div class="small">总能耗 (Tce)</div>
                    <h4 id="groupchangeRate"><!--3.4<span class="arrow">↑</span>--></h4>
                    <div class="small">同比去年 (%)</div>
                </div>
            </div>

            <div class="clearfix energy-list col-lg-12 ">
                <div class="energy-list-box energy-list-box-first">
                    <a href="${web}/third/energy/page/1">
                    <div class="energy-head ">
                        <span class="energy-list-name">水能耗</span>
                        <div class="energy-list-info">
                            <span class="energy-list-num " id="waterTotal"></span>
                            <span class="energy-list-measure ">T</span>
                            <span class="energy-list-proportion " id="waterchangeRate"></span>
                        </div>
                    </div>
                    </a>

                    <div class="energy-chart">
                        <div id="waterEnergyChart"></div>
                    </div>
                </div>

                <div class="energy-list-box">
                    <a href="${web}/third/energy/page/2">
                    <div class="energy-head ">
                        <span class="energy-list-name">电能耗</span>
                        <div class="energy-list-info">
                            <span class="energy-list-num" id="electricTotal"></span>
                            <span class="energy-list-measure ">kW·h</span>
                            <span class="energy-list-proportion" id="elechangeRate"></span>
                        </div>
                    </div>
                    </a>
                    <div class="energy-chart">
                        <div id="electricEnergyChart"></div>
                    </div>
                </div>

                <div class="energy-list-box">
                    <a href="${web}/third/energy/page/3">
                    <div class="energy-head ">
                        <span class="energy-list-name">气能耗</span>
                        <div class="energy-list-info">
                            <span class="energy-list-num " id="gasTotal"></span>
                            <span class="energy-list-measure ">m³</span>
                            <span class="energy-list-proportion " id="gaschangeRate"></span>
                        </div>
                    </div>
                    </a>
                    <div class="energy-chart">
                        <div id="gasEnergyChart"></div>
                    </div>
                </div>

                <div class="energy-list-box">
                    <a href="${web}/third/energy/page/4">
                    <div class="energy-head ">
                        <span class="energy-list-name">热能耗</span>
                        <div class="energy-list-info">
                            <span class="energy-list-num" id="hotTotal"></span>
                            <span class="energy-list-measure">GJ</span>
                            <span class="energy-list-proportion" id="hotchangeRate"></span>
                        </div>
                    </div>
                    </a>
                    <div class="energy-chart">
                        <div id="hotEnergyChart"></div>
                    </div>
                </div>

                <div class="energy-list-box energy-list-box-last">
                    <a href="${web}/third/energy/page/5">
                    <div class="energy-head ">
                        <span class="energy-list-name">煤能耗</span>
                        <div class="energy-list-info">
                            <span class="energy-list-num" id="coalTotal"></span>
                            <span class="energy-list-measure ">T</span>
                            <span class="energy-list-proportion" id="coalchangeRate"></span>
                        </div>
                    </div>
                    </a>
                    <div class="energy-chart">
                        <div id="coalEnergyChart"></div>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>
<!-- <div class="index_contentList">-->

<div class="col-lg-12 no-padding index_contentList">
    <div class="col-lg-12 mb14">
        <div class="index_contentBox clearfix">
            <div class="titbox clearfix no-padding no-margin">
                <div class="pull-left energyTit analy_tit"><i></i>${(company.nextDes ne null&&company.nextDes ne "")?company.nextDes:"分公司"}能耗明细<small class="font-sm">Assessment indicators</small></div>
                <a class="pull-right exportlist mr15 export-emc" export-url="${web}/energy/monitor/fgs/export">导出列表</a>
            </div>
            <div class="AssessmentBox rconttable col-lg-12 no-padding">
                <table class="table table-striped table-bordered table-hover col-lg-12 no-padding">
                    <thead>
                    <tr class="first_tr">
                        <td>${(company.nextDes ne null&&company.nextDes ne "")?company.nextDes:"分公司"}</td>
                        <td>能源总量（Tce）</td>
                        <td>水能总量（T）</td>
                        <td>电耗总量(kW·h)</td>
                        <td>气能耗总量（m³）</td>
                        <td>热能耗总量（GJ）</td>
                        <td>煤能耗总量（T）</td>
                        <td>油能耗总量（L）</td>
                    </tr>
                    </thead>
                    <tbody id="fgsEnergyTbody">
                    <c:forEach var="fgs" items="${fgsList}" varStatus="state">

                        <tr class="${state.index%2 eq 0?'':'bgc'}">
                            <td>
                                <a href="${web}/third/fgs/energys/${fgs.orgId}/${fgs.orgName}" class="need_a">${fgs.orgName}</a>
                            </td>
                            <td class="need_title">${fgs.totalBq}（同<span class="${fgs.totalAn eq 0?'':(fgs.totalAn gt 0?'redcolor':'bluecolor')}"><fmt:formatNumber type="number" value="${fgs.totalAn}" pattern="0.00" maxFractionDigits="2"/>%${fgs.totalAn eq 0?'':(fgs.totalAn gt 0?'↑':'↓')}</span>）</td>
                            <td class="need_title">${fgs.waterBq}（同<span class="${fgs.waterAn eq 0?'':(fgs.waterAn gt 0?'redcolor':'bluecolor')}"><fmt:formatNumber type="number" value="${fgs.waterAn}" pattern="0.00" maxFractionDigits="2"/>%${fgs.waterAn eq 0?'':(fgs.waterAn gt 0?'↑':'↓')}</span>）</td>
                            <td class="need_title">${fgs.electricBq}（同<span class="${fgs.electricAn eq 0?'':(fgs.electricAn gt 0?'redcolor':'bluecolor')}"><fmt:formatNumber type="number" value="${fgs.electricAn}" pattern="0.00" maxFractionDigits="2"/>%${fgs.electricAn eq 0?'':(fgs.electricAn gt 0?'↑':'↓')}</span>）</td>
                            <td class="need_title">${fgs.gasBq}（同<span class="${fgs.gasAn eq 0?'':(fgs.gasAn gt 0?'redcolor':'bluecolor')}"><fmt:formatNumber type="number" value="${fgs.gasAn}" pattern="0.00" maxFractionDigits="2"/>%${fgs.gasAn eq 0?'':(fgs.gasAn gt 0?'↑':'↓')}</span>）</td>
                            <td class="need_title">${fgs.heatBq}（同<span class="${fgs.heatAn eq 0?'':(fgs.heatAn gt 0?'redcolor':'bluecolor')}"><fmt:formatNumber type="number" value="${fgs.heatAn}" pattern="0.00" maxFractionDigits="2"/>%${fgs.heatAn eq 0?'':(fgs.totalAn gt 0?'↑':'↓')}</span>）</td>
                            <td class="need_title">${fgs.coalBq}（同<span class="${fgs.coalAn eq 0?'':(fgs.coalAn gt 0?'redcolor':'bluecolor')}"><fmt:formatNumber type="number" value="${fgs.coalAn}" pattern="0.00" maxFractionDigits="2"/>%${fgs.coalAn eq 0?'':(fgs.coalAn gt 0?'↑':'↓')}</span>）</td>
                            <td class="need_title">${fgs.oilBq}（同<span class="${fgs.oilAn eq 0?'':(fgs.oilAn gt 0?'redcolor':'bluecolor')}"><fmt:formatNumber type="number" value="${fgs.oilAn}" pattern="0.00" maxFractionDigits="2"/>%${fgs.oilAn eq 0?'':(fgs.oilAn gt 0?'↑':'↓')}</span>）</td>
                        </tr>
                    </c:forEach>
                    <%--<tr class="">
                        <td>
                            <a href="javascript:;" class="need_a">集团</a>
                        </td>
                        <td class="need_title">800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td class="need_title">800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>--%>

                    </tbody>
                </table>

                <div class="col-lg-12 no-padding mt20" style="border-bottom: 1px solid #d0d4d9;">
                    <div class="col-lg-6 no-padding analyBoxList" style="border-right: 1px solid #d0d4d9;">
                        <div id="piechart" style="width: 100%;height:268px;"></div>
                        <div class="piechartTit">
                            ${(company.nextDes ne null&&company.nextDes ne "")?company.nextDes:"分公司"}能耗占比分布图
                        </div>
                    </div>

                    <div class="col-lg-6 no-padding analyBoxList analyBoxline">
                        <div id="linechart" style="width: 100%;height:268px;"></div>
                        <div class="piechartTit">
                            ${(company.nextDes ne null&&company.nextDes ne "")?company.nextDes:"分公司"}能耗趋势对比图
                        </div>
                    </div>

                </div>
                <div class="col-lg-12 no-padding">
                    <div class="col-lg-6 no-padding analyBoxList" style="border-right: 1px solid #d0d4d9;">
                        <div id="barchart01" style="width: 100%;height:268px;"></div>
                        <div class="piechartTit">
                            ${(company.nextDes ne null&&company.nextDes ne "")?company.nextDes:"分公司"}能耗同比
                        </div>
                    </div>
                    <div class="col-lg-6 no-padding analyBoxList">
                        <div id="barchart02" style="width: 100%;height:268px;"></div>
                        <div class="piechartTit">
                            ${(company.nextDes ne null&&company.nextDes ne "")?company.nextDes:"分公司"}能耗排名
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>

</div>
<!-- <div class="index_contentList">-->

<div class="col-lg-12 no-padding index_contentList">
    <div class="col-lg-12 mb14">
        <div class="index_contentBox clearfix">
            <div class="titbox clearfix no-padding no-margin">
                <div class="pull-left energyTit analy_tit_as"><i></i>能源流明细<small class="font-sm">Assessment indicators</small></div>
                <a export-url="${web}/energy/monitor/exportEnergyFlowDetail" class="pull-right exportlist mr15 export-emc">导出列表</a>
            </div>
            <div class="AssessmentBox rconttable col-lg-12 no-padding">
                <table class="table table-striped table-bordered table-hover col-lg-12 no-padding">
                    <thead>
                    <tr class="first_tr">
                        <td>能源站</td>
                        <td>能源总量（Tce）</td>
                        <td>水能总量（T）</td>
                        <td>电耗总量(kW·h)</td>
                        <td>气能耗总量（m³）</td>
                        <td>热能耗总量（GJ）</td>
                        <td>煤能耗总量（T）</td>
                        <td>油能耗总量（L）</td>
                    </tr>
                    </thead>
                    <tbody id="energyFlowDetail">

                    </tbody>
                </table>

                <div class="col-lg-12 no-padding mt20" style="border-bottom: 1px solid #d0d4d9;">
                    <div class="col-lg-6 no-padding analyBoxList" style="border-right: 1px solid #d0d4d9;">
                        <div id="piechart_as" style="width: 100%;height:268px;"></div>
                        <div class="piechartTit">
                            能源流能耗占比分布图
                        </div>
                    </div>
                    <div class="col-lg-6 no-padding analyBoxList analyBoxline">
                        <div id="linechart_as" style="width: 100%;height:268px;"></div>

                        <div class="piechartTit">
                            能源流能耗趋势对比图
                        </div>
                    </div>

                </div>
                <div class="col-lg-12 no-padding">
                    <div class="col-lg-6 no-padding analyBoxList" style="border-right: 1px solid #d0d4d9;">
                        <div id="barchart01_as" style="width: 100%;height:268px;"></div>
                        <div class="piechartTit">
                            能源流能耗同比
                        </div>
                    </div>

                </div>

            </div>
        </div>

    </div>

</div>
<!-- <div class="index_contentList">-->
</div>

</div>

</body>
</html>