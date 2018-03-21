<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<div class="row">
    <h4 class="widget-title lighter">
        <span>
            <i class="ace-icon fa fa-star orange"></i>
            选房方式：${preallocation.compensation_type == 1?"按确权面积":"按人口指标"}
        </span>
    </h4>
</div>
<div class="row">
    <div class="widget-box">
        <div class="widget-header"><h4 class="widget-title lighter smaller">
            <i class="ace-icon fa fa-user blue"></i><span class="show_text">按确权面积选房</span>
        </h4>
            <div class="widget-toolbar">
                <strong class="show-head">收起</strong>
                <a href="#" data-action="collapse">
                    <i class="ace-icon fa fa-chevron-up"></i>
                </a>
            </div>
        </div>
        <div class="widget-body">
            <div class="row no-margin">
                <h4 class="widget-title lighter">
                    <span>
                        <i class="ace-icon fa fa-star orange"></i>
                        选房指标：${preallocation.cog_land_area}㎡、实际选房面积：<span id="area-area"></span>㎡、剩余选房指标：<span
                            id="area-sub">${preallocation.cog_land_area}</span>㎡、
                        总购房款结算：<span id="area-money">${preallocation.money_homestead}</span>、备注：
                    </span>
                </h4>
            </div>
            <table id="area-table" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>购房人</th>
                    <th>购房户型</th>
                    <th>购房面积</th>
                    <th>购房款</th>
                    <th>备注</th>
                </tr>
                </thead>
                <tbody id="area-tbody">
                <c:forEach items="${preallocation.subHome}" var="subHome">
                    <c:if test="${subHome.compensation_type == 1 }">
                        <c:forEach items="${subHome.selectHomes}" var="selectHome">
                            <tr class="select-item">
                                <td>${subHome.host_name}</td>
                                <td>${selectHome.house_type_name}</td>
                                <td class="select-area">${selectHome.area}</td>
                                <td class="select-money">${selectHome.money}</td>
                                <td></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="row" style="margin-top: 10px;">
    <div class="widget-box">
        <div class="widget-header"><h4 class="widget-title lighter smaller">
            <i class="ace-icon fa fa-user blue"></i><span class="show_text">按人口指标选房</span>
        </h4>
            <div class="widget-toolbar">
                <strong class="show-head">收起</strong>
                <a href="#" data-action="collapse">
                    <i class="ace-icon fa fa-chevron-up"></i>
                </a>
            </div>
        </div>
        <div class="widget-body">
            <div class="row no-margin">
                <h4 class="widget-title lighter">
                    <span>
                        <i class="ace-icon fa fa-star orange"></i>
                        选房指标：${preallocation.relas.size()}人、实际选房面积：<span id="rk-area"></span>㎡、剩余选房指标：<span
                            id="rk-sub">${preallocation.relas.size()}</span>㎡、
                        总购房款结算：<span id="rk-money">${preallocation.money_homestead}</span>、备注：
                    </span>
                </h4>
            </div>
            <table id="rk-table" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>购房人</th>
                    <th>购房户型</th>
                    <th>购房面积</th>
                    <th>购房款</th>
                    <th>备注</th>
                </tr>
                </thead>
                <tbody id="rk-tbody">
                <c:forEach items="${preallocation.subHome}" var="subHome">
                    <c:if test="${subHome.compensation_type == 2 }">
                        <c:forEach items="${subHome.selectHomes}" var="selectHome">
                            <tr class="select-item">
                                <td>${subHome.host_name}</td>
                                <td>${selectHome.house_type_name}</td>
                                <td class="select-rk-area">${selectHome.area}</td>
                                <td class="select-rk-money">${selectHome.money}</td>
                                <td></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    $(function () {
        var $area_area = $('#area-area');
        var $area_sub = $('#area-sub');
        var $area_money = $('#area-money');

        var $area_select = $('.select-area');
        var selected_area = 0;
        if($area_select.length > 0){
            $area_select.each(function (i, item) {
                selected_area += parseFloat($(item).html());
            })
        }


        var $money_select = $('.select-money');
        var selected_money = 0;
        if($money_select.length > 0){
            $money_select.each(function (i, item) {
                selected_money += parseFloat($(item).html());
            })
        }

        $area_area.html(selected_area);
        $area_sub.html(parseFloat($area_sub.html()) - selected_area);
        $area_money.html(parseFloat($area_money.html()) - selected_money);

        /**/
        var $rk_area = $('#rk-area');
        var $rk_sub = $('#rk-sub');
        var $rk_money = $('#rk-money');

        var $area_select_rk = $('.select-rk-area');
        var selected_area_rk = 0;

        if ($area_select_rk.length > 0) {
            $area_select_rk.each(function (i, item) {
                selected_area_rk += parseFloat($(item).html());
            })
        }


        var $money_select_rk = $('.select-rk-money');
        var selected_money_rk = 0;
        if ($money_select_rk.length > 0) {
            $money_select_rk.each(function (i, item) {
                selected_money_rk += parseFloat($(item).html());
            })
        }

        $rk_area.html(selected_area_rk);
        $rk_sub.html(parseFloat($rk_sub.html()) * 50 - selected_area_rk);
        $rk_money.html(parseFloat($rk_money.html()) - selected_money_rk);
    });
</script>