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
<script type="text/javascript" src="/date/jquery.min.js"></script>
<script type="text/javascript" src="/date/dateRange.js"></script>
<script type="text/javascript" src="/date/monthPicker.js"></script>
<link rel="stylesheet" type="text/css" href="/date/dateRange.css"/>
<link rel="stylesheet" type="text/css" href="/date/monthPicker.css"/>
<style>
.td80 img{width:30px;height:30px; margin:0 auto; }
</style>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/cardcoupon/cardcoupon_validate.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="29" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
        	<h3 style="padding-left:10px; margin-bottom:25px;">核销与统计</h3>
      		<div class="data-nav">
				<a href="/managermall/seller/kaquan/carddata.do"><p class="kaquan-data <c:if test="${type == 1 }">nav-orange</c:if>">卡券数据</p></a>
				<a href="/managermall/seller/kaquan/deliverydata.do"><p class="put-data <c:if test="${type == 2 }">nav-orange</c:if>">投放数据</p></a>
				<a href="/managermall/seller/kaquan/validatedata.do"><p class="hexiao-data <c:if test="${type == 3 }">nav-orange</c:if>">核销数据</p></a>
			</div>
        <br>
			
		<div class="member_myorder">
			<!--卡券数据-->
          	<c:if test="${type == 1 }">
				<div class="kaquan-data-label02">
				<form action="${url }"  method="post" id="from">
	            <input id="page" name="pageNum" type="hidden"
							value="${page.getPageNum() }">
					<div class="label-title02 clearfix">
						<p class="label-name02">优惠券数据</p>
						<p class="xiazai-label">
							<a style="color: #44b5df" href="javascript:excel()">下载表格</a>
						</p>
						<div class="time-quantum01">
							<div class="ta_date" id="div_date_demo3">
								<span class="date_title" id="date_demo3"></span>
								<a class="opt_sel" id="input_trigger_demo3" href="#">
									<i class="i_orderd"></i>
								</a>
							</div>
							<div id="datePicker"></div>
						</div>
						<div class="time-quantum02">
							<select name="time" class="date" onchange="getTime(this,1)" id="aRecent7DaysDemo3">
								<option value="">全部</option>
								<option value="2" 
									<c:if test="${time == 2 }"> selected="selected"</c:if>>昨天</option>
								<option value="7" 
									<c:if test="${time == 7 }"> selected="selected"</c:if>>最近7天</option>
								<option value="14" 
									<c:if test="${time == 14 }"> selected="selected"</c:if>>最近14天</option>
								<option value="30"
									<c:if test="${time == 30 }"> selected="selected"</c:if>>最近30天</option>
								<option value="90" 
									<c:if test="${time == 90 }"> selected="selected"</c:if>>最近90天</option>
							</select>
						</div>
					</div>
					<table id="excel" width="100%" border="0" cellspacing="0" cellpadding="0" style="margin: 0; clear: both;">
						<tr>
							<th class="td100">
								<select name="type" id="cardType" class="list-tpye" onchange="getType(this,'card')">
									<option value="">全部卡券</option>
									<option value="0">礼品券</option>
								<option value="1">代金券</option>
								<option value="2">折扣券</option>
								</select>
							</th>
							<th class="td100">卡券名称</th>
							<th class="td160" >领取</th>
							<th class="td100">核销</th>


						</tr>
						<c:forEach items="${page }" var="data">
							<tr>
								<td class="td100"><c:if test="${data.couponCfg==0 }">礼品券</c:if>
									<c:if test="${data.couponCfg==1 }">代金券</c:if> <c:if
										test="${data.couponCfg==2 }">折扣券</c:if></td>
								<td class="td100">${data.name }</td>
								<td class="td160"><span>${data.cardReceiveDisplay }</span></td>
								<td class="td100">
									${data.writeOff }
								</td>
							</tr>
						</c:forEach>
					</table>
					<c:if test="${page.getPages()==0}">
						<div align="center">
							<font color="red">暂时没有数据!</font>
						</div>
					</c:if>
					<input id="cardExcel" type="hidden" value="${templateVo.getPageNum()}" />
					<div class="page">
						<c:if test="${page.getPageNum()>1}">
							<a
								href="javascript:previousPage(${page.getPageNum()-1},'from','page')"
								class="prev">上一页</a>
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
								<c:forEach var="i" begin="${page.getPages()-2}"
									end="${page.getPages()}">
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
							<a
								href="javascript:previousPage(${page.getPageNum()+1},'from','page')"
								class="next">下一页</a>
						</c:if>
						<span>共${page.getPages()}页</span><span>到第</span> <input
							class="input1" id="jumPage" name="page" type="text"
							value="${page.getPageNum()}"
							onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}" /><span>页</span>
					</div>
					</form>
				</div>
			</c:if>
            
            
          <!--投放数据-->  
            <c:if test="${type == 2 }">
				<div class="put-data-label02">
				<form action="${url }"  method="post" id="from">
	            <input id="page" name="pageNum" type="hidden">
					<div class="label-title02 clearfix">
						<p class="label-name02">优惠券数据</p>
						<p class="xiazai-label">
							<a style="color: #44b5df" href="javascript:delivery()">下载表格</a>
						</p>
						<div class="time-quantum01">
							<div class="ta_date" id="div_date_demo3">
								<span class="date_title" id="date_demo3"></span>
								<a class="opt_sel" id="input_trigger_demo3" href="#">
									<i class="i_orderd"></i>
								</a>
							</div>
							<div id="datePicker"></div>
						</div>
						<div class="time-quantum02">
							<select name="time" class="deliveryDate date" onchange="getTime(this,2)" id="aRecent7DaysDemo3">
								<option value="">全部</option>
								<option value="2" 
									<c:if test="${time == 2 }"> selected="selected"</c:if>>昨天</option>
								<option value="7" 
									<c:if test="${time == 7 }"> selected="selected"</c:if>>最近7天</option>
								<option value="14" 
									<c:if test="${time == 14 }"> selected="selected"</c:if>>最近14天</option>
								<option value="30"
									<c:if test="${time == 30 }"> selected="selected"</c:if>>最近30天</option>
								<option value="90" 
									<c:if test="${time == 90 }"> selected="selected"</c:if>>最近90天</option>
							</select>
						</div>
					</div>

					<table id="excel1" width="100%" border="0" cellspacing="0" cellpadding="0"
						style="margin: 0; clear: both;">
						<tr>
							<th class="td160">
								<select name="type" id="deliveryType" class="list-tpye" onchange="getWay(this)">
									<option value="">全部投放方式</option>
									<option value="0">公众号直接投放</option>
									<option value="1">自营投放</option>
									<option value="2">周边投放</option>
								</select>
							</th>
							<th class="td160" >展示</th>
							<th class="td160" >领取</th>
							<th class="td160" >分享</th>
							<th class="td160" >分享领取</th>


						</tr>
						<c:forEach items="${templateVo }" var="data">
							<tr>

								<td class="td160"><c:if test="${data.getWay==0 }">公众号直接投放</c:if>
									<c:if test="${data.getWay==1 }">自营投放</c:if> <c:if
										test="${data.getWay==2 }">周边投放</c:if></td>
								<td class="td160"><span>${data.cardPushDisplay }</span></td>
								<td class="td160"><span>${data.cardReceiveDisplay }</span></td>
								<td class="td160"><span>${data.cardShareDisplay }</span></td>
								<td class="td160"><span>${data.cardShareReceiveDisplay }</span></td>


							</tr>
						</c:forEach>
					</table>
					<c:if test="${templateVo.getPages()==0}">
						<div align="center">
							<font color="red">暂时没有数据!</font>
						</div>
					</c:if>
					<div class="page">
						<c:if test="${templateVo.getPageNum()>1}">
							<a
								href="javascript:previousPage(${templateVo.getPageNum()-1},'from','page')"
								class="prev">上一页</a>
						</c:if>
						<input id="deliExcel" type="hidden" value="${templateVo.getPageNum()}" />
						<c:choose>
							<c:when test="${templateVo.getPages()<7}">
								<c:forEach var="i" begin="1" end="${templateVo.getPages()}">
									<c:choose>
										<c:when test="${i==templateVo.getPageNum()}">
											<em class="current">${i}</em>
										</c:when>
										<c:otherwise>
											<a href="javascript:previousPage(${i},'from','page')">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:when test="${templateVo.getPages()>6}">
								<c:forEach var="i" begin="1" end="3">
									<c:choose>
										<c:when test="${i==templateVo.getPageNum()}">
											<em class="current">${i}</em>
										</c:when>
										<c:otherwise>
											<a href="javascript:previousPage(${i},'from','page')">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${templateVo.getPageNum()>4}">
									<em>...</em>
								</c:if>
								<c:forEach var="i" begin="4" end="${templateVo.getPages()-3}">
									<c:if test="${i==templateVo.getPageNum()}">
										<em class="current">${i}</em>
									</c:if>
								</c:forEach>
								<c:if test="${templateVo.getPageNum()<templateVo.getPages()-3}">
									<em>...</em>
								</c:if>
								<c:forEach var="i" begin="${templateVo.getPages()-2}"
									end="${templateVo.getPages()}">
									<c:choose>
										<c:when test="${i==templateVo.getPageNum()}">
											<em class="current">${i}</em>
										</c:when>
										<c:otherwise>
											<a href="javascript:previousPage(${i},'from','page')">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
						</c:choose>
						<c:if test="${templateVo.getPages()>templateVo.getPageNum()}">
							<a
								href="javascript:previousPage(${templateVo.getPageNum()+1},'from','page')"
								class="next">下一页</a>
						</c:if>
						<span>共${templateVo.getPages()}页</span><span>到第</span> <input
							class="input1" id="jumPage" name="page" type="text"
							value="${templateVo.getPageNum()}"
							onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}" /><span>页</span>
					</div>
				</form>
				</div>
				</c:if>
            
            
          <!--核销数据-->
			<c:if test="${type == 3 }">
				<div class="hexiao-data-label02">	
				<form action="${url }"  method="post" id="from">
	            <input id="page" name="pageNum" type="hidden">
					<div class="label-title02 clearfix">
				        <p class="label-name02">核销数据</p>
						<p class="xiazai-label">
							<a style="color: #44b5df" href="javascript:validate()">下载表格</a>
						</p>
					<div class="time-quantum01">
						<div class="ta_date" id="div_date_demo3">
							<span class="date_title" id="date_demo3"></span>
							<a class="opt_sel" id="input_trigger_demo3" href="#">
								<i class="i_orderd"></i>
							</a>
						</div>
						<div id="datePicker"></div>
					</div>
					<div class="time-quantum02">
						<select name="time" class="validateDate date" onchange="getTime(this,3)" id="aRecent7DaysDemo3">
							<option value="">全部</option>
							<option value="2" 
								<c:if test="${time == 2 }"> selected="selected"</c:if>>昨天</option>
							<option value="7" 
								<c:if test="${time == 7 }"> selected="selected"</c:if>>最近7天</option>
							<option value="14" 
								<c:if test="${time == 14 }"> selected="selected"</c:if>>最近14天</option>
							<option value="30"
								<c:if test="${time == 30 }"> selected="selected"</c:if>>最近30天</option>
							<option value="90" 
								<c:if test="${time == 90 }"> selected="selected"</c:if>>最近90天</option>
						</select>
					</div>           
				</div>
				<table id="excel2" width="100%" border="0" cellspacing="0" cellpadding="0" style="margin:0;clear:both;">
					<tr> 
						<th class="td160"> 
							<select name="type2" id="validateType" class="list-tpye" onchange="getType(this,'validate')">
								<option value="">全部卡券</option>
								<option value="0" <c:if test="${type2 == 0 }"> selected="selected"</c:if>>礼品券</option>
								<option value="1" <c:if test="${type2 == 1 }"> selected="selected"</c:if>>代金券</option>
								<option value="2" <c:if test="${type2 == 2 }"> selected="selected"</c:if>>折扣券</option>
							</select>
				         </th>
				         <th class="td160">商家名称</th>  
				         <th class="td100">商家ID</th> 
				         <th class="td160">卡券名称</th>  
				         <th class="td100">认证编号</th>            
				         <th class="td160">核销时间</th>
				         <th class="td100">用户昵称</th>
				    </tr>
					<c:forEach items="${validatePage }" var="data">
						<tr>
							<td class="td100">
								<c:if test="${data.couponCfg==0 }">礼品券</c:if>
								<c:if test="${data.couponCfg==1 }">代金券</c:if>
								<c:if test="${data.couponCfg==2 }">折扣券</c:if>
							</td>
							<td class="td160">${data.storeName }</td>
							<td class="td100">${data.storeId }</td>   
                    		<td class="td160">${data.name }</td>
                   			<td class="td100">${data.code }</td>            
                  		 	<td class="td160">
                  		 		<fm:formatDate value="${data.validateDate }" pattern="yyyy-MM-dd" />
                  		 	</td>
                   			<td class="td100">
                   				<c:if test="${data.nickName=='' || data.nickName==null }">${data.openId }</c:if>
                   				<c:if test="${data.nickName!='' && data.nickName!=null }">${data.nickName }</c:if>
                   			</td>
                   		</tr>
					</c:forEach>
				</table>
					<c:if test="${validatePage.getPages()==0}">
						<div align="center">
							<font color="red">暂时没有数据!</font>
						</div>
					</c:if>
					<input id="valiExcel" type="hidden" value="${validatePage.getPageNum()}" />
					<div class="page">
						<c:if test="${validatePage.getPageNum()>1}">
							<a href="javascript:previousPage(${validatePage.getPageNum()-1},'from','page')"	class="prev">上一页</a>
						</c:if>
						<c:choose>
							<c:when test="${validatePage.getPages()<7}">
								<c:forEach var="i" begin="1" end="${validatePage.getPages()}">
									<c:choose>
										<c:when test="${i==validatePage.getPageNum()}">
											<em class="current">${i}</em>
										</c:when>
										<c:otherwise>
											<a href="javascript:previousPage(${i},'from','page')">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:when test="${validatePage.getPages()>6}">
								<c:forEach var="i" begin="1" end="3">
									<c:choose>
										<c:when test="${i==validatePage.getPageNum()}">
											<em class="current">${i}</em>
										</c:when>
										<c:otherwise>
											<a href="javascript:previousPage(${i},'from','page')">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${validatePage.getPageNum()>4}">
									<em>...</em>
								</c:if>
								<c:forEach var="i" begin="4" end="${validatePage.getPages()-3}">
									<c:if test="${i==validatePage.getPageNum()}">
										<em class="current">${i}</em>
									</c:if>
								</c:forEach>
								<c:if test="${validatePage.getPageNum()<validatePage.getPages()-3}">
									<em>...</em>
								</c:if>
								<c:forEach var="i" begin="${validatePage.getPages()-2}"
									end="${validatePage.getPages()}">
									<c:choose>
										<c:when test="${i==validatePage.getPageNum()}">
											<em class="current">${i}</em>
										</c:when>
										<c:otherwise>
											<a href="javascript:previousPage(${i},'from','page')">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
						</c:choose>
						<c:if test="${validatePage.getPages()>validatePage.getPageNum()}">
							<a href="javascript:previousPage(${validatePage.getPageNum()+1},'from','page')"
							class="next">下一页</a>
						</c:if>
						<span>共${validatePage.getPages()}页</span><span>到第</span> <input
							class="input1" id="jumPage" name="page" type="text"
							value="${validatePage.getPageNum()}"
							onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}" /><span>页</span>
					</div>
				</form>
				</div>
			</c:if>
				<input type="hidden" id="startDateinp" value="${startDate }">
				<input type="hidden" id="endDateinp" value="${endDate }">
             
			<!-- <script>
					$(".put-data").click(
   					 function(){
						$(".hexiao-data-label02").hide()
						$(".kaquan-data-label02").hide()
						$(".put-data-label02").show()
						$(".put-data").addClass("nav-orange")
						$(".kaquan-data").removeClass("nav-orange")
						$(".hexiao-data").removeClass("nav-orange")
							}
					)
					
					$(".kaquan-data").click(
   					 function(){
						$(".put-data-label02").hide()
						$(".hexiao-data-label02").hide()
						$(".kaquan-data-label02").show()
						$(".kaquan-data").addClass("nav-orange")
						$(".put-data").removeClass("nav-orange")
						$(".hexiao-data").removeClass("nav-orange")
							}
					)
					
					$(".hexiao-data").click(
   					 function(){
						$(".put-data-label02").hide()
						$(".kaquan-data-label02").hide()
						$(".hexiao-data-label02").show()
						$(".hexiao-data").addClass("nav-orange")
						$(".kaquan-data").removeClass("nav-orange")
						$(".put-data").removeClass("nav-orange")
							}
					)

			</script>       -->   
             <script type="text/javascript">
	            if('${card}' != null) {
				  	 $("#cardType").find("option[value='${card}']").attr("selected",true);
				}
	            if('${delivery}' != null) {
				  	 $("#deliveryType").find("option[value='${delivery}']").attr("selected",true);
				}
	            if('${validate}' != null) {
				  	 $("#validateType").find("option[value='${validate}']").attr("selected",true);
				}
				if('${time}' != null) {
				  	 $("#aRecent7DaysDemo3").find("option[value='${time}']").attr("selected",true);
				}
			</script>
        </div>
			
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>