<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-项目基本情况</title>
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
                    <li class="active">项目基本情况</li>
                    <c:if test="${type eq 1}">
                        <li class="active">项目简介</li>
                    </c:if>
                    <c:if test="${type eq 2}">
                        <li class="active">工作流程</li>
                    </c:if>
                    <c:if test="${type eq 3}">
                        <li class="active">组织架构</li>
                    </c:if>
                </ul>
                <!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        <c:if test="${type eq 1}">
                            项目简介
                        </c:if> <c:if test="${type eq 2}">
                        工作流程
                    </c:if> <c:if test="${type eq 3}">
                        组织架构
                    </c:if>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                       <div style="font-size: 16px;"> <c:if test="${type eq 1}">
                            ${prjBaseinfo.introduction}
                        </c:if>
                        <c:if test="${type eq 2}">
                            ${prjBaseinfo.flow}
                        </c:if>
                        <c:if test="${type eq 3}">
                            ${prjBaseinfo.architecture}
                        </c:if>
                       </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <c:if test="${bln:isP('PrjBaseIntroSave') and type eq 1}">
                                    <button class="btn btn-primary" type="click" id="btnUpdate">
                                        <i class="ace-icon fa fa-save bigger-110"></i> 修改
                                    </button>
                                </c:if>
                                <c:if test="${bln:isP('PrjBaseFlowSave') and type eq 2}">
                                    <button class="btn btn-primary" type="click" id="btnUpdate">
                                        <i class="ace-icon fa fa-save bigger-110"></i> 修改
                                    </button>
                                </c:if>
                                <c:if test="${bln:isP('PrjBaseArchiSave') and type eq 3}">
                                    <button class="btn btn-primary" type="click" id="btnUpdate">
                                        <i class="ace-icon fa fa-save bigger-110"></i> 修改
                                    </button>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.main-content -->
        </div>
        <!-- /.main-container -->
        <%@ include file="../common/js.jsp" %>

        <script type="text/javascript">
				$(function() {
					$("#btnUpdate").bind('click', updatePro);
				})
				//修改
				var updatePro = function() {
					window.location = "toUpdateProjectIntro.htm?backUrl=${backUrl}&type=${type}";
				}

        </script>
</body>
</html>
