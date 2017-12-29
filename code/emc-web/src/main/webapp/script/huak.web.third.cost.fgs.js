/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/17<BR>
 */

function loadDataFun() {
    initCssFunc();
    initEnergyRank01(1);
    initEnergyRank02(1);
    // createtable();
    initTable();
    initJTenergyData();
    initEnergyCostData();
    initCostTypeData();
}

/*表格数据加载*/
function initTable(){
    var data = $("#searchTools").serialize()+"&orgId="+$("#orgId").val();
    $.ajax({
        url: _web + '/cost/thired/ThirdlevelCodeTtable',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: data,
        dataType: "json",
        success: function (result) {
            thirdTable(result.object);
        }
    });
};

/*按钮切换事件和样式绑定*/
function initCssFunc(){
    $.each($(".ec_title"), function(index, item) {
        if(index == 0) {
            $.each($(this).find("a"), function(sindex, sitem) {
                $(this).click(function() {
                    $(this).addClass("button-group-act").siblings().removeClass("button-group-act");
                    var type = $(sitem).html();
                    if(type == "能源"){
                        $(".title_dw_station").html("能源成本排名(万元)");
                        initEnergyRank01(1);
                    }
                    if(type == "设备"){
                        $(".title_dw_station").html("设备成本排名(万元)");
                        initEnergyRank01(2);
                    }
                    if(type == "人工"){
                        $(".title_dw_station").html("人工成本排名(万元)");
                        initEnergyRank01(3);
                    }
                    if(type == "管理"){
                        $(".title_dw_station").html("管理成本排名(万元)");
                        initEnergyRank01(4);
                    }
                    if(type == "其他"){
                        $(".title_dw_station").html("其他成本排名(万元)");
                        initEnergyRank01(5);
                    }
                });

            });
        } else {
            $.each($(this).find("a"), function(sindex, sitem) {
                $(this).click(function() {
                    $(this).addClass("button-group-act").siblings().removeClass("button-group-act");
                    var type = $(sitem).html();
                    if(type == "水"){
                        $(".title_dw_feed").html("供热源能耗排名(T)");
                        initEnergyRank02(1);
                    }
                    if(type == "电"){
                        $(".title_dw_feed").html("供热源能耗排名(kW·h)");
                        initEnergyRank02(2);
                    }
                    if(type == "气"){
                        $(".title_dw_feed").html("供热源能耗排名(m³)");
                        initEnergyRank02(3);
                    }
                    if(type == "热"){
                        $(".title_dw_feed").html("供热源能耗排名(GJ)");
                        initEnergyRank02(4);
                    }
                    if(type == "煤"){
                        $(".title_dw_feed").html("供热源能耗排名(T)");
                        initEnergyRank02(5);
                    }
                });
            });
        }
    })
}
/*站能耗排名*/
function initEnergyRank01(type){
    var data = $("#searchTools").serialize()+"&orgId="+$("#orgId").val()+"&type="+type+"&orgType=3";
    $.ajax({
        url: _web + '/cost/thired/StationRanking',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: data,
        dataType: "json",
        success: function (data) {
            console.log(data)
            chart01Fun(data.object);
        }
    });

}
/*源能耗排名*/
function initEnergyRank02(type){
    var data = $("#searchTools").serialize()+"&orgId="+$("#orgId").val()+"&type="+type+"&orgType=1";
    $.ajax({
        url: _web + '/cost/thired/StationRanking',
        type: 'get',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: data,
        dataType: "json",
        success: function (result) {
            chart02Fun(result.object);
        }
    });
}
/*集团总能耗数据查询封装*/
function initJTenergyData(){
    var data = $("#searchTools").serialize()+"&orgId="+$("#orgId").val();
    $.ajax({
        url: _web + '/cost/thired/total/costTotalDetail',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function(result) {
            var _DATA = result.object;
            var _DATE_LIST = result.object.date;
            var _DATA_CURRENT = _DATA.data[0].bm_current;
            var _DATA_LAST = _DATA.data[0].bm_last;
            var _TB = 0;
            $("#groupTotal").html(toDecimalCommon(_DATA_CURRENT,2));
            if(_DATA_LAST != 0){
                _TB = parseFloat(toDecimalCommon((_DATA_CURRENT- _DATA_LAST)/_DATA_LAST,4))*10000/100;
            }
            var _ZZ =_TB>0?"↑":((_TB == 0)?"→":"↓");
            $("#groupchangeRate").addClass("energy_gray2");
            $("#groupchangeRate").html(toDecimal(_TB) + "<span class='arrow'>"+_ZZ+"</span>");
            groupCostChartFun(_DATA, _DATE_LIST);
        },
    });
}
/*能源类型-用量折线图-数据加载*/
function initEnergyCostData(){
    var data = $("#searchTools").serialize()+"&orgId="+$("#orgId").val();
    $.ajax({
        url: _web + '/cost/thired/unit/thirdSubCompanyEnergyDetail',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function(result) {
            if(result.flag){
                var _DATA = result.object.data;
                var _DATE = result.object.date;
                $.each(_DATA,function(_INDEX,_ITEM){
                        energyCostChartFun(_ITEM, _DATE);
                })
            }
        }
    });
}
/*成本类型-用量折线图-数据加载*/
function initCostTypeData(){
    var data = $("#searchTools").serialize()+"&orgId="+$("#orgId").val();
    $.ajax({
        url: _web + '/cost/thired/CostDetail',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function(result) {
            if(result.flag){
                var _DATA = result.object.data;
                var _DATE = result.object.date;
                $.each(_DATA,function(_INDEX,_ITEM){
                    if(_ITEM.type =="chart1"){
                        //管理
                        manegerCostChartFun(_ITEM, _DATE);
                    }
                    if(_ITEM.type =="chart2"){
                        //其他
                        otherCostChartFun(_ITEM, _DATE);
                    }
                    if(_ITEM.type =="chart3"){
                        //设备
                        deviceCostChartFun(_ITEM, _DATE);
                    }
                    if(_ITEM.type =="chart4"){
                        //人工
                        labourCostChartFun(_ITEM, _DATE);
                    }

                })
            }
        }
    });
}
/*集团总成本-折线图*/
function groupCostChartFun(datalist, datelist) {
    $("#groupEnergyChart").empty();
    groupEnergyChart = echarts.init(document.getElementById('groupEnergyChart'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '15',
            top: '10',
            right: '40',
            bottom: '10',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick: {
                show: false
            },
            splitArea: {
                show: true,
                areaStyle: {
                    color: chartsColor.areacolor
                }
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: chartsColor.ec1.facecolor1
                }
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: chartsColor.ec1.facecolor2
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            },
            data: datelist

        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: chartsColor.ec1.facecolor3
                }
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: chartsColor.ec1.facecolor4
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            }
        },
        color: chartsColor.ec1.facecolor5,
        series: []
    }

    var _ITEM_CURRENT = {
        name: "今年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "solid"
            }
        },
        data: datalist.data[0].bm_cdata
    };
    var _ITEM_LAST = {
        name: "去年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "dashed"
            }
        },
        data: datalist.data[0].bm_ldata
    };
    option.series.push(_ITEM_CURRENT);
    option.series.push(_ITEM_LAST);
    groupEnergyChart.setOption(option);
}
/*水能耗-折线图*/
function energyCostChartFun(_DATA, _YEAR_LIST) {
    var _TB = 0;
    var _DATA_CURRENT = _DATA.totalcurrentyear;
    var _DATA_LAST = _DATA.totallastyear;

    $("#waterTotal").html(toFormatNum(_DATA_CURRENT));
    console.log(_DATA_CURRENT+"......_DATA_CURRENT");
    console.log(_DATA_LAST+"......_DATA_LAST");
    debugger;
    if(_DATA_LAST != 0){
        _TB = parseFloat(toDecimalCommon((_DATA_CURRENT - _DATA_LAST)/_DATA_LAST,4))*10000/100;
    }
    if(_TB >0){
        $("#waterTotal").next("span").addClass("energy-remind");
        $("#waterTotal").addClass("energy-remind");
        $("#waterTotal").closest(".energy-head").addClass("energy-snh-remind");
        $("#waterchangeRate").html("(" + toDecimal(_TB) + "↑)");
    }else{
        $("#waterTotal").closest(".energy-head").addClass("energy-snh");
        if(_TB == 0){
            $("#waterchangeRate").html("(" + 0 + "→)");
        }else{
            $("#waterchangeRate").html("(" + toDecimal(_TB) + "↓)");
        }
    }
    $("#waterEnergyChart").empty();
    waterEnergyChart = echarts.init(document.getElementById('waterEnergyChart'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '0',
            top: '10',
            right: '35',
            bottom: '0',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            },
            data: _YEAR_LIST

        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#e8e8e8',
                    type: 'dashed'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            }
        },
        color: chartsColor.ec2.facecolor1,
        series: []
    }
    var _ITEM_CURRENT = {
        name: "今年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "solid"
            }
        },
        data: _DATA.currentYear
    };
    var _ITEM_LAST = {
        name: "去年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "dashed"
            }
        },
        data: _DATA.lastyear
    };
    option.series.push(_ITEM_CURRENT);
    option.series.push(_ITEM_LAST);
    waterEnergyChart.setOption(option);
}
/*管理成本-折线图*/
function manegerCostChartFun(_DATA, _YEAR_LIST) {
    var _TB = 0;
    var _DATA_CURRENT = _DATA.totalcurrentyear;
    var _DATA_LAST = _DATA.totallastyear;
    $("#electricTotal").html(toFormatNum(_DATA_CURRENT));
    if(_DATA_LAST != 0){
        _TB = parseFloat(toDecimalCommon((_DATA_CURRENT - _DATA_LAST)/_DATA_LAST,4))*10000/100;
    }
    if(_TB >0){
        $("#electricTotal").next("span").addClass("energy-remind");
        $("#electricTotal").addClass("energy-remind");
        $("#electricTotal").closest(".energy-head").addClass("energy-dnh-remind");
        $("#elechangeRate").html("(" + toDecimal(_TB) + "↑)");
    }else{
        $("#electricTotal").closest(".energy-head").addClass("energy-dnh");
        if(_TB == 0){
            $("#elechangeRate").html("(" + 0 + "→)");
        }else{
            $("#elechangeRate").html("(" + toDecimal(_TB) + "↓)");
        }
    }
    $("#electricEnergyChart").empty();
    electricEnergyChart = echarts.init(document.getElementById('electricEnergyChart'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '0',
            top: '10',
            right: '35',
            bottom: '0',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            },
            data: _YEAR_LIST

        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#e8e8e8',
                    type: 'dashed'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            }
        },
        color: chartsColor.ec2.facecolor1,
        series: []
    }
    var _ITEM_CURRENT = {
        name: "今年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "solid"
            }
        },
        data: _DATA.currentYear
    };
    var _ITEM_LAST = {
        name: "去年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "dashed"
            }
        },
        data: _DATA.lastyear
    };
    option.series.push(_ITEM_CURRENT);
    option.series.push(_ITEM_LAST);
    electricEnergyChart.setOption(option);
}
/*其他成本-折线图*/
function otherCostChartFun(_DATA, _YEAR_LIST) {
    var _TB = 0;
    var _DATA_CURRENT = _DATA.totalcurrentyear;
    var _DATA_LAST = _DATA.totallastyear;
    $("#gasTotal").html(toFormatNum(_DATA_CURRENT));
    if(_DATA_LAST != 0){
        _TB = parseFloat(toDecimalCommon((_DATA_CURRENT - _DATA_LAST)/_DATA_LAST,4))*10000/100;
    }
    if(_TB >0){
        $("#gasTotal").next("span").addClass("energy-remind");
        $("#gasTotal").addClass("energy-remind");
        $("#gasTotal").closest(".energy-head").addClass("energy-qnh-remind");
        $("#gaschangeRate").html("(" + toDecimal(_TB) + "↑)");
    }else{
        $("#gasTotal").closest(".energy-head").addClass("energy-qnh");
        if(_TB == 0){
            $("#gaschangeRate").html("(" + 0 + "→)");
        }else{
            $("#gaschangeRate").html("(" + toDecimal(_TB) + "↓)");
        }
    }
    $("#gasEnergyChart").empty();
    gasEnergyChart = echarts.init(document.getElementById('gasEnergyChart'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '0',
            top: '10',
            right: '35',
            bottom: '0',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            },
            data: _YEAR_LIST

        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#e8e8e8',
                    type: 'dashed'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            }
        },
        color: chartsColor.ec2.facecolor1,
        series: []
    }
    var _ITEM_CURRENT = {
        name: "今年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "solid"
            }
        },
        data: _DATA.currentYear
    };
    var _ITEM_LAST = {
        name: "去年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "dashed"
            }
        },
        data: _DATA.lastyear
    };
    option.series.push(_ITEM_CURRENT);
    option.series.push(_ITEM_LAST);
    gasEnergyChart.setOption(option);
}
/*设备成本-折线图*/
function deviceCostChartFun(_DATA, _YEAR_LIST) {
    var _TB = 0;
    var _DATA_CURRENT = _DATA.totalcurrentyear;
    var _DATA_LAST = _DATA.totallastyear;
    $("#hotTotal").html(toFormatNum(_DATA_CURRENT));
    if(_DATA_LAST != 0){
        _TB = parseFloat(toDecimalCommon((_DATA_CURRENT - _DATA_LAST)/_DATA_LAST,4))*10000/100;
    }
    if(_TB >0){
        $("#hotTotal").next("span").addClass("energy-remind");
        $("#hotTotal").addClass("energy-remind");
        $("#hotTotal").closest(".energy-head").addClass("energy-rnh-remind");
        $("#hotchangeRate").html("(" + toDecimal(_TB) + "↑)");
    }else{
        $("#hotTotal").closest(".energy-head").addClass("energy-rnh");
        if(_TB == 0){
            $("#hotchangeRate").html("(" + 0 + "→)");
        }else{
            $("#hotchangeRate").html("(" + toDecimal(_TB) + "↓)");
        }
    }
    $("#hotEnergyChart").empty();
    hotEnergyChart = echarts.init(document.getElementById('hotEnergyChart'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '0',
            top: '10',
            right: '35',
            bottom: '0',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            },
            data: _YEAR_LIST

        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#e8e8e8',
                    type: 'dashed'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            }
        },
        color: chartsColor.ec2.facecolor1,
        series: []
    }
    var _ITEM_CURRENT = {
        name: "今年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "solid"
            }
        },
        data: _DATA.currentYear
    };
    var _ITEM_LAST = {
        name: "去年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "dashed"
            }
        },
        data: _DATA.lastyear
    };
    option.series.push(_ITEM_CURRENT);
    option.series.push(_ITEM_LAST);
    hotEnergyChart.setOption(option);
}
/*煤能耗-折线图*/
function labourCostChartFun(_DATA, _YEAR_LIST) {
    var _TB = 0;
    var _DATA_CURRENT = _DATA.totalcurrentyear;
    var _DATA_LAST = _DATA.totallastyear;
    $("#coalTotal").html(toFormatNum(_DATA_CURRENT));
    if(_DATA_LAST != 0){
        _TB = parseFloat(toDecimalCommon((_DATA_CURRENT - _DATA_LAST)/_DATA_LAST,4))*10000/100;
    }
    if(_TB >0){
        $("#coalTotal").next("span").addClass("energy-remind");
        $("#coalTotal").addClass("energy-remind");
        $("#coalTotal").closest(".energy-head").addClass("energy-mnh-remind");
        $("#coalchangeRate").html("(" + toDecimal(_TB) + "↑)");
    }else{
        $("#coalTotal").closest(".energy-head").addClass("energy-mnh");
        if(_TB == 0){
            $("#coalchangeRate").html("(" + 0 + "→)");
        }else{
            $("#coalchangeRate").html("(" + toDecimal(_TB) + "↓)");
        }
    }
    $("#coalEnergyChart").empty();
    coalEnergyChart = echarts.init(document.getElementById('coalEnergyChart'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '0',
            top: '10',
            right: '35',
            bottom: '0',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            },
            data: _YEAR_LIST

        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#e8e8e8',
                    type: 'dashed'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            }
        },
        color: chartsColor.ec2.facecolor1,
        series: []
    }
    var _ITEM_CURRENT = {
        name: "今年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "solid"
            }
        },
        data: _DATA.currentYear
    };
    var _ITEM_LAST = {
        name: "去年",
        type: 'line',
        symbol: 'circle',
        smooth: false,
        lineStyle: {
            normal: {
                type: "dashed"
            }
        },
        data: _DATA.lastyear
    };
    option.series.push(_ITEM_CURRENT);
    option.series.push(_ITEM_LAST);
    coalEnergyChart.setOption(option);
}
/*热力站成本排名*/
function chart01Fun(data) {
    echartsSelf({
        id: 'piechart_as',
        echartsConfig: {
            axisLabelRotate: '-50', //倾斜角度
            xAxisBoundaryGap: true,
            dataZoom: true,
            dataZoomstartValue: 0,
            dataZoomendValue: 9,
            axisLabelInterval:0,
            bg: 'row',
            xData: data.unitnames,
            series: [{
                type: 'bar',
                dataList: data.unitnumber,
                barWidth: 20
            }]
        }
    });
}
/*热源成本排名*/
function chart02Fun(data) {
    echartsSelf({
        id: 'linechart_as',
        echartsConfig: {
            axisLabelRotate: '-50', //倾斜角度
            xAxisBoundaryGap: true,
            dataZoom: true,
            dataZoomstartValue: 0,
            dataZoomendValue: 9,
            axisLabelInterval:0,
            bg: 'row',
            xData: data.unitnames,
            series: [{
                type: 'bar',
                dataList: data.unitnumber,
                barWidth: 20

            }]
        }
    });
}
