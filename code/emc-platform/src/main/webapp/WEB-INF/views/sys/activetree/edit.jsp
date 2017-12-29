<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        var icon = "<i class='fa fa-times-circle'></i> ";
        $.validator.addMethod("checkUnique", function(value, element) {
            var admCode = $(top.document).find('#admCode').val();
            var deferred = $.Deferred();
            if("${adm.admCode}" == $(top.document).find("#admCode").val()){
                //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
                deferred.resolve();
            }else {
                $.ajax({
                    url: _platform + '/active/checkcode',
                    type: 'POST',
                    async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                    data: {admCode: admCode},
                    dataType: 'json',
                    success: function (result) {
                        //alert(result.flag);
                        //return false;
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
        }, "行政代码已存在");

        $.validator.addMethod("checkName", function(value, element) {
            var admName = $(top.document).find('#admName').val();
            var deferred = $.Deferred();
            if("${adm.admName}" == $(top.document).find("#admName").val()){
                //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
                deferred.resolve();
            }else {
                $.ajax({
                    url: _platform + '/active/check',
                    type: 'POST',
                    async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                    data: {admName: admName},
                    dataType: 'json',
                    success: function (result) {
                        //alert(result.flag);
                        //return false;
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
        }, "行政名称已存在");
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
        // validate signup form on keyup and submit
        // 行政代码长度数值校验
        $.validator.addMethod("isNumber", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            var reg = new RegExp("^[0-9]*$");
            if(!reg.test(value)){
                //top.layer.msg("请输入数字!");
                return false;
            }else{
                return true;
            }
        }, "请确认输入的数值为正整数且为12位(如：110101001001)");
        //校验汉字
        $.validator.addMethod("isChnises", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            var reg = new RegExp("^[\u0391-\uFFE5]+$");
            if(!reg.test(value)){
                return false;
            }else{
                return true;
            }
        }, "请确认输入为汉字");
        // 经纬度数值校验
        $.validator.addMethod("isLng", function(value, element) {
            var reg = new RegExp("^[0-9]+(.[0-9]{1,6})?$");
            if(!reg.test(value)){
                //top.layer.msg("请输入数字!");
                return false;
            }else{
                return true;
            }
        }, "请确认输入的数值为小数(精确到6位小数：如:0.000001)");
        $(top.document).find("#activeTreeAddForm").validate({
            onsubmit:true,// 是否在提交是验证
            //移开光标:如果有内容,则进行验证
            onfocusout: function (element) {
                if ($(element).val()==null||$(element).val()=="") {
                    $(element).closest('.form-group').removeClass('has-error');
                    $(element).siblings('.help-block').remove();
                }else{
                    $(element).valid();
                }
            },
            onkeyup :false,// 是否在敲击键盘时验证
            rules: {
                admName: {
                    required: true,
                    checkName : true,
                    isChnises:true
                },admCode:{
                    required:true,
                    minlength:12,
                    isNumber:true,
                    checkUnique: true
                },admLevel:{
                    required:true
                },lng:{
                    isLng:true
                },lat:{
                    isLng:true
                }
            },
            messages: {
                    admCode:{
                        required: icon + "请选择行政代码"
                    },admName: {
                    required: icon + "请输入区划名称"
                    },
                        admLevel:{
                    required: icon + "请选择行政级别"
                    }
            },
            submitHandler:function(){

                var index = top.layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                var data = $(top.document).find('#activeTreeAddForm').serialize();
                //alert(data);
                $.ajax({
                    url:_platform + '/active/tree/edit',
                    data:$(top.document).find('#activeTreeAddForm').serialize(),
                    type:'post',
                    dataType:'json',
                    success:function(result) {
                        if(result.flag){
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            ztreeValueAdm();
                        }else{
                            top.layer.close(index);
                            top.layer.msg(result.msg);
                        }
                    }
                });
            }
        });
    });
</script>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" id="activeTreeAddForm" role="form">
                <input type="hidden" name="pCode" value="${id}">
                <input id="comId" name="comId" type="hidden" class="class-comid" value="">


                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>行政代码：</label>
                    <div class="col-sm-8">
                        <input id="admCode" name="admCode" value="${adm.admCode}" class="form-control orgNameID" type="text" maxlength="12" placeholder="请输入行政代码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>区划名称：</label>
                    <div class="col-sm-8">
                        <input id="admName" name="admName" value="${adm.admName}" class="form-control orgNameID" type="text" maxlength="18" placeholder="请输入行政区划名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>行政级别：</label>

                    <div class="col-sm-8">
                        <select id="admLevel" name="admLevel" class="chosen-select form-control">
                            <option value="">请选择级别</option>
                            <c:forEach items="${level}" var="item" varStatus="status" >
                                <%--　　var value = ${item.cname}; //传递过来的是int或float类型，不需要加引号--%>
                                <%--　　var id = "${status.id}";//加引号--%>
                                <option  ${adm.admLevel eq item.key?'selected':''}  value="${item.key}">${item.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red"></span>城乡分类代码：</label>

                    <div class="col-sm-8">
                        <select id="admType" name="admType" class="chosen-select form-control">
                            <option value="">请选择分类</option>
                            <c:forEach items="${admType}" var="item" varStatus="status" >
                                <%--　　var value = ${item.cname}; //传递过来的是int或float类型，不需要加引号--%>
                                <%--　　var id = "${status.id}";//加引号--%>
                                <option  ${adm.admType eq item.seq?'selected':''}  value="${item.seq}">${item.des}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red"></span>经度：</label>
                    <div class="col-sm-8">
                        <input id="lng" name="lng" value="${adm.lng}" class="form-control" type="text" maxlength="12" placeholder="请输入经度">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red"></span>纬度：</label>
                    <div class="col-sm-8">
                        <input id="lat" name="lat" value="${adm.lat}" class="form-control orgNameID" type="text" maxlength="12" placeholder="请输入纬度">
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
