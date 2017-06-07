<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-项目组别管理</title>
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
                    <li class="active">项目组别管理</li>
                </ul>
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        项目组别管理
                        <small><i class="ace-icon fa fa-angle-double-right"></i> 修改项目组别
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal" action="update.htm" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <input type="hidden" name="id" value="${prjGroup.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择项目：</label>
                                <div class="col-sm-9 ">
                                    <form:select path="prjGroup.prj_base_info_id" name="prj_base_info_id"
                                                 class="col-xs-10 col-sm-5"
                                                 id="prj_base_info_id">
                                        <option value="">请选择项目</option>
                                        <form:options items="${listProject }" itemValue="id" itemLabel="prj_name"/>
                                    </form:select>
                                    <label id="prj_base_info_idTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择区段：</label>
                                <div class="col-sm-9 ">
                                    <select id="section_id" name="section_id">
                                        <c:forEach items="${listSection}" var="section">
                                            <option value="${section.id}" <c:if
                                                    test="${section.id eq prjGroup.section_id}">selected</c:if>>${section.name}</option>
                                        </c:forEach>

                                    </select>
                                    <label id="section_idTip"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">组别名称：</label>
                                <div class="col-sm-9">
                                    <input id="name" name="name" type="text" class="col-xs-10 col-sm-5" placeholder=""
                                           value="${prjGroup.name }"><label id="nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">住户数：</label>
                                <div class="col-sm-9">
                                    <input id="total_homes" type="text" name="total_homes" class="col-xs-10 col-sm-5"
                                           placeholder=""
                                           value="${prjGroup.total_homes }"><label id="total_homesTip"></label>
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
					    $("#prj_base_info_id").change(function() {
		                $("#section_id").empty();
		                if( $("#prj_base_info_id").val()!=''){
		                var url = "${dynamicServer}/common/listSecionByPrj.htm?prj_base_info_id="+ $("#prj_base_info_id").val();
		                $.getJSON(url, function(data) {
			                cache : false,
					        $.each(data, function(i, item) {
					        $("#section_id").append("<option value='"+item["id"]+"'>"+item["name"]+"</option>");
							});
				        });
                        }
                        });


						$("#form").validate({
							//debug : true,
							errorElement : "label",
							errorClass : "valiError",
							errorPlacement : function(error, element) {
								error.appendTo($("#" + element.attr('id') + "Tip"));
							},
							rules : {
								prj_base_info_id : {
									required : true
								},
								section_id : {
									required : true
								},
								name : {
									required : true,
									maxlength : 20
								},
								total_homes : {
									required : true,
									maxlength : 10,
									number:true
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
