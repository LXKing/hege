//当前日期
function startTime(id_) {
    //获取当前系统日期
    var today=new Date();
    var y=today.getFullYear();
    var mo=today.getMonth();
    var da=today.getDate();
    var weekString="日一二三四五六";

    $(id_).html(y + "-" + (mo+1) + "-" + da + " 星期" + weekString.charAt(today.getDay()));
}


//导航选中
$(document).ready(function(){
    $("#ace-nav li.navlh>a").each(function(){
        $this = $(this);
        if($this[0].href==String(window.location)){
            $(this).parent("li").addClass("navlh-act").siblings().removeClass("navlh-act");
        }
    });
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
//绑定弹出层小
$(document).on('click', '.top-layer-min-work', function () {
    var $this = $(this);
    var url = $this.attr("layer-url");
    var title = $this.attr("layer-title");
    var form = $this.attr("layer-form-id");

    var width = 0.7;
    var height = 0.85;
    openLayerWork(url,title,form,width,height);
});
//绑定弹出层小
$(document).on('click', '.top-layer-min-send', function () {
    var $this = $(this);
    var url = $this.attr("layer-url");
    var title = $this.attr("layer-title");
    var form = $this.attr("layer-form-id");

    var width = 0.7;
    var height = 0.85;
    openLayerSend(url,title,form,width,height);
});

//绑定弹出层小
$(document).on('click', '.top-layer-min-done', function () {
    var $this = $(this);
    var url = $this.attr("layer-url");
    var title = $this.attr("layer-title");
    var form = $this.attr("layer-form-id");

    var width = 0.35;
    var height = 0.5;
    openLayerDone(url,title,form,width,height);
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
        width = top.document.documentElement.clientWidth*0.7+"px";
    }else{
        width = top.document.documentElement.clientWidth*width+"px";
    }
    if(height==null||height=='undefined'){
        height = top.document.documentElement.clientHeight*0.85+"px";
    }else{
        height = top.document.documentElement.clientHeight*height+"px";
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
function openLayerWork(url,title,form,width,height){
    if(width==null||width=='undefined'){
        width = top.document.documentElement.clientWidth*0.7+"px";
    }else{
        width = top.document.documentElement.clientWidth*width+"px";
    }
    if(height==null||height=='undefined'){
        height = top.document.documentElement.clientHeight*0.85+"px";
    }else{
        height = top.document.documentElement.clientHeight*height+"px";
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
            btn: ['保存',"保存并发送", '取消' ],
            maxmin:true,
            yes: function () {
                $top.find("#urlType").val(0);
                $top.find("#"+form).submit();
            },
            btn2: function(index, layero){
                $top.find("#urlType").val(1);
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
function openLayerSend(url,title,form,width,height){
    if(width==null||width=='undefined'){
        width = top.document.documentElement.clientWidth*0.7+"px";
    }else{
        width = top.document.documentElement.clientWidth*width+"px";
    }
    if(height==null||height=='undefined'){
        height = top.document.documentElement.clientHeight*0.85+"px";
    }else{
        height = top.document.documentElement.clientHeight*height+"px";
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
            btn: ['派单', '取消' ],
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


function openLayerDone(url,title,form,width,height){
    if(width==null||width=='undefined'){
        width = top.document.documentElement.clientWidth*0.7+"px";
    }else{
        width = top.document.documentElement.clientWidth*width+"px";
    }
    if(height==null||height=='undefined'){
        height = top.document.documentElement.clientHeight*0.85+"px";
    }else{
        height = top.document.documentElement.clientHeight*height+"px";
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
            btn: ['确认', '取消'],
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