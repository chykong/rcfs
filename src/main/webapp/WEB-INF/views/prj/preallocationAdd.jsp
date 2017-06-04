<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>${site_name} 拆除腾退户信息管理 - 添加基本情况</title>
    <style>
        textarea {
            padding-left: 3px !important;
        }
    </style>
    <%@ include file="../common/header.jsp" %>
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-datepicker/bootstrap-datepicker3.css"/>"/>

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
                    <li class="active"><small><i class="ace-icon fa fa-angle-double-right"></i> 添加基本情况</small></li>
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
                                    <a data-toggle="tab" href="#faq-tab-inhost" aria-expanded="true">
                                        <i class="blue ace-icon fa fa-inbox bigger-120"></i>
                                        进度信息
                                    </a>
                                </li>
                            </ul>
                            <form:form servletRelativeAction="/prj/preallocation/basic/add.htm" id="save-form" method="post"
                                       cssClass="form-horizontal" commandName="preallocation">
                                <div class="tab-content no-border padding-24">
                                    <!-- 基本情况 begin-->
                                    <div id="faq-tab-basic" class="tab-pane fade active in" data-type="1">
                                        <%@ include file="_formfields.jspf" %>
                                    </div>
                                    <div id="faq-tab-inhost" class="tab-pane fade" data-type="3">
                                        <%@ include file="_inhost_formfields.jspf" %>
                                    </div>
                                    <div class="clearfix form-actions">
                                        <div class="col-md-offset-3 col-md-9">
                                            <button id="btn-save" class="btn btn-info" type="submit">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                保存
                                            </button>
                                            &nbsp; &nbsp; &nbsp;
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


<div id="material-modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    上传材料附件
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <c:url value="/common/upload?${_csrf.parameterName}=${_csrf.token}" var="upload_url"/>
                            <form:form action="${upload_url}"
                                       enctype="multipart/form-data" method="post" id="material-form">
                                <input type="hidden" name="file_type" value="file">
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <input name="upload_file" type="file" id="material-input"/>
                                    </div>
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" type="submit">
                                            <i class="ace-icon fa fa-cloud-upload bigger-110"></i> 上传
                                        </button>
                                        <button class="btn btn-white btn-primary" type="button" id="closematerial-modal"
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
<%@ include file="../common/js.jsp" %>
<script src="<c:url value="/assets/js/jquery.form.js"/>"></script>

<script>
    $(function () {
        var $section = $("#section");
        var $group = $("#groups");
        $section.on('change', function () {
            if($(this).val() == ''){
                return;
            }
            var url = '<c:url value="/projects/getGroupsBySection?${_csrf.parameterName}=${_csrf.token}"/>';
            $.ajax({
                url: url,
                data: {
                    section_id: $section.find(":checked").attr("data-id"),
                    is_search : 0
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
        $('#material-input').ace_file_input({
            style: 'well',
            btn_choose: '点击选择',
            btn_change: null,
            no_icon: 'ace-icon fa fa-folder-open-o',
            droppable: false,
            thumbnail: 'large',
            maxSize: 1024 * 1000 * 10,
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
        var $material_form = $("#material-form");
        $material_form.on('submit', function () {
            if (!$("#material-input").val()) return false;

            $material_form.ajaxSubmit({
                type: 'post', // 提交方式 get/post
                url: $material_form.attr('action'),
                dataType: 'json',
                success: function (result) {
                    if (result && result.success && result.data) {
                        var file_path = result.data.file_path + "/" + result.data.file_name;
                        $("#closematerial-modal").click();
                        var vHTML = "<input type=\"text\" readonly class=\"form-control search-query\" value='" + result.data.original_name + "'>\
                            <span class=\"input-group-btn\">\
                                <button id='delFile' type=\"button\" class=\"btn btn-purple btn-sm\">\
                                <span class=\"ace-icon fa fa-trash-o icon-on-right bigger-110\"></span>\
                                删除\
                                </button>\
                                </span>";
                        $("#fileDiv").append(vHTML);
                        $("#checkFile").addClass("hidden");
                        $("#other_file_name").val(result.data.original_name);
                        $("#other_file_path").val(file_path);
                        return;
                    }
                    $.notify((result && result.message) || '上传失败');
                }
            });
            return false;
        });
        $("#fileDiv").on("click", "#delFile", function () {
            bootbox.confirm('你确定要删除该附件吗("<b style="color: red">删除以后将会彻底删除附件文件</b>")？', function (result) {
                if (result) {
                    $.ajax({
                        url: '<c:url value="/common/delFile?${_csrf.parameterName}=${_csrf.token}"/>',
                        data: {
                            path: $("#filePath").val()
                        },
                        type: 'post',
                        success: function (result) {
                            console.log($("#filePath").val());
                            if (result.success) {
                                $("#checkFile").removeClass("hidden");
                                $("#fileDiv input").remove();
                                $("#fileDiv span").remove();
                            } else {
                                $.notify({message: "文件路径错误!", z_index: 15111});
                            }
                        }
                    })
                }
            });
        })

        $("#save-form").validate({
            debug : true,
            errorElement: "label",
            errorClass: "validError",
            errorPlacement: function (error, element) {
                var $valid_pane = element.closest(".tab-pane");
                var $valid_tab = $("#myTab").find("[data-type=" + $valid_pane.attr("data-type") + "]");
                if (!$valid_pane.hasClass("active")) {
                    $valid_pane.addClass("active").addClass("in");
                    $valid_tab.addClass("active").addClass("in");

                    $valid_pane.siblings(".tab-pane").removeClass("active").removeClass("in");
                    $valid_tab.siblings("li").removeClass("active").removeClass("in");
                }
                error.appendTo($("#" + element.attr('id') + "Tip"));
            },
            ignore: "",
            rules: {
                map_id: {
                    required: true,
                    maxlength: 20,
                    remote: {
                        url: "<c:url value="/prj/preallocation/basic/checkMapId.htm"/>", //后台处理程序
                        type: "post", //数据发送方式
                        dataType: "json", //接受数据格式
                        data: { //要传递的数据
                            mapId: function () {
                                if ($("#id").val() > 0) {
                                    if ($("#mapId").val() != $("#update_mapId").val()) {
                                        return $("#mapId").val();
                                    } else {
                                        return '';
                                    }
                                } else {
                                    return $("#mapId").val();
                                }
                            }
                        }
                    }
                },
                host_name: {
                    required: true,
                    maxlength: 50
                },
                location: {
                    maxlength: 200
                },
                id_card: {
                    idcard: true,
                    maxlength: 18
                },
                section: {
                    required: true,
                    maxlength: 200
                },
                groups: {
                    required: true
                },
                demolish_co: {
                    required: false
                },
                appraise_co: {
                    required: false
                },
                geo_co: {
                    required: false
                },
                pulledown_co: {
                    required: false
                }
            },
            messages: {
                map_id: {
                    remote: "<span class=\"red\">编号已存在</span>"
                }
            },
            submitHandler:function(){
                return false;
            }
        });
    })
</script>
</body>
</html>
