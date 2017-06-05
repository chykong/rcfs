<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-汇报材料</title>
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
						<li class="active">汇报材料</li>
					</ul>
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<!-- /.page-header -->
					<div class="row">
						<div class="col-xs-12">
							<form id="form" name="form" class="form-horizontal" action="update.htm" method="post">
							<input type="hidden" name="id" value="${prjReport.id }">
								<input type="hidden" name="backUrl" value="${backUrl }">
								<div class="form-group">
									<label class="col-sm-3 control-label">阶段：</label>
									<div class="col-sm-9">
										<select id="progress" name="progress" value="${prjReport.progress }">
											<option value="1">前期准备阶段</option>
											<option value="2">入户调查阶段</option>
											<option value="3">预分方案制作阶段</option>
											<option value="4">动迁准备阶段</option>
											<option value="5">动迁阶段</option>
											<option value="6">收尾阶段</option>
										</select>
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">标题：</label>
									<div class="col-sm-9"> 
										<input id="title" name="title" type="text" class="col-xs-10 col-sm-5" placeholder="" value="${prjReport.title }"> <label
											id="nameTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">内容：</label>
									<div class="col-sm-9">
										<script id="introduction" name="architecture"
											type="text/plain">${prjReport.content }</script>
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
								title : {
									required : true,
									maxlength : 100
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
