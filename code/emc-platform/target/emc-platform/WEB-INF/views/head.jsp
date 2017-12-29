<%--
  Created by IntelliJ IDEA.
  User: lichao
  Date: 2017/5/5
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--头--%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta name="keywords" content="HUAK,能源管控中心,能管,能管后台">
<meta name="description" content="HUAK 能源管控中心">
<!--[if lt IE 9]>
<meta http-equiv="refresh" content="0;ie.html" />
<![endif]-->
<%--图标--%>
<link rel="shortcut icon" href="${platform}/static/Hplus/favicon.ico">
<%-- css--%>
<link href="${platform}/static/Hplus/css/plugins/chosen/chosen.css" rel="stylesheet">
<link href="${platform}/static/Hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${platform}/static/Hplus/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="${platform}/static/Hplus/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${platform}/static/Hplus/css/animate.css" rel="stylesheet">
<link href="${platform}/static/Hplus/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<link href="${platform}/static/Hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${platform}/static/Hplus/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="${platform}/static/Hplus/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
<link href="${platform}/static/Hplus/plugins/ztree/css/metroStyle/metroStyle.css"  rel="stylesheet">
<link href="${platform}/static/Hplus/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${platform}/static/Hplus/css/huak.css" rel="stylesheet">

<!-- jQuery -->
<script src="${platform}/static/Hplus/js/jquery.min.js?v=2.1.4"></script>
<%--bootstrap--%>
<script src="${platform}/static/Hplus/js/bootstrap.min.js?v=3.3.6"></script>
<%--jquery plugins--%>
<script src="${platform}/static/Hplus/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${platform}/static/Hplus/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${platform}/static/Hplus/js/plugins/layer/layer.min.js"></script>
<!-- jQuery Validation plugin javascript-->
<script src="${platform}/static/Hplus/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${platform}/static/Hplus/js/plugins/validate/messages_zh.min.js"></script>
<!-- Sweet alert -->
<script src="${platform}/static/Hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
<!-- layerDate plugin javascript -->
<script src="${platform}/static/Hplus/js/plugins/layer/laydate/laydate.js"></script>
<script src="${platform}/static/Hplus/js/plugins/layer/extend/layer.ext.js"></script>
<!-- Data picker -->
<script src="${platform}/static/Hplus/js/plugins/layer/laydate/laydate.js"></script>
<script  src="${platform}/static/Hplus/js/plugins/daterangepicker/moment.js"> </script>
<script  src="${platform}/static/Hplus/js/plugins/daterangepicker/daterangepicker.js"></script>
<script src="${platform}/static/Hplus/js/plugins/datapicker/bootstrap-datepicker.js"></script>

<!-- Toastr script -->
<script src="${platform}/static/Hplus/js/plugins/toastr/toastr.min.js"></script>

<%--进度条--%>
<script src="${platform}/static/Hplus/js/plugins/pace/pace.min.js"></script>
<%--icheck--%>
<script src="${platform}/static/Hplus/js/plugins/iCheck/icheck.min.js"></script>
<%--自定义--%>
<script src="${platform}/static/Hplus/js/hplus.js?v=4.1.0"></script>
<script src="${platform}/static/Hplus/js/contabs.js"></script>
<script src="${platform}/static/Hplus/js/content.js?v=1.0.0"></script>
<script src="${platform}/static/Hplus/js/pcctv.js"></script>
<script src="${platform}/static/Hplus/js/jquery-jtemplates.js"></script>
<!-- Bootstrap table -->
<script src="${platform}/static/Hplus/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<%--<script src="${platform}/static/Hplus/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>--%>
<script src="${platform}/static/Hplus/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<%--ztree--%>
<script src="${platform}/static/Hplus/plugins/ztree/js/jquery.ztree.core.js"></script>
<script src="${platform}/static/Hplus/plugins/ztree/js/jquery.ztree.excheck.js"></script>
<script src="${platform}/static/Hplus/plugins/ztree/js/jquery.ztree.exedit.js"></script>
<%--chosen--%>
<script src="${platform}/static/Hplus/js/plugins/chosen/chosen.jquery.js"></script>

<%--<script src="${platform}/static/Hplus/js/pagination.js"></script>
<script src="${platform}/static/Hplus/js/treegrid.js"></script>
<script src="${platform}/static/Hplus/js/pcctv.js"></script>
<script src="${platform}/static/Hplus/js/org.js"></script>--%>

<%--emc项目组脚本--%>
<script src="${platform}/static/Hplus/js/emc.js"></script>
<script src="${platform}/static/Hplus/js/tree.drop.town.box.js"></script>
<script src="${platform}/static/Hplus/js/tree.org.view.js"></script>
<script type="text/javascript">
    var _platform = '${platform}';
    layer.config({
        extend: ['skin/moon/style.css'] //加载新皮肤
        /*skin: 'layer-ext-moon' //一旦设定，所有弹层风格都采用此主题。*/
    });
    toastr.options = {
        closeButton: true,
        debug: true,
        progressBar: true,
        "positionClass": "toast-top-right",
        showDuration: 400,
        hideDuration: 1000,
        timeOut: 2000,
        extendedTimeOut: 1000,
        showEasing: "swing",
        hideEasing: "linear",
        showMethod: "fadeIn",
        hideMethod: "fadeOut"
    }


</script>
