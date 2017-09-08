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
        <script type="text/javascript">
        	$(function(){
        		 if ("${hangyeType2}" !="") {
        			 $('#hangyeType2').combobox('select',${hangyeType2});
        		 } else {
        			 $('#hangyeType2').combobox('select',"");
        		 }
        		 if ("${hangyeType}" !="") {
        			 $('#hangyeType').combobox('select',${hangyeType});
        		 } else {
        			 $('#hangyeType').combobox('select',"");
        		 }
        	})
        	
        	function Excel() {
				if (confirm("是否确认导出报表?")) {
				    var b = $("#from").serialize();
				    var a = "list_export.do?" + b;
				    window.open(a);
				}
			};
			
			function allAuthcation(){
				if (confirm("技术人员才能操作，确定运行？")) {
					window.location.href = "/managermall/systemmall/store/allZfbAuthcation.do"; 
				}
			}
        </script>
        </head>
        <body>
        <jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>
        <!--------------------------我的账户-------------------------------->
        <div class="membercontent">
        <!----------------左侧----------------------->
        <jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="1"/></jsp:include>
        <!-- end -->
        <div class="memberright">
        <h3 style="padding-left:10px; margin-bottom:25px;">商家管理——店铺列表</h3>
        <form id="from"  class="zcform1" method="post" action="list.do">
        <input id="page" name="pageNum" type="hidden">
        <p class="clearfix">
        <label class="one" for="con-email4" >创建时间：</label>
        <input id="begin" name="createStart" style="width:100px;" class="easyui-datebox con-time" value="${createStart}">
        <label class="one" for="con-email2">-</label>
        <input id="ending" name="createEnd"  style="width:100px;" class="easyui-datebox con-time" value="${createEnd}">

        <label class="one" for="con-email4" >商家注册名：</label>
        <input class="con-email4" name="username" value="${username}">
        <label class="one" for="con-email4" >店铺名称：</label>
        <input class="con-email4" name="storeName" value="${storeName}"><br/><br/>
        <label class="one" for="con-email4" >店铺ID：</label>
        <input class="con-email4" name="storeId" value="${storeId}">
        <label class="one" for="con-email4" >业务员：</label>
        <input class="con-email4" name="yewuyuan" value="${yewuyuan}">
        <label class="one" for="con-email4" >行业类型：</label>
        <select id="hangyeType2" name="hangyeType2" class="easyui-combobox"></select>
        <select id="hangyeType" name="hangyeType" class="easyui-combobox"></select>
        </p>
        <p>
        <label class="one" for="con-email4" >点餐状态：</label>
        	<select name="orderStatus">
	            <option value="">全部</option>
	            <option value="0"<c:if test="${orderStatus ==0 }">selected="selected"</c:if>>不开通</option>
	            <option value="1" <c:if test="${orderStatus ==1 }">selected="selected"</c:if>>开通</option>
            </select>
        <label class="one" for="con-email4" >收银台状态：</label>
        	<select name="cashierStatus">
		        <option value="">全部</option>
		        <option value="0"<c:if test="${cashierStatus ==0 }">selected="selected"</c:if>>关闭</option>
		        <option value="1" <c:if test="${cashierStatus ==1 }">selected="selected"</c:if>>开启</option>
            </select>
        <label class="one" for="con-email4" style="margin-left:17px">审核状态：</label>
	        <select name="status">
             <option value="">全部</option>
             <option value="0"<c:if test="${status ==0 }">selected="selected"</c:if>>未审核</option>
             <option value="1" <c:if test="${status ==1 }">selected="selected"</c:if>>审核未通过</option>
             <option value="2" <c:if test="${status ==2 }">selected="selected"</c:if>>审核通过</option>
            </select>
        </p>


        <p class="clearfix" style=margin-top:18px;>
        <label class="one" for="con-email4" >费率套餐：</label>
	         <select name="rateStatus">
             	<option value="">全部</option>
             	<c:forEach items="${fqStoreRates }" var="fsr">
		        	<option value="${fsr.id }" <c:if test="${rateStatus == fsr.id }">selected="selected"</c:if>>${fsr.rateName }</option>
		        </c:forEach>
            </select>
        
        <label class="one" for="con-email4" style="margin-left: 10px;">微信进件：</label>
	        <select name="wxAuthState" class="choice-content" style="margin-left:-5px!important;">
		        <option value="">全部</option>
		        <option value="0" <c:if test="${wxAuthState eq 0 }">selected="selected"</c:if>>未进件</option>
		        <option value="1" <c:if test="${wxAuthState eq 1 }">selected="selected"</c:if>>进件中</option>
		        <option value="2" <c:if test="${wxAuthState eq 2 }">selected="selected"</c:if>>成功</option>
		        <option value="3" <c:if test="${wxAuthState eq 3 }">selected="selected"</c:if>>失败</option>
        	</select>
        <label class="one" for="con-email4" style="margin-left: 10px;">支付宝进件：</label>
	        <select name="zfbAuthState" class="choice-content" style="margin-left:-5px!important;">
		        <option value="">全部</option>
		        <option value="0" <c:if test="${zfbAuthState eq 0 }">selected="selected"</c:if>>未进件</option>
		        <option value="1" <c:if test="${zfbAuthState eq 1 }">selected="selected"</c:if>>进件中</option>
		        <option value="2" <c:if test="${zfbAuthState eq 2 }">selected="selected"</c:if>>成功</option>
		        <option value="3" <c:if test="${zfbAuthState eq 3 }">selected="selected"</c:if>>失败</option>
	        </select>
	   
		<button class="out-form submit9" type="button" style="float:right;" onclick="Excel()">导出报表</button>
		 <button class="find-store submit8" style="float:right;">查询</button>
		</P>
      <p class="clearfix">
        <%--<input type="button" value="审核通过" class="submit9" onclick="updateStauts(2)">--%>
        <%--<label for="select">&nbsp;&nbsp;&nbsp;</label>--%>
        <%--<input type="button" value="审核不通过" class="submit9" onclick="updateStauts(1)">--%>
        <%-- <%--<label for="select">&nbsp;&nbsp;&nbsp;</label>--%>
        <input type="button" value="需授权" class="submit9" onclick="updateScope(1)">
        <label for="select">&nbsp;&nbsp;&nbsp;</label>
        <input type="button" value="无需授权" class="submit9" onclick="updateScope(2)">

        <label for="select">&nbsp;&nbsp;&nbsp;</label>
        <input type="button" value="开启收银台" class="submit9" onclick="updateCashier(1)">
        <label for="select">&nbsp;&nbsp;&nbsp;</label>
        <input type="button" value="关闭收银台" class="submit9" onclick="updateCashier(0)">

        <label for="select">&nbsp;&nbsp;&nbsp;</label>
        <input type="button" value="开通收点餐" class="submit9" onclick="updateOrder(1)">
        <label for="select">&nbsp;&nbsp;&nbsp;</label>
        <input type="button" value="不开通点餐" class="submit9" onclick="updateOrder(0)">
        <label for="select">&nbsp;&nbsp;&nbsp;</label>
        <input type="button" value="兴业进件信息编辑" style="width:115px;" class="submit9" onclick="editXyAuthcationInfo()">
        <input type="button" value="通道设置" style="width:60px;" class="submit9" onclick="setTongDao()">
        <!-- <input type="button" value="技术人员操作按钮" style="width:115px;" class="submit9" onclick="allAuthcation()"> -->
        </p>

        </form>

        <div class="member_myorder" id="store-list">
        <table id="table" border="0" cellspacing="0" cellpadding="0">
        <tr>
              <th class="td40">
              <input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/>
              <th class="td50">店铺ID</th>
              <th class="td80">注册名</th>
              <th class="td80">店铺名称</th>
             <!--  <th class="td160">评分</th>
              <th class="td160">推荐店铺</th>   -->
              <th class="td50">邀请码</th>
              <th class="td50 th20">审核<br>状态</th>
              <th class="td60 th20">微信授<br>权状态</th>
              <th class="td50 th20">收银台<br>状态</th>
              <th class="td50 th20">点餐<br>状态</th>
              <!--<th class="td100">基础费率</th>
              <th class="td100">支付费率</th> -->
              <th class="td80">创建时间</th>        
              <th class="td100">费率设置</th>
              <th class="td100">行业类型</th>
              <th class="td80">银行通道</th>
              <th class="td80">渠道绑定</th>
              <th class="td80">微信进件</th>
              <th class="td80">支付宝进件</th>
        	  <th class="td50" style="width:80px;">操作</th>
        </tr>
        <c:forEach items="${page}" var="s">
            <tr>
             <td class="td40">
                   	 <input name="ids" type="checkbox" value="${s.id}" onclick="xuan('dx')" />
                   	 </td>
                    <td class="td50 tdfont">${s.id}</td>
                    <td class="td80 tdfont">${s.sellerName }</td>
                    <td class="td80 th20">${s.name }</td>
                     <td class="td50 tdfont">${s.invite }</td>
                   <!--  <td class="td160">5</td>
                    <td class="td160">
                    <img src="images/tu01.png" width="16" height="16" /></td>  --> 
                    <td class="td50 th20">
                    	<c:if test="${s.status == 0  }">未审核</c:if>
                    	<c:if test="${s.status == 1  }">审核<br>失败</c:if>
                    	<c:if test="${s.status == 2  }">审核<br>通过</c:if>
                    </td>
                    <td class="td60 th20">
                        <c:if test="${s.scope != 2  }">需确认<br>授权</c:if>
                    	<c:if test="${s.scope == 2  }">无需确认<br>授权</c:if>
                    	
                    </td>
                    <td class="td50">
                        <c:if test="${s.openCashier == 0  }">关闭</c:if>
                    	<c:if test="${s.openCashier == 1  }">开启</c:if>
                    </td>
                    <td class="td50">
                        <c:if test="${s.openOrder == 0  }">不开通</c:if>
                    	<c:if test="${s.openOrder == 1  }">开通</c:if>
                    </td>
                    <!--<td class="td100 tdfont">
                        <p>支付宝:0.00%</p>
                        <p>微信:0.00%</p>
                        <p>QQ钱包:0.00%</p>
                    </td>
                    <td class="td100 tdfont">
                        <p>支付宝:0.00%</p>
                        <p>微信:0.00%</p>
                        <p>QQ钱包:0.00%</p>
                    </td> -->
                    <td class="td80 tdfont"><fmt:formatDate value="${s.createDate}" pattern="yyyy-MM-dd "/></td> 
                    
                    <td class="td100">
                    	<c:if test="${!empty s.rateName || s.rateName != ''}">${s.rateName }</c:if>
                    	<c:if test="${empty s.rateName || s.rateName=='' }">-</c:if> | T+${s.statementPeriod }
                    	<p><button onclick="getId('${s.id}','${s.rateId }','${s.statementPeriod }')">设置 </button></p>
                        
                    </td> 
                    <td class="td80">
                    	<p><c:if test="${s.categoryId>2 && s.categoryId<30 }">餐饮| ${s.categoryDetails }</c:if>
                    	<c:if test="${s.categoryId>29 }">非餐饮| ${s.categoryDetails }</c:if> 
                    	<c:if test="${s.categoryId<2 }">未设置</c:if></p>
                    	<p><button onclick="getcategoryId('${s.id}','${s.categoryId}')">设置 </button></p>
                        
                    </td>
                    <td class="td80">
                    	<p>
	                    	<c:if test="${s.channelValidation == 0}">原生通道</c:if>
	                    	<c:if test="${s.channelValidation == 1}">广州民生通道</c:if>
	                    	<c:if test="${s.channelValidation == 2}">深圳兴业直连</c:if>
	                    	<c:if test="${s.channelValidation == 3}">深圳兴业间连</c:if>
                    	</p>
                    	<%-- <p><button onclick="getPayChnnel('${s.id}')">设置</button></p> --%>
                        
                    </td>
                    <td class="td80">
                    	<p><c:if test="${not empty s.clerkName }">${s.clerkName }</c:if>
                    	<c:if test="${empty s.clerkName }">-</c:if></p>
                    	<p><button onclick = "setBinding('${s.id}','${s.clerkId}')">改绑</button></p>
                        
                    </td>
                    <td class="td50">
                    	<p>
                    		<c:if test="${s.wxAuthState eq 0}">未进件</c:if>
                    		<c:if test="${s.wxAuthState eq 1}">进件中</c:if>
               				<c:if test="${s.wxAuthState eq 2}">成功</c:if>
               				<c:if test="${s.wxAuthState eq 3}">失败</c:if>
                    	</p>
                    </td>
                     <td class="td50">
                    	<p>
                    		<c:if test="${s.zfbAuthState eq 0}">未进件</c:if>
                    		<c:if test="${s.zfbAuthState eq 1}">进件中</c:if>
               				<c:if test="${s.zfbAuthState eq 2}">成功</c:if>
               				<c:if test="${s.zfbAuthState eq 3}">失败</c:if>
                    	</p>
                    </td>
		            <td class="td80">
		                <ul>
		                    <li>
		                    	<a type="button" onclick="javascript:window.location.href='getStoreDetail.do?id=${s.id}'" href="#" style="border-right:1px solid #666;padding-right:5px;">审核</a>
		                    	<!-- <button disabled="value" style="padding:5px;border:none;color:#666;cursor:pointer;">审核</button> -->
		                        <a  onclick="authcation(${s.id},${s.status})" style="padding:5px;color:fff;cursor:pointer;">进件</a>
		                    	<!-- <button disabled="value"  style="padding:5px;border:none;color:#666;cursor:pointer;">进件</button> -->
		                     </li>
		                </ul>
		            </td>
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
		    	<div class="title">店铺类型设置<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('hangyelx').style.display='none';document.getElementById('fade').style.display='none'">×</a></span></div>
				<div class="nr">
		        	<p>大 行 业：
		        	 <input type="hidden" id="categorystoreId" value="" />
		        		<select id="categoryId2" name="categoryId2" class="easyui-combobox" style="width:130px;height:32px"></select></p><br>
		                <p>细分行业：
		        		<select id="categoryId" name="categoryId" class="easyui-combobox" style="width:130px;height:32px"></select></p><br>
		            <p><input  class="submit8" name="" type="submit" value="确认" onclick="return setcategoryId();"/>
		            	<input  class="submit8" style="margin:0 0 0 20px;" name="" type="reset" value="取消" onclick="document.getElementById('hangyelx').style.display='none';document.getElementById('fade').style.display='none'"/></p>
		        </div> 
		    </div>
		</div>
		
		<!--支付渠道设置弹出层-->
		<div id="payChnnel" class="white_content_new1">	
		    <div class="m-windowbox400">
		    	<div class="title">支付渠道设置<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('payChnnel').style.display='none';document.getElementById('fade').style.display='none'">×</a></span></div>
				<div class="nr">
		        	<p>渠道：
		        		<input type="hidden" id="payChnnelStoreId" value="" />
		        		<select id="channelValidation" name="channelValidation" style="width:130px;height:32px">
		        		
		        		</select>
		        	</p>
		        	<br />
		            <p><input  class="submit8" name="" type="submit" value="确认" onclick="return setPayChnnel();"/>
		            	<input  class="submit8" style="margin:0 0 0 20px;" name="" type="reset" value="取消" onclick="document.getElementById('payChnnel').style.display='none';document.getElementById('fade').style.display='none'"/></p>
		        </div> 
		    </div>
		</div>
		
		<!--支付渠道设置弹出层-->
		<div id="tongDaoPopUp" class="white_content_new1">	
		    <div class="m-windowbox400">
		    	<div class="title">支付渠道设置<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('tongDaoPopUp').style.display='none';document.getElementById('fade').style.display='none'">×</a></span></div>
				<div class="nr">
		        	<p>渠道：
		        		<input type="hidden" name="tongdaoIds" id="tongdaoIds" />
		        		<select name="tongdaoVal" id="tongdaoVal" style="width:130px;height:32px">
		        			<option value="0">原生通道</option>
				        	<option value="1">广州民生直连</option>
				        	<option value="2">深圳兴业直连</option>
				        	<option value="3">深圳兴业间连</option>
		        		</select>
		        	</p>
		        	<br />
		            <p><input  class="submit8" name="" type="submit" value="确认" onclick="return setTongDaoSubmit();"/>
		            	<input  class="submit8" style="margin:0 0 0 20px;" name="" type="reset" value="取消" onclick="document.getElementById('tongDaoPopUp').style.display='none';document.getElementById('fade').style.display='none'"/></p>
		        </div> 
		    </div>
		</div>
		
		<!--兴业银行进件信息编辑弹出层-->
		<div id="xyEditInfo" class="white_content_new1">	
		    <div class="m-windowbox400">
		    	<div class="title">兴业进件信息设置<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('xyEditInfo').style.display='none';document.getElementById('fade').style.display='none'">×</a></span></div>
				<div class="nr">
					<p>店铺ID：
		        		<!-- <input type="hidden" id="xyStoreId" value="" /> -->
		        		<input type="text" disabled="disabled" id="xyStoreId" value="" />
		        	</p><br />
		        	<p>商户ID：
		        		<input type="text" id="xyNumber" value="" />
		        	</p><br />
		        	<p>密钥：
		        		<input type="text" id="xyKey" value="" />
		        	</p>
		        	<br />
		            <p><input  class="submit8" name="" type="submit" value="确认" onclick="return setXyEditAucation();"/>
		            	<input  class="submit8" style="margin:0 0 0 20px;" name="" type="reset" value="取消" onclick="document.getElementById('xyEditInfo').style.display='none';document.getElementById('fade').style.display='none'"/></p>
		        </div> 
		    </div>
		</div>
		
        <!--进件信息弹出层-->
        <div id="msAuthcation" class="white_content_new1">
        <div class="m-windowbox400">
        <div class="title">进件渠道<span class="close"><a href="javascript:void(0)" onclick="document.getElementById('msAuthcation').style.display='none';document.getElementById('fade').style.display='none'">
        ×</a></span></div>
        <div class="nr">
        <p><span style="color:red;margin-left: 27px;">*</span>银行：
        <input type="hidden" id="authcationId" value="" />
        <select name="authBank" id="authBank" style="width:130px;height:32px">
        	<option value="1">广州民生银行</option>
        </select></p><br>
        <p><span style="color:red;">*</span>支付通道：
        <select name="anthenticationType" id="anthenticationType" style="width:130px;height:32px">
        	<option value="0">全部</option>
        	<option value="1">微信</option>
        	<option value="2">支付宝</option>
        </select></p><br>
        <p><input  class="submit8" name="" type="submit" value="确认" onclick="setAuthcation();"/>
        <input onclick="document.getElementById('msAuthcation').style.display='none';document.getElementById('fade').style.display='none'"  class="submit8" style="margin:0 0 0 20px;" name="" type="reset" value="取消" /></p>
        </div>
        </div>
        </div>
        
		<!-- 更改渠道提示框 -->
		<div class="success-cash" id="success-cash" style="display:none;">
	 		<div class="success-box">
	 		<div class="success-contents">
	 			<h3 id="cash-head"></h3>
	 			<div class="success-content" id="cash-content">
	 			
	 			</div>
	 			</div>
	 			<button onclick="document.getElementById('success-cash').style.display= 'none';window.location.reload();">关闭</button>
	 		</div>
	 	</div>
 	
        <div id="fade" class="black_overlay"></div>
        </body>

        </html>
