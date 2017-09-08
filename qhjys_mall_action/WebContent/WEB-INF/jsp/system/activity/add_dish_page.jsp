<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>

<link href="/qhjys_mall/seller-manage/css/membernew.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/activity/disp_list.js"></script>
<script type="text/javascript" src="/js/system/activity/list.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<link type="text/css" rel="stylesheet" href="/css/uploadify.css" />
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="2" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/product/uLeft.jsp" flush="true" ><jsp:param name="val" value="6" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">活动管理——添加菜品</h3> 
          <div class="member_myorder">
          	<form id="addform" action="/managermall/systemmall/activity/add_dish.do" method="post"  
          	onsubmit="javascript:if(window.confirm('你确定要添加吗？')){return true;}else{ return false;}">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="td100">活动ID：</td>
                        <td>
			                   <select name="lotteryId">
			                   			<c:forEach items="${lotterys}" var="store">
			                   				<option value="${store.id}">${store.lotteryName}</option>
			                   			</c:forEach>
			                   </select>	     
                        </td>
                      </tr>
                      <tr>
                        <td class="td100">奖项等级：</td>
                        <td>
                        	<select name="rankLevel">
			                   				<option value="1">1</option>
			                   				<option value="2">2</option>
			                   				<option value="3">3</option>
			                   				<option value="4">4</option>
			                   </select>	   
                        </td>
                      </tr>
                      <tr>
                        <td class="td100">菜品名称：</td>
                        <td><input class="con-email5" id="dishName" name="dishName" value="" ></td>
                      </tr>
               
                      <tr>
                        <td class="td100">菜品图片：</td>
                        <td>
                        
                   <!--      <input class="con-email5" id="dishImage" name="dishImage" value="" > -->
                        <img  id="img1"  src="" width="150" height="120" alt="" />
	        			<input type="hidden" id="img1u" name="dishImage" value=" "/>
	        		  <input  class="uploadify" type="file" value="上传" id="update1"/>
                        
                        </td>
                      </tr>
                      
                	 <tr>
                        <td class="td100">菜品说明：</td>
                        <td><input class="con-email5" id="dishInfo" name="dishInfo" value="" ></td>
                      </tr>
	        	
	        		 <tr>
                        <td class="td100">兑换限制人数：</td>
                        <td>
                        	<select name="limitNum">
                        		<option value="1">1</option>
                        		<option value="2">2</option>
                        		<option value="3">3</option>
                        		<option value="4">4</option>
                        		<option value="5">5</option>
                        		<option value="6">6</option>
                        		<option value="7">7</option>
                        		<option value="8">8</option>
                        		<option value="9">9</option>
                        		<option value="10">10</option>
                        		<option value="11">11</option>
                        		<option value="12">12</option>
                        		<option value="13">13</option>
                        		<option value="14">14</option>
                        		<option value="15">15</option>
                        		<option value="16">16</option>
                        		<option value="17">17</option>
                        		<option value="18">18</option>
                        		<option value="19">19</option>
                        		<option value="20">20</option>
                        		<option value="21">21</option>
                        		<option value="22">22</option>
                        		<option value="23">23</option>
                        		<option value="24">24</option>
                        		<option value="25">25</option>
                        		<option value="26">26</option>
                        		<option value="27">27</option>
                        		<option value="28">28</option>
                        		<option value="29">29</option>
                        		<option value="30">30</option>
                        		<option value="31">31</option>
                        		<option value="32">32</option>
                        		<option value="33">33</option>
                        		<option value="34">34</option>
                        		<option value="35">35</option>
                        		<option value="36">36</option>
                        		<option value="37">37</option>
                        		<option value="38">38</option>
                        		<option value="39">39</option>
                        		<option value="40">40</option>
                        		<option value="41">41</option>
                        		<option value="42">42</option>
                        		<option value="43">43</option>
                        		<option value="44">44</option>
                        		<option value="45">45</option>
                        		<option value="46">46</option>
                        		<option value="47">47</option>
                        		<option value="48">48</option>
                        		<option value="49">49</option>
                        		<option value="50">50</option>
                        		<option value="51">51</option>
                        		<option value="52">52</option>
                        		<option value="53">53</option>
                        		<option value="54">54</option>
                        		<option value="55">55</option>
                        		<option value="56">56</option>
                        		<option value="57">57</option>
                        		<option value="58">58</option>
                        		<option value="59">59</option>
                        		<option value="60">60</option>
                        	</select>
                        </td>
                      </tr>
               		 <tr>
                        <td class="td100">校验时间：</td>
                     	<td><input id="begin" name="beginTime" class="easyui-datebox con-time" value=""></td>
                      </tr>
               
                   
                      <tr>
                        <td class="td100"></td>
                        <input  name="token" type="hidden" value="${addDishPageToken}"/>
                        <td><input id="toaddreb" type="submit" value="确定" class="submit9" >&nbsp;&nbsp;</td>
                      </tr>
                    </table></form>
            </div>
            <!--上一页下一页-->
       
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
