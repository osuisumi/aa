<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<fmt:formatDate value="${now}" type="both" pattern="yyyy-MM-dd" var="nowDate" />
<%@ taglib prefix="aip" tagdir="/WEB-INF/tags" %>
<spring:url value="/js" var="scriptUrl"/>
<c:set var="javaScripts">
${scriptUrl}/sip-core.js,${scriptUrl}/activity-function.js,${scriptUrl}/My97DatePicker/WdatePicker.js,${scriptUrl}/aip-debate.js
</c:set>
<aip:mainLayout title="新增辩论" javascripts="${javaScripts}">
<div class="ag-pMain">
<form:form id="addDebateForm" action="${ctx}/debates" method="post">
<input type="hidden" name="relationId" value="${relationId }">
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
						placeholder="请输入你的辨论主题" value="" />
				</div>
			</div>
		</div>
		<!--end .am-pb-mod 活动发布模块-->
		<div class="am-pb-mod">
			<div class="c-center">
				<div class="m-ipt-mod">
					<textarea name="supplementExplanation" id="" class="u-textarea"
						placeholder="补充说明，（选填）" class="{required:true,byteMaxLength:800}"></textarea>
				</div>
			</div>
		</div>
		<!--end .am-pb-mod 活动发布模块-->
		<div class="am-pb-mod am-pb-mod-vp">
			<div class="c-txt">
				<em>*</em><span>正方论点：</span>
			</div>
			<div class="c-center">
				<div class="m-ipt-mod">
					<input type="hidden" name="arguments[0].id" value="${sipc:uuid()}">
					<input type="hidden" name="arguments[0].orderNo" value="0">
					<input type="text" name="arguments[0].description"
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
					<input type="hidden" name="arguments[1].id" value="${sipc:uuid()}">
					<input type="hidden" name="arguments[1].orderNo" value="1">
					<input type="text" name="arguments[1].description"
						class="u-ipt {required:true,byteMaxLength:30}"
						placeholder="一句话的描述" value="${debate.arguList[1].description }">
				</div>
			</div>
			<div class="c-side">
				<a class="u-nbtn au-add-ep">+补充说明</a> <span style="display: none">1</span>
				<a class="u-nbtn au-delete-vp">×删除</a>
			</div>
		</div>
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
							value="<c:choose><c:when test='${empty debate.startTime }'>${nowDate}</c:when><c:otherwise><fmt:formatDate value="${debate.startTime }" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>">
						<a class="u-calendar-ico"></a>
					</div>
					<span class="u-time-txt">至</span>
					<div class="m-slt-time">
						<input name="activity.endTime" id="endTimeParam" type="text"
							class="u-ipt {gtAndEqStartTime:'startTimeParam'}"
							onclick="WdatePicker({minDate:'${nowDate}'})"
							value=''>
						<a class="u-calendar-ico"></a>
					</div>
				</div>
			</div>
		</div>
		<!--end .am-pb-mod 活动发布模块-->
		<aip:sip-tag/>
		<!--end .am-pb-mod 活动发布模块-->
		<div class="am-pb-mod am-pb-mod2">
			<div class="c-center">
				<div class="m-btnBlock">
					<a class="u-confirm-btn" onclick="saveDebate();">发布活动</a> <a
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
	function saveDebate() {
		if (!$('#addDebateForm').validate().form()) {
			return false;
		}
		$.ajax({
				url:$("#addDebateForm").attr("action"),
				data:$("#addDebateForm").serialize(),
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
</aip:mainLayout>