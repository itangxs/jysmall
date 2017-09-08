<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<style type="text/css">
.memberrightttt{float:left; width:928px; /* height:100%; */ padding:20px 20px 50px 20px;}
</style>
<link href="/css/seller/public.css" rel="stylesheet" type="text/css" />
<link href="/css/seller/membernew.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>


<script type="text/javascript"charset="UTF-8">
	function storeCheckStatus(id,status){
		$.ajax({
			async : false,
			type : "post",
			url : "/managermall/systemmall/store/updateWxStoreCheck.do",
			data : {id:id,status:status},
			success : function(g) {
					alert("修改成功");
					window.location.href = window.location.href;
			},
			error : function() {
				alert("服务器忙")
			}
		})
	}
</script>


</head>

<body>
<!-------------------top---------------------------------------------------------------------->

<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="4"/></jsp:include>
	<div class="memberrightttt">
      <h3 style="padding-left:5px; margin-bottom:25px;">微信店铺详情</h3>
      
          <table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td style="text-align:right;"><a href="javascript:history.go(-1);"></a></td></tr></table>
          <div class="member_myorder">
           
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  
                <tr>
                  <td class="td160">店铺ID</td>
                <%--   <td class="td504">${fqStore.sellerId}</td> --%>
                  <td class="td504">${storeCheck.storeId}</td>
                </tr>
                <tr>
                  <td class="td160">店铺名称</td>
                  <td class="td504">${storeCheck.storeName}</td>
                </tr>
                
                
                
                <tr>
                  <td class="td160">店铺LOGO:</td>
                  <td class="td504"><img src ="${storeCheck.storeLogo}"width="150" height="120" ></td>
                </tr>
                
            
                <tr>
                  <td class="td160">店铺介绍:</td>
                  <td class="td504">${storeCheck.storeInfo}</td>
                </tr>
                <tr>
                  <td class="td160">营业开始:</td>
                  <td class="td504">${storeCheck.trafficBeginTime}</td>
                </tr>
                <tr>
                  <td class="td160">营业结束:</td>
                  <td class="td504">${storeCheck.trafficEndTime}</td>
                </tr>
                
                
                <tr>
                  <td class="td160">地址:</td>
                  <td class="td504">${storeCheck.address}</td>
                </tr>
                
                
                <tr>
                  <td class="td160">电话:</td>
                  <td class="td504">${storeCheck.phoneNum}</td>
                </tr>
                
                
                <tr>
                  <td class="td160">定金比例:</td>
                  <td class="td504">${storeCheck.proportion}</td>
                </tr>
                
                
                <tr>
                  <td class="td160">商圈:</td>
                  <td class="td504">
                  	<c:forEach items="${fqLocation}" var="sq">
                  		<c:if test="${sq.id==storeCheck.locationId}">${sq.location}</c:if>
                  	</c:forEach>
                  </td>
                </tr>
                
                <tr>
                  <td class="td160">修改时间:</td>
                  <td class="td504">
                  <fmt:formatDate value="${storeCheck.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                </tr>
                <tr>
                  <td class="td160">审核状态:</td>
                  <td class="td504">
                  <c:if test="${storeCheck.status == 0  }">未审核</c:if>
                    	<c:if test="${storeCheck.status == 1  }">审核通过</c:if>
                    	<c:if test="${storeCheck.status == 2  }">审核不通过</c:if>
                </td>
                </tr>
              <c:if test="${storeCheck.status == 0}">
                <tr>
                <td class="td160"></td>
                   <td class="td504">
                  	<input class="submit8" type="button" value="审核通过" onclick="storeCheckStatus('${storeCheck.id}',1)"/> 
                  	<input class="submit8" type="button" value="审核未通过" onclick="storeCheckStatus('${storeCheck.id}',2)"/>
                   </td>
                </tr>  </c:if>
            </table>
           
            <br/>
      </div>
  </div>
	<div class="clear"></div>
</div>

<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>

</html>
