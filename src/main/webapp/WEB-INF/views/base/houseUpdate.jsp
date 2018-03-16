<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-项目参与机构人员</title>
    <%@ include file="../common/header.jsp" %>

    <link rel="stylesheet" href="<c:url value="/assets/webuploader-0.1.5/webuploader.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/assets/webuploader-0.1.5/style.css"/>"/>
    <style>
        .show-img{
            cursor: zoom-in;
        }
    </style>
</head>

<body class="no-skin">
<%@ include file="../common/top.jsp" %>
<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
                    <li class="active">户型管理</li>
                    <li class="active">修改户型</li>
                </ul>
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal"
                              action="update.htm" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <input type="hidden" name="id" value="${baseHouse.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">户型：</label>
                                <div class="col-sm-9">
                                    <input id="name" type="text" name="name"
                                           class="col-xs-10 col-sm-5" placeholder="" value="${baseHouse.name }"><label
                                        id="nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">户型图：</label>
                                <div class="col-sm-9">
                                    <c:choose>
                                        <c:when test="${empty baseHouse.houseImage}">
                                            <img id="show_img" class="hidden show-img" src="" style="height: 300px;"/>
                                            <input type="hidden" id="houseImage" name="houseImage" value="">
                                            <a id="del_bus" data-load="0" class="hidden btn btn-white btn-danger" data-toggle="modal">
                                                <i class="ace-icon fa fa-trash-o bigger-110"></i> 删除
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <img id="show_img" class="show-img" src="${imageServer}${baseHouse.houseImage}" style="height: 300px;"/>
                                            <input type="hidden" id="houseImage" name="houseImage" value="${baseHouse.houseImage}">
                                            <a id="del_bus" data-load="1" class="btn btn-white btn-danger" data-toggle="modal">
                                                <i class="ace-icon fa fa-trash-o bigger-110"></i> 删除
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                    <a id="importImg" class="btn btn-white btn-primary" >
                                        <i class="ace-icon fa fa-picture-o bigger-110"></i> 选择图片
                                    </a>
                                    <label id="bus_licenseTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">户型描述：</label>
                                <div class="col-sm-9">

                                    <textarea  id="houseDescribe" class="col-xs-10 col-sm-5  autosize" type="text"  name="houseDescribe" class="col-xs-10 col-sm-5"  value=""> ${baseHouse.houseDescribe } </textarea>

                                    <%--<input id="houseDescribe" type="text" --%>
                                           <%-->--%>
                                    <label id="houseDescribeTip"></label>
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-3 col-md-9">

                                    <button class="btn btn-primary" type="submit">
                                        <i class="ace-icon fa fa-save bigger-110"></i> 保存
                                    </button>
                                    <button class="btn" type="button" onclick="history.back(-1)">
                                        <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                                    </button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <!-- /.main-content -->
            </div>
            <div id="showImg-modal" class="modal fade" tabindex="-1">
                <div class="modal-dialog" style="width: 800px">
                    <div class="modal-content">
                        <div class="modal-header no-padding">
                            <div class="table-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    <span class="white">&times;</span>
                                </button>
                                查看户型图
                            </div>
                        </div>
                        <div class="modal-body no-padding">
                            <div class="widget-box" style="border: none">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <form id="show-form" action="#" method="post">
                                            <div class="form-group">
                                                <div class="col-xs-12">
                                                    <img id="zoom_img" src="" style="width: 98%;"/>
                                                </div>
                                                <div class="col-xs-12" align="center">
                                                    <button class="btn btn-white btn-primary" type="button"  data-dismiss="modal">
                                                        <i class="ace-icon fa fa-times bigger-110"></i> 关闭
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer no-margin-top"></div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <div id="uploadImg-modal" class="modal fade" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header no-padding">
                            <div class="table-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    <span class="white">&times;</span>
                                </button>
                                上传户型图
                            </div>
                        </div>
                        <div class="modal-body no-padding">
                            <div class="widget-box" style="border: none">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <form id="uploadImg-form" action="<c:url value="/common/upload"/>"
                                              enctype="multipart/form-data" method="post">
                                            <input name="file_type" type="hidden" value="houseImage"/>

                                            <div class="form-group">
                                                <div class="col-xs-12">
                                                    <input name="file" type="file" id="uploadImg-input"
                                                           accept=".jpg,.gif,.png"/>
                                                </div>
                                                <div class="col-xs-12">
                                                    <p class="center">上传文件格式为
                                                        <a class="red">.jpg,.gif,.png</a>。
                                                    </p>
                                                </div>
                                                <div class="col-xs-12" align="center">
                                                    <button class="btn btn-white btn-primary" type="submit">
                                                        <i class="ace-icon fa fa-cloud-upload bigger-110"></i> 上传
                                                    </button>
                                                    <button class="btn btn-white btn-primary" type="button"
                                                            id="close-modal" data-dismiss="modal">
                                                        <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer no-margin-top"></div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.main-container -->
            <%@ include file="../common/js.jsp" %>
            <script src="${staticServer}/assets/js/jquery.form.js"></script>
            <script src="<c:url value="/assets/webuploader-0.1.5/webuploader.js"/>"></script>
            <script type="text/javascript">
                function delete_img($file_item){
                    if ($file_item.hasClass('upload-state-done')) {
                        var file_path = $file_item.find('[name*=file_path]');
                        $.ajax({
                            url: '<c:url value="/common/delFile"/>',
                            data: {
                                path: file_path.val()
                            },
                            type: 'post',
                            success: function () {
                            }
                        })
                    }
                    $file_item.remove();
                }
                $(document).ready(function () {
                    $("#importImg").on('click',function(){
                        $('#uploadImg-modal').modal('show');
                    })
                    var uploader = WebUploader.create({
                        // 选完文件后，是否自动上传。
                        auto: false,
                        dnd: document.body,
                        disableGlobalDnd: true,
                        paste: document.body,

                        // swf文件路径
                        swf: '<c:url value="/assets/webuploader-0.1.5/Uploader.swf"/>',

                        // 文件接收服务端。
                        server: '<c:url value="/common/upload?file_type=meachan_attach"/>',

                        // 选择文件的按钮。可选。
                        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                        pick: '#filePicker',

                    });
                    $("#fileUpload").on('click', function () {
                        uploader.upload();
                    });

                    var $list = $('#fileList');
                    var $file_items = $list.find('.file-item');
                    var index = $file_items.length;
                    uploader.on('fileQueued', function (file) {
                        var $li = $(
                            '<div id="' + file.id + '" class="file-item thumbnail">' +
                            '<input type="hidden" name="mechanismAttachList[' + index + '].file_name" value=""/>' +
                            '<input type="hidden" name="mechanismAttachList[' + index + '].file_path" value=""/>' +
                            '<div class="show_bg"><i class="ace-icon fa fa-file-text-o"></i></div>' +
                            '<div data-id="' + file.id + '" class="del red"><i class="ace-icon fa fa-trash-o"></i>   删除</div>' +
                            '<div class="info">' + file.name + '</div>' +
                            '</div>'
                            ),
                            $img = $li.find('img');


                        // $list为容器jQuery实例
                        $list.append($li);

                        // 创建缩略图
                        // 如果为非图片文件，可以不用调用此方法。
                        // thumbnailWidth x thumbnailHeight 为 100 x 100
//                        uploader.makeThumb(file, function (error, src) {
//                            if (error) {
//                                $img.replaceWith('<span>不能预览</span>');
//                                return;
//                            }
//
//                            $img.attr('src', src);
//                        }, 100, 100);
                        $('#fileUpload').removeClass('hidden');
                        $('#loading').removeClass('hidden');
                        index++;
                    });

                    $list.on('click', '.del', function () {
                        var $that = $(this);
                        if ($that.hasClass('uploaded')) {
                            bootbox.confirm('<b style="color: red">该文件删除后无法恢复</b>，确定要删除该文件吗？', function (result) {
                                if (result) {
                                    var $file_item = $that.closest('.file-item');
                                    delete_img($file_item);
                                }
                            });
                        } else {
                            var $file_item = $that.closest('.file-item');
                            delete_img($file_item);
                            var id = $that.attr('data-id');
                            uploader.removeFile(id, true);

                        }
                        var $file_items = $('.file-item');
                        var flag =0;
                        $file_items.each(function(i,item){
                            if(!$(item).hasClass('upload-state-done')){
                                flag++;
                            }
                        })
                        if(flag == 0){
                            $('#fileUpload').addClass('hidden');
                            $('#loading').addClass('hidden');
                        }
                    });

                    // 文件上传过程中创建进度条实时显示。
                    uploader.on('uploadProgress', function (file, percentage) {
                        var $li = $('#' + file.id),
                            $percent = $li.find('.progress span');

                        // 避免重复创建
                        if (!$percent.length) {
                            $percent = $('<p class="progress"><span></span></p>')
                                .appendTo($li)
                                .find('span');
                        }

                        $percent.css('width', percentage * 100 + '%');
                    });

                    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
                    uploader.on('uploadSuccess', function (file, response) {
                        var result = response._raw;
                        var reg = /'/g;
                        result = result.replace(reg, '"');
                        var json = $.parseJSON(result);
                        var $file_item = $('#' + file.id);
                        $file_item.find('[name*=file_name]').val(json.original_name);
                        $file_item.find('[name*=file_path]').val(json.createFilepath + json.createFilename);
                        $file_item.addClass('upload-state-done');
                    });

                    // 文件上传失败，显示上传出错。
                    uploader.on('uploadError', function (file) {
                        var $li = $('#' + file.id),
                            $error = $li.find('div.error');

                        // 避免重复创建
                        if (!$error.length) {
                            $error = $('<div class="error"></div>').appendTo($li);
                        }

                        $error.text('上传失败');
                    });

                    // 完成上传完了，成功或者失败，先删除进度条。
                    uploader.on('uploadComplete', function (file) {
                        $('#' + file.id).find('.progress').remove();
                        $('#loading').addClass('hidden');
                        $('#fileUpload').addClass('hidden');
                    });

                    //上传图片
                    $('#uploadImg-input').ace_file_input({
                        style: 'well',
                        btn_choose: '点击选择图片',
                        btn_change: null,
                        no_icon: 'ace-icon fa fa-picture-o',
                        droppable: false,
                        allowExt: [ "png", "jpg", "jpeg", "gif" ],
                        thumbnail: 'large',
                        //large | fit
                        maxSize: 10240000,
                        before_remove: function () {
                            return true;
                        }
                    }).on('change', function () {
                        var $uploadImg = $('#uploadImg-input');
                        if ($uploadImg.val() == '') {
                            $uploadImg.ace_file_input('reset_input');
                        }
                    }).on('file.error.ace', function (ev, info) {
                        if (info.error_count['ext'] || info.error_count['mime'])
                            bootbox.alert("请选择正确的文件格式!");
                        if (info.error_count['size'])
                            bootbox.alert("文件大小不超过10M!");
                    });

                    $("#uploadImg-form").on('submit', function (e) {
                        var imgcheck = $("#uploadImg-input").val();
                        if (imgcheck == '') {
                            return false;
                        }
                        $("#uploadImg-form").ajaxSubmit({
                            type: 'post',
                            // 提交方式 get/post
                            url: '${dynamicServer}/common/upload.htm',
                            // 需要提交的 url
                            success: function (data) { // data 保存提交后返回的数据，一般为 json 数据
                                var dataObj = eval("(" + data + ")");
                                var file_path = dataObj.createFilepath;
                                var file_name = dataObj.createFilename;
                                $("#close-modal").click();
                                //$("#deleteImg").attr("style", "");
                                var show_url = '${imageServer}' + file_path + file_name;
                                $('#show_img').removeClass('hidden').attr('src', show_url);
                                $('#del_bus').removeClass('hidden');
                                $("#houseImage").val(file_path + file_name);
                            }
                        });
                        return false;
                    });
                    $('#del_bus').on('click', function () {
                        var ev = $("#houseImage").val();
                        var load = $(this).attr('data-load');
                        if(load == 1){
                            bootbox.confirm('该图片已上传，删除后不可恢复，确认删除吗？',function(result){
                                if(result){
                                    deleteImg(ev);
                                }
                            });
                        }else{
                            deleteImg(ev);
                        }

                    });
                    $('#show_img').on('click',function(){
                        $('#zoom_img').attr('src',$(this).attr('src'));
                        $('#showImg-modal').modal('show');
                    })

                    $("#form").validate({
                        //debug : true,
                        errorElement: "label",
                        errorClass: "valiError",
                        errorPlacement: function (error, element) {
                            error.appendTo($("#" + element.attr('id') + "Tip"));
                        },
                        rules: {
                            name: {
                                required: true,
                                maxlength: 50
                            },
                            houseDescribe:{
                                required: true,
                                maxlength: 500
                            }
                        },
                        messages: {}
                    });
                });
                function  deleteImg(url){
                    $.ajax({
                        type: "post",
                        url: "${dynamicServer}/common/delFile.htm",
                        data: {
                            path: url
                        },
                        success: function (data) {
                            var dataObj = eval("(" + data + ")");
                            if (dataObj.success) {
                                $("#houseImage").val("");
                                $('#show_img').addClass('hidden').attr('src', '');
                                $('#del_bus').addClass('hidden');
                            } else {
                                bootbox.alert("操作失败");
                            }
                        }
                    });
                }


            </script>
</body>
</html>
