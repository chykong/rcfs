<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-修改安置房概况</title>
    <%@ include file="../common/header.jsp" %>
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
                    <li class="active">安置房概况</li>
                        <li class="active">修改安置房概况</li>
                </ul>
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        安置房概况
                        <small><i
                                class="ace-icon fa fa-angle-double-right"></i> 修改安置房概况
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal"
                              action="update.htm" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <input type="hidden" name="prj_base_info_id" value="${basePlacement.prj_base_info_id}">
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <script id="content" name="content"
                                            type="text/plain">${basePlacement.content}
                                    </script>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">附件：</label>
                                <%--保存文件name和路径--%>
                                <input  type="hidden" id="filePath"  name="attach_path"  value="">
                                <div class="col-sm-9">
                                    <div style="height: 38px;">
                                        <input readonly type="text" id="attach_name" name="attach_name" value="${basePlacement.attach_name}"
                                               class="col-xs-3"/>
                                        <a href="#uploadImg-modal2" class="btn btn-white btn-primary" data-toggle="modal"
                                           style="margin-left: 5px">
                                            <i class="ace-icon fa fa-folder-open bigger-110"></i> 选择文件
                                        </a>
                                    </div>

                                    <label id="file_nameTip2"></label>
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
            <div id="uploadImg-modal2" class="modal fade" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header no-padding">
                            <div class="table-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    <span class="white">&times;</span>
                                </button>
                                上传安置房概况文档文件
                            </div>
                        </div>
                        <div class="modal-body no-padding">
                            <div class="widget-box" style="border: none">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <form id="uploadImg-form" action="<c:url value="/common/upload"/>"
                                              enctype="multipart/form-data"  method="post">
                                            <div class="form-group">
                                                <input  type="hidden"   name="file_type"  value="placement">
                                                <div class="col-xs-12">
                                                    <input name="file" type="file" id="uploadImg-input2"
                                                           accept=".doc,.docx,.xls,.xlsx,.ppt,.pptx,.txt,.pdf,.csv"/>
                                                </div>
                                                <div class="col-xs-12">
                                                    <p class="center">上传文件格式为
                                                        <a class="red">.doc,.docx,.xls,.xlsx,.ppt,.pptx,.txt,.pdf,.csv</a>。
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
            <%@ include file="../common/ueditor.jsp" %>
            <script src="${staticServer}/assets/js/jquery.form.js"></script>
            <script type="text/javascript">
                $(document).ready(function() {

                    $('#uploadImg-input2').ace_file_input({
                        style : 'well',
                        btn_choose : '点击选择文件',
                        btn_change : null,
                        no_icon : 'ace-icon fa fa-folder-open-o',
                        droppable : false,
                        allowExt : [ "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "pdf", "csv" ],
                        thumbnail : 'large',
                        //large | fit
                        maxSize : 10240000,
                        before_remove : function() {
                            return true;
                        }
                    }).on('change', function() {
                        var  $uploadInput=$("#uploadImg-input2");
                        if($uploadInput.val()==''){
                            $uploadInput.ace_file_input('reset_input')
                        }
                    }).on('file.error.ace', function(ev, info) {
                        if (info.error_count['ext'] || info.error_count['mime'])
                            bootbox.alert("请选择正确的文件格式!");
                        if (info.error_count['size'])
                            bootbox.alert("文件大小不超过10M!");
                    });
                    //点击上传之后执行的代码
                    $("#uploadImg-form").on('submit', function (e) {
                        var imgcheck = $("#uploadImg-input2").val();
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
                                var original_name = dataObj.original_name;
                                $("#close-modal").click();
                                $("#filePath").val(file_path + file_name);
                                $("#attach_name").val(original_name);
                            }
                        });
                        return false;
                    });

                    $("#form").validate({
                        //debug : true,
                        errorElement: "label",
                        errorClass: "valiError",
                        errorPlacement: function(error, element) {
                            error.appendTo($("#" + element.attr('id') + "Tip"));
                        },
                        rules: {
                        },
                        messages: {},
                        submitHandler: function(form) {
                            form.submit();
                        }
                    });

                    UE.getEditor('content', {
                        toolbars: ueditorToolbar1,
                        initialFrameHeight: 300,
                        imagePath: '${pageContext.request.contextPath}/'
                    });
                });

            </script>
</body>
</html>
