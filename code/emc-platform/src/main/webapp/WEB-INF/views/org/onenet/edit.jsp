<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="oncenetEditForm" role="form">
                <input type="hidden" name="id" value="${oncenet.id}">
                <input type="hidden" name="comId" id="comId" value="${oncenet.comId}">
                <input type="hidden" name="netNameOld" id="netNameOld" value="${oncenet.netName}">
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>管网名称：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="netName" id="netName" class="form-control" type="text" value="${oncenet.netName}" maxlength="16" placeholder="请输入管网名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>管网代码：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="netCode" id="netCode" class="form-control" type="text" value="${oncenet.netCode}" maxlength="16" placeholder="请输入管网代码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>管线类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">

                        <select id="netTypeId" name="netTypeId" class="chosen-select form-control">
                            <c:forEach items="${sysdic}" var="item" varStatus="status" >
                                <option  ${oncenet.netTypeId eq item.seq?'selected':''}  value="${item.seq}">${item.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>供热类型：</label>

                    <div class="col-sm-8">
                        <select id="heatType" name="heatType" class="chosen-select form-control">
                            <c:forEach items="${dicheat}" var="item" varStatus="status" >
                                <%--　　var value = ${item.cname}; //传递过来的是int或float类型，不需要加引号--%>
                                <%--　　var id = "${status.id}";//加引号--%>

                                <option  ${oncenet.heatType eq item.seq?'selected':''}  value="${item.seq}">${item.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>管线长度：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="length" class="form-control" value="${oncenet.length}"  type="text" maxlength="16" placeholder="请输入管线长度">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red"></span>小室数量：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="cellNum" class="form-control" value="${oncenet.cellNum}" type="text" maxlength="16" placeholder="请输入小室数量">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red"></span>管段数量：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="partNum" class="form-control" value="${oncenet.partNum}" type="text" maxlength="16" placeholder="请输入管段数量">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red"></span>输送介质：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="medium" class="form-control" value="${oncenet.medium}"  type="text" maxlength="16" placeholder="请输入输送介质">
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
        // validate signup form on keyup and submit
        var $form = $(top.document).find("#oncenetEditForm");
        var icon = "<i class='fa fa-times-circle'></i> ";
        var comId = $(top.document).find(".chosen-select").find("option:selected").val();//选中的文本
        $(top.document).find("#comId").val(comId);

        $.validator.addMethod("checkUnique", function (value, element) {
            var netName = $(top.document).find('#netName').val();
            var comId = $(top.document).find("#comId").val();
            var nameOld = $(top.document).find("#netNameOld").val();
            var netName = $(top.document).find("#netName").val();
            var deferred = $.Deferred();//创建一个延迟对象

            if("${oncenet.netName}" == $(top.document).find("#netName").val()){
                //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
                deferred.resolve();
            }else{
                $.ajax({
                    url: _platform + '/oncenet/check',
                    type: 'POST',
                    async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                    data: {netName:netName,comId:comId},
                    dataType: 'json',
                    success: function (result) {
                        //alert(result.flag);
                        if (!result.flag) {
                            deferred.reject();
                        } else {
                            deferred.resolve();
                        }
                    }
                });
            }
            //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
            return deferred.state() == "resolved" ? true : false;
        }, "管网名称已存在");
        $.validator.addMethod("checkCodeUnique", function (value, element) {
            var netCode = $(top.document).find('#netCode').val();
            var comId = $(top.document).find("#comId").val();
           // alert(netCode+"--"+comId);
            var deferred = $.Deferred();//创建一个延迟对象
            if("${oncenet.netCode}" == $(top.document).find("#netCode").val()){
                //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
                deferred.resolve();
            }else {
                $.ajax({
                    url: _platform + '/oncenet/checkcode',
                    type: 'POST',
                    async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                    data: {netCode: netCode, comId: comId},
                    dataType: 'json',
                    success: function (result) {
                        if (!result.flag) {
                            deferred.reject();
                        } else {
                            deferred.resolve();
                        }
                    }
                });
            }
            //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
            return deferred.state() == "resolved" ? true : false;
        }, "管网代码已存在");
        // 管线长度数值校验
        $.validator.addMethod("isNumber", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            var reg = new RegExp("^[0-9]+(.[0-9]{0,2})?$");
            if(!reg.test(value)){
                //top.layer.msg("请输入数字!");
                return false;
            }else{
                return true;
            }
        }, "请确认输入的数值为整数或小数(精确到2位小数：如:0.01)");
        // 小室数量和管段数量校验
        $.validator.addMethod("isCellNum", function(value, element) {
            var reg = new RegExp("^[0-9]*$");
            if(!reg.test(value)){
                //top.layer.msg("请输入数字!");
                return false;
            }else{
                return true;
            }
        }, "请确认输入的数值为正整数");
        // 管网代码验证
        $.validator.addMethod("isNetCode", function(value, element) {
            var reg = new RegExp("^[A-Za-z0-9]+$");
            if(!reg.test(value)){
                //top.layer.msg("请输入数字!");
                return false;
            }else{
                return true;
            }
        }, "请输入正确的管网代码(如：数字字母组合)");
        $(top.document).on('mousedown','input:not(:submit):not(:button)',function(){
            $(this).closest('.form-group').removeClass('has-error');
            $(this).siblings('.help-block').remove();
        });
        //下拉框信息绑定
        //下拉框js
        $(top.document).find(".chosen-select:not([name='searchComp'])").chosen().on('change',function () {
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
                netName: {
                    required: true,
                    minlength: 2,
                    checkUnique: true
                },
                netCode: {
                    required: true,
                    isNetCode:true,
                    minlength: 2,
                    checkCodeUnique: true
                },
                length: {
                    required: true,
                    isNumber:true
                },
                netTypeId: {
                    required: true
                },
                heatType: {
                    required: true
                },
                partNum: {
                    isCellNum:true
                },
                cellNum: {
                    isCellNum:true
                }
            },
            messages: {
                netName: {
                    required: icon + "请输入管网名称",
                    minlength: icon + "管网名称必须2个字符以上"
                },
                netCode: {
                    required: icon + "请输入管网代码"
                },
                length: {
                    required: icon + "请输入管网长度"
                },
                netTypeId: {
                    required: icon + "请输入管网类型"
                },
                heatType: {
                    required: icon + "请输入供热类型"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
               // alert($form.serialize());
                $.ajax({
                    url: _platform + '/oncenet/editvalue',
                    data: $form.serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            $('#oncenet-table-list').bootstrapTable("refresh");
                        } else {
                            top.layer.close(index);
                            top.layer.msg(result.msg);
                        }
                    }
                });
            }
        });

    });
</script>