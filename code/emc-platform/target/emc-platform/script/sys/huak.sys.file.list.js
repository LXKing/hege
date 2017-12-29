/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/11/03<BR>
 */
$(function () {
    $(document).ready(function () {
        $('.file-box').each(function () {
            animationHover(this, 'pulse');
        });
        var $css1 = $('.file-control');
        $(document).on('click', '.file-control', function (event) {
            var $this = $(this);
            $css1.each(function(){
                $(this).removeClass('active');
            });
            $this.addClass('active');
            getClass();
        });
    });
    //初始化时间插件
    getFileList();
});

var getClass = function (){
    var $css = $('.file-control.active');
    var $model = $('#templateupload');
    var $data = $('#webupload');
    var $file = $('#fileupload');
    if($css.html() == '模板'){
        $model.show();
        $data.hide();
        $file.hide();
    }else if($css.html() == '文件'){
        $data.hide();
        $file.show();
        $model.hide();
    }else if($css.html() == '数据'){
        $model.hide();
        $data.show();
        $file.hide();
    }
}

function getFileList() {
    var index = layer.load(1, {
        shade: [0.1, 'black'] //0.1透明度的白色背景
    });
    var obj = $("#file-form").serialize();
    $.ajax({
        url: ctx + '/file/list',
        timeout: 5000,
        type: 'PATCH',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
        dataType: 'json',
        data:obj ,
        success: function (result) {
            layer.close(index);
        },
        error: function (e) {
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
function editfile() {
    var id = getCheckValues();
    if (id.length == 0) {
        layer.msg("请选择要编辑的文件");
        return false;
    }
    if (id.split(',').length > 1) {
        layer.msg("请选择一个文件进行编辑");
        return false;
    }

    $.get(ctx + '/file/edit/' + id, function (result) {
        $('#layer-div').html(result);
    });
}

function uploadFile() {
    $.get(ctx + '/file/add',{type:0}, function (result) {
        $('#layer-div').html(result);
    });
    layer.open({
        area: ['550px', '500px'],
        type: 1,
        title: '上传数据',

        skin: 'layer-ext-moon', //样式类名
        yes: function () {
            $("#fileAddForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: false, //开启遮罩关闭
        content: $('#layer-div')
    });
}


function uploadModel() {
    $.get(ctx + '/file/add',{type:1}, function (result) {
        $('#layer-div').html(result);
    });
    layer.open({
        area: ['550px', '300px'],
        type: 1,
        title: '添加模板',
        btn: ['保存', '取消'],
        skin: 'layer-ext-moon', //样式类名
        yes: function () {
            $("#fileAddForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: false, //开启遮罩关闭
        content: $('#layer-div')
    });
}



function deletefile() {

    var ids = getCheckValues();
    if (ids.length == 0) {
        layer.msg("请选择要删除的文件");
        return false;
    }
    layer.confirm('您是否确定删除所选文件？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: ctx + '/file/delete',
            data: {ids: ids},
            type: 'POST',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    $("#content-main").empty().load(ctx + '/file/list');
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}


