<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ attribute name="title" type="java.lang.String" required="true" %>
<%@ attribute name="javascripts" type="java.lang.String" required="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8"/>
		<title><c:out value="${title}"/></title>
		<spring:url value="/css" var="styleUrl" />
		<spring:url value="/js" var="scriptUrl" />	
		<link rel="stylesheet" href="${styleUrl}/reset.css"/>
		<link rel="stylesheet" href="${styleUrl}/activity.css"/>	
		<link rel="stylesheet" href="${styleUrl}/research.css"/>
		<link type="text/css" rel="stylesheet" href="${scriptUrl}/validation/css/cmxform.css">
		<script type="text/javascript" src="${scriptUrl}/jquery.js"></script>
		<script type="text/javascript" src="${scriptUrl}/validation/lib/jquery.metadata.js"></script> 
		<script type="text/javascript" src="${scriptUrl}/validation/jquery.validate.js"></script> 
		<script type="text/javascript" src="${scriptUrl}/validation/expand.js"></script> 
		<script type="text/javascript" src="${scriptUrl}/validation/localization/messages_cn.js"></script>
		<script type="text/javascript" src="${scriptUrl}/sip-common.js"></script> 
		<c:if test="${not empty javascripts}">
			<c:forTokens items="${javascripts}" delims="," var="url">
			    <script type="text/javascript" src="${url}"></script>
			</c:forTokens>
		</c:if>
	</head>
	
	<body>
		<div id="wrap">
			<jsp:include page="/WEB-INF/views/include/header.jsp"/>	
			<div id="g-bd">
				<div class="f-auto">
	    		<!--start 资源详情 -->
	    		<div  class="g-content-page">
		        <!--start #publishArgue 发起辩论-->
			        <jsp:include page="/WEB-INF/views/include/breadCrumb.jsp"/>			        
					<jsp:doBody />
				</div>
				</div>
			</div>
			<jsp:include page="/WEB-INF/views/include/footer.jsp"/>	
		</div>
	</body>
</html>
