/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/24<BR>
 */

//回车事件
var loge;
document.onkeydown = function(e){
    if(!loge){
        loge = true;
        return;
    }
    e = e ? e : event;
    if(e.keyCode == 13){
        $('#login').click();
        return false;
    }
}
//以下为官方示例
$(function () {
    if(getCookie("isAutoLogin")=="true"){
        $("#isAutoLogin").prop({
            checked:true
        });
        $.ajax({
            url:ctx + '/login-in?' + Math.random(),
            data:{"login":getCookie("loginUserName"),"pwd":getCookie("loginPwd")},
            type:'POST',
            dataType:'json',
            success:function(result) {
                if(result.isLogin){
                    //存放cookie
                    //存是否自动登录状态
                    setCookie('isAutoLogin', $("#isAutoLogin").is(':checked'), 30);
                    //存放用户密码
                    setCookie('loginUserName', $("input[name='login']").val(), 30);
                    setCookie('loginPwd', $("input[name='pwd']").val(), 30);
                    window.location.replace(ctx + "/index");
                }else{
                    $('.login-error').remove();
                    $("#msg").append('<span style="color: red;" class="help-block m-b-none login-error"><i class="fa fa-times-circle"></i> '+result.msg+'</span>');
                    //$('.ver-code-img').click();
                }
            }
        })
    }
    $("#login").click(function(){
            var index = top.layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url:ctx + '/login-in?' + Math.random(),
                data:$('form').serialize(),
                type:'POST',
                dataType:'json',
                success:function(result) {
                    if(result.isLogin){
                        //存放cookie
                        //存是否自动登录状态
                        setCookie('isAutoLogin', $("#isAutoLogin").is(':checked'), 30);
                        //存放用户密码
                        setCookie('loginUserName', $("input[name='login']").val(), 30);
                        setCookie('loginPwd', $("input[name='pwd']").val(), 30);
                        window.location.replace(ctx + "/index");
                    }else{
                        $('.login-error').remove();
                        $("#msg").append('<span style="color: red;" class="help-block m-b-none login-error"><i class="fa fa-times-circle"></i> '+result.msg+'</span>');
                        //$('.ver-code-img').click();
                        top.layer.close(index);
                    }
                }
            })
    }

    );


    //刷新验证码
    $('.ver-code-img').bind('click', function() {
        this.src = this.src + '?';
    });
});