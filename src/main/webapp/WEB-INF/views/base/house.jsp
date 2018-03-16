<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-户型管理</title>
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
                    <li class="active">户型管理</li>
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
                                <div class="widget-toolbar">
                                    <a href="#" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>

                            <!-- /section:custom/widget-box.options -->
                            <div class="widget-body">
                                <div class="widget-main">
                                    <table class="searchField" style="margin: 4px; padding: 4px;">
                                        <tr>
                                            <td>标题：</td>
                                            <td><input type="text" id="txtTitle"
                                                       class="form-control input-large" placeholder=""
                                                       value="${baseHouseSearchVO.name }"></td>
                                            <td>
                                                <button class="btn btn-primary btn-sm" id="btnSearch">
                                                    <i class="ace-icon fa fa-search"></i> 查询
                                                </button>
                                                <%--<c:if test="${bln:isP('BaseHouseAdd')}">--%>
                                                    <button type="button" class="btn btn-success btn-sm"
                                                            id="btnAdd">
                                                        <i class="ace-icon fa fa-plus bigger-110"></i>新增
                                                    </button>
                                                <%--</c:if>--%>
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
                                <th width=60>序号</th>
                                <th width=100>户型</th>
                                <th width=100>户型图</th>
                                <th width=240>户型描述</th>
                                <th width="140">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="house" varStatus="st">
                                <tr>
                                    <td>${st.index +1 }</td>
                                    <td>${house.name}</td>
                                    <td><a href="javascript:viewZZ('${house.houseImage}');">查看</a></td>
                                    <td>${house.houseDescribe}</td>
                                    <td>

                                        <c:if test="${bln:isP('BasePolicyAdd')}">
                                            <a href="toUpdate.htm?id=${house.id}&backUrl=${backUrl}">
                                                修改 </a>
                                        </c:if>
                                        <c:if test="${bln:isP('BasePolicyAdd')}">
                                            <a href="javascript:delPolicy('${house.id}');"> 删除 </a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.span -->
                </div>

                <div id="zz_modal" class="modal fade" tabindex="-21">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header no-padding">
                                <div class="table-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">
                                        <span class="white">&times;</span>
                                    </button>
                                    查看户型图
                                </div>
                            </div>
                            <div class="modal-body no-padding">
                                <div class="widget-box" style="border: none">
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <form:form action="" enctype="multipart/form-data"
                                                       method="post" id="bus_form">
                                                <div class="form-group">
                                                    <div class="col-xs-12 text-center">
                                                        <h4 id="no_img" style="margin-top: 0;margin-bottom:45px;">暂无户型图</h4>
                                                        <img id="show_img" class="hidden" style="max-width: 100%" src=""/>
                                                    </div>
                                                    <div class="col-xs-12" align="center" style="margin-top: 5px;">
                                                        <button class="btn btn-white btn-primary" type="button" data-dismiss="modal">
                                                            <i class="ace-icon fa fa-times bigger-110"></i> 关闭
                                                        </button>
                                                    </div>
                                                </div>
                                            </form:form>
                                            <div style="clear: both"></div>
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
                <!-- /.row -->
                <div class="row">
                    <div class="col-xs-12">${ pageNavigate.pageModel}</div>
                </div>

            </div>
            <!-- /.main-content -->
        </div>
        <!-- /.main-container -->
        <%@ include file="../common/js.jsp" %>

        <script type="text/javascript">
            $(function () {
                $("#btnSearch").bind('click', searchPolicy);
                $("#btnAdd").bind('click', addPolicy);
            })

            // 查询方法
            var searchPolicy = function () {
                var url = "index.htm?";
                if ($("#txtTitle").val() != '')
                    url += "&name=" + $("#txtTitle").val();
                window.location = encodeURI(url);
            }
            //查看执照方法
            var viewZZ = function (url) {
                if (url != '') {
                    $('#no_img').addClass('hidden');
                    $('#show_img').removeClass('hidden').attr('src', '${imageServer}' + url);
                } else {
                    $('#show_img').addClass('hidden');
                    $('#no_img').removeClass('hidden');
                }
                $('#zz_modal').modal('show');
            };
            // 删除
            var delPolicy = function (id) {
                bootbox.confirm("你确定要删除该户型吗？", function (result) {
                    if (result) {
                        window.location = "delete.htm?id=" + id
                            + "&backUrl=${backUrl}";
                    }
                })
            }
            //新增
            var addPolicy = function (id) {
                window.location = 'toAdd.htm?backUrl=${backUrl }';
            }
        </script>
</body>
</html>
