<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" id="menuEditForm" role="form">
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单属性：</label>
                    <div class="col-sm-8">
                        <select name="menuType" class="chosen-select" style="width:360px">
                            <option <c:if test="${menu.menuType eq 0}">selected="selected" </c:if>  value="0">前台</option>
                            <option <c:if test="${menu.menuType eq 1}">selected="selected" </c:if>  value="1">后台</option>
                        </select>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单名称：</label>
                    <div class="col-sm-8">
                        <input id="menuName" name="menuName" value="${menu.menuName}" class="form-control" type="text" maxlength="8" placeholder="请输入菜单名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单路径：</label>
                    <div class="col-sm-8">
                        <input id="menuUrl" name="menuUrl"  value="${menu.menuUrl}" class="form-control" type="text" maxlength="128" placeholder="请输入菜单路径">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单类型：</label>
                    <div class="col-sm-8">
                        <select name="type" class="chosen-select" style="width:360px">
                            <option <c:if test="${menu.type eq 0}">selected="selected" </c:if>  value="0">模块</option>
                            <option <c:if test="${menu.type eq 1}">selected="selected" </c:if>  value="1">菜单</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label">菜单图标：</label>
                    <div class="col-sm-8">
                        <input id="menuIcon" name="menuIcon" value="${menu.menuIcon}" class="form-control" type="text" maxlength="128" placeholder="请输入菜单图标">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>菜单序号：</label>
                    <div class="col-sm-8">
                        <input id="seq" name="seq" value="${menu.seq}" class="form-control" type="text" maxlength="8" placeholder="请输入菜单名称">
                    </div>
                </div>
                    <input id="pMenuId" name="pMenuId" value="${menu.pMenuId}" hidden="hidden"/>
                    <input id="id" name="id" value="${menu.id}" hidden="hidden"/>
            </form>
        </div>
    </div>
</div>
<script>


    $.validator.setDefaults({
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
            } else {
                error.appendTo(element.parent());
            }
        },
        errorClass: "help-block m-b-none m-t-xs",
        validClass: "help-block m-b-none m-t-none"
    });


    //以下为官方示例
    $(function () {
        var $form =  $(top.document).find("#menuEditForm");
        $(top.document).find(".chosen-select:not([name='searchComp'])").chosen();
        $.validator.addMethod("checkUnique", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            var pMenuId = $(top.document).find("#pMenuId").val();
            if("${menu.menuName}" == $(top.document).find("#menuName").val()){
                //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
                deferred.resolve();
            }else{
                $.ajax({
                    url:_platform+'/menu/check',
                    type:'POST',
                    async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                    data: {menuName:$(top.document).find('#menuName').val(),pMenuId:pMenuId},
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
            return deferred.state() == "resolved" ? true : false;
        }, "菜单名称已存在");

        $.validator.addMethod("checkSeq", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            var pMenuId = $(top.document).find("#pMenuId").val();
            if("${menu.seq}" == value){
                deferred.resolve();
            }else{
                $.ajax({
                    url:_platform+'/menu/check',
                    type:'POST',
                    async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                    data: {seq:value,pMenuId:pMenuId},
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
            return deferred.state() == "resolved" ? true : false;
        }, "菜单序号已存在");


        //提示信息绑定
        $('input:not(:submit):not(:button)').mousedown(function () {
            $(this).closest('.form-group').removeClass('has-error');
            $(this).siblings('.help-block').remove();
        });
        //下拉框信息绑定
        $('select').change(function () {
            if($(this).find('option:first').val()!=$(this).val()){
                $(this).siblings('.help-block').remove();
            }
            return false;
        });
        var icon = "<i class='fa fa-times-circle'></i> ";
        $form.validate({
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
                menuName: {
                    required: true,
                    minlength: 2,
                    checkUnique: true
                },
                menuUrl: {
                    required: true
                },
                type:{
                    required: true
                },
                seq:{
                    checkSeq: true,
                    required: true,
                    digits: true
                }
            },
            messages: {
                menuName: {
                    required: icon + "请输入菜单名称",
                    minlength: icon + "菜单名称必须2个字符以上"
                },
                menuUrl: {
                    required: icon + "请输入菜单路径"
                },
                type:{
                    required: icon + "请选择菜单类型"
                },
                seq:{
                    required: icon + "请填写菜单序号",
                    digits: icon + "菜单序号必须为正整数！"
                }
            },
            submitHandler:function(){
                var index = top.layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url:_platform + '/menu/edit',
                    data:$form.serialize(),
                    type:'POST',
                    dataType:'json',
                    success:function(result) {
                        if(result.flag){
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            refreshParentNode();
                        }else{
                            top.layer.close(index);
                            top.layer.msg(result.msg);
                        }
                    }
                });
            }
        });

    });
    function refreshParentNode() {
       var treeObj = $.fn.zTree.getZTreeObj("menuTree");
       var type = "refresh";
       var silent = false;
       var nodes = treeObj.getSelectedNodes();
        /*根据 zTree 的唯一标识 tId 快速获取节点 JSON 数据对象*/
        var parentNode = treeObj.getNodeByTId(nodes[0].parentTId);
        /*选中指定节点*/
        treeObj.selectNode(parentNode);
        treeObj.reAsyncChildNodes(parentNode, type, silent);

    }
</script>