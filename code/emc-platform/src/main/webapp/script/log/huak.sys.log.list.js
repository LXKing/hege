$(function () {
	//日志列表
	var userTable = $('#log-table-list').bootstrapTable({
		height: getHeight() + 30,//高度
        cache: false,//禁用 AJAX 数据缓存
        url: _platform + '/log/list',//获取数据的Servlet地址
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
            formsParam(param,'log-form',true);
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
//            {
//                checkbox: true
//            },
            {
                title: '序号',
                field: 'sn',
                align: 'center',
                formatter:function(value,row,index){
                    return index+1;
                }
            },
            {
                title: '操作名称',
                field: 'optName',
                align: 'center'
            },
            {
                title: '操作类型',
                field: 'optType',
                align: 'center',
                formatter:function(value,row,index){
                	var str = "";
                    switch(value){
                    	case '0':str="增加";break;
                    	case '1':str="删除";break;
                    	case '2':str="修改";break;
                    	case '3':str="查询";break;
                    }
                    return str;
                }
            },
            {
                title: '操作模块名称',
                field: 'optKey',
                align: 'center'
            },
            {
                title: '操作类名称',
                field: 'className',
                align: 'center'
            },
            {
                title: '操作方法名称',
                field: 'methodName',
                align: 'center'
            },
            {
                title: '客户机IP',
                field: 'remoteIp',
                align: 'center'
            },
            {
                title: '客户机名称',
                field: 'remoteName',
                align: 'center'
            },
            {
                title: '客户机端口',
                field: 'remotePort',
                align: 'center'
            },
            {
                title: '请求资源',
                field: 'reqUri',
                align: 'center'
            },
            {
                title: '请求地址',
                field: 'reqUrl',
                align: 'center'
            },
            {
                title: '创建时间',
                field: 'createTime',
                align: 'center'
            },
            {
                title: '创建人',
                field: 'creator',
                align: 'center'
            }
//            ,
//            {
//                title: '操作',
//                field: 'opt',
//                align: 'center' ,
//                formatter:function(value,row,index){
//                    return '<a title="编辑" class="btn btn-xs btn-info top-layer-min" layer-form-id="userEditForm" layer-title="编辑日志" layer-url="'+_platform+'/user/edit/'+row.id+'" > <i class="fa fa-edit"></i></a>&nbsp;' +
//                        '<a title="删除" class="btn btn-xs btn-danger" onclick="deleteUser(&quot;'+row.id+'&quot;)"><i class="fa fa-trash-o"></i></a>&nbsp;' +
//                        '<a title="授权权限" class="btn btn-xs btn-warning" onclick="roleAuthPage()"><i class="fa fa-wrench"></i></a>';
//                }
//            }
        ]
	});
	//初始化日期控件
	initDateBox(['cStartTime','cEndTime']);
});

/**
 * 初始化日期框
 * @param ids
 */
function initDateBox(ids){
	if(ids!=null&&ids.length>0){
		for(var index in ids){
			if(!isNaN(index)){
				var id = ids[index];
				var datebox = {
			        elem: '#'+id,
			        format: 'YYYY-MM-DD hh:mm:ss',
			        max: '2099-06-16 23:59:59',
			        istime: true,
			        istoday: false,
			        choose: function (datas) {
//			            start.max = datas; //结束日选好后，重置开始日的最大日期
			        }
			    };
			    laydate(datebox);
			}
		}
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
	var forms=null;
	if(isVisible){
		forms = $('#'+formId+' input:visible');
		forms.push($('#'+formId+' select:visible'));
	}else{
		forms = $('#'+formId+' input');
		forms.push($('#'+formId+' select'));
	}
    for(var i in forms){
    	if(!isNaN(i)){
    		var k = $(forms[i]).attr("name");
    		var v = $(forms[i]).val();
    		param[k] = v;
    		paramStr+=k+"="+v+"&";
    	}
    }
    return paramStr.substring(0,paramStr.length-1);
}

/**
 * 导出日志信息到excel
 */
function exportLog(){
	var paramStr = formsParam({},"log-form",true);
	var url = _platform + '/log/export?'+paramStr;
	window.location.href = url;
}