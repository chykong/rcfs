<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-地图</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/js.jsp"%>
	<link href="<c:url value="/assets/components/GIS/gapi/esri/css/esri.css"/>" rel="stylesheet">
	<link href="<c:url value="/assets/components/GIS/gapi/map.css"/>" rel="stylesheet">


	<script src="<c:url value="/assets/components/GIS/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/assets/components/GIS/jquery-1.10.2.min.js"/>"></script>
	<script src="<c:url value="/assets/components/GIS/gapi/map.js"/>"></script>
	<script src="<c:url value="/assets/components/GIS/gapi/init.js"/>"></script>
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
						<li class="active">地图</li>
					</ul> <%@ include file="../common/navigate.jsp"%>
					<!-- /.breadcrumb -->
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="main-box">
						<div class="middle">
							<div style="width: 100%;" class="content">
								<div id="map" style="padding: 1px; width: 100%;">
									<!--查询-->
									<div style="left: 10px;top:15px; width:300px; position: absolute; z-index: 9999; ">
										<div class="input-group">
											<div class="input-icon">
												<input type="hidden" id="project_id" value="${projectId}">
												<input type="text" id="searchipt" style="height:34px !important" placeholder="请输入查询关键字" class="form-control">
											</div>
											<span class="input-group-btn">
                                <a href="javascript:;" onclick="onSearch();" class="btn btn-primary btn-large">
                                    <i class="fa fa-search"></i>
                                </a>
                            </span>
										</div>
									</div>
									<!--切换-->
									<div style="width: 100px; right: 16px; top: 15px; position: absolute; z-index: 9999; box-shadow: 0px 0px 8px #66AFE9;">
										<div id="layerbox_item" class="changemap" style="text-align: center;">
											<div id="vecMap" class="layer_item satellite item span_active" data-type="satellite">
												<span class="name">地图</span>
											</div>
											<div id="imgMap" class="layer_item traffic item" data-type="traffic">
												<span class="name">卫星</span>
											</div>
										</div>
									</div>
									<!--图例-->
									<div style="width:100%;bottom: 120px; position: absolute; z-index: 9999;text-align:center">
										<table style="line-height:24px; margin-top:5px;">
											<tr>
												<td><div style="width: 30px; height: 15px; background-color: #0099FF; margin-left: 10px;border-radius:4px; "></div> <!--未入户  -10  --> </td>
												<td><span style="margin-left: 10px;"><strong>未入户</strong> </span></td>
												<td><div style="width:30px; height: 15px; background-color: #FFFF33; margin-left: 10px;border-radius:4px; "></div> <!--已入户未签约--></td>
												<td><span style="margin-left: 10px;"><strong>已入户未签约</strong> </span></td>
												<td><div style="width: 30px; height: 15px; background-color: #FF0000; margin-left:10px;border-radius:4px; "></div> <!--已签约未交房--></td>
												<td><span style="margin-left: 10px;"><strong>已签约</strong> </span></td>
												<td><div style="width: 30px; height: 15px; background-color: #33FF00; margin-left: 10px;border-radius:4px; "></div> <!--已交房--></td>
												<td><span style="margin-left: 10px;"><strong>已审核</strong> </span></td>
												<td><div style="width: 30px; height: 15px; background-color: #999999; margin-left:10px;border-radius:4px; "></div> <!--已拆除--></td>
												<td><span style="margin-left: 10px;"><strong>已交房</strong> </span></td>
												<td><div style="width: 30px; height: 15px; background-color: #999999; margin-left:10px;border-radius:4px; "></div> <!--已拆除--></td>
												<td><span style="margin-left: 10px;"><strong>已拆除</strong> </span></td>
											</tr>
										</table>
									</div>
									<div id="map_tip" style="background-color: #C11329;position: absolute; cursor: pointer; z-index: 99999;display:none;border: none; color: #fff; height: 45px; font-size: 12px; box-shadow: 0px 0px 8px #66AFE9; padding: 5px; border-radius: 4px;">
									</div>
								</div>

							</div>
						</div>
					</div>

					<div id="map_title" style="display: none;">
						<div style='height: 35px; line-height: 35px; font-size: 14px; margin-left: 5px;'>基本信息</div>
					</div>
					<div id="map_content" style="display: none; font-family: 微软雅黑;">
						<div>
							<ul class="nav nav-tabs" role="tablist">
								<li class="active" role="presentation"><a href="#case_1" aria-controls="case_1" role="tab" data-toggle="tab" style=" text-decoration: none; font-family: 微软雅黑; padding: 2px 8px;">基本信息</a></li>
								<li role="presentation"><a href="#case_2" aria-controls="case_2" role="tab" data-toggle="tab" style=" text-decoration: none; font-family: 微软雅黑; padding: 2px 8px;">工作人员</a></li>
							</ul>
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="case_1">
									<div class="row">
										<div class="col-md-6">
											<span style="color:#7E7C7C;">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：&nbsp;</span>
											<label id="map_id"></label>
										</div>
										<div class="col-md-6">
											<span style="color:#7E7C7C;">被拆迁除腾退人：&nbsp;</span>
											<label id="host_name"></label>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<span style="color:#7E7C7C;">坐&nbsp;落&nbsp;位&nbsp;置&nbsp;：</span>
											<label id="location"></label>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<span style="color:#7E7C7C;">06年前面积：&nbsp;</span>
											<label id="before_area"></label>㎡
										</div>
										<div class="col-md-6">
											<span style="color:#7E7C7C;">房屋营业面积：&nbsp;</span>
											<label id="management_house_area"></label>㎡
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<span style="color:#7E7C7C;">06-09间面积：&nbsp;</span>
											<label id="between_area"></label>㎡
										</div>
										<div class="col-md-6">
											<span style="color:#7E7C7C;">场地营业面积：&nbsp;</span>
											<label id="field_house_area"></label>㎡
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<span style="color:#7E7C7C;">09年后面积：&nbsp;</span>
											<label id="after_area"></label>㎡
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<span style="color:#7E7C7C;">滞留原因：&nbsp;</span>
											<label id="no_sign_reason"></label>
										</div>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane" id="case_2">
									<div class="row">
										<div class="col-md-12">
											<span style="color:#7E7C7C;">包村干部：&nbsp;</span>
											<label id="leader"></label>
										</div>
										<div class="col-md-12">
											<span style="color:#7E7C7C;">管理公司：&nbsp;</span>
											<label id="management_co"></label>
										</div>
										<div class="col-md-12">
											<span style="color:#7E7C7C;">测绘公司：&nbsp;</span>
											<label id="geo_co"></label>
										</div>
										<div class="col-md-12">
											<span style="color:#7E7C7C;">评估公司：&nbsp;</span>
											<label id="appraise_co"></label>
										</div>
										<div class="col-md-12">
											<span style="color:#7E7C7C;">拆除公司：&nbsp;</span>
											<label id="pulledown_co"></label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
				<!-- /.main-content -->
			</div>
			<!-- /.main-container -->
			<%@ include file="../common/js.jsp"%>

			<script type="text/javascript">
				$(function() {
				})
			</script>
</body>
</html>
