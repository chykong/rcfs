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
                                <div class="widget-toolbar">
                                    <strong id="show-head">收起</strong>
                                    <a href="#" id="coll-btn" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="widget-body widget-color-blue">
                                <div class="widget-main no-padding">
                                    <c:url value="/prj/preallocation/archive/index.htm" var="index_url"/>
                                    <form:form action="${index_url}" method="post"
                                               cssClass="form-horizontal" role="form" modelAttribute="preallocationSearchVO"
                                               cssStyle="padding-top: 10px;" data-ajax="true">
                                        <div class="row">
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-4">编号：</label>
                                                    <div class="col-xs-7 col-lg-8">
                                                        <input type="text" id="map_id" class="col-xs-10 col-sm-10 col-lg-8" value=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-6">被拆除腾退人：</label>
                                                    <div class="col-xs-7 col-lg-6 no-padding-left">
                                                        <input type="text" id="host_name" class="col-xs-12 col-sm-12 col-lg-8" value=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-5">房屋坐落：</label>
                                                    <div class="col-xs-7 col-lg-7">
                                                        <input type="text" id="location" class="col-xs-10 col-sm-10 col-lg-8" value=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-5">房屋状态：</label>
                                                    <div class="col-xs-7 col-lg-7">
                                                        <select class="col-xs-10 col-sm-10 col-lg-8" id="status" name="section">
                                                            <option value="">--全部--</option>
                                                            <option value="0">未入户</option>
                                                            <option value="10">已入户</option>
                                                            <option value="20">已签约</option>
                                                            <option value="30">已审核</option>
                                                            <option value="40">已交房</option>
                                                            <option value="50">已拆除</option>
                                                            <option value="60">已放款</option>
                                                            <option value="70">已归档</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-4">镇：</label>
                                                    <div class="col-xs-7 col-lg-8">
                                                        <form:select path="town" cssClass="col-xs-10 col-sm-10 col-lg-8">
                                                            <form:option value="">--全部--</form:option>
                                                            <form:options items="${townList}" itemLabel="content" itemValue="value"></form:options>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-6">村：</label>
                                                    <div class="col-xs-7 col-lg-6 no-padding-left">
                                                        <form:select path="village" cssClass="col-xs-10 col-sm-10 col-lg-8">
                                                            <form:option value="">--全部--</form:option>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-5">标段：</label>
                                                    <div class="col-xs-7 col-lg-7">
                                                        <select class="col-xs-10 col-sm-10 col-lg-8" id="section"
                                                                name="section">
                                                            <option value="">--全部--</option>
                                                            <c:forEach items="${sectionList}" var="section">
                                                                <option value="${section.name}" <c:if test="${preallocationSearchVO.section eq section.name}">selected</c:if> data-id="${section.id}">${section.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-5">组别：</label>
                                                    <div class="col-xs-7 col-lg-7">
                                                        <select class="col-xs-12 col-sm-12 col-lg-8" id="groups" name="groups">
                                                            <option value="">--全部--</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-4  col-lg-4 col-lg-offset-8">
                                                    <button type="button" class="btn btn-primary btn-sm" id="btn-search">
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
                        <div style="position: relative">
                            <div id="foo" style="position:absolute;width: 100%;z-index: 9999;left: 34%;top:125px;"><h4 style="padding-left: 10%">加载中...</h4></div>
                        <%@ include file="_archivedatatable.jsp" %>
                        </div>
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
                            <form action="<c:url value="/prj/preallocation/archive/updateArchive.htm"/>" enctype="multipart/form-data"
                                  class="form-horizontal" method="post" id="archive-form">
                                <input id="archive_map_id" name="map_id" type="hidden" value="" />
                                <input id="archive_host_name" name="host_name" type="hidden" value="" />
                                <input id="archive_file_path" name="file_path" type="hidden" value="" />
                                <input id="archive_file_name" name="file_name" type="hidden" value="" />
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
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <input name="file" type="file" id="material-input"
                                                       accept="application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>
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
    <script src="<c:url value="/assets/js/jquery-ui-spin.js"/>"></script>

    <script src="<c:url value="/assets/datatables/js/dataTables.bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/datatables/extensions/FixedColumns/js/dataTables.fixedColumns.js"/>"></script>
    <script>
        $(function () {
            var opts = {
                lines: 11 // The number of lines to draw
                , length: 18 // The length of each line
                , width: 14 // The line thickness
                , radius: 32 // The radius of the inner circle
                , scale: 0.5 // Scales overall size of the spinner
                , corners: 1 // Corner roundness (0..1)
                , color: '#000' // #rgb or #rrggbb or array of colors
                , opacity: 0.25 // Opacity of the lines
                , rotate: 0 // The rotation offset
                , direction: 1 // 1: clockwise, -1: counterclockwise
                , speed: 1 // Rounds per second
                , trail: 60 // Afterglow percentage
                , fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
                , zIndex: 2e9 // The z-index (defaults to 2000000000)
                , className: 'spinner' // The CSS class to assign to the spinner
                , top: '0%' // Top position relative to parent
                , left: '5%' // Left position relative to parent
                , shadow: false // Whether to render a shadow
                , hwaccel: false // Whether to use hardware acceleration
                , position: 'absolute' // Element positioning
            }
            var target = document.getElementById('foo')
            var spinner = new Spinner(opts).spin(target);
            $("#coll-btn").on('click',function(){
                if($(this).find('i').hasClass('fa-chevron-up')){
                    $('#show-head').html('展开');
                }else{
                    $('#show-head').html('收起');
                }
            })
            var $section = $("#section");
            var $group = $("#groups");
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
                        section_id: $section.find(":checked").attr("data-id")
                    },
                    type: 'post',
                    success: function (result) {
                        var json = eval('('+result + ')');
                        $group.empty();
                        if (json && json.length) {
                            $group.append('<option value="">--全部--</option>');
                            $.each(json, function (i, group) {
                                $group.append('<option value="' + group.name + '">' + group.name + '</option>');
                            });
                        }
                    }
                });
            });

            var $town = $("#town");
            var $village = $("#village");
            $town.on('change', function () {
                if($(this).val() == ''){
                    $village.empty();
                    $village.append('<option value="">--请选择镇--</option>');
                    return;
                }
                var url = '<c:url value="/prj/preallocation/basic/getVillageByTown.htm"/>';
                $.ajax({
                    url: url,
                    data: {
                        town: $town.val()
                    },
                    type: 'post',
                    success: function (result) {
                        var json = eval('('+result + ')');
                        $village.empty();
                        if (json && json.length) {
                            $village.append('<option value="">--全部--</option>');
                            $.each(json, function (i, village) {
                                $village.append('<option value="' + village.value + '">' + village.content + '</option>');
                            });
                        }
                    }
                });
            });
            $('#material-input').ace_file_input({
                style: 'well',
                btn_choose: '点击选择word文件',
                btn_change: null,
                no_icon: 'ace-icon fa fa-folder-open-o',
                droppable: false,
                thumbnail: 'large',
                maxSize: 1024 * 1024 * 10,
                before_remove: function () {
                    return true;
                }
            }).on('change', function () {
                var $material_input = $('#material-input');
                if ($material_input.val() == '') {
                    $material_input.ace_file_input('reset_input');
                }
            }).on('file.error.ace', function (ev, info) {
                if (info.error_count['size'])
                    $.notify({message: "文件大小不超过10M!!", z_index: 1051});
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
