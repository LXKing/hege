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
    /*查询热源列表页*/
    bootstraplist();
    //页面说明
    console.info("页面说明：\n1.系统默认2个角色为超级管理员和企业管理员；\n" +
        "2.管理员用户才能进行管理且只能看到自己创建的角色；\n" +
        "功能：\n" +
        "【添加】【删除】【修改】【授权权限】【检索】【重置】【导出EXCEL】\n" +
        "字段：\n角色主键、角色名称、角色备注\n" +
        "创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );

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
            end.start = datas; //将结束日的初始值设定为开始日
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

    bTable = $('#feed-table-list').bootstrapTable({
        height: getHeight() + 30,//高度
        cache: false,//禁用 AJAX 数据缓存
        url: _platform + '/feed/list',//获取数据的Servlet地址
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
                title: '热源名称',
                field: 'feedName',
                align: 'center'
            },
            {
                title: '热源编号',
                field: 'feedCode',
                align: 'center'
            },
            {
                title: '热源性质',
                field: 'feedType',
                align: 'center',
                formatter:function(value,row,index){
                    if(value == '1'){
                        return '热电';
                    }
                    if(value == '2'){
                        return '区域锅炉房';
                    }
                    if(value == '3'){
                        return '核电';
                    }
                    if(value == '4'){
                        return '工业余热';
                    }

                    return '';
                }
            },
            {
                title: '供热类型',
                field: 'heatType',
                align: 'center',
                formatter:function(value,row,index){

                    if(value == '1'){
                        return '区域供热';
                    }
                    if(value == '2'){
                        return '集中供热';
                    }
                    if(value == '3'){
                        return '尖峰供热';
                    }
                    return '';
                }
            },
            {
                title: '装机容量',
                field: 'installCapacity',
                align: 'center'
            },
            {
                title: '供热能力',
                field: 'heatCapacity',
                align: 'center'
            },
            {
                title: '汽机数量',
                field: 'steamturbineNum',
                align: 'center'
            },
            {
                title: '锅炉数量',
                field: 'boilerNum',
                align: 'center'
            },
            {
                title: '操作',
                field: 'opt',
                align: 'center' ,
                formatter:function(value,row,index){
                    var html = "";
                    var treeNode =orgTree.getSelectedNodes();
                    var ts = $(top.document).find("[name='searchComp']").val();
                    var orgId = null;
                    if(treeNode.length>0){
                        orgId = treeNode[0].id
                    }
                    if($("#feedUpdateAuth").val()){
                        html += '<a title="编辑" class="btn btn-xs btn-info top-layer-min" layer-form-id="feed_edit_Form" layer-title="编辑热源" layer-url="'+_platform+'/feed/edit/'+row.id+'/'+orgId+'/'+ts+'"> <i class="fa fa-edit"></i></a>&nbsp;';
                    }
                    if($("#feedDeleteAuth").val()){
                        html += '<a title="删除" class="btn btn-xs btn-danger" onclick="deleteFeed(&quot;'+row.id+'&quot;)"><i class="fa fa-trash-o"></i></a>&nbsp;';
                    }
                    return html;
                }
            }

        ]


    });

}
function search(){
    $('#feed-table-list').bootstrapTable('refresh');
}
//layer
function deleteFeed(id) {
    top.layer.confirm('您是否确定删除热源？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var index = top.layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url: _platform + '/feed/delete/' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (result) {
                if (result.flag) {
                    top.layer.closeAll();
                    top.layer.msg(result.msg);
                    $('#feed-table-list').bootstrapTable("refresh");
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
    var param = {
        pageNumber: params.pageNumber,
        pageSize: params.pageSize
    };

    formsParam(param,'feed-searchform',false);
    return param;
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
function treeNodeClick(e,treeId,treeNode){
    top.orgId = treeNode.id;
    $("#orgId").val(treeNode.id);
    search();
}

function addFeed(){
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
    openLayer(_platform+"/feed/add/"+treeNode[0].id+"/"+ts,"添加热源","feed_add_Form",null,null);
}

function resetFeedSearch(){
    $("#feed-searchform").find('input[name="feedName"]').val();
    $("#feed-searchform").find('input[name="feedCode"]').val();
    $("#feed-searchform").find('input[name="installCapacity"]').val();
    $("#feed-searchform").find('input[name="heatCapacity"]').val();
    $("#heatType").chosen("destroy").val("").chosen();
    $("#feedType").chosen("destroy").val("").chosen();
}