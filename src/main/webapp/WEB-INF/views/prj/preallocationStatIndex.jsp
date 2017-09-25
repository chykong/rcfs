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
                    <li class="active"><small>进度统计表</small></li>
                </ul>
                <!-- /.breadcrumb -->
                <%@ include file="../common/navigate.jsp"%>
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
                                    <c:url value="/prj/preallocation/stat/index.htm" var="index_url"/>
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
                                            <div class="col-xs- col-lg-3">
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
                                        <div class="row">
                                            <div class="col-xs- col-lg-3">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-4">日期为空：</label>
                                                    <div class="col-xs-7 col-lg-8">
                                                        <select id="search_date" class="col-xs-10 col-sm-10 col-lg-8">
                                                            <option value="">--全部--</option>
                                                            <option value="1">入户日期</option>
                                                            <option value="2">签约日期</option>
                                                            <option value="3">交房日期</option>
                                                            <option value="4">拆除日期</option>
                                                            <option value="5">审核日期</option>
                                                            <option value="6">放款日期</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-4 col-lg-3 hidden">
                                                <div class="form-group">
                                                    <label class="control-label col-xs-5 col-lg-6">是否为空：</label>
                                                    <div class="col-xs-7 col-lg-6 no-padding-left">
                                                        <input id="search_blank" class="col-xs-10 col-sm-10 col-lg-8" value="0"/>
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
                        <c:choose>
                            <c:when test="${land_status == 1}">
                                <%@ include file="_statdatatable.jsp" %>
                            </c:when>
                            <c:otherwise>
                                <%@ include file="_statdatatable.jsp" %>
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
                                    <a href="<c:url value="/assets/templates/import-preallocations-basic-template.xlsx"/>" target="_blank">
                                        导入基本情况模板.xlsx
                                    </a>
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
</body>

<javascripts>
    <%@ include file="../common/js.jsp" %>
    <script src="<c:url value="/assets/js/jquery.form.js"/>"></script>


    <script src="<c:url value="/assets/datatables/js/jquery.dataTables.js"/>"></script>
    <script src="<c:url value="/assets/datatables/js/dataTables.bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/datatables/extensions/FixedColumns/js/dataTables.fixedColumns.js"/>"></script>
    <script>
        $(function () {

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
                        var json = eval('('+result + ')');
                        $preallocations_import_input.ace_file_input('loading', false);
                        var msg = '';
                        console.log(json.success)
                        if (json.success) {
                            $("#import-modal").modal("hide");  //关闭上传窗口
                            $preallocations_import_input.ace_file_input('reset_input');

                            if (!json.data || !json.data.length) {
                                msg = json.msgText;
                            } else {
                                $.each(json.data, function (i, item) {
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
//                $("#preallocationsSearchDTO").submit();
            })

        });
    </script>
</javascripts>
</html>
