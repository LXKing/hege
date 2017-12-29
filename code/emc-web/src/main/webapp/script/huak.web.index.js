$(function () {
    console.info('12345678910.3--->'+toFormatNum(12345678910.3));
    console.info('1234567891.3--->'+toFormatNum(1234567891.3));
    console.info('123456789.3--->'+toFormatNum(123456789.3));
    console.info('12345678.3--->'+toFormatNum(12345678.3));
    console.info('1234567.3--->'+toFormatNum(1234567.3));
    console.info('123456.3--->'+toFormatNum(123456.3));
    console.info('12345.3--->'+toFormatNum(12345.3));
    console.info('1234.3--->'+toFormatNum(1234.3));
    console.info('123.3--->'+toFormatNum(123.3));
    console.info('12.3--->'+toFormatNum(12.3));
    chart12Fun();
    chart10Fun();
    var myChartEnergy;
    var myChartQualified;
    var myChartCarbon;
    try{
        $.ajax({
            url: _web + "/energy/top/all",
            type: "GET",
            data: $("#searchTools").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.all.eTotal == null || data.all.eTotal == '') {
                    $(".eTotal").html(0 + "Tce");
                } else {
                    $(".eTotal").html(toFormatNum(data.all.eTotal) + "Tce");
                }
                if (data.all.carbonTotal == null || data.all.carbonTotal == '') {
                    $(".carbonTotal").html(0 + "T");
                } else {
                    //$(".carbonTotal").html(data.all.carbonTotal + "T");
                    $(".carbonTotal").html(0 + "T");
                }

                if (data.all.costAll == null || data.all.costAll == '') {
                    $(".costAll").html(0 + "万元");
                } else {
                    $(".costAll").html(data.all.costAll + "万元");
                }
//                if (data.all.yardage == null || data.all.yardage == '') {
//                    $(".yardage").html(0 + " Tce/万m²");
//                } else {
//                    $(".yardage").html(data.all.yardage + " Tce/万m²");
//                }
//                $(".zyardage").html(2358 + " GJ/万m²");
//                if (data.all.priceArea == null || data.all.priceArea == '') {
//                    $(".priceArea").html(0 + " 万m²");
//                } else {
//                    $(".priceArea").html(data.all.priceArea + " 万m²");
//                }

            }
        });
        $.ajax({
            url: _web + "/energy/top/all1",
            type: "GET",
            data: $("#searchTools").serialize(),
            dataType: "json",
            success: function (data) {
//                if (data.all.eTotal == null || data.all.eTotal == '') {
//                    $(".eTotal").html(0 + " Tce");
//                } else {
//                    $(".eTotal").html(data.all.eTotal + " Tce");
//                }
//                if (data.all.carbonTotal == null || data.all.carbonTotal == '') {
//                    $(".carbonTotal").html(0 + " T");
//                } else {
//                    $(".carbonTotal").html(data.all.carbonTotal + " T");
//                }
//
//                if (data.all.costAll == null || data.all.costAll == '') {
//                    $(".costAll").html(0 + " 万元");
//                } else {
//                    $(".costAll").html(data.all.costAll + " 万元");
//                }
                if (data.all.yardage == null || data.all.yardage == '') {
                    $(".yardage").html(0 + "Tce/万m²");
                } else {
                    $(".yardage").html(toFormatNum(data.all.yardage) + "Tce/万m²");
                }
                $(".zyardage").html(0 + "GJ/万m²");
                if (data.all.priceArea == null || data.all.priceArea == '') {
                    $(".priceArea").html(0 + "万m²");
                } else {
                    $(".priceArea").html(data.all.priceArea + "万m²");
                }

            }
        });
        //供热源数据去取
        $.ajax({
            url: _web + "/energy/top/feed",
            type: "GET",
            data: $("#searchTools").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.all.eTotal == null || data.all.eTotal == '') {
                        $(".feTotal").html(0 + "Tce");
                } else {
                    $(".feTotal").html(toFormatNum(data.all.eTotal) + "Tce");
                }
                if (data.all.carbonTotal == null || data.all.carbonTotal == '') {
                    $(".fCarbonTotal").html(0 + "T");
                } else {
                    $(".fCarbonTotal").html(toFormatNum(data.all.carbonTotal) + "T");
                }
                if (data.all.costAll == null || data.all.costAll == '') {
                    $(".fCostAll").html(0 + "万元");
                } else {
                    $(".fCostAll").html(data.all.costAll + "万元");
                }

            }

        });
        //管网数据去取
        $.ajax({
            url: _web + "/energy/top/net",
            type: "GET",
            data: $("#searchTools").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.all.netLen == null || data.all.netLen == '') {
                    $(".netLen").html(0 + "km");
                } else {
                    $(".netLen").html(data.all.netLen + "km");
                }
                if (data.all.netCost == null || data.all.netCost == '') {
                    $(".netCost").html(0 + "万元");
                } else {
                    $(".netCost").html(data.all.netCost + "万元");
                }

            }

        });
        //换热站数据去取
        $.ajax({
            url: _web + "/energy/top/station",
            type: "GET",
            data: $("#searchTools").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.all.eTotal == null || data.all.eTotal == '') {
                    $(".seTotal").html(0 + "Tce");
                } else {
                    $(".seTotal").html(toFormatNum(data.all.eTotal) + "Tce");
                }
                if (data.all.carbonTotal == null || data.all.carbonTotal == '') {
                    $(".sCarbonTotal").html(0 + "T");
                } else {
                    $(".sCarbonTotal").html(data.all.carbonTotal + "T");
                }
                if (data.all.costAll == null || data.all.costAll == '') {
                    $(".sCostAll").html(0 + "万元");
                } else {
                    $(".sCostAll").html(data.all.costAll + "万元");
                }

            }

        });
        //管网数据去取
        $.ajax({
            url: _web + "/energy/top/line",
            type: "GET",
            data: $("#searchTools").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.all.lineLen == null || data.all.lineLen == '') {
                    $(".lineLen").html(0 + "km");
                } else {
                    $(".lineLen").html(data.all.lineLen + "km");
                }
                if (data.all.lineCost == null || data.all.lineCost == '') {
                    $(".lineCost").html(0 + " 万元");
                } else {
                    $(".lineCost").html(data.all.lineCost + "万元");
                }

            }

        });
        //民户数据去取
        $.ajax({
            url: _web + "/energy/top/room",
            type: "GET",
            data: $("#searchTools").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.all.rTotal == null || data.all.rTotal == '') {
                    $(".rTotal").html(0 + "Tce");
                } else {
                    $(".rTotal").html(0 + "Tce");
                }
                if (data.all.hgl == null || data.all.hgl == '') {
                    $(".hgl").html(0 + "%");
                } else {
                    $(".hgl").html("0%");
                }
                if (data.all.roomCost == null || data.all.roomCost == '') {
                    $(".roomCost").html(0 + "万元");
                } else {
//                    $(".roomCost").html(data.all.roomCost + " 万元");
                    $(".roomCost").html(0 + "万元");
                    $(".roomCost").html(0 + "万元");
                }

            }

        });
    }catch(error) {}

});

function typefun(these, code) {
    //$(these).addClass("on").siblings().removeClass("on");
    //$("#website").attr("src", _web + "/static/img/index/websitet_cs0" + code + ".png");
    if(faceKey == "dark") {
        $("#website").attr("src", _web + "/static/imgdark/index/websitet_cs0" + code + ".png");
    } else {
        $("#website").attr("src", _web + "/static/img/index/websitet_cs0" + code + ".png");
    }
    if (code == 6) {
//        $(".PeopleTabdiv").show();
//        $(".otherTabdiv").hide();
        myChartQualified.resize();
        myChartCarbon.resize();
        setCookie('toolOrgType', 5, 3);
        $("#toolOrgType").val(5);
    } else if (code == 5) {
//        $(".PeopleTabdiv").show();
        myChartQualified.resize();
        setCookie('toolOrgType', 4, 3);
        $("#toolOrgType").val(4);
    } else if (code == 4) {
//        $(".PeopleTabdiv").show();
        myChartQualified.resize();
        setCookie('toolOrgType', 3, 3);
        $("#toolOrgType").val(3);
    } else if (code == 3) {
//        $(".PeopleTabdiv").show();
        myChartQualified.resize();
        setCookie('toolOrgType', 2, 3);
        $("#toolOrgType").val(2);
    } else if (code == 2) {
//        $(".PeopleTabdiv").show();
        myChartQualified.resize();
        setCookie('toolOrgType', 1, 3);
        $("#toolOrgType").val(1);
    } else if (code == 1) {
//        $(".PeopleTabdiv").show();
        myChartQualified.resize();
        setCookie('toolOrgType', '', 3);
        $("#toolOrgType").val("");
    } else {
//        $(".PeopleTabdiv").show();
        myChartQualified.resize();
    }
    loadDataFun();
};

function loadDataFun(){


    //chart02Fun();

    //chart03Fun();

    //chart04Fun();

    //chart05Fun();

    //chart06Fun();





    //chart09Fun();

    chart11Fun();
    chart13Fun();
    chart08Fun();
    chart01Fun();
    chart07Fun();
}
var myChartEnergy;
var myChartQualified;
var chart01;
var chart02;
var chart03;
var chart04;
var chart05;
var chart11;
var chart12;
var chart10;
/*website*/
var websiteheight;
websiteheight = $("#website").height() - 12;
$(".index_menuBox").height(websiteheight);


/*组件-单耗趋势*/
function chart01Fun() {
    $.ajax({
        url: _web + '/energycomparison/list',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (result) {
            if(result.flag == true){
                if(null != result.object){
                    chart01Show(result.object.data, result.object.yearDate, result.object.other);
                }else{
                    chart01Show([],[],null);
                }
            }

        }
    });
}

/*单耗趋势-折线图*/
function chart01Show(datalist, datelist, other) {
    $("#chart01").empty();
    chart01 = echarts.init(document.getElementById('chart01'));
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
            axisTick: {show: false},
            splitArea: {
                show: true,
                areaStyle: {
                    color: chartsColor.areacolor
                }
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
            data: datelist

        },
        yAxis: {
            type: 'value',
            axisTick: {show: false},
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
        color: chartsColor.chart01.color,
        series: []
    }
    $.each(datalist, function (index, value) {
        var typeName = value.typeName;
        var typeLine = "";
        if (index == 0) {
            typeLine = "solid";

        }
        if (index == 1) {
            typeLine = "dashed";
        }
        var item = {
            name: typeName,
            type: 'line',
            symbol: 'circle',
            smooth: false,
            lineStyle: {normal: {type: typeLine}},
            data: value.dataList
        }

        option.series.push(item);
    });

    //配置上限值 下限值  今年平均值
    var upperList = [];
    var lowerList = [];
    var averageList = [];
    var labelStyle = {
        normal: {
            show: true,
            position: 'right',
            textStyle: {color: chartsColor.linefontcolor},
            formatter: function (params) {
                if (params.dataIndex == datelist.length - 1) {
                    return params.data
                } else {
                    return ""
                }

            }
        }
    }
    $.each(datelist, function (index, value) {
        upperList.push(parseFloat(other.upperLimit.data[index]))
        lowerList.push(parseFloat(other.lowerLimit.data[index]))
        averageList.push(parseFloat(other.average.data[index]))
    })
    option.series.push({
        name: other.upperLimit.typeName,
        type: 'line',
        symbolSize: 1,
        lineStyle: {normal: {type: 'dashed', color: '#e8afa6'}},
        label: labelStyle,
        data: upperList
    });
    option.series.push({
        name: other.lowerLimit.typeName,
        type: 'line',
        symbolSize: 1,
        lineStyle: {normal: {type: 'dashed', color: '#9ad9d7'}},
        label: labelStyle,
        data: lowerList
    });
    option.series.push({
        name: other.average.typeName,
        type: 'line',
        symbolSize: 1,
        lineStyle: {normal: {type: 'dashed', color: '#3B96DD'}},
        label: labelStyle,
        data: averageList
    });
    chart01.setOption(option);
}

/*分公司成本-柱状图*/
function chart02Fun() {
    $("#branchcost-year").html("2016");
    $("#chart02").empty();
    chart02 = echarts.init(document.getElementById('chart02'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '15',
            top: '10',
            right: '45',
            bottom: '10',
            containLabel: true
        },
        xAxis: {
            type: 'category',
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
            data: [
                "朝一",
                "朝二",
                "东城",
                "西城",
                "海淀",
                "丰台"
            ]

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
            splitArea: {
                show: true,
                areaStyle: {
                    color: chartsColor.areacolor
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
        color: chartsColor.chart02.color,
        series: []
    }
    var datalist = [
        {
            "typeName": "成本",
            "dataList": [
                "600",
                "300",
                "500",
                "400",
                "620",
                "320"
            ]
        }
    ]
    $.each(datalist, function (index, data) {
        var typeName = data.typeName;
        var item = {
            name: typeName,
            type: 'bar',
            barWidth: '20',
            markLine: {
                data: [
                    {
                        type: 'average',
                        name: '平均值'
                    }
                ]
            },
            data: data.dataList
        }
        option.series.push(item);
    });
    chart02.setOption(option);
}

/*分公司成本-柱状图*/

function chart02Fun() {
    $("#branchcost-year").html("2016");
    $("#chart02").empty();
    chart02 = echarts.init(document.getElementById('chart02'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '15',
            top: '10',
            right: '45',
            bottom: '10',
            containLabel: true
        },
        xAxis: {
            type: 'category',
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
            data: [
                "朝一",
                "朝二",
                "东城",
                "西城",
                "海淀",
                "丰台"
            ]

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
            splitArea: {
                show: true,
                areaStyle: {
                    color: chartsColor.areacolor
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
        color: chartsColor.chart02.color,
        series: []
    }
    var datalist = [
        {
            "typeName": "成本",
            "dataList": [
                "600",
                "300",
                "500",
                "400",
                "620",
                "320"
            ]
        }
    ]
    $.each(datalist, function (index, data) {
        var typeName = data.typeName;
        var item = {
            name: typeName,
            type: 'bar',
            barWidth: '20',
            markLine: {
                data: [
                    {type: 'average', name: '平均值'}
                ]
            },
            data: data.dataList
        }
        option.series.push(item);
    });
    chart02.setOption(option);
}

/*碳排放趋势-折线图*/
function chart03Fun() {
    $("#chart03").empty();
    chart03 = echarts.init(document.getElementById('chart03'));
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
            axisTick: {show: false},
            splitLine: {
                show: false
            },
            splitArea: {
                show: true,
                areaStyle: {
                    color: chartsColor.areacolor
                }
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
            data: [
                "15-11",
                "15-12",
                "16-01",
                "16-02",
                "16-03"
            ]

        },
        yAxis: {
            type: 'value',
            axisTick: {show: false},
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
        color: chartsColor.chart03.color,
        series: []
    }
    var datalist = [
        {
            "typeName": "今年",
            "dataList": [
                "600",
                "300",
                "500",
                "400",
                "620"
            ]
        },
        {
            "typeName": "去年",
            "dataList": [
                "810",
                "500",
                "700",
                "500",
                "720"
            ]
        }
    ]
    $.each(datalist, function (index, data) {
        var typeName = data.typeName;
        var typeLine = "";
        if (index == 0) {
            typeLine = "solid";
        }
        if (index == 1) {
            typeLine = "dashed";
        }
        var item = {
            name: typeName,
            type: 'line',
            symbol: 'circle',
            smooth: false,
            lineStyle: {normal: {type: typeLine}},
            data: data.dataList
        }
        option.series.push(item);
    });
    chart03.setOption(option);
}

/*公司成本-折线图*/
function chart04Fun() {
    $("#branchcost-year").html("2016");
    $("#chart04").empty();
    chart04 = echarts.init(document.getElementById('chart04'));
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
            axisTick: {show: false},
            splitLine: {
                show: false
            },
            splitArea: {
                show: true,
                areaStyle: {
                    color: chartsColor.areacolor
                }
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
            data: [
                "15-11",
                "15-12",
                "16-01",
                "16-02",
                "16-03"
            ]

        },
        yAxis: {
            type: 'value',
            axisTick: {show: false},
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
        color: ['#3B96DD', '#c2ccd3'],
        series: []
    }
    var datalist = [
        {
            "typeName": "今年",
            "dataList": [
                "600",
                "300",
                "500",
                "400",
                "620"
            ]
        },
        {
            "typeName": "去年",
            "dataList": [
                "810",
                "500",
                "700",
                "500",
                "720"
            ]
        }
    ]
    $.each(datalist, function (index, data) {
        var typeName = data.typeName;
        var typeLine = "";
        if (index == 0) {
            typeLine = "solid";
        }
        if (index == 1) {
            typeLine = "dashed";
        }
        var item = {
            name: typeName,
            type: 'line',
            symbol: 'circle',
            smooth: false,
            lineStyle: {normal: {type: typeLine}},
            data: data.dataList
        }
        option.series.push(item);
    });
    chart04.setOption(option);
}

/*成本明细-饼图*/
function chart05Fun() {
    $.ajax({
        url: _web + '/component/costDetail',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (result) {
            var energy = result.object.ccs;
            var device = result.object.device;
            var labor = result.object.labor;
            var manage = result.object.manage;
            var other = result.object.other;
            var total = result.object.total_sum;
            var tb_flag = result.object.flag_total;
            var total_tb = result.object.tb_total;
            var tbl = "";
            if (tb_flag == 'true') {
                tbl = "(" + total_tb + "↑)";

            }
            if (tb_flag == 'false') {
                tbl = "(" + total_tb + "↓)";
            }
            if (tb_flag == 'null') {
                tbl = "(" + total_tb + "→)";
            }
            /*能源费*/
            $("#energy_cost").html(energy);
            var tb_flag = result.object.flag_ccs;
            var total_tb = result.object.tb_css;
            if (tb_flag == 'true') {
                $("#energy_tb").html("(" + total_tb + "↑)");
                $("#energy_tb").attr('class', ' cost-list-remind');

            }
            if (tb_flag == 'false') {
                $("#energy_tb").html("(" + total_tb + "↓)");
                $("#energy_tb").attr('class', ' cost-list-relax');
            }
            if (tb_flag == 'null') {
                $("#energy_tb").html("(" + total_tb + "→)");
            }
            /*设备费*/
            $("#device_cost").html(device);
            var tb_flag = result.object.flag_device;
            var total_tb = result.object.tb_device;
            if (tb_flag == 'true') {
                $("#device_tb").html("(" + total_tb + "↑)");
                $("#device_tb").attr('class', ' cost-list-remind');
            }
            if (tb_flag == 'false') {
                $("#device_tb").html("(" + total_tb + "↓)");
                $("#device_tb").attr('class', ' cost-list-relax');
            }
            if (tb_flag == 'null') {
                $("#device_tb").html("(" + total_tb + "→)");
            }
            /*人工费*/
            $("#labor_cost").html(labor);
            var tb_flag = result.object.flag_labor;
            var total_tb = result.object.tb_labor;
            if (tb_flag == 'true') {
                $("#labor_tb").html("(" + total_tb + "↑)");
                $("#labor_tb").attr('class', ' cost-list-remind');
            }
            if (tb_flag == 'false') {
                $("#labor_tb").html("(" + total_tb + "↓)");
                $("#labor_tb").attr('class', ' cost-list-relax');
            }
            if (tb_flag == 'null') {
                $("#labor_tb").html("(" + total_tb + "→)");
            }
            /*管理费*/
            $("#manage_cost").html(manage);
            var tb_flag = result.object.flag_manage;
            var total_tb = result.object.tb_manage;
            if (tb_flag == 'true') {
                $("#manage_tb").html("(" + total_tb + "↑)");
                $("#manage_tb").attr('class', ' cost-list-remind');
            }
            if (tb_flag == 'false') {
                $("#manage_tb").html("(" + total_tb + "↓)");
                $("#manage_tb").attr('class', ' cost-list-relax');
            }
            if (tb_flag == 'null') {
                $("#manage_tb").html("(" + total_tb + "→)");
            }
            /*其他费*/
            $("#other_cost").html(other);
            var tb_flag = result.object.flag_other;
            var total_tb = result.object.tb_other;
            if (tb_flag == 'true') {
                $("#other_tb").html("(" + total_tb + "↑)");
                $("#other_tb").attr('class', ' cost-list-remind');
            }
            if (tb_flag == 'false') {
                $("#other_tb").html("(" + total_tb + "↓)");
                $("#other_tb").attr('class', ' cost-list-relax');
            }
            if (tb_flag == 'null') {
                $("#other_tb").html("(" + total_tb + "→)");
            }
            initChart05(energy, device, manage, labor, other, total, tbl);
        }
    });

}
/*成本明细-饼图初始化*/
function initChart05(energy, device, manage, labor, other, total, tbl) {
    $("#chart05").empty();
    chart05 = echarts.init(document.getElementById('chart05'));
    var option = {
        title: {
            text: total,
            subtext: '成本总量（万元）\n（'+tbl+'%↓）', //↑↓
            x: 'center',
            y: 'center',
            top: '40%',
            itemGap: 0,
            textStyle: {
                color: chartsColor.chart05.facecolor1,
                fontFamily:'Eurostile LT',
                fontSize: 44,
                fontWeight: 'normal'
            },
            subtextStyle: {
                color: chartsColor.chart05.facecolor2,
                fontFamily: '微软雅黑',
                fontSize: 12,
                fontWeight: 'normal'
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)",
            show: true
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: []
        },
        toolbox: {
            show: false,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: false
                },
                magicType: {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: false,
        color: chartsColor.chart05.facecolor6,
        series: [
            {
                type: 'pie',
                radius: chartsColor.chart05.facecolor5,
                silent: true,
                itemStyle: {
                    normal: {
                        color: chartsColor.chart05.facecolor3,
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    }
                },
                data: [
                    {
                        value: 1,
                        name: '圈',
                        selected: false,
                        hoverAnimation: false
                    }
                ]
            },
            {
                name: '成本明细',
                type: 'pie',
                radius: ['70%', '80%'],

                // for funnel
                x: '60%',
                width: '35%',
                funnelAlign: 'left',
                itemStyle: {
                    normal: {
                        borderColor: chartsColor.chart05.facecolor4,
                        borderWidth: '2',
                        label: {
                            show: false
                        }
                    }
                },


                data: [
                    {value: labor, name: '人工费'},
                    {value: manage, name: '管理费'},
                    {value: other, name: '其他费'},
                    {value: energy, name: '能源费'},
                    {value: device, name: '设备费'}
                ]
            }
        ]
    };
    chart05.setOption(option);
}

function chart06Fun() {
    //占比-赋值即可
    var level_ = 0.75;
    //$("#level_num").text((level_ * 100) + '%');
    var wave = (function () {
        var ctx;
        var waveImage;
        var canvasWidth;
        var canvasHeight;
        var needAnimate = false;

        function init(callback) {
            var wave = document.getElementById('chart06');
            var canvas = document.createElement('canvas');
            if (!canvas.getContext) return;
            ctx = canvas.getContext('2d');
            canvasWidth = wave.offsetWidth;
            canvasHeight = wave.offsetHeight;
            canvas.setAttribute('width', canvasWidth);
            canvas.setAttribute('height', canvasHeight);
            wave.appendChild(canvas);
            waveImage = new Image();
            waveImage.onload = function () {
                waveImage.onload = null;
                callback();
            }
            waveImage.src = _web + '/static/img/index/wave.png';
        }

        function animate() {
            var waveX = 0;
            var waveY = 0;
            var waveX_min = -220;
            var waveY_max = canvasHeight * level_;
            var requestAnimationFrame =
                window.requestAnimationFrame ||
                window.mozRequestAnimationFrame ||
                window.webkitRequestAnimationFrame ||
                window.msRequestAnimationFrame ||
                function (callback) {
                    window.setTimeout(callback, 1000 / 60);
                };

            function loop() {
                ctx.clearRect(0, 0, canvasWidth, canvasHeight);
                if (!needAnimate) return;
                if (waveY < waveY_max) waveY += 1.5;
                if (waveX < waveX_min) waveX = 0; else waveX -= 3;

                ctx.globalCompositeOperation = 'source-over';
                ctx.beginPath();
                ctx.arc(canvasWidth / 2, canvasHeight / 2, canvasHeight / 2, 0, Math.PI * 2, true);
                ctx.closePath();
                ctx.fill();

                ctx.globalCompositeOperation = 'source-in';
                ctx.drawImage(waveImage, waveX, canvasHeight - waveY);

                requestAnimationFrame(loop);
            }

            loop();
        }

        function start() {
            if (!ctx) return init(start);
            needAnimate = true;
            setTimeout(function () {
                if (needAnimate) animate();
            }, 500);
        }

         function stop() {
            needAnimate = false;
        }

        return {start: start, stop: stop};
    }());
    wave.start();
}
/*能耗明细*/
function chart07Fun() {
    $.ajax({
        url: _web + '/energyConsumption/list',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (result) {
            if (result.flag) {
                /*仪表盘*/
                var kedu1 = result.object.kedu1; //蓝色的刻度
                var pcd = result.object.pcd; //偏差度
                var pcdz =result.object.pcdz; //偏差值
                var currentPlan = result.object.currentPlan;
                var bm_total = result.object.bm_total;
                initChart(kedu1, currentPlan, bm_total);
                pcd = toDecimal(pcd);
                pcdz =  toDecimal(pcdz);
                $("#pc_plan_percent").html("偏差度(" + toFormatNum(pcd) + "%)");
                $("#pc_plan").html(pcdz);
                /*总标煤展示*/
                $("#bm_total").html(toFormatNum(bm_total));
                var tb_flag = result.object.total_flag;
                var total_tb = result.object.total_tb;
                if (tb_flag == true) {
                    $("#total_tb").html("(" + total_tb + "↑)");
                    $("#total_tb").attr("class", "cb_color");//红
                }
                if (tb_flag == false) {
                    $("#total_tb").html("(" + total_tb + "↓)");
                    $("#total_tb").attr("class", "zc_color");//绿
                }
                if (tb_flag == null) {
                    $("#total_tb").html("(" + total_tb + "→)");
                    $("#total_tb").attr("class", "");//白
                }
                /*水*/
                $("#whater").html(toFormatNum(result.object.whater,0) + "T");
                var tb_flag = result.object.whater_flag;
                var total_tb = result.object.whater_tb;
                if (tb_flag == true) {
                    $("#whater_tb").html("(" + total_tb + "↑)");
                    $("#whater_tb").attr("class", "energyBoxLegendListPara energyBoxLegendListPara_cb");
                    $("#whater").attr("class", "energyBoxLegendListText energyBoxLegendListText_cb");
                    $("#w1").addClass("energyBoxLegendListIcon01_cb");
                }
                if (tb_flag == false) {
                    $("#whater_tb").html("(" + total_tb + "↓)");
                    $("#whater_tb").attr("class", "energyBoxLegendListPara energyBoxLegendListPara_cd");
                    $("#whater").attr("class", "energyBoxLegendListText energyBoxLegendListText_cd");
                }
                if (tb_flag == null) {
                    $("#whater_tb").html("(" + total_tb + "→)");
                }
                /*电*/
                $("#electric").html(toFormatNum(result.object.electric,0) + "kW·h");
                var tb_flag = result.object.electric_flag;
                var total_tb = result.object.electric_tb;
                if (tb_flag == true) {
                    $("#electric_tb").html("(" + total_tb + "↑)");
                    $("#electric_tb").attr("class", "energyBoxLegendListPara energyBoxLegendListPara_cb");
                    $("#electric").attr("class", "energyBoxLegendListText energyBoxLegendListText_cb");
                    $("#e1").addClass("energyBoxLegendListIcon02_cb");
                }
                if (tb_flag == false) {
                    $("#electric_tb").html("(" + total_tb + "↓)");
                    $("#electric_tb").attr("class", "energyBoxLegendListPara energyBoxLegendListPara_cd");
                    $("#electric").attr("class", "energyBoxLegendListText energyBoxLegendListText_cd");
                }
                if (tb_flag == null) {
                    $("#electric_tb").html("(" + total_tb + "→)");
                }
                /*气*/
                $("#gas").html(toFormatNum(result.object.gas,0) + "m³");
                var tb_flag = result.object.gas_flag;
                var total_tb = result.object.gas_tb;
                if (tb_flag == true) {
                    $("#gas_tb").html("(" + total_tb + "↑)");
                    $("#gas_tb").attr("class", "energyBoxLegendListPara energyBoxLegendListPara_cb");
                    $("#gas").attr("class", "energyBoxLegendListText energyBoxLegendListText_cb");
                    $("#g1").addClass("energyBoxLegendListIcon03_cb");
                }
                if (tb_flag == false) {
                    $("#gas_tb").html("(" + total_tb + "↓)");
                    $("#gas_tb").attr("class", "energyBoxLegendListPara energyBoxLegendListPara_cd");
                    $("#gas").attr("class", "energyBoxLegendListText energyBoxLegendListText_cd");
                }
                if (tb_flag == null) {
                    $("#gas_tb").html("(" + total_tb + "→)");
                }
                /*热*/
                $("#heat").html(toFormatNum(result.object.heat,0) + "GJ");
                var tb_flag = result.object.heat_flag;
                var total_tb = result.object.heat_tb;
                if (tb_flag == true) {
                    $("#heat_tb").html("(" + total_tb + "↑)");
                    $("#heat_tb").attr("class", "energyBoxLegendListPara energyBoxLegendListPara_cb");
                    $("#heat").attr("class", "energyBoxLegendListText energyBoxLegendListText_cb");
                    $("#h1").addClass("energyBoxLegendListIcon05_cb");
                }
                if (tb_flag == false) {
                    $("#heat_tb").html("(" + total_tb + "↓)");
                    $("#heat_tb").attr("class", "energyBoxLegendListPara energyBoxLegendListPara_cd");
                    $("#heat").attr("class", "energyBoxLegendListText energyBoxLegendListText_cd");
                }
                if (tb_flag == null) {
                    $("#heat_tb").html("(" + total_tb + "→)");
                }
                /*煤*/
                $("#coal").html(toFormatNum(result.object.coal,0) + "T");
                var tb_flag = result.object.coal_flag;
                var total_tb = result.object.coal_tb;
                if (tb_flag == true) {
                    $("#coal_tb").html("(" + total_tb + "↑)");
                    $("#coal_tb").attr("class", "energyBoxLegendListPara energyBoxLegendListPara_cb");
                    $("#coal").attr("class", "energyBoxLegendListText energyBoxLegendListText_cb");
                    $("#c1").addClass("energyBoxLegendListIcon04_cb");
                }
                if (tb_flag == false) {
                    $("#coal_tb").html("(" + total_tb + "↓)");
                    $("#coal_tb").attr("class", "energyBoxLegendListPara energyBoxLegendListPara_cd");
                    $("#coal").attr("class", "energyBoxLegendListText energyBoxLegendListText_cd");
                }
                if (tb_flag == null) {
                    $("#coal_tb").html("(" + total_tb + "→)");
                }
            } else {}
        },
        error: function (e) {

        }
    });

}

/*能耗明细图表初始化*/
function initChart(kedu1, mx, bm_total) {
    bm_total = toDecimal(bm_total);
    myChartEnergy = echarts.init(document.getElementById('EnergyChart'));
    var colorvalue = null;
    var max = 0;
    if (mx == 0) {
        max = bm_total *3;
        colorvalue = [ [kedu1, '#df5f4a'],[0.75, '#df5f4a'],[1, '#df5f4a'] ];
    }else{
        max = parseInt(mx/0.75).toFixed(0);
        colorvalue = [ [kedu1, '#3b96db'],[0.75, '#32bbb6'],[1, '#df5f4a'] ];
    }
    var option1 = {
        tooltip: {
            formatter: "{a} <br/>{c} {b}"
        },
        series: [
            {
                name: '能耗',
                type: 'gauge',
                z: 3,
                min: 0,
                max: max,
                startAngle: 180,
                endAngle: 0,
                splitNumber: -1,
                radius: '100%',
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: [
                            [0.5, '#3b96db'],
                            [1, '#df5f4a']
                        ],
                        width: 10
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#d44243',
                        label:{
                            show: true,
                            position:'inner'
                        }
                    }
                },
                detail: {
                    show: false,
                    formatter: '{value}',
                    textStyle: {
                        fontSize: 5
                    }
                },
                data: [
                    {
                        value: "100"
                    }
                ]
            },
            {

                type: 'gauge',
                z: 4,
                min: 0,
                max: max,
                startAngle: 180,
                endAngle: 0,
                radius: '78%',
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: [
                            [1, chartsColor.chart07.facecolor1]
                        ],
                      width: 2
                    }
                },
                itemStyle: {
                    normal: {
                        opacity: 0,
                        color: '#fff'
                    }
                },
                axisTick: {
                    lineStyle: {
                        color: chartsColor.chart07.facecolor1,
                        width: 1
                    },
                    length: 2,
                    splitNumber: 1
                },
                splitLine: {
                    show: true,
                    length: 10,
                    lineStyle: {
                        color: chartsColor.chart07.facecolor1,
                        width: 1
                    }
                },
                axisLabel: {
                    formatter:function(e){
                        return toFormatNumbers(e,0);
                    },
                    textStyle: {
                        color: chartsColor.chart07.facecolor1
                    }

                },
                detail: {
                    show: false
                },
                data: [
                    {
                        value: bm_total
                    }
                ]
            }

        ]
    }

    option1.series[0].axisLine.lineStyle.color = colorvalue;
    option1.series[0].data[0].value = bm_total;//这个值设大点
    myChartEnergy.setOption(option1);
}

/*保留小数位*/
function toDecimal(x,num) {
    num = num == undefined ?1:num;
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = f.toFixed(num);
    return f;
}

/* 返回float*/
function returnFloat(value) {
    var value = Math.round(parseFloat(value) * 100) / 100;
    var xsd = value.toString().split(".");
    if (xsd.length > 1) {
        value = xsd[0];
        return value;
    }
}

/*居民 合格率趋势*/
function chart08Fun() {
    $.ajax({
        url: _web + '/roomtemperature/list',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: $("#searchTools").serialize()+"&"+$.param({min:18,max:22}),
        dataType: "json",
        success: function (result) {
            if (result.flag == true) {
                if (result.object.bar != null)
                    chart08Bar(result.object.bar);
                chart08Sd(result.object.datas, result.object.times, result.object.low, result.object.range, result.object.high)
            }
        }
    });


}

/*居民 合格率趋势图 bar*/
function chart08Bar(data) {
    var databar = [];
    var color = ['red', '#32bbb6', 'blue'];
    data.forEach(function (value, index, array) {
        var item = {
            value: value.value,
            color: color[index],
            text: value.text
        };
        databar.push(item);
    });
    var barchartdiv = $("#barchart");
    var barcharthtml = "";
    for (var i = 0; i < databar.length; i++) {
        var width = 25;
        if(parseInt(databar[i].value) >=50){ width = 50;};
        barcharthtml += "<div style='width:" + width + "%'><p style='color:" + databar[i].color + "'>" + toDecimalCommon(databar[i].value,1) + "%</p><div><span style='background:" + databar[i].color + "'><span></div><p>" + databar[i].text + "</p></div>";
    }
    barchartdiv.html(barcharthtml);
}

/*居民 合格率趋势图 散点图*/
function chart08Sd(data, times) {
    var obj = new Array();
    $.each(data, function (index, value) {
        var temp = [];
        temp.push(value.times);
        temp.push(value.temps);
        temp.push(value.station);
        temp.push(value.village);
        temp.push(value.roomcode);
        obj.push(temp);
    });

    /*居民 合格率趋势*/
    myChartQualified = echarts.init(document.getElementById('QualifiedChart'));

    // See https://github.com/ecomfe/echarts-stat
    var myRegression = ecStat.regression('linear', obj);
    myRegression.points.sort(function (a, b) {
        return a[0] - b[0];
    });
    optionQualified = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            left: '15',
            top: '10',
            right: '30',
            bottom: '10',
            containLabel: true
        },
        xAxis: {
            type: 'category',
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
            data: times
        },
        yAxis: {
            type: 'value',
            max: 30,
            min: 10,
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
        series: [
            {
                name: '室温',
                type: 'scatter',
                symbolSize: function (data) {
                    return 2;
                },
                markLine: {
                    lineStyle: {
                        normal: {
                            color: 'red'
                        }
                    },
                    data: [
                        {
                            type: 'average',
                            name: '平均值'
                        }
                    ]
                },
                itemStyle: {
                    normal: {
                        color: chartsColor.chart08.facecolor1
                    }
                },
                data: obj
            }
        ]
    };
    myChartQualified.setOption(optionQualified);
}

/*居民室温合格率--chartCarbon*/
function chart09Fun() {
    myChartCarbon = echarts.init(document.getElementById('chartCarbon'));
    optionCarbon = {
        title: {
            text: "25.2",
            subtext: '室温合格率（%）\n（1.6%↓）',  //↑↓
            x: 'center',
            y: 'center',
            itemGap: -5,
            textStyle: {
                color: chartsColor.chart09.facecolor4,
                fontFamily: '微软雅黑',
                fontSize: 44,
                fontWeight: 'normal'
            },
            subtextStyle: {
                color: chartsColor.chart09.facecolor5,
                fontFamily: '微软雅黑',
                fontSize: 12,
                fontWeight: 'normal'
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },
        series: [
            {
                type: 'pie',
                radius: chartsColor.chart09.facecolor6,
                silent: true,
                itemStyle: {
                    normal: {
                        color: chartsColor.chart09.facecolor1,
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    }
                },
                data: [
                    {value: 1, name: '背景', selected: false, hoverAnimation: false}
                ]
            },
            {
                name: '合格率',
                type: 'pie',
                radius: ['70%', '80%'],
                itemStyle: {
                    normal: {
                        color: chartsColor.chart09.facecolor2,
                        label: {show: false}
                    }
                },
                data: [
                    {value: 1, name: '圈', selected: false, hoverAnimation: false}
                ]
            },
            {
                name: '合格率',
                type: 'pie',
                radius: ['72%', '80%'],
                funnelAlign: 'left',
                itemStyle: {
                    normal: {
                        label: {show: false}
                    }
                },
                data: [
                    {
                        value: 206.4,
                        name: '合格率',
                        itemStyle: {
                            normal: {
                                color: chartsColor.chart09.facecolor3
                            }
                        }
                    },
                    {
                        value: 800,
                        name: '占位',
                        hoverAnimation: false,
                        tooltip: {
                            show: false
                        },
                        itemStyle: {
                            normal: {
                                color: 'rgba(0,0,0,0)',
                                label: {show: false},
                                labelLine: {show: false}
                            },
                            emphasis: {
                                color: 'rgba(0,0,0,0)'
                            }
                        }
                    }
                ]
            }
        ]
    };
    myChartCarbon.setOption(optionCarbon);
}

/*组件-天气预报*/
function chart10Fun() {
    $.ajax({
        url: _web + '/weathercondition/list',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        dataType: "json",
        success: function (result) {
            $(".chart-content.clearfix").empty();
            if (result.flag == true) {
                if ( result.object.currentWeather != undefined) {
                    var flag =  result.object.aqi == undefined ;
                    var aqi = 0;
                    if(flag){ aqi= "无";}else{ aqi=result.object.aqi.aqiLevel;}
                    var dates = new Pdate();
                    var d = getNowFormatDate();
                    var date = dates.format(flag ? d:result.object.aqi.reportDate);
                    var s = dates.showCal(flag ? d:result.object.aqi.reportDate);
                    var html = '<div class="cb-header clearfix" id="dates">' +
                        '<span class="cb-title" >' + date + ' ' + result.object.currentWeather.weekDay + ' 农历' + s + '</span>' +
                        '<div class="cb-title-right ">空气质量：<span >' + aqi + '</span></div></div>';
                    $(".chart-content.clearfix").append(html);
                    var html2 = "<ul>";
                    var weathericon = 'wather weather' + result.object.currentWeather.weatherIcon;
                    var curentwindp=  result.object.currentForcast.wind+""+ result.object.currentForcast.winp;
                    var tempcureent = result.object.currentWeather.weatherCurrent;
                    var forcastweather = result.object.currentForcast.weather;
                    if(curentwindp.length>4){
                        curentwindp = curentwindp.substr(0,4)+"...";
                    }
                    if(tempcureent.length>4){
                        tempcureent = tempcureent.substr(0,4)+"...";
                    }
                    if(forcastweather.length>4){
                        forcastweather = forcastweather.substr(0,4)+"...";
                    }
                    html2 += " <li> <div class='" + weathericon + "'></div>" +
                        "<div class='detail clearfix'> <div>" + result.object.currentWeather.temperatureCurr + "</div>" +
                        "<div><p>℃</p><p title='"+ result.object.currentWeather.weatherCurrent+"'>" + tempcureent + "（实时）</p></div></div>" +
                        "<h3>" + result.object.currentForcast.tempLow + "~" + result.object.currentForcast.tempHigh + "℃<h4 title='"+result.object.currentForcast.weather+"'>" + forcastweather  +
                        "</h4><h5 title='"+ result.object.currentForcast.wind+""+ result.object.currentForcast.winp+"'>" + curentwindp + "</h5>" +
                        " </li>"
                    if (result.object.weekForcast.length > 0) {
                        result.object.weekForcast.forEach(function (value, index, array) {
                            var tempwindp =   value.wind + "" + value.winp ;
                            var forcustweather =   value.weather ;
                            if(curentwindp.length>4){
                                tempwindp = tempwindp.substr(0,4)+"...";
                            }
                            if(forcustweather.length>4){
                                forcustweather = forcustweather.substr(0,4)+"...";
                            }
                            var iconclass = 'wather weather' + value.weatherIcon;
                            html2 += " <li>" +
                                "<h1>" + value.week + "</h1>" +
                                "<h2>" + dates.format(value.reportDate) + "</h2>" +
                                "<div class='" + iconclass + "'></div>" +
                                "<h3>" + value.tempLow + "~" + value.tempHigh + "℃</h3>" +
                                "<h4 title='" + value.weather + "'>" +forcustweather + "</h4>" +
                                "<h5 title='" + value.wind + "" + value.winp + "'>"+tempwindp+"</h5>" +
                                "</li>";
                        });
                    }
                    html2 += "</ul>";
                    $(".chart-content.clearfix").append(html2);
                }else{

                }
                initchart10(result.object.weathers.hour, result.object.weathers.temp);
            }

        }

    });

}
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
/*天气预报图表*/
function initchart10(hours, temps) {
    $("#chart10").empty();
    chart10 = echarts.init(document.getElementById('chart10'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '15',
            top: '10',
            right: '10',
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
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: chartsColor.chart10.facecolor1
                }
            },
            data: hours

        },

        yAxis: {
            type: 'value',
            max: 50,
            axisTick: {
                show: false
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: chartsColor.chart10.facecolor3,
                    type: 'solid'
                }
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: chartsColor.chart10.facecolor1
                }
            }
        },
        series: [
            {
                name: "温度",
                type: 'line',
                symbol: 'circle',
                smooth: false,
                hoverAnimation: false,
                symbolSize: [8, 8],
                lineStyle: {
                    normal: {
                        color: chartsColor.chart10.facecolor2
                    }
                },
                itemStyle: {
                    normal: {
                        color: chartsColor.chart10.facecolor2,
                        borderWidth: 1,
                        borderColor: "#fff"
                    }
                },
                data: temps
            }
        ]
    }

    chart10.setOption(option);
}

/*组件-昨天、今天、前天 日单耗对比*/
function chart13Fun() {
    //trend 1升  2降
    $.ajax({
        url: _web + '/recentdetail/list',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (result) {
            console.info(result);
            if (result.flag == true) {
                var recentdata = result.object;
                var recenthtml = "";
                var recentlisthtml = "";
                //var unitlist = ['T/万m²', 'kW·h/万m²', 'm³/万m²', 'GJ/万m²'];
                for (var i = 0; i < recentdata.length; i++) {
                    recenthtml += "<div>" + recentdata[i].value + "<p>标煤</p></div>";
                    recentlisthtml += "<ul>";
                    for (var j = 0; j < recentdata[i].list.length; j++) {
                        recentlisthtml += "<li><p><span>" + recentdata[i].list[j].value + "</span></p><span>" + recentdata[i].list[j].value2 + "</span><span  >" + (recentdata[i].list[j].trend == 1 ? "↑" : (recentdata[i].list[j].trend == 2 ? "↓" : "→")) + "</span></li>";
                    }
                    recentlisthtml += "</ul>";
                }
                $("#recentall").html(recenthtml);
                $("#recentlist").html(recentlisthtml);
            }

        }

    });


}

function chart11Fun() {
    $("#chart11").empty();
    chart11 = echarts.init(document.getElementById('chart11'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '15',
            top: '10',
            right: '20',
            bottom: '10',
            containLabel: true
        },
        xAxis: {
            type: 'category',
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
            data: ['1月', '2月', '3月', '4月', '5月']

        },
        yAxis: [
            {
                name: '条',
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
                splitArea: {
                    show: true,
                    areaStyle: {
                        color: chartsColor.areacolor
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
            {
                type: 'value',
                name: '线',
                position: 'right',
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
            }
        ],
        color: chartsColor.chart11.facecolor1,

        series: [
            {
                name: '条',
                type: 'bar',
                barWidth: '20',
                data: [10, 20, 10, 30, 40]
            },
            {
                name: '线',
                yAxisIndex: 1,
                type: 'line',
                label: {
                    normal: {
                        show: false

                    }
                },
            smooth: true,
            symbol: 'circle',
            symbolSize: 9,
            showSymbol: false,
            lineStyle: {
                normal: {
                    shadowOffsetY: 2,
                    shadowColor: '#f0f1f2'
                }
            },
            data: [1, 13, 37, 35, 15]
        }]
    }

    chart11.setOption(option);
}

/*组件-健康指数*/
function chart12Fun() {
    $(".barwrap").show();
    $(".barwrap2").hide();
    var titledata = ['工况运行', '经济运行', '服务情况', '室温报警'];
    initchart12(titledata);
    $.ajax({
        url: _web + "/healthcheck/list",
        type: "POST",
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (data) {
            doCss(data);
        }
    });
}

/*组件-健康指数检测初始化*/
function initchart12(titledata){
    $("#chart12").html("");
    //value1 严重 value2中度 value3轻度
    var data = []; //参数数量有几个写几个 自动识别前台样式 1工况运行 2经济运行 3 服务情况 4作业管理
    var html = "";
    html += "<div>";
    for(var i = 0; i < titledata.length; i++) {
//        var classname = "runc";
        html += "<div id='"+(parseInt(i)+parseInt(1))+"' class='runc'><h1>" + titledata[i] + "</h1>" +
            "<div><div><div></div></div></div></div>";
    }
    html += "</div>";
    html += "<div>";
    for(var i = 0; i < titledata.length; i++){
        var classname = "runc";
        html += "<div id='"+i+1+"'  class='" + classname + "'><div class='jiao'></div><ul>";
        if("工况运行"==titledata[i]){
            html += "<li>一级<span class='a'></span></li>";
            html += "<li>二级<span class='b'></span></li>";
            html += "<li>三级<span class='c'></span></li>";
            html += "<li>四级<span class='d'></span></li>";
        }else if("经济运行"==titledata[i]) {
            html += "<li>行标<span class='a'></span></li>";
            html += "<li>地标<span class='b'></span></li>";
            html += "<li>企标<span class='c'></span></li>";
        }else if("室温报警"==titledata[i]){
            html += "<li>低温<span class='a'></span></li>";
            html += "<li>高温<span class='b'></span></li>";
        }else{
            html += "<li>严重<span class='a'></span></li>";
            html += "<li>中度<span class='b'></span></li>";
            html += "<li>轻度<span class='c'></span></li>";
        }
        html += "</ul></div>";

    }
    html += "</div>";
    $("#chart12").html(html);
}

/*组件-健康指数定时器改变样式*/
function doCss(data){
    timerTask(1,data);
    var bar = document.getElementById("barjc");
    bar.style.width=parseInt(0)+ "%";
    $("#totals").html("100%");
    $("#checktitle").html("正在检测服务情况...");
    if( bar.style.width == "0%"){
        run();
    }
}

/*组件-健康指数-定时器任务1*/
function timerTask(i,data){
    $("#"+i).attr("class","runb");
    setTimeout(function(){
        if(i == 1){
            $("#"+i).attr("class","run"+data.object.gkyx.css);
            $($("#"+(i-1)+1).find("ul li")[0]).find("span").html(data.object.gkyx.level1);
            $($("#"+(i-1)+1).find("ul li")[1]).find("span").html(data.object.gkyx.level2);
            $($("#"+(i-1)+1).find("ul li")[2]).find("span").html(data.object.gkyx.level3);
            $($("#"+(i-1)+1).find("ul li")[3]).find("span").html(data.object.gkyx.level4);
            if(data.object.gkyx.css == 'm'){
                $("#"+(i-1)+1).attr("class","runb");
            }else{
                $("#"+(i-1)+1).attr("class","run"+data.object.gkyx.css);
            }

        }else if( i == 2){
            $("#"+i).attr("class","run"+data.object.jjyx.css);
            $($("#"+(i-1)+1).find("ul li")[0]).find("span").html(data.object.jjyx.serious);
            $($("#"+(i-1)+1).find("ul li")[1]).find("span").html(data.object.jjyx.moderate);
            $($("#"+(i-1)+1).find("ul li")[2]).find("span").html(data.object.jjyx.mild);

            if(data.object.jjyx.css == 'm'){
                $("#"+(i-1)+1).attr("class","runb");
            }else{
                $("#"+(i-1)+1).attr("class","run"+data.object.jjyx.css);
            }

        }else if (i == 3){
            $("#"+i).attr("class","run"+data.object.fwqk.css);
            $($("#"+(i-1)+1).find("ul li")[0]).find("span").html(data.object.fwqk.serious);
            $($("#"+(i-1)+1).find("ul li")[1]).find("span").html(data.object.fwqk.moderate);
            $($("#"+(i-1)+1).find("ul li")[2]).find("span").html(data.object.fwqk.mild);
            if(data.object.fwqk.css == 'm'){
                $("#"+(i-1)+1).attr("class","runb");
            }else{
                $("#"+(i-1)+1).attr("class","run"+data.object.fwqk.css);
            }

        }else if( i == 4){
            $("#"+i).attr("class","run"+data.object.zygl.css);
            $($("#"+(i-1)+1).find("ul li")[0]).find("span").html(data.object.zygl.min);
            $($("#"+(i-1)+1).find("ul li")[1]).find("span").html(data.object.zygl.max);
            if(data.object.zygl.css == 'm'){
                $("#"+(i-1)+1).attr("class","runb");
            }else{
                $("#"+(i-1)+1).attr("class","run"+data.object.zygl.css);
            }
        }
        if(i<=3){
            timerTask(parseInt(i)+1,data);
        }else{
            $(".barwrap").hide();
            $(".barwrap2").show();
            $(".barwrap2 div").first().text(data.object.score);
            return;
        }
    },i*2000);
}

/*组件-健康指数-定时器任务2*/
function run(){
    var bar = document.getElementById("barjc");
    var total = document.getElementById("totals");
    bar.style.width=parseInt(bar.style.width) + 1 + "%";
    total.innerHTML = bar.style.width;
    if(bar.style.width == "100%"){
        clearTimeout(timeout);
        $("#totals").html("100%");
        $("#checktitle").html("检测完成");
        return;
    }
    var timeout = setTimeout("run()",200);
}

function cutNh(){
    if($("#bg-left").hasClass("button-group-act")) return;
    $("#bg-right").removeClass("button-group-act");
    $("#bg-left").addClass("button-group-act");

    $("#qs-title").hide();
    $("#nh-title").show();

    $("#chart04").hide();
    $("#chart02").show();
    chart02.resize();

}

function cutQs(){
    if($("#bg-right").hasClass("button-group-act")) return;
    $("#bg-left").removeClass("button-group-act");
    $("#bg-right").addClass("button-group-act");

    $("#nh-title").hide();
    $("#qs-title").show();

    $("#chart02").hide();
    $("#chart04").show();
    chart04.resize();
}

