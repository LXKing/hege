<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户列表</title>
    <link rel="shortcut icon" href="../favicon.ico">
    <!---->
    <link rel="stylesheet" href="../css/ztree/css/metroStyle/metroStyle.css" type="text/css">


    <link href="../css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="../css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="../css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="../css/plugins/chosen/chosen.css" rel="stylesheet">

    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css?v=4.1.0" rel="stylesheet">
    <link href="../css/mystyle.css" rel="stylesheet">
    <!-- 全局js -->
    <script src="../js/jquery.min.js?v=2.1.4"></script>
    <script src="../js/bootstrap.min.js?v=3.3.6"></script>
    <!--自定义-->
    <script src="../js/content.js?v=1.0.0"></script>

    <!--下拉框chosen-->
    <script src="../js/plugins/chosen/chosen.jquery.js"></script>
    <!-- layerDate plugin javascript -->
    <script src="../js/plugins/layer/laydate/laydate.js"></script>

    <!-- Bootstrap table -->
    <script src="../js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="../js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="../js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <script type="text/javascript" src="../css/ztree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="../css/ztree/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="../css/ztree/js/jquery.ztree.exedit.js"></script>

    <script type="application/javascript">

            $(function() {
                //页面说明
                console.info("页面说明：\n1.左侧为组织机构树，根据选择的默认公司为最高节点展示；\n" +
                        "2.数据展示结构为该组织下以及组织的所有下级组织的用户；\n" +
                        "3.添加用户时询问是否为员工，是则默认生成一条员工信息，不是则选择组织机构；\n" +
                        "功能：\n" +
                        "【添加】【删除】【修改】【授权角色】【授权权限】【禁用】【启用】【重置密码】【检索】【重置】【导出EXCEL】\n" +
                        "字段：\n用户主键、用户名称、直属机构、是否员工、账号、状态、最后登录时间、登陆次数\n" +
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
                laydate(start);
                laydate(end);


                //bootstrapTable
                var bt_data = [{
                    "checkbox":'<div class="checkbox checkbox-info"><input id="checkbox4" type="checkbox"></div>',
                    "Tid": "1",
                    "yhmc": "李超",
                    "zsjg": "华热科技",
                    "yhzh": "18511112222",
                    "zt": "启用",
                    "zhdlsj": "2017-01-01 15:23:23",
                    "dlcs": "45",
                    "cjjg": "华热科技",
                    "cjz": "超级管理员",
                    "cjsj": "2017-01-01 15:23:23",
                    "cz": '<a class="btn btn-xs btn-info" title="编辑" onclick="editRole()"> <i class="fa fa-edit"></i></a>&nbsp;' +
                            '<a class="btn btn-xs btn-warning" title="授权角色" onclick="roleAuthPage()"><i class="fa fa-user"></i></a>&nbsp;'+
                            '<a class="btn btn-xs btn-warning" title="授权权限" onclick="roleAuthPage()"><i class="fa fa-wrench"></i></a>&nbsp;'
                }, {
                    "checkbox":'<input type="checkbox" class="i-checks"  name="input[]">',
                    "Tid": "2",
                    "yhmc": "刘德华",
                    "zsjg": "华热科技",
                    "yhzh": "18511112222",
                    "zt": "启用",
                    "zhdlsj": "2017-01-01 15:23:23",
                    "dlcs": "45",
                    "cjjg": "华热科技",
                    "cjz": "李超",
                    "cjsj": "2017-01-01 15:23:23",
                    "cz": '<a class="btn btn-xs btn-info" title="编辑" onclick="editRole()"> <i class="fa fa-edit"></i></a>&nbsp;' +
                            '<a class="btn btn-xs btn-warning" title="授权角色" onclick="roleAuthPage()"><i class="fa fa-user"></i></a>&nbsp;'+
                            '<a class="btn btn-xs btn-warning" title="授权权限" onclick="roleAuthPage()"><i class="fa fa-wrench"></i></a>&nbsp;'
                }, {
                    "checkbox":'<input type="checkbox" class="i-checks"  name="input[]">',
                    "Tid": "2",
                    "yhmc": "刘德华",
                    "zsjg": "华热科技",
                    "yhzh": "18511112222",
                    "zt": "启用",
                    "zhdlsj": "2017-01-01 15:23:23",
                    "dlcs": "45",
                    "cjjg": "华热科技",
                    "cjz": "李超",
                    "cjsj": "2017-01-01 15:23:23",
                    "cz": '<a class="btn btn-xs btn-info" title="编辑" onclick="editRole()"> <i class="fa fa-edit"></i></a>&nbsp;' +
                            '<a class="btn btn-xs btn-warning" title="授权角色" onclick="roleAuthPage()"><i class="fa fa-user"></i></a>&nbsp;'+
                            '<a class="btn btn-xs btn-warning" title="授权权限" onclick="roleAuthPage()"><i class="fa fa-wrench"></i></a>&nbsp;'
                }, {
                    "checkbox":'<input type="checkbox" class="i-checks"  name="input[]">',
                    "Tid": "2",
                    "yhmc": "刘德华",
                    "zsjg": "华热科技",
                    "yhzh": "18511112222",
                    "zt": "启用",
                    "zhdlsj": "2017-01-01 15:23:23",
                    "dlcs": "45",
                    "cjjg": "华热科技",
                    "cjz": "李超",
                    "cjsj": "2017-01-01 15:23:23",
                    "cz": '<a class="btn btn-xs btn-info" title="编辑" onclick="editRole()"> <i class="fa fa-edit"></i></a>&nbsp;' +
                            '<a class="btn btn-xs btn-warning" title="授权角色" onclick="roleAuthPage()"><i class="fa fa-user"></i></a>&nbsp;'+
                            '<a class="btn btn-xs btn-warning" title="授权权限" onclick="roleAuthPage()"><i class="fa fa-wrench"></i></a>&nbsp;'
                }];


                $('#exampleTableFromData').bootstrapTable({
                    data: bt_data,
                    pagination:true,
                    striped:true,
                    pageSize:2,
                    pageList:[2,10,30],
                    height:480
                    // mobileResponsive: true,
                });

                //
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);

                //下拉框js
                $(".chosen-select").chosen();
            });

            var setting = {
                view: {
                    selectedMulti: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                }
            };

            var zNodes =[
                {id:0,pId:null,name:"华热科技发展有限公司", open:true},
                { id:1, pId:0, name:"热力集团", open:true},
                { id:11, pId:1, name:"海淀分公司", open:true},
                { id:12, pId:1, name:"朝一分公司", open:true},
                { id:111, pId:11, name:"双榆树中心", open:true},
                { id:112, pId:11, name:"海淀技术部"},
                { id:1111, pId:111, name:"双榆树热力站"},
                { id:1112, pId:111, name:"小营热源厂"},
                { id:121, pId:12, name:"SOHO热力站"},
                { id:2, pId:0, name:"天津大港公司", open:true},
                { id:21, pId:2, name:"第一分公司"},
                { id:22, pId:2, name:"第二分公司"},
                { id:211, pId:21, name:"团结村换热站"},
                { id:212, pId:21, name:"设计院换热站"},
                { id:221, pId:22, name:"xx换热站"},
                { id:3, pId:0, name:"长春热力集团"},
                { id:4, pId:0, name:"亚泰热力公司"},
                { id:5, pId:0,name:"捷能热力电站有限公司"}
            ];



    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div>
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-9 col-xs-9 col-md-9 col-lg-9">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="roles-form" role="form" class="form-horizontal m-t">
                        <input type="hidden" name="_method" value="PATCH">
                        <input type="hidden" id="pageNo" name="pageNo" value="1">

                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">用户名称</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" name="roleName" placeholder="请输入用户名称">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">直属机构</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" name="roleName" placeholder="请输入直属机构">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">创建者</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" name="roleName" placeholder="请输入创建者">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">状态</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <select name="useStatus" class="chosen-select form-control">
                                            <option value="">全部</option>
                                            <option value="0">启用</option>
                                            <option value="1">禁用</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                <div class="form-group">
                                    <label class="control-label col-sm-2 col-xs-2 col-md-2 col-lg-2">创建时间</label>
                                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                        <input id="start" class="laydate-icon form-control layer-date" placeholder="请输入开始时间">
                                    </div>
                                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                        <input id="end" class="laydate-icon form-control layer-date" placeholder="请输入结束时间">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-xs-4 col-md-4 col-lg-4">用户账号</label>
                                    <div class="col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" name="roleName" placeholder="请输入用户账号">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8">
                                <div class="form-group">
                                    <label class="control-label col-sm-2 col-xs-2 col-md-2 col-lg-2">登录时间</label>
                                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                        <input id="start1" class="laydate-icon form-control layer-date" placeholder="请输入开始时间">
                                    </div>
                                    <div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
                                        <input id="end1" class="laydate-icon form-control layer-date" placeholder="请输入结束时间">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-8 col-xs-8 col-md-8 col-lg-8  btn-group">

                                <button type="button" class="btn btn-sm btn-info" onclick="addRole()"><i class="fa fa-plus"></i>添加</button>
                                <button type="button" class="btn btn-sm btn-danger " onclick="addRole()"><i class="fa fa-unlock-alt"></i>禁用</button>
                                <button type="button" class="btn btn-sm btn-danger " onclick="addRole()"><i class="fa fa-unlock"></i>启用</button>
                                <button type="button" class="btn btn-sm btn-danger" onclick="deleteRoles()"><i class="fa fa-trash-o"></i>删除</button>
                                <button type="button" class="btn btn-sm btn-warning" onclick="deleteRoles()"><i class="fa fa-key"></i>重置密码</button>
                                    <!--<button type="button" class="btn btn-sm btn-info" onclick="editRole()">
                                        <i class="fa fa-edit"></i>编辑
                                    </button>

                                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteRoles()">
                                        <i class="fa fa-trash-o"></i>删除
                                    </button>

                                    <button type="button" class="btn btn-sm btn-warning" onclick="roleAuthPage()">
                                        <i class="fa fa-wrench"></i>角色授权
                                    </button>-->
                            </div>
                            <div class="btn-tools col-sm-4 col-xs-4 col-md-4 col-lg-4">

                                <button type="button" class="btn btn-sm btn-primary" onclick="getRoleList()"> 搜索</button>
                                <button type="reset" class="btn btn-sm btn-success"> 重置</button>
                                <button type="button" class="btn btn-sm btn-primary" onclick="getRoleList()"> 导出Excel</button>

                            </div>
                        </div>
                    </form>
                    <div class="example">
                    <table id="exampleTableFromData" data-mobile-responsive="true">
                        <thead>
                        <tr>
                            <th data-field="checkbox"><input type="checkbox" class="checkbox-all i-checks" name="input[]"/></th>
                            <th data-field="Tid">ID</th>
                            <th data-field="yhmc">用户名称</th>
                            <th data-field="zsjg">直属机构</th>
                            <th data-field="yhzh">用户账号</th>
                            <th data-field="zt">状态</th>
                            <th data-field="zhdlsj">最后登录时间</th>
                            <th data-field="dlcs">登录次数</th>
                            <th data-field="cjjg">创建机构</th>
                            <th data-field="cjz">创建者</th>
                            <th data-field="cjsj">创建时间</th>
                            <th data-field="cz">操作</th>
                        </tr>
                        </thead>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!--<div id="layer-div" style="display: none"></div>

<%--jtemplement 模板--%>
<textarea id="tpl-list" style="display: none">
    {#foreach $T.data as item}
    <tr>
        <td>
            <input type="checkbox" class="i-checks" value="{$T.item.role_id}" name="input[]">
        </td>
        <td>{$T.item.role_name}</td>
        <td>{$T.item.role_des}</td>
        <td title="{$T.item.memo}">{formatText($T.item.memo,10)}</td>
        <td>
            <a class="btn btn-white btn-xs btn-bitbucket" title="查看">
                <i class="fa fa-file-text-o"></i>
            </a>
                <a class="btn btn-danger btn-xs btn-bitbucket" title="删除" onclick="deleteRole('{$T.item.role_id}')">
                    <i class="fa fa-trash-o"></i>
                </a>
        </td>
    </tr>
    {#/for}
</textarea>-->
</body>
</html>