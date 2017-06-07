<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-项目参与人员</title>
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
						<li class="active">项目参与人员</li>
						<li class="active">新增参与人员</li>
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
									<label class="col-sm-3 control-label">工作职能：</label>
									<div class="col-sm-9">
										<input id="duty" type="text" name="duty"
											class="col-xs-10 col-sm-5" placeholder="" value=""><label
											id="dutyTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">姓名：</label>
									<div class="col-sm-9">
										<input id="name" name="name" type="text"
											class="col-xs-10 col-sm-5" placeholder="" value=""> <label
											id="nameTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">手机：</label>
									<div class="col-sm-9">
										<input id="mobile" type="text" name="mobile"
											class="col-xs-10 col-sm-5" placeholder="" value=""><label
											id="mobileTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">组别：</label>
									<div class="col-sm-9">
										<input id="groups" type="text" name="groups"
											class="col-xs-10 col-sm-5" placeholder="" value=""><label
											id="groupsTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">公司名称：</label>
									<div class="col-sm-9">
										<input id="company" type="text" name="company"
											class="col-xs-10 col-sm-5" placeholder="" value=""><label
											id="companyTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">工作职责：</label>
									<div class="col-sm-9">
										<input id="project_duty" type="text" name="project_duty"
											class="col-xs-10 col-sm-5" placeholder="" value=""><label
											id="project_dutyTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">发布时间：</label>
									<div class="col-sm-9">
										<input id="release_date" type="text" name="release_date"
											class="col-xs-10 col-sm-5" placeholder="" value="${release_date }"><label
											id="release_dateTip"></label>
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
				<link rel="stylesheet" type="text/css"
					href="${staticServer }/assets/datetimepicker/jquery.datetimepicker.css" />
				<script
					src="${staticServer }/assets/datetimepicker/jquery.datetimepicker.js"
					type="text/javascript"></script>
				<script type="text/javascript">
					$(document)
							.ready(
									function() {
										$("#form")
												.validate(
														{
															//debug : true,
															errorElement : "label",
															errorClass : "valiError",
															errorPlacement : function(
																	error,
																	element) {
																error
																		.appendTo($("#"
																				+ element
																						.attr('id')
																				+ "Tip"));
															},
															rules : {
																duty : {
																	maxlength : 100
																},
																name : {
																	required : true,
																	maxlength : 20
																},
																mobile : {
																	required : true,
																	maxlength : 11
																},
																groups : {
																	maxlength : 100
																},
																company : {
																	maxlength : 100
																},
																project_duty : {
																	maxlength : 100
																},
																release_date : {
																	maxlength : 10
																}
															},
															messages : {},
															submitHandler : function(
																	form) {
																form.submit();
															}
														});

										UE
												.getEditor(
														'content',
														{
															toolbars : ueditorToolbar1,
															initialFrameHeight : 300,
															imagePath : '${pageContext.request.contextPath}/'
														});
									});

					//时间
					$('#release_date').datetimepicker({
						lang : 'ch',
						timepicker : false,
						format : 'Y-m-d',
						formatDate : 'Y-m-d'
					});
				</script>
</body>
</html>
