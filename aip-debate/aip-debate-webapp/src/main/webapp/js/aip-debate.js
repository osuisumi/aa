function supportDebateArgument(argumentId, debateRelationId) {
	$.ajax({
		url : "/debate_users",
		data : "argument.id=" + argumentId + "&debateRelation.id="
				+ debateRelationId,
		type : "POST",
		success : function(data) {
			alert(data);
		}
	})
}

function publishDebateUserViews() {
	if (!$('#publishDebateUserViewsForm').validate().form()) {
		return false;
	}

	$.ajax({
		url : $("#publishDebateUserViewsForm").attr("action"),
		data : $("#publishDebateUserViewsForm").serialize(),
		type : "post",
		success : function(data) {
			alert(data.responseCode);
		}
	})
}

function updateDebateUserViews() {
	if (!$('#editUserViewsForm').validate().form()) {
		return false;
	}

	$.ajax({
		url : $("#editUserViewsForm").attr("action"),
		data : $("#editUserViewsForm").serialize(),
		type : "post",
		success : function(data) {
			alert(data.responseCode);
		}
	})
}

function loadDebateUserViews() {
	$.ajax({
		url : $("#viewUserViewsForm").attr("action"),
		data : $("#viewUserViewsForm").serialize(),
		type : "GET",
		success : function(data) {
			$(".ag-comment-layout").html(data);
			activitysJs.functions.diySelects();
		}
	})
}

function supportDebateUserViews(id, supportNum) {
	$("#attitudeRelationId").val(id);
	$.ajax({
		url : $("#attitudesForm").attr("action"),
		data : $("#attitudesForm").serialize(),
		type : "POST",
		success : function(data) {
			if (data != null && data.responseCode == '00') {
				$("#sn_" + id).text("（" + (supportNum + 1) + "）");
			}
		}
	})
}

function deleteDebateUserViews(id, debateUserId) {
	$('#deleteUserViewsForm').attr('action',
			$('#deleteUserViewsForm').attr('action') + '/' + id);
	$('#duvf_debateUserId').val(debateUserId);
	$.ajax({
		url : $("#deleteUserViewsForm").attr("action"),
		data : $("#deleteUserViewsForm").serialize(),
		type : "POST",
		success : function(data) {
			if (data != null && data.responseCode == '00') {
				loadDebateUserViews();
			}
		}
	})
}

function createFollow() {
	$.ajax({
		url : $("#createFollowForm").attr("action"),
		data : $("#createFollowForm").serialize(),
		type : "POST",
		success : function(data) {
			if (data != null && data.responseCode == '00') {
				// loadDebateUserViews();
				$(".ag-opa .ua-collect").text("已关注");
			}
		}
	});
}

function createViewComment(obj) {
	var $form=$(obj).closest(".am-isComment-box").find("form");
	if (!$form.validate().form()) {
		return false;
	}
	console.log($form.html());
	$.ajax({
		url : $form.attr("action"),
		data : $form.serialize(),
		type : "POST",
		success : function(data) {
			if (data != null && data.responseCode == '00') {
				alert(data);
			}
		}
	});
}

