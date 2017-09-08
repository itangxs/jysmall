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
<script type="text/javascript">
	function validate(){
		var storeId = document.getElementById("storeId").value;
		var loanTotal = document.getElementsByName("loanTotal")[0].value;
		var payType = document.getElementsByName("payType")[0].value;
		var beginTime = document.getElementById("beginTime").value;
		var bankName = document.getElementsByName("bankName")[0].value;
		var cardAccount = document.getElementsByName("cardAccount")[0].value;
		if(storeId==""){
			alert("放款失败，请先查找商家ID");
			return false;
		}else if(loanTotal=="" || payType=="" || beginTime=="" || bankName=="" || cardAccount==""){
			alert("请完善信息");
			return false;
		}else{
			return true;
		}
	}
</script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="6" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="6" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
     	<h3 class="jinrongtitle">新增项目</h3>
     	<form id="from2" name="form2" action="/managermall/systemmall/finance/queryByStoreId.do" method="post">
        <p class="jinrongformform">
        
          	<span class="jinrongformleft">商家ID</span>
          	<input class="con-email4" name="storeId" value="${store.id}">&nbsp;&nbsp;
            <input type="submit" value="查找" class="submit8">
        
        </p>
        </form>
        <div class="jrsearchresults">
        	<ul>
            	<li>商家名称：${store.name }</li>
                <li>联系人：${store.contactName }</li>
                <li>联系电话：${store.contactTel }</li>
                <li>银行卡户名：${bank.cardholder }</li>
                <li>开户行：${bank.branch }</li>
                <li>账号：${bank.carkNum }</li>
                <div class="clear"></div>
            </ul>
        </div>
        <h3 class="jinrongtitleline">放款信息</h3>
        <form id="from" name="form1" action="/managermall/systemmall/finance/financeadd.do" method="post" onsubmit="return validate();">
        <div>
          <input id="store_id" name="store_id" type="hidden" value="${store.id }">
          <input id="page" name="pageNum" type="hidden">
          <p class="jinrongformform">
          	<span class="jinrongformleft">借款金额</span>
          	<input class="con-email4" name="loanTotal" value="${loanTotal}">  
          </p>
          <p class="jinrongformform">
          	<span class="jinrongformleft">借款周期</span>            
            <select name="payType" class="selectjr">
                <option value="">全部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                <option value="4"  <c:if test="${payType==4 }">selected="selected"</c:if>>4周</option>
                <option value="5"	<c:if test="${payType==5 }">selected="selected"</c:if>>5周</option>
                <option value="6"  <c:if test="${payType==6 }">selected="selected"</c:if>>6周</option>
                <option value="7"  <c:if test="${payType==7 }">selected="selected"</c:if>>7周</option>
                <option value="8"  <c:if test="${payType==8 }">selected="selected"</c:if>>8周</option>
                <option value="9"	<c:if test="${payType==9 }">selected="selected"</c:if>>9周</option>
                <option value="10"  <c:if test="${payType==10 }">selected="selected"</c:if>>10周</option>
                <option value="11"  <c:if test="${payType==11 }">selected="selected"</c:if>>11周</option>
                <option value="12"  <c:if test="${payType==12 }">selected="selected"</c:if>>12周</option>
                <option value="13"	<c:if test="${payType==13 }">selected="selected"</c:if>>13周</option>
                <option value="14"  <c:if test="${payType==14 }">selected="selected"</c:if>>14周</option>
                <option value="15"  <c:if test="${payType==15 }">selected="selected"</c:if>>15周</option>
                <option value="16"  <c:if test="${payType==16 }">selected="selected"</c:if>>16周</option>
                <option value="17"  <c:if test="${payType==17 }">selected="selected"</c:if>>17周</option>
                <option value="18"  <c:if test="${payType==18 }">selected="selected"</c:if>>18周</option>
                <option value="19"	<c:if test="${payType==19 }">selected="selected"</c:if>>19周</option>
                <option value="20"  <c:if test="${payType==20 }">selected="selected"</c:if>>20周</option>
                <option value="21"  <c:if test="${payType==21 }">selected="selected"</c:if>>21周</option>
                <option value="22"  <c:if test="${payType==22 }">selected="selected"</c:if>>22周</option>
                <option value="23"	<c:if test="${payType==23 }">selected="selected"</c:if>>23周</option>
                <option value="24"  <c:if test="${payType==24 }">selected="selected"</c:if>>24周</option>
            </select>  	
          </p>
          <p class="jinrongformform">
             <span class="jinrongformleft">放款日期</span>
			 <input id="beginTime" name="beginTime"  class="con-email3"  type="text" readonly="readonly"  value="${beginTime}"/>
        	 <img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'beginTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/>
          </p>
      
        </div>
        <h3 class="jinrongtitleline">放款账号</h3>
        <div>
        	
           
          <input id="page" name="pageNum" type="hidden">
          <p class="jinrongformform">
          	<span class="jinrongformleft">开 户 行</span>
          	<input class="con-email4" name="bankName" value="${bankName}">
          </p>
          <p class="jinrongformform">
          	<span class="jinrongformleft">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span>
            <input class="con-email4" name="cardAccount" value="${cardAccount}">       	
          </p>
          <p class="jinrongformform">
             <span class="jinrongformleft"></span>
             <input type="submit" value="确认" class="submit8">&nbsp;&nbsp;&nbsp;&nbsp;
             <input name="重置" type="reset" class="submit8" value="重置">
          </p>
          <p>&nbsp;</p>
       
        </div>
        </form>
        
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
