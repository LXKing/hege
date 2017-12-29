
/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/17<BR>
 */

function loadDataFun() {
    initAssessment();
    initTable();
    initChart01(0);
    var date = new Date();
    var year = date.getFullYear();
    var od = $(".ec_title");
    $.each($(".button-group").find("a"), function(sindex, sitem) {
        var id = parseInt(sindex)+1;
        $(this).click(function() {
            var title ='';
            $(od[0]).empty();
            $(od[1]).empty();
            var yeart =  (year-1) +'~'+ year;
            title = yeart;
            $(".cb-title").empty();
            $(this).addClass("button-group-act").siblings().removeClass("button-group-act");

            $("#energytype").val(id);
            if(id == 1){ title += '年度管理成本情况对比 (单位: 万元)';$(od[0]).html(yeart+"年度管理成本排名(万元)");$(od[1]).html("管理总成本趋势(万元)");}
            if(id == 2){ title += '年度其他成本情况对比 (单位: 万元)';$(od[0]).html(yeart+"年度其他成本排名(万元)");$(od[1]).html("其他总成本趋势(万元)");}
            if(id == 3){ title += '年度设备成本情况对比 (单位: 万元)';$(od[0]).html(yeart+"年度设备成本排名(万元)");$(od[1]).html("设备总成本趋势(万元)");}
            if(id == 4){ title += '年度人工成本情况对比 (单位: 万元)';$(od[0]).html(yeart+"年度人工成本排名(万元)");$(od[1]).html("人工总成本趋势(万元)");}
            if(id == 5){ title += '年度能源成本情况对比 (单位: 万元)';$(od[0]).html(yeart+"年度能源成本排名(万元)");$(od[1]).html("能源总成本趋势(万元)");}
            $(".cb-title").html(title);
            initChart01(sindex);
            initAssessment();
        });
        if(id == 1){
            $(this).click();
        }
    });
}

/*三级页面-用能单位-能源用量数据查询*/
function initChart01(index){
    var data = $("#searchTools").serialize()+"&type="+$("#type").val()+"&energytype="+$("#energytype").val();
    $.ajax({
        url: _web + '/thired/cost/unit/thirdUnitSituationCostContrast',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:data,
        dataType: "json",
        success: function (result) {
            console.info(result);
            chart01Fun(result.object);
        }
    });
}

/*三级页面-用能单位-表单数据查询*/
function initTable(){
    createtable();
    var data = $("#searchTools").serialize()+"&id="+$("#id").val();
    $.ajax({
        url: _web + '/third/energy/unit/unitTableList',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: data,
        dataType: "json",
        success: function (result) {
            thirdTable(result.object);
        }
    });

}

/*三级页面-用能单位-能源用量排名*/
function initAssessment(){

    console.log($("#energytype").val())
    var data = $("#searchTools").serialize()+"&type="+$("#type").val()+"&energytype="+$("#energytype").val();
    $.ajax({
        url: _web + '/thired/cost/unit/thirdUnitCostDetail',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:  data,
        dataType: "json",
        success: function (result) {
            console.log(result);
            echartsSelf({
                id: 'piechart_as',
                echartsConfig: {
                    axisLabelRotate: '-50', //倾斜角度
                    xAxisBoundaryGap: true,
                    dataZoom: true,
                    dataZoomstartValue: 0,
                    axisLabelInterval:0,
                    dataZoomendValue: 9,
                    bg: 'row',
                    xData: result.object.name,
                    series: [
                        {
                            type: 'bar',
                            name:'成本用量',
                            dataList: result.object.cur,
                            barWidth: 20
                        }
                    ]
                }
            });
        }
    });
    $.ajax({
        url: _web + '/thired/cost/unit/thirdUnitCostTrend',
        type: 'GET',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:  data,
        dataType: "json",
        success: function (result) {
            echartsSelf({
                id: 'linechart_as',
                echartsConfig: {
                    axisLabelRotate: '0', //倾斜角度
                    xAxisBoundaryGap: true,
                    dataZoom: true,
                    xData: result.object.date,
                    series: [
                        {
                            type: 'line',
                            name:'成本用量',
                            dataList: result.object.data[0].currentYear,
                            barWidth: 20

                        }
                    ]
                }
            });
        }
    });
}

/*三级页面-用能单位-能源用量对比*/
function chart01Fun(data) {
    echartsSelf({
        id: "groupEnergyChart",
        leftName:'用量',
        rightName:'同期偏差',
        echartsConfig: {
            dataZoom: true,
            xAxisBoundaryGap: true,
            axisLabelRotate: '-50', //倾斜角度
            axisLabelInterval:0,
            xData: data.name,
            series: [
                {
                    type: 'line',
                    label:{show:true},
                    dataList: data.tqb,
                    name:'同期偏差',
                    typeLine: 'solid',
                    barColor: 'rgba(50,187,182,1)',
                    yAxisIndex: 1,
                    label: {
                        show: true
                    }
                },
                {
                    type: 'bar',
                    name:'本年计划',
                    dataList: data.plan,
                    barWidth: 20,
                    barColor: 'rgba(59,150,219,0.4)'
                },
                {
                    type: 'bar',
                    name:'本期能耗',
                    dataList: data.cur,
                    barWidth: 20,
                    barColor: 'rgba(59,150,219,1)'
                },
                {
                    type: 'bar',
                    name:'去年同期',
                    dataList: data.tq,
                    barWidth: 20,
                    barColor: 'rgba(50,187,182,1)'
                }
            ]
        }
    });
}