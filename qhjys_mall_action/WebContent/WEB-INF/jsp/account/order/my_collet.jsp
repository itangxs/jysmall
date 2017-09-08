<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<link href="/css/member.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/account/order/my_collet.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="3"/></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
		<div class="tabs">
			<a href="/managermall/account/order/myCollet.do?status=1" <c:if test="${status==1 }">class="tabaction"</c:if>>商品收藏</a>
			<a href="/managermall/account/order/myCollet.do?status=0" <c:if test="${status==0 }">class="tabaction"</c:if>>店铺收藏</a>
		</div>
        <div class="member_myorder">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td588">名称</th>
                    <c:if test="${status==1}">
                    <th class="td130">金元宝</th>
                    </c:if>
                    <th class="td80">操作</th>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            	<c:forEach var="page" items="${pageInfo}">
				<tr>
				 <c:if test="${status==1 }">
                    <td class="td588">
                    	<a href="/getProdDetail.do?prodId=${page.prodId}"><img src="${page.prodImg}" /></a>
                    	<p><br>${page.prodName}</p>
                    </td>
                   	<td class="td130"><fm:formatNumber value="${page.price*20}"/></td>
                </c:if>
                <c:if test="${status==0 }">
                    <td class="td588">
                    	<a href="/getStoreDetail.do?storeId=${page.storeId}"><img src="${page.storeImg}" /></a>
                    	<p><br>${page.storeName}</p>
                    </td>
                </c:if>
                
                    <td class="td80">
                    	<c:if test="${status==1 }">
                	    	<p><a href="/getProdDetail.do?prodId=${page.prodId}" class="fontred">购买</a></p>
                	    	  <p><a href="javascript:delet(${page.prodId},1);">删除</a></p>
                	    </c:if>
                	    <c:if test="${status==0 }">
                	    	<p><a href="/getStoreDetail.do?storeId=${page.storeId}" class="fontred">查看</a></p>
                	    	  <p><a href="javascript:delet(${page.storeId},0);">删除</a></p>
                	    </c:if>
                      
                    </td>
                </tr>            	
            	</c:forEach>
            </table>
        </div>
    </div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>
