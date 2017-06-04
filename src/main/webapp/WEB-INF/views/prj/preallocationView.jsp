<%@ include file="../common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${site_name} 拆迁户管理 - 查看基本情况</title>
    <style>
        .form-group img {
            max-width: 100%;
        }

        .form-group p {
            text-align: left;
        }

        input {
            border: none;
        }
    </style>
</head>
<body>
<div class="page-header">
    <h4>
        拆迁户管理
        <small><i class="ace-icon fa fa-angle-double-right"></i> 查看</small>
    </h4>
</div>
<div class="row">
    <div class="col-xs-12">
        <div class="tabbable">
            <!-- #section:pages/faq -->
            <ul class="nav nav-tabs padding-18 tab-size-bigger" id="myTab">
                <li class="<c:if test="${view_type != 2}">active</c:if>">
                    <a data-toggle="tab" href="#faq-tab-basic" aria-expanded="true">
                        <i class="blue ace-icon fa fa-user bigger-120"></i>
                        基本信息
                    </a>
                </li>
                <c:if test="${(view_type == 1 && preallocation.totalCompensation > 0) || view_type == 2}">
                    <li class="<c:if test="${view_type == 2}">active</c:if>" id="compen-pane">
                        <a data-toggle="tab" href="#faq-tab-compensate">
                            <i class="blue ace-icon fa fa-jpy bigger-120"></i>
                            补偿信息
                        </a>
                    </li>
                </c:if>
                <c:if test="${preallocation.status >= 20}">
                    <li class="" id="active-pane">
                        <a data-toggle="tab" href="#faq-tab-demolition">
                            <i class="blue ace-icon fa fa-list bigger-120"></i>
                            拆迁进度
                        </a>
                    </li>
                </c:if>
                <c:if test="${preallocation.status > 40}">
                    <li class="" id="active-pane">
                        <a data-toggle="tab" href="#faq-tab-chooseRoom">
                            <i class="blue ace-icon fa fa-home bigger-120"></i>
                            选房进度
                        </a>
                    </li>
                </c:if>
                <c:if test="${preallocation.status > 50}">
                    <li class="" id="active-pane">
                        <a data-toggle="tab" href="#faq-tab-archive">
                            <i class="blue ace-icon fa fa-home bigger-120"></i>
                            归档进度
                        </a>
                    </li>
                </c:if>
                <li class="" id="hj-pane">
                    <a data-toggle="tab" href="#faq-tab-hj">
                        <i class="blue ace-icon fa fa-users bigger-120"></i>
                        户籍信息
                    </a>
                </li>
                <li class="" id="inhost-pane">
                    <a data-toggle="tab" href="#faq-tab-inhost">
                        <i class="blue ace-icon fa fa-inbox bigger-120"></i>
                        入户信息
                    </a>
                </li>
            </ul>

            <form:form servletRelativeAction="/preallocations/compensation/basic" id="save-form" method="post"
                       cssClass="form-horizontal" commandName="preallocation">
            <!-- /section:pages/faq -->
            <div class="tab-content no-border padding-24">
                <!-- 基本情况 begin-->
                <div id="faq-tab-basic" class="tab-pane fade <c:if test="${view_type != 2}">active in</c:if>">
                    <div class="row">
                        <!--左列-->
                        <div class="col-xs-10 col-sm-10 col-lg-6">
                            <div class="form-group">
                                <form:label path="mapId"
                                            cssClass="col-sm-3 col-lg-4 control-label no-padding-right">
                                    编号：
                                </form:label>
                                <div class="col-sm-9 col-lg-8">
                                    <form:input path="mapId" cssClass="col-xs-10 col-sm-10 col-lg-6" readonly="true"
                                                maxlength="20"/>
                                    <label id="mapIdTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label path="location"
                                            cssClass="col-sm-3 col-lg-4 control-label no-padding-right">
                                    房屋坐落：
                                </form:label>
                                <div class="col-sm-9 col-lg-8">
                                    <form:input path="location" cssClass="col-xs-10 col-sm-10 col-lg-11"
                                                readonly="true"
                                                maxlength="200"/>
                                    <label id="locationTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label path="houseProperty"
                                            cssClass="col-sm-3 col-lg-4 control-label no-padding-right">
                                    房屋性质：
                                </form:label>
                                <div class="col-sm-9 col-lg-8">
                                    <form:input path="houseProperty" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                readonly="true"
                                                maxlength="200"/>
                                    <label id="housePropertyTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label path="houseProperty"
                                            cssClass="col-sm-3 col-lg-4 control-label no-padding-right">
                                    门牌号：
                                </form:label>
                                <div class="col-sm-9 col-lg-8">
                                    <form:input path="mp" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                readonly="true"
                                                maxlength="200"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label path="houseRegisterNum"
                                            cssClass="col-sm-3 col-lg-4 control-label no-padding-right">
                                    户籍人数：
                                </form:label>
                                <div class="col-sm-9 col-lg-8">
                                    <form:input path="houseRegisterNum" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                readonly="true"
                                                maxlength="200"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 col-lg-4 control-label no-padding-right">
                                    材料文件：
                                </label>
                                <div class="col-sm-9 col-lg-8" style="height: 30px;line-height: 30px;">
                                    <a target="_blank" href="<c:url value="/preallocations/basic/downFile?file_path=${preallocation.other_file_path}&file_name=${preallocation.other_file_name}"/>">
                                    ${preallocation.other_file_name}</a>
                                </div>
                            </div>
                        </div>
                        <!--右列-->
                        <div class="col-xs-10 col-sm-10 col-lg-6">
                            <div class="form-group">
                                <form:label path="hostName" cssClass="col-sm-3 control-label no-padding-right">
                                    被拆迁人：
                                </form:label>
                                <div class="col-sm-9">
                                    <form:input path="hostName" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                readonly="true"
                                                maxlength="40"/>
                                    <label id="hostNameTip"></label>
                                </div>
                            </div>
                            <div class="form-group hidden-md" style="height: 34px;">

                            </div>
                            <div class="form-group">
                                <form:label path="actualHomesteadArea"
                                            cssClass="col-sm-3 control-label no-padding-right">
                                    实测宅基地面积：
                                </form:label>
                                <div class="col-sm-9">
                                    <form:input path="actualHomesteadArea" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                readonly="true"
                                                maxlength="200"/>
                                    <label id="actualHomesteadAreaTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label path="cogHomesteadArea"
                                            cssClass="col-sm-3 control-label no-padding-right">
                                    认定宅基地面积：
                                </form:label>
                                <div class="col-sm-9">
                                    <form:input path="cogHomesteadArea" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                readonly="true"
                                                maxlength="200"/>
                                    <label id="cogHomesteadAreaTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label path="actualHouseArea"
                                            cssClass="col-sm-3 control-label no-padding-right">
                                    实测房屋面积：
                                </form:label>
                                <div class="col-sm-9">
                                    <form:input path="actualHouseArea" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                readonly="true"
                                                maxlength="200"/>
                                    <label id="actualHouseAreaTip"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label path="cogHouseArea" cssClass="col-sm-3 control-label no-padding-right">
                                    认定房屋面积：
                                </form:label>
                                <div class="col-sm-9">
                                    <form:input path="cogHouseArea" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                readonly="true"
                                                maxlength="200"/>
                                    <label id="cogHouseAreaTip"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 基本情况 end-->
                <!-- 补偿情况 begin-->
                <c:if test="${(view_type == 1 && preallocation.totalCompensation > 0) || view_type == 2}">
                    <div id="faq-tab-compensate" class="tab-pane fade <c:if test="${view_type == 2}">active in</c:if>">
                        <div class="row">
                            <!-- 左侧 begin-->
                            <div class="col-xs-10 col-sm-6 col-lg-6">
                                <div class="form-group">
                                    <form:label path="moneyHomestead"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        宅基地区位补偿价：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneyHomestead"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 pinggu"
                                                    readonly="true" maxlength="20"/>
                                        <label id="moneyHomesteadTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="moneyNewHouse"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        房屋重置成新补偿价：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneyNewHouse"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 pinggu"
                                                    readonly="true" maxlength="20"/>
                                        <label id="moneyNewHouseTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="moneyNewAdjunct"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        装修、设备及附属物重置成新价：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneyNewAdjunct"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 pinggu"
                                                    readonly="true" maxlength="20"/>
                                        <label id="moneyNewAdjunctTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="appraiseCompensation"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        评估金额：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="appraiseCompensation"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                                    readonly="true" maxlength="20" disabled="true"/>
                                        <label id="appraiseCompensationTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="azArea" cssClass="col-sm-3 control-label no-padding-right">
                                        安置面积：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="azArea" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                    readonly="true" maxlength="20"/>
                                        <label id="azAreaTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="floatAzArea"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        浮动安置面积：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="floatAzArea" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                    readonly="true" maxlength="20"/>
                                        <label id="floatAzAreaTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="oneRoomNum"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        一居室（套）：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="oneRoomNum"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 yugutao"
                                                    readonly="true" maxlength="20"/>
                                        <label id="oneRoomNumTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="oneRoomArea"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        一居室面积：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="oneRoomArea"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 yuguarea"
                                                    readonly="true" maxlength="20"/>
                                        <label id="oneRoomAreaTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="twoRoomNum"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        二居室（套）：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="twoRoomNum"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 yugutao"
                                                    readonly="true" maxlength="20"/>
                                        <label id="twoRoomNumTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="twoRoomArea"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        二居室面积：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="twoRoomArea"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 yuguarea"
                                                    readonly="true" maxlength="20"/>
                                        <label id="twoRoomAreaTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="threeRoomNum"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        三居室（套）：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="threeRoomNum"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 yugutao"
                                                    readonly="true" maxlength="20"/>
                                        <label id="threeRoomNumTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="threeRoomArea"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        三居室面积：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="threeRoomArea"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 yuguarea"
                                                    readonly="true" maxlength="20"/>
                                        <label id="threeRoomAreaTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="azRoomBudgetNum"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        安置房预估套数：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="azRoomBudgetNum" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                    readonly="true" maxlength="20"/>
                                        <label id="azRoomBudgetNumTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="azRoomBudgetArea"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        安置房预估面积：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="azRoomBudgetArea" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                    readonly="true" maxlength="20"/>
                                        <label id="azRoomBudgetAreaTip"></label>
                                    </div>
                                </div>
                            </div>
                            <!-- 右侧 begin-->
                            <div class="col-xs-10 col-sm-6 col-lg-6">
                                <div class="form-group">
                                    <form:label path="moneyRelocate"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        搬迁费：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneyRelocate"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 buzhu"
                                                    readonly="true" maxlength="20"/>
                                        <label id="moneyRelocateTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="moneyLsaz" cssClass="col-sm-3 control-label no-padding-right">
                                        临时安置费：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneyLsaz" cssClass="col-xs-10 col-sm-10 col-lg-6 buzhu"
                                                    readonly="true" maxlength="20"/>
                                        <label id="moneyLsazTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="moneySsbcf"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        停产停业损失补偿费：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneySsbcf" cssClass="col-xs-10 col-sm-10 col-lg-6 buzhu"
                                                    readonly="true" maxlength="20"/>
                                        <label id="moneySsbcfTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="moneyDhyjf"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        电话移机费：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneyDhyjf" cssClass="col-xs-10 col-sm-10 col-lg-6 buzhu"
                                                    readonly="true" maxlength="20"/>
                                        <label id="moneyDhyjfTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="moneyYxdsyjf"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        有线电视移机费：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneyYxdsyjf"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 buzhu"
                                                    readonly="true" maxlength="20"/>
                                        <label id="moneyYxdsyjfTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="moneyKtyjf"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        空调移机费：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneyKtyjf" cssClass="col-xs-10 col-sm-10 col-lg-6 buzhu"
                                                    readonly="true" maxlength="20"/>
                                        <label id="moneyKtyjfTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="moneyRsqyjf"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        热水器移机费：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneyRsqyjf" cssClass="col-xs-10 col-sm-10 col-lg-6 buzhu"
                                                    readonly="true" maxlength="20"/>
                                        <label id="moneyRsqyjfTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="subsidySpecial"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        特困补助费：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="subsidySpecial"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 buzhu"
                                                    readonly="true" maxlength="20"/>
                                        <label id="subsidySpecialTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="subsidyRelocate"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        拆迁补助费：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="subsidyRelocate"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                                    readonly="true"
                                                    maxlength="20"/>
                                        <label id="subsidyRelocateTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="totalCompensation"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        货币补偿额：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="totalCompensation"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 yusuansub"
                                                    readonly="true"
                                                    maxlength="20"/>
                                        <label id="totalCompensationTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="budgetCompensation"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        预算房款：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="budgetCompensation"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6 yusuansub"
                                                    readonly="true" maxlength="20"/>
                                        <label id="budgetCompensationTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="budgetSub" cssClass="col-sm-3 control-label no-padding-right">
                                        预算差价：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="budgetSub" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                    readonly="true" maxlength="20"/>
                                        <label id="budgetSubTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="moneyBenchmark"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        基准价格：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneyBenchmark" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                    readonly="true" maxlength="20"/>
                                        <label id="budgetSubTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="moneyAdjunct" cssClass="col-sm-3 control-label no-padding-right">
                                        附属物价：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="moneyAdjunct" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                    readonly="true" maxlength="20"/>
                                        <label id="budgetSubTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="signingProportionalAward"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        签约比例奖：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="signingProportionalAward"
                                                    cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                    readonly="true" maxlength="20"/>
                                        <label id="budgetSubTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="diseaseSubsidies"
                                                cssClass="col-sm-3 control-label no-padding-right">
                                        大病补助：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="diseaseSubsidies" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                    readonly="true" maxlength="20"/>
                                        <label id="budgetSubTip"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label path="incentiveFees" cssClass="col-sm-3 control-label no-padding-right">
                                        奖励费：
                                    </form:label>
                                    <div class="col-sm-9">
                                        <form:input path="incentiveFees" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                                    readonly="true" maxlength="20"/>
                                        <label id="budgetSubTip"></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${preallocation.status >= 20}">
                    <div id="faq-tab-demolition" class="tab-pane fade ">
                        <div class="form-group">
                            <form:label path="status"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                拆迁状态：
                            </form:label>
                            <div class="col-sm-9">
                                <c:if test="${preallocation.status == 10}">
                                    <span class="label label-success ">未入户</span>
                                </c:if>
                                <c:if test="${preallocation.status == 20}">
                                    <span class="label label-success ">已入户未签约</span>
                                </c:if>
                                <c:if test="${preallocation.status == 21}">
                                    <span class="label label-success ">已审核</span>
                                </c:if>
                                <c:if test="${preallocation.status == 30}">
                                    <span class="label label-success ">已签约未交房</span>
                                </c:if>
                                <c:if test="${preallocation.status == 40}">
                                    <span class="label label-success ">已签约已交房</span>
                                </c:if>
                                <c:if test="${preallocation.status == 50}">
                                    <span class="label label-success ">已选房</span>
                                </c:if>
                                <c:if test="${preallocation.status == 60}">
                                    <span class="label label-success ">已归档</span>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="signedDate"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                签约时间：
                            </form:label>
                            <div class="col-sm-9">
                                <input type="text" readonly="readonly" class="col-xs-10 col-sm-10 col-lg-6"
                                       value="<fmt:formatDate value="${preallocation.signedDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="handoverHouseDate"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                交房时间：
                            </form:label>
                            <div class="col-sm-9">
                                <input type="text" class="col-xs-10 col-sm-10 col-lg-6"
                                       value="<fmt:formatDate value="${preallocation.handoverHouseDate}" pattern="yyyy-MM-dd HH:ss"/>"
                                       readonly/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="handoverEndDate"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                交房截止时间：
                            </form:label>
                            <div class="col-sm-9">
                                <input type="text" class="col-xs-10 col-sm-10 col-lg-6"
                                       value="<fmt:formatDate value="${preallocation.handoverEndDate}" pattern="yyyy-MM-dd HH:ss"/>"
                                       readonly/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="selectHouseCode" cssClass="col-sm-3 control-label no-padding-right">
                                选房序号：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="selectHouseCode" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="beforeDemolishedArea"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                待拆除面积：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="beforeDemolishedArea"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="afterDemolishedArea"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                已拆除面积：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="afterDemolishedArea"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="demolishedDate"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                拆除时间：
                            </form:label>
                            <div class="col-sm-9">
                                <input type="text" class="col-xs-10 col-sm-10 col-lg-6"
                                       value="<fmt:formatDate value="${preallocation.demolishedDate}" pattern="yyyy-MM-dd HH:ss"/>"
                                       readonly/>
                            </div>
                        </div>
                    </div>
                </c:if>
                <!-- 选房情况 begin-->
                <c:if test="${preallocation.status > 40}">
                    <div id="faq-tab-chooseRoom" class="tab-pane fade ">

                        <div class="form-group">
                            <form:label path="ChooseRoomPosition"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                选房位置：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="ChooseRoomPosition"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseHost"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房产权人1：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseHost"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseRoom"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房居室：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseRoom"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseArea"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房面积（m²）：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseArea"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseHost2"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房产权人2：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseHost2"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseRoom2"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房居室：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseRoom2"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseArea2"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房面积（m²）：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseArea2"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseHost3"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房产权人3：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseHost3"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseRoom3"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房居室：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseRoom3"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseArea3"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房面积（m²）：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseArea3"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseHost4"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房产权人4：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseHost4"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseRoom4"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房居室：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseRoom4"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="azHouseArea4"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房面积（m²）：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="azHouseArea4"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="totalAzHouseArea"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房面积合计：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="totalAzHouseArea"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="floatAzHouseArea"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房浮动面积合计：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="floatAzHouseArea"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="moneyAzHouse"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房价款：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="moneyAzHouse"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="moneyFloatAzHouse"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房浮动面积价款：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="moneyFloatAzHouse"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="totalAzPayHouse"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                安置房购房款合计：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="totalAzPayHouse"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label path="firstCalculateSub"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                首次结算差价：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="firstCalculateSub"
                                            cssClass="col-xs-10 col-sm-10 col-lg-6 huobi"
                                            readonly="true" maxlength="20"/>
                            </div>
                        </div>
                    </div>
                </c:if>


                <c:if test="${preallocation.status > 50}">
                    <div id="faq-tab-archive" class="tab-pane fade ">
                        <div class="form-group">
                            <form:label path="status"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                存档状况：
                            </form:label>
                            <div class="col-sm-9">
                                <c:if test="${preallocation.status <60 }">
                                    <span class="label label-success ">未归档</span>
                                </c:if>
                                <c:if test="${preallocation.status == 60}">
                                    <span class="label label-success ">已归档</span>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <form:label path="azHouseHost"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                存档时间：
                            </form:label>
                            <div class="col-sm-9">
                                <input class="col-xs-10 col-sm-10 col-lg-6" readonly type="text"
                                       value="<fmt:formatDate value='${preallocation.archiveDate}'
                                        pattern='yyyy-MM-dd HH:mm"'/>"/>
                            </div>
                        </div>
                        <div class=" form-group">
                            <form:label path="azHouseHost"
                                        cssClass="col-sm-3 control-label no-padding-right">
                                档案柜号：
                            </form:label>
                            <div class="col-sm-9">
                                <form:input path="archivesCabinetNumber" cssClass="col-xs-10 col-sm-10 col-lg-6"
                                            readonly="true"/>

                            </div>
                        </div>

                    </div>
                </c:if>
                <div id="faq-tab-hj" class="tab-pane fade ">
                    <div class="form-group">
                        <table id="relation_table" class="table  table-bordered table-hover">
                            <thead>
                            <tr class="center">
                                <th width="80">姓名</th>
                                <th width="105">出生日期</th>
                                <th width="110">与产权人关系</th>
                                <th width="110">与户主关系</th>
                                <th>备注</th>
                            </tr>
                            </thead>

                            <tbody id="relation-items">
                            <c:forEach items="${preallocation.relations}" var="relation" varStatus="st">
                                <tr class="relation-item">
                                    <td>${relation.name}</td>
                                    <td><fmt:formatDate value="${relation.birthday}" pattern="yyyy-MM-dd"/>
                                    </td>
                                    <td>${relation.hostRelation}
                                    </td>
                                    <td>${relation.householderRelation}</td>
                                    <td>${relation.note}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
                <div id="faq-tab-inhost" class="tab-pane fade ">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">
                            标段：
                        </label>
                        <div class="col-sm-9">
                                ${preallocation.section}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">
                            组别：
                        </label>
                        <div class="col-sm-9">
                                ${preallocation.groups}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">
                            拆迁公司：
                        </label>
                        <div class="col-sm-9">
                                ${preallocation.demolishCo}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">
                            评估公司：
                        </label>
                        <div class="col-sm-9">
                                ${preallocation.appraiseCo}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">
                            测绘公司：
                        </label>
                        <div class="col-sm-9">
                                ${preallocation.geoCo}
                            <label id="geoCoTip"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">
                            拆除公司：
                        </label>
                        <div class="col-sm-9">
                                ${preallocation.pulledownCo}
                        </div>
                    </div>
                </div>
                </form:form>
            </div>
        </div>
        <div class="form-group" style="margin-top: 50px;">
            <div class="col-xs-4 col-sm-4 right" style="text-align: right"><strong>创建人：</strong></div>
            <div class="col-xs-2 col-sm-2 ">${preallocation.createdBy}</div>
            <div class="col-xs-2 col-sm-2 right" style="text-align: right"><strong>创建时间：</strong></div>
            <div class="col-xs-4 col-sm-4 "><fmt:formatDate value="${preallocation.createdAt}"
                                                            pattern="yyyy-MM-dd HH:mm"/></div>
        </div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-4 col-md-8">
                <div class="clearfix form-actions">
                    <a id="btn-reset" href="javascript:history.back();" class="btn">
                        <i class="ace-icon fa fa-undo"></i>
                        返回
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
