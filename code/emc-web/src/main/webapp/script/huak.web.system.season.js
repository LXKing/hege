$(function () {
    loadDataFun();
});

function loadDataFun() {
    //日期范围限制
    var start = {
        elem: '#start',
        format: 'YYYY/MM/DD hh:mm:ss',
        //min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY/MM/DD hh:mm:ss',
        max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
    querySeasonConfig();


}

function reset() {
    $("#nameSearch").val("");
    $("#start").val("");
    $("#end").val("");
}
/**
 * 分页查询
 */
function querySeasonConfig() {
    var index = top.layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url: _web + '/season/list',
        type: 'POST',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: $("#seasonSearch").serialize(),
        dataType: "json",
        success: function (data) {
            $("#redtipspad").text(data.list.page.total);
            $("#seasonBody").setTemplateElement("tpl-season");
            $("#seasonBody").processTemplate(data.list);
            /*分页效果*/
            $("#paging").createPage({
                pageCount: data.list.page.pages, //总页数
                current: data.list.page.pageNumber, //当前页数
                backFn: function (p) { //单击回调方法，p是当前页码
                    $("#seasonSearch").find('input[name="pageNumber"]').val(p);
                    querySeasonConfig();
                }
            });
            top.layer.close(index);
        }
    });
}

/**
 * 删除
 */
function delSeasonConfig(id) {
    top.layer.confirm('您是否确定删除？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _web + '/season/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    querySeasonConfig();
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
