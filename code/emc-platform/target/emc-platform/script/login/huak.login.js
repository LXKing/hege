/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/24<BR>
 */
//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
$.validator.setDefaults({
    highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        element.closest('.form-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
        if (element.is(":radio") || element.is(":checkbox")) {
            error.appendTo(element.parent().parent().parent());
        } else {
            error.appendTo(element.parent());
        }
    },
    errorClass: "help-block m-b-none m-t-xs",
    validClass: "help-block m-b-none m-t-none"


});

if (window.top !== window.self) {
    window.top.location = window.location;
}
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

    //提示信息绑定
    $('input:not(:submit):not(:button)').mousedown(function () {
        $(this).closest('.form-group').removeClass('has-error');
        $(this).siblings('.help-block').remove();
    });
    //下拉框信息绑定
    $('select').change(function () {
        if($(this).find('option:first').val()!=$(this).val()){
            $(this).siblings('.help-block').remove();
        }
        return false;
    });
    // validate signup form on keyup and submit
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#form").validate({
        onsubmit:true,// 是否在提交是验证
        //移开光标:如果有内容,则进行验证
        onfocusout: function (element) {
            if ($(element).val()==null||$(element).val()=="") {
                $(element).closest('.form-group').removeClass('has-error');
                $(element).siblings('.help-block').remove();
            }else{
                $(element).valid();
            }
        },
        onkeyup :false,// 是否在敲击键盘时验证
        rules: {
            login: {
                required: true,
                minlength: 2
            },
            pwd: {
                required: true,
                minlength: 6
            },
            vc: {
                required: true,
                minlength: 4,
                maxlength:4
            }
        },
        messages: {
            login: {
                required: icon + "请输入您的用户名",
                minlength: icon + "用户名必须2个字符以上"
            },
            pwd: {
                required: icon + "请输入您的密码",
                minlength: icon + "密码必须6个字符以上"
            },
            vc: {
                required: icon + "请输入验证码",
                minlength: icon + "验证码必须4个字符"
            }
        },
        submitHandler:function(){
            $.ajax({
                url:ctx + '/login-in?' + Math.random(),
                data:$('form').serialize(),
                type:'POST',
                dataType:'json',
                success:function(result) {
                    if(result.isLogin){
                        window.location.replace(ctx + "/main");
                    }else{
                        $('.login-error').remove();
                        $("#login").after('<span style="color: red;" class="help-block m-b-none login-error"><i class="fa fa-times-circle"></i> '+result.msg+'</span>');
                        $('.ver-code-img').click();
                    }
                }
            });
        }
    });

    //刷新验证码
    $('.ver-code-img').bind('click', function() {
        this.src = this.src + '?';
    });
});

//自定义规则:
$.validator.addMethod("nameRule", function (value, element) {
    return (/^(([\u4e00-\u9fa5]|·){2,10}|[a-zA-Z\.\s]{4,20})$/.test(value));
}, "请输入真实姓名，英文长度4-20，中文长度2-10");

$.validator.addMethod("address", function (value, element) {
    return (/^(\S(\S|\s){3,63})$/.test(value));
}, "请输入4-64位字符之间的详细地址");

//自定义规则:不能是纯数字
$.validator.addMethod("notFullNum", function (value, element) {
    return !value.match(/^([0-9]+)$/);
}, "姓名不能使用纯数字");

//自定义规则:固定电话
$.validator.addMethod("telePhone", function (value, element) {
    return this.optional(element)||value.match(/^(0\d{2,3}\-)(([2-9]\d{6,7})|([2-9]\d{6,7})(\-\d{1,4}))$/);
}, "请输入有效的固定电话。如010-4888888-8");

$.validator.addMethod("mobile", function (value, element) {
    return value.match(/^(1\d{10})$/);
}, "请输入有效的手机号");

$.validator.addMethod("email", function (value, element) {
    return this.optional(element)||(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value));
}, "邮箱格式不正确，请重新输入");
