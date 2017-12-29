<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="energyProjectEditForm" role="form">
               <input id="id" type="hidden" name="id" value="${energyProject.id }">
               <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>组织机构：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                    	<ul id="org" light="${energyProject.orgId }" class="energyProject-add-org-tree" style="height: 200px;overflow-y:scroll;border: 1px solid #E5E6E7;"></ul>
	                    	<input value="${energyProject.orgId }" type="text" class="form-control" name="orgId" id="orgId" style="visibility: hidden;height: 0px;">
	                    </div>
		            </div>
                </div>
               <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>采暖季：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                    	<select id="seasonId" name="seasonId" class="form-control m-b" ></select>
	                    </div>
		            </div>
                </div>
               <div class="row">
	                <div class="form-group" style="width:100%;float: left;margin-right: 0px;">
	                    <label class="col-sm-4 col-xs-4 col-md-4 col-lg-4 control-label"><span class="red">*</span>能耗计划：</label>
	                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
	                    	<input value="${energyProject.num }" id="num" name="num" class="form-control m-b"  />
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
        var nodes = epEditTree.getSelectedNodes();
    	var selectedNode = nodes[0];
    	top.$('#orgId').val(selectedNode.id);//选择组织机构节点的时候保存所选节点的组织Id
    	top.$('#orgId-error').remove();//如果没选择组织结构点击保存会出现 错误提示 ，这样可以在选择节点后消除 错误提示
    	top.$('#orgId').closest('.form-group').removeClass('has-error').addClass('has-success');
    	top.$('#num"]').focus();
		top.$('#num').blur();
    }
    var epEditTree;
    $(function () {
    	
    	top.$('#seasonId').html("${seasonHtml}");
    	
    	//初始化组织机构树
    	epEditOrg = new Org({
            class:"energyProject-add-org-tree"
        });
    	epEditTree = epEditOrg.initTree();

    	top.$('#seasonId').on('change',function(){
    		top.$('#num').focus();
    		top.$('#num').blur();
    	});
    	
    	//获取表单元素
     	var $form = $(top.document).find("#energyProjectEditForm");
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
                orgId: {
                    required: true
                },
                seasonId: {
                    required: true
                },
                num: {
                    required: true,
                    isNum:true,
                    hasUnique:true
                }
            },
            messages: {
                orgId: {
                    required: icon + "请选择所属组织结构"
                },
                seasonId: {
                    required: icon + "请选择所属采暖季"
                },
                num: {
                    required: icon + "请输入能耗计划"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });
                $.ajax({
                    url: _platform + '/energy/project/edit',
                    data: $form.serialize(),
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            $('#energyProject-table-list').bootstrapTable("refresh");
                        } else {
                            top.layer.close(index);
                            top.layer.msg(result.msg);
                        }
                    }
                });
            }
        });
    	
        $.validator.addMethod("isNum", function(value, element){
    		var num = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
    	    return this.optional(element) || (num.test(value));
    	},icon +  "请输入正确的数字");
        
        $.validator.addMethod("hasUnique", function(value, element) {
        	if(top.$('#orgId').val()=='${energyProject.orgId}'
        			&&top.$('#seasonId').val()=='${energyProject.seasonId}') return true;
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url:_platform+'/energy/project/hasUnique',
                type:'POST',
                async:false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {
                		orgId:top.$('#orgId').val(),
                		seasonId:top.$('#seasonId').val()
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
        }, icon + "所属组织机构和采暖季存在能耗计划！");
    	
    });
</script>