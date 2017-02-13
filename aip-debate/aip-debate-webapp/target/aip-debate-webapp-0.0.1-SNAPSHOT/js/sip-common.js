//打开意见反馈
function  open_feedback (){
        var $opa_btn = ".m-fix-opa .item1";
        var $feedback = $(".g-feedback-box");
        var widths = 280;
        var height = 290;

        $(document).on("click",$opa_btn,function(){
            var _ts = $(this);
            if(_ts.hasClass("z-crt")){
               // _ts.removeClass("z-crt");
                close_feedback();
            }else {
                _ts.addClass("z-crt");
                $feedback.show().stop().animate({
                    'width': widths + 'px',
                    'height': height + 'px'
                },200);
            }
        })
        $feedback.find(".u-close-btn").on("click",function(){
            close_feedback();
        })

    }

    // 关闭意见反馈
function close_feedback(){
        $feedback = $(".g-feedback-box");
        $feedback.stop().animate({
            'width': 0,
            'height': 0
        },200,function(){
            $(this).hide();
            $(".m-fix-opa .item1").removeClass("z-crt");
        })
}

function send_feedback(){
	if (!$("#feedbackForm").validate().form()) {
		return false;
	}
	$.post($("#feedbackForm").attr("action"),$("#feedbackForm").serialize(),
			function(data){
				if(data.responseCode=='00'){
					alert("感谢您的反馈！我们将努力提升服务！");
				}
				close_feedback();
			}
	);
}

function subnavFn(){
	var $subnav = $(".m-subnav");
	$subnav.children(".t1").on("click",function(){
		if($(this).hasClass("z-crt")){
			$subnav.children("a").removeClass("z-crt");
			$(".m-subnav-tip").hide();
		}else {
			$subnav.children("a").removeClass("z-crt");
			$(this).addClass("z-crt");
			$(".m-subnav-tip").hide();
			$(".m-subnav-tip.tip1").show();
		}
	})
	$subnav.children(".t2").on("click",function(){
		if($(this).hasClass("z-crt")){
			$subnav.children("a").removeClass("z-crt");
			$(".m-subnav-tip").hide();
		}else {
			$subnav.children("a").removeClass("z-crt");
			$(this).addClass("z-crt");
			$(".m-subnav-tip").hide();
			$(".m-subnav-tip.tip2").show();
		}
	})
}

function toTop(){
    $(document).on("click","#toTop",function(){
        $('html,body').animate({
            scrollTop: 0
        },100);
    })
}