<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2016/8/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper wrapper-content">
    <div class="row col-sm-12 col-xs-12 col-md-12 col-lg-12">
        <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
            <form class="form-horizontal" id="seasonAddForm" rseasonAddFormole="form">
                <input type="hidden" id="today" value="${todayNow}">
                <div class="form-group">
                    <label class="col-sm-3  control-label"><span class="red">*</span>采暖季度：</label>

                    <div class="col-sm-8">
                        <select id="season" name="season" class="chosen-select form-control">
                            <option value="">请选择采暖季度</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>开始时间：</label>

                    <div class="col-sm-8">
                        <input id="start2" type="text" class="laydate-icon  form-control layer-date"  name="sDate"
                               placeholder="请选择开始时间"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="red">*</span>结束时间：</label>

                    <div class="col-sm-8">
                        <input id="end2" type="text" class="laydate-icon  form-control layer-date validateDate"  name="eDate"
                               placeholder="请选择结束时间"/>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script>
    //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
    $.validator.setDefaults({
        ignore: ":hidden:not(select)",//校验chosen
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function (element) {
            $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
        },
        errorElement: "span",
        errorPlacement: function (error, element) {
            if (element.is(":radio") || element.is(":checkbox")) {
                error.insertAfter(element.parent().parent());
            } else if(element.is("select")){
                error.insertAfter(element.next());
            }else{
                error.insertAfter(element);
            }
        },
        errorClass: "help-block m-b-none m-t-xs",
        validClass: "help-block m-b-none m-t-none"
    });

    //以下为官方示例
    $(function () {
        // validate signup form on keyup and submit
        var icon = "<i class='fa fa-times-circle'></i> ";
        var $form = $(top.document).find("#seasonAddForm");
        var today = $(top.document).find("#today").val();
        //初始化采暖季度下拉框
        var yearSpan = 10;//上下跨度
        var year = Number(today.toString().substr(0, 4));
        console.info(year)
        for (var i = 0 - yearSpan; i < yearSpan; i++) {
            var year1 = year + i;
            var year2 = year + i + 1;
            $(top.document).find("#season").append('<option value="' + year1 + '-' + year2 + '">' + year1 + '-' + year2 + '</option>');
        }
        $(top.document).find("#season").chosen();

        $(top.document).find("#season").on('change', function () {
            var season = $(this).val();
            var date1 = season.substr(0, 4) + "-11-15";
            var date2 = season.substr(5, 9) + "-03-15";
            $(top.document).find('#start').val(date1);
            $(top.document).find('#end').val(date2);
        });

        //初始化时间插件
        //日期范围限制

        var start = {
            elem: '#start2',
            format: 'YYYY-MM-DD ',
            //min: laydate.now(), //设定最小日期为当前日期
            max: '2099-06-16 ', //最大日期
            istime: true,
            istoday: false,
            choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };
        var end = {
            elem: '#end2',
            format: 'YYYY-MM-DD ',
            max: '2099-06-16',
            istime: true,
            istoday: false,
            choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };
        top.laydate(start);
        top.laydate(end);

        //下拉框js
        $(top.document).find(".chosen-select:not([name='searchComp'])").chosen();

        $.validator.addMethod("checkSeason", function (value, element) {
            var deferred = $.Deferred();//创建一个延迟对象
            console.log(value);
            var season = $(top.document).find(element).val();
            $.ajax({
                url: _web + '/season/checkname',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {name: value,comId:'${comId}'},
                dataType: 'json',
                success: function (result) {
                    if (!result.flag) {
                        deferred.reject();
                    } else {
                        deferred.resolve();
                    }
                }
            });
            //deferred.state()有3个状态:pending:还未结束,rejected:失败,resolved:成功
            return deferred.state() == "resolved" ? true : false;
        }, "采暖季已存在");

        //验证时间是否在采暖季度之间
        $.validator.addMethod("dateSeason", function (value, element, param) {

            var season = $(top.document).find(param).val();
            //alert("验证时间是否在采暖季度之间"+season);
            var date1 = season.substr(0, 4) + "-01-01";
            var date2 = season.substr(5, 9) + "-12-31";
            return this.optional(element) || ((value >= date1) && (value <= date2));
        }, icon + "开始或结束时间不在采暖季度之间");

        //验证结束时间是否大于开始时间
        $.validator.addMethod("isGt", function (value, element, param) {
            //alert("验证结束时间是否大于开始时间");
            var startDate = $(top.document).find(param).val();
            return this.optional(element) || (value > startDate);
        }, icon + "结束时间必须大于开始时间");

        //该采暖季的时间段 是否已经存在
        $.validator.addMethod("isAmong", function (value, element, param) {
            //alert("验证结束时间是否大于开始时间");
            var start = $(top.document).find(param).val();
            var deferred = $.Deferred();//创建一个延迟对象
            $.ajax({
                url: _web + '/season/check',
                type: 'POST',
                async: false,//要指定不能异步,必须等待后台服务校验完成再执行后续代码
                data: {sdate:start,edate:value,comId:'${comId}'},
                dataType: 'json',
                success: function (result) {
                    if (!result.flag) {
                        deferred.reject();
                    } else {
                        deferred.resolve();
                    }
                }
            }); return deferred.state() == "resolved" ? true : false;
        }, icon + "结束时间和开始时间格式不对,在其他采暖季之间或该采暖季已存在");
        //提示信息绑定
        $(top.document).find('input:not(:submit):not(:button)').mousedown(function () {
            //alert("提示绑定信息");
            $(this).closest('.form-group').removeClass('has-error');
            $(this).siblings('.help-block').remove();
        });
        //下拉框信息绑定
        $(top.document).find('select').change(function () {
            //alert("下拉框信息绑定");
            if ($(this).find('option:first').val() != $(this).val()) {
                $(this).siblings('.help-block').remove();
            }
            return false;
        });

        $form.validate({
            onsubmit: true,// 是否在提交是验证
            //移开光标:如果有内容,则进行验证
            onfocusout: function (element) {
                if ($(element).val() == null || $(element).val() == "") {
                    $(element).closest('.form-group').removeClass('has-error');
                    $(element).siblings('.help-block').remove();
                } else {
                    $(element).valid();
                }
            },
            onkeyup: false,// 是否在敲击键盘时验证
            rules: {
                season: {
                    required: true,
                    checkSeason: '#season'
                },
                sDate: {
                    required: true
                    //dateSeason: '#season'
                },
                eDate: {
                    required: true,
                    dateSeason: '#season',
                    isGt: '#start',
                    isAmong:'#start'
                }
            },
            messages: {
                season: {
                    required: icon + "请选择采暖季度"
                },
                sDate: {
                    required: icon + "请选择开始时间"
                },
                eDate: {
                    required: icon + "请选择结束时间"
                }
            },
            submitHandler: function () {
                var index = top.layer.load(1, {
                    shade: [0.1, '#fff'] //0.1透明度的白色背景
                });

                $.ajax({
                    url: _web + '/season/addvalue',
                    data: {name:$(top.document).find('#season').val(),
                        sdate:$(top.document).find('#start').val(),
                        edate:$(top.document).find('#end').val(),
                        comId:'${comId}'},
                    type: 'POST',
                    dataType: 'json',
                    success: function (result) {
                        // alert(result.flag);
                        if (result.flag) {
                            top.layer.closeAll();
                            top.layer.msg(result.msg);
                            querySeasonConfig();
                        } else {
                            top.layer.close(index);
                            top.layer.msg(result.msg);
                        }
                    }
                });
            }
        });

    });
</script>