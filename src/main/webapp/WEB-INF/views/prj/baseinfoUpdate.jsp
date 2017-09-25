<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-项目管理</title>
    <%@ include file="../common/header.jsp" %>
</head>

<body class="no-skin">
<%@ include file="../common/top.jsp" %>

<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
                    <li class="active">系统管理</li>
                    <li class="active">项目管理</li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        项目管理
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 修改项目
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal" action="update.htm" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <input type="hidden" name="id" value="${prjBaseinfo.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">项目名称：</label>
                                <div class="col-sm-9">
                                    <input id="prj_name" name="prj_name" type="text" class="col-xs-10 col-sm-5" placeholder=""
                                           value="${prjBaseinfo.prj_name }"  ><label id="prj_nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">阶段：</label>
                                <div class="col-sm-9 ">
                                    <select name="progress" class="col-xs-10 col-sm-5">
                                        <option value="1" <c:if test="${prjBaseinfo.progress eq 1}"> selected</c:if>>入户</option>
                                        <option value="2" <c:if test="${prjBaseinfo.progress eq 2}"> selected</c:if>>签约</option>
                                        <option value="3" <c:if test="${prjBaseinfo.progress eq 3}"> selected</c:if>>交房</option>
                                    </select> <label id="targetTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">占地面积单位：</label>
                                <div class="col-sm-9 ">
                                    <select name="area_type" class="col-xs-10 col-sm-5">
                                        <option value="0" <c:if test="${prjBaseinfo.area_type eq 0}"> selected</c:if>>平方米</option>
                                        <option value="1" <c:if test="${prjBaseinfo.area_type eq 1}"> selected</c:if>>亩</option>
                                    </select> <label id="area_typeTip"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">统计方式：</label>
                                <div class="col-sm-9 ">
                                    <select name="stat_type" class="col-xs-10 col-sm-5">
                                        <option value="1" <c:if test="${prjBaseinfo.stat_type eq 1}"> selected</c:if>>按户统计</option>
                                        <option value="3" <c:if test="${prjBaseinfo.stat_type eq 3}"> selected</c:if>>按建筑面积统计</option>
                                        <option value="2" <c:if test="${prjBaseinfo.stat_type eq 2}"> selected</c:if>>按土地面积统计</option>
                                    </select> <label id="stat_typeTip"></label>
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

            <script type="text/javascript">
					$(document).ready(function() {
						$("#form").validate({
							//debug : true,
							errorElement : "label",
							errorClass : "valiError",
							errorPlacement : function(error, element) {
								error.appendTo($("#" + element.attr('id') + "Tip"));
							},
							rules : {
								prj_name : {
									required : true,
									maxlength : 100
								}
							},
							submitHandler : function(form) {
								form.submit();
							}
						});
					});

            </script>
</body>
</html>
