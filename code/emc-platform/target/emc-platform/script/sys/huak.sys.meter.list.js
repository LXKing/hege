/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/30<BR>
 */
var bTable;
var orgTree;

$(function () {
    init();
    initable();
});

function init (){
    //日期范围限制
    var start = {
        elem: '#start',
        format: 'YYYY/MM/DD hh:mm:ss',
        //min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY/MM/DD hh:mm:ss',
        max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    //下拉框js
    $(".chosen-select").chosen();
}

/**
 *  计量仪器管理-批量导入-excel
 */
function uploaderExcel() {
    $.get(_platform + '/meterCollect/uploadPage', function (result) {
        var $top = $(top.document);
        var layerDiv = '<div id="layer-div"></div>';
        $top.find('body').append(layerDiv);
        $top.find("#layer-div").html(result);
        top.layer.open({
            area: ['550px', '500px'],
            type: 1,
            title: "批量上传",
            maxmin: true,
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,//出场动画
            shadeClose: true, //开启遮罩关闭
            cancel: function (index, layero) {
                top.uploader.destroy();
                top.layer.closeAll();
                $('#meter-table-list').bootstrapTable('refresh');
                return false;
            },
            content: $top.find("#layer-div")
        });
    });
}

function initable() {

    bTable = $('#meter-table-list').bootstrapTable({
        height: getHeight() + 30,//高度
        cache: false,//禁用 AJAX 数据缓存
        url: _platform + '/meterCollect/list',//获取数据的Servlet地址
        method: 'post',//使用POST请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        idField: "ID",
        pagination: true,//是否分页
        pageSize: 10,//每页显示的记录数
        pageNumber: 1,//当前第几页
        pageList: [10, 30, 50],//记录数可选列表
        search: false,  //是否启用查询
        striped: true,//表格显示条纹
        //showColumns: false,//不显示隐藏列
        sidePagination: "server", //服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: params,
        formatLoadingMessage: function () {
            return "请稍等，正在加载中...";
        },
        responseHandler: function (res) {

            return {
                "rows": res.list.list,
                "total": res.list.page.total
            };
        },
        columns: [
            {
                field: 'id', title: 'ID', visible: false
            },
            /*{
             checkbox: true
             },*/
            {
                title: '序号',
                field: 'sn',
                align: 'center',
                formatter: function (value, row, index) {
                    return index + 1;
                }
            },
            {
                title: '代码',
                field: 'code',
                align: 'center'
            },
            {
                title: '出厂编号',
                field: 'serialNo',
                align: 'center'
            },
            {
                title: '名称',
                field: 'name',
                align: 'center'
            },
            {
                title: '单位名称',
                field: 'unitname',
                align: 'center'
            },
            {
                title: '能源类型',
                field: 'energyTypeId',
                align: 'center'
            },
            {
                title: '实虚表',
                field: 'isreal',
                align: 'center',
                formatter: function (value, row, index) {
                    if (value == '0') {
                        return '实表';
                    }
                    if (value == '1') {
                        return '虚表 ';
                    }
                    return '';
                }
            },
            {
                title: '总表',
                field: 'istotal',
                align: 'center',
                formatter: function (value, row, index) {
                    if (value == '1') {
                        return '单位总表';
                    }
                    if (value == '2') {
                        return '系统总表 ';
                    }
                    if (value == '0') {
                        return '否 ';
                    }
                    return '';
                }
            },
            {
                title: '系数',
                field: 'coef',
                align: 'center'
            },
            {
                title: '单位类型',
                field: 'unitType',
                align: 'center',
                formatter: function (value, row, index) {
                    if (value == '1') {
                        return '热源';
                    }
                    if (value == '2') {
                        return '管网';
                    }
                    if (value == '3') {
                        return '热力站';
                    }
                    if (value == '4') {
                        return '管线';
                    }
                    if (value == '5') {
                        return '民户';
                    }
                    return '';
                }
            },
            {
                title: '采集',
                field: 'isauto',
                align: 'center',
                formatter: function (value, row, index) {
                    if (value == '0') {
                        return '自动采集';
                    }
                    if (value == '1') {
                        return '手工 ';
                    }
                    return '';
                }
            },
            {
                title: '点表',
                field: 'tag',
                align: 'center',
                formatter: function (value, row, index) {
                    if(value!=null&&value!="") {
                        if (value.length > 6) {
                            return '<span title="' + value + '">' + value.substr(0, 6) + '</span>';
                        }
                        return value;
                    }
                    return value;
                }
            },
            {
                title: '公式',
                field: 'formula',
                align: 'center',
                formatter: function (value, row, index) {
                    if(value!=null&&value!="") {
                        if (value.length > 6) {
                            return '<span title="' + value + '">' + value.substr(0, 6) + '</span>';
                        }
                        return value;
                    }
                    return value;
                }
            },
//            {
//                title: '预存',
//                field: 'isprestore',
//                align: 'center',
//                formatter: function (value, row, index) {
//                    if (value == '0') {
//                        return '不是';
//                    }
//                    if (value == '1') {
//                        return '是 ';
//                    }
//                    return '';
//                }
//            },
            {
                title: '删除标识',
                field: 'isdelete',
                align: 'center',
                formatter: function (value, row, index) {
                    if (value == '0') {
                        return '未删除';
                    }
                    if (value == '1') {
                        return '软删除标识 ';
                    }
                    return '';
                }
            },
            {
                title: '描述',
                field: 'depict',
                align: 'center',
                formatter: function (value, row, index) {
                    if(value!=null&&value!=""){
                    if (value.length > 6) {
                        return '<span title="' + value + '">' + value.substr(0, 6) + '</span>';
                    }
                    return value;
                    }
                    return value;
                }
            },
            {
                title: '操作',
                field: 'opt',
                align: 'center',
                formatter: function (value, row, index) {

                    var html = "";
                    if ($("#meterCollectUpdate").val()) {
                        html += '<a title="编辑" class="btn btn-xs btn-info top-layer-min" layer-form-id="MeterCollectEditForm" layer-title="编辑计量器具" layer-url="' + _platform + '/meterCollect/edit/' + row.id + '/'+row.comId+'"> <i class="fa fa-edit"></i></a>&nbsp;';
                    }
                    if ($("#meterCollectDelete").val()) {
                        html += '<a title="删除" class="btn btn-xs btn-danger" onclick="deleteMeter(&quot;' + row.id + '&quot;)"><i class="fa fa-trash-o"></i></a>&nbsp;';
                    }
                    if ($("#meterCollectDelete").val()) {
                        if (row.isreal == '0') {
                            html += '<a title="换表" class="btn btn-xs btn-info " onclick="changeMeter(&quot;' + row.id + '&quot;)"> <i class="fa  fa-magic"></i></a>&nbsp;';
//                            if(row.isprestore == '1' ){
//                                html += '<a title="预存" class="btn btn-xs btn-info" onclick="prestoreMeter(&quot;' + row.id + '&quot;)"> <i class="fa fa-edit"></i></a>&nbsp;';
//                            }
                        }
                    }
                    return html;
                }
            }

        ]


    });


}

//计量仪器-换表
function changeMeter(id) {
    openLayer(_platform + "/meterCollect/change/"+id, "计量器具表更换", "meterCollectChangeForm", null, null);
}


//计量仪器-预存
function prestoreMeter(id) {
    openLayer(_platform + "/meterCollect/prestore/"+id, "计量器具预存", "meterCollectPrestoreForm", null, null);
}

function search() {
    $('#meter-table-list').bootstrapTable('refresh');
}
//layer
function deleteMeter(id) {
    top.layer.confirm('您是否确定删除吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _platform + '/meterCollect/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    $('#meter-table-list').bootstrapTable("refresh");
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}

function addMeterCollect() {
    var comId = $(top.document).find("[name='searchComp']").val();
    openLayer(_platform + "/meterCollect/add/"+comId, "添加计量器具表", "addMeterCollectAddForm", null, null);
}
function params(params) {
    var ts = $(top.document).find("[name='searchComp']").val();
    $("#comId").val(ts);
    return $("#meter-form").serialize();
}


