jQuery(document).ready(function($){
    top.uploader = null;
    var $t = $(top.document);

    var server = _web + $("#upload-url").val();
    $btn = $t.find("#ctlBtn");
    $wrap = $t.find('#uploader'),
        $queue = $t.find('#thelist').appendTo( $wrap.find('.uploader-list') );

    var uploader = WebUploader.create({
        pick:{id:$t.find('#picker')},
        swf:  _web + '/static/js/webuploader/Uploader.swf',
        chunked: false,
        server: server,
        // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
        disableGlobalDnd: true,
        threads:1,
        fileNumLimit :5,
        accept : {
            title : 'Applications',
            extensions : 'xls,xlsx',
            mimeTypes : 'application/xls,application/xlsx'
        },
        fileSizeLimit: 2000 * 1024 * 1024,    // 200 M
        fileSingleSizeLimit: 100 * 1024 * 1024    // 50 M
    });
    top.uploader = uploader;
    $btn.on('click', function () {
        uploader.upload();
    });
    uploader.on( 'fileQueued', function( file ){
        if(checkfile(file)) {
            var $li = '<div id="' + file.id + '" class="item" >' +
                '<div class="info" style="width:160px;float: left;margin: 5px;font-size:12px;font-weight:bolder;height: 15px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;-o-text-overflow:ellipsis;">' + file.name + '</div>' +
                '<p class="state" style="float: left;margin: 5px;color: #a9a9a9;height: 15px;">等待上传...</p>&nbsp;<button class="webuploadDelbtn">删除</button></div>' +
                '<HR  style="FILTER: alpha(opacity=100,finishopacity=0,style=1);margin: 1px;" class="c' + file.id + '" width="100%" color=#987cb9 SIZE=3><br id="id' + file.id + '">';
            $queue.append($li);
        }
    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        //debugger;
        var $li = $t.find( '#'+file.id ),
        $percent = $li.find('.progress .progress-bar');
//        $percent.css("float","left");
        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<span class="progress progress-striped active" style="width: 30%;height: 18px;float: left;margin:5px 0px 0px 5px;">' +
                '<span class="progress-bar" role="progressbar" style="width: 0%;float: left;"> ' +
                '</span>' +
                '</span>').appendTo( $li ).find('.progress-bar');
        }
        $li.find('p.state').text('上传中');
        $li.find(".progress-bar").text(Math.round(percentage * 100) + '%');
        $percent.css( 'width', percentage * 100 + '%' );
    });
    //删除
    $t.on("click",'.webuploadDelbtn',function () {
        var $ele = $(this);
        var id = $ele.parent().attr("id");
        var file = uploader.getFile(id);
        uploader.removeFile(file);
    });

    uploader.on('fileDequeued', function (file) {
        var fullName = $t.find("#hiddenInput"+ file.id).val();
        if (fullName!=null) {
            $.post(webuploaderoptions.deleteServer, { fullName: fullName }, function (data) {
                alert(data.message);
            })
        }
        $t.find("#id"+file.id).remove();
        $t.find(".c"+file.id).remove();
        $t.find("#"+file.id).remove();
        $t.find("#hiddenInput"+ file.id).remove();

    })

    var checkfile = function(file){
        if(file.size>100 * 1024 * 1024){
            layer.msg('文件过大！');
            return false;
        }else{
            return true;
        }
    }

    //当某个文件上传到服务端响应后，会派送此事件来询问服务端响应是否有效。
    uploader.on("uploadAccept",function(object,ret){
        //debugger;
        //服务器响应了
        //ret._raw  类似于 data
        var data =ret.message;
        if(data.flag != "1"){
            uploader.reset();
            top.layer.msg(data.message);
            return false;

        }else{
            top.layer.msg("上传导入成功，请刷新页面查看！");
            return true;
        }

    })
    uploader.on( 'uploadSuccess', function( file ) {
        $t.find( '#'+file.id ).find('.webuploadDelbtn').remove();
        $t.find( '#'+file.id ).find('p.state').text('已上传');
    });
    uploader.on( 'uploadError', function( file ) {
        $t.find( '#'+file.id ).find('.webuploadDelbtn').remove();
        $t.find( '#'+file.id ).find('p.state').text('上传出错');
    });

    uploader.on( 'uploadComplete', function( file ) {
        $t.find( '#'+file.id ).find('.progress').fadeOut();
    });

});