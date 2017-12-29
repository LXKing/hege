/**
 * sunbinbin
 */
    $(function(){
        load();
    });
var tree;
 function load() {
    tree = getCompanyTree();
    $('.company-tree').on('click', '.delCom', function () {
            var _this = $(this);
            var children=_this.parents('tr').find('span');
            var orgType =  $(_this.parents("tr").find("td")[4]).html();
            var id = _this.parents('tr').attr('data-id');
            if(children.length>0){
                layer.msg("请先删除下级机构！");
            }else {
                layer.confirm('是否删除该数据？', {
                    btn: ['删除', '取消'] //按钮
                }, function () {
                    var index = layer.load(1, {
                        shade: [0.1, '#fff'] //0.1透明度的白色背景
                    });
                    $.ajax({
                        url: ctx + '/company/delete',
                        type: 'POST',
                        dataType: 'json',
                        data: {orgId: id, typeId: textValue(orgType)},
                        success: function (result) {
                            if (result > 0) {
                                layer.closeAll();
//                        tree.defaults.delNode(_this.parents('tr'));
                                load();
                                layer.msg("删除成功！");
                            } else {
                                layer.close(index);
                                layer.msg("删除失败！");
                            }
                        }
                    });
                });
            }

    }).on('click', '.addCom', function () {
        var id= $(this).parents('tr').attr('data-id');
        var orgType =  $($(this).parents("tr").find("td")[4]).html();
        if(textValue(orgType) !=1){
            layer.msg("只能在公司下添加子公司！");
            return ;
        };

        $('#layer-div').html("");
        $.get(ctx + '/company/add', {pid: id}, function (result) {
            $('#layer-div').html(result);
        })
        layer.open({
            area: ['800px', '600px'],
            type: 1,
            title: '公司信息',
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 1,
            btn: ['保存', '取消'],
            yes: function () {
                $("#org-add-Form").submit();
            },
            scrollbar: false,
            shadeClose: false, //开启遮罩关闭
            content: $('#layer-div')
        });
    }).on('click', '.updCom', function () {
        var _this = $(this);
        var id = _this.parents('tr').attr('data-id');
        var orgType =  $(_this.parents("tr").find("td")[4]).html();
        if(textValue(orgType) !=1){
            layer.msg("只能修改公司信息！");
            return ;
        };
        $.get(ctx + '/company/edit', {orgId: id}, function (result) {
            $('#layer-div').html(result);
        });
        layer.open({
            area: ['750px', '600px'],
            type: 1,
            title: '公司信息',
            skin: 'layer-ext-moon', //样式类名
            closeBtn: 1, //不显示关闭按钮
            shift: 1,
            btn: ['保存', '取消'],
            yes: function () {
                $("#org-edit-Form").submit();
            },
            scrollbar: false,
            shadeClose: false, //开启遮罩关闭
            content: $('#layer-div')
        });
    })

}

function getCompanyTree(){
    var orgName = $("#orgname").val();
    var orgCode = $("#orgcode").val();
    var params = {
        _method: 'PATCH'
    };
    if(validates(orgName)){
        params.orgName =orgName;
    }
    if(validates(orgCode)){
        params.orgCode = orgCode;
    }
   var companyTree =  $('.company-tree').TreeGrid({
        url:  ctx + '/company/list',//树请求地址（必填）
        params: params,//参数
        pFieldName: 'pOrgId',
        fieldId: 'orgId',//主键（必填）
        fieldName: 'orgName',//展开收起所属字段（必填）
        fields: [{ name: 'orgCode' },{ name: 'shortName' },
            {name: 'groupTypeId',
                formater: function (val) {
                    if (val == 1) {
                        return '集团内';
                    } else {
                        return '集团外';
                    }
                } }, {  name: 'typeId',
                formater:function(val){
                        if(val == 1){
                            return '公司';
                        }else if(val == 2){
                            return '热源';
                        }else if (val == 3){
                            return '一次网';
                        }else if (val == 4){
                            return '热力站';
                        }else if (val == 5){
                            return '二次线';
                        } else if(val == 6){
                            return '楼栋';
                        }else if (val == 7){
                            return '单元';
                        }else if (val == 8){
                            return '房间';
                        }else if (val == 9){
                            return '部门';
                        }else if(val == 10){
                            return '一次网网段';
                        }else if (val == 11){
                            return '二次线网段';
                        }else if (val == 12){
                            return '小室';
                        }else{
                            return '';
                        }
            } }
        ],//其它字段
        btnTdHtml: $("#company-tree-btn").html(),
        children: 'children'
    });
    return companyTree;
}

var textValue = function(val){
    if(val == 1){
        return "公司";
    }else if(val == 2){
        return "热源";
    }else if (val == 3){
        return "一次网";
    }else if (val == 4){
        return "热力站";
    }else if (val == 5){
        return "二次线";
    } else if(val == 6){
        return "楼栋";
    }else if (val == 7){
        return "单元";
    }else if (val == 8){
        return "房间";
    }else if (val == 9){
        return "部门";
    }else if(val == 10){
        return "一次网网段";
    }else if (val == 11){
        return "二次线网段";
    }else if (val == 12){
        return "小室";
    }else if(val == "公司"){
        return 1;
    }else if(val == "热源"){
        return 2;
    }else if (val == "一次网"){
        return 3;
    }else if (val == "热力站"){
        return 4;
    }else if (val == "二次线"){
        return 5;
    } else if(val == "楼栋"){
        return 6;
    }else if (val == "单元"){
        return 7;
    }else if (val == "房间"){
        return 8;
    }else if (val == "部门"){
        return 9;
    }else if(val == "一次网网段"){
        return 10;
    }else if (val == "二次线网段"){
        return 11;
    }else if (val == "小室"){
        return 12;
    }
}

/* 添加菜单回调函数 */
function adCompanydCollBack(){
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url:ctx + '/company/add' ,
        data:$("#org-add-Form").serialize(),
        type:'POST',
        dataType:'json',
        success:function(result) {
            if(result.flag){
                closeWindow();
                layer.msg(result.msg);
                tree.defaults.addNode(tree, $('tr[data-id="' + $('input[name="pOrgId"]').val() + '"]'), result.data);
            }else{
                layer.close(index);
                layer.msg(result.msg);
                closeWindow();
            }
        }
    });

}

/* 添加菜单回调函数 */
function eidtCompanyCollBack(){
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        url:ctx + '/company/edit' ,
        data:$("#org-edit-Form").serialize(),
        type:'POST',
        dataType:'json',
        success:function(result) {
            if(result.flag){
                debugger;
               tree.defaults.updNode(tree,$('tr[data-id="'+result.data.orgId+'"]'),result.data);
                layer.close(index);
                closeWindow();
                layer.msg(result.msg);
            }else{
                layer.close(index);
                layer.msg(result.msg);
            }
        }
    });

}
var add = function (id){
    $('#layer-div').html("");
    $.get(ctx + '/company/add', {pid: id}, function (result) {
        $('#layer-div').html(result);
    })
    layer.open({
        area: ['800px', '600px'],
        type: 1,
        title: '公司信息',
        skin: 'layer-ext-moon', //样式类名
        closeBtn: 1, //不显示关闭按钮
        shift: 1,
        btn: ['保存', '取消'],
        yes: function () {
            $("#org-add-Form").submit();
        },
        scrollbar: false,
        shadeClose: false, //开启遮罩关闭
        content: $('#layer-div')
    });
}

 function validates(str){
    if(str !=null &&str !=undefined&& str != ""){
       return true;
    }else{
        return false;
    }
}
var search = function (){
    load( );

}


/**
 * 关闭添加窗口
 */
var closeWindow = function () {
    layer.closeAll();
}