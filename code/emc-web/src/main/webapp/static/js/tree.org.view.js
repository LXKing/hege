/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/19<BR>
 *     树下拉框
 *     基于ztree写的
 *     使用时先定义ztree 的setting
 *     然后调用就行了，如
 *     var setting={};
 *     $('input').treeBox({setting:setting});
 */
    var Org = function(options) {
        return new Org.fn.init(options);
    }
    var id = generateUUID();
    Org.prototype.id = id;
    Org.fn = Org.prototype = {
        constructor: Org,
        init: function(options) {
            this.id = id;
            this.options = options;
            this.initTree = function() {
               return this.view(this.options);
            }
        },
        view: function(options) {
            this.class ="."+options.class;
            var $this =$(top.document).find(this.class);
            var $thisTree = null;
            if($this.length<1){
                $this = $(this.class)
            }
            if($this.length>0) {
                var ts = $("#comId").val();
                top.lightId = $this.attr("light");
                var id = 'temp_org_tree_'+this.id;
                $this.html("<div id='"+id+"' light='"+ top.lightId+"' class='ztree'></div>");
                var setting = {
                    async: { enable: true, url: _web + '/common/org/tree/'+ts, autoParam: ["id"]},
                    view: {selectedMulti: false, fontCss: {color: "black"}},
                    check: { enable: false },
                    data: { simpleData: { enable: true, idKey: "id", pIdKey: "pId", system: "Name", rootPId: "" } },
                    edit: {enable: false },
                    callback: { onClick: treeNodeClick,onAsyncSuccess: zTreeOnAsyncSuccess}
                };
                var obj = $(top.document).find("#"+id);
                if(obj.length<1){
                    obj = $("#"+id);
                }
                $thisTree = $.fn.zTree.init(obj, setting);
                top.comm_ztree = $.fn.zTree.getZTreeObj(id);
            }
            return $thisTree;
        }
    }
    var zTreeOnAsyncSuccess = function(){
        var lightId = top.lightId;
        top.comm_ztree = $.fn.zTree.getZTreeObj("temp_org_tree_"+id);
        var treeObj = $.fn.zTree.getZTreeObj("temp_org_tree_"+id);
        var nodes = [];
        if (lightId != "" && lightId != undefined) {
            nodes = lightId.split(",");
            for (var i = 0; i < nodes.length; i++) {
                var node = treeObj.getNodeByParam("id", nodes[i]);
                treeObj.selectNode(node, true);
            }
        }
    }
    Org.fn.init.prototype = Org.fn;


function generateUUID() {
    var d = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    return uuid;
};











