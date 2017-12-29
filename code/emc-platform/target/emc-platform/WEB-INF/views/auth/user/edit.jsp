<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="userEditForm" role="form">
	            <div class="row">
	                <div class="form-group" style="width:50%;float: left;margin-right: 0px;">
	                	<input type="hidden" id="id" name="id" value="${user.id }">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>中文名称：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <input name="userName" class="form-control" type="text" maxlength="16" placeholder="请输入用户中文名称" value="${user.userName }">
	                    </div>
	                </div>
	                <div class="form-group" style="width:50%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>联系电话：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <input name="mobile" class="form-control" type="text" maxlength="12" placeholder="请输入用户联系电话" value="${user.mobile }">
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group" style="width:50%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>登录账号：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <input name="login" class="form-control" type="text" maxlength="16" placeholder="请输入用户登录账号" value="${user.login }">
	                    </div>
	                </div>
	                <div class="form-group" style="width:50%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label">电子邮箱：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <input name="mail" class="form-control" type="text" maxlength="16" placeholder="请输入用户电子邮箱" value="${user.mail }">
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group" style="width:50%;float: left;margin-right: 0px;">
	                   <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>登录密码：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <input id="password" name="password" class="form-control" type="text" maxlength="16" placeholder="请输入用户登录密码" value="${user.password }">
	                    </div>
	                </div>
	                <div class="form-group" style="width:50%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>使用状态：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select id="useStatus" name="useStatus" class="form-control">
	                        	<option value="0">启用</option>
	                        	<option value="1">禁用</option>
	                        </select>
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group" style="width:50%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>组织机构：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                    	<ul id="org" class="user-edit-org-tree" light="${user.orgId }" style="height: 200px;overflow-y:scroll;border: 1px solid #E5E6E7;" value="${user.orgId }"></ul>
	                    	<input type="text" value="${user.orgId }" class="form-control" name="orgId" id="orgId" style="visibility: hidden;height: 0px;">
	                    </div>
		            </div>
	                <div class="form-group" style="width:50%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label">所属员工：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select id="empId" name="empId" class="form-control" multiple="" style="height: 200px;overflow-y:scroll;">
	                        </select>
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group" style="margin-right: 0px;">
	                    <label class="col-sm-2 col-xs-2 col-md-2 col-lg-2 control-label">备注说明：</label>
	                    <div style="width: 74%;float: left;margin-left: 13px;"> 
	                        <textarea class="form-control" rows="4" maxlength="125" id="memo" name="memo" placeholder="请输入备注"></textarea>
	                    </div>
	                </div>
	            </div>
            </form>
        </div>
    </div>
</div>
<script>
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

//点击组织机构树
function treeNodeClick(){
//     var treeObj = $.fn.zTree.getZTreeObj("temp_org_tree");
    var nodes = userEditTree.getSelectedNodes();
	var selectedNode = nodes[0];
	top.$('#orgId').val(selectedNode.id);
	top.$('#orgId-error').remove();//如果没选择组织结构点击保存会出现 错误提示 ，这样可以在选择节点后消除 错误提示
	top.$('#orgId').closest('.form-group').removeClass('has-error').addClass('has-success');
	//根据机构id，查询所属此机构的员工
	$.post(_platform + '/user/org/emp',{
		orgId:selectedNode.id
	},function(result){
		top.$('#empId').html('');
		var selectHtmlStr = "";
		if(result!=null&&result!=""){
			for(var i=0;i<result.length;i++){
				var empId = result[i].empId;
				var empName = result[i].empName;
				selectHtmlStr += "<option value='"+empId+"'>"+empName+"</option>";
			}
			top.$('#empId').html(selectHtmlStr);
		}
	},'json');
}
var userEditTree;
$(function () {
	top.$('#memo').text('${user.memo}');//初始化备注
	top.$('#useStatus').val('${user.useStatus}');//初始化使用状态下拉框
	top.$('#empId').html('${emp}');
	//初始化组织机构树
	userEditOrg = new Org({
        class:"user-edit-org-tree"
    });
	userEditTree = userEditOrg.initTree();
	//获取表单元素
 	var $form = $(top.document).find("#userEditForm");
    var icon = "<i class='fa fa-times-circle'></i> ";
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
	//表单验证
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
        	userName: {
                required: true,
                isName: true,
                minlength: 2
            },
            mobile: {
                required: true,
                isPhone: true
            },
            login: {
                required: true,
                isLogin: true,
                loginUnique:true,
                minlength: 2
            },
            password: {
                required: true,
                isPassword: true,
                minlength:6
            },
            useStatus: {
                required: true
            },
            mail:{
                isEmail:true
            },
            orgId:{
            	required: true
            }
        },
        messages: {
        	userName: {
                required: icon + "请输入用户名称",
                minlength: icon + "用户名称必须2个字符以上"
            },
            mobile: {
                required: icon + "请输入手机号码"
            },
            login: {
                required: icon + "请输入登录账号",
                minlength: icon + "登录名称必须2个字符以上"
            },
            password: {
                required: icon + "请输入密码",
                minlength:icon + "密码最少6个字符以上"
            },
            useStatus: {
                required: icon + "请选择使用状态"
            },
            orgId:{
            	required: icon + "请选择组织机构"
            }
        },
        submitHandler: function () {
            var index = top.layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: _platform + '/user/edit',
                data: $form.serialize(),
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        top.layer.closeAll();
                        top.layer.msg(result.msg);
                        $('#user-table-list').bootstrapTable("refresh");
                    } else {
                        layer.close(index);
                        top.layer.msg(result.msg);
                    }
                }
            });
        }
    });
	
    //名称校验
	$.validator.addMethod("isName", function(value, element){
	    var tel = /^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]*$/;
	    return this.optional(element) || (tel.test(value));
	},icon +  "请输入正确的名称，只能是以汉字或字母开头的汉字、字母、数字组合！");
	
	//手机号码校验
	$.validator.addMethod("isPhone", function(value, element){
		var shouji =/^1[34578]\d{9}$/;
	    var zuoji = /^((\d{3,4}\-)|)\d{7,8}(|([-\u8f6c]{1}\d{1,5}))$/;
	    debugger;
	    var result;
	    if(value.length==11&&value.indexOf('-')==-1&&value.indexOf('0')!=0){
	    	result = shouji.test(value);
	    }else{
	    	result = zuoji.test(value);
	    }
	    return this.optional(element) || result;
	}, icon + "请输入正确的手机号码或座机号");
	
	//登录账号校验
	$.validator.addMethod("isLogin", function(value, element){
	    var tel = /^[a-zA-Z][a-zA-Z0-9]*$/;
	    return this.optional(element) || (tel.test(value));
	}, icon + "请输入正确的登录账号，只能是以字母开头的字母、数字组合！");
	
	//密码校验,支持所有数字、英文大小写、英文键盘所有特殊符号
	$.validator.addMethod("isPassword", function(value, element){
	    var tel = /^[\w\-\@\.\\\!\#\$\%\^\&\*\(\)\+\=\`\~\,\/\<\>\?\;\:\'\"\[\]\{\}\|]+$/;
	    return this.optional(element) || (tel.test(value));
	}, icon + "请输入正确的密码,支持所有数字、英文大小写、英文键盘所有特殊符号");
	
	//电子邮箱校验
	$.validator.addMethod("isEmail", function(value, element){
	    var tel = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	    return this.optional(element) || (tel.test(value));
	}, icon + "邮箱格式不正确，请重新输入");
	
	 $.validator.addMethod("loginUnique", function(value, element) {
		 	if(value=='${user.login}') return true;
	        var deferred = $.Deferred();//创建一个延迟对象
	        $.ajax({
	            url:_platform+'/user/check/login',
	            type:'POST',
	            async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
	            data: {login:top.$('input[name="login"]').val()},
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
	    }, icon + "登录账号已存在");

});
</script>