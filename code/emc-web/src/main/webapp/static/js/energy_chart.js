loadDataFun();

function loadDataFun() {

	$.ajax({
		url: "json/6-1.json",
		type: "GET",
		dataType: "json",
		error: function(request) {
			alert("Connection error");
		},
		success: function(data) {
			$("#groupTotal").html(data.data.groupTotal.energy.value);
			if(data.data.groupTotal.energy.type == 1) {
				$("#groupTotal").addClass("energy_gray");
			};
			if(data.data.groupTotal.changeRate.type == 1) {
				$("#groupchangeRate").addClass("energy_gray2");
				$("#groupchangeRate").html(data.data.groupTotal.changeRate.rate + "<span class='arrow'>↓</span>");
			} else {
				$("#groupchangeRate").html(data.data.groupTotal.changeRate.rate + "<span class='arrow'>↑</span>");
			};
			//水能耗
			$("#waterTotal").html(data.data.waterTotal.energy.value);
			if(data.data.waterTotal.energy.type == 1) {
				$("#waterTotal").closest(".energy-head").addClass("energy-snh");
			} else {
				$("#waterTotal").next("span").addClass("energy-remind");
				$("#waterTotal").addClass("energy-remind");
				$("#waterTotal").closest(".energy-head").addClass("energy-snh-remind");
			};
			if(data.data.waterTotal.changeRate.type == 1) {
				$("#waterchangeRate").html("(" + data.data.waterTotal.changeRate.rate + "↓)");
			} else {
				$("#waterchangeRate").addClass("energy-remind");
				$("#waterchangeRate").html("(" + data.data.waterTotal.changeRate.rate + "↑)");
			};
			//电能耗
			$("#electricTotal").html(data.data.electricTotal.energy.value);
			if(data.data.electricTotal.energy.type == 1) {
				$("#electricTotal").closest(".energy-head").addClass("energy-dnh");
			} else {
				$("#electricTotal").next("span").addClass("energy-remind");
				$("#electricTotal").addClass("energy-remind");
				$("#electricTotal").closest(".energy-head").addClass("energy-dnh-remind");
			};
			if(data.data.electricTotal.changeRate.type == 1) {
				$("#elechangeRate").html("(" + data.data.electricTotal.changeRate.rate + "↓)");
			} else {
				$("#elechangeRate").html("(" + data.data.electricTotal.changeRate.rate + "↑)");
				$("#elechangeRate").addClass("energy-remind");
			};
			//气能耗
			$("#gasTotal").html(data.data.gasTotal.energy.value);
			if(data.data.gasTotal.energy.type == 1) {
				$("#gasTotal").closest(".energy-head").addClass("energy-qnh");
			} else {
				$("#gasTotal").next("span").addClass("energy-remind");
				$("#gasTotal").addClass("energy-remind");
				$("#gasTotal").closest(".energy-head").addClass("energy-qnh-remind");
			};
			if(data.data.gasTotal.changeRate.type == 1) {
				$("#gaschangeRate").html("(" + data.data.gasTotal.changeRate.rate + "↓)");
			} else {
				$("#gaschangeRate").html("(" + data.data.gasTotal.changeRate.rate + "↑)");
				$("#gaschangeRate").addClass("energy-remind");
			};

			//热能耗
			$("#hotTotal").html(data.data.hotTotal.energy.value);
			if(data.data.hotTotal.energy.type == 1) {
				$("#hotTotal").closest(".energy-head").addClass("energy-rnh");
			} else {
				$("#hotTotal").next("span").addClass("energy-remind");
				$("#hotTotal").addClass("energy-remind");
				$("#hotTotal").closest(".energy-head").addClass("energy-rnh-remind");
			};
			if(data.data.hotTotal.changeRate.type == 1) {
				$("#hotchangeRate").html("(" + data.data.hotTotal.changeRate.rate + "↓)");
			} else {
				$("#hotchangeRate").html("(" + data.data.hotTotal.changeRate.rate + "↑)");
				$("#hotchangeRate").addClass("energy-remind");
			};

			//煤能耗
			$("#coalTotal").html(data.data.coalTotal.energy.value);
			if(data.data.coalTotal.energy.type == 1) {
				$("#coalTotal").closest(".energy-head").addClass("energy-mnh");
			} else {
				$("#coalTotal").next("span").addClass("energy-remind");
				$("#coalTotal").addClass("energy-remind");
				$("#coalTotal").closest(".energy-head").addClass("energy-mnh-remind");
			};
			if(data.data.coalTotal.changeRate.type == 1) {
				$("#coalchangeRate").html("(" + data.data.coalTotal.changeRate.rate + "↓)");
			} else {
				$("#coalchangeRate").html("(" + data.data.coalTotal.changeRate.rate + "↑)");
				$("#coalchangeRate").addClass("energy-remind");
			};

			groupEnergyChartFun(data.data.groupEnergy.data, data.data.groupEnergy.yearDate);
			waterEnergyChartFun(data.data.waterEnergy.data, data.data.waterEnergy.yearDate);
			electricEnergyChartFun(data.data.electricEnergy.data, data.data.electricEnergy.yearDate);
			gasEnergyChartFun(data.data.gasEnergy.data, data.data.gasEnergy.yearDate);
			hotEnergyChartFun(data.data.hotEnergy.data, data.data.hotEnergy.yearDate);
			coalEnergyChartFun(data.data.coalEnergy.data, data.data.coalEnergy.yearDate);

			 chart07Fun();
			 chart08Fun();
			 chart09Fun();
			 chart10Fun();
			 chart11Fun();
			 chart12Fun();
		}
	});
}

/*集团总能耗-折线图*/
function groupEnergyChartFun(datalist, datelist) {
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
					fontFamily: 'arial',
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
			},
		},
		color: chartsColor.ec1.facecolor5,
		series: []
	}
	$.each(datalist, function(index, data) {
		var typeName = data.typeName;
		var typeLine = "";
		if(index == 0) {
			typeLine = "solid";
		}
		if(index == 1) {
			typeLine = "dashed";
		}
		var item = {
			name: typeName,
			type: 'line',
			symbol: 'circle',
			smooth: false,
			lineStyle: {
				normal: {
					type: typeLine
				}
			},
			data: data.dataList
		}
		option.series.push(item);
	});
	groupEnergyChart.setOption(option);
}

function waterEnergyChartFun(data, date) {
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
				show: false,
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
					fontFamily: 'arial',
				}
			},
			data: date

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
			},
		},
		color: chartsColor.ec2.facecolor1,
		series: []
	}
	$.each(data, function(index, data) {
		var typeName = data.typeName;
		var typeLine = "";
		if(index == 0) {
			typeLine = "solid";
		}
		if(index == 1) {
			typeLine = "dashed";
		}
		var item = {
			name: typeName,
			type: 'line',
			symbol: 'circle',
			smooth: false,
			lineStyle: {
				normal: {
					type: typeLine
				}
			},
			data: data.dataList
		}
		option.series.push(item);
	});
	waterEnergyChart.setOption(option);
}

function electricEnergyChartFun(data, date) {
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
				show: false,
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
					fontFamily: 'arial',
				}
			},
			data: date

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
			},
		},
		color: chartsColor.ec2.facecolor1,
		series: []
	}
	$.each(data, function(index, data) {
		var typeName = data.typeName;
		var typeLine = "";
		if(index == 0) {
			typeLine = "solid";
		}
		if(index == 1) {
			typeLine = "dashed";
		}
		var item = {
			name: typeName,
			type: 'line',
			symbol: 'circle',
			smooth: false,
			lineStyle: {
				normal: {
					type: typeLine
				}
			},
			data: data.dataList
		}
		option.series.push(item);
	});
	electricEnergyChart.setOption(option);
}

/*气能耗-折线图*/
function gasEnergyChartFun(data, date) {
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
				show: false,
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
					fontFamily: 'arial',
				}
			},
			data: date

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
			},
		},
		color: chartsColor.ec2.facecolor1,
		series: []
	}
	$.each(data, function(index, data) {
		var typeName = data.typeName;
		var typeLine = "";
		if(index == 0) {
			typeLine = "solid";
		}
		if(index == 1) {
			typeLine = "dashed";
		}
		var item = {
			name: typeName,
			type: 'line',
			symbol: 'circle',
			smooth: false,
			lineStyle: {
				normal: {
					type: typeLine
				}
			},
			data: data.dataList
		}
		option.series.push(item);
	});
	gasEnergyChart.setOption(option);
}

/*热能耗-折线图*/
function hotEnergyChartFun(data, date) {
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
				show: false,
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
					fontFamily: 'arial',
				}
			},
			data: date

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
			},
		},
		color: chartsColor.ec2.facecolor1,
		series: []
	}
	$.each(data, function(index, data) {
		var typeName = data.typeName;
		var typeLine = "";
		if(index == 0) {
			typeLine = "solid";
		}
		if(index == 1) {
			typeLine = "dashed";
		}
		var item = {
			name: typeName,
			type: 'line',
			symbol: 'circle',
			smooth: false,
			lineStyle: {
				normal: {
					type: typeLine
				}
			},
			data: data.dataList
		}
		option.series.push(item);
	});
	hotEnergyChart.setOption(option);
}

/*煤能耗-折线图*/
function coalEnergyChartFun(data, date) {
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
				show: false,
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
					fontFamily: 'arial',
				}
			},
			data: date

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
			},
		},
		color: chartsColor.ec2.facecolor1,
		series: []
	}
	$.each(data , function(index, data) {
		var typeName = data.typeName;
		var typeLine = "";
		if(index == 0) {
			typeLine = "solid";
		}
		if(index == 1) {
			typeLine = "dashed";
		}
		var item = {
			name: typeName,
			type: 'line',
			symbol: 'circle',
			smooth: false,
			lineStyle: {
				normal: {
					type: typeLine
				}
			},
			data: data.dataList
		}
		option.series.push(item);
	});
	coalEnergyChart.setOption(option);
}

/*分公司能耗占比分布图*/
function chart07Fun() {
	var piechart = echarts.init(document.getElementById('piechart'));
	var option = {

		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)",
			show: true
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

		series: [{
				type: 'pie',
				radius: ['0', '80%'],
				silent: true,
				itemStyle: {
					normal: {
						color: chartsColor.ec3.facecolor1,
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					}
				},
				data: [{
					value: 1,
					name: '圈',
					selected: false,
					hoverAnimation: false,
				}]
			},
			{
				name: '分公司能耗占比',
				type: 'pie',
				radius: ['60%', '80%'],

				// for funnel
				x: '60%',
				width: '35%',
				funnelAlign: 'left',
				itemStyle: {
					normal: {
						borderColor: chartsColor.ec3.facecolor1,
						borderWidth: '4',
						label: {
							show: true
						}
					}
				},
				color:chartsColor.ec4.facecolor5,
				data: [{
						value: 335,
						name: '朝一'
					},
					{
						value: 310,
						name: '朝二'
					},
					{
						value: 251,
						name: '丰台'
					},
					{
						value: 234,
						name: '东城'
					},
					{
						value: 135,
						name: '西城'
					},
					{
						value: 1048,
						name: '海淀'
					}
				]

			}
		]
	};
	piechart.setOption(option);
}

/*分公司能耗趋势对比图*/
function chart08Fun() {
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
			orient: 'vertical',
			right: '5%',
			top: '28',
			itemWidth: 8,
			itemHeight: 4,
			icon: 'rect',
			itemGap: 20,
			data: ['朝一', '朝二', '丰台', '东城', '西城', '海淀']
		},
		color: chartsColor.ec4.facecolor5,
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
			data: ['2015-11', '2015-12', '2016-01', '2016-02', '2016-03']

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
			},
		},

		series: [{
				name: '朝一',
				type: 'line',
				symbol: 'circle',
				smooth: false,
				data: [120, 132, 101, 134, 90]
			},
			{
				name: '朝二',
				type: 'line',
				symbol: 'circle',
				smooth: false,
				data: [140, 112, 51, 34, 69]
			},
			{
				name: '丰台',
				type: 'line',
				symbol: 'circle',
				smooth: false,
				data: [220, 182, 191, 234, 290]
			},
			{
				name: '东城',
				type: 'line',
				symbol: 'circle',
				smooth: false,
				data: [150, 232, 201, 154, 190]
			},
			{
				name: '西城',
				type: 'line',
				symbol: 'circle',
				smooth: false,
				data: [320, 332, 301, 334, 390]
			},
			{
				name: '海淀',
				type: 'line',
				symbol: 'circle',
				smooth: false,
				data: [820, 932, 901, 934, 629]
			}
		]
	};

	linechart.setOption(option);
}

/*分公司能耗同比*/
function chart09Fun() {
	var barchart01 = echarts.init(document.getElementById('barchart01'));
	var option = {
		title: {
			subtext: '分公司能耗 (单位: GJ/万m²)',
			top: '-18px',
			left: '35px',
			subtextStyle: {
				color: chartsColor.linefontcolor,
				fontStyle: 'normal',
				fontWeight: 'normal',
				fontFamily: '微软雅黑',
				fontSize: 12,
			}
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'shadow'
			}
		},
		legend: {
			data: ['今年', '去年'],
			itemWidth: 8,
			itemHeight: 4,
			right: '40',
			top: '10px',
			textStyle: {
				color: chartsColor.linefontcolor,
				fontStyle: 'normal',
				fontWeight: 'lighter',
				fontFamily: '微软雅黑',
				fontSize: 12,
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
				show: false,
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
					fontFamily: 'arial',
				}
			},
			data: ['朝一', '朝二', '丰台', '东城', '西城', '海淀']

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
			},
		},
		color: chartsColor.ec4.facecolor3,
		series: [{
				name: "今年",
				type: 'bar',
				barWidth: '20',
				data: [10, 52, 200, 334, 390, 330]
			},
			{
				name: "去年",
				type: 'bar',
				barWidth: '20',
				data: [10, 52, 200, 334, 390, 330]
			}
		]
	}

	barchart01.setOption(option);
}
/*分公司能耗排名---barchart02*/

function chart10Fun() {
	var barchart02 = echarts.init(document.getElementById('barchart02'));
	var option = {
		title: {
			subtext: '分公司能耗 (单位: GJm²㎡)',
			top: '-18px',
			left: '35px',
			subtextStyle: {
				color: chartsColor.linefontcolor,
				fontStyle: 'normal',
				fontWeight: 'normal',
				fontFamily: '微软雅黑',
				fontSize: 12,
			}
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'shadow'
			}
		},
		legend: {
			data: ['今年', '去年'],
			itemWidth: 8,
			itemHeight: 4,
			right: '40',
			top: '10px',
			textStyle: {
				color: chartsColor.linefontcolor,
				fontStyle: 'normal',
				fontWeight: 'lighter',
				fontFamily: '微软雅黑',
				fontSize: 12,
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
				show: false,
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
					fontFamily: 'arial',
				}
			},
			data: ['朝一', '朝二', '丰台', '东城', '西城', '海淀']

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
			},
		},
		color: chartsColor.ec4.facecolor4,
		series: [{
			name: "分公司能耗",
			type: 'bar',
			barWidth: '20',
			markLine: {
				data: [{
					type: 'average',
					name: '平均值'
				}]
			},
			data: [10, 52, 200, 334, 390, 330]
		}]
	}

	barchart02.setOption(option);
}
/*能源流能耗占比分布图*/

function chart11Fun() {
	var piechart_as = echarts.init(document.getElementById('piechart_as'));
	var option = {

		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)",
			show: true
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

		series: [{
				type: 'pie',
				radius: ['0', '80%'],
				silent: true,
				itemStyle: {
					normal: {
						color: chartsColor.ec3.facecolor1,
						label: {
							show: false
						},
						labelLine: {
							show: false
						}
					}
				},
				data: [{
					value: 1,
					name: '圈',
					selected: false,
					hoverAnimation: false,
				}]
			},
			{
				name: '能源流能耗占比',
				type: 'pie',
				radius: ['60%', '80%'],

				// for funnel
				x: '60%',
				width: '35%',
				funnelAlign: 'left',
				itemStyle: {
					normal: {
						borderColor: chartsColor.ec3.facecolor1,
						borderWidth: '4',
						label: {
							show: true
						}
					}
				},
				color:chartsColor.ec4.facecolor6,
				data: [{
						value: 335,
						name: '一次网'
					},
					{
						value: 310,
						name: '换热站'
					},
					{
						value: 251,
						name: '二次网'
					},
					{
						value: 135,
						name: '民户'
					},
					{
						value: 1048,
						name: '供热源'
					}
				]

			}
		]
	};
	piechart_as.setOption(option);
}
/*能源流能耗趋势对比图*/
function chart12Fun() {
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
			orient: 'vertical',
			right: '5%',
			top: '28',
			itemWidth: 8,
			itemHeight: 4,
			icon: 'rect',
			itemGap: 20,
			data: ['一次网', '换热站', '二次网', '民户', '供热源']
		},
		color: chartsColor.ec4.facecolor6,
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
			data: ['2015-11', '2015-12', '2016-01', '2016-02', '2016-03']

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
			},
		},

		series: [{
				name: '一次网',
				type: 'line',
				symbol: 'circle',
				smooth: false,
				data: [120, 132, 101, 134, 90]
			},
			{
				name: '换热站',
				type: 'line',
				symbol: 'circle',
				smooth: false,
				data: [140, 112, 51, 34, 69]
			},
			{
				name: '二次网',
				type: 'line',
				symbol: 'circle',
				smooth: false,
				data: [220, 182, 191, 234, 290]
			},

			{
				name: '民户',
				type: 'line',
				symbol: 'circle',
				smooth: false,
				data: [320, 332, 301, 334, 390]
			},
			{
				name: '供热源',
				type: 'line',
				symbol: 'circle',
				smooth: false,
				data: [820, 932, 901, 934, 629]
			}
		]
	};

	linechart_as.setOption(option);

	/*能源流能耗同比*/
	var barchart01_as = echarts.init(document.getElementById('barchart01_as'));
	var option = {
		title: {
			subtext: '能源流能耗 (单位: Gm²/㎡)',
			top: '-18px',
			left: '35px',
			subtextStyle: {
				color: chartsColor.linefontcolor,
				fontStyle: 'normal',
				fontWeight: 'normal',
				fontFamily: '微软雅黑',
				fontSize: 12,
			}
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'shadow'
			}
		},
		legend: {
			data: ['今年', '去年'],
			itemWidth: 8,
			itemHeight: 4,
			right: '40',
			top: '10px',
			textStyle: {
				color: chartsColor.linefontcolor,
				fontStyle: 'normal',
				fontWeight: 'lighter',
				fontFamily: '微软雅黑',
				fontSize: 12,
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
				show: false,
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
					fontFamily: 'arial',
				}
			},
			data: ['供热源', '一次网', '换热站', '二次网', '民户']

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
			},
		},
		color: chartsColor.ec4.facecolor3,
		series: [{
				name: "今年",
				type: 'bar',
				barWidth: '20',
				data: [10, 52, 200, 390, 330]
			},
			{
				name: "去年",
				type: 'bar',
				barWidth: '20',
				data: [10, 52, 200, 334, 330]
			}
		]
	}

	barchart01_as.setOption(option);
}
window.onresize = function() {
	 
}