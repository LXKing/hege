/*function loadDataFun() {
 loadFun();
 loadassessment();
 }

 function loadassessment(){
 var data = $("#searchTools").serialize()+"&type="+$("#type").val();
 $.ajax({
 url: _web + '/third/energy/assessment',
 type: 'post',
 async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
 data:data,
 dataType: "json",
 success: function (result) {
 console.info(result);
 initassessment(result);
 }
 });

 }

 *//*三级页面-能耗分析-站的各种能源类型排名*//*
 function initassessment(result){
 if(result.flag){
 echartsSelf({
 id: 'linechart_as',
 echartsConfig: {
 xAxisBoundaryGap: true,
 dataZoom: true,
 dataZoomstartValue: 1,
 dataZoomendValue: 5,
 xData: result.object.heatnames,
 series: [{
 type: 'bar',
 dataList:  result.object.heatnum

 }]
 }
 });

 echartsSelf({
 id: 'piechart_as',
 echartsConfig: {
 xAxisBoundaryGap: true,
 dataZoom: true,
 dataZoomstartValue: 1,
 dataZoomendValue: 5,
 xData: result.object.stationnames,
 series: [{
 type: 'bar',
 dataList: result.object.stationnums

 }]
 }
 });
 }
 }

 *//*三级页面-能耗分析-源、网、站、线、户的各种能源类型能耗*//*
 function loadFun(){
 var data = $("#searchTools").serialize()+"&type="+$("#type").val();
 $.ajax({
 url: _web + '/third/energy/energyDetail',
 type: 'post',
 async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
 data:data,
 dataType: "json",
 success: function (result) {
 initchart(result);
 }
 });
 }

 function initchart(result){

 if(result.flag){
 var datelist = result.object.date;
 $.each(result.object.data,function(index,value){
 var options = {
 id: value.type,
 echartsConfig: {
 xData: datelist,
 series: [{
 type: 'line',
 dataList: value.currentYear,
 typeLine: 'solid'
 },
 {
 type: 'line',
 dataList:value.lastyear,
 typeLine: 'dashed'
 }
 ]
 }
 };
 echartsSelf(options);
 })
 }


 };*/
$(function() {
    loadDHdetail();

    loadOrgFeedDH();
    loadOrgNetDH();
    loadOrgStationDH();
    loadOrgLineDH();
    loadOrgRoomDH();


    loadFeedDH();
    loadStationDH();
    loadTable();
});

//加载水单耗明细
function loadDHdetail(){

    var  type=$("#thirdType").val();
    var  unittype="0";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/"+type+"/"+unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            $(".groupTotal").text(toFormatNum(data.ZDH));
            if(data.TB>0){
                var TB = data.TB+"<span class='arrow'>↑</span>";
                $(".groupchangeRate").html(TB);
            }else if(data.TB==0){
                $("#groupchangeRate").css('color','#999');
                var TB = data.TB+"<span class='arrow'>→</span>";
                $(".groupchangeRate").html(TB);
            }else if(data.TB<0){
                $("#groupchangeRate").css('color','#3db1b0');
                var TB = data.TB+"<span class='arrow'>↓</span>";
                $(".groupchangeRate").html(TB)
            }

            echartsSelf({
                id: "groupEnergyChart",
                echartsConfig: {
                    xData: data.xdatas,
                    series: [{
                        type: 'line',
                        name:data.datas[0].typeName,
                        dataList: data.datas[0].dataList,
                        typeLine: 'solid'
                    },
                        {
                            type: 'line',
                            name:data.datas[1].typeName,
                            dataList: data.datas["1"].dataList,
                            typeLine: 'dashed'
                        }
                    ]
                }
            });
        }
    });
}
//加载热源的单耗排名
function loadFeedDH() {
    var  type=$("#thirdType").val();
    $.ajax({
        url: _web + "/third/analysis/water/feed-dh/"+type,
        type: "GET",
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (data) {
            feedDh(data);
        }
    });
}
//加载换热站的单耗排名
function loadStationDH() {
    var  type=$("#thirdType").val();
    $.ajax({
        url: _web + "/third/analysis/water/station-dh/"+type,
        type: "GET",
        data: $("#searchTools").serialize(),
        dataType: "json",
        success: function (data) {
            stationDh(data);
        }
    });
}
/*三级页面-各站点能源类型用量明细*/
function loadTable(){
    createtable();
    var data = $("#searchTools").serialize()+"&type="+$("#thirdType").val();
    $.ajax({
        url: _web + '/third/analysis/table-list',
        type: 'post',
        async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {
            thirdTable(result.object);
        }
    });

}

//1 -- 热源水单耗
function loadOrgFeedDH() {
    var type = $("#thirdType").val();
    var unittype = "1";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/" + type + "/" + unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            getFeedEc(data,type);

        }
    });
}
//2 -- 管网水单耗
function loadOrgNetDH() {
    var type = $("#thirdType").val();
    var unittype = "2";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/" + type + "/" + unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            getNetEc(data,type);

        }
    });
}
//3 --加载换热站站单耗
function loadOrgStationDH() {
    var type = $("#thirdType").val();
    var unittype = "3";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/" + type + "/" + unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            getStationEc(data,type);

        }
    });
}

//4 -- 管线水单耗
function loadOrgLineDH() {
    var type = $("#thirdType").val();
    var unittype = "4";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/" + type + "/" + unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            getLine(data,type);

        }
    });
}
//5 -- 民户水单耗
function loadOrgRoomDH() {
    var type = $("#thirdType").val();
    var unittype = "5";
    var data = $("#searchTools").serialize();
    $.ajax({
        url: _web + "/third/analysis/water/detail/" + type + "/" + unittype,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (data) {
            getRoomEc(data,type);

        }
    });
}
function getStationEc(data,type){
    console.log(data);
    $("#chart3").empty();
    chart3  =echarts.init(document.getElementById('chart3'));
    $(".stationTotal").html(toFormatNum(data.ZDH));
    if(type=="1"){
        $(".stationDw").html("T/万m²");
        $(".stationDw").closest(".energy-head").addClass("energy-snh-remind");
    }else if(type=="2"){
        $(".stationDw").html("kW·h/万m²");
        $(".stationDw").closest(".energy-head").addClass("energy-dnh-remind");
    }else if(type=="3"){
        $(".stationDw").html("m³/万m²");
        $(".stationDw").closest(".energy-head").addClass("energy-qnh-remind");
    }else if(type=="4"){
        $(".stationDw").html("GJ/万m²");
        $(".stationDw").closest(".energy-head").addClass("energy-rnh-remind");
    }else if(type=="5"){
        $(".stationDw").html("T/万m²");
        $(".stationDw").closest(".energy-head").addClass("energy-mnh-remind");
    }
//    if(data.TB < 0){
//        $(".stationTotal").closest(".energy-head").addClass("energy-snh");
//    }else{
//        $(".stationTotal").next("span").addClass("energy-remind");
//        $(".stationTotal").addClass("energy-remind");
//        $(".stationTotal").closest(".energy-head").addClass("energy-snh-remind");
//    }
    if(data.TB < 0){
        $(".stationTQ").css('color','#3db1b0');
        $(".stationTQ").html("("+data.TB + "↓)");
    }else if(data.TB > 0){
        $(".stationTQ").addClass("energy-remind");
        $(".stationTQ").html("("+data.TB + "↑)");
    }else{
        $(".stationTQ").css('color','#999');
        $(".stationTQ").html("("+data.TB + "→)");
    }
    echartsSelf({
        id: "chart3",
        echartsConfig: {
            xData: data.xdatas,
            series: [{
                type: 'line',
                name:data.datas[0].typeName,
                dataList: data.datas[0].dataList,
                typeLine: 'solid'

            },
                {
                    type: 'line',
                    name:data.datas[1].typeName,
                    dataList: data.datas[1].dataList,
                    typeLine: 'dashed'
                }
            ]
        }
    });
}
function getFeedEc(data,type){
    $("#chart1").empty();
    chart1  =echarts.init(document.getElementById('chart1'));
    $(".feedTotal").html(toFormatNum(data.ZDH));
    if(type=="1"){
        $(".feedDw").html("T/万m²");
        $(".feedTotal").closest(".energy-head").addClass("energy-snh-remind");
    }else if(type=="2"){
        $(".feedDw").html("kW·h/万m²");
        $(".feedTotal").closest(".energy-head").addClass("energy-dnh-remind");
    }else if(type=="3"){
        $(".feedDw").html("m³/万m²");
        $(".feedTotal").closest(".energy-head").addClass("energy-qnh-remind");
    }else if(type=="4"){
        $(".feedDw").html("GJ/万m²");
        $(".feedTotal").closest(".energy-head").addClass("energy-rnh-remind");
    }else if(type=="5"){
        $(".feedDw").html("T/万m²");
        $(".feedTotal").closest(".energy-head").addClass("energy-mnh-remind");
    }

//    if(data.TB < 0){
//        $(".feedTotal").closest(".energy-head").addClass("energy-snh");
//    }else{
//        $(".feedTotal").next("span").addClass("energy-remind");
//        $(".feedTotal").addClass("energy-remind");
//        $(".feedTotal").closest(".energy-head").addClass("energy-snh-remind");
//    }
    if(data.TB < 0){
        $(".feedTQ").css('color','#3db1b0');
        $(".feedTQ").html("("+data.TB + "↓)");
    }else if(data.TB > 0){
        $(".feedTQ").addClass("energy-remind");
        $(".feedTQ").html("("+data.TB + "↑)");
    }else{
        $(".feedTQ").css('color','#999');
        $(".feedTQ").html("("+data.TB + "→)");
    }
    echartsSelf({
        id: "chart1",
        echartsConfig: {
            xData: data.xdatas,
            series: [{
                type: 'line',
                name:data.datas[0].typeName,
                dataList: data.datas[0].dataList,
                typeLine: 'solid'
            },
                {
                    type: 'line',
                    name:data.datas[1].typeName,
                    dataList: data.datas[1].dataList,
                    typeLine: 'dashed'
                }
            ]
        }
    });
}
function getNetEc(data,type){
    $("#chart2").empty();
    chart2  =echarts.init(document.getElementById('chart2'));
    $(".netTotal").html(toFormatNum(data.ZDH))
    if(type=="1"){
        $(".netDw").html("T/万m²");
        $(".netDw").closest(".energy-head").addClass("energy-snh-remind");
    }else if(type=="2"){
        $(".netDw").html("kW·h/万m²");
        $(".netDw").closest(".energy-head").addClass("energy-dnh-remind");
    }else if(type=="3"){
        $(".netDw").html("m³/万m²");
        $(".netDw").closest(".energy-head").addClass("energy-qnh-remind");
    }else if(type=="4"){
        $(".netDw").html("GJ/万m²");
        $(".netDw").closest(".energy-head").addClass("energy-rnh-remind");
    }else if(type=="5"){
        $(".netDw").html("T/万m²");
        $(".netDw").closest(".energy-head").addClass("energy-mnh-remind");
    }

//    if(data.TB < 0){
//        $(".netTotal").closest(".energy-head").addClass("energy-snh");
//    }else{
//        $(".netTotal").next("span").addClass("energy-remind");
//        $(".netTotal").addClass("energy-remind");
//        $(".netTotal").closest(".energy-head").addClass("energy-snh-remind");
//    }
    if(data.TB < 0){
        $(".netTQ").css('color','#3db1b0');
        $(".netTQ").html("("+data.TB + "↓)");
    }else if(data.TB > 0){
        $(".netTQ").addClass("energy-remind");
        $(".netTQ").html("("+data.TB + "↑)");
    }else{
        $(".netTQ").css('color','#999');
        $(".netTQ").html("("+data.TB + "→)");
    }
    echartsSelf({
        id: "chart2",
        echartsConfig: {
            xData: data.xdatas,
            series: [{
                type: 'line',
                name:data.datas[0].typeName,
                dataList: data.datas[0].dataList,
                typeLine: 'solid'

            },
                {
                    type: 'line',
                    name:data.datas[1].typeName,
                    dataList: data.datas[1].dataList,
                    typeLine: 'dashed'
                }
            ]
        }
    });
}
function getLine(data,type){
    $("#chart4").empty();
    chart4  =echarts.init(document.getElementById('chart4'));
    $(".lineTotal").html(toFormatNum(data.ZDH));
    if(type=="1"){
        $(".lineDw").html("T/万m²");
        $(".netDw").closest(".energy-head").addClass("energy-snh-remind");

    }else if(type=="2"){
        $(".lineDw").html("kW·h/万m²");
        $(".lineDw").closest(".energy-head").addClass("energy-dnh-remind");

    }else if(type=="3"){
        $(".lineDw").html("m³/万m²");
        $(".lineDw").closest(".energy-head").addClass("energy-qnh-remind");

    }else if(type=="4"){
        $(".lineDw").html("GJ/万m²");
        $(".lineDw").closest(".energy-head").addClass("energy-enh-remind");

    }else if(type=="5") {
        $(".lineDw").html("T/万m²");
        $(".lineDw").closest(".energy-head").addClass("energy-mnh-remind");

    }

    if(data.TB < 0){
        $(".lineTQ").css('color','#3db1b0');
        $(".lineTQ").html("("+data.TB + "↓)");
    }else if(data.TB > 0){
        $(".lineTQ").addClass("energy-remind");
        $(".lineTQ").html("("+data.TB + "↑)");
    }else{
        $(".lineTQ").css('color','#999');
        $(".lineTQ").html("("+data.TB + "→)");
    }
    echartsSelf({
        id: "chart4",
        echartsConfig: {
            xData: data.xdatas,
            series: [{
                type: 'line',
                name:data.datas[0].typeName,
                dataList: data.datas[0].dataList,
                typeLine: 'solid'

            },
                {
                    type: 'line',
                    name:data.datas[1].typeName,
                    dataList: data.datas[1].dataList,
                    typeLine: 'dashed'
                }
            ]
        }
    });
}
function getRoomEc(data,type){
    $("#chart5").empty();
    chart5  =echarts.init(document.getElementById('chart5'));
    $(".roomTotal").html(toFormatNum(data.ZDH));
    if(type=="1"){
        $(".roomDw").html("T/万m²");
        $(".roomDw").closest(".energy-head").addClass("energy-snh-remind");
    }else if(type=="2"){
        $(".roomDw").html("kW·h/万m²");
        $(".roomDw").closest(".energy-head").addClass("energy-dnh-remind");
    }else if(type=="3"){
        $(".roomDw").html("m³/万m²");
        $(".roomDw").closest(".energy-head").addClass("energy-qnh-remind");
    }else if(type=="4"){
        $(".roomDw").html("GJ/万m²");
        $(".roomDw").closest(".energy-head").addClass("energy-rnh-remind");
    }else if(type=="5") {
        $(".roomDw").html("T/万m²");
        $(".roomDw").closest(".energy-head").addClass("energy-mnh-remind");
    }

    if(data.TB < 0){
        $(".roomTQ").css('color','#3db1b0');
        $(".roomTQ").html("("+data.TB + "↓)");
    }else if(data.TB > 0){
        $(".roomTQ").addClass("energy-remind");
        $(".roomTQ").html("("+data.TB + "↑)");
    }else{
        $(".roomTQ").css('color','#999');
        $(".roomTQ").html("("+data.TB + "→)");
    }
    echartsSelf({
        id: "chart5",
        echartsConfig: {
            xData: data.xdatas,
            series: [{
                type: 'line',
                name:data.datas[0].typeName,
                dataList: data.datas[0].dataList,
                typeLine: 'solid'

            },
                {
                    type: 'line',
                    name:data.datas[1].typeName,
                    dataList: data.datas[1].dataList,
                    typeLine: 'dashed'
                }
            ]
        }
    });

}
//加载热源的单耗排名
function feedDh(data){

    echartsSelf({
        id: 'linechart_as',
        echartsConfig: {
            axisLabelRotate: '-30', //倾斜角度
            xAxisBoundaryGap: true,
            dataZoom: true,
            dataZoomstartValue: 0,
            dataZoomendValue: 9,
            axisLabelInterval:0,
            bg: 'row',
            xData: data.mapName,
            series: [{
                type: 'bar',
                dataList: data.mapValue,
                name:'单耗',
                barWidth: 20
            }]
        }
    });
}

function stationDh(data){

    echartsSelf({
        id: 'piechart_as',
        echartsConfig: {
            axisLabelRotate: '-30', //倾斜角度
            xAxisBoundaryGap: true,
            dataZoom: true,
            dataZoomstartValue: 0,
            dataZoomendValue: 9,
            axisLabelInterval:0,
            bg: 'row',
            xData: data.mapName,
            series: [{
                type: 'bar',
                dataList: data.mapValue,
                name:'单耗',
                barWidth: 20
            }]
        }
    });
}
function loadDataFun() {
    createtable();

}

