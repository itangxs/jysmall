<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<% 
if(null!=request.getHeader("user-agent")&&((request.getHeader("user-agent").toLowerCase().indexOf("iphone")!=-1 || request.getHeader("user-agent").toLowerCase().indexOf("android")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("ipad")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("linux")!=-1))) 
{ 

}else{

	return ;
}  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<title>我的订单列表</title>
<link href="/css/zhifu.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/common/js/jquery.min.js"></script>
<script type="text/javascript" src="/common/js/jquery-ui.js"></script>

</head>

<body>
<div class="wrapper">

	
	<c:forEach items="${list}" var="ob">
		<c:if test="${ob.status==1 }">
		  <!--支付尾款-->
		  <div class="orderlistbox">
		  	<div class="left">
		    	<p class="h25"><a href="/wxMall/fqStoreDetail.do?id=${ob.storeId}" >${ob.storeName}</a></p>
		        <p class="fgray">下单时间：<fmt:formatDate value="${ob.createTime}" pattern="yyyy-MM-dd HH:mm"/> </p>
		    </div>
		    <div class="right">
		        <p><span class="f30red">¥${ob.totalAmount}</span></p>
		        <p><a href="/user/fqorder/toOrderDetailWk.do?orderId=${ob.id }" class="buttonred">支付尾款</a></p>
		    </div>
		  </div>
		 </c:if>
		<c:if test="${ob.status==0 }">
		  <!--支付尾款-->
		  <div class="orderlistbox">
		  	<div class="left">
		    	<p class="h25"><a href="/wxMall/fqStoreDetail.do?id=${ob.storeId}" >${ob.storeName}</a></p>
		        <p class="fgray">下单时间：<fmt:formatDate value="${ob.createTime}" pattern="yyyy-MM-dd HH:mm"/> </p>
		    </div>
		    <div class="right">
		        <p><span class="f30red">¥${ob.totalAmount}</span></p>
		        <p><a href="/user/fqorder/toOrderDetail.do?orderId=${ob.id }" class="buttonred">去支付</a></p>
		    </div>
		  </div>
		 </c:if>
		  <c:if test="${ob.status==2}">
		 <!--已完成支付订单-->
		  <div class="orderlistbox">
		  	<div class="left">
		    	<p class="h25"><a href="/wxMall/fqStoreDetail.do?id=${ob.storeId}" >${ob.storeName}</a></p>
		        <p class="fgray">下单时间：<fmt:formatDate value="${ob.createTime}" pattern="yyyy-MM-dd HH:mm"/></p>
		    </div>
		    <div class="right">
		    	<p class="fgray"><span class="f20">已支付</span><span class="f30">¥${ob.totalAmount}</span></p>
		        <p><a href="/wxMall/fqorderDetail.do?id=${ob.id}&openId=${openId}" class="buttonred">查看详情</a></p>
		    </div>
		  </div>
		  </c:if>
		  <c:if test="${ob.status==3}">
		 <!--   已消费支付订单-->
			  <div class="orderlistbox">
			  	<div class="left">
			    	<p class="h25"><a href="/wxMall/fqStoreDetail.do?id=${ob.storeId}">${ob.storeName}</a></p>
			        <p class="fgray">下单时间：<fmt:formatDate value="${ob.createTime}" pattern="yyyy-MM-dd HH:mm"/></p>
			    </div>
			    <div class="right">
			    	<p class="fgray"><span class="f20">已消费</span><span class="f30">¥${ob.totalAmount}</span></p>
			        <p><a href="/wxMall/fqorderDetail.do?id=${ob.id}&openId=${openId}" class="buttongray">查看详情</a></p>
			    </div>
			  </div>
		   </c:if>
  </c:forEach>
  <div id="content"> </div>
</div>

  	<input id="indexPage" type="hidden" value="${page}">
    <input id="openId" type="hidden" value="${openId}">
    
  <c:if test="${empty list }">
  		<div class="loadbox">暂时没有数据</div>  
  </c:if>
	<c:if test="${!empty list }">
	  <div id="dianji" align="center" class="loadbox">
	  	<a href="javascript:ajaxMore()">点击加载更多</a>
	  </div>
	  
	  <div id="jiazai" align="center"  class="loadbox" style="display: none;">
	 	 	<img src="/images/weixin/more.gif" style="">
	 	 	<br>
	 	 	正在加载....
  	  </div>
	  
   </c:if>
</body>

<script type="text/javascript">
	function ajaxMore(){
		//获取页数
		var indexPage = document.getElementById('indexPage');
		var openId = document.getElementById('openId').value;
		var pageno = indexPage.value;
		var page =  parseInt(pageno)+1;
		
		var param = "page="+page+"&openId="+openId;
		var urlval = '/wxMall/ajaxmyfqorder.do?'+param;
		var dianji = document.getElementById('dianji');
		var jiazai = document.getElementById('jiazai');
		
		$("#dianji").css('display','none');
		$("#jiazai").css('display','block');
		$.ajax({
	    	type:'post',//可选get
	    	url:urlval,//这里是接收数据的后台程序
	    	//data:param,//传给后台的数据，多个参数用&连接
	    	dataType:'',//服务器返回的数据类型 可选xml, json, script, or html,text等,建议还回json
	    	success:function(object){
	    		var list = object;
	    		/* var page = object.page; */
    			if(list.length<1){
    				alert("对不起,没有数据了.");	
    			}
				if(list.length>0){
		    			for(var i =0;i<list.length;i++){
			    			var ob = list[i];
			    			var html = '';
			    			var time2 = new Date(ob.createTime).Format("yyyy-MM-dd hh:mm");  
			    			if(ob.status==0){
			    				html='<div class="orderlistbox">'+
			    				  	'<div class="left">'+
				    		    	'<p class="h25"><a href="/wxMall/fqStoreDetail.do?id='+ob.storeId+'" >'+ob.storeName+'</a></p>'+
				    		        '<p class="fgray">下单时间：'+time2+'</p>'+
					    		    '</div>'+
					    		    '<div class="right">'+
			    		        	'<p><span class="f30red">¥'+ob.totalAmount+'</span></p>'+
			    		        	'<p><a href="payretainage.html" class="buttonred">支付尾款</a></p>'+
			    		   			 '</div>'+
			    		 			' </div>';
			    			}
			    			if(ob.status==1){
			    				html='<div class="orderlistbox">'+
			    			  	'<div class="left">'+
			    			    	'<p class="h25"><a href="/wxMall/fqStoreDetail.do?id='+ob.storeId+'" >'+ob.storeName+'</a></p>'+
			    			       ' <p class="fgray">下单时间：'+time2+'</p>'+
			    			    '</div>'+
			    			    '<div class="right">'+
			    			    	'<p class="fgray"><span class="f20">已支付</span><span class="f30">¥'+ob.totalAmount+'</span></p>'+
			    			       ' <p><a href="/wxMall/fqorderDetail.do?id='+ob.id+'&openId='+openId+'" class="buttongray">查看详情</a></p>'+
			    			   ' </div>'+
			    			 ' </div>';
			    			}
			    			
			    			if(ob.status==2){
			    				html='<div class="orderlistbox">'+
			    			  '	<div class="left">'+
			    			    	'<p class="h25"><a href="/wxMall/fqStoreDetail.do?id='+ob.storeId+'">'+ob.storeName+'</a></p>'+
			    			      '  <p class="fgray">下单时间：'+time2+'</p>'+
			    			   ' </div>'+
			    			   ' <div class="right">'+
			    			    '	<p class="fgray"><span class="f20">已消费</span><span class="f30">¥'+ob.totalAmount+'</span></p>'+
			    			       ' <p><a href="/wxMall/fqorderDetail.do?id='+ob.id+'&openId='+openId+'" class="buttongray">查看详情</a></p>'+
			    			   ' </div>'+
			    			 ' </div>';
			    			}
			              
			              
			              
			              
			    	        $("#content").append(html); 
			    		} 
					}
	    		indexPage.value = page;
	    		$("#dianji").css('display','block');
	    		$("#jiazai").css('display','none');
	    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(){
	    	//ajax提交失败的处理函数！
	    		$("#dianji").css('display','block');
	    		$("#jiazai").css('display','none');
	    		alert("服务器忙...");
	    	}
	    });
	}
	
	
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
</script>


</html>
