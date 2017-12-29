/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/11/17<BR>
 *     能源类型
 */
$(function () {
    getEnergyTypeList();
    var config = {
        '.chosen-select': {},
        '.chosen-select-deselect': {
            allow_single_deselect: true
        },
        '.chosen-select-no-single': {
            disable_search_threshold: 10
        },
        '.chosen-select-no-results': {
            no_results_text: 'Oops, nothing found!'
        },
        '.chosen-select-width': {
            width: "10%"
        }
    }
    for (var selector in config) {
        $(selector).chosen(config[selector]);
    }
});
function getEnergyTypeList() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/energy-type/list',
        timeout: 5000,
        type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
        dataType: 'json',
        data: $("#energy-type-from").serialize(),
        success: function (result) {
            $(".pagination").pagination({
                pageNo: result.list.page.curPage,
                rowTotal: result.list.page.totalRow,
                _callBack: getEnergyTypeList
            });
            // 附上模板
            $("#energy-type-tbody").setTemplateElement("tpl-list");
            // 给模板加载数据
            $("#energy-type-tbody").processTemplate(result.list);
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green'
            });
            layer.close(index);
        },
        error: function () {
            layer.close(index);
            layer.msg("加载失败");
        },
        complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
            layer.close(index);
            if (status == 'timeout') {//超时,status还有success,error等值的情况
                layer.msg("加载超时");
            }
        }
    });
}
//layer
function editEnergyType() {
    var id = getCheckValues();
    if (id.length == 0) {
        layer.msg("请选择要编辑的能源类型");
        return false;
    }
    if (id.split(',').length > 1) {
        layer.msg("请选择一个能源类型进行编辑");
        return false;
    }

    $.get(ctx + '/energy-type/edit/' + id, function (result) {
        $('#layer-div').html(result);
    });
    layer.open({
        area: ['500px', '600px'],
        type: 1,
        title: '编辑能源类型',
        btn: ['保存', '取消'],
        yes: function () {
            $("#energyTypeEditForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#layer-div')
    });
}

function addEnergyType() {
    $.get(ctx + '/energy-type/add', function (result) {
        $('#layer-div').html(result);
    });
    layer.open({
        area: ['500px', '600px'],
        type: 1,
        title: '添加能源类型',
        btn: ['保存', '取消'],
        yes: function () {
            $("#energyTypeAddForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#layer-div')
    });
}

function deleteEnergyTypes() {

    var ids = getCheckValues();
    if (ids.length == 0) {
        layer.msg("请选择要删除的能源类型");
        return false;
    }
    layer.confirm('您是否确定删除所选能源类型？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: ctx + '/energy-type/delete',
            data: {ids: ids},
            type: 'POST',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    $("#content-main").empty().load(ctx + '/energy-type/list');
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}

function deleteEnergyType(id) {
    layer.confirm('您是否确定删除能源类型？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: ctx + '/energy-type/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    $("#content-main").empty().load(ctx + '/energy-type/list');
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}