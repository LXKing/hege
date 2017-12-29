/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  bin  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/25<BR>
 */
$(function () {
    init();
    getTownetList();
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
/* 查询字典列表 */
function getTownetList() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/secondnet/list',
        timeout: 15000,
        type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
        dataType: 'json',
        data: $("#secondnet-search-form").serialize(),
        success: function (result) {
            $(".pagination").pagination({
                pageNo: result.list.page.curPage,
                rowTotal: result.list.page.totalRow,
                _callBack: getTownetList
            });
            // 附上模板
            $("#secondnet-tbody").setTemplateElement("secondnet-list");
            // 给模板加载数据
            $("#secondnet-tbody").processTemplate(result.list);
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
/*重置查询条件*/
var resetSearchForm = function(){
    debugger;
    $("#secondnet-search-form").find("div").find("input").val("");
    $("#netTypeId").chosen("destroy").val("").chosen();
}

/*查询*/
var searchSecondnetList = function(){
    $("input[id ='pageNo']").val(1);
    getTownetList();
}

var window;
/* 跳转到添加页并弹出窗口 */
function addSecondnet() {
    $.get(ctx + '/secondnet/add', function (result) {
        $('#layer-div').html(result);
    });
    window =layer.open({
        area: ['550px', '550px'],
        type: 1,
        title: '二次管网',
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 1,
        btn: ['保存', '取消'],
        scrollbar: false,
        yes: function () {
            $("#secondnet_add_form").submit();
        },
        content:  $('#layer-div')
    });
}



/*
 *
 * */
var getNode = function () {
    var station = new Station({
        width:800,
        height: 600,
        value:"secondnetStationId",
        text:"secondnetStationName",
        windowId:"layer-div"
    });
    station.show();
}

var editSecondnet = function (id){
    var ids;
    if(id == undefined){
        var  objects= getCheckObject("secondnet-list-table");
        if (objects.length == 0) {
            layer.msg("请选择一条记录进行编辑！");
            return false;
        }
        if (objects.length > 1) {
            layer.msg("只能选择一条记录进行编辑！");
            return false;
        }
        ids = objects[0].id;
    }else{
        ids = id;
    }

    $.get(ctx + '/secondnet/edit/' + ids, function (result) {
        $('#layer-div').html(result);
    });
    layer.open({
        area: ['600px', '550px'],
        type: 1,
        title: '二次网修改',
        btn: ['保存', '取消'],
        yes: function () {
            $("#secondnet_edit_form").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#layer-div')
    });
}

var delSecondnet = function (id){
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
            url: ctx + '/secondnet/delete',
            type: 'POST',
            dataType: 'json',
            data: {secondnettId: ids},
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    getTownetList();
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}


/* 添加字典回调函数 */
function addCollBack(){
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url:ctx + '/secondnet/add' ,
        data:$("#secondnet_add_form").serialize(),
        type:'POST',
        dataType:'json',
        success:function(result) {
            if(result.flag){
                closeWindow();
                getTownetList();
                layer.msg(result.msg);
                layer.close(index);
            }else{
                layer.close(index);
                layer.msg(result.msg);
            }
        }
    });
}

/* 修改字典回调函数 */
function editCollBack(){
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url:ctx + '/secondnet/edit' ,
        data:$("#secondnet_edit_form").serialize(),
        type:'POST',
        dataType:'json',
        success:function(result) {
            if(result.flag){
                closeWindow();
                getTownetList();
                layer.msg(result.msg);
                layer.close(index);
            }else{
                layer.close(index);
                layer.msg(result.msg);
            }
        }
    });
}


/**
 * 关闭添加窗口
 */
var closeWindow = function (index) {
    layer.closeAll();
}


