<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-管理公司人员</title>
    <%@ include file="../common/header.jsp"%>

</head>

<body class="no-skin">
<%@ include file="../common/top.jsp"%>
<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp"%>
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
                    <li class="active">安置房概况</li>
                    <li class="active">安置房概况详细</li>
                    <li class="active">修改安置房概况详细</li>
                </ul>
            </div>

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="form" name="form" class="form-horizontal" action="update.htm" method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <input type="hidden" name="id" value="${basePlacementDetail.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">楼号：</label>
                                <div class="col-sm-9">
                                    <input id="build_code" name="build_code" type="text" class="col-xs-10 col-sm-5"
                                           maxlength="50" value="${basePlacementDetail.build_code}">
                                    <label id="build_codeTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">楼层：</label>
                                <div class="col-sm-9">
                                    <input id="floor" type="text" name="floor" class="col-xs-10 col-sm-5"
                                           maxlength="50" value="${basePlacementDetail.floor}"><label
                                        id="floorTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">门牌号：</label>
                                <div class="col-sm-9">
                                    <input id="map_code" type="text" name="map_code" class="col-xs-10 col-sm-5"
                                           maxlength="50" value="${basePlacementDetail.map_code}"><label
                                        id="map_codeTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">户型：</label>
                                <div class="col-sm-9">
                                    <select id="house_type" type="text" name="house_type" class="col-xs-10 col-sm-5">
                                        <option>-选择-</option>
                                        <%--<option <c:if test="${basePlacementDetail.house_type == '平层' }">selected</c:if> >平层</option>--%>
                                        <%--<option <c:if test="${basePlacementDetail.house_type =='错层' }">selected</c:if>>错层</option>--%>
                                        <%--<option <c:if test="${basePlacementDetail.house_type =='跃层' }">selected</c:if>>跃层</option>--%>

                                        <c:forEach items="${baseHouses}" var="listHouse">
                                            <option value="${listHouse.id}" <c:if test="${basePlacementDetail.house_type==listHouse.id}">selected</c:if> >${listHouse.name}</option>
                                        </c:forEach>

                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">面积：</label>
                                <div class="col-sm-9">
                                    <input id="area" type="text" name="area" class="col-xs-10 col-sm-5" placeholder=""
                                    value="${basePlacementDetail.area}"><label
                                        id="areaTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">购房金额：</label>
                                <div class="col-sm-9">
                                    <input id="money" type="text" name="money" class="col-xs-10 col-sm-5" placeholder=""
                                           value="${basePlacementDetail.money}"><label
                                        id="moneyTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注说明：</label>
                                <div class="col-sm-9">
                                    <textarea id="note" name="note" class="col-xs-10 col-sm-5">${basePlacementDetail.note}</textarea><label
                                        id="noteTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">通透：</label>
                                <div class="col-sm-9">
                                    <label class="radio-inline">
                                        <div class="radio">
                                            <label>
                                                <input name="bool_tt" value="1"  <c:if test="${basePlacementDetail.bool_tt ==1 }">checked</c:if> type="radio" class="ace">
                                                <span class="lbl">是</span>
                                            </label>
                                        </div>
                                    </label>
                                    <label class="radio-inline">
                                        <div class="radio">
                                            <label>
                                                <input name="bool_tt" value="0" type="radio" <c:if test="${basePlacementDetail.bool_tt ==0 }">checked</c:if> class="ace">
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
                                                <input name="bool_mc" value="1" type="radio" <c:if test="${basePlacementDetail.bool_mc ==1 }">checked</c:if> class="ace">
                                                <span class="lbl">是</span>
                                            </label>
                                        </div>
                                    </label>
                                    <label class="radio-inline">
                                        <div class="radio">
                                            <label>
                                                <input name="bool_mc" value="0" type="radio" <c:if test="${basePlacementDetail.bool_mc ==0 }">checked</c:if> class="ace">
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
                                                <input name="bool_mw" value="1" type="radio" <c:if test="${basePlacementDetail.bool_mw ==1 }">checked</c:if> class="ace">
                                                <span class="lbl">是</span>
                                            </label>
                                        </div>
                                    </label>
                                    <label class="radio-inline">
                                        <div class="radio">
                                            <label>
                                                <input name="bool_mw" value="0" type="radio"  <c:if test="${basePlacementDetail.bool_mw ==0 }">checked</c:if> class="ace">
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
            <%@ include file="../common/js.jsp"%>
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
                            build_code: {
                                required: true,
                                maxlength: 50
                            },
                            map_code: {
                                required: true,
                                maxlength: 50
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
                        messages : {
                        },
                        submitHandler : function(form) {
                            form.submit();
                        }
                    });

                });

            </script>
</body>
</html>



