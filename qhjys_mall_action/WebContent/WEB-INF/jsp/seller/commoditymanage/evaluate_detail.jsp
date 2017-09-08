<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/seller/commoditymanage/evaluate_detail.js"></script>
</head> 
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="11" /></jsp:include>
		<!--------------右侧------------------>
	<div class="memberright">
	  <div class="member_myorder">
   	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <th width="292" class="td130"><span style="text-align:left;">产品评论详情</span></th>
                  <th width="292" class="td130">&nbsp;</th>
                  <th width="292" class="td130">&nbsp;</th>
                    <th width="636" class="td130"><a href="/managermall/seller/commoditymanage/queryEvaluateList.do" class="fontred">返回评论列表</a></th>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="80" style="text-align:right;">商品名称：</td>
                    <td width="848" class="td508">${evaluate.prodName}</td>
                </tr>
                <tr>
                  <td width="80" style="text-align:right;">会员名：</td>
                    <td width="848" class="td508">${evaluate.userAccount}***</td>
                </tr>
                  <tr>
                  <td style="text-align:right;">评论时间</td>
                  <td class="td508"><fmt:formatDate value="${evaluate.revDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              </tr> 
                <tr>
                  <td width="80" style="text-align:right;">评分：</td>
                    <td width="848" class="td508">${evaluate.score}</td>
                     <td><input type="hidden" name="id" id="id" value="${evaluate.id}" /></td>
                     <td><input type="hidden" name="token" id="token" value="${sellerEvaluateToken}" /></td>
                </tr>
             
                <tr>
                    <td width="80" style="text-align:right;">评论内容：</td>
                    <td width="848" class="td508">${evaluate.reviews}</td>
              </tr>
               <tr>
                    <td width="80" style="text-align:right;">商家回复：</td>
                    <c:if test="${not empty evaluate.replyContent}">
                    	<td width="848" class="td508">${evaluate.replyContent}</td>
                    </c:if>
                     <c:if test="${empty evaluate.replyContent}">
                     	<td width="848" class="td508"><textarea id="sellerReplyContent" name="sellerReplyContent" cols="" rows="5">${evaluate.replyContent}</textarea></td>
                     </c:if>
              </tr>
              <tr>
                  <td style="text-align:right; padding-top:10px;"></td>
                  <td class="td508"><form id="form1" name="form1" method="post" action="">
                  <c:if test="${empty evaluate.replyContent}">
                  		<input type="submit" name="button" id="button" value="回复"  class="submit6" />
                  </c:if>
                  </form></td>
              </tr>
              
            </table>
            
              </div>
             
</div>
	<div class="clear"></div>
</div>
</body>
</html>