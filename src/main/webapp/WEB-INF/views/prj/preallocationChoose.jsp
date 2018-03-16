<%--@elvariable id="site_name" type="java.lang.String"--%>
<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfToken"--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${site_name} 项目实施管理</title>
    <link rel="stylesheet" href="<c:url value="/assets/datatables/css/dataTables.bootstrap.css"/>"/>
    <link rel="stylesheet"
          href="<c:url value="/assets/datatables/extensions/FixedColumns/css/fixedColumns.dataTables.css"/>">
    <link rel="stylesheet"
          href="<c:url value="/assets/datatables/extensions/FixedColumns/css/fixedColumns.bootstrap.css"/>">

    <%@ include file="../common/header.jsp" %>
    <style type="text/css">
        .row {
            margin: 0;
        }

        .btn-primary, .btn-primary:focus {
            border-color: #ffffff;
        }

        .widget-main {
            padding: 0;
        }

        a {
            color: white;
        }

        a:hover {
            color: #fe9e19;
        }

        td {
            cursor: pointer;
        }

        .selected {
            background: #d3413b !important;
        }

        .no-selected {
            background: #85b558;
        }

        .buildSpan {
            background: #85b558 !important;
            border-color: #85b558 !important;
        }

        .build-active, .select-active {
            background: #286090 !important;
            border-color: #286090 !important;
        }

        .select-now {
            background: #d3413b !important;
        }

        .buildSpan:hover {
            background: #85b558 !important;
            border-color: #85b558 !important;
        }

        .dataTable {
            width: 100% !important;
        }

        .dataTables_scrollFootInner {
            width: 100% !important;
        }

        .select-info {
            width: 32% !important;
            padding: 5px !important;
            display: inline-block;
            margin-left: 2px;
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
                    <li class="active">项目实施管理</li>
                    <li class="active">
                        <small>选房管理</small>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
                <%@ include file="../common/navigate.jsp" %>
            </div>
            <div class="page-content">
                <div class="tabbable">
                    <!-- #section:pages/faq -->
                    <ul class="nav nav-tabs padding-18 tab-size-bigger" id="myTab">
                        <li class="active" data-type="1">
                            <a data-toggle="tab" href="#faq-tab-basic" aria-expanded="true">
                                <i class="blue ace-icon fa fa-user bigger-120"></i>
                                选房
                            </a>
                        </li>
                        <li class="" data-type="3">
                            <a data-toggle="tab" href="#faq-tab-jd" aria-expanded="true">
                                <i class="blue ace-icon fa fa-bar-chart-o bigger-120"></i>
                                选房列表
                            </a>
                        </li>
                    </ul>
                    <div class="tab-content no-border padding-24">
                        <!-- 基本情况 begin-->
                        <div id="faq-tab-basic" class="tab-pane fade active in" data-type="1">
                            <div id="search" class="row center">
                                <div class="col-xs-12">
                                    <label class="">请输入选房号:</label>
                                    <input id="search_input" type="text" class="" value=""
                                           style="height: 42px;line-height: 40px;"/>
                                    <a class="btn btn-primary" id="confirm_code"><i class="ace-icon fa fa-search"></i>
                                        查询</a>
                                </div>
                                <!-- /.span -->
                            </div>
                            <div id="info" class="row hidden" style="margin-top: 5px">
                                <div class="col-xs-6">
                                    <table class="table table-striped table-bordered table-hover">
                                        <tr>
                                            <td class="text-right no-bg"><strong>产权人:</strong></td>
                                            <td class="no-bg" id="name"></td>
                                            <td class="text-right no-bg" width="120">编号：</td>
                                            <td class="no-bg" id="map_id"></td>
                                        </tr>
                                        <tr>
                                            <td class="text-right no-bg">补偿方式:</td>
                                            <td class="no-bg" id="select_type"></td>
                                            <td class="text-right no-bg people" width="120">认定安定人口:</td>
                                            <td class="no-bg people" id="people_num"></td>

                                        </tr>
                                        <tr>
                                            <td class="text-right no-bg">应安置面积:</td>
                                            <td class="no-bg" id="az_area"></td>
                                            <td class="text-right no-bg" width="120">选房顺序号:</td>
                                            <td class="no-bg" id="select_code"></td>
                                        </tr>
                                    </table>
                                    <div class="center">
                                        <a class="btn btn-success" id="select"><i class="ace-icon fa fa-check"></i>确认信息</a>
                                        <a class="btn btn-danger hidden" id="no-select"><i
                                                class="ace-icon fa fa-undo"></i>返回</a>
                                    </div>
                                </div>
                                <div class="col-xs-5 no-padding">
                                    <div id="select-items">
                                    </div>
                                    <div style="text-align: center">
                                        <div id="select_confirm" class="row center hidden"
                                             style="display: inline-block;">
                                            <button class="btn btn-primary">选房</button>
                                        </div>
                                        <div id="confirm" class="row center hidden"
                                             style="display: inline-block;">
                                            <button class="btn btn-primary">完成选房</button>
                                        </div>
                                    </div>
                                </div>
                                <div id="select-infos" class="col-xs-1 no-padding" style="border: 1px solid #CCC;">
                                    <span class="">已选择：<span id="select_num">0</span> 套<br/>剩余：<span
                                            id="sub_area"></span>㎡</span>
                                </div>
                                <!-- /.span -->
                            </div>
                            <div id="error" class="row hidden" style="margin-top: 5px">
                                <div class="col-xs-8 col-xs-offset-2">
                                    <table class="table table-striped table-bordered table-hover">
                                        <tr>
                                            <td class="text-center no-bg"><strong>未查询到该选房顺序号</strong></td>
                                        </tr>
                                    </table>
                                </div>
                                <!-- /.span -->
                            </div>
                            <div id="xk" class="row hidden">
                                <div class="col-xs-12 no-padding">
                                    <div class="row">
                                        <div class="col-sm-1 no-padding">
                                            <div class="widget-box">
                                                <div class="widget-body">
                                                    <!--  -->
                                                    <div id="container1" style=" overflow: auto;" align="center">
                                                        <c:forEach items="${basePlacementSelect.buildCodeList}"
                                                                   var="buildCode" varStatus="buildSt">
                                                            <span id="${buildCode}"
                                                                  class="btn btn-large btn-block btn-primary build <c:if test="${buildSt.index !=0}">buildSpan</c:if> <c:if test="${buildSt.index ==0}">build-active</c:if> ">
                                                                 ${buildCode}# 号楼
                                                            </span>
                                                        </c:forEach>

                                                    </div>
                                                    <div id="container2"
                                                         style="overflow: auto; margin-top:40px;padding: 0"
                                                         align="center">
                                                        <div class="row">
                                                            <div class="col-xs-12 no-padding">
                                                                <p style="background: #85b558;" class="text-center">
                                                                    可售 ${basePlacementSelect.total - basePlacementSelect.noSelect}套
                                                                </p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-12 no-padding">
                                                                <p style="background: #d3413b;" class="text-center">
                                                                    已售 ${basePlacementSelect.noSelect}套
                                                                </p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-12 no-padding">
                                                                <p style="background: #FFFFFF" class="text-center">
                                                                    总计 ${basePlacementSelect.total}套
                                                                </p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /.widget-body -->
                                            </div>
                                            <!-- /.widget-box -->
                                        </div>
                                        <div class="col-sm-9">
                                            <div class="widget-box">
                                                <div class="widget-body">
                                                    <div class="widget-main">
                                                        <!-- #section:plugins/charts.flotchart -->
                                                        <div id="container3"
                                                             style="min-width: 70%; overflow: auto;max-height: 500px;"
                                                             align="center">

                                                            <c:forEach items="${basePlacementSelect.buildCodeList}"
                                                                       var="buildCode" varStatus="st">
                                                                <div id="build_${buildCode}"
                                                                     <c:if test="${st.index != 0}">class="hidden"</c:if>>
                                                                    <table class="table table-bordered table-striped"
                                                                           id="tb">
                                                                        <tbody>
                                                                        <c:forEach
                                                                                items="${basePlacementSelect.floorList}"
                                                                                var="floor">
                                                                            <c:if test="${floor.buildCode == buildCode}">
                                                                                <tr>
                                                                                    <td class="no-bg">
                                                                                        <strong>${floor.floor}层</strong>
                                                                                    </td>
                                                                                    <c:forEach
                                                                                            items="${basePlacementSelect.selectList}"
                                                                                            var="select">
                                                                                        <c:if test="${floor.floor == select.floor && select.build_code == buildCode}">
                                                                                            <td style="text-align: center;color:white;"
                                                                                                id="select_${select.id}"
                                                                                                class="no-selected <c:if test="${select.is_select == 1}">selected</c:if>"
                                                                                                data-url="${select.house_url}"
                                                                                                data-build="${buildCode}"
                                                                                                data-floor="${floor.floor}"
                                                                                                data-note="${select.note}"
                                                                                                data-map="${select.map_code}"
                                                                                                data-area="${select.area}"
                                                                                                data-money="${select.money}"
                                                                                                data-id="${select.id}"
                                                                                                onclick="SelectdHouse('${buildCode}','${floor.floor}','${select.map_code}','${select.money}','${select.area}','${select.id}',this)">
                                                                                                    ${select.map_code}
                                                                                            </td>
                                                                                        </c:if>
                                                                                    </c:forEach>
                                                                                </tr>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- /.widget-main -->
                                                </div>
                                                <!-- /.widget-body -->
                                            </div>
                                            <!-- /.widget-box -->
                                        </div>
                                        <!-- /.col -->
                                        <div class="col-sm-2">
                                            <div class="widget-box">
                                                <div class="widget-body">
                                                    <div id="container" style="height: 500px" align="center">
                                                        <div class="row">
                                                            <div class="col-xs-12">
                                                                <p class="text-center">
                                                                    <strong>
                                                                    </strong></p><h4><strong>
                                                                详细信息
                                                            </strong></h4><strong>
                                                            </strong>
                                                                <p></p>
                                                            </div>
                                                        </div>
                                                        <%--<div class="row">--%>
                                                        <%--<h4>楼栋图</h4>--%>
                                                        <%--<div class="col-xs-12">--%>
                                                        <%--<a target="_blank"--%>
                                                        <%--href="<c:url value="/assets/images/201703211305450136.jpg"/>"><img--%>
                                                        <%--src="<c:url value="/assets/images/201703211305450136.jpg"/>"--%>
                                                        <%--style="width:80%;"></a>--%>
                                                        <%--</div>--%>
                                                        <%--</div>--%>
                                                        <div class="row">
                                                            <h4>户型图</h4>
                                                            <div class="col-xs-12">
                                                                <a href="#"
                                                                   target="_blank" id="house_link">
                                                                    <img id="house_img" style="width:100%;"
                                                                         src=""></a>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-6 no-padding">
                                                                <p class="text-right">
                                                                    楼层：
                                                                </p>
                                                            </div>
                                                            <div class="col-xs-6 no-padding"><p class="text-left"
                                                                                                id="floor_show">
                                                            </p></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-6 no-padding">
                                                                <p class="text-right">
                                                                    房号：
                                                                </p>
                                                            </div>
                                                            <div class="col-xs-6 no-padding"><p class="text-left"
                                                                                                id="map_show">
                                                            </p></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-6 no-padding">
                                                                <p class="text-right">
                                                                    建筑面积：
                                                                </p>
                                                            </div>
                                                            <div class="col-xs-6 no-padding"><p class="text-left"
                                                                                                id="area"></p></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-6 no-padding">
                                                                <p class="text-right">
                                                                    金额：
                                                                </p>
                                                            </div>
                                                            <div class="col-xs-6 no-padding"><p class="text-left"
                                                                                                id="money"></p></div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-xs-6 no-padding">
                                                                <p class="text-right">
                                                                    情况说明：
                                                                </p>
                                                            </div>
                                                            <div class="col-xs-6 no-padding" id="note"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /.widget-body -->
                                            </div>
                                            <!-- /.widget-box -->
                                        </div>
                                    </div>
                                </div>
                                <!-- /.span -->
                            </div>
                        </div>
                        <div id="faq-tab-jd" class="tab-pane fade" data-type="2">
                            <div class="row">
                                <div class="col-xs-12">
                                    <%@ include file="./_select_datatable.jsp" %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="qr_modal" class="modal fade" tabindex="-21">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    选房确认单
                </div>
            </div>
            <div class="modal-body no-padding">
                <div class="widget-box" style="border: none">
                    <div class="widget-body">
                        <div class="widget-main">
                            <c:url value="/prj/preallocation/choose/saveSelectHouse.htm" var="save_url"/>
                            <form:form action="${save_url}" method="post" id="js_form">
                                <input type="hidden" id="save_map" name="map_id" value=""/>
                                <input type="hidden" id="save_select" name="id" value=""/>
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <table class="table table-striped table-bordered table-hover" id="qr_table">
                                            <tr>
                                                <td class="text-right no-bg">产权人:</td>
                                                <td class="no-bg" id="qr_name"></td>
                                                <td class="text-right no-bg" width="120">编号：</td>
                                                <td class="no-bg" id="qr_code"></td>
                                            </tr>
                                            <tr>
                                                <td class="text-right no-bg">补偿方式:</td>
                                                <td class="no-bg" id="qr_type"></td>
                                                <td class="text-right no-bg people" width="120">认定安定人口:</td>
                                                <td class="no-bg people" id="qr_people"></td>

                                            </tr>
                                            <tr>
                                                <td class="text-right no-bg">应安置面积：</td>
                                                <td class="no-bg" id="qr_area"></td>
                                                <td class="text-right no-bg" width="120">选房顺序号：</td>
                                                <td class="no-bg" id="qr_select"></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div class="col-xs-12 center">
                                        <h4>确认选择该安置房吗?确认后不可更改</h4>
                                    </div>
                                    <div class="col-xs-12" align="center" style="margin-top: 10px;">
                                        <button id="queren" class="btn btn-white btn-primary" type="button"
                                                data-dismiss="modal">
                                            <i class="ace-icon fa fa-check bigger-110"></i> 确认
                                        </button>
                                        <button class="btn btn-white btn-primary" type="button" data-dismiss="modal">
                                            <i class="ace-icon fa fa-print bigger-110"></i> 打印
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


</body>

<javascripts>
    <%@ include file="../common/js.jsp" %>
    <script src="${staticServer}/assets/js/jquery.form.js"></script>

    <script src="<c:url value="/assets/datatables/js/jquery.dataTables.js"/>"></script>
    <script src="<c:url value="/assets/datatables/js/dataTables.bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/datatables/extensions/FixedColumns/js/dataTables.fixedColumns.js"/>"></script>
    <script>
        $(function () {
            $('#confirm_code').on('click', function () {
                if ($("#search_input").val() == '') {
                    $("#search_input").focus();
                    return;
                }

                $.ajax({
                    url: '<c:url value="/prj/preallocation/choose/getBySelectCode.htm"/>',
                    data: {
                        select_code: $("#search_input").val()
                    },
                    type: 'post',
                    success: function (result) {
                        var json = eval('(' + result + ')');
                        if (json.status != 20 && json.status != 50) {
                            bootbox.alert('未签约完成无法选房!');
                            return;
                        }
                        if (json.status == 50) {
                            bootbox.alert('该户已经选房完毕!');
                            return;
                        }
                        if (json != null) {
                            $('#name').html(json.host_name);
                            $('#qr_name').html(json.host_name);
                            $('#js_name').html(json.host_name);

                            $('#map_id').html(json.map_id);
                            $('#qr_code').html(json.map_id);
                            $('#js_code').html(json.map_id);
                            var select_type = json.compensation_type == 1 ? '按面积' : '按人均50';
                            $('#select_type').html(select_type);
                            $('#qr_type').html(select_type);
                            $('#js_type').html(select_type);

                            $('#select_code').html(json.signed_code);
                            $('#qr_select').html(json.signed_code);
                            $('#js_select').html(json.signed_code);

                            $('#js_total').html(transNum(json.allocation_housing_assess_price, 1));
                            if (json.compensation_type == 1) {
                                $('#az_area').html(json.total_homestead_area);
                                $('#sub_area').html(json.total_homestead_area);
                                $('#qr_area').html(json.total_homestead_area);
                                $('#js_area').html(json.total_homestead_area);

                                $('.people').addClass('hidden');
                            } else {
                                $('#people_num').html(json.relas_num);
                                $('#qr_people').html(json.relas_num);
                                $('#js_people').html(json.relas_num);

                                $('#az_area').html(json.relas_num * 50);
                                $('#sub_area').html(json.relas_num * 50);
                                $('#qr_area').html(json.relas_num * 50);
                                $('#js_area').html(json.relas_num * 50);

                                $('.people').removeClass('hidden');
                            }
                            $('#info').removeClass('hidden');
                            $('#error').addClass('hidden');
                        } else {
                            $('#error').removeClass('hidden');
                            $('#info').addClass('hidden');
                        }
                    }
                });
            })

            $('.build').on('click', function () {
                var $that = $(this);
                $that.siblings().removeClass('build-active').addClass('buildSpan');
                $that.addClass('build-active').removeClass('buildSpan');

                var id = $that.attr('id');
                var $div = $('#build_' + id);
                $div.siblings().addClass('hidden');
                $div.removeClass('hidden');
            });
            $('#select').on('click', function () {
                $('#search').addClass('hidden');
                $('#select').addClass('hidden');
                $('#xk').removeClass('hidden');
                $('#select_confirm').removeClass('hidden');
                $('#confirm').removeClass('hidden');
                $('#no-select').removeClass('hidden');
            })

            $('#no-select').on('click', function () {
                $('#info').addClass('hidden');
                $('#xk').addClass('hidden');
                $('#select_confirm').addClass('hidden');
                $('#confirm').addClass('hidden');
                $('#search').removeClass('hidden');
                $('#search_input').focus();
                $('#select').removeClass('hidden');
                $('#no-select').addClass('hidden');
            })
            $('#select_confirm').on('click', function () {
                var $select_active = $('.select-active');
                if ($select_active.length != 1) {
                    bootbox.alert('请选择安置房');
                    return;
                }

                var select_item = $select_active[0];
                var sub_area = parseFloat($('#sub_area').html());
                var select_area = parseFloat($(select_item).attr("data-area"));

                if (sub_area < select_area) {
                    bootbox.alert('剩余安置面积不足');
                    return;
                } else {
                    sub_area = sub_area - select_area;
                    $('#sub_area').html(sub_area);
                }

                //tab显示
                var build_html = $(select_item).attr("data-build") + '号楼 ' + $(select_item).attr("data-map") + ' ' + $(select_item).attr("data-area") + '㎡';
                var _html = '<div class="alert alert-info select-info">\
                    <button type="button" data-id="' + $(select_item).attr("id") + '" class="close select-close" data-dismiss="alert">\
                    <i class="ace-icon fa fa-times"></i>\
                    </button>\
                    <strong>' + build_html + '</strong>\
                    <br>\
                    </div>';
                $('#select-items').append(_html);
                //表单显示
                var table_html = $(select_item).attr("data-build") + '号楼 ' + $(select_item).attr("data-map");
                var tr_html ='<tr  id="js_' + $(select_item).attr("id") +'">\
                        <td class="text-right no-bg">选房位置：</td>\
                    <td class="no-bg" >' + table_html + '</td>\
                        <td class="text-right no-bg" width="120">选房面积：</td>\
                    <td class="no-bg" >' + $(select_item).attr("data-area") + '</td>\
                        </tr>';

                $("#qr_table").append(tr_html);

                $(select_item).removeClass('select-active').addClass('select-now');
                var $select_info = $('.select-now');
                $('#select_num').html($select_info.length);
            })
            $('#select-items').on('click', '.select-close', function () {
                var id = $(this).attr('data-id');

                var select_item = $('#' + id);
                var sub_area = parseFloat($('#sub_area').html());
                var select_area = parseFloat($(select_item).attr("data-area"));

                sub_area = sub_area + select_area;
                $('#sub_area').html(sub_area);
                $(select_item).addClass('no-selected').removeClass('select-now');

                var $select_info = $('.select-now');
                $('#select_num').html($select_info.length);

                $('#js_' + id).remove();

            })
            $('#confirm').on('click', function () {
                var $select_info = $('.select-now');
                if ($select_info.length < 1) {
                    bootbox.alert('请选择安置房');
                    return;
                }
                var map_ids = '';
                $select_info.each(function(i,item){
                    console.log($(item))
                    map_ids += $(item).attr("data-id") + ',';
                })
                if(map_ids.length > 1){
                    map_ids = map_ids.substring(0,map_ids.length-1);
                }
                $('#save_select').val(map_ids);
                $('#save_map').val($('#qr_code').html());
                $('#qr_modal').modal({
                    backdrop: 'static',
                    show: true
                });
            })
            $('#queren').on('click', function () {
                $("#js_form").ajaxSubmit({
                    type: 'post',
                    // 提交方式 get/post
                    url: $("#js_form").attr('action'),
                    // 需要提交的 url
                    success: function (data) { // data 保存提交后返回的数据，一般为 json 数据
                        var dataObj = eval("(" + data + ")");
                        console.log(dataObj);
                    }
                });
            })
        });

        function transNum(s, type) {

            if (!/^(-)?[0-9\.]*$/.test(s))
                return "0.00";
            if (s == null || s == "")
                return "0.00";
            if (s >= 0) {
                s = s.toString().replace(/^(\d*)$/, "$1.");
                s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");
                s = s.replace(".", ",");
                var re = /(\d)(\d{3},)/;
                while (re.test(s))
                    s = s.replace(re, "$1,$2");
                s = s.replace(/,(\d\d)$/, ".$1");
                if (type == 0) {// 不带小数位(默认是有小数位)
                    var a = s.split(".");
                    if (a[1] == "00") {
                        s = a[0];
                    }
                }

            } else if (s < 0) {
                s = 0 - s;
                s = s.toString().replace(/^(\d*)$/, "$1.");
                s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");
                s = s.replace(".", ",");
                var re = /(\d)(\d{3},)/;
                while (re.test(s))
                    s = s.replace(re, "$1,$2");
                s = "-" + s.replace(/,(\d\d)$/, ".$1");
                if (type == 0) {// 不带小数位(默认是有小数位)
                    var a = s.split(".");
                    if (a[1] == "00") {
                        s = -a[0];
                    }
                }
            }
            return s;
        }
        function SelectdHouse(buildCode, floor, map_code, money, area, id, control) {
            if ($(control).hasClass('selected') || $(control).hasClass('select-now')) {
                return;
            }
            var $td = $('#xk').find('td');
            $td.each(function (i, item) {
                if (!$(item).hasClass('no-bg')) {
                    $(item).css('background', '#85b558');
                    $(item).addClass('no-selected').removeClass('select-active');
                }
            });
            $(control).removeClass('no-selected').addClass('select-active');

            var house_url = '${imageServer}' + $(control).attr('data-url');
            $('#house_link').attr('href', house_url);
            $('#house_img').attr('src', house_url);
            $('#floor_show').html(buildCode + '号楼 ' + floor + '层');
            $('#map_show').html(map_code);
            $('#area').html(area + '㎡');
            $('#money').html(transNum(money, 1));
            $('#note').html($(control).attr('data-note'));

            $("#qr_location").html(buildCode + '号楼 ' + floor + '层 ' + map_code);
            $("#qr_select_area").html(area + '㎡');

        }
    </script>
</javascripts>
</html>
