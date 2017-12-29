/**
 * sunbinbin
 */
var tree;
var id ;
var title;
/*
* 组织机构树初始化
* */
var commonCompanyload = function () {
    var orgName = $("#common-orgname").val();
    var orgCode = $("#common-orgcode").val();
    var typeId = $("#common-typeid").val();
    var params = {
        _method: 'PATCH'
    };
    if(validates(orgName)){
        params.orgName =orgName;
    }
    if(validates(orgCode)){
        params.orgCode = orgCode;
    }
    if(validates(typeId)){
        params.typeId = typeId;
    }

    tree = $('.common-huak-org').TreeGrid({
        url:  ctx + '/common/list',//树请求地址（必填）
        params: params,//参数
        pFieldName: 'pOrgId',
        isCheckBox: true,
        fieldId: 'orgId',//主键（必填）
        fieldName: 'orgName',//展开收起所属字段（必填）
        fields: [{ name: 'orgCode' },{ name: 'shortName' }, {  name: 'typeId',formater:function(val){
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
            }
        } } ],
        children: 'children'
    });

}

var setOrgValues = function(){

    var $this = top.documentText;
    var $span = $this.parent();
    var $textInput=$span.siblings('input:text');
    var $hiddenInput=$span.siblings('input:hidden');
   var objects=  getCheckObject("org_common_id");
    if(objects.length>1){
        parent.layer.msg('只能选择一个组织机构信息！', {shade: 0.3});
        return false;
    }
    if(objects.length<1){
        parent.layer.msg('请选择一个组织信息！', {shade: 0.3});
        return false;
    }
    debugger;
    $($hiddenInput).val(objects[0].id);
    $($textInput).val(objects[0].name);
    parent.layer.close(top.layerIndex);
    $(top.documentWindows).remove(".common-org-window");
}


/*
*
* 校验字符串是否为空
* 为空返回false 不为空返回 true
* */
var validates = function (str){
    if(str !=null &&str !=undefined&& str != ""){
       return true;
    }else{
        return false;
    }
}

/*
* 查询方法
* */
var search = function (){
    load( );
}

