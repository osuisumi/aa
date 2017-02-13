<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="relationId" type="java.lang.String" required="false"%>
<c:if test="${not empty relationId}">
	<script>
		$.get('/tags','relation.id=${relationId}',
						function(data) {
							if (data != null) {
								var $tag_lst = $(".am-add-tag .am-tag-lst")
								$.each(data,function(i, tag) {
									$tag_lst.append('<li>'+ '<span class="txt">'
														  + tag.name
														  + '</span>'
														  + '<input type="hidden" name="tags['+i+'].id" value="'+ tag.id + '"/>'
														  + '<a href="javascript:void(0);" class="dlt" title="删除标签">×</a>'
														  + '</li>');
									})

							}
						});
	</script>
</c:if>
<div class="am-pb-mod am-pb-mod1">
	<div class="c-txt">
		<em></em><span>标签：</span>
	</div>
	<div class="c-center">
		<div class="am-add-tag f-cb">
			<div class="am-tagipt m-ipt-mod">
				<input type="text" placeholder="添加标签，如：教案" id="tagInput" value=""
					class="u-ipt"> <input type="hidden" id="hTagId">
				<div class="l-slt-lst">
					<i class="trg"></i> <i class="trgs"></i>
					<div class="lst" class="labelSelectDiv"></div>
				</div>
			</div>
			<a class="u-nbtn au-add-tag">+添加标签</a>
			<ul class="am-tag-lst">

			</ul>
		</div>
	</div>
</div>