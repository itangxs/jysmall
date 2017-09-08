<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心	-商品订单</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script src="/common/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/seller/order/list.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/order/advance_list.js"></script>
</head>
<body>

<!--微信鼓励金以及满额立减活动弹窗-->
<div id="cktxj5" class="white_content05" <c:if test="${istanc == 0}"> style="display: none;"</c:if>>
            	
    			<img src="/images/seller/wxtanchu_adv.jpg">
                <a class="close-btn2" href="javascript:void(0)" onclick = "document.getElementById('cktxj5').style.display='none';document.getElementById('fade2').style.display='none'"></a>
                <a class="detai-btn1" href="http://www.jysmall.com/wechatevent.html"></a>
    		</div>  

			<div id="fade2" class="black_overlay03" <c:if test="${istanc == 0}"> style="display: none;"</c:if>></div>


	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="22" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
        	<div class="excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
            <h3 style="padding-left:; padding-bottom:20px;">支付订单列表</h3>
			<form id="from" name="query" method="post" class="zcform1" action="/managermall/seller/funds/advancelist.do">
				<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
				<input type="hidden" id="datenum" name="datenum" value="${datenum}">
            	<label class="one" for="con-email2">创建时间${istancc}：</label>
                
                <input id="tjsjs" name="tjsjs"  class="con-email3"  type="text" readonly  value=""/>
	        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'tjsjs'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 	           
	            <label class="one" for="con-email2" >-</label>
	            <input id="tjsje" name="tjsje"  class="con-email3"   type="text" readonly  value=""/>
	        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'tjsje'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle">
                    
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:changedatenum(1)" <c:if test="${datenum==1 }"> class="huiboxcurrent"</c:if><c:if test="${datenum!=1 }"> class="huibox"</c:if>>今天</a> | 最近：<a href="javascript:changedatenum(7)" <c:if test="${datenum==7 }"> class="huiboxcurrent"</c:if><c:if test="${datenum!=7 }"> class="huibox"</c:if>>7天</a>&nbsp;&nbsp;<a href="javascript:changedatenum(30)" <c:if test="${datenum==30 }"> class="huiboxcurrent"</c:if><c:if test="${datenum!=30 }"> class="huibox"</c:if>>1个月</a>&nbsp;&nbsp;<a href="javascript:changedatenum(90)" <c:if test="${datenum==90 }"> class="huiboxcurrent"</c:if><c:if test="${datenum!=90 }"> class="huibox"</c:if>>3个月</a><br>
		  <p class="clearfix"><br>
					<label class="one" for="con-email2">订单编号${datenum}：</label>
					<input name="orderNo" type="text" class="con-input" value="${orderNo}"><em class="space"></em>
					
					<label class="one" for="con-email2">&nbsp;&nbsp;支付方式：</label>
                    <select name="payType">
                    	<option value="">全部</option>
                      <option value="1" <c:if test="${payType==1 }"> selected="selected"</c:if>>微信</option>
                      <option value="2" <c:if test="${payType==2 }"> selected="selected"</c:if>>支付宝</option>
                      <option value="3" <c:if test="${payType==3 }"> selected="selected"</c:if>>QQ钱包</option> 
                      <option value="4" <c:if test="${payType==4 }"> selected="selected"</c:if>>POS</option>         
                    </select>
					&nbsp;&nbsp;<input type="submit" value="查询" class="submit6">&nbsp;&nbsp;<input type="button" value="重置" class="submit6" onclick="window.location.href=window.location.href">
			  </p>
			</form>
            <h3 class="jinrongtitle">查询统计</h3>
            <div class="jinrongbk1">
        	<ul>
            	<li>订单数量：${orderCountVo.orderNum} 个</li>
                <li>交易总额：${orderCountVo.payTotal} 元</li>
                <li>手续费用：${orderCountVo.rateTotal}元</li>
                <li>到账金额：${orderCountVo.totamtTotal} 元</li>
                <div class="clear"></div>
            </ul>
        </div>
        
			<div class="member_myorder">
        
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>	
						<th class="">订单编号</th>
						<th class="td120">创建时间</th>
						<th class="td90">支付方式</th>
						<th class="td100">会员ID</th>						
						<th class="td100">支付金额</th>
						<th class="td80">手续费率</th>
						<th class="td80">手续费</th>
                        <th class="td80">到账金额</th>
						<th class="td80">消费来源</th>
					</tr>
					<c:forEach items="${page}" var="page">
					<tr>
						<td class="td201">
							<a href="/getProdDetail.do?prodId=${info.prodId}"><img src="${fn:substring(info.prodImage,0,fn:indexOf(info.prodImage,'|'))}" /></a>
							<p>${page.orderNo}</p>
						</td>
						<td class="td120 th20"><fm:formatDate value="${page.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="td90">
							   <c:if test="${page.type==1 || page.type==3 || page.type==5}">
										微信							   
							   </c:if>
							    <c:if test="${page.type==2 || page.type==6}">
										支付宝						   
							   </c:if>
							    <c:if test="${page.type==7}">
										QQ钱包							   
							   </c:if>
							     <c:if test="${page.type==4}">
										POS							   
							   </c:if>
						</td>
						<td class="td100">${page.openId }</td>						
						<td class="td100">￥${page.realPay }</td>
                        <td class="td80">${page.orderRate }%</td>
                        <td class="td80">￥${page.rateFee }</td>
                        <td class="td80">￥${page.totamt }</td>
						<td class="td80">
                        <c:if test="${page.bankType == 1}">零钱</c:if>
					    <c:if test="${page.bankType == 2}">信用卡</c:if>
					    <c:if test="${page.bankType == 3}">储蓄卡</c:if>
					    <c:if test="${empty page.bankType || page.bankType=='' }">-</c:if>
                    	</td>  
					</tr>
					</c:forEach>
					
				</table>
			</div>
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
		<div class="clear"></div>
	</div>
    
   
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
    	

</body>
</html>