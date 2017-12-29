
function loadDataFun(){
    $.each( $('.energy-head'),function(index,item){
        $(item).removeClass('energy-add');
    });
    initCss();
    loadFun();
    loadassessment();
    loadEnergyTotalDetail();
    loadTable();
}

function initCss(){
    var id = $("#id").val();
    var title = $($(".select-box")[0]).find(".x-sfleft1").find("input").val();
    if(id == '1'){
        $($("#groupEnergyChart").prev()).find(".cb-title").html(title+"（单位：万元）");
        $($($("#groupEnergyChart").parent().next()).find(".small")[0]).html(title+"管理费"+"（万元）");
        $(".maintitle").html("管理费"); //切换页后改这个可以改整体标题
    }else if(id == '2'){
        $($("#groupEnergyChart").prev()).find(".cb-title").html(title+"（单位：万元）");
        $($($("#groupEnergyChart").parent().next()).find(".small")[0]).html(title+"其他费"+"（万元）");
        $(".maintitle").html("其他费"); //切换页后改这个可以改整体标题
    }else if(id == '3'){
        $($("#groupEnergyChart").prev()).find(".cb-title").html(title+"（单位：万元）");
        $($($("#groupEnergyChart").parent().next()).find(".small")[0]).html(title+"设备费"+"（万元）");
        $(".maintitle").html("设备费"); //切换页后改这个可以改整体标题
    }else if(id == '4'){
        $($("#groupEnergyChart").prev()).find(".cb-title").html(title+"（单位：万元）");
        $($($("#groupEnergyChart").parent().next()).find(".small")[0]).html(title+"人工费"+"（万元）");
        $(".maintitle").html("人工费"); //切换页后改这个可以改整体标题
    }else if(id == '5'){
        $($("#groupEnergyChart").prev()).find(".cb-title").html(title+"（单位：万元）");
        $($($("#groupEnergyChart").parent().next()).find(".small")[0]).html(title+"能源费"+"（万元）");
        $(".maintitle").html("能源费"); //切换页后改这个可以改整体标题
    }

}

/*三级页面-能耗分析-站的各种能源类型排名数据入口*/
function loadassessment(){
    var data = $("#searchTools").serialize()+"&energytype="+$("#energytype").val();
    $.ajax({
        url: _web + '/thired/cost/unit/thridCostTypeStationRanking',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {
            console.log(result);
            initassessment(result);
        }
    });

}

/*三级页面-能耗分析-站的各种能源类型排名*/
function initassessment(result){
    if(result.flag){
        echartsSelf({
            id: 'linechart_as',
            echartsConfig: {
                xAxisBoundaryGap: true,
                axisLabelRotate: '-30', //倾斜角度
                dataZoom: true,
                dataZoomstartValue: 0,
                dataZoomendValue: 9,
                axisLabelInterval:0,
                xData: result.object.heatnames,
                series: [{
                    type: 'bar',
                    barWidth: 20,
                    dataList:  result.object.heatnum

                }]
            }
        });

        echartsSelf({
            id: 'piechart_as',
            echartsConfig: {
                xAxisBoundaryGap: true,
                axisLabelRotate: '-50', //倾斜角度
                dataZoom: true,
                dataZoomstartValue: 0,
                dataZoomendValue:9,
                axisLabelInterval:0,
                xData: result.object.stationnames,
                series: [{
                    type: 'bar',
                    barWidth:20,
                    dataList: result.object.stationnums

                }]
            }
        });
    }
}

/*三级页面-能耗分析-源、网、站、线、户的各种能源类型能耗*/
function loadFun(){
    var data = $("#searchTools").serialize()+"&energytype="+$("#id").val();
    $.ajax({
        url: _web + '/thired/cost/unit/getEachStationCostDetail',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {
            initchart(result);
        }
    });
}

/*三级页面-能耗分析-源、网、站、线、户的各种能源类型能耗图展示*/
function initchart(result){
        var id = $("#id").val();
        if(result.flag){
            var datelist = result.object.date;
            $.each(result.object.data,function(index,data){
                var tb = 0;

                $($("#"+data.type).parent().prev()).find('.energy-list-info').find(".energy-list-num").html(toFormatNum(data.totalcurrentyear));
                if(id == '1'){
                    $($("#"+data.type).parent().prev()).find('.energy-list-info').find(".energy-list-measure").html("万元");
                }else if(id == '2'){
                    $($("#"+data.type).parent().prev()).find('.energy-list-info').find(".energy-list-measure").html("万元");
                }else if(id == '3'){
                    $($("#"+data.type).parent().prev()).find('.energy-list-info').find(".energy-list-measure").html("万元");
                }else if(id == '4'){
                    $($("#"+data.type).parent().prev()).find('.energy-list-info').find(".energy-list-measure").html("万元");
                }else if(id == '5'){
                    $($("#"+data.type).parent().prev()).find('.energy-list-info').find(".energy-list-measure").html("万元");
                }

                if(data.totallastyear != 0){
                    tb = toDecimalCommon((data.totalcurrentyear - value.totallastyear)/data.totallastyear,4)*10000/100;
                }

                if(tb>0){
                    $("#"+data.type).parent().prev().addClass('energy-add')
                }else{
                    $("#"+data.type).parent().prev().removeClass('energy-add');
                }
                var zz =tb>0?"↑":((tb == 0)?"→":"↓");
                $($("#"+data.type).parent().prev()).find('.energy-list-info').find(".energy-list-proportion").html("("+tb+'%'+zz+")");
                var options = {
                    id: data.type,
                    echartsConfig: {
                        xData: datelist,
                        series: [{
                            type: 'line',
                            dataList: data.currentYear,
                            typeLine: 'solid'
                        },
                            {
                                type: 'line',
                                dataList:data.lastyear,
                                typeLine: 'dashed'
                            }
                        ]
                    }
                };
                echartsSelf(options);
            })
        }


};

/*三级页面-集团总成本类型图展示*/
function loadEnergyTotalDetail() {
    var data = $("#searchTools").serialize()+"&energytype="+$("#id").val();
    var tb = 0;
    $.ajax({
        url: _web + '/thired/cost/unit/getCostTypeDetail',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {

            var datelist = result.object.date;
            var currentyear = result.object.data[0].currentYear;
            var lastyear = result.object.data[0].lastyear;
            var totalcurrent = result.object.data[0].totalcurrentyear;
            var totallast = result.object.data[0].totallastyear;
            console.log(totalcurrent+"....totalcurrent")
            console.log(totallast+"....totallast")
            console.log(datelist+"....datelist")
            if(totallast != 0){

                tb = parseFloat(toDecimalCommon((totalcurrent- totallast)/totallast,4))*10000/100;
            }
            var zz =tb>0?"↑":((tb == 0)?"→":"↓");
            $("#groupTotal").html(toFormatNum(totalcurrent));
            var html = tb+'<span class="arrow">'+zz+'</span>';
            $("#groupchangeRate").html(html);
            echartsSelf({
                id: "groupEnergyChart",
                echartsConfig: {
                    xData: datelist,
                    series: [{
                        type: 'line',
                        dataList:currentyear,
                        typeLine: 'solid'

                    },
                        {
                            type: 'line',
                            dataList: lastyear,
                            typeLine: 'dashed'
                        }
                    ]
                }
            });
        }
    });

//    createtable();

}

/*三级页面-各站点能源类型用量明细*/
function loadTable(){
    createtable();
    var data = $("#searchTools").serialize()+"&energytype="+$("#id").val();
    $.ajax({
        url: _web + '/thired/cost/unit/getUnitCostDetail',
        type: 'GET',
        async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {
            thirdTable(result.object);
        }
    });



}