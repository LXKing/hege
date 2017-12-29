$(function () {
    loadDataFun();
});

function loadDataFun() {
    queryAllocation();

    $("body").off("click", ".x-sfbgbox1").on("click", ".x-sfbgbox1", function () {
        $(this).next().stop(true, false).slideToggle(200, function () {
        });
    });
    //下拉切换事件
    $("body").off("click", ".x-sfoption1 p").on("click", ".x-sfoption1 p", function (event) {
        var selectval = $(this).html();
        var selectid = $(this).attr("value");

        $(this).parent().siblings('input:hidden').val(selectid);
        $(this).parent().prev().find("input").val(selectval);

        if('unit_type'==$(this).parent().attr('id')){
            $("#index_type").find('.x-sfbgbox1').find(':input').val('请选择指标类型');
            $("#index_type").find('input:hidden').val('');
            var $sfoption1 =  $("#index_type").find('.x-sfoption1:hidden');
            $.ajax({
                url: _web + '/select/index/type',
                type: 'POST',
                async:true,
                data: {unitType:selectid},
                dataType: 'json',
                success: function (result) {
                    $sfoption1.empty();
                    $.each(result, function (idx, item) {
                        $sfoption1.append('<p value="' + item.INDEXID + '">' + item.INDEXNAME + '</p>');
                    });
                }
            });
        }
        $(this).parent().slideUp(200, function () {
        });
        event.stopPropagation();
    });
    $("body").on("mouseleave", ".x-selectfree", function (event) {
        $(this).find(".x-sfoption1").slideUp(200, function () {
        });
        event.stopPropagation();
    });
}

function reset() {
    $("#unitNameSearch").val("");
    $("#unit_type").siblings('.x-sfbgbox1').find(':input').val('请选择单位类型');
    $("#index_type").find('.x-sfbgbox1').find(':input').val('请先选择单位类型');
    $("#unitTypeSearch").val("");
    $("#typeIdSearch").val("");
}
/**
 * 分页查询
 */
function queryAllocation() {
    var index = top.layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: _web + '/index/allocation/list',
        type: 'POST',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: $("#allocationSearch").serialize(),
        dataType: "json",
        success: function (data) {
            $("#redtipspad").text(data.list.page.total);
            $("#allocationBody").setTemplateElement("tpl-allocation");
            $("#allocationBody").processTemplate(data.list);
            /*分页效果*/
            $("#paging").createPage({
                pageCount: data.list.page.pages, //总页数
                current: data.list.page.pageNumber, //当前页数
                backFn: function (p) { //单击回调方法，p是当前页码
                    $("#allocationSearch").find('input[name="pageNumber"]').val(p);
                    queryAllocation();
                }
            });
            top.layer.close(index);
        }
    });
}

/**
 * 删除
 */
function delAllocation(id) {
    top.layer.confirm('您是否确定删除？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _web + '/index/allocation/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    queryAllocation();
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}
