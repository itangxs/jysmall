<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />   
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/redpack/list.js"></script>
<title>飞券网商家后台中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="52" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:10px; margin-bottom:25px;">红包活动列表</h3>
         <p class="clearfix">
            <input type="button" value="新建活动" class="submitskyblue"  onclick="window.location.href='/managermall/seller/redpack/createRedpack.do'" style="float:right;" > 
         </p>
         <form id="from"  class="zcform1" method="post" action="/managermall/seller/redpack/redpacklist.do">
         	<input id="page" name="pageNum" type="hidden" value="${pageNum }">
         </form>
        <div class="member_myorder">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>  
                    <th class="td160">活动名称</th>
                    <th class="180">活动时间</th>
                    <th class="td160">状态</th>
                    <th class="td100">其他</th>
                    <th class="td100">操作</th>
                </tr>        
                  <c:forEach items="${page}" var="s">
	                  <tr>
	                    <td class="td160">${s.activityName }
	                    </td>
	                    <td class="td180" style=" text-align:center"> <fmt:formatDate value="${s.beginDate}" pattern="yyyy-MM-dd"/>-
                   		 <fmt:formatDate value="${s.endDate}" pattern="yyyy-MM-dd"/></td>
	                   <c:if test="${s.hasChange == 1 }"> <td class="td160">
		                    <a title="开始" href="javascript:updateStatus('1','${s.id }')" <c:choose><c:when test="${s.status==1 }">class="m-iconhref01-current"</c:when><c:otherwise>class="m-iconhref01"</c:otherwise></c:choose>
	                    		><span class="m-icon4"></span></a>
	                    	<a title="暂停" href="javascript:updateStatus('2','${s.id }')" <c:choose><c:when test="${s.status==2 }">class="m-iconhref01-current"</c:when><c:otherwise>class="m-iconhref01"</c:otherwise></c:choose>
	                    		><span class="m-icon5"></span></a>
	                    	<a title="结束" href="javascript:updateStatus('0','${s.id }')" <c:choose><c:when test="${s.status==0 }">class="m-iconhref01-current"</c:when><c:otherwise>class="m-iconhref01"</c:otherwise></c:choose>
								><span class="m-icon6"></span></a></td></c:if>
							<c:if test="${s.hasChange == 0 }">
							<td class="td160">
							<a title="此为奖励的营销基金红包活动，由飞券设置，您暂无修改状态和删除的权限" href="" class="m-iconhref01<c:if test="${s.status == 1 }">-current</c:if>" class="m-icon4"><span class="m-icon4"></span></a>
	                    	<a title="此为奖励的营销基金红包活动，由飞券设置，您暂无修改状态和删除的权限" href="" class="m-iconhref01<c:if test="${s.status == 2 }">-current</c:if>"><span class="m-icon5"></span></a>
	                    	<a title="此为奖励的营销基金红包活动，由飞券设置，您暂无修改状态和删除的权限" href="" class="m-iconhref01<c:if test="${s.status == 0 }">-current</c:if>"><span class="m-icon6"></span></a></td>
							</c:if>	
	                    <td class="td100"><a href="/managermall/seller/redpack/hongbao_detail.do?redpackId=${s.id }">详情</a></td>  
	                    <td class="td100">
	                     <c:if test="${s.hasChange == 1 }"><a href="javascript:deleteactivity('${s.id }')" class="submitred">删除</a></c:if>
	                     <c:if test="${s.hasChange == 0 }"><a href="javascript:void(0);" title="此为奖励的营销基金红包活动，由飞券设置，您暂无修改状态和删除的权限" class="submitgray">删除</a></c:if>
	                    </td>
	                </tr>
                  
                  </c:forEach>
              
              </table>
        </div>
        <!--上一页下一页-->
         <div class="page">
            	<c:if test="${page.getPageNum()>1}">
            	<a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${page.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${page.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${page.getPages()-3}">
            			<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
               <span>共${page.getPages()}页</span><span>到第</span>
               <input class="input1" id="jumPage" name="pageNum" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
	</div>
  <div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>