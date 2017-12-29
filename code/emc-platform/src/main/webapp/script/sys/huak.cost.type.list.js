/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/1<BR>
 */
$(function () {
    $('#costType-table-list').bootstrapTable({
        height: getHeight() + 30,//高度
        cache: false,//禁用 AJAX 数据缓存
        url: _platform + '/cost/type/list',//获取数据的Servlet地址
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
                nameZh:$('input[name="nameZh"]').val(),
                nameEn:$('input[name="nameEn"]').val(),
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
                title: '类型名称(中文)',
                field: 'nameZh',
                align: 'center'
            },
            {
                title: '类型名称(英文)',
                field: 'nameEn',
                align: 'center'
            },
            {
                title: '用量单位',
                field: 'dosageUnit',
                align: 'center'
            },
            {
                title: '操作',
                field: 'opt',
                align: 'center' ,
                formatter:function(value,row,index){
                    var html = "";
                    if($("#costTypeUpdate").val()){
                        html += '<a title="编辑" class="btn btn-xs btn-info top-layer-min" layer-form-id="costTypeEditForm" layer-title="编辑字典" layer-url="'+_platform+'/cost/type/edit/'+row.id+'" > <i class="fa fa-edit"></i></a>&nbsp;';
                    }
                    if($("#costTypeDelete").val()){
                        html += '<a title="删除" class="btn btn-xs btn-danger" onclick="deleteCostType(&quot;'+row.id+'&quot;)"><i class="fa fa-trash-o"></i></a>';
                    }
                    return  html;

                }
            }

        ]


    });

    $(".chosen-select:not([name='searchComp'])").chosen();
    //页面说明
    console.info("页面说明：\n1.系统默认2个字典为超级管理员和企业管理员；\n" +
        "2.管理员用户才能进行管理且只能看到自己创建的字典；\n" +
        "功能：\n" +
        "【添加】【删除】【修改】【授权权限】【检索】【重置】【导出EXCEL】\n" +
        "字段：\n字典主键、字典名称、字典备注\n" +
        "创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );

});

function deleteCostType(id) {
    top.layer.confirm('您是否确定删除成本类型？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _platform + '/cost/type/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    $('#costType-table-list').bootstrapTable("refresh");
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}