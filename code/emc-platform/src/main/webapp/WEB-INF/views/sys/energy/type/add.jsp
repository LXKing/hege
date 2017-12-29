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
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="energyTypeAddForm" role="form">

                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>类型名称(中文)：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="nameZh" class="form-control" type="text" maxlength="8" value=""
                               placeholder="请输入类型名称(中文)">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>类型名称(英文)：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="nameEn" class="form-control" value="" type="text" maxlength="16" placeholder="请输入类型名称(英文)">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select name="type" class="chosen-select form-control">
                            <option value="">请选择类型</option>
                            <c:forEach items="${sysDic['energyType']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>经济类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select name="ecoType" class="chosen-select form-control">
                            <option value="">请选择经济类型</option>
                            <c:forEach items="${sysDic['energyEcoType']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>用量单位：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="dosageUnit" class="form-control" value="" type="text" maxlength="16" placeholder="请输入用量单位">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>默认单价(元)：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="price" class="form-control" value="" type="text" maxlength="10" placeholder="请输入默认单价(元)">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>标煤系数：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="coef" class="form-control" type="text" maxlength="10" placeholder="请输入标煤系数">
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
        var $form = $(top.document).find("#energyTypeAddForm");
        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";

        $.validator.addMethod("checkNameZh", function (value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url: _platform + '/energy/type/check/name/zh',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {nameZh: value},
                dataType: 'json',
                success: function (result) {
                    if (!result.flag) {
                        deferred.reject();
                    } else {
                        deferred.resolve();
                    }
                }
            });
            //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
            return deferred.state() == "resolved" ? true : false;
        }, "类型名称(中文)已存在");

        $.validator.addMethod("checkNameEn", function (value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url: _platform + '/energy/type/check/name/en',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {nameEn: value},
                dataType: 'json',
                success: function (result) {
                    if (!result.flag) {
                        deferred.reject();
                    } else {
                        deferred.resolve();
                    }
                }
            });
            //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
            return deferred.state() == "resolved" ? true : false;
        }, "类型名称(英文)已存在");

        //中文校验
        $.validator.addMethod("isNameZh", function(value, element){
            var tel = /^[^\u0000-\u00FF]+$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "类型名称(中文)只能输入中文");
        //英文校验
        $.validator.addMethod("isNameEn", function(value, element){
            var tel = /^[A-Za-z]+$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "类型名称(英文)只能输入英文");
        //单价校验
        $.validator.addMethod("isPrice", function(value, element){
            var tel = /^\d+(\.\d{1,2})?$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "单价格式错误,正确格式如1,1.21");
        //标煤校验
        $.validator.addMethod("isCoef", function(value, element){
            var tel = /^\d+(\.\d+)?$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "标煤格式错误，正确格式如1,1.234");

        //提示信息绑定
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
                nameZh: {
                    required: true,
                    isNameZh:true,
                    checkNameZh:true
                },
                nameEn: {
                    required: true,
                    isNameEn:true,
                    checkNameEn:true
                },
                dosageUnit: {
                    required: true
                },
                price: {
                    required: true,
                    isPrice:true
                },
                coef:{
                    required: true,
                    isCoef:true
                },
                ecoType:{
                    required: true
                },
                type:{
                    required: true
                }

            },
            messages: {
                nameZh: {
                    required: icon + "请输入类型名称(中文)"
                },
                nameEn: {
                    required: icon + "请输入类型名称(英文)"
                } ,
                dosageUnit: {
                    required: icon + "请输入用量单位"
                },
                price: {
                    required: icon + "请输入能源单价(元)"
                },
                coef: {
                    required: icon + "请输入标煤系数"
                },
                ecoType: {
                    required: icon + "请选择经济类型"
                },
                type: {
                    required: icon + "请选择类型"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: _platform + '/energy/type/add',
                    data: $form.serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            $('#energyType-table-list').bootstrapTable("refresh");
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