<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-管理公司人员</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/datatables.jsp"%>

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
						<c:if test="${type==1}">
							<li class="active">管理公司人员</li>
						</c:if>
						<c:if test="${type==2}">
							<li class="active">项目参与人员</li>
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
													<button class="btn btn-primary btn-sm" id="btnSearch">
														<i class="ace-icon fa fa-search"></i> 刷新
													</button> <c:if test="${bln:isP('BaseContactsAdd')}">
														<button type="button" class="btn btn-success btn-sm"
															id="btnAdd">
															<i class="ace-icon fa fa-plus bigger-110"></i>新增
														</button>
													</c:if>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="col-xs-12">
							<table id="datatable"
								class="table table-bordered table-striped table-hover">
								<thead>
									<tr>
										<th width=60>序号</th>
										<th width=150>姓名</th>
										<th width=150>手机</th>
										<th width="140">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list }" var="contacts" varStatus="st">
										<tr>
											<td>${st.index+1 }</td>
											<td>${contacts.name }</td>
											<td>${contacts.mobile }</td>
											<td><c:if test="${bln:isP('BaseContactsUpdate')}">
													<a
														href="toUpdate.htm?id=${contacts.id }&backUrl=${backUrl}">
														修改 </a>
												</c:if> <c:if test="${bln:isP('BaseContactsDelete')}">
													<a href="javascript:delContacts(${contacts.id });"> 删除
													</a>
												</c:if></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.span -->
						<!-- /.row -->
						<div class="row">
							<div class="col-xs-12">${ pageNavigate.pageModel}</div>
						</div>
					</div>

				</div>
				<!-- /.main-content -->
			</div>
			<!-- /.main-container -->
			<%@ include file="../common/js.jsp"%>

			<script type="text/javascript">

				$(function() {
					$("#btnSearch").bind('click', searchContacts);
					$("#btnAdd").bind('click', addContacts);
				})

				// 查询方法
				var searchContacts = function() {
					var url = "index.htm?type="+${type};
					window.location = encodeURI(url);
				}
				// 删除
				var delContacts = function(id) {
					bootbox.confirm("你确定要删除吗？", function(result) {
						if (result) {
							window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}&type="+${type};
						}
					})
				}
				//新增
				var addContacts = function(id) {
					window.location = "toAdd.htm?backUrl=${backUrl }&type="+${type};
				}


        </script>
</body>
</html>
