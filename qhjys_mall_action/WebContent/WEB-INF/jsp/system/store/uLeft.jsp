<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!----------------左侧----------------------->
<div class="memberleft">
	<!--左侧菜单-->
	<div class="membermenu">
		<ul>
			<li>
				<h1>商家管理</h1>
				<p>
					<a href="storeAccountList.do" class="${param.position eq '2'?'menucurrent2':''}"><span>·</span>商家列表</a>
				<p>
					<a href="list.do" class="${param.position eq '1'?'menucurrent2':''}"><span>·</span>店铺列表</a>
				</p>
				<p>
					<a href="storeCheckLsit.do" class="${param.position eq '3'?'menucurrent2':''}"><span>·</span>店铺修改列表</a>
				</p>
			</li>
			
			<li>
				<p>
					<a href="/managermall/systemmall/store/wxlist.do" class="${param.position eq '4'?'menucurrent2':''}"><span>·</span>微信店铺列表</a>
				</p>
				<p>
					<a href="/managermall/systemmall/store/wxChecklist.do" class="${param.position eq '5'?'menucurrent2':''}"><span>·</span>微信店铺修改列表</a>
				</p>
				<p>
					<a href="/managermall/systemmall/store/queryFqStoreApply.do" class="${param.position eq '6'?'menucurrent2':''}"><span>·</span>微信店铺开通申请</a>
				</p>
			</li>
		</ul>
	</div>
</div>