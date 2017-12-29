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
            <form class="form-horizontal" id="indexAddForm" dic="form">
                <input type="hidden" id="comId" name="comId" value="${company.id}">
                <input type="hidden" id="orgId" name="orgId" value="${org.id}">
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>单位类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="unitType" name="unitType" class="chosen-select form-control">
                            <c:forEach items="${sysDic['orgType']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>用能单位：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="unitId" name="unitId" class="chosen-select form-control">
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>指标类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="typeId" name="typeId" class="chosen-select form-control">

                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>企业指标：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="enterprise" class="form-control inputs-lg" value="${typeZh}" type="text" maxlength="16" placeholder="请输入企业指标">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>地方指标：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="local" class="form-control inputs-lg" value="${typeZh}" type="text" maxlength="16" placeholder="请输入地方指标">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>行业指标：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="industry" class="form-control inputs-lg" value="${typeZh}" type="text" maxlength="16" placeholder="请输入行业指标">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span class="red">*</span>生效时间：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input id="indexTime" type="text" class="laydate-icon  form-control inputs-lg layer-date"  name="indexTime" placeholder="请选择生效时间"/>
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
        var $form = $(top.document).find("#indexAddForm");

        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";

        laydate({
            elem: '#indexTime',
            format: 'YYYY-MM-DD hh:00:00',
            max: '2099-06-16 ', //最大日期
            istime: true,
            istoday: false,
            event: 'focus'
        });

        $("#unitType").chosen().on('change',function () {
            //加载用能单位
            getUnitSelect();
            //加载指标类型
            getIndexTypeSelect();
        });
        //加载用能单位
        getUnitSelect();
        //加载指标类型
        getIndexTypeSelect();

        $.validator.addMethod("checkType", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_web + '/index/allocation/check/type',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {unitId:$("#unitId").val(),typeId:$("#typeId").val(),comId:$("#comId").val()},
                dataType: 'json',
                success:function(result) {
                    if (!result.flag) {
                        deferred.reject();
                    } else {
                        deferred.resolve();
                    }
                }
            });
            //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
            return deferred.state() == "resolved" ? true : false;
        }, icon + '该单位下指标类型已经存在');

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
                typeId: {
                    required: true,
                    checkType:true
                },
                industry: {
                    required: true,
                    isFloat: true
                },
                local: {
                    required: true,
                    isFloat: true
                },
                enterprise: {
                    required: true,
                    isFloat: true
                },
                indexTime:{
                    required: true
                }
            },
            messages: {
                typeId: {
                    required: icon + "请选择指标类型"
                },
                industry: {
                    required: icon + "请输入行业指标"
                } ,
                local: {
                    required: icon + "请输入地方指标"
                },
                enterprise: {
                    required: icon + "请输入企业指标"
                },
                indexTime:{
                    required: icon + "请选择生效时间"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: _web + '/index/allocation/add',
                    data: $form.serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            queryAllocation();
                        } else {
                            top.layer.close(index);
                            top.layer.msg(result.msg);
                        }
                    }
                });
            }
        });

    });

    function getUnitSelect() {
        $.ajax({
            url: _web + '/select/org/unit',
            type: 'POST',
            data: {comId: $("#comId").val(),orgId:$("#orgId").val(),unitType:$("#unitType").val()},
            dataType: 'json',
            success: function (result) {
                var $element = $("#unitId");
                $element.empty();
                $.each(result, function (idx, item) {
                    $element.append('<option value="' + item.UNITID + '">' + item.UNITNAME + '</option>');
                });
                $element.chosen("destroy").chosen();
            }
        });
    }

    function getIndexTypeSelect() {
        $.ajax({
            url: _web + '/select/index/type',
            type: 'POST',
            data: {unitType:$("#unitType").val()},
            dataType: 'json',
            success: function (result) {
                var $element = $("#typeId");
                $element.empty();
                $.each(result, function (idx, item) {
                    $element.append('<option value="' + item.INDEXID + '">' + item.INDEXNAME + '</option>');
                });
                $element.chosen("destroy").chosen();
            }
        });
    }
</script>