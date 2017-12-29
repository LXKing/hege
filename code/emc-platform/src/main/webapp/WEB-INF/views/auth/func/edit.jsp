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

            <form class="form-horizontal" id="funcEditForm" role="form"  >
                <input type="hidden" name="_method" value="PUT">
                <input type="hidden" name="id" value="${func.id}">
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label">所属菜单：</label>

                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                        <input name="pMenuName" class="form-control" value="${menu.menuName}" type="text" disabled>
                        <input type="hidden" name="menuId" value="${menu.id}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3 control-label"><span class="red">*</span>功能名称：</label>

                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                        <input name="funcName" class="form-control" type="text" value="${func.funcName}" maxlength="16" placeholder="请输入功能名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3 control-label"><span class="red">*</span>唯一标识：</label>

                    <div class="col-sm-8  col-xs-8 col-md-8 col-lg-8">
                        <input name="uname" class="form-control" value="${func.uname}" type="text" maxlength="32" placeholder="请输入唯一标识">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span class="red">*</span>是否查询：</label>

                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                        <select name="issearch" class="chosen-select form-control">
                            <option value="1" ${func.issearch eq 1?'selected':''}>否</option>
                            <option value="0" ${func.issearch eq 0?'selected':''}>是</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  col-xs-3 col-md-3 col-lg-3 control-label"><span class="red">*</span>排序：</label>

                    <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                        <input name="seq" class="form-control" value="${func.seq}" type="text" maxlength="3" placeholder="请输入排序值,查询为1">
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

        var $form = $(top.document).find("#funcEditForm");
        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";
        $.validator.addMethod("checkFuncName", function(value, element) {
            if(value =='${func.funcName}'){
                return true;
            }
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_platform + '/func/check/name',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {funcName:value,menuId:'${menu.id}'},
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
        }, '菜单[${menu.menuName}]下功能名称已存在');

        $.validator.addMethod("checkUnique", function(value, element) {
            if(value =='${func.uname}'){
                return true;
            }
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_platform+'/func/check/uname',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {uname:value},
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
        }, "唯一标识已存在");

        $.validator.addMethod("checkFuncSearch", function(value, element) {
            if(value =='${func.issearch}'){
                return true;
            }
            if(1==$(element).val()){
                return true;
            }
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_platform+'/func/check/search',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {menuId:'${menu.id}'},
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
        }, '每个菜单下有且只有一个是查询');

        //中文校验
        $.validator.addMethod("isChinaName", function(value, element){
            var tel = /^[^\u0000-\u00FF]+$/;
            return this.optional(element) || (tel.test(value));
        }, icon + "请输入中文");

        // 英文名称验证
        $.validator.addMethod("isEnglishName", function(value, element) {
            var tel = /^[A-Za-z]+$/;
            return this.optional(element) || (tel.test(value));
        }, "请输入英文名称");

        // 排序校验
        $.validator.addMethod("isSeq", function(value, element) {
            if(value =='${func.seq}'){
                return true;
            }
            if(1==$(top.document).find('select[name="issearch"]').val()){
                if(value>1){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(value==1){
                    return true;
                }else{
                    return false;
                }
            }
        }, "查询的排序必须为1,非查询大于1,且为正整数");

        //提示信息绑定
        $(top.document).on('mousedown','input:not(:submit):not(:button)',function(){
            $(this).closest('.form-group').removeClass('has-error');
            $(this).siblings('.help-block').remove();
        });
        //下拉框信息绑定
        //下拉框js
        $(top.document).find(".chosen-select:not([name='searchComp'])").chosen().on('change',function () {
            $(this).siblings('.help-block').remove();
            /*if ($(this).find('option:first').val() != $(this).val()) {
             $(this).siblings('.help-block').remove();
             }*/
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
                funcName: {
                    required: true,
                    minlength: 2,
                    isChinaName:true,
                    checkFuncName:true
                },
                uname: {
                    required: true,
                    checkUnique:true,
                    minlength: 4,
                    isEnglishName:true
                },
                issearch:{
                    checkFuncSearch:true
                },
                seq: {
                    required: true,
                    isSeq:true
                }
            },
            messages: {
                funcName: {
                    required: icon + "请输入菜单名称",
                    minlength: icon + "功能名称必须2个字符以上"
                },
                uname: {
                    required: icon + "请输入唯一标识",
                    minlength: icon + "唯一标识必须4个字符以上"
                },
                seq: {
                    required: icon + "请输入排序值"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url:_platform + '/func/edit',
                    data:$form.serialize(),
                    type:'POST',
                    dataType:'json',
                    success:function(result) {
                        if(result.flag){
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            $('#func-table-list').bootstrapTable("refresh");
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