<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function(){
        $(".chosen-select").chosen();
    });
</script>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal" id="employee_add_Form" role="form">
                <input id="orgId" name="orgId" value="${object.orgId}" hidden="hidden"/>
                
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>员工名称：</label>
                    <div class="col-sm-8">
                        <input id="empName" name="empName" class="form-control" type="text" value="${object.empName}" maxlength="8" placeholder="请输入员工名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>员工工号：</label>
                    <div class="col-sm-8">
                        <input id="jobNum" name="jobNum" class="form-control" value="${object.jobNum}" type="text" maxlength="128" placeholder="请输入员工工号">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label">员工性别：</label>
                    <div class="col-sm-8">
                        <select name="sex" class="chosen-select " style="width:360px" >
                            <option <c:if test="${object.sex eq 0}">selected="selected" </c:if>  value="0">男</option>
                            <option <c:if test="${object.sex eq 1}">selected="selected" </c:if>  value="1">女</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label">所属班长：</label>
                    <div class="col-sm-8">
                        <select id="pId" name="pId" class="chosen-select form-control">
                            <option value="">请选择员工</option>
                            <c:forEach items="${emp}" var="item">
                                <option value="${item.employeeId}">${item.empName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>生日：</label>
                    <div class="col-sm-8">
                        <input id="birthday" type="text" class="laydate-icon  form-control layer-date"  name="birthday"
                               placeholder="请选择生日"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label">员工年龄：</label>
                    <div class="col-sm-8">
                        <input id="age" name="age" class="form-control" type="text"  value="${object.age}" maxlength="128" placeholder="请输入员工年龄">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label">员工电话：</label>
                    <div class="col-sm-8">
                        <input id="tel" name="tel" class="form-control" type="text" value="${object.tel}" maxlength="128" placeholder="请输入员工电话">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>员工手机号：</label>
                    <div class="col-sm-8">
                        <input id="phone" name="phone" class="form-control" type="text" value="${object.phone}" maxlength="128" placeholder="请输入员工手机号">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label">员工邮箱：</label>
                    <div class="col-sm-8">
                        <input id="email" name="email" class="form-control" type="text" value="${object.email}" maxlength="128" placeholder="请输入员工邮箱">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3  control-label">员工简介：</label>
                    <div class="col-sm-8">
                        <input id="memo" name="memo" class="form-control" type="text" value="${object.memo}" maxlength="128" placeholder="请输入员工简介">
                    </div>
                </div>
                <input type="hidden" name="status" value="0"/>
            </form>
        </div>
    </div>
</div>
<script>

    var birthday = {
        elem: '#birthday',
        format: 'YYYY-MM-DD ',
        //min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 ', //最大日期
        istime: true,
        istoday: false
    };

    top.laydate(birthday);
    //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
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
        var $form =  $(top.document).find("#employee_add_Form");
        $(top.document).find(".chosen-select:not([name='searchComp'])").chosen();
        $.validator.addMethod("checkUnique", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_platform+'/employee/check',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {empName:value},
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
        }, "员工名称已存在");
        $.validator.addMethod("isMobile", function(value, element) {
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请正确填写您的手机号码");
        $.validator.addMethod("checkCodeUnique", function(value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_platform+'/employee/check',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {jobNum:value},
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
        }, "员工工号已存在");

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
        // validate signup form on keyup and submit
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
                empName: {
                    required: true,
                    minlength: 2,
                    checkUnique: true
                },
                jobNum: {
                    required: true,
                    checkCodeUnique:true
                },
                birthday:{
                    required: true
                },
                email:{
                    email:true
                },
                age:{
                    digits:true
                },
                phone:{
                    required: true,
                    minlength:11,
                    isMobile:true
                }
            },
            messages: {
                empName: {
                    required: icon + "请输入员工名称",
                    minlength: icon + "员工名称必须2个字符以上"
                },
                jobNum: {
                    required: icon + "请输入员工工号"
                },
                birthday :{
                    required: icon + "请输入出生日期"
                },
                email:{
                    email:icon + "请输入正确的邮箱格式"
                },
                age:{
                    digits:icon + "请输入正确的年龄"
                },
                phone:{
                    required: icon + "请输入员工手机号",
                    minlength:icon + "手机号最少11位",
                    isMobile: icon + "请输入正确的手机号"
                }
            },
            submitHandler:function(){
                var index = top.layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url:_platform + '/employee/add',
                    data:$form.serialize(),
                    type:'post',
                    dataType:'json',
                    success:function(result) {
                        if(result.flag){
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            $('#employee-table-list').bootstrapTable("refresh");
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
      var treeNode = top.treeNode;
        var treeObj = $.fn.zTree.getZTreeObj("employeeTree");
        type = "refresh";
        silent = false;
        /*根据 zTree 的唯一标识 tId 快速获取节点 JSON 数据对象*/
        /*选中指定节点*/
        treeNode.isParent = true;
        treeObj.selectNode(treeNode);
        treeObj.reAsyncChildNodes(treeNode, type, silent);
    }
</script>