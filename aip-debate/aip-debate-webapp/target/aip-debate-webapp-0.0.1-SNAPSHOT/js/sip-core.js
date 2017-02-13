String.prototype.trim= function(){  
    return this.replace(/(^\s*)|(\s*$)/g, "");  
};
$.extend({
	ajaxQuery:function(formId,divId,callback){
		$.ajax({
			url:$("#"+formId).attr("action"),
			data:$("#"+formId).serialize(),
			type:"get",
			success:function(data){
				$("#"+divId).html(data);
				if(callback!=undefined){
					var $callback = callback;
					if (! $.isFunction($callback)) $callback = eval('(' + callback + ')');
					$callback();
				}
			}
		});		
		
	},
	ajaxSubmit:function(formId){
		var rData = $.ajax({
			url:$("#"+formId).attr("action"),
			data:$("#"+formId).serialize(),
			type:"post",
			async:false,
			success:function(data){
				
			}
		}).responseText;			
		return rData;
	}
});

function assignParam(formId,objectId){
	$.each($('#'+formId+' :input'),function(){
		$(this).val($('#'+$(this).attr('id')+'_'+objectId).text());
	});
}

function checkAllBox(id){
	if($("#"+id).prop("checked")){
		$(":checkbox").each(function(){
			$(this).prop("checked",true);			
		});
	}else{
		$(":checkbox").each(function(){
			$(this).prop("checked",false);			
		});
	}
}

function validateCheckbox(msg){
	var isOk = false;
	$(":checkbox").each(function(){
		if($(this).prop("checked")==true){
			isOk = true;
			return isOk;
		}			
	});
	if(!isOk){
		bootbox.alert(msg);	
	}
	return isOk;
}

//txt:文本框jquery对象
//limit:限制的字数
//isbyte:true:视limit为字节数；false:视limit为字符数
//cb：回调函数，参数为可输入的字数
function initLimit(txt,limit,isbyte,cb){
	txt.keyup(function(){
		var str=txt.val();
		var charLen;
		var byteLen=0;
		if(isbyte){
			for(var i=0;i<str.length;i++){
				if(str.charCodeAt(i)>255){
					byteLen+=2;
				}else{
					byteLen++;
				}
			}
			charLen = Math.floor((limit-byteLen)/2);
		}else{
			byteLen=str.length;
			charLen=limit-byteLen;
		}
		cb(charLen);
	});	
}

function uuid(){
	var guid = (G() + G() + "" + G() + "" + G() + "" + 
			G() + "" + G() + G() + G()).toLowerCase();
	return guid;
}
function G() {
	return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
}

function getByteLength(value){
	var length = value.trim().length; 
    for(var i = 0; i < value.length; i++){      
        if(value.charCodeAt(i) > 127){      
        	length++;      
        }      
    }
    return length;
}

function getSuffix(fileName){
	var index = fileName.lastIndexOf(".");
	return fileName.substring(index+1,fileName.length);
}
