<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-汇报材料</title>
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
                    <li class="active">汇报材料</li>
                </ul>
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <!-- /.page-header -->
                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal" action="add.htm" method="post">
                            <input type="hidden" name="progress" value="${progress }">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">阶段：</label>
                                <div class="col-sm-10">
                                    ${bln:getProjectProgress(progress)}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标题：</label>
                                <div class="col-sm-10">
                                    <input id="title" name="title" type="text" class="col-xs-10 col-sm-5" placeholder=""
                                           value=""> <label
                                        id="nameTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label ">汇报材料:</label>
                                <div class="col-sm-10" id="mch_certificate">
                                    <input readonly type="text" id="file_name" name="file_name"
                                           value="" class="col-xs-3"/>
                                    <input readonly type="hidden" id="file_path" name="file_path"
                                           value=""/>
                                    <a href="#uploadImg-modal" class="btn btn-white btn-primary" data-toggle="modal"
                                       style="margin-left: 5px">
                                        <i class="ace-icon fa fa-folder-open bigger-110"></i> 选择文件
                                    </a>
                                    <label id="file_nameTip"></label>
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
        </div>
    </div>
</div>
<!--上传文件-->
<div id="uploadImg-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    上传文件
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <form id="uploadImg-form" action="${dynamicServer}/common/upload.htm"
                                  enctype="multipart/form-data" method="post">
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <input name="file" type="file" id="uploadImg-input"
                                               accept=".doc,.docx,.xls,.xlsx,.ppt,.pptx,.txt,.pdf,.csv,.htm,.html,.jpg,.jpeg,.tif,.tiff,.bmp,.png,.gif"/>
                                    </div>
                                    <div class="col-xs-12">
                                        <p class="center">上传文件格式为
                                            <a class="red">.doc,.docx,.xls,.xlsx,.ppt,.pptx,.txt,.pdf,.csv</a>。
                                        </p>
                                    </div>
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" type="submit">
                                            <i class="ace-icon fa fa-cloud-upload bigger-110"></i> 上传
                                        </button>
                                        <button class="btn btn-white btn-primary" type="button"
                                                id="close-modal" data-dismiss="modal">
                                            <i class="ace-icon fa fa-undo bigger-110"></i> 取消
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
<!-- /.main-container -->
<%@ include file="../common/js.jsp" %>
<script src="${staticServer}/assets/js/src/elements.fileinput.js"></script>
<script src="${staticServer}/assets/js/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#form").validate({
		//debug : true,
		errorElement : "label",
		errorClass : "valiError",
		errorPlacement : function(error, element) {
			error.appendTo($("#" + element.attr('id') + "Tip"));
		},
		rules : {
			title : {
				required : true,
				maxlength : 100
			},
			file_name : {
				required : true
			}
		},
		messages : {},
		submitHandler : function(form) {
			form.submit();
		}
	});

	//上传图片
	$('#uploadImg-input').ace_file_input({
		style : 'well',
		btn_choose : '点击选择文件',
		btn_change : null,
		no_icon : 'ace-icon fa fa-folder-open-o',
		droppable : false,
		allowExt : [ "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "pdf", "csv" ],
		thumbnail : 'large',
		//large | fit
		maxSize : 10240000,
		before_remove : function() {
			return true;
		}
	}).on('change', function() {
		if ($('#uploadImg-input').val() == '') {
			$('#uploadImg-input').parent().find(".ace-file-container").removeClass("hide-placeholder").removeClass("selected");
			$('#uploadImg-input').parent().find(".ace-file-container").attr("data-title", '点击选择文件');
			$('#uploadImg-input').parent().find(".ace-file-name").find("i").addClass("fa-folder-open-o").removeClass("fa-file");
			$('#uploadImg-input').parent().find(".ace-file-name").attr("data-title", 'No File ...');
		}
	}).on('file.error.ace', function(ev, info) {
		if (info.error_count['ext'] || info.error_count['mime'])
			bootbox.alert("请选择正确的文件格式!");
		if (info.error_count['size'])
			bootbox.alert("文件大小不超过10M!");
	});
	$("#uploadImg-form").on('submit', function(e) {
		var imgcheck = $("#uploadImg-input").val();
		if (imgcheck == '') {
			return false;
		}
		$("#uploadImg-form").ajaxSubmit({
			type : 'post',
			// 提交方式 get/post
			url : '${dynamicServer}/common/upload.htm',
			// 需要提交的 url
			success : function(data) { // data 保存提交后返回的数据，一般为 json 数据
				var dataObj = eval("(" + data + ")");
				var file_path = dataObj.createFilepath ;
				var file_name =   dataObj.createFilename;
				$("#close-modal").click();
				//$("#deleteImg").attr("style", "");
				$("#file_path").val(file_path);
				$("#file_name").val(file_name);
			}
		});
		return false;
	});
	var delMchPic = function() {
		var ev =$("#file_path").val()+"/" +$("#file_name").val();
		$.ajax({
			type : "post",
			url : "${dynamicServer}/common/delFile.htm",
			data : {
				path_name : ev,
			},
			dataType : "json",
			success : function(data) {
				if (data.success) {
					$("#file_name").val("");
					$("#deleteImg").attr("style", "display: none");
				} else {
					alert("操作失败");
				}
			}
		});
	}
});




</script>
</body>
</html>
