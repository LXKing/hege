/*下拉框*/
//公共颜色
var color = ['#c675c3', '#8d82cc', '#3b96db', '#a1b1c5', '#32bbb6', '#df614c','#99ff33','#FFFF00'];
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 公共方法
 * 格式化数据
 * 算小数点保留六位 4位+.+1位
 * 例：123456789.1111 ->1.2亿
 *     12345678.1111 ->1234.6万
 *     12345.1111->1.2万
 *     1234.1111->1234.1
 * */
function toFormatNum(val,num){
    num = num == undefined ? 1:num;
    var divisor = 1;//倍率
    var suffix = '';//单位
    var f = parseFloat(val);
    if (isNaN(f)) {
        return 0;
    }
    if(f-99999999>0){
        divisor = 100000000;
        suffix = '亿';
    }else if(f-9999>0){
        divisor = 10000;
        suffix = '万';
    }
    var result = parseFloat(f/divisor).toFixed(num);
    if(num>0){
        var disp = 10;
        for(var i=1;i < num;i++){
            disp = disp*10;
        }
        result = Math.round(f/divisor*disp)/disp;
    }

    return  result + suffix;
}

function toFormatNumbers(val,num){
    num = num == undefined ? 1:num;
    var f = parseFloat(val);
    if (isNaN(f)) {
        return 0;
    }
    var result =f.toFixed(num);
    return  result;
}

function toolReplace() {
    var url = document.location.href;
    var reurl = url;
    if (url.indexOf("?") > 0) {
        reurl = url.substr(0, url.indexOf("?"));
    }
    document.location.replace(reurl);
}
/**
 * 保留2位小数
 * @param x
 * @returns {Number}
 */
function toDecimal(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(x*100)/100;
    return f;
}

/*保留小数位*/
function toDecimalCommon(x,num) {
    num = num == undefined ?1:num;
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = f.toFixed(num);
    return f;
}

/**
 * 保留1位小数
 * @param x
 * @returns {Number}
 */
function toOneDecimal(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(x*10)/10;
    return f;
}

$(function () {
    /**
     * 设置全局session超时ajax处理
     *
     * */
    $.ajaxSetup({
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        complete: function (XMLHttpRequest, textStatus) {
            var sessionStatus = XMLHttpRequest.getResponseHeader("sessionStatus");
            var locat = "";
            if(_web.indexOf('www.e-heating.org')!=-1){
                locat = _web + "/login.html";
            }else{
                locat = _web + "/login";
            }
            if (sessionStatus == "timeout") {
                layer.alert("会话超时，请重新登录",function(){
                    window.location.replace(locat);
                });
                //使用 setTimeout 时需注意，当该代码执行时，JS 会立即编译函数第一个参数“code”
                //所以该函数的第一个参数应该为：需要编译的代码、或者一个函数
                //例1：setTimeout("alert('x')", 2000);
                //例2：setTimeout(function () { alert('x'); }, 2000);

                //错误示例：setTimeout(alert('x'), 2000); "x" 会立马跳出来，延时没有效果
                setTimeout(function(){
                    window.location.replace(locat);
                }, 5000);
            }
        }
    });
    //条件下拉框
    $.ajax({
        url: _web + "/tools/search/org",
        type: "POST",
        async:false,
        dataType: "json",
        success: function (data) {
            var html = '';
            $.each(data, function (idx, item) {
                if (idx == 0) {
                    if (getCookie("toolOrgId") == null || getCookie("toolOrgId") == "") {
                        $('.x-sfleft1.x-sfw1').html('<input type="text" value="' + item.ORG_NAME + '" readonly="readonly">');
                        $('#toolOrgId').val(item.ID);
                    } else {
                        $('.x-sfleft1.x-sfw1').html('<input type="text" value="' + getCookie("toolOrgName") + '" readonly="readonly">');
                        $('#toolOrgId').val(getCookie("toolOrgId"));
                    }

                }
                html += '<p value="' + item.ID + '">' + item.ORG_NAME + '</p>'
            });
            $(".x-sfoption.x-sfoption1").html(html);

        }
    });
    //默认类型
    if (getCookie("toolFeedType") == null || getCookie("toolFeedType") == "") {
        $('#toolFeedType').val("");
    } else {
        var toolFeedType = getCookie("toolFeedType");
        $('#toolFeedType').val(toolFeedType);
        if (toolFeedType == 1) {
            $('.btnAlarm').each(function () {
                if ("集中供暖" == $(this).text()) {
                    $(this).removeClass("btnAlarm-on");
                } else if ("区域供暖" == $(this).text()) {
                    $(this).addClass("btnAlarm-on");
                } else if ("全部" == $(this).text()) {
                    $(this).removeClass("btnAlarm-on");
                }
            });
        }
        if (toolFeedType == 2) {
            $('.btnAlarm').each(function () {
                if ("集中供暖" == $(this).text()) {
                    $(this).addClass("btnAlarm-on");
                } else if ("区域供暖" == $(this).text()) {
                    $(this).removeClass("btnAlarm-on");
                } else if ("全部" == $(this).text()) {
                    $(this).removeClass("btnAlarm-on");
                }
            });
        }
    }



    //默认时间段
    if (getCookie("dateType") == null || getCookie("dateType") == "") {
        $.ajax({
            url: _web + "/tools/search/season",
            type: "POST",
            async:false,
            dataType: "json",
            success: function (data) {
                if(data.flag==false){
                    $('.btnAlarm').each(function () {
                        if ("本年度" == $(this).text()) {
                            $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
                        }
                    });
                    $.ajax({
                        url: _web + "/tools/search/year",
                        type: "POST",
                        async: false,
                        dataType: "json",
                        success: function (result) {
                            $('#toolStartDate').val(result.startDate);
                            $('#toolEndDate').val(result.endDate);
                            $('#begin').val(result.startDate);
                            $('#end').val(result.endDate);
                        }
                    });
                }else{
                    $('#toolStartDate').val(data.startDate);
                    $('#toolEndDate').val(data.endDate);
                    $('#begin').val(data.startDate);
                    $('#end').val(data.endDate);
                }

            }
        });
    } else {
        var dataType = getCookie("dateType");
        $('.btnAlarm').each(function () {
            if ("本年度" == $(this).text() && dataType == 1) {
                $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
            } else if ("本采暖季" == $(this).text() && dataType == 2) {
                $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
            } else if ("自定义" == $(this).text() && dataType == 3) {
                $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
            }
        });
        $('#toolStartDate').val(getCookie("toolStartDate"));
        $('#toolEndDate').val(getCookie("toolEndDate"));
        $('#begin').val(getCookie("toolStartDate"));
        $('#end').val(getCookie("toolEndDate"));
    }




    /*返回顶部*/
    $("#returnTop").click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 1000);
        return false;
    });

    $('.publicmenu .scbtn').click(function(){
        if($('.publicmenu').css('left')=="0px"){
            $('.publicmenu').animate({left:'-196px'});
            $('.publicmenu-con').hide();
            $('.publicmenu .scbtn').animate({left:'0px'});
            $('.publicmenu .scbtn').addClass('close');
        }else{
            $('.publicmenu-con').show();
            $('.publicmenu .scbtn').animate({left:'196px'});
            $('.publicmenu').animate({left:'0px'});
            $('.publicmenu .scbtn').removeClass('close');
        }

    });

    $('.publicmenu .btn-up').click(function(){
        var top = parseInt($('.publicmenu-con > ul').css("top"));
        if(-top>=parseInt($('.publicmenu-con > ul').height())-44){
            return;
        }
        $('.publicmenu-con > ul').animate({"top":(top-44)+"px"});
    });

    $('.publicmenu .btn-down').click(function(){
        var top = parseInt($('.publicmenu-con > ul').css("top"));
        if(top==0){
            return;
        }
        $('.publicmenu-con > ul').animate({"top":(top+44)+"px"});
    });



    $(".grouplist input").click(function(){
        $(this).next().show();
    });
    $(".grouplist").hover(function(){

    },function(){
        $(this).find("ul").hide();
    });


    $("body").on("click", ".x-sfbgbox", function () {
        $(this).next().stop(true, false).slideToggle(200, function () {
        });
    });
    //下拉切换事件
    $("body").on("click", ".x-sfoption p", function () {
        var selectval = $(this).html();
        var selectid = $(this).attr("value");

        $('#toolOrgId').val(selectid);
        $(this).parent().prev().find("input").val(selectval);

        setCookie('toolOrgId', selectid, 3);
        setCookie('toolOrgName', selectval, 3);
        //var hidval = $(this).parent().next().val(selectid);
        $(this).parent().slideUp(200, function () {
        });
        toolReplace();

    });
    $("body").on("mouseleave", ".x-selectfree", function () {
        $(this).find(".x-sfoption").slideUp(200, function () {
        });
    });

    //单击按钮事件
    $(".select-boxbtnAlarm .btnAlarm").click(function () {
        var thisText = $(this).text();
        if (thisText != "本采暖季") {
            $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
        }

        if (thisText == "自定义") {
            $(".select-boxWdate input").attr("disabled", false).removeClass("time-input-disable");
            $("#begin").focus();
        } else if (thisText == "本采暖季") {
            $("#selectdate").hide();
            $.ajax({
                url: _web + "/tools/search/season",
                type: "POST",
                async:false,
                dataType: "json",
                success: function (data) {
                    if(data.flag==false){
                        top.layer.alert(data.msg);
                    }else{
                        $(this).addClass("btnAlarm-on").siblings().removeClass("btnAlarm-on");
                        $('#toolStartDate').val(data.startDate);
                        $('#toolEndDate').val(data.endDate);
                        $('#begin').val(data.startDate);
                        $('#end').val(data.endDate);
                        setCookie('toolStartDate', data.startDate, 3);
                        setCookie('toolEndDate', data.endDate, 3);
                        setCookie('dateType', 2, 3);
                        toolReplace();
                    }

                }
            });
        } else if (thisText == "本年度") {
            $("#selectdate").hide();
            $.ajax({
                url: _web + "/tools/search/year",
                type: "POST",
                async:false,
                dataType: "json",
                success: function (data) {
                    $('#toolStartDate').val(data.startDate);
                    $('#toolEndDate').val(data.endDate);
                    $('#begin').val(data.startDate);
                    $('#end').val(data.endDate);
                    setCookie('toolStartDate', data.startDate, 3);
                    setCookie('toolEndDate', data.endDate, 3);
                    setCookie('dateType', 1, 3);
                    toolReplace();
                }

            });

        } else if (thisText == "区域供暖") {
            $('#toolFeedType').val(1);
            setCookie('toolFeedType', 1, 3);

            toolReplace();
        } else if (thisText == "集中供暖") {
            $('#toolFeedType').val(2);
            setCookie('toolFeedType', 2, 3);
            toolReplace();
        } else if (thisText == "全部") {
            $('#toolFeedType').val("");
            setCookie('toolFeedType', "", 3);
            toolReplace();
        }else {
            $(".select-boxWdate input").attr("disabled", true).addClass("time-input-disable");
        }

    });

    //				$(".energy_consumption").click(function() {
    //					layer.open({
    //						type: 2,
    //						title: '能耗分析',
    //						shadeClose: true,
    //						shade: 0.8,
    //						area: ['98%', '95%'],
    //						content: 'energy_analysis_detail.html' //iframe的url
    //					});
    //				})

    //选择日期
    updateConfig();

    var startDate = new Date($("#begin").val());
    var endDate =  new Date($("#end").val());

    function updateConfig() {
        //					var options = {
        //						singleDatePicker: true,
        //						locale: {
        //
        //							customRangeLabel: '自定义',
        //							daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
        //							monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
        //								'七月', '八月', '九月', '十月', '十一月', '十二月'
        //							],
        //							firstDay: 1
        //						}
        //					};
        //
        //					$('#datepicker-config').daterangepicker(options, function(start, end, label) {
        //						console.info("start"+start.format('YYYY-MM-DD'));
        //						console.info("end"+end.format('YYYY-MM-DD'));
        //						$("#begin").val(start.format('YYYY-MM-DD'));
        //						$("#end").val(end.format('YYYY-MM-DD'));
        //
        //					});
        //
        $('#datepicker-config').click(function () {
            $("#selectdate").show();
            $('.calendar.left').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: 'yyyy-mm-dd',
                initialDate:$("#begin").val()
            }).on('changeDate', function (ev) {
                startDate = ev.date;

            });
            $('.calendar.right').datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                initialDate:$("#end").val()
            }).on('changeDate', function (ev) {
                endDate = ev.date;

            });

        });

    }

    //繁琐了
    //获取当前时间
    var nowdate = new Date();
    var y = nowdate.getFullYear();
    var m = nowdate.getMonth() + 1;
    var d = nowdate.getDate();
    var formatnowdate = y + '-' + m + '-' + d;

    //获取近一周
    var oneweekdate = new Date(nowdate - 7 * 24 * 3600 * 1000);
    var y = oneweekdate.getFullYear();
    var m = oneweekdate.getMonth() + 1;
    var d = oneweekdate.getDate();
    var formatoneweekdate = y + '-' + m + '-' + d;

    //获取近一月
    nowdate.setMonth(nowdate.getMonth() - 1);
    var y = nowdate.getFullYear();
    var m = nowdate.getMonth() + 1;
    var d = nowdate.getDate();
    var formatonemonth = y + '-' + m + '-' + d;

    //获取近两月
    nowdate.setMonth(nowdate.getMonth() - 2);
    var y = nowdate.getFullYear();
    var m = nowdate.getMonth() + 2;
    var d = nowdate.getDate();
    var formattwomonth = y + '-' + m + '-' + d;

    $("#nearlyaweek").click(function () {
        $(".btn-confirm").click();
        $("#begin").val(formatoneweekdate);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
        setCookie('toolStartDate', formatoneweekdate, 3);
        setCookie('toolEndDate', formatnowdate, 3);
        setCookie('dateType', 3, 3);
    });

    $("#nearlyamonth").click(function () {
        $(".btn-confirm").click();
        $("#begin").val(formatonemonth);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
        setCookie('toolStartDate', formatonemonth, 3);
        setCookie('toolEndDate', formatnowdate, 3);
        setCookie('dateType', 3, 3);
    });

    $("#nearlytwomonth").click(function () {
        $(".btn-confirm").click();
        $("#begin").val(formattwomonth);
        $("#end").val(formatnowdate);
        $("#selectdate").hide();
        setCookie('toolStartDate', formattwomonth, 3);
        setCookie('toolEndDate', formatnowdate, 3);
        setCookie('dateType', 3, 3);
    });

    $(".applyBtn").click(function () {
        debugger
        $("#begin").val(startDate.Format("yyyy-MM-dd"));
        $("#end").val(endDate.Format("yyyy-MM-dd"));
        if (endDate < startDate) {
            layer.msg('开始时间小于结束时间！');
            return false;
        }
        $("#selectdate").hide();
        setCookie('toolStartDate', startDate.Format("yyyy-MM-dd"), 3);
        if (endDate >= startDate) {
            setCookie('toolEndDate', endDate.Format("yyyy-MM-dd"), 3);
        }
        setCookie('dateType', 3, 3);
        toolReplace();
    });

    $(".cancelBtn").click(function () {
        $("#selectdate").hide();
    });

    /*导出列表*/
    $(document).on("click", ".export-emc", function () {
        var $from = $("#searchTools");
        var url = $(this).attr('export-url') + '?' + $from.serialize();
        window.open(url);
    });

    changeFace();
    $("#header .changeface").click(function() {
        faceKey = faceKey == "dark" ? "white" : "dark";
        changeFace();
    });
});


/**
 * 换肤
 * @type {string}
 */
var path = _web+"/static/";
var faceKey = localStorage.faceKey == null||localStorage.faceKey == ""?"dark":localStorage.faceKey; //dark
var chartsColor;

function changeFace() {
    $('html').removeClass();
    localStorage.faceKey = faceKey;
    chartsColor = {
        linefontcolor: faceKey == 'dark' ? '#fff' : '#666',
        areacolor: faceKey == 'dark' ? ['transparent', 'rgba(15,54,66,.7)'] : ['#F3F3F4', '#E4E4E5'],
        chart01: {
            color: faceKey == 'dark' ? ['#33fff8', '#304d60'] : ['#3B96DD', '#c2ccd3'],

        },
        chart02: {
            color: faceKey == 'dark' ? ['#33fff8'] : ['#3B96DD'],

        },
        chart03: {
            color: faceKey == 'dark' ? ['#33fff8', '#304d60'] : ['#3B96DD', '#c2ccd3'],

        },
        chart10: {
            facecolor1: faceKey == 'dark' ? '#618292' : '#9a9a9b',
            facecolor2: faceKey == 'dark' ? '#33fff8' : '#277aba',
            facecolor3: faceKey == 'dark' ? '#30515e' : ''
        },
        chart11: {
            facecolor1: faceKey == 'dark' ? ['#33fff8'] : ['#3B96DD'],

        },
        chart08: {
            facecolor1: faceKey == 'dark' ? ['#21a7a9'] : ['#7fb7e1'],

        },
        chart05: {
            facecolor1: faceKey == 'dark' ? '#fff' : '#8394aa',
            facecolor2: faceKey == 'dark' ? '#33fff8' : '#2eada8',
            facecolor3: faceKey == 'dark' ? '#325e70' : '#ffffff',
            facecolor4: faceKey == 'dark' ? '#093043' : '#f0f1f2',
            facecolor5: faceKey == 'dark' ? ['63%', '64%'] : ['0%', '64%'],
            facecolor6: faceKey == 'dark' ? ['#33fff8', '#32657f', '#1d465b', '#ff6349', '#3db4ff']:['#32bbb6', '#8394aa', '#b7c1cf', '#df5f4a', '#3b96db']
        },
        chart07: {
            facecolor1: faceKey == 'dark' ? '#89b1c3' : '#ccc',
        },
        chart09: {
            facecolor1: faceKey == 'dark' ? '#325e70' : '#ffffff',
            facecolor2: faceKey == 'dark' ? '#1d465b' : '#dce0e5',
            facecolor3: faceKey == 'dark' ? '#33fff8' : '#3b96db',
            facecolor4: faceKey == 'dark' ? '#33fff8' : '#348bce',
            facecolor5: faceKey == 'dark' ? '#f86148' : '#d4513b',
            facecolor6: faceKey == 'dark' ? ['63%', '64%'] : ['0%', '64%'],
        },
        ec1:{
            facecolor1: faceKey == 'dark' ? '#325d6c' : '#dbdcdf',
            facecolor2: faceKey == 'dark' ? '#325d6c' : '#abcd',
            facecolor3: faceKey == 'dark' ? '#618292' : '#9a9a9b',
            facecolor4: faceKey == 'dark' ? '#2f4f5f' : '#dbdcdf',
            facecolor5: faceKey == 'dark' ? ['#33fff8', '#32657f'] : ['#3B96DD', '#c2ccd3'],
        },
        ec2:{
            facecolor1: faceKey == 'dark' ? ['#33fff8', '#32657f'] : ['#3B96DD', '#c2ccd3'],

        },
        ec3:{
            facecolor1: faceKey == 'dark' ? "transparent":"#fff",

        },
        ec4:{
            facecolor1: faceKey == 'dark' ? "#618292":"#9a9a9b",
            facecolor2: faceKey == 'dark' ? "#618292":"#9a9a9b",
            facecolor3: faceKey == 'dark' ? ['#33fff8', '#304d60']:['#3B96DD', '#a1b1c5'],
            facecolor4: faceKey == 'dark' ?['#33fff8']: ['#3B96DD'],
            facecolor5:faceKey == 'dark' ? ['#c675c3', '#8d82cc', '#3db4ff', '#32657f', '#33fff8', '#ff6349']:  ['#c675c3', '#8d82cc', '#3b96db', '#a1b1c5', '#32bbb6', '#df614c'],
            facecolor6:faceKey == 'dark' ? ['#c675c3', '#8d82cc', '#3db4ff', '#33fff8', '#ff6349']: ['#c675c3', '#8d82cc', '#3b96db', '#32bbb6', '#df614c'],
        }

    }

    //top（tools.jsp） 顶部源、网、站、线、户 的toolOrgType 默认值设置
    if(getCookie("toolOrgType") == null || getCookie("toolOrgType") == ""){
        $('#toolOrgType').val("");
        if(faceKey == "dark"){
            $("#website").attr("src", _web + "/static/imgdark/index/websitet_cs01.png");
        }else{
            $("#website").attr("src", _web + "/static/img/index/websitet_cs01.png");
        }
    }else{
        var toolOrgType = getCookie("toolOrgType");
        if(faceKey == "dark"){
            $("#website").attr("src", _web + "/static/imgdark/index/websitet_cs0" + (Number(toolOrgType)+1) + ".png");
        }else{
            $("#website").attr("src", _web + "/static/img/index/websitet_cs0" + (Number(toolOrgType)+1) + ".png");
        }

        $('#toolOrgType').val(toolOrgType);
    }

    if(faceKey == "dark") {
        $('html').addClass('facedark');
        $(".energy_black img").eq(0).attr("src", path + "imgdark/images/btn01.png");
        $(".energy_black img").eq(1).attr("src", path + "imgdark/images/btn02.png");
        $(".energy_black img").eq(2).attr("src", path + "imgdark/images/btn03.png");
        $(".bordertopnone img").eq(0).attr("src", path + "imgdark/tijian.png");
    }else{
        $(".energy_black img").eq(0).attr("src", path + "img/images/btn01.png");
        $(".energy_black img").eq(1).attr("src", path + "img/images/btn02.png");
        $(".energy_black img").eq(2).attr("src", path + "img/images/btn03.png");
        $(".bordertopnone img").eq(0).attr("src", path + "img/tijian.png");
    }
    loadDataFun();
}

function loadDataFun(){

}

function createtable() {
    var right_div2 = document.getElementById("right_div2");
    var gunwidth = document.getElementById("right_div2").offsetWidth - document.getElementById("right_div2").clientWidth;
    $("#left_div2").css("padding-bottom", gunwidth + "px");
    $("#right_divx").css("width",document.getElementById("right_div2").offsetWidth + "px");
    right_div2.onscroll = function() {
        var right_div2_top = this.scrollTop;
        var right_div2_left = this.scrollLeft;

        document.getElementById("left_div2").scrollTop = right_div2_top;
        document.getElementById("right_div1").scrollLeft = right_div2_left;
    }
    //设置右边div宽度
    document.getElementById("right_div").style.width = "" + document.documentElement.clientWidth - 230 + "px";
    setInterval(function() {
        document.getElementById("right_div").style.width = "" + document.documentElement.clientWidth - 230 + "px";
    }, 0);

}

/**
 * 三级页面表格封装
 * @param data
 */
function thirdTable(data){
    console.log(data);
    //生成横向表头
    thirdXTHead(data.dates);

    var $leftTable = $("#left_table2");
    var leftHtml = '';
    var $rightTable = $("#right_table2");
    var rightHtml = '';

    if(data.list==null||data.list.length==0){
        leftHtml = '<tr><th> </th></tr>';
        rightHtml = '<tr>当前条件下未查询到对应数据！</tr>';
    }else{
        var flag = 0;
        $.each(data.list,function(idx,item){
            //取第一个总量
            if(idx==0){
                var total = getTotalTable(data,item.unitType);
                flag = item.unitType;
                leftHtml += '<tr class="bg"><th>' + item.unitName + '</th></tr>';
                rightHtml += '<tr class="bg">'+ getTrBody(total,data.dates.length) + '</tr>';
            }else{
                if(flag != item.unitType){
                    //判断取总量类型
                    var total = getTotalTable(data,item.unitType);
                    flag = item.unitType;
                    leftHtml += '<tr class="bg"><th>' + item.unitName + '</th></tr>';
                    rightHtml += '<tr class="bg">'+ getTrBody(total,data.dates.length) + '</tr>';
                }
            }
            leftHtml += '<tr><th>' + item.unitName + '</th></tr>';
            rightHtml += '<tr>'+ getTrBody(item,data.dates.length) + '</tr>';
        });
    }

    $leftTable.html(leftHtml);
    $rightTable.html(rightHtml);

}

/**
 * 生成横向表头
 * @param dates
 */
function thirdXTHead(dates){
    var $table = $("#right_table1");
    var tr1 = '<tr>';
    var tr2 = '<tr>';
    $.each(dates,function(idx,item){
        tr1 += '<th colspan="3">' + item + '</th>';
        tr2 += '<td>实际</td><td>计划</td><td>同期</td>';
    });
    tr1 += '</tr>';
    tr2 += '</tr>';
    $table.html(tr1 + tr2);
}

function getTotalTable(data,type){
    if(type == 1){
        return data.feedTotal;
    }else if(type == 2){
        return data.netTotal;
    }else if(type == 3){
        return data.stationTotal;
    }else if(type == 4){
        return data.lineTotal;
    }else if(type == 5){
        return data.roomTotal;
    }
}

/**
 *
 */
function getTrBody(data,size){
    var bodyHtml = '';
    for(var i=0;i<size;i++){
        bodyHtml += '<td>' + data['c'+i] + '</td> <td>' + data['p'+i] + '</td> <td>' + data['l'+i] + '</td>';
    }
    return bodyHtml;
}


//map容器
function Map() {
    this.elements = new Array();
    //获取MAP元素个数
    this.size = function() {
        return this.elements.length;
    }

    //判断MAP是否为空
    this.isEmpty = function() {
        return(this.elements.length < 1);
    }

    //删除MAP所有元素
    this.clear = function() {
        this.elements = new Array();
    }

    //向MAP中增加元素（key, value)
    this.put = function(_key, _value) {
        this.elements.push( {
            key : _key,
            value : _value
        });
    }

    //删除指定KEY的元素，成功返回True，失败返回False
    this.remove = function(_key) {
        var bln = false;
        try{
            for(i = 0; i < this.elements.length; i++) {
                if(this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch(e) {
            bln = false;
        }
        return bln;
    }

    //获取指定KEY的元素值VALUE，失败返回NULL
    this.get = function(_key) {
        try{
            for(i = 0; i < this.elements.length; i++) {
                if(this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        } catch(e) {
            return null;
        }
    }

    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
    this.element = function(_index) {
        if(_index < 0 || _index >= this.elements.length) {
            return null;
        }
        return this.elements[_index];
    }

    //判断MAP中是否含有指定KEY的元素
    this.containsKey = function(_key) {
        varbln = false;
        try{
            for(i = 0; i < this.elements.length; i++) {
                if(this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch(e) {
            bln = false;
        }
        return bln;
    }

    //判断MAP中是否含有指定VALUE的元素
    this.containsValue = function(_value) {
        var bln = false;
        try{
            for(i = 0; i < this.elements.length; i++) {
                if(this.elements[i].value == _value) {
                    bln = true;
                }
            }
        } catch(e) {
            bln = false;
        }
        return bln;
    }

    //获取MAP中所有VALUE的数组（ARRAY）
    this.values = function() {
        var arr = new Array();
        for(i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].value);
        }
        return arr;
    }

    //获取MAP中所有KEY的数组（ARRAY）
    this.keys = function() {
        var arr = new Array();
        for(i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].key);
        }
        return arr;
    }
}

//=============================================遮罩层=====================================================//
/* 显示遮罩层 */
function showOverlay(id) {
    $("#"+id).height(pageHeight());
    $("#"+id).width(pageWidth());
    // fadeTo第一个参数为速度，第二个为透明度
    // 多重方式控制透明度，保证兼容性，但也带来修改麻烦的问题
     top.layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    $("#overlay").fadeTo(100, 0.3);
}

/* 隐藏覆盖层 */
function hideOverlay(id) {
    top.layer.closeAll();
    $("#"+id).fadeOut(200);

}

/* 当前页面高度 */
function pageHeight() {
    return document.body.scrollHeight;
}

/* 当前页面宽度 */
function pageWidth() {
    return document.body.scrollWidth;
}

/* 定位到页面中心 */
function adjust(id) {
    var w = $(id).width();
    var h = $(id).height();

    var t = scrollY() + (windowHeight()/2) - (h/2);
    if(t < 0) t = 0;

    var l = scrollX() + (windowWidth()/2) - (w/2);
    if(l < 0) l = 0;

    $(id).css({left: l+'px', top: t+'px'});
}

//浏览器视口的高度
function windowHeight() {
    var de = document.documentElement;

    return self.innerHeight || (de && de.clientHeight) || document.body.clientHeight;
}

//浏览器视口的宽度
function windowWidth() {
    var de = document.documentElement;

    return self.innerWidth || (de && de.clientWidth) || document.body.clientWidth
}

/* 浏览器垂直滚动位置 */
function scrollY() {
    var de = document.documentElement;

    return self.pageYOffset || (de && de.scrollTop) || document.body.scrollTop;
}

/* 浏览器水平滚动位置 */
function scrollX() {
    var de = document.documentElement;

    return self.pageXOffset || (de && de.scrollLeft) || document.body.scrollLeft;
}
//=============================================遮罩层结束=====================================================//

(function($){
    $.fn.serializeJson = function(){
        var jsonData1 = {};
        var serializeArray = this.serializeArray();
        // 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
        $(serializeArray).each(function () {
            if (jsonData1[this.name]) {
                if ($.isArray(jsonData1[this.name])) {
                    jsonData1[this.name].push(this.value);
                } else {
                    jsonData1[this.name] = [jsonData1[this.name], this.value];
                }
            } else {
                jsonData1[this.name] = this.value;
            }
        });
        // 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
        var vCount = 0;
        // 计算json内部的数组最大长度
        for(var item in jsonData1){
            var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
            vCount = (tmp > vCount) ? tmp : vCount;
        }

        if(vCount > 1) {
            var jsonData2 = new Array();
            for(var i = 0; i < vCount; i++){
                var jsonObj = {};
                for(var item in jsonData1) {
                    jsonObj[item] = jsonData1[item][i];
                }
                jsonData2.push(jsonObj);
            }
            return JSON.stringify(jsonData2);
        }else{
            return "[" + JSON.stringify(jsonData1) + "]";
        }
    };
})(jQuery);