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
<form id="selleruserform4" action="/account/task/selleruser4.do" method="post">
	<!--进度条-->  
	<div class="jindu jd50"><i class="text40">50</i><em class="text25">%</em></div>
	<div class="taskjindubox1">       
        <div class="taskjindunr">
        	<input type="hidden" name="userId" value="${user.id }">
            <!--问卷-->
            <div class="xuanbox">
            	<p>兴趣：</p>
            	<ul>                	
                	<li>                    	
                        <label class="marginbottom10"><input name="interest" type="checkbox" value="1" />读书</label>
                        <label class="marginbottom10"><input name="interest" type="checkbox" value="2" />逛街</label>
                        <label class="marginbottom10"><input name="interest" type="checkbox" value="3" />音乐</label>
                        <label class="marginbottom10"><input name="interest" type="checkbox" value="4" />旅游</label>
                    </li>
                    <li>
                        <label class="marginbottom10"><input name="interest" type="checkbox" value="5" />聚会</label>
                        <label class="marginbottom10"><input name="interest" type="checkbox" value="6" />游戏</label>
                        <label class="marginbottom10"><input name="interest" type="checkbox" value="7" />电影</label>
                        <label class="marginbottom10"><input name="interest" type="checkbox" value="8" />户外运动</label>
                    </li>
                    <div class="clear"></div>
                </ul>
                
            </div>
            <div class="clear"></div>
        </div>
        <div class="centerdiv"><input class="nextanniu" type="button" value="下一题" onclick="submit4();" /></div>
    </div>
    </form>
</div>

</body>
</html>
