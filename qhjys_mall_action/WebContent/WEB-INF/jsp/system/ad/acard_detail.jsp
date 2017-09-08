<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link href="/qhjys_mall/seller-manage/css/A-act-details.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/system/ad/acard_detail.js"></script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="position" value="" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">新建商家卡券</h3> 
          <div class="member_myorder">
            <div class="content">
					<div class="contact-box">
                    	<div class="left-part">
  							<label>活动名称：</label>
           		  		</div>
                        <div class="right-part">
                         	<p>${acard.acardName }</p>
                        </div> 
                    	<div class="clearfix"></div>
    				</div>
       			 	<div class="contact-box">
                    	<div class="left-part">
    						<label>商家ID：</label>
    
            			</div>
                        <div class="right-part">
                         	<p>${acard.storeId }(${acard.storeName })</p>
                        </div> 
                        <div class="clearfix"></div>
    				</div>
       		
        			<div class="contact-box">
                    	<div class="left-part">
    						<label>活动时间：</label>
            			</div>
                        <div class="right-part">
                         	 <fmt:formatDate value="${acard.beginDate}" pattern="yyyy-MM-dd"/>至
        					 <fmt:formatDate value="${acard.endDate}" pattern="yyyy-MM-dd"/>		
                        </div> 
                        <div class="clearfix"></div>
    				</div>
       		
        			<div class="contact-box">
                    	<div class="left-part">
    						<label>每日活动时段：</label>
            			</div>
                        <div class="right-part">
                        	<c:forEach items="${times }" var="time" varStatus="status">
                        	<p><fmt:parseNumber integerOnly="true" value="${time.startTime/60}" />点${time.startTime%60} 分
                        	 至 <fmt:parseNumber integerOnly="true" value="${time.stopTime/60}" />点${time.stopTime%60} 分
                        	</p>
                        	</c:forEach>
                        </div>
                        <div class="clearfix"></div> 
    				</div>
        			
                     <div class="contact-box1">
                         <div class="right-part">
                         	<div class="lottery">
         						<span>单一微信账号每天可推送次数为${acard.pushNum }次</span>
							</div>
                         </div> 
                         <div class="clearfix"></div>
                     </div>
                     
                    <c:forEach items="${prizes }" var="prize" varStatus="status">
                    	<div class="contact-box">
                    	<div class="left-part">
    						<h2>卡券${status.count }</h2>
             			</div>
                        <div class="clearfix"></div>
                    </div>                     
                     <div class="contact-box">
                    	 <div class="left-part">   
    						 <label>名称：</label>
              
            			 </div>
                         <div class="right-part">
                         	 <p>${prize.prizeName }</p>
                        </div>
                        <div class="clearfix"></div>
                     </div>
                     <div class="contact-box">
                    	 <div class="left-part">   
    						 <label>使用规则：</label>
            			 </div>
                         <div class="right-part">
                         	 <p>消费满 ${prize.prizeLine } 元即可使用</p>
                        </div>
                        <div class="clearfix"></div>
                     </div>                        
                      <div class="contact-box">
                    	 <div class="left-part">    
    				    	 <label>描述：</label>
        
                         </div>
                         <div class="right-part">
                         	  <p>${prize.prizeInfo }</p>
                         </div>
                         <div class="clearfix"></div>
                      </div>           		 
                     <div class="contact-box2">
                    	<div class="right-part">
    						<img width="348" height="224" src="${prize.prizeImage }" alt="" />
             			</div>                    
                        <div class="clearfix"></div>
                      </div> 
                    </c:forEach>
                      
            		 <div class="contact-box">
                         <div class="right-part">
                         	   <div class="setting1">
                         	   <c:forEach items="${prizes }" var="prize" varStatus="status">
   									<div class="set01">
   										<label>卡券${status.count }概率：</label>
                                        <span>${prize.probability }%</span>
     							    </div>
      							    <!-- <div class="set03">
   										<label>卡券3概率：</label>
                                        <span>25%</span>
       							    </div> -->
       							    <div class="clearfix"></div> 
       							    </c:forEach> 
  							   </div> 
  							   <!-- <div class="setting2">
   									<div class="set02">
   										<label>卡券2概率：</label>
                                        <span>30%</span>
       							    </div>
        
        							<div class="set04">
   										<label>卡券4概率：</label>
                                        <span>40%</span>
      							    </div> 
      							    <div class="clearfix"></div> 
  							   </div> -->
                          </div>
                          <div class="clearfix"></div>
            	     </div>
                     
                     <div class="contact-box3">
                    	 <div class="memberright">
        					<div class="excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
            				<h3 style="margin-left:20px; width:150px; background-color:#f9f9f9; border:1px #c8cbcd solid;text-align:center; line-height:36px;">活动展现报表</h3>
                            <p>活动展现人数：5300个</p>
                            
                    	    <div class="member_myorder">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<th class="td40">昵称</th>					
										<th class="td40">头像</th>
										<th class="td40">卡券名称</th>
										<th class="td80">展现时间</th>
									</tr>
                   	                <tr>
										<td class="td40">wary</td>				
										<td class="td40">
                                        	<img width="30px" height="30px" style="margin:0 auto" src="">
                                        </td>
										<td class="td40">三等奖</td>
										<td class="td80">2016年8月7日 15:23</td>
									</tr>
								</table>
							 </div>
                          </div>
                          <div class="clearfix"></div>
                   	 </div>  
                     
                    <div class="contact-box3">
                    	 <div class="memberright">
        					<div class="excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
            				<h3 style="margin-left:20px; width:150px; background-color:#f9f9f9; border:1px #c8cbcd solid;text-align:center; line-height:36px;">抽奖参与报表</h3>
                            <p>抽奖参与人数：5300个</p>
                            
                    	    <div class="member_myorder">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<th class="td40">昵称</th>					
										<th class="td40">头像</th>
										<th class="td40">卡券名称</th>
										<th class="td80">参与时间</th>
									</tr>
                   	                <tr>
										<td class="td40">wary</td>				
										<td class="td40">
                                        	<img width="30px" height="30px" style="margin:0 auto" src="">
                                        </td>
										<td class="td40">三等奖</td>
										<td class="td80">2016年8月7日 15:23</td>
									</tr>				
								</table>
							 </div>
                          </div>
                          <div class="clearfix"></div>
                   	 </div> 
                     
                      <div class="contact-box3">
                    	 <div class="memberright">
        					<div class="excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
            				<h3 style="margin-left:20px; width:150px; background-color:#f9f9f9; border:1px #c8cbcd solid;text-align:center; line-height:36px;">活动转发报表</h3>
                            <p>活动转发人数：5300个</p>
                            
                    	    <div class="member_myorder">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<th class="td40">昵称</th>					
										<th class="td40">头像</th>
										<th class="td40">卡券名称</th>
										<th class="td80">转发时间</th>
									</tr>
                   	                <tr>
										<td class="td40">wary</td>				
										<td class="td40">
                                        	<img width="30px" height="30px" style="margin:0 auto" src="">
                                        </td>
										<td class="td40">三等奖</td>
										<td class="td80">2016年8月7日 15:23</td>
									</tr>				
								</table>
							 </div>
                          </div>
                          <div class="clearfix"></div>
                   	 </div> 
                     
                      <div class="contact-box3">
                    	 <div class="memberright">
        					<div class="excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
            				<h3 style="margin-left:20px; width:150px; background-color:#f9f9f9; border:1px #c8cbcd solid;text-align:center; line-height:36px;">奖券领取报表</h3>
                            <p>奖券领取人数：5300个</p>
                            
                    	    <div class="member_myorder">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<th class="td40">昵称</th>					
										<th class="td40">头像</th>
										<th class="td40">卡券名称</th>
										<th class="td80">领取时间</th>
									</tr>
                   	                <tr>
										<td class="td40">wary</td>				
										<td class="td40">
                                        	<img width="30px" height="30px" style="margin:0 auto" src="">
                                        </td>
										<td class="td40">三等奖</td>
										<td class="td80">2016年8月7日 15:23</td>
									</tr>				
								</table>
							 </div>
                          </div>
                          <div class="clearfix"></div>
                   	 </div> 
                     
                      <div class="contact-box3">
                    	 <div class="memberright">
        					<div class="excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
            				<h3 style="margin-left:20px; width:150px; background-color:#f9f9f9; border:1px #c8cbcd solid;text-align:center; line-height:36px;">奖券核销报表</h3>
                            <p>奖券核销人数：5300个</p>
                            
                    	    <div class="member_myorder">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<th class="td40">昵称</th>					
										<th class="td40">头像</th>
										<th class="td40">卡券名称</th>
										<th class="td80">核销时间</th>
									</tr>
                   	                <tr>
										<td class="td40">wary</td>				
										<td class="td40">
                                        	<img width="30px" height="30px" style="margin:0 auto" src="">
                                        </td>
										<td class="td40">三等奖</td>
										<td class="td80">2016年8月7日 15:23</td>
									</tr>				
								</table>
							 </div>
                          </div>
                          <div class="clearfix"></div>
                   	 </div> 
                     
                      <div class="contact-box3">
                    	 <div class="memberright">
        					<div class="excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
            				<h3 style="margin-left:20px; width:150px; background-color:#f9f9f9; border:1px #c8cbcd solid;text-align:center; line-height:36px;">奖券到期报表</h3>
                            <p>奖券到期人数：5300个</p>
                            
                    	    <div class="member_myorder">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<th class="td40">昵称</th>					
										<th class="td40">头像</th>
										<th class="td40">卡券名称</th>
										<th class="td80">到期时间</th>
									</tr>
                   	                <tr>
										<td class="td40">wary</td>				
										<td class="td40">
                                        	<img width="30px" height="30px" style="margin:0 auto" src="">
                                        </td>
										<td class="td40">三等奖</td>
										<td class="td80">2016年8月7日 15:23</td>
									</tr>
								</table>
							 </div>
                          </div>
                          <div class="clearfix"></div>
                   	 </div> 
                    <div class="contact-box3"> 
                    	   <div class="right-part">
                     		 <input id="affirm" type="button" value="修改" onclick="xiugai('${acard.id}')">
   					  		 <input id="cancel" type="button" value="取消" onclick="quxiao()">
                     	  </div>
                          <div class="clearfix"></div>
                     </div>
    	</div>
              </div>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
