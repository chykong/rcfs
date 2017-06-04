<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}-选择项目</title>
    <%@ include file="../common/header.jsp" %>
</head>

<body class="no-skin">
<%@ include file="../common/top.jsp" %>

<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <%@ include file="../common/navigate.jsp" %>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        切换项目
                        <small><i class="ace-icon fa fa-angle-double-right"></i>
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <form id="inputForm" name="inputForm" class="form-horizontal" action="saveChangeProject.htm"
                              method="post">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">可选项目：</label>
                                <div class="col-sm-9">
                                    <form:select path="sysLogSearchVO.user_id" class="form-control input-small"
                                                 id="current_project_id">
                                        <form:options items="${listProject}" itemValue="id" itemLabel="realname"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-9">
                                    <div class="radio-inline">
                                        <label>
                                            <input name="current_land_status" type="radio" class="ace input-lg"
                                                   value="1"
                                                   <c:if test="${sessionScope.userSession.current_land_status eq 1}">checked </c:if>>
                                            <span class="lbl bigger-120">国有</span>
                                        </label>
                                    </div>

                                    <div class="radio-inline">
                                        <label>
                                            <input name="current_land_status" type="radio" class="ace input-lg"
                                                   value="2"
                                                   <c:if test="${sessionScope.userSession.current_land_status eq 2}">checked </c:if> >
                                            <span class="lbl bigger-120">集体</span>
                                        </label>
                                    </div>

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-9">
                                    <div class="radio-inline">
                                        <label>
                                            <input name="current_building_type" type="radio" class="ace input-lg"
                                                   value="1"
                                                   <c:if test="${sessionScope.userSession.current_building_type eq 1}">checked </c:if>>
                                            <span class="lbl bigger-120">住宅</span>
                                        </label>
                                    </div>

                                    <div class="radio-inline">
                                        <label>
                                            <input name="current_building_type" type="radio" class="ace input-lg"
                                                   value="2"
                                                   <c:if test="${sessionScope.userSession.current_building_type eq 2}">checked </c:if>>
                                            <span class="lbl bigger-120">非住宅</span>
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="clearfix form-actions">
                                <div class="col-md-offset-3 col-md-9">
                                    <button class="btn btn-primary" type="submit">
                                        <i class="ace-icon fa fa-save bigger-110"></i> 确认
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

            </script>
</body>
</html>
