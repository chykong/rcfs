<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-政策管理</title>
    <%@ include file="../common/header.jsp" %>
</head>

<body class="no-skin">
<%@ include file="../common/top.jsp" %>

<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
                    <li class="active">政策管理</li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        政策管理
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 修改政策
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal" action="update.htm" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <input type="hidden" name="id" value="${basePolicy.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">政策标题：</label>
                                <div class="col-sm-9">
                                    <input id="title" name="title" type="text" class="col-xs-10 col-sm-5" placeholder=""
                                           value="${basePolicy.title }" readonly=""><label
                                        id="titleTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">政策类型：</label>
                                <div class="col-sm-9">
                                    <script id="content" name="content"
                                            type="text/plain">${basePolicy.content }</script>
                                    </label>
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
            <%@ include file="../common/js.jsp" %>
            <%@ include file="../common/ueditor.jsp" %>

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
								title : {
									required : true,
									maxlength : 50
								}
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
