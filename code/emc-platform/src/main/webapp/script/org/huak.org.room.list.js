var roomSearTree;
$(function () {
	roomSearOrg = new Org({
        class:"org-tree"
    });
	roomSearTree = roomSearOrg.initTree();
	initTreeBox();
	parent.$("[name='searchComp']").change(function(){
		window.location.reload(); 
	});
	//户列表
	var roomTable = $('#room-table-list').bootstrapTable({
		height: getHeight() + 30,//高度
        cache: false,//禁用 AJAX 数据缓存
        url: _platform + '/room/list',//获取数据的Servlet地址
        method: 'post',//使用POST请求到服务器获取数据
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        idField: "ID",
        pagination: true,//是否分页
        pageSize: 10,//每页显示的记录数
        pageNumber: 1,//当前第几页
        pageList: [10, 30, 50],//记录数可选列表
        search: false,  //是否启用查询
        striped: true,//表格显示条纹
        //showColumns: false,//不显示隐藏列
        sidePagination: "server", //服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {
            var param = {
                _method: "PATCH",
                pageNumber: params.pageNumber,
                pageSize: params.pageSize
            };
            formsParam(param,'room-form',false);
            return param;
        }, formatLoadingMessage: function () {
            return "请稍等，正在加载中...";
        },
        responseHandler: function (res) {
            return {
                "rows": res.list.list,
                "total": res.list.page.total
            };
        },
        columns: [
            {
                field: 'id', title: 'ID', visible: false
            },
            {
                checkbox: true
            },
            {
                title: '序号',
                field: 'sn',
                align: 'center',
                formatter:function(value,row,index){
                    return index+1;
                }
            },
            {
                title: '户名称',
                field: 'roomName',
                align: 'center'
            },
            {
                title: '户编码',
                field: 'roomCode',
                align: 'center'
            },
            {
                title: '供热面积',
                field: 'heatArea',
                align: 'center'
            },
            {
                title: '管线名称',
                field: 'lineName',
                align: 'center'
            },
            {
                title: '单元名称',
                field: 'cellName',
                align: 'center'
            },
            {
                title: '楼座名称',
                field: 'banName',
                align: 'center'
            },
            {
                title: '小区名称',
                field: 'communityName',
                align: 'center'
            },
            {
                title: '所属机构',
                field: 'orgName',
                align: 'center'
            },
            {
                title: '所属公司',
                field: 'comName',
                align: 'center'
            },
            {
                title: '供热类型',
                field: 'heatName',
                align: 'center'
            },
            {
                title: '操作',
                field: 'opt',
                align: 'center' ,
                formatter:function(value,row,index){
                	var html = "";
                	if($('#roomUpdate').val()){
                		html += '<a title="编辑" class="btn btn-xs btn-info top-layer-min" layer-form-id="roomEditForm" layer-title="编辑户" layer-url="'+_platform+'/room/edit/'+row.id+'" > <i class="fa fa-edit"></i></a>&nbsp;';
                	}
                	if($('#roomDelete').val()){
                		html += '<a title="删除" class="btn btn-xs btn-danger" onclick="deleteroom(&quot;'+row.id+'&quot;)"><i class="fa fa-trash-o"></i></a>&nbsp;';
                	}
                	return html;
                }
            }

        ]
	});
});
//组织机构树点击节点事件
var treeNodeClick = function(e,treeId,treeNode){ 
	var orgId = treeNode.id;
	var orgName = treeNode.name;
	$("#orgId").val(orgId);
	$("input[name='orgName']").val(orgName);
	communitySelect();
};

//初始化treebox
function initTreeBox(){
	if(roomSearTree==null){
		setTimeout('initTreeBox()',50)
	}else{
//		var treeObj = $.fn.zTree.getZTreeObj("temp_org_tree");
		var box = $('input[name="orgName"]').treeBox({setting:roomSearTree.setting,zNodes:roomSearTree.getNodes()});
	}
}

/**
 * 获取页面表单中的input和select元素的name和value，并放入param对象中
 * @param param form请求参数对象
 * @param formId form表单id
 * @param isVisible 是否只获取可见元素
 */
function formsParam(param,formId,isVisible){
	var paramStr="";
	var forms=[];
	var selects = [];
	if(isVisible){
		forms = $('#'+formId+' input:visible');
		selects = $('#'+formId+' select:visible');
	}else{
		forms = $('#'+formId+' input');
		selects = $('#'+formId+' select');
	}
	for(var j=0;j<selects.length;j++){
		forms.push(selects[j]);
	}
	for(var i=0;i<forms.length;i++){
		var k = $(forms[i]).attr("name");
		var v = $(forms[i]).val();
		param[k] = v;
		paramStr+=k+"="+v+"&";
	}
    return paramStr.substring(0,paramStr.length-1);
}

/**
 * 删除户
 * @param id
 */
function deleteroom(id) {
    layer.confirm('您是否确定删除户？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _platform + '/room/delete/' + id,
            type: 'POST',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    layer.closeAll();
                    $('#room-table-list').bootstrapTable("refresh");
                    layer.msg(result.msg);
                } else {
                    layer.close(index);
                    layer.msg(result.msg);
                }
            }
        });
    });
}

/**
 * 导出户信息到excel
 */
function exportRoom(){
	var tableDatas = $('#room-table-list').bootstrapTable('getData');
	if(tableDatas==null||tableDatas=='null'||tableDatas==''){
		layer.msg('没有数据要导出，请重新搜索！');
		return;
	}
	var paramStr = formsParam({},"room-form",true);
	var url = _platform + '/room/export?'+paramStr;
	window.location.href = url;
}