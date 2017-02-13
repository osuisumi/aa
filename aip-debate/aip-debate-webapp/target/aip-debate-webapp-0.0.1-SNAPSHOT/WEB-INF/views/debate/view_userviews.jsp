<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib prefix="aip" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://aip.haoyu.com/debate" prefix="aip_debate" %>
<c:set var="argumentCount" value="${fn:length(debateRelation.debate.arguments)}"/>
	<div class="am-coment-tp">
		<div class="c-sttc">
			已有&nbsp;<b>${debateRelation.viewsNum}</b>&nbsp;个观点<i class="ua-dots"></i><b>${debateRelation.commentsNum}</b>&nbsp;条评论
		</div>
		<div class="am-diyslt">
			<c:choose>
				<c:when test="${empty debateUser.argument or empty debateUser.argument.id}">
					<a href="javascript:void(0);" class="show"><span>全部观点</span><i class="ua-ico"></i></a>
				</c:when>
				<c:otherwise>
					<c:forEach items="${debateRelation.debate.arguments}" var="argument" varStatus="vs">
						<c:if test="${ debateUser.argument.id eq argument.id}">
							<a href="javascript:void(0);" class="show"><span>${aip_debate:getArgumentLabel(argumentCount,argument.orderNo)}方观点</span><i class="ua-ico"></i></a>
						</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<div class="slt-lst">
				<i class="trg"></i> <i class="trgs"></i> 
				<a href="javascript:$('#vuvf_argumentId').val('');loadDebateUserViews();">全部观点</a> 
				<c:forEach items="${debateRelation.debate.arguments}" var="argument" varStatus="vs">
					<a href="javascript:$('#vuvf_argumentId').val('${argument.id}');loadDebateUserViews();">${aip_debate:getArgumentLabel(argumentCount,argument.orderNo)}方观点</a>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="am-slt-sort f-cb">
	<c:if test="${empty orders or orders eq 'createTime.desc'}"></c:if>
		<a href="javascript:$('#vuvf_orders').val('create_time.desc');loadDebateUserViews();" <c:if test="${empty orders or orders eq 'create_time.desc'}">class="z-crt"</c:if>>时间</a> 
		<a href="javascript:$('#vuvf_orders').val('comments_num.desc');loadDebateUserViews();" <c:if test="${not empty orders and orders eq 'comments_num.desc'}">class="z-crt"</c:if>>评论数</a> 
		<a href="javascript:$('#vuvf_orders').val('support_num.desc');loadDebateUserViews();" <c:if test="${not empty orders and orders eq 'support_num.desc'}">class="z-crt"</c:if>>点赞数</a>
	</div>
	<div class="ag-comment-main">
		<c:choose>
			<c:when test="${empty debateUserViews}">
				<div class="no-content no-content1">
                        <p>还没有其他观点，快成为第一个提出观点的人~</p>
                 </div>
			</c:when>
			<c:otherwise>
				<ul class="ag-cmt-lst ag-cmt-lst-p">				
				<c:forEach items="${debateUserViews}" var="duv">
					<li class="am-cmt-block">
                            <div class="c-info">
                                <a href="#" class="au-cmt-headimg">
                                    <aip:avatar user="${duv.creator}"/>
                                </a>
                                <p class="tp">
                                    <a href="#" class="name">${duv.creator.realName}</a>
                                    <span class="au-idt-type au-idt-type${duv.debateUser.argument.orderNo+1}">${aip_debate:getArgumentLabel(argumentCount,duv.debateUser.argument.orderNo)}方</span>
                                    <span class="time">${sipc:prettyTimeMilliSecond(duv.createTime)}</span>
                                </p>
                                <p class="cmt-dt">${duv.viewsContent}</p>
                                <div class="ag-opa">
                                    <a href="javascript:supportDebateUserViews('${duv.id}',${duv.supportNum});" class="ua-praise">
                                        <i class="ua-ico"></i>赞同<b id="sn_${duv.id}">（${duv.supportNum}）</b>
                                    </a>
                                    <i class="ua-opa-dot"></i>
                                    <a href="javascript:void(0);" class="ua-comment">
                                        <i class="ua-ico"></i>评论<b>（${duv.commentsNum}）</b>
                                    </a>
                                    <i class="ua-opa-dot"></i>
                                    <a href="###" onclick="javascript:$('#editUserViewsForm').attr('action',$('#editUserViewsForm').attr('action')+'/${duv.id}');$('#editUserViewsText').text($(this).parent().prev().text());activitysJs.functions.aJumpLayer($(this),$('.m-alterComment-layer'))" class="ua-edit ua-editComment-btn">
                                           <i class="ua-ico"></i>编辑
                                    </a>
                                    <i class="ua-opa-dot"></i>
                                    <a href="###" onclick="javascript:deleteDebateUserViews('${duv.id}','${duv.debateUser.id}')" class="ua-delete">
                                        <i class="ua-ico"></i>删除
                                    </a>
                                </div>    
                            </div>
                            <div class="ag-is-comment">
                                <i class="ua-comment-trg"></i>
                                <div class="am-isComment-box am-ipt-mod">
                                    <textarea id="comments_${duv.id}" class="u-textarea" placeholder="我也说一句"></textarea>
                                    <div class="am-cmtBtn-block f-cb">
                                        <a href="javascript:void(0);" class="ua-face"></a>
                                        <a href="javascript:void(0);" class="u-cmtPublish-btn u-confirm-btn1">发表</a>
                                    </div>
                                </div>

                            </div>
                        </li>		
				</c:forEach>
				</ul> 
				<form action="${ctx}/attitudes" id="attitudesForm">
					<input type="hidden" name="attitude" value="support"/>
					<input type="hidden" name="relation.id" id="attitudeRelationId">
					<input type="hidden" name="relation.type" value="debate_user_views">
				</form>
				<form:form action="${ctx}/debate_userviews" method="delete" id="deleteUserViewsForm">
					<input type="hidden" name="debateUser.id" id="duvf_debateUserId">
				</form:form>  
			</c:otherwise>
		</c:choose>
	</div>
