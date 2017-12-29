<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <link rel="stylesheet" href="${web}/static/css/huarestyle.css" />
    <link rel="stylesheet" href="${web}/static/css/homemian.css" />
    <link rel="stylesheet" href="${web}/static/css/dark.css" />
    <style>
        .facedark body,
        body{
            background: transparent !important;
        }
    </style>
</head>

<body>
<div class="publicmenu" >
    <a href="javascript:void(0);" class="scbtn"></a>
    <div class="title">流程首页</div>
    <div class="publicmenu-con">
        <a href="javascript:void(0);" class="btn-up btngun"></a>
        <a href="javascript:void(0);" class="btn-down btngun"></a>
        <ul>
            <li class="active">
                <a href="javascript:void(0);" menu-url="/process/index">流程首页</a>
            </li>
            <li class="">
                <a href="javascript:void(0);" menu-url="http://121.18.114.66:8084/hdb3.2/HdbGraph.aspx?type=1&user=system&active=1&file=zzrl.东关新村换热站">东关新村换热站</a>
            </li>
            <li class="">
                <a href="javascript:void(0);" menu-url="/process/index">流程测试2</a>
            </li>
            <li class="">
                <a href="javascript:void(0);" menu-url="http://crm.e-heating.org/crm/loginAction!login.action">打开OA</a>
            </li>
            <li class="">
                <a href="javascript:void(0);" menu-url="http://www.baidu.com">打开百度</a>
            </li>
            <c:forEach items="${menus}" var="oneMenu">
                <li class="${fn:length(oneMenu.menus)>0?'more':''} ${oneMenu.menuName eq '后台首页'?'active':''}">
                    <a href="javascript:void(0);" menu-url="${oneMenu.menuUrl}">${oneMenu.menuName}</a>
                    <c:if test="${fn:length(oneMenu.menus)>0}">
                        <ul>
                            <c:forEach items="${oneMenu.menus}" var="twoMenu">
                                <li class=""><a href="javascript:void(0);" menu-url="${twoMenu.menuUrl}">${twoMenu.menuName}</a></li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>


</div>

<script src="${web}/static/js/jquery/jquery.min.js"></script>
<script src="${web}/static/js/jquery.mousewheel.js"></script>
<script>
    var _web = '${web}';
    $(function () {

        $('.publicmenu .scbtn').click(function(){
            if($('.publicmenu').css('left')=="0px"){
                $('.publicmenu').animate({left:'-196px'});
                $('.publicmenu .scbtn').animate({left:'0px'});
                $('.publicmenu .scbtn').addClass('close');
                parent.$("#iframepublicmenu").css('z-index',0);
            }else{
                $('.publicmenu .scbtn').animate({left:'196px'});
                $('.publicmenu').animate({left:'0px'});
                $('.publicmenu .scbtn').removeClass('close');
                parent.$("#iframepublicmenu").css('z-index',9);
            }

        });

        var mousewheelflag = true;

        $('div.publicmenu')
                .bind('mousewheel', function(event, delta) {
                    vel = Math.abs(delta);
                    if(delta > 0 ){
                        $('.publicmenu .btn-up').trigger("click");
                    }else{
                        $('.publicmenu .btn-down').trigger("click");
                    }
                    return false;
                });

        $('.publicmenu .btn-up').click(function(){
            if(!mousewheelflag){return;}
            var top = parseInt($('.publicmenu-con > ul').css("top"));
            if(-top>=parseInt($('.publicmenu-con > ul').height())-44){
                return;
            }
            mousewheelflag = false;
            $('.publicmenu-con > ul').animate({"top":(top-44)+"px"},function(){
                mousewheelflag = true;
            });
        });

        $('.publicmenu .btn-down').click(function(){
            if(!mousewheelflag){return;}
            var top = parseInt($('.publicmenu-con > ul').css("top"));
            if(top==0){
                return;
            }
            mousewheelflag = false;
            $('.publicmenu-con > ul').animate({"top":(top+44)+"px"},function(){
                mousewheelflag = true;
            });
        });



        $(".grouplist input").click(function(){
            $(this).next().show();
        });
        $(".grouplist").hover(function(){

        },function(){
            $(this).find("ul").hide();
        });

        var faceKey = localStorage.faceKey == null ? "dark" : localStorage.faceKey; //dark
        var chartsColor;
        changeFace();
        $("#header .changeface").click(function() {
            faceKey = faceKey == "dark" ? "" : "dark";
            changeFace();
        });

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
                    facecolor6: faceKey == 'dark' ? ['#33fff8', '#32657f', '#1d465b', '#ff6349', '#3db4ff'] : ['#32bbb6', '#8394aa', '#b7c1cf', '#df5f4a', '#3b96db']
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
                ec1: {
                    facecolor1: faceKey == 'dark' ? '#325d6c' : '#dbdcdf',
                    facecolor2: faceKey == 'dark' ? '#325d6c' : '#abcd',
                    facecolor3: faceKey == 'dark' ? '#618292' : '#9a9a9b',
                    facecolor4: faceKey == 'dark' ? '#2f4f5f' : '#dbdcdf',
                    facecolor5: faceKey == 'dark' ? ['#33fff8', '#32657f'] : ['#3B96DD', '#c2ccd3'],
                },
                ec2: {
                    facecolor1: faceKey == 'dark' ? ['#33fff8', '#32657f'] : ['#3B96DD', '#c2ccd3'],

                },
                ec3: {
                    facecolor1: faceKey == 'dark' ? "transparent" : "#fff",

                },
                ec4: {
                    facecolor1: faceKey == 'dark' ? "#618292" : "#9a9a9b",
                    facecolor2: faceKey == 'dark' ? "#618292" : "#9a9a9b",
                    facecolor3: faceKey == 'dark' ? ['#33fff8', '#304d60'] : ['#3B96DD', '#a1b1c5'],
                    facecolor4: faceKey == 'dark' ? ['#33fff8'] : ['#3B96DD'],
                    facecolor5: faceKey == 'dark' ? ['#c675c3', '#8d82cc', '#3db4ff', '#32657f', '#33fff8', '#ff6349'] : ['#c675c3', '#8d82cc', '#3b96db', '#a1b1c5', '#32bbb6', '#df614c'],
                    facecolor6: faceKey == 'dark' ? ['#c675c3', '#8d82cc', '#3db4ff', '#33fff8', '#ff6349'] : ['#c675c3', '#8d82cc', '#3b96db', '#32bbb6', '#df614c'],
                }

            }

            if (faceKey == "dark") {
                $("#website").attr("src", "imgdark/index/websitet_cs01.png");
                $('html').addClass('facedark');
                $(".energy_black img").eq(0).attr("src", "imgdark/images/btn01.png");
                $(".energy_black img").eq(1).attr("src", "imgdark/images/btn02.png");
                $(".energy_black img").eq(2).attr("src", "imgdark/images/btn03.png");
                $(".bordertopnone img").eq(0).attr("src", "imgdark/tijian.png");
            } else {
                $("#website").attr("src", "img/index/websitet_cs01.png");
                $(".energy_black img").eq(0).attr("src", "img/images/btn01.png");
                $(".energy_black img").eq(1).attr("src", "img/images/btn02.png");
                $(".energy_black img").eq(2).attr("src", "img/images/btn03.png");
                $(".bordertopnone img").eq(0).attr("src", "img/tijian.png");
            }
            loadDataFun();
        }

        function loadDataFun() {

        }

        //菜单绑定单击事件
        $('.publicmenu').on('click', 'ul a', function () {

            var menuName = $(this).text();
            var url = $(this).attr('menu-url');
            if ("流程首页" == menuName) {
                document.location.replace(_web + url);
                return false;
            }
            if ('#' != url) {
                $('.publicmenu .title').text(menuName);
                parent.$(".pull-left.yuce-tit").html(menuName);
                $('.publicmenu li').removeClass('active');
                $(this).parent().addClass('active');
                $(this).parents('.more').addClass('active');
                $('.publicmenu').animate({left: '-196px'});
                $('.publicmenu .scbtn').animate({left: '0px'});
                $('.publicmenu .scbtn').addClass('close');
                parent.$("#iframepublicmenu").css('z-index',0);
                //top.$(".iframepublicmenu").css();
//                $('.publicmenu-con').hide();
//                $('.publicmenu').animate({left: '-196px'});
//                $('.publicmenu .scbtn').animate({left: '0px'});
//                $('.publicmenu .scbtn').addClass('close');

                openPageToSrc(url);
            } else {
                return false;
            }
        });
    });


    /**
     * ajax打开页面
     * @param url
     */
    function openPageToSrc(url) {
        var suff = url.substr(url.lastIndexOf('&file=') + 6, url.length);
        console.info(suff);
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _web + '/process/decode',
            type: 'POST',
            data: {"code": suff, "enc": "GBK"},
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    url = url.replace(suff, result.code);
                    parent.$("#proecssFrame").attr("src", url);
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });

    }


</script>
</body>

</html>
