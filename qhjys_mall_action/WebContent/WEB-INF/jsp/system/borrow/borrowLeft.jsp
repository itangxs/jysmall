<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!----------------左侧----------------------->
<div class="memberleft">
	<!--左侧菜单-->
	<div class="membermenu">
		<ul>
			<li>
				<h1>借贷管理</h1>
				<p>
					<a href="/managermall/systemmall/borrow/wxstorelist.do" class="${param.position eq '3'?'menucurrent2':''}"><span>·</span>店铺列表</a>
				</p>
				
				<p>
					<a href="/managermall/systemmall/borrow/brstorelist.do" class="${param.position eq '1'?'menucurrent2':''}"><span>·</span>借贷店铺列表</a>
				</p>
				
				<p>
					<a href="/managermall/systemmall/borrow/allborrowlist.do" class="${param.position eq '2'?'menucurrent2':''}"><span>·</span>借贷一览</a>
				</p>
			</li>
		</ul>
	</div>
</div>