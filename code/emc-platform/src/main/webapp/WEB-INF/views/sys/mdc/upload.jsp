<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../../head.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${platform}/static/Hplus/css/plugins/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="${platform}/static/Hplus/css/demo/webuploader-demo.css">
    <link rel="stylesheet" type="text/css" href="${platform}/static/Hplus/css/mystyle.css">
    <link href="${platform}/static/Hplus/js/plugins/layer/laydate/need/laydate.css"  type="text/css"  rel="stylesheet">
    <link href="${platform}/static/Hplus/js/plugins/layer/laydate/skins/default/laydate.css"   type="text/css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 百度Web Uploader</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <script src="${platform}/static/Hplus/js/plugins/webuploader/webuploader.js"></script>
    <script src="${platform}/script/sys/huak.sys.file.add.js"></script>
    <style>
        .filelist{
            width: 200px;
            height:10px;
            display: table-cell;
            vertical-align: middle;
        }
    </style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeIn">
    <div id="uploader" class="wu-example">
        <!--用来存放文件信息-->
        <div id="thelist" class="uploader-list box4" style="width:500px;height: 300px;overflow-y:scroll; overflow-x:hidden;background-color: #b3d4fd">
            <br />
            <div class="fold_box4"></div>
            <div class="fold2_box4"></div>
        </div>
        <div class="btns">
            <button id="ctlBtn" class="btn btn-default" style="float: right;height: 38px;margin-right: 5px;">开始上传</button>
            <div id="picker" style="float: right;height: 30px;margin-left: 5px;">选择文件</div>
        </div>
    </div>

</div>
</body>

</html>
