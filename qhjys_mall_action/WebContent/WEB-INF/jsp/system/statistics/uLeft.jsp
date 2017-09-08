<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="memberleft">
    	<!--左侧菜单-->
   	  <div class="membermenu">
        	<ul>
            	<li>
                	<h1>销售统计</h1>
                    <p><a href="/managermall/systemmall/statistics/salesRankingList.do" class="${param.position eq '1'?'menucurrent2':''}" ><span>·</span>商品销售排行报表</a></p>    
                    <p><a href="/managermall/systemmall/statistics/salesDetailList.do"  class="${param.position eq '2'?'menucurrent2':''}" ><span>·</span>商品销售明细报表</a></p>   
                    <p><a href="/managermall/systemmall/statistics/salesDiscountList.do"  class="${param.position eq '7'?'menucurrent2':''}" ><span>·</span>折扣明细报表</a></p>                    
                    <p><a href="/managermall/systemmall/statistics/discountList.do"  class="${param.position eq '8'?'menucurrent2':''}" ><span>·</span>支付明细报表</a></p>
                    <p><a href="/managermall/systemmall/statistics/cashDiscountList.do"  class="${param.position eq '12'?'menucurrent2':''}" ><span>·</span>套现订单表</a></p>
                    <p><a href="/managermall/systemmall/statistics/storeAnalyzeList.do"  class="${param.position eq '13'?'menucurrent2':''}" ><span>·</span>店铺分析报表</a></p>
                    <p><a href="/managermall/systemmall/statistics/accountingList.do"  class="${param.position eq '14'?'menucurrent2':''}"><span>·</span>已核算业绩报表</a></p>
                    <p><a href="/managermall/systemmall/statistics/outstandingList.do"  class="${param.position eq '15'?'menucurrent2':''}"><span>·</span>未核算业绩报表</a></p>                    
                </li>      
           </ul>   
           <ul>
            	<li>
                	<h1>店铺统计</h1>
                    <p><a href="/managermall/systemmall/statistics/shopAreaList.do"  class="${param.position eq '3'?'menucurrent2':''}" ><span>·</span>店铺区域分布</a></p>    
                    <p><a href="/managermall/systemmall/statistics/shopSalesList.do"  class="${param.position eq '4'?'menucurrent2':''}" ><span>·</span>店铺销售额统计</a></p>
                    <p><a href="/managermall/systemmall/statistics/shopDaySalesList.do"  class="${param.position eq '5'?'menucurrent2':''}" ><span>·</span>店铺每日收入报表</a></p>                    
                    <p><a href="/managermall/systemmall/statistics/shopMonthSalesList.do"  class="${param.position eq '6'?'menucurrent2':''}"><span>·</span>店铺月收入报表</a></p>                               
                </li>      
           </ul>
           <ul>
            	<li>
                	<h1>渠道管理</h1>
                    <p><a href="/managermall/systemmall/qdmanage/fwsList.do"  class="${param.position eq '9'?'menucurrent2':''}" ><span>·</span>服务商列表</a></p>    
                    <p><a href="/managermall/systemmall/qdmanage/tdzList.do"  class="${param.position eq '10'?'menucurrent2':''}" ><span>·</span>团队长列表</a></p>
                    <p><a href="/managermall/systemmall/qdmanage/ywyList.do"  class="${param.position eq '11'?'menucurrent2':''}" ><span>·</span>业务员列表</a></p>                             
                </li>      
           </ul>
                 		           
        </div>
        
    </div>
</body>
</html>