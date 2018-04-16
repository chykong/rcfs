<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
                    <li class="active">新增安置房概况详细</li>
                </ul>
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal" action="add.htm" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">楼号：</label>
                                <div class="col-sm-9">
                                    <input id="build_code" name="build_code" type="text" class="col-xs-10 col-sm-5"
                                           maxlength="50" value="">
                                    <label id="build_codeTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">楼层：</label>
                                <div class="col-sm-9">
                                    <input id="floor" type="text" name="floor" class="col-xs-10 col-sm-5"
                                           maxlength="50" value=""><label
                                        id="floorTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">门牌号：</label>
                                <div class="col-sm-9">
                                    <input id="map_code" type="text" name="map_code" class="col-xs-10 col-sm-5"
                                           maxlength="50" value=""><label
                                        id="map_codeTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">户型：</label>
                                <div class="col-sm-9">
                                    <select id="house_type" type="text" name="house_type" class="col-xs-10 col-sm-5">
                                        <option value="">-选择-</option>
                                        <%--<option value="平层">平层</option>--%>
                                        <%--<option value="错层">错层</option>--%>
                                        <%--<option value="跃层">跃层</option>--%>
                                        <c:forEach items="${baseHouses}" var="house" varStatus="st">
                                            <option value="${house.id}">${house.name}</option>

                                        </c:forEach>

                                    </select>
                                    <label id="house_typeTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">面积：</label>
                                <div class="col-sm-9">
                                    <input id="area" type="text" name="area" class="col-xs-10 col-sm-5" placeholder=""
                                           value=""><label
                                        id="areaTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">购房金额：</label>
                                <div class="col-sm-9">
                                    <input id="money" type="text" name="money" class="col-xs-10 col-sm-5" placeholder=""
                                           value=""><label
                                        id="moneyTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注说明：</label>
                                <div class="col-sm-9">
                                    <textarea id="note" name="note" class="col-xs-10 col-sm-5"></textarea><label
                                        id="noteTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">通透：</label>
                                <div class="col-sm-9">
                                    <label class="radio-inline">
                                        <div class="radio">
                                            <label>
                                                <input name="bool_tt" type="radio" class="ace" value="1">
                                                <span class="lbl">是</span>
                                            </label>
                                        </div>
                                    </label>
                                    <label class="radio-inline">
                                        <div class="radio">
                                            <label>
                                                <input name="bool_tt" type="radio" class="ace" value="0">
                                                <span class="lbl">否</span>
                                            </label>
                                        </div>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">明厨：</label>
                                <div class="col-sm-9">
                                    <label class="radio-inline">
                                        <div class="radio">
                                            <label>
                                                <input name="bool_mc" type="radio" class="ace" value="1">
                                                <span class="lbl">是</span>
                                            </label>
                                        </div>
                                    </label>
                                    <label class="radio-inline">
                                        <div class="radio">
                                            <label>
                                                <input name="bool_mc" type="radio" class="ace" value="0">
                                                <span class="lbl">否</span>
                                            </label>
                                        </div>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">明卫：</label>
                                <div class="col-sm-9">
                                    <label class="radio-inline">
                                        <div class="radio">
                                            <label>
                                                <input name="bool_mw" type="radio" class="ace" value="1">
                                                <span class="lbl">是</span>
                                            </label>
                                        </div>
                                    </label>
                                    <label class="radio-inline">
                                        <div class="radio">
                                            <label>
                                                <input name="bool_mw" type="radio" class="ace" value="0">
                                                <span class="lbl">否</span>
                                            </label>
                                        </div>
                                    </label>
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
                $(document).ready(function () {
                    $("#form").validate({
                        //debug : true,
                        errorElement: "label",
                        errorClass: "valiError",
                        errorPlacement: function (error, element) {
                            error.appendTo($("#" + element.attr('id') + "Tip"));
                        },
                        rules: {
                            build_code: {
                                required: true,
                                maxlength: 50
                            },
                            map_code: {
                                required: true,
                                maxlength: 50
                            },
                            floor: {
                                required: true,
                                maxlength: 50,
                                number: true
                            },
                            house_type: {
                                required: true,
                                maxlength: 50
                            },
                            area: {
                                required: true,
                                maxlength: 5,
                                number: true
                            },
                            money: {
                                required: true,
                                maxlength: 20,
                                number: true
                            },
                            note: {
                                maxlength: 500
                            },
                            bool_tt: {
                                required: true
                            },
                            bool_mc: {
                                required: true
                            },
                            bool_mw: {
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

