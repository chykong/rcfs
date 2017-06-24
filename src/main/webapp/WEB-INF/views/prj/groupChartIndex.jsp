<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-分组进度图</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-datepicker/bootstrap-datepicker3.css"/>"/>


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
                        <small>分组进度图</small>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
                <%@ include file="../common/navigate.jsp" %>
            </div>
            <div class="page-content">
                <div class="widget-box widget-color-blue" id="widget-box-search">
                    <div class="widget-header">
                        <h5 class="widget-title bigger lighter">
                            <i class="ace-icon fa fa-table"></i> 操作面板
                        </h5>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main no-padding">
                            <c:url value="/prj/charts/groupIndex.htm?type=${type}" var="index_url"/>
                            <form:form action="${index_url}" method="post"
                                       cssClass="form-horizontal" role="form" modelAttribute="prjChartsSearchVO"
                                       cssStyle="padding-top: 10px;" data-ajax="true">
                                <div class="row">
                                    <div class="col-xs-4 col-lg-3">
                                        <div class="form-group">
                                            <label class="control-label col-xs-5 col-lg-4">镇：</label>
                                            <div class="col-xs-7 col-lg-8">
                                                <form:select path="town" cssClass="col-xs-10 col-sm-10 col-lg-10">
                                                    <form:option value="">--全部--</form:option>
                                                    <form:options items="${townList}" itemLabel="content" itemValue="value"></form:options>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-lg-3">
                                        <div class="form-group">
                                            <label class="control-label col-xs-5 col-lg-4">村：</label>
                                            <div class="col-xs-7 col-lg-8 no-padding-left">
                                                <form:select path="village" cssClass="col-xs-10 col-sm-10 col-lg-10">
                                                    <form:option value="">--全部--</form:option>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-lg-3">
                                        <div class="form-group">
                                            <label class="control-label col-xs-5 col-lg-4">日期：</label>
                                            <div class="col-xs-7 col-lg-8">
                                                <input type="text" id="date" name="date" class="col-xs-10 col-sm-10 col-lg-10" value="${prjChartsSearchVO.date}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-actions">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-4  col-lg-4 col-lg-offset-8">
                                            <button type="submit" class="btn btn-primary btn-sm" id="btn-search">
                                                <i class="ace-icon fa fa-search"></i> 查询
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="hr hr-18 dotted hr-double"></div>
                        <div class="row">
                            <div class="col-lg-4" id="right_inhost" style="height: 300px;"></div>
                            <div class="col-lg-8" id="left_inhost" style="height: 300px;padding-right: 10px;"></div>
                        </div>
                    </div>
                    <!-- /.span -->
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="hr hr-18 dotted hr-double"></div>
                        <div class="row">
                            <div class="col-lg-4" id="right_sign" style="height: 300px;"></div>
                            <div class="col-lg-8" id="left_sign" style="height: 300px;padding-right: 10px;"></div>
                        </div>
                    </div>
                    <!-- /.span -->
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="hr hr-18 dotted hr-double"></div>
                        <div class="row">
                            <div class="col-lg-4" id="right_handover" style="height: 300px;"></div>
                            <div class="col-lg-8" id="left_handover" style="height: 300px;padding-right: 10px;"></div>
                        </div>
                    </div>
                    <!-- /.span -->
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="hr hr-18 dotted hr-double"></div>
                        <div class="row">
                            <div class="col-lg-4" id="right_money" style="height: 300px;"></div>
                            <div class="col-lg-8" id="left_money" style="height: 300px;padding-right: 10px;"></div>
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
    <script src="${pageContext.request.contextPath}/assets/echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/echarts/shine.js"></script>
    <script src="<c:url value="/assets/js/bootstrap-datepicker/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/assets/js/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"/>"></script>
    <script>
        var $town = $("#town");
        var $village = $("#village");
        function getVillage(){
            var url = '<c:url value="/prj/preallocation/basic/getVillageByTown.htm"/>';
            $.ajax({
                url: url,
                data: {
                    town: $town.val()
                },
                type: 'post',
                success: function (result) {
                    var json = eval('('+result + ')');
                    $village.empty();
                    if (json && json.length) {
                        $village.append('<option value="">--全部--</option>');
                        $.each(json, function (i, village) {
                            $village.append('<option value="' + village.value + '">' + village.content + '</option>');
                        });
                    }
                }
            });
        }
        $(function () {
            var $town = $("#town");
            if($town.val() != ''){
                getVillage();
            }
            var $village = $("#village");
            $town.on('change', function () {
                if($(this).val() == ''){
                    $village.empty();
                    $village.append('<option value="">--请选择镇--</option>');
                    return;
                }
                getVillage();

            });

            $("#date").datepicker({
                format: "yyyy-mm-dd",
                autoclose: true,
                todayHighlight: true,
                language: "zh-CN",
                orientation: "bottom auto"
            });

            var url = '${pageContext.request.contextPath}/prj/charts/getGroup.htm?type=${type}';
            if($("#town").val() != ''){
                url += "&town=" + $("#town").val();
            }
            if($("#village").val() != ''){
                url += "&village=" + $("#village").val();
            }
            if($("#date").val() != ''){
                url += "&date=" + $("#date").val();
            }
            // 基于准备好的dom，初始化echarts实例
            //入户实例
            var inhost_Chart = echarts.init(document.getElementById('left_inhost'), 'shine');
            var inhost_Chart2 = echarts.init(document.getElementById('right_inhost'), 'shine');
            var inhost_option = {
                title: {
                    text: '入户进度'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data: ['当日入户', '累计入户'],
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: [],
                        name: '组别',
                        boundaryGap: ['20%', '20%'],
                        axisTick: {
                            inside: true
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '${typeName}${typeUnit}',
                        nameLocation: 'end'
                    }
                ],
                series: [
                    {
                        type: 'bar',
                        barMinHeight:'1',
                        label: {
                            normal: {
                                show:true,
                                position:'bottom',
                                offset:[0,-25],
                                textStyle: {
                                    color:'#000'
                                }
                            }
                        }
                    }
                ]
            };
            inhost_Chart.showLoading();
            // 使用刚指定的配置项和数据显示图表。
            inhost_Chart.setOption(inhost_option);



            var inhost_option2 = {
                title: {
                    text: '入户累计完成'
                },
                tooltip: {
                    formatter: "{a} <br/>{b} 完成率: {c}%"
                },
                toolbox: {
                    show: false
                },
                series: []
            };
            inhost_Chart2.setOption(inhost_option2);

            $.get(url + '&search_type=1').done(function (json) {
                inhost_Chart.hideLoading();
                var data = eval('(' + json + ')');
                // 填入数据
                inhost_Chart.setOption({
                    xAxis: {
                        data: data.categories
                    },
                    series: data.barData
                });
                inhost_Chart2.setOption({
                    series: data.guageData
                });
            });


            // 签约实例
            var sign_Chart = echarts.init(document.getElementById('left_sign'), 'shine');
            var sign_Chart2 = echarts.init(document.getElementById('right_sign'), 'shine');
            var sign_option = {
                title: {
                    text: '签约进度'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data: ['当日签约', '累计签约'],
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: [],
                        name: '组别',
                        boundaryGap: ['20%', '20%'],
                        axisTick: {
                            inside: true
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '${typeName}${typeUnit}',
                        nameLocation: 'end'
                    }
                ],
                series: [
                    {
                        type: 'bar',
                        barMinHeight:'1',
                        label: {
                            normal: {
                                show:true,
                                position:'bottom',
                                offset:[0,-25],
                                textStyle: {
                                    color:'#000'
                                }
                            }
                        }
                    }
                ]
            };
            sign_Chart.showLoading();
            // 使用刚指定的配置项和数据显示图表。
            sign_Chart.setOption(sign_option);

            var sign_option2 = {
                title: {
                    text: '签约累计完成'
                },
                tooltip: {
                    formatter: "{a} <br/>{b} 完成率: {c}%"
                },
                toolbox: {
                    show: false
                },
                series: []
            };
            sign_Chart2.setOption(sign_option2);

            $.get(url + '&search_type=2').done(function (json) {
                sign_Chart.hideLoading();
                var data = eval('(' + json + ')');
                // 填入数据
                sign_Chart.setOption({
                    xAxis: {
                        data: data.categories
                    },
                    series: data.barData
                });
                sign_Chart2.setOption({
                    series: data.guageData
                });
            });

            // 交房实例
            var handover_Chart = echarts.init(document.getElementById('left_handover'), 'shine');
            var handover_Chart2 = echarts.init(document.getElementById('right_handover'), 'shine');
            var handover_option = {
                title: {
                    text: '交房进度'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data: ['当日交房', '累计交房'],
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: [],
                        name: '组别',
                        boundaryGap: ['20%', '20%'],
                        axisTick: {
                            inside: true
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '${typeName}${typeUnit}',
                        nameLocation: 'end'
                    }
                ],
                series: [
                    {
                        type: 'bar',
                        barMinHeight:'1',
                        label: {
                            normal: {
                                show:true,
                                position:'bottom',
                                offset:[0,-25],
                                textStyle: {
                                    color:'#000'
                                }
                            }
                        }
                    }
                ]
            };
            handover_Chart.showLoading();
            // 使用刚指定的配置项和数据显示图表。
            handover_Chart.setOption(handover_option);

            var handover_option2 = {
                title: {
                    text: '交房累计完成'
                },
                tooltip: {
                    formatter: "{a} <br/>{b} 完成率: {c}%"
                },
                toolbox: {
                    show: false
                },
                series: []
            };
            handover_Chart2.setOption(handover_option2);

            $.get(url + '&search_type=3').done(function (json) {
                handover_Chart.hideLoading();
                var data = eval('(' + json + ')');
                // 填入数据
                handover_Chart.setOption({
                    xAxis: {
                        data: data.categories
                    },
                    series: data.barData
                });
                handover_Chart2.setOption({
                    series: data.guageData
                });
            });

            // 放款实例
            var money_Chart = echarts.init(document.getElementById('left_money'), 'shine');
            var money_Chart2 = echarts.init(document.getElementById('right_money'), 'shine');
            var money_option = {
                title: {
                    text: '放款进度'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data: ['当日放款', '累计放款'],
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: [],
                        name: '组别',
                        boundaryGap: ['20%', '20%'],
                        axisTick: {
                            inside: true
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '${typeName}${typeUnit}',
                        nameLocation: 'end'
                    }
                ],
                series: [
                    {
                        type: 'bar',
                        barMinHeight:'1',
                        label: {
                            normal: {
                                show:true,
                                position:'bottom',
                                offset:[0,-25],
                                textStyle: {
                                    color:'#000'
                                }
                            }
                        }
                    }
                ]
            };
            money_Chart.showLoading();
            // 使用刚指定的配置项和数据显示图表。
            money_Chart.setOption(money_option);

            var money_option2 = {
                title: {
                    text: '放款累计完成'
                },
                tooltip: {
                    formatter: "{a} <br/>{b} 完成率: {c}%"
                },
                toolbox: {
                    show: false
                },
                series: []
            };
            money_Chart2.setOption(money_option2);

            $.get(url + '&search_type=4').done(function (json) {
                money_Chart.hideLoading();
                var data = eval('(' + json + ')');
                // 填入数据
                money_Chart.setOption({
                    xAxis: {
                        data: data.categories
                    },
                    series: data.barData
                });
                money_Chart2.setOption({
                    series: data.guageData
                });
            });
        });
    </script>
</javascripts>
</html>
