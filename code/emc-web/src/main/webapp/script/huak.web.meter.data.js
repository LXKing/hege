
$(function(){
    //下拉切换事件
    $(".select").on("click", "p", function () {
        var selectval = $(this).html();
        var selectid = $(this).attr("value");
        $(this).parent().prev().find("input").val(selectval);
        $(this).parent().next().val(selectid);
        $(this).parent().slideUp(200, function () {
        });
    });
    loadDataFun();
})

function loadDataFun() {
    queryMeter(1);
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
        }
        $hidden.val(num);
        $div.empty().append(num).append($hidden);
        $td.empty().append($div);
    });
}

/**
 * 重置
 */
function resetMeter(){
    $.each($(".select"),function(){
        $(this).prev().find("input").val($($(this).prev().find('input')[0]).attr('value'));
        $(this).next().val("");
    });
    $("input[name='code']").val("");
    $("input[name='collectName']").val("");
    $("input[name='tag']").val("");
}


/**
 * 前台-安全与后台-采集表管理-数据查询
 */
function queryMeter(num){
    var data = $("#meter-search-form").serialize()+'&pageNumber='+num;
    showOverlay("overlay");
    $.ajax({
        url: _web + '/meterData/meter/list',
        type: 'POST',
        async: true,//要指定不能异步,必须等待后台服务校验完成再执行后续代null码
        data: data,
        dataType: "json",
        success: function (data) {
                console.info(data);
                $("#redtipspad").text(data.list.page.total);
                $("#projectTbody").setTemplateElement("template");
                $("#projectTbody").processTemplate(data.list);
                /*分页效果*/
                $("#paging").createPage({
                    pageCount: data.list.page.pages, //总页数
                    current:data.list.page.pageNumber, //当前页数
                    backFn: function (p) { //单击回调方法，p是当前页码
                        queryMeter(p);
                    }
                });
                hideOverlay("overlay");
        }
    });
}

/**
 *前台-安全与后台-采集表管理-列表-超长字段动态处理
 * @param data
 * @returns {*}
 */
function formatValue(str,num){
    if(null == num || 'undefined' == num){
        num = 20;
    }
    if(num<str.length){
        return str.substr(0,num)+'...';
    }else{
        return str;
    }
}
//id翻译
function getTypeName(id){
    var name = id;
    var energyTypes = eval('('+$("#energyTypeJson").val()+')');
    $.each(energyTypes,function(idx,item){
        if(id == item.id){
            name = item.nameZh;
            return false;
        }
    });
    return name;
}
/**
 *  前台-安全与后台-采集表管理-列表-字段动态替换
 * @param value
 * @param type
 * @returns {string}
 */
function changeValue(value,type){
        if (value == '1' && type =='energy') {
            return '水';
        }
        if (value == '2' && type =='energy') {
            return '电';
        }
        if (value == '3' && type =='energy') {
            return '气';
        }
        if (value == '4' && type =='energy') {
            return '热';
        }
        if (value == '5' && type =='energy') {
            return '煤';
        }
        if (value == '0' && type == 'real') {
            return '实表';
        }
        if (value == '1' && type == 'real') {
            return '虚表 ';
        }
        if (value == '1' && type == 'total') {
            return '单位总表';
        }
        if (value == '2' && type == 'total') {
            return '系统总表 ';
        }
        if (value == '0' && type == 'total') {
            return '总表 ';
        }
        if (value == '1' && type == 'unittype') {
            return '热源';
        }
        if (value == '2' && type == 'unittype') {
            return '管网';
        }
        if (value == '3' && type == 'unittype') {
            return '热力站';
        }
        if (value == '4' && type == 'unittype') {
            return '管线';
        }
        if (value == '5' && type == 'unittype') {
            return '民户';
        }
        if (value == '0' && type == 'auto') {
            return '自动采集';
        }
        if (value == '1' && type == 'auto') {
            return '手工 ';
        }
        if (value == '0' && type == 'prestore') {
            return '不是';
        }
        if (value == '1' && type == 'prestore') {
            return '是 ';
        }
        if (value == '0' && type == 'delete') {
            return '未删除';
        }
        if (value == '1' && type == 'delete') {
            return '软删除标识 ';
        }
        return '';

}

/**
 * 前台-安全与后台-采集表管理-添加
 */
function addMeterCollect() {
    openLayer(_web + "/meterData/add", "添加采集表", "addMeterCollectAddForm", null, null);
}

/**
 * 前台-安全与后台-采集表管理-批量导入-excel
 */
function uploaderExcel() {
    $.get(_web + '/meterData/uploadPage', function (result) {
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

/**
 * 前台-安全与后台-采集表管理-导出excel
 */
$(document).on("click", ".export", function () {
    var $from = $("#meter-search-form");
    var url = $(this).attr('export-url') + '?' + $from.serialize();
     window.location.href = url;
});

/**
 * 前台-安全与后台-采集表管理-修改采集表
 */
function editMeter(id){
    openLayer(_web + "/meterData/edit/"+id, "修改采集表", "MeterDataEditForm", null, null);
}

/**
 * 前台-安全与后台-采集表管理-删除采集表
 */
function deleteMeter(id) {
    top.layer.confirm('您是否确定删除吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _web + '/meterData/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    queryMeter(1);
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}

/**
 * 前台-安全与后台-采集表管理-换表
 */
function changeMeter(id) {
    openLayer(_web + "/meterData/change/"+id, "采集表更换", "meterDataChangeForm", null, null);
}


/**
 * 前台-安全与后台-采集表管理-预存
 */
function prestoreMeter(id) {
    openLayer(_web + "/meterData/prestore/"+id, "采集表预存", "meterDataPrestoreForm", null, null);
}