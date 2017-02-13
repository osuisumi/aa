<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://aip.haoyu.com/debate" prefix="aip_debate" %>
<%@ taglib prefix="aip" tagdir="/WEB-INF/tags" %>
<spring:url value="${pageContext.request.contextPath}/images" var="imageUrl" />
<spring:url value="/js" var="scriptUrl"/>
<c:set var="debate" value="${debateUser.debateRelation.debate}"/>
<c:set var="argumentCount" value="${fn:length(debateArgumentStats)}"/> 
<c:set var="javaScripts">
${scriptUrl}/sip-core.js,${scriptUrl}/activity-function.js,${scriptUrl}/aip-debate.js
</c:set>
<aip:mainLayout title="浏览辩论"  javascripts="${javaScripts}">
<div class="ag-cMain">
                <div class="ag-main-hd">
                    <div class="am-title">
                        <h2>
                            <span class="a-type">【辩论】</span>
                            <span class="txt">${debate.title}</span>
                        </h2>
                    </div>
                    <div class="am-title-info f-cb">
                        <div class="c-infor">
                            <span class="txt">发起人：${debate.creator.realName}</span>
                            <span class="txt">参与人数：${debateUser.debateRelation.participateNum}</span>
                            <span class="line">|</span>
                            <span class="txt">浏览数：${debateUser.debateRelation.browseNum}</span>
                        </div>
                        <div class="ag-opa">
                            <a href="###" <c:if test="${empty follow}">onclick="createFollow();" </c:if> class="ua-collect">
                                <i class="ua-ico"></i>
                                <c:choose>
                                    <c:when test="${not empty follow}">
                                    	 已关注(${debateUser.debateRelation.followNum})
                                    </c:when> 
                                    <c:otherwise>
                                    	关注(${debateUser.debateRelation.followNum})
                                    </c:otherwise>                                                         
                                </c:choose>
                            </a>
                            <i class="ua-opa-dot"></i>
                            <a href="javascript:void(0);" class="ua-edit">
                                <i class="ua-ico"></i>编辑
                            </a>
                            <i class="ua-opa-dot"></i>
                            <a href="javascript:void(0);" class="ua-delete">
                                <i class="ua-ico"></i>删除
                            </a>
                            <form id="createFollowForm" action="${ctx}/follows" method="POST">
                            	<input type="hidden" name="followEntity.id" value="${debateUser.debateRelation.id}">
                            	<input type="hidden" name="followEntity.type" value="debate_relation">
                            </form>
                        </div>    
                        <div class="am-tt-tag-lst">
                        	<c:if test="${not empty debateUser.debateRelation.debate.tags}">
                        		<c:forEach items="${debateUser.debateRelation.debate.tags}" var="tag">
                        			<span class="tag">${tag.name}</span>
                        		</c:forEach>
                        	</c:if>
                        </div>
                        <!-- /* 此处为管理员操作 */ -->
                        
                    </div>
                    <p class="cont-txt">${debate.supplementExplanation}</p>
                    <p class="cont-img">
                        <a href="javascript:void(0);"><img src="${imageUrl}/agImg1.jpg" alt=""></a>
                        <a href="javascript:void(0);"><img src="${imageUrl}/agImg2.jpg" alt=""></a>
                    </p>
                    <aip:timePeriod timePeriod="${debate.debateRelations[0].timePeriod}" label="活动"/>
                </div><!--end .g-main-hd -->
                <div class="g-aruge-bd">
                	<c:choose>
                		<c:when test="${sipc:hasEnded(debate.debateRelations[0].timePeriod.endTime)}">
                			<div class="m-ratio">
		                        <span class="txt">当前统计结果：</span>
		                        <div class="ratio-p f-cb" style="width: 40%">
									<aip:argumentStatResult participateNum="${debateUser.debateRelation.participateNum}" debateArgumentStats="${debateArgumentStat}"/>
		                        </div>
		                    </div>
		                    <div class="ag-aruge-box end">
		                    	
		                    	<c:forEach items="${debateArgumentStats}" var="das" varStatus="vs">
			                        <div class="am-viewpoint am-viewpoint${vs.count}">
			                            <div class="ts-con">
			                                <h3>${das.argument.description}</h3>
			                                <p>${das.argument.supplementExplanation}</p>
			                                <span class="vp-type vp-type${vs.count}">${aip_debate:getArgumentLabel(argumentCount,das.argument.orderNo)}</span>
			                            </div>
			                            <div class="am-best-vp">
			                                    <div class="sign-pd">
			                                        <div class="sign">
			                                            <b>最佳${aip_debate:getArgumentLabel(argumentCount,das.argument.orderNo)}方观点</b>
			                                            <i class="trg1"></i>
			                                            <i class="trg2"></i>
			                                        </div>
			                                    </div>
				                                <div class="am-best-cmt">
				                                	<c:choose>
					                                	<c:when test="${not empty das.bestViews}">
					                                    <div class="t-user">
					                                        <a href="###" class="img">
					                                        	<aip:avatar user="${das.bestViews.creator}"/>
					                                        </a>
					                                        <a href="#" class="name">${das.bestViews.creator.realName}</a>
					                                    </div>
					                                    <div class="t-txt-block">
					                                        <i class="i-trg"></i>
					                                        <p>${das.bestViews.viewsContent}</p>
					                                    </div>
					                                    </c:when>
					                                    <c:otherwise>
					                                    	数据正在统计中。。。
					                                    </c:otherwise>
				                                    </c:choose>
				                                </div>
			                            </div>
			                        </div><!--end .m-viewpoint 观点模块-->
			                     </c:forEach>
                       	   </div>                        
                		</c:when>
                		<c:otherwise>
                			<div class="ag-aruge-box"> 
		                    	<c:set var="hasSupportArgument" value="${not empty debateUser.id}"/>
		                    	<c:forEach items="${debateArgumentStats}" var="das" varStatus="vs">
		                    		<div class="am-viewpoint am-viewpoint${vs.count} <c:if test="${vs.first}">first</c:if><c:if test="${vs.last}">last</c:if>">
			                            <div class="ts-con" <c:if test="${hasSupportArgument and debateUser.argument.id ne das.argument.id}">style="opacity: 0.6;"</c:if>>			                                
			                            	<h3>${das.argument.description}</h3>
			                                <p>${das.argument.supplementExplanation}</p>
			                                <span class="vp-type vp-type${vs.count}">${aip_debate:getArgumentLabel(argumentCount,das.argument.orderNo)}</span>
			                                <div class="support">
			                                    <div class="t-tp">
			                                        <a href="javascript: <c:choose><c:when test="${hasSupportArgument}">void(0);</c:when><c:otherwise>supportDebateArgument('${das.argument.id}','${debateUser.debateRelation.id}');</c:otherwise></c:choose>" class="au-support">
			                                            <i class="u-ico"></i><em><c:if test="${hasSupportArgument and debateUser.argument.id eq das.argument.id}">已</c:if>支持</em>
			                                        </a>
			                                    </div>
			                                    <span class="s-txt"><em>${das.participateNum}</em></span>
			                                </div>    
			                            </div>
			                            <div class="au-vp-border"></div>
		                            <div class="au-vp-shade"></div>
		                          	</div><!--end .m-viewpoint 观点模块-->
		                    	</c:forEach>                       
		                        <div class="am-vp-border"></div>
		                        <div class="am-vs-icon">选择论点</div>
		                    </div>
		                    <div class="am-ratio">
		                        <span class="txt">当前统计结果：</span>
		                        <div class="ratio-p f-cb" style="width: 40%">
									<aip:argumentStatResult participateNum="${debateUser.debateRelation.participateNum}" debateArgumentStats="${debateArgumentStat}"/>
		                        </div>
		                    </div>
                		</c:otherwise>
                	</c:choose>
                </div><!--end .g-aruge-bd -->   
                <c:if test="${sipc:hasEnded(debate.debateRelations[0].timePeriod.endTime)==false}">
                <form:form action="${ctx}/debate_userviews" method="post" id="publishDebateUserViewsForm"> 
                <input type="hidden" name="debateUser.id" value="${debateUser.id}">
                <div class="am-comment-box am-ipt-mod">                	
                    <span class="au-comment-trg"></span>
                    <label class="comment-placeholder"></label>                    
                    <textarea id="" name="viewsContent" <c:if test="${empty debateUser.id}">onclick='activitysJs.functions.aJumpLayer($(".u-cmtPublish-btn"), $(".m-sltCamp-layer"));'</c:if> class="u-textarea {required:true,byteMaxLength:800}"></textarea>
                    <div class="am-cmtBtn-block f-cb">
                       <!--  <a href="javascript:void(0);" class="ua-face"></a> -->
                        <c:if test="${not empty debateUser.id}">
                        	<a href="javascript:publishDebateUserViews();" class="u-cmtPublish-btn u-confirm-btn1">发表</a>
                        </c:if>
                    </div>
                </div><!--end .m-comment-box 评论框--> 
                </form:form>
                </c:if>
            </div>
            <div class="ag-comment-layout">
            	
            </div>
            <form id="viewUserViewsForm" action="${ctx}/debate_userviews">
            	<input type="hidden" name="debateRelation.id" value="${debateUser.debateRelation.id}"/>
            	<input type="hidden" name="argument.id" id="vuvf_argumentId"/>
            	<input type="hidden" name="id" id="vuvf_id"/>
            	<input type="hidden" name="creator.id" id="vuvf_creator"/>
            	<input type="hidden" name="orders" id="vuvf_orders">
            </form>
            <script>
            		loadDebateUserViews();
            </script>
        <!--end #arguePage 辩论详情-->
        <div class="m-blackbg"></div>
        <c:if test="${empty debateUser.id}">
		    <div class="am-layer m-sltCamp-layer">
		        <div class="am-layer-hd">
		            <h3>提示</h3>
		            <a href="javascript:void(0);" class="au-close-btn">×</a>
		        </div>
		        <div class="am-layer-bd">
		           <div class="m-sltCamp">
		               <p>您当前暂未选择论点，选择论点后就可以发布了哦~</p>
		               <c:forEach items="${debateArgumentStats}" var="das" varStatus="vs">
		               		<a href="javascript:supportDebateArgument('${das.argument.id}','${debateUser.debateRelation.id}');" class="camp camp${vs.count}">支持${aip_debate:getArgumentLabel(argumentCount,das.argument.orderNo)}方</a>
		               </c:forEach>
		           </div>
		        </div>
		    </div>
	    </c:if>
	     <!--start .m-alterComment-layer 观点编辑弹出框-->
	    <form:form id="editUserViewsForm" action="${ctx}/debate_userviews" method="put"> 
	    <div class="am-layer m-alterComment-layer">
	        <div class="am-layer-hd">
	            <h3>编辑观点</h3>
	            <a href="javascript:void(0);" class="au-close-btn">×</a>
	        </div>
	        <div class="am-layer-bd">
	           <div class="g-ipt-box">
	                <div class="m-ipt-mod">
	                    <textarea id="editUserViewsText" class="u-textarea {required:true,byteMaxLength:800}" name="viewsContent" style="height: 150px;"></textarea>
	                </div>
	           </div>
	        </div>
	        <div class="am-layer-ft">
	            <a href="javascript:void(0);" onclick="updateDebateUserViews();" class="au-confirm-btn">确定</a>
	            <a href="javascript:void(0);" class="au-cancel-btn">取消</a>
	        </div>
	    </div>
	    </form:form>
	    <!--end .m-alterComment-layer 观点编辑弹出框-->
</aip:mainLayout>            
         