<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-touch-fullscreen" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no">
<meta name="keywords" content="飞券" />
<meta name="description" content="飞券" />
<title>飞券</title>
<link rel="stylesheet" type="text/css" href="/css/app/sellerTask.css">
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/wap/sellerTask.js"></script>
</head>
<body>
<div class="global">
	<div class="top1"><img src="${task.images }"></div>
  <div class="module">
  	<div class="login">
    	  <form action="" method="POST" id="sellerTask" name="sellerTask" onSubmit="return false;">
           <input type="hidden" id="userId" name="userId" value="${userId }">
           <input type="hidden" id="sellerId" name="sellerId" value="${task.project}">
            <label class="loginbk"><input name="sellerPass"  type="text" id ="sellerPass" class="inputname inputtext" value="请输入商户推荐码"></label>
            <label class="loginbk"><input name="" type="button" class="loginbutton" id="stbut" onclick="submit1();" value="马上参与"></label>                  
   	  </form>      
    </div>
  </div> 
</div>
</body>
</html>