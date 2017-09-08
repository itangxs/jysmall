<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/system/finance/user_account_record.js"></script>
<script type="text/javascript" src="/js/system/strategySet/royalty_incentive_strategy.js"></script>
<title>飞券网平台管理中心</title>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="6" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="10" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">提成奖励策略</h3>
           <form id="from" name="from"  class="zcform1" action="/managermall/systemmall/finance/royalty_incentive_strategy.do" method="post">
    			<input type="hidden" id="page" name="pageNum" value="${pageNum }">
		        <p class="clearfix">
		            <label class="one" for="con-email4" >策略名称：</label>
		        	<input class="con-email4" name="roleName" value="${roleName }" >
                    <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <label class="one" for="con-email4" >创建人：</label>
		        	<input class="con-email4" name="createName" value="${createName }" >
		        </p>
   		        <p class="clearfix">
		        	<label class="one" for="con-email4" >创建时间：</label>
					<input id="beginTime" name="beginTime"  class="con-email3"  type="text" readonly  value="${beginTime}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'beginTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		            <label class="one" for="con-email4" >-</label>
		            <input id="endTime" name="endTime"  class="con-email3"  type="text" readonly  value="${endTime}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'endTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		            <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		            <input type="submit" value="查询" class="submit8">       
		        </p>
		        <p class="clearfix">
               <input type="button" value="导出报表" class="submit9" onclick="Excel()">
               <label for="select">&nbsp;&nbsp;</label> 
               <input type="button" value="新增" class="submit9" onclick = "document.getElementById('jlcl-xz').style.display='block';document.getElementById('fade').style.display='block'">
           </p>
        	</form>
           
        <div class="member_myorder">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                	<th class="td160">项次</th>
                    <th class="td160">创建时间</th>
                    <th class="td160">创建人</th>                              
                    <th class="td160">策略名称</th>
                    <th class="td160">团队长提成比例</th>
                    <th class="td160">业务员提成比例</th>
                    <th class="td160">操作</th>
                </tr>
                <c:forEach items="${page}" var="page">
	                <tr>
                    	<td class="td160">${page.id }</td>
                        <td class="td160"><fmt:formatDate value="${page.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <td class="td160">${page.createName }</td>
                        <td class="td160">${page.roleName }</td>
                        <td class="td160">
	                        <c:if test="${!empty page.teamProportion }">${page.teamProportion }%</c:if>
	                        <c:if test="${empty page.teamProportion }">0.00%</c:if>
                        </td>
                        <td class="td160">
                       	 	<c:if test="${!empty page.clerkProportion }">${page.clerkProportion }%</c:if>
	                        <c:if test="${empty page.clerkProportion }">0.00%</c:if>
	                    </td>
                        <td class="td160"><a href = "javascript:void(0)" onclick = "clickDelete('${page.id}')">删除</a> | <a  href = "javascript:void(0)" onclick = "detail('${page.id}')">修改</a></td>
	                </tr>
                </c:forEach>
              </table>
              <input id="fcr_id" type="hidden">
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
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />

<!--提成奖励策略新增弹出层-->
<div id="jlcl-xz" class="white_content">
	<p class="close03">新增</p>
    <div class="nr nr01">
    	<div class="fuws_detail tdz-detail">
          <div class="bangd_left fuws_left">
          	 <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 策略名称：</label>        
        	 </p>
             <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 团队长提成比例：</label>        
        	 </p>
             <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 业务员提成比例：</label>        
        	 </p>
  
          </div>
          <div class="bangd_right fuws_right">
				<p class="clearfix">
                	<input value="" id="roleName" class="w180">
                </p>
                <p class="clearfix">
                	<input value="" id="teamProportion" class="w180"> %
                </p>
                <p class="clearfix">
                	<input value="" id="clerkProportion" class="w180"> %
                </p>
   
          </div>
        </div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('jlcl-xz').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:add()" style=" background-color:#64a1ce;" onclick = "">确认</a>
                                                        
     </div>   
</div>
<!--提成奖励策略修改弹出层-->
<div id="jlcl-xg" class="white_content">
	<p class="close03">修改</p>
    <div class="nr nr01">
    	<div class="fuws_detail tdz-detail">
          <div class="bangd_left fuws_left">
          	 <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 策略名称：</label>        
        	 </p>
             <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 业务员提成比例：</label>        
        	 </p>
             <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 团队长提成比例：</label>        
        	 </p>
  
          </div>
          <div class="bangd_right fuws_right">
				<p class="clearfix">
                	<input id="roleName1" value="" class="w180 bg-gray" onclick="alert('策略名称不能修改！');" onfocus="this.blur()">
                </p>
                <p class="clearfix">
                	<input id="teamProportion1" value="" class="w180"> %
                </p>
                <p class="clearfix">
                	<input id="clerkProportion1" value="" class="w180"> %
                </p>
   
          </div>
        </div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('jlcl-xg').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:update()" style=" background-color:#64a1ce;" onclick = "">确认</a>
                                                        
     </div>   
</div>

<!--提成奖励策略删除弹出层：第一种情况-->
	<div id="jlcl-sc01" class="white_content">
		<p class="close03">删除</p>
		<div class="nr nr01">
			<div class="kaquan_list">
				<p style="line-height:40px; font-size:24px">你确定要删除该信息吗？<p>
			</div>
		</div>
		<div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('jlcl-sc01').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:deleteFqCommissionRole()" style=" background-color:#64a1ce;" onclick = "">确认</a>
                                                        
     </div> 
	</div>
    
    <!--提成奖励策略删除弹出层：第二种情况-->
	<div id="jlcl-sc02" class="white_content">
		<p class="close03">删除</p>
		<div class="nr nr01">
			<div class="kaquan_list">
				<p style="line-height:40px; font-size:24px">该策略有绑定的业务无法删除！<p>
			</div>
		</div>
		<div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('jlcl-sc02').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
                                                        
     </div> 
	</div>
 

<div id="fade" class="black_overlay"></div>
</body>
</html>