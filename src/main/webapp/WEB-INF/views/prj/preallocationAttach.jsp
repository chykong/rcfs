<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>


<!-- 轮播（Carousel）指标 -->
<ol class="carousel-indicators">
    <c:if test="${attachList.size() == 0}">
        <div class="small-item">
            <img style="width: 72px;height: auto;" data-target="#myCarousel" data-slide-to="0" class="active" src="<c:url value="/assets/images/default-attach.png"/>"/>
        </div>
    </c:if>
<c:forEach items="${attachList}" var="attach" varStatus="st">
    <div class="small-item">
        <img style="width: 72px;height: auto;" data-target="#myCarousel" data-slide-to="${st.index}" <c:if test="${st.index == 0}">class="active"</c:if> src="${imageServer}/${attach.file_path}" />
    </div>
</c:forEach>
</ol>
<!-- 轮播（Carousel）项目 -->
<div class="carousel-inner">
    <c:if test="${attachList.size() == 0}">
        <div class="item active">
            <img src="<c:url value="/assets/images/default-attach.png"/>" alt="暂无照片">
        </div>
    </c:if>
    <c:forEach items="${attachList}" var="attach" varStatus="st">
        <div class="item <c:if test="${st.index == 0}">active</c:if>">
            <img src="${imageServer}/${attach.file_path}" alt="">
        </div>
    </c:forEach>
</div>
<!-- 轮播（Carousel）导航 -->
<a class="carousel-control left" href="#myCarousel"
   data-slide="prev"><i class="ace-icon fa fa-chevron-left"></i></a>
<a class="carousel-control right" href="#myCarousel"
   data-slide="next"><i class="ace-icon fa fa-chevron-right"></i></a>
