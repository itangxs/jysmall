<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
	<link href="/css/public.css" rel="stylesheet" type="text/css" />
	<link href="/css/taskjindu.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="taskjindubox">	
	<div class="taskjindubox1">
    	<div class="taskjindutop"></div>        
        <div class="taskjindunr">
        	<!--进度条-->  
            <div class="jindu jd100"><i class="text40">100</i><em class="text25">%</em></div>
            <!--问卷-->       	
            <div class="xuanbox"> 
           	   <div class="qqtext"><strong></strong>
            	<p class="gray">任务完成,等待审核</p>    
               </div>
          </div>
        </div></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>