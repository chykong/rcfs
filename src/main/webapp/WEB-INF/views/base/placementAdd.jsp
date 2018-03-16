<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-管理公司人员</title>
    <%@ include file="../common/header.jsp"%>

</head>

<body class="no-skin">
<%@ include file="../common/top.jsp"%>
<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp"%>
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
                    <li class="active">安置房概况</li>
                    <li class="active">新增安置房概况</li>
                </ul>
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal" action="add.htm" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">概况：</label>
                                <div class="col-sm-9">
                                    <textarea id=content" name="content" type="text" class="col-xs-10 col-sm-5" placeholder="" value="" rows="5"></textarea><label id="contentTip">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">附件：</label>
                                <div class="col-sm-9">
                                    <input id="attach1" type="file" name="attach1" class="col-xs-10 col-sm-5" placeholder="" value=""><label
                                        id="attach1Tip"></label>
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
            <!-- /.main-container -->
            <%@ include file="../common/js.jsp"%>
            <%@ include file="../common/ueditor.jsp"%>
            <script type="text/javascript">
                $(document).ready(function() {
                    $("#form").validate({
                        //debug : true,
                        errorElement : "label",
                        errorClass : "valiError",
                        errorPlacement : function(error, element) {
                            error.appendTo($("#" + element.attr('id') + "Tip"));
                        },
                        rules : {
                            content : {
                                required : true,
                                maxlength : 20
                            }

                        },
                        messages : {
                        },
                        submitHandler : function(form) {
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

