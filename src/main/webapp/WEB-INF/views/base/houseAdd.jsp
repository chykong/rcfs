<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-户型管理</title>
    <%@ include file="../common/header.jsp" %>
    <link rel="stylesheet" href="<c:url value="/assets/webuploader-0.1.5/webuploader.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/assets/webuploader-0.1.5/style.css"/>"/>
    <style>
        .show-img {
            cursor: zoom-in;
            height: 300px;
            max-width: 41.66%;
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
                    <li class="active">新增户型</li>
                </ul>
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal"
                              action="add.htm" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">户型：</label>
                                <div class="col-sm-9">
                                    <input id="name" type="text" name="name"
                                           class="col-xs-10 col-sm-5" maxlength="100" x value=""><label
                                        id="nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">户型图：</label>
                                <div class="col-sm-9">
                                    <img id="show_img" class="hidden show-img" src=""/>
                                    <input type="hidden" id="houseImage" name="houseImage" value="">
                                    <a id="del_bus" class="hidden btn btn-white btn-danger" data-toggle="modal">
                                        <i class="ace-icon fa fa-trash-o bigger-110"></i> 删除
                                    </a>
                                    <a id="openImg" class="btn btn-white btn-primary" data-toggle="modal">
                                        <i class="ace-icon fa fa-picture-o bigger-110"></i> 选择图片
                                    </a>
                                    <label id="bus_licenseTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">户型描述：</label>
                                <div class="col-sm-9">
                                    <textarea id="houseDescribe" class="col-xs-10 col-sm-5  autosize"  type="text" name="houseDescribe" class="col-xs-10 col-sm-5" maxlength="500"></textarea>
                                    <%--<input --%>
                                            <%--x value="">--%>
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
                                                    <button class="btn btn-white btn-primary" type="button"
                                                            data-dismiss="modal">
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
                                            <input name="file_type" type="hidden" value="upload"/>

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
                $(document).ready(function () {
                    $('#openImg').on('click',function(){
                        $('#uploadImg-modal').modal('show');
                    })
                    //上传图片
                    $('#uploadImg-input').ace_file_input({
                        style: 'well',
                        btn_choose: '点击选择图片',
                        btn_change: null,
                        no_icon: 'ace-icon fa fa-picture-o',
                        droppable: false,
                        allowExt: ["png", "jpg", "jpeg", "gif"],
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
                        $.ajax({
                            type: "post",
                            url: "${dynamicServer}/common/delFile.htm",
                            data: {
                                path: ev
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
                    });
                    $('#show_img').on('click', function () {
                        $('#zoom_img').attr('src', $(this).attr('src'));
                        $('#showImg-modal').modal('show');
                    })

                    $("#form").validate({
                        //debug : true,
                        errorElement : "label",
                        errorClass : "valiError",
                        errorPlacement : function(error, element) {
                            error.appendTo($("#" + element.attr('id') + "Tip"));
                        },
                        rules : {
                            name: {
                                required : true,
                                maxlength : 50
                            },
                            houseDescribe:{
                                required : true,
                                maxlength : 500
                            }

                        },
                        messages : {
                        },
                        submitHandler : function(form) {
                            form.submit();
                        }
                    });
                });


            </script>
</body>
</html>
