$(function () {
    loadDataFun();
});

function loadDataFun() {
    queryAlarmConfig();

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
    $("#unitNameSearch").val("");
    $("#unit_type").siblings('.x-sfbgbox1').find(':input').val('请选择单位类型');
    $("#alarm_type").find('.x-sfbgbox1').find(':input').val('请选择报警类型');
    $("#alarm_level").find('.x-sfbgbox1').find(':input').val('请选择报警等级');
    $("#unitTypeSearch").val("");
    $("#alarmLevelSearch").val("");
    $("#alarmTypeSearch").val("");
    $("#alarmNameSearch").val("");
    $("#tagSearch").val("");
}
/**
 * 分页查询
 */
function queryAlarmConfig() {
    var index = top.layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: _web + '/alarm/config/list',
        type: 'POST',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: $("#alarmConfigSearch").serialize(),
        dataType: "json",
        success: function (data) {
            $("#redtipspad").text(data.list.page.total);
            $("#alarmConfigBody").setTemplateElement("tpl-alarmConfig");
            $("#alarmConfigBody").processTemplate(data.list);
            /*分页效果*/
            $("#paging").createPage({
                pageCount: data.list.page.pages, //总页数
                current: data.list.page.pageNumber, //当前页数
                backFn: function (p) { //单击回调方法，p是当前页码
                    $("#alarmConfigSearch").find('input[name="pageNumber"]').val(p);
                    queryAlarmConfig();
                }
            });
            top.layer.close(index);
        }
    });
}

/**
 * 删除
 */
function delAlarmConfig(id) {
    top.layer.confirm('您是否确定删除？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _web + '/alarm/config/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    queryAlarmConfig();
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}

/**
 * 前台-安全与后台-工况报警-批量导入-excel
 */
function uploaderExcel() {
    $.get(_web + '/alarm/config/upload/page', function (result) {
        var $top = $(top.document);
        var layerDiv = '<div id="layer-div"></div>';
        $top.find('body').append(layerDiv);
        $top.find("#layer-div").html(result);
        top.layer.open({
            area: ['550px', '500px'],
            type: 1,
            title: "批量上传",
            scrollbar: false,
            maxmin: true,
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,//出场动画
            shadeClose: true, //开启遮罩关闭
            cancel: function (index, layero) {
                top.uploader.destroy();
                top.layer.closeAll();
                return false;
            },
            content: $top.find("#layer-div")
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
