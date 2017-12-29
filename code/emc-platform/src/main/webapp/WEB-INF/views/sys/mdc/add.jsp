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
            <form class="form-horizontal" id="addMeterCollectAddForm" role="form">

                <input id="comId" name="comId" type="hidden" class="class-comid" value="">
                <input id="orgId" name="orgId" type="hidden" class="class-comid" value="${orgId}">


                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>供热单位类型：</label>

                    <div class="col-sm-8">
                        <select id="unitType" name="unitType" class="chosen-select unittype-select form-control">
                            <c:forEach items="${sysDic['orgType']}" var="type">
                                <option value="${type.seq}">${type.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>供热单位：</label>

                    <div class="col-sm-8">
                        <select id="unitId" name="unitId" class="chosen-select-unit form-control">

                        </select>
                    </div>
                </div>
                <div class="form-group">

                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>计量名称：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="name" id="name" class="form-control" type="text" maxlength="16" placeholder="请输入计量名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>计量代码：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="code" id="code" class="form-control" value="${code}" type="text" maxlength="7" placeholder="请输入计量代码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red"></span>出厂编号：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="serialNo" id="serialNo" class="form-control" type="text" maxlength="16" placeholder="请输入出厂编号">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>能源类型：</label>

                    <div class="col-sm-8">
                        <select id="energyTypeId" name="energyTypeId" class="chosen-select  form-control">
                            <option value="">请选择类型</option>
                            <c:forEach items="${energy}" var="type">
                                <option value="${type.id}">${type.nameZh}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div id="isReal" class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>实虚表：</label>

                    <div class="col-sm-8">
                        <select id="isreal" name="isreal" class="chosen-select form-control">
                            <option value="">请选择类型</option>
                            <option value="0">实表</option>
                            <option value="1">虚表</option>
                        </select>
                    </div>
                </div>
                <div id="gongshi" class="form-group">
                </div>
                <div id="yucun" class="form-group">
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>是否总表：</label>
                    <div class="col-sm-8">
                        <select name="istotal" id="istotal" class="chosen-select form-control">
                            <option value="">请选择类型</option>
                            <option value="0">总表</option>
                            <option value="1">单位总表</option>
                            <option value="2">系统总表</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>自动采集：</label>
                    <div class="col-sm-8">
                        <select name="isauto" id="isauto" class="chosen-select form-control">
                            <option value="">请选择类型</option>
                            <option value="0">自动采集</option>
                            <option value="1">手工</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>系数：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input id="coef" name="coef" class="form-control" type="text" maxlength="16" placeholder="请输入计量系数">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red">*</span>点表：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="tag" id="tag" class="form-control" type="text" maxlength="80" placeholder="请输入点表">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>删除标识：</label>
                    <div class="col-sm-8">
                        <select name="isdelete" id="isdelete" class="chosen-select form-control">
                            <option value="">请选择类型</option>
                            <option value="0">未删除</option>
                            <option value="1">软删除标识</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span
                            class="red"></span>描述：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="depict" id="depict" class="form-control" type="text" maxlength="32" placeholder="请输入计量器具描述">
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
    var icon = "<i class='fa fa-times-circle'></i> ";
    var comId = $(top.document).find(".chosen-select").find("option:selected").val();//选中的文本
    $(top.document).find("#comId").val(comId);

    var $form = $(top.document).find("#addMeterCollectAddForm");

    var unittype = $(top.document).find("#unitType").find("option:selected").val();
        getType(unittype);
//    $(top.document).find(".unittype-select").on('change', function () {
//        getType($(this).val());
//    });
    $(top.document).find(".unittype-select").chosen().on('change',function () {
        var unittype = $(top.document).find("#unitType").find("option:selected").val();
        getType(unittype);
    });

    $(top.document).find("#isreal").on('change', function () {
        var real = $(this).val();
        if(real==1){
            //0-实表-预存  1-虚表-公式+-×÷]
            var html='<div  class="form-group">'+
                     '<label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label">'+
                     '<span class="red"></span>公式：</label>'+
                     '<div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">'+
                     '<input name="formula" id="formula" class="form-control" type="text" maxlength="64" placeholder="请输入公式">'+
                     '<div><button class="btnFun" type="button">+</button><button type="button" class="btnFun">-</button>' +
                     '<button type="button" class="btnFun">×</button><button type="button" class="btnFun">÷</button>' +
                     '<button type="button" class="btnFun">(</button><button type="button" class="btnFun">)</button></div>'+
                     '</div>'+'</div>';
            $(top.document).find("#yucun").html("");
            $(top.document).find("#gongshi").html(html);
        }
        if(real==0){
//            var html='<div  class="form-group">'+
//                    '<label class="col-sm-3  control-label"><span class="red">*</span>是否预存：</label>'+
//                    '<div class="col-sm-8">'+
//                    '<select name="isprestore" id="isprestore" class="chosen-select form-control">'+
//                    '<option value="">请选择类型</option>'+
//                    '<option value="0">不是</option>'+
//                    '<option value="1">是</option>'+
//                    '</select>'+
//                    '</div>'+'</div>';
            $(top.document).find("#gongshi").html("");
//            $(top.document).find("#yucun").html(html);
        }
    });
    $(top.document).undelegate(".btnFun",'click');
    $(top.document).delegate(".btnFun",'click', function () {

//        alert($(this).text());
        var value = $(this).text();

        var v = $(top.document).find("#formula").val();

        if(value=="("){
//            alert(v+"------"+value);
            $(top.document).find("#formula").val(v+value);
            return false;
        }
        if(v==''||v==null){
            top.layer.msg("请填写公式或组成公式的代码");
            return false;
        }else {
            //alert(v+$(this).text());
            var reg = /^[((\A\d{5})\.\+\-\×\÷]+$/;
            //alert(v);
            if (!reg.test(v)) {
                top.layer.msg("请输入正确的公式，该不合法!");
                return false;
            }else{
                $.ajax({
                url: _platform + '/meterCollect/check/formula',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {formula:v},
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    if (data.result) {
                        top.layer.msg("请输入正确的公式!");
                        return false;
                    } else {
                        //alert(v+"---"+value);
                        $(top.document).find("#formula").val(v+value);
                    }
                }
            });

         }
      }
   });

    $.validator.addMethod("checkName", function (value, element) {
        var name = $(top.document).find('#name').val();
        var unitId = $(top.document).find("#unitId").find("option:selected").val();
        var deferred = $.Deferred();//创建一个延迟对象
        $.ajax({
            url: _platform + '/meterCollect/check/name',
            type: 'POST',
            async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
            data: {name:name,unitId:unitId},
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
    }, "计量名称已存在");

    $.validator.addMethod("checkCode", function (value, element) {
        var code = $(top.document).find('#code').val();
        var comId = $(top.document).find("#comId").val();
        var deferred = $.Deferred();//创建一个延迟对象
        $.ajax({
            url: _platform + '/meterCollect/check/code',
            type: 'POST',
            async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
            data: {code:code,comId:comId},
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
    }, "计量代码已存在");
    $.validator.addMethod("checkSerialNo", function (value, element) {
        var serialNo = $(top.document).find('#serialNo').val();
        var deferred = $.Deferred();//创建一个延迟对象
        $.ajax({
            url: _platform + '/meterCollect/check/serialno',
            type: 'POST',
            async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
            data: {serialNo:serialNo},
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
    }, "出场编号已存在");
    // 校验公式
//    $.validator.addMethod("checkFormula", function(value, element) {
//        var deferred = $.Deferred();//创建一个延迟对象
//        var reg=/^[(A\d{5})\.\+\-\×\÷]+$/;
//        if(!reg.test(value)){
//            //top.layer.msg("请输入数字!");
//            return false;
//        }else{
//
//            $.ajax({
//                url: _platform + '/meterCollect/check/formula',
//                type: 'POST',
//                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
//                data: {formula:value},
//                dataType: 'json',
//                success: function (data) {
//                    console.log(data);
//                    if (data.result) {
//                        return false;
//                    } else {
//                        top.layer.msg("ok!");
//                        deferred.resolve();
//                        return true;
//                    }
//                }
//            });
//        }
//    }, "请输入正确的公式");
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
    // 长度数值校验
    $.validator.addMethod("isDouble", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        var reg = new RegExp("^[0-9]+(.[0-9]{1,6})?$");
        if(!reg.test(value)){
            //top.layer.msg("请输入数字!");
            return false;
        }else{
            return true;
        }
    }, "请确认输入的数值为整数或小数(精确到6位小数：如:0.000001)");
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
                minlength: 2,
                checkName: true
            },
            code: {
                required: true,
                minlength: 2,
                checkCode: true
            },
            serialNo: {
                required: true,
                checkSerialNo:true
            },
            energyTypeId: {
                required: true
            },
            isreal: {
                required: true
            },
            istotal: {
                required:true
            },
            isauto: {
                required:true
            },
            unitType: {
                required:true
            },
            coef: {
                required:true,
                isDouble:true
            },
            tag: {
                required:true
            },
            isprestore: {
                required:true
            }
            ,
            isdelete: {
                required:true
            },
            formula: {
                required:true
            }
        },
        messages: {
            name: {
                required: icon + "请输入计量名称",
                minlength: icon + "计量名称必须2个字符以上"
            },
            code: {
                required: icon + "请输入计量代码"
            },
            energyTypeId: {
                required: icon + "请输入能源类型"
            },
            isreal: {
                required: icon + "请输入实虚表"
            },
            istotal: {
                required: icon + "请输入总表"
            },
            isauto: {
                required: icon + "请输入采集"
            },
            unitType: {
                required: icon + "请输入用能单位类型"
            },
            coef: {
                required: icon + "请输入系数"
            },
            tag: {
                required: icon + "请输入点表"
            },
            isprestore: {
                required: icon + "请输入预存值"
            },
            isdelete: {
                required: icon + "请输入删除标识"
            }
        },
        submitHandler: function () {
            var index = top.layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
           // alert($form.serialize());
            $.ajax({
                url: _platform + '/meterCollect/addvalue',
                data: $form.serialize(),
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        top.layer.closeAll();
                        top.layer.msg(result.msg);
                        $('#meter-table-list').bootstrapTable("refresh");
                    } else {
                        top.layer.close(index);
                        top.layer.msg(result.msg);
                    }
                }
            });
        }
    });

});

function getType(type){
    $.ajax({
        url: _platform + '/meterCollect/unit',
        type: 'POST',
        dataType: 'json',
        data:{unitType:type,comId:$(top.document).find(".chosen-select").find("option:selected").val()},
        success: function (result) {
            var $elment=$(top.document).find(".chosen-select-unit");
            $elment.empty();
            var data = result.list;
            var optionHtml='';
            for(var i =0;i<data.length;i++){
                optionHtml+='<option value="'+data[i].unitId+'">'+data[i].unitName+'</option>'
            }
            if(optionHtml==null||optionHtml==""){
                top.layer.msg("该类型下没有供热单位");
                $elment.empty();
            }else{
                $elment.append(optionHtml);
            }
            //下拉框js
            $elment.chosen("destroy").chosen();
        }
    });
}

</script>