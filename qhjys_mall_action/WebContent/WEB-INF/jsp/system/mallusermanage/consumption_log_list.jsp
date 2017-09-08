<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>

<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/mallusermanage/consumption_log_list.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="4" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
 	<jsp:include page="/WEB-INF/jsp/system/mallusermanage/uLeft.jsp" flush="true" ><jsp:param name="position" value="3" /></jsp:include>
    <!--------------右侧------------------>
		<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">消费积分操作日志</h3>
      <form id="from" name="form1"  class="zcform1" action="/managermall/systemmall/malluser/consumptionLogList.do" method="post">
      	<input id="page" name="pageNum" type="hidden">
           <p class="clearfix">
        	<label class="one" for="con-email4" >会员ID：</label>
        	<input class="con-email4" name="userId" value="${userId}"> 
            <label class="one" for="con-email4" >变更类型：</label>
        	 <select name="type">
	             <option value="">全部</option>
	             <option value="1"  <c:if test="${type==1 }"> selected="selected"</c:if> >赠送积分</option>
	             <option value="2"  <c:if test="${type==2 }"> selected="selected"</c:if> >兑换商品</option>
            </select>  
           <label class="one" for="con-email4" >变更日期：</label>
           
        	
        	<input id="begin" name="changeStart" class="easyui-datebox con-time" value="${changeStart}" >
			<label class="one" for="con-email2">-</label>
			<input id="ending" name="changeEnd" class="easyui-datebox con-time" value="${changeEnd}" ><em class="space"></em>
             
            <input type="submit" value="查询" class="submit8">
           </p>
        </form>	  
         <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td160">会员ID</th>
                    <th class="td160">变更日期</th>
                    <th class="td160">变更类型</th>
                    <th class="td160">当前积分</th>  
                    <th class="td160">变更积分</th>
                    <th class="td160">变更后积分</th>  
                    <th class="td160">备注</th>
                </tr>
               <c:forEach items="${page}" var="c">
                <tr>   
                    <td class="td160">${c.sendId}</td>
                    <td class="td160"><fmt:formatDate value="${c.createTime}" pattern="yyyy-MM-dd "/></td>
                    <td class="td160">
           				<c:if test="${c.type==1 }"> 赠送积分</c:if>         	
           				<c:if test="${c.type==2 }"> 兑换商品</c:if>         	
                  </td>
                    <td class="td160">${c.sendBefor }</td>  
                    <td class="td160">${c.integral }</td>
                    <td class="td160">${c.sendAfter }</td>
                    <td class="td160">${c.remark }</td>              
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
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
