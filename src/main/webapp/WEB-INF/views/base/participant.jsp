<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-项目参与人员</title>
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
                     <li class="active">项目参与人员</li>
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
                                            <td>姓名：</td>
                                            <td><input type="text" id="txtName" class="form-control input-middle"
                                                       placeholder=""
                                                       value="${baseContactsSearchVO.name }"></td>
                                            <td>手机号：</td>
                                            <td><input type="text" id="txtMobile" class="form-control input-middle"
                                                       placeholder=""
                                                       value="${baseContactsSearchVO.mobile }"></td>
                                            <td>
                                                <button class="btn btn-primary btn-sm" id="btnSearch">
                                                    <i class="ace-icon fa fa-search"></i> 查询
                                                </button>
                                                <c:if test="${bln:isP('BaseParticipantAdd')}">
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
                               class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th width=150>工作职能</th>
                                <th width=100>姓名</th>
                                <th width=130>手机号</th>
                                <th width=100>组别</th>
                                <th width=150>公司名称</th>
                                <th width=150>工作职责</th>
                                <th width=150>发布人</th>
                                <th width=250>发布日期</th>
                                <th width="140">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list }" var="participant" varStatus="st">
                                <tr>
                                    <td>${participant.project_duty }</td>
                                    <td>${participant.name }</td>
                                    <td>${participant.mobile }</td>
                                    <td>${participant.groups }</td>
                                    <td>${participant.company }</td>
                                    <td>${participant.duty }</td>
                                    <td>${participant.created_by}</td>
                                    <td>${participant.release_date }</td>
                                    <td><c:if test="${bln:isP('BaseParticipantUpdate')}">
                                        <a
                                                href="toUpdate.htm?id=${participant.id }&backUrl=${backUrl}">
                                            修改 </a>
                                    </c:if> <c:if test="${bln:isP('BaseParticipantDelete')}">
                                        <a href="javascript:delContacts(${participant.id });"> 删除
                                        </a>
                                    </c:if></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">${ pageNavigate.pageModel}</div>
                    </div>
                </div>
            </div>
            <!-- /.main-content -->
        </div>
    </div>
</div>
<!-- /.main-container -->
<%@ include file="../common/js.jsp" %>

<script type="text/javascript">

				$(function() {
					$("#btnSearch").bind('click', searchContacts);
					$("#btnAdd").bind('click', addContacts);
				})

				// 查询方法
				var searchContacts = function() {
					var url = "index.htm?";
					if ($("#txtName").val() != '')
						url += "&name=" + $("#txtName").val();
					if ($("#txtMobile").val() != '')
						url += "&mobile=" + $("#txtMobile").val();
					window.location = encodeURI(url);
				}
				// 删除
				var delContacts = function(id) {
					bootbox.confirm("你确定要删除吗？", function(result) {
						if (result) {
							window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}";
						}
					})
				}
				//新增
				var addContacts = function(id) {
					window.location = "toAdd.htm?backUrl=${backUrl }";
				}


</script>
</body>
</html>
