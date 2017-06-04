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
                    <li class="active">公司管理</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box" id="widget-box-search">
                            <h4 class="pull-left">操作面板</h4>
                            <div class="widget-header">
                                <div class="widget-toolbar">
                                    <strong>操作面板</strong>
                                    <a href="#" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main no-padding">
                                    <c:url value="/prj/preallocation/basic/index.htm" var="index_url"/>
                                    <form:form action="${index_url}" method="post"
                                               cssClass="form-horizontal" role="form" modelAttribute="preallocationSearchVO"
                                               cssStyle="padding-top: 10px;" data-ajax="true">
                                        <div class="row">
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-4">ID号：</label>
                                                    <div class="col-xs-7 col-lg-8">
                                                        <input type="text" id="mapId" class="col-xs-10 col-sm-10 col-lg-8" value=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-4">被拆迁人：</label>
                                                    <div class="col-xs-7 col-lg-8">
                                                        <input type="text" id="hostName" class="col-xs-10 col-sm-10 col-lg-8" value=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-4">房屋坐落：</label>
                                                    <div class="col-xs-7 col-lg-8">
                                                        <input type="text" id="location" class="col-xs-10 col-sm-10 col-lg-8" value=""/>
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
                                                    <label class="control-label col-xs-5 col-lg-4">组别：</label>
                                                    <div class="col-xs-7 col-lg-8">
                                                        <select class="col-xs-10 col-sm-10 col-lg-8" id="groups" name="groups">
                                                            <option value="">--请选择--</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-6  col-lg-4 col-lg-offset-8">
                                                    <button type="button" class="btn btn-primary btn-sm" id="btn-search">
                                                        <i class="ace-icon fa fa-search"></i> 查询
                                                    </button>
                                                    <a href="<c:url value="/preallocations/basic/add"/>" class="btn btn-success btn-sm">
                                                        <i class="ace-icon fa fa-plus bigger-110"></i>新增
                                                    </a>
                                                    <a href="#import-modal" class="btn btn-warning btn-sm" data-toggle="modal">
                                                        <i class="ace-icon fa fa-file-excel-o bigger-110"></i>导入基本信息
                                                    </a>
                                                    <a href="#hj-modal" class="btn btn-warning btn-sm" data-toggle="modal">
                                                        <i class="ace-icon fa fa-file-excel-o bigger-110"></i>导入户籍信息
                                                    </a>
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
                                <%@ include file="_datatable.jsp" %>
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

<div id="import-modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    导入拆迁户基本情况
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="form-group">
                                <div class="col-xs-12" style="margin-bottom: 10px">
                                    <span>请下载模板，按照模板格式整理数据：</span>
                                    <a href="<c:url value="/preallocations/basic/importTemp?type=1"/>" target="_blank">
                                        导入拆迁户基本情况模板.xls
                                    </a>
                                </div>
                            </div>
                            <c:url value="/preallocations/basic/import?${_csrf.parameterName}=${_csrf.token}"
                                   var="import_url"/>
                            <c:url value="/preallocations/basic/checkImport?${_csrf.parameterName}=${_csrf.token}"
                                   var="check_import_url"/>
                            <form:form action="${check_import_url}" enctype="multipart/form-data" method="post"
                                       id="preallocations-upload-form" cssClass="form-horizontal">
                                <input type="hidden" name="file_type" value="preallocations">
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <input name="excel_file" type="file" id="preallocations-import-input"
                                               accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                                    </div>
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" type="submit">
                                            <i class="ace-icon fa fa-cloud-upload bigger-110"></i> 确定
                                        </button>
                                        <button class="btn btn-white btn-primary" type="button"
                                                data-dismiss="modal">
                                            <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                                        </button>
                                    </div>
                                </div>
                            </form:form>
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
<div id="hj-modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    导入拆迁户户籍情况
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="form-group">
                                <div class="col-xs-12" style="margin-bottom: 10px">
                                    <span>请下载模板，按照模板格式整理数据：</span>
                                    <a href="<c:url value="/preallocations/basic/importTemp?type=2"/>" target="_blank">
                                        导入拆迁户户籍情况模板.xls
                                    </a>
                                </div>
                            </div>
                            <c:url value="/preallocations/basic/import-hj?${_csrf.parameterName}=${_csrf.token}"
                                   var="import_hj_url"/>
                            <form:form action="${import_hj_url}" enctype="multipart/form-data" method="post"
                                       id="preallocations-hj-form" cssClass="form-horizontal">
                                <input type="hidden" name="file_type" value="preallocations">
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <input name="excel_file" type="file" id="hj-import-input"
                                               accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                                    </div>
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" type="submit">
                                            <i class="ace-icon fa fa-cloud-upload bigger-110"></i> 确定
                                        </button>
                                        <button class="btn btn-white btn-primary" type="button"
                                                data-dismiss="modal">
                                            <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                                        </button>
                                    </div>
                                </div>
                            </form:form>
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
<div id="import-result-modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    基本情况导入结果
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="row">
                                <div class="col-xs-12 text-danger" id="result-text">
                                </div>
                                <div class="col-xs-12" align="center">
                                    <button class="btn btn-white btn-primary" type="button" id="result-btn">
                                        <i class="ace-icon fa fa-pencil bigger-110"></i> 更新
                                    </button>
                                    <button class="btn btn-white btn-primary" type="button" id="resultadd-btn">
                                        <i class="ace-icon fa fa-plus bigger-110"></i> 新增
                                    </button>
                                </div>
                            </div>
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
<div id="hj-result-modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    户籍情况导入结果
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="row">
                                <div class="col-xs-12 text-danger" id="hj-result-text">
                                </div>
                                <div class="col-xs-12" align="center">
                                    <button class="btn btn-white btn-primary" type="button" id="hj-result-btn">
                                        <i class="ace-icon fa fa-check-o bigger-110"></i> 确认
                                    </button>
                                </div>
                            </div>
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

            /*基本情况导入*/
            var $preallocations_import_input = $('#preallocations-import-input');
            $preallocations_import_input.ace_file_input({
                style: 'well',
                btn_choose: '点击选择Excel文件',
                btn_change: null,
                no_icon: 'ace-icon fa fa-file-excel-o',
                droppable: false,
                thumbnail: 'small',//large | fit
                maxSize: 10240000,
                allowExt: ['xls', 'xlsx', 'xlt', 'xlw', 'xlc', 'xlm'],
                allowMime: ['application/vnd.ms-excel', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'],
                before_remove: function () {
                    return true;
                }

            }).on('change', function () {
                if ($preallocations_import_input.val() == '') {
                    return false;
                }
            }).on('file.error.ace', function (ev, info) {
                if (info.error_count['ext'] || info.error_count['mime']) {
                    $.notify("请选择Excel文件!");
                    return false;
                }
                if (info.error_count['size']) {
                    $.notify("文件不超过100M!");
                    return false;
                }
            });

            //导入提交
            var $preallocations_upload_form = $("#preallocations-upload-form");
            $preallocations_upload_form.on('submit', function () {
                var upload_check = $preallocations_import_input.val();
                if (upload_check == '') {
                    $preallocations_import_input.ace_file_input('reset_input');
                    $.notify("请选择文件!");
                    return false;
                }
                $preallocations_import_input.ace_file_input('loading', true);
                $(".ace-file-overlay").append('<div class="overlay-text">正在导入中...</div>');
                $preallocations_upload_form.ajaxSubmit({
                    type: 'post', // 提交方式 get/post
                    url: $preallocations_upload_form.attr('action'),
                    dataType: 'json',
                    success: function (result) {
                        $preallocations_import_input.ace_file_input('loading', false);
                        var msg = '';
                        if (result && result.success && result.data) {
                            $("#import-modal").modal("hide");  //关闭上传窗口
                            $preallocations_import_input.ace_file_input('reset_input');

                            if (!result.data.length) {
                                msg = result.message;
                            } else {
                                $.each(result.data, function (i, item) {
                                    var index = parseInt(item.rowIndex);
                                    msg += '第' + index + '行,' + item.reason + '<br/>';
                                });
                                msg += '其余导入成功';
                            }
                            $("#result-text").html(msg);
                            $("#import-result-modal").modal("show");
//
                            //$.notify(msg);
                        } else {
                            $("#import-modal").modal("hide");   //关闭上传窗口
                            $preallocations_import_input.val('').ace_file_input('reset_input').ace_file_input('loading', false);
                        }
                    }
                });
                return false;
            });

            /*户籍情况导入*/
            var $hj_import_input = $('#hj-import-input');
            $hj_import_input.ace_file_input({
                style: 'well',
                btn_choose: '点击选择Excel文件',
                btn_change: null,
                no_icon: 'ace-icon fa fa-file-excel-o',
                droppable: false,
                thumbnail: 'small',//large | fit
                maxSize: 10240000,
                allowExt: ['xls', 'xlsx', 'xlt', 'xlw', 'xlc', 'xlm'],
                allowMime: ['application/vnd.ms-excel', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'],
                before_remove: function () {
                    return true;
                }

            }).on('change', function () {
                if ($hj_import_input.val() == '') {
                    return false;
                }
            }).on('file.error.ace', function (ev, info) {
                if (info.error_count['ext'] || info.error_count['mime']) {
                    $.notify("请选择Excel文件!");
                    return false;
                }
                if (info.error_count['size']) {
                    $.notify("文件不超过100M!");
                    return false;
                }
            });

            var $preallocations_hj_form = $("#preallocations-hj-form");

            //导入提交
            $preallocations_hj_form.on('submit', function () {
                var upload_check = $hj_import_input.val();
                if (upload_check == '') {
                    $hj_import_input.ace_file_input('reset_input');
                    $.notify("请选择文件!");
                    return false;
                }
                $hj_import_input.ace_file_input('loading', true);
                $(".ace-file-overlay").append('<div class="overlay-text">正在导入中...</div>');
                $preallocations_hj_form.ajaxSubmit({
                    type: 'post', // 提交方式 get/post
                    url: $preallocations_hj_form.attr('action'),
                    dataType: 'json',
                    success: function (result) {
                        $hj_import_input.ace_file_input('loading', false);
                        var msg = '';
                        if (result && result.success && result.data) {
                            $("#hj-modal").modal("hide");  //关闭上传窗口
                            $hj_import_input.ace_file_input('reset_input');

                            if (!result.data.length) {
                                msg = result.message;
                            } else {
                                $.each(result.data, function (i, item) {
                                    var index = parseInt(item.rowIndex);
                                    msg += '第' + index + '行,' + item.reason + '<br/>';
                                });
                                msg += '其余导入成功';
                            }
                            $("#hj-result-text").html(msg);
                            $("#hj-result-modal").modal("show");
//
                            //$.notify(msg);
                        } else {
                            $("#hj-modal").modal("hide");   //关闭上传窗口
                            $hj_import_input.val('').ace_file_input('reset_input').ace_file_input('loading', false);
                        }
                    }
                });
                return false;
            });
            $("#hj-result-btn").on('click', function () {
                $("#hj-result-modal").modal("hide");
            });
            $("#result-btn").on('click', function () {
                $("#import-result-modal").modal("hide");
//                $("#preallocationsSearchDTO").submit();

                $preallocations_upload_form.attr('action', '${import_url}');
                if (data == '') {
                    $preallocations_import_input.ace_file_input('reset_input');
                    $.notify("请选择文件!");
                    return false;
                }
                console.log(data)
                $preallocations_import_input.val(data);
                $preallocations_upload_form.ajaxSubmit({
                    type: 'post', // 提交方式 get/post
                    url: $preallocations_upload_form.attr('action'),
                    dataType: 'json',
                    success: function (result) {
                        $preallocations_import_input.ace_file_input('loading', false);
                        var msg = '';
                        if (result && result.success && result.data) {
                            $("#import-modal").modal("hide");  //关闭上传窗口
                            $preallocations_import_input.val('').ace_file_input('reset_input');
                            $("#import-result-modal").modal("show");

                            if (!result.data.length) {
                                msg = result.message;
                            } else {
                                $.each(result.data, function (i, item) {
                                    var index = parseInt(item.rowIndex);
                                    msg += '第' + index + '行,' + item.reason + '<br/>';
                                });
                                msg += '其余导入成功';
                            }
                            $("#result-text").html(msg);
                            $("#import-result-modal").modal("show");
//
                            //$.notify(msg);
                        } else {
                            $("#import-modal").modal("hide");   //关闭上传窗口
                            $preallocations_import_input.val('').ace_file_input('reset_input').ace_file_input('loading', false);
                        }
                    }
                });
            })

        });
    </script>
</javascripts>
</html>
