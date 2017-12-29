<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>热力站添加</title>
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
    new PCAS('province','${province}','','city','${city}','','county','${county}','','town','${town}','');
    $(top.document).find(".chosen-select:not([name='searchComp'])").chosen();

    var $stationForm = $(top.document).find("#stationAddForm");
    $.validator.addMethod("nodeCodeUnique", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        var newcode = $(top.document).find("#stationCode").val();
        var comId = $(top.document).find("[name='searchComp']").val();
        $.ajax({
            url:_platform+'/station/check',
            type:'POST',
            async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
            data: {stationCode:newcode,comId:comId},
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
    }, icon + "热力站编码已存在");

    $.validator.addMethod("nodeNameUnique", function(value, element) {
        var deferred = $.Deferred();//创建一个延迟对象
        var stationName =  $(top.document).find("#stationName").val();
        var comId = $(top.document).find("[name='searchComp']").val();
        $.ajax({
            url:_platform+'/station/check',
            type:'POST',
            async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
            data: {stationName:stationName,comId:comId},
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
    }, icon + "热力站名称已存在");

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

    $stationForm.validate({
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
            stationCode: {
                required: true,
                nodeCodeUnique: true
            },
            stationName: {
                required: true,
                nodeNameUnique: true
            },
            shortName: {
                required: true
            },
            manageTypeId: {
                required: true
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
            heatArea:{
                required: true
            },
            heatType:{
                required: true
            },
            lng:{
                checklng: true
            },
            lat:{
                checklat: true
            }
        },
        messages: {
            orgId: {
                required: icon + "请选择组织机构"
            },
            stationCode: {
                required: icon + "请填写热力站编码"
            },
            stationName: {
                required: icon + "请填写热力站名称"
            },
            shortName: {
                required: icon + "请填写简称"
            },
            manageTypeId: {
                required: icon + "请选择管理类型"
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
            heatArea:{
                required: icon + "请填写供热面积"
            },
            heatType:{
                required: icon + "请选择供热类型"
            }
        },
        submitHandler: function () {
            var index = top.layer.load(1, {
                shade: [0.5,'#fff'] //0.1透明度的白色背景
            });

            var net =$stationForm.find("#netId").val();
            var feed =$stationForm.find("#feedId").val();
            if(net == "" && feed ==""){
                top.layer.close(index);
                top.layer.msg("请选择热源或者管网！");
                return false;
            }else if( net !="" && feed !=""){
                top.layer.close(index);
                top.layer.msg("只能选择热源或者管网中的一个！");
                return false;
            }else{
                $.ajax({
                    url:_platform + '/station/add',
                    data:$stationForm.serialize(),
                    type:'post',
                    dataType:'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            $('#station-table-list').bootstrapTable("refresh");
                        } else {
                            top.layer.msg(result.msg);
                            top.layer.close(index);
                        }
                    }
                });
            }
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
                    <form class="form-horizontal" id="stationAddForm" role="form">
                        <input type="hidden" name="orgId" value="${object.orgId}"/>
                        <input type="hidden" name="comId" value="${object.comId}"/>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>热力站编码：</label>
                            <div class="col-sm-5">
                                <input id="stationCode" name="stationCode" class="form-control"  type="text" maxlength="20" placeholder="请输入热力站编码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>热力站名称：</label>
                            <div class="col-sm-5">
                                <input name="stationName" id="stationName" class="form-control"  type="text" maxlength="64" placeholder="请输入热力站名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="td">
                                <label class="col-md-2  control-label"><span class="red">*</span>管理类型：</label>
                                <div class="col-sm-5">
                                    <select id="manageTypeId" name="manageTypeId" class="chosen-select form-control"  >
                                        <option value="">请选择管理类型</option>
                                        <c:forEach items="${sysDic['managetype']}" var="type">
                                            <option <c:if test="${object.manageTypeId eq type.seq}">selected="selected" </c:if> value="${type.seq}">${type.des}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="td">
                                <label class="col-md-2  control-label"><span class="red">*</span>供热类型：</label>
                                <div class="col-sm-4">
                                    <select id="heatType" name="heatType" class="chosen-select form-control"  >
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
                                <label class="col-md-2  control-label">所属管网：</label>
                                <div class="col-sm-5">
                                    <select id="netId" name="netId" class="chosen-select form-control"  >
                                        <option value="">请选择管网</option>
                                        <c:forEach items="${oncenet}" var="net">
                                            <option <c:if test="${object.netId eq net.ID}">selected="selected" </c:if> value="${net.ID}">${net.NET_NAME}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="td">
                                <label class="col-md-2  control-label">所属热源：</label>
                                <div class="col-sm-5">
                                    <select id="feedId" name="feedId" class="chosen-select form-control"  >
                                        <option value="">请选择热源</option>
                                        <c:forEach items="${feed}" var="feed">
                                            <option <c:if test="${object.feedId eq feed.ID}">selected="selected" </c:if> value="${feed.ID}">${feed.FEED_NAME}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label"><span class="red">*</span>供热面积：</label>
                            <div class="col-sm-5">
                                <input name="heatArea" class="form-control" type="text" maxlength="64" placeholder="请输入居民面积">
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
                            <div class="col-sm-5">
                                <input name="addr"  class="form-control" type="text" maxlength="64" placeholder="请输入区划代码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2  control-label">经度：</label>
                            <div class="col-sm-5">
                                <input name="lng"  class="form-control"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2  control-label">纬度：</label>
                            <div class="col-sm-5">
                                <input name="lat"  class="form-control">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<div id="layer-div" ></div>