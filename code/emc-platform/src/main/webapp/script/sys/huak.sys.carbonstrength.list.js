/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  bin  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/25<BR>
 */
$(function () {
    init();
    getStrengthList();
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
/* 查询碳排放强度列表 */
function getStrengthList() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/carbonstrength/list',
        timeout: 15000,
        type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
        dataType: 'json',
        data: $("#carbonstrength-form").serialize(),
        success: function (result) {
            $(".pagination").pagination({
                pageNo: result.list.page.curPage,
                rowTotal: result.list.page.totalRow,
                _callBack: getStrengthList
            });
            // 附上模板
            $("#carbonstrength-tbody").setTemplateElement("carbonstrength-list");
            // 给模板加载数据
            $("#carbonstrength-tbody").processTemplate(result.list);
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
var reset = function(){

}

/*查询*/
var searchFormulaList = function(){
    getStrengthList();
}


/* 跳转到添加页并弹出窗口 */
function addcarbonstrength() {
    $('#layer-div').html("");
    $.get(ctx + '/carbonstrength/add', function (result) {
        $('#layer-div').html(result);
    });
    window =layer.open({
        area: ['750px', '500px'],
        type: 1,
        title: '碳排放强度新增',
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 1,
        btn: ['保存', '取消'],
        scrollbar: false,
        yes: function () {
            $("#carbonstrengthAddForm").submit();
        },
        content: $('#layer-div')
    });
}

var editcarbonstrength = function (id){
    var ids;
    if(id == undefined){
        ids= getCheckValues("carbonstrength-table-list");
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

    $.get(ctx + '/carbonstrength/edit/' + ids, function (result) {
        $('#layer-div').html(result);
    });
    layer.open({
        area: ['600px', '500px'],
        type: 1,
        title: '碳排放强度',
        btn: ['保存', '取消'],
        yes: function () {
            $("#carbonstrengthEditForm").submit();
        },
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 2,
        shadeClose: true, //开启遮罩关闭
        content: $('#layer-div')
    });
}

var delcarbonstrength = function (id){
    var ids;
    if(id == undefined){
        ids= getCheckValues("carbonstrength-table-list");
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
            url: ctx + '/carbonstrength/delete',
            type: 'POST',
            dataType: 'json',
            data: {strid: ids},
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    getStrengthList();
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}


/* 添加碳排放强度回调函数 */
function addCollBack(){
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url:ctx + '/carbonstrength/add' ,
        data:$("#carbonstrengthAddForm").serialize(),
        type:'POST',
        dataType:'json',
        success:function(result) {
            if(result.flag){
                closeWindow();
                getStrengthList();
                layer.msg(result.msg);
                layer.close(index);
            }else{
                layer.close(index);
                layer.msg(result.msg);
            }
        }
    });
}

/* 修改碳排放强度回调函数 */
function editCollBack(){
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url:ctx + '/carbonstrength/edit' ,
        data:$("#carbonstrengthEditForm").serialize(),
        type:'POST',
        dataType:'json',
        success:function(result) {
            if(result.flag){
                closeWindow();
                getStrengthList();
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


