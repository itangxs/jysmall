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
<style>
.member_myorder{ margin-top:27px;}
.td80 img{width:30px;height:30px; margin:0 auto; }
</style>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/cardcoupon/cardcoupon_delivery.js"></script>
<script type="text/javascript" src="/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="/js/highcharts/exporting.js"></script>
<script type="text/javascript" src="/js/highcharts/highcharts-zh_CN.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="28" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
        	<h3 style="padding-left:10px; margin-bottom:25px;">投放方式</h3>
      
        <div class="member_myorder">
      		<div class="toufang-way">
            	<div class="zj-toufang zj-toufang-seller">
                	<a href = "javascript:void(0)" onclick = "document.getElementById('kaquan03').style.display='block';document.getElementById('fade').style.display='block'">
                    	<div class="text-content">
            				<h1>公众号直接投放</h1>
                			<p>通过公众号消息，直接发送卡券。通过公众号消息，直接发送卡券。</p>
                   		 	<p class="cishu">本月剩余次数：<span>${cardCouponDelivery.pushNum }次</span></p>
						</div>
                    </a>
                    
                    <div class="zj-detail">
						<h2><a href = "javascript:void(0)" onclick = "document.getElementById('zj-detail-tanc').style.display='block';">详<br>情</a></h2>
                    </div>
                    
                    <div id="zj-detail-tanc">
                    	<div class="detail-top clearfix">
                    		<div class="detail-name01">直接投放</div>
                        	<div class="detail-close"><a href = "javascript:void(0)" onclick = "document.getElementById('zj-detail-tanc').style.display='none';">关闭</a></div>
                        </div>
                        <div class="detail-center clearfix">
                        	<div id="container" class="left-chart">
                        	<c:if test="${isshow == 1 }">
                                 	<script>
                                	var chart = new Highcharts.Chart('container', {
                                	    title: {
                                	        text: '',
                                	        x: -20
                                	    },
                                	    xAxis: {
                                	        categories: ${dateStrs}
                                	    },
                                	    yAxis: {
                                	    	 title: {
                                	             text: ''
                                	         },
                                	        plotLines: [{
                                	            value: 0,
                                	            width: 1,
                                	            color: '#808080'
                                	        }]
                                	    },
                                	    legend: {
                                	        layout: 'vertical',
                                	        align: 'right',
                                	        verticalAlign: 'middle',
                                	        borderWidth: 0
                                	    },
                                	    series: [{
                                	        name: '领取',
                                	        data: ${gzlq},
                                	        color: '#44b5df'
                                	    }, {
                                	        name: '使用',
                                	        data: ${gzsy},
                                	        color: '#56caad'
                                	    }]
                                	});
                                	</script> </c:if>
                                </div>
                               
                             
                        </div>
                        
                        <div class="detail-bottom clearfix">
                        		
                                <div class="show-box"><span class="detail-blue02"></span><span>领取次数</span><span class="show-data"><c:if test="${isshow == 1 }">${gzlqAll }</c:if></span></div>
                                <div class="show-box"><span class="detail-green02"></span><span>使用次数</span><span class="show-data"><c:if test="${isshow == 1 }">${gzsyAll}</c:if></span></div>
                        </div>
                    </div>
                    
				</div>
                
            	<div class="zy-toufang zy-toufang-seller">
               	    <a href = "javascript:void(0)" onclick = "document.getElementById('zytf01').style.display='block';document.getElementById('fade').style.display='block'">
                    	<div class="text-content">
            				<h1>自营投放</h1>
                			<p>顾客通过在本店消费，获得本店卡券，顾客通过在本店消费，获得本店卡券。</p>
							<p class="xianyou01">现有投放卡券：
								<c:forEach items="${cardCouponTemplates }" var="template">
			                		<span>${template.name }</span>
		              			 </c:forEach>
							</p>
						</div>
                    </a>
                    
                     <div class="zy-detail">
						<h2><a href = "javascript:void(0)" onclick = "document.getElementById('zy-detail-tanc').style.display='block';">详<br>情</a></h2>
                    </div>
                    
                      <div id="zy-detail-tanc">
                    	<div class="detail-top clearfix">
                    		<div class="detail-name02">自营投放</div>
                        	<div class="detail-close"><a href = "javascript:void(0)" onclick = "document.getElementById('zy-detail-tanc').style.display='none';">关闭</a></div>
                        </div>
                        <div class="detail-center clearfix">
                        		<div id="container1" class="left-chart">
                               <c:if test="${isshow == 1 }">
                                	<script>
                                	var chart = new Highcharts.Chart('container1', {
                                	    title: {
                                	        text: '',
                                	        x: -20
                                	    },
                                	    xAxis: {
                                	        categories: ${dateStrs}
                                	    },
                                	    yAxis: {
                                	    	 title: {
                                	             text: ''
                                	         },
                                	        plotLines: [{
                                	            value: 0,
                                	            width: 1,
                                	            color: '#808080'
                                	        }]
                                	    },
                                	    legend: {
                                	        layout: 'vertical',
                                	        align: 'right',
                                	        verticalAlign: 'middle',
                                	        borderWidth: 0
                                	    },
                                	    series: [{
                                	        name: '领取',
                                	        data: ${zylq},
                                	        color: '#44b5df'
                                	    }, {
                                	        name: '使用',
                                	        data: ${zysy},
                                	        color: '#56caad'
                                	    }]
                                	});
                                	</script>
</c:if>
                                </div>
                                
                                 
                        </div>
                        
                        <div class="detail-bottom clearfix">
                                <div class="show-box"><span class="detail-blue02"></span><span>领取次数</span><span class="show-data"><c:if test="${isshow == 1 }">${zyAll.cardReceiveDisplay}</c:if></span></div>
                                <div class="show-box"><span class="detail-green02"></span><span>使用次数</span><span class="show-data"><c:if test="${isshow == 1 }">${zyAll.writeOff }</c:if></span></div>
                        </div>
                    </div>
                    
                   </div> 
                    
                    
                <div class="zb-toufang zb-toufang-seller">
                	<a href = "javascript:void(0)" onclick = "document.getElementById('zbtf01').style.display='block';document.getElementById('fade').style.display='block'">
                    	<div class="text-content">
           			     	<h1>周边投放</h1>
                	     	<p>顾客在本店消费，获得周边卡券，顾客在本店消费，获得周边卡券。</p>
                       	 	<p class="xianyou02">现有投放卡券：
								<c:forEach items="${cardCouponTemplates1 }" var="template">
			                		<span>${template.name }</span>
		              			 </c:forEach>
						 	</p>
						</div>
                    </a>
                    <div class="zb-detail">
						<h2><a href = "javascript:void(0)" onclick = "document.getElementById('zb-detail-tanc').style.display='block';">详<br>情</a></h2>
                    </div>
                    
                    <div id="zb-detail-tanc">
                    	<div class="detail-top clearfix">
                    		<div class="detail-name03">周边投放</div>
                        	<div class="detail-close"><a href = "javascript:void(0)" onclick = "document.getElementById('zb-detail-tanc').style.display='none';">关闭</a></div>
                        </div>
                        <div class="detail-center clearfix">
                        		<div id="container2" class="left-chart">
                              <c:if test="${isshow == 1 }">
                               <script>
                                	var chart = new Highcharts.Chart('container2', {
                                	    title: {
                                	        text: '',
                                	        x: -20
                                	    },
                                	    xAxis: {
                                	        categories: ${dateStrs}
                                	    },
                                	    yAxis: {
                                	    	 title: {
                                	             text: ''
                                	         },
                                	        plotLines: [{
                                	            value: 0,
                                	            width: 1,
                                	            color: '#808080'
                                	        }]
                                	    },
                                	    legend: {
                                	        layout: 'vertical',
                                	        align: 'right',
                                	        verticalAlign: 'middle',
                                	        borderWidth: 0
                                	    },
                                	    series: [{
                                	        name: '展示',
                                	        data: ${zbzs},
                                	        color: '#f98096'
                                	    },{
                                	        name: '领取',
                                	        data: ${zblq},
                                	        color: '#44b5df'
                                	    }, {
                                	        name: '使用',
                                	        data: ${zbsy},
                                	        color: '#56caad'
                                	    }]
                                	});
                                	</script></c:if>
                                </div>
                                
                                
                        </div>
                        
                        <div class="detail-bottom clearfix">
                        		<div class="show-box"><span class="detail-red02"></span><span>展示次数</span><span class="show-data"><c:if test="${isshow == 1 }">${zbAll.cardPushDisplay }</c:if>
                        		</span></div>
                                <div class="show-box"><span class="detail-blue02"></span><span>领取次数</span><span class="show-data"><c:if test="${isshow == 1 }">${zbAll.cardReceiveDisplay }</c:if>
                                </span></div>
                                <div class="show-box"><span class="detail-green02"></span><span>使用次数</span><span class="show-data"><c:if test="${isshow == 1 }">${zbAll.writeOff }</c:if></span></div>
                        </div> 
                    </div>
                    
				</div>       
            </div>
         
        

        </div>
			
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
 
    
  <!--公众号直接投放第一步-->
<div id="kaquan03" class="white_content">
	<p class="close-blue">直接投放</p>
    <div class="nr nr03">
    	<div class="toufang_list">
        	<div class="clearfix zj-step01" style="">
            	<div class="zj_left">
            		<p><label class="one" for="con-email2" >选择卡券：</label><label for="select">&nbsp;&nbsp;</label></p>
                </div>
                <div class="zj_right">
            		<c:forEach items="${notPut }" var="card">
                		<p><input id="${card.id }" name="test" type="checkbox" onclick="" value="${card.name }"/>
	            		<label class="one" for="con-email2" >${card.name }</label>
	            		</p>
                	</c:forEach>
                	<input type="hidden" id="pCardId">
                </div>
                
			</div>
		</div>
          <div class="toufang_tips">
        	<p>本月剩余群发次数：<span style="color:#ff8b00">${cardCouponDelivery.pushNum }次</span></p>
       	</div>

    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" border:1px solid #44b5df;color:#44b5df;" onclick = "document.getElementById('kaquan03').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
          <a href = "javascript:delivery(0)" style=" background-color:#44b5df;"  onclick = "">确认</a>
                                                  
     </div>   
</div>
  <!--公众号直接投放第二步-->
<div id="kaquan04" class="white_content">
	<p class="close-blue">直接投放</p>
    <div class="nr nr03">
    	<div class="toufang_list01"> 
    		<p class="zj-step02">是否确认群发卡券：<br><span id="cardName"></span><p>
		</div>
        <div class="toufang_tips">
        	<p>本月剩余群发次数：<span style="color:#ff8b00">${cardCouponDelivery.pushNum }次</span></p>
       	</div>

    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)"  onclick = "document.getElementById('kaquan04').style.display='none';document.getElementById('kaquan03').style.display='block'" style=" background-color:#ccc;">返回</a>
          <a href = "javascript:void(0)" style=" background-color:#44b5df;"  onclick = "updateStatus('${cardCouponDelivery.pushNum }',0)">确认</a>
                                                  
     </div>   
</div>
  <!--公众号直接投放第三步-->
<div id="kaquan05" class="white_content">
	<p class="close-blue">直接投放</p>
    <div class="nr nr03">
    	<div class="toufang_list01">
            <p class="zj-step03">您本月的群发次数已用完！<p>
		</div>
          <div class="toufang_tips">
        	<p>本月剩余群发次数：<span style="color:#ff8b00">${cardCouponDelivery.pushNum }次</span></p>
       	</div>

    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('kaquan05').style.display='none';document.getElementById('fade').style.display='none'">返回</a>
 
                                                  
     </div>   
</div>

 <!--自营投放第一步-->
<div id="zytf01" class="white_content">
	<p class="close-red">自营投放</p>
    <div class="nr nr03 nr05">
    	<div class="toufang_list02">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin:0 auto;">
                <tr>
                    <th  class="td240">现有投放卡券（${proprietaryNum }/4）</th>
                    <th class="td60">操作</th>
                </tr>
               	<tr>
               		<c:choose>
                		<c:when test="${proprietaryBySort1!=null }">
		                    <td class="td240"><span class="gl-description01">${proprietaryBySort1.name }</span><br>（抽取几率：${queryByType.firstRankProbability }%，建议投放高价值卡券）</td>
	                        <td class="td60"><a href="javascript:deleteCard('${proprietaryBySort1.id }')">删除</a></td>     
	                    </c:when>
	                    <c:otherwise>
	                    	<td class="td100">
	                    	<a href = "javascript:insert(1)" onclick = "document.getElementById('zytf01').style.display='none';document.getElementById('zytf02').style.display='block'">
	                    	<img src="/images/seller/zy-add.png"></a><br>（抽取几率：${queryByType.firstRankProbability}%，建议投放高价值卡券）</td>
                       		<td id="1"></td>
	                    </c:otherwise>  
                    </c:choose>     
                </tr>
                <tr>
                	<c:choose>
	                	<c:when test="${proprietaryBySort2!=null }">
			                    <td class="td240"><span class="gl-description01">${proprietaryBySort2.name }</span><br>（抽取几率：${queryByType.secondRankProbability}%，建议投放中高价值卡券）</td>
		                        <td class="td60"><a href="javascript:deleteCard('${proprietaryBySort2.id }')">删除</a></td>            
	                	</c:when>
	                	<c:otherwise>
		                    	<td class="td100">
		                    	<a href = "javascript:insert(2)" onclick = "document.getElementById('zytf01').style.display='none';document.getElementById('zytf02').style.display='block'">
		                    	<img src="/images/seller/zy-add.png"></a><br>（抽取几率：${queryByType.secondRankProbability}%，建议投放中高价值卡券）</td>
                        		<td id="2"></td>
		                 </c:otherwise>  
                	</c:choose> 
               	</tr>
               	<tr>
                	<c:choose>
	                	<c:when test="${proprietaryBySort3!=null }">
			                    <td class="td240"><span class="gl-description01">${proprietaryBySort3.name }</span><br>（抽取几率：${queryByType.thirdRankProbability}%，建议投放中低价值卡券）</td>
		                        <td class="td60"><a href="javascript:deleteCard('${proprietaryBySort3.id }')">删除</a></td>            
	                	</c:when>
	                	<c:otherwise>
		                    	<td class="td100">
		                    	<a href = "javascript:insert(3)" onclick = "document.getElementById('zytf01').style.display='none';document.getElementById('zytf02').style.display='block'">
		                    	<img src="/images/seller/zy-add.png"></a><br>（抽取几率：${queryByType.thirdRankProbability}%，建议投放中低价值卡券）</td>
                        		<td id="3"></td>
		                 </c:otherwise>  
                	</c:choose> 
               	</tr>
               	<tr>
                	<c:choose>
	                	<c:when test="${proprietaryBySort4!=null }">
			                    <td class="td240"><span class="gl-description01">${proprietaryBySort4.name }</span><br>（抽取几率：${queryByType.fourthRankProbability}%，建议投放低价值卡券）</td>
		                        <td class="td60"><a href="javascript:deleteCard('${proprietaryBySort4.id }')">删除</a></td>            
	                	</c:when>
	                	<c:otherwise>
		                    	<td class="td100">
		                    	<a href = "javascript:insert(4)" onclick = "document.getElementById('zytf01').style.display='none';document.getElementById('zytf02').style.display='block'">
		                    	<img src="/images/seller/zy-add.png"></a><br>（抽取几率：${queryByType.fourthRankProbability}%，建议投放低价值卡券）</td>
                        		<td id="4"></td>
		                 </c:otherwise>  
                	</c:choose> 
               	</tr>
                <input type="hidden" id="sort"/>
            </table>
		</div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style="border:1px solid #F98096;color:#F98096;" onclick = "document.getElementById('zytf01').style.display='none';document.getElementById('fade').style.display='none'">返回</a>
         
     </div>   
</div>



  <!--自营投放第二步-->
<div id="zytf02" class="white_content">
	<p class="close-red">自营投放</p>
    <div class="nr nr03">
    	<div class="toufang_list">
        	<div class="clearfix zj-step01" style="">
            	<div class="zj_left">
            		<p><label class="one" for="con-email2" >选择卡券：</label><label for="select">&nbsp;&nbsp;</label></p>
                </div>
                <div class="zj_right">
            		<c:forEach items="${notPut }" var="card">
                		<p><input id="${card.id }" name="test" type="checkbox" onclick="" value="${card.name }"/>
	            		<label class="one" for="con-email2" >${card.name }</label></p>
                	</c:forEach>
                	<input type="hidden" id="pCardId"/>
                </div>
                
			</div>
		</div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('zytf02').style.display='none';document.getElementById('zytf01').style.display='block'">返回</a>
         <a href = "javascript:delivery(1)" style=" background-color:#f98096;">确认</a>
     </div>   
</div>
  <!--自营投放第三步-->
<div id="zytf03" class="white_content">
	<p class="close-red">自营投放</p>
    <div class="nr nr03">
    	<div class="toufang_list"> 
    		<p class="zj-step02">是否确认群发卡券：<br><span id="pCardName"></span><p>
		</div>
    </div>
    <div class="kqanniu01">
    	<a href="javascript:void(0)" style=" background-color:#ccc;"  onclick = "document.getElementById('zytf03').style.display='none';document.getElementById('zytf02').style.display='block'">返回</a>
          <a href = "javascript:void(0)" style=" background-color:#f98096;"  onclick = "updateStatus('${cardCouponDelivery.pushNum }',1)">确认</a>
                                                  
     </div>   
</div>
  <!--自营投放第四步-->
<div id="zytf04" class="white_content">
	<p class="close-red">自营投放</p>
    <div class="nr nr03">
    	<div class="toufang_list">
            <p class="zj-step03">您本月的群发次数已用完！<p>
		</div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style="background-color:#ccc;" onclick = "document.getElementById('zytf04').style.display='none';document.getElementById('fade').style.display='none'">返回</a>
 
                                                  
     </div>   
</div>


<!--周边投放第一步-->
<div id="zbtf01" class="white_content">
	<p class="close-green">周边投放</p>
    <div class="nr nr03 nr05">
    	<div class="toufang_list02">
        	
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin:0 auto;">
	                 <tr>
	                    <th  class="td240">现有投放卡券（${peripheralNum }/4）</th>
	                    <th class="td60">操作</th>
	                </tr>
	                <tr>
                		<c:choose>
	                		<c:when test="${peripheralBySort1 != null }">
			                    <td class="td240"><span class="gl-description01">${peripheralBySort1.name }</span><br>（抽取几率：${queryByType2.firstRankProbability}%，建议投放高价值卡券）</td>
		                        <td class="td60"><a href="javascript:deleteCard('${peripheralBySort1.id }')">删除</a></td>     
		                    </c:when>
		                    <c:otherwise>
		                    	<td class="td100">
		                    	<a href = "javascript:insert(1)" onclick = "document.getElementById('zbtf01').style.display='none';document.getElementById('zbtf02').style.display='block'">
		                    	<img src="/images/seller/zy-add.png"></a><br>（抽取几率：${queryByType2.firstRankProbability}%，建议投放高价值卡券）</td>
                        		<td id="1"></td>
		                    </c:otherwise>  
	                    </c:choose>     
	                </tr>
	                <tr>
	                	<c:choose>
		                	<c:when test="${peripheralBySort2 != null }">
				                    <td class="td240"><span class="gl-description01">${peripheralBySort2.name }</span><br>（抽取几率：${queryByType2.secondRankProbability}%，建议投放中高价值卡券）</td>
			                        <td class="td60"><a href="javascript:deleteCard('${peripheralBySort2.id }')">删除</a></td>            
		                	</c:when>
		                	<c:otherwise>
			                    	<td class="td100">
			                    	<a href = "javascript:insert(2)" onclick = "document.getElementById('zbtf01').style.display='none';document.getElementById('zbtf02').style.display='block'">
			                    	<img src="/images/seller/zy-add.png"></a><br>（抽取几率：${queryByType2.secondRankProbability}%，建议投放中高价值卡券）</td>
	                        		<td id="2"></td>
			                 </c:otherwise>  
	                	</c:choose> 
                	</tr>
                	<tr>
	                	<c:choose>
		                	<c:when test="${peripheralBySort3 != null }">
				                    <td class="td240"><span class="gl-description01">${peripheralBySort3.name }</span><br>（抽取几率：${queryByType2.thirdRankProbability}%，建议投放中低价值卡券）</td>
			                        <td class="td60"><a href="javascript:deleteCard('${peripheralBySort3.id }')">删除</a></td>            
		                	</c:when>
		                	<c:otherwise>
			                    	<td class="td100">
			                    	<a href = "javascript:insert(3)" onclick = "document.getElementById('zbtf01').style.display='none';document.getElementById('zbtf02').style.display='block'">
			                    	<img src="/images/seller/zy-add.png"></a><br>（抽取几率：${queryByType2.thirdRankProbability}%，建议投放中低价值卡券）</td>
	                        		<td id="3"></td>
			                 </c:otherwise>  
	                	</c:choose> 
                	</tr>
                	<tr>
	                	<c:choose>
		                	<c:when test="${peripheralBySort4 != null }">
				                    <td class="td240"><span class="gl-description01">${peripheralBySort4.name }</span><br>（抽取几率：${queryByType2.fourthRankProbability}%，建议投放低价值卡券）</td>
			                        <td class="td60"><a href="javascript:deleteCard('${peripheralBySort4.id }')">删除</a></td>            
		                	</c:when>
		                	<c:otherwise>
			                    	<td class="td100">
			                    	<a href = "javascript:insert(4)" onclick = "document.getElementById('zbtf01').style.display='none';document.getElementById('zbtf02').style.display='block'">
			                    	<img src="/images/seller/zy-add.png"></a><br>（抽取几率：${queryByType2.fourthRankProbability}%，建议投放低价值卡券）</td>
	                        		<td id="4"></td>
			                 </c:otherwise>  
	                	</c:choose> 
                	</tr>
	            </table>
	    
		</div>

    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style="border:1px solid #56caad;color:#56caad;" onclick = "document.getElementById('zbtf01').style.display='none';document.getElementById('fade').style.display='none'">返回</a>
         
     </div>   
</div>



  <!--周边投放第二步-->
<div id="zbtf02" class="white_content">
	<p class="close-green">周边投放</p>
    <div class="nr nr03">
    	<div class="toufang_list">
        	<div class="clearfix zj-step01" style="">
            	<div class="zj_left">
            		<p><label class="one" for="con-email2" >选择卡券：</label><label for="select">&nbsp;&nbsp;</label></p>
                </div>
                <div class="zj_right">
            		<c:forEach items="${notPut }" var="card">
                		<p><input id="${card.id }" name="test" type="checkbox" onclick="" value="${card.name }"/>
	            		<label class="one" for="con-email2" >${card.name }</label></p>
                	</c:forEach>
                	<input type="hidden" id="pCardId"/>
                </div>
                
			</div>
		</div>

    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('zbtf02').style.display='none';document.getElementById('zbtf01').style.display='block'">返回</a>
         <a href = "javascript:delivery(2)" style=" background-color:#56caad;"  onclick = "">确认</a>
     </div>   
</div>
  <!--周边投放第三步-->
<div id="zbtf03" class="white_content">
	<p class="close-green">周边投放</p>
    <div class="nr nr03">
    	<div class="toufang_list"> 
    		<p class="zj-step02">是否确认群发卡券：<br><span id="zCardName"></span><p>
		</div>

    </div>
    <div class="kqanniu01">
    	<a href="javascript:void(0)" style=" background-color:#ccc;"  onclick = "document.getElementById('zbtf03').style.display='none';document.getElementById('zbtf02').style.display='block'">返回</a>
          <a href = "javascript:void(0)" style=" background-color:#56caad;"  onclick = "updateStatus('${cardCouponDelivery.pushNum }',2)">确认</a>
                                                  
     </div>   
</div>
  <!--周边投放第四步-->
<div id="zbtf04" class="white_content">
	<p class="close-green">周边投放</p>
    <div class="nr nr03">
    	<div class="toufang_list">
            <p class="zj-step03">您本月的群发次数已用完！<p>
		</div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('zbtf04').style.display='none';document.getElementById('fade').style.display='none'">返回</a>
 
                                                  
     </div>   
</div>

<!--弹出幕布-->
<div id="fade" class="black_overlay"></div>
</body>
</html>