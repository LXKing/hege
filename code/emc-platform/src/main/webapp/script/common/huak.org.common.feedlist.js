/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  bin  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/25<BR>
 */
$(function () {
    init();
    getCommonFeedList();
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
function getCommonFeedList() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: ctx + '/common/feedlist',
        timeout: 30000,
        type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
        dataType: 'json',
        data: $("#feed-common-form").serialize(),
        success: function (result) {
            $(".feed_pagination").pagination({
                pageNo: result.list.page.curPage,
                rowTotal: result.list.page.totalRow,
                pageNoId: "feed_pageNo",//页数id
                pageGoId: "feed_page-go",
                _callBack: getCommonFeedList
            });
            // 附上模板
            $("#feed-common-tbody").setTemplateElement("common-feed-datalist");
            // 给模板加载数据
            $("#feed-common-tbody").processTemplate(result.list);
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


var setValues = function(){
    var $this = top.documentText;
    var $span = $this.parent();
    var $textInput=$span.siblings('input:text');
    var $hiddenInput=$span.siblings('input:hidden');
    var objects=  getCheckItems("common-feed-list");
    if(objects.length>1){
        parent.layer.msg('只能选择一个热源信息！', {shade: 0.3});
        return false;
    }
    if(objects.length<1){
        parent.layer.msg('请选择一个热源信息！', {shade: 0.3});
        return false;
    }
    $($hiddenInput).val(objects[0].id);
    $($textInput).val(objects[0].name);
    parent.layer.close(top.layerIndex);
    $(top.documentWindows).remove(".common-org-window");
}


/*查询*/
var findAll = function () {
    $("input[id ='pageNo']").val(1);
    getCommonFeedList();
}









