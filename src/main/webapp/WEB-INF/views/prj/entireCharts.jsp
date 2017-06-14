<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-整体进度图</title>
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
                    <li class="active">系统管理</li>
                    <li class="active">进度管理</li>
                    <li class="active">
                        <small>整体进度图</small>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
                <%@ include file="../common/navigate.jsp" %>
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="hr hr-18 dotted hr-double"></div>
                        <div class="row">
                            <div id="divSche" style="height: 400px;"></div>
                        </div>
                    </div>
                    <!-- /.span -->
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/js.jsp" %>
<script src="${pageContext.request.contextPath}/assets/echarts/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/echarts/shine.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('divSche'), 'shine');
    option = {
        title: {
            text: '整体进度图-按${typeName}'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['入户', '签约', '交房']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: []
        },
        yAxis: {
            type: 'value'
        },
        series: []
    };
    myChart.showLoading();
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);


    // 异步加载数据

    $.get('${pageContext.request.contextPath}/prj/charts/getEntire.htm?type=${type}').done(function (json) {
        myChart.hideLoading();
        var data = eval('(' + json + ')');
        // 填入数据
        myChart.setOption({
            xAxis: {
                data: data.categories
            },
            series: data.lineData
        });

    });
</script>

</body>