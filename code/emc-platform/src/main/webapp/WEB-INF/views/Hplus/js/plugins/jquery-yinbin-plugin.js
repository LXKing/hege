/**
 * @author yinbin
 * @remarks 此js为jquery扩展自定义插件
 * 依赖开发jquery版本2.1.4
 * 
 */

 function getData(url){
    	var da='';
    	 $.ajax({
				url : url,
				type : "post",
				async : false,
				dataType:'json',
				success : function(data) {
					da = data;
				}
			});
    	 return da;
    }
    
 //jquery form方法
 //用途：对form表单的各种操作
 //用法：参数1 类型，参数二：url（后台地址）
 //持续扩展
/*    $.fn.form = function (type,url){
    	//获取后台数据并填充到form表单
    	//要求 form input name 要求全部大写或小写
    	if(type=='load'){
			 var jsonValue = getData(url);
			 var obj = this;
			  $.each(jsonValue, function (name, ival) {
		   	    	var $oinput = obj.find("input[name=" + name.toUpperCase() + "]"); 
		   	    	console.info( name.toLowerCase());
		   	    	if($oinput.attr("type") == undefined){
		   	    		$oinput = obj.find("input[name=" + name.toLowerCase() + "]");
		   	    	}
		   	    	if ($oinput.attr("type")== "radio" || $oinput.attr("type")== "checkbox"){
		   	    		 $oinput.each(function(){
		   	    			 if(Object.prototype.toString.apply(ival) == '[object Array]'){//是复选框，并且是数组
		   	                      for(var i=0;i<ival.length;i++){
		   	                          if($(this).val()==ival[i])
		   	                             $(this).attr("checked", "checked");
		   	                      }
		   	    	 		 }else{
		   	                     if($(this).val()==ival)
		   	                        $(this).attr("checked", "checked");
		   	                 }
		   	             });
		   	    	}else if($oinput.attr("type")== "textarea"){//多行文本框
		   	    		$oinput.html(ival);
		   	    	}else{
		   	    		$oinput.val(ival); 
		   	        }
		   	   });
		  }
    }*/
    
    
    
    $.fn.form = function (type,url){
    	//获取后台数据并填充到form表单
    	//要求 form input name 要求全部大写或小写
    	if(type=='load'){
			 var jsonValue = getData(url);
			
			 var $oinput = this.find("input,select,textarea"); 
			 $oinput.each(function(){
				 var ival = jsonValue[this.name.toUpperCase()];
//				 /this.value=ival;
				 
				 if (this.type== "radio" || this.type== "checkbox"){
	   	    		
	   	    			 if(Object.prototype.toString.apply(ival) == '[object Array]'){//是复选框，并且是数组
	   	                      for(var i=0;i<ival.length;i++){
	   	                          if($(this).val()==ival[i])
	   	                             $(this).attr("checked", "checked");
	   	                      }
	   	    	 		 }else{	   	    	 			
	   	                     if($(this).val()==ival){
	   	                        $(this).attr("checked", "checked");
	   	                     }
	   	                }
	   	    	}else if(this.type== "textarea"){//多行文本框
	   	    	 $(this).html(ival);
	   	    	}else{
	   	    		this.value=ival; 
	   	        }
			 });
		  }
    }
    
    
    //重写contains 不区分大小写
    jQuery.expr[':'].contains = function(a, i, m){
    	 return jQuery(a).text().toUpperCase()
    	   .indexOf(m[3].toUpperCase()) >= 0;
    	};  
    	  
    	jQuery.expr[':'].contains = function(a, i, m){
    	 return jQuery(a).text().toUpperCase()
    	   .indexOf(m[3].toUpperCase()) >= 0;
    	};