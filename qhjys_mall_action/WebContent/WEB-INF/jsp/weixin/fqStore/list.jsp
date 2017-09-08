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
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<title>商家列表</title>
<link href="/css/fqzhifu.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="/common/js/jquery.min.js"></script>
<script type="text/javascript" src="/common/js/jquery-ui.js"></script>

<script type="text/javascript">
window.onload = function ()
{
	var indexPage = document.getElementById('indexPage');
	indexPage.value=1;
var oSelect = document.getElementsByTagName("span")[0];
var oSub = document.getElementsByTagName("ul")[0];
var aLi = oSub.getElementsByTagName("li");
var i = 0;
oSelect.onclick = function (event)
{
var style = oSub.style;
style.display = style.display == "block" ? "none" : "block";
//阻止事件冒泡
(event || window.event).cancelBubble = true
};
for (i = 0; i < aLi.length; i++)
{
//鼠标划过
aLi[i].onmouseover = function ()
{
this.className = "hover"
};
//鼠标离开
aLi[i].onmouseout = function ()
{
this.className = "";
};
//鼠标点击
aLi[i].onclick = function ()
{
oSelect.innerHTML = this.innerHTML
}
}
document.onclick = function ()
{
oSub.style.display = "none";
};
};
    </script>
</head>

<body>
<div class="wrapper">
  <div class="index_img">
  	<a href="/wxMall/fqStoreList.do">
 		 <img src="/images/banner_wxzf.jpg">
 	 </a>
  </div>
  <!--搜索-->
  <div id="search">
    <div class="box">
    <form>
    <span class="select">
    <c:if test="${empty lid}">
    	商圈选择
    </c:if>	
    <c:if test="${!empty lid}">
    	<c:forEach items="${fqLocations}" var="fqlocation">
      		<c:if test="${fqlocation.id==lid}">
      			${fqlocation.location}
      		</c:if>
        </c:forEach>
    </c:if>	
   </span>
    </form>
    </div>    
    <ul class="sub">
    	<c:forEach items="${fqLocations}" var="fqlocation">
      	 <a href="/wxMall/fqStoreList.do?lid=${fqlocation.id}&page=1"><li>${fqlocation.location}</li></a>
        </c:forEach>
    </ul>
    </div>
    <!--end-->
  <div class="probox">
  	<ul>
  	
  	 
  	<c:forEach items="${stores}" var="store" > 
    	<li>
        	<div class="proimg"><a href="/wxMall/fqStoreDetail.do?id=${store.id}"><img  src="${store.storeLogo}"></a></div>
    		<div class="centerinfo">
            	<div class="zhekoutext"><c:if test="${store.rebateNum <10}"><b><fmt:formatNumber value="${store.rebateNum}" pattern="#,#0.0#"/></b>折</c:if><c:if test="${store.id != 38 }"><a href="/store/fqproducts/onlineorder.do?storeId=${store.id}" class="buttondd">在线点餐</a></c:if>
            	<c:if test="${empty store.rebateId }">
		          	<p><a class="buttonddgreen" href="javascript:alert('暂无折扣买单')">折扣买单</a></p>
		        </c:if>
           		<c:if test="${not empty store.rebateId }">
            		<p><a class="buttonddgreen" href="/wxMall/storeDetail.do?rebateId=${store.rebateId }">折扣买单</a></p>
            	</c:if>
                <!-- <p><a href="#" class="buttonddgreen">折扣买单</a></p> -->
                </div>
            	<h1><a href="/wxMall/fqStoreDetail.do?id=${store.id}">${store.storeName}</a></h1>
                <h2>${store.address}</h2>
            </div>
            <div class="clear"></div>
        </li>
    </c:forEach>  
    <input id="indexPage" type="hidden" value="${page}">
    <input id="lid" type="hidden" value="${lid}">
      <div id="content">
       </div>
    </ul> 
  </div>
  <div class="loadbox">
  
  <div id="dianji" align="center" class="loadbox">
  	<a href="javascript:ajaxMore()">点击加载更多</a>
  </div>
  <div id="jiazai" align="center"  class="loadbox" style="display: none;">
 	 	<img src="/images/weixin/more.gif" style="">
 	 	<br>
 	 	正在加载....
  </div>
  
  
  </div>  
</div>
</body>


<script type="text/javascript">
	function ajaxMore(){
		//获取页数
		var indexPage = document.getElementById('indexPage');
		var lid = document.getElementById('lid').value;
		var pageno = indexPage.value;
		var page =  parseInt(pageno)+1;
		
		var param = "page="+page+"&lid="+lid;
		var urlval = '/wxMall/fqStoreListAjax.do?'+param;
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
			    			var store = list[i];
			    			var id = store.id;
			    			var storeLogo = store.storeLogo;
			    			var storeRebate = store.rebateNum;
			    			storeRebate = storeRebate.toFixed(1);
			    			var storeName = store.storeName;
			    			var address = store.address;
			    			var buttonddgreen ;
			    			
			    			
			    			if(null==store.rebateId){
			    				var  at ="'暂无折扣买单'";
			    				buttonddgreen ='<p><a class="buttonddgreen" href="javascript:alert('+at+')">折扣买单</a></p>'
			    			};
			    				
			    		
			    			if(null!=store.rebateId){
			    				var stid = store.rebateId;
			    				buttonddgreen = '<p><a class="buttonddgreen" href="/wxMall/storeDetail.do?rebateId='+stid+'">折扣买单</a></p>'
			    			};
			    			var zhekou = "";
			    			if(storeRebate<10){
			    				zhekou = '<b>'+storeRebate+'</b>折';
			    			}
			    			
			    		
			    			var html = '<li><div class="proimg"><a href="/wxMall/fqStoreDetail.do?id='+id+'"><img  src="'+storeLogo+'"></a></div>'+
			        		'<div class="centerinfo">'+
			                '	<div class="zhekoutext">'+zhekou+'<a href="/wxMall/fqStoreDetail.do?id='+id+'" class="buttondd">在线点餐</a>'+buttonddgreen+
			               '</div> 	<h1><a href="/wxMall/fqStoreDetail.do?id='+id+'">'+storeName+'</a></h1>'+
			                ' <h2>'+address+'</h2>'+
			               ' </div>'+
			              ' <div class="clear"></div> </li>';
			    	        $("#content").append(html); 
			    		} 
					}
	    		indexPage.value = page;
	    		if(list.length>0){
	    			$("#dianji").css('display','block');
    			}
	    		
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
</script>
</html>