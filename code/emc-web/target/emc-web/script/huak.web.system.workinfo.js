$(function () {
    loadDataFun();
});
function loadDataFun() {

    queryWorkInfo();

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
    $("#workOrderNameSearch").val("");
    $("#workerOder_type").siblings('.x-sfbgbox1').find(':input').val('请选择工单类型');
    $("#workOrderTypeSearch").val("");
}
/**
 * 分页查询
 */
function queryWorkInfo() {
    var index = top.layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: _web + '/work/order/info/list',
        type: 'POST',
        data: $("#workInfoSearch").serialize(),
        dataType: "json",
        success: function (data) {
            $("#redtipspad").text(data.list.page.total);
            $("#workOrderBody").setTemplateElement("tpl-workOrder");
            $("#workOrderBody").processTemplate(data.list);
            /*分页效果*/
            $("#paging").createPage({
                pageCount: data.list.page.pages, //总页数
                current: data.list.page.pageNumber, //当前页数
                backFn: function (p) { //单击回调方法，p是当前页码
                    $("#workInfoSearch").find('input[name="pageNumber"]').val(p);
                    queryWorkInfo();
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
                    queryWorkInfo();
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}


/**
 * 关闭工单
 */
function closeOrder(id) {
    top.layer.confirm('您是否确定关闭工单？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _web + '/work/order/info/close',
            type: 'POST',
            data:{"id":id},
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    queryWorkInfo();
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}
/**
 * 接收工单
 */
function receivedOrder(code,mid,reid) {
    top.layer.confirm('您是否确定接收工单？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _web + '/work/order/info/received',
            type: 'GET',
            data:{"code":code,"mid":mid,"reid":reid},
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    queryWorkInfo();
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}
/**
 * 确认工单
 */
function confirmOrder(id) {
    top.layer.confirm('您是否确定工单？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _web + '/work/order/info/confirm',
            type: 'POST',
            data:{"id":id},
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    queryWorkInfo();
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}
function fromatStr(str,num){
    if(num<str.length){
        return str.substr(0,num)+'...';
    }else{
        return str;
    }

}

