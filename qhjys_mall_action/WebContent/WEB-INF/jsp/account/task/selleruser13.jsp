<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>金钥匙商城</title>
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<link href="/css/taskjindu1.css" rel="stylesheet" type="text/css" />  
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/account/selleruser.js"></script>  
</head>
<body class="taskjindubg">
<div class="taskjindubox">
	<!--进度条-->  
	<form id="selleruserform3" action="/account/task/selleruser3.do" method="post">
	<div class="jindu jd34"><i class="text40">34</i><em class="text25">%</em></div>
	<div class="taskjindubox1">        
        <div class="taskjindunr">
            <!--问卷-->
              <input type="hidden" name="userId" value="${user.id }">
            <div class="xuanbox">
            	<ul>
                	<li>
                    	<p>身份：</p>
                         <label class="marginbottom10"><input name="identity" type="radio" value="1" />学生</label>
                        <label class="marginbottom10"><input name="identity" type="radio" value="2" />在职</label>
                        <label class="marginbottom10"><input name="identity" type="radio" value="3" />自由职业</label>
                        <label class="marginbottom10"><input name="identity" type="radio" value="4" />其他</label>
                    </li>
                    <li>
                    	<p>个人情况：</p>
                       <label class="marginbottom10"><input name="marital" type="radio" value="1" />单身</label>
                        <label class="marginbottom10"><input name="marital" type="radio" value="2" />恋爱</label>
                        <label class="marginbottom10"><input name="marital" type="radio" value="3" />已婚</label>
                        <label class="marginbottom10"><input name="marital" type="radio" value="4" />保密</label>
                    </li>
                    <div class="clear"></div>
                </ul>
                
            </div>
            <div class="clear"></div>
        </div>
        <div class="centerdiv"><input class="nextanniu" type="button" value="下一题" onclick="submit3();"/></div>
    </div>
    </form>
</div>
</body>
</html>
