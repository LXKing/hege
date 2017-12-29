
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
    openLayer(_platform+"/active/editnode/"+id,"编辑行政区划节点","activeTreeAddForm",null,null);
    return true;
}
$(document).ready(function(){
    //页面说明
    var  url ="${platform}/org/ztreeValue";
    console.info("页面说明：系统登录用户选择企业或者公司后显示该企业或者公司的组织机构\n" +
        "功能：\n【添加】【删除】【修改】\n" +
        "字段：\n组织机构名称、上级组织机构\n" +
        "以树显示组织机构，所以有隐藏字段：\n创建人、创建人组织、创建时间、修改人、修改人组织、修改时间、是否删除" );
    ztreeValueAdm();
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
        var zTree = $.fn.zTree.getZTreeObj("activeTree");
         //zTree.getSelectedNodes();
        top.treeNode = treeNode;
        var id =treeNode.id;
        openLayer(_platform+"/active/addnode/"+id,"添加行政区划节点","activeTreeAddForm",null,null);
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
    var zTree = $.fn.zTree.getZTreeObj("activeTree");
    var paramsArray = new Array();
    if(treeNode.isParent){
        var childNodes = zTree.removeChildNodes(treeNode);
        for(var i = 0; i < childNodes.length; i++){
            paramsArray.push(childNodes[i].id);
        }
        alert("删除父节点的id为："+treeNode.id+"\r\n他的孩子节点有："+paramsArray.join(","));
        paramsArray.push(treeNode.id);
        alert("将要删除的所有节点为："+paramsArray.join(","));
    }else{
        paramsArray.push(treeNode.id);
    }
    alert("你点击要删除的节点的名称为："+treeNode.name+"\r\n"+"节点id为："+treeNode.id+"---"+paramsArray.join(","));
         var ids = paramsArray.join(",");
            alert(ids);
    $.ajax({
        type: "post",
        url: _platform+"/active/delete",
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
        url: _platform + "/active/detail",
        data: {id: treeNode.id},
        dataType: "json",
        success: function (data) {
            $("#admName").val(data.activeDetail.admName);
            $("#admCode").val(data.activeDetail.admCode);
            $("#admType").val(data.activeDetail.admType);
            $("#lng").val(data.activeDetail.lng);
            $("#lat").val(data.activeDetail.lat);
            var type = data.activeDetail.admLevel;
            var admType = data.activeDetail.admType;
            if (admType == "100") {
                $("#admType").val("城镇");
            } else if (admType == "110") {
                $("#admType").val("城区");
            } else if (admType == "111") {
                $("#admType").val("主城区");
            } else if (admType == "112") {
                $("#admType").val("城乡结合区");
            } else if (admType == "120") {
                $("#admType").val("镇区");
            }else if (admType == "121") {
                $("#admType").val("镇中心区");
            }else if (admType == "122") {
                $("#admType").val("镇乡结合区");
            }else if (admType == "123") {
                $("#admType").val("特殊区域");
            }else if (admType == "200") {
                $("#admType").val("乡村");
            }else if (admType == "210") {
                $("#admType").val("乡中心区");
            }else if (admType == "220") {
                $("#admType").val("村庄");
            }else{
                $("#admType").val("");
            }

            if (type == "1") {
                $("#admLevel").val("省级/直辖市");
            } else if (type == "2") {
                $("#admLevel").val("市级/区");
            } else if (type == "3") {
                $("#admLevel").val("县级");
            } else if (type == "4") {
                $("#admLevel").val("镇");
            } else if (type == "5") {
                $("#admLevel").val("村");
            }
        },
        error: function (data) {

        }
    });

}
function ztreeValueAdm(){

    $.ajax({
        type: "get",
        url: _platform+"/active/admintree",
        data: {},
        dataType: "json",
        success: function (data) {
            //console.log(data);
            var nodes='';
            var zNodes ='[';
            for (var i=0;i<data.length;i++){
                var admName="'"+data[i].admName +"'";
                var admCode=data[i].admCode;
                var pCode=data[i].pCode;
                zNodes+="{ id:"+admCode+", pId:"+pCode+", name:"+admName+"},";
            };
            var newnodes=zNodes.substring(0,zNodes.length-1);
            nodes= newnodes+"]"
            //alert(nodes);
            $.fn.zTree.init($("#activeTree"), setting, eval("(" + nodes + ")"));
        },
        error: function(data) {
            alert("data is erro");
        }
    });
}
