/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/11/03<BR>
 */
$(function () {
    //初始化时间插件
    $('#s_date').datepicker();
    $('#e_date').datepicker();

    getSeasonList();
});
function getSeasonList() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/season/list',
        timeout: 5000,
        type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
        dataType: 'json',
        data: $("#seasons-from").serialize(),
        success: function (result) {
            $(".pagination").pagination({
                pageNo: result.list.page.curPage,
                rowTotal: result.list.page.totalRow,
                _callBack: getSeasonList
            });
            // 附上模板
            $("#season-tbody").setTemplateElement("tpl-list");
            // 给模板加载数据
            $("#season-tbody").processTemplate(result.list);
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
function editSeason() {
    var id = getCheckValues();
    if (id.length == 0) {
        layer.msg("请选择要编辑的采暖季");
        return false;
    }
    if (id.split(',').length > 1) {
        layer.msg("请选择一个采暖季进行编辑");
        return false;
    }

    $.get(ctx + '/season/edit/' + id, function (result) {
        $('#layer-div').html(result);
    });
    layer.open({
        area: ['600px', '500px'],
        type: 1,
        title: '编辑采暖季',
        btn: ['保存', '取消'],
        yes: function () {
            $("#seasonEditForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#layer-div')
    });
}

function addSeason() {
    $.get(ctx + '/season/add', function (result) {
        $('#layer-div').html(result);
    });
    layer.open({
        area: ['500px', '500px'],
        type: 1,
        title: '添加采暖季',
        btn: ['保存', '取消'],
        yes: function () {
            $("#seasonAddForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#layer-div')
    });
}

function deleteSeasons() {

    var ids = getCheckValues();
    if (ids.length == 0) {
        layer.msg("请选择要删除的采暖季");
        return false;
    }
    layer.confirm('您是否确定删除所选采暖季？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: ctx + '/season/delete',
            data: {ids: ids},
            type: 'POST',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    $("#content-main").empty().load(ctx + '/season/list');
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}


