<%--@elvariable id="site_name" type="java.lang.String"--%>
<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfToken"--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${site_name} 项目管理情况</title>
    <link rel="stylesheet" href="<c:url value="/assets/datatables/css/dataTables.bootstrap.css"/>"/>
    <link rel="stylesheet"
          href="<c:url value="/assets/datatables/extensions/FixedColumns/css/fixedColumns.dataTables.css"/>">
    <link rel="stylesheet"
          href="<c:url value="/assets/datatables/extensions/FixedColumns/css/fixedColumns.bootstrap.css"/>">

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
                    <li class="active">系统管理</li>
                    <li class="active">项目管理情况</li>
                    <li class="active">
                        <small>档案管理</small>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
                <%@ include file="../common/navigate.jsp" %>
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box widget-color-blue" id="widget-box-search">
                            <div class="widget-header">
                                <h5 class="widget-title bigger lighter">
                                    <i class="ace-icon fa fa-table"></i> 操作面板
                                </h5>
                            </div>
                            <div class="widget-body widget-color-blue">
                                <div class="widget-main no-padding">
                                    <c:url value="/prj/preallocation/archive/index.htm" var="index_url"/>
                                    <form:form action="${index_url}" method="post"
                                               cssClass="form-horizontal" role="form"
                                               modelAttribute="preallocationSearchVO"
                                               cssStyle="padding-top: 10px;" data-ajax="true">
                                        <div class="row">
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-4">ID号：</label>
                                                    <div class="col-xs-7 col-lg-8">
                                                        <input type="text" id="mapId"
                                                               class="col-xs-10 col-sm-10 col-lg-8" value=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-6">被拆除腾退人：</label>
                                                    <div class="col-xs-7 col-lg-6 no-padding-left">
                                                        <input type="text" id="hostName"
                                                               class="col-xs-12 col-sm-12 col-lg-12" value=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-5">房屋坐落：</label>
                                                    <div class="col-xs-7 col-lg-7">
                                                        <input type="text" id="location"
                                                               class="col-xs-10 col-sm-10 col-lg-8" value=""/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-4">标段：</label>
                                                    <div class="col-xs-7 col-lg-8">
                                                        <select class="col-xs-10 col-sm-10 col-lg-8" id="section"
                                                                name="section">
                                                            <option value="">--请选择--</option>
                                                                <%--<c:forEach items="${sectionList}" var="section">--%>
                                                                <%--<option value="${section.name}"--%>
                                                                <%--<c:if test="${preallocation.section eq section.name}">selected</c:if>--%>
                                                                <%--data-id="${section.id}">${section.name}</option>--%>
                                                                <%--</c:forEach>--%>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-6">组别：</label>
                                                    <div class="col-xs-7 col-lg-6 no-padding-left">
                                                        <select class="col-xs-12 col-sm-12 col-lg-12" id="groups"
                                                                name="groups">
                                                            <option value="">--请选择--</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-4  col-lg-4 col-lg-offset-8">
                                                    <button type="button" class="btn btn-primary btn-sm"
                                                            id="btn-search">
                                                        <i class="ace-icon fa fa-search"></i> 查询
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                        <div class="hr hr-18 dotted hr-double"></div>
                        <c:choose>
                            <c:when test="${__currentProject == null || __currentProject.landStatus == null ||
                                (__currentProject.landStatusValue == 2 && __currentProject.buildingTypeValue == 1)}">
                                <%@ include file="_archivedatatable.jsp" %>
                            </c:when>
                            <c:when test="${__currentProject != null && __currentProject.landStatusValue == 1}">
                                <%@ include file="_datatable_state.jsp" %>
                            </c:when>
                            <c:otherwise>
                                <c:set var="datatableName"
                                       value="_datatable_${__currentProject.landStatus.name().toLowerCase()}_${__currentProject.buildingType.name().toLowerCase()}.jsp"/>
                                <c:import url="${datatableName}" charEncoding="utf-8"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <!-- /.span -->
                </div>
            </div>
        </div>
    </div>
</div>
<div id="archive-modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    <p id="title"></p>
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <form action="<c:url value="/prj/preallocation/archive/updateArchive.htm"/>"
                                  class="form-horizontal" method="post" id="archive-form">
                                <input id="map_id" name="map_id" type="hidden" value="" />
                                <input id="host_name" name="host_name" type="hidden" value="" />
                                <input id="backUrl" name="backUrl" type="hidden" value="${backUrl}" />
                                <div class="row">
                                    <div class="col-xs-12" id="result-text">
                                        <div class="form-group">
                                            <label class="col-sm-3  control-label">
                                                档案柜号：
                                            </label>
                                            <div class="col-sm-9">
                                                <input id="archive_code" name="archive_code" class="col-xs-10 col-sm-10 col-lg-6" type="text"
                                                       placeholder="请输入档案柜号..." maxlength="40"/>
                                                <label id="archive_codeTip"></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" type="submit" id="result-btn">
                                            <i class="ace-icon fa fa-check bigger-110"></i> 确认归档
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer no-margin-top"></div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
</body>

<javascripts>
    <%@ include file="../common/js.jsp" %>

    <script src="<c:url value="/assets/datatables/js/jquery.dataTables.js"/>"></script>
    <script src="<c:url value="/assets/datatables/js/dataTables.bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/datatables/extensions/FixedColumns/js/dataTables.fixedColumns.js"/>"></script>
    <script>
        $(function () {

            var $section = $("#section");
            var $group = $("#groups");
            $section.on('change', function () {
                if ($(this).val() == '') {
                    $group.empty();
                    $group.append('<option value="">--请选择--</option>');
                    return;
                }
                var url = '<c:url value="/projects/getGroupsBySection?${_csrf.parameterName}=${_csrf.token}"/>';
                $.ajax({
                    url: url,
                    data: {
                        section_id: $section.find(":checked").attr("data-id"),
                        is_search: 1
                    },
                    type: 'post',
                    success: function (result) {
                        $group.empty();
                        if (result && result.length) {
                            $group.append('<option value="">--请选择--</option>');
                            $.each(result, function (i, group) {
                                $group.append('<option value="' + group.name + '">' + group.name + '</option>');
                            });
                        }
                    }
                });
            });

            $("#archive-form").validate({
//            debug : true,
                errorElement: "label",
                errorClass: "valiError",
                errorPlacement: function (error, element) {
                    error.appendTo($("#" + element.attr('id') + "Tip"));
                },
                ignore: "",
                rules: {
                    map_id: {
                        required: true
                    },
                    host_name: {
                        required: true
                    },
                    archive_code: {
                        required: true,
                        maxlength: 50
                    }
                },
                submitHandler:function(){
                    return true;
                }
            });
        });
    </script>
</javascripts>
</html>
