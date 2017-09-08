<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<script type="text/javascript" src="/js/index.js"></script>
<link rel="stylesheet" type="text/css"  href="/css/index2.css"/>
<link rel="stylesheet" type="text/css"  href="/css/seller/cashier_desk.css"/>
<script type="text/javascript" src="/js/account/comm.js"></script>

<style>
.topmenu1 h2{ position:absolute; right:0; font-size:14px; font-weight:normal; color:#ccc; width:800px; margin:0 auto;}
.topmenu1 h2 em{ color:#333;}
.topmenu1 h2 ul li{ position:relative; float:left;}
.topmenu1 h2 .line{ position:absolute; display:block; margin-top:-22px; background:#ccc; height:13px; width:1px;}
.topmenu1 h2 ul li a{padding:0 10px; position:relative; z-index:301;}
.topmenu1 a.menuyes{ display:block; font-size:14px; height:36px; line-height:36px; border-left:1px solid #f7f5ee; border-right:1px solid #f7f5ee;}
.topmenu1 a.menuyes:hover{border-left:1px solid #ddd; border-right:1px solid #ddd; border-bottom:1px solid #fff; background:#fff;}
.topmenu1 a.menuyes.current{ display:block; border-left:1px solid #ddd; border-right:1px solid #ddd; border-bottom:1px solid #fff; background:#fff;}
.left-city{ float:left; font-size:14px; font-weight:normal; position:relative; z-index:299;}
.left-city h4{ position:absolute; z-index:299; left:35px; top:0px; width:90px;}
.left-city h4 a{ font-size:14px; display:block; border-left:1px solid #f7f5ee;  border-bottom:1px solid #f7f5ee; border-right:1px solid #f7f5ee; height:36px; line-height:36px; padding:0 10px;}
.left-city h4 a:hover{border-left:1px solid #ddd; border-right:1px solid #ddd;  border-bottom:1px solid #fff; background:#fff;}
.left-city h4 a.current{border-left:1px solid #ddd; border-right:1px solid #ddd;  border-bottom:1px solid #fff; background:#fff;}
.city-list{ position:absolute; z-index:298; left:55px; top:35px; background:#FFF; border:1px solid #ddd;width:200px; padding:0 0 10px 10px;}
.city-list1{ position:absolute; z-index:298;  top:35px; background:#FFF; border:1px solid #ddd;width:200px; padding:0 0 10px 10px;}
.city-list2{ position:absolute; z-index:298; left:-100px; top:35px; background:#FFF; border:1px solid #ddd;width:200px; padding:0 0 10px 10px;} 
.city-list h3{ font-size:15px; color:#f6696a;}
.city-list ul li{ float:left; margin-right:10px; line-height:20px;}
.arrow-down-logo{ display:inline-block;vertical-align:middle; width:12px; height:6px; background:url(/images/ico-jt.png) no-repeat 3px -114px;}
.servicexiala{ position:absolute; z-index:299; left:10px; top:35px; background:#FFF; border-left:1px solid #ddd;border-right:1px solid #ddd;border-bottom:1px solid #ddd;}
.servicexiala a{ display:block; width:67px; padding-left:10px;}
.weixinxiala{ position:absolute; width:200px; z-index:299; right:0; top:35px;background:#FFF;border-top:1px solid #ddd;border-left:1px solid #ddd;border-right:1px solid #ddd;border-bottom:1px solid #ddd; text-align:center; color:#333;}
</style>
<!-------------------top------------------>


	<!-- 未开通收银台取消执行js一些事件 -->
	<!-- 未开通 -->
	<c:if test="${ store.openCashier == 0}">
		<a href="javascript:void(0)" class="cashierdesklogo" onclick="document.getElementById('cashierdesk').style.display='block'; document.getElementById('fade').style.display='block';document.getElementById('shoukuan').value='';document.getElementById('shoukuanma').value='';"><img src="/images/seller/CashierDesk.png" /></a>
	</c:if>
	<!-- 开通 -->
	<c:if test="${ store.openCashier == 1}">
		<a href="javascript:void(0)" class="cashierdesklogo" onclick="document.getElementById('cashierdesk').style.display='block'; document.getElementById('fade').style.display='block';document.getElementById('shoukuan').value='';document.getElementById('shoukuanma').value='';$('#shoukuan').focus();orderList();"><img src="/images/seller/CashierDesk.png" /></a>
	</c:if>

<!--弹出幕布-->

<!--弹窗开始-->

<div id="cashierdesk" class="white_content_desk">	
	<!--收银台开始-->
    <div class="cashierbox">	
      <div class="title">飞券收银台<span><a href="javascript:void(0)" onclick="document.getElementById('cashierdesk').style.display='none';document.getElementById('fade').style.display='none';" class="closewindow">×</a></span></div>
      <c:if test="${ store.openCashier == 0}">
	      <!--未开通收银台弹框开始-->
	      <div class="zfcgcontent1">
	      	<div id="notopen" class="white_content_desk2">
	        	<div class="zfcg_tanchu">
	            	<p><img class="img350" src="/images/seller/cashierdesk_notopen.jpg" /></p>
	              <div class="notopentext">
	                  <p>您尚未开通PC收银台服务， 为保证支付过程安全稳定高效， 此服务开通须使用匹配的硬件， 如有需要欢迎致电客服热线<b class="red">400-6333-088</b>申请。</p>
	              </div>
	            </div>       
	        </div>
	      </div>
	      <!--未开通收银台弹框结束-->
      </c:if>
      <!---进度条开始--->

      <div class="zfcgcontent1" style="display:none;">        	
    	<div class="jindudonghua"><a href="javascript:void(1)" onclick="document.getElementById('zhifuchenggong').style.display='block'"><img src="/images/seller/timg.gif" /></a></div>
        <!--支付失败弹窗开始-->
        <div id="zhifushibai" class="white_content_desk1" style="display:none;">
        	<div class="zfcg_tanchubox">
                <div class="zfcg_tanchu">
                    <div class="title">支付提示</div>
                    <div class="nr">
                        <p class="textredcg1"><span class="zhifucha"></span>支付失败</p>
                        <p id="zhifushibaiinfo">支付失败，请重试</p>                
                    </div>
                    <div class="closediv"><a href="javascript:void(1)" onclick="document.getElementById('zhifushibai').style.display='none';$('.zfcgcontent1').hide();$('.zfcgcontent').hide();$('#shoukuanma').focus();" class="anniu">关闭</a></div>
                </div>
            </div>
        </div>               
        <!--支付失败弹窗结束-->
        <!--支付成功弹窗开始-->
        <div id="zhifuchenggong"  class="white_content_desk1" style="display:none;">
        	<div class="zfcg_tanchubox">
                <div class="zfcg_tanchu">
                    <div class="title">支付提示</div>
                    <div class="nr">
                        <p class="textbluecg"><span class="zhifugou"></span><span>支付成功</span></p>
                        <p class="textredcg">收款金额：￥0.01</p>
                        <p>支付成功，5秒以后返回</p>
                    </div>
                    <div class="closediv"><a href="javascript:void(1)" onclick="document.getElementById('zhifuchenggong').style.display='none';$('.zfcgcontent1').hide();$('.zfcgcontent').hide();$('#shoukuan').focus();" class="anniu">关闭</a></div>
                </div>
            </div>
        </div>               
        <!--支付成功弹窗结束-->
    </div>    
    
    <!-- 未开通时候的遮罩层 -->
	<c:if test="${ store.openCashier == 0}">
    		<div class="zfcgcontent" style=""></div>
    </c:if>
    <!---进度条结束--->

    <div style="display:;">
      <div class="cashiercontent">
          <div class="cashierform">
              <div class="inputbox">
                  <p class="skje">收款金额</p>
                  <input class="input1" id="shoukuan" name="" type="text" value="" onfocus="this.style.color='black'" />
              </div>      	
              <div class="inputbox">
                  <p class="skm">收款码</p>
                  <input class="input2" id="shoukuanma" name="" type="text" value="" onfocus="this.style.color='black'" />
              </div>            
            <p class="textred">*用扫描设备扫描或手动输入客户手机的付款码</p>
            <a href="/managermall/seller/funds/advancelist.do" class="zfddbutton"><span>查看支付订单</span></a>
          </div>
          <article class="keyboardbox" id="keyboard" onload="javascript:form1.shoukuan.focus();">
              <script type="text/javascript" src="/js/seller/keyboard_cashierdesk1.js"></script>
              <script type="text/javascript">
              (function(){
                  var input1 = document.getElementById('shoukuan');
                  var input2 = document.getElementById('shoukuanma'); 
                  //alert("1")
                      new KeyBoard(input1);
                   // alert("2")
                  input1.click = function(){
                      new KeyBoard(input1);
                  };
                  input2.click = function(){
                      new KeyBoard(input2);
                  };

              })();
              </script>
          </article>
          <div class="clear"></div>
      </div>
    </div>
      <div class="tablebox">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <th class="">最新支付订单</th>
              <th class="tdw230">支付方式</th>
              <th class="tdw230">收款时间</th>
            </tr>
            <c:forEach items="${orderList }" varStatus="status" var="order">
	            <tr>
	              <c:if test="${status.index==0 }">
	              	<td class="alignleft"><span class="one">1</span>成功收款￥${order.money }</td>
	              </c:if>
	              <c:if test="${status.index==1 }">
	             	<td class="alignleft"><span class="two">2</span>成功收款￥${order.money }</td>
	              </c:if>
	              <c:if test="${status.index==2 }">
	              	<td class="alignleft"><span class="three">3</span>成功收款￥${order.money }</td>
	              </c:if>
	              <td>
	              	<c:if test="${order.type==1 || order.type==3 || order.type==5}">
	              		<!--微信--><img class="imgc" src="/images/seller/cashier_weixin.jpg" />
	              	</c:if>
	                <c:if test="${order.type==2 || order.type==6}">
	              		<!--支付宝--><img class="imgc" src="/images/seller/cashier_alipay.jpg" />
	              	</c:if>
	              	<c:if test="${order.type==7 }">
	              		<!--qq钱包--><img class="imgc" src="/images/seller/cashier_qq.jpg" />
	              	</c:if> 
	              	<c:if test="${order.type==8 }">
	              		<!--百度钱包--><img style="display:none;" class="imgc" src="/images/seller/cashier_baidu.jpg" />
	              	</c:if> 
	                  <!--京东钱包--><img style="display:none;" class="imgc" src="/images/seller/cashier_jd.jpg" />
	                  <!--银联支付--><img style="display:none;" class="imgc" src="/images/seller/cashier_UnionPay.jpg" />
	              </td>
	              <td>
	             	 <fm:formatDate value="${order.payTime }" pattern="yyyy-MM-dd HH:mm" />
	              </td>
	            </tr>
            </c:forEach>
          </table>
      </div>
  </div>	
	<!--收银台结束-->
</div>
<div id="fade" class="black_overlay"></div>
<!--弹窗结束-->

<!--top菜单-->
<div class="topmenu">	
	
    <div class="topmenu1">
 
	<h2>
		<ul><li>
				<span style="color: #555;">您好，${seller.username} 欢迎来到飞券网！</span>
			<!-- 	<span><a href="login.do">登录</a></span>
				<span><a href="registration.do">注册</a></span> -->
			<c:if test="${!empty seller}"><a href="/seller/out.do">退出</a></c:if> </li>
           
				<li>
			 <!--  <h4 >
            	<a id="switchCity1" href="javascript:;" class="menuyes">服务中心<span class="arrow-down-logo"></span></a>
			 </h4>
			 <div id="citys1" class="servicexiala" style="display:none;z-index: 9999;width: 88px;">
						    <ul>
								<li><a href="/mallcms/info3.do?id=24">使用指南</a></li>
								<li><a href="/mallcms/info.do?id=5">关于我们</a></li>
							 </ul>
			</div> -->
				
					<!-- <a href="javascript:;">服务中心<span class="arrow-down-logo"></span></a><span class="line"></span>
					<div class="servicexiala" style="display:none;">
						<a href="javascript:;">购物指南</a><a href="javascript:;">售后服务</a><a href="javascript:;">支付方式</a><a href="javascript:;">关于我们</a>
					</div> -->
				<!-- </li>
				<li>
				
					<h4>
						<a id="switchCity2" href="javascript:;" class="menuyes">微信公众号<span class="arrow-down-logo"></span></a>
					</h4>
					<div id="citys2" class="city-list2" style="text-align: center;display:none;">
						<p style="text-align: center;">飞券网：jysmall</p>
						<P style="text-align: center;">
							<img src="/images/weixin.jpg" />
						</P >
						<p style="text-align: center;">扫一扫，关注飞券网公众号</p>
					</div> -->
					
					
					<!-- <a href="javascript:;" class="menuyes">微信公众号
					<span class="arrow-down-logo"></span></a><span class="line"></span>
					<div class="weixinxiala" style="display: none">
						<p>金钥匙商城：jysmall</p>
						<P>
							<img src="/images/weixin.jpg" />
						</P>
						<p>扫一扫，关注金钥匙公众号</p>
					</div> -->
			<!-- 	</li>-->
			</ul>
			</h2> 
 
	 
	</div>
</div>
<div class="wrapper01">
	<!--top广告-->
	<div class="top_center">
		<h1>
			<a href="/" style="float:left;margin-bottom:0px;"><img src="/images/seller/logo.png" /></a>
		</h1>
        <h1>
			<a href="/managermall/seller/redpack/activityDetail.do" style="float:right;margin-bottom:0px;"><img src="/images/seller/top-ad.png" /></a>
		</h1>
	</div>
</div>
<!----------------主导航条--------------->
<div class="top_nav">
	<div class="wrapper">
		<h1>
			<!--<a href="/managermall/seller/center/page.do" class="${param.val eq '1'?'top_nav_current':''}">商家中心</a>-->
            <a href="/managermall/seller/funds/account/overview.do" class="${param.val eq '1'?'top_nav_current':''}">商家中心</a>
			<a href="/managermall/seller/center/center.do" class="${param.val eq '2'?'top_nav_current':''}">账号管理</a>
			<!-- <a href="/managermall/seller/message/messageCenterList.do" class="${param.val eq '3'?'top_nav_current':''}">消息中心</a> -->
		</h1>
		<div class="clear"></div>
	</div>
</div>