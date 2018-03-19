<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>${site_name} 拆除腾退户信息管理 - 添加基本情况2</title>
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
                    <li class="active">
                        <small>添加基本情况</small>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
                <%@ include file="../common/navigate.jsp" %>
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
                                <li class="" data-type="2">
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
                                <li class="" data-type="4">
                                    <a data-toggle="tab" href="#faq-tab-qt" aria-expanded="true">
                                        <i class="blue ace-icon fa fa-inbox bigger-120"></i>
                                        其他信息
                                    </a>
                                </li>
                                <li class="" data-type="5">
                                    <a id="toXc" data-toggle="tab" href="#faq-tab-xc" aria-expanded="true">
                                        <i class="blue ace-icon fa fa-image bigger-120"></i>
                                        现场照片
                                    </a>
                                </li>
                                <li class="" data-type="6">
                                    <a data-toggle="tab" href="#faq-tab-jt" aria-expanded="true">
                                        <i class="blue ace-icon fa fa-building bigger-120"></i>
                                        人口情况
                                    </a>
                                </li>
                                <li class="" data-type="7">
                                    <a data-toggle="tab" href="#faq-tab-ts" aria-expanded="true">
                                        <i class="blue ace-icon fa fa-question bigger-120"></i>
                                        特殊情况
                                    </a>
                                </li>
                            </ul>
                            <form:form servletRelativeAction="/prj/preallocation/basic/add.htm" id="save-form"
                                       method="post"
                                       cssClass="form-horizontal" commandName="preallocation">
                                <input type="hidden" name="backUrl" value="${backUrl}"/>
                                <div class="tab-content no-border padding-24">
                                    <!-- 基本情况 begin-->
                                    <div id="faq-tab-basic" class="tab-pane fade active in" data-type="1">
                                        <%@ include file="./new_jsp/_fy_formfields.jspf" %>
                                    </div>
                                    <div id="faq-tab-jd" class="tab-pane fade" data-type="2">
                                        <%@ include file="_jdformfields.jspf" %>
                                    </div>
                                    <div id="faq-tab-bc" class="tab-pane fade" data-type="3">
                                        <%@ include file="./new_jsp/_fy_bcformfields.jspf" %>
                                    </div>
                                    <div id="faq-tab-qt" class="tab-pane fade" data-type="4">
                                        <%@ include file="_inhost_formfields.jspf" %>
                                    </div>
                                    <div id="faq-tab-xc" class="tab-pane fade" data-type="5">
                                        <%@ include file="_xc_photo.jspf" %>
                                    </div>
                                    <div id="faq-tab-jt" class="tab-pane fade" data-type="6">
                                        <%@ include file="_rk_info.jspf" %>
                                    </div>
                                    <div id="faq-tab-ts" class="tab-pane fade" data-type="6">
                                        <%@ include file="_ts_info.jspf" %>
                                    </div>
                                    <div class="clearfix form-actions">
                                        <div class="col-md-offset-3 col-xs-offset-3 col-md-9">
                                            <button id="btn-save" class="btn btn-primary" type="button">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                保存
                                            </button>
                                            &nbsp; &nbsp; &nbsp;
                                            <a id="btn-reset" class="btn"
                                               href="<c:url value="/prj/preallocation/basic/index.htm"/>">
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
                    上传附件文件
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <c:url value="/common/upload.htm" var="upload_url"/>
                            <form:form action="${upload_url}"
                                       enctype="multipart/form-data" method="post" id="material-form">
                                <input type="hidden" name="file_type" value="file">
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <input name="file" type="file" id="material-input"
                                               accept="application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>
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
<%@ include file="../common/ueditor.jsp" %>

<script src="<c:url value="/assets/webuploader-0.1.5/webuploader.js"/>"></script>

<script src="<c:url value="/assets/js/jquery.form.js"/>"></script>
<script src="<c:url value="/assets/js/bootstrap-datepicker/bootstrap-datepicker.js"/>"></script>
<script src="<c:url value="/assets/js/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"/>"></script>
<script>

    $("#btn-save").on('click', function () {

        var $relaName = $(".relaName");
        if ($relaName.length >= 1) {
            var flag = 0;
            $relaName.each(function (i, item) {
                if ($(item).val() === '') {
                    $(item).focus();
                    $(item).attr('placeholder', '不能为空');
                    flag = 1;
                    return;
                }
            });
            if (flag === 1) {
                return;
            }
        }

        var $host = $(".host");
        if ($host.length >= 1) {
            var flag = 0;
            $host.each(function (i, item) {
                if ($(item).val() === '') {
                    $(item).focus();
                    $(item).attr('placeholder', '不能为空');
                    flag = 1;
                    return;
                }
            });
            if (flag === 1) {
                return;
            }
        }


        var $relaIdNo = $(".relaIdNo");
        if ($relaIdNo.length >= 1) {
            var flag = 0;
            $relaIdNo.each(function (i, item) {
                if ($(item).val() === '') {
                    $(item).focus();
                    $(item).attr('placeholder', '不能为空');
                    flag = 1;
                    return;
                }
            });
            if (flag === 1) {
                return;
            }
        }


        var $relaName1 = $(".relaName1");
        if ($relaName1.length >= 1) {
            var flag = 0;
            $relaName1.each(function (i, item) {
                if ($(item).val() === '') {
                    $(item).focus();
                    $(item).attr('placeholder', '不能为空');
                    flag = 1;
                    return;
                }
            });
            if (flag === 1) {
                return;
            }
        }

        var $host1 = $(".host1");
        if ($host1.length >= 1) {
            var flag = 0;
            $host1.each(function (i, item) {
                if ($(item).val() === '') {
                    $(item).focus();
                    $(item).attr('placeholder', '不能为空');
                    flag = 1;
                    return;
                }
            });
            if (flag === 1) {
                return;
            }
        }

        var $relaIdNo1 = $(".relaIdNo1");
        if ($relaIdNo1.length >= 1) {
            var flag = 0;
            $relaIdNo1.each(function (i, item) {
                if ($(item).val() === '') {
                    $(item).focus();
                    $(item).attr('placeholder', '不能为空');
                    flag = 1;
                    return;
                }
            });
            if (flag === 1) {
                return;
            }
        }

        $("#btn-save").submit();
    });


    function initWebUploader(pick, fileUpload, fileList, loading) {
        var uploader = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: false,
            dnd: document.body,
            disableGlobalDnd: true,
            paste: document.body,

            // swf文件路径
            swf: '<c:url value="/assets/webuploader-0.1.5/Uploader.swf"/>',

            // 文件接收服务端。
            server: '<c:url value="/common/upload.htm"/>',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#' + pick,

            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/jpg,image/jpeg,image/png'
            }
        });

        $("#" + fileUpload).on('click', function () {
            uploader.upload();
        });
        var $list = $('#' + fileList);

        $list.on('click', '.del', function () {
            var $file_item = $(this).closest('.file-item');
            if ($file_item.hasClass('upload-state-done')) {
                var file_path = $file_item.find('[name*=file_path]');
                $.ajax({
                    url: '<c:url value="/common/delFile.htm"/>',
                    data: {
                        path: file_path.val()
                    },
                    type: 'post',
                    success: function (result) {
                        var json = eval('(' + result + ')');

                    }
                })
            }

            var id = $(this).attr('data-id');
            uploader.removeFile(id, true);
            $file_item.remove();
            var $file_items = $list.find('.file-item');
            var flag = 0;
            $file_items.each(function (i, item) {
                if (!$(item).hasClass('upload-state-done')) {
                    flag++;
                }
            })
            if (flag == 0) {
                $('#' + fileUpload).addClass('hidden');
                $('#' + loading).addClass('hidden');
            }
        });

        // 文件上传过程中创建进度条实时显示。
        uploader.on('uploadProgress', function (file, percentage) {
            var $li = $('#' + file.id),
                $percent = $li.find('.progress span');

            // 避免重复创建
            if (!$percent.length) {
                $percent = $('<p class="progress"><span></span></p>')
                    .appendTo($li)
                    .find('span');
            }

            $percent.css('width', percentage * 100 + '%');
        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader.on('uploadSuccess', function (file, response) {
            var result = response._raw;
            var reg = /'/g;
            result = result.replace(reg, '"');
            var json = $.parseJSON(result);
            var $file_item = $('#' + file.id);
            $file_item.find('[name*=file_name]').val(json.original_name);
            $file_item.find('[name*=file_path]').val(json.createFilepath + json.createFilename);
            $file_item.addClass('upload-state-done');
        });

        // 文件上传失败，显示上传出错。
        uploader.on('uploadError', function (file) {
            var $li = $('#' + file.id),
                $error = $li.find('div.error');

            // 避免重复创建
            if (!$error.length) {
                $error = $('<div class="error"></div>').appendTo($li);
            }

            $error.text('上传失败');
        });

// 完成上传完了，成功或者失败，先删除进度条。
        uploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').remove();
            $('#' + loading).addClass('hidden');
            $('#' + fileUpload).addClass('hidden');
        });

        return uploader;
    }

    function image(uploader, $img, file) {
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr('src', src);
        }, 100, 100);
    }

    $(function () {

        UE.getEditor('content', {
            toolbars: ueditorToolbar1,
            initialFrameHeight: 300,
            imagePath: '${pageContext.request.contextPath}/'
        });

        var click = 0;
        $('#toXc').on('shown.bs.tab', function (e) {
            if (click > 0) {
                return;
            }
            var uploader = initWebUploader('filePicker', 'fileUpload', 'fileList', 'loading');
            var index = 0;
            var $list = $('#fileList');
            uploader.on('fileQueued', function (file) {
                var $li = $(
                    '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<input type="hidden" name="preallAttaches[' + index + '].type" value="1">' +
                    '<input type="hidden" name="preallAttaches[' + index + '].file_name" value=""/>' +
                    '<input type="hidden" name="preallAttaches[' + index + '].file_path" value=""/>' +
                    '<div data-id="' + file.id + '" class="del red"><i class="ace-icon fa fa-trash-o"></i>   删除</div>' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                    '</div>'
                    ),
                    $img = $li.find('img');
                // $list为容器jQuery实例
                $list.append($li);
                // 创建缩略图
                // 如果为非图片文件，可以不用调用此方法。
                // thumbnailWidth x thumbnailHeight 为 100 x 100
                image(uploader, $img, file)

                $('#fileUpload').removeClass('hidden');
                $('#loading').removeClass('hidden');
                index++;
            });

            var uploader2 = initWebUploader('filePicker2', 'fileUpload2', 'fileList2', 'loading2');
            var $list2 = $('#fileList2');
            uploader2.on('fileQueued', function (file) {
                var $li = $(
                    '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<input type="hidden" name="preallAttaches[' + index + '].type" value="2">' +
                    '<input type="hidden" name="preallAttaches[' + index + '].file_name" value=""/>' +
                    '<input type="hidden" name="preallAttaches[' + index + '].file_path" value=""/>' +
                    '<div data-id="' + file.id + '" class="del red"><i class="ace-icon fa fa-trash-o"></i>   删除</div>' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                    '</div>'
                    ),
                    $img = $li.find('img');
                // $list为容器jQuery实例
                $list2.append($li);
                // 创建缩略图
                // 如果为非图片文件，可以不用调用此方法。
                // thumbnailWidth x thumbnailHeight 为 100 x 100
                image(uploader2, $img, file)

                $('#fileUpload2').removeClass('hidden');
                $('#loading2').removeClass('hidden');
                index++;
            });

            var uploader3 = initWebUploader('filePicker3', 'fileUpload3', 'fileList3', 'loading3');
            var $list3 = $('#fileList3');
            uploader3.on('fileQueued', function (file) {
                var $li = $(
                    '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<input type="hidden" name="preallAttaches[' + index + '].type" value="3">' +
                    '<input type="hidden" name="preallAttaches[' + index + '].file_name" value=""/>' +
                    '<input type="hidden" name="preallAttaches[' + index + '].file_path" value=""/>' +
                    '<div data-id="' + file.id + '" class="del red"><i class="ace-icon fa fa-trash-o"></i>   删除</div>' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                    '</div>'
                    ),
                    $img = $li.find('img');
                // $list为容器jQuery实例
                $list3.append($li);
                // 创建缩略图
                // 如果为非图片文件，可以不用调用此方法。
                // thumbnailWidth x thumbnailHeight 为 100 x 100
                image(uploader3, $img, file)

                $('#fileUpload3').removeClass('hidden');
                $('#loading3').removeClass('hidden');
                index++;
            });

            click++;
        })

        $(".date").datepicker({
            format: "yyyy-mm-dd",
            autoclose: true,
            todayHighlight: true,
            language: "zh-CN",
            orientation: "bottom auto"
        });

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
                    section_id: $section.find(":checked").attr("data-id"),
                    is_search: 0
                },
                type: 'post',
                success: function (result) {
                    var json = eval('(' + result + ')');
                    $group.empty();
                    if (json && json.length) {
                        $group.append('<option value="">--请选择--</option>');
                        $.each(json, function (i, group) {
                            $group.append('<option value="' + group.name + '">' + group.name + '</option>');
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
        var $material_form = $("#material-form");
        $material_form.on('submit', function () {
            if (!$("#material-input").val()) return false;

            $material_form.ajaxSubmit({
                type: 'post', // 提交方式 get/post
                url: $material_form.attr('action'),
                success: function (result) {
                    var json = eval('(' + result + ')');
                    if (json.success) {
                        var file_path = json.createFilepath + "/" + json.createFilename;
                        $("#closematerial-modal").click();

                        <%--$("#photo").attr('src', '${imageServer}' + file_path);--%>

                        $("#other_file_name").val(json.original_name);
                        $("#other_file_path").val(file_path);
                        $("#other_file_name").removeClass("hidden");
                        $("#photoDiv").removeClass("hidden");
                        $("#checkFile").addClass("hidden");
                        return;
                    }
                    $.notify((json && json.message) || '上传失败');
                }
            });
            return false;
        });
        $("#fileDiv").on("click", "#delFile", function () {
            bootbox.confirm('你确定要删除该文件吗(<b style="color: red">删除以后将会彻底删除文件文件</b>)？', function (result) {
                if (result) {
                    $.ajax({
                        url: '<c:url value="/common/delFile.htm"/>',
                        data: {
                            path: $("#other_file_path").val()
                        },
                        type: 'post',
                        success: function (result) {
                            var json = eval('(' + result + ')');
                            console.log($("#other_file_path").val());
                            if (json.success) {
                                $("#checkFile").removeClass("hidden");
                                $("#photoDiv").addClass("hidden");
                                $("#other_file_name").addClass("hidden");
                            } else {
                                $.notify({message: "文件路径错误!", z_index: 15111});
                            }
                        }
                    })
                }
            });
        })
        $('#checkFile').on('click', function () {
            $("#material-modal").modal('show');
        })

        $("#save-form").validate({
//            debug : true,
            errorElement: "label",
            errorClass: "valiError",
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
                                    if ($("#map_id").val() != $("#update_mapId").val()) {
                                        return $("#map_id").val();
                                    } else {
                                        return '';
                                    }
                                } else {
                                    return $("#map_id").val();
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
                    maxlength: 18
                },
                section: {
                    required: true
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
            submitHandler: function () {
                return true;
            }
        });


        var indexRela = 0;
        $('#addRela').on('click', function () {
            var $addRelaBody = $('#addRelaBody');
            var _html = '<tr id="trHome_' + indexRela + '">\
                <td><input type="text" name="relas[' + indexRela + '].name" class="col-lg-12 relaName" value=""/></td>\
                <input type="hidden" name="relas[' + indexRela + '].type" class="col-lg-12" value="1"/>\
                <td><input type="text" name="relas[' + indexRela + '].host_relation" class="col-lg-12 host" value=""/></td>\
            <td><input type="text" name="relas[' + indexRela + '].id_no" class="col-lg-12 id_no relaIdNo " value="" id="no' + indexRela + '" onblur="exist(' + indexRela + ')"/></td>\
                <td><input type="text" name="relas[' + indexRela + '].age" placeholder="来源自身份证号" class="col-lg-12 birthday" readonly value=""/></td>\
                <td>\
                <select name="relas[' + indexRela + '].marriage_status" class="col-lg-12">\
                <option value="">-请选择-</option>\
                <option value="1">未婚</option>\
            <option value="2" >已婚</option>\
            <option value="3" >再婚</option>\
            <option value="4" >离异</option>\
            <option value="5" >丧偶</option>\
            </select>\
            </td>\
            <td><textarea name="relas[' + indexRela + '].note" class="col-lg-12"></textarea></td>\
                <td>\
                <a data-id="' + indexRela + '" class="del"> 删除 </a>\
                </td>\
                </tr>';

            $addRelaBody.append(_html);
            indexRela++;
        });
        $('#addRelaBody').on('click', '.del', function () {
            var $that = $(this);
            var del_id = $that.attr('data-id');
            var $tr = $('#trHome_' + del_id);
            $tr.remove();
        })
//        $('#addBody').on('click', '.del', function () {
//            var $that = $(this);
//            var del_id = $that.attr('data-id');
//            var $tr = $('#tr_' + del_id);
//            $tr.remove();
//        })

        $('#addRela1').on('click', function () {
            var $addRelaBody = $('#addRelaBody1');
            var _html = '<tr id="1trHome_' + indexRela + '">\
            <input type="hidden" name="relas[' + indexRela + '].type" class="col-lg-12" value="2"/>\
                <td><input type="text" name="relas[' + indexRela + '].name" class="col-lg-12 relaName" value=""/></td>\
                <td><input type="text" name="relas[' + indexRela + '].host_relation" class="col-lg-12 host1" value=""/></td>\
            <td><input type="text" name="relas[' + indexRela + '].id_no" class="col-lg-12 id_no relaIdNo1" value="" id="no' + indexRela + '" onblur="exist(' + indexRela + ')"/></td>\
                <td><input type="text" name="relas[' + indexRela + '].age" placeholder="来源自身份证号" class="col-lg-12 birthday" readonly value=""/></td>\
                <td>\
                <select name="relas[' + indexRela + '].marriage_status" class="col-lg-12">\
                <option value="">-请选择-</option>\
                <option value="1">未婚</option>\
            <option value="2" >已婚</option>\
            <option value="3" >再婚</option>\
            <option value="4" >离异</option>\
            <option value="5" >丧偶</option>\
            </select>\
            </td>\
            <td><textarea name="relas[' + indexRela + '].note" class="col-lg-12"></textarea></td>\
                <td>\
                <a data-id="' + indexRela + '" class="del1"> 删除 </a>\
                </td>\
                </tr>';

            $addRelaBody.append(_html);
            indexRela++;
        });
        $('#addRelaBody1').on('click', '.del1', function () {
            var $that = $(this);
            var del_id = $that.attr('data-id');
            var $tr = $('#1trHome_' + del_id);
            $tr.remove();
        })
    })


    function exist(id_no) {
        var $no = $("#no" + id_no).val().length;
        if ($no == 18 || $no == 16) {
        } else {
//            $no.focus()
//            document.getElementById("#no" + id_no).focus();
            bootbox.alert('请输入正确的身份证号！')
        }
    }
</script>
</body>
</html>
