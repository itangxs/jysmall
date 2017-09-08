 /**
  * cookie 操作公用js
  */
 var cookie ={
	 /**
	  * 添加cookie
	  */
	 addCookie:function(name,value,expires,path,domain){
		 var str=name+"="+escape(value);
		 if(expires!=""){
		  var date=new Date();
		  date.setTime(date.getTime()+expires*24*3600*1000);//expires单位为天
		  str+=";expires="+date.toGMTString();
		 }
		 if(path!=""){
		  str+=";path="+path;//指定可访问cookie的目录
		 }
		 if(domain!=""){
		  str+=";domain="+domain;//指定可访问cookie的域
		 }
		 document.cookie=str;
	 },
	 /**
	  * 得到cookie
	  */
	 getCookie:function(name){
		 var str=document.cookie.split(";");
		 for(var i=0;i<str.length;i++){
		 var str2=str[i].split("=");
		  if(str2[0]==name)return unescape(str2[1]);
		 }
	 },
	 /**
	  *删除cookie
	  */
	 delCookie:function(name){
		 var date=new Date();
		 date.setTime(date.getTime()-10000);
		 document.cookie=name+"=n;expire="+date.toGMTString();
	 }
 };

