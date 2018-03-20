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
    <div class="widget-box" >
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
                        <c:forEach items="${subHome.selectHomes}" var="selectHome">
                            <tr>
                                <td>${subHome.host_name}</td>
                                <td>${selectHome.house_type_name}</td>
                                <td>${selectHome.area}</td>
                                <td>${selectHome.money}</td>
                                <td></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="row"style="margin-top: 10px;">
    <div class="widget-box" >
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

                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    $(function () {
    });
</script>