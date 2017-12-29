/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  bin  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/25<BR>
 */
$(function () {
    init();
    getUnitList();
});
function init() {
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
    function getUnitList() {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: ctx + '/unit/list',
            timeout: 30000,
            type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
            dataType: 'json',
            data: $("#unit-search-form").serialize(),
            success: function (result) {
                $(".pagination").pagination({
                    pageNo: result.list.page.curPage,
                    rowTotal: result.list.page.totalRow,
                    _callBack: getUnitList
                });
                // 附上模板
                $("#unit-tbody").setTemplateElement("unit-list");
                // 给模板加载数据
                $("#unit-tbody").processTemplate(result.list);
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
    var searchunit = function () {
        $("input[id ='pageNo']").val(1);
        getUnitList();
    }

/*重置查询条件*/
var resetunitSearch = function(){
    $("#unit-search-form").find("div").find("input").val("");
}

var delunit = function (id){
    var ids;
    if(id == undefined){
        ids= getCheckValues("unitListTable");
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
            url: ctx + '/unit/delete',
            type: 'POST',
            dataType: 'json',
            data: {unitId: ids},
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    layer.msg(result.msg);
                    getUnitList();
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}







