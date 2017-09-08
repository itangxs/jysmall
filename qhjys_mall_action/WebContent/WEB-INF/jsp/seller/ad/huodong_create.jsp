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

<script>
function showRecords(){
	document.getElementById('cktxjl').style.display='block';
	document.getElementById('fade').style.display='block';
}
</script>
<title>飞券网商家后台中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="51" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:10px; padding-bottom:20px;">活动创建</h3>
       	<div class="hongbaocreate"> 
        	<p style="font-size:18px; line-height:126px; margin-left:26px;" title="营销账户金额可用于开展返红包等营销活动，点击”充值“按钮可将商家账户余额转至营销账户。">营销账户总额：<span class="rental_show" title="营销账户金额可用于开展返红包等营销活动，点击”充值“按钮可将商家账户余额转至营销账户。">￥${cash.integral }</span>
            	<a class="chongzhi" href="/managermall/seller/redpack/chongzhi.do">充值</a><a class="check_record" href="javascript:showRecords()">查看充值记录</a>
            </p>          
        </div>
        <div class="hongbaocreate02">       
         <div>
         	<img src="/images/seller/hb_icon.png" style="display:block; margin:0 auto;"> 
        	<p><a class="newcreate" href="/managermall/seller/redpack/createRedpack.do">新建红包活动</a></p>
            <p><a class="check_activity_list" href="/managermall/seller/redpack/redpacklist.do">查看红包列表</a></p>                
        </div>        	
    </div>
   
   <!--底部红包活动banner,跳转到活动功能页面--> 
    <div class="bottom_banenr">
    	<a href="/seller/activity_step.do"><img src="/images/seller/bottom_banner.png"></a>
    </div>
    
    
    </div>
  <div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
<!--查看提现记录弹出层2-->
<div id="cktxjl" class="white_content">
	<p class="close">充值记录</p>
    <div class="nr">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                   <th>充值日期</th>
                    <th>充值金额</th>
                    <th>资金来源</th>
              
                </tr>
                <c:forEach items="${list }" var="l">
                 <tr>
	                  <td><p><fmt:formatDate value="${l.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p></td>
						<td>￥${l.money}</td>
	                    <td style="color:#5cbd1b;">
							<c:if test="${l.type == 1 }">商家充值</c:if>
							<c:if test="${l.type == 2 }">营销基金</c:if>
						</td>
	                </tr>
                </c:forEach>
   
            </table>
    </div>
    <div class="hranniu"><a href="javascript:void(0)" onclick="document.getElementById('cktxjl').style.display='none';document.getElementById('fade').style.display='none'">关闭</a></div>
</div>
<!--弹出幕布-->
    <div id="fade" class="black_overlay"></div>


    <!--活动banner弹窗-->
    <div id="banner-tanchu" <c:if test="${istanchu == 0}"> style="display: none;"</c:if>>
    <img src="/images/seller/activity-tanchu.png">
    <a id="close-tanchu" href = "javascript:void(0)" onclick = "document.getElementById('fade02').style.display='none';document.getElementById('banner-tanchu').style.display='none';"><img src="/images/close.png"></a>
    <div class="check_detail">
    <a href= "/seller/activity_step.do" style="position:absolute;left:50%;bottom:50px; margin-left:-54px;color:white; font-size:22px;" >查看详情>></a>
    </div>
    </div>

<!--活动banner弹出幕布-->
<div id="fade02" class="black_overlay02" <c:if test="${istanchu == 0}"> style="display: none;"</c:if>></div>
</body>
</html>