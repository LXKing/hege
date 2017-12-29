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
    <meta name="decorator" content="main"/>
    <title>华热能管系统-能耗分析</title>
    <script src="${web}/script/huak.web.energy.index.js"></script>
</head>
<body>
<div class="index_mainbody  ">

<div class="index_content row no-margin">
<div class="col-lg-12 no-padding index_contentList">
    <div class="col-lg-12 mb14  ">
        <div class=" index_contentBox clearfix">
            <div class="titbox clearfix no-padding no-margin">
                <div class="pull-left groupEnergyTit energyTit"><i></i>集团总能耗<small class="font-sm">Energy Monitoring</small></div>
            </div>
            <div class="groupEnergy-box col-lg-12  clearfix">
                <div class="chart-box groupEnergy-chart col-lg-8 ">
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
                <div class="groupEnergy-info col-lg-4">
                    <h3>${result.total.total }</h3>
                    <div class="small">集团总能耗 (GJ/m3)</div>
                    <h4>${result.total.scale }<span class="arrow"> ${result.total.up=='0'?'↓':'↑' }</span></h4>
                    <div class="small">同比去年 (%)</div>
                </div>
            </div>

            <div class="clearfix energy-list col-lg-12 ">
                <div class="energy-list-box energy-list-box-first">
                    <div class="energy-head energy-snh-remind">
                        <span class="energy-list-name">水能耗</span>
                        <div class="energy-list-info">
                            <span class="energy-list-num energy-remind">${result.water.total }</span>
                            <span class="energy-list-measure energy-remind">GJ</span>
                            <span class="energy-list-proportion energy-remind">
                            	(${result.water.scale }%${result.water.up=='0'?'↓':'↑' })
                            </span>
                        </div>
                    </div>

                    <div class="energy-chart">
                        <div id="waterEnergyChart"></div>
                    </div>
                </div>

                <div class="energy-list-box">
                    <div class="energy-head energy-dnh">
                        <span class="energy-list-name">电能耗</span>
                        <div class="energy-list-info">
                            <span class="energy-list-num">${result.elec.total }</span>
                            <span class="energy-list-measure">GJ</span>
                            <span class="energy-list-proportion">
                            	(${result.elec.scale }%${result.elec.up=='0'?'↓':'↑' })
                            </span>
                        </div>
                    </div>
                    <div class="energy-chart">
                        <div id="electricEnergyChart"></div>
                    </div>
                </div>

                <div class="energy-list-box">
                    <div class="energy-head energy-qnh-remind">
                        <span class="energy-list-name">气能耗</span>
                        <div class="energy-list-info">
                            <span class="energy-list-num energy-remind">${result.gas.total }</span>
                            <span class="energy-list-measure energy-remind">GJ</span>
                            <span class="energy-list-proportion energy-remind">
                            	(${result.gas.scale }%${result.gas.up=='0'?'↓':'↑' })
                            </span>
                        </div>
                    </div>

                    <div class="energy-chart">
                        <div id="gasEnergyChart"></div>
                    </div>
                </div>

                <div class="energy-list-box">
                    <div class="energy-head energy-rnh">
                        <span class="energy-list-name">热能耗</span>
                        <div class="energy-list-info">
                            <span class="energy-list-num">${result.hot.total }</span>
                            <span class="energy-list-measure">GJ</span>
                            <span class="energy-list-proportion energy-remind">
                            	(${result.hot.scale }%${result.hot.up=='0'?'↓':'↑' })
                            </span>
                        </div>
                    </div>

                    <div class="energy-chart">
                        <div id="hotEnergyChart"></div>
                    </div>
                </div>

                <div class="energy-list-box energy-list-box-last">
                    <div class="energy-head energy-mnh">
                        <span class="energy-list-name">煤能耗</span>
                        <div class="energy-list-info">
                            <span class="energy-list-num">${result.coal.total }</span>
                            <span class="energy-list-measure ">GJ</span>
                            <span class="energy-list-proportion">
                            	(${result.coal.scale }%${result.coal.up=='0'?'↓':'↑' })
                            </span>
                        </div>
                    </div>

                    <div class="energy-chart">
                        <div id="coalEnergyChart"></div>
                    </div>
                </div>
            </div>

        </div>


    </div>
</div> <!-- <div class="index_contentList">-->



<div class="col-lg-12 no-padding index_contentList">
    <div class="col-lg-12 mb14">
        <div class="index_contentBox clearfix">
            <div class="titbox clearfix no-padding no-margin">
                <div class="pull-left energyTit analy_tit"><i></i>分公司能耗明细<small class="font-sm">Assessment indicators</small></div>
                <a href="javascript:;" class="pull-right exportlist mr15 export-emc">导出列表</a>
            </div>
            <div class="AssessmentBox rconttable col-lg-12 no-padding">
                <table class="table table-striped table-bordered table-hover col-lg-12 no-padding">
                    <thead>
                    <tr class="first_tr">
                        <td>分公司</td>
                        <td>能源总量（万GJ）</td>
                        <td>水能总量（T）</td>
                        <td>电耗总量(kW·h)</td>
                        <td>气能耗总量（M²）</td>
                        <td>热能耗总量（GJ）</td>
                        <td>煤能耗总量（GJ）</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="">
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td class="need_title">800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td class="need_title">800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr class="bgc">
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr>
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr class="bgc">
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr>
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr class="bgc">
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr>
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>

                    </tbody>
                </table>

                <div class="col-lg-12 no-padding mt20" style="border-bottom: 1px solid #d0d4d9;">
                    <div class="col-lg-6 no-padding analyBoxList" style="border-right: 1px solid #d0d4d9;">
                        <div id="piechart" style="width: 100%;height:268px;"></div>
                        <div class="piechartTit">
                            分公司能耗占比分布图
                        </div>
                    </div>
                    <div class="col-lg-6 no-padding analyBoxList analyBoxline">
                        <div id="linechart" style="width: 100%;height:268px;"></div>

                        <div class="piechartTit">
                            分公司能耗趋势对比图
                        </div>
                    </div>

                </div>
                <div class="col-lg-12 no-padding">
                    <div class="col-lg-6 no-padding analyBoxList" style="border-right: 1px solid #d0d4d9;">
                        <div id="barchart01" style="width: 100%;height:268px;" ></div>
                        <div class="piechartTit">
                            分公司能耗同比
                        </div>
                    </div>
                    <div class="col-lg-6 no-padding analyBoxList">
                        <div id="barchart02" style="width: 100%;height:268px;" ></div>
                        <div class="piechartTit">
                            分公司能耗排名
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>

</div> <!-- <div class="index_contentList">-->

<div class="col-lg-12 no-padding index_contentList">
    <div class="col-lg-12 mb14">
        <div class="index_contentBox clearfix">
            <div class="titbox clearfix no-padding no-margin">
                <div class="pull-left energyTit analy_tit_as"><i></i>能源流明细<small class="font-sm">Assessment indicators</small></div>
                <a href="javascript:;" class="pull-right exportlist mr15 export-emc">导出列表</a>
            </div>
            <div class="AssessmentBox rconttable col-lg-12 no-padding">
                <table class="table table-striped table-bordered table-hover col-lg-12 no-padding">
                    <thead>
                    <tr class="first_tr">
                        <td>分公司</td>
                        <td>能源总量（万GJ）</td>
                        <td>水能总量（T）</td>
                        <td>电耗总量(kW·h)</td>
                        <td>气能耗总量（M²）</td>
                        <td>热能耗总量（GJ）</td>
                        <td>煤能耗总量（GJ）</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="">
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td class="need_title">800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td class="need_title">800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr class="bgc">
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr>
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr class="bgc">
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr>
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr class="bgc">
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>
                    <tr>
                        <td><a href="javascript:;" class="need_a">集团</a></td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                        <td>800（同<span class="bluecolor">1.2↓</span>&nbsp;环&nbsp;<span class="redcolor">1.2↑</span>）</td>
                    </tr>

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
                        <div id="barchart01_as" style="width: 100%;height:268px;" ></div>
                        <div class="piechartTit">
                            能源流能耗同比
                        </div>
                    </div>
                    <div class="col-lg-6 no-padding analyBoxList">

                    </div>
                </div>

            </div>
        </div>

    </div>

</div> <!-- <div class="index_contentList">-->
</div>

</div>

</body>
</html>