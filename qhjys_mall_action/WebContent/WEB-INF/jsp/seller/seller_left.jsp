<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!---------------左侧----------------->
<div class="memberleft">
	<!--左侧菜单-->
	<div class="membermenu">
		<ul>
			<li>
				<h1>店铺管理</h1>
				<p>
					<a href="/managermall/seller/serviceAgreement.do" class="${param.position eq '1'?'menucurrent':''}"><span>·</span>店铺认证</a>
				</p>
				<p>
					<a href="/managermall/seller/queryStoreInfo.do" class="${param.position eq '2'?'menucurrent':''}"><span>·</span>店铺信息</a>
				</p>
				
			</li>
			<c:if test="${sessionScope.store.status == 2}">
				<li>
					<h1>资金管理</h1>
					<p>
						<a href="/managermall/seller/funds/account/overview.do" class="${param.position eq '3'?'menucurrent':''}"><span>·</span>商家账户总览</a>
					</p>
					<p>
						<a href="/managermall/seller/credit/list.do" class="${param.position eq '4'?'menucurrent':''}"><span>·</span>借贷信息总览</a>
					</p>
				
				</li>
				
	
				<li>
					<h1>交易管理</h1>
				
                    <p>
						<a href="/managermall/seller/funds/advancelist.do" class="${param.position eq '22'?'menucurrent':''}"><span>·</span>支付订单</a>
					</p>
                    <p>
						<a href="/managermall/seller/wxuser/list.do" class="${param.position eq '33'?'menucurrent':''}"><span>·</span>消费用户</a>
					</p>
				
			
				</li>
				<li>
					<h1>微信店铺</h1>
					<p>
						<a href="/managermall/seller/queryWXStoreInfo.do" class="${param.position eq '21'?'menucurrent':''}"><span>·</span>微信店铺信息</a>
					</p>
					<p>
						<a href="/managermall/seller/fqproducts/type_manager.do" class="${param.position eq '26'?'menucurrent':''}"><span>·</span>菜系管理</a>
					</p>
					<p>
						<a href="/managermall/seller/fqproducts/manager.do" class="${param.position eq '25'?'menucurrent':''}"><span>·</span>菜品管理</a>
					</p>
					
                    <p>
						<a href="/managermall/seller/fqorder/orderList.do" class="${param.position eq '24'?'menucurrent':''}"><span>·</span>订单管理</a>
					</p>
				
				</li>
				
                <li style="background:url(/images/newest.png) no-repeat 172px top;">
					<h1>营销活动</h1>
					<p>
                    	<a href="/managermall/seller/redpack/activity.do" class="${param.position eq '51'?'menucurrent':''}"><span>·</span>活动创建</a>
                        </p>
                    <p>
                    	<a href="/managermall/seller/redpack/redpacklist.do" class="${param.position eq '52'?'menucurrent':''}"><span>·</span>红包活动列表</a>
                    </p>
					
					<p>	<a href="/managermall/seller/wxmessage/list.do" class="${param.position eq '34'?'menucurrent':''}"><span>·</span>微信信息</a>
					</p><c:if test="${seller.username == 'sale123' }"></c:if>
				</li>
				<!-- -->
                <li>
					<h1>卡券管理</h1>
					<p>
						<a href="/managermall/seller/kaquan/list.do"
							class="${param.position eq '27'?'menucurrent':''}"><span>·</span>卡券货架</a>
					</p>
					<p>
						<a href="/managermall/seller/kaquan/deliverylist.do"
							class="${param.position eq '28'?'menucurrent':''}"><span>·</span>投放方式</a>
					</p>
					<p>
						<a href="/managermall/seller/kaquan/carddata.do"
							class="${param.position eq '29'?'menucurrent':''}"><span>·</span>核销与统计</a>
					</p>
					<p>
						<a href="/managermall/seller/kaquan/cardidentify.do"
							class="${param.position eq '30'?'menucurrent':''}"><span>·</span>卡券核销</a>
					</p>
				
				</li> 
			</c:if>
		</ul>
	</div>
</div>