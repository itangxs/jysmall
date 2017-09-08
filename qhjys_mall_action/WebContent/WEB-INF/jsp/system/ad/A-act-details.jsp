<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link href="/qhjys_mall/seller-manage/css/A-act-details.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/system/activity/list.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="val" value="3" /></jsp:include>
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
                         	<p>小肥羊券1</p>
                        </div> 
                    	<div class="clearfix"></div>
    				</div>
       			 	<div class="contact-box">
                    	<div class="left-part">
    						<label>商家ID：</label>
    
            			</div>
                        <div class="right-part">
                         	<p>1635(小肥羊)</p>
                        </div> 
                        <div class="clearfix"></div>
    				</div>
       		
        			<div class="contact-box">
                    	<div class="left-part">
    						<label>活动时间：</label>
           
            			</div>
                        <div class="right-part">
                         	 <p>2016年8月15 至 2016年9月15</p>
                        </div> 
                        <div class="clearfix"></div>
    				</div>
       		
        			<div class="contact-box">
                    	<div class="left-part">
    						<label>每日活动时段：</label>
                    
            			</div>
                        <div class="right-part">
                         	 <p>11:10 至 15:10</p>
                             <p>17:30 至 21:30</p>
                        </div>
                        <div class="clearfix"></div> 
    				</div>
        			
                     <div class="contact-box1">
                         <div class="right-part">
                         	<div class="lottery">
         						<span>单一微信账号每天可推送次数为2次</span>
							</div>
                         </div> 
                         <div class="clearfix"></div>
                     </div> 
   		 			<div class="contact-box">
                    	<div class="left-part">
    						<h2>卡券1</h2>
             			</div>
                        <div class="clearfix"></div>
                    </div>                     
                     <div class="contact-box">
                    	 <div class="left-part">   
    						 <label>名称：</label>
              
            			 </div>
                         <div class="right-part">
                         	 <p>小肥羊</p>
                        </div>
                        <div class="clearfix"></div>
                     </div>
                     <div class="contact-box">
                    	 <div class="left-part">   
    						 <label>使用规则：</label>
              
            			 </div>
                         <div class="right-part">
                         	 <p>消费满 100 元即可使用</p>
                        </div>
                        <div class="clearfix"></div>
                     </div>                        
                      <div class="contact-box">
                    	 <div class="left-part">    
    				    	 <label>描述：</label>
        
                         </div>
                         <div class="right-part">
                         	  <p>好吃又多汁羊肉</p>
                         </div>
                         <div class="clearfix"></div>
                      </div>           		 
                     <div class="contact-box2">
                    	<div class="right-part">
    						<img width="348" height="224" src="" alt="" />
             			</div>                    
                        <div class="clearfix"></div>
                      </div> 
                     
                     
            		 <div class="contact-box">
                    	<div class="left-part">
    						<h2>卡券2</h2>
             			</div>
                        <div class="clearfix"></div>
                    </div>                     
                     <div class="contact-box">
                    	 <div class="left-part">   
    						 <label>名称：</label>
              
            			 </div>
                         <div class="right-part">
                         	 <p>小肥羊</p>
                        </div>
                        <div class="clearfix"></div>
                     </div> 
                     <div class="contact-box">
                    	 <div class="left-part">   
    						 <label>使用规则：</label>
              
            			 </div>
                         <div class="right-part">
                         	 <p>消费满 100 元即可使用</p>
                        </div>
                        <div class="clearfix"></div>
                     </div>                       
                      <div class="contact-box">
                    	 <div class="left-part">    
    				    	 <label>描述：</label>
        
                         </div>
                         <div class="right-part">
                         	  <p>好吃又多汁羊肉</p>
                         </div>
                         <div class="clearfix"></div>
                      </div>           		 
                     <div class="contact-box2">
                    	<div class="right-part">
    						<img width="348" height="224" src="" alt="" />
             			</div>                    
                        <div class="clearfix"></div>
                      </div> 
                      
                      
                       
                       <div class="contact-box">
                    	<div class="left-part">
    						<h2>卡券3</h2>
             			</div>
                        <div class="clearfix"></div>
                    </div>                     
                     <div class="contact-box">
                    	 <div class="left-part">   
    						 <label>名称：</label>
              
            			 </div>
                         <div class="right-part">
                         	 <p>小肥羊</p>
                        </div>
                        <div class="clearfix"></div>
                     </div> 
                     <div class="contact-box">
                    	 <div class="left-part">   
    						 <label>使用规则：</label>
              
            			 </div>
                         <div class="right-part">
                         	 <p>消费满 100 元即可使用</p>
                        </div>
                        <div class="clearfix"></div>
                     </div>                       
                      <div class="contact-box">
                    	 <div class="left-part">    
    				    	 <label>描述：</label>
        
                         </div>
                         <div class="right-part">
                         	  <p>好吃又多汁羊肉</p>
                         </div>
                         <div class="clearfix"></div>
                      </div>           		 
                     <div class="contact-box2">
                    	<div class="right-part">
    						<img width="348" height="224" src="" alt="" />
             			</div>                    
                        <div class="clearfix"></div>
                      </div> 
                       
                       <div class="contact-box">
                    	<div class="left-part">
    						<h2>卡券4</h2>
             			</div>
                        <div class="clearfix"></div>
                    </div>                     
                     <div class="contact-box">
                    	 <div class="left-part">   
    						 <label>名称：</label>
              
            			 </div>
                         <div class="right-part">
                         	 <p>小肥羊</p>
                        </div>
                        <div class="clearfix"></div>
                     </div> 
                     <div class="contact-box">
                    	 <div class="left-part">   
    						 <label>使用规则：</label>
              
            			 </div>
                         <div class="right-part">
                         	 <p>消费满 100 元即可使用</p>
                        </div>
                        <div class="clearfix"></div>
                     </div>                       
                      <div class="contact-box">
                    	 <div class="left-part">    
    				    	 <label>描述：</label>
        
                         </div>
                         <div class="right-part">
                         	  <p>好吃又多汁羊肉</p>
                         </div>
                         <div class="clearfix"></div>
                      </div>           		 
                     <div class="contact-box2">
                    	<div class="right-part">
    						<img width="348" height="224" src="" alt="" />
             			</div>                    
                        <div class="clearfix"></div>
                      </div>  
                      
            		 <div class="contact-box">
                         <div class="right-part">
                         	   <div class="setting1">
   									<div class="set01">
   										<label>卡券1概率：</label>
                                        <span>15%</span>
     							    </div>
      							    <div class="set03">
   										<label>卡券3概率：</label>
                                        <span>25%</span>
       							    </div>  
       							    <div class="clearfix"></div>     
  							   </div>  
  							   <div class="setting2">
   									<div class="set02">
   										<label>卡券2概率：</label>
                                        <span>30%</span>
       							    </div>
        
        							<div class="set04">
   										<label>卡券4概率：</label>
                                        <span>40%</span>
      							    </div> 
      							    <div class="clearfix"></div> 
  							   </div>
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
                     		 <input id="affirm" type="button" value="修改">
   					  		 <input id="cancel" type="button" value="取消">
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
