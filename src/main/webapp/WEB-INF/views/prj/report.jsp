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
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
                    <li class="active">汇报材料</li>
                </ul>
                <%@ include file="../common/navigate.jsp" %>
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
                                                </button>
                                                <c:if test="${bln:isP('PrjReportAdd')}">
                                                    <button type="button" class="btn btn-success btn-sm" id="btnAdd">
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
                <div class="row">
                    <div class="col-xs-12">${bln:getProcessTab('index.htm',prjReportSearchVO.progress)}
                    </div>
                </div>
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th width=60>序号</th>
                                <th>文件名称</th>
                                <th width="100">下载</th>
                                <th width=150>发布人</th>
                                <th width=140>发布时间</th>
                                <th width="140">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list }" var="report" varStatus="st">
                                <tr>
                                    <td>${st.index+1 }</td>
                                    <td>${report.title }</td>
                                    <td><a href="download.htm?id=${report.id }">下载</a></td>
                                    <td>${report.created_by }</td>
                                    <th width=120><fmt:formatDate value="${report.created_at}"
                                                                  pattern="yyyy-MM-dd HH:mm"/></th>
                                    <td><c:if test="${bln:isP('PrjReportUpdate')}">
                                        <a href="toUpdate.htm?id=${report.id }&backUrl=${backUrl}"> 修改 </a>
                                    </c:if> <c:if test="${bln:isP('PrjReportDelete')}">
                                        <a href="javascript:delReport(${report.id });"> 删除 </a>
                                    </c:if></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.span -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-xs-12">${ pageNavigate.pageModel}</div>
                </div>

            </div>
            <!-- /.main-content -->
        </div>
        <!-- /.main-container -->
        <%@ include file="../common/js.jsp" %>

        <script type="text/javascript">
				$(function() {
					$("#btnSearch").bind('click', searchReport);
					$("#btnAdd").bind('click', addReport);
				})

				// 查询方法
				var searchReport = function() {
					var url = "index.htm?";
					window.location = encodeURI(url);
				}
				// 删除
				var delReport = function(id) {
					bootbox.confirm("你确定要删除该汇报材料吗？", function(result) {
						if (result) {
							window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}";
						}
					})
				}
				//新增
				var addReport = function(id) {
				 	var project_progress=$(".active").find("input").val()
					window.location = 'toAdd.htm?progress=${prjReportSearchVO.progress}&backUrl=${backUrl }';
				}

        </script>
</body>
</html>
