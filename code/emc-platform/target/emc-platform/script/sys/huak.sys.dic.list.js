/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/30<BR>
 */
$(function () {
    $('#dic-table-list').bootstrapTable({
        height: getHeight() + 30,//高度
        cache: false,//禁用 AJAX 数据缓存
        url: _platform + '/sys/dic/list',//获取数据的Servlet地址
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
                des:$('input[name="des"]').val(),
                typeUs:$('input[name="typeUs"]').val(),
                typeZh:$('input[name="typeZh"]').val(),
                _method: "PATCH",
                pageNumber: params.pageNumber,
                pageSize: params.pageSize
            };
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
            /*{
             checkbox: true
             },*/
            {
                title: '序号',
                field: 'sn',
                align: 'center',
                formatter:function(value,row,index){
                    return index+1;
                }
            },
            {
                title: '字典名称',
                field: 'des',
                align: 'center'
            },
            {
                title: '字典类型(英文)',
                field: 'typeUs',
                align: 'center'
            },
            {
                title: '字典类型(中文)',
                field: 'typeZh',
                align: 'center'
            },
            {
                title: '排序',
                field: 'seq',
                align: 'center'
            },
            {
                title: '操作',
                field: 'opt',
                align: 'center' ,
                formatter:function(value,row,index){
                    var html = "";
                    if($("#sysDicUpdate").val()){
                        html += '<a title="编辑" class="btn btn-xs btn-info top-layer-min" layer-form-id="dicEditForm" layer-title="编辑字典" layer-url="'+_platform+'/sys/dic/edit/'+row.id+'" > <i class="fa fa-edit"></i></a>&nbsp;';
                    }
                    if($("#sysDicDelete").val()){
                        html += '<a title="删除" class="btn btn-xs btn-danger" onclick="deleteDic(&quot;'+row.id+'&quot;)"><i class="fa fa-trash-o"></i></a>';
                    }
                    return  html;

                }
            }

        ]


    });

    //页面说明
    console.info("页面说明：\n1.系统默认2个字典为超级管理员和企业管理员；\n" +
        "2.管理员用户才能进行管理且只能看到自己创建的字典；\n" +
        "功能：\n" +
        "【添加】【删除】【修改】【授权权限】【检索】【重置】【导出EXCEL】\n" +
        "字段：\n字典主键、字典名称、字典备注\n" +
        "创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );

});
function search(){
    $('#dic-table-list').bootstrapTable('refresh');
}

function deleteDic(id) {
    top.layer.confirm('您是否确定删除字典？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _platform + '/sys/dic/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    $('#dic-table-list').bootstrapTable("refresh");
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}
function addDic() {
    var url = _platform + '/sys/dic/add?'+$("#dics-form").serialize();
    openLayer(url,"添加字典","dicAddForm");
}
function dicAuthPage() {
    var id = getCheckValues();
    if (id.length == 0) {
        layer.msg("请选择要授权的字典");
        return false;
    }
    if (id.split(',').length > 1) {
        layer.msg("请选择一个字典进行授权");
        return false;
    }
    $("#content-main").empty().load(_platform + '/dic/auth?id=' + id);
}