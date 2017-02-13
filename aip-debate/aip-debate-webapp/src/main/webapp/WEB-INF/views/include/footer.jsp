<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <div id="g-ft">
	    <div class="f-auto">
		<ul class="m-ft-info">
			<li class="item1">
			    <h6>主办单位</h6>
			    <p>广东第二师范学院</p>
			 </li>
			 <li class="item2">
			    <h6>地址</h6>
			    <p>广州市海珠区新港中路351号</p>
			</li>
			<li class="item3">
			    <h6>电话</h6>
			    <p>020-34113588</p>
			</li>
		</ul>
		<div class="copyright">
			<img src="${ctx}/images/copy-logo.png" alt="">
			<p>广东第二师范学院版权所有</p>
			<p>CopyRight<span>©</span>2012-<fmt:formatDate value="${now}" type="both" pattern="yyyy" />&nbsp;&nbsp;粤ICP备05008839号</p>
		</div>
	</div>
</div>	    		
<div class="m-fix-opa">
	<a href="javascript:void(0);" class="item1" title="反馈意见"></a> <a
		href="javascript:void(0);" class="item2" id="toTop" title="返回顶部"></a>
	<div class="g-feedback-box">
		<form action="${pageContext.request.contextPath}/feedbacks" id="feedbackForm" method="post">
		<input type="hidden" name="relation.id" value="sbt">
		<input type="hidden" name="relation.type" value="site">
		<h3 class="tt">意见反馈</h3>
		<a href="javascript:void(0);" class="u-close-btn">×</a>
		<p class="explain">您留下的每个字都将被用于改善我们的服务。</p>
		<div class="m-ipt-mod">
			<input type="text" name="email" class="u-ipt {required:true,email:true}" placeholder="邮箱">
		</div>
		<div class="m-ipt-mod">
			<textarea name="content"  class="u-textarea {required:true,byteMaxLength:2000}" placeholder="您的反馈"></textarea>
		</div>
		<a href="###" onclick="send_feedback();" class="u-confirm-btn">发送</a>
		</form>
	</div>
</div>
<script>
//返回顶部
toTop();
//打开意见反馈
open_feedback();
subnavFn();
</script>