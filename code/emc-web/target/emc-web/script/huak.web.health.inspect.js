var imgPath =_web + "/static/" + (localStorage.faceKey == "dark" ? "imgdark" : "img");

var bfbfm;


function loadDataFun() {
    initRuning();
    setChart(null,null,null);
    $("#runbtn").click(function() {
        polling();
        loadRuning();

    });
    $(document).on('click','.normalitem h1',function(){
        $(this).parent().parent().find("ul").toggle();
        if ($(this).parent().parent().find("ul").is(":hidden")) {
            $(this).addClass('open');
        } else {
            $(this).removeClass('open');

        }
    });
}

function loadScore(){
    $.ajax({
        url: _web + "/healthcheck/score",
        type: "POST",
        data: {},
        dataType: "json",
        success: function (data) {
            console.log(data);
            // doCss(data);
            if(data.flag==false){
                setChart(100,'无检测记录',null);
            }else{
                setChart(data.score,'上次检测分数',data.time);
            }
        }
    });
}
/**
 * 修改第一个图标为运行状态
 */
function modifyStateRuning(){
    var $ul = $("#healthlistul");
    var $first_li = $ul.find("li").first();
    $first_li.find("div").addClass("state0");
    var url = $first_li.find("img").attr("src");
    var path = url.substr(0,url.lastIndexOf("/"));
    var imgName = url.substr(url.lastIndexOf("/"),url.length);
    imgName = imgName.replace("-nor","");
    $first_li.find("img").attr("src",path + imgName);
}
/**
 * 修改运行图标
 * 如果num>0为异常
 * num==0为正常，分组显示
 * @param num
 */
function modifyStateStop(num){
    var $ul = $("#healthlistul");
    var $first_li = $ul.find("li").first();
    var $li = $first_li.clone();
    $li.find("div").removeClass("state0");
    var url = $li.find("img").attr("src");
    var path = url.substr(0,url.lastIndexOf("/"));
    var imgName = url.substr(url.lastIndexOf("/"),url.length);

    if(num>0){//异常
        imgName = imgName.replace(".png","-abnor.png");
        $li.find("img").attr("src",path + imgName);
        $li.find("p").append('<span>'+num+'</span>');

        $("#errorlistul").append($li.hide());
        $li.fadeIn(1000,function(){
            $li.show();
        });
        var count = Number($("#errorcount").text());
        if(count==null||count=='NaN'){
            count = 0;
        }
        $("#errorcount").text(count+1);
    }else{
        imgName = imgName.replace(".png","-nor.png");
        $li.find("img").attr("src",path + imgName);
        var parentName = $li.attr("parentName");
        var parentTitle = $li.attr("parentTitle");
        if($('#'+parentName).length==0){
            var html = '<div class="panelwrap" id="'+parentName+'"><div class="title"><h1>'+parentTitle+' - 共<span>0</span>项</h1></div><ul></ul></div>';
            $('.normalitem').append(html);
        }
        $('#'+parentName).find('ul').append($li.hide());
        $li.fadeIn(1000,function(){
            $li.show();
        });

        var $span = $('#'+parentName).find('.title').find('span');
        var count1 = Number($span.text());
        if(count1==null||count1=='NaN'){
            count1 = 0;
        }
        $span.text(count1+1);

        var count = Number($("#normalcount").text());
        if(count==null||count=='NaN'){
            count = 0;
        }
        $("#normalcount").text(count+1);
    }
    $first_li.remove();

    var healthcount = Number($("#healthcount").text());
    if(healthcount==null||healthcount=='NaN'){
        healthcount = 0;
    }
    $("#healthcount").text(healthcount-1);

    //不是最后一个
    if(healthcount-1!=0){
        var bfb = (bfbfm-healthcount+1)/bfbfm*100;
        modifyStateRuning();
        setPressbar(bfb);
    }else{
        setPressbar(100);
    }


}
/**
 * 打印信息
 * @param msg
 */
function printlnMsg(msg){
    var $div = $("#printMsg");
    var length = $div.find("p").length;
    if(length<7){
        $div.append('<p>' + msg + '</p>');
    }else if(length == 7){
        $div.find("p").first().text("...");
        $div.find("p").eq(1).remove();
        $div.append('<p>' + msg + '</p>');
    }
}

function loadRuning(){

    $.ajax({
        url : _web+"/healthcheck/season",
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        type : "POST",
        cache : false,
        data: {},
        dataType: "json",
        success : function(data) {
            if(data.flag==false){
                top.layer.msg(data.msg);
                return false;
            }else{
                modifyStateRuning();
                $("#running").show();
                $("#runbtn").hide();
                $(".erroritem").show();
                $(".normalitem").show();
                var healthItems = eval($("#healthItem").val());
                var key = $("#key").val();
                healthItems.push({"key":key});

                $.ajax({
                    url : _web+"/health/testing",
                    contentType : 'application/json;charset=utf-8', //设置请求头信息
                    type : "POST",
                    cache : false,
                    data: JSON.stringify(healthItems),
                    dataType: "json",
                    success : function(data) {

                    }
                });
            }
        }
    });

}
/**
 * 长连接
 */
function polling(){
    var key = $("#key").val();
    $.ajax({
        url : _web+"/health/polling",
        type : "GET",
        timeout:30000,
        data:{"key":key},
        cache : false,
        dataType:"JSON",
        success : function(data) {
            if(data != null && data != ""){
                if(data.msg!=null&&data.msg!="undefined"&&data.msg!=""){
                    printlnMsg(data.msg);
                }
                if(data.num!=null&&data.num!="undefined"){
                    modifyStateStop(data.num);
                }
                if(data.end==null||data.end==""||data.end=="undefined"){
                    polling();
                }else{
                    $.ajax({
                        url: _web + "/healthcheck/list/second",
                        type: "POST",
                        data: {},
                        dataType: "json",
                        success: function (data) {
                            console.log(data);
                           // doCss(data);
                            if(data.flag==false){
                                top.layer.msg(data.msg);
                            }else{
                                setChart(data.object.score,'检测分数',data.object.date);
                            }
                        }
                    });
                }
            }else{
                polling();
            }

        },
        complete:function(XMLHttpRequest,status){
            console.info("status"+status);
            if(status=='timeout') {//超时,status还有success,error等值的情况
                polling();
            }
        }
    });
}
/**
 * 初始化待检测项
 */
function initRuning(){
    var healthItem = eval($("#healthItem").val());
    $("#healthlistul").empty();
    $("#healthcount").html(healthItem.length);
    bfbfm = healthItem.length;
    $.each(healthItem,function(idx,item){
        var  $li = '<li parentName="'+item.parentName+'" parentTitle="'+item.parentTitle+'"><div><img src="'+imgPath+'/health/'+item.img+'-nor.png"></div><p>'+item.title+'</p></li>';
        $("#healthlistul").append($li);
    });
}

/**
 * 设置分数信息
 */
function setChart(score,info,date){
    if(score!=null){
        $("#chart01").find('h1').text(score);
    }
    if(info!=null){
        $("#chart01").find('h2').text(info);
    }
    if(date!=null){
        $("#chart01").find('h3').text(date);
    }
    chart01Fun();
}

/**
 * 进度条设置
 */
function setPressbar(bfb){
    $(".pressbar div").css( 'width',bfb+'%');
    if(bfb == 100){
        $("#replacebtn").show();
        $("#running").hide();
    }
}
function chart01Fun() {

    //占比-赋值即可
    var level_ = 0.75;
    //$("#level_num").text((level_ * 100) + '%');
    var wavehealth = (function() {
        var ctx;
        var waveImage;
        var canvasWidth;
        var canvasHeight;
        var needAnimate = false;

        function init(callback) {
            var wave = document.getElementById('chart01');
            if ($(wave).find("canvas").length > 0) {
                return;
            }
            var canvas = document.createElement('canvas');
            if (!canvas.getContext) return;
            ctx = canvas.getContext('2d');
            canvasWidth = wave.offsetWidth;
            canvasHeight = wave.offsetHeight;
            canvas.setAttribute('width', canvasWidth);
            canvas.setAttribute('height', canvasHeight);
            wave.appendChild(canvas);
            waveImage = new Image();
            waveImage.onload = function() {
                waveImage.onload = null;
                callback();
            }
            waveImage.src = _web + '/static/img/wave2.png';
        }

        function animate() {
            var waveX = 0;
            var waveY = 0;
            var waveX_min = -220;
            var waveY_max = canvasHeight * level_;
            var requestAnimationFrame =
                window.requestAnimationFrame ||
                window.mozRequestAnimationFrame ||
                window.webkitRequestAnimationFrame ||
                window.msRequestAnimationFrame ||
                function(callback) {
                    window.setTimeout(callback, 1000 / 60);
                };

            function loop() {
                ctx.clearRect(0, 0, canvasWidth, canvasHeight);
                if (!needAnimate) return;
                if (waveY < waveY_max) waveY += 1.5;
                if (waveX < waveX_min) waveX = 0;
                else waveX -= 3;

                ctx.globalCompositeOperation = 'source-over';
                ctx.beginPath();
                ctx.arc(canvasWidth / 2, canvasHeight / 2, canvasHeight / 2, 0, Math.PI * 2, true);
                ctx.closePath();
                ctx.fill();

                ctx.globalCompositeOperation = 'source-in';
                ctx.drawImage(waveImage, waveX, canvasHeight - waveY);

                requestAnimationFrame(loop);
            }
            loop();
        }

        function start() {
            if (!ctx) return init(start);
            needAnimate = true;
            setTimeout(function() {
                if (needAnimate) animate();
            }, 500);
        }

        function stop() {
            needAnimate = false;
        }
        return {
            start: start,
            stop: stop
        };
    }());
    wavehealth.start();
}