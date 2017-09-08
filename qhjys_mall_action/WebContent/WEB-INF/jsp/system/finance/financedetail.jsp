<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script src="/common/My97DatePicker/WdatePicker.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="6" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="7" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
     	<h3 class="jinrongtitle">项目列表-详情</h3>
        <p class="">
          	<span class="jinrongformleft">商家名称</span>${list.storeName }
        </p>
        <h3 class="jinrongtitleline">放款信息</h3>
        <div>
        	
          <input id="page" name="pageNum" type="hidden">
          <p class="jinrongformform">
          	<span class="jinrongformleft">借款金额</span>￥${list.loanTotal }
          </p>
          <p class="jinrongformform">
          	<span class="jinrongformleft">借款周期</span>${list.loanCycle }周
          </p>
         <p class="jinrongformform">
             <span class="jinrongformleft">放款日期</span><fmt:formatDate value="${list.loanDate }" pattern="yyyy-MM-dd "/>
        </div>
        <h3 class="jinrongtitleline">放款账号</h3>
        <div>
          <p class="jinrongformform">
          	<span class="jinrongformleft">开 户 行</span>${list.bankName }
          </p>
          <p class="jinrongformform">
          	<span class="jinrongformleft">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span>${list.cardAccount }
          </p>        	
            <form id="from" name="form1" action="/managermall/systemmall/finance/financelist.do" method="post">
          <input id="page" name="pageNum" type="hidden">
          <p class="jinrongformform">
             <span class="jinrongformleft"></span>
            <input type="submit" value="返回" class="submit8" style="width:100px;">
          </p>
          <p>&nbsp;</p>
        </form>
        </div>
        
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
