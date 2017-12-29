<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper wrapper-content">
    <div class="row col-sm-12 col-xs-12 col-md-12 col-lg-12">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="workSendEditForm" dic="form">
                <input type="hidden" id="comid" name="comid" value="${detail.comid}">
                <input type="hidden" id="orgId" name="orgId" value="${org.id}">
                <input type="hidden" id="code" name="code" value="${detail.code}">
                <input type="hidden" id="id" name="id" value="${detail.id}">
                <input type="hidden" id="readStauts" name="readStauts" value="${detail.readStauts}">
                <input type="hidden" id="createTime" name="createTime" value="${detail.createTime}">
                <input type="hidden" id="creator" name="creator" value="${detail.creator}">

                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>任务单类型：</label>
                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="type" name="type" class="chosen-select form-control">
                            <option value="">请输入任务类型</option>
                            <option <c:if test="${detail.type eq 0}">selected="selected" </c:if> value="0">指定时间</option>
                            <option <c:if test="${detail.type eq 1}">selected="selected" </c:if> value="1">非指定时间</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>任务单名称：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input id="name" name="name" class="form-control inputs-lg" value="${detail.name}" type="text" maxlength="16" placeholder="请输任务单名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>指令内容：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <textarea content="content" id="content" name="content" class="form-control inputs-lg" rows="3" cols="20"  type="text" maxlength="512" placeholder="请输指令内容">${detail.content}</textarea>
                    </div>
                </div>

                <div class="form-group starttime">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span class="red">*</span>任务开始时间：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input id="startTime" type="text" value="${detail.startTime}" class="laydate-icon  form-control inputs-lg layer-date"  name="startTime" placeholder="请选择任务开始时间"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span class="red">*</span>完成时间：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input id="finishTime" type="text" value="${detail.finishTime}" class="laydate-icon  form-control inputs-lg layer-date"  name="finishTime" placeholder="请选择完成时间"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>选人：</label>
                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="takor" name="takor" class="chosen-select form-control">
                            <option value="">请选择人员</option>
                            <c:forEach items="${list}" var="emp">
                                <option <c:if test="${emp.id eq listemp}">selected="selected" </c:if> value="${emp.id}">${emp.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
$.validator.setDefaults({
    ignore: ":hidden:not(select)",//校验chosen
    highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
        if (element.is(":radio") || element.is(":checkbox")) {
            error.insertAfter(element.parent().parent());
        } else if(element.is("select")){
            error.insertAfter(element.next());
        }else{
            error.insertAfter(element);
        }
    },
    errorClass: "help-block m-b-none m-t-xs",
    validClass: "help-block m-b-none m-t-none"
});

//以下为官方示例
$(function () {
    var $form = $(top.document).find("#workSendEditForm");
    var orderType = ${detail.type};
    if(orderType==1){
        $(".starttime").hide();
    }
    // validate signup form on keyup and submit
    var icon = "<i class='fa fa-times-circle'></i> ";

    laydate({
        elem: '#startTime',
        format: 'YYYY-MM-DD hh:00:00',
        max: '2099-06-16 ', //最大日期
        istime: true,
        istoday: false,
        event: 'focus'
    });
    laydate({
        elem: '#finishTime',
        format: 'YYYY-MM-DD hh:00:00',
        max: '2099-06-16 ', //最大日期
        istime: true,
        istoday: false,
        event: 'focus'
    });

    //小数校验
    $.validator.addMethod("isFloat", function(value, element){
        var tel = /^\d+(\.\d{1,3})?$/;
        return this.optional(element) || (tel.test(value));
    }, icon + "请输入正整数或者小数,小数点后不超过三位");

    //提示信息绑定
    $(top.document).on('mousedown','input:not(:submit):not(:button)',function(){
        $(this).closest('.form-group').removeClass('has-error');
        $(this).siblings('.help-block').remove();
    });
    //下拉框信息绑定
    //下拉框js
    $(top.document).find(".chosen-select").chosen().on('change',function () {
        if ($(this).find('option:first').val() != $(this).val()) {
            $(this).siblings('.help-block').remove();
        }
        return false;
    });

    $form.validate({
        onsubmit: true,// 是否在提交是验证
        //移开光标:如果有内容,则进行验证
        onfocusout: function (element) {
            if ($(element).val() == null || $(element).val() == "") {
                $(element).closest('.form-group').removeClass('has-error');
                $(element).siblings('.help-block').remove();
            } else {
                $(element).valid();
            }
        },
        onkeyup: false,// 是否在敲击键盘时验证
        rules: {
            type: {
                required: true
            },
            name: {
                required: true
            },
            content: {
                required: true
            },
            startTime: {
                required: true
            },
            finishTime:{
                required: true
            },
            takor:{
                required: true
            }
        },
        messages: {
            type: {
                required: icon + "请选择类型"
            },
            name: {
                required: icon + "请输入工单代码"
            } ,
            content: {
                required: icon + "请输入内容"
            },
            startTime: {
                required: icon + "请输入开始时间"
            },
            finishTime:{
                required: icon + "请选择完成时间"
            },
            takor:{
                required: icon + "请选择人员"
            }
        },
        submitHandler: function () {
            var index = top.layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: _web + '/work/order/info/edit/send',
                data: $form.serialize(),
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        top.layer.closeAll();
                        top.layer.msg(result.msg);
                        queryWorkInfo();
                    } else {
                        top.layer.close(index);
                        top.layer.msg(result.msg);
                    }
                }
            });
        },

    });
});

$(top.document).find("#type").on('change', function () {
    var real = $(this).val();
    if(real==1){
        $(".starttime").hide();
    }
    if(real==0){
        $(".starttime").show();
    }
});
</script>