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
(function ($) {
    var treeBox = function (ele, options) {
        this.$element = ele;
        this.defaults = {
            setting: undefined,//树请求地址（必填）
            zNodes:undefined
        };
        this.options = $.extend({}, $.fn.treeBox.defaults, options);

    }

    /**
     * 内部方法
     * @type {{}}
     */
    treeBox.prototype = {
        initOptions:function(opts){
            this.setting = (opts.setting == undefined || opts.setting == "" || opts.url == null) ? undefined : opts.setting;
            this.zNodes = (opts.zNodes == undefined || opts.zNodes == "" || opts.zNodes == null) ? undefined : opts.zNodes;
        },
        //生成div
        createElement: function () {
            var position = this.$element.position();
            var left = position.left;
            var top = position.top + this.$element.outerHeight();

            var _ele = '<div id="tree-box" class="tree-box" style="position: absolute; left: ' + left + 'px; top: ' + top + 'px;width:'+this.$element.outerWidth()+'px">' +
                '<div><ul id="tree-box-ztree" class="ztree"></ul></div>' +
                '</div>';
            return _ele;
        }
    }

    $.fn.treeBox = function (options) {
        var box = new treeBox(this, options);
        box.initOptions(options);
        this.on('click', function (event) {
            $(this).after(box.createElement());
            var $boxTree = $("#tree-box-ztree");

            if($boxTree.length==0){
                $boxTree = $(top.document).find("#tree-box-ztree");
            }
            if(box.zNodes == undefined || opts.url == "" || opts.url == null){
                $.fn.zTree.init($boxTree, box.options.setting);
            }else{
                $.fn.zTree.init($boxTree, box.options.setting, box.options.zNodes);
            }
            $(document).bind("click", bodyDown);
            event.stopPropagation();//阻止冒泡
        });

        return box;
    }

    function bodyDown(event) {
        var $boxTree = $("#tree-box");
        if($boxTree.length==0 ){
            $boxTree = $(top.document).find("#tree-box");
        }
        if (!(event.target.id == "tree-box" || $(event.target).parents("#tree-box").length > 0)) {
            $boxTree.remove();
            $(document).unbind("click", bodyDown);
        }
    }
})(jQuery, document);