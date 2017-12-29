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
            <form class="form-horizontal" id="alarmConfigTempEditForm" dic="form">
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
                            class="red">*</span>最低温度：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="mintemp" id="mintemp" value="${alarmConfig.mintemp}" class="form-control inputs-lg" type="text" maxlength="16" placeholder="请输入最低温度">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>最高温度：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="maxtemp" id="maxtemp" value="${alarmConfig.maxtemp}" class="form-control inputs-lg" type="text" maxlength="16" placeholder="请输入最低温度">
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
        var $form = $(top.document).find("#alarmConfigTempEditForm");

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


        $.validator.addMethod("checkTemp", function(value, element){
            if(value<-30){
                top.layer.msg("最低温度不能低于零下30℃");
                return false;
            }else if(value>50){
                top.layer.msg("最高温度不能高于50℃");
                return false;
            }else{
                return true;
            }
        }, icon + "请输入输入 -30℃~50℃ 的范围");

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
                mintemp:{
                    required: true,
                    checkTemp:true
                },
                maxtemp: {
                    required: true,
                    checkTemp:true
                },
                isenable: {
                    required: true
                }
            },
            messages: {
                mintemp: {
                    required: icon + "请输入温度"
                },
                maxtemp: {
                    required: icon + "请输入温度"
                },
                isenable: {
                    required: icon + "请选择是否启用"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: _web + '/temp/config/edit',
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

</script>