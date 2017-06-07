<%--@elvariable id="site_name" type="java.lang.String"--%>
<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfToken"--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${site_name} 整体进度图</title>
    <link rel="stylesheet" href="<c:url value="/assets/highcharts/css/highcharts.css"/>"/>


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
                    <li class="active"><small>整体进度图</small></li>
                </ul>
                <!-- /.breadcrumb -->
                <%@ include file="../common/navigate.jsp"%>
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="hr hr-18 dotted hr-double"></div>
                        <div style="overflow: auto">
                            <div id="container" style="min-width: 400px;height: 400px;
                            <c:if test="${inHostList.size()>15}">width: ${inHostList.size() * 100}px</c:if>
                                    "></div>
                        </div>
                    </div>
                    <!-- /.span -->
                </div>
            </div>
        </div>
    </div>
</div>


</body>

<javascripts>
    <%@ include file="../common/js.jsp" %>
    <script src="<c:url value="/assets/js/jquery.form.js"/>"></script>
    <script src="<c:url value="/assets/highcharts/js/highcharts.js"/>"></script>
    <script>
        $(function () {
            /*横轴长度*/
            var xAxis_length = ${inHostList.size()-1};
            /*横轴 各列数据*/
            var xAxis = [], countHosts = [], countContract = [], countHandover = [];
            <c:forEach items="${inHostList}" var="inhost">
                xAxis.push('${inhost.title}');
                countHosts.push(${inhost.countLeftDay});
            </c:forEach>
            <c:forEach items="${contractList}" var="contract">
                countContract.push(${contract.countLeftDay});
            </c:forEach>
            <c:forEach items="${handoverList}" var="handover">
                countHandover.push(${handover.countLeftDay});
            </c:forEach>
            $('#container').highcharts({
                chart: {
                    type: 'line'
                },
                title: {
                    text: '整体进度图'
                },
                subtitle: {
                    text: ''
                },
                xAxis: {
                    categories: xAxis,
                    max: xAxis_length,
                    tickWidth: 100
                },
                yAxis: [{
                    min: 0,
                    title: {
                        text: '数目'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                }],
                credits: {
                    enabled: false //去除版权信息
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormatter: function () {
                            return '<tr><td style="color:{series.color};padding:0">' + this.series.name + ': </td>' +
                                '<td style="padding:0"><b>' + this.y + ' 户</b></td></tr>';
                    },
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y}'
                        }
                    }
                },
                series: [{
                    name: '入户',
                    data: countHosts,
                    visible: true
                }, {
                    name: '签约',
                    data: countContract,
                    visible: true
                }, {
                    name: '交房',
                    data: countHandover,
                    visible: true
                }],
                lang: {
                    contextButtonTitle: '导出按钮文字',
                    decimalPoint: '小数点',
                    downloadJPEG: '导出JPEG',
                    downloadPDF: '导出PDF',
                    downloadPNG: '导出PNG',
                    downloadSVG: '导出SVG',
                    drillUpText: '上钻',
                    invalidDate: '无效的时间',
                    loading: '加载中',
                    months: '月份',
                    noData: '没有数据',
                    numericSymbolMagnitude: '国际单位符基数',
                    numericSymbols: '国际单位符',
                    printChart: '打印图表',
                    resetZoom: '重置缩放比例',
                    resetZoomTitle: '重置缩放标题',
                    shortMonths: '月份缩写',
                    shortWeekdays: '星期缩写',
                    thousandsSep: '千分号',
                    weekdays: '星期'
                }
            });

        });
    </script>
</javascripts>
</html>
