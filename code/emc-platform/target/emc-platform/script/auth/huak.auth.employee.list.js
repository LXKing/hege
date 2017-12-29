/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/30<BR>
 */
var bTable;
var orgTree;
$(function () {
    var org = new Org({
        class:"org-tree"
    });
    orgTree = org.initTree();
    /*查询员工列表页*/
    bootstraplist();
    //日期范围限制
    var start = {
        elem: '#start',
        format: 'YYYY/MM/DD hh:mm:ss',
        //min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY/MM/DD hh:mm:ss',
        max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
//    laydate(start);
//    laydate(end);

    //下拉框js
    $(".chosen-select").chosen();

});


function bootstraplist(){

    bTable = $('#employee-table-list').bootstrapTable({
        height: getHeight() + 30,//高度
        cache: false,//禁用 AJAX 数据缓存
        url: _platform + '/employee/list',//获取数据的Servlet地址
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
        queryParams: queryParams,
        formatLoadingMessage: function () {
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
                title: '员工名称',
                field: 'empName',
                align: 'center'
            },
            {
                title: '工号',
                field: 'jobNum',
                align: 'center'
            },
            {
                title: '性别',
                field: 'sex',
                align: 'center',
                formatter:function(value,row,index){
                    if(value == '1'){
                        return '女';
                    }
                    if(value == '0'){
                        return '男';
                    }
                    return '';
                }
            },
            {
                title: '手机号',
                field: 'phone',
                align: 'center'
            },
            {
                title: '邮箱',
                field: 'email',
                align: 'center'
            },
            {
                title: '年龄',
                field: 'age',
                align: 'center'
            },
            {
                title: '生日',
                field: 'birthday',
                align: 'center'
            },
            {
                title: '联系电话',
                field: 'tel',
                align: 'center'
            },
            {
                title: '操作',
                field: 'opt',
                align: 'center' ,
                formatter:function(value,row,index){
                    var ts = $(top.document).find("[name='searchComp']").val();
                    var html = "";
                    if($("#employeeUpdateAuth").val()){
                        html += '<a title="编辑" class="btn btn-xs btn-info top-layer-min" layer-form-id="employee_edit_Form" layer-title="编辑员工" layer-url="'+_platform+'/employee/edit/'+row.id+'/'+ts+'"> <i class="fa fa-edit"></i></a>&nbsp;';
                    }
                    if($("#employeeDeleteAuth").val()){
                        html += '<a title="删除" class="btn btn-xs btn-danger" onclick="deleteemployee(&quot;'+row.id+'&quot;)"><i class="fa fa-trash-o"></i></a>&nbsp;';
                    }
                    return html;
                }
            }

        ]


    });

}
function search(){
    $('#employee-table-list').bootstrapTable('refresh');
}
//layer
function deleteemployee(id) {
    top.layer.confirm('您是否确定删除员工？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _platform + '/employee/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    $('#employee-table-list').bootstrapTable("refresh");
                } else {
                    top.layer.close(index);
                    top.layer.msg(result.msg);
                }
            }
        });
    });
}

function queryParams(params) {
    var ts = $(top.document).find("[name='searchComp']").val();
    $("#comId").val(ts);
    return $("#employee-searchform").serialize();
}

function treeNodeClick(e,treeId,treeNode){
    $("#orgId").val(treeNode.id);
    search();
}

function addEmployee(){
    var treeNode = orgTree.getSelectedNodes();
    var ts = $(top.document).find("[name='searchComp']").val();
    if(treeNode.length<1){
        layer.msg("请先选择一个组织机构！");
        return;
    }
    if(treeNode.length>1){
        layer.warn("只能选择一个上级组织！");
        return;
    }
    openLayer(_platform+"/employee/add/"+treeNode[0].id+"/"+ts,"添加员工","employee_add_Form",null,null);
}

