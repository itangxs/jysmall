<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/system/mallusermanage/list.js"></script>
</head>
<body>
<!-------------------top---------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="4" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/mallusermanage/uLeft.jsp" flush="true" ><jsp:param name="position" value="1" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">会员管理——会员列表</h3>
     	<form id="from" name="form1" action="list.do" method="post">
          <input id="page" name="pageNum" type="hidden">
          <p class="clearfix">
        	<label class="one" for="con-email4" >来源：</label>
        	<select class="con-email3" id="reqistSource" name="reqistSource">
        		<option value="">全部</option>
        		<option value="商城" <c:if test="${reqistSource=='商城'}">selected="selected"</c:if>>商城</option>
        		<option value="APP" <c:if test="${reqistSource=='APP'}">selected="selected"</c:if>>APP</option>
        		<option value="商城wap" <c:if test="${reqistSource=='商城wap'}">selected="selected"</c:if>>商城wap</option>
        	</select>
        	
            <label class="one" for="con-email4" >会员姓名：</label>
        	<input class="con-email4" name="userName" value="${userName}">
        	
            <label class="one" for="con-email4" >手机号码：</label>
        	<input class="con-email4" name="phone" value="${phone}">
          </p>
           <p class="clearfix"> 
        	<label class="one" for="con-email4" >创建时间：</label>
        	
        	<input id="begin" name="createStart" class="easyui-datebox con-time" value="${createStart}">
			<label class="one" for="con-email2">-</label>
			<input id="ending" name="createEnd" class="easyui-datebox con-time" value="${createEnd}"><em class="space"></em>
             
            <label class="one" for="con-email4" >启用状态：</label>    
            <select name="status" class="con-email3">
            	 <option value="">全部</option>
            	 <option value="0"  <c:if test="${status==0 }">selected="selected"</c:if>>禁用</option>
            	 <option value="1"	<c:if test="${status==1 }">selected="selected"</c:if>>启用</option>
            </select>
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
             <input type="submit" value="查询" class="submit8">
         </p>
        </form>
           <p class="clearfix">
             <input type="button" value="重置密码"  class="submit9" onclick="resetPassword()" >
             <label for="select">&nbsp;&nbsp;&nbsp;</label>
             <input type="button" value="启用" class="submit8" onclick="status1('on')" >
             <label for="select">&nbsp;&nbsp;&nbsp;</label>
             <input type="button" value="禁用" class="submit8" onclick="status1('off')">
           </p>
          <div class="member_myorder">
         	<table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                   <th class="td80">
                 	 <input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/>
                   <th class="td160">会员ID</th>
                   <th class="td160">会员姓名</th>
                   <th class="td160">会员等级</th>
                   <!-- <th class="td160">所在地区</th>   -->
                   <th class="td160">电子邮件</th>
                   <th class="td160">手机号码</th>
                   <th class="td160">来源</th>
                  <!--  <th class="td160">账户余额</th> -->
                   <th class="td160">启用状态</th>
                   <th class="td160">注册时间</th>
                   <th class="td160">操作</th>
               </tr>
              <c:forEach items="${page}" var="u">
                <tr>
                    <td class="td80">
                   	 <input name="ids" type="checkbox" value="${u.id}" onclick="xuan('dx')" /></td>
                    </td>
                    <td class="td160">${u.id}</td>
                    <td class="td160">${u.username}</td>
                    <td class="td160">${u.level}</td>
                    <!-- <td class="td160">深圳</td>   -->
                    <td class="td160">${u.email}</td>
                    <td class="td160">${u.phoneNum}</td>
                    <td class="td160">${u.reqistSource}</td>
                   <!--  <td class="td160">0.00</td>    -->
                    <td class="td160">
                  		<c:choose>
                  			<c:when test="${u.enabled==1}">启用</c:when>
                  			<c:when test="${u.enabled==0}">禁用</c:when>
                  			<c:otherwise>禁用</c:otherwise>
                  		</c:choose>
                    </td>
                     <td class="td160"><fmt:formatDate value="${u.registTime}" pattern="yyyy-MM-dd HH:mm"/></td>   
                    <td class="td160"><a href="/managermall/systemmall/malluser/detail.do?id=${u.id}">查看</a> </td>
                </tr>
              </c:forEach>
              </table>
            </div>
            <!--上一页下一页-->
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
               <input class="input1" id="jumPage" name="pageNum" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
