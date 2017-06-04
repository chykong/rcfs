<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
    <ul class="breadcrumb">
        <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
        <li class="active">切换项目</li>
    </ul>
    <div class="pull-right">
                        <span>
                            当前项目：
                            <strong class="blue">
                                    ${sessionScope.userSession.current_project_name}（ ${sessionScope.userSession.current_land_name}）（ ${sessionScope.userSession.current_building_name}）
                            </strong>
                        </span>
        <span class="help-inline" style="margin-left: 20px;">
                            <a href="${dynamicServer}/sys/user/changeProject.htm" title="切换项目"
                               class="btn btn-link btn-small">
                                <i class="ace-icon fa fa-refresh blue"></i>
                                切换项目
                            </a>
                        </span>
    </div>
</div>