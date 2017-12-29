$(function(){
        var setting = {
            async: {
                enable: true,
                type: "post",
                url:_platform+"/menu/list/tree",
                autoParam: ["id"]
            },
            view: {
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom,
                selectedMulti: false,
                fontCss:{color:"blue"}
            },
            check: {enable: true },
            data: {
                key : { name : "name" },
                simpleData : {
                    enable : true,
                    idKey : "id",
                    pIdKey : "pId",
                    rootPId : null
                }
            },
            edit: {
                drag:{ isCopy:false,isMove:false},
                enable: true,
                addTitle:'添加菜单',
                removeTitle:'删除菜单',
                renameTitle:'修改菜单'
            },
            callback:{
                beforeEditName:beforeEdt,
                beforeRemove:beforeRemove,
                onClick:clickNode
            }
        };

    $.fn.zTree.init($("#menuTree"), setting);

    /**
     * 获取焦点时显示编辑图标
     * @param treeId
     * @param treeNode
     */
    function addHoverDom(treeId, treeNode) {
        var flag = $("#menuAddAuth").val();
        if(flag){
            var sObj = $("#" + treeNode.tId + "_span");

            if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='添加菜单' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_"+treeNode.tId);
            if (btn) btn.bind("click", function(){
                addData(treeId,treeNode);
                return false;
            });
        }
    };

    /**
     * 点击删除按钮时，出发触发回调函数
     *
     * @param treeId
     * @param treeNode
     */
    function beforeRemove(treeId, treeNode) {
        if(treeNode.isParent){
            layer.confirm('该节点是父节点,是否删除该节点以及其子节点？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                return  onRemove(treeId,treeNode);
            });
        }else{
            layer.confirm('是否删除该节点？', {
                btn: ['确定','否'] //按钮
            }, function(){
                return  onRemove(treeId,treeNode);
            });
        }
        return false;
    };

    /**
     * 失去焦点时，移除编辑按钮
     * @param treeId
     * @param treeNode
     */
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
    };

    /**
     * 点击编辑按钮时，触发回调函数
     *
     * @param treeId
     * @param treeNode
     */
    function beforeEdt(treeId,treeNode){
        top.treeNode = treeNode;
        var zTree = $.fn.zTree.getZTreeObj("menuTree");
        zTree.selectNode(treeNode);
        openLayer(_platform+"/menu/edit/"+treeNode.id,"修改菜单","menuEditForm",null,null);
    }





    /**
     * 删除方法自定义
     * @param treeId
     * @param treeNode
     */
    function onRemove(treeId,treeNode){
        var zTree = $.fn.zTree.getZTreeObj("menuTree");
        var paramsArray = new Array();
        if(treeNode.isParent){
            var childNodes = zTree.removeChildNodes(treeNode);
            for(var i = 0; i < childNodes.length; i++){
                paramsArray.push(childNodes[i].id);
            }
            paramsArray.push(treeNode.id);
        }else{
            paramsArray.push(treeNode.id);
        }
        var ids = paramsArray.join(",");
        $.ajax({
            type: "post",
            url: _platform+"/menu/delete",
            data: {ids:ids},
            dataType: "json",
            success: function (data) {
                layer.msg(data.msg);
                zTree.removeNode(treeNode);
            },
            error: function(data) {
                layer.msg(data.msg);
            }
        });
        return true;
    }

    function addData(treeId,treeNode){

        top.treeNode = treeNode;
        openLayer(_platform+"/menu/add/"+treeNode.id,"添加菜单","menuAddForm",null,null);
    };

    function clickNode(e,treeId,treeNode){
        $.ajax({
            type: "post",
            url: _platform+"/menu/detail",
            data: {id:treeNode.id},
            dataType: "json",
            success: function (data) {
                var menutype =data.menu.menuType;
                var type = data.menu.type;
                if(menutype == "0"){
                    $("#menuType").val("前台");
                }else
                    $("#menuType").val("后台");

                if(type == "0"){
                    $("#type").val("模块");
                }else
                    $("#type").val("菜单");
                $("#seq").val(data.menu.seq);
                $("#menuName").val(data.menu.menuName);
                $("#menuUrl").val(data.menu.menuUrl);
            },
            error: function(data) {

            }
        });
    }

});



