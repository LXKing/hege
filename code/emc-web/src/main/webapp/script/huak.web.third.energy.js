
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
    var type = $("#type").val();
    var title = $($(".select-box")[0]).find(".x-sfleft1").find("input").val();
    if(type == '1'){
        $($("#groupEnergyChart").prev()).find(".cb-title").html(title+"（单位：T）");
        $($($("#groupEnergyChart").parent().next()).find(".small")[0]).html(title+"用水量"+"（T）");
        $(".maintitle").html("用水量"); //切换页后改这个可以改整体标题
    }else if(type == '2'){
        $($("#groupEnergyChart").prev()).find(".cb-title").html(title+"（单位：kWh）");
        $($($("#groupEnergyChart").parent().next()).find(".small")[0]).html(title+"用电量"+"（kWh）");
        $(".maintitle").html("用电量"); //切换页后改这个可以改整体标题
    }else if(type == '3'){
        $($("#groupEnergyChart").prev()).find(".cb-title").html(title+"（单位：m³）");
        $($($("#groupEnergyChart").parent().next()).find(".small")[0]).html(title+"用气量"+"（m³）");
        $(".maintitle").html("用气量"); //切换页后改这个可以改整体标题
    }else if(type == '4'){
        $($("#groupEnergyChart").prev()).find(".cb-title").html(title+"（单位：GJ）");
        $($($("#groupEnergyChart").parent().next()).find(".small")[0]).html(title+"用热量"+"（GJ）");
        $(".maintitle").html("用热量"); //切换页后改这个可以改整体标题
    }else if(type == '5'){
        $($("#groupEnergyChart").prev()).find(".cb-title").html(title+"（单位：T）");
        $($($("#groupEnergyChart").parent().next()).find(".small")[0]).html(title+"用煤量"+"（T）");
        $(".maintitle").html("用煤量"); //切换页后改这个可以改整体标题
    }

}

/*三级页面-能耗分析-站的各种能源类型排名数据入口*/
function loadassessment(){
    var data = $("#searchTools").serialize()+"&type="+$("#type").val();
    $.ajax({
        url: _web + '/third/energy/assessment',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {
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
    var type = $("#type").val();


    var data = $("#searchTools").serialize()+"&type="+type;
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

/*三级页面-能耗分析-源、网、站、线、户的各种能源类型能耗图展示*/
function initchart(result){
        var type = $("#type").val();
        if(result.flag){
            var datelist = result.object.date;
            $.each(result.object.data,function(index,value){
                var tb = 0;
                $($("#"+value.type).parent().prev()).find('.energy-list-info').find(".energy-list-num").html(toFormatNum(value.totalcurrentyear));
                if(type == '1'){
                    $($("#"+value.type).parent().prev()).find('.energy-list-info').find(".energy-list-measure").html("T");
                }else if(type == '2'){
                    $($("#"+value.type).parent().prev()).find('.energy-list-info').find(".energy-list-measure").html("kWh");
                }else if(type == '3'){
                    $($("#"+value.type).parent().prev()).find('.energy-list-info').find(".energy-list-measure").html("m³");
                }else if(type == '4'){
                    $($("#"+value.type).parent().prev()).find('.energy-list-info').find(".energy-list-measure").html("GJ");
                }else if(type == '5'){
                    $($("#"+value.type).parent().prev()).find('.energy-list-info').find(".energy-list-measure").html("T");
                }

                if(value.totallastyear != 0){
                    tb = toDecimalCommon((value.totalcurrentyear - value.totallastyear)/value.totallastyear,4)*10000/100;
                }

                if(tb>0){
                    $("#"+value.type).parent().prev().addClass('energy-add')
                }else{
                    $("#"+value.type).parent().prev().removeClass('energy-add');
                }
                var zz =tb>0?"↑":((tb == 0)?"→":"↓");
                $($("#"+value.type).parent().prev()).find('.energy-list-info').find(".energy-list-proportion").html("("+tb+'%'+zz+")");
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


};

/*三级页面-集团总能源类型能耗图展示*/
function loadEnergyTotalDetail() {
    var data = $("#searchTools").serialize()+"&type="+$("#type").val();
    var tb = 0;

    $.ajax({
        url: _web + '/third/energy/energyTotalDetail',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {
            var datelist = result.object.date;
            var currentyear = result.object.data[0].currentYear;
            var lastyear = result.object.data[0].lastyear;
            var totalcurrent = result.object.data[0].totalcurrentyear;
            var totallast = result.object.data[0].totallastyear;
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
    var data = $("#searchTools").serialize()+"&type="+$("#type").val();
    $.ajax({
        url: _web + '/third/energy/tablelist',
        type: 'post',
        async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {
            thirdTable(result.object);
        }
    });



}