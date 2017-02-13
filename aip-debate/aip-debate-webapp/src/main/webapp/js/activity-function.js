$(function(){
    activitysJs.functions.init();
})
$(window).resize(function(){

})

var activitysJs = $(window).activitysJs || {};

activitysJs.functions = {
    init : function(){
        var _this = this;
        _this.viewpointShow();
        _this.diySelects();
        _this.commentOpa(".am-comment-box .u-textarea");
        _this.commentOpa(".am-isComment-box .u-textarea");
        _this.publishArgue();
        _this.set_tag();
        _this.prepare_lessons();
    },
    //倒计时
    countDown : function(refreshDivId,url,params){
        var $countDownBox = $(".am-count-down"),
            //$endTime = new Date($countDownBox.find(".endTime")),
            $endTime = $countDownBox.find(".endTime"),
            $nowTime = $countDownBox.find(".nowTime"),
            $day = $countDownBox.find(".day"),
            $hour = $countDownBox.find(".hour"),
            $minute = $countDownBox.find(".minute"),
            $second = $countDownBox.find(".second");
        var endTime = new Date($endTime.val()); //结束日期
   	    var nowTime = new Date($nowTime.val()); //结束日期
        var leftTime = parseInt((endTime.getTime()-nowTime.getTime())/1000); //计算剩下的时间 parseInt()为javascript中的取整
        var leftDay = parseInt(leftTime/(24*60*60)); //计算剩下的天数
        var leftHours = parseInt(leftTime/(60*60)%24); //计算小时
        var leftMinutes = parseInt(leftTime/60%60); //计算分钟
        var leftSeconds =  parseInt(leftTime%60); //计算分钟
            
        $day.text(leftDay);//设置天
        $hour.text(leftHours);//设置小时
        $minute.text(leftMinutes);//设置分钟
        $second.text(leftSeconds);//设置秒
        //判断天数是否小于10
        if(leftDay < 10) {
            $day.text("0" + leftDay);
        }
        //判断小时是否小于10
        if(leftHours < 10) {
            $hour.text("0" + leftHours);
        }
        //判断分钟是否小于10
        if(leftMinutes < 10) {
            $minute.text("0" + leftMinutes);
        }
        //判断秒钟是否小于10
        if(leftSeconds < 10) {
            $second.text("0" + leftSeconds);
        }
        //判断是否到了结束时间
        if(leftTime < 1){
            clearTimeout(countDownTimer);
            $day.text("00");
            $hour.text("00");
            $minute.text("00");
            $second.text("00");
        }  
        var countDownTimer = setInterval(function(){  
			leftTime = leftTime - 1; //计算剩下的时间 parseInt()为javascript中的取整
            var leftDay = parseInt(leftTime/(24*60*60)); //计算剩下的天数
            var leftHours = parseInt(leftTime/(60*60)%24); //计算小时
            var leftMinutes = parseInt(leftTime/60%60); //计算分钟
            var leftSeconds =  parseInt(leftTime%60); //计算分钟 
        	$day.text(leftDay);
            $hour.text(leftHours);
            $minute.text(leftMinutes);
            $second.text(leftSeconds);
            if(leftDay < 10) {
                $day.text("0" + leftDay);
            }
            if(leftHours < 10) {
                $hour.text("0" + leftHours);
            }
            if(leftMinutes < 10) {
                $minute.text("0" + leftMinutes);
            }
            if(leftSeconds < 10) {
                $second.text("0" + leftSeconds);
            }
            //判断是否到了结束时间
            if(leftTime < 1){
                clearTimeout(countDownTimer);
                $day.text("00");
                $hour.text("00");
                $minute.text("00");
                $second.text("00");
                  //刷新的页面  
            	$('#'+refreshDivId).load(url,params);
            } 
        },1000);
    },
    //辩论支持效果
/*    supportOpa : function(){
        var $support = $(".u-support");
        $support.on("click",function(){
            var _ts = $(this);
            //遍历判断是否点赞过
            _ts.each(function(){
                if(!$support.hasClass("z-crt")){
                    //点赞增加动画
                    $(this).addClass("z-crt").children(".u-ico").addClass("pulse");
                    //其他观点变灰
                    $(".u-support.z-crt").parents(".m-viewpoint").siblings().children(".ts-con").animate({"opacity" : "0.6"},200);
                }
            })
        })
    },*/
    //辩论观点布局
    viewpointShow : function(){
        var $vptParent = $(".ag-aruge-box"),
            $vptBox = $vptParent.children(".am-viewpoint"),
            $ratioP = $(".am-ratio"),
            $ratioBox = $ratioP.children(".ratio-p"),
            vptLenght = $vptBox.length,
            vptArr = ["正","反","甲","乙","丙","丁"];
        //初始判断第一个和最后一个元素，并增加clsss
        $vptBox.eq(0).addClass("first");
        $vptBox.eq(vptLenght - 1).addClass("last");
        $ratioBox.css({"width": 25 * vptLenght + "%"});
        for(var i = 0; i < vptLenght+1; i++){
            //判断观点个数，如果为两个，则名字为正反,否则命名为甲乙丙丁
            if(vptLenght < 3){
                $vptBox.eq(i).find(".vp-type").text(vptArr[i]);
                $vptBox.eq(i).find(".sign b").text("最佳"+vptArr[i]+"方观点");
                $(".u-idt-type1").text("正方");
                $(".u-idt-type2").text("反方");

            }else {
                $vptBox.eq(i).find(".vp-type").text(vptArr[i+2]);
                $vptBox.eq(i).find(".sign b").text("最佳`"+vptArr[i+2]+"方观点");
                $(".u-idt-type" + i).text(vptArr[i+1] + "方");
            }
        }
    },
    //评论框效果
    diySelects : function(){
        var $diy_select = $(".am-diyslt");

        $diy_select.each(function(){
            var $showBlock = $(this).find(".show"),
                $slt_lst = $diy_select.find(".slt-lst"),
                $slt_option = $slt_lst.find("a");
            //打开或关闭下拉框
            $showBlock.on("click",function(){
                var _ts = $(this);
                  $slt_lst.show();
            });
            //点击选择下拉框内容
            $slt_option.on("click",function(){
                $slt_option.removeClass("z-crt");
                $(this).addClass("z-crt");
          		$showBlock.children("span").text($(this).text());
                $slt_lst.hide();
            });
            //判断是否点击其他地方
            $(document).on("click",function(e){ 
                var target = $(e.target); 
                if(target.closest($diy_select).length == 0){ 
                    $showBlock.removeClass("z-crt");
                    $slt_lst.hide();
                } 
            }); 
        });

    },
    //评论框效果
    commentOpa : function(commentBox){
    	var u_height = 76,//输入框展开后的输入框高度
        s_height = 22;//输入框收起后的输入框高度

    $(document).on("click",commentBox,function(){
	        var _ts = $(this),
	            $commentBox = _ts.parents(".am-ipt-mod"),
	            $comment_p = $commentBox.find(".comment-placeholder"),
	            $comment_b = $commentBox.find(".am-cmtBtn-block");
	
	        //占位符效果
	        $comment_p.stop().animate({opacity : '0'},200,function(){
	            $(this).hide();
	        });
	        //输入框效果
	        _ts.stop().animate({height : u_height + "px"},100,function(){
	            $comment_b.show();
	        });
	
	        //点击收缩
	        $(document).bind("click",function(e){ 
	            var target = $(e.target); 
	            //event.stopPropagation();
	            //判断输入框是否已经输入文字
	            if(target.closest($commentBox).length == 0){ 
	                if($commentBox.val()==""){
	                    $comment_p.stop().animate({opacity : '1'},200,function(){
	                        $(this).show();
	                    });
	                    _ts.stop().animate({height : s_height + "px"},100,function(){
	                        $comment_b.hide();
	                    });
	                }
	            } 
	        }); 
	
	    })
    },

     aJumpLayer : function(clickBtn,layer){
     	 var width = layer.innerWidth(),
         height = layer.innerHeight();
     	 
         layer.show().css({'margin-top':-height/2+'px','margin-left':-width/2+'px'});
         $('.m-blackbg').show().css("opacity","0.5"); 
         
         
        layer.find('.au-confirm-btn').bind('click',function(){
            layer.hide();
            $('.m-blackbg').hide().css("opacity","1");
        })
        layer.find('.au-cancel-btn').bind('click',function(){
            layer.hide();
            $('.m-blackbg').hide().css("opacity","1");
        })
        layer.find('.au-close-btn').bind('click',function(){
            layer.hide();
            $('.m-blackbg').hide().css("opacity","1");
        })
     },
	
      publishArgue : function(){
       
            //补充说明模块
      

        var $pb_parent = $(".ag-pbArgue-lst");
            pb_mod = ".am-pb-mod",
            pb_vp_mod = ".am-pb-mod-vp",
            pb_ep_mod = ".am-pb-mod-ep",
            add_ep_btn = ".au-add-ep",
            add_vp_btn = ".au-add-vp",
            dlt_vp_btn = ".au-delete-vp",
            dlt_ep_btn = ".au-delete-ep",
            tt_txt = ".c-txt",
            vptArr = ["正","反","甲","乙","丙","丁"];
        
        //添加补充说明模块
        $pb_parent.on("click",add_ep_btn,function(){
            var _ts = $(this),
                $pb_mod = _ts.parents(pb_mod);
	          var exBox = '<div class="am-pb-mod am-pb-mod-ep">'+
	                '<div class="c-center">'+
	                    '<div class="m-ipt-mod">'+
	                        '<textarea name="arguments[' + _ts.next().text() +'].supplementExplanation" id="arguse_'+_ts.next().text()+'" class="u-textarea {byteMaxLength:200}" placeholder="补充说明，（选填）"></textarea>'+
	                    '</div>'+
	                '</div>'+
	                '<div class="c-side">'+
	                    '<a href="javascript:void(0);" class="u-nbtn au-delete-ep">×删除</a>'+
	                '</div>'+
	            '</div>';
            //判断是否添加了补充说明
            $pb_mod.after(exBox);
            _ts.hide();
            
        })

        //添加观点模块
        $pb_parent.on("click",add_vp_btn,function(){
            var _ts = $(this),
                $pb_mod = _ts.parents(pb_mod),
                $pb_vp_mod = $pb_mod.siblings(pb_vp_mod);
                t_lenght = $pb_vp_mod.length;
            //判断是否已添加观点模块的个数
            if(t_lenght < 4){
                for(i= 0; i < t_lenght+1; i ++){
                	var argumentId = uuid();
                    $pb_vp_mod.eq(i).find(".c-txt span").text(vptArr[i+2] + "方论点：");
                }
                $pb_mod.before('<div class="am-pb-mod am-pb-mod-vp">'+
                                    '<div class="c-txt">'+
                                        '<em>*</em><span>'+ vptArr[t_lenght+2] +'方论点：</span>'+
                                    '</div>'+
                                    '<div class="c-center">'+
                                        '<div class="m-ipt-mod">'+
                                        	'<input type="hidden" name="arguments['+ t_lenght +'].id" value="'+argumentId +'" >'+
                                        	'<input type="hidden" name="arguments['+ t_lenght +'].orderNo" value="'+t_lenght +'" >'+
                                            '<input type="text" name="arguments['+ t_lenght +'].description" class="u-ipt {required:true,byteMaxLength:30}" placeholder="一句话的描述">'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="c-side">'+
                                        '<a href="javascript:void(0);" class="u-nbtn au-add-ep">+补充说明</a>'+
                                        '<span style="display:none">'+ t_lenght +'</span>'+
                                        '<a href="javascript:void(0);" class="u-nbtn au-delete-vp">×删除</a>'+
                                    '</div>'+
                                '</div>');
            }else {
                alert("观点不能超过4个");
            }
            
        })

        //删除补充说明模块
        $pb_parent.on("click",dlt_ep_btn,function(){
            var _ts = $(this),
                $pb_mod = _ts.parents(pb_mod),
                $pb_vp_mod = $pb_mod.prev(pb_vp_mod);

            $pb_mod.detach();
            $pb_vp_mod.find(add_ep_btn).show();
        })

        //删除论点模块
        $pb_parent.on("click",dlt_vp_btn,function(){
            var _ts = $(this),
                $pb_vp_mod = _ts.parents(pb_vp_mod),
                $pb_ep_mod = $pb_vp_mod.next(pb_ep_mod),
                vp_length = $(pb_vp_mod).length;

            //判断观点个数，小于等于2个，这不删除
            if(vp_length > 2) {
            	//将后续相邻模块的数组下标减1
            	$pb_vp_mod.nextAll(".am-pb-mod-vp").each(function(i){
            		var orderNo = $(this).find("input[name$='orderNo']").val();
            		console.log($(this).html());
            		console.log($(this).filter("input[name$='orderNo']").html());
            		$(this).find("input[name$='id']").attr("name","arguments["+(orderNo-1)+"].id");
            		$(this).find("input[name$='orderNo']").attr("name","arguments["+(orderNo-1)+"].orderNo");
            		$(this).find("input[name$='orderNo']").val(orderNo-1);
            		$(this).find("input[name$='description']").attr("name","arguments["+(orderNo-1)+"].description");
            		$(this).find(".c-side span").text(orderNo-1);
            		if($("#arguse_"+orderNo).length>0){
            			$("#arguse_"+orderNo).attr("name","arguments["+(orderNo-1)+"].supplementExplanation");
            		}
            	});
            	
            	$pb_ep_mod.nextAll(".am-pb-mod-ep").each(function(i){
            		console.log(i);
            	});
                $pb_vp_mod.detach();
                $pb_ep_mod.detach();
            }else {
                alert("论点不能少于2个");
            }
            //判断观点个数，设置文字
            for(i = 0; i < vp_length; i++){
                if(vp_length > 3){
                    $(pb_vp_mod).eq(i).find(".c-txt span").text(vptArr[i+2] + "方论点：");  
                }else {
                    $(pb_vp_mod).eq(i).find(".c-txt span").text(vptArr[i] + "方论点：");
                }
                
            }
            
        })

        
    },
     //添加标签
    set_tag : function(){
        var $tag_parents = $(".am-add-tag");
        $tag_parents.each(function(){

            var obj = new Object();

            var _ts = $(this);
                $ipt_parents = _ts.find(".am-tagipt"),
                $ipt = $ipt_parents.find(".u-ipt"),
                $hint_lst = $ipt_parents.find(".l-slt-lst"),
                $add_btn = _ts.find(".au-add-tag"),
                $tag_lst = _ts.find(".am-tag-lst");

            //显示标签提示框  
            activitysJs.functions.tag_hint_show($ipt,$hint_lst);  
            activitysJs.functions.add_tag($ipt,$add_btn,$tag_lst);  
            activitysJs.functions.delete_tag($tag_lst);  
        })
        
        
    },
    //显示标签提示框
    tag_hint_show : function($ipt,$hint_lst){
        //输入框获取焦点
       /* $ipt.on("focus",function(){
            if( !$(this).val()=="" ){

                $hint_lst.show();
                
            }

            //获取键盘时间
           $(this).on("keyup",function(){
                //判断输入文字是，提示框显示
                if( !$(this).val()=="" ){
					$hint_lst.find(".lst").load('${ctx}/label/listLableByName.do','name='+$(this).val(),function(data){
						$hint_lst.find(".lst").empty();
						var htmlStr = '<a href="javascript:void(0);" title="培训备课">培训备课</a>';
						$hint_lst.find(".lst").append(htmlStr);
	                    $hint_lst.show();
					});
                }else {
                    $hint_lst.hide();
                }
           })

        })
*/
    	
    	 $ipt.on("focus",function(){
            if( !$(this).val()=="" ){

                $hint_lst.show();
                
            }

        })
		$ipt.on("keyup",function(){
				//判断输入文字是，提示框显示
				if( !$(this).val()=="" ){
					$.get('/tags','tag.name='+$(this).val(),function(data){
	                  	if(data!=null){
	                  		$hint_lst.find(".lst").empty();
	                  		$("#hTagId").val("");
	                  		$.each(data,function(i,tag){
	                  			$hint_lst.find(".lst").append("<a href='javascript:void(0);' id='"+tag.id+"' title='"+tag.name+"'>"+tag.name+"</a>");
	    					});
		                    $hint_lst.show();
	                  	}else{
	                  		 $hint_lst.hide();
	                  	}
					});

				}else {
					$hint_lst.hide();
				}
			})	

        this.tag_hint_hide($ipt,$hint_lst);
    },
    //关闭标签提示框
    tag_hint_hide : function($ipt,$hint_lst){
        //获取
        $(document).on("click",function(e){
            var target = $(e.target); 
            //判断是否点击的是标签提示框和输入框，如果不是，隐藏标签提示框
            if(target.closest($hint_lst).length == 0 && target.closest($ipt).length == 0){ 

                $hint_lst.hide();
            }

        }); 
        //选择提示框选项关闭提示框
        $hint_lst.on("click","a",function(){

            $hint_lst.hide();
            $ipt.val($(this).text());
            $("#hTagId").val($hint_lst.find(".lst a[title='"+$(this).text()+"']").attr("id"));
        })

    },
    //添加标签
    add_tag : function($ipt,$add_btn,$tag_lst){
        $add_btn.on("click",function(){
            var ss = false;
            var lengths = $tag_lst.children().length;
            //判断输入框是否为空
            if($ipt.val() != ""){
               //遍历标签列表
                for(var i = 0; i < lengths; i++){
                    //如果已有相同标签
                    if($ipt.val() == $tag_lst.children().eq(i).find(".txt").text()){
                        alert("已有相同标签");
                        ss = true;
                    }

                }
                //如果没有相同标签，添加新的标签
                if(!ss){
                	//判断是否选中下拉菜单中的值，如果没有选中则先执行添加标签操作，获取返回的标签ID
                	if($("#hTagId").val()==''){
	                	$.post('/tags','name='+$ipt.val(),function(data){
	                		if(data!=null){
	                			var responseData = data.responseData;
	                			$("#hTagId").val(responseData.id);
	                		}
	                	});
                	}
                    $tag_lst.append(
                        '<li>'+
                            '<span class="txt">'+$ipt.val()+'</span>'+
                             '<input type="hidden" name="tags['+lengths+'].id" value="'+ $("#hTagId").val() + '"/>'+
                            '<a href="javascript:void(0);" class="dlt" title="删除标签">×</a>'+
                        '</li>'
                    );
                    //添加标签后，清除输入框中的文字
                    $ipt.val("");
                    $("#hTagId").val("");
                }
            }
        })

    },
    //删除标签
    delete_tag : function($tag_lst){
        $tag_lst.on("click",".dlt",function(){
            $(this).parent().remove();
            var lengths = $tag_lst.children().length;
            for(var i = 0; i < lengths; i++){
            	 $tag_lst.children().eq(i).find("input[name^='tags']").attr("name","tags['"+i+"'].id");
            }
        })
    },
    

	/*---------------------------- 备课js   ----------------------------*/
    prepare_lessons : function(){

        //备课增加文件夹功能
   //     this.prepare_add_folder();
        
        //备课操作功能
        this.prepare_lessons_opa();
        //备课文件详情重命名
        this.alter_filename();
        //修改和添加文件历史版本备注
        this.alter_edition_remark();

    },
    //备课操作功能
    prepare_lessons_opa : function(){
        //备课文件重命名
        this.prepare_file_rename();  
        //显示隐藏文件夹操作功能
        this.show_file_opa();
    },
    //显示隐藏文件夹操作功能
    show_file_opa : function(hovers){   


        $(".am-file-lst").children().hover(function(){

            if($(this).find(".rename-box").length == 0){
                
                $(this).find(".f-opa").show();
                $(this).find(".f-info").hide();
            }

            if($(this).find(".rename-box").is(":hidden")){

                $(this).find(".f-opa").show();
                $(this).find(".f-info").hide();
            }

            
        },function(){

            $(this).find(".f-opa").hide();

            if($(this).find(".rename-box").is(":hidden")){

                $(this).find(".f-info").show();

            }else {

                $(this).find(".f-info").hide();
            }
            
        })
        
    },
	//备课增加文件夹
	prepare_add_folder : function(){
		var $add_folder_btn = $(".ua-add-folder");
        var $file_lst_par = $(".am-file-lst");
        var onoff = true;
        $add_folder_btn.on("click",function(){
            //判断是否正在创建文件夹
            if(onoff){  
                onoff = false;
                //增加新文件夹
                $file_lst_par.prepend(
                    '<li>'+
                        '<div class="am-file-block am-file-folder">'+
                            '<div class="file-view">'+
                                '<div class="ua-folder"></div>'+
                            '</div>'+
                            '<div class="add-rename-box" style="display: block">'+
                                '<input type="text" value="" class="rename-ipt">'+
                                '<div>'+
                                    '<a onclick="saveFolderOrRename(this)">创建</a>'+
                                    '<a class="cancel">取消</a>'+
                                '</div>'+
                            '</div>'+
                        '</div>'+
                    '</li>'
                );
                //设置新建的文件夹
                $file_lst_par.children().each(function(){
                    var _ts = $(this),
                        $add_rename_box = _ts.find(".add-rename-box"),
                        $rename_ipt = $add_rename_box.find(".rename-ipt"),
                //        $found_btn = $add_rename_box.find(".confirm"),
                        $cancel_btn = $add_rename_box.find(".cancel");
                    //输入框获取焦点和默认文件夹名字
                    $rename_ipt.focus().val("新建文件夹");
                    //取消创建文件夹
                    $cancel_btn.on("click",function(){
                        _ts.remove();
                        onoff = true;
                    })
                    //创建文件夹
                    $found_btn.on("click",function(){
                        onoff = true;
                        var $rename_box = $(this).parents(".add-rename-box");
                        $rename_box.hide();
                        $rename_box.before(
                            '<b class="f-name">'+$rename_ipt.val()+'</b>'+
                            '<div class="f-opa">'+
                                '<a href="javascript:void(0);" class="download">下载</a>'+
                                '<a href="javascript:void(0);" class="move">移动</a>'+
                                '<a href="javascript:void(0);" class="rename">重命名</a>'+
                                '<a href="javascript:void(0);" class="delete">删除</a>'+
                            '</div>'+
                            '<div class="rename-box">'+
                                '<input type="text" value="" class="rename-ipt">'+
                                '<div>'+
                                    '<a href="javascript:void(0);" class="confirm">确定</a>'+
                                    '<a href="javascript:void(0);" class="cancel">取消</a>'+
                                '</div>'+
                            '</div>'
                        );
                        $rename_box.prevAll(".f-opa").show();

                         activitysJs.functions.prepare_lessons_opa();  

                    });
                });    

            }else {
                //如果正在创建文件夹，让创建文件夹输入框获取焦点
                $file_lst_par.children().eq(0).find(".rename-ipt").focus();

            }
          

            
        })

	},
	
	//备课文件重命名
	prepare_file_rename : function(){
		var $file_name_par = $(".am-file-block");
		$file_name_par.each(function(){
			var _ts = $(this);
			var $rename = _ts.find(".rename"); //重命名按钮
            var $rename_box = _ts.find(".rename-box"); //重命名模块
            var $rename_ipt = _ts.find(".rename-ipt"); //重命名输入框
            var $rename_confirm = _ts.find(".confirm"); //确定按钮
			var $rename_cancel = _ts.find(".cancel"); //取消按钮
			var $file_name = _ts.find(".f-name"); //文件名字
            var $file_info = _ts.find(".f-info"); //重命名按钮
			var $file_opa = _ts.find(".f-opa"); //操作模块

            /*
            获取文件名和后缀名
            //方法一
            var strFileName=file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
            var FileExt=file.replace(/.+\./,"");
            //方法二
            var pos= $file_name.text().lastIndexOf("\\");
            var names = $file_name.text().substring(pos+1);//文件名
            */

            //点击重命名按钮执行操作
			/*$rename.on("click",function(){
                $rename_box.show();
                $file_name.hide();
                $(this).parent().hide();
                var oldFileName = $file_name.text().trim();
                var index =oldFileName.lastIndexOf('.');
                var fileName =oldFileName.substring(0,index);
                $rename_ipt.val(fileName).focus();
            })*/
            //失去焦点时，
          /*  $rename_ipt.on("blur",function(){
                $rename_box.hide();
                $file_name.show().text($rename_ipt.val());
                $file_opa.show();
            })*/
            //确定保存名字
         /*   $rename_confirm.on("click",function(){
                $rename_box.hide();
                $file_name.show().text($rename_ipt.val());
                $file_opa.show();
            })*/
            //取消重命名
            $rename_cancel.on("click",function(){
                $rename_box.hide();
                $file_name.show();
                $file_opa.show();
                
            })
			
		})
	},
	 	//关闭发表框并清除发表框的数据
 	closeTextarea : function closeTextarea(commentBox){
 		var comment_p = commentBox.children(".comment-placeholder");
        var comment_t = commentBox.children(".u-textarea");
        var comment_b = commentBox.children(".am-cmtBtn-block");
        comment_t.val('');
        comment_p.stop().animate({opacity : '1'},200,function(){
 			comment_p.show();
         });
 		comment_t.stop().animate({height :22+"px"},100,function(){
 			comment_b.hide();
         });
 	},
 	 //打开绝对定位弹出框
    openABlayer : function(){

        var $layersLayout = $(".ab-layers");
        var $whiteBG = $(".m-whitebg");

        $layersLayout.show();
        $whiteBG.show();

        this.closeABlayer();

    },
    
    //关闭绝对定位弹出框
    closeABlayer : function(){

        var $layersLayout = $(".ab-layers");
        var $whiteBG = $(".m-whitebg");
        var $colseBtn = $layersLayout.find(".au-closelayer-ico");

        $colseBtn.on("click",function(){
            $layersLayout.hide();
            $whiteBG.hide();
             $('#dialogDiv').empty();
        })
    },

    //文件详情重命名
    alter_filename : function(){

        var $pars = $(".au-file-tt"); //大模块
        
        $pars.each(function(){
            var _ts = $(this), 
                $alter_btn = _ts.find(".ua-edit"), //修改按钮
                $tt = _ts.find(".tt"), //标题
                $alter_box = _ts.find(".am-rename-box"), //修改输入模块
                $alter_ipt = $alter_box.find(".rename-ipt"); //修改输入框

            //显示修改框
            $alter_btn.on("click",function(){
                //隐藏文件名和修改按钮
                $(this).hide();
                $tt.hide();
                //显示修改框
                $alter_box.show();
                //获取文件名
                $alter_ipt.val($tt.text());

                //取消文件详情重命名
                activitysJs.functions.cancel_alter_filename(
                    $alter_btn,
                    $tt,
                    $alter_box
                );
                //确定修改文件详情重命名
                activitysJs.functions.confirm_alter_filename(
                    $alter_btn,
                    $tt,
                    $alter_box,
                    $alter_ipt
                );

            });

        });
    },

    //取消文件详情重命名
    cancel_alter_filename : function($alter_btn,$tt,$alter_box){

        $alter_box.find(".cancel").on("click",function(){
            //取消
            $alter_box.hide();
            $alter_btn.show();
            $tt.show();
        })

    },
    //确定修改文件详情重命名
    confirm_alter_filename : function($alter_btn,$tt,$alter_box,$alter_ipt){

        $alter_box.find(".confirm").on("click",function(){
            //保存文件名并关闭
            $tt.show().text($alter_ipt.val());
            $alter_btn.show();
            $alter_box.hide();
            
        })

    },


    //修改和添加文件历史版本备注
    alter_edition_remark : function(){

        var $pars = $(".edition-lst li"); //大模块
        
        $pars.each(function(){
            var _ts = $(this), 
                $alter_btn = _ts.find(".au-alter-btn"), //修改按钮
                $tt = _ts.find(".explain"), //标题
                $alter_box = _ts.find(".am-textarea"), //修改输入模块
                $alter_ipt = $alter_box.find(".u-textarea"); //修改输入框

            //显示修改框
            $alter_btn.on("click",function(){
                //隐藏文件名和修改按钮
                $(this).hide();
                $tt.hide();
                //显示修改框
                $alter_box.show();
                //获取文件名
                $alter_ipt.val($tt.text());

                //取消文件详情重命名
                activitysJs.functions.cancel_alter_filename(
                    $alter_btn,
                    $tt,
                    $alter_box
                );
                //确定修改文件详情重命名
                activitysJs.functions.confirm_alter_filename(
                    $alter_btn,
                    $tt,
                    $alter_box,
                    $alter_ipt
                );

            });
        })
    }





















};


/*--start----多个同类名选项卡----start---*/
$.fn.extend({
    myTab : function(options)
    {
        var defaults = 
        {
            pars    : '.myTab',   //最外层父级
            tabNav  : '.tabNav',  //标签导航
            li      : 'li',       //标签
            tabCon  : '.tabCon',  //区域父级
            tabList : '.tabList', //t区域模块
            cur     : 'cur',      //选中类
            eType   : 'click',    //事件
            page    : 0 //默认显示第几个模块
        }
        var options = $.extend(defaults,options),
        _ts = $(this),
        tabBtn  = _ts.find(options.tabNav).find(options.li);
        tabList  = _ts.find(options.tabCon).find(options.tabList);
        this.each(function(){
            tabBtn.removeClass(options.cur);
            tabBtn.eq(options.page).addClass(options.cur);
            tabList.hide();
            tabList.eq(options.page).show();
            tabBtn.eq(options.page).show();
            tabBtn.on(options.eType,function(){
                var index = $(this).parents(options.tabNav).find(options.li).index(this);
                $(this).addClass(options.cur).siblings().removeClass(options.cur);
                $(this).parents(options.pars).find(options.tabCon).find(options.tabList).eq(index).css({'display':'block'}).siblings().css({'display':'none'});
            })
        })
        return this;
    }
})
/*--end-----多个同类名选项卡---end---*/