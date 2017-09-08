<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link href="/qhjys_mall/seller-manage/css/membernew.css" rel="stylesheet" type="text/css" />
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/system/product/rebate_list.js"></script>
<title>飞券网平台管理中心</title>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="2" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/product/uLeft.jsp" flush="true" ><jsp:param name="val" value="4" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
		<div class="memberright">
     	<h3 style="padding-left:10px; margin-bottom:25px;">折扣审核</h3>
    		<form id="from" name="from"  class="zcform1"  method="post">
    			<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
		        <p class="clearfix">
                	<label class="one" for="con-email4" >店铺名称：</label>
		        	<input class="con-email4" name="storeName" value="${storeName}" >
		        	<label class="one" for="con-email4" >折扣名称：</label>
		        	<input class="con-email4" name="rebateName" value="${rebateName}" >
		        </p>
   		        <p class="clearfix">
		        	<label class="one" for="con-email4" >日期：</label>
		        	<input id="benginTime" name="benginTime"  class="con-email3"  type="text" readonly="readonly"  value="${benginTime}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'benginTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		            <label class="one" for="con-email4" >-</label>
		            <input id="endTime" name="endTime"  class="con-email3"  type="text" readonly="readonly"  value="${endTime}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'endTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		            <label class="one" for="con-email4" >销售状态：</label>    
		            <select id="status" name="status">
		            <option value="-1">全部</option>
		            <option value="0" <c:if test="${status ==0 }">selected="selected"</c:if>>下架</option>
		            <option value="1" <c:if test="${status ==1 }">selected="selected"</c:if>>上架</option>
		            </select>
		            <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		            <input type="submit" value="查询" class="submit8">
		        </p>
        	</form>
         	<form id="form1" name="form1"  class="zcform1">
	           	<p class="clearfix">
	             	<input type="submit" value="上架" class="submit9" onclick="updatestatus(1)">
	             	<label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	             	<input type="submit" value="下架" class="submit9"  onclick="updatestatus(0)">
	             	<input style="top:0px;" type="button" value="添加折扣" class="tanchubutton"  onclick="openadddiv()">
	           	</p>
         	</form>
          	<div class="member_myorder">
            	<!--排序开始-->
            	<div id="paixu" class="tanchudiv" style="display:none;z-index: 100">

                	<h1>位置排序</h1>
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="td100">折扣名称：<input id="rebid" type="hidden" value=""></td>
                        <td id="rebaname"></td>
                      </tr>
                      <tr>
                        <td class="td100">店铺名称：</td>
                        <td id="stoname"></td>
                      </tr>
                      <tr>
                        <td class="td100">置顶等级：</td>
                        <td><input class="con-email4" id="reblavel"  value="" >&nbsp;&nbsp;(0-10)</td>
                      </tr>
                     
                      <tr>
                        <td class="td100"></td>
                        <td><input type="button" value="保存" class="submit9" onclick="changelevel()">&nbsp;&nbsp;<input id="paixuclose" type="button" class="submitgray" value="取消" onclick="paixuclose();"></td>
                      </tr>
                    </table>
                </div>
                <!--排序结束-->
                <!--添加折扣开始-->
            	<div id="adddiv" class="tanchudiv" style="display:none;z-index:500;top: 2px;">
            	<form id="addform" action="/managermall/systemmall/rebate/addRebate.do" method="post">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="td100">店铺ID：</td>
                        <td><input id="reid1" name="id" type="hidden">
                        <input class="con-email4" id="storeId" name="storeId" value="" >&nbsp;<input id="idbut" type="button" value="确定" class="submit8" onclick="getstorename()"></td>
                      </tr>
                      <tr>
                        <td class="td100">店铺名称：</td>
                        <td id="storename"></td>
                      </tr>
                      <tr>
                        <td class="td100">折扣名称：</td>
                        <td><input class="con-email5" id="rebateName1" name="rebateName" value="" ></td>
                      </tr>
                      <tr>
                        <td class="td100">开始日期：</td>
                        <td><input id="beginDate" name="beginDate"  class="con-email3"  type="text" readonly="readonly"  value=""/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'beginDate'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		            <label class="one" for="con-email4" > 请输入开始日期</label>
		            </td>
                      </tr>
                      <tr>
                        <td class="td100">结束日期：</td>
                        <td><input id="endDate" name="endDate"  class="con-email3"  type="text" readonly="readonly"  value=""/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'endDate'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 请输入开始日期</td>
                      </tr>
                      <tr>
                        <td class="td100">市场折：</td>
                        <td><input class="con-email4" id="ortherRebate1" name="ortherRebate" value="10" readonly="readonly">&nbsp;&nbsp;请输入市场折</td>
                      </tr>
                      <tr>
                        <td class="td100">折扣：</td>
                        <td><input class="con-email4" id="rebate1" name="rebate" value="10" readonly="readonly">&nbsp;&nbsp;请输入折扣</td>
                      </tr>
                       <tr>
                        <td class="td100">折扣说明：</td>
                        <td><input class="con-email5" id="zexplain" name="zexplain" value="" ></td> 
                      </tr>
                      <tr>
                        <td class="td100"></td>
                        <td><input id="toaddreb" type="button" value="确定" class="submit9" onclick="addrebate()">&nbsp;&nbsp;<a href="javascript:closeadddiv();"><input name="closeadddiv" type="button" class="submitgray" value="取消"/></a></td>
                      </tr>
                    </table></form>
                </div>
                <!--添加折扣结束-->
            	
         	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                	<th class="td40"><input id="selectAll" type="checkbox" value="" onclick="selectAll()" /></th>
                    <th class="td130">折扣名称</th>
                    <th class="td100">店铺名称</th>  
                    <th class="td60">折扣</th>
                    <th class="">位置</th>
                    <th class="td90">开始日期</th>
                    <th class="td90">结束日期</th>
                    <th class="td70">销售状态</th>
                    <th class="td80">操作</th>
                </tr>
				<c:forEach items="${page}" var="rebate">
					<tr>
						<td class="td40"><input name="ids" type="checkbox" value="${rebate.id}" /></td>
                    	<td class="td130">${rebate.rebateName}</td>
                    	<td class="td100">${rebate.storeName}</td>
                    	<td class="td60">${rebate.rebate }折</td>  
                    	<td align="center">${rebate.level }</td>
                    	<td class="td90"><fmt:formatDate value="${rebate.beginDate }" pattern="yyyy-MM-dd"/></td>
                    	<td class="td90" align="center"><fmt:formatDate value="${rebate.endDate }" pattern="yyyy-MM-dd"/></td>
                    	<c:if test="${rebate.status == 0}">
                    		<td class="td70">下架</td>
                    	</c:if>
                    	<c:if test="${rebate.status == 1}">
                    		<td class="td70">上架</td>
                    	</c:if>
                   		 <td class="td80"><a href="/managermall/systemmall/store/getStoreDetail.do?id=${rebate.storeId}" target="_Blank">查看</a> <a href="javascript:void(0)" onclick="paixuopen('${rebate.id}','${rebate.rebateName}','${rebate.storeName}','${rebate.level }')">排序</a><br>
                   		 <a href="javascript:openupdatediv('${rebate.id}','${rebate.storeId}','${rebate.rebateName}','${rebate.rebate}','${rebate.ortherRebate}','<fmt:formatDate value="${rebate.beginDate }" pattern="yyyy-MM-dd"/>','<fmt:formatDate value="${rebate.endDate }" pattern="yyyy-MM-dd"/>','${rebate.storeName}','${rebate.zexplain}')" onclick="">修改折扣</a></td>
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
               		<input class="input1" id="jumPage" name="page" type="text" value="${pageNum}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
</body>
</html>