
function loadDataFun(){
    //加载分公司能耗
    $.ajax({
        url : _web+"/cost/secondary/line/SubCostDetailed",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            fgsCostList(data);
        }
    });
    $.ajax({
        url : _web+"/cost/secondary/second/BranchCostProportion",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            chart01Fun(data);
        }
    });
    $.ajax({
        url : _web+"/cost/secondary/second/twolEvelSubFlowLine",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            console.log(data);
            chart02Fun(data);
        }
    });
    $.ajax({
        url : _web+"/cost/secondary/second/BranchEnergyConsAn",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            console.log(data);
            chart03Fun(data);
        }
    });
    $.ajax({
        url : _web+"/cost/secondary/second/BranchEnergyCons",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            console.log(data);
            chart04Fun(data);
        }
    });

    //能耗折线
    $.ajax({
        url : _web+"/cost/secondary/line/groupTotalCost",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            console.log(data);
            $("#groupTotal").html(toFormatNum(data.bq));
        	/*if(data.tb < 0){
        		$("#groupTotal").addClass("energy_gray");
        	};*/
            if(data.tb < 0){
            	$("#groupchangeRate").css('color','#3db1b0');
                $("#groupchangeRate").html(toFormatNum(data.tb) + "<span class='arrow'>↓</span>");
            }else if(data.tb > 0){
                $("#groupchangeRate").html(toFormatNum(data.tb) + "<span class='arrow'>↑</span>");
            }else if(data.tb == 0){
            	$("#groupchangeRate").css('color','#999');
            	$("#groupchangeRate").html("0" + "<span class='arrow'>→</span>");
            }
            groupCostChartFun(data.energy.datas, data.energy.xdatas);
        }
    });
    //能源成本
    $.ajax({
        url : _web+"/cost/secondary/line/SubCostEnergy",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            console.log(data.bq.energy)
            console.log(toFormatNum(data.bq.energy))
            $("#waterTotal").html(toFormatNum(data.bq.energy));
            if(data.tb.energy < 0){
                $("#waterTotal").closest(".energy-head").addClass("energy-snh");
            }else{
                $("#waterTotal").next("span").addClass("energy-remind");
                $("#waterTotal").addClass("energy-remind");
                $("#waterTotal").closest(".energy-head").addClass("energy-snh-remind");
            }
            if(data.tb.energy < 0){
                $("#waterchangeRate").css('color','#3db1b0');
                $("#waterchangeRate").html("("+toFormatNum(data.tb.energy) + "↓)");

            }else if(data.tb.energy > 0){
                $("#waterchangeRate").addClass("energy-remind");
                $("#waterchangeRate").html("("+toFormatNum(data.tb.energy) + "↑)");
            }else{
                $("#waterchangeRate").css('color','#999');
                $("#waterchangeRate").html("("+toFormatNum(data.tb.energy) + "→)");
            }
            energyCostChartFun(data.energy.datas, data.energy.xdatas);
        }
    });

    //成本类型-除能源成本外
    $.ajax({
        url : _web+"/cost/secondary/line/SubCost",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            $("#electricTotal").html(toFormatNum(data.bq.manage));
            if(data.tb.manage < 0){
                $("#electricTotal").closest(".energy-head").addClass("energy-snh");
            }else{
                $("#electricTotal").next("span").addClass("energy-remind");
                $("#electricTotal").addClass("energy-remind");
                $("#electricTotal").closest(".energy-head").addClass("energy-snh-remind");
            }
            if(data.tb.manage < 0){
                $("#elechangeRate").css('color','#3db1b0');
                $("#elechangeRate").html("("+toFormatNum(data.tb.manage) + "↓)");
            }else if(data.tb.manage > 0){
                $("#elechangeRate").addClass("energy-remind");
                $("#elechangeRate").html("("+toFormatNum(data.tb.manage) + "↑)");
            }else{
                $("#elechangeRate").css('color','#999');
                $("#elechangeRate").html("("+toFormatNum(data.tb.manage) + "→)");
            }

            manageCostChartFun(data.manage.datas, data.manage.xdatas);

            $("#gasTotal").html(toFormatNum(data.bq.other));
            if(data.tb.other < 0){
                $("#gasTotal").closest(".energy-head").addClass("energy-dnh");
            }else{
                $("#gasTotal").next("span").addClass("energy-remind");
                $("#gasTotal").addClass("energy-remind");
                $("#gasTotal").closest(".energy-head").addClass("energy-dnh-remind");
            }
            if(data.tb.other < 0){
                $("#gaschangeRate").css('color','#3db1b0');
                $("#gaschangeRate").html("("+toFormatNum(data.tb.other) + "↓)");
            }else if(data.tb.other > 0){
                $("#gaschangeRate").addClass("energy-remind");
                $("#gaschangeRate").html("("+toFormatNum(data.tb.other) + "↑)");
            }else{
                $("#gaschangeRate").css('color','#999');
                $("#gaschangeRate").html("("+toFormatNum(data.tb.other) + "→)");
            }

            otherCostChartFun(data.other.datas, data.other.xdatas);

            $("#hotTotal").html(toFormatNum(data.bq.device));
            if(data.tb.device < 0){
                $("#hotTotal").closest(".energy-head").addClass("energy-qnh");
            }else{
                $("#hotTotal").next("span").addClass("energy-remind");
                $("#hotTotal").addClass("energy-remind");
                $("#hotTotal").closest(".energy-head").addClass("energy-qnh-remind");
            }
            if(data.tb.device < 0){
                $("#hotchangeRate").css('color','#3db1b0');
                $("#hotchangeRate").html("("+toFormatNum(data.tb.device) + "↓)");
            }else if(data.tb.device > 0){
                $("#hotchangeRate").addClass("energy-remind");
                $("#hotchangeRate").html("("+toFormatNum(data.tb.device) + "↑)");
            }else{
                $("#hotchangeRate").css('color','#999');
                $("#hotchangeRate").html("("+toFormatNum(data.tb.device) + "→)");
            }

            deviceCostChartFun(data.device.datas, data.device.xdatas);

            $("#coalTotal").html(toFormatNum(data.bq.labor));
            if(data.tb.labor < 0){
                $("#coalTotal").closest(".energy-head").addClass("energy-rnh");
            }else{
                $("#coalTotal").next("span").addClass("energy-remind");
                $("#coalTotal").addClass("energy-remind");
                $("#coalTotal").closest(".energy-head").addClass("energy-rnh-remind");
            }
            if(data.tb.labor < 0){
                $("#coalchangeRate").css('color','#3db1b0');
                $("#coalchangeRate").html("("+toFormatNum(data.tb.labor) + "↓)");
            }else if(data.tb.labor > 0){
                $("#coalchangeRate").addClass("energy-remind");
                $("#coalchangeRate").html("("+toFormatNum(data.tb.labor) + "↑)");
            }else{
                $("#coalchangeRate").css('color','#999');
                $("#coalchangeRate").html("("+toFormatNum(data.tb.labor) + "→)");
            }

            laborCostChartFun(data.labor.datas, data.labor.xdatas);

        }
    });


    /**
     * 能源流相关开始
     */
    //能源流明细
    $.ajax({
        url : _web+"/cost/secondary/second/EnergyFlowDetail",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
        	renderEnergyFlowDetail(data.list);
        }
    });
    //能源流成本占比分布图
    $.ajax({
        url : _web+"/cost/secondary/second/TwoEnergyFlowPie",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
        	chart2EnergyPie(data);
        }
    });
    //能源流能耗趋势对比图
    $.ajax({
         url : _web+"/cost/secondary/second/twolEvelEnergyFlowLine",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
             chart2EnergyLine(data.datas);
        }
    });
    //能源流能耗同比
    $.ajax({
        url : _web+"/cost/secondary/second/TwoEnergyFlowPieAn",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
        	chart2EnergyBar(data.data);
        }
    });
}

function fgsCostList(data){
    var arr  = [];
    var obj = {}
    for(let i = 0; i< 5;i++){
        obj = {};
        data.list.forEach(function(item,j){
            switch(j){
                case 0:obj.id = item.id[i];break;
                case 1:obj.orgName = item.orgName[i];break;
                case 2:obj.enertyBq = item.enertyBq[i];break;
                case 3:obj.laborBq = item.laborBq[i];break;
                case 4:obj.totalBq = item.totalBq[i];break;
                case 5:obj.managerBq = item.managerBq[i];break;
                case 6:obj.deviceBq = item.deviceBq[i];break;
                case 7:obj.otherBq = item.otherBq[i];break;
                case 8:obj.totalAn = item.totalAn[i];break;
                case 9:obj.enertyAn = item.enertyAn[i];break;
                case 10:obj.laborAn = item.laborAn[i];break;
                case 11:obj.managerAn = item.managerAn[i];break;
                case 12:obj.deviceAn = item.deviceAn[i];break;
                case 13:obj.otherAn = item.otherAn[i];break;
            }
        })
        arr.push(obj);
    }
    var html = "";
    $.each(arr,function(idx,item){
        html +='<tr class="'+(idx%2 == 0?"":"bgc")+'">';
        html +='<td><a href="'+_web+'/cost/thired/fgs/'+item.id+'" class="need_a">'+item.orgName+'</a></td>';
        html +=getHtmlTd(item.totalBq,item.totalAn);
        html +=getHtmlTd(item.enertyBq,item.enertyAn);
        html +=getHtmlTd(item.deviceBq,item.deviceAn);
        html +=getHtmlTd(item.laborBq,item.laborAn);
        html +=getHtmlTd(item.managerBq,item.managerAn);
        html +=getHtmlTd(item.otherBq,item.otherAn);
        html +='</tr>';
    });
    $("#fgsEnergyTbody").html(html);
}

function getHtmlTd(bq,an){
    return '<td class="need_title">'+toDecimal(bq)+'（同<span class="'+(an == 0?"":(an > 0?"redcolor":"bluecolor"))+'">'+toDecimal(an)+'%'+(an == 0?"→":(an > 0?"↑":"↓"))+'</span>）</td>';
}

/*集团总能耗-折线图*/
function groupCostChartFun(datalist,datelist){
    console.log(datalist)
    console.log(datelist);
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
            axisTick:{show:false},
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
    $.each(datalist,function(index,data){
        var typeName = data.typeName;
        var typeLine = "";
        if(index == 0){
            typeLine = "solid";
        }
        if(index == 1){
            typeLine = "dashed";
        }
        var item = {
            name:typeName,
            type:'line',
            symbol: 'circle',
            smooth: false,
            lineStyle:{normal:{type:typeLine}},
            data:data.dataList
        }
        option.series.push(item);
    });
    groupEnergyChart.setOption(option);
}
/*能源成本-折线图*/
function energyCostChartFun(datalist, datelist){
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
    $.each(datalist,function(index,data){
        var typeName = data.typeName;
        var typeLine = "";
        if(index == 0){
            typeLine = "solid";
        }
        if(index == 1){
            typeLine = "dashed";
        }
        var item = {
            name:typeName,
            type:'line',
            symbol: 'circle',
            smooth: false,
            lineStyle:{normal:{type:typeLine}},
            data:data.dataList
        }
        option.series.push(item);
    });
    waterEnergyChart.setOption(option);
}
/*管理成本-折线图*/
function manageCostChartFun(datalist, datelist){
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
    $.each(datalist,function(index,data){
        var typeName = data.typeName;
        var typeLine = "";
        if(index == 0){
            typeLine = "solid";
        }
        if(index == 1){
            typeLine = "dashed";
        }
        var item = {
            name:typeName,
            type:'line',
            symbol: 'circle',
            smooth: false,
            lineStyle:{normal:{type:typeLine}},
            data:data.dataList
        }
        option.series.push(item);
    });
    electricEnergyChart.setOption(option);
}

/*其他成本-折线图*/
function otherCostChartFun(datalist, datelist){
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
    $.each(datalist,function(index,data){
        var typeName = data.typeName;
        var typeLine = "";
        if(index == 0){
            typeLine = "solid";
        }
        if(index == 1){
            typeLine = "dashed";
        }
        var item = {
            name:typeName,
            type:'line',
            symbol: 'circle',
            smooth: false,
            lineStyle:{normal:{type:typeLine}},
            data:data.dataList
        }
        option.series.push(item);
    });
    gasEnergyChart.setOption(option);
}

/*设备成本-折线图*/
function deviceCostChartFun(datalist, datelist){
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
    $.each(datalist,function(index,data){
        var typeName = data.typeName;
        var typeLine = "";
        if(index == 0){
            typeLine = "solid";
        }
        if(index == 1){
            typeLine = "dashed";
        }
        var item = {
            name:typeName,
            type:'line',
            symbol: 'circle',
            smooth: false,
            lineStyle:{normal:{type:typeLine}},
            data:data.dataList
        }
        option.series.push(item);
    });
    hotEnergyChart.setOption(option);
}

/*人工成本-折线图*/
function laborCostChartFun(datalist, datelist){
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
    $.each(datalist,function(index,data){
        var typeName = data.typeName;
        var typeLine = "";
        if(index == 0){
            typeLine = "solid";
        }
        if(index == 1){
            typeLine = "dashed";
        }
        var item = {
            name:typeName,
            type:'line',
            symbol: 'circle',
            smooth: false,
            lineStyle:{normal:{type:typeLine}},
            data:data.dataList
        }
        option.series.push(item);
    });
    coalEnergyChart.setOption(option);
}








/*分公司成本占比分布图*/
function chart01Fun(data){
    var piechart = echarts.init(document.getElementById('piechart'));
    var option = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)",
            show:true
        },

        toolbox: {
            show : false,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : false,

        series : [
            {
                type:'pie',
                radius: chartsColor.chart05.facecolor5,
                silent:true,
                itemStyle : {
                    normal : {
                        color: chartsColor.ec3.facecolor1,
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    }
                },
                data:[
                    {value:1, name:'圈', selected:false,hoverAnimation:false}
                ]
            },
            {
                name:'分公司能耗占比',
                type:'pie',
                radius: ['70%', '80%'],

                // for funnel
                x: '60%',
                width: '35%',
                funnelAlign: 'left',
                itemStyle : {
                    normal : {
                        borderColor: chartsColor.ec3.facecolor1,
                        borderWidth: '2',
                        label : {show:true}
                    }
                },
                color:color,
                data:data.list
            }
        ]
    };
    piechart.setOption(option);
}


/*分公司成本趋势对比图*/
function chart02Fun(data){
    console.log(data);
    var linechart = echarts.init(document.getElementById('linechart'));
    var option = {

        tooltip: {
            trigger: 'axis'
        },
        grid: {
            //left: '15%',
            top: '28',
            right: '115',
            bottom: '5',
            containLabel: true
        },
        legend: {
            orient : 'vertical',
            right : '5%',
            top: '28',
            itemWidth:8,
            itemHeight:4,
            icon:'rect',
            itemGap:20,
            data:data.legends,
            textStyle:{
                color:chartsColor.ec4.facecolor5
            }
        },
        color:chartsColor.ec4.facecolor5,
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
                    color: chartsColor.ec4.facecolor1
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            },
            splitArea: {
                show: true,
                areaStyle: {
                    color: chartsColor.areacolor
                }
            },
            data: data.xaxis

        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: chartsColor.ec4.facecolor2
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
        series: data.list
    };

    linechart.setOption(option);
}


/*分公司成本同比*/
function chart03Fun(data) {
    var barchart01 = echarts.init(document.getElementById('barchart01'));
    var option = {
        title:{
            subtext:'成本 (单位: 万元)',
            top:'-18px',
            left:'35px',
            subtextStyle:{
                color: chartsColor.linefontcolor,
                fontStyle: 'normal',
                fontWeight: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 12
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer : {
                type : 'shadow'
            }
        },
        legend: {
            data:['本期','同期'],
            itemWidth:8,
            itemHeight:4,
            right: '40',
            top:'10px',
            textStyle: {
                color: chartsColor.linefontcolor,
                fontStyle: 'normal',
                fontWeight: 'lighter',
                fontFamily: '微软雅黑',
                fontSize: 12
            }
        },
        grid: {
            //left: '15',
            top: '50',
            right: '45',
            bottom: '5',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            axisTick:{show:false},
            splitLine: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel : {
                show:true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            },
            data:data.xaxis

        },
        yAxis: {
            type: 'value',
            axisTick:{show:false},
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
            splitArea: {
                show: true,
                areaStyle: {
                    color: chartsColor.areacolor
                }
            },
            axisLabel: {
                show:true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            }
        },
        color:['#3B96DD','#a1b1c5'],
        series: [
            {
                name:"本期",
                type:'bar',
                barWidth: '20',
                data:data.bq
            },
            {
                name:"同期",
                type:'bar',
                barWidth: '20',
                data:data.tq
            }
        ]
    }

    barchart01.setOption(option);
}


/*分公司能耗排名---barchart02*/
function chart04Fun(data){
    var	barchart02 = echarts.init(document.getElementById('barchart02'));
    var option = {
        title:{
            subtext:'成本 (单位: 万元)',
            top:'-18px',
            left:'35px',
            subtextStyle:{
                color: chartsColor.linefontcolor,
                fontStyle: 'normal',
                fontWeight: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 12
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer : {
                type : 'shadow'
            }
        },
        legend: {
            data:['今年','去年'],
            itemWidth:8,
            itemHeight:4,
            right: '40',
            top:'10px',
            textStyle: {
                color: chartsColor.linefontcolor,
                fontStyle: 'normal',
                fontWeight: 'lighter',
                fontFamily: '微软雅黑',
                fontSize: 12
            }
        },
        grid: {
            //left: '15',
            top: '50',
            right: '45',
            bottom: '5',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            axisTick:{show:false},
            splitLine: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#9a9a9b'
                }
            },
            axisLabel : {
                show:true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            },
            data:data.xaxis

        },
        yAxis: {
            type: 'value',
            axisTick:{show:false},
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
            splitArea: {
                show: true,
                areaStyle: {
                    color: chartsColor.areacolor
                }
            },
            axisLabel: {
                show:true,
                textStyle: {
                    color: chartsColor.linefontcolor,
                    fontFamily: 'arial'
                }
            }
        },
        color:chartsColor.ec4.facecolor4,
        series: [
            {
                name:"分公司能耗",
                type:'bar',
                barWidth: '20',
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                },
                data:data.list
            }
        ]
    }

    barchart02.setOption(option);
}


/*能源流成本明细*/
function renderEnergyFlowDetail(data){
	$('#energyFlowDetail').html('');
	var html = '';
	for(var i=0;i<data.length;i++){
		html+="<tr class=\""+(i%2==0?"":"bgc")+"\">";
        html+="<td>";
        html+="    <a href='"+_web+"/thired/cost/unit/"+data[i].unittype+"' class='need_a'>"+data[i].unitname+"</a>";
        html+="</td>";
        html+=getHtmlTd(data[i].groupE,data[i].groupS);
        html+=getHtmlTd(data[i].waterE,data[i].waterS);
        html+=getHtmlTd(data[i].elecE,data[i].elecS);
        html+=getHtmlTd(data[i].gasE,data[i].gasS);
        html+=getHtmlTd(data[i].hotE,data[i].hotS);
        html+=getHtmlTd(data[i].coalE,data[i].coalS);
        html+="</tr>";
	}
	$('#energyFlowDetail').html(html);
}
/*能源流能耗占比分布图*/
function chart2EnergyPie(data){
    console.log(data);
    var piechart_as = echarts.init(document.getElementById('piechart_as'));
    var option = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)",
            show:true
        },

        toolbox: {
            show : false,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : false,

        series : [
            {
                type:'pie',
                radius: chartsColor.chart05.facecolor5,
                silent:true,
                itemStyle : {
                    normal : {
                        color: chartsColor.ec3.facecolor1,
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    }
                },
                data:[
                    {value:1, name:'圈', selected:false,hoverAnimation:false}
                ]
            },
            {
                name:'能源流能耗占比',
                type:'pie',
                radius : ['70%', '80%'],

                // for funnel
                x: '60%',
                width: '35%',
                funnelAlign: 'left',
                itemStyle : {
                    normal : {
                        borderColor: chartsColor.ec3.facecolor1,
                        borderWidth: '2',
                        label : {show:true}
                    }
                },
                color:['#c675c3', '#8d82cc', '#3b96db', '#32bbb6', '#df614c'],
                data:data.list
            }
        ]
    };
    piechart_as.setOption(option);
}


/*能源流能耗趋势对比图*/
function chart2EnergyLine(data){

    console.log(data);
    var linechart_as = echarts.init(document.getElementById('linechart_as'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            //left: '15%',
            top: '28',
            right: '115',
            bottom: '5',
            containLabel: true
        },
        legend: {
            orient : 'vertical',
            right : '5%',
            top: '28',
            itemWidth:8,
            itemHeight:4,
            icon:'rect',
            itemGap:20,
            data:data.legends,
            textStyle:{
                color:chartsColor.ec4.facecolor5
            }
        },
        color:chartsColor.ec4.facecolor5,
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
            splitArea: {
                show: true,
                areaStyle: {
                    color: chartsColor.areacolor
                }
            },
            data: data.xDate

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

        series: data.series
//        	[
//            {
//                name:'一次网',
//                type:'line',
//                symbol: 'circle',
//                smooth: false,
//                data:data.type2
//            },
//            {
//                name:'换热站',
//                type:'line',
//                symbol: 'circle',
//                smooth: false,
//                data:data.type3
//            },
//            {
//                name:'二次线',
//                type:'line',
//                symbol: 'circle',
//                smooth: false,
//                data:data.type4
//            },
//
//            {
//                name:'民户',
//                type:'line',
//                symbol: 'circle',
//                smooth: false,
//                data:data.type5
//            },
//            {
//                name:'供热源',
//                type:'line',
//                symbol: 'circle',
//                smooth: false,
//                data:data.type1
//            }
//        ]
    };

    linechart_as.setOption(option);
}



/*能源流能耗同比*/
function chart2EnergyBar(data){
    var barchart01_as = echarts.init(document.getElementById('barchart01_as'));
    var option = {
        title:{
            subtext:'能源流成本 (单位: 万元)',
            top:'-18px',
            left:'35px',
            subtextStyle:{
                color: chartsColor.linefontcolor,
                fontStyle: 'normal',
                fontWeight: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 12
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer : {
                type : 'shadow'
            }
        },
        legend: {
            data:['今年','去年'],
            itemWidth:8,
            itemHeight:4,
            right: '40',
            top:'10px',
            textStyle: {
                color: chartsColor.linefontcolor,
                fontStyle: 'normal',
                fontWeight: 'lighter',
                fontFamily: '微软雅黑',
                fontSize: 12
            }
        },
        grid: {
            //left: '15',
            top: '50',
            right: '45',
            bottom: '5',
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
            data:['供热源','一次网','换热站','二次线','民户']

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
            splitArea: {
                show: true,
                areaStyle: {
                    color: chartsColor.areacolor
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
        color: chartsColor.ec4.facecolor3,
        series: [
            {
                name:"今年",
                type:'bar',
                barWidth: '20',
                data:data.cur
            },
            {
                name:"去年",
                type:'bar',
                barWidth: '20',
                data:data.last
            }
        ]
    }

    barchart01_as.setOption(option);
}


window.onresize = function(){
    /*groupEnergyChart();
    waterEnergyChart();
    electricEnergyChart.resize();
    gasEnergyChart.resize();
    hotEnergyChart.resize();
    coalEnergyChart.resize();

    piechart.resize();
    linechart.resize();
    barchart01.resize();
    barchart02.resize();

    piechart_as.resize();
    linechart_as.resize();
    barchart01_as.resize();*/
}