<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-管理公司人员</title>
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
                    <li class="active">管理公司人员</li>
                    <li class="active">修改人员信息</li>
                </ul>
            </div>

            <div class="page-content">
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal"
                              action="update.htm" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <input type="hidden" name="id" value="${baseContacts.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名：</label>
                                <div class="col-sm-9">
                                    <input id="name" name="name" type="text"
                                           class="col-xs-10 col-sm-5" placeholder=""
                                           value="${baseContacts.name }" readonly="readonly"><label
                                        id="nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手机：</label>
                                <div class="col-sm-9">
                                    <input id="mobile" type="text" name="mobile"
                                           class="col-xs-10 col-sm-5" placeholder=""
                                           value="${baseContacts.mobile }"><label id="mobileTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">职务：</label>
                                <div class="col-sm-9">
                                    <input id="duty" type="text" name="duty" class="col-xs-10 col-sm-5" placeholder=""
                                           value="${baseContacts.duty }"><label
                                        id="dutyTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">职责：</label>
                                <div class="col-sm-9">
                                    <input id="project_duty" type="text" name="project_duty" class="col-xs-10 col-sm-5"
                                           placeholder="" value="${baseContacts.project_duty }"><label
                                        id="project_dutyTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-9">
                                    <input id="note" type="text" name="note" class="col-xs-10 col-sm-10" placeholder=""
                                           value="${baseContacts.note }"><label
                                        id="noteTip"></label>
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
					$(document).ready(
							function() {
								$("#form").validate(
										{
											//debug : true,
											errorElement : "label",
											errorClass : "valiError",
											errorPlacement : function(error,
													element) {
												error.appendTo($("#"
														+ element.attr('id')
														+ "Tip"));
											},
											rules : {
												name : {
									required : true,
									maxlength : 20
								},
								mobile : {
									required : true,
									maxlength : 11
								},
								duty : {
									maxlength : 100
								},
								project_duty : {
									maxlength : 100
								},
								note : {
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
