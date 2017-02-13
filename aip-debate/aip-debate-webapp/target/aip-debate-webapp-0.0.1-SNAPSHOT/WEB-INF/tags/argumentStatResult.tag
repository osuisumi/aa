<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sipc" uri="/WEB-INF/tld/sip-core-tag.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://aip.haoyu.com/debate" prefix="aip_debate" %>
<%@ attribute name="debateArgumentStats" type="java.util.List" required="true"%>
<%@ attribute name="participateNum" type="java.lang.Integer" required="true"%>
<c:set var="argumentCount" value="${fn:length(debateArgumentStats)}"/>
<c:forEach items="${debateArgumentStats}" var="das" varStatus="vs">
	<c:choose>
		<c:when test="${empty das.participateNum or das.participateNum==0}">
			<fmt:formatNumber value="0" var="percent" />
			<span class="ratio ratio${vs.count }" style="width:${percent}%">${percent}%<span
				class="b"></span>
				<div class="show-num">
                         <i class="trg"></i>0人支持${aip_debate:getArgumentLabel(argumentCount,das.argument.orderNo)}方
                </div>
			</span>				
		</c:when>
		<c:otherwise>
			<fmt:formatNumber value="${das.participateNum/participateNum*82}" pattern="##.#" var="width" />
			<fmt:formatNumber value="${das.participateNum/participateNum*100}" pattern="##.#" var="percent" />
				<span class="ratio ratio${vs.count }" style="width:${width}%">${percent}%
					<span class="b"></span>
					<div class="show-num">
                         <i class="trg"></i>${das.participateNum}人支持${aip_debate:getArgumentLabel(argumentCount,das.argument.orderNo)}方
                    </div>
				</span>
		</c:otherwise>
	</c:choose>
</c:forEach>