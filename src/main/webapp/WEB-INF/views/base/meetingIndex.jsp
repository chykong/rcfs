<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-会议纪要</title>
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
                    <li class="active">会议纪要</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box widget-color-blue">
                           

                            <!-- /section:custom/widget-box.options -->
                            <div >
                                  <button type="button" class="btn btn-success btn-sm" id="btnAdd">
                                      <i class="ace-icon fa fa-plus bigger-110"></i>新增
                                  </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                	<div class="col-xs-12">
                		<ul>
                			<li class="active"><input type="hidden" value="1"/>前期准备阶段</li>
                			<li><input type="hidden" value="2"/>入户调查阶段</li>
                			<li><input type="hidden" value="3"/>预分方案制作阶段</li>
                			<li><input type="hidden" value="4"/>动迁准备阶段</li>
                			<li><input type="hidden" value="5"/>动迁阶段</li>
                			<li><input type="hidden" value="6"/>收尾阶段</li>
                		</ul>
                	</div>
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th width=60>序号</th>
                                <th width=150>标题</th>
                                <th width=150>发布人</th>
                                <th width=100>发布时间</th>
                                <th width="140">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list }" var="meeting" varStatus="st">
                                <tr>
                                    <td>${st.index+1 }</td>
                                    <td>${meeting.title }</td>
                                    <td>${meeting.created_by }</td>
                                    <th width=120><fmt:formatDate value="${meeting.created_at}"
                                                                  pattern="yyyy-MM-dd HH:mm"/></th>
                                    <td><c:if test="${bln:isP('BaseCompanyUpdate')}">
                                        <a href="toUpdate.htm?id=${company.id }&backUrl=${backUrl}"> 修改 </a>
                                    </c:if> <c:if test="${bln:isP('BaseCompanyDelete')}">
                                        <a href="javascript:delCompany(${company.id });"> 删除 </a>
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
					$("#btnSearch").bind('click', searchCompany);
					$("#btnAdd").bind('click', addMeeting);
				})

				// 查询方法
				var searchCompany = function() {
					var url = "index.htm?";
					window.location = encodeURI(url);
				}
				// 删除
				var delCompany = function(id) {
					bootbox.confirm("你确定要删除该公司吗？", function(result) {
						if (result) {
							window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}";
						}
					})
				}
				//新增
				var addMeeting = function(id) {
					var project_progress=$(".active:input").val()
					window.location = 'toAdd.htm?backUrl=${backUrl }&project_progress='+project_progress;
				}
        </script>
</body>
</html>
