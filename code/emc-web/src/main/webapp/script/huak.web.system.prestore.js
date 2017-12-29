$(function(){
    loadDataFun();
})
function loadDataFun() {

    // http://www.treejs.cn/v3/main.php#_zTreeInfo
    query(1);
//    var setting = {
//        data: {
//            simpleData: {
//                enable: true
//            }
//        }
//    };
//    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, nodes);

////table 数据查询
//    var unitName=$("#unitName").val();
//    var name=$("#name").val();
//    var sTime=$("#startTime").val();
//    var eTime=$("#endTime").val();
//    alert(unitName);
//    $.ajax({
//        url: _web + '/record/prestore/list',
//        type: 'post',
//        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
//        data: {unitName:unitName,name:name,startTime:sTime,endTime:eTime},
//        dataType: "json",
//        success: function (data) {
//            console.log(data.list);
//            console.log(data.list.page);
//            $("#redtipspad").text(data.list.page.total);
//            // 附上模板
//            $("#projectTbody").setTemplateElement("template");
//            // 给模板加载数据
//            $("#projectTbody").processTemplate(data.list);
//            /*分页效果*/
//            $("#paging").createPage({
//                pageCount: data.list.page.pages, //总页数
//                current: data.list.page.pageNumber, //当前页数
//                backFn: function (p) { //单击回调方法，p是当前页码
//                    console.log(p);
//                }
//            });
//        }
//    });


}
function query(num){

//table 数据查询
    var unitName=$("#unitName").val();
    var name=$("#name").val();
    var sTime=$("#startTime").val();
    var eTime=$("#endTime").val();
    console.log(unitName);
    $.ajax({
        url: _web + '/record/prestore/list',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: {unitName:unitName,name:name,startTime:sTime,endTime:eTime,pageNumber:num},
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
/*导出列表*/
$(document).on("click", ".exportprestor", function () {
    var $from = $("#formForExport");
    var url = $(this).attr('export-url') + '?' + $from.serialize();
    window.open(url);
});

function reset(){
    $("#unitName").val("");
    $("#name").val("");
    $("#startTime").val("");
    $("#endTime").val("");
}