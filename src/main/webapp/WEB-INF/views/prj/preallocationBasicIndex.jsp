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

    <style>
        .checked_tr {
            background: #307ECC !important;
            color: white;
        }

        .checked_tr a {
            color: white;
        }

        .number {
            padding-left: 5px;
        }
    </style>
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
                        <small>基本情况</small>
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
                            <div class="widget-body">
                                <div class="widget-main no-padding">
                                    <c:url value="/prj/preallocation/basic/index.htm" var="index_url"/>
                                    <form:form action="${index_url}" method="post"
                                               cssClass="form-horizontal" role="form"
                                               modelAttribute="preallocationSearchVO"
                                               cssStyle="padding-top: 10px;" data-ajax="true">
                                        <div class="row">
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-4">编号：</label>
                                                    <div class="col-xs-7 col-lg-8">
                                                        <form:input type="text" path="map_id"
                                                                    class="col-xs-10 col-sm-10 col-lg-8" value=""/>

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-6">被拆除腾退人：</label>
                                                    <div class="col-xs-7 col-lg-6 no-padding-left">
                                                        <form:input type="text" path="host_name"
                                                                    class="col-xs-12 col-sm-12 col-lg-8" value=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-5">房屋坐落：</label>
                                                    <div class="col-xs-7 col-lg-7">
                                                        <form:input type="text" path="location"
                                                                    class="col-xs-10 col-sm-10 col-lg-8" value=""/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-5">房屋状态：</label>
                                                    <div class="col-xs-7 col-lg-7">
                                                        <form:select class="col-xs-10 col-sm-10 col-lg-8" path="status">
                                                            <form:option value="">--全部--</form:option>
                                                            <form:option value="0">未入户</form:option>
                                                            <form:option value="10">已入户</form:option>
                                                            <form:option value="20">已签约</form:option>
                                                            <form:option value="30">已审核</form:option>
                                                            <form:option value="40">已交房</form:option>
                                                            <form:option value="50">已拆除</form:option>
                                                            <form:option value="60">已放款</form:option>
                                                            <form:option value="70">已归档</form:option>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-4">镇：</label>
                                                    <div class="col-xs-7 col-lg-8">
                                                        <form:select path="town"
                                                                     cssClass="col-xs-10 col-sm-10 col-lg-8">
                                                            <form:option value="">--全部--</form:option>
                                                            <form:options items="${townList}" itemLabel="content"
                                                                          itemValue="value"></form:options>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-6">村：</label>
                                                    <div class="col-xs-7 col-lg-6 no-padding-left">
                                                        <form:select path="village"
                                                                     cssClass="col-xs-10 col-sm-10 col-lg-8">
                                                            <form:option value="">--全部--</form:option>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-5">标段：</label>
                                                    <div class="col-xs-7 col-lg-7">
                                                        <select class="col-xs-10 col-sm-10 col-lg-8" id="section">
                                                            <option value="">--全部--</option>
                                                            <c:forEach items="${sectionList}" var="section">
                                                                <option data-id="${section.id}" value="${section.name}"
                                                                        <c:if test="${preallocationSearchVO.section eq section.name}">selected</c:if>
                                                                >${section.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-5">组别：</label>
                                                    <div class="col-xs-7 col-lg-7">
                                                        <form:select class="col-xs-12 col-sm-12 col-lg-8" path="groups">
                                                            <form:option value="">--全部--</form:option>
                                                        </form:select>
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
                                                    <c:if test="${bln:isP('PrjPreallocationAdd')}">
                                                        <a href="javascript:addBasic();"
                                                           class="btn btn-success btn-sm">
                                                            <i class="ace-icon fa fa-plus bigger-110"></i>新增
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${bln:isP('PrjPreallocationImport')}">
                                                        <button id="importBtn" type="button"
                                                                class="btn btn-success btn-sm"
                                                                data-toggle="modal">
                                                            <i class="ace-icon fa fa-file-excel-o bigger-110"></i>导入基本信息
                                                        </button>
                                                    </c:if>
                                                    <c:if test="${bln:isP('PrjPreallocationExport')}">
                                                        <button id="exportBtn" type="button"
                                                                class="btn btn-waring btn-sm">
                                                            <i class="ace-icon fa fa-file-excel-o bigger-110"></i>导出
                                                        </button>
                                                    </c:if>

                                                </div>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                        <div class="hr hr-18 dotted hr-double"></div>
                        <div style="position: relative">
                            <c:choose>
                                <c:when test="${land_status == 1}">
                                    <%@ include file="_datatable.jsp" %>
                                </c:when>
                                <c:otherwise>
                                    <%@ include file="_datatable.jsp" %>
                                    <%--<%@ include file="_datatable_state.jsp" %>--%>
                                </c:otherwise>
                                <%--<c:otherwise>--%>
                                <%--<c:set var="datatableName"--%>
                                <%--value="_datatable_${__currentProject.landStatus.name().toLowerCase()}_${__currentProject.buildingType.name().toLowerCase()}.jsp"/>--%>
                                <%--<c:import url="${datatableName}" charEncoding="utf-8"/>--%>
                                <%--</c:otherwise>--%>
                            </c:choose>
                        </div>
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
                                    <c:if test="${prj_base_info_id == 28}">
                                        <a href="<c:url value="/assets/templates/import-preallocations-huangcun-template.xlsx"/>"
                                           target="_blank">
                                            导入基本情况模板.xlsx
                                        </a>
                                    </c:if>
                                    <c:if test="${prj_base_info_id != 28}">
                                        <a href="<c:url value="/assets/templates/import-preallocations-basic-template.xlsx"/>"
                                           target="_blank">
                                            导入基本情况模板.xlsx
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                            <c:url value="/prj/preallocation/basic/import.htm"
                                   var="import_url"/>
                            <form:form action="${import_url}" enctype="multipart/form-data" method="post"
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
                                        <i class="ace-icon fa fa-check bigger-110"></i> 确认
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
<div id="batch-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    批量删除-<label id="batch_record"></label>
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <form id="batch-form" action="deleteBatch.htm" enctype="multipart/form-data"
                                  method="post">
                                <div class="form-group">
                                    <input type="hidden" id="map_ids" name="map_ids" value=""/>
                                    <div id="batch_loading" class="loading hidden">
                                        <i class="ace-icon fa fa-spinner fa-spin orange bigger-250"></i>
                                        <span>正在删除中...</span>
                                    </div>
                                    <div class="col-xs-12 widget-box widget-color-blue"
                                         style="margin-bottom: 10px;max-height: 350px;overflow: auto">
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th width="200px">编号</th>
                                                <th>姓名</th>
                                            </tr>
                                            </thead>
                                            <tbody id="batch_items">

                                            </tbody>

                                        </table>
                                    </div>
                                    <div class="col-xs-12" style="margin-bottom: 15px">
                                        <input type="hidden" id="batch_record_id" value="" name="id">
                                        <input type="hidden" value="" id="backUrl" name="backUrl">
                                    </div>
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" id="batch-submit" type="button">
                                            <i class="ace-icon fa fa-cloud-upload bigger-110"></i> 确认删除
                                        </button>
                                        <button class="btn btn-white btn-primary" type="button"
                                                id="batch_close-modal" data-dismiss="modal">
                                            <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div style="clear: both;"></div>
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
    <script src="<c:url value="/assets/js/jquery.form.js"/>"></script>
    <script src="<c:url value="/assets/js/jquery-ui-spin.js"/>"></script>

    <script src="<c:url value="/assets/datatables/js/jquery.dataTables.js"/>"></script>
    <script src="<c:url value="/assets/datatables/js/dataTables.bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/datatables/extensions/FixedColumns/js/dataTables.fixedColumns.js"/>"></script>
    <script>
        function geturl(url) {
            if ($("#map_id").val() != '') {
                url += '&map_id=' + $("#map_id").val();
            }
            if ($("#host_name").val() != '') {
                url += '&host_name=' + $("#host_name").val();
            }
            if ($("#location").val() != '') {
                url += '&location=' + $("#location").val();
            }
            if ($("#section").val() != '') {
                url += '&section=' + $("#section").val();
            }
            if ($("#groups").val() != '') {
                url += '&groups=' + $("#groups").val();
            }
            if ($("#town").val() != '') {
                url += '&town=' + $("#town").val();
            }
            if ($("#village").val() != '') {
                url += '&village=' + $("#village").val();
            }
            if ($("#status").val() != '') {
                url += '&status=' + $("#status").val();
            }
            return url;
        }
        function addBasic() {
            window.location = '<c:url value="/prj/preallocation/basic/toAdd.htm?backUrl="/>' + geturl('${backUrl}');
        }
        // 删除
        function delBasic(id) {
            bootbox.confirm("你确定要删除该记录吗？", function (result) {
                if (result) {
                    window.location = "delete.htm?backUrl=" + geturl('${backUrl}') + "&&id=" + id;
                }
            })
        }
        //修改
        function updateBasic(id) {
            window.location = '<c:url value="/prj/preallocation/basic/toUpdate.htm?type=1&backUrl="/>' + geturl('${backUrl}') + '&id=' + id;
        }
        $(function () {

            $("#coll-btn").on('click', function () {
                if ($(this).find('i').hasClass('fa-chevron-up')) {
                    $('#show-head').html('展开');
                } else {
                    $('#show-head').html('收起');
                }
            })

            var $section = $("#section");
            var $group = $("#groups");
            $section.on('change', function () {
                if ($(this).val() == '') {
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
                        var json = eval('(' + result + ')');
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
                if ($(this).val() == '') {
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
                        var json = eval('(' + result + ')');
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
                    success: function (result) {
//                        var json = eval('('+result + ')');
                        var json = $.parseJSON(result);
                        $preallocations_import_input.ace_file_input('loading', false);
                        var msg = '';
                        if (json.success) {
                            $("#import-modal").modal("hide");  //关闭上传窗口
                            $preallocations_import_input.ace_file_input('reset_input');

                            if (json.msgText.length == 0) {
                                msg = "导入成功";
                            } else {
                                $.each(json.msgText, function (i, item) {
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
            $("#result-btn").on('click', function () {
                $("#import-result-modal").modal("hide");
                $("#btn-search").click();
//                $("#preallocationsSearchDTO").submit();
            })

            $('#basic-table').on('change', '[name="deletes"]', function () {
                var $that = $(this);
                var $tr = $that.closest('tr');
                var $td = $('#basic-table').find('[data-id="' + $that.val() + '"]');

                if ($that.prop('checked')) {
                    $tr.addClass('checked_tr');
                    $td.closest('tr').addClass('checked_tr');
                } else {
                    $tr.removeClass('checked_tr');
                    $td.closest('tr').removeClass('checked_tr');
                }
            });

            $('#all_check').on('change', function () {
                var $tr = $('#basic-table').find('tr');
                var $check_box = $('.DTFC_Cloned').find('[type="checkbox"]');
                var $check_tr = $check_box.closest('tr');

                if ($check_box.length <= 0) {
                    return;
                }
                var checked = $(this).prop('checked');
                if ($(this).prop('checked')) {
                    $tr.each(function (i, item) {
                        $(item).addClass('checked_tr');
                    });
                    $check_tr.each(function (i, item) {
                        $(item).addClass('checked_tr');
                    })
                    $check_box.prop('checked', true);
                } else {
                    $tr.removeClass('checked_tr');
                    $check_tr.removeClass('checked_tr');
                    $check_box.prop('checked', false);
                }
            });

            $('#btnBatch').on('click', function () {
                var url = geturl('${backUrl}');
                $('#backUrl').val(url);
                var $check_box = $('.DTFC_Cloned').find('[type="checkbox"]:checked');

                if ($check_box.length < 1) {
                    bootbox.alert('请至少选择一户');
                    return;
                }
                var ids = '';
                var $batch_items = $('#batch_items');
                var _html;
                $batch_items.empty();

                $check_box.each(function (i, item) {
                    ids += $(item).val() + ',';
                    _html += '<tr><td>' + $(item).val() + '</td><td>' + $(item).attr("data-name") + '</td></tr>';
                });
                if (ids.indexOf(',') > 0) {
                    ids = ids.substring(0, ids.length - 1);
                }

                var msg = '共选择<span class="label label-success">' + $check_box.length + '</span>户：';
                $('#batch_record').html(msg);

                $batch_items.append(_html);
                $('#map_ids').val(ids);

                $("#batch-modal").modal({
                    backdrop: 'static',
                    show: true
                });

            })

            $('#importBtn').on('click', function () {
                $("#import-modal").modal({
                    backdrop: 'static',
                    show: true
                });
            })

            $('#batch-submit').on('click', function () {
                bootbox.confirm("你确定要批量删除这些记录吗？<span class='red'>（删除后不可恢复，请谨慎操作）</span>", function (result) {
                    if (result) {
                        $('#batch-form').submit();
                    }
                })

            });

            $('.my-tooltip-link ').tooltip();

            $("#exportBtn").on('click', function (e) {
                bootbox.confirm({
                    buttons: {
                        confirm: {
                            label: '确认'
                        },
                        cancel: {
                            label: '取消'
                        }
                    },
                    message: '你确定要导出吗？',
                    callback: function (result) {
                        if (result) {
                            var url = '<c:url value="/prj/preallocation/basic/export?___=_"/>';

                            window.open(geturl(url));
                        } else {
                        }
                    }
                });
            });
        });
    </script>
</javascripts>
</html>
