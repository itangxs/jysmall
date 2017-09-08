<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/system/store/store_list.js"></script>
<script type="text/javascript" src="/js/system/qdmanage/fqTeam.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="8" /></jsp:include>
<!--------------------------我的账户-------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/statistics/uLeft.jsp"><jsp:param name="position"  value="10"/></jsp:include>
	<!-- end -->
		<div class="memberright">
             <h3 style="padding-left:10px; margin-bottom:25px;">团队长列表</h3>
        <form id="from"  class="zcform1" method="post" action="/managermall/systemmall/qdmanage/tdzList.do" onsubmit="return keyword();">
         <input id="page" name="pageNum" type="hidden">
          <p class="clearfix">
        	<label class="one" for="con-email4" >创建时间：</label>
        	<input id="begin" name="createStart" class="easyui-datebox con-time" value="${createStart}">
			<label class="one" for="con-email2">-</label>
			<input id="ending" name="createEnd" class="easyui-datebox con-time" value="${createEnd}">
            <label for="select">&nbsp;&nbsp;</label>
			<label class="one" for="con-email3" >团队长：</label>
        	<input class="con-email3" name="principal" value="${principal }">
            <label for="select">&nbsp;&nbsp;</label>
			<label class="one" for="con-email3" >电话：</label>
        	<input class="con-email3" name="phoneNum" value="${phoneNum }">      
       	  </p>

           <p class="clearfix">
        	<label class="one" for="con-email4" >公司名称：</label>
        	<input class="con-email4" name="branchName" value="${branchName }">
            <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        	<label class="one" for="con-email4" >团队长ID：</label>
        	<input class="con-email4" id="tId" name="id" value="${id }">
            <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input type="submit" value="查询" class="submit8">
            <input type="button" value="导出报表" class="submit9" onclick="Excel()">
             <input type="button" value="新增" class="submit8" onclick = "document.getElementById('qud-xz').style.display='block';document.getElementById('fade').style.display='block'">
           </p>
  
        </form>
        
        <div class="member_myorder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th class="td80">创建时间</th>
				    <th class="td50">团队长ID</th>
				    <th class="td80">公司名称</th>
				    <th class="td50">团队长名称</th>                            
				    <th class="td50">电话</th>
				    <th class="td80">操作</th>
				</tr>
              	<c:forEach items="${page}" var="fqTeam">
              		<tr>
	                	<td class="td80"><fmt:formatDate value="${fqTeam.createTime }" pattern="yyyy-MM-dd "/></td>
	                    <td class="td50">${fqTeam.id }</td>
	                    <td class="td80">${fqTeam.branchName }</td>
	                    <td class="td50">${fqTeam.principal }</td>
	                    <td class="td50">${fqTeam.phoneNum }</td>
	                    <td class="td80"><a href="javascript:clickDelete(${fqTeam.id })">删除</a> | <a href="javascript:detail(${fqTeam.id })">修改</a></td>
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


<!--服务商列表新增弹出层-->
<div id="qud-xz" class="white_content">
	<p class="close03">新增</p>
    <div class="nr nr01">
    	<div class="fuws_detail tdz-detail">
          <div class="bangd_left fuws_left">
          	 <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 姓名：</label>        
        	 </p>
             <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 电话：</label>        
        	 </p>
             
             <p class="clearfix">
            		<label class="con-email130" for="con-email4"><span class="textred">*</span> 归属公司：</label>
             </p>       
          </div>
          <div class="bangd_right fuws_right">
				<p class="clearfix">
                	<input id="principal" value="" class="w180">
                </p>
                <p class="clearfix">
                	<input id="phoneNum" value="" class="w180">
                </p>
                 <p class="clearfix">
            		 <select class="h30" id="branchId">
                    	<option>请选择</option>
                        <c:forEach items="${fqBranchs }" var="fb">
                        	<option value="${fb.id }">${fb.branchName }</option>
                        </c:forEach>
                    </select>
           	 	</p>

          </div>
        </div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('qud-xz').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:add()" style=" background-color:#64a1ce;" onclick = "">确认</a>
                                                        
     </div>   
</div>

<!--服务商列表修改弹出层-->
<div id="qud-xg" class="white_content">
	<p class="close03">修改</p>
    <div class="nr nr01">
    	<div class="fuws_detail tdz-detail">
          <div class="bangd_left fuws_left">
          	 <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 姓名：</label>        
        	 </p>
             <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 电话：</label>        
        	 </p>
             
             <p class="clearfix">
            		<label class="con-email130" for="con-email4"><span class="textred">*</span> 归属公司：</label>
             </p>       
          </div>
          <div class="bangd_right fuws_right">
				<p class="clearfix" >
                	<input id="principal1" value="" class="w180 bg-gray" onclick="alert('姓名不能修改！');" onfocus="this.blur()">
                </p>
                <p class="clearfix">
                	<input id="phoneNum1" value="" class="w180">
                </p>
                 <p class="clearfix">
            		 <select class="h30" id="branchId1">
                    	<option value="">请选择</option>
                        <c:forEach items="${fqBranchs }" var="fb">
                        	<option value="${fb.id }">${fb.branchName }</option>
                        </c:forEach>
                    </select>
           	 	</p>

          </div>
        </div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('qud-xg').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:update()" style=" background-color:#64a1ce;" onclick = "">确认</a>
                                                        
     </div>   
</div>

<!--服务商列表删除弹出层：第一种情况-->
	<div id="qud-sc01" class="white_content">
		<p class="close03">删除</p>
		<div class="nr nr01">
			<div class="kaquan_list">
				<p style="line-height:40px; font-size:24px">你确定要删除该信息吗？<p>
				<span id="tdz_id"></span>
			</div>
		</div>
		<div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('qud-sc01').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:deleteFqTeam()" style=" background-color:#64a1ce;" onclick = "">确认</a>
                                                        
     </div> 
	</div>
    
    <!--服务商列表删除弹出层：第二种情况-->
	<div id="qud-sc02" class="white_content">
		<p class="close03">删除</p>
		<div class="nr nr01">
			<div class="kaquan_list">
				<p style="line-height:40px; font-size:24px">该团队长有绑定的业务员信息不能删除！<p>
			</div>
		</div>
		<div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('qud-sc02').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
                                                        
     </div> 
	</div>
 

<div id="fade" class="black_overlay"></div>
</body>

</html>
