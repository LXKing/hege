<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${web}/static/css/webupload/webuploader.css">
    <link rel="stylesheet" type="text/css" href="${web}/static/css/webupload/mystyle.css">
    <%--<link href="${web}/script/skins/default/laydate.css"   type="text/css" rel="stylesheet">--%>
    <link href="${web}/script/need/laydate.css"  type="text/css"  rel="stylesheet">
    <script src="${web}/static/js/webuploader/webuploader.js"></script>
    <script src="${web}/script/huak.sys.file.add.js"></script>
</head>

<body class="gray-bg" >
<div class="wrapper wrapper-content animated fadeIn" >
    <input type="hidden" id="upload-url" value="${uploadUrl}">
    <div id="uploader" class="wu-example" >
        <!--用来存放文件信息-->
        <div id="thelist" class="uploader-list box4">
            <br />
            <div class="fold_box4"></div>
            <div class="fold2_box4"></div>
        </div>
        <div class="btnss">
            <button id="ctlBtn" class="btn-default" style="position: absolute; height: 40px;left: 420px;width: 83px; ">开始上传</button>
            <div id="picker" style=" position: absolute;height: 30px;left:330px; ">选择文件</div>
        </div>
    </div>

</div>
</body>

</html>
