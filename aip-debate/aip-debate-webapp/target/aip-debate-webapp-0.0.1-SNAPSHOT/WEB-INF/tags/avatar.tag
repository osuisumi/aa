<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url value="${pageContext.request.contextPath}/images" var="imageUrl" />
<%@ attribute name="user" type="com.haoyu.sip.core.entity.User" required="true"%>
<c:choose>
	<c:when test="${not empty user and not empty user.avatar}">
		<img src="${user.avatar}" alt="">
	</c:when>
	<c:otherwise>
		<img src="${imageUrl}/avatar.jpg" alt="">
	</c:otherwise>
</c:choose>
