
var orgTree;

$(function(){
    init();
    //loadDataFun();
})

function init(){
    //下拉框js
    var org = new Org({
        class:"org-tree"
    });
    orgTree =  org.initTree();

}
//组织机构树点击节点事件
var treeNodeClick = function(e,treeId,treeNode){
    console.log(treeNode.id);
    $("#orgId").val(treeNode.id);
    query(1);
};

function query(num){
//table 数据查询

    var data = $("#onecenet-formData").serialize();
    console.log(data);
    $.ajax({
        url: _web + '/oncenet/listpage',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: $("#onecenet-formData").serialize(),
        dataType: "json",
        success: function (data) {
            console.info(data);
            console.info(data.list);

                $("#redtipspad").text(data.list.page.total);
                $("#projectTbody").setTemplateElement("template");
                $("#projectTbody").processTemplate(data.list);
                /*分页效果*/
                $("#paging").createPage({
                    pageCount: data.list.page.pages, //总页数
                    current: data.list.page.pageNumber, //当前页数
                    backFn: function (p) { //单击回调方法，p是当前页码
                        query(p);
                    }
                });

        }
    });
}

function reset(){

    $("#energyType").prev().find("input").val("请选择能源类型");
    $("#netName").val("");
    $("#netCode").val("");

}
//下拉切换事件
$("body").on("click", "#energyType p", function () {
    alert(12);
    var selectval = $(this).html();
    var selectid = $(this).attr("value");

    $('#energyType').val(selectid);
    $(this).parent().prev().find("input").val(selectval);
    $(this).parent().slideUp(200, function () {
    });
});

//下拉切换事件
$("body").on("click", "#x-sfoption1 p", function (event) {
    alert(11);
    var selectval = $(this).html();
    var selectid = $(this).attr("value");
    $(this).parent().prev().find("input").val(selectval);
    $("#unitId").val(selectid);
    $(this).parent().slideUp(200, function () {
    });
    event.stopPropagation();
});
$("body").on("mouseleave", ".x-selectfree", function (event) {
    $(this).find(".x-sfoption1").slideUp(200, function () {
    });
    event.stopPropagation();
});

/*导出列表*/
//$(document).on("click", ".exportchange", function () {
//    var $from = $("#formForExport");
//    var url = $(this).attr('export-url') + '?' + $from.serialize();
//    window.open(url);
//});
//function loadDataFun() {
//    query(1);
//}
//
//function reset(){
//   $("#unitName").val("");
//   $("#name").val("");
//   $("#startTime").val("");
//   $("#endTime").val("");
//}
//function query(num){
//
////table 数据查询
//    var unitName=$("#unitName").val();
//    var name=$("#name").val();
//    var sTime=$("#startTime").val();
//    var eTime=$("#endTime").val();
//    console.log(unitName);
//    $.ajax({
//        url: _web + '/record/change/list',
//        type: 'post',
//        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
//        data: {unitName:unitName,name:name,startTime:sTime,endTime:eTime,pageNumber:num},
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
//                    query(p);
//                }
//            });
//        }
//    });
//}
