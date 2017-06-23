<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-消息发送</title>
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
                    <li class="active">消息发送</li>
                    <li class="active">新增消息</li>
                </ul>
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal"
                              action="add.htm" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">消息标题：</label>
                                <div class="col-sm-9">
                                    <input id="title" type="text" name="title"
                                           class="col-xs-10 col-sm-5" placeholder=""
                                           value=""><label id="titleTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">消息内容：</label>
                                <div class="col-sm-9">
										<textarea id="content" name="content" class="col-xs-10 col-sm-5"
                                                  placeholder="" style="height: 200px;"></textarea>
                                    <label id="contentTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">消息接收人：</label>
                                <div class="col-sm-9">
                                    <form:select path="baseMessage.user_id" name="user_id" class="col-xs-10 col-sm-5"
                                                 id="role_id">
                                        <option value="">请选择消息接收人</option>
                                        <form:options items="${listUser }" itemValue="id" itemLabel="realname"/>
                                    </form:select>
                                    <label id="user_idTip"></label>
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
                $(document)
                    .ready(
                        function () {
                            $("#form")
                                .validate(
                                    {
                                        //debug : true,
                                        errorElement: "label",
                                        errorClass: "valiError",
                                        errorPlacement: function (error,
                                                                  element) {
                                            error
                                                .appendTo($("#"
                                                    + element
                                                        .attr('id')
                                                    + "Tip"));
                                        },
                                        rules: {
                                            title: {
                                                required: true,
                                                maxlength: 50
                                            },
                                            user_id: {
                                                required: true
                                            }
                                        },
                                        messages: {},
                                        submitHandler: function (form) {
                                            form.submit();
                                        }
                                    });
                        });
            </script>
</body>
</html>
