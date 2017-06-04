<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<div class="pull-right">
          <span>

                            <label>
                                   当前项目： <span
                                    style="color:red"> ${sessionScope.userSession.current_project_name}</span>（ ${sessionScope.userSession.current_land_name}）（ ${sessionScope.userSession.current_building_name}）
                            </label>
                        </span>
    <span class="help-inline" style="margin-left: 20px;">
                            <a href="${dynamicServer}/sys/user/changeProject.htm" title="切换项目"
                               class="btn btn-link btn-small">
                                <i class="ace-icon fa fa-th blue"></i>
                                切换项目
                            </a>
                        </span>
</div>