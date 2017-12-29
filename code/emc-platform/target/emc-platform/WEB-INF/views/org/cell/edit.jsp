<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="cellEditForm" role="form">
            	<input id="id" name="id" value="${cell.id }" type="hidden" />
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>单元名称：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <input name="cellName" value="${cell.cellName }" class="form-control" type="text" maxlength="16" placeholder="请输入单元名称">
	                    </div>
	                </div>
                </div>
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                   <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>所属公司：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select disabled="disabled" onchange="getCommunitySelectHtml()" value="${cell.comId }" id="comId" name="comId" class="form-control m-b" >
                            </select>
	                    </div>
	                </div>
                </div>
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>组织机构：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                    	<ul id="org" light="${cell.orgId }" value="${cell.orgId }" class="cell-edit-org-tree" style="height: 200px;overflow-y:scroll;border: 1px solid #E5E6E7;"></ul>
	                    	<input type="text" value="${cell.orgId }" class="form-control" name="orgId" id="orgId" style="visibility: hidden;height: 0px;">
	                    </div>
		            </div>
                </div>
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>所属小区：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select onclick="editCommunity()" onchange="getSelectHtml()" id="communityId" name="communityId" class="form-control m-b" ></select>
	                    </div>
	                </div>
                </div>
                <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>所属楼座：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                        <select onclick="editBan()" id="banId" name="banId" class="form-control m-b" ></select>
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
//小区下拉框点击事件
top.editCommunity = function(){
	var orgId = top.$("#orgId").val();
	var comId = top.$("#comId").val();
	if(orgId==null||orgId==''||comId==null||comId==''){
		top.layer.msg('请先选择公司和组织结构！');
		return;
	}
}
//楼座下拉框点击事件
top.editBan = function(){
	var orgId = top.$("#orgId").val();
	var comId = top.$("#comId").val();
	var communityId = top.$("#communityId").val();
	if(orgId==null||orgId==''||comId==null||comId==''||communityId==null||communityId==''){
		top.layer.msg('请先选择公司、组织结构和小区！');
		return;
	}
}
//获取小区下拉框html文
top.getCommunitySelectHtml = function(){
	top.$("#communityId").html('');
	var orgId = top.$("#orgId").val();
	var comId = top.$("#comId").val();
	if(orgId==null||orgId==''||comId==null||comId==''){
		top.getSelectHtml();return;
	}
	$.get(_platform + '/ban/communitySelectHtmlStr',{
		orgId:orgId,
		comId:comId
	},function(data){
		top.$("#communityId").html(data.html);
		top.getSelectHtml();
	},'json');
}
//获取楼座html文
top.getSelectHtml = function(){
	top.$("#banId").html('');
	var orgId = top.$("#orgId").val();
	var comId = top.$("#comId").val();
	var communityId = top.$("#communityId").val();
	if(orgId==null||orgId==''||comId==null||comId==''||communityId==null||communityId=='') return;
	$.get(_platform + '/cell/banSelectHtmlStr',{
		orgId:orgId,
		comId:comId,
		communityId:communityId
	},function(data){
		top.$("#banId").html(data.html);
	},'json');
}

//点击组织机构树
function treeNodeClick(){
//     var treeObj = $.fn.zTree.getZTreeObj("temp_org_tree");
    var nodes = cellEditTree.getSelectedNodes();
	var selectedNode = nodes[0];
	top.$('#orgId').val(selectedNode.id);//选择组织机构节点的时候保存所选节点的组织Id
	top.$('#orgId-error').remove();//如果没选择组织结构点击保存会出现 错误提示 ，这样可以在选择节点后消除 错误提示
	top.$('#orgId').closest('.form-group').removeClass('has-error').addClass('has-success');
	top.getCommunitySelectHtml();//选择节点后更新此节点相关联的小区信息
}

var cellEditTree;
$(function () {
	//初始化公司下拉框
	top.$('#comId').html('${com}');
	top.$('#communityId').html('${community}');//初始化小区下拉框
	top.$('#banId').html('${ban}');//初始化楼座下拉框
	
	//初始化组织机构树
	cellEditOrg = new Org({
        class:"cell-edit-org-tree"
    });
	cellEditTree = cellEditOrg.initTree();
	
	top.$('#banId').on('change',function(){
		top.$('input[name="cellName"]').focus();
		top.$('input[name="cellName"]').blur();
	});
	
	//获取表单元素
 	var $form = $(top.document).find("#cellEditForm");
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
        	cellName: {
                required: true,
                isName: true,
                cellNameUnique:true,
                minlength: 2
            },
            comId: {
                required: true
            },
            orgId: {
                required: true
            },
            communityId: {
                required: true  
            },
            banId: {
                required: true
            }
        },
        messages: {
        	cellName: {
                required: icon + "请输入单元名称",
                minlength: icon + "单元名称必须2个字符以上"
            },
            comId: {
                required: icon + "请选择所属公司"
            },
            orgId: {
                required: icon + "请选择所属组织结构"
            },
            communityId:{
            	required: icon + "请选择所属小区"
            },
            banId:{
            	required: icon + "请选择所属楼座"
            }
        },
        submitHandler: function () {
            var index = top.layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: _platform + '/cell/edit',
                data: $form.serialize(),
                type: 'POST',
                dataType: 'json',
                success: function (result) {
                    if (result.flag) {
                        top.layer.closeAll();
                        top.layer.msg(result.msg);
                        $('#cell-table-list').bootstrapTable("refresh");
                    } else {
                        top.layer.close(index);
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
    
    $.validator.addMethod("cellNameUnique", function(value, element) {
    	if(value=='${cell.cellName}'
			&&top.$('#communityId').val()=='${cell.communityId}'
				&&top.$('#banId').val()=='${cell.banId}') return true;
        var deferred = $.Deferred();//创建一个延迟对象
        $.ajax({
            url:_platform+'/cell/check/cellName',
            type:'POST',
            async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
            data: {cellName:top.$('input[name="cellName"]').val(),
            	communityId:top.$('#communityId').val(),
            	banId:top.$('#banId').val()
            	},
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
    }, icon + "所属楼座已存在此单元名称");
	
});
</script>