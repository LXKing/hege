/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  bin  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/25<BR>
 */
$(function () {
    init();
    getAdministrative();
});
function init(){
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



}

function addMessage(){
    var province = $("#province").val();
    var city = $("#city").val();
    var county = $("#county").val();
    var town = $("#town").val();
    if(town != ""){

        $("input[name='pCode']").val(town);
    }else{
        $("#admTypeDiv").hide();
        if(county != ""){

            $("input[name='pCode']").val(county);
        }else{
            if(city != ""){

                $("input[name='pCode']").val(city);
            }else{
                if(province != ""){

                    $("input[name='pCode']").val(province);
                }else{

                    $("input[name='pCode']").val("");
                }
            }
        }
    }
}

/* 查询字典列表 */
function getAdministrative() {
//    layer.load('加载带文字', 3);
    var index = layer.load('1',{
         shade: [0.4, 'black'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/administrative/list',
        timeout: 30000,
        type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
        dataType: 'json',
        data: $("#administrative-form").serialize(),
        success: function (result) {
            $(".pagination").pagination({
                pageNo: result.list.page.curPage,
                rowTotal: result.list.page.totalRow,
                _callBack: getAdministrative
            });
            // 附上模板
            $("#administrative-tbody").setTemplateElement("tpl-list");
            // 给模板加载数据
            $("#administrative-tbody").processTemplate(result.list);
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


/*查询*/
var search = function(){
    $("input[id ='pageNo']").val(1);
    getAdministrative();
}




var editDictionary = function (id){
    var ids;
    if(id == undefined){
        ids= getCheckValues();
        if (ids.length == 0) {
            layer.msg("请选择一条记录进行编辑！");
            return false;
        }
        if (ids.length > 1) {
            layer.msg("只能选择一条记录进行编辑！");
            return false;
        }
    }else{
        ids = id;
    }

    $.get(ctx + '/administrative/edit/' + ids, function (result) {
        $('#layer-div').html(result);
    });
    layer.open({
        area: ['600px', '500px'],
        type: 1,
        title: '字典',
        btn: ['保存', '取消'],
        yes: function () {
            $("#editForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#layer-div')
    });
}

var delDictionary = function (id){
    var ids;
    if(id == undefined){
        ids= getCheckValues();
        if (ids.length == 0) {
            layer.msg("至少选择一条记录进行删除！");
            return false;
        }
    }else{
        ids = id;
    }
    layer.confirm('是否删除数据？', {
        btn: ['删除', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: ctx + '/administrative/delete',
            type: 'POST',
            dataType: 'json',
            data: {admCode: ids},
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    getAdministrative();
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}







/**
 * 关闭添加窗口
 */
var closeWindow = function (index) {
    layer.closeAll();
}


