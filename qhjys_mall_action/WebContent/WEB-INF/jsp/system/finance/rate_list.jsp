<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">

<script src="/common/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/system/finance/rate_list.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="6" />
</jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="8" /></jsp:include>
	<!--------------右侧------------------>
    <div class="memberright">
      <h3 class="jinrongtitle">费率套餐列表</h3>
      <form id="from" name="from"  class="zcform1" method="post" action="/managermall/systemmall/finance/rateList.do">
        <input id="page" name="pageNum" type="hidden" value="${page.getPageNum() }">
        <p class="clearfix">
        	<label class="one" for="con-email4" >创建时间：</label>
        	<%-- 
        	<input id="startTime" name="startTime" class="easyui-datebox con-time" value="${startTime}">
			<label class="one" for="con-email2">-</label>
			<input id="endTime" name="endTime" class="easyui-datebox con-time" value="${endTime}">
			 --%>
			<input id="benginTime" name="startTime"  class="con-email3"  type="text" readonly="readonly" value="${startTime}"/>
        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'benginTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		    <label class="one" for="con-email4" >-</label>
		    <input id="endTime" name="endTime"  class="con-email3"  type="text" readonly="readonly" value="${endTime}"/>
        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'endTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/>
        	
        	          
          <label class="one" for="con-email4" >项目名称：</label>
          <input class="con-email4" name="rateName" value="${rateName}" >
          <input type="submit" value="查询" class="submit8">
          &nbsp;
          <input name="重置" type="reset" class="submit8" value="重置">
          &nbsp;
          <a href="javascript:void(0)" onclick="document.getElementById('addrate').style.display='block'; document.getElementById('fade').style.display='block'" class="submit8" style="display:inline-block; width:60px; height:28px; line-height:28px; text-align:center;">新增</a>
        </p>
      </form>
      <div class="member_myorder">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th class="td90">项次</th>
            <th class="td140">创建时间</th>
            <th class="td140">项目名称</th>
            <th class="td100">创建人</th>
            <th class="td160">基础费率</th>
            <th class="td160">附加费率</th>
            <th class="td140">操作</th>
          </tr>
          <c:forEach items="${page }" var="p">
          <tr>
            <td class="td90">${p.id }</td>
            <td class="td140"><fmt:formatDate value="${p.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td class="td140">${p.rateName }</td>
            <td class="td100">${p.adminUserName }</td>
            <td class="td160 th20">
            	<p>支付宝：${p.alipayBaseRate }%</p>
                <p>微信：${p.wechatBaseRate  }%</p>
                <p>QQ钱包：${p.qqpayBaseRate }%</p>
            </td>
            <td class="td160 th20">
            	<p>支付宝：${p.alipayAppendRate }%</p>
                <p>微信：${p.wechatAppendRate }%</p>
                <p>QQ钱包：${p.qqpayAppendRate }%</p>
            </td>
            <td class="td140"><a class="fontred" href="javascript:void(0)" onclick="deleteFun('deleterate','${p.id}')" type="submit">删除</a>&nbsp;&nbsp;<a class="fontblue" href="javascript:void(0)" onclick="detail('${p.id}')">修改</a></td>
          </tr>
          </c:forEach>
        </table>
      </div>
      <!--上一页下一页-->
      <div class="page">
        <c:if test="${page.getPageNum()>1}"><a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a></c:if>
        <c:choose>
          <c:when test="${page.getPages()<7}">
            <c:forEach var="i" begin="1" end="${page.getPages()}">
              <c:choose>
                <c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
                <c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise>
              </c:choose>
            </c:forEach>
          </c:when>
          <c:when test="${page.getPages()>6}">
            <c:forEach var="i" begin="1" end="3">
              <c:choose>
                <c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
                <c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise>
              </c:choose>
            </c:forEach>
            <c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
            <c:forEach var="i" begin="4" end="${page.getPages()-3}">
              <c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
            </c:forEach>
            <c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
            <c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
              <c:choose>
                <c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
                <c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise>
              </c:choose>
            </c:forEach>
          </c:when>
        </c:choose>
        <c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
        <span>共${page.getPages()}页</span><span>到第</span>
        <input class="input1" id="jumPage" name="pageNum" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/>
        <span>页</span></div>
    </div>
	<div class="clear"></div>
</div>

<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />

<!--新增弹出层-->
<div id="addrate" class="white_content_new">	
    <div class="m-windowbox">
    	<div class="title">新增<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('addrate').style.display='none';document.getElementById('fade').style.display='none'">
×</a></span></div>
		<div class="nrbox">
        	<p>项目名称：<input class="con-email350" name="rateName" id="rateName"></p>
            <p>费率设置：</p>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <th class="td70">&nbsp;</th>
                <th class="td90">支付方式</th>
                <th class="td90">基础费率</th>
                <th>附加费率</th>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>微信支付</td>
                <td>
                	<div class="xialabox">      
                        <span class="jiantou">      
                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value">     
                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select></span><input type="number" name="box" class="shurubox" id="wechatBaseRate">     

                    </div> 
                </td>
                <td>订单金额 > <input type="number" class="input100" name="userName" value="${userName}" id="wechatAppendMoney" > = 
                	<div class="xialabox">      
                        <span class="jiantou">      

                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value" >     

                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select></span><input type="number" name="box" class="shurubox" id="wechatAppendRate">     

                    </div>
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>支付宝</td>
                <td>
                    <div class="xialabox">      
                        <span class="jiantou">      
                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value">     
                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select></span><input type="number" name="box" class="shurubox" id="alipayBaseRate">     
                    </div> 
                </td>
                <td>订单金额 > <input type="number" class="input100" name="userName" value="${userName}" id="alipayAppendMoney" > = 
                	<div class="xialabox">      
                        <span class="jiantou">      
                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value" >     
                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select></span><input type="number" name="box" class="shurubox" id="alipayAppendRate">     
                    </div> </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>QQ钱包</td>
                <td>
                    <div class="xialabox">      
                        <span class="jiantou">      
                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value">     
                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select></span><input type="number" name="box" class="shurubox" id="qqpayBaseRate">     
                    </div> 
                </td>
                <td>订单金额 > <input type="number" class="input100" name="userName" id="qqpayAppendMoney" > = 
                	<div class="xialabox">      
                        <span class="jiantou">      
                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value">     
                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select></span><input type="number" name="box" class="shurubox" id="qqpayAppendRate">     
                    </div> </td>
              </tr>
            </table>
            <p class="tdcenter"><input  class="button200" name="" type="button" onclick="insertStoreRate()" value="确认" /></p>
        </div> 
    </div>
</div>
<!--删除弹出层-->
<div id="deleterate" class="white_content_new1">	
<input type="hidden" id="numId"/>
    <div class="m-windowbox400">
    	<div class="title">删除<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('deleterate').style.display='none';document.getElementById('fade').style.display='none'">
×</a></span></div>
		<div class="nr">
        	<p>确定要删除信息吗？</p><br>
            <p><input  class="submit8" name="" type="button" onclick="delet($('#numId').val());onLoad()" value="确认" />
            	<input  class="submit8" style="margin:0 0 0 20px;" name="" type="button" onclick="document.getElementById('deleterate').style.display='none';document.getElementById('fade').style.display='none'" value="取消" /></p>
        </div> 
    </div>
</div>
<!--修改弹出层-->
<div id="modifyrate" class="white_content_new">	
<input name="box" type="hidden" id="id">
    <div class="m-windowbox">
    	<div class="title">修改<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('modifyrate').style.display='none';document.getElementById('fade').style.display='none'">
×</a></span></div>
		<div class="nrbox">
			
        	<p>项目名称：<span id="rateName1"></span></p>
            <p>费率设置：</p>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <th class="td70">&nbsp;</th>
                <th class="td90">支付方式</th>
                <th class="td90">基础费率</th>
                <th>附加费率</th>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>微信支付</td>
                <td>
                    <div class="xialabox">      
                        <span class="jiantou">      
                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value" >     
                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select></span><input type="number" name="box" class="shurubox" id="wechatBaseRate1">   
                    </div> 
                </td>
                <td>订单金额 > <input type="number" class="input100" name="userName" value="${userName}" id="wechatAppendMoney1" > = 
                	<div class="xialabox">      
                        <span class="jiantou">      
                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value">     
                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select></span><input type="number" name="box" class="shurubox"  id="wechatAppendRate1"> 
                    </div> 
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>支付宝</td>
                <td>
                    <div class="xialabox">      
                        <span class="jiantou">      
                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value">     
                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select></span><input type="number" name="box" class="shurubox"  id="alipayBaseRate1">    
                    </div> 
                </td>
                <td>订单金额 > <input type="number" class="input100" name="userName" value="${userName}" id="alipayAppendMoney1" > = 
                	<div class="xialabox">      
                        <span class="jiantou">      
                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value">     
                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select></span><input type="number" name="box" class="shurubox"  id="alipayAppendRate1">   
                    </div> 
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>QQ钱包</td>
                <td>
                    <div class="xialabox">      
                        <span class="jiantou">      
                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value">     
                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select></span><input type="number" name="box" class="shurubox"  id="qqpayBaseRate1">  
                    </div> 
                </td>
                <td>订单金额 > <input type="number" class="input100" name="userName" value="" id="qqpayAppendMoney1" > = 
                	<div class="xialabox">      
                        <span class="jiantou">      
                        <select class="selectbox" onchange="this.parentNode.nextSibling.value=this.value" >     
                        <option value="">请选择 </option>   
                        <option value="0.00">0.00%</option>      
                        <option value="0.38">0.38%</option>      
                        <option value="0.60">0.60%</option>  
                        </select>
                        </span><input type="number" name="box" id="qqpayAppendRate1" class="shurubox">   
                    </div> 
                </td>
              </tr>
            </table>
            <p class="tdcenter"><input  class="button200" name="" type="button" onclick="updateRate($('#id').val())" value="确认" /></p>
        </div> 
    </div>
</div>
<div id="fade" class="black_overlay"></div>
</body>
</html>
