//bootstraptable 高
function getHeight() {
    return $(window).height() - 130;
}

//导出EXCEL按钮绑定事件
$(document).on('click', '.excel-export-btn', function () {
    var $from = $(this).parents('form');
    var url = $(this).attr('export-url') + '?' + $from.serialize();
    window.open(url);
});

//绑定弹出层小
$(document).on('click', '.top-layer-min', function () {
    var $this = $(this);
    var url = $this.attr("layer-url");
    var title = $this.attr("layer-title");
    var form = $this.attr("layer-form-id");

    var width = 0.7;
    var height = 0.85;
    openLayer(url,title,form,width,height);
});
//绑定弹出层满
$(document).on('click', '.top-layer-max', function () {
    var $this = $(this);
    var url = $this.attr("layer-url");
    var title = $this.attr("layer-title");
    var form = $this.attr("layer-form-id");

    var width = 1;
    var height = 1;
    openLayer(url,title,form,width,height);
});

function openLayer(url,title,form,width,height){
    if(width==null||width=='undefined'){
        width = top.document.body.clientWidth*0.7+"px";
    }else{
        width = top.document.body.clientWidth*width+"px";
    }
    if(height==null||height=='undefined'){
        height = top.document.body.clientHeight*0.85+"px";
    }else{
        height = top.document.body.clientHeight*height+"px";
    }

    var $top = $(top.document);
    var layerDiv = '<div id="layer-div"></div>';
    $top.find('body').append(layerDiv);
    $.get(url, function (result) {
        $top.find("#layer-div").html(result);
        top.layer.open({
            area: [width, height],
            type: 1,
            title: title,
            btn: ['保存', '取消'],
            maxmin:true,
            yes: function () {
                $top.find("#"+form).submit();
            },
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 2,//出场动画
            shadeClose: true, //开启遮罩关闭
            content: $top.find("#layer-div")
        });
    });
}
//绑定查询
$(document).on('click', '.emc-search', function () {
    var tableId = $(this).attr("bootstrap-table-id");
    $("#"+tableId).bootstrapTable("refresh");
});


//绑定重置
$(document).on('click', '.emc-reset', function () {
    //清空表单
    var $from = $(this).parents('form');
    $from[0].reset();
    //清空chosen
    $from.find('.chosen-select').val("");
    $from.find('.chosen-select').trigger("chosen:updated");
});


