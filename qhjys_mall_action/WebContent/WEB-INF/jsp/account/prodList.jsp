<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<link rel="stylesheet" type="text/css" href="/css/list.css" />
<script type="text/javascript" src="/js/pagingUtil.js"></script>       
</head>
<body>
<jsp:include page="uHeader.jsp" flush="true" />
<!--list广告-s-->
<div class="list_adv">
<a href="${ad1.link == null?'#':ad1.link }"><img src="${ad1.image == null?'images/list_adv.jpg':ad1.image }" /></a>
</div>
<!--list广告-e-->
<!--list-search-s-->
<div class="search_content">
	<input id="hType" type="hidden" value="${type}">
	<input id="keyW" type="hidden" value="${keywork}">
	<input id="hCate" type="hidden" value="${parCate}">
	<input id="hSort" type="hidden" value="${sort}">
	<input id="hArea" type="hidden" value="${area}">
	<input id="hMaxu" type="hidden" value="${maxUse}">
	<!--搜索选择-->
	<c:if test="${!empty category}">
	<%-- <div class="search_top">
    	<span class="topall"><a href="javascript:;">全部</a></span><em>></em>
      	<span class="search_choose">
			<span class="leibie"><a href="javascript:;">全部</a></span>
            <span class="close"><a href="javascript:;">.</a></span>      		
      		<span id="cearchCate" class="xiala" style="display:none;">
            	<a href="javascript:;" class="current">全部</a>
            	<a href="javascript:;">美食</a>
      			<c:forEach var="item" items="${category}">
      			<a href="javascript:;">${item.name}</a>
      			</c:forEach>
            </span>
    	</span>        
        <div class="clear"></div>
    </div> --%>
	</c:if>
    <!--搜索类型-->
    <div class="search_type">
        <ul id="search_type">
        	<c:if test="${!empty category}">
        	<li>
                <div class="type1">分类：</div>
                <div id="sort" class="type2">
                	<c:if test="${empty sort||parCate==sort}"><a href="javascript:void(0);" class="all"><input type="hidden" value="${parCate}">全部</a></c:if>
                	<c:if test="${!empty sort&&parCate!=sort}"><a href="javascript:void(0);"><input type="hidden" value="${parCate}">全部</a></c:if>
                	<c:forEach var="node" items="${category}">
	                	<c:if test="${node.id == sort}">
	                	<a href="javascript:void(0);" class="all"><input type="hidden" value="${node.id}">${node.name}</a>
	                	</c:if>
	                	<c:if test="${node.id != sort}">
	                	<a href="javascript:void(0);"><input type="hidden" value="${node.id}">${node.name}</a>
	                	</c:if>
                	</c:forEach>
                </div>
                <div class="clear"></div>
            </li>
        	</c:if>
        	<%-- <c:if test="${empty category}">
        	 <div class="type1">全部：</div>
        	 <c:if test="${detail.parentId==1}">美食</c:if>
        	 <c:if test="${detail.parentId==2}">电影</c:if>
        	 <c:if test="${detail.parentId==3}">购物</c:if>
        	 <c:if test="${detail.parentId==4}">旅游</c:if>
        	 <c:if test="${detail.parentId==5}">休闲娱乐</c:if>
        	 <c:if test="${detail.parentId==6}">丽人</c:if>
        	 <c:if test="${detail.parentId==7}">生活服务</c:if>
        			-- ${detail.name}
        	</c:if> --%>
      		
            <li>
                <div class="type1">区域：</div>
                <div id="area" class="type2">
                	<c:if test="${empty area}"><a href="javascript:void(0);" class="all"><input type="hidden" value="">全部</a></c:if>
                	<c:if test="${!empty area}"><a href="javascript:void(0);"><input type="hidden" value="">全部</a></c:if>
                	<c:forEach var="node" items="${areas}">
	                	<c:if test="${node.id == area}">
	                	<a href="javascript:void(0);" class="all"><input type="hidden" value="${node.id}">${node.name}</a>
	                	</c:if>
	                	<c:if test="${node.id != area}">
	                	<a href="javascript:void(0);"><input type="hidden" value="${node.id}">${node.name}</a>
	                	</c:if>
                	</c:forEach>
                </div>
                <div class="clear"></div>
            </li>
            <c:if test="${parCate==1}">
            <li>
                <div class="type1">人数：</div>
                <div id="userNum" class="type2">
                	<a href="javascript:;" class="all"><input type="hidden" value="">全部</a>
                	<a href="javascript:;"><input type="hidden" value="1">单人</a>
                	<a href="javascript:;"><input type="hidden" value="2">双人</a>
                	<a href="javascript:;"><input type="hidden" value="3">3-4人</a>
                	<a href="javascript:;"><input type="hidden" value="5">5-6人</a>
                	<a href="javascript:;"><input type="hidden" value="7">7-8人</a>
                	<a href="javascript:;"><input type="hidden" value="9">9-10人</a>
                	<a href="javascript:;"><input type="hidden" value="10">10人以上</a>
                </div>
                <div class="clear"></div>
            </li>
            </c:if>
        </ul>
    </div>
</div>
<!--list-search-e-->
<!--排序-e-->
<div class="main_sortbar">
	<ul id="sortField">
    	<li lang="default" <c:if test="${empty clause || clause=='default'}">class="current"</c:if>><a href="javascript:;">默认排序</a></li>
        <li lang="saleNum" <c:if test="${clause=='saleNum'}">class="current"</c:if>><a href="javascript:;">销量</a><span <c:if test="${clause=='saleNum'}">class="jiantoudownred"</c:if><c:if test="${clause!='saleNum'}">class="jiantoudown"</c:if>><!--当前状态箭头样式是jiantoudownred--></span></li>
        <li lang="price" <c:if test="${clause=='price'}">class="current"</c:if>><a href="javascript:;">价格</a><span <c:if test="${clause=='price'}">class="jiantouupred"</c:if><c:if test="${clause!='price'}">class="jiantouup"</c:if>><!--当前状态箭头样式是jiantouupred--></span></li>
        <li lang="avgScore" <c:if test="${clause=='avgScore'}">class="current"</c:if>><a href="javascript:;">评分</a><span <c:if test="${clause=='avgScore'}">class="jiantoudownred"</c:if><c:if test="${clause!='avgScore'}">class="jiantoudown"</c:if>><!--当前状态箭头样式是jiantoudownred--></span></li>
        <li lang="publish" <c:if test="${clause=='publish'}">class="current"</c:if>><a href="javascript:;">发布时间</a><span <c:if test="${clause=='publish'}">class="jiantoudownred"</c:if><c:if test="${clause!='publish'}">class="jiantoudown"</c:if>><!--当前状态箭头样式是jiantoudownred--></span></li>
		<li>
        	<div class="main_sortbarbk">
                <select id="priceRange">
                    <option value="0" <c:if test="${empty priceRange || priceRange==0}">selected</c:if>>价格区间</option>
                    <option value="1" <c:if test="${priceRange==1}">selected</c:if>>20元以下</option>
                    <option value="2" <c:if test="${priceRange==2}">selected</c:if>>20-50元</option>
                    <option value="3" <c:if test="${priceRange==3}">selected</c:if>>50-100元</option>
                    <option value="4" <c:if test="${priceRange==4}">selected</c:if>>100-300元</option>
                    <option value="5" <c:if test="${priceRange==5}">selected</c:if>>300-500元</option>
                    <option value="6" <c:if test="${priceRange==6}">selected</c:if>>500-1000元</option>
                    <option value="7" <c:if test="${priceRange==7}">selected</c:if>>1000元以上</option>
                </select>
        	</div>        
        </li>
	</ul>
</div>
<!--搜索到商品-s-->
<div class="contentall">
	<div class="searchpro_list">
    	<ul>
    		<c:forEach var="i" items="${page}">
    		<li>
				<a href="/getProdDetail.do?prodId=${i.id}"><img src="${fn:substring(i.images, 0, fn:indexOf(i.images, '|'))}" alt="飞券、飞券网、${i.storeName}" title="飞券、飞券网、${i.storeName}"/></a>
				<h1><a href="/getProdDetail.do?prodId=${i.id}">${i.name}</a></h1>
				<h2><a href="/getProdDetail.do?prodId=${i.id}">${i.title}</a></h2>
				<h3>￥<em><fm:formatNumber value="${i.unitPrice}"  type="currency" pattern="#,###.##"/></em>
				<span>￥<fm:formatNumber value="${i.origPrice}"  type="currency" pattern="#,###.##"/></span></h3>
            </li>
    		</c:forEach>
            <div class="clear"></div>
        </ul>
   	    <c:if test="${page.getPages()==0}">
 	    	<div align="center">
		  		<font  color="red">暂时没有数据!</font>
		    </div>
	    </c:if>
	    <form action="/searchProduct.do" method="post"  id="from">
	    	<input name="pageNum" type="hidden" id="page" value="">
	    	<input name="category"  type="hidden" value="${categoryID}">
	    	<input name="sort"   type="hidden" value="${sort}">
	    	<input name="area" type="hidden" value="${area}">
	    	<input name="maxUse" type="hidden" value="${maxUse}">
	    	<input name="clause" type="hidden" value="${clause}">
	    	<input name="type"  type="hidden"value="${type}">
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
        </div>	</div>
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
						<a href="/getProdDetail.do?prodId=${i.id}"><img src="${fn:substring(i.images,0, fn:indexOf(i.images,'|'))}" alt="飞券、飞券网、${i.storeName}" title="飞券、飞券网、${i.storeName}"/></a>
						<p><a href="/getProdDetail.do?prodId=${i.id}">${i.name}</a></p>
						<b>
							<em>¥<fm:formatNumber value="${i.unitPrice}"  type="currency" pattern="#,###.##"/></em>
							<i>¥<fm:formatNumber value="${i.origPrice}"  type="currency" pattern="#,###.##"/></i>
						</b>426504
					</li>
				</c:forEach>
            </ul>
    	</div>
    </div>
    <!--右侧-e-->
    <div class="clear"></div>
</div>
<!--搜索到商品-e-->
<jsp:include page="uFooter.jsp" flush="true" />
<script type="text/javascript" src="/js/account/prodList.js"></script>
</body>
</html>