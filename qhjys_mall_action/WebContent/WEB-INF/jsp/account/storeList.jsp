<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网-店铺列表</title>
<link rel="stylesheet" type="text/css" href="/css/list.css" />
<script src="/js/pagingUtil.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
	<!--list广告-s-->
	<div class="list_adv">
		<a href="${ad1.link == null?'#':ad1.link }"><img src="${ad1.image == null?'images/list_adv.jpg':ad1.image }" /></a>
	</div>
	<div class="search_content">
		<input id="keyW" type="hidden" value="${keywork}">
		<input id="hArea" type="hidden" value="${area}">
		<!--搜索类型-->
		<div class="search_type">
			<ul id="search_type">
				<li>
					<div class="type1">分类：</div>
					<div id="category" class="type2">
						<c:if test="${empty category}"><a href="javascript:;" class="all"><input type="hidden" value="">全部</a></c:if>
						<c:if test="${!empty category}"><a href="javascript:;"><input type="hidden" value="">全部</a></c:if>
						<c:forEach var="node" items="${list}">
							<c:if test="${node.id == category}">
							<a href="javascript:;" class="all"><input type="hidden" value="${node.id}">${node.name}</a>
							</c:if>
							<c:if test="${node.id != category}">
							<a href="javascript:;"><input type="hidden" value="${node.id}">${node.name}</a>
							</c:if>
						</c:forEach>
					</div>
					<div class="clear"></div>
				</li>
				<li>
					<div class="type1">区域：</div>
					<div id="area" class="type2">
						<c:if test="${empty area}"><a href="javascript:;" class="all"><input type="hidden" value="">全部</a></c:if>
						<c:if test="${!empty area}"><a href="javascript:;"><input type="hidden" value="">全部</a></c:if>
						<c:forEach var="node" items="${areas}">
							<c:if test="${node.id == area}">
							<a href="javascript:;" class="all"><input type="hidden" value="${node.id}">${node.name}</a>
							</c:if>
							<c:if test="${node.id != area}">
							<a href="javascript:;"><input type="hidden" value="${node.id}">${node.name}</a>
							</c:if>
						</c:forEach>
					</div>
					<div class="clear"></div>
				</li>
			</ul>
		</div>
	</div>
	<div class="main_sortbar">
		<ul id="sortField">
			<li lang="default" <c:if test="${empty clause || clause=='default'}">class="current"</c:if>><a href="javascript:;">默认排序</a></li>
			<li lang="saleNum" <c:if test="${clause=='saleNum'}">class="current"</c:if>><a href="javascript:;">销量</a><span class="jiantoudown"><!--当前状态箭头样式是jiantoudownred--></span></li>
			<li lang="avgScore" <c:if test="${clause=='avgScore'}">class="current"</c:if>><a href="javascript:;">评分</a><span class="jiantoudown"><!--当前状态箭头样式是jiantoudownred--></span></li>
		</ul>
	</div>
	<div class="contentall">
		<div class="sellerlist">
			<ul>
				<c:forEach var="i" items="${page}">
				<li>
					<a href="/getStoreDetail.do?storeId=${i.id}"><img src="${fn:substring(i.imageUrl, 0, fn:indexOf(i.imageUrl, ','))}"></a>
					<div class="sellerlist-text">
						<p><strong>${i.name}</strong></p>
						<p>地址：${i.province} ${i.city} ${i.areaName} ${i.address}</p>
						<p>电话：${i.contactTel}</p>
						<p>手机：${i.contactPhone}</p>
						<h2>
							共有 
						  <a href="/getStoreDetail.do?storeId=${i.id}#xiaofeipingjia"><span>${i.countNum}</span> </a>	
							人评价
                        </h2>
					</div>
				</li>
				</c:forEach>
			</ul>
	   	    <c:if test="${page.getPages()==0}">
		    	<div align="center">
		  			<font  color="red">暂时没有数据!</font>
		    	</div>
	    	</c:if>
		<form action="/searchStore.do" method="post"  id="from">
	    	<input name="pageNum" type="hidden" id="page" value="">
	    	<input name="keywork"  type="hidden" value="${keywork}">
	    	<input name="categoryId"  type="hidden" value="${category}">
	    	<input name="areaId"  type="hidden" value="${areaId}">
	    	<input name="sort"  type="hidden" value="${sort}">
	    </form>
	    
        <!--分页-->
       <div class="page">
            	<c:if test="${page.getPageNum()>1}"> 
            	<a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${page.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${page.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${page.getPages()-3}">
            			<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
                <span>共${page.getPages()}页</span><span>到第</span>
                <input class="input1" id="jumPage" name="page" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        	</div>	
        </div>
        <!--右侧-s-->
	<div class="contentright">
    	<!--右侧广告-->
    	<div class="right_adv">
           <a href="${ad2.link == null?'#':ad2.link }"><img src="${ad2.image == null?'images/list_right_adv.jpg':ad2.image }" /></a>
    		<!-- <a href="${ad3.link == null?'#':ad3.link }"><img src="${ad3.image == null?'images/list_right_appdown.jpg':ad3.image }" /></a> -->
        </div>
        <!--右侧推荐-->
        <div class="right_tuijian">
        	<h1><span><a href="javascript:window.location.reload();">换一批</a></span>看了又看</h1>
            <ul>
            	<c:forEach var="i" items="${prodCate}">
					<li>
						<a href="/getProdDetail.do?prodId=${i.id}"><img src="${fn:substring(i.images,0, fn:indexOf(i.images,'|'))}" /></a>
						<p><a href="/getProdDetail.do?prodId=${i.id}">${i.name}</a></p>
						<b>
							<em>¥<fm:formatNumber value="${i.unitPrice}"  type="currency" pattern="#,###.##"/></em>
							<i>¥<fm:formatNumber value="${i.origPrice}"  type="currency" pattern="#,###.##"/></i>
						</b>
					</li>
				</c:forEach>
            </ul>
    	</div>
    </div>
    <!--右侧-e-->
		<div class="clear"></div>
	</div>
	<jsp:include page="uFooter.jsp" flush="true" />
	<script type="text/javascript" src="/js/account/storeList.js"></script>
<body>
</html>