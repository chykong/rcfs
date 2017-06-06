<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>${webTitle }-汇报材料</title>
	<%@ include file="../common/header.jsp" %>

</head>

<body class="no-skin">
<%@ include file="../common/top.jsp" %>
<div class="main-container" id="main-container">
	<%@ include file="../common/menu.jsp" %>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
					<li class="active">汇报材料</li>

				</ul>
			</div>

			<div class="page-content">
				<!-- /.page-header -->
				<div class="page-header">
					<h1>
						${prjReport.title }

						<small>
							<i class="ace-icon fa fa-angle-double-right"></i>
							${bln:getProjectProgress(prjReport.progress)}
						</small>
					</h1>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div style="font-size: 16px;">
							${prjReport.content }
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn" type="button" onclick="history.back(-1)">
									<i class="ace-icon fa fa-undo bigger-110"></i> 返回
								</button>
							</div>
						</div>
					</div>
				</div>

				<!-- /.main-content -->
			</div>
		</div>
	</div>
</div>
<!-- /.main-container -->
<%@ include file="../common/js.jsp" %>
</body>
</html>
