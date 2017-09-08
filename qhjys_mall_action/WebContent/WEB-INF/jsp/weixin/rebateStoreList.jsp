<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath().equals("/")?"":request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% 
if(null!=request.getHeader("user-agent")&&((request.getHeader("user-agent").toLowerCase().indexOf("iphone")!=-1 || request.getHeader("user-agent").toLowerCase().indexOf("android")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("ipad")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("linux")!=-1))) 
{ 

}else{

	return ;
}  
%> 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no" />
<script src="<%=path %>/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="/js/weixin/rebatestore.js" type="text/javascript"></script>
<script type="text/javascript">
 $(function(){
	 initStoreList();
 });
</script>
<title>飞券网-热门餐厅</title>
<link href="/css/zhifu1.css" type="text/css" rel="stylesheet" />
</head>

<body>
<div class="wrapper">
  <div class="index_img"><img src="<%=path %>/images/banner_wxzf.jpg"></div>
  <input type="hidden" id="pageNum" name="pageNum" value=""/>
  <input type="hidden" id="path" name="path" value="<%=path%>"/>
  <div class="probox">
  	 <ul id="storeList">
    <%--<li>
        	<div class="proimg"><img src="<%=path %>/images/nopic.png"></div>
    		<div class="centerinfo">
            	<div class="zhekoutext"><b>8.8</b>折</div>
            	<h1>好美味烧烤店好美味烧烤店</h1>
                <h2>罗湖区<br> 滨河大道5003号爱地大厦西座 7 楼 D-F</h2>
            </div>
    		<div class="orderbutton"><a href="order.html">去买单</a></div>
            <div class="clear"></div>
    </li> --%>
       <!--  <li>
        	<span class="proimg"><img src="images/nopic.png"></span>
    <span class="centerinfo">
            	<div class="zhekoutext"><b>8.8</b>折</div>
            	<h1>好美味烧烤店</h1>
                <h2>罗湖区<br>
    滨河大道5003号爱地大厦西座 7 楼 D-F</h2>
            </span>
    		<span class="orderbutton"><a href="order.html">去买单</a></span>
            <div class="clear"></div>
        </li> -->
    
    </ul> 
  </div>
  <div class="loadbox"><a href="#" id="getmore" onclik="getNextPage()">加载更多</a></div>  
</div>
</body>
</html>