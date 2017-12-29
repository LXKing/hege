/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/9/7<BR>
 *     treeGrid组件
 */

(function ($) {
    function isNextNode(str, class1, class2) {
        var a = class1.replace(str, '').replace('tree-bg-color', '').replace(' ', '');
        var b = class2.replace(str, '').replace('tree-bg-color', '').replace(' ', '');
        return a < b;
    }

    function isEqualLevel(str, class1, class2) {
        var a = class1.replace(str, '').replace('tree-bg-color', '').replace(' ', '');
        var b = class2.replace(str, '').replace('tree-bg-color', '').replace(' ', '');
        return a == b;
    }

    var TreeGrid = function (ele, options) {
        this.$element = ele;
        this.defaults = {
            url: undefined,//树请求地址（必填）
            params: [],//参数
            pFieldName: undefined,//父类主键名称
            fieldId: undefined,//主键（必填）
            fieldName: undefined,//展开收起所属字段（必填）
            isField: false,//是否存在你字段（false不存在）
            isCheckBox: false,//是否带CheckBox
            checkIds: [],//选中主键
            fields: [],//其它字段
            isBtn: false,//是否存在按钮（false不存在）
            btnTdHtml: undefined,//按钮td的html
            children: 'children',
            timeout: 5000,
            _callBack: undefined,//回调函数
            //删除 tr
            delNode: function ($tr) {
                if ($tr.find('span').length > 0) {
                    //layer.msg('请先删除下级');
                    return false;
                } else {
                    if (!isEqualLevel('tree-level-', $tr.attr('class'), $tr.prev().attr('class'))) {
                        $tr.prev().find('span').remove();
                    }
                    $tr.remove();
                    return true;
                }

            },
            //修改 tr
            updNode: function ($tree, $tr, field) {
                var trClassNum = parseInt($tr.attr('class').replace('tree-level-', ''));
                var html = '';
                html += '<tr class="tree-level-' + trClassNum + '"  data-id="' + $tr.attr('data-id') + '">';
                if ($tree.isCheckBox) {
                    html += '<td><input class="i-checks" name="input[]" type="checkbox" value="' + $tr.attr('data-id') + '"></td>';
                }
                html += '<td class="tree-level">';
                if ($tr.find('.tree-level span').length > 0) {
                    html += '<span>' + $tr.find('span').html() + '</span>';
                }

                html += field[$tree.fieldName] + '</td>';
                if ($tree.isField) {
                    $($tree.fields).each(function (indx, item) {
                        if (item.formater != undefined && item.formater != null && item.formater != '') {
                            html += '<td>' + item.formater(field[item.name]) + '</td>';
                        } else {
                            html += '<td>' + (field[item.name] == undefined ? '' : field[item.name]) + '</td>';
                        }
                    });
                }
                if ($tree.isBtn) {
                    html += $tree.btnTdHtml;
                }
                html += '</tr>';
                $tr.after(html);
                $('.i-checks').iCheck({
                    checkboxClass: 'icheckbox_square-green',
                    radioClass: 'iradio_square-green'
                });
                $tr.remove();
            },
            //在tr下级添加一个节点
            addNode: function ($tree, $tr, field) {
                var trClassNum = parseInt($tr.attr('class').replace('tree-level-', '')) + 1;

                var html = '';
                html += '<tr class="tree-level-' + trClassNum + '"  data-id="' + field[$tree.fieldId] + '">';
                if ($tree.isCheckBox) {
                    html += '<td><input class="i-checks" name="input[]" type="checkbox" value="' + field[$tree.fieldId] + '"></td>';
                }
                html += '<td class="tree-level">';
                html += field[$tree.fieldName] + '</td>';
                if ($tree.isField) {
                    $($tree.fields).each(function (indx, item) {
                        if (item.formater != undefined && item.formater != null && item.formater != '') {
                            html += '<td>' + item.formater(field[item.name]) + '</td>';
                        } else {
                            html += '<td>' + (field[item.name] == undefined ? '' : field[item.name]) + '</td>';
                        }
                    });
                }
                if ($tree.isBtn) {
                    html += $tree.btnTdHtml;
                }
                html += '</tr>';


                if ($tr.find('.tree-level i').length > 0) {
                    if ($tr.find('.fa-chevron-circle-right').length > 0) {
                        $tr.find('.fa-chevron-circle-right').click();
                    }
                } else {
                    $tr.find('.tree-level').prepend('<span><i class="fa fa-chevron-circle-down" /></span>');
                }
                $tr.after(html);
                $('.i-checks').iCheck({
                    checkboxClass: 'icheckbox_square-green',
                    radioClass: 'iradio_square-green'
                });

            }
        };
        this.options = $.extend({}, $.fn.TreeGrid.defaults, options);
    }

    TreeGrid.prototype = {
        /* 初始化参数 */
        initOptions: function (opts) {
            this.url = (opts.url == undefined || opts.url == "" || opts.url == null) ? undefined : opts.url;
            this.pFieldName = (opts.pFieldName == undefined || opts.pFieldName == "" || opts.pFieldName == null) ? undefined : opts.pFieldName;
            this.params = (opts.params == undefined || opts.params == "" || opts.params == null) ? [] : opts.params;
            this.fieldId = (opts.fieldId == "" || opts.fieldId == undefined || opts.fieldId == null) ? undefined : opts.fieldId;
            this.fieldName = (opts.fieldName == "" || opts.fieldName == undefined || opts.fieldName == null) ? undefined : opts.fieldName;
            this.fields = (opts.fields == "" || opts.fields == undefined || opts.fields == null) ? [] : opts.fields;
            this.btnTdHtml = (opts.btnTdHtml == "" || opts.btnTdHtml == undefined || opts.btnTdHtml == null) ? undefined : opts.btnTdHtml;
            this.children = (opts.children == "" || opts.children == undefined || opts.children == null) ? 'children' : opts.children;
            this._callBack = opts._callBack;

            this.isField = this.fields.length > 0 ? true : false;
            this.isBtn = this.btnTdHtml == undefined ? false : true;
            this.isCheckBox = (opts.isCheckBox == undefined || opts.isCheckBox == "" || opts.isCheckBox == null) ? false : opts.isCheckBox;
        },
        /* 初始化树*/
        initTree: function () {
            var $treegrid = this;
            var index = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: this.url,
                timeout: this.timeout,
                type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
                dataType: 'json',
                data: $.param(this.params),
                success: function (result) {
                    var html = '';
                    if (result.list.length > 0) {
                        $.each(result.list, function (idx, idata) {
                            html += '<tr class="tree-level-1" data-id="' + idata[$treegrid.fieldId] + '" >';
                            if ($treegrid.isCheckBox) {
                                html += '<td><input class="i-checks" name="input[]" type="checkbox" value="' + idata[$treegrid.fieldId] + '"></td>';
                            }
                            html += '<td class="tree-level">';
                            if (idata[$treegrid.children] > 0) {
                                html += '<span><i class="fa fa-chevron-circle-right" /></span>';
                            }
                            html += idata[$treegrid.fieldName] + '</td>';
                            if ($treegrid.isField) {
                                $($treegrid.fields).each(function (indx, item) {
                                    if (item.formater != undefined && item.formater != null && item.formater != '') {
                                        html += '<td>' + item.formater(idata[item.name]) + '</td>';
                                    } else {
                                        html += '<td>' + (idata[item.name] == undefined ? '' : idata[item.name]) + '</td>';
                                    }
                                });
                            }
                            if ($treegrid.isBtn) {
                                html += $treegrid.btnTdHtml;
                            }
                            html += '</tr>';
                        });

                    }
                    $treegrid.$element.html(html);
                    $('.i-checks').iCheck({
                        checkboxClass: 'icheckbox_square-green',
                        radioClass: 'iradio_square-green'
                    });
                    layer.close(index);
                },
                error: function () {
                    layer.close(index);
                    layer.msg("加载失败");
                },
                complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
                    layer.close(index);
                    if (status == 'timeout') {//超时,status还有success,error等值的情况
                        layer.msg("加载超时");
                    }
                }
            });
        },
        /* 是否是上级 tree-level- ,tree-level-4,tree-level-2 */
        isPreNode: function (str, class1, class2) {
            var a = class1.replace(str, '').replace('tree-bg-color', '').replace(' ', '');
            var b = class2.replace(str, '').replace('tree-bg-color', '').replace(' ', '');
            return a > b;
        },
        /* 添加树 */
        addAjaxNodes: function ($tr) {
            var pId = $tr.attr('data-id');
            var trClassNum = parseInt($tr.attr('class').replace('tree-level-', '')) + 1;
            var datas = this.params;
            datas[this.pFieldName] = pId;
            var $treegrid = this;
            var index = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                url: this.url,
                timeout: this.timeout,
                type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
                dataType: 'json',
                data: $.param(datas),
                success: function (result) {
                    var html = '';
                    $.each(result.list, function (idx, field) {
                        html += '<tr class="tree-level-' + trClassNum + '"  data-id="' + field[$treegrid.fieldId] + '">';
                        if ($treegrid.isCheckBox) {
                            html += '<td><input class="i-checks" name="input[]" type="checkbox" value="' + field[$treegrid.fieldId] + '"></td>';
                        }
                        html += '<td class="tree-level">';
                        if (field[$treegrid.children] > 0) {
                            html += '<span><i class="fa fa-chevron-circle-right" /></span>';
                        }
                        html += field[$treegrid.fieldName] + '</td>';
                        if ($treegrid.isField) {
                            $($treegrid.fields).each(function (indx, item) {
                                if (item.formater != undefined && item.formater != null && item.formater != '') {
                                    html += '<td>' + item.formater(field[item.name]) + '</td>';
                                } else {
                                    html += '<td>' + (field[item.name] == undefined ? '' : field[item.name]) + '</td>';
                                }
                            });
                        }
                        if ($treegrid.isBtn) {
                            html += $treegrid.btnTdHtml;
                        }
                        html += '</tr>';
                    });
                    $tr.after(html);
                    $('.i-checks').iCheck({
                        checkboxClass: 'icheckbox_square-green',
                        radioClass: 'iradio_square-green'
                    });
                    layer.close(index);
                },
                error: function () {
                    layer.close(index);
                    layer.msg("加载失败");
                },
                complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
                    layer.close(index);
                    if (status == 'timeout') {//超时,status还有success,error等值的情况
                        layer.msg("加载超时");
                    }
                }
            });
        }
    }
    $.fn.TreeGrid = function (options) {
        var treegrid = new TreeGrid(this, options);
        treegrid.initOptions(options);
        treegrid.initTree();

        this.off('click', '.fa-chevron-circle-down');
        this.off('click', '.fa-chevron-circle-right');
        this.off('click', 'tr');
        this.on('click', '.fa-chevron-circle-down', function (event) {
            /* 收起事件 */
            var $this = $(this);
            var $tr = $(this).parents('tr');
            var $trs = $tr.nextAll();
            $this.removeClass('fa-chevron-circle-down').addClass('fa-chevron-circle-right');
            $trs.each(function (i, item) {
                //下一个同辈元素或者上级元素
                if ($(item).hasClass($tr.attr('class').replace('tree-bg-color', '').replace(' ', '')) || treegrid.isPreNode('tree-level-', $tr.attr('class'), $(item).attr('class'))) {
                    return false;
                }
                $(item).hide();
            });
            event.stopPropagation();//阻止冒泡
        }).on('click', '.fa-chevron-circle-right', function (event) {
            /* 展开事件 */
            var $this = $(this);
            var $tr = $(this).parents('tr');
            $this.removeClass('fa-chevron-circle-right').addClass('fa-chevron-circle-down');
            //是否有下级
            if ($tr.next().is('tr') && isNextNode('tree-level-', $tr.attr('class'), $tr.next().attr('class'))) {
                var $trs = $tr.nextAll();
                $trs.each(function (i, item) {
                    if ($(item).hasClass($tr.attr('class').replace('tree-bg-color', '').replace(' ', ''))) {
                        return false;
                    }
                    $(item).find('.fa-chevron-circle-right').removeClass('fa-chevron-circle-right').addClass('fa-chevron-circle-down');
                    $(item).show();
                });
            } else {
                treegrid.addAjaxNodes($tr);
            }
            event.stopPropagation();//阻止冒泡
        }).on('click', 'tr', function () {
            if ($.isFunction(treegrid._callBack)) {
                treegrid._callBack($(this));
            }

        });

        return treegrid;
    }
})(jQuery);
