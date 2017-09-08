<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
	<link href="/css/public.css" rel="stylesheet" type="text/css" />
	<link href="/css/taskjindu.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/js/account/selleruser.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
	<div class="taskjindubox">	
	<form id="selleruserform3" action="/account/task/selleruser3.do" method="post">
		<div class="taskjindubox1">
	    	<div class="taskjindutop"></div>        
	        <div class="taskjindunr">
	        	<!--进度条-->  
	           <div class="jindu jd34"><i class="text40">34</i><em class="text25">%</em></div>
	            <!--问卷-->
	             <input type="hidden" name="userId" value="${user.id }">
	             <input type="hidden" name="ispc" value="${ispc }">
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
	        </div>
	   	  <div class="taskjindubottom"></div>
	       <div><input class="nextanniu" type="button" value="下一题" onclick="submit3();"/></div>
	    </div> 
	    </form>   
	</div>
	<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>