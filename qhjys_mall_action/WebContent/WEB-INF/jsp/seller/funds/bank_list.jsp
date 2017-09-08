<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/js/seller/funds/bank_list.js"></script>
<title>飞券网商家后台中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="6" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 style="padding-left:10px; padding-bottom:20px;">银行卡</h3>
           <p class="clearfix">
            <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;银行卡：</label>
            <label class="one4" for="con-email2" style="width:50px;height:33px;text-align:left;color:#F30; font-size:20px;" >${bankCount}张</label>
            <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input type="button" value="添加银行卡" class="submit7" onclick="window.location.href='/managermall/seller/funds/toAddBank.do'">
           </p>
          
        <div class="member_myorder">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                   <!-- <th class="td130">地区</th> -->
                    <th class="td130">真实姓名</th>
                    <th class="td130">银行名称</th>
                    <th class="td200">银行卡号</th>
                    <th class="td130">是否默认</th>
                    <th class="td130">操作</th>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <c:forEach items="${vos}" var="p">
                <tr>
                  <%--  <td class="td130">${p.address}</td> --%>
                    <td class="td130">${p.realName}</td>
                    <td class="td130">${p.bankName}</td>
                    <td class="td200">
                    ${fn:substring(p.bankNumber, 0, 4)}*****${fn:substring(p.bankNumber, fn:length(p.bankNumber)-4 , fn:length(p.bankNumber))}
                    </td>
                    <td class="td130">
                     <c:if test="${empty p.defaultStaus ||p.defaultStaus==0  }">
                    	否
                    </c:if>
                    <c:if test="${p.defaultStaus==1}">
                    	是
                    </c:if>
                    </td>
                    <td class="td130"> 
                    		<c:if test="${p.defaultStaus == 0}">
                    			&nbsp;
                    			<a onclick="set(${p.id})" style="cursor: pointer;">设为默认</a>
                    			&nbsp;&nbsp;
                    		</c:if>
                    		<!-- <a onclick="delet(${p.id})" style="cursor: pointer;"> 删除</a> -->	</td>
                </tr>
                </c:forEach>
            </table>
            
        </div>
        <!--上一页下一页-->
       
    </div>
	<div class="clear"></div>

</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
