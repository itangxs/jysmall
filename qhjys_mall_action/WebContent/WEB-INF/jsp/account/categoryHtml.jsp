<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="indexleft_menu">
	<ul id="menu">
		<c:forEach var="node" items="${array}">
			<li lang="${node.id}">
				<h2 <c:if test="${node.id ==1}">class="shipin"</c:if>
					<c:if test="${node.id ==2}">class="dianying"</c:if>
					<c:if test="${node.id ==3}">class="gouwu"</c:if>
					<c:if test="${node.id ==4}">class="lvyou"</c:if>
					<c:if test="${node.id ==5}">class="xiuxianyule"</c:if>
					<c:if test="${node.id ==6}">class="liren"</c:if>
					<c:if test="${node.id ==7}">class="shenghuofuwu"</c:if>>
				</h2>
				<div class="memunav">
					<p class="menu2"><a href="/searchProduct.do?type=sort&category=${node.id}">${node.text}</a></p>
					<p class="menu1"><c:forEach var="it" items="${node.childs}">
						<a href="/searchProduct.do?type=sort&category=${node.id}&sort=${it.id}">${it.text}</a>
					</c:forEach></p>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>

<!--左侧菜单弹出窗口-start-->
<div id="menu_pop" style="display:none">
	<c:forEach var="node" items="${array}">
		<div lang="${node.id}" class="bannShow">
			<div class="indexleftmenu_tanchu" lang="${node.id}"  id="${node.id }menu" style="display:none">
                <dl>
                	<dd>
                        <c:forEach var="it" items="${node.childs}" varStatus="status">                                    
                        <a href="/searchProduct.do?type=sort&category=${node.id}&sort=${it.id}">${it.text}</a>
                        <c:if test="${status.count%12 == 0 }">
                    </dd>
                    <dd></c:if></c:forEach></dd>
                </dl>
    		</div>
    	</div>
	</c:forEach>
</div>
<!--<div id="menu_pop" class="toppro_menu" style="display:none">
	<c:forEach var="node" items="${array}">
		<div lang="${node.id}" class="bannShow">
			<div class="tanchuadv">
				<c:forEach var="img" items="${sysImg}">
					<c:if test="${img.type==node.id}">
						<a href="${img.field}"><img src="${img.img}" /></a>
					</c:if>
				</c:forEach>
			</div>
            <ul><li>
					<h1><a href="/searchProduct.do?type=sort&category=${node.id}">${node.text}</a></h1>
					<h2><c:forEach var="it" items="${node.childs}">
                   		|<a href="/searchProduct.do?type=sort&category=${node.id}&sort=${it.id}">${it.text}</a>
					</c:forEach></h2>
			</li></ul>
            		
		</div>
	</c:forEach>
</div>-->