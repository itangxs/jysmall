<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/mallusermanage/list.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/system/store/store_list1.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>
<!--------------------------我的账户-------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="6"/></jsp:include>
	<!-- end -->
		<div class="memberright">
             <h3 style="padding-left:10px; margin-bottom:25px;">商家管理——微信店铺开通申请</h3>
        <form id="from"  class="zcform1" method="post" action="list.do">
         <input id="page" name="pageNum" type="hidden">
           <p class="clearfix">
             <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="button" value="开通收点餐" class="submit9" onclick="updateOrder(1,0)">
              <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="button" value="不开通点餐" class="submit9" onclick="updateOrder(0,0)">
           </p>
           
        </form>
        
        <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td40">
                    <input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/>
                    <th class="td50">账户名</th>
                    <th class="td80">店铺ID</th>
                    <th class="td80">店铺名称</th>
                    <th class="td50 th20">审核状态</th>
                    <th class="td80">创建时间</th>   
                </tr>
              <c:forEach items="${page}" var="s">
                <tr>
                    <td class="td40">
                   	 <input name="ids" type="checkbox" value="${s.id}" onclick="xuan('dx')" />
                   	 </td>
                    <td class="td50 tdfont">${s.username}</td>
                    <td class="td80 tdfont">${s.storeId }</td>
                    <td class="td80 th20">${s.storeName }</td>
                    <td class="td50 th20">
                    	<c:if test="${s.status == 0  }">未审核</c:if>
                    	<c:if test="${s.status == 1  }">审核通过</c:if>
                    	<c:if test="${s.status == 2  }">审核失败</c:if>
                    </td>
                    <td class="td80 tdfont"><fmt:formatDate value="${s.createTime}" pattern="yyyy-MM-dd "/></td> 
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

<!--费率设置弹出层-->
<div id="feilvset" class="white_content_new1">	
    <div class="m-windowbox400">
    	<div class="title">费率设置<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('feilvset').style.display='none';document.getElementById('fade').style.display='none'">
×</a></span></div>
		<div class="nr">
			<input type="hidden" id="storeId" value="${storeId }" />
        	<p>设置提现费率：
        		<select id="rate">
        			<option value="">无费率</option>
		        	<c:forEach items="${fqStoreRates }" var="fsr">
		        		<option value="${fsr.id }">${fsr.rateName }</option>
		        	</c:forEach>
                </select></p><br>
            <p><input  class="submit8" name="" type="submit" value="确认" onclick="return setRate();"/>
            	<input  class="submit8" style="margin:0 0 0 20px;" name="" type="reset" value="取消" /></p>
        </div> 
    </div>
</div>
<div id="fade" class="black_overlay"></div>
</body>

</html>
