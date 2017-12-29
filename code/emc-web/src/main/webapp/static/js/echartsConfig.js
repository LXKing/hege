var echartsSelf = function(options) {

    var echartObj;
    this.init = function() {
        echartObj = echarts.init(document.getElementById(options.id));

    };
    this.createChart = function() {

        var seriesData = [];
        for(var i = 0; i < options.echartsConfig.series.length; i++) {
            var _item = options.echartsConfig.series[i];
            var seriesitem = {
                name: _item.name,
                type: _item.type,
                symbol: 'circle',
                smooth: false,
                yAxisIndex: _item.yAxisIndex,
                lineStyle: {
                    normal: {
                        type: _item.typeLine || 'solid'

                    }
                },
                itemStyle: {
                    normal: {
                        label : _item.label || null,
                        color: _item.barColor || null
                    }
                },
                barGap: 0,
                data: _item.dataList,
                barWidth: _item.barWidth || null

            };
            seriesData.push(seriesitem);
        }
        var option = {
            dataZoom: {
                type: 'slider',
                show: options.echartsConfig.dataZoom || false,
                startValue: options.echartsConfig.dataZoomstartValue || null,
                endValue: options.echartsConfig.dataZoomendValue || null,
//				zoomLock: true,
                height: 18,
                backgroundColor:faceKey == 'dark' ? 'rgba(57,220,214,.07)' :  '#dcdfe3',
                showDetail: false,
                showDataShadow: false,
                borderColor: faceKey == 'dark' ? 'transparent' : '#dcdfe3',
                fillerColor: faceKey == 'dark' ? 'rgba(51,255,248,.2)' : '#ffffff',
                //				handleSize:1,
                handleIcon: 'M0,0 v9.7h1 v-9.7h-1 Z',
                handleStyle: {
                    color: faceKey == 'dark' ? 'rgba(51,255,248,1)' : '#277aba',
                },
                dataBackground: {}
            },
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '0',
                top: '10',
                right: '0',
                bottom: options.echartsConfig.dataZoom ? '80' : '10',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: options.echartsConfig.xAxisBoundaryGap || false,
                axisTick: {
                    show: false
                },
                splitArea: {
                    show: options.echartsConfig.bg && options.echartsConfig.bg == 'row' ? false : true,
                    areaStyle: {
                        color: chartsColor.areacolor
                    }
                },
                splitLine: {
                    show: options.echartsConfig.bg && options.echartsConfig.bg == 'row' ? false : true,
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
                    },

                    interval:(options.echartsConfig.axisLabelInterval=='undefined'||options.echartsConfig.axisLabelInterval==null)?'auto':options.echartsConfig.axisLabelInterval,
                    rotate: options.echartsConfig.axisLabelRotate || 0
                },
                data: options.echartsConfig.xData

            },
            yAxis: [{
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
                    show: options.echartsConfig.bg && options.echartsConfig.bg == 'row' ? true : false,
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
                splitArea: {
                    show: options.echartsConfig.bg && options.echartsConfig.bg == 'row' ? true : false,
                    areaStyle: {
                        color: chartsColor.areacolor
                    }
                }
            }, {
                type: 'value',
                position: 'right',
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
                        fontFamily: 'arial',
                        format: '{value}%'
                    }
                }
            }],
            color: chartsColor.ec2.facecolor1,
            series: seriesData
        };
        echartObj.setOption(option);

    };
    this.init();
    this.createChart();

    return echartObj;

};