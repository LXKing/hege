<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bin
  Date: 2017/8/31
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="meterDataPrestoreForm" role="form">
                <input id="comId" name="comId" type="hidden" class="class-comid" value="${mec.comId}">
                <input type="hidden" name="collectId" value="${mec.id}"/>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>计量名称：</label>

                    <div class="col-sm-7  col-xs-7 col-md-7 col-lg-7">
                        <input name="name" readonly  class="form-control inputs-lg" value="${mec.name}" type="text" maxlength="16" placeholder="请输入计量名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>所属单位：</label>

                    <div class="col-sm-7 ">
                        <select id="unitId" disabled name="unitId" class="chosen-select-unit form-control inputs-lg" >
                            <c:forEach items="${uList}" var="unit">
                                <option <c:if test="${mec.unitId eq unit.unitId}">selected="selected" </c:if> value="${unit.unitId}">${unit.unitName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>预存时间：</label>
                    <div class="col-sm-7 ">
                        <input id="start" type="text"  class="laydate-icon  form-control layer-date inputs-lg"  name="prestoreTime"
                               placeholder="提示：换表时间精确到小时！"/>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>旧计量表底：</label>

                    <div class="col-sm-7  col-xs-7 col-md-7 col-lg-7">
                        <input name="usedNum" class="form-control inputs-lg" value="" type="text" maxlength="16" placeholder="请输入旧计量表底">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>预存值：</label>
                    <div class="col-sm-7  col-xs-7 col-md-7 col-lg-7">
                        <input name="prestoreNum" id="code" class="form-control inputs-lg" value="" type="text" maxlength="16" placeholder="请输入预存值">
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
            error.insertAfter(element.parent());
        }else if(element.is("input") && $(element).attr('name') == 'formula'){
            error.insertAfter(element.parent().parent());
        }else{
            error.insertAfter(element.parent());
        }
    },
    errorClass: "help-block m-b-none m-t-xs",
    validClass: "help-block m-b-none m-t-none"
});

//以下为官方示例
$(function () {
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD hh:mm:ss',
        //min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 ', //最大日期
        istime: true,
        istoday: false
    };
    top.laydate(start);
    // validate signup form on keyup and submit
    var icon = "<i class='fa fa-times-circle'></i> ";

    var $form = $(top.document).find("#meterDataPrestoreForm");

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

            changeTime: {
                required: true
            },
            usedNum: {
                required:true
            },
            usedCoef: {
                required:true
            },
            newNum: {
                required:true
            },
            newCoef: {
                required:true
            }

        },
        messages: {
            changeTime: {
                required: icon + "请输入换表日期"
            },
            usedNum: {
                required:icon + "请输入旧计量表底"
            },
            usedCoef: {
                required:icon + "请输入旧计量系数"
            },
            newNum: {
                required:icon + "请输入新计量表底"
            },
            newCoef: {
                required:icon + "请输入新计量系数"
            }
        },
        submitHandler: function () {
            var index = top.layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: _web + '/meterData/prestore',
                data: $form.serialize(),
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        top.layer.closeAll();
                        top.layer.msg(result.msg);
                        queryMeter(1);
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