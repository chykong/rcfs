<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-管理公司人员</title>
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
                    <li class="active">安置房概况</li>
                    <li class="active">安置房概况详细</li>
                </ul>
                <%@ include file="../common/navigate.jsp" %>
                <!-- /.breadcrumb -->
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box widget-color-blue">
                            <!-- #section:custom/widget-box.options -->
                            <div class="widget-header">
                                <h5 class="widget-title bigger lighter">
                                    <i class="ace-icon fa fa-table"></i> 操作面板
                                </h5>
                            </div>

                            <!-- /section:custom/widget-box.options -->
                            <div class="widget-body">
                                <div class="widget-main">
                                    <table class="searchField" style="margin: 4px; padding: 4px;">
                                        <tr>
                                            <td>楼号：</td>
                                            <td><input type="text" id="txtBuild" class="form-control input-middle"
                                                       placeholder=""
                                                       value="${placementDetailSearchVO.build_code}"></td>
                                            <td>门牌号：</td>
                                            <td><input type="text" id="txtMap" class="form-control input-middle"
                                                       placeholder=""
                                                       value="${placementDetailSearchVO.map_code}"></td>
                                            <td>
                                                <button class="btn btn-primary btn-sm" id="btnSearch">
                                                    <i class="ace-icon fa fa-search"></i> 查询
                                                </button>
                                                <c:if test="${bln:isP('BasePlacementDetailAdd')}">
                                                    <button type="button" class="btn btn-success btn-sm"
                                                            id="btnAdd">
                                                        <i class="ace-icon fa fa-plus bigger-110"></i>新增
                                                    </button>
                                                </c:if>
                                                <c:if test="${bln:isP('BasePlacementDetailImport')}">
                                                    <button id="importBtn"  class="btn btn-success btn-sm">
                                                        <i class="ace-icon fa fa-file-excel-o"></i>导入</button>
                                                </c:if>
                                                <a class="btn btn-sm" href="<c:url value="/base/placement/index"/>" onclick="history.back(-1)">
                                                    <i class="ace-icon fa fa-arrow-left bigger-110"></i> 返回安置房概况
                                                </a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th width=40>序号</th>
                                <th width=80>楼号</th>
                                <th width=80>楼层</th>
                                <th width=80>门牌号</th>
                                <th width="140">户型</th>
                                <th width="140">面积</th>
                                <th width=80>通透</th>
                                <th width=80>明厨</th>
                                <th width=80>明卫</th>
                                <th width=100>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list }" var="placement" varStatus="st">
                                <tr>
                                    <td>${st.index+1 }</td>
                                    <td>${placement.build_code }</td>
                                    <td>${placement.floor }</td>
                                    <td>${placement.map_code }</td>
                                    <td>${placement.house_type_name}</td>
                                    <td>${placement.area }</td>
                                    <td><c:if test="${placement.bool_tt ==1 }"><span
                                            class="label label-success">是</span></c:if>
                                        <c:if test="${placement.bool_tt ==0 }"><span
                                                class="label label-danger">否</span></c:if></td>
                                    <td><c:if test="${placement.bool_mc ==1 }"><span
                                            class="label label-success">是</span></c:if>
                                        <c:if test="${placement.bool_mc ==0 }"><span
                                                class="label label-danger">否</span></c:if></td>
                                    <td><c:if test="${placement.bool_mw ==1 }"><span
                                            class="label label-success">是</span></c:if>
                                        <c:if test="${placement.bool_mw ==0 }"><span
                                                class="label label-danger">否</span></c:if></td>
                                    <td>
                                        <c:if test="${bln:isP('BasePlacementDetailUpdate') && placement.is_select != 1}">
                                            <a href="toUpdate.htm?id=${placement.id }&backUrl=${backUrl}">修改</a>
                                        </c:if>
                                        <c:if test="${bln:isP('BasePlacementDetailRealUpdate')}">
                                            <a href="toUpdate.htm?id=${placement.id }&backUrl=${backUrl}">修改</a>
                                        </c:if>
                                        <c:if test="${bln:isP('BasePlacementDetailDelete')}">
                                            <a href="javascript:delContacts(${placement.id });"> 删除</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
        <!-- /.main-content -->
    </div>
</div>
</div>


<!--导入model-->
<div id="import_modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    导入安置房详情
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="form-group">
                                <div class="col-xs-12" style="margin-bottom: 10px">
                                    <span>请下载模板，按照模板格式整理数据：</span>
                                    <a href="${dynamicServer}/templates/import-placementdetail-template.xls" target="_blank">导入安置房详情信息.xls</a>
                                </div>
                            </div>
                            <c:url value="/base/placementdetail/import.htm" var="import_url"/>
                            <form:form action="${import_url}"
                                       enctype="multipart/form-data" method="post" id="upload_form">
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <input name="excel_file" type="file" id="import_input"
                                               accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                                    </div>
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" type="submit">
                                            <i class="ace-icon fa fa-cloud-upload bigger-110"></i> 确定
                                        </button>
                                        <button class="btn btn-white btn-primary" type="button"
                                                id="closeicon_modal"
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
<div id="import_result_modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    导入结果
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <form:form action="" id="import_result_form">
                                <div class="form-group">
                                    <div class="col-xs-12" id="result_text" style="max-height: 350px;overflow: auto">
                                        <span id="importResult" style="width:560px;height: 200px"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-12" align="center">
                                        <button class="btn btn-white btn-primary" type="button" id="result_btn">
                                            <i class="ace-icon fa fa-check bigger-110"></i> 确认
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
<script type="text/javascript">

    $(function () {
        $("#btnAdd").bind('click', addContacts);
        $('#btnSearch').on('click',function(){
            var url = "index.htm?";
            if ($("#txtBuild").val() != '')
                url += "&build_code=" + $("#txtBuild").val();
            if ($("#txtMap").val() != '')
                url += "&map_code=" + $("#txtMap").val();
            window.location = encodeURI(url);
        });

        $('#importBtn').on('click', function () {
            $("#import_modal").modal({
                backdrop: 'static',
                show: true
            });
        })
    })


    // 删除
    var delContacts = function (id) {
        bootbox.confirm("你确定要删除吗？", function (result) {
            if (result) {
                window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}";
            }
        })
    }
    //新增
    var addContacts = function (id) {
        window.location = "toAdd.htm?backUrl=${backUrl }";
    }
    $(function () {

        var $import_input = $('#import_input');
        $import_input.ace_file_input({
            style: 'well',
            btn_choose: '点击选择Excel文件',
            btn_change: null,
            no_icon: 'ace-icon fa fa-file-excel-o',
            droppable: false,
            thumbnail: 'small',//large | fit
            maxSize: 10240000,
            allowExt: ['xls', 'xlsx'],
            allowMime: ['application/vnd.ms-excel', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'],
            before_remove: function () {
                return true;
            }

        }).on('change', function () {
            if ($import_input.val() == '') {
                $import_input.ace_file_input('reset_input');
                return false;
            }
        }).on('file.error.ace', function (ev, info) {
            if (info.error_count['ext'] || info.error_count['mime']) {
                $.notify("请选择Excel文件!");
                return false;
            }
            if (info.error_count['size']) {
                $.notify("文件不超过10M!");
                return false;
            }
        });

        var upload_form = $("#upload_form");
        //导入提交
        upload_form.on('submit', function () {
            var upload_check = $import_input.val();
            if (upload_check == '') {
                $import_input.ace_file_input('reset_input');
                $.notify("请选择文件!");
                return false;
            }
            $import_input.ace_file_input('loading', true);
            $(".ace-file-overlay").append('<div class="overlay-text">正在导入中...</div>');
            //return ;
            upload_form.ajaxSubmit({
                type: 'post', // 提交方式 get/post
                url: upload_form.attr('action'),
                success: function (result) {
                    var dataObj = eval("(" + result + ")");
                    $import_input.ace_file_input('loading', false);
                    var msg = '';
                    if (dataObj.success) {
                        $("#import_modal").modal("hide");  //关闭上传窗口
                        $import_input.val('');
                        $import_input.ace_file_input('reset_input');

                        msg = '导入成功';
                        $("#importResult").html(msg);
                        $("#import_result_modal").modal("show");
//
                        //$.notify(msg);
                    } else {
                        $("#import_modal").modal("hide");   //关闭上传窗口
                        $import_input.val('');
                        $import_input.ace_file_input('reset_input');
                        $import_input.ace_file_input('loading', false);

                        msg = dataObj.msgText + '\n' + dataObj.errorInfo;
                        $("#importResult").html(msg);
                        $("#import_result_modal").modal("show");

                    }
                }
            });
            return false;
        });
        $("#result_btn").on('click', function () {
            $("#import_result_modal").modal("hide");
            $("#btnSearch").click();
        })
    })


</script>
</body>
</html>

