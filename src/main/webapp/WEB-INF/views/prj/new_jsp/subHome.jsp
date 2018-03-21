<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<c:forEach items="${preallocations}" var="preallocation">
    <tr role="row" class="odd append-item" style="height: 43px;">
        <td></td>
        <td>${preallocation.map_id}</td>
        <td>${preallocation.host_name}</td>
        <td>${preallocation.location}</td>
        <td class=" text-right">${preallocation.cog_land_area}</td>
        <td class=" text-right">${preallocation.total_homestead_area}</td>
        <td></td>
        <td></td>
    </tr>
</c:forEach>