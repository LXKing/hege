$(function() {

	loadDataFun();

});

function loadDataFun() {
	$.each($(".energy-list .energy-chart > div"), function(index, item) {
		var options = {
			id: item.id,
			echartsConfig: {
				xData: [1, 2, 3, 4, 5, 6, 7],
				series: [{
						type: 'line',
						dataList: [1, 4, 5, 2, 3],
						typeLine: 'solid'
					},
					{
						type: 'line',
						dataList: [2, 4, 7, 1, 4, 5],
						typeLine: 'dashed'
					}
				]
			}
		};
		echartsSelf(options);
	});

	echartsSelf({
		id: 'piechart_as',
		echartsConfig: {
			xAxisBoundaryGap: true,
			dataZoom: true,
			dataZoomstartValue: 1,
			dataZoomendValue: 5,
			xData: ['站1', '站2', '站3', '站4', '站5', '站6', '站7'],
			series: [{
				type: 'bar',
				dataList: [1, 2, 3, 4, 5, 6, 7],

			}]
		}
	});

	echartsSelf({
		id: 'linechart_as',
		echartsConfig: {
			xAxisBoundaryGap: true,
			dataZoom: true,
			dataZoomstartValue: 1,
			dataZoomendValue: 5,
			xData: ['站1', '站2', '站3', '站4', '站5', '站6', '站7'],
			series: [{
				type: 'bar',
				dataList: [1, 2, 3, 4, 5, 6, 7],

			}]
		}
	});
}