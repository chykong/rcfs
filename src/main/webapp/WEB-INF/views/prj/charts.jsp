<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="${pageContext.request.contextPath}/assets/echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/echarts/shine.js"></script>
    <script src="${pageContext.request.contextPath}/assets/components/jquery/dist/jquery.js?"></script>

</head>

<body>
<table border="0">
    <tr>
        <td>
            <div id="left" style="width: 600px;height:300px;flow:left;"></div>
        </td>
        <td>
            <div id="right" style="width: 300px;height:300px;float: left;"> </div>
        </td>
    </tr>
</table>
<script type="text/javascript">
       // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('left'),'shine');
        var myChart2 = echarts.init(document.getElementById('right'),'shine');
        var option = {
            title:{ text: '交房各组进组统计'
            },
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
        },
        legend: {
            data:['当日交房','累计交房']
        },
        grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
        },
    xAxis : [
        {
            type : 'category',
            data : []
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : []
};
        myChart.showLoading();
        // 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option);

 var option2 = {
     title: {
        text: '交房累计完成度'
    },
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
    },
    toolbox: {
     show : false
    },
    series: []
};
myChart2.setOption(option2);

// 异步加载数据

$.get('${pageContext.request.contextPath}/prj/charts/loaddata.htm').done(function (json) {
     myChart.hideLoading();
    var data = eval('('+json + ')');
    // 填入数据
    myChart.setOption({
        xAxis: {
            data: data.categories
        },
        series: data.barData
    });
    myChart2.setOption({
        series: data.guageData
    });
});




</script>


</body>
</html>