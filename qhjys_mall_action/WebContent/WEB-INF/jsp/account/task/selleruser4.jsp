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
<form id="selleruserform4" action="/account/task/selleruser4.do" method="post">
	<div class="taskjindubox1">
    	<div class="taskjindutop"></div>        
        <div class="taskjindunr">
        	<!--进度条-->  
            <div class="jindu jd50"><i class="text40">50</i><em class="text25">%</em></div>
            <!--问卷-->
             <input type="hidden" name="userId" value="${user.id }">
             <input type="hidden" name="ispc" value="${ispc }">
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
        </div>
   	  <div class="taskjindubottom"></div>
        <div><input class="nextanniu" type="button" value="下一题" onclick="submit4();"/></div>
    </div> </form>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>