<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-会议纪要</title>
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
						<li class="active">会议纪要</li>
					</ul>
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<!-- /.page-header -->
					<div class="row">
						<div class="col-xs-12">
							<form id="form" name="form" class="form-horizontal" action="update.htm" method="post">
								<input type="hidden" name="backUrl" value="${backUrl }">
								<div class="form-group">
									<label class="col-sm-3 control-label">标题：</label>
									<div class="col-sm-9"> 
										${prjMeeting.title }<label
											id="nameTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">内容：</label>
									<div class="col-sm-9">
										${prjMeeting.content }
										</label>
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
				
</body>
</html>
