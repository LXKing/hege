$(function(){
    loadDataFun();
})

/*导出列表*/
$(document).on("click", ".exportchange", function () {
    var $from = $("#formForExport");
    var url = $(this).attr('export-url') + '?' + $from.serialize();
    window.open(url);
});
function loadDataFun() {
    query(1);

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




function reset(){
    $("#power_type").siblings('.x-sfbgbox1').find(':input').val('东线');
    $("#powertype").val("");
   $("#startTime").val("");
   $("#endTime").val("");
}


function query(num){

//table 数据查询
    var sTime=$("#startTime").val();
    var eTime=$("#endTime").val();
    $.ajax({
        url: _web + '/history/data/list',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: {startTime:sTime,endTime:eTime,pageNumber:num},
        dataType: "json",
        success: function (data) {
            console.log(data.list);
            console.log(data.list.page);
            $("#redtipspad").text(data.list.page.total);
            // 附上模板
            $("#projectTbody").setTemplateElement("template");
            // 给模板加载数据
            $("#projectTbody").processTemplate(data.list);
            /*分页效果*/
            $("#paging").createPage({
                pageCount: data.list.page.pages, //总页数
                current: data.list.page.pageNumber, //当前页数
                backFn: function (p) { //单击回调方法，p是当前页码
                    console.log(p);
                    query(p);
                }
            });
        }
    });
}
