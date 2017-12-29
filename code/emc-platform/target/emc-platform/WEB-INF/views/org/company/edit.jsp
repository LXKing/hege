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
            <form class="form-horizontal" id="companyEditForm" role="form">
                <input type="hidden" name="_method" value="PUT">
                <input type="hidden" name="id" value="${company.id}">
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>公司名称：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input id="cname" name="cname" class="form-control" type="text" maxlength="32" value="${company.cname}"
                               placeholder="请输入公司名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label">公司下级翻译：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="nextDes" class="form-control" type="text" maxlength="16"  value="${company.nextDes}"
                               placeholder="请输入公司下级翻译">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label">数据表名：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="tableName" class="form-control" readonly type="text" maxlength="64"  value="${company.tableName}"
                               placeholder="请输入数据表名">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label">logo图片名称：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="logoImg" class="form-control" type="text" maxlength="64" value="${company.logoImg}"
                               placeholder="请输入logo图片名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label">天气采集类型：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="weatherType" name="weatherType" class="chosen-select form-control"  >
                            <option value="1" ${company.wcode eq company.id?"selected":""}>自定义</option>
                            <option value="2" ${company.wcode ne company.id?"selected":""}>气象节点</option>
                        </select>
                    </div>
                </div>
                <div class="form-group" id="weatherP"  style="display: ${company.wcode eq company.id?"none":""};">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label">气象省级：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="weatherPs" name="weatherPs" class="chosen-select form-control"  >
                            <option value="">请选择省级</option>
                            <c:forEach items="${cites}" var="city">
                                <option value="${city.W_CODE}" ${city.W_CODE eq ccode?'selected':''}>${city.W_NAME}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group" id="weatherC" style="display: ${company.wcode eq company.id?"none":""};">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label">气象市级：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="weatherCs" name="weatherCs" class="chosen-select form-control"  >
                            <option value="">请选择市级</option>
                            <c:forEach items="${pcties}" var="city">
                                <option value="${city.W_CODE}" ${city.W_CODE eq pcode?'selected':''}>${city.W_NAME}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group" id="weatherT" style="display: ${company.wcode eq company.id?"none":""};">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>气象县级：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <select id="weatherTs" name="wcode" class="chosen-select form-control"  >
                            <option value="${company.wcode eq company.id?company.wcode:""}">请选择县级</option>
                            <c:forEach items="${ccties}" var="city">
                                <option value="${city.W_CODE}" ${city.W_CODE eq company.wcode?'selected':''}>${city.W_NAME}</option>
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
            element.closest('.form-group').removeClass('has-error').addClass('has-success');
        },
        errorElement: "span",
        errorPlacement: function (error, element) {
            if (element.is(":radio") || element.is(":checkbox")) {
                error.appendTo(element.parent().parent().parent());
            }else if(element.is("select")){
                error.appendTo(element.parent());
            } else {
                error.appendTo(element.parent());
            }
        },
        errorClass: "help-block m-b-none m-t-xs",
        validClass: "help-block m-b-none m-t-none"


    });



    //以下为官方示例
    $(function () {
        var $form = $(top.document).find("#companyEditForm");
        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";
        $(top.document).find(".chosen-select:not([name='searchComp'])").chosen();

        $(top.document).find('#weatherType').change(function () {
            if ($(this).val() ==1) {
                $(top.document).find("#weatherP").hide();
                $(top.document).find("#weatherC").hide();
                $(top.document).find("#weatherT").hide();
            }else{
                $(top.document).find("#weatherP").show();
                $(top.document).find("#weatherC").show();
                $(top.document).find("#weatherT").show();
            }
        });
        $(top.document).find('#weatherPs').change(function () {
            var $element = $(top.document).find('#weatherCs');
            $.ajax({
                url: _platform + '/company/weather/city',
                type: 'POST',
                data: {wPCode: $(this).val(),_method:'PATCH'},
                dataType: 'json',
                success: function (result) {
                    $element.empty();
                    $element.append('<option value="">请选择市级</option>');
                    if(result.list!=null&&result.list!=""){
                        $.each(result.list, function (idx, item) {
                            $element.append('<option value="' + item.W_CODE + '">' + item.W_NAME + '</option>');
                        });
                    }
                    $element.chosen("destroy").chosen();
                    $(top.document).find('#weatherCs').change();
                }
            });
        });
        $(top.document).find('#weatherCs').change(function () {
            var $element = $(top.document).find('#weatherTs');
            $.ajax({
                url: _platform + '/company/weather/city',
                type: 'POST',
                data: {wPCode: $(this).val(),_method:'PATCH'},
                dataType: 'json',
                success: function (result) {
                    $element.empty();
                    $element.append('<option value="">请选择县级</option>');
                    if(result.list!=null&&result.list!="") {
                        $.each(result.list, function (idx, item) {
                            $element.append('<option value="' + item.W_CODE + '">' + item.W_NAME + '</option>');
                        });
                    }
                    $element.chosen("destroy").chosen();
                }
            });
        });

        //提示信息绑定
        $('input:not(:submit):not(:button)').mousedown(function () {
            $(this).closest('.form-group').removeClass('has-error');
            $(this).siblings('.help-block').remove();
        });
        //下拉框信息绑定
        $('select').change(function () {
            if ($(this).find('option:first').val() != $(this).val()) {
                $(this).siblings('.help-block').remove();
            }
            return false;
        });

        $.validator.addMethod("weatherTs", function(value, element) {
            var val = $(top.document).find("#weatherType").val();
            if(val==1){
                return true;
            }else{
                if($(top.document).find("#weatherTs").val()!=null&&$(top.document).find("#weatherTs").val()!=""){
                    return true;
                }else{
                    return false;
                }
            }
        }, icon + "请选择气象县级");

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
                cname: {
                    required: true,
                    minlength: 2
                } ,
                wcode:{
                    weatherTs:true
                }
            },
            messages: {
                cname: {
                    required: icon + "请输入公司名称",
                    minlength: icon + "公司名称必须2个字符以上"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: _platform + '/company/edit',
                    data: $form.serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            $('#company-table-list').bootstrapTable("refresh");
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