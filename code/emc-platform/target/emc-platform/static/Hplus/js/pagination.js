/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/26<BR>
 * JQuery扩展分页组件
 *
 *
 * 页面源代码
 *
 * <div class="row">
 <div class="col-lg-6 form-group" style="padding-top: 7px;">
 <span>显示第1到第4条记录，总共10033条记录  每页显示10条记录</span>
 </div>
 <div class="col-lg-6" style="text-align: right">
 <div class="btn-group">
 <button type="button" class="btn btn-white"><i class="fa fa-chevron-left"></i>
 </button>
 <button class="btn btn-white">1</button>
 <button class="btn btn-white">...</button>
 <button class="btn btn-white">3</button>
 <button class="btn btn-white  active">4</button>
 <button class="btn btn-white">5</button>
 <button class="btn btn-white">...</button>
 <button class="btn btn-white">10</button>
 <button type="button" class="btn btn-white"><i class="fa fa-chevron-right"></i>
 </button>
 </div>
 <input type="text" class="page-control">
 <button class="btn btn-success">跳转</button>
 </div>
 </div>
 */
(function ($) {
    var Pagination = function (ele, options) {
        this.$element = ele;
        this.defaults = {
            pageNo: 1,//当前页
            pageSize: 10,//页大小
            rowTotal: 0,//总记录数
            pageTotal: 0,//总页数
            startRow: 1,//开始记录数
            endRow: 10,//结束记录数
            prevNo: 0,//显示的前一页
            nextNo: 0,//显示的下一页
            btnNo: 1,//按钮
            pageNoId: "pageNo",//页数id
            pageGoId: "page-go",
            _callBack: null//回调函数
        };
        this.options = $.extend({}, $.fn.pagination.defaults, options);
    }

    Pagination.prototype = {
        refactorPagination: function () {
            var pageHtml = '<div class="row"><div class="col-lg-6 form-group" style="padding-top: 7px;">';
            pageHtml += this.initSpan();
            pageHtml += '</div><div class="col-lg-6" style="text-align: right">';
            pageHtml += this.initBtnFalse();
            pageHtml += '</div></div>';
            this.$element.html(pageHtml);
        },
        initBtnFalse: function () {
            var btnHtml = '<div class="btn-group">';
            //是否有上一页
            btnHtml += this.pageNo == 1 ? '<button type="button" class="btn btn-white"><i class="fa fa-chevron-left"></i></button> ' : '<button type="button" class="btn btn-white page-prev"><i class="fa fa-chevron-left"></i></button> ';
            if (this.pageTotal <= 5) {//总页数<=5
                for (var i = 1; i <= this.pageTotal; i++) {
                    btnHtml += this.btnNo == i ? '<button class="btn btn-white page-num">' + i + '</button>' : '<button class="btn btn-white page-num">' + i + '</button>';
                }
            } else {//总页数>5
                if (this.btnNo < 4) {//后...
                    for (var i = 1; i <= this.btnNo + 1; i++) {
                        btnHtml += this.btnNo == i ? '<button class="btn btn-white page-num">' + i + '</button>' : '<button class="btn btn-white page-num">' + i + '</button>';
                    }
                    btnHtml += '<button class="btn btn-white page-after">...</button>';
                    btnHtml += '<button class="btn btn-white page-num">' + this.pageTotal + '</button>';
                } else if (this.btnNo > this.pageTotal - 3) {//c.前...
                    btnHtml += '<button class="btn btn-white page-num">1</button>';
                    btnHtml += '<button class="btn btn-white page-before">...</button>';
                    for (var i = this.btnNo - 1; i <= this.pageTotal; i++) {
                        btnHtml += this.btnNo == i ? '<button class="btn btn-white page-num">' + i + '</button>' : '<button class="btn btn-white page-num">' + i + '</button>';
                    }
                } else {//前后...
                    btnHtml += '<button class="btn btn-white page-num">1</button>';
                    btnHtml += '<button class="btn btn-white page-before">...</button>';
                    for (var i = this.btnNo - 1; i <= this.btnNo + 1; i++) {
                        btnHtml += this.btnNo == i ? '<button class="btn btn-white page-num">' + i + '</button>' : '<button class="btn btn-white page-num">' + i + '</button>';
                    }
                    btnHtml += '<button class="btn btn-white page-after">...</button>';
                    btnHtml += '<button class="btn btn-white page-num">' + this.pageTotal + '</button>';
                }
            }
            //是否有下一页
            btnHtml += this.pageNo == this.pageTotal ? '<button type="button" class="btn btn-white"><i class="fa fa-chevron-right"></i></button>' : '<button type="button" class="btn btn-white page-next"><i class="fa fa-chevron-right"></i></button>';
            btnHtml += '</div>';
            btnHtml += '<input type="text" id="'+this.pageGoId+'" class="page-control">';
            btnHtml += '<button class="btn btn-success page-go">跳转</button>';

            return btnHtml;
        },
        /* 初始化 */
        initPagination: function () {
            var pageHtml = '<div class="row"><div class="col-lg-6 form-group" style="padding-top: 7px;">';
            pageHtml += this.initSpan();
            pageHtml += '</div><div class="col-lg-6" style="text-align: right">';
            pageHtml += this.initBtn();
            pageHtml += '</div></div>';
            this.$element.html(pageHtml);
        },
        /* 初始化文字部分 */
        initSpan: function () {
            return '<span>显示第' + this.startRow + '到第' + this.endRow + '条记录，总共' + this.rowTotal + '条记录  每页显示' + this.pageSize + '条记录</span>';
        },

        /* 初始化按钮部分
         *
         * 1.总页数<=5
         * 2.总页数>5 a.后... b.前后... c.前...
         */
        initBtn: function () {
            var btnHtml = '<div class="btn-group">';
            //是否有上一页
            btnHtml += this.pageNo == 1 ? '<button type="button" class="btn btn-white"><i class="fa fa-chevron-left"></i></button> ' : '<button type="button" class="btn btn-white page-prev"><i class="fa fa-chevron-left"></i></button> ';
            if (this.pageTotal <= 5) {//总页数<=5
                for (var i = 1; i <= this.pageTotal; i++) {
                    btnHtml += this.pageNo == i ? '<button class="btn btn-white page-num active">' + i + '</button>' : '<button class="btn btn-white page-num">' + i + '</button>';
                }
            } else {//总页数>5
                if (this.pageNo < 4) {//后...
                    for (var i = 1; i <= this.pageNo + 1; i++) {
                        btnHtml += this.pageNo == i ? '<button class="btn btn-white page-num active">' + i + '</button>' : '<button class="btn btn-white page-num">' + i + '</button>';
                    }
                    btnHtml += '<button class="btn btn-white page-after">...</button>';
                    btnHtml += '<button class="btn btn-white page-num">' + this.pageTotal + '</button>';
                } else if (this.pageNo > this.pageTotal - 3) {//c.前...
                    btnHtml += '<button class="btn btn-white page-num">1</button>';
                    btnHtml += '<button class="btn btn-white page-before">...</button>';
                    for (var i = this.pageNo - 1; i <= this.pageTotal; i++) {
                        btnHtml += this.pageNo == i ? '<button class="btn btn-white page-num active">' + i + '</button>' : '<button class="btn btn-white page-num">' + i + '</button>';
                    }
                } else {//前后...
                    btnHtml += '<button class="btn btn-white page-num">1</button>';
                    btnHtml += '<button class="btn btn-white page-before">...</button>';
                    for (var i = this.pageNo - 1; i <= this.pageNo + 1; i++) {
                        btnHtml += this.pageNo == i ? '<button class="btn btn-white page-num active">' + i + '</button>' : '<button class="btn btn-white page-num">' + i + '</button>';
                    }
                    btnHtml += '<button class="btn btn-white page-after">...</button>';
                    btnHtml += '<button class="btn btn-white page-num">' + this.pageTotal + '</button>';
                }
            }
            //是否有下一页
            btnHtml += this.pageNo == this.pageTotal ? '<button type="button" class="btn btn-white"><i class="fa fa-chevron-right"></i></button>' : '<button type="button" class="btn btn-white page-next"><i class="fa fa-chevron-right"></i></button>';
            btnHtml += '</div>';
            btnHtml += '<input type="text" id="'+this.pageGoId+'" class="page-control">';
            btnHtml += '<button class="btn btn-success page-go">跳转</button>';

            return btnHtml;
        },
        /* 初始化参数 */
        initOptions: function (opts) {
            this.pageNo = (opts.pageNo == undefined || opts.pageNo == "" || opts.pageNo == null) ? 1 : opts.pageNo;
            this.pageSize = (opts.pageSize == "" || opts.pageSize == undefined || opts.pageSize == null) ? 10 : opts.pageSize;
            this.rowTotal = (opts.rowTotal == "" || opts.rowTotal == undefined || opts.rowTotal == null) ? 0 : opts.rowTotal;
            //总页数
            this.pageTotal = parseInt(this.rowTotal / this.pageSize);
            this.pageTotal = this.rowTotal % this.pageSize > 0 ? this.pageTotal + 1 : this.pageTotal;
            //开始数
            this.startRow = (this.pageNo - 1) * this.pageSize + 1;
            //结束数
            this.endRow = this.pageNo * this.pageSize;
            //上一页
            this.prevNo = (this.pageNo - 1) < 1 ? 1 : this.pageNo - 1;
            //下一页
            this.nextNo = (this.pageNo + 1) > this.pageTotal ? this.pageTotal : this.pageNo + 1;
            //
            this.btnNo = (opts.btnNo == undefined || opts.btnNo == "" || opts.btnNo == null) ? this.pageNo : opts.btnNo;
            this._callBack = opts._callBack;
            this.pageNoId = (opts.pageNoId == undefined || opts.pageNoId == "" || opts.pageNoId == null) ? "pageNo" : opts.pageNoId;
            this.pageGoId = (opts.pageGoId == undefined || opts.pageGoId == "" || opts.pageGoId == null) ? "page-go" : opts.pageGoId;
        }
    }
    $.fn.pagination = function (options) {
        var pagination = new Pagination(this, options);
        pagination.initOptions(options);
        pagination.initPagination();
        //事件委派数字分页
        this.off('click', '.page-num');
        this.on('click', '.page-num', function () {
            $("#" + pagination.pageNoId).val($(this).text());
            pagination._callBack();
        });
        //事件委派前...
        this.off('click', '.page-before');
        this.on('click', '.page-before', function () {
            var btnNo = pagination.btnNo - 3;
            pagination.initOptions({
                btnNo: parseInt(btnNo),
                pageNo: pagination.pageNo,
                pageSize: pagination.pageSize,
                rowTotal: pagination.rowTotal,
                _callBack: pagination._callBack,
                pageNoId: pagination.pageNoId,
                pageGoId: pagination.pageGoId
            });
            pagination.refactorPagination();
        });
        //事件委派后...
        this.off('click', '.page-after');
        this.on('click', '.page-after', function () {
            var btnNo = pagination.btnNo + 3;
            pagination.initOptions({
                btnNo: parseInt(btnNo),
                pageNo: pagination.pageNo,
                pageSize: pagination.pageSize,
                rowTotal: pagination.rowTotal,
                _callBack: pagination._callBack,
                pageNoId: pagination.pageNoId,
                pageGoId: pagination.pageGoId
            });
            pagination.refactorPagination();
        });
        //事件委派上一页
        this.off('click', '.page-prev');
        this.on('click', '.page-prev', function () {
            $("#" + pagination.pageNoId).val(pagination.pageNo - 1);
            pagination._callBack();
        });
        //事件委派下一页
        this.off('click', '.page-next');
        this.on('click', '.page-next', function () {
            $("#" + pagination.pageNoId).val(pagination.pageNo + 1);
            pagination._callBack();
        });
        //事件委派跳页
        this.off('click', '.page-go');
        this.on('click', '.page-go', function () {
            $("#" + pagination.pageNoId).val($("#" + pagination.pageGoId).val());
            pagination._callBack();
        });
        //事件委派输入限制
        this.off('blur', '#' + pagination.pageGoId);
        this.on('blur', '#' + pagination.pageGoId, function () {
            var pageNo = $(this).val();
            var g = /^[1-9]*[1-9][0-9]*$/;
            if (!g.test(pageNo)) {
                $("#" + pagination.pageGoId).val(1);
            } else {
                if (pageNo > pagination.pageTotal) {
                    $("#" + pagination.pageGoId).val(pagination.pageTotal);
                }
            }
        });
    }


    /* 暴露部分参数修改 */
    $.fn.pagination.on = function (options) {

    }


})(jQuery);
