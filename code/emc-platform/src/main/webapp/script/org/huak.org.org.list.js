
/*
 * 2017年5月10日 14:41:38 lrm
 * */

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
        key : { name : "name" },
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            system:"Name",
            rootPId: ""
        }
    },
    async : { // 是否异步加载 相当于ajax

        enable : true//设置 zTree 是否开启异步加载模式
                      //默认值：false

    },

    edit: {
        drag:{ isCopy:false,isMove:false},
        enable: true,
        editNameSelectAll:true,
        addTitle:'添加',
        removeTitle:'删除',
        renameTitle:'编辑'

    },
    callback: {
        beforeRemove:beforeRemove,//点击删除时触发，用来提示用户是否确定删除
        beforeEditName: beforeEditName,//点击编辑时触发，用来判断该节点是否能编辑
        onRemove:onRemove,//删除节点后触发，用户后台操作
        onRename:onRename,//编辑后触发，用于操作后台
        beforeDrag:beforeDrag, //用户禁止拖动节点
        onClick:clickNode//点击节点触发的事件

    }
};
function beforeEditName(treeId,treeNode){

    //需要对名字做判定的，可以来这里写~~
    //alert(treeNode.id+"---"+treeNode.name);
    var id = treeNode.id;
    openLayer(_platform+"/org/editnode/"+id,"编辑机构节点","orgTreeEditForm",null,null);
    return true;
}
$(document).ready(function(){
    //页面说明
    var  url ="${platform}/org/ztreeValue";
    console.info("页面说明：系统登录用户选择企业或者公司后显示该企业或者公司的组织机构\n" +
        "功能：\n【添加】【删除】【修改】\n" +
        "字段：\n组织机构名称、上级组织机构\n" +
        "以树显示组织机构，所以有隐藏字段：\n创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );
    ztreeValue();
});
var newCount = 1;
function addHoverDom(treeId, treeNode) {
    var id1 =treeNode.id;
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='添加' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        top.treeNode = treeNode;
        var id =treeNode.id;
        openLayer(_platform+"/org/addnode/"+id,"添加机构节点","orgTreeAddForm",null,null);
    });
};

function removeHoverDom(treeId, treeNode) {

    // alert(treeNode.tId);
    $("#addBtn_"+treeNode.tId).unbind().remove();

};
function beforeRemove(treeId,treeNode){
    layer.confirm('确认删除吗', {
        btn: ['确定','取消'] //按钮
    }, function(){
        onRemove(treeId,treeNode);
        return true;
    });
    return false;
}

function onRemove(treeId,treeNode){
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    var paramsArray = new Array();
    if(treeNode.isParent){
        var childNodes = zTree.removeChildNodes(treeNode);
        for(var i = 0; i < childNodes.length; i++){
            paramsArray.push(childNodes[i].id);
        }
        //alert("删除父节点的id为："+treeNode.id+"\r\n他的孩子节点有："+paramsArray.join(","));
        paramsArray.push(treeNode.id);
        //alert("将要删除的所有节点为："+paramsArray.join(","));
    }else{
        paramsArray.push(treeNode.id);
    }
    //alert("你点击要删除的节点的名称为："+treeNode.name+"\r\n"+"节点id为："+treeNode.id+"---"+paramsArray.join(","));
         var ids = paramsArray.join(",");
            //alert(ids);
    $.ajax({
        type: "post",
        url: _platform+"/org/delete",
        data: {ids:ids},
        dataType: "json",
        success: function (data) {
            console.log(data);
            //alert(data.flag);
            zTree.removeNode(treeNode);
            layer.msg(data.msg);
        },
        error: function(data) {
            layer.msg("data is erro");
        }
    });

    return false;
}

function onRename(e, treeId, treeNode, isCancel) {
    //需要对名字做判定的，可以来这里写~~
    //alert(treeNode.id+"---"+treeNode.name);
//    var id = treeNode.id;
//    openLayer(_platform+"/org/editnode/"+id,"编辑机构节点","orgTreeEditForm",null,null);
    return true;
}
function beforeDrag(treeId,treeNodes){
    return false;
}

function clickNode(e,treeId,treeNode) {
    //alert(treeNode.id+"---"+treeNode.name);
    $.ajax({
        type: "post",
        url: _platform + "/org/detail",
        data: {id: treeNode.id},
        dataType: "json",
        success: function (data) {
            console.log(data);
            $("#orgName").val(data.orgDetail.orgName);
            $("#orgCode").val(data.orgDetail.orgCode);
            $("#shortName").val(data.orgDetail.shortName);
            $("#memo").val(data.orgDetail.memo);
            $("#sort").val(data.orgDetail.sort);
            var type = data.orgDetail.typeId;

            if (type == "1") {
                $("#typeId").val("公司");
            } else if (type == "2") {
                $("#typeId").val("分公司");
            } else if (type == "3") {
                $("#typeId").val("中心");
            } else if (type == "4") {
                $("#typeId").val("服务站");
            } else if (type == "5") {
                $("#typeId").val("部门");
            }
        },
        error: function (data) {

        }
    });

}
function ztreeValue(){

    var comId = $(top.document).find("[name='searchComp']").val();;

    $.ajax({
        type: "get",
        url: _platform+"/org/ztreeValue",
        data: {comId:comId},
        dataType: "json",
        success: function (data) {
            //console.log(data);
            var nodes='';
            var zNodes ='[';
            for (var i=0;i<data.length;i++){
                var orgName='"' + data[i].orgName + '"';
                var id='"' + data[i].id + '"';
                var pid='"' + data[i].pOrgId + '"';
                zNodes+="{ id:"+id+", pId:"+pid+", name:"+orgName+", open:true},";
            };
            var newnodes=zNodes.substring(0,zNodes.length-1);
            nodes= newnodes+"]"
            //alert(nodes);
            $.fn.zTree.init($("#treeDemo"), setting, eval("(" + nodes + ")"));
        },
        error: function(data) {
            //alert("data is erro");
        }
    });
}
