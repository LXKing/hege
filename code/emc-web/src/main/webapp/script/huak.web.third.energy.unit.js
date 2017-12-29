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
        var type = parseInt(sindex)+1;
        $(this).click(function() {
            var title ='';
            $(od[0]).empty();
            $(od[1]).empty();
            var yeart =  (year-1) +'~'+ year;
            title = yeart;
            $(".cb-title").empty();
            $(this).addClass("button-group-act").siblings().removeClass("button-group-act");

            $("#energytype").val(type);
            if(type == 1){ title += '年度水能耗情况对比 (单位: T)';$(od[0]).html(yeart+"年度水能耗排名(T)");$(od[1]).html("水总能耗趋势(T)");}
            if(type == 2){ title += '年度电能耗情况对比 (单位: kW·h)';$(od[0]).html(yeart+"年度电能耗排名(kW·h)");$(od[1]).html("电总能耗趋势(kW·h)");}
            if(type == 3){ title += '年度气能耗情况对比 (单位: m³)';$(od[0]).html(yeart+"年度气能耗排名(m³)");$(od[1]).html("气总能耗趋势(m³)");}
            if(type == 4){ title += '年度热能耗情况对比 (单位: GJ)';$(od[0]).html(yeart+"年度热能耗排名(GJ)");$(od[1]).html("热总能耗趋势(GJ)");}
            if(type == 5){ title += '年度煤能耗情况对比 (单位: T)';$(od[0]).html(yeart+"年度煤能耗排名(T)");$(od[1]).html("煤总能耗趋势(T)");}
            $(".cb-title").html(title);
            initChart01(sindex);
            initAssessment();
        });
        if(type == 1){
            $(this).click();
        }
    });
}

/*三级页面-用能单位-能源用量数据查询*/
function initChart01(index){
    var data = $("#searchTools").serialize()+"&type="+$("#type").val()+"&energyType="+$("#energytype").val();
    $.ajax({
        url: _web + '/third/energy/unit/energyDetail',
        type: 'post',
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
    var data = $("#searchTools").serialize()+"&type="+$("#type").val();
    $.ajax({
        url: _web + '/third/energy/unit/unitTableList',
        type: 'post',
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
    var data = $("#searchTools").serialize()+"&type="+$("#type").val()+"&energyType="+$("#energytype").val();
    $.ajax({
        url: _web + '/third/energy/unit/unitAssessment',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data:  data,
        dataType: "json",
        success: function (result) {
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
                            name:'能耗用量',
                            dataList: result.object.cur,
                            barWidth: 20
                        }
                    ]
                }
            });
        }
    });
    $.ajax({
        url: _web + '/third/energy/unit/unitAllAssessment',
        type: 'post',
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
                            name:'能耗用量',
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