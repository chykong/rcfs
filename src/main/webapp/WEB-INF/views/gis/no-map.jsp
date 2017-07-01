<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-地图</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/js.jsp"%>
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
						<li class="active">地图</li>
					</ul> <%@ include file="../common/navigate.jsp"%>
					<!-- /.breadcrumb -->
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<h4>该项目暂无地图,敬请期待</h4>

					<div class="main-box">
						<div class="middle">
						</div>
					</div>

					<div id="map_title" style="display: none;">
						<div style='height: 35px; line-height: 35px; font-size: 14px; margin-left: 5px;'>敬请期待</div>
					</div>
					<div id="map_content" style="display: none; font-family: 微软雅黑;">

					</div>

				</div>
				<!-- /.main-content -->
			</div>
			<!-- /.main-container -->
			<%@ include file="../common/js.jsp"%>

			<script type="text/javascript">
				$(function() {
				})
			</script>
</body>
</html>
