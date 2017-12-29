<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 个人资料</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <script src="../js/jquery.min.js?v=2.1.4"></script>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="../css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="../css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css?v=4.1.0" rel="stylesheet">
    <link href="../css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/ztree/css/metroStyle/metroStyle.css" type="text/css">
    <script type="text/javascript" src="../css/ztree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="../css/ztree/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="../css/ztree/js/jquery.ztree.exedit.js"></script>
    <script src="../js/bootstrap.min.js?v=3.3.6"></script>
    <script src="../js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="../js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="../js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <SCRIPT type="text/javascript">
        <!--
        var setting = {
            view: {
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom,
                selectedMulti: false,
                fontCss:{color:"blue"}
            },
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            edit: {
                enable: true
            },
            callback:{
                beforeEditName:beforeEdt
            }
        };

        var zNodes =[
            { id:1, pId:null, name:"前台菜单", open:true},
            { id:2, pId:null, name:"后台菜单"},
            { id:12, pId:2, name:"组织机构管理"},
            { id:121, pId:2, name:"权限管理"},
            { id:122, pId:2, name:"用户管理"},
            { id:123, pId:2, name:"角色管理"},
            { id:124, pId:1, name:"统计分析"},
            { id:123, pId:1, name:"热力站统计"},
            { id:124, pId:1, name:"热力站填报"},
            { id:125, pId:1, name:"热源填报"},
            { id:126, pId:1, name:"热源能耗统计"},
            { id:127, pId:1, name:"对标管理"},
            { id:128, pId:1, name:"专家管理"}
        ];

        $(document).ready(function(){

            //页面说明
            console.info("页面说明：\n左侧菜单树:是系统菜单的树形结构。\n" +
                    "右侧：菜单的详细信息。\n" +
                    "功能：\n" +
                    "【添加】【删除】【修改】【检索】\n" +
                    "字段：\n菜单名称、菜单上级\n" +
                    "创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });

        var newCount = 1;
        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                    + "' title='add node' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_"+treeNode.tId);
            if (btn) btn.bind("click", function(){
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
                return false;
            });
        };
        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_"+treeNode.tId).unbind().remove();
        };
        function beforeEdt(treeId,treeNode){
            alert("添加菜单！");
        }

        $(function(){

            $('#mytab').bootstrapTable({
                 data:[{
                  menu_name:"组织机构管理",
                  url: '/eccp/eccp-platform/org/list',
                  frontorback: '前台',
                  id:"1"
                }, {
                     menu_name:"菜单管理",
                     url: '/eccp/eccp-platform/menu/list',
                     frontorback: '前台',
                     id:"2"
                }],
                url:"",
                dataField: "rows",//服务端返回数据键值 就是说记录放的键值是rows，分页时使用总记录数的键值为total
                height: tableHeight(),//高度调整
                search: true,//是否搜索
                pagination: true,//是否分页
                pageSize: 20,//单页记录数
                pageList: [5, 10, 20, 50],//分页步进值
                sidePagination: "server",//服务端分页
                contentType: "application/x-www-form-urlencoded",//请求数据内容格式 默认是 application/json 自己根据格式自行服务端处理
                dataType: "json",//期待返回数据类型
                method: "post",//请求方式
                searchAlign: "left",//查询框对齐方式
                queryParamsType: "limit",//查询参数组织方式
                queryParams: function getParams(params) {
                    //params obj
                    params.other = "otherInfo";
                    return params;
                },
                searchOnEnterKey: false,//回车搜索
                showRefresh: true,//刷新按钮
                showColumns: true,//列选择按钮
                buttonsAlign: "left",//按钮对齐方式
                toolbar: "#toolbar",//指定工具栏
                toolbarAlign: "right",//工具栏对齐方式
                columns: [
                    {
                        title: "全选",
                        field: "select",
                        checkbox: true,
                        width: 25,//宽度
                        align: "center",//水平
                        valign: "middle"//垂直
                    },
                    {
                        title: "菜单名称",//标题
                        field: "menu_name",//键名
                        sortable: true,//是否可排序
                        order: "desc"//默认排序方式
                    },
                    {
                        field: "url",
                        title: "链接",
                        sortable: true,
                        titleTooltip: "菜单链接"
                    },
                    {
                        field: "frontorback",
                        title: "前后台",
                        sortable: true
                    },
                    {
                        field: "id",
                        title: "操作",
                        formatter: 'infoFormatter'//对本列数据做格式化
                    }
                ],
//                onClickRow: function(row, $element) {
//                    //$element是当前tr的jquery对象
//                    $element.css("background-color", "green");
//                },//单击row事件
                locale: "zh-CN",//中文支持,
                detailView: false, //是否显示详情折叠
                detailFormatter: function(index, row, element) {
                    var html = '';
                    $.each(row, function(key, val){
                        html += "<p>" + key + ":" + val +  "</p>"
                    });
                    return html;
                 }
        });

            function tableHeight() {
                return $(window).height() - 50;
            }
        });
        //-->
    </SCRIPT>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row animated fadeInRight">
        <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>菜单管理</h5>
                </div>
                <div  class="ibox-content">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
        <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>菜单详情</h5>
                </div>
                <div class="ibox-content" style="height: 100%">
                    <div class="row">
                        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
                            <form class="form-horizontal" id="addForm" role="form">
                                <input name="dicId" value="${object.dicId}" type="hidden"/>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">菜单名称：</label>

                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input class="form-control" readonly value="组织机构管理" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">菜单链接：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input name="dicCode" class="form-control" readonly value="/eccp/eccp-platform/org/list" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">唯一编码：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input name="dicDes" value="org" readonly class="form-control" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="td">
                                        <label class="col-sm-3 col-xs-3 col-md-3 col-lg-3  control-label">前后台：</label>
                                        <div class="col-sm-6 col-xs-6 col-md-6 col-lg-6">
                                            <input name="dicName" readonly value="后台" class="form-control" type="text">
                                        </div>
                                    </div>
                                </div>
                                </form>
                        </div>


                     </div>
                 </div>
             </div>
        </div>
    </div>
</div>
</body>
</html>
