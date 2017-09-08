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
<script type="text/javascript" src="/js/system/mallusermanage/list.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/system/store/store_list1.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>
<!--------------------------我的账户-------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="1"/></jsp:include>
	<!-- end -->
		<div class="memberright">
             <h3 style="padding-left:10px; margin-bottom:25px;">店铺列表</h3>
        <form id="from"  class="zcform1" method="post" action="list.do">
        	<p class="clearfix">
        	<label class="one" for="con-email4" >创建时间：</label>
        	<input id="begin" name="createStart" class="easyui-datebox con-time" value="${createStart}">
			<label class="one" for="con-email2">-</label>
			<input id="ending" name="createEnd" class="easyui-datebox con-time" value="${createEnd}">
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <label class="one" for="con-email4" >费率套餐：</label> 
            <select name="cashierStatus" style="width:118px;">
		        <option value="">全部</option>
		        <option value="0">餐饮服务类</option>
		        <option value="1">餐饮超市类</option>
            </select>
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <label class="one" for="con-email4" >结算周期：</label> 
            <select name="cashierStatus" style="width:118px;">
		        <option value="">全部</option>
		        <option value="0">D+0</option>
		        <option value="1">T+1</option>
            </select>
             
       	  </p>
         <input id="page" name="pageNum" type="hidden">
           <p class="clearfix">  
           
        	<label class="one" for="con-email4" >店铺ID：</label>
        	<input class="con-email4" name="storeId" value="${storeId}"> 
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>	
            <label class="one" for="con-email4" >店铺名称：</label>
        	<input class="con-email4" name="storeName" value="${storeName}">
           
            <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <label class="one" for="con-email4" >业务员：</label>
        	<input class="con-email4" name="username" value="${username}">
           </p>
           
       	  <p>
            <label class="one" for="con-email4" >行业类型：</label> 
            <select name="cashierStatus">
		        <option value="">全部</option>
		        <option value="0">餐饮</option>
		        <option value="1">非餐饮</option>
            </select>
            <select name="cashierStatus">
		        <option value="">全部</option>
		        <option value="0">粤菜</option>
		        <option value="1">卤菜</option>
                <option value="2">湘菜</option>
                <option value="3">川菜</option>
            </select>
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <label class="one" for="con-email4" >点餐状态：</label> 
            <select name="orderStatus" style="width:118px;">
	            <option value="">全部</option>
	            <option value="0">开通</option>
	            <option value="1">不开通</option>
            </select>
            <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
       	  	<label class="one" for="con-email4" >收银台状态：</label> 
            <select name="cashierStatus" style="width:118px;">
		        <option value="">全部</option>
		        <option value="0">开通</option>
		        <option value="1">不开通</option>
            </select>
   
            <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <label class="one" for="con-email4" >状态：</label> 
            <select name="rateStatus" style="width:118px;">
             	<option value="">全部</option>
		        	<option value="">未进件</option>
                    <option value="">进件中</option>
                    <option value="">进件成功</option>
                    <option value="">进件失败</option>
                    <option value="">未审核</option>
                    <option value="">审核通过</option>
            </select>
       	  </p>
       	  <p class="clearfix">

       	  </p>
          
           <p class="clearfix">
             <input type="submit" value="查询" class="submit8">
              <label for="select">&nbsp;</label>       
             <input type="button" value="进件" class="submit8" onclick="">
              <label for="select">&nbsp;</label>       
             <input type="button" value="导出报表" class="submit9" onclick="Excel()">
     
           </p>
           
        </form>
        
        <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td40">
                    <input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/></th>
                    <th class="td100">创建时间</th>
                    <th class="td80">注册名</th>
                    <th class="td80">店铺ID</th>
                    <th class="td80">店铺名称</th>
                    <th class="td80 th20">归属<br>业务员</th>
                    <th class="td80 th20">大行业</th>
                    <th class="td80 th20">细分行业</th>
                    <th class="td100">费率<br>套餐</th>
                    <th class="td80">结算<br>周期</th> 
                    <th class="td80 th20">点餐<br>状态</th>
                    <th class="td50 th20">收银台<br>状态</th>
                    <th class="td100">状态</th>                           
                    <th class="td100">操作</th>
                </tr>
                
                <tr>
                    <td class="td40">
                    <input name="ids" type="checkbox" value="${s.id}" onclick="xuan('dx')" /></td>
                    <td class="td100"></td>
                    <td class="td80"></td>
                    <td class="td80"></td>
                    <td class="td80"></td>
                    <td class="td80 th20"></td>
                    <td class="td80 th20"></td>
                    <td class="td80 th20"></td>
                    <td class="td100"></td>
                    <td class="td80"></td> 
                    <td class="td80 th20"></td>
                    <td class="td50 th20"></td>
                    <td class="td100"></td>                           
                    <td class="td100">审核 | 编辑</td>
                </tr>
      
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

<!--渠道绑定设置弹出层-->
<div id="qdbd" class="white_content">
	<p class="close03">绑定</p>
    <div class="nr nr01">
    	<div class="bangd_detail">
          <div class="bangd_left">
          	 <input type="hidden" id="store_id"/>
             <p class="clearfix">
            		<label class="con-email130" for="con-email4"><span class="textred">*</span> 区域：</label>
             </p>
             <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 公司：</label>        
        	 </p>
              <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 团队长：</label>        
        	 </p>
              <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 业务员：</label>        
        	 </p>
          </div>
          <div class="bangd_right">
          		<p class="clearfix">
            		<select prompt="请选择" id="licenseProvince" name="provinceId" class="easyui-combobox" style="width:130px;height:32px"></select>
                    <select prompt="请选择" id="licenseCity" name="cityId" class="easyui-combobox" style="width:130px;height:32px"></select>
           	 	</p>
                
                <p class="clearfix">
                	<select prompt="请选择" id="branchId" name="branchId" class="easyui-combobox" style="width:130px;height:32px"></select>
                </p>
                <p class="clearfix">
            		<select prompt="请选择" id="teamId" name="teamId" class="easyui-combobox" style="width:130px;height:32px"></select>
           	 	</p>
                
                <p class="clearfix">
                	<select prompt="请选择" id="clerkId" name="clerkId" class="easyui-combobox" style="width:130px;height:32px"></select>
                </p>
          </div>
        </div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('qdbd').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:void(0)" style=" background-color:#64a1ce;" onclick = "binding()">确认</a>
                                                        
     </div>   
</div>

<!--渠道改绑设置弹出层-->
<div id="qdgb" class="white_content">
	<p class="close03">改绑</p>
    <div class="nr nr01">
    	<div class="bangd_detail">
          <div class="bangd_left">
             <p class="clearfix">
            		<label class="con-email130" for="con-email4"><span class="textred">*</span> 区域：</label>
             </p>
             <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 公司：</label>        
        	 </p>
              <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 团队长：</label>        
        	 </p>
              <p class="clearfix">
                    <label class="one" for="con-email4" ><span class="textred">*</span> 业务员：</label>        
        	 </p>
          </div>
          <div class="bangd_right">
          		<p class="clearfix">
            		<select prompt="请选择" id="licenseProvince1" name="provinceId" class="easyui-combobox" style="width:130px;height:32px"></select>
                    <select prompt="请选择" id="licenseCity1" name="cityId" class="easyui-combobox" style="width:130px;height:32px"></select>
           	 	</p>
                
                <p class="clearfix">
                	<select prompt="请选择" id="branchId1" name="branchId" class="easyui-combobox" style="width:130px;height:32px"></select>
                </p>
                <p class="clearfix">
            		<select prompt="请选择" id="teamId1" name="teamId" class="easyui-combobox" style="width:130px;height:32px"></select>
           	 	</p>
                
                <p class="clearfix">
                	<select prompt="请选择" id="clerkId1" name="clerkId" class="easyui-combobox" style="width:130px;height:32px"></select>
                </p>
          </div>
        </div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('qdgb').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:void(0)" style=" background-color:#64a1ce;" onclick = "toBind()">确认</a>
                                                        
     </div>   
</div>


<!--费率设置弹出层-->
<div id="feilvset" class="white_content_new1">	
    <div class="m-windowbox400">
    	<div class="title">费率设置<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('feilvset').style.display='none';document.getElementById('fade').style.display='none'">
×</a></span></div>
		<div class="nr">
			<input type="hidden" id="storeId" value="${storeId }" />
            <p>店铺结算周期：
            	T+ <input style="width:40px;" name="period" id="period" type="text"> 天
            </p><br>
        	<p>设置提现费率：
        		<select id="rate">
        			<option value="">无费率</option>
		        	<c:forEach items="${fqStoreRates }" var="fsr">
		        		<option value="${fsr.id }">${fsr.rateName }</option>
		        	</c:forEach>
                </select></p><br>
            <p><input  class="submit8" name="" type="submit" value="确认" onclick="return setRate();"/>
            	<input  class="submit8" style="margin:0 0 0 20px;" name="" type="reset" value="取消" /></p>
        </div> 
    </div>
</div>
<!--行业类型弹出层-->
<div id="hangyelx" class="white_content_new1">	
    <div class="m-windowbox400">
    	<div class="title">店铺类型设置<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('hangyelx').style.display='none';document.getElementById('fade').style.display='none'">
×</a></span></div>
		<div class="nr">
        	<p>大 行 业：
        	 <input type="hidden" id="categorystoreId" value="" />
        		<select id="categoryId2" name="categoryId2" class="easyui-combobox" style="width:130px;height:32px"></select></p><br>
                <p>细分行业：
        		<select id="categoryId" name="categoryId" class="easyui-combobox" style="width:130px;height:32px"></select></p><br>
            <p><input  class="submit8" name="" type="submit" value="确认" onclick="return setcategoryId();"/>
            	<input  class="submit8" style="margin:0 0 0 20px;" name="" type="reset" value="取消" /></p>
        </div> 
    </div>
</div>
<div id="fade" class="black_overlay"></div>
</body>

</html>
