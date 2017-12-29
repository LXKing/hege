
var dataMap =null;
$(function(){
    dataMap=new Map();
    loadDataFun();
})

function loadDataFun() {

    query(1);
    var $div,value,$hidden;
    $('.editTable').on('click','.icon-edit',function(){
        //单击td事件
        var $this = $(this).parents("tr").find(".td-edit");
        $div = $this.find('.div-edit');
        value = Number($div.text());
        $hidden = $this.find('input:hidden');
        var input = '<input class="input-edit" type="text" value="' + value + '">';
        $this.empty().append($hidden).append(input);
        $this.find('.input-edit').focus();
    }).on('click','tr .input-edit',function(event){
        event.stopPropagation();//阻止冒泡
    }).on('focusout','tr .input-edit',function(){
        //失去焦点事件
        var $this = $(this);
        var $td = $this.parent('.td-edit');
        var num = Number($this.val());
        //console.log(num);
        if(num != value){
            //显示保存按钮
            $("#data-save").show();
            // 修改标记
            var $flag = $td.parent('tr').find('input[name="flag"]');
            var $id = $td.parent('tr').find('input[name="id"]');
            $flag.val(1);//1为用修改  2为不用修改
            dataMap.remove($id);
        }
        $hidden.val(num);
        $div.empty().append(num).append($hidden);
        $td.empty().append($div);
    });
}

function reset(){
    $("#x-sfoption1").prev().find("input").val("请选择用能单位");
    $("#energyType").prev().find("input").val("请选择能源类型");
    $("#unitId").val("");
    $("#name").val("");
    $("#name").val("");
    $("#energyType").val("");
    $("#startTime").val("");
    $("#endTime").val("");
}
//下拉切换事件
$("body").on("click", "#energyType p", function () {
    var selectval = $(this).html();
    var selectid = $(this).attr("value");

    $('#energyType').val(selectid);
    $(this).parent().prev().find("input").val(selectval);
    $(this).parent().slideUp(200, function () {
    });
});

//下拉切换事件
$("body").on("click", "#x-sfoption1 p", function (event) {
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

function query(num){
//table 数据查询
    dataMap.clear();
    var unitId=$("#unitId").val();
    var name=$("#name").val();
    var startTime=$("#startTime").val();
    var endTime=$("#endTime").val();
    var energyType =$("#energyType").val();
//    alert(unitName);
//    alert(energyType);
//    alert(name);
//    alert(startTime);
//    alert(endTime);
    $.ajax({
        url: _web + '/data/fill/list/data',
        type: 'post',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: {name:name,startTime:startTime,endTime:endTime,unitId:unitId,energyType:energyType},
        dataType: "json",
        success: function (data) {
            console.info(data);
            if(data.flag){
                $.each(data.object,function(index,value){
                    if(value.realFlag == 0){
                        dataMap.put(value.id,value.id);
                    }
                });
                $("#redtipspad").text(data.object.page.total);
                $("#projectTbody").setTemplateElement("template");
                $("#projectTbody").processTemplate(data);
                /*分页效果*/
                $("#paging").createPage({
                    pageCount: data.object.page.pages, //总页数
                    current: data.object.page.pageNumber, //当前页数
                    backFn: function (p) { //单击回调方法，p是当前页码
                        query(p);
                    }
                });
            }
        }
    });
}
function dataSave (){
    $("#data-save").hide();
//    if(dataMap.size()>0){
//        top.layer.alert("填完再保存！");
//        return ;
//    }
    var data = $("#_editForm").serializeJson();
    console.log(data);
    $.ajax({
        url:_web +"/data/fill/add/data",
        type: "POST",
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        dataType:"json",
        data:data,            //将Json对象序列化成Json字符串，toJSON()需要引用jquery.json.min.js
        success: function(data){
            if(data == "1"){
                $("#data-save").show();
                top.layer.alert("数据填报失败！");
            }
            if(data == "0"){
                top.layer.alert("数据已填报！");
            }
        }
    });
    dataMap.clear();
}