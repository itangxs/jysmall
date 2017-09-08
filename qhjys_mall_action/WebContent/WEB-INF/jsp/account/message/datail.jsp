<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<link href="/css/account/evaluate/add_page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/account/evaluate/add_page.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="5"/></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
   	    <div class="member_messagedetail">
   	   
        	<div class="title">
            	<h1>${message.title}</h1>
                <p><fmt:formatDate value="${message.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
           </div>
            <div class="detail">
              <p>${message.content}</p>
            </div>
            <div class="fanhui"><a href="javascript:history.go(-1);" class="fontred">[返回]</a></div>
        </div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>
