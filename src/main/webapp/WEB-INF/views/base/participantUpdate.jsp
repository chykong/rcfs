<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle}-项目参与人员</title>
<%@ include file="../common/header.jsp"%>
</head>

<body class="no-skin">
	<%@ include file="../common/top.jsp"%>

	<div class="main-container" id="main-container">
		<%@ include file="../common/menu.jsp"%>
		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
						<li class="active">项目参与人员</li>
						<li class="active">修改人员信息</li>
					</ul>
				</div>

				<div class="page-content">
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<form id="form" name="form" class="form-horizontal"
								action="update.htm" method="post">
								<input type="hidden" name="backUrl" value="${backUrl }">
								<input type="hidden" name="id" value="${baseParticipant.id }">
								<div class="form-group">
									<label class="col-sm-3 control-label">工作职能：</label>
									<div class="col-sm-9">
										<input id="duty" name="duty" type="text"
											class="col-xs-10 col-sm-5" placeholder=""
											value="${baseParticipant.duty }"><label id="nameTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">姓名：</label>
									<div class="col-sm-9">
										<input id="name" name="name" type="text"
											class="col-xs-10 col-sm-5" placeholder=""
											value="${baseParticipant.name }" ><label
											id="nameTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">手机：</label>
									<div class="col-sm-9">
										<input id="mobile" type="text" name="mobile"
											class="col-xs-10 col-sm-5" placeholder=""
											value="${baseParticipant.mobile }"><label
											id="mobileTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">组别：</label>
									<div class="col-sm-9">
										<input id="groups" type="text" name="groups"
											class="col-xs-10 col-sm-5" placeholder=""
											value="${baseParticipant.groups }"><label
											id="groupsTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">公司名称：</label>
									<div class="col-sm-9">
										<input id="company" type="text" name="company"
											class="col-xs-10 col-sm-5" placeholder=""
											value="${baseParticipant.company }"><label
											id="companyTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">职责：</label>
									<div class="col-sm-9">
										<input id="project_duty" type="text" name="project_duty"
											class="col-xs-10 col-sm-5" placeholder=""
											value="${baseParticipant.project_duty }"><label
											id="project_dutyTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">发布日期 ：</label>
									<div class="col-sm-9">
										<input id="release_date" type="text" name="release_date"
											class="col-xs-10 col-sm-5" placeholder=""
											value="${baseParticipant.release_date }"><label
											id="greated_atTip"></label>
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
				<link rel="stylesheet" type="text/css"
					href="${staticServer }/assets/datetimepicker/jquery.datetimepicker.css" />
				<script
					src="${staticServer }/assets/datetimepicker/jquery.datetimepicker.js"
					type="text/javascript"></script>
				<script type="text/javascript">
					$(document).ready(
							function() {
								$("#form").validate(
										{
											//debug : true,
											errorElement : "label",
											errorClass : "valiError",
											errorPlacement : function(error,
													element) {
												error.appendTo($("#"
														+ element.attr('id')
														+ "Tip"));
											},
											rules : {
												name : {
													required : true,
													maxlength : 20
												},
												mobile : {
													required : true,
													maxlength : 11
												},
												duty : {
													maxlength : 100
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
											submitHandler : function(form) {
												form.submit();
											}
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
