<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>飞券网平台管理中心</title>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/system/kaquan/dateRange.js"></script>
<script type="text/javascript" src="/js/system/kaquan/monthPicker.js"></script>
<link rel="stylesheet" type="text/css" href="/css/system/dateRange.css"/>
<link rel="stylesheet" type="text/css" href="/css/system/monthPicker.css"/>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/kaquan/kaquan_hexiao.js"></script>



</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="position" value="8" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:10px; margin-bottom:25px;">核销与统计</h3>
      		<div class="data-nav">
            	<a href="/managermall/systemmall/cardcoupon/kaquanData_list.do"><p class="kaquan-data <c:if test="${hexiao_type ==1 }">nav-blue</c:if>">卡券数据</p></a>
                <a href="/managermall/systemmall/cardcoupon/deliverData_list.do"><p class="put-data   <c:if test="${hexiao_type ==2 }">nav-blue</c:if>">投放数据</p></a>
                <a href="/managermall/systemmall/cardcoupon/valiData_list.do"><p class="hexiao-data  <c:if test="${hexiao_type ==3 }">nav-blue</c:if>">核销数据</p></a>
            </div>
        <br>
          <div class="member_myorder">
          <!--卡券数据-->
          <c:if test="${hexiao_type ==1 }">
          	  <div class="kaquan-data-label">
              	<form id="from"  class="zcform1" method="post" action="/managermall/systemmall/cardcoupon/kaquanData_list.do">
        			<p class="clearfix">
        				<!-- <input type="hidden" id=""> -->
						<input id="page" name="pageNum" type="hidden" value="${page.getPageNum() }">
                	    <label class="one" for="con-email4" >店铺名称：</label>
		        		<input class="con-email4" name="storeName" value="${storeName}" id="storeName" >
                    	<label for="select">&nbsp;&nbsp;&nbsp;&nbsp; </label>
		        		<label class="one" for="con-email4" >店铺ID：</label>
		        		<input class="con-email4" name="storeId" value="${storeId}" id="storeId" >	           
                        <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					   <input type="submit" value="查询" class="submit8">         	     
           			</p>
        		 	
          		<div class="label-title clearfix">
                	<p class="label-name">优惠券数据</p>
                        <p class="xiazai-label"><a href="/managermall/systemmall/cardcoupon/kaquanListExcel.do">下载表格</a></p>
                       <div class="time-quantum01">
								<div class="ta_date" id="div_date_demo3">
								<span class="date_title" id="date_demo"></span>
									<a class="opt_sel" id="input_trigger_demo" href="#">
										<i class="i_orderd"></i>
									</a>
								</div>  
							<div id="datePicker"></div>
						</div>
                       <div class="time-quantum02"> 
                       		<input type="hidden" id="startTimeinp" name="startDate" value="${startDate }">
         					<input type="hidden" id="endTimeinp" name="endDate" value="${endDate }">
                       		<select name="time" onchange="submitDate(this)" id="aRecent7DaysDemo3">
								<option value="-1">全部</option>
								<option value="7"<c:if test="${time == 7}">selected="selected"</c:if>>最近7天</option>
								<option value="14"<c:if test="${time == 14 }">selected="selected"</c:if>>最近14天</option>
								<option value="30"<c:if test="${time == 30 }">selected="selected"</c:if>>最近30天</option>
							</select>
					   </div> 
                </div>                 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin:0;clear:both;">
               <tr> 
                    <th class="td100" > 
                     <select name="couponCfg" id="cardData" class="list-tpye" onchange="submitForm()">
                     	<option value="-1">全部卡券</option>
		        		<option <c:if test="${couponCfg == 0 }"> selected="selected" </c:if> value="0" >礼品券</option>
		        		<option <c:if test="${couponCfg == 1 }"> selected="selected" </c:if> value="1" >代金券</option>
                        <option <c:if test="${couponCfg == 2 }"> selected="selected" </c:if> value="2" >折扣券</option>
		        	</select>
                    </th>
                    <th class="td100">店铺名称</th>
                    <th class="td100">店铺ID</th>
                    <th class="td100">卡券名称</th>            
                    <th class="td160" style="line-height:18px;">领取<br><span style="font-weight:normal;">实际 | 显示</span></th>
                    <th class="td100">核销</th>                  
                </tr>
				<c:forEach items="${page }" var="a">
                <tr>
                    <td class="td160">
	                    <c:if test="${a.couponCfg == 0 }">礼品券</c:if>
	                    <c:if test="${a.couponCfg == 1 }">代金券</c:if>
	                    <c:if test="${a.couponCfg == 2 }">折扣券</c:if>
                    </td> 
                    <td class="td160">${a.storeName }</td>
                    <td class="td100">${a.storeId }</td>                 
                    <td class="td100">${a.name }</td>
                    <td class="td160"><span>${a.cardReciveTrue }</span> <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span>${a.cardReciveDisplay }</span></td>
                    <td class="td100">
                   		${a.writeOff }
                    </td>
                </tr>
                </c:forEach>
              </table>
              </form>
			</div>
			<div class="page">
			<c:if test="${page.getPageNum()>1}">
				<a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a>
			</c:if>
			<c:choose>
				<c:when test="${page.getPages()<7}">
					<c:forEach var="i" begin="1" end="${page.getPages()}">
						<c:choose>
							<c:when test="${i==page.getPageNum()}">
								<em class="current">${i}</em>
							</c:when>
							<c:otherwise>
								<a href="javascript:previousPage(${i},'from','page')">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:when test="${page.getPages()>6}"> 
					<c:forEach var="i" begin="1" end="3">
						<c:choose>
							<c:when test="${i==page.getPageNum()}">
								<em class="current">${i}</em>
							</c:when>
							<c:otherwise>
								<a href="javascript:previousPage(${i},'from','page')">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${page.getPageNum()>4}">
						<em>...</em>
					</c:if>
					<c:forEach var="i" begin="4" end="${page.getPages()-3}">
						<c:if test="${i==page.getPageNum()}">
							<em class="current">${i}</em>
						</c:if>
					</c:forEach>
					<c:if test="${page.getPageNum()<page.getPages()-3}">
						<em>...</em>
					</c:if>
					<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
						<c:choose>
							<c:when test="${i==page.getPageNum()}">
								<em class="current">${i}</em>
							</c:when>
							<c:otherwise>
								<a href="javascript:previousPage(${i},'from','page')">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
			</c:choose>
			<c:if test="${page.getPages()>page.getPageNum()}">
				<a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a>
			</c:if>
			<span>共${page.getPages()}页</span><span>到第</span> <input class="input1" id="jumPage" name="pageNum" type="text" value="${page.getPageNum()}"
				onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}" /><span>页</span>
				<div class="clear"></div>
			  </div>
            </c:if>
          <!--投放数据-->  
          <c:if test="${hexiao_type ==2 }">
            <div class="put-data-label">
            	<form id="from1"  class="zcform1" method="post" action="/managermall/systemmall/cardcoupon/deliverData_list.do">
        			<p class="clearfix">
						<input id="page" name="pageNum" type="hidden" value="${page.getPageNum() }">
                	    <label class="one" for="con-email4" >店铺名称：</label>
		        		<input class="con-email4" name="storeName" value="${storeName}" id="storeName1" >
                    	<label for="select">&nbsp;&nbsp;&nbsp;&nbsp; </label>
		        		<label class="one" for="con-email4" >店铺ID：</label>
		        		<input class="con-email4" name="storeId" value="${storeId}" id="storeId1">	           
                        <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					    <input type="submit" value="查询" class="submit8">         	     
           			</p>
          		 <div class="label-title clearfix">
                		<p class="label-name">优惠券数据</p>
                        <p class="xiazai-label"><a href="/managermall/systemmall/cardcoupon/deliverDataListExcel.do">下载表格</a></p>
                       <div class="time-quantum01">
								<div class="ta_date" id="div_date_demo3">
								<span class="date_title" id="date_demo1"></span>
									<a class="opt_sel" id="input_trigger_demo1" href="#">
										<i class="i_orderd"></i>
									</a>
								</div>  
							<div id="datePicker"></div>
						</div>
                       <div class="time-quantum02"> 
                       	<input type="hidden" id="startTimeinp1" name="startDate" value="${startDate }">
         				<input type="hidden" id="endTimeinp1" name="endDate" value="${endDate }"> 
                       		<select name="time" onchange="submitDate1(this)" id="aRecent7DaysDemo3">
                       			<option value="-1">全部</option>
								<option <c:if test="${time == 7}">selected="selected"</c:if> value="7" >最近7天</option>
								<option <c:if test="${time == 14 }">selected="selected"</c:if> value="14">最近14天</option>
								<option <c:if test="${time == 30 }">selected="selected"</c:if> value="30">最近30天</option>
							</select>
					   </div>               
                </div>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin:0;clear:both;">
               <tr> 

                    <th class="td160"> 
                     	<select name="deliverType" class="list-tpye" id="deliverType" onchange="submitForm1()">
                     	<option value="-1">全部投放方式</option>
		        		<option value="0" <c:if test="${deliverType == 0 }"> selected="selected"</c:if>>公众号直接投放</option>
		        		<option value="1" <c:if test="${deliverType == 1 }"> selected="selected"</c:if>>自营投放</option>
                        <option value="2" <c:if test="${deliverType == 2 }"> selected="selected"</c:if>>周边投放</option>
		        		</select>
                    </th>
                    <th class="td100">店铺名称</th>
                    <th class="td100">店铺ID</th>
             
                    <th class="td160" style="line-height:18px;">展示<br><span style="font-weight:normal;">实际 | 显示</span></th>
                    <th class="td160" style="line-height:18px;">领取<br><span style="font-weight:normal;">实际 | 显示</span></th>
                    <th class="td160" style="line-height:18px;">分享<br><span style="font-weight:normal;">实际 | 显示</span></th>
                    <th class="td160" style="line-height:18px;">分享领取<br><span style="font-weight:normal;">实际 | 显示</span></th>
        
                </tr>
                <c:forEach items="${page1 }" var="b">
	                <tr>
	                    <td class="td160"  >
	                    	<c:if test="${b.deliverType == 0 }">公众号直接投放</c:if>
	                    	<c:if test="${b.deliverType == 1 }">自营投放</c:if>
	                    	<c:if test="${b.deliverType == 2 }">周边投放</c:if>
	                    </td> 
	                    <td class="td160">${b.storeName }</td>
	                    <td class="td100">${b.storeId }</td>                
	                    <td class="td160"><span><c:if test="${empty b.cardPushTrue }">-</c:if>${b.cardPushTrue }</span> <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span><c:if test="${empty b.cardPushDisplay }">-</c:if>${b.cardPushDisplay }</span></td>
	                    <td class="td160"><span>${b.cardReciveTrue }</span> <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span>${b.cardReciveDisplay }</span></td>
	                    <td class="td160"><span>${b.cardShareTrue }</span> <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span>${b.cardShareDisplay }</span></td>
	                    <td class="td160"><span>${b.cardShareReciveTrue }</span> <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span>${b.cardShareReciveDisplay }</span></td>
	                </tr>
                </c:forEach>
              </table>
              </form>
			</div>
			<div class="page">
			<c:if test="${page1.getPageNum()>1}">
				<a href="javascript:previousPage(${page1.getPageNum()-1},'from1','page')" class="prev">上一页</a>
			</c:if>
			<c:choose>
				<c:when test="${page1.getPages()<7}">
					<c:forEach var="i" begin="1" end="${page1.getPages()}">
						<c:choose>
							<c:when test="${i==page1.getPageNum()}">
								<em class="current">${i}</em>
							</c:when>
							<c:otherwise>
								<a href="javascript:previousPage(${i},'from1','page')">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:when test="${page1.getPages()>6}">
					<c:forEach var="i" begin="1" end="3">
						<c:choose>
							<c:when test="${i==page1.getPageNum()}">
								<em class="current">${i}</em>
							</c:when>
							<c:otherwise>
								<a href="javascript:previousPage(${i},'from1','page')">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${page1.getPageNum()>4}">
						<em>...</em>
					</c:if>
					<c:forEach var="i" begin="4" end="${page1.getPages()-3}">
						<c:if test="${i==page1.getPageNum()}">
							<em class="current">${i}</em>
						</c:if>
					</c:forEach>
					<c:if test="${page1.getPageNum()<page1.getPages()-3}">
						<em>...</em>
					</c:if>
					<c:forEach var="i" begin="${page1.getPages()-2}" end="${page1.getPages()}">
						<c:choose>
							<c:when test="${i==page1.getPageNum()}">
								<em class="current">${i}</em>
							</c:when>
							<c:otherwise>
								<a href="javascript:previousPage(${i},'from1','page')">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
			</c:choose>
			<c:if test="${page1.getPages()>page1.getPageNum()}">
				<a href="javascript:previousPage(${page1.getPageNum()+1},'from1','page')" class="next">下一页</a>
			</c:if>
			<span>共${page1.getPages()}页</span><span>到第</span> <input class="input1" id="jumPage" name="pageNum" type="text" value="${page1.getPageNum()}"
				onkeydown="if(event.keyCode==13){previousPage(this.value,'from1','page')}" /><span>页</span>
				<div class="clear"></div>
		</div>
            </c:if>
          <!--核销数据-->
          <c:if test="${hexiao_type == 3 }">
              <div class="hexiao-data-label">
              		<form id="from2"  class="zcform1" method="post" action="/managermall/systemmall/cardcoupon/valiData_list.do">
        			<p class="clearfix">
         			 	<!-- <input type="hidden" id="validateType">  -->
						<input id="page" name="pageNum" type="hidden">
                	    <label class="one" for="con-email4" >店铺名称：</label>
		        		<input class="con-email4" name="storeName" value="${storeName}" id="storeName2" >
                    	<label for="select">&nbsp;&nbsp;&nbsp;&nbsp; </label>
		        		<label class="one" for="con-email4" >店铺ID：</label>
		        		<input class="con-email4" name="storeId" value="${storeId}" id="storeId2">	           
                        <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					   <input type="submit" value="查询" class="submit8">         	     
           			</p>
           			
          		    <div class="label-title clearfix">
                		<p class="label-name">优惠券数据</p>
                        <p class="xiazai-label"><a href="javascript:Excel()">下载表格</a></p>
                        <div class="time-quantum01">
								<div class="ta_date" id="div_date_demo3">
								<span class="date_title" id="date_demo2"></span>
									<a class="opt_sel" id="input_trigger_demo2" href="#">
										<i class="i_orderd"></i>
									</a>
								</div>  
							<div id="datePicker"></div>
						</div>
						<input type="hidden" id="startTimeinp2" name="startDate" value="${startDate }">
         				<input type="hidden" id="endTimeinp2" name="endDate" value="${endDate }"> 
                       <div class="time-quantum02"> 
                       		<select name="time" class="selector" onchange="submitDate2(this)" id="aRecent7DaysDemo3">
                       			<option value="-1">全部</option>
				 				<option <c:if test="${time == 7 }"> selected="selected"</c:if> value="7" >最近7天</option>
								<option <c:if test="${time == 14 }"> selected="selected"</c:if> value="14">最近14天</option>
								<option <c:if test="${time == 30 }"> selected="selected"</c:if> value="30">最近30天</option>
							</select>
					   </div> 
                   </div>
                   
              <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin:0;clear:both;">
               <tr> 
                    <th class="td160"> 
                     <select name="couponCfg" id="validateType" class="list-tpye" onchange="submitForm2()">
                     	<option value="-1">全部卡券</option>
		        		<option value="0" <c:if test="${couponCfg == 0 }"> selected="selected"</c:if>>礼品券</option>
		        		<option value="1" <c:if test="${couponCfg == 1 }"> selected="selected"</c:if>>代金券</option>
                        <option value="2" <c:if test="${couponCfg == 2 }"> selected="selected"</c:if>>折扣券</option>
		        	</select>
                    </th> 
                    <th class="td160">店铺名称</th>
                    <th class="td100">店铺ID</th>
                    <th class="td160">卡券名称</th>  
                    <th class="td100">认证编号</th>            
                    <th class="td160">核销时间</th>
                    <th class="td100">用户昵称</th>
                </tr>
                <c:forEach items="${page2 }" var="c">
                <tr>
                    <td class="td160">
						<c:if test="${c.couponCfg == 0 }">礼品券</c:if>
						<c:if test="${c.couponCfg == 1 }">代金券</c:if>
						<c:if test="${c.couponCfg == 2 }">折扣券</c:if>
					</td> 
                    <td class="td160">${c.storeName }</td>
                    <td class="td100">${c.storeId }</td>                 
                    <td class="td160">${c.name }</td>
                    <td class="td100">${c.code }</td>            
                    <td class="td160">${c.valiDate }</td>
                    <td class="td100">${c.nickName }</td> 
                </tr>
                </c:forEach>
              </table>
              </form>
			</div>
			<div class="page">
			<c:if test="${page2.getPageNum()>1}">
				<a href="javascript:previousPage(${page2.getPageNum()-1},'from2','page')" class="prev">上一页</a>
			</c:if>
			<c:choose>
				<c:when test="${page2.getPages()<7}">
					<c:forEach var="i" begin="1" end="${page2.getPages()}">
						<c:choose>
							<c:when test="${i==page2.getPageNum()}">
								<em class="current">${i}</em>
							</c:when>
							<c:otherwise>
								<a href="javascript:previousPage(${i},'from2','page')">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:when test="${page2.getPages()>6}">
					<c:forEach var="i" begin="1" end="3">
						<c:choose>
							<c:when test="${i==page2.getPageNum()}">
								<em class="current">${i}</em>
							</c:when>
							<c:otherwise>
								<a href="javascript:previousPage(${i},'from2','page')">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${page2.getPageNum()>4}">
						<em>...</em>
					</c:if>
					<c:forEach var="i" begin="4" end="${page2.getPages()-3}">
						<c:if test="${i==page2.getPageNum()}">
							<em class="current">${i}</em>
						</c:if>
					</c:forEach>
					<c:if test="${page2.getPageNum()<page2.getPages()-3}">
						<em>...</em>
					</c:if>
					<c:forEach var="i" begin="${page2.getPages()-2}" end="${page2.getPages()}">
						<c:choose>
							<c:when test="${i==page2.getPageNum()}">
								<em class="current">${i}</em>
							</c:when>
							<c:otherwise>
								<a href="javascript:previousPage(${i},'from','page')">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
			</c:choose>
			<c:if test="${page2.getPages()>page2.getPageNum()}">
				<a href="javascript:previousPage(${page2.getPageNum()+1},'from2','page')" class="next">下一页</a>
			</c:if>
			<span>共${page2.getPages()}页</span><span>到第</span> <input class="input1" id="jumPage" name="pageNum" type="text" value="${page2.getPageNum()}"
				onkeydown="if(event.keyCode==13){previousPage(this.value,'from2','page')}" /><span>页</span>
				<div class="clear"></div>
		</div>
         </c:if>
        </div>
  </div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
