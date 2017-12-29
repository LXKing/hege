

function loadDataFun(){

//加载能源流明细
    $.ajax({
        url : _web+"/energy/secondary/detail",
        type : "POST",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            EnergyDetailList(data);
        }
    });
    function EnergyDetailList(data){
        var html = "";
        $.each(data.list,function(idx,item){
            html +='<tr class="'+(idx%2 == 0?"":"bgc")+'">';
            html +='<td><a href="'+_web+'/third/analysis/unit/'+item.id+'" class="need_a">';
            if(item.id=="1"){
                html+="供热源"+'</a></td>';
            }else if(item.id=="2"){
                html+="一次网"+'</a></td>';
            }else if(item.id=="3"){
                html+="换热站"+'</a></td>';
            }else if(item.id=="4"){
                html+="二次线"+'</a></td>';
            }else if(item.id=="5"){
                html+="民户"+'</a></td>';
            }
            html +=getHtmlTd(item.totalBq,item.totalTq);
            html +=getHtmlTd(item.waterBq,item.waterTq);
            html +=getHtmlTd(item.electricBq,item.electricTq);
            html +=getHtmlTd(item.gasBq,item.gasTq);
            html +=getHtmlTd(item.heatBq,item.heatTq);
            html +=getHtmlTd(item.coalBq,item.coalTq);
            html +=getHtmlTd(item.oilBq,item.oilTq);
            html +='</tr>';
        });
        $("#EnergyDetailTbody").html(html);
    }

    function getHtmlTd(bq,an){
        return '<td class="need_title">'+bq+'（同<span class="'+(an == 0?"":(an > 0?"redcolor":"bluecolor"))+'">'+toDecimal(an)+'%'+(an == 0?"→":(an > 0?"↑":"↓"))+'</span>）</td>';
    }


    //能源流能耗占比分布图
    $.ajax({
        url : _web+"/energy/secondary/proportion",
        type : "POST",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            chart05Fun(data);
        }
    });
    //能源流趋势分布图
    $.ajax({
        url : _web+"/energy/secondary/trend",
        type : "POST",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            chart06Fun(data);
        }
    });
    //能源流趋势分布图
    $.ajax({
        url : _web+"/energy/secondary/an",
        type : "POST",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            chart07Fun(data);
        }
    });
    //加载分公司单耗
    $.ajax({
        url : _web+"/cons/analysis/fgs/list",
        type : "POST",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            fgsEnergyList(data);
        }
    });
    $.ajax({
        url : _web+"/cons/analysis/fgs/ratio",
        type : "POST",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            chart01Fun(data.list);
        }
    });
    $.ajax({
        url : _web+"/cons/analysis/fgs/trend",
        type : "POST",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            chart02Fun(data);
        }
    });
    $.ajax({
        url : _web+"/cons/analysis/fgs/an",
        type : "POST",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            chart03Fun(data);
        }
    });
    $.ajax({
        url : _web+"/cons/analysis/fgs/ranking",
        type : "POST",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
            chart04Fun(data);
        }
    });
    $.ajax({
        url : _web+"/cons/analysis/groupDanHao",
        type : "GET",
        data:$("#searchTools").serialize(),
        dataType: "json",
        success : function(data) {
        	$("#groupTotal").html(toFormatNum(data.data.groupTotal.energy.value));
//        	if(data.data.groupTotal.energy.type == 1){
//        		$("#groupTotal").addClass("energy_gray");
//        	};
            if(data.data.groupTotal.changeRate.type == 1){
            	$("#groupchangeRate").css('color','#3db1b0');
                $("#groupchangeRate").html(data.data.groupTotal.changeRate.rate + "<span class='arrow'>↓</span>");
            }else if(data.data.groupTotal.changeRate.type == 0){
                $("#groupchangeRate").html(data.data.groupTotal.changeRate.rate + "<span class='arrow'>↑</span>");
            }else if(data.data.groupTotal.changeRate.type == -1){
            	$("#groupchangeRate").css('color','#999');
            	$("#groupchangeRate").html("0" + "<span class='arrow'>→</span>");
            }
            //水单耗
            console.log(data.data.waterTotal.energy.value);
            $("#waterTotal").html(toFormatNum(data.data.waterTotal.energy.value));
            if(data.data.waterTotal.energy.type == 1){
                $("#waterTotal").closest(".energy-head").addClass("energy-snh");
            }else{
                $("#waterTotal").next("span").addClass("energy-remind");
                $("#waterTotal").addClass("energy-remind");
                $("#waterTotal").closest(".energy-head").addClass("energy-snh-remind");
            }
            if(data.data.waterTotal.changeRate.type == 1){
            	$("#waterchangeRate").css('color','#3db1b0');
                $("#waterchangeRate").html("("+data.data.waterTotal.changeRate.rate + "↓)");
            }else if(data.data.waterTotal.changeRate.type == 0){
                $("#waterchangeRate").addClass("energy-remind");
                $("#waterchangeRate").html("("+data.data.waterTotal.changeRate.rate + "↑)");
            }else if(data.data.waterTotal.changeRate.type == -1){
            	$("#waterchangeRate").css('color','#999');
                $("#waterchangeRate").html("("+data.data.waterTotal.changeRate.rate + "→)");
            }
            //电单耗
            $("#electricTotal").html(toFormatNum(data.data.electricTotal.energy.value));
            if(data.data.electricTotal.energy.type == 1){
                $("#electricTotal").closest(".energy-head").addClass("energy-dnh");
            }else{
                $("#electricTotal").next("span").addClass("energy-remind");
                $("#electricTotal").addClass("energy-remind");
                $("#electricTotal").closest(".energy-head").addClass("energy-dnh-remind");
            };
            if(data.data.electricTotal.changeRate.type == 1){
            	$("#elechangeRate").css('color','#3db1b0');
                $("#elechangeRate").html("("+data.data.electricTotal.changeRate.rate + "↓)");
            }else if(data.data.electricTotal.changeRate.type == 0){
                $("#elechangeRate").html("("+data.data.electricTotal.changeRate.rate + "↑)");
                $("#elechangeRate").addClass("energy-remind");
            }else if(data.data.electricTotal.changeRate.type == -1){
            	$("#elechangeRate").css('color','#999');
                $("#elechangeRate").html("("+data.data.electricTotal.changeRate.rate + "→)");
            };
            //气单耗
            $("#gasTotal").html(toFormatNum(data.data.gasTotal.energy.value));
            if(data.data.gasTotal.energy.type == 1){
                $("#gasTotal").closest(".energy-head").addClass("energy-qnh");
            }else{
                $("#gasTotal").next("span").addClass("energy-remind");
                $("#gasTotal").addClass("energy-remind");
                $("#gasTotal").closest(".energy-head").addClass("energy-qnh-remind");
            };
            if(data.data.gasTotal.changeRate.type == 1){
            	$("#gaschangeRate").css('color','#3db1b0');
                $("#gaschangeRate").html("("+data.data.gasTotal.changeRate.rate + "↓)");
            }else if(data.data.gasTotal.changeRate.type == 0){
                $("#gaschangeRate").html("("+data.data.gasTotal.changeRate.rate + "↑)");
                $("#gaschangeRate").addClass("energy-remind");
            }else if(data.data.gasTotal.changeRate.type == -1){
            	$("#gaschangeRate").css('color','#999');
                $("#gaschangeRate").html("("+data.data.gasTotal.changeRate.rate + "→)");
            };

            //热单耗
            $("#hotTotal").html(toFormatNum(data.data.hotTotal.energy.value));
            if(data.data.hotTotal.energy.type == 1){
                $("#hotTotal").closest(".energy-head").addClass("energy-rnh");
            }else{
                $("#hotTotal").next("span").addClass("energy-remind");
                $("#hotTotal").addClass("energy-remind");
                $("#hotTotal").closest(".energy-head").addClass("energy-rnh-remind");
            };
            if(data.data.hotTotal.changeRate.type == 1){
            	$("#hotchangeRate").css('color','#3db1b0');
                $("#hotchangeRate").html("("+data.data.hotTotal.changeRate.rate + "↓)");
            }else if(data.data.hotTotal.changeRate.type == 0){
                $("#hotchangeRate").html("("+data.data.hotTotal.changeRate.rate + "↑)");
                $("#hotchangeRate").addClass("energy-remind");
            }else if(data.data.hotTotal.changeRate.type == -1){
            	$("#hotchangeRate").css('color','#999');
                $("#hotchangeRate").html("("+data.data.hotTotal.changeRate.rate + "→)");
            };

            //煤单耗
            $("#coalTotal").html(toFormatNum(data.data.coalTotal.energy.value));
            if(data.data.coalTotal.energy.type == 1){
                $("#coalTotal").closest(".energy-head").addClass("energy-mnh");
            }else{
                $("#coalTotal").next("span").addClass("energy-remind");
                $("#coalTotal").addClass("energy-remind");
                $("#coalTotal").closest(".energy-head").addClass("energy-mnh-remind");
            };
            if(data.data.coalTotal.changeRate.type == 1){
            	$("#coalchangeRate").css('color','#3db1b0');
                $("#coalchangeRate").html("("+data.data.coalTotal.changeRate.rate + "↓)");
            }else if(data.data.coalTotal.changeRate.type == 0){
                $("#coalchangeRate").html("("+data.data.coalTotal.changeRate.rate + "↑)");
                $("#coalchangeRate").addClass("energy-remind");
            }else if(data.data.coalTotal.changeRate.type == -1){
            	$("#coalchangeRate").css('color','#999');
                $("#coalchangeRate").html("("+data.data.coalTotal.changeRate.rate + "→)");
            };

            groupEnergyChartFun(data.data.groupEnergy.data, data.data.groupEnergy.yearDate);
            waterEnergyChartFun(data.data.waterEnergy.data, data.data.waterEnergy.yearDate);
            electricEnergyChartFun(data.data.electricEnergy.data, data.data.electricEnergy.yearDate);
            gasEnergyChartFun(data.data.gasEnergy.data, data.data.gasEnergy.yearDate);
            hotEnergyChartFun(data.data.hotEnergy.data, data.data.hotEnergy.yearDate);
            coalEnergyChartFun(data.data.coalEnergy.data, data.data.coalEnergy.yearDate);


        }
    });

    //chart05Fun();
    //chart06Fun();
    // chart07Fun();
}
function fgsEnergyList(data){

    var html = "";
    $.each(data.list,function(idx,item){
        html +='<tr class="'+(idx%2 == 0?"":"bgc")+'">';
        html +='<td><a href="'+_web+'/third/analysis/fgs/'+item.id+'" class="need_a">'+item.orgName+'</a></td>';
        html +=getHtmlTd(item.totalBq,item.totalAn);
        html +=getHtmlTd(item.waterBq,item.waterAn);
        html +=getHtmlTd(item.electricBq,item.electricAn);
        html +=getHtmlTd(item.gasBq,item.gasAn);
        html +=getHtmlTd(item.heatBq,item.heatAn);
        html +=getHtmlTd(item.coalBq,item.coalAn);
        html +=getHtmlTd(item.oilBq,item.oilAn);
        html +='</tr>';
    });
    $("#fgsEnergyTbody").html(html);
}

function getHtmlTd(bq,an){
    return '<td class="need_title">'+toDecimal(bq)+'（同<span class="'+(an == 0?"":(an > 0?"redcolor":"bluecolor"))+'">'+toDecimal(an)+'%'+(an == 0?"→":(an > 0?"↑":"↓"))+'</span>）</td>';
}
/*集团总单耗-折线图*/
function groupEnergyChartFun(datalist, datelist){
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

function waterEnergyChartFun(datalist, datelist){
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

function electricEnergyChartFun(datalist, datelist){
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

/*气单耗-折线图*/
function gasEnergyChartFun(datalist, datelist){
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

/*热单耗-折线图*/
function hotEnergyChartFun(datalist, datelist){
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

/*煤单耗-折线图*/
function coalEnergyChartFun(datalist, datelist){
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








/*分公司单耗占比分布图*/
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
                name:'分公司单耗占比',
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
                color:color,
                data:data

            }
        ]
    };
    piechart.setOption(option);
}


/*分公司单耗趋势对比图*/
function chart02Fun(data){
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


/*分公司单耗同比*/
function chart03Fun(data) {
    var barchart01 = echarts.init(document.getElementById('barchart01'));
    var option = {
        title:{
            subtext:'单耗 (单位: Tce/万m²)',
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
            data:data.xaxis

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


/*分公司单耗排名---barchart02*/
function chart04Fun(data){
    var	barchart02 = echarts.init(document.getElementById('barchart02'));
    var option = {
        title:{
            subtext:'单耗 (单位: Tce/万m²)',
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
            data:data.xaxis

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
        color: chartsColor.ec4.facecolor4,
        series: [
            {
                name:"分公司单耗",
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

/*能源流单耗占比分布图------*/
function chart05Fun(data){
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
                        color:'#fff',
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
                name:'能源流单耗占比',
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
                color:color,
                data:data.list

            }
        ]
    };
    piechart_as.setOption(option);
}


/*能源流单耗趋势对比图*/
function chart06Fun(data){
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


        series: data.list
    };

    linechart_as.setOption(option);
}


/*能源流单耗同比*/
function chart07Fun(data){
    var barchart01_as = echarts.init(document.getElementById('barchart01_as'));
    var option = {
        title:{
            subtext:'能源流单耗 (单位: GJ/万m²)',
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
            data:data.xaxis

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
                data:data.bq
            },
            {
                name:"去年",
                type:'bar',
                barWidth: '20',
                data:data.tq
            }
        ]
    }

    barchart01_as.setOption(option);
}


window.onresize = function(){
    groupEnergyChart.resize();
    waterEnergyChart.resize();
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
    barchart01_as.resize();
}