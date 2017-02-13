<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url value="${pageContext.request.contextPath}/images" var="imageUrl" />
<div id="g-hd">
	<div class="f-auto">
		<h1 id="logo">
			<a href="###"> <img src="${imageUrl}/logo.png" alt=""/>广东第二师范学院培训网
			</a>
		</h1>
		<!--end #logo -->
		<ul id="nav">
			<li><a href="index.html" class="z-crt">校本研修</a></li>
			<li><a href="area.html">区域研修</a></li>
			<li><a href="schools-lst.html">区域/学校列表</a></li>
		</ul>
		<!--end nav -->
		<div class="m-tp-user">
			<a href="#" class="user"> <img src="${imageUrl}/avatar.jpg" alt=""/>罗维维
			</a>
		</div>
		<!--end .m-tp-user -->
		<a href="message.html" class="u-message"> <i class="u-message-ico"></i>
		</a>
		<div class="m-search">
			<a href="#" class="u-search"> <i class="u-search-ico"></i>
			</a> <input type="text" value="" placeholder="请输入搜索的内容"/>
		</div>
		<!--end .m-tp-user -->
	</div>
</div>