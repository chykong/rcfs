<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>${site_name} 拆除腾退户信息管理 - 查看详细情况</title>
    <style>
        textarea {
            padding-left: 3px !important;
        }
    </style>
    <%@ include file="../common/header.jsp" %>
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-datepicker/bootstrap-datepicker3.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/assets/webuploader-0.1.5/webuploader.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/assets/webuploader-0.1.5/style.css"/>"/>
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
                    <li class="active">拆除腾退户信息管理</li>
                    <li class="active"><small>查看详细情况</small></li>
                </ul>
                <!-- /.breadcrumb -->
                <%@ include file="../common/navigate.jsp"%>
            </div>
            <div class="page-content">

                <div class="row">
                    <div class="col-xs-12">
                        <div class="tabbable">
                            <!-- #section:pages/faq -->
                            <ul class="nav nav-tabs padding-18 tab-size-bigger" id="myTab">
                                <li class="active" data-type="1">
                                    <a data-toggle="tab" href="#faq-tab-basic" aria-expanded="true">
                                        <i class="blue ace-icon fa fa-user bigger-120"></i>
                                        基本信息
                                    </a>
                                </li>
                                <li class="" data-type="3">
                                    <a data-toggle="tab" href="#faq-tab-jd" aria-expanded="true">
                                        <i class="blue ace-icon fa fa-bar-chart-o bigger-120"></i>
                                        进度信息
                                    </a>
                                </li>
                                <li class="" data-type="3">
                                    <a data-toggle="tab" href="#faq-tab-bc" aria-expanded="true">
                                        <i class="blue ace-icon fa fa-jpy bigger-120"></i>
                                        补偿信息
                                    </a>
                                </li>
                                <li class="" data-type="3">
                                    <a data-toggle="tab" href="#faq-tab-qt" aria-expanded="true">
                                        <i class="blue ace-icon fa fa-inbox bigger-120"></i>
                                        其他信息
                                    </a>
                                </li>
                                <li class="" data-type="4">
                                    <a id="toXc" data-toggle="tab" href="#faq-tab-xc" aria-expanded="true">
                                        <i class="blue ace-icon fa fa-image bigger-120"></i>
                                        现场照片
                                    </a>
                                </li>
                            </ul>
                            <form:form servletRelativeAction="/prj/preallocation/basic/update.htm" id="save-form" method="post"
                                       cssClass="form-horizontal" commandName="preallocation">
                                <div class="tab-content no-border padding-24">
                                    <!-- 基本情况 begin-->
                                    <div id="faq-tab-basic" class="tab-pane fade active in" data-type="1">
                                        <%@ include file="_formfields.jspf" %>
                                    </div>
                                    <div id="faq-tab-jd" class="tab-pane fade" data-type="2">
                                        <%@ include file="_jdformfields.jspf" %>
                                    </div>
                                    <div id="faq-tab-bc" class="tab-pane fade" data-type="3">
                                        <%@ include file="_bcformfields.jspf" %>
                                    </div>
                                    <div id="faq-tab-qt" class="tab-pane fade" data-type="4">
                                        <%@ include file="_inhost_formfields.jspf" %>
                                    </div>

                                    <div id="faq-tab-xc" class="tab-pane fade" data-type="5">
                                        <%@ include file="xc_photo.jspf" %>
                                    </div>
                                    <div class="clearfix form-actions">
                                        <div class="col-md-offset-3 col-xs-offset-3 col-md-9">
                                            <a id="btn-reset" class="btn" href="<c:url value="/prj/preallocation/basic/index.htm"/>">
                                                <i class="ace-icon fa fa-undo bigger-110"></i>
                                                返回
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../common/js.jsp" %>
<script src="<c:url value="/assets/webuploader-0.1.5/webuploader.js"/>"></script>

<script>
    $(function () {
        $("input").attr('readonly','true');
        $("input").attr('placehodler','0');
        $("select").attr('disabled','true');
        $("textarea").attr('readonly','true');

        $('[id*=filePicker]').addClass('hidden');
        $('.del').addClass('hidden');
        if($("#other_file_path").val() != '' && $("#other_file_name").val() != ''){
            $("#other_file_name").hide();
            $("#photoDiv").append('<a href="${imageServer}'+ $("#other_file_path").val() + '">' + $("#other_file_name").val()+ '</a>');
        }
        $("#delFile").addClass('hidden');
        $("#checkFile").addClass('hidden');

        countYjf();

        var $section = $("#section");
        var $group = $("#groups");
        var old_group = '${preallocation.groups}';
        $section.on('change', function () {
            if($(this).val() == ''){
                $group.empty();
                $group.append('<option value="">--请选择标段--</option>');
                return;
            }
            var url = '<c:url value="/common/listGroupBySec.htm"/>';
            $.ajax({
                url: url,
                data: {
                    section_id: $section.find(":checked").attr("data-id"),
                    is_search : 0
                },
                type: 'post',
                success: function (result) {
                    var json = eval('('+result + ')');
                    $group.empty();
                    if (json && json.length) {
                        $group.append('<option value="">--请选择--</option>');
                        $.each(json, function (i, group) {
                            if(old_group == group.name){
                                $group.append('<option value="' + group.name + '" selected>' + group.name + '</option>');
                            }else{
                                $group.append('<option value="' + group.name + '">' + group.name + '</option>');
                            }
                        });
                    }
                }
            });
        });
        $section.change();
    })
</script>
</body>
</html>
