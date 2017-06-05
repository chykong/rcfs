<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-项目基本情况</title>
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
						<li class="active">项目基本情况</li>
						<c:if test="${prjBaseinfo.type==1}">
							<li class="active">项目简介</li>
						</c:if>
						<c:if test="${prjBaseinfo.type==2}">
							<li class="active">工作流程</li>
						</c:if>
						<c:if test="${prjBaseinfo.type==3}">
							<li class="active">组织架构</li>
						</c:if>
					</ul>
					<!-- /.breadcrumb -->
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="widget-box widget-color-blue">
								<!-- #section:custom/widget-box.options -->
								<div class="widget-header">
									<h5 class="widget-title bigger lighter">
										<i class="ace-icon fa fa-table"></i> 操作面板
									</h5>
								</div>

								<!-- /section:custom/widget-box.options -->
								<div class="widget-body">
									<div class="widget-main">
										<table class="searchField" style="margin: 4px; padding: 4px;">
											<tr>
												<td>
													<button class="btn btn-success btn-sm" id="btnUpdate">
														<i class="ace-icon fa fa-plus bigger-110"></i> 修改
													</button>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
							<div class="widget-box widget-color-blue">

								<c:if test="${prjBaseinfo.type==1}">
									<div
										style="text-align: center; font-size: 20px; margin-top: 10px;">${prjBaseinfo.prj_name}</div>
									<div>${prjBaseinfo.introduction}</div>
								</c:if>
								<c:if test="${prjBaseinfo.type==2}">
									<div>${prjBaseinfo.flow}</div>
								</c:if>
								<c:if test="${prjBaseinfo.type==3}">
									<div>${prjBaseinfo.architecture}</div>
								</c:if>
							</div>
						</div>
					</div>

				</div>
				<!-- /.main-content -->
			</div>
			<!-- /.main-container -->
			<%@ include file="../common/js.jsp"%>

			<script type="text/javascript">
				$(function() {
					$("#btnAdd").bind('click', addCompany);
					$("#btnUpdate").bind('click', updatePro);
				})
				//新增
				var addCompany = function(id) {
					window.location = 'toAdd.htm?backUrl=${backUrl }';
				}
				//修改
				var updatePro = function() {
					window.location = "toUpdate.htm?id=" + ${prjBaseinfo.id}+"&backUrl=${backUrl}&type=" + ${prjBaseinfo.type};

				}
			</script>
</body>
</html>
