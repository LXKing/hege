<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>热源管理</title>
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
            error.insertAfter(element.parent().parent().parent());
        } else if(element.is("select")){
            error.insertAfter(element.parent());
        }else{
            error.insertAfter(element.parent());
        }
    },
    errorClass: "help-block m-b-none m-t-xs",
    validClass: "help-block m-b-none m-t-none"
});





//以下为官方示例
$(function () {
    var icon = "<i class='fa fa-times-circle'></i> ";
    var $form = $(top.document).find("#feed_edit_Form");
    new PCAS('province','${province}','${object.provinceId}','city','${city}','${object.cityId}','county','${county}','${object.countyId}','town','${town}','${object.townId}');
    var config = {
        '.chosen-select': {},
        '.chosen-select-deselect': {
            allow_single_deselect: true
        },
        '.chosen-select-no-single': {
            disable_search_threshold: 10
        },
        '.chosen-select-no-results': {
            no_results_text: 'Oops, nothing found!'
        },
        '.chosen-select-width': {
            width: "10%"
        }
    }
    $(top.document).find(".chosen-select:not([name='searchComp'])").chosen();
    $.validator.addMethod("feedCodeUnique", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        var newcode = $(top.document).find("#feedCode").val();
        var oldcode = $(top.document).find("#oldCode").val();
        if(oldcode == newcode){
            deferred.resolve();
        }else{
            $.ajax({
                url:_platform+'/feed/check',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {feedCode:newcode},
                dataType: 'json',
                success:function(result) {
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
    }, icon + "热源编码已存在");

    $.validator.addMethod("feedNameUnique", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        var oldName = $(top.document).find("#oldName").val();
        var feedName = $(top.document).find("#feedName").val();
        if(oldName == feedName){
            deferred.resolve();
        }else{
            $.ajax({
                url:_platform+'/feed/check',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {feedName:feedName},
                dataType: 'json',
                success:function(result) {
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
    }, icon + "热源名称已存在");

    $.validator.addMethod("checklng", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        if(!(value =="" ||value == null)){
            if(!(/^-?(?:(?:180(?:\.0{1,5})?)|(?:(?:(?:1[0-7]\d)|(?:[1-9]?\d))(?:\.\d{1,5})?))$/.test(value))){
                deferred.reject();
            }else{
                deferred.resolve();
            }
        }else{
            deferred.resolve();
        }
        return deferred.state() == "resolved" ? true : false;
    }, icon + "请填写正确的经度");

    $.validator.addMethod("checklat", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        if(!(value =="" ||value == null)) {
            if (!(/^-?(?:90(?:\.0{1,5})?|(?:[1-8]?\d(?:\.\d{1,5})?))$/.test(value))) {
                deferred.reject();
            } else {
                deferred.resolve();
            }
        }else{
            deferred.resolve();
        }
        return deferred.state() == "resolved" ? true : false;
    }, icon + "请填写正确的纬度");


    $form.validate({
        onsubmit: true,// 是否在提交是验证
        //移开光标:如果有内容,则进行验证
        onfocusout: function (element) {
            if ($(element).is(":radio")){
                return;
            }
            if ($(element).val() == null || $(element).val() == "") {
                $(element).closest('.form-group').removeClass('has-error');

                if($(element).parent(".chosen-search").length==1){
                    $(element).parents(".chosen-container").parent().siblings('.help-block').remove();
                }else{
                    $(element).parent().siblings('.help-block').remove();
                }
            } else {
                $(element).valid();
            }
        },
        onkeyup: false,// 是否在敲击键盘时验证
        onclick:false,
        rules: {
            orgId: {
                required: true
            },
            feedCode: {
                required: true,
                feedCodeUnique: true
            },
            feedName: {
                required: true,
                feedNameUnique:true
            },
            shortName: {
                required: true
            },
            heatType: {
                required: true
            },
            feedType: {
                required: true
            },
            installCapacity: {
                number: true
            },
            heatCapacity: {
                number: true
            },
            steamturbineNum: {
                number: true
            },
            boilerNum: {
                number:  true
            },
            addr: {
                required: true
            },
            provinceId:{
                required: true
            },
            cityId:{
                required: true
            },
            lng:{
                checklng: true
            },
            lat:{
                checklat: true
            },
            heatArea:{
                required: true,
                number:  true
            }

        },
        messages: {
            admCode: {
                required: icon + "请输入区划编码",
                minlength: icon + "区划编码必须在11-12个字符",
                maxlength: icon + "区划编码长度不能超过12个字符"
            },
            orgId: {
                required: icon + "请选择组织机构"
            },
            feedCode: {
                required: icon + "请填写热源编码"
            },
            feedName: {
                required: icon + "请填写热源名称"
            },
            shortName: {
                required: icon + "请填写简称"
            },
            heatType: {
                required: icon + "请选择供热类型"
            },
            feedType: {
                required: icon + "请选择热源性质"
            },
            installCapacity: {
                number: icon + "请输入正确的数字"
            },
            heatCapacity: {
                number:  icon + "请输入正确的数字"
            },
            steamturbineNum: {
                number: icon + "请输入正确的数字"
            },
            boilerNum: {
                number:  icon + "请输入正确的数字"
            },
            addr: {
                required: icon + "请填写详细地址"
            },
            provinceId:{
                required: icon + "请选择省份"
            },
            cityId:{
                required: icon + "请选择城市"
            },
            heatArea: {
                required: icon + "请填写供热面积",
                number:  icon + "请输入正确的数字"
            }
        },
        submitHandler: function () {
            var index = top.layer.load(1, {
                shade: [0.1,'#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url:_platform + '/feed/edit',
                data:$form.serialize(),
                type:'POST',
                dataType:'json',
                success: function (result) {
                    if (result.flag) {
                        top.layer.closeAll();
                        top.layer.msg(result.msg);
                        $('#feed-table-list').bootstrapTable("refresh");
                    } else {
                        top.layer.msg(result.msg);
                    }
                },
                error:function(){
                    top.layer.msg("请求服务器失败！");
                }
            });
        }
    });

});
</script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                    <form class="form-horizontal" id="feed_edit_Form" role="form">
                        <input type="hidden" name="id" value="${object.id}"/>
                        <input type="hidden" name="" value="${object.status}"/>
                        <input type="hidden" name="orgId" value="${object.orgId}"/>
                        <input type="hidden" name="comId" value="${object.comId}"/>
                        <input id="oldCode"  value="${object.feedCode}" class="form-control" type="hidden" >
                        <input id="oldName"  value="${object.feedName}" class="form-control" type="hidden" >
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>热源编码：</label>
                            <div class="col-sm-4">
                                <input name="feedCode" id="feedCode" value="${object.feedCode}" class="form-control" type="text" maxlength="20" placeholder="请输入热源代码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>热源名称：</label>
                            <div class="col-sm-4">
                                <input name="feedName" id="feedName" class="form-control" value="${object.feedName}" type="text" maxlength="64" placeholder="请输入热源名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="td">
                                <label class="col-md-2  control-label"><span class="red">*</span>供热类型：</label>
                                <div class="col-sm-4">
                                    <select id="heatType" name="heatType" class="chosen-select form-control" onchange="addMessage()" >
                                        <option value="">请选择供热类型</option>
                                        <c:forEach items="${sysDic['supportheattype']}" var="type">
                                            <option <c:if test="${object.heatType eq type.seq}">selected="selected" </c:if> value="${type.seq}">${type.des}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="td">
                                <label class="col-md-2  control-label"><span class="red">*</span>热源性质：</label>
                                <div class="col-sm-4">
                                    <select id="feedType" name="feedType" class="chosen-select form-control" onchange="addMessage()">
                                        <option value="">请选择热源性质</option>
                                        <c:forEach items="${sysDic['natureheat']}" var="types">
                                            <option <c:if test="${object.feedType eq types.seq}">selected="selected" </c:if> value="${types.seq}">${types.des}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">装机容量：</label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="installCapacity" class="form-control" value="${object.installCapacity}" type="text" maxlength="16"
                                           placeholder="请输入装机容量">
                                    <span class="input-group-addon">MW</span>
                                </div>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">锅炉数量：</label>
                            <div class="col-sm-4">
                                <input name="boilerNum" class="form-control" value="${object.boilerNum}" type="text" maxlength="64" placeholder="请输入锅炉数量">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">汽机数量：</label>
                            <div class="col-sm-4">
                                <input name="steamturbineNum" class="form-control" value="${object.steamturbineNum}" type="text" maxlength="64" placeholder="请输入汽机数量">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">供热能力：</label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input name="heatCapacity" class="form-control" type="text" maxlength="16" value="${object.heatCapacity}"
                                           placeholder="请输入供热能力">
                                    <span class="input-group-addon">m²</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="td">
                                <label class="col-md-2  control-label">所属管网：</label>
                                <div class="col-sm-4">
                                    <select id="netId" name="netId" class="chosen-select form-control"  >
                                        <option value="">请选择管网</option>
                                        <c:forEach items="${oncenet}" var="net">
                                            <option <c:if test="${object.netId eq net.id}">selected="selected" </c:if> value="${net.id}">${net.netName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="td">
                                <label class="col-md-2  control-label"><span class="red">*</span>所属省：</label>
                                <div class="col-sm-4">
                                    <select id="province" name="provinceId" class="chosen-select form-control" >
                                        <option value="">请选择省份</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="td">
                                <label class="col-sm-2  control-label"><span class="red">*</span>所属市：</label>
                                <div class="col-sm-4">
                                    <select id="city" name="cityId" class="chosen-select form-control" >
                                        <option value="">请选择市</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="td">
                                <label class="col-md-2  control-label">所属县：</label>
                                <div class="col-sm-4">
                                    <select id="county" name="countyId" class="chosen-select form-control" >
                                        <option value="">请选择县</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="td">
                                <label class="col-sm-2  control-label">所属镇(乡)：</label>
                                <div class="col-sm-4">
                                    <select id="town" name="townId" class="chosen-select form-control" >
                                        <option value="">请选择镇(乡)</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>详细地址：</label>
                            <div class="col-sm-4">
                                <input name="addr" class="form-control" type="text" maxlength="64" value="${object.addr}" placeholder="请输入区划代码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">经度：</label>
                            <div class="col-sm-4">
                                <input name="lng"  class="form-control" value="${object.lng}" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">纬度：</label>
                            <div class="col-sm-4">
                                <input name="lat" class="form-control" value="${object.lat}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>供热面积：</label>
                            <div class="col-sm-4">
                                <input name="heatArea" class="form-control" value="${object.heatArea}">
                            </div>
                        </div>
                    </form>
            </div>
        </div>
    </div>
</div>
</body>
