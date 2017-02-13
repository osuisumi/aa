<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://aip.haoyu.com/debate" prefix="aip_debate" %>
<fmt:formatDate value="<%=new java.util.Date()%>" type="both"
	pattern="yyyy-MM-dd" var="nowDate" />
<%@ taglib prefix="main" tagdir="/WEB-INF/tags" %>
<spring:url value="/js" var="scriptUrl"/>
<c:set var="javaScripts">
${scriptUrl}/sip-core.js,${scriptUrl}/activity-function.js,${scriptUrl}/My97DatePicker/WdatePicker.js
</c:set>
<main:mainLayout title="编辑辩论" javascripts="${javaScripts}">
<div class="ag-pMain">
<form:form id="editDebateForm" action="${ctx}/debates/${debate.id}" method="put">
	<input type="hidden" name="debateRelations[0].id" value="${debate.debateRelations[0].id}">
	<div class="ag-pbCon">
		<div class="ag-pbArgue-lst">
			<div class="am-pb-mod">
				<div class="c-txt">
					<em>*</em><span>论题：</span>
				</div>
				<div class="c-center">
					<div class="m-ipt-mod">
						<input type="text" name="title"
							class="u-ipt {required:true,byteMaxLength:60}"
							placeholder="请输入你的辨论主题" value="${debate.title}" />
					</div>
				</div>
			</div>
			<!--end .am-pb-mod 活动发布模块-->
			<div class="am-pb-mod">
				<div class="c-center">
					<div class="m-ipt-mod">
						<textarea name="supplementExplanation" id="" class="u-textarea"
							placeholder="补充说明，（选填）" class="{required:true,byteMaxLength:800}">${debate.supplementExplanation}</textarea>
					</div>
				</div>
			</div>
			<!--end .am-pb-mod 活动发布模块-->
			<c:choose>
			<c:when test="${not empty debate.arguments }">
				<c:set var="argumentCount" value="${fn:length(debate.arguments)}"/>
				<c:forEach items="${debate.arguments}" var="argument" varStatus="vs">
					<div class="am-pb-mod am-pb-mod-vp">
						<div class="c-txt">
							<em>*</em> 
							<span>
								${aip_debate:getArgumentLabel(argumentCount,argument.orderNo)}方 
							</span>
						</div>
						<div class="c-center">
							<div class="m-ipt-mod">
								<input type="hidden" name="arguments[${argument.orderNo }].id"
									value="${argument.id}"> 
									<input type="hidden" name="arguments[${argument.orderNo }].orderNo" value="${argument.orderNo }">
									<input type="text"
									name="arguments[${argument.orderNo}].description"
									class="u-ipt {required:true,byteMaxLength:30}"
									placeholder="一句话的描述"
									value="${argument.description }">
							</div>
						</div>
						<div class="c-side">
								<a class="u-nbtn au-add-ep" <c:if test="${not empty argument.supplementExplanation}">style="display:none"</c:if>>+补充说明</a> 
								<span style="display: none">${argument.orderNo }</span> 
								<a class="u-nbtn au-delete-vp">×删除</a>
						</div>
					</div>
					<c:if test="${not empty argument.supplementExplanation}">
						<div class="am-pb-mod am-pb-mod-ep">
							<div class="c-txt">
								<em></em> <span>
									补充说明：
								</span>
							</div>
							<div class="c-center">
								<div class="m-ipt-mod">
									<textarea name="arguments[${argument.orderNo }].supplementExplanation" id="arguse_${argument.orderNo }"
										class="u-textarea {byteMaxLength:200}" placeholder="补充说明，（选填）">${argument.supplementExplanation }</textarea>
								</div>
								<div class="c-side">
									<a href="javascript:void(0);" class="u-nbtn au-delete-ep">×删除</a>
								</div>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<div class="am-pb-mod am-pb-mod-vp">
				<div class="c-txt">
					<em>*</em><span>正方论点：</span>
				</div>
				<div class="c-center">
					<div class="m-ipt-mod">
						<input type="hidden" name="arguments[0].id" value="${sip-c:uuid}">
						<input type="hidden" name="arguments[0].orderNo" value="0"> 
						<input type="text"
							name="arguments[0].description"
							class="u-ipt {required:true,byteMaxLength:30}"
							placeholder="一句话的描述" value="">
					</div>
				</div>
				<div class="c-side">
					<a class="u-nbtn au-add-ep">+补充说明</a> <span style="display: none">0</span>
					<a class="u-nbtn au-delete-vp">×删除</a>
				</div>
			</div>
			<!--end .am-pb-mod 活动发布模块-->
			<div class="am-pb-mod am-pb-mod-vp">
				<div class="c-txt">
					<em>*</em><span>反方论点：</span>
				</div>
				<div class="c-center">
					<div class="m-ipt-mod">
						<input type="hidden" name="arguments[1].id" value="${sip-c:uuid}">
						<input type="hidden" name="arguments[1].orderNo" value="1">
						<input type="text" name="arguments[1].description"
							class="u-ipt {required:true,byteMaxLength:30}"
							placeholder="一句话的描述" value="">
					</div>
				</div>
				<div class="c-side">
					<a class="u-nbtn au-add-ep">+补充说明</a> <span style="display: none">1</span>
					<a class="u-nbtn au-delete-vp">×删除</a>
				</div>
			</div>
			</c:otherwise>
			</c:choose>
			<!--end .am-pb-mod 活动发布模块-->
			<div class="am-pb-mod am-pb-mod1">
				<div class="c-center">
					<div class="m-btnBlock1">
						<a class="u-nbtn au-add-vp">+新论点</a>
					</div>
				</div>
			</div>
		
			<div class="am-pb-mod">
				<div class="c-txt">
					<em>*</em><span>活动时间：</span>
				</div>
				<div class="c-center">
					<div class="m-time-mod f-cb">
						<div class="m-slt-time">
							<input name="activity.startTime" id="startTimeParam" type="text"
								class="u-ipt" onclick="WdatePicker({minDate:'${nowDate}'})"
								value="<c:choose><c:when test='${empty debate.debateRelations[0].timePeriod.startTime }'>${nowDate}</c:when><c:otherwise><fmt:formatDate value="${debate.debateRelations[0].timePeriod.startTime }" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>">
							<a class="u-calendar-ico"></a>
						</div>
						<span class="u-time-txt">至</span>
						<div class="m-slt-time">
							<input name="activity.endTime" id="endTimeParam" type="text"
								class="u-ipt {gtAndEqStartTime:'startTimeParam'}"
								onclick="WdatePicker({minDate:'${nowDate}'})" value='<c:choose><c:when test='${empty debate.debateRelations[0].timePeriod.endTime }'>${nowDate}</c:when><c:otherwise><fmt:formatDate value="${debate.debateRelations[0].timePeriod.endTime }" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>'>
							<a class="u-calendar-ico"></a>
						</div>
					</div>
				</div>
			</div>
			<!--end .am-pb-mod 活动发布模块-->
			<main:sip-tag relationId="${debate.id}"/>
			<!--end .am-pb-mod 活动发布模块-->
			<div class="am-pb-mod am-pb-mod2">
				<div class="c-center">
					<div class="m-btnBlock">
						<a class="u-confirm-btn" onclick="editDebate()">发布活动</a> <a
							class="u-cancel-btn" onclick="cancel()">取消</a>
					</div>
				</div>
			</div>
			<!--end .am-pb-mod 活动发布模块-->
		</div>
		<!--end .ag-pbArgue-lst -->
	</div>
	<!--end .ag-publishCon-->
</form:form>
</div>
<script>
function editDebate() {
	if (!$('#editDebateForm').validate().form()) {
		return false;
	}
	$.ajax({
			url:$("#editDebateForm").attr("action"),
			data:$("#editDebateForm").serialize(),
			type:"post",
			async:false,
			success:function(data){
				if(data!=null){
					alert($.parseJSON(data));
				}
			}
	})
}
</script>
</main:mainLayout>