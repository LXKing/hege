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
            <form class="form-horizontal" id="alarmConfigEditForm" dic="form">
                <input type="hidden" name="_method" value="PUT">
                <input type="hidden" id="id" name="id" value="${alarmConfig.id}">
                <input type="hidden" id="comId" name="comId" value="${company.id}">
                <input type="hidden" id="orgId" name="orgId" value="${org.id}">
                <input type="hidden" id="unitType" name="unitType" value="${alarmConfig.unitType}">
                <input type="hidden" name="unitId" value="${alarmConfig.unitId}">
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>单位类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select disabled class="chosen-select form-control">
                            <c:forEach items="${sysDic['orgType']}" var="type">
                                <option ${type.seq eq alarmConfig.unitType?'selected':''} value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>用能单位：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select disabled id="unitId" class="chosen-select form-control">
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>点名：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="tag" class="form-control inputs-lg" value="${alarmConfig.tag}"  type="text" maxlength="80" placeholder="请输入点名">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>报警描述：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="alarmName" class="form-control inputs-lg" value="${alarmConfig.alarmName}" type="text" maxlength="32" placeholder="请输入报警描述">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>报警类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="alarmType" name="alarmType" class="chosen-select form-control">
                            <c:forEach items="${sysDic['alarmType']}" var="type">
                                <option ${type.seq eq alarmConfig.alarmType?'selected':''} value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>报警等级：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="alarmLevel" name="alarmLevel" class="chosen-select form-control">
                            <c:forEach items="${sysDic['alarmLevel']}" var="type">
                                <option ${type.seq eq alarmConfig.alarmLevel?'selected':''} value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group" style="display: ${0 eq alarmConfig.alarmType?'none':''}">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span class="red">*</span>报警模式：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="alarmModel" name="alarmModel" class="chosen-select form-control">
                            <c:forEach items="${sysDic['alarmModel']}" var="type">
                                <option ${type.seq eq alarmConfig.model?'selected':''} value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>报警阈值：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="num" class="form-control inputs-lg" value="${alarmConfig.num}" type="text" maxlength="16" placeholder="请输入报警阈值">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>是否启用：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="isenable" name="isenable" class="chosen-select form-control">
                            <option ${0 eq alarmConfig.isenable?'selected':''} value="0">启用</option>
                            <option ${1 eq alarmConfig.isenable?'selected':''} value="1">停用</option>
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
        var $form = $(top.document).find("#alarmConfigEditForm");

        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";

        $("#alarmType").chosen().on('change',function () {
            if(0==$(this).val()){
//                $("#alarmModel").chosen("destroy");
                $("#alarmModel").parents('.form-group').hide();
            }else{
//                $("#alarmModel").chosen("destroy").chosen();
                $("#alarmModel").parents('.form-group').show();
            }
        });
        //加载用能单位
        getUnitSelect();
        //加载tag
        //getTagSelect();

        //校验重复添加
        $.validator.addMethod("checkAddRepeat", function (value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url: _web + '/alarm/config/check/repeat',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {unitType: $("#unitType").val(),
                    unitId: $("#unitId").val(),
                    alarmType: $("#alarmType").val(),
                    alarmLevel: $("#alarmLevel").val(),
                    model: $("#alarmModel").val(),
                    id:$("#id").val()},
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
        }, icon + "工况报警配置重复添加");

        //小数校验
        $.validator.addMethod("isFloat", function(value, element){
            var tel = /^\d+(\.\d{1,4})?$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "请输入正整数或者小数,小数点后不超过四位");

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
                tag: {
                    required: true
                },
                alarmName: {
                    required: true
                },
                num: {
                    required: true,
                    isFloat: true
                },
                alarmLevel: {
                    required: true,
                    checkAddRepeat: true
                }
            },
            messages: {
                tag: {
                    required: icon + "请选择点名"
                },
                alarmName: {
                    required: icon + "请输入报警描述"
                },
                num: {
                    required: icon + "请输入报警阈值"
                },
                alarmLevel: {
                    required: icon + "请选择报警等级"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: _web + '/alarm/config/edit',
                    data: $form.serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            queryAlarmConfig();
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
                    $element.append('<option ' + (item.UNITID == "${alarmConfid.unitId}"?'selected':'') + ' value="' + item.UNITID + '">' + item.UNITNAME + '</option>');
                });
                $element.chosen("destroy").chosen();
            }
        });
    }

    <%--function getTagSelect() {--%>
        <%--$.ajax({--%>
            <%--url: _web + '/select/tags',--%>
            <%--type: 'POST',--%>
            <%--dataType: 'json',--%>
            <%--success: function (result) {--%>
                <%--var $element = $("#tag");--%>
                <%--$element.empty();--%>
                <%--$.each(result, function (idx, item) {--%>
                    <%--$element.append('<option ' + (item.TAG == "${alarmConfig.tag}"?'selected':'') + ' value="' + item.TAG + '">' + item.TAG + '</option>');--%>
                <%--});--%>
                <%--$element.chosen("destroy").chosen();--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>
</script>