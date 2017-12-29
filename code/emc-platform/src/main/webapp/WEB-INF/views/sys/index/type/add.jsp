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
            <form class="form-horizontal" id="indexTypeAddForm" role="form">


                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>能源类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select name="energyType" class="chosen-select form-control">
                            <option value="">请选择类型</option>
                            <c:forEach items="${sysDic['energyType']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>单位类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select name="unitType" class="chosen-select form-control">
                            <option value="">请选择单位类型</option>
                            <c:forEach items="${sysDic['orgType']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>类型名称：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="name" class="form-control" type="text" maxlength="8" value=""
                               placeholder="请输入类型名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>用量单位：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="unitMeter" class="form-control" value="" type="text" maxlength="16" placeholder="请输入用量单位">
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
        var $form = $(top.document).find("#indexTypeAddForm");
        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";

        $.validator.addMethod("checkName", function (value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url: _platform + '/index/type/check/name',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {name: value,unitType:$(top.document).find('select[name="unitType"]').val()},
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
        }, "类型名称已存在");


        //中文校验
        $.validator.addMethod("isName", function(value, element){
            var tel = /^[^\u0000-\u00FF]+$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "类型名称只能输入中文");

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
                name: {
                    required: true,
                    isName:true,
                    checkName:true
                },
                energyType: {
                    required: true
                },
                unitType: {
                    required: true
                },
                unitMeter: {
                    required: true
                }

            },
            messages: {
                name: {
                    required: icon + "请输入类型名称"
                },
                energyType: {
                    required: icon + "请选择能源类型"
                } ,
                unitType: {
                    required: icon + "请选择单位类型"
                },
                unitMeter: {
                    required: icon + "请输入用量单位"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: _platform + '/index/type/add',
                    data: $form.serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            $('#indexType-table-list').bootstrapTable("refresh");
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