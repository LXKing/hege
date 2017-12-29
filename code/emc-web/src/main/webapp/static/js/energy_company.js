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

function waterEnergyChartFun(datalist, datelist) {
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
			},
		},
		color: chartsColor.ec2.facecolor1,
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
	waterEnergyChart.setOption(option);
}

function electricEnergyChartFun(datalist, datelist) {
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
			},
		},
		color: chartsColor.ec2.facecolor1,
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
	electricEnergyChart.setOption(option);
}

/*气能耗-折线图*/
function gasEnergyChartFun(datalist, datelist) {
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
			},
		},
		color: chartsColor.ec2.facecolor1,
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
	gasEnergyChart.setOption(option);
}

/*热能耗-折线图*/
function hotEnergyChartFun(datalist, datelist) {
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
			},
		},
		color: chartsColor.ec2.facecolor1,
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
	hotEnergyChart.setOption(option);
}

/*煤能耗-折线图*/
function coalEnergyChartFun(datalist, datelist) {
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
			},
		},
		color: chartsColor.ec2.facecolor1,
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
	coalEnergyChart.setOption(option);
}

window.onresize = function() {

}