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
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        项目基本情况
                        <small><i
                                class="ace-icon fa fa-angle-double-right"></i> <c:if
                                test="${type eq 1}">
                            修改项目简介
                        </c:if> <c:if test="${type eq 2}">
                            修改工作流程
                        </c:if> <c:if test="${type eq 3}">
                            修改组织架构
                        </c:if>
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal"
                              action="updateProjectIntro.htm?type=${type}" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <script id="content" name="content"
                                            type="text/plain"><c:if
                                            test="${type eq 1}">${prjBaseinfo.introduction }    </c:if>
            <c:if test="${type eq 2}">${prjBaseinfo.flow } </c:if>
            <c:if test="${type eq 3}">${prjBaseinfo.architecture }</c:if>
                                    </script>
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
            <%@ include file="../common/js.jsp" %>
            <%@ include file="../common/ueditor.jsp" %>
            <script type="text/javascript">
					$(document).ready(function() {
                        $("#form").validate({
                            //debug : true,
                            errorElement: "label",
                            errorClass: "valiError",
                            errorPlacement: function(error, element) {
                                error.appendTo($("#" + element.attr('id') + "Tip"));
                            },
                            rules: {
                            },
                            messages: {},
                            submitHandler: function(form) {
                                form.submit();
                            }
                        });

                        UE.getEditor('content', {
                            toolbars: ueditorToolbar1,
                            initialFrameHeight: 300,
                            imagePath: '${pageContext.request.contextPath}/'
                        });
                    });

            </script>
</body>
</html>
