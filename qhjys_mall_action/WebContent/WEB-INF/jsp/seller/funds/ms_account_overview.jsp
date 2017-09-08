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
<script type="text/javascript">
function excel(){
	var urlval = '/managermall/seller/funds/withdraw_export.do';
	window.open(urlval);
}
</script>
<title>飞券网商家后台中心</title>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="3" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
	
    	<div class="excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
        <h3 style="padding-left:10px; padding-bottom:20px;">商家账户总览</h3>
		
        <form action="/managermall/seller/funds/withdraw_list.do"  method="post" id="from" style="display:none;">
        		<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
        </form>
        <div class="member_myorder">
   	    <table  border="0" cellspacing="0" cellpadding="0">
        		<tr>
                	<th colspan="4" style="font-size:15px;">在线支付结算表</th>
                </tr>
                <tr>
                   <th class="td300">日期</th>
                    <th>金额</th> 
                    <th class="td300">结算情况</th>
                </tr>
                <c:forEach items="${page }" var="p" varStatus="status">
                	<tr>
                  		<td style="text-align:center; line-height:18px;">
                  			<p><fmt:formatDate value="${p.createDate}" pattern="yyyy-MM-dd"/></p>
                  		</td>
						<td style="text-align: center;">￥${p.withdrawMoeny }</td>
				  		<td style="text-align: center;">
				  		<c:if test="${p.state == 0 }"><span class="t-fontred">未审核</span></c:if>
				  		<c:if test="${p.state == 1 }"><span class="t-fontblue">已审核</span></c:if>
				  		<c:if test="${p.state == 2 }"><span class="t-fontblue">出账中</span></c:if>
				  		<c:if test="${p.state == 3 }"><span class="t-fontblue">出账失败</span></c:if>
				  		<c:if test="${p.state == 4 }"><span class="t-fontblue">出账成功</span></c:if>
				  		</td>
                	</tr>
                </c:forEach>
            </table>
       	    <c:if test="${page.getPages()==0}">
			<div align="center">
       		  	<font  color="red">暂时没有数据!</font>
       		</div>
       	    </c:if>
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
               <input class="input1" id="jumPage" name="page" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        	</div>
        </div>
    </div>
  <div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
<!--弹出幕布-->
<div id="fade" class="black_overlay"></div>
</body>
</html>
